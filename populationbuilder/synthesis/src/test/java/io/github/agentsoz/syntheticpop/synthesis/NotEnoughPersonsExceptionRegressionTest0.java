package io.github.agentsoz.syntheticpop.synthesis;

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

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotEnoughPersonsExceptionRegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test001");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str3 = notEnoughPersonsException1.toString();
        java.lang.Throwable throwable4 = null;
        try {
            notEnoughPersonsException1.addSuppressed(throwable4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str3 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str3.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test002");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array3 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertNotNull(throwable_array3);
    }

    @Test
    public void test003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test003");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        java.lang.Throwable[] throwable_array13 = notEnoughPersonsException11.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertNotNull(throwable_array13);
    }

    @Test
    public void test004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test004");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.Throwable[] throwable_array7 = notEnoughPersonsException4.getSuppressed();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array7);
    }

    @Test
    public void test005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test005");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.String str9 = notEnoughPersonsException1.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test006");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.String str17 = notEnoughPersonsException14.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str17 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str17.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test007");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array17 = notEnoughPersonsException9.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException22.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str26 = notEnoughPersonsException25.toString();
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str36 = notEnoughPersonsException35.toString();
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException35);
        notEnoughPersonsException31.addSuppressed((java.lang.Throwable) notEnoughPersonsException35);
        java.lang.Throwable[] throwable_array39 = notEnoughPersonsException31.getSuppressed();
        java.lang.Throwable[] throwable_array40 = notEnoughPersonsException31.getSuppressed();
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        java.lang.String str42 = notEnoughPersonsException4.toString();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array17);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertTrue("'" + str26 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str26.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str36 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str36.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array39);
        org.junit.Assert.assertNotNull(throwable_array40);
        org.junit.Assert.assertTrue("'" + str42 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str42.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test008");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException7 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str8 = notEnoughPersonsException7.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        java.lang.Throwable[] throwable_array11 = notEnoughPersonsException7.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        java.lang.Throwable[] throwable_array13 = notEnoughPersonsException7.getSuppressed();
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str8.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array11);
        org.junit.Assert.assertNotNull(throwable_array13);
    }

    @Test
    public void test009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test009");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str16 = notEnoughPersonsException15.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        java.lang.Throwable[] throwable_array19 = notEnoughPersonsException11.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException21);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        java.lang.String str48 = notEnoughPersonsException1.toString();
        java.lang.String str49 = notEnoughPersonsException1.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str16.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array19);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str48 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str48.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str49 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str49.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test010");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str16 = notEnoughPersonsException15.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        java.lang.Throwable[] throwable_array19 = notEnoughPersonsException11.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException21);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        java.lang.Throwable[] throwable_array48 = notEnoughPersonsException11.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str16.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array19);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertNotNull(throwable_array48);
    }

    @Test
    public void test011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test011");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array25 = notEnoughPersonsException24.getSuppressed();
        java.lang.String str26 = notEnoughPersonsException24.toString();
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException24.getSuppressed();
        notEnoughPersonsException20.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException30 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException32 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str35 = notEnoughPersonsException34.toString();
        notEnoughPersonsException32.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        notEnoughPersonsException30.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str44 = notEnoughPersonsException43.toString();
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        java.lang.Throwable[] throwable_array47 = notEnoughPersonsException39.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        notEnoughPersonsException30.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array54 = notEnoughPersonsException53.getSuppressed();
        java.lang.String str55 = notEnoughPersonsException53.toString();
        java.lang.Throwable[] throwable_array56 = notEnoughPersonsException53.getSuppressed();
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException20.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertNotNull(throwable_array25);
        org.junit.Assert.assertTrue("'" + str26 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str26.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str35 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str35.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str44 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str44.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array47);
        org.junit.Assert.assertNotNull(throwable_array54);
        org.junit.Assert.assertTrue("'" + str55 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str55.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array56);
    }

    @Test
    public void test012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test012");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        java.lang.String str23 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array25 = notEnoughPersonsException11.getSuppressed();
        java.lang.Throwable[] throwable_array26 = notEnoughPersonsException11.getSuppressed();
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException11.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str23.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array25);
        org.junit.Assert.assertNotNull(throwable_array26);
        org.junit.Assert.assertNotNull(throwable_array27);
    }

    @Test
    public void test013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test013");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str28 = notEnoughPersonsException27.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        java.lang.Throwable[] throwable_array31 = notEnoughPersonsException23.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        java.lang.Throwable[] throwable_array37 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str44 = notEnoughPersonsException43.toString();
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        java.lang.Throwable[] throwable_array47 = notEnoughPersonsException39.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException52 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str53 = notEnoughPersonsException52.toString();
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException52);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str58 = notEnoughPersonsException57.toString();
        java.lang.String str59 = notEnoughPersonsException57.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str28 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str28.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array31);
        org.junit.Assert.assertNotNull(throwable_array37);
        org.junit.Assert.assertTrue("'" + str44 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str44.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array47);
        org.junit.Assert.assertTrue("'" + str53 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str53.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str58 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str58.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str59 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str59.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test014");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array17 = notEnoughPersonsException9.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException22.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str26 = notEnoughPersonsException25.toString();
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str36 = notEnoughPersonsException35.toString();
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException35);
        notEnoughPersonsException31.addSuppressed((java.lang.Throwable) notEnoughPersonsException35);
        java.lang.Throwable[] throwable_array39 = notEnoughPersonsException31.getSuppressed();
        java.lang.Throwable[] throwable_array40 = notEnoughPersonsException31.getSuppressed();
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        java.lang.Throwable[] throwable_array42 = notEnoughPersonsException4.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException44 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str49 = notEnoughPersonsException48.toString();
        notEnoughPersonsException46.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        notEnoughPersonsException44.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.Throwable[] throwable_array53 = notEnoughPersonsException48.getSuppressed();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array17);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertTrue("'" + str26 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str26.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str36 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str36.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array39);
        org.junit.Assert.assertNotNull(throwable_array40);
        org.junit.Assert.assertNotNull(throwable_array42);
        org.junit.Assert.assertTrue("'" + str49 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str49.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array53);
    }

    @Test
    public void test015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test015");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException20.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertNotNull(throwable_array23);
    }

    @Test
    public void test016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test016");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array25 = notEnoughPersonsException24.getSuppressed();
        java.lang.String str26 = notEnoughPersonsException24.toString();
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException24.getSuppressed();
        notEnoughPersonsException20.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        java.lang.Throwable[] throwable_array29 = notEnoughPersonsException20.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertNotNull(throwable_array25);
        org.junit.Assert.assertTrue("'" + str26 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str26.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertNotNull(throwable_array29);
    }

    @Test
    public void test017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test017");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException48.getSuppressed();
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.Throwable[] throwable_array51 = notEnoughPersonsException48.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str58 = notEnoughPersonsException57.toString();
        notEnoughPersonsException55.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException53.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException63 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException63);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str71 = notEnoughPersonsException70.toString();
        notEnoughPersonsException68.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        java.lang.Throwable[] throwable_array74 = notEnoughPersonsException66.getSuppressed();
        java.lang.String str75 = notEnoughPersonsException66.toString();
        notEnoughPersonsException63.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        notEnoughPersonsException48.addSuppressed((java.lang.Throwable) notEnoughPersonsException63);
        java.lang.String str78 = notEnoughPersonsException48.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertNotNull(throwable_array49);
        org.junit.Assert.assertNotNull(throwable_array51);
        org.junit.Assert.assertTrue("'" + str58 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str58.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertTrue("'" + str71 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str71.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array74);
        org.junit.Assert.assertTrue("'" + str75 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str75.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str78 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: " + "'", str78.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: "));
    }

    @Test
    public void test018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test018");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str3 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array4 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str5 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException7 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str12 = notEnoughPersonsException11.toString();
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        notEnoughPersonsException7.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str21 = notEnoughPersonsException20.toString();
        notEnoughPersonsException18.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array24 = notEnoughPersonsException16.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException26);
        notEnoughPersonsException7.addSuppressed((java.lang.Throwable) notEnoughPersonsException26);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException30 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException32 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str35 = notEnoughPersonsException34.toString();
        notEnoughPersonsException32.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        notEnoughPersonsException30.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str44 = notEnoughPersonsException43.toString();
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        java.lang.Throwable[] throwable_array47 = notEnoughPersonsException39.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        notEnoughPersonsException30.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        notEnoughPersonsException7.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        java.lang.String str53 = notEnoughPersonsException30.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException56 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException58 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException60 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str61 = notEnoughPersonsException60.toString();
        notEnoughPersonsException58.addSuppressed((java.lang.Throwable) notEnoughPersonsException60);
        notEnoughPersonsException56.addSuppressed((java.lang.Throwable) notEnoughPersonsException60);
        java.lang.Throwable[] throwable_array64 = notEnoughPersonsException56.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException56.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException69 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException71 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException73 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str74 = notEnoughPersonsException73.toString();
        notEnoughPersonsException71.addSuppressed((java.lang.Throwable) notEnoughPersonsException73);
        notEnoughPersonsException69.addSuppressed((java.lang.Throwable) notEnoughPersonsException73);
        java.lang.Throwable[] throwable_array77 = notEnoughPersonsException69.getSuppressed();
        java.lang.String str78 = notEnoughPersonsException69.toString();
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException69);
        notEnoughPersonsException30.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str3 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str3.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array4);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str12 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str12.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str21 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str21.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array24);
        org.junit.Assert.assertTrue("'" + str35 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str35.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str44 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str44.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array47);
        org.junit.Assert.assertTrue("'" + str53 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str53.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str61 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str61.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array64);
        org.junit.Assert.assertTrue("'" + str74 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str74.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array77);
        org.junit.Assert.assertTrue("'" + str78 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str78.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test019");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertNotNull(throwable_array2);
    }

    @Test
    public void test020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test020");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str2 = notEnoughPersonsException1.toString();
        java.lang.String str3 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array6 = notEnoughPersonsException5.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException8 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str9 = notEnoughPersonsException8.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.String str11 = notEnoughPersonsException8.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException17 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str18 = notEnoughPersonsException17.toString();
        notEnoughPersonsException15.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        java.lang.Throwable[] throwable_array21 = notEnoughPersonsException13.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str30 = notEnoughPersonsException29.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException8.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.Throwable[] throwable_array35 = notEnoughPersonsException8.getSuppressed();
        org.junit.Assert.assertTrue("'" + str2 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str2.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str3 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str3.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array6);
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str11.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str18 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str18.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array21);
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str30 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str30.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array35);
    }

    @Test
    public void test021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test021");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        java.lang.String str23 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array25 = notEnoughPersonsException11.getSuppressed();
        java.lang.Throwable[] throwable_array26 = notEnoughPersonsException11.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException30 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException32 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str33 = notEnoughPersonsException32.toString();
        notEnoughPersonsException30.addSuppressed((java.lang.Throwable) notEnoughPersonsException32);
        notEnoughPersonsException28.addSuppressed((java.lang.Throwable) notEnoughPersonsException32);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str42 = notEnoughPersonsException41.toString();
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException41);
        notEnoughPersonsException37.addSuppressed((java.lang.Throwable) notEnoughPersonsException41);
        java.lang.Throwable[] throwable_array45 = notEnoughPersonsException37.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException47 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException37.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        notEnoughPersonsException28.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str56 = notEnoughPersonsException55.toString();
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException55);
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException55);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException60 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException62 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException64 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str65 = notEnoughPersonsException64.toString();
        notEnoughPersonsException62.addSuppressed((java.lang.Throwable) notEnoughPersonsException64);
        notEnoughPersonsException60.addSuppressed((java.lang.Throwable) notEnoughPersonsException64);
        java.lang.Throwable[] throwable_array68 = notEnoughPersonsException60.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException60.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        notEnoughPersonsException28.addSuppressed((java.lang.Throwable) notEnoughPersonsException51);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException75 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array76 = notEnoughPersonsException75.getSuppressed();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException75);
        java.lang.String str78 = notEnoughPersonsException51.toString();
        java.lang.Throwable[] throwable_array79 = notEnoughPersonsException51.getSuppressed();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException51);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str23.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array25);
        org.junit.Assert.assertNotNull(throwable_array26);
        org.junit.Assert.assertTrue("'" + str33 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str33.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str42 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str42.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array45);
        org.junit.Assert.assertTrue("'" + str56 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str56.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str65 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str65.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array68);
        org.junit.Assert.assertNotNull(throwable_array76);
        org.junit.Assert.assertTrue("'" + str78 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str78.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array79);
    }

    @Test
    public void test022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test022");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException7 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str8 = notEnoughPersonsException7.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        java.lang.Throwable[] throwable_array11 = notEnoughPersonsException7.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array15 = notEnoughPersonsException14.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException17 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str22 = notEnoughPersonsException21.toString();
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException21);
        notEnoughPersonsException17.addSuppressed((java.lang.Throwable) notEnoughPersonsException21);
        java.lang.Throwable[] throwable_array25 = notEnoughPersonsException17.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str32 = notEnoughPersonsException31.toString();
        notEnoughPersonsException29.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        java.lang.Throwable[] throwable_array35 = notEnoughPersonsException27.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException42 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException44 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str45 = notEnoughPersonsException44.toString();
        notEnoughPersonsException42.addSuppressed((java.lang.Throwable) notEnoughPersonsException44);
        notEnoughPersonsException40.addSuppressed((java.lang.Throwable) notEnoughPersonsException44);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str54 = notEnoughPersonsException53.toString();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        java.lang.Throwable[] throwable_array57 = notEnoughPersonsException49.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException59 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException59);
        notEnoughPersonsException40.addSuppressed((java.lang.Throwable) notEnoughPersonsException59);
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException59);
        notEnoughPersonsException17.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException65 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException67 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException69 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str70 = notEnoughPersonsException69.toString();
        notEnoughPersonsException67.addSuppressed((java.lang.Throwable) notEnoughPersonsException69);
        notEnoughPersonsException65.addSuppressed((java.lang.Throwable) notEnoughPersonsException69);
        java.lang.Throwable[] throwable_array73 = notEnoughPersonsException65.getSuppressed();
        java.lang.Throwable[] throwable_array74 = notEnoughPersonsException65.getSuppressed();
        notEnoughPersonsException17.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        java.lang.Throwable[] throwable_array77 = notEnoughPersonsException14.getSuppressed();
        notEnoughPersonsException7.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array79 = notEnoughPersonsException7.getSuppressed();
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str8.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array11);
        org.junit.Assert.assertNotNull(throwable_array15);
        org.junit.Assert.assertTrue("'" + str22 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str22.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array25);
        org.junit.Assert.assertTrue("'" + str32 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str32.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array35);
        org.junit.Assert.assertTrue("'" + str45 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str45.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str54 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str54.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array57);
        org.junit.Assert.assertTrue("'" + str70 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str70.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array73);
        org.junit.Assert.assertNotNull(throwable_array74);
        org.junit.Assert.assertNotNull(throwable_array77);
        org.junit.Assert.assertNotNull(throwable_array79);
    }

    @Test
    public void test023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test023");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str28 = notEnoughPersonsException27.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        java.lang.Throwable[] throwable_array31 = notEnoughPersonsException23.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        java.lang.String str37 = notEnoughPersonsException33.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str28 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str28.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array31);
        org.junit.Assert.assertTrue("'" + str37 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str37.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test024");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str28 = notEnoughPersonsException27.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        java.lang.Throwable[] throwable_array31 = notEnoughPersonsException23.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        java.lang.Throwable[] throwable_array37 = notEnoughPersonsException33.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str28 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str28.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array31);
        org.junit.Assert.assertNotNull(throwable_array37);
    }

    @Test
    public void test025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test025");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.String str17 = notEnoughPersonsException11.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str17 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str17.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test026");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        java.lang.String str23 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str30 = notEnoughPersonsException29.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        java.lang.String str32 = notEnoughPersonsException29.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str39 = notEnoughPersonsException38.toString();
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        java.lang.Throwable[] throwable_array42 = notEnoughPersonsException34.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException44 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException44);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException47 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array48 = notEnoughPersonsException47.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException50 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str51 = notEnoughPersonsException50.toString();
        notEnoughPersonsException47.addSuppressed((java.lang.Throwable) notEnoughPersonsException50);
        notEnoughPersonsException44.addSuppressed((java.lang.Throwable) notEnoughPersonsException50);
        notEnoughPersonsException29.addSuppressed((java.lang.Throwable) notEnoughPersonsException44);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException56 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException58 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException60 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str61 = notEnoughPersonsException60.toString();
        notEnoughPersonsException58.addSuppressed((java.lang.Throwable) notEnoughPersonsException60);
        notEnoughPersonsException56.addSuppressed((java.lang.Throwable) notEnoughPersonsException60);
        java.lang.Throwable[] throwable_array64 = notEnoughPersonsException56.getSuppressed();
        java.lang.Throwable[] throwable_array65 = notEnoughPersonsException56.getSuppressed();
        notEnoughPersonsException29.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str23.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str30 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str30.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str32 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str32.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str39 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str39.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array42);
        org.junit.Assert.assertNotNull(throwable_array48);
        org.junit.Assert.assertTrue("'" + str51 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str51.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str61 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str61.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array64);
        org.junit.Assert.assertNotNull(throwable_array65);
    }

    @Test
    public void test027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test027");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str16 = notEnoughPersonsException15.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        java.lang.Throwable[] throwable_array19 = notEnoughPersonsException11.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException21);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str54 = notEnoughPersonsException53.toString();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        java.lang.Throwable[] throwable_array57 = notEnoughPersonsException49.getSuppressed();
        java.lang.Throwable[] throwable_array58 = notEnoughPersonsException49.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        java.lang.String str60 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException62 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str63 = notEnoughPersonsException62.toString();
        java.lang.Throwable[] throwable_array64 = notEnoughPersonsException62.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        java.lang.Throwable[] throwable_array66 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str16.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array19);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str54 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str54.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array57);
        org.junit.Assert.assertNotNull(throwable_array58);
        org.junit.Assert.assertTrue("'" + str60 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str60.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str63 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str63.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array64);
        org.junit.Assert.assertNotNull(throwable_array66);
    }

    @Test
    public void test028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test028");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str16 = notEnoughPersonsException15.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        java.lang.Throwable[] throwable_array19 = notEnoughPersonsException11.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException21);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str54 = notEnoughPersonsException53.toString();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        java.lang.Throwable[] throwable_array57 = notEnoughPersonsException49.getSuppressed();
        java.lang.Throwable[] throwable_array58 = notEnoughPersonsException49.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        java.lang.String str60 = notEnoughPersonsException1.toString();
        java.lang.String str61 = notEnoughPersonsException1.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str16.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array19);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str54 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str54.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array57);
        org.junit.Assert.assertNotNull(throwable_array58);
        org.junit.Assert.assertTrue("'" + str60 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str60.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str61 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str61.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test029");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.String str23 = notEnoughPersonsException1.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str23.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test030");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array3 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array4 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertNotNull(throwable_array3);
        org.junit.Assert.assertNotNull(throwable_array4);
    }

    @Test
    public void test031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test031");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        java.lang.String str47 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str54 = notEnoughPersonsException53.toString();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException58 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException60 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException62 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str63 = notEnoughPersonsException62.toString();
        notEnoughPersonsException60.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        notEnoughPersonsException58.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        java.lang.Throwable[] throwable_array66 = notEnoughPersonsException58.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException58.addSuppressed((java.lang.Throwable) notEnoughPersonsException68);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException68);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        java.lang.Throwable[] throwable_array72 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array73 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str47 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str47.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str54 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str54.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str63 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str63.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array66);
        org.junit.Assert.assertNotNull(throwable_array72);
        org.junit.Assert.assertNotNull(throwable_array73);
    }

    @Test
    public void test032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test032");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array26 = notEnoughPersonsException25.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        java.lang.String str31 = notEnoughPersonsException28.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str39 = notEnoughPersonsException38.toString();
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException47 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str48 = notEnoughPersonsException47.toString();
        notEnoughPersonsException45.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        java.lang.Throwable[] throwable_array51 = notEnoughPersonsException43.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        java.lang.String str56 = notEnoughPersonsException53.toString();
        java.lang.Throwable[] throwable_array57 = notEnoughPersonsException53.getSuppressed();
        java.lang.String str58 = notEnoughPersonsException53.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        java.lang.String str60 = notEnoughPersonsException1.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertNotNull(throwable_array26);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str31 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str31.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str39 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str39.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str48 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str48.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array51);
        org.junit.Assert.assertTrue("'" + str56 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str56.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array57);
        org.junit.Assert.assertTrue("'" + str58 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str58.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str60 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str60.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test033");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException48.getSuppressed();
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.Throwable[] throwable_array51 = notEnoughPersonsException48.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str58 = notEnoughPersonsException57.toString();
        notEnoughPersonsException55.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException53.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException63 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException63);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str71 = notEnoughPersonsException70.toString();
        notEnoughPersonsException68.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        java.lang.Throwable[] throwable_array74 = notEnoughPersonsException66.getSuppressed();
        java.lang.String str75 = notEnoughPersonsException66.toString();
        notEnoughPersonsException63.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        notEnoughPersonsException48.addSuppressed((java.lang.Throwable) notEnoughPersonsException63);
        java.lang.Throwable[] throwable_array78 = notEnoughPersonsException48.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertNotNull(throwable_array49);
        org.junit.Assert.assertNotNull(throwable_array51);
        org.junit.Assert.assertTrue("'" + str58 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str58.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertTrue("'" + str71 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str71.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array74);
        org.junit.Assert.assertTrue("'" + str75 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str75.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array78);
    }

    @Test
    public void test034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test034");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException48.getSuppressed();
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.Throwable[] throwable_array51 = notEnoughPersonsException48.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str58 = notEnoughPersonsException57.toString();
        notEnoughPersonsException55.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException53.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException63 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException63);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str71 = notEnoughPersonsException70.toString();
        notEnoughPersonsException68.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        java.lang.Throwable[] throwable_array74 = notEnoughPersonsException66.getSuppressed();
        java.lang.String str75 = notEnoughPersonsException66.toString();
        notEnoughPersonsException63.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        notEnoughPersonsException48.addSuppressed((java.lang.Throwable) notEnoughPersonsException63);
        java.lang.Throwable[] throwable_array78 = notEnoughPersonsException63.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertNotNull(throwable_array49);
        org.junit.Assert.assertNotNull(throwable_array51);
        org.junit.Assert.assertTrue("'" + str58 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str58.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertTrue("'" + str71 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str71.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array74);
        org.junit.Assert.assertTrue("'" + str75 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str75.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array78);
    }

    @Test
    public void test035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test035");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array26 = notEnoughPersonsException25.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        java.lang.String str31 = notEnoughPersonsException28.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str39 = notEnoughPersonsException38.toString();
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException47 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str48 = notEnoughPersonsException47.toString();
        notEnoughPersonsException45.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        java.lang.Throwable[] throwable_array51 = notEnoughPersonsException43.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        java.lang.String str56 = notEnoughPersonsException53.toString();
        java.lang.Throwable[] throwable_array57 = notEnoughPersonsException53.getSuppressed();
        java.lang.String str58 = notEnoughPersonsException53.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        java.lang.Throwable[] throwable_array60 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertNotNull(throwable_array26);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str31 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str31.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str39 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str39.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str48 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str48.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array51);
        org.junit.Assert.assertTrue("'" + str56 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str56.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array57);
        org.junit.Assert.assertTrue("'" + str58 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str58.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array60);
    }

    @Test
    public void test036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test036");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array10 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str11 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array12 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertNotNull(throwable_array10);
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str11.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array12);
    }

    @Test
    public void test037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test037");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException48.getSuppressed();
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.Throwable[] throwable_array51 = notEnoughPersonsException48.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str58 = notEnoughPersonsException57.toString();
        notEnoughPersonsException55.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException53.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException63 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException63);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str71 = notEnoughPersonsException70.toString();
        notEnoughPersonsException68.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        java.lang.Throwable[] throwable_array74 = notEnoughPersonsException66.getSuppressed();
        java.lang.String str75 = notEnoughPersonsException66.toString();
        notEnoughPersonsException63.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        notEnoughPersonsException48.addSuppressed((java.lang.Throwable) notEnoughPersonsException63);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException79 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException81 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException83 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str84 = notEnoughPersonsException83.toString();
        notEnoughPersonsException81.addSuppressed((java.lang.Throwable) notEnoughPersonsException83);
        notEnoughPersonsException79.addSuppressed((java.lang.Throwable) notEnoughPersonsException83);
        java.lang.Throwable[] throwable_array87 = notEnoughPersonsException79.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException89 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException79.addSuppressed((java.lang.Throwable) notEnoughPersonsException89);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException92 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str93 = notEnoughPersonsException92.toString();
        notEnoughPersonsException89.addSuppressed((java.lang.Throwable) notEnoughPersonsException92);
        notEnoughPersonsException48.addSuppressed((java.lang.Throwable) notEnoughPersonsException92);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertNotNull(throwable_array49);
        org.junit.Assert.assertNotNull(throwable_array51);
        org.junit.Assert.assertTrue("'" + str58 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str58.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertTrue("'" + str71 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str71.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array74);
        org.junit.Assert.assertTrue("'" + str75 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str75.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str84 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str84.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array87);
        org.junit.Assert.assertTrue("'" + str93 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str93.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test038");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str3 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array4 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array5 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException7 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str12 = notEnoughPersonsException11.toString();
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        notEnoughPersonsException7.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        java.lang.Throwable[] throwable_array15 = notEnoughPersonsException7.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException17 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException7.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str25 = notEnoughPersonsException24.toString();
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        notEnoughPersonsException20.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str34 = notEnoughPersonsException33.toString();
        notEnoughPersonsException31.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException29.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        java.lang.Throwable[] throwable_array37 = notEnoughPersonsException29.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException29.addSuppressed((java.lang.Throwable) notEnoughPersonsException39);
        notEnoughPersonsException20.addSuppressed((java.lang.Throwable) notEnoughPersonsException39);
        notEnoughPersonsException7.addSuppressed((java.lang.Throwable) notEnoughPersonsException39);
        java.lang.Throwable[] throwable_array43 = notEnoughPersonsException7.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException47 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str50 = notEnoughPersonsException49.toString();
        notEnoughPersonsException47.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        notEnoughPersonsException45.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        java.lang.Throwable[] throwable_array53 = notEnoughPersonsException45.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException45.addSuppressed((java.lang.Throwable) notEnoughPersonsException55);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException58 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str59 = notEnoughPersonsException58.toString();
        notEnoughPersonsException55.addSuppressed((java.lang.Throwable) notEnoughPersonsException58);
        notEnoughPersonsException7.addSuppressed((java.lang.Throwable) notEnoughPersonsException55);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException55);
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str3 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str3.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array4);
        org.junit.Assert.assertNotNull(throwable_array5);
        org.junit.Assert.assertTrue("'" + str12 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str12.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array15);
        org.junit.Assert.assertTrue("'" + str25 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str25.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str34 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str34.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array37);
        org.junit.Assert.assertNotNull(throwable_array43);
        org.junit.Assert.assertTrue("'" + str50 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str50.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array53);
        org.junit.Assert.assertTrue("'" + str59 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str59.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test039");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str30 = notEnoughPersonsException29.toString();
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        java.lang.Throwable throwable34 = null;
        try {
            notEnoughPersonsException25.addSuppressed(throwable34);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertTrue("'" + str30 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str30.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test040");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException6 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException8 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str9 = notEnoughPersonsException8.toString();
        notEnoughPersonsException6.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.Throwable[] throwable_array12 = notEnoughPersonsException4.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str32 = notEnoughPersonsException31.toString();
        notEnoughPersonsException29.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str41 = notEnoughPersonsException40.toString();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        java.lang.Throwable[] throwable_array44 = notEnoughPersonsException36.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException52 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException54 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException56 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str57 = notEnoughPersonsException56.toString();
        notEnoughPersonsException54.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        notEnoughPersonsException52.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        java.lang.Throwable[] throwable_array60 = notEnoughPersonsException52.getSuppressed();
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException52.getSuppressed();
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException52);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.Throwable[] throwable_array64 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str71 = notEnoughPersonsException70.toString();
        notEnoughPersonsException68.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException75 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException77 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException79 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str80 = notEnoughPersonsException79.toString();
        notEnoughPersonsException77.addSuppressed((java.lang.Throwable) notEnoughPersonsException79);
        notEnoughPersonsException75.addSuppressed((java.lang.Throwable) notEnoughPersonsException79);
        java.lang.Throwable[] throwable_array83 = notEnoughPersonsException75.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException85 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException75.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        java.lang.Throwable[] throwable_array88 = notEnoughPersonsException66.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException90 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array91 = notEnoughPersonsException90.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException93 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str94 = notEnoughPersonsException93.toString();
        notEnoughPersonsException90.addSuppressed((java.lang.Throwable) notEnoughPersonsException93);
        java.lang.String str96 = notEnoughPersonsException93.toString();
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException93);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array12);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str32 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str32.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str41 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str41.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array44);
        org.junit.Assert.assertTrue("'" + str57 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str57.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array60);
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertNotNull(throwable_array64);
        org.junit.Assert.assertTrue("'" + str71 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str71.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str80 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str80.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array83);
        org.junit.Assert.assertNotNull(throwable_array88);
        org.junit.Assert.assertNotNull(throwable_array91);
        org.junit.Assert.assertTrue("'" + str94 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str94.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str96 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str96.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test041");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array26 = notEnoughPersonsException25.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        java.lang.String str31 = notEnoughPersonsException28.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array35 = notEnoughPersonsException34.getSuppressed();
        java.lang.String str36 = notEnoughPersonsException34.toString();
        java.lang.Throwable[] throwable_array37 = notEnoughPersonsException34.getSuppressed();
        java.lang.Throwable[] throwable_array38 = notEnoughPersonsException34.getSuppressed();
        java.lang.String str39 = notEnoughPersonsException34.toString();
        java.lang.String str40 = notEnoughPersonsException34.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertNotNull(throwable_array26);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str31 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str31.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array35);
        org.junit.Assert.assertTrue("'" + str36 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str36.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array37);
        org.junit.Assert.assertNotNull(throwable_array38);
        org.junit.Assert.assertTrue("'" + str39 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str39.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str40 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str40.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test042");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException6 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException8 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str9 = notEnoughPersonsException8.toString();
        notEnoughPersonsException6.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.Throwable[] throwable_array12 = notEnoughPersonsException4.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str32 = notEnoughPersonsException31.toString();
        notEnoughPersonsException29.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str41 = notEnoughPersonsException40.toString();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        java.lang.Throwable[] throwable_array44 = notEnoughPersonsException36.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException52 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException54 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException56 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str57 = notEnoughPersonsException56.toString();
        notEnoughPersonsException54.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        notEnoughPersonsException52.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        java.lang.Throwable[] throwable_array60 = notEnoughPersonsException52.getSuppressed();
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException52.getSuppressed();
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException52);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.Throwable[] throwable_array64 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str67 = notEnoughPersonsException66.toString();
        java.lang.Throwable[] throwable_array68 = notEnoughPersonsException66.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array12);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str32 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str32.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str41 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str41.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array44);
        org.junit.Assert.assertTrue("'" + str57 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str57.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array60);
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertNotNull(throwable_array64);
        org.junit.Assert.assertTrue("'" + str67 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str67.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array68);
    }

    @Test
    public void test043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test043");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        java.lang.String str23 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException30 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str31 = notEnoughPersonsException30.toString();
        notEnoughPersonsException28.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        java.lang.Throwable[] throwable_array34 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str41 = notEnoughPersonsException40.toString();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        java.lang.Throwable[] throwable_array44 = notEnoughPersonsException36.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str54 = notEnoughPersonsException53.toString();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException58 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException60 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException62 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str63 = notEnoughPersonsException62.toString();
        notEnoughPersonsException60.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        notEnoughPersonsException58.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        java.lang.Throwable[] throwable_array66 = notEnoughPersonsException58.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException58.addSuppressed((java.lang.Throwable) notEnoughPersonsException68);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException68);
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException68);
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException74 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException76 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException78 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str79 = notEnoughPersonsException78.toString();
        notEnoughPersonsException76.addSuppressed((java.lang.Throwable) notEnoughPersonsException78);
        notEnoughPersonsException74.addSuppressed((java.lang.Throwable) notEnoughPersonsException78);
        java.lang.Throwable[] throwable_array82 = notEnoughPersonsException74.getSuppressed();
        java.lang.Throwable[] throwable_array83 = notEnoughPersonsException74.getSuppressed();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException74);
        java.lang.String str85 = notEnoughPersonsException26.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException26);
        java.lang.String str87 = notEnoughPersonsException11.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException89 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException91 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str92 = notEnoughPersonsException91.toString();
        notEnoughPersonsException89.addSuppressed((java.lang.Throwable) notEnoughPersonsException91);
        java.lang.Throwable[] throwable_array94 = notEnoughPersonsException91.getSuppressed();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException91);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str23.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str31 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str31.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array34);
        org.junit.Assert.assertTrue("'" + str41 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str41.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array44);
        org.junit.Assert.assertTrue("'" + str54 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str54.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str63 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str63.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array66);
        org.junit.Assert.assertTrue("'" + str79 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str79.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array82);
        org.junit.Assert.assertNotNull(throwable_array83);
        org.junit.Assert.assertTrue("'" + str85 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str85.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str87 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str87.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str92 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str92.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array94);
    }

    @Test
    public void test044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test044");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str16 = notEnoughPersonsException15.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        java.lang.Throwable[] throwable_array19 = notEnoughPersonsException11.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException21);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        java.lang.String str48 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str16.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array19);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str48 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str48.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array49);
    }

    @Test
    public void test045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test045");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: ");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException7 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str8 = notEnoughPersonsException7.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        java.lang.Throwable[] throwable_array11 = notEnoughPersonsException3.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str21 = notEnoughPersonsException20.toString();
        notEnoughPersonsException18.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array24 = notEnoughPersonsException16.getSuppressed();
        java.lang.String str25 = notEnoughPersonsException16.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException16);
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException13.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.String str29 = notEnoughPersonsException13.toString();
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str8.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array11);
        org.junit.Assert.assertTrue("'" + str21 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str21.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array24);
        org.junit.Assert.assertTrue("'" + str25 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str25.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test046");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.String str2 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array3 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertTrue("'" + str2 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: " + "'", str2.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: "));
        org.junit.Assert.assertNotNull(throwable_array3);
    }

    @Test
    public void test047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test047");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str16 = notEnoughPersonsException15.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        java.lang.Throwable[] throwable_array19 = notEnoughPersonsException11.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException21);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str54 = notEnoughPersonsException53.toString();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        java.lang.Throwable[] throwable_array57 = notEnoughPersonsException49.getSuppressed();
        java.lang.Throwable[] throwable_array58 = notEnoughPersonsException49.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException61 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException63 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException65 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str66 = notEnoughPersonsException65.toString();
        notEnoughPersonsException63.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        notEnoughPersonsException61.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        java.lang.Throwable[] throwable_array69 = notEnoughPersonsException61.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException71 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException61.addSuppressed((java.lang.Throwable) notEnoughPersonsException71);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException74 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array75 = notEnoughPersonsException74.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException77 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str78 = notEnoughPersonsException77.toString();
        notEnoughPersonsException74.addSuppressed((java.lang.Throwable) notEnoughPersonsException77);
        notEnoughPersonsException71.addSuppressed((java.lang.Throwable) notEnoughPersonsException77);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException77);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException83 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array84 = notEnoughPersonsException83.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException86 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str87 = notEnoughPersonsException86.toString();
        notEnoughPersonsException83.addSuppressed((java.lang.Throwable) notEnoughPersonsException86);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException83);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str16.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array19);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str54 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str54.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array57);
        org.junit.Assert.assertNotNull(throwable_array58);
        org.junit.Assert.assertTrue("'" + str66 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str66.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array69);
        org.junit.Assert.assertNotNull(throwable_array75);
        org.junit.Assert.assertTrue("'" + str78 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str78.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array84);
        org.junit.Assert.assertTrue("'" + str87 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str87.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test048");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: ");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException7 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str8 = notEnoughPersonsException7.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        java.lang.Throwable[] throwable_array11 = notEnoughPersonsException3.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str21 = notEnoughPersonsException20.toString();
        notEnoughPersonsException18.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array24 = notEnoughPersonsException16.getSuppressed();
        java.lang.String str25 = notEnoughPersonsException16.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException16);
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException13.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array29 = notEnoughPersonsException13.getSuppressed();
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str8.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array11);
        org.junit.Assert.assertTrue("'" + str21 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str21.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array24);
        org.junit.Assert.assertTrue("'" + str25 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str25.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertNotNull(throwable_array29);
    }

    @Test
    public void test049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test049");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str2 = notEnoughPersonsException1.toString();
        java.lang.String str3 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array6 = notEnoughPersonsException5.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException8 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str9 = notEnoughPersonsException8.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.String str11 = notEnoughPersonsException8.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException17 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str18 = notEnoughPersonsException17.toString();
        notEnoughPersonsException15.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        java.lang.Throwable[] throwable_array21 = notEnoughPersonsException13.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str30 = notEnoughPersonsException29.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException8.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.Throwable[] throwable_array35 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str36 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array39 = notEnoughPersonsException38.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str42 = notEnoughPersonsException41.toString();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException41);
        java.lang.String str44 = notEnoughPersonsException41.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException50 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str51 = notEnoughPersonsException50.toString();
        notEnoughPersonsException48.addSuppressed((java.lang.Throwable) notEnoughPersonsException50);
        notEnoughPersonsException46.addSuppressed((java.lang.Throwable) notEnoughPersonsException50);
        java.lang.Throwable[] throwable_array54 = notEnoughPersonsException46.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException56 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException46.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException59 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array60 = notEnoughPersonsException59.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException62 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str63 = notEnoughPersonsException62.toString();
        notEnoughPersonsException59.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        notEnoughPersonsException56.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException72 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str73 = notEnoughPersonsException72.toString();
        notEnoughPersonsException70.addSuppressed((java.lang.Throwable) notEnoughPersonsException72);
        notEnoughPersonsException68.addSuppressed((java.lang.Throwable) notEnoughPersonsException72);
        java.lang.Throwable[] throwable_array76 = notEnoughPersonsException68.getSuppressed();
        java.lang.Throwable[] throwable_array77 = notEnoughPersonsException68.getSuppressed();
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException68);
        java.lang.Throwable[] throwable_array79 = notEnoughPersonsException41.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException81 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException83 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException85 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str86 = notEnoughPersonsException85.toString();
        notEnoughPersonsException83.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        notEnoughPersonsException81.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        java.lang.String str91 = notEnoughPersonsException1.toString();
        org.junit.Assert.assertTrue("'" + str2 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str2.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str3 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str3.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array6);
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str11.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str18 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str18.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array21);
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str30 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str30.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array35);
        org.junit.Assert.assertTrue("'" + str36 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str36.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array39);
        org.junit.Assert.assertTrue("'" + str42 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str42.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str44 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str44.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str51 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str51.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array54);
        org.junit.Assert.assertNotNull(throwable_array60);
        org.junit.Assert.assertTrue("'" + str63 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str63.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str73 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str73.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array76);
        org.junit.Assert.assertNotNull(throwable_array77);
        org.junit.Assert.assertNotNull(throwable_array79);
        org.junit.Assert.assertTrue("'" + str86 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str86.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str91 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str91.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test050");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException5.getSuppressed();
        java.lang.String str10 = notEnoughPersonsException5.toString();
        java.lang.Throwable[] throwable_array11 = notEnoughPersonsException5.getSuppressed();
        java.lang.Throwable[] throwable_array12 = notEnoughPersonsException5.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str10 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str10.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array11);
        org.junit.Assert.assertNotNull(throwable_array12);
    }

    @Test
    public void test051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test051");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str2 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array3 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str4 = notEnoughPersonsException1.toString();
        org.junit.Assert.assertTrue("'" + str2 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str2.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array3);
        org.junit.Assert.assertTrue("'" + str4 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str4.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test052");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException5.getSuppressed();
        java.lang.String str10 = notEnoughPersonsException5.toString();
        java.lang.String str11 = notEnoughPersonsException5.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str10 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str10.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str11.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test053");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException6 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException8 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str9 = notEnoughPersonsException8.toString();
        notEnoughPersonsException6.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.Throwable[] throwable_array12 = notEnoughPersonsException4.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str32 = notEnoughPersonsException31.toString();
        notEnoughPersonsException29.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str41 = notEnoughPersonsException40.toString();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        java.lang.Throwable[] throwable_array44 = notEnoughPersonsException36.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException52 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException54 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException56 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str57 = notEnoughPersonsException56.toString();
        notEnoughPersonsException54.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        notEnoughPersonsException52.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        java.lang.Throwable[] throwable_array60 = notEnoughPersonsException52.getSuppressed();
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException52.getSuppressed();
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException52);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.Throwable[] throwable_array64 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array65 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array12);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str32 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str32.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str41 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str41.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array44);
        org.junit.Assert.assertTrue("'" + str57 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str57.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array60);
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertNotNull(throwable_array64);
        org.junit.Assert.assertNotNull(throwable_array65);
    }

    @Test
    public void test054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test054");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        java.lang.Throwable[] throwable_array8 = notEnoughPersonsException4.getSuppressed();
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException4.getSuppressed();
        java.lang.String str10 = notEnoughPersonsException4.toString();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array8);
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str10 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str10.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test055");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str16 = notEnoughPersonsException15.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        java.lang.Throwable[] throwable_array19 = notEnoughPersonsException11.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException21);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str54 = notEnoughPersonsException53.toString();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        java.lang.Throwable[] throwable_array57 = notEnoughPersonsException49.getSuppressed();
        java.lang.Throwable[] throwable_array58 = notEnoughPersonsException49.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        java.lang.String str60 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array62 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str16.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array19);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str54 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str54.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array57);
        org.junit.Assert.assertNotNull(throwable_array58);
        org.junit.Assert.assertTrue("'" + str60 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str60.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertNotNull(throwable_array62);
    }

    @Test
    public void test056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test056");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array17 = notEnoughPersonsException9.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException22.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str26 = notEnoughPersonsException25.toString();
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str36 = notEnoughPersonsException35.toString();
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException35);
        notEnoughPersonsException31.addSuppressed((java.lang.Throwable) notEnoughPersonsException35);
        java.lang.Throwable[] throwable_array39 = notEnoughPersonsException31.getSuppressed();
        java.lang.Throwable[] throwable_array40 = notEnoughPersonsException31.getSuppressed();
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        java.lang.Throwable[] throwable_array42 = notEnoughPersonsException4.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException44 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str49 = notEnoughPersonsException48.toString();
        notEnoughPersonsException46.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        notEnoughPersonsException44.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException54 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array55 = notEnoughPersonsException54.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str58 = notEnoughPersonsException57.toString();
        notEnoughPersonsException54.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        java.lang.Throwable[] throwable_array60 = notEnoughPersonsException54.getSuppressed();
        java.lang.String str61 = notEnoughPersonsException54.toString();
        notEnoughPersonsException48.addSuppressed((java.lang.Throwable) notEnoughPersonsException54);
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array17);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertTrue("'" + str26 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str26.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str36 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str36.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array39);
        org.junit.Assert.assertNotNull(throwable_array40);
        org.junit.Assert.assertNotNull(throwable_array42);
        org.junit.Assert.assertTrue("'" + str49 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str49.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array55);
        org.junit.Assert.assertTrue("'" + str58 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str58.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array60);
        org.junit.Assert.assertTrue("'" + str61 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str61.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test057");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array17 = notEnoughPersonsException9.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str24 = notEnoughPersonsException23.toString();
        notEnoughPersonsException21.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException19.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException32 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str37 = notEnoughPersonsException36.toString();
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        notEnoughPersonsException32.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str46 = notEnoughPersonsException45.toString();
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException45);
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException45);
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException41.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException51);
        notEnoughPersonsException32.addSuppressed((java.lang.Throwable) notEnoughPersonsException51);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException51);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException59 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException61 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str62 = notEnoughPersonsException61.toString();
        notEnoughPersonsException59.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        notEnoughPersonsException57.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        java.lang.Throwable[] throwable_array65 = notEnoughPersonsException57.getSuppressed();
        java.lang.Throwable[] throwable_array66 = notEnoughPersonsException57.getSuppressed();
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException9);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array71 = notEnoughPersonsException70.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException73 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str74 = notEnoughPersonsException73.toString();
        notEnoughPersonsException70.addSuppressed((java.lang.Throwable) notEnoughPersonsException73);
        java.lang.String str76 = notEnoughPersonsException73.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException78 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException80 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException82 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str83 = notEnoughPersonsException82.toString();
        notEnoughPersonsException80.addSuppressed((java.lang.Throwable) notEnoughPersonsException82);
        notEnoughPersonsException78.addSuppressed((java.lang.Throwable) notEnoughPersonsException82);
        java.lang.Throwable[] throwable_array86 = notEnoughPersonsException78.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException88 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException78.addSuppressed((java.lang.Throwable) notEnoughPersonsException88);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException91 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array92 = notEnoughPersonsException91.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException94 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str95 = notEnoughPersonsException94.toString();
        notEnoughPersonsException91.addSuppressed((java.lang.Throwable) notEnoughPersonsException94);
        notEnoughPersonsException88.addSuppressed((java.lang.Throwable) notEnoughPersonsException94);
        notEnoughPersonsException73.addSuppressed((java.lang.Throwable) notEnoughPersonsException88);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException88);
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array17);
        org.junit.Assert.assertTrue("'" + str24 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str24.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str37 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str37.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str46 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str46.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array49);
        org.junit.Assert.assertTrue("'" + str62 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str62.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array65);
        org.junit.Assert.assertNotNull(throwable_array66);
        org.junit.Assert.assertNotNull(throwable_array71);
        org.junit.Assert.assertTrue("'" + str74 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str74.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str76 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str76.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str83 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str83.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array86);
        org.junit.Assert.assertNotNull(throwable_array92);
        org.junit.Assert.assertTrue("'" + str95 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str95.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test058");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException48.getSuppressed();
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.Throwable[] throwable_array51 = notEnoughPersonsException48.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str58 = notEnoughPersonsException57.toString();
        notEnoughPersonsException55.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException53.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException63 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException63);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str71 = notEnoughPersonsException70.toString();
        notEnoughPersonsException68.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        java.lang.Throwable[] throwable_array74 = notEnoughPersonsException66.getSuppressed();
        java.lang.String str75 = notEnoughPersonsException66.toString();
        notEnoughPersonsException63.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        notEnoughPersonsException48.addSuppressed((java.lang.Throwable) notEnoughPersonsException63);
        java.lang.Throwable throwable78 = null;
        try {
            notEnoughPersonsException48.addSuppressed(throwable78);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertNotNull(throwable_array49);
        org.junit.Assert.assertNotNull(throwable_array51);
        org.junit.Assert.assertTrue("'" + str58 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str58.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertTrue("'" + str71 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str71.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array74);
        org.junit.Assert.assertTrue("'" + str75 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str75.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test059");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array15 = notEnoughPersonsException14.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException17 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str18 = notEnoughPersonsException17.toString();
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        java.lang.String str21 = notEnoughPersonsException11.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertNotNull(throwable_array15);
        org.junit.Assert.assertTrue("'" + str18 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str18.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str21 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str21.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test060");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException10);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        java.lang.Throwable[] throwable_array15 = notEnoughPersonsException13.getSuppressed();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array15);
    }

    @Test
    public void test061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test061");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        java.lang.String str23 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException30 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str31 = notEnoughPersonsException30.toString();
        notEnoughPersonsException28.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        java.lang.Throwable[] throwable_array34 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str40 = notEnoughPersonsException39.toString();
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException39);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        java.lang.String str43 = notEnoughPersonsException14.toString();
        java.lang.String str44 = notEnoughPersonsException14.toString();
        java.lang.Throwable[] throwable_array45 = notEnoughPersonsException14.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str23.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str31 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str31.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array34);
        org.junit.Assert.assertTrue("'" + str40 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str40.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str43 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str43.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str44 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str44.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array45);
    }

    @Test
    public void test062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test062");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str2 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array3 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str4 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array5 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertTrue("'" + str2 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str2.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array3);
        org.junit.Assert.assertTrue("'" + str4 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str4.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array5);
    }

    @Test
    public void test063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test063");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException48.getSuppressed();
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.String str51 = notEnoughPersonsException48.toString();
        java.lang.String str52 = notEnoughPersonsException48.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertNotNull(throwable_array49);
        org.junit.Assert.assertTrue("'" + str51 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: " + "'", str51.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: "));
        org.junit.Assert.assertTrue("'" + str52 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: " + "'", str52.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: "));
    }

    @Test
    public void test064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test064");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        java.lang.String str23 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException30 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str31 = notEnoughPersonsException30.toString();
        notEnoughPersonsException28.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        java.lang.Throwable[] throwable_array34 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str40 = notEnoughPersonsException39.toString();
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException39);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        java.lang.String str43 = notEnoughPersonsException36.toString();
        java.lang.Throwable[] throwable_array44 = notEnoughPersonsException36.getSuppressed();
        java.lang.String str45 = notEnoughPersonsException36.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str23.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str31 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str31.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array34);
        org.junit.Assert.assertTrue("'" + str40 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str40.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str43 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str43.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array44);
        org.junit.Assert.assertTrue("'" + str45 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str45.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test065");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str28 = notEnoughPersonsException27.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        java.lang.Throwable[] throwable_array31 = notEnoughPersonsException23.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array39 = notEnoughPersonsException38.getSuppressed();
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException38.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException47 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str48 = notEnoughPersonsException47.toString();
        notEnoughPersonsException45.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        java.lang.Throwable[] throwable_array51 = notEnoughPersonsException43.getSuppressed();
        java.lang.String str52 = notEnoughPersonsException43.toString();
        java.lang.Throwable[] throwable_array53 = notEnoughPersonsException43.getSuppressed();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        java.lang.Throwable throwable55 = null;
        try {
            notEnoughPersonsException43.addSuppressed(throwable55);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str28 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str28.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array31);
        org.junit.Assert.assertNotNull(throwable_array39);
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str48 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str48.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array51);
        org.junit.Assert.assertTrue("'" + str52 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str52.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array53);
    }

    @Test
    public void test066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test066");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array17 = notEnoughPersonsException9.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException22.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str26 = notEnoughPersonsException25.toString();
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array32 = notEnoughPersonsException31.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str35 = notEnoughPersonsException34.toString();
        notEnoughPersonsException31.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        java.lang.String str37 = notEnoughPersonsException34.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str44 = notEnoughPersonsException43.toString();
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        java.lang.Throwable[] throwable_array47 = notEnoughPersonsException39.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException52 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array53 = notEnoughPersonsException52.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str56 = notEnoughPersonsException55.toString();
        notEnoughPersonsException52.addSuppressed((java.lang.Throwable) notEnoughPersonsException55);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException55);
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException61 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException63 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException65 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str66 = notEnoughPersonsException65.toString();
        notEnoughPersonsException63.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        notEnoughPersonsException61.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        java.lang.Throwable[] throwable_array69 = notEnoughPersonsException61.getSuppressed();
        java.lang.Throwable[] throwable_array70 = notEnoughPersonsException61.getSuppressed();
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        java.lang.String str73 = notEnoughPersonsException4.toString();
        java.lang.String str74 = notEnoughPersonsException4.toString();
        java.lang.String str75 = notEnoughPersonsException4.toString();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array17);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertTrue("'" + str26 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str26.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array32);
        org.junit.Assert.assertTrue("'" + str35 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str35.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str37 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str37.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str44 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str44.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array47);
        org.junit.Assert.assertNotNull(throwable_array53);
        org.junit.Assert.assertTrue("'" + str56 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str56.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str66 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str66.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array69);
        org.junit.Assert.assertNotNull(throwable_array70);
        org.junit.Assert.assertTrue("'" + str73 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str73.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str74 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str74.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str75 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str75.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test067");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array26 = notEnoughPersonsException25.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        java.lang.String str31 = notEnoughPersonsException28.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        java.lang.String str33 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array34 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str41 = notEnoughPersonsException40.toString();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        java.lang.Throwable[] throwable_array44 = notEnoughPersonsException36.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertNotNull(throwable_array26);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str31 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str31.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str33 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str33.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array34);
        org.junit.Assert.assertTrue("'" + str41 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str41.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array44);
    }

    @Test
    public void test068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test068");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str16 = notEnoughPersonsException15.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        java.lang.Throwable[] throwable_array19 = notEnoughPersonsException11.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException21);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str54 = notEnoughPersonsException53.toString();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        java.lang.Throwable[] throwable_array57 = notEnoughPersonsException49.getSuppressed();
        java.lang.Throwable[] throwable_array58 = notEnoughPersonsException49.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        java.lang.String str60 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str62 = notEnoughPersonsException1.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str16.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array19);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str54 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str54.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array57);
        org.junit.Assert.assertNotNull(throwable_array58);
        org.junit.Assert.assertTrue("'" + str60 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str60.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertTrue("'" + str62 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str62.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test069");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str2 = notEnoughPersonsException1.toString();
        java.lang.String str3 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array6 = notEnoughPersonsException5.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException8 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str9 = notEnoughPersonsException8.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.String str11 = notEnoughPersonsException8.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException17 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str18 = notEnoughPersonsException17.toString();
        notEnoughPersonsException15.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        java.lang.Throwable[] throwable_array21 = notEnoughPersonsException13.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str30 = notEnoughPersonsException29.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException8.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array37 = notEnoughPersonsException36.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str40 = notEnoughPersonsException39.toString();
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException39);
        java.lang.String str42 = notEnoughPersonsException39.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException44 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str49 = notEnoughPersonsException48.toString();
        notEnoughPersonsException46.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        notEnoughPersonsException44.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.Throwable[] throwable_array52 = notEnoughPersonsException44.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException54 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException44.addSuppressed((java.lang.Throwable) notEnoughPersonsException54);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array58 = notEnoughPersonsException57.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException60 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str61 = notEnoughPersonsException60.toString();
        notEnoughPersonsException57.addSuppressed((java.lang.Throwable) notEnoughPersonsException60);
        notEnoughPersonsException54.addSuppressed((java.lang.Throwable) notEnoughPersonsException60);
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException54);
        java.lang.String str65 = notEnoughPersonsException39.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException39);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array69 = notEnoughPersonsException68.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException71 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str72 = notEnoughPersonsException71.toString();
        notEnoughPersonsException68.addSuppressed((java.lang.Throwable) notEnoughPersonsException71);
        java.lang.String str74 = notEnoughPersonsException71.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException76 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException78 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException80 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str81 = notEnoughPersonsException80.toString();
        notEnoughPersonsException78.addSuppressed((java.lang.Throwable) notEnoughPersonsException80);
        notEnoughPersonsException76.addSuppressed((java.lang.Throwable) notEnoughPersonsException80);
        java.lang.Throwable[] throwable_array84 = notEnoughPersonsException76.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException86 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException76.addSuppressed((java.lang.Throwable) notEnoughPersonsException86);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException89 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array90 = notEnoughPersonsException89.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException92 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str93 = notEnoughPersonsException92.toString();
        notEnoughPersonsException89.addSuppressed((java.lang.Throwable) notEnoughPersonsException92);
        notEnoughPersonsException86.addSuppressed((java.lang.Throwable) notEnoughPersonsException92);
        notEnoughPersonsException71.addSuppressed((java.lang.Throwable) notEnoughPersonsException86);
        java.lang.Throwable[] throwable_array97 = notEnoughPersonsException71.getSuppressed();
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException71);
        org.junit.Assert.assertTrue("'" + str2 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str2.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str3 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str3.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array6);
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str11.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str18 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str18.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array21);
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str30 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str30.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array37);
        org.junit.Assert.assertTrue("'" + str40 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str40.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str42 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str42.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str49 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str49.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array52);
        org.junit.Assert.assertNotNull(throwable_array58);
        org.junit.Assert.assertTrue("'" + str61 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str61.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str65 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str65.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array69);
        org.junit.Assert.assertTrue("'" + str72 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str72.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str74 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str74.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str81 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str81.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array84);
        org.junit.Assert.assertNotNull(throwable_array90);
        org.junit.Assert.assertTrue("'" + str93 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str93.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array97);
    }

    @Test
    public void test070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test070");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array26 = notEnoughPersonsException25.getSuppressed();
        java.lang.String str27 = notEnoughPersonsException25.toString();
        java.lang.Throwable[] throwable_array28 = notEnoughPersonsException25.getSuppressed();
        java.lang.String str29 = notEnoughPersonsException25.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        java.lang.Throwable[] throwable_array31 = notEnoughPersonsException25.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException50 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str51 = notEnoughPersonsException50.toString();
        notEnoughPersonsException48.addSuppressed((java.lang.Throwable) notEnoughPersonsException50);
        notEnoughPersonsException46.addSuppressed((java.lang.Throwable) notEnoughPersonsException50);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException59 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str60 = notEnoughPersonsException59.toString();
        notEnoughPersonsException57.addSuppressed((java.lang.Throwable) notEnoughPersonsException59);
        notEnoughPersonsException55.addSuppressed((java.lang.Throwable) notEnoughPersonsException59);
        java.lang.Throwable[] throwable_array63 = notEnoughPersonsException55.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException65 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException55.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        notEnoughPersonsException46.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array71 = notEnoughPersonsException70.getSuppressed();
        notEnoughPersonsException65.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        java.lang.Throwable[] throwable_array73 = notEnoughPersonsException70.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException75 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException77 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException79 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str80 = notEnoughPersonsException79.toString();
        notEnoughPersonsException77.addSuppressed((java.lang.Throwable) notEnoughPersonsException79);
        notEnoughPersonsException75.addSuppressed((java.lang.Throwable) notEnoughPersonsException79);
        java.lang.Throwable[] throwable_array83 = notEnoughPersonsException75.getSuppressed();
        java.lang.String str84 = notEnoughPersonsException75.toString();
        java.lang.Throwable[] throwable_array85 = notEnoughPersonsException75.getSuppressed();
        notEnoughPersonsException70.addSuppressed((java.lang.Throwable) notEnoughPersonsException75);
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException75);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertNotNull(throwable_array26);
        org.junit.Assert.assertTrue("'" + str27 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str27.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array28);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array31);
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str51 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str51.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str60 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str60.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array63);
        org.junit.Assert.assertNotNull(throwable_array71);
        org.junit.Assert.assertNotNull(throwable_array73);
        org.junit.Assert.assertTrue("'" + str80 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str80.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array83);
        org.junit.Assert.assertTrue("'" + str84 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str84.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array85);
    }

    @Test
    public void test071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test071");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array26 = notEnoughPersonsException25.getSuppressed();
        java.lang.String str27 = notEnoughPersonsException25.toString();
        java.lang.Throwable[] throwable_array28 = notEnoughPersonsException25.getSuppressed();
        java.lang.String str29 = notEnoughPersonsException25.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        java.lang.Throwable[] throwable_array31 = notEnoughPersonsException25.getSuppressed();
        java.lang.Throwable[] throwable_array32 = notEnoughPersonsException25.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertNotNull(throwable_array26);
        org.junit.Assert.assertTrue("'" + str27 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str27.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array28);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array31);
        org.junit.Assert.assertNotNull(throwable_array32);
    }

    @Test
    public void test072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test072");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        java.lang.String str47 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str54 = notEnoughPersonsException53.toString();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        java.lang.Throwable[] throwable_array58 = notEnoughPersonsException49.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str47 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str47.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str54 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str54.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array58);
    }

    @Test
    public void test073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test073");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str3 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array4 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str5 = notEnoughPersonsException1.toString();
        java.lang.String str6 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array7 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array17 = notEnoughPersonsException9.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException22.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str26 = notEnoughPersonsException25.toString();
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        java.lang.Throwable[] throwable_array29 = notEnoughPersonsException25.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str3 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str3.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array4);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array7);
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array17);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertTrue("'" + str26 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str26.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array29);
    }

    @Test
    public void test074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test074");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str28 = notEnoughPersonsException27.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        java.lang.Throwable[] throwable_array31 = notEnoughPersonsException23.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        java.lang.Throwable[] throwable_array37 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str44 = notEnoughPersonsException43.toString();
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        java.lang.Throwable[] throwable_array47 = notEnoughPersonsException39.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException52 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str53 = notEnoughPersonsException52.toString();
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException52);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        java.lang.String str56 = notEnoughPersonsException49.toString();
        java.lang.Throwable[] throwable_array57 = notEnoughPersonsException49.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str28 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str28.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array31);
        org.junit.Assert.assertNotNull(throwable_array37);
        org.junit.Assert.assertTrue("'" + str44 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str44.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array47);
        org.junit.Assert.assertTrue("'" + str53 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str53.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str56 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str56.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array57);
    }

    @Test
    public void test075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test075");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str28 = notEnoughPersonsException27.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        java.lang.Throwable[] throwable_array31 = notEnoughPersonsException23.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array39 = notEnoughPersonsException38.getSuppressed();
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException38.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException47 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str48 = notEnoughPersonsException47.toString();
        notEnoughPersonsException45.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        java.lang.Throwable[] throwable_array51 = notEnoughPersonsException43.getSuppressed();
        java.lang.String str52 = notEnoughPersonsException43.toString();
        java.lang.Throwable[] throwable_array53 = notEnoughPersonsException43.getSuppressed();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        java.lang.Throwable[] throwable_array55 = notEnoughPersonsException38.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str28 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str28.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array31);
        org.junit.Assert.assertNotNull(throwable_array39);
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str48 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str48.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array51);
        org.junit.Assert.assertTrue("'" + str52 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str52.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array53);
        org.junit.Assert.assertNotNull(throwable_array55);
    }

    @Test
    public void test076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test076");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str2 = notEnoughPersonsException1.toString();
        java.lang.String str3 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array6 = notEnoughPersonsException5.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException8 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str9 = notEnoughPersonsException8.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.String str11 = notEnoughPersonsException8.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException17 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str18 = notEnoughPersonsException17.toString();
        notEnoughPersonsException15.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        java.lang.Throwable[] throwable_array21 = notEnoughPersonsException13.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str30 = notEnoughPersonsException29.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException8.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.Throwable[] throwable_array35 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str36 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array39 = notEnoughPersonsException38.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str42 = notEnoughPersonsException41.toString();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException41);
        java.lang.String str44 = notEnoughPersonsException41.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException50 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str51 = notEnoughPersonsException50.toString();
        notEnoughPersonsException48.addSuppressed((java.lang.Throwable) notEnoughPersonsException50);
        notEnoughPersonsException46.addSuppressed((java.lang.Throwable) notEnoughPersonsException50);
        java.lang.Throwable[] throwable_array54 = notEnoughPersonsException46.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException56 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException46.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException59 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array60 = notEnoughPersonsException59.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException62 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str63 = notEnoughPersonsException62.toString();
        notEnoughPersonsException59.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        notEnoughPersonsException56.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException72 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str73 = notEnoughPersonsException72.toString();
        notEnoughPersonsException70.addSuppressed((java.lang.Throwable) notEnoughPersonsException72);
        notEnoughPersonsException68.addSuppressed((java.lang.Throwable) notEnoughPersonsException72);
        java.lang.Throwable[] throwable_array76 = notEnoughPersonsException68.getSuppressed();
        java.lang.Throwable[] throwable_array77 = notEnoughPersonsException68.getSuppressed();
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException68);
        java.lang.Throwable[] throwable_array79 = notEnoughPersonsException41.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException81 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException83 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException85 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str86 = notEnoughPersonsException85.toString();
        notEnoughPersonsException83.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        notEnoughPersonsException81.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        java.lang.Throwable[] throwable_array91 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertTrue("'" + str2 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str2.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str3 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str3.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array6);
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str11.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str18 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str18.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array21);
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str30 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str30.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array35);
        org.junit.Assert.assertTrue("'" + str36 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str36.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array39);
        org.junit.Assert.assertTrue("'" + str42 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str42.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str44 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str44.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str51 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str51.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array54);
        org.junit.Assert.assertNotNull(throwable_array60);
        org.junit.Assert.assertTrue("'" + str63 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str63.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str73 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str73.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array76);
        org.junit.Assert.assertNotNull(throwable_array77);
        org.junit.Assert.assertNotNull(throwable_array79);
        org.junit.Assert.assertTrue("'" + str86 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str86.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array91);
    }

    @Test
    public void test077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test077");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str10 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array11 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array12 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str10 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str10.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array11);
        org.junit.Assert.assertNotNull(throwable_array12);
    }

    @Test
    public void test078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test078");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException48.getSuppressed();
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.String str51 = notEnoughPersonsException24.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str58 = notEnoughPersonsException57.toString();
        notEnoughPersonsException55.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException53.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException63 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException53.addSuppressed((java.lang.Throwable) notEnoughPersonsException63);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str67 = notEnoughPersonsException66.toString();
        notEnoughPersonsException63.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException63);
        java.lang.Throwable[] throwable_array70 = notEnoughPersonsException24.getSuppressed();
        java.lang.String str71 = notEnoughPersonsException24.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertNotNull(throwable_array49);
        org.junit.Assert.assertTrue("'" + str51 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str51.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str58 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str58.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertTrue("'" + str67 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str67.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array70);
        org.junit.Assert.assertTrue("'" + str71 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str71.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test079");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array17 = notEnoughPersonsException9.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException22.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str26 = notEnoughPersonsException25.toString();
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array32 = notEnoughPersonsException31.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str35 = notEnoughPersonsException34.toString();
        notEnoughPersonsException31.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        java.lang.String str37 = notEnoughPersonsException34.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str44 = notEnoughPersonsException43.toString();
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        java.lang.Throwable[] throwable_array47 = notEnoughPersonsException39.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException52 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array53 = notEnoughPersonsException52.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str56 = notEnoughPersonsException55.toString();
        notEnoughPersonsException52.addSuppressed((java.lang.Throwable) notEnoughPersonsException55);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException55);
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException61 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException63 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException65 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str66 = notEnoughPersonsException65.toString();
        notEnoughPersonsException63.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        notEnoughPersonsException61.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        java.lang.Throwable[] throwable_array69 = notEnoughPersonsException61.getSuppressed();
        java.lang.Throwable[] throwable_array70 = notEnoughPersonsException61.getSuppressed();
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        java.lang.String str73 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException75 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException77 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException79 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str80 = notEnoughPersonsException79.toString();
        notEnoughPersonsException77.addSuppressed((java.lang.Throwable) notEnoughPersonsException79);
        notEnoughPersonsException75.addSuppressed((java.lang.Throwable) notEnoughPersonsException79);
        java.lang.Throwable[] throwable_array83 = notEnoughPersonsException75.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException85 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException75.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        java.lang.String str87 = notEnoughPersonsException85.toString();
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array17);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertTrue("'" + str26 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str26.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array32);
        org.junit.Assert.assertTrue("'" + str35 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str35.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str37 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str37.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str44 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str44.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array47);
        org.junit.Assert.assertNotNull(throwable_array53);
        org.junit.Assert.assertTrue("'" + str56 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str56.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str66 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str66.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array69);
        org.junit.Assert.assertNotNull(throwable_array70);
        org.junit.Assert.assertTrue("'" + str73 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str73.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str80 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str80.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array83);
        org.junit.Assert.assertTrue("'" + str87 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str87.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test080");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        java.lang.String str23 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.String str25 = notEnoughPersonsException11.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str23.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str25 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str25.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test081");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException48.getSuppressed();
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.String str51 = notEnoughPersonsException24.toString();
        java.lang.Throwable[] throwable_array52 = notEnoughPersonsException24.getSuppressed();
        java.lang.String str53 = notEnoughPersonsException24.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertNotNull(throwable_array49);
        org.junit.Assert.assertTrue("'" + str51 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str51.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array52);
        org.junit.Assert.assertTrue("'" + str53 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str53.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test082");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str3 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array4 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array5 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array6 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str7 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array8 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str3 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str3.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array4);
        org.junit.Assert.assertNotNull(throwable_array5);
        org.junit.Assert.assertNotNull(throwable_array6);
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array8);
    }

    @Test
    public void test083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test083");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str28 = notEnoughPersonsException27.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        java.lang.Throwable[] throwable_array31 = notEnoughPersonsException23.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array39 = notEnoughPersonsException38.getSuppressed();
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str28 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str28.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array31);
        org.junit.Assert.assertNotNull(throwable_array39);
        org.junit.Assert.assertNotNull(throwable_array41);
    }

    @Test
    public void test084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test084");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        java.lang.String str23 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException30 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str31 = notEnoughPersonsException30.toString();
        notEnoughPersonsException28.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        java.lang.Throwable[] throwable_array34 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str40 = notEnoughPersonsException39.toString();
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException39);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException44 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str49 = notEnoughPersonsException48.toString();
        notEnoughPersonsException46.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        notEnoughPersonsException44.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.Throwable[] throwable_array52 = notEnoughPersonsException44.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException54 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException44.addSuppressed((java.lang.Throwable) notEnoughPersonsException54);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException59 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException61 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str62 = notEnoughPersonsException61.toString();
        notEnoughPersonsException59.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        notEnoughPersonsException57.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str71 = notEnoughPersonsException70.toString();
        notEnoughPersonsException68.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        java.lang.Throwable[] throwable_array74 = notEnoughPersonsException66.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException76 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException76);
        notEnoughPersonsException57.addSuppressed((java.lang.Throwable) notEnoughPersonsException76);
        notEnoughPersonsException44.addSuppressed((java.lang.Throwable) notEnoughPersonsException76);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException81 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array82 = notEnoughPersonsException81.getSuppressed();
        notEnoughPersonsException76.addSuppressed((java.lang.Throwable) notEnoughPersonsException81);
        java.lang.Throwable[] throwable_array84 = notEnoughPersonsException81.getSuppressed();
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException81);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str23.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str31 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str31.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array34);
        org.junit.Assert.assertTrue("'" + str40 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str40.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str49 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str49.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array52);
        org.junit.Assert.assertTrue("'" + str62 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str62.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str71 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str71.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array74);
        org.junit.Assert.assertNotNull(throwable_array82);
        org.junit.Assert.assertNotNull(throwable_array84);
    }

    @Test
    public void test085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test085");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException6 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException8 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str9 = notEnoughPersonsException8.toString();
        notEnoughPersonsException6.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.Throwable[] throwable_array12 = notEnoughPersonsException4.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str32 = notEnoughPersonsException31.toString();
        notEnoughPersonsException29.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str41 = notEnoughPersonsException40.toString();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        java.lang.Throwable[] throwable_array44 = notEnoughPersonsException36.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException52 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException54 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException56 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str57 = notEnoughPersonsException56.toString();
        notEnoughPersonsException54.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        notEnoughPersonsException52.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        java.lang.Throwable[] throwable_array60 = notEnoughPersonsException52.getSuppressed();
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException52.getSuppressed();
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException52);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException65 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array66 = notEnoughPersonsException65.getSuppressed();
        java.lang.String str67 = notEnoughPersonsException65.toString();
        java.lang.Throwable[] throwable_array68 = notEnoughPersonsException65.getSuppressed();
        java.lang.Throwable[] throwable_array69 = notEnoughPersonsException65.getSuppressed();
        java.lang.String str70 = notEnoughPersonsException65.toString();
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        java.lang.String str72 = notEnoughPersonsException4.toString();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array12);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str32 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str32.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str41 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str41.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array44);
        org.junit.Assert.assertTrue("'" + str57 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str57.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array60);
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertNotNull(throwable_array66);
        org.junit.Assert.assertTrue("'" + str67 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str67.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array68);
        org.junit.Assert.assertNotNull(throwable_array69);
        org.junit.Assert.assertTrue("'" + str70 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str70.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str72 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str72.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test086");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array26 = notEnoughPersonsException25.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        java.lang.String str31 = notEnoughPersonsException28.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        java.lang.String str33 = notEnoughPersonsException28.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str40 = notEnoughPersonsException39.toString();
        notEnoughPersonsException37.addSuppressed((java.lang.Throwable) notEnoughPersonsException39);
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException39);
        java.lang.Throwable[] throwable_array43 = notEnoughPersonsException35.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException45);
        java.lang.String str47 = notEnoughPersonsException45.toString();
        java.lang.Throwable[] throwable_array48 = notEnoughPersonsException45.getSuppressed();
        notEnoughPersonsException28.addSuppressed((java.lang.Throwable) notEnoughPersonsException45);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertNotNull(throwable_array26);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str31 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str31.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str33 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str33.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str40 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str40.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array43);
        org.junit.Assert.assertTrue("'" + str47 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str47.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array48);
    }

    @Test
    public void test087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test087");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException5.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str16 = notEnoughPersonsException15.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str25 = notEnoughPersonsException24.toString();
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        notEnoughPersonsException20.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        java.lang.Throwable[] throwable_array28 = notEnoughPersonsException20.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException30 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException20.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str39 = notEnoughPersonsException38.toString();
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException47 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str48 = notEnoughPersonsException47.toString();
        notEnoughPersonsException45.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        java.lang.Throwable[] throwable_array51 = notEnoughPersonsException43.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        java.lang.String str57 = notEnoughPersonsException34.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str16.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str25 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str25.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array28);
        org.junit.Assert.assertTrue("'" + str39 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str39.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str48 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str48.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array51);
        org.junit.Assert.assertTrue("'" + str57 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str57.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test088");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array17 = notEnoughPersonsException9.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str24 = notEnoughPersonsException23.toString();
        notEnoughPersonsException21.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException19.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException32 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str37 = notEnoughPersonsException36.toString();
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        notEnoughPersonsException32.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str46 = notEnoughPersonsException45.toString();
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException45);
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException45);
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException41.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException51);
        notEnoughPersonsException32.addSuppressed((java.lang.Throwable) notEnoughPersonsException51);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException51);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException59 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException61 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str62 = notEnoughPersonsException61.toString();
        notEnoughPersonsException59.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        notEnoughPersonsException57.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        java.lang.Throwable[] throwable_array65 = notEnoughPersonsException57.getSuppressed();
        java.lang.Throwable[] throwable_array66 = notEnoughPersonsException57.getSuppressed();
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException9);
        java.lang.String str69 = notEnoughPersonsException9.toString();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array17);
        org.junit.Assert.assertTrue("'" + str24 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str24.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str37 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str37.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str46 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str46.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array49);
        org.junit.Assert.assertTrue("'" + str62 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str62.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array65);
        org.junit.Assert.assertNotNull(throwable_array66);
        org.junit.Assert.assertTrue("'" + str69 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str69.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test089");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        java.lang.String str23 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array25 = notEnoughPersonsException11.getSuppressed();
        java.lang.Throwable[] throwable_array26 = notEnoughPersonsException11.getSuppressed();
        java.lang.String str27 = notEnoughPersonsException11.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str23.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array25);
        org.junit.Assert.assertNotNull(throwable_array26);
        org.junit.Assert.assertTrue("'" + str27 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str27.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test090");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str2 = notEnoughPersonsException1.toString();
        java.lang.String str3 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array6 = notEnoughPersonsException5.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException8 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str9 = notEnoughPersonsException8.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.String str11 = notEnoughPersonsException8.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException17 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str18 = notEnoughPersonsException17.toString();
        notEnoughPersonsException15.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        java.lang.Throwable[] throwable_array21 = notEnoughPersonsException13.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str30 = notEnoughPersonsException29.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException8.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.Throwable[] throwable_array35 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array36 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException42 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str43 = notEnoughPersonsException42.toString();
        notEnoughPersonsException40.addSuppressed((java.lang.Throwable) notEnoughPersonsException42);
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException42);
        java.lang.Throwable[] throwable_array46 = notEnoughPersonsException38.getSuppressed();
        java.lang.Throwable[] throwable_array47 = notEnoughPersonsException38.getSuppressed();
        java.lang.String str48 = notEnoughPersonsException38.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        org.junit.Assert.assertTrue("'" + str2 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str2.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str3 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str3.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array6);
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str11.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str18 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str18.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array21);
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str30 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str30.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array35);
        org.junit.Assert.assertNotNull(throwable_array36);
        org.junit.Assert.assertTrue("'" + str43 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str43.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array46);
        org.junit.Assert.assertNotNull(throwable_array47);
        org.junit.Assert.assertTrue("'" + str48 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str48.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test091");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str16 = notEnoughPersonsException15.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        java.lang.Throwable[] throwable_array19 = notEnoughPersonsException11.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException21);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str54 = notEnoughPersonsException53.toString();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        java.lang.Throwable[] throwable_array57 = notEnoughPersonsException49.getSuppressed();
        java.lang.Throwable[] throwable_array58 = notEnoughPersonsException49.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        java.lang.String str60 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException63 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array64 = notEnoughPersonsException63.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str67 = notEnoughPersonsException66.toString();
        notEnoughPersonsException63.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        java.lang.String str69 = notEnoughPersonsException66.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str16.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array19);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str54 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str54.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array57);
        org.junit.Assert.assertNotNull(throwable_array58);
        org.junit.Assert.assertTrue("'" + str60 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str60.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertNotNull(throwable_array64);
        org.junit.Assert.assertTrue("'" + str67 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str67.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str69 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str69.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test092");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array17 = notEnoughPersonsException9.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str24 = notEnoughPersonsException23.toString();
        notEnoughPersonsException21.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException19.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException32 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str37 = notEnoughPersonsException36.toString();
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        notEnoughPersonsException32.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str46 = notEnoughPersonsException45.toString();
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException45);
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException45);
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException41.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException51);
        notEnoughPersonsException32.addSuppressed((java.lang.Throwable) notEnoughPersonsException51);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException51);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException57 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException59 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException61 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str62 = notEnoughPersonsException61.toString();
        notEnoughPersonsException59.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        notEnoughPersonsException57.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        java.lang.Throwable[] throwable_array65 = notEnoughPersonsException57.getSuppressed();
        java.lang.Throwable[] throwable_array66 = notEnoughPersonsException57.getSuppressed();
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException57);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException9);
        java.lang.Throwable[] throwable_array69 = notEnoughPersonsException4.getSuppressed();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array17);
        org.junit.Assert.assertTrue("'" + str24 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str24.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str37 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str37.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str46 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str46.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array49);
        org.junit.Assert.assertTrue("'" + str62 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str62.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array65);
        org.junit.Assert.assertNotNull(throwable_array66);
        org.junit.Assert.assertNotNull(throwable_array69);
    }

    @Test
    public void test093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test093");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str28 = notEnoughPersonsException27.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        java.lang.Throwable[] throwable_array31 = notEnoughPersonsException23.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException42 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException44 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str45 = notEnoughPersonsException44.toString();
        notEnoughPersonsException42.addSuppressed((java.lang.Throwable) notEnoughPersonsException44);
        notEnoughPersonsException40.addSuppressed((java.lang.Throwable) notEnoughPersonsException44);
        java.lang.Throwable[] throwable_array48 = notEnoughPersonsException44.getSuppressed();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException44);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException44);
        java.lang.String str51 = notEnoughPersonsException44.toString();
        java.lang.Throwable[] throwable_array52 = notEnoughPersonsException44.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException54 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException56 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str57 = notEnoughPersonsException56.toString();
        notEnoughPersonsException54.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        java.lang.Throwable[] throwable_array59 = notEnoughPersonsException56.getSuppressed();
        java.lang.Throwable[] throwable_array60 = notEnoughPersonsException56.getSuppressed();
        notEnoughPersonsException44.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str28 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str28.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array31);
        org.junit.Assert.assertTrue("'" + str45 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str45.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array48);
        org.junit.Assert.assertTrue("'" + str51 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str51.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array52);
        org.junit.Assert.assertTrue("'" + str57 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str57.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array59);
        org.junit.Assert.assertNotNull(throwable_array60);
    }

    @Test
    public void test094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test094");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: ");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException7 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str8 = notEnoughPersonsException7.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        java.lang.Throwable[] throwable_array11 = notEnoughPersonsException3.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str21 = notEnoughPersonsException20.toString();
        notEnoughPersonsException18.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array24 = notEnoughPersonsException16.getSuppressed();
        java.lang.String str25 = notEnoughPersonsException16.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException16);
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException13.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array29 = notEnoughPersonsException1.getSuppressed();
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str8.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array11);
        org.junit.Assert.assertTrue("'" + str21 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str21.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array24);
        org.junit.Assert.assertTrue("'" + str25 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str25.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertNotNull(throwable_array29);
    }

    @Test
    public void test095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test095");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array3 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str4 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array5 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str6 = notEnoughPersonsException1.toString();
        java.lang.String str7 = notEnoughPersonsException1.toString();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertNotNull(throwable_array3);
        org.junit.Assert.assertTrue("'" + str4 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: " + "'", str4.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: "));
        org.junit.Assert.assertNotNull(throwable_array5);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: " + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: "));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: " + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: "));
    }

    @Test
    public void test096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test096");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str4 = notEnoughPersonsException3.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException3);
        java.lang.Throwable[] throwable_array6 = notEnoughPersonsException3.getSuppressed();
        java.lang.Throwable[] throwable_array7 = notEnoughPersonsException3.getSuppressed();
        java.lang.String str8 = notEnoughPersonsException3.toString();
        org.junit.Assert.assertTrue("'" + str4 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str4.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array6);
        org.junit.Assert.assertNotNull(throwable_array7);
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str8.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test097");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array17 = notEnoughPersonsException9.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException22.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str26 = notEnoughPersonsException25.toString();
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array17);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertTrue("'" + str26 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str26.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test098");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array17 = notEnoughPersonsException9.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException22.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str26 = notEnoughPersonsException25.toString();
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array32 = notEnoughPersonsException31.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str35 = notEnoughPersonsException34.toString();
        notEnoughPersonsException31.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        java.lang.String str37 = notEnoughPersonsException34.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str44 = notEnoughPersonsException43.toString();
        notEnoughPersonsException41.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        java.lang.Throwable[] throwable_array47 = notEnoughPersonsException39.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException39.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException52 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array53 = notEnoughPersonsException52.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str56 = notEnoughPersonsException55.toString();
        notEnoughPersonsException52.addSuppressed((java.lang.Throwable) notEnoughPersonsException55);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException55);
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException61 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException63 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException65 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str66 = notEnoughPersonsException65.toString();
        notEnoughPersonsException63.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        notEnoughPersonsException61.addSuppressed((java.lang.Throwable) notEnoughPersonsException65);
        java.lang.Throwable[] throwable_array69 = notEnoughPersonsException61.getSuppressed();
        java.lang.Throwable[] throwable_array70 = notEnoughPersonsException61.getSuppressed();
        notEnoughPersonsException34.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        java.lang.String str73 = notEnoughPersonsException34.toString();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array17);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertTrue("'" + str26 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str26.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array32);
        org.junit.Assert.assertTrue("'" + str35 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str35.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str37 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str37.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str44 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str44.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array47);
        org.junit.Assert.assertNotNull(throwable_array53);
        org.junit.Assert.assertTrue("'" + str56 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str56.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str66 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str66.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array69);
        org.junit.Assert.assertNotNull(throwable_array70);
        org.junit.Assert.assertTrue("'" + str73 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str73.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test099");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.Throwable[] throwable_array7 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str27 = notEnoughPersonsException26.toString();
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException26);
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException26);
        java.lang.Throwable[] throwable_array30 = notEnoughPersonsException22.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException32 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException32);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException22);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str41 = notEnoughPersonsException40.toString();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        java.lang.Throwable[] throwable_array44 = notEnoughPersonsException36.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str54 = notEnoughPersonsException53.toString();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException58 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException60 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException62 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str63 = notEnoughPersonsException62.toString();
        notEnoughPersonsException60.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        notEnoughPersonsException58.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        java.lang.Throwable[] throwable_array66 = notEnoughPersonsException58.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException58.addSuppressed((java.lang.Throwable) notEnoughPersonsException68);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException68);
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException68);
        java.lang.Throwable[] throwable_array72 = notEnoughPersonsException36.getSuppressed();
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        java.lang.Throwable[] throwable_array74 = notEnoughPersonsException22.getSuppressed();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array7);
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str27 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str27.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array30);
        org.junit.Assert.assertTrue("'" + str41 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str41.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array44);
        org.junit.Assert.assertTrue("'" + str54 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str54.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str63 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str63.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array66);
        org.junit.Assert.assertNotNull(throwable_array72);
        org.junit.Assert.assertNotNull(throwable_array74);
    }

    @Test
    public void test100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test100");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        java.lang.String str23 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException30 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str31 = notEnoughPersonsException30.toString();
        notEnoughPersonsException28.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        java.lang.Throwable[] throwable_array34 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str40 = notEnoughPersonsException39.toString();
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException39);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        java.lang.String str43 = notEnoughPersonsException36.toString();
        java.lang.String str44 = notEnoughPersonsException36.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str23.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str31 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str31.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array34);
        org.junit.Assert.assertTrue("'" + str40 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str40.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str43 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str43.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str44 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str44.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test101");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str2 = notEnoughPersonsException1.toString();
        java.lang.Throwable[] throwable_array3 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array4 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException6 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str7 = notEnoughPersonsException6.toString();
        java.lang.Throwable[] throwable_array8 = notEnoughPersonsException6.getSuppressed();
        java.lang.String str9 = notEnoughPersonsException6.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException6);
        org.junit.Assert.assertTrue("'" + str2 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str2.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array3);
        org.junit.Assert.assertNotNull(throwable_array4);
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array8);
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test102");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array3 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array6 = notEnoughPersonsException5.getSuppressed();
        java.lang.String str7 = notEnoughPersonsException5.toString();
        java.lang.Throwable[] throwable_array8 = notEnoughPersonsException5.getSuppressed();
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException5.getSuppressed();
        java.lang.String str10 = notEnoughPersonsException5.toString();
        java.lang.String str11 = notEnoughPersonsException5.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.String str13 = notEnoughPersonsException1.toString();
        java.lang.Throwable throwable14 = null;
        try {
            notEnoughPersonsException1.addSuppressed(throwable14);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertNotNull(throwable_array3);
        org.junit.Assert.assertNotNull(throwable_array6);
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array8);
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str10 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str10.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str11.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: " + "'", str13.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: "));
    }

    @Test
    public void test103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test103");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException6 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException8 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str9 = notEnoughPersonsException8.toString();
        notEnoughPersonsException6.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.Throwable[] throwable_array12 = notEnoughPersonsException4.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException31 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str32 = notEnoughPersonsException31.toString();
        notEnoughPersonsException29.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException31);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str41 = notEnoughPersonsException40.toString();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        java.lang.Throwable[] throwable_array44 = notEnoughPersonsException36.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException46 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException46);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException52 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException54 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException56 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str57 = notEnoughPersonsException56.toString();
        notEnoughPersonsException54.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        notEnoughPersonsException52.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        java.lang.Throwable[] throwable_array60 = notEnoughPersonsException52.getSuppressed();
        java.lang.Throwable[] throwable_array61 = notEnoughPersonsException52.getSuppressed();
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException52);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str64 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str71 = notEnoughPersonsException70.toString();
        notEnoughPersonsException68.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException75 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException77 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException79 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str80 = notEnoughPersonsException79.toString();
        notEnoughPersonsException77.addSuppressed((java.lang.Throwable) notEnoughPersonsException79);
        notEnoughPersonsException75.addSuppressed((java.lang.Throwable) notEnoughPersonsException79);
        java.lang.Throwable[] throwable_array83 = notEnoughPersonsException75.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException85 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException75.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        java.lang.String str88 = notEnoughPersonsException85.toString();
        java.lang.Throwable[] throwable_array89 = notEnoughPersonsException85.getSuppressed();
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException85);
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array12);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str32 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str32.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str41 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str41.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array44);
        org.junit.Assert.assertTrue("'" + str57 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str57.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array60);
        org.junit.Assert.assertNotNull(throwable_array61);
        org.junit.Assert.assertTrue("'" + str64 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str64.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str71 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str71.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str80 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str80.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array83);
        org.junit.Assert.assertTrue("'" + str88 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str88.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array89);
    }

    @Test
    public void test104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test104");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        java.lang.Throwable[] throwable_array17 = notEnoughPersonsException9.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException19 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException22.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str26 = notEnoughPersonsException25.toString();
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException19.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException19);
        java.lang.Throwable[] throwable_array30 = notEnoughPersonsException4.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException32 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array33 = notEnoughPersonsException32.getSuppressed();
        java.lang.Throwable[] throwable_array34 = notEnoughPersonsException32.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array37 = notEnoughPersonsException36.getSuppressed();
        java.lang.String str38 = notEnoughPersonsException36.toString();
        java.lang.Throwable[] throwable_array39 = notEnoughPersonsException36.getSuppressed();
        java.lang.Throwable[] throwable_array40 = notEnoughPersonsException36.getSuppressed();
        java.lang.String str41 = notEnoughPersonsException36.toString();
        java.lang.String str42 = notEnoughPersonsException36.toString();
        notEnoughPersonsException32.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        notEnoughPersonsException4.addSuppressed((java.lang.Throwable) notEnoughPersonsException32);
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array17);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertTrue("'" + str26 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str26.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array30);
        org.junit.Assert.assertNotNull(throwable_array33);
        org.junit.Assert.assertNotNull(throwable_array34);
        org.junit.Assert.assertNotNull(throwable_array37);
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array39);
        org.junit.Assert.assertNotNull(throwable_array40);
        org.junit.Assert.assertTrue("'" + str41 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str41.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str42 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str42.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test105");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str28 = notEnoughPersonsException27.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        java.lang.Throwable[] throwable_array31 = notEnoughPersonsException23.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException33);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array39 = notEnoughPersonsException38.getSuppressed();
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException38);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException38.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException47 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str48 = notEnoughPersonsException47.toString();
        notEnoughPersonsException45.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        java.lang.Throwable[] throwable_array51 = notEnoughPersonsException43.getSuppressed();
        java.lang.String str52 = notEnoughPersonsException43.toString();
        java.lang.Throwable[] throwable_array53 = notEnoughPersonsException43.getSuppressed();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException56 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str57 = notEnoughPersonsException56.toString();
        java.lang.Throwable[] throwable_array58 = notEnoughPersonsException56.getSuppressed();
        java.lang.String str59 = notEnoughPersonsException56.toString();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str28 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str28.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array31);
        org.junit.Assert.assertNotNull(throwable_array39);
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str48 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str48.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array51);
        org.junit.Assert.assertTrue("'" + str52 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str52.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array53);
        org.junit.Assert.assertTrue("'" + str57 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str57.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array58);
        org.junit.Assert.assertTrue("'" + str59 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str59.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test106");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: ");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException7 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str8 = notEnoughPersonsException7.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException7);
        java.lang.Throwable[] throwable_array11 = notEnoughPersonsException3.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str21 = notEnoughPersonsException20.toString();
        notEnoughPersonsException18.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array24 = notEnoughPersonsException16.getSuppressed();
        java.lang.String str25 = notEnoughPersonsException16.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException16);
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException13.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException30 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException32 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException34 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str35 = notEnoughPersonsException34.toString();
        notEnoughPersonsException32.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        notEnoughPersonsException30.addSuppressed((java.lang.Throwable) notEnoughPersonsException34);
        java.lang.Throwable[] throwable_array38 = notEnoughPersonsException30.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException30.addSuppressed((java.lang.Throwable) notEnoughPersonsException40);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException47 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str48 = notEnoughPersonsException47.toString();
        notEnoughPersonsException45.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException47);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException52 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException54 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException56 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str57 = notEnoughPersonsException56.toString();
        notEnoughPersonsException54.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        notEnoughPersonsException52.addSuppressed((java.lang.Throwable) notEnoughPersonsException56);
        java.lang.Throwable[] throwable_array60 = notEnoughPersonsException52.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException62 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException52.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        notEnoughPersonsException43.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        notEnoughPersonsException30.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException67 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array68 = notEnoughPersonsException67.getSuppressed();
        notEnoughPersonsException62.addSuppressed((java.lang.Throwable) notEnoughPersonsException67);
        java.lang.String str70 = notEnoughPersonsException62.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str8.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array11);
        org.junit.Assert.assertTrue("'" + str21 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str21.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array24);
        org.junit.Assert.assertTrue("'" + str25 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str25.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str35 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str35.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array38);
        org.junit.Assert.assertTrue("'" + str48 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str48.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str57 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str57.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array60);
        org.junit.Assert.assertNotNull(throwable_array68);
        org.junit.Assert.assertTrue("'" + str70 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str70.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test107");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array3 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException7 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str10 = notEnoughPersonsException9.toString();
        notEnoughPersonsException7.addSuppressed((java.lang.Throwable) notEnoughPersonsException9);
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException9);
        java.lang.Throwable[] throwable_array13 = notEnoughPersonsException5.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException15.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.String str22 = notEnoughPersonsException1.toString();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertNotNull(throwable_array3);
        org.junit.Assert.assertTrue("'" + str10 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str10.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array13);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str22 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: " + "'", str22.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: "));
    }

    @Test
    public void test108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test108");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str16 = notEnoughPersonsException15.toString();
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        java.lang.Throwable[] throwable_array19 = notEnoughPersonsException11.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException21 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException21);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException53 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str54 = notEnoughPersonsException53.toString();
        notEnoughPersonsException51.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        notEnoughPersonsException49.addSuppressed((java.lang.Throwable) notEnoughPersonsException53);
        java.lang.Throwable[] throwable_array57 = notEnoughPersonsException49.getSuppressed();
        java.lang.Throwable[] throwable_array58 = notEnoughPersonsException49.getSuppressed();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        java.lang.String str60 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException62 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException64 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str67 = notEnoughPersonsException66.toString();
        notEnoughPersonsException64.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        notEnoughPersonsException62.addSuppressed((java.lang.Throwable) notEnoughPersonsException66);
        java.lang.Throwable[] throwable_array70 = notEnoughPersonsException62.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException72 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException62.addSuppressed((java.lang.Throwable) notEnoughPersonsException72);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException75 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array76 = notEnoughPersonsException75.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException78 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str79 = notEnoughPersonsException78.toString();
        notEnoughPersonsException75.addSuppressed((java.lang.Throwable) notEnoughPersonsException78);
        notEnoughPersonsException72.addSuppressed((java.lang.Throwable) notEnoughPersonsException78);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException78);
        java.lang.Throwable[] throwable_array83 = notEnoughPersonsException78.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str16.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array19);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertTrue("'" + str54 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str54.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array57);
        org.junit.Assert.assertNotNull(throwable_array58);
        org.junit.Assert.assertTrue("'" + str60 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str60.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str67 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str67.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array70);
        org.junit.Assert.assertNotNull(throwable_array76);
        org.junit.Assert.assertTrue("'" + str79 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str79.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array83);
    }

    @Test
    public void test109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test109");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.Throwable[] throwable_array7 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str14 = notEnoughPersonsException13.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        notEnoughPersonsException9.addSuppressed((java.lang.Throwable) notEnoughPersonsException13);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException22 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str27 = notEnoughPersonsException26.toString();
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException26);
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException26);
        java.lang.Throwable[] throwable_array30 = notEnoughPersonsException22.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException32 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException22.addSuppressed((java.lang.Throwable) notEnoughPersonsException32);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException22);
        java.lang.Throwable[] throwable_array35 = notEnoughPersonsException22.getSuppressed();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array7);
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str14.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str27 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str27.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array30);
        org.junit.Assert.assertNotNull(throwable_array35);
    }

    @Test
    public void test110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test110");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str10 = notEnoughPersonsException1.toString();
        java.lang.String str11 = notEnoughPersonsException1.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str10 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str10.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str11.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test111");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException4 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str5 = notEnoughPersonsException4.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException4);
        java.lang.String str7 = notEnoughPersonsException4.toString();
        java.lang.Throwable[] throwable_array8 = notEnoughPersonsException4.getSuppressed();
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException4.getSuppressed();
        java.lang.Throwable[] throwable_array10 = notEnoughPersonsException4.getSuppressed();
        java.lang.String str11 = notEnoughPersonsException4.toString();
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertTrue("'" + str5 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str5.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str7 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str7.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array8);
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertNotNull(throwable_array10);
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str11.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test112");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        java.lang.Throwable[] throwable_array23 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str30 = notEnoughPersonsException29.toString();
        notEnoughPersonsException27.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException25);
        java.lang.String str34 = notEnoughPersonsException25.toString();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertNotNull(throwable_array23);
        org.junit.Assert.assertTrue("'" + str30 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str30.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str34 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str34.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test113");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException12 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str15 = notEnoughPersonsException14.toString();
        notEnoughPersonsException12.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        java.lang.Throwable[] throwable_array18 = notEnoughPersonsException10.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException20 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException20);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException24 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str29 = notEnoughPersonsException28.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException28);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException33 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException35 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException37 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str38 = notEnoughPersonsException37.toString();
        notEnoughPersonsException35.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException37);
        java.lang.Throwable[] throwable_array41 = notEnoughPersonsException33.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException43 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException33.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException43);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException24);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array49 = notEnoughPersonsException48.getSuppressed();
        notEnoughPersonsException24.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.String str51 = notEnoughPersonsException24.toString();
        java.lang.String str52 = notEnoughPersonsException24.toString();
        java.lang.Throwable[] throwable_array53 = notEnoughPersonsException24.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str15.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array18);
        org.junit.Assert.assertTrue("'" + str29 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str29.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str38 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str38.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array41);
        org.junit.Assert.assertNotNull(throwable_array49);
        org.junit.Assert.assertTrue("'" + str51 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str51.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str52 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str52.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array53);
    }

    @Test
    public void test114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test114");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException10 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException10);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException17 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str18 = notEnoughPersonsException17.toString();
        notEnoughPersonsException15.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        java.lang.Throwable[] throwable_array21 = notEnoughPersonsException13.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException30 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str31 = notEnoughPersonsException30.toString();
        notEnoughPersonsException28.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        java.lang.Throwable[] throwable_array34 = notEnoughPersonsException26.getSuppressed();
        java.lang.String str35 = notEnoughPersonsException26.toString();
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException26);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException40 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException42 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str43 = notEnoughPersonsException42.toString();
        notEnoughPersonsException40.addSuppressed((java.lang.Throwable) notEnoughPersonsException42);
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException42);
        java.lang.Throwable[] throwable_array46 = notEnoughPersonsException38.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException48 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException51 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str52 = notEnoughPersonsException51.toString();
        notEnoughPersonsException48.addSuppressed((java.lang.Throwable) notEnoughPersonsException51);
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException48);
        java.lang.String str55 = notEnoughPersonsException26.toString();
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException26);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException58 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array59 = notEnoughPersonsException58.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException61 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str62 = notEnoughPersonsException61.toString();
        notEnoughPersonsException58.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        java.lang.String str64 = notEnoughPersonsException61.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException66 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException68 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException70 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str71 = notEnoughPersonsException70.toString();
        notEnoughPersonsException68.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException70);
        java.lang.Throwable[] throwable_array74 = notEnoughPersonsException66.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException76 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException66.addSuppressed((java.lang.Throwable) notEnoughPersonsException76);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException79 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array80 = notEnoughPersonsException79.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException82 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str83 = notEnoughPersonsException82.toString();
        notEnoughPersonsException79.addSuppressed((java.lang.Throwable) notEnoughPersonsException82);
        notEnoughPersonsException76.addSuppressed((java.lang.Throwable) notEnoughPersonsException82);
        notEnoughPersonsException61.addSuppressed((java.lang.Throwable) notEnoughPersonsException76);
        java.lang.Throwable[] throwable_array87 = notEnoughPersonsException61.getSuppressed();
        notEnoughPersonsException10.addSuppressed((java.lang.Throwable) notEnoughPersonsException61);
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str18 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str18.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array21);
        org.junit.Assert.assertTrue("'" + str31 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str31.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array34);
        org.junit.Assert.assertTrue("'" + str35 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str35.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str43 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str43.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array46);
        org.junit.Assert.assertTrue("'" + str52 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str52.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str55 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str55.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array59);
        org.junit.Assert.assertTrue("'" + str62 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str62.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str64 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str64.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str71 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str71.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array74);
        org.junit.Assert.assertNotNull(throwable_array80);
        org.junit.Assert.assertTrue("'" + str83 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str83.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array87);
    }

    @Test
    public void test115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test115");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("");
        java.lang.Throwable[] throwable_array2 = notEnoughPersonsException1.getSuppressed();
        java.lang.Throwable[] throwable_array3 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException7 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException9 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str10 = notEnoughPersonsException9.toString();
        notEnoughPersonsException7.addSuppressed((java.lang.Throwable) notEnoughPersonsException9);
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException9);
        java.lang.Throwable[] throwable_array13 = notEnoughPersonsException5.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException15);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException15.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException25 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException27 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str28 = notEnoughPersonsException27.toString();
        notEnoughPersonsException25.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException27);
        java.lang.Throwable[] throwable_array31 = notEnoughPersonsException23.getSuppressed();
        java.lang.Throwable[] throwable_array32 = notEnoughPersonsException23.getSuppressed();
        java.lang.String str33 = notEnoughPersonsException23.toString();
        notEnoughPersonsException18.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        org.junit.Assert.assertNotNull(throwable_array2);
        org.junit.Assert.assertNotNull(throwable_array3);
        org.junit.Assert.assertTrue("'" + str10 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str10.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array13);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str28 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str28.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array31);
        org.junit.Assert.assertNotNull(throwable_array32);
        org.junit.Assert.assertTrue("'" + str33 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str33.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test116");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str2 = notEnoughPersonsException1.toString();
        java.lang.String str3 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array6 = notEnoughPersonsException5.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException8 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str9 = notEnoughPersonsException8.toString();
        notEnoughPersonsException5.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.String str11 = notEnoughPersonsException8.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException13 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException15 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException17 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str18 = notEnoughPersonsException17.toString();
        notEnoughPersonsException15.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException17);
        java.lang.Throwable[] throwable_array21 = notEnoughPersonsException13.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException23 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException13.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array27 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException29 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str30 = notEnoughPersonsException29.toString();
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException23.addSuppressed((java.lang.Throwable) notEnoughPersonsException29);
        notEnoughPersonsException8.addSuppressed((java.lang.Throwable) notEnoughPersonsException23);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException8);
        java.lang.Throwable[] throwable_array35 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str36 = notEnoughPersonsException1.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException38 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array39 = notEnoughPersonsException38.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException41 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str42 = notEnoughPersonsException41.toString();
        notEnoughPersonsException38.addSuppressed((java.lang.Throwable) notEnoughPersonsException41);
        java.lang.String str44 = notEnoughPersonsException41.toString();
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException41);
        java.lang.Throwable[] throwable_array46 = notEnoughPersonsException1.getSuppressed();
        java.lang.String str47 = notEnoughPersonsException1.toString();
        org.junit.Assert.assertTrue("'" + str2 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str2.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str3 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str3.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array6);
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str9.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str11.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str18 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str18.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array21);
        org.junit.Assert.assertNotNull(throwable_array27);
        org.junit.Assert.assertTrue("'" + str30 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str30.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array35);
        org.junit.Assert.assertTrue("'" + str36 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str36.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array39);
        org.junit.Assert.assertTrue("'" + str42 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str42.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str44 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str44.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array46);
        org.junit.Assert.assertTrue("'" + str47 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str47.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
    }

    @Test
    public void test117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "NotEnoughPersonsExceptionRegressionTest0.test117");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException1 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException3 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException5 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str6 = notEnoughPersonsException5.toString();
        notEnoughPersonsException3.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException5);
        java.lang.Throwable[] throwable_array9 = notEnoughPersonsException1.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException11 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException1.addSuppressed((java.lang.Throwable) notEnoughPersonsException11);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException14 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException16 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException18 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str19 = notEnoughPersonsException18.toString();
        notEnoughPersonsException16.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException18);
        java.lang.Throwable[] throwable_array22 = notEnoughPersonsException14.getSuppressed();
        java.lang.String str23 = notEnoughPersonsException14.toString();
        notEnoughPersonsException11.addSuppressed((java.lang.Throwable) notEnoughPersonsException14);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException26 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException28 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException30 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str31 = notEnoughPersonsException30.toString();
        notEnoughPersonsException28.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException30);
        java.lang.Throwable[] throwable_array34 = notEnoughPersonsException26.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException36 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException26.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException39 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        java.lang.String str40 = notEnoughPersonsException39.toString();
        notEnoughPersonsException36.addSuppressed((java.lang.Throwable) notEnoughPersonsException39);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException36);
        java.lang.String str43 = notEnoughPersonsException14.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException45 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException47 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException49 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str50 = notEnoughPersonsException49.toString();
        notEnoughPersonsException47.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        notEnoughPersonsException45.addSuppressed((java.lang.Throwable) notEnoughPersonsException49);
        java.lang.Throwable[] throwable_array53 = notEnoughPersonsException45.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException55 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException45.addSuppressed((java.lang.Throwable) notEnoughPersonsException55);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException58 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException60 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException62 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str63 = notEnoughPersonsException62.toString();
        notEnoughPersonsException60.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        notEnoughPersonsException58.addSuppressed((java.lang.Throwable) notEnoughPersonsException62);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException67 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException69 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException71 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str72 = notEnoughPersonsException71.toString();
        notEnoughPersonsException69.addSuppressed((java.lang.Throwable) notEnoughPersonsException71);
        notEnoughPersonsException67.addSuppressed((java.lang.Throwable) notEnoughPersonsException71);
        java.lang.Throwable[] throwable_array75 = notEnoughPersonsException67.getSuppressed();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException77 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        notEnoughPersonsException67.addSuppressed((java.lang.Throwable) notEnoughPersonsException77);
        notEnoughPersonsException58.addSuppressed((java.lang.Throwable) notEnoughPersonsException77);
        notEnoughPersonsException45.addSuppressed((java.lang.Throwable) notEnoughPersonsException77);
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException82 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.Throwable[] throwable_array83 = notEnoughPersonsException82.getSuppressed();
        notEnoughPersonsException77.addSuppressed((java.lang.Throwable) notEnoughPersonsException82);
        java.lang.String str85 = notEnoughPersonsException77.toString();
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException87 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException notEnoughPersonsException89 = new io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException("hi!");
        java.lang.String str90 = notEnoughPersonsException89.toString();
        notEnoughPersonsException87.addSuppressed((java.lang.Throwable) notEnoughPersonsException89);
        notEnoughPersonsException77.addSuppressed((java.lang.Throwable) notEnoughPersonsException87);
        notEnoughPersonsException14.addSuppressed((java.lang.Throwable) notEnoughPersonsException77);
        java.lang.String str94 = notEnoughPersonsException77.toString();
        java.lang.Throwable[] throwable_array95 = notEnoughPersonsException77.getSuppressed();
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str6.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array9);
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str19.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array22);
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str23.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str31 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str31.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array34);
        org.junit.Assert.assertTrue("'" + str40 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str40.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str43 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str43.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str50 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str50.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array53);
        org.junit.Assert.assertTrue("'" + str63 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str63.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str72 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str72.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array75);
        org.junit.Assert.assertNotNull(throwable_array83);
        org.junit.Assert.assertTrue("'" + str85 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str85.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str90 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str90.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertTrue("'" + str94 + "' != '" + "io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!" + "'", str94.equals("io.github.agentsoz.syntheticpop.synthesis.NotEnoughPersonsException: hi!"));
        org.junit.Assert.assertNotNull(throwable_array95);
    }
}

