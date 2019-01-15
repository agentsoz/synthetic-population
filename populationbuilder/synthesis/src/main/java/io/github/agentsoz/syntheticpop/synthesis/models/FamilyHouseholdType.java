package io.github.agentsoz.syntheticpop.synthesis.models;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2019 by its authors. See AUTHORS file.
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

/**
 * @author wniroshan 18/12/17
 */

/**
 * This enum relates to HCFMD - Family Household Composition (Dwelling) in
 * 2901.0 - Census of Population and Housing: Census Dictionary, 2016. 	'Visitors only household' and 'Other non-classifiable household'
 * are ignored.
 */
public enum FamilyHouseholdType {

    F1_COUPLE_ONLY(1, FamilyType.COUPLE_ONLY),
    F1_COUPLE_WITH_CHILDREN(1, FamilyType.COUPLE_WITH_CHILDREN),
    F1_ONE_PARENT(1, FamilyType.ONE_PARENT),
    F1_OTHER_FAMILY(1, FamilyType.OTHER_FAMILY),
    F2_COUPLE_ONLY(2, FamilyType.COUPLE_ONLY),
    F2_COUPLE_WITH_CHILDREN(2, FamilyType.COUPLE_WITH_CHILDREN),
    F2_ONE_PARENT(2, FamilyType.ONE_PARENT),
    F2_OTHER_FAMILY(2, FamilyType.OTHER_FAMILY),
    F3_COUPLE_ONLY(3, FamilyType.COUPLE_ONLY),
    F3_COUPLE_WITH_CHILDREN(3, FamilyType.COUPLE_WITH_CHILDREN),
    F3_ONE_PARENT(3, FamilyType.ONE_PARENT),
    F3_OTHER_FAMILY(3, FamilyType.OTHER_FAMILY),
    LONE_PERSON(1, FamilyType.LONE_PERSON),
    GROUP_HOUSEHOLD(1, FamilyType.GROUP_HOUSEHOLD);

    private int familyCount;
    private FamilyType familyType;

    FamilyHouseholdType(int familyCount, FamilyType familyType) {
        this.familyCount = familyCount;
        this.familyType = familyType;
    }

    public int getFamilyCount() {
        return this.familyCount;
    }

    public FamilyType getFamilyType() {
        return this.familyType;
    }

}
