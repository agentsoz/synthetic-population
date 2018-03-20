package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.AgeRange;
import bnw.abm.intg.synthesis.models.Person;
import bnw.abm.intg.synthesis.models.RelationshipStatus;
import bnw.abm.intg.synthesis.models.Sex;
import bnw.abm.intg.util.Log;
import com.sun.istack.internal.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wniroshan 18 Dec 2017
 */
class ExtrasHandler {

    final Random random;
    private final List<IndRecord> indRecords;
    final private List<Person> extras;

    ExtrasHandler(List<HhRecord> hhRecords, List<IndRecord> indRecords, Random random) {
        this.extras = this.getExtras(hhRecords, indRecords);
        this.random = random;
        this.indRecords = indRecords;
    }

    private List<Person> getExtras(List<HhRecord> hhRecs, List<IndRecord> indRecs) {
        int personsInHh = 0;
        int personsInInd = 0;
        List<Person> extras = new ArrayList<>();
        for (HhRecord hhRec : hhRecs) {
            personsInHh += (hhRec.HH_COUNT * hhRec.NUM_OF_PERSONS_PER_HH);
        }
        for (IndRecord inRec : indRecs) {
            personsInInd += inRec.IND_COUNT;
        }

        int extraPersons = personsInHh > personsInInd ? (personsInHh - personsInInd) : 0;
        for (int i = 0; i < extraPersons; i++) {
            extras.add(new Person());
        }
        return extras;
    }

    int remainingExtras() {
        return extras.size();
    }

    /**
     * Generates persons from the Extras based on specified RelationshipStatus, AgeRange and Sex characteristics. If a characteristic is set
     * to null, value for that particular characteristic is selected according to the observed probability distribution of all categories
     * under that characteristic. For example, if ageRange is null, but relStatus and sex is given, ages are assigned to agents
     * probabilistically based on the age distribution of persons in the specified relStatus and sex category.
     *
     * @param relStatus The RelationshipStatus of the persons
     * @param sex       The Sex of the persons
     * @param ageRange  The AgeRange of the persons
     * @param count     The number of persons to create
     * @return The list of newly created persons persons
     */
    List<Person> getPersonsFromExtras(RelationshipStatus relStatus, Sex sex, AgeRange ageRange, int count) {

        List<Person> persons = new ArrayList<>(count);

        if (this.extras.size() >= count) {
            List<Person> selected = this.extras.subList(0, count);
            persons.addAll(selected);
            this.extras.removeAll(selected);
        } else {
            throw new NotEnoughPersonsException("There are not enough persons in extras. Requested: " + count + " available: " + this.extras
                    .size());
        }


        //Proportionally set the properties of persons we selected
        if (!persons.isEmpty()) {
            List<IndRecord> dist = indRecords.stream()
                                             .filter(r -> (relStatus == null || r.RELATIONSHIP_STATUS == relStatus) //filter by
                                                     // relationship. If relationship status is null get all records
                                                     && (ageRange == null || r.AGE_RANGE == ageRange)// Filter by age range, or get all
                                                     // if not specified
                                                     && (sex == null || r.SEX == sex))// Filter by sex, or get all if not specified
                                             .collect(Collectors.toList());

            int sum = dist.stream().mapToInt(r -> r.IND_COUNT).sum();

            for (Person p : persons) {
                if (p.getRelationshipStatus() != null && p.getSex() != null && p.getAgeRange() != null) {
                    continue;
                }
                int offset = random.nextInt(sum);
                int s = 0;
                for (IndRecord r : dist) {
                    s += r.IND_COUNT;
                    if (offset < s) {
                        setProperties(p, r.RELATIONSHIP_STATUS, r.SEX, r.AGE_RANGE);
                        break;
                    }
                }
            }
        }
        return persons;
    }

    /**
     * Generates children based on specified sex and ageRange. RelationshipStatus of all children produced with this method is assumed to be
     * U15_CHILD. If sex and ageRanges are given as null they are assigned probabilistically according to the observed population
     * distributions. TODO: Determine RelatioshipStatus of children in a more realistic method.
     *
     * @param sex      Sex of the children
     * @param ageRange AgeRange of the children
     * @param count    Number children to form
     * @return A list of children
     */
    List<Person> getChildrenFromExtras(Sex sex, AgeRange ageRange, int count) {
        return getPersonsFromExtras(RelationshipStatus.U15_CHILD, sex, ageRange, count);
    }

    /**
     * Get person instances from the specified list
     *
     * @param knownList The list of persons to take persons from
     * @param sex       The sex of the persons
     * @param ageRange  The age range of the persons
     * @param count     The number of persons to take
     * @return The list of new persons with properties already set.
     */
    private List<Person> getFromExtraPropertyKnownLists(@NotNull List<Person> knownList, Sex sex, AgeRange ageRange, int count) {
        List<Person> persons = new ArrayList<>(count);
        if (!(knownList.isEmpty())) {
            List<Person> temp = knownList.stream()
                                         .filter(p -> (ageRange == null || ageRange == p.getAgeRange()) && (sex == null || sex == p
                                                 .getSex()))
                                         .collect(
                                                 Collectors.toList());
            if (temp.size() >= count) {
                persons.addAll(temp.subList(0, count));
                knownList.removeAll(persons);
            } else {
                //We may want more married persons than we have. If so, get what we can and spawn the reset from Extras.
                persons.addAll(temp);
                knownList.removeAll(temp);
            }


        }
        return persons;
    }

    /**
     * Converts all the remaining extras to Children and Relatives. The characteristics of persons are determined probabilistically based on
     * observed distribution of children categories and relatives.
     *
     * @return Map of children and relatives by RelationshipStatus
     */
    Map<RelationshipStatus, List<Person>> convertAllExtrasToChildrenAndRelatives() {

        Log.debug("Remaining extras: " + remainingExtras());

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
                    Person p = this.extras.remove(0);
                    setProperties(p, r.RELATIONSHIP_STATUS, r.SEX, r.AGE_RANGE);
                    persons.computeIfAbsent(r.RELATIONSHIP_STATUS, v -> {
                        return new ArrayList<>();
                    }).add(p);
                    totalNewPersons++;
                    break;
                }
            }
        }
        Log.info("The Children and Relatives formed by converting extras: " + totalNewPersons);
        persons.entrySet().stream().forEach(e -> Log.debug("Extra " + e.getKey() + ": " + e.getValue().size()));

        Log.debug("Remaining extras: " + remainingExtras());

        return persons;
    }

    /**
     * Add persons to extras list. Each persons relationship status is set to null. Age and Sex is not changed. The input person list is
     * cleared.
     *
     * @param persons The list of persons to add to extras
     */
    void addToExtras(List<Person> persons) {
        persons.forEach(p -> {
            p.setRelationshipStatus(null);
            p.clearFamilyID();
        });
        this.extras.addAll(persons);
        persons.clear();
    }

    /**
     * Set properties of a person if they are not already set
     *
     * @param p         The person
     * @param relStatus Relationship Status
     * @param sex       Sex
     * @param ageRange  Age range
     */
    private void setProperties(Person p, RelationshipStatus relStatus, Sex sex, AgeRange ageRange) {
        if (p.getRelationshipStatus() == null)
            p.setRelationshipStatus(relStatus);
        if (p.getAgeRange() == null)
            p.setAgeRange(ageRange);
        if (p.getSex() == null)
            p.setSex(sex);
    }
}
