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
 * @author wniroshan 18 Dec 2017
 */

/**
 * This is mainly a representation of unit level decomposition of HCFMD - Family Household Composition (Dwelling). BASIC
 * and UNDEFINED enums are not part of HCFMD but added for system needs.
 */
public enum FamilyType {
    COUPLE_WITH_CHILDREN("Couple family with children", 2, 0, 1, 0, 0, 0),
    COUPLE_ONLY("Couple family with no children", 2, 0, 0, 0, 0, 0),
    ONE_PARENT("One parent family", 0, 1, 1, 0, 0, 0),
    OTHER_FAMILY("Other family", 0, 0, 0, 0, 2, 0),
    LONE_PERSON("Lone person", 0, 0, 0, 1, 0, 0),
    GROUP_HOUSEHOLD("Group household", 0, 0, 0, 0, 0, 2),
    /**
     * This is the basic structure containing minimum required persons for couple only, couple with children. other
     * or one parent families. Generally represents an intermediate state of a family instance that will be eventually
     * converted to one of the above 4 family types.
     */
    BASIC("MARRIED basic or Lone parent basic ", 0, 0, 0, 0, 0, 0),
    /**
     * Generally indicates an erroneous state in the family.
     */
    UNDEFINED("Undefined", 0, 0, 0, 0, 0, 0);

    private String type;
    private int basicChildren, basicMarried, basicLonePersons, basicLoneParents, basicRelatives, basicGroupHouseholdPersons;

    FamilyType(String type, int basicMarried, int basicLoneParents, int basicChildren, int basicLonePersons, int basicRelatives,
               int basicGroupHouseholdPersons) {
        this.type = type;
        this.basicMarried = basicMarried;
        this.basicLoneParents = basicLoneParents;
        this.basicChildren = basicChildren;
        this.basicLonePersons = basicLonePersons;
        this.basicRelatives = basicRelatives;
        this.basicGroupHouseholdPersons = basicGroupHouseholdPersons;

    }

    public String description() {
        return type;
    }

    public int basicSize() {
        return basicChildren + basicMarried + basicLonePersons + basicLoneParents + basicRelatives + basicGroupHouseholdPersons;
    }

    public int basicChildren() {
        return basicChildren;
    }

    public int basicGroupHouseholdPersons() {
        return basicGroupHouseholdPersons;
    }

    public int basicMarriedPersons() {
        return basicMarried;
    }

    public int basicLonePersons() {
        return basicLonePersons;
    }

    public int basicLoneParents() {
        return basicLoneParents;
    }

    public int basicRelatives() {
        return basicRelatives;
    }
}
