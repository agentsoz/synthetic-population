package io.github.agentsoz.syntheticpop.synthesis;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataWriterRegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test01");
        io.github.agentsoz.syntheticpop.synthesis.models.IndRecord[] indRecord_array0 = new io.github.agentsoz.syntheticpop.synthesis.models.IndRecord[] {};
        java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.IndRecord> arraylist_indRecord1 = new java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.IndRecord>();
        boolean b2 = java.util.Collections.addAll((java.util.Collection<io.github.agentsoz.syntheticpop.synthesis.models.IndRecord>) arraylist_indRecord1, indRecord_array0);
        io.github.agentsoz.syntheticpop.synthesis.models.Household[] household_array3 = new io.github.agentsoz.syntheticpop.synthesis.models.Household[] {};
        java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household> arraylist_household4 = new java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household>();
        boolean b5 = java.util.Collections.addAll((java.util.Collection<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household4, household_array3);
        java.nio.file.Path path6 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.savePersonsSummary((java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.IndRecord>) arraylist_indRecord1, (java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household4, path6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(indRecord_array0);
        org.junit.Assert.assertTrue(b2 == false);
        org.junit.Assert.assertNotNull(household_array3);
        org.junit.Assert.assertTrue(b5 == false);
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test02");
        io.github.agentsoz.syntheticpop.synthesis.models.HhRecord[] hhRecord_array0 = new io.github.agentsoz.syntheticpop.synthesis.models.HhRecord[] {};
        java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.HhRecord> arraylist_hhRecord1 = new java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.HhRecord>();
        boolean b2 = java.util.Collections.addAll((java.util.Collection<io.github.agentsoz.syntheticpop.synthesis.models.HhRecord>) arraylist_hhRecord1, hhRecord_array0);
        java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household> list_household3 = null;
        java.nio.file.Path path4 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.saveHouseholdSummary((java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.HhRecord>) arraylist_hhRecord1, list_household3, path4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(hhRecord_array0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test03");
        java.nio.file.Path path0 = null;
        io.github.agentsoz.syntheticpop.synthesis.models.Household[] household_array1 = new io.github.agentsoz.syntheticpop.synthesis.models.Household[] {};
        java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household> arraylist_household2 = new java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household>();
        boolean b3 = java.util.Collections.addAll((java.util.Collection<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household2, household_array1);
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.saveHouseholds(path0, (java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(household_array1);
        org.junit.Assert.assertTrue(b3 == false);
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test04");
        java.nio.file.Path path0 = null;
        io.github.agentsoz.syntheticpop.synthesis.models.Household[] household_array1 = new io.github.agentsoz.syntheticpop.synthesis.models.Household[] {};
        java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household> arraylist_household2 = new java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household>();
        boolean b3 = java.util.Collections.addAll((java.util.Collection<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household2, household_array1);
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.savePersons(path0, (java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(household_array1);
        org.junit.Assert.assertTrue(b3 == false);
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test05");
        java.nio.file.Path path0 = null;
        io.github.agentsoz.syntheticpop.synthesis.models.Household[] household_array1 = new io.github.agentsoz.syntheticpop.synthesis.models.Household[] {};
        java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household> arraylist_household2 = new java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household>();
        boolean b3 = java.util.Collections.addAll((java.util.Collection<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household2, household_array1);
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.saveFamilies(path0, (java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(household_array1);
        org.junit.Assert.assertTrue(b3 == false);
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test06");
        io.github.agentsoz.syntheticpop.synthesis.DataWriter dataWriter0 = new io.github.agentsoz.syntheticpop.synthesis.DataWriter();
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test07");
        io.github.agentsoz.syntheticpop.synthesis.models.HhRecord[] hhRecord_array0 = new io.github.agentsoz.syntheticpop.synthesis.models.HhRecord[] {};
        java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.HhRecord> arraylist_hhRecord1 = new java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.HhRecord>();
        boolean b2 = java.util.Collections.addAll((java.util.Collection<io.github.agentsoz.syntheticpop.synthesis.models.HhRecord>) arraylist_hhRecord1, hhRecord_array0);
        io.github.agentsoz.syntheticpop.synthesis.models.Household[] household_array3 = new io.github.agentsoz.syntheticpop.synthesis.models.Household[] {};
        java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household> arraylist_household4 = new java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household>();
        boolean b5 = java.util.Collections.addAll((java.util.Collection<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household4, household_array3);
        java.nio.file.Path path6 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.saveHouseholdSummary((java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.HhRecord>) arraylist_hhRecord1, (java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household4, path6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(hhRecord_array0);
        org.junit.Assert.assertTrue(b2 == false);
        org.junit.Assert.assertNotNull(household_array3);
        org.junit.Assert.assertTrue(b5 == false);
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test08");
        java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.HhRecord> list_hhRecord0 = null;
        io.github.agentsoz.syntheticpop.synthesis.models.Household[] household_array1 = new io.github.agentsoz.syntheticpop.synthesis.models.Household[] {};
        java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household> arraylist_household2 = new java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household>();
        boolean b3 = java.util.Collections.addAll((java.util.Collection<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household2, household_array1);
        java.nio.file.Path path4 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.saveHouseholdSummary(list_hhRecord0, (java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household2, path4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(household_array1);
        org.junit.Assert.assertTrue(b3 == false);
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test09");
        java.nio.file.Path path0 = null;
        java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household> list_household1 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.saveFamilies(path0, list_household1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test10");
        java.nio.file.Path path0 = null;
        java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household> list_household1 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.saveHouseholds(path0, list_household1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test11");
        io.github.agentsoz.syntheticpop.synthesis.models.IndRecord[] indRecord_array0 = new io.github.agentsoz.syntheticpop.synthesis.models.IndRecord[] {};
        java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.IndRecord> arraylist_indRecord1 = new java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.IndRecord>();
        boolean b2 = java.util.Collections.addAll((java.util.Collection<io.github.agentsoz.syntheticpop.synthesis.models.IndRecord>) arraylist_indRecord1, indRecord_array0);
        java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household> list_household3 = null;
        java.nio.file.Path path4 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.savePersonsSummary((java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.IndRecord>) arraylist_indRecord1, list_household3, path4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(indRecord_array0);
        org.junit.Assert.assertTrue(b2 == false);
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test12");
        java.nio.file.Path path0 = null;
        java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household> list_household1 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.savePersons(path0, list_household1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test13");
        java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.HhRecord> list_hhRecord0 = null;
        java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household> list_household1 = null;
        java.nio.file.Path path2 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.saveHouseholdSummary(list_hhRecord0, list_household1, path2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test14");
        java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.IndRecord> list_indRecord0 = null;
        io.github.agentsoz.syntheticpop.synthesis.models.Household[] household_array1 = new io.github.agentsoz.syntheticpop.synthesis.models.Household[] {};
        java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household> arraylist_household2 = new java.util.ArrayList<io.github.agentsoz.syntheticpop.synthesis.models.Household>();
        boolean b3 = java.util.Collections.addAll((java.util.Collection<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household2, household_array1);
        java.nio.file.Path path4 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.savePersonsSummary(list_indRecord0, (java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household>) arraylist_household2, path4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(household_array1);
        org.junit.Assert.assertTrue(b3 == false);
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataWriterRegressionTest0.test15");
        java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.IndRecord> list_indRecord0 = null;
        java.util.List<io.github.agentsoz.syntheticpop.synthesis.models.Household> list_household1 = null;
        java.nio.file.Path path2 = null;
        try {
            io.github.agentsoz.syntheticpop.synthesis.DataWriter.savePersonsSummary(list_indRecord0, list_household1, path2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
    }
}

