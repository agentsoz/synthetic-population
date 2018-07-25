package io.github.agentsoz.syntheticpop.synthesis;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2018 by its authors. See AUTHORS file.
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

import io.github.agentsoz.syntheticpop.synthesis.models.*;
import io.github.agentsoz.syntheticpop.util.Log;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author wniroshan 03 Apr 2018
 */
public class HouseholdFactoryTest {

    private List<IndRecord> indRecords = null;
    private List<HhRecord> hhRecords = null;

    private List<Person> children = null;
    private List<Person> marriedMales = null;
    private List<Person> marriedFemales = null;
    private List<Person> loneParents = null;
    private List<Person> relatives = null;
    private List<Family> couples = null;
    private List<Family> coupleWithChildren = null;
    private List<Family> oneParentFamilies = null;
    private List<Family> otherFamilies = null;
    private List<Household> households = null;
    private HouseholdFactory hf = null;
    private FamilyFactory ff = null;

    @Before
    public void setupHouseholdFactory() {
        Log.createLogger("SynthesisTest", "PopulationSynthesisTest.log");
    }

    void setup() {

        IndRecord childRec = new IndRecord(RelationshipStatus.U15_CHILD, Sex.Male, AgeRange.A0_14, 6, "TESTSA2");
        IndRecord maleRec = new IndRecord(RelationshipStatus.MARRIED, Sex.Male, AgeRange.A40_54, 2, "TESTSA2");
        IndRecord femaleRec = new IndRecord(RelationshipStatus.MARRIED, Sex.Female, AgeRange.A40_54, 2, "TESTSA2");
        IndRecord loneParentRec = new IndRecord(RelationshipStatus.LONE_PARENT, Sex.Female, AgeRange.A25_39, 4, "TESTSA2");
        IndRecord relativesRec = new IndRecord(RelationshipStatus.RELATIVE, Sex.Male, AgeRange.A70_84, 5, "TESTSA2");
        indRecords = new ArrayList<>(Arrays.asList(childRec, maleRec, femaleRec, loneParentRec, relativesRec));

        HhRecord cplWChildRec = new HhRecord(5, 2, FamilyType.COUPLE_WITH_CHILDREN, 2, "TESTSA2");
        HhRecord oneParentRec = new HhRecord(3, 1, FamilyType.ONE_PARENT, 1, "TESTSA2");
        HhRecord otherFamilyRec = new HhRecord(3, 1, FamilyType.OTHER_FAMILY, 2, "TESTSA2");
        hhRecords = new ArrayList<>(Arrays.asList(cplWChildRec, otherFamilyRec, oneParentRec));
        // We have an extra lone parent and a child. And 2 relatives missing.

        Random rand = new Random(2342);
        ExtrasHandler xh = new ExtrasHandler(indRecords, rand);

        children = PersonsFactory.makeAllPersonsByRelationshipType(indRecords, RelationshipStatus.U15_CHILD);
        List<Person> married = PersonsFactory.makeAllPersonsByRelationshipType(indRecords, RelationshipStatus.MARRIED);
        marriedMales = married.stream().filter(p -> p.getSex() == Sex.Male).collect(Collectors.toList());
        marriedFemales = married.stream().filter(p -> p.getSex() == Sex.Female).collect(Collectors.toList());
        loneParents = PersonsFactory.makeAllPersonsByRelationshipType(indRecords, RelationshipStatus.LONE_PARENT);
        relatives = PersonsFactory.makeAllPersonsByRelationshipType(indRecords, RelationshipStatus.RELATIVE);


        ff = new FamilyFactory(rand, xh);
        couples = ff.formCoupleFamilyBasicUnits(marriedMales.size(), marriedMales, marriedFemales);
        coupleWithChildren = ff.formCoupleWithChildFamilyBasicUnits(cplWChildRec.HH_COUNT, couples, children);
        oneParentFamilies = ff.formOneParentBasicUnits(loneParents.size(), loneParents, children);
        otherFamilies = ff.formOtherFamilyBasicUnits(otherFamilyRec.HH_COUNT, relatives);

        hf = new HouseholdFactory(hhRecords, rand, xh);
        households = hf.formAllFamilyHouseholdsWithPrimaryFamilies(couples,
                                                                   coupleWithChildren,
                                                                   oneParentFamilies,
                                                                   otherFamilies);
    }

