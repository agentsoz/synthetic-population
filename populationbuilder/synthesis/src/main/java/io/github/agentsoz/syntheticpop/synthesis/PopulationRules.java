package io.github.agentsoz.syntheticpop.synthesis;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2018 by its authors. See AUTHORS file.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import io.github.agentsoz.syntheticpop.synthesis.models.AgeRange;
import io.github.agentsoz.syntheticpop.synthesis.models.Person;

import java.util.function.Function;

/**
 * @author wniroshan 11 Jan 2018
 */
public class PopulationRules {

    /**
     * Validates the rule that a child must come from an age category between 15 to 60 years age gap (younger) to the parent's.
     *
     * @param parent1 The first parent. Can be a Person or an AgeRange.
     * @param parent2 The second parent. Can be a Person or an AgeRange
     * @param child   The age of the child. Can be a Person, an AgeRange or an age (integer)
     * @return True if age ranges are valid, otherwise false
     */
    public static <T, E> boolean validateParentChildAgeRule(T parent1, T parent2, E child) {
        int childAge;

        if (child instanceof AgeRange) {
            childAge = ((AgeRange) child).min();
        } else if (child instanceof Integer) {
            childAge = (Integer)child;
        } else if (child instanceof Person) {
            childAge = ((Person) child).getAge() >= 0 ? ((Person) child).getAge() : ((Person) child).getAgeRange().min();
        } else {
            throw new Error(child.getClass() + " is not a compatible age representation");
        }

        Function<T, Boolean> validateAgeGap = (T parent) -> {
            boolean parentAgeCondition;
            if (parent != null) {
                int parentAge;
                if (parent instanceof Person) {
                    parentAge = (((Person) parent).getAge() >= 0) ? ((Person) parent).getAge() : ((Person) parent).getAgeRange().min();
                } else if (parent instanceof AgeRange) {
                    parentAge = ((AgeRange) parent).min();
                } else if (parent instanceof Integer) {
                    parentAge = (Integer) parent;
                } else {
                    throw new Error(parent.getClass() + " is not a compatible age representation");
                }
                int parent1AgeGap = parentAge - childAge;
                parentAgeCondition = 1 <= parent1AgeGap && parent1AgeGap <= 60;
            } else {
                parentAgeCondition = true;
            }
            return parentAgeCondition;
        };

        return validateAgeGap.apply(parent1) && validateAgeGap.apply(parent2);
    }
}
