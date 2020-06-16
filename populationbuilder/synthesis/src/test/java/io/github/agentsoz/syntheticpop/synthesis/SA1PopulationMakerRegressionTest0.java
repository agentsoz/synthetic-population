package io.github.agentsoz.syntheticpop.synthesis;

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

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SA1PopulationMakerRegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SA1PopulationMakerRegressionTest0.test1");
        java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>> map_str_map_str_i0 = null;
        java.util.Map<java.lang.String, java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household>> map_str_list_household1 = null;
        java.util.Random random2 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.SA1PopulationMaker.distributePopulationToSA1s(map_str_map_str_i0, map_str_list_household1, random2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SA1PopulationMakerRegressionTest0.test2");
        java.nio.file.Path path0 = null;
        java.util.Map<java.lang.String, java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household>> map_str_list_household2 = null;
        java.util.Random random3 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.SA1PopulationMaker.assignTENLLDtoHouseholds(path0, "", map_str_list_household2, random3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SA1PopulationMakerRegressionTest0.test3");
        io.github.agentsoz.syntheticpop.synthesis.SA1PopulationMaker sA1PopulationMaker0 = new io.github.agentsoz.syntheticpop.synthesis.SA1PopulationMaker();
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SA1PopulationMakerRegressionTest0.test4");
        java.nio.file.Path path0 = null;
        java.util.Map<java.lang.String, java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household>> map_str_list_household2 = null;
        java.util.Random random3 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.SA1PopulationMaker.assignTENLLDtoHouseholds(path0, "hi!", map_str_list_household2, random3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
    }
}