    @Test
    public void testFormAllFamilyHouseholdsWithPrimaryFamilies() {
        setup();

        Assert.assertEquals("Number of Couple With no Children family households",
                            households.stream().filter(h -> h.getPrimaryFamilyType() == FamilyType.COUPLE_ONLY).count(),
                            hhRecords.stream()
                                     .filter(r -> r.getPrimaryFamilyType() == FamilyType.COUPLE_ONLY)
                                     .mapToInt(r -> r.HH_COUNT)
                                     .sum());
        Assert.assertEquals("Number of Couple With Child primary family households",
                            households.stream().filter(h -> h.getPrimaryFamilyType() == FamilyType.COUPLE_WITH_CHILDREN).count(),
                            hhRecords.stream()
                                     .filter(r -> r.getPrimaryFamilyType() == FamilyType.COUPLE_WITH_CHILDREN)
                                     .mapToInt(r -> r.HH_COUNT)
                                     .sum());
        Assert.assertEquals("Number of Other family households",
                            households.stream().filter(h -> h.getPrimaryFamilyType() == FamilyType.OTHER_FAMILY).count(),
                            hhRecords.stream()
                                     .filter(r -> r.getPrimaryFamilyType() == FamilyType.OTHER_FAMILY)
                                     .mapToInt(r -> r.HH_COUNT)
                                     .sum());
        Assert.assertEquals("Number of One Parent family households",
                            households.stream().filter(h -> h.getPrimaryFamilyType() == FamilyType.ONE_PARENT).count(),
                            hhRecords.stream()
                                     .filter(r -> r.getPrimaryFamilyType() == FamilyType.ONE_PARENT)
                                     .mapToInt(r -> r.HH_COUNT)
                                     .sum());
    }

    @Test
    public void testAssignOneParentUnitsAsNonPrimaryFamilies() throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        setup();
        Assert.assertTrue("One parent families are not empty", !oneParentFamilies.isEmpty());
        int startOneParentFamiliesCount = oneParentFamilies.size();
        int startNonPrimarySlots = households.parallelStream().mapToInt(h -> h.getExpectedFamilyCount() - h.getCurrentFamilyCount()).sum();
        Method method = HouseholdFactory.class.getDeclaredMethod("assignOneParentUnitsAsNonPrimaryFamilies", List.class, List.class);
        method.setAccessible(true);
        method.invoke(hf, households, oneParentFamilies);

