package io.github.agentsoz.syntheticpop.synthesis.models;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2020 by its authors. See AUTHORS file.
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

import java.util.Comparator;

/**
 * @author wniroshan 18 Dec 2017
 */
public enum AgeRange {
    A0_14(0, 14),
    A15_24(15, 24),
    A25_39(25, 39),
    A40_54(40, 54),
    A55_69(55, 69),
    A70_84(70, 84),
    A85_99(85, 99),
    A100_110(100, 110);
    private int min, max;
    private boolean isEmpty = true;

    AgeRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Were any persons observed in census data in this age?
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Mark that there are persons in this age range in the census data
     *
     * @param status Whether there are persons in this age range
     */
    public void markNotEmpty(boolean status) {
        this.isEmpty = !status;
    }

    public int min() {
        return this.min;
    }

    public int max() {
        return this.max;
    }

    public static class AgeComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getAgeRange().compareTo(o2.getAgeRange());
        }
    }

    public static class YoungestParentAgeComparator implements Comparator<Family> {
        @Override
        public int compare(Family o1, Family o2) {
            Person o1YoungestParent = o1.getMembers()
                                        .stream()
                                        .filter(m -> m.getRelationshipStatus() == RelationshipStatus.MARRIED ||
                                                m.getRelationshipStatus() == RelationshipStatus.LONE_PARENT)
                                        .min(new AgeRange.AgeComparator())
                                        .orElse(null);
            Person o2YoungestParent = o2.getMembers()
                                        .stream()
                                        .filter(m -> m.getRelationshipStatus() == RelationshipStatus.MARRIED ||
                                                m.getRelationshipStatus() == RelationshipStatus.LONE_PARENT)
                                        .min(new AgeRange.AgeComparator())
                                        .orElse(null);

            if (o1YoungestParent == null || o2YoungestParent == null) {
                throw new Error("Trying to order a family that has no parents");
            }
            return o1YoungestParent.getAgeRange().compareTo(o2YoungestParent.getAgeRange());
        }
    }
}
