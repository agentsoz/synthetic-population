package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;

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

    ExtrasHandler(List<HhRecord> hhRecords, List<IndRecord> indRecords, double sexRatio, Random random) {
        this.extras = this.getExtras(hhRecords, indRecords);
        this.maleProbability = sexRatio;
        this.random = random;
        this.hhRecords = hhRecords;
        this.indRecords = indRecords;
    }

    /**
     * Adds required number of persons to the family from extras
     *
     * @param family             The family instance
     * @param relationshipStatus The type of the newly added persons
     * @param requiredMembers    The number of persons to add
     */
    void addMembersToFamilyFromExtras(Family family,
                                      RelationshipStatus relationshipStatus,
                                      int requiredMembers) throws NotEnoughPersonsException {
        List<AgeRange> agesList = new ArrayList<>(Arrays.asList(AgeRange.values()));
        for (int i = 0; i < requiredMembers; i++) {
            if (extras.isEmpty()) {
                throw new NotEnoughPersonsException("Not enough persons in Extras");
            }
            Person member = extras.remove(0);
            //TODO: Implement sex selection based on distribution in relationship type
            member.setSex(Utils.getSexRandomly(random, maleProbability));
            member.setRelationshipStatus(relationshipStatus);

            switch (relationshipStatus) {
                case U15_CHILD:
                    member.setAgeRange(AgeRange.A0_14);
                    break;
                case STUDENT:
                    member.setAgeRange(AgeRange.A15_24);
                    break;
                case O15_CHILD:
                    //TODO: Allow a wider range of age categories based on parents' age.
                    member.setAgeRange(AgeRange.A25_39);
                    break;
                default:
                    //TODO: Implement AgeRange selection based on age distribution of relationship/sex type
                    Collections.shuffle(agesList);
                    member.setAgeRange(agesList.get(0));

            }

            family.addMember(member);
        }
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
     * characteristic is set to null, value for that particular characteristic selected according to the observed
     * probability distribution. For example, if ageRange is null, but relStatus and sex is given, ages are assigned to
     * agents based on distribution of age categories within the specified relStatus and sex category.
     *
     * @param relStatus The RelationshipStatus of the persons
     * @param ageRange  The AgeRange of the persons
     * @param sex       The Sex of the persons
     * @param count     The number of persons to create
     * @return The list of newly created persons persons
     */
    List<Person> spawnPersonsFromExtras(RelationshipStatus relStatus, AgeRange ageRange, Sex sex, int count) {
        List<IndRecord> dist = indRecords.stream()
                .filter(r -> (relStatus == null || r.RELATIONSHIP_STATUS == relStatus)) //Filter by relationship status. If relationship status is null get all records
                .filter(r -> (ageRange == null || r.AGE_RANGE == ageRange)) // Filter by age range, or get all if not specified
                .filter(r -> (sex == null || r.SEX == sex))// Filter by sex, or get all if not specified
                .collect(Collectors.toList());

        int sum = dist.stream().mapToInt(r -> r.IND_COUNT).sum();

        List<Person> persons = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            int offset = random.nextInt(sum);
            int s = 0;
            for (IndRecord r : dist) {
                s += r.IND_COUNT;
                if (offset < s) {
                    persons.add(getPersonFromExtras(r.RELATIONSHIP_STATUS, r.AGE_RANGE, r.SEX));
                }
            }
        }

        return persons;
    }


}
