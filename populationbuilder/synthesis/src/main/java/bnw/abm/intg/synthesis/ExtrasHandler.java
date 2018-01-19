package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.Log;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wniroshan 18 Dec 2017
 */
class ExtrasHandler {

    final double maleProbability;
    final Random random;
    final List<HhRecord> hhRecords;
    final List<IndRecord> indRecords;
    final private List<Person> extras;
    private List<Person> extraMarried = null;

    ExtrasHandler(List<HhRecord> hhRecords, List<IndRecord> indRecords, double sexRatio, Random random) {
        this.extras = this.getExtras(hhRecords, indRecords);
        this.maleProbability = sexRatio;
        this.random = random;
        this.hhRecords = hhRecords;
        this.indRecords = indRecords;
    }

    private List<Person> getExtras(List<HhRecord> hhrecs, List<IndRecord> indrecs) {
        int personsInHh = 0;
        int personsInInds = 0;
        List<Person> extras = new ArrayList<>();
        for (HhRecord hhRec : hhrecs) {
            personsInHh += (hhRec.HH_COUNT * hhRec.NUM_OF_PERSONS_PER_HH);
        }
        for (IndRecord inRec : indrecs) {
            personsInInds += inRec.IND_COUNT;
        }

        int extraPersons = personsInHh > personsInInds ? (personsInHh - personsInInds) : 0;
        for (int i = 0; i < extraPersons; i++) {
            extras.add(new Person());
        }
        return extras;
    }

    int remainingExtras() {
        return extras.size();
    }

    Person getPersonFromExtras(RelationshipStatus relStatus, AgeRange ageRange, Sex sex) {

        Person person = extras.remove(0);
        person.setAgeRange(ageRange);
        person.setRelationshipStatus(relStatus);
        person.setSex(sex);

        return person;
    }

    /**
     * Generates persons from the Extras based on specified RelationshipStatus, AgeRange and Sex characteristics. If a
     * characteristic is set to null, value for that particular characteristic is selected according to the observed
     * probability distribution of all categories under that characteristic. For example, if ageRange is null, but
     * relStatus and sex is given, ages are assigned to agents probabilistically based on the age distribution of
     * persons in the specified relStatus and sex category.
     *
     * @param relStatus The RelationshipStatus of the persons
     * @param sex       The Sex of the persons
     * @param ageRange  The AgeRange of the persons
     * @param count     The number of persons to create
     * @return The list of newly created persons persons
     */
    List<Person> getPersonsFromExtras(RelationshipStatus relStatus, Sex sex, AgeRange ageRange, int count) {

        List<Person> persons = new ArrayList<>(count);

        //If we want extra married persons, try to use them from previously saved extraMarried persons list.
        if (relStatus == RelationshipStatus.MARRIED) {
            persons.addAll(getFromExtraMarried(sex, ageRange, count));
        }
        count = count - persons.size();
        if (count > 0) {
            //Spawn the persons we want from Extras.
            List<IndRecord> dist = indRecords.stream()
                    .filter(r -> (relStatus == null || r.RELATIONSHIP_STATUS == relStatus)) //Filter by relationship
                    // status. If relationship status is null get all records
                    .filter(r -> (ageRange == null || r.AGE_RANGE == ageRange)) // Filter by age range, or get all if
                    // not specified
                    .filter(r -> (sex == null || r.SEX == sex))// Filter by sex, or get all if not specified
                    .collect(Collectors.toList());

            int sum = dist.stream().mapToInt(r -> r.IND_COUNT).sum();

            for (int i = 0; i < count; i++) {
                int offset = random.nextInt(sum);
                int s = 0;
                for (IndRecord r : dist) {
                    s += r.IND_COUNT;
                    if (offset < s) {
                        persons.add(getPersonFromExtras(r.RELATIONSHIP_STATUS, r.AGE_RANGE, r.SEX));
                        break;
                    }
                }
            }
        }
        return persons;
    }

