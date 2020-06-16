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

/**
 * @author wniroshan 18 Dec 2017
 */

/**
 * This refers to RLHP	Relationship in Household categories in 2901.0 - Census of Population and Housing: Census Dictionary, 2016
 * The enums are custom fields constructed by combining several categories in RLHP.
 */
public enum RelationshipStatus {

    /**
     * RLHP: Other related individual and (Unrelated individual living in family household under Non-family member category)
     */
    RELATIVE,
    /**
     * RLHP: Lone person under Non-family member category
     */
    LONE_PERSON,
    /**
     * RLHP: Husband, Wife or Partner - includes registered and de facto ( including same sex) couples
     */
    MARRIED,
    /**
     * RLHP: Child under 15
     */
    U15_CHILD,
    /**
     * RLHP: Dependent student
     */
    STUDENT,
    /**
     * RLHP: Non-dependent student combined
     */
    O15_CHILD,
    /**
     * RLHP: 	Lone parent
     */
    LONE_PARENT,
    /**
     * RLHP: Group household member under Non-family member category
     */
    GROUP_HOUSEHOLD,

    /**
     * This is a custom type that combine U15_CHILD, STUDENT and O15_CHILD. Only to be used when generating summary reports.
     */
    CHILDREN;
}
