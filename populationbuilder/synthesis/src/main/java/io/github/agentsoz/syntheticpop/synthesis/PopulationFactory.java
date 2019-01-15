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

import io.github.agentsoz.syntheticpop.synthesis.models.*;
import io.github.agentsoz.syntheticpop.util.GlobalConstants;
import io.github.agentsoz.syntheticpop.util.Log;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wniroshan 15 Mar 2018
 */
public class PopulationFactory {
    private final List<HhRecord> hhRecs;
    private final List<IndRecord> indRecs;
    private final Random random;
    private final double nonPrimaryCwcProbability;
    private final ExtrasHandler extrasHandler;
    private final FamilyFactory familyFactory;
    private final HouseholdFactory householdFactory;
    private List<Person> children;
    private List<Person> marriedMales;
    private List<Person> marriedFemales;
    private List<Person> relatives;
    private List<Person> lonePersons;
    private List<Person> groupHhPersons;
    private List<Person> loneParents;
    private List<Family> basicCouples;
    private List<Family> basicOneParentFamilies;
    private List<Family> basicPrimaryCoupleWithChildFamilies;
    private List<Family> basicPrimaryOtherFamilies;

    PopulationFactory(List<HhRecord> hhRecords, List<IndRecord> indRecords, double nonPrimaryCoupleWithChildProbability, Random random) {
        hhRecs = hhRecords;
        indRecs = indRecords;
        this.random = random;
        this.nonPrimaryCwcProbability = nonPrimaryCoupleWithChildProbability;

        extrasHandler = new ExtrasHandler(indRecs, random);
        familyFactory = new FamilyFactory(random, extrasHandler);
        householdFactory = new HouseholdFactory(hhRecs, random, extrasHandler);
    }

    List<Person> makeAllPersons() {
        formAllPersons();
        List<Person> persons = new ArrayList<>();
        persons.addAll(lonePersons);
        persons.addAll(groupHhPersons);
        persons.addAll(marriedMales);
        persons.addAll(marriedFemales);
        persons.addAll(loneParents);
        persons.addAll(relatives);
        persons.addAll(children);
        return persons;
    }

    List<Household> makePopulation() {

        formAllPersons();
        formAllKnownFamilies();
        List<Household> allHouseholds = null;
        try {
            allHouseholds = new ArrayList<>(formHouseholds());
            for (Household h : allHouseholds) {
                if (!h.validate()) {
                    Log.error("Bad state in household: " + h);
                }
                for (Family f : h.getFamilies()) {
                    if (!f.validate()) {
                        Log.error("Bad state in family: " + f + " in household: " + h);
                    }
                }
            }
        } catch (Exception e) {
            Log.error("Error detected", e);
            Log.debug("Remaining Relatives: " + relatives.size());
            Log.debug("Remaining Children: " + children.size());
            Log.debug("Remaining Male Married: " + marriedMales.size());
            Log.debug("Remaining Female Married: " + marriedFemales.size());
            Log.debug("Remaining Lone Parents: " + loneParents.size());
            Log.debug("Remaining Lone Persons: " + lonePersons.size());
            Log.debug("Remaining GroupHousehold persons: " + groupHhPersons.size());
            Log.debug("Remaining Basic Couples: " + basicCouples.size());
            Log.debug("Remaining Basic One Parent units: " + basicOneParentFamilies.size());
            Log.debug("Remaining Extras: " + extrasHandler.remainingExtras());
            Log.errorAndExit("Household construction failed", GlobalConstants.ExitCode.PROGERROR);
        }
        return allHouseholds;
    }