    List<Person> getFromExtraMarried(Sex sex, AgeRange ageRange, int count) {
        List<Person> persons = new ArrayList<>(count);
        if (!(extraMarried.isEmpty() || extraMarried == null)) {
            List<Person> temp = extraMarried.stream()
                    .filter(p -> (ageRange == null || ageRange == p.getAgeRange()) && (sex == null || sex == p.getSex
                            ()))
                    .collect(
                            Collectors.toList());
            if (temp.size() >= count) {
                persons.addAll(temp.subList(0, count));
                extraMarried.removeAll(persons);
            } else {
                //We may want more married persons than we have. If so, get what we can and spawn the reset from Extras.
                persons.addAll(temp);
                extraMarried.removeAll(temp);
            }


        }
        return persons;
    }

    /**
     * Saves extra married persons for later use. These persons will be used to construct more couple relationships if
     * originally formed couples are not enough to complete the population. This method copies the list of persons
     * passed to a new list and clears the original list avoid errors.
     *
     * @param extraMarried The list of extra married persons. The list is expected to contain either male or female
     *                     persons, not both.
     */
    void setExtraMarriedPersons(List<Person> extraMarried) {
        this.extraMarried = new ArrayList<>(extraMarried);
        extraMarried.clear();
        Log.info("Saved extra-married " + this.extraMarried.get(0).getSex() + " for later use");
    }

    int remainingExtraMarried() {
        return extraMarried.size();
    }


    /**
     * Converts all the remaining extras to Children and Relatives. The characteristics of persons are determined
     * probabilistically based on observed distribution of children categories and relatives.
     *
     * @param includeExtraMarried indicates whether extra-married persons should also be converted to children.
     * @return Map of children and relatives by RelationshipStatus
     */
    Map<RelationshipStatus, List<Person>> convertAllExtrasToChildrenAndRelatives(boolean includeExtraMarried) {

        if (includeExtraMarried) {
            Log.debug("Remaining extra-married: " + remainingExtraMarried());
            Log.debug("Remaining extras after adding extra-married: " + remainingExtras());
            extras.addAll(extraMarried);
        }

        List<IndRecord> dist = indRecords.stream()
                .filter(r -> (r.RELATIONSHIP_STATUS == RelationshipStatus.O15_CHILD
                        || r.RELATIONSHIP_STATUS == RelationshipStatus.STUDENT
                        || r.RELATIONSHIP_STATUS == RelationshipStatus.U15_CHILD
                        || r.RELATIONSHIP_STATUS == RelationshipStatus.RELATIVE))
                .collect(Collectors.toList());

        int sum = dist.stream().mapToInt(r -> r.IND_COUNT).sum();
        Map<RelationshipStatus, List<Person>> persons = new HashMap<>(5, 1);
        int totalNewPersons = 0;
        int remainingExtras = remainingExtras();
        for (int i = 0; i < remainingExtras; i++) {
            int offset = random.nextInt(sum);
            int s = 0;
            for (IndRecord r : dist) {
                s += r.IND_COUNT;
                if (offset < s) {
                    persons.computeIfAbsent(r.RELATIONSHIP_STATUS, v -> {return new ArrayList<>();})
                            .add(getPersonFromExtras(r.RELATIONSHIP_STATUS, r.AGE_RANGE, r.SEX));
                    totalNewPersons++;
                    break;
                }
            }
        }
        Log.info("The Children and Relatives formed by converting extras: " + totalNewPersons);
        Log.debug("Extra O15_CHILD: " + persons.get(RelationshipStatus.O15_CHILD).size());
        Log.debug("Extra STUDENT: " + persons.get(RelationshipStatus.STUDENT).size());
        Log.debug("Extra U15_CHILD: " + persons.get(RelationshipStatus.U15_CHILD).size());
        Log.debug("Extra RELATIVE: " + persons.get(RelationshipStatus.RELATIVE).size());

        Log.debug("Remaining extras: " + remainingExtras());
        Log.debug("Remaining extra-married: " + remainingExtraMarried());

        return persons;
    }
}
