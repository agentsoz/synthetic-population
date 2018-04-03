package io.github.agentsoz.syntheticpop.synthesis;

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