    private void formAllPersons() {

        List<Person> married = PersonsFactory.makeAllPersonsByRelationshipType(indRecs, RelationshipStatus.MARRIED);
        marriedMales = married.stream().filter(p -> p.getSex() == Sex.Male).collect(Collectors.toList());
        marriedFemales = married.stream()
                                .filter(p -> p.getSex() == Sex.Female)
                                .collect(Collectors.toList());

        relatives = PersonsFactory.makeAllPersonsByRelationshipType(indRecs, RelationshipStatus.RELATIVE);
        loneParents = PersonsFactory.makeAllPersonsByRelationshipType(indRecs,
                                                                      RelationshipStatus.LONE_PARENT);
        children = PersonsFactory.makeAllPersonsByRelationshipType(indRecs,
                                                                   RelationshipStatus.U15_CHILD,
                                                                   RelationshipStatus.STUDENT,
                                                                   RelationshipStatus.O15_CHILD);
        lonePersons = PersonsFactory.makeAllPersonsByRelationshipType(indRecs, RelationshipStatus.LONE_PERSON);
        groupHhPersons = PersonsFactory.makeAllPersonsByRelationshipType(indRecs, RelationshipStatus.GROUP_HOUSEHOLD);
        extrasHandler.formExtras(hhRecs);
        Log.info("Formed all person instances");
        Log.debug("Remaining Married males: " + marriedMales.size());
        Log.debug("Remaining Married females: " + marriedFemales.size());
        Log.debug("Remaining Relatives: " + relatives.size());
        Log.debug("Remaining Children: " + children.size());
        Log.debug("Remaining Lone parents: " + loneParents.size());
        Log.debug("Remaining Lone persons: " + lonePersons.size());
        Log.debug("Remaining Group household persons: " + groupHhPersons.size());
        Log.debug("Remaining Extras: " + extrasHandler.remainingExtras());
    }

    private void formAllKnownFamilies() {

        Log.info("Forming Basic One Parent families: " + loneParents.size());
        basicOneParentFamilies = familyFactory.formOneParentBasicUnits(loneParents.size(), loneParents, children);
        Log.debug("Formed Basic One Parent families: " + basicOneParentFamilies.size());
        Log.debug("Remaining Lone parent persons: " + loneParents.size());
        Log.debug("Remaining Children: " + children.size());

        Log.info("Forming Basic Couple families: " + Math.min(marriedMales.size(), marriedFemales.size()));
        basicCouples = familyFactory.formCoupleFamilyBasicUnits(Math.min(marriedMales.size(), marriedFemales.size()),
                                                                marriedMales,
                                                                marriedFemales);
        Log.debug("Formed Basic couples: " + basicCouples.size());
        Log.debug("Remaining Married males: " + marriedMales.size());
        Log.debug("Remaining Married females: " + marriedFemales.size());

        //Form basic family structures and removes couples from married males and females list
        List<HhRecord> coupleWChildRecs = DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                                     FamilyHouseholdType.F1_COUPLE_WITH_CHILDREN,
                                                                                     FamilyHouseholdType.F2_COUPLE_WITH_CHILDREN,
                                                                                     FamilyHouseholdType.F3_COUPLE_WITH_CHILDREN);
        int fCount = coupleWChildRecs.stream().mapToInt(r -> r.HH_COUNT).sum();
        Log.info("Forming Basic Couple with Children families: " + fCount);
        basicPrimaryCoupleWithChildFamilies = familyFactory.formCoupleWithChildFamilyBasicUnits(fCount, basicCouples, children);
        Log.debug("Formed Basic Couple with Children families: " + basicPrimaryCoupleWithChildFamilies.size());
        Log.debug("Remaining Basic couples: " + basicCouples.size());
        Log.debug("Remaining Children: " + children.size());

