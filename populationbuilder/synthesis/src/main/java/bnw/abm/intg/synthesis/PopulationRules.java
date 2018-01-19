package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;

import java.util.Collections;
import java.util.List;

/**
 * @author wniroshan
 * @date 11 Jan 2018
 */
public class PopulationRules {

    /**
     * Returns a suitable child for the given parent. If the child is found the its index is returned.
     * Rule applied here is: a child must come from an age category with at least a 15 year age gap (younger) to
     * the parent's.
     *
     * @param parent   The parent
     * @param children The list of children to select the child
     * @return The index of the child instance in the children list. Returns -1 if no suitable child is found.
     */
    static int selectChild(Person parent, List<Person> children) {
        for (int i = 0; i < children.size(); i++) {
            if (parent.getAgeRange().compareTo(children.get(i).getAgeRange()) > -1) {
                return i;
            }
        }
        return -1; //If no suitable child

    }


    /**
     * Finds a suitable household that the given child can be added.
     * Rule applied here is: a child must come from an age category with at least a 15 year age gap (younger) to
     * the parent's.
     *
     * @param child      The child that needs a family household
     * @param households The list of households to select from
     * @return The index of the suitable household. Returns -1 if no suitable household was found
     */
    static int selectHouseholdWithSuitablePrimaryFamilyForChild(Person child, List<Household> households) {
        for (int i = 0; i < households.size(); i++) {
            Household h = households.get(i);
            if(h.getExpectedSize() > h.getCurrentSize()) {
                Family pf = h.getPrimaryFamily();
                Person youngestParent = pf.getMembers()
                        .stream()
                        .filter(m -> m.getRelationshipStatus() == RelationshipStatus.MARRIED ||
                                m.getRelationshipStatus() == RelationshipStatus.LONE_PARENT)
                        .min(new AgeRange.AgeComparator())
                        .get();
                if (youngestParent.getAgeRange().compareTo(child.getAgeRange()) > -1) {
                    return i;
                }
            }
        }
        return -1;
    }
}
