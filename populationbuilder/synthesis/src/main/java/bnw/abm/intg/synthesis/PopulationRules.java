package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wniroshan
 * @date 11 Jan 2018
 */
public class PopulationRules {

    /**
     * Returns a suitable child for the given parent. If the child is found the its index is returned. Rule applied here is: a child must
     * come from an age category with at least a 15 year age gap (younger) to the parent's.
     *
     * @param parent   The parent
     * @param children The list of children to select the child
     * @return The index of the child instance in the children list. Returns -1 if no suitable child is found.
     */
    static int selectChild(Person parent, List<Person> children) {
        for (int i = 0; i < children.size(); i++) {
            if (parent.getAgeRange().compareTo(children.get(i).getAgeRange()) > 0) {
                return i;
            }
        }
        return -1; //If no suitable child

    }

    /**
     * Returns a suitable child types for the given parent. Rule applied here is: a child must come from an age category with at least a 15
     * year age gap (younger) to the parent's.
     *
     * @param parent     The parent
     * @param indRecords The list of IndRecords to choose children types from
     * @return The list of suitable IndRecords
     */
    static List<IndRecord> selectChildTypes(Person parent, List<IndRecord> indRecords) {
        return indRecords.stream()
                         .filter(r -> (r.RELATIONSHIP_STATUS == RelationshipStatus.U15_CHILD
                                 || r.RELATIONSHIP_STATUS == RelationshipStatus.STUDENT
                                 || r.RELATIONSHIP_STATUS == RelationshipStatus.O15_CHILD)
                                 && (parent.getAgeRange().compareTo(r.AGE_RANGE) > 0))
                         .collect(Collectors.toList());
    }


    /**
     * Finds a suitable household that the given child can be added. Rule applied here is: a child must come from an age category with at
     * least a 15 year age gap (younger) to the parent's.
     *
     * @param child      The child that needs a family household
     * @param households The list of households to select from
     * @return The index of the suitable household. Returns -1 if no suitable household was found
     */
    static int selectHouseholdWithSuitablePrimaryFamilyForChild(Person child, List<Household> households) {
        for (int i = 0; i < households.size(); i++) {
            Household h = households.get(i);
            if ((h.getPrimaryFamilyType() == FamilyType.ONE_PARENT || h.getPrimaryFamilyType() == FamilyType
                    .COUPLE_WITH_CHILDREN)
                    && h.getExpectedSize() > h.getCurrentSize()) {
                Family pf = h.getPrimaryFamily();

                Person youngestParent = pf.getYoungestParent();

                if (youngestParent.getAgeRange().compareTo(child.getAgeRange()) > 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Finds a suitable wife for the given husband considering their age. The rule applied here is: The female partner's age category must
     * be same of one below as the male partner's age
     *
     * @param husband The male married person
     * @param wives   Potential partners list containing female married persons
     * @return The index of the selected female partner. Returns -1 if a suitable partner was not found.
     */
    static int selectWife(Person husband, List<Person> wives) {
        for (int i = 0; i < wives.size(); i++) {
            Person wife = wives.get(i);
            if (husband.getAgeRange().compareTo(wife.getAgeRange()) == 0 || husband.getAgeRange()
                                                                                   .compareTo(wife.getAgeRange()) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds a suitable husband for the given wife considering their age. The rule applied here is: The female partner's age category must
     * be same of one below as the male partner's age
     *
     * @param wife     The female married person
     * @param husbands Potential partners list containing male married persons
     * @return The index of the selected male partner. Returns -1 if a suitable partner was not found.
     */
    static int selectHusband(Person wife, List<Person> husbands) {
        for (int i = 0; i < husbands.size(); i++) {
            Person husband = husbands.get(i);
            if (wife.getAgeRange().compareTo(husband.getAgeRange()) == 0 || wife.getAgeRange()
                                                                                .compareTo(husband.getAgeRange()) == -1) {
                return i;
            }
        }
        return -1;
    }
}