        // Forms Other Family basic family structures
        List<HhRecord> otherFamiliesRecs = DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                                      FamilyHouseholdType.F1_OTHER_FAMILY,
                                                                                      FamilyHouseholdType.F2_OTHER_FAMILY,
                                                                                      FamilyHouseholdType.F3_OTHER_FAMILY);
        fCount = otherFamiliesRecs.stream().mapToInt(r -> r.HH_COUNT).sum();
        Log.info("Forming Basic Other families: " + fCount);
        basicPrimaryOtherFamilies = familyFactory.formOtherFamilyBasicUnits(fCount, relatives);
        Log.debug("Remaining Relatives: " + relatives.size());
        Log.debug("Remaining Basic Other families: " + basicPrimaryOtherFamilies.size());

    }

    private List<Household> formHouseholds() {

        List<Household> allHouseholds = new ArrayList<>();
        allHouseholds.addAll(householdFactory.formLonePersonHouseholds(lonePersons));
        allHouseholds.addAll(householdFactory.formGroupHouseholds(groupHhPersons));
        List<Household> familyHhs = householdFactory.formAllFamilyHouseholdsWithPrimaryFamilies(basicCouples,
                                                                                                basicPrimaryCoupleWithChildFamilies,
                                                                                                basicOneParentFamilies,
                                                                                                basicPrimaryOtherFamilies);
        //Fill 1 family couple only households with relatives
        householdFactory.completeHouseholdsWithRelatives(familyHhs, relatives, FamilyHouseholdType.F1_COUPLE_ONLY);
        //Fill 1 family other family households with relatives
        householdFactory.completeHouseholdsWithRelatives(familyHhs, relatives, FamilyHouseholdType.F1_OTHER_FAMILY);

        householdFactory.addNonPrimaryFamiliesToHouseholds(familyHhs,
                                                           basicCouples,
                                                           basicOneParentFamilies,
                                                           children,
                                                           relatives,
                                                           marriedMales,
                                                           marriedFemales,
                                                           loneParents,
                                                           nonPrimaryCwcProbability,
                                                           getRelationshipDistInPrimaryFamilies(hhRecs),
                                                           familyFactory);
        familyHhs.forEach(h -> {
            if (h.getExpectedFamilyCount() != h.getCurrentFamilyCount()) {
                throw new IllegalStateException("Family count wrong: " + h.getExpectedSize() + " person:" + h
                        .getFamilyHouseholdType() + " has only " + h
                        .getCurrentFamilyCount() + " families");
            }
        });

        householdFactory.completeHouseholdsWithChildren(familyHhs, children);
        //TODO: Record children that are converted to extras
        householdFactory.addExtrasAsChildrenAndRelatives(familyHhs, indRecs, marriedMales, marriedFemales, loneParents, children);
        householdFactory.completeHouseholdsWithRelatives(familyHhs, relatives, null);

        allHouseholds.addAll(familyHhs);

        return allHouseholds;
    }


    private Map<FamilyType, Integer> getRelationshipDistInPrimaryFamilies(List<HhRecord> hhRecords) {

        Map<FamilyType, Integer> dist = new HashMap<>(4, 1);
        for (HhRecord hhRec : hhRecords) {

            //The number of couple, lone parent and other relationships is similar to the number of primary families (i.e. households)
            switch (hhRec.getPrimaryFamilyType()) {
                case COUPLE_ONLY:
                    dist.compute(FamilyType.COUPLE_ONLY, (k, v) -> (v == null) ? hhRec.HH_COUNT : v + hhRec.HH_COUNT);
                    break;
                case COUPLE_WITH_CHILDREN:
                    dist.compute(FamilyType.COUPLE_ONLY, (k, v) -> (v == null) ? hhRec.HH_COUNT : v + hhRec.HH_COUNT);
                    break;
                case ONE_PARENT:
                    dist.compute(FamilyType.ONE_PARENT, (k, v) -> (v == null) ? hhRec.HH_COUNT : v + hhRec.HH_COUNT);
                    break;
                case OTHER_FAMILY:
                    dist.compute(FamilyType.OTHER_FAMILY, (k, v) -> (v == null) ? hhRec.HH_COUNT : v + hhRec.HH_COUNT);
                    break;
                case LONE_PERSON:
                    break;
                case GROUP_HOUSEHOLD:
                    break;
                default:
                    throw new IllegalStateException("Unrecognised family type: " + hhRec.getPrimaryFamilyType());
            }
        }
        return dist;
    }
}