        Assert.assertTrue("One parent families were assigned as non-primary families",
                          startOneParentFamiliesCount > oneParentFamilies.size());
        int usedOneParentFamilies = startOneParentFamiliesCount - oneParentFamilies.size();
        int endNonPrimarySlots = households.parallelStream().mapToInt(h -> h.getExpectedFamilyCount() - h.getCurrentFamilyCount()).sum();
        Assert.assertEquals("Only One Parent families are used as non-primary families",
                            (startNonPrimarySlots - endNonPrimarySlots),
                            usedOneParentFamilies);
    }

    @Test
    public void testAddNonPrimaryFamiliesToHouseholds() {

        setup();
        Assert.assertTrue("One parent families are not empty", !oneParentFamilies.isEmpty());
        int childCount = children.size();
        int oneParentFamilyCount = oneParentFamilies.size();
        int loneParentsCount = loneParents.size();
        long expectedUnusedOneParentFamilies = oneParentFamilyCount - (households.stream()
                                                                                 .filter(h -> h.getPrimaryFamilyType() == FamilyType
                                                                                         .COUPLE_WITH_CHILDREN)
                                                                                 .count());
        hf.addNonPrimaryFamiliesToHouseholds(households,
                                             couples,
                                             oneParentFamilies,
                                             children,
                                             relatives,
                                             marriedMales,
                                             marriedFemales,
                                             loneParents,
                                             0.2,
                                             null,
                                             ff);
        Assert.assertTrue("The two Other Family households are incomplete",
                          households.parallelStream()
                                    .filter(h -> h.getPrimaryFamilyType() == FamilyType.OTHER_FAMILY)
                                    .allMatch(h -> h.getExpectedSize() > h.getCurrentSize()));
        Assert.assertTrue("The One Parent primary family household is incomplete",
                          households.parallelStream()
                                    .filter(h -> h.getPrimaryFamilyType() == FamilyType.ONE_PARENT)
                                    .allMatch(h -> h.getExpectedSize() > h.getCurrentSize()));
        Assert.assertTrue("Only One parent families are added as non-primary families in this scenario",
                          households.stream()
                                    .filter(h -> h.getCurrentFamilyCount() > 1)
                                    .map(h -> h.getFamilies().get(1))
                                    .allMatch(f -> f.getType() == FamilyType.ONE_PARENT));
        Assert.assertTrue("One parent families are empty", oneParentFamilies.isEmpty());
        Assert.assertEquals("Adding children in one parent families back to children list",
                            childCount + expectedUnusedOneParentFamilies,
                            children.size());
        Assert.assertEquals("Adding lone parents in one parent families back to lone parents list",
                            loneParentsCount + expectedUnusedOneParentFamilies,
                            loneParents.size());

    }

    @Test
    public void testCompleteHouseholdsWithChildren() {

        setup();
        hf.addNonPrimaryFamiliesToHouseholds(households,
                                             couples,
                                             oneParentFamilies,
                                             children,
                                             relatives,
                                             marriedMales,
                                             marriedFemales,
                                             loneParents,
                                             0.2,
                                             null,
                                             ff);
        int startingChildCount = children.size();
        int startingLoneParents = loneParents.size();
        hf.completeHouseholdsWithChildren(households, children);
        Assert.assertEquals("One children from children list was assigned to One Parent primary family household",
                            startingChildCount - 1,
                            children.size());
        Assert.assertEquals("Lone parents should not be assigned to families", startingLoneParents, loneParents.size());

    }

    @Test
    public void testAddExtrasAsChildrenAndRelatives() {
        setup();
        hf.addNonPrimaryFamiliesToHouseholds(households,
                                             couples,
                                             oneParentFamilies,
                                             children,
                                             relatives,
                                             marriedMales,
                                             marriedFemales,
                                             loneParents,
                                             0.2,
                                             null,
                                             ff);
        hf.completeHouseholdsWithChildren(households, children);
        Assert.assertTrue("The two Other Family households are incomplete",
                          households.parallelStream()
                                    .filter(h -> h.getExpectedSize() > h.getCurrentSize())
                                    .allMatch(h -> h.getPrimaryFamilyType() == FamilyType.OTHER_FAMILY));
        hf.addExtrasAsChildrenAndRelatives(households, indRecords, marriedMales, marriedFemales, loneParents, children);

        Assert.assertTrue("The unassigned children were converted to extras so children list is empty", children.isEmpty());
        Assert.assertTrue("The unassigned lone parents were converted to extras so lone parents list is empty", loneParents.isEmpty());
    }

    @Test
    public  void testcompleteHouseholdsWithRelatives(){
        setup();
        hf.addNonPrimaryFamiliesToHouseholds(households,
                                             couples,
                                             oneParentFamilies,
                                             children,
                                             relatives,
                                             marriedMales,
                                             marriedFemales,
                                             loneParents,
                                             0.2,
                                             null,
                                             ff);
        hf.completeHouseholdsWithChildren(households, children);
        hf.addExtrasAsChildrenAndRelatives(households, indRecords, marriedMales, marriedFemales, loneParents, children);

        Assert.assertTrue("There are relatives remaining", !relatives.isEmpty());
        hf.completeHouseholdsWithRelatives(households, relatives, null);
        Assert.assertTrue("All households are complete", households.parallelStream().allMatch(h -> h.validate()));
        Assert.assertTrue("All Relatives were used", relatives.isEmpty());
    }
}
