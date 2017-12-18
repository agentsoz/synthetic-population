package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;

import java.util.*;

/**
 * @author wniroshan
 * @date 18 Dec 2017
 */
class ExtrasHandler {

    final private List<Person> extras;
    final double sexRatio;

    ExtrasHandler(List<Person> extras, double sexRatio) {
        this.extras = extras;
        this.sexRatio = sexRatio;
    }

    /**
     * Adds required number of persons to the family from extras
     *
     * @param family             The family instance
     * @param relationshipStatus The type of the newly added persons
     * @param requiredMembers    The number of persons to add
     */
    void addMembersToFamilyFromExtras(Family family, RelationshipStatus relationshipStatus, int requiredMembers, Random rand) {
        List<AgeRange> agesList = new ArrayList<>(Arrays.asList(AgeRange.values()));
        for (int i = 0; i < requiredMembers; i++) {
            Person member = extras.remove(0);
            member.setSex(Utils.selectTrueOrFalseRandomlyWithBias(rand, sexRatio) ? Sex.Male : Sex.Female);
            member.setType(relationshipStatus);
            Collections.shuffle(agesList);

            switch (relationshipStatus) {
                case U15Child:
                    member.setAgeCat(AgeRange.A0_14);
                    break;
                case Student:
                    member.setAgeCat(AgeRange.A15_24);
                    break;
                case O15Child:
                    //TODO: Allow a wider range of age categories based on parents' age.
                    member.setAgeCat(AgeRange.A25_39);
                    break;
                default:
                    member.setAgeCat(agesList.get(0));

            }

            family.addMember(member);
        }
    }

}
