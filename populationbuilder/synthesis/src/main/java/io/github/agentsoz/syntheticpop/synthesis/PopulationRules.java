package io.github.agentsoz.syntheticpop.synthesis;

import io.github.agentsoz.syntheticpop.synthesis.models.AgeRange;

/**
 * @author wniroshan 11 Jan 2018
 */
public class PopulationRules {

    /**
     * Validates the rule that a child must come from an age category between 15 to 45 years age gap (younger) to the parent's.
     *
     * @param parent1Age The AgeRange of the parent1
     * @param parent2Age The AgeRange of parent2
     * @param childAge   The AgeRange of the child
     * @return True if age ranges are valid, otherwise false
     */
    public static boolean validateParentChildAgeRule(AgeRange parent1Age, AgeRange parent2Age, AgeRange childAge) {
        int parent1AgeGap = parent1Age.min() - childAge.max();
        boolean parent2AgeCondition, parent1AgeCondition = 1 <= parent1AgeGap && parent1AgeGap <= 31;
        if (parent2Age != null) {
            int parent2AgeGap = parent2Age.min() - childAge.max();
            parent2AgeCondition = 1 <= parent2AgeGap && parent2AgeGap <= 31;
        } else {
            parent2AgeCondition = true;
        }
        return parent1AgeCondition & parent2AgeCondition;
    }

    /**
     * Validates the rule that a child must come from an age category with at least a 15 year age gap (younger) to the parent's.
     *
     * @param parentAge The age of the parent
     * @param childAge  The age of the child
     * @return True if age ranges are valid, otherwise false
     */
    public static boolean validateParentChildAgeRule(int parentAge, int childAge) {
        int ageGap = parentAge - childAge;
        return 15 <= ageGap && ageGap <= 45;
    }
}
