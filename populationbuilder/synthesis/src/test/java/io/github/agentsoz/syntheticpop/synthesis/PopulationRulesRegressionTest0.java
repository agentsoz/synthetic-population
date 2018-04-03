package io.github.agentsoz.syntheticpop.synthesis;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PopulationRulesRegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test1");
        io.github.agentsoz.syntheticpop.synthesis.PopulationRules populationRules0 = new io.github.agentsoz.syntheticpop.synthesis.PopulationRules();
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test2");
        io.github.agentsoz.syntheticpop.synthesis.models.AgeRange ageRange0 = null;
        io.github.agentsoz.syntheticpop.synthesis.models.AgeRange ageRange1 = null;
        try {
            boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(ageRange0, ageRange1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
    }
}

