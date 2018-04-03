package io.github.agentsoz.syntheticpop.synthesis;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HouseholdSummaryRegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "HouseholdSummaryRegressionTest0.test1");
        io.github.agentsoz.syntheticpop.synthesis.HouseholdSummary householdSummary0 = new io.github.agentsoz.syntheticpop.synthesis.HouseholdSummary();
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "HouseholdSummaryRegressionTest0.test2");
        java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household> list_household0 = null;
        try {
            java.util.Map<java.lang.String, java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household>> map_str_list_household1 = io.github.agentsoz.syntheticpop.synthesis.HouseholdSummary.groupHouseholdsByHouseholdType(list_household0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
    }
}

