package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;

import java.util.*;

/**
 * @author wniroshan 18 Dec 2017
 */
class ExtrasHandler {

    final double maleProbability;
    final Random random;
    final private List<Person> extras;

    ExtrasHandler(List<HhRecord> hhRecords, List<IndRecord> indRecords, double sexRatio, Random random) {
        this.extras = this.getExtras(hhRecords, indRecords);
        this.maleProbability = sexRatio;
        this.random = random;
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


}
