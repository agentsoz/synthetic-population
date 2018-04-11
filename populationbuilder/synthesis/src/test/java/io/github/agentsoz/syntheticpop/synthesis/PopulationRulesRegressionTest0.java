package io.github.agentsoz.syntheticpop.synthesis;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PopulationRulesRegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test001");
        io.github.agentsoz.syntheticpop.synthesis.models.AgeRange ageRange0 = null;
        io.github.agentsoz.syntheticpop.synthesis.models.AgeRange ageRange1 = null;
        io.github.agentsoz.syntheticpop.synthesis.models.AgeRange ageRange2 = null;
        try {
            boolean b3 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(ageRange0, ageRange1, ageRange2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test002");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test003");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test004");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', 1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test005");
        io.github.agentsoz.syntheticpop.synthesis.PopulationRules populationRules0 = new io.github.agentsoz.syntheticpop.synthesis.PopulationRules();
    }

    @Test
    public void test006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test006");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test007");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test008");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test009");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test010");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test011");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test012");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test013");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test014");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test015");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test016");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test017");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test018");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test019");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test020");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test021");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test022");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test023");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test024");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test025");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test026");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test027");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test028");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test029");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test030");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test031");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test032");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test033");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test034");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test035");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test036");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test037");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test038");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test039");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test040");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test041");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test042");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test043");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test044");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test045");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test046");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test047");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test048");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test049");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test050");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test051");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test052");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test053");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test054");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test055");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test056");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test057");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) '#');
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test058");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test059");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test060");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test061");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test062");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test063");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (-1));
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test064");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test065");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test066");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test067");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test068");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test069");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test070");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test071");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test072");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test073");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test074");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test075");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test076");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test077");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test078");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test079");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test080");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test081");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test082");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test083");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', 0);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test084");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test085");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test086");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', 1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test087");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test088");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test089");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test090");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test091");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test092");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test093");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test094");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test095");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test096");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test097");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', 10);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test098");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test099");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test100");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test101");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test102");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test103");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test104");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test105");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test106");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test107");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test108");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test109");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test110");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test111");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test112");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test113");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test114");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test115");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test116");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test117");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test118");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test119() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test119");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test120() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test120");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test121() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test121");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test122() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test122");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test123() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test123");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', 0);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test124() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test124");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test125() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test125");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test126() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test126");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test127() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test127");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test128() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test128");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test129() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test129");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test130() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test130");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test131() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test131");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test132() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test132");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test133() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test133");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test134() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test134");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test135() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test135");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test136() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test136");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test137() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test137");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test138() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test138");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', 1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test139() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test139");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test140() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test140");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test141() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test141");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test142() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test142");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test143() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test143");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test144() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test144");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test145() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test145");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test146() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test146");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test147() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test147");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test148() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test148");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test149() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test149");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test150() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test150");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test151() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test151");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test152() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test152");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test153() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test153");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test154() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test154");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test155() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test155");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test156() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test156");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test157() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test157");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test158() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test158");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test159() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test159");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test160() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test160");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test161");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test162() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test162");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test163() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test163");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test164");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test165() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test165");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test166() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test166");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test167");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test168");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test169");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test170() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test170");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test171");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test172");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test173");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test174");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test175");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test176");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test177() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test177");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test178");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test179");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test180() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test180");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test181() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test181");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test182");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test183");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test184");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test185() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test185");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test186");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test187() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test187");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test188");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (-1));
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test189() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test189");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test190() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test190");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test191() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test191");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test192");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test193() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test193");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test194() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test194");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test195() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test195");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test196() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test196");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test197() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test197");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test198() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test198");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test199() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test199");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test200() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test200");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test201() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test201");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test202() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test202");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test203() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test203");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test204() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test204");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test205() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test205");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test206() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test206");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test207() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test207");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test208() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test208");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test209");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test210() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test210");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test211() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test211");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test212() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test212");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test213() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test213");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test214");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test215() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test215");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', 0);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test216() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test216");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test217() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test217");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test218() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test218");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test219() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test219");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test220() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test220");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test221() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test221");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test222");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test223");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test224() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test224");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test225() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test225");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test226() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test226");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test227() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test227");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test228() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test228");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test229() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test229");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test230() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test230");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test231() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test231");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test232() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test232");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test233() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test233");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test234");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(0, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test235");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) '4');
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test236() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test236");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test237() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test237");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test238() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test238");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test239() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test239");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test240() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test240");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test241");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test242");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test243() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test243");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test244() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test244");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test245");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', 10);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test246");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test247() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test247");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test248");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test249");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test250() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test250");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test251() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test251");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test252() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test252");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test253() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test253");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test254() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test254");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test255() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test255");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test256() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test256");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test257() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test257");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test258() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test258");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test259() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test259");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test260() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test260");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test261() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test261");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test262() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test262");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test263() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test263");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test264() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test264");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test265() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test265");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test266() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test266");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test267() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test267");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test268() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test268");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test269() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test269");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test270() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test270");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test271() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test271");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test272() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test272");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test273() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test273");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test274() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test274");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test275() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test275");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test276() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test276");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) '4');
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test277() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test277");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test278() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test278");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test279() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test279");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test280() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test280");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test281() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test281");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test282() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test282");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test283() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test283");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test284() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test284");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test285() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test285");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test286() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test286");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test287() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test287");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test288() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test288");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test289() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test289");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test290() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test290");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test291() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test291");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test292() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test292");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test293() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test293");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test294() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test294");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test295() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test295");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test296() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test296");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test297() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test297");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test298() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test298");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test299() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test299");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test300() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test300");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) '4');
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test301() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test301");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test302() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test302");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test303() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test303");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test304() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test304");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(10, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test305() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test305");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test306() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test306");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test307() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test307");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) ' ');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test308() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test308");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test309() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test309");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test310() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test310");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test311() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test311");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test312() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test312");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test313() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test313");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test314() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test314");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test315() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test315");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test316() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test316");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test317() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test317");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test318() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test318");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test319() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test319");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) 'a', (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test320() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test320");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test321() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test321");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test322() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test322");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test323() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test323");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test324() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test324");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) '4');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test325() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test325");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test326() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test326");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test327() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test327");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', 10);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test328() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test328");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test329() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test329");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test330() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test330");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test331() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test331");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test332() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test332");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test333() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test333");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test334() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test334");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 10, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test335() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test335");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) (short) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test336() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test336");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '4', (int) ' ');
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test337() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test337");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test338() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test338");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test339() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test339");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) (byte) 10);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test340() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test340");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (-1));
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test341() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test341");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test342() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test342");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test343() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test343");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test344() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test344");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) '4');
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test345() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test345");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 0, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test346() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test346");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test347() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test347");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 0, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test348() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test348");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) (byte) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test349() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test349");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(100, (int) '#');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test350() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test350");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) '#', (int) (short) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test351() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test351");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) -1, (-1));
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test352() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test352");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule(1, (int) (short) 10);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test353() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test353");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test354() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test354");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 1, (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test355() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test355");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) -1, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test356() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test356");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, (int) (byte) -1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test357() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test357");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 100, (int) (byte) 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test358() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test358");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 1, (int) (short) 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test359() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test359");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) ' ', (int) (short) -1);
        org.junit.Assert.assertTrue(b2 == true);
    }

    @Test
    public void test360() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test360");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), 100);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test361() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test361");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (byte) 100, (int) 'a');
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test362() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test362");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((int) (short) 10, 1);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test363() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "PopulationRulesRegressionTest0.test363");
        boolean b2 = io.github.agentsoz.syntheticpop.synthesis.PopulationRules.validateParentChildAgeRule((-1), (int) (byte) 0);
        org.junit.Assert.assertTrue(b2 == false);
    }
}

