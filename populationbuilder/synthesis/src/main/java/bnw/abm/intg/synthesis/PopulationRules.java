package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.AgeRange;

/**
 * @author wniroshan 11 Jan 2018
 */
public class PopulationRules {

    /**
     * Validates the rule that a child must come from an age category with at least a 15 year age gap (younger) to the parent's.
     *
     * @param parentAge The AgeRange of the parent
     * @param childAge  The AgeRange of the child
     * @return True if age ranges are valid, otherwise false
     */
    public static boolean validateParentChildAgeRule(AgeRange parentAge, AgeRange childAge) {
        int ageGap = parentAge.min() - childAge.max();
        return 0 < ageGap && ageGap <= 61 ;

//        return 2 >= parentAge.compareTo(childAge) && parentAge.compareTo(childAge) > 0;
    }
}
