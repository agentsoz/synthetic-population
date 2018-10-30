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

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wniroshan 13 Mar 2018
 */
public class HouseholdFactory {

    private final Random random;
    private final ExtrasHandler extrasHandler;
    private final List<HhRecord> hhRecs;
    private final Comparator<Family> youngParentAgeComparator = new AgeRange.YoungestParentAgeComparator();

    HouseholdFactory(List<HhRecord> hhRecords, Random random, ExtrasHandler extrasHandler) {
        this.hhRecs = hhRecords;
        this.random = random;
        this.extrasHandler = extrasHandler;
    }

    /**
     * Creates all lone person households and add them to the global list of households.
     *
     * @param lonePersons Lone Person instances
     * @return The list of lone person households
     */
    List<Household> formLonePersonHouseholds(List<Person> lonePersons) {
        List<HhRecord> lnPersonHhs = DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                                FamilyHouseholdType
                                                                                        .LONE_PERSON);
        List<Household> hhList = new ArrayList<>();
        if (!lnPersonHhs.isEmpty()) {
            int hhCount = lnPersonHhs.get(0).HH_COUNT;// Only 1 member households have lone persons
            int diff = hhCount - lonePersons.size();

            for (int i = 0; i < lnPersonHhs.get(0).HH_COUNT; i++) {
                Family f = new Family();
                if (lonePersons.isEmpty()) {
                    throw new NotEnoughPersonsException(lnPersonHhs.get(0).FAMILY_HOUSEHOLD_TYPE + ": Not enough lone " +
                                                                "persons");
                }
                f.addMember(lonePersons.remove(0));
                f.setType(FamilyType.LONE_PERSON);
                Household h = new Household(1, FamilyHouseholdType.LONE_PERSON, lnPersonHhs.get(0).SA);
                h.addFamily(f);
                hhList.add(h);
            }

            Log.info("Lone person households: Households formed: " + hhList.size());
            if (diff > 0) {
                Log.warn("Lone person households: Persons discarded: " + 0);
                Log.warn("Lone person households: Unformed 1 member households: " + diff);
            } else if (diff < 0) {
                Log.warn("Lone person households: Persons discarded: " + ((-1) * diff));
                Log.warn("Lone person households: Unformed 1 member households: " + 0);
            } else {
                Log.info("Lone person households: All required households created");
            }
        }
        return hhList;
    }

    /**
     * Creates all group households and add them to the global list of households.
     *
     * @param groupHhPersons group household persons
     * @return The list of group households
     */
    List<Household> formGroupHouseholds(List<Person> groupHhPersons) {
        List<HhRecord> grpHhRecs = DataReader.getHhRecordsByPrimaryFamilyType(hhRecs, FamilyHouseholdType.GROUP_HOUSEHOLD);

        List<Household> hhList = new ArrayList<>();
        int totalGroupHhs = 0;
        for (HhRecord hhRec : grpHhRecs) {

            int hhCount = hhRec.HH_COUNT;
            totalGroupHhs += hhRec.HH_COUNT;
            int hhSize = hhRec.NUM_OF_PERSONS_PER_HH;

            for (int i = 0; i < hhCount; i++) {
                Family f = new Family();
                if (hhSize > groupHhPersons.size()) {
                    throw new NotEnoughPersonsException(hhRec.FAMILY_HOUSEHOLD_TYPE + ": Not enough group household persons");
                }
                f.addMembers(groupHhPersons.subList(0, hhSize));
                f.setType(FamilyType.GROUP_HOUSEHOLD);
                groupHhPersons.subList(0, hhSize).clear();

                Household h = new Household(hhRec.NUM_OF_PERSONS_PER_HH, FamilyHouseholdType.GROUP_HOUSEHOLD, hhRec.SA);
                h.addFamily(f);

                hhList.add(h);
            }

        }
        Log.info("Group households: Households formed: " + hhList.size());
        Log.info("Group households: All Households created");
        if (!groupHhPersons.isEmpty()) {
            Log.warn("Group households: Discarded group household persons: " + groupHhPersons.size());
        }
        return hhList;
    }


    /**
     * Constructs all family households (except Lone person and group households) and adds a suitable basic primary family unit. Input lists
     * are altered by this method.
     *
     * @param coupleOnlyBasic         couple only basic family units
     * @param coupleWithChildrenBasic couple with children basic family units
     * @param oneParentBasicFamilies  one parent basic family units
     * @param otherFamilyBasic        other family basic family unis
     * @return All households with their primary family
     */
    List<Household> formAllFamilyHouseholdsWithPrimaryFamilies(List<Family> coupleOnlyBasic,
                                                               List<Family> coupleWithChildrenBasic,
                                                               List<Family> oneParentBasicFamilies,
                                                               List<Family> otherFamilyBasic) {

        Log.info("Populating all family households with primary families");

        Collections.shuffle(coupleOnlyBasic, random);
        Collections.shuffle(otherFamilyBasic, random);
        Collections.shuffle(coupleWithChildrenBasic, random);
        Collections.shuffle(oneParentBasicFamilies, random);

        hhRecs.sort(Comparator.comparing(hhRec -> hhRec.NUM_OF_PERSONS_PER_HH));

        List<Household> basicHouseholds = new ArrayList<>();
        int cpl = 0, cwc = 0, other = 0, one = 0; //bookkeeping
        for (HhRecord hhRec : hhRecs) {
            List<Family> primaryFamilyUnitsList;
            switch (hhRec.getPrimaryFamilyType()) {
                case COUPLE_ONLY:
                    cpl += hhRec.HH_COUNT;
                    primaryFamilyUnitsList = coupleOnlyBasic;
                    break;
                case COUPLE_WITH_CHILDREN:
                    cwc += hhRec.HH_COUNT;
                    primaryFamilyUnitsList = coupleWithChildrenBasic;
                    break;
                case ONE_PARENT:
                    one += hhRec.HH_COUNT;
                    primaryFamilyUnitsList = oneParentBasicFamilies;
                    break;
                case OTHER_FAMILY:
                    other += hhRec.HH_COUNT;
                    primaryFamilyUnitsList = otherFamilyBasic;
                    break;
                default:
                    continue;
            }

            if (hhRec.HH_COUNT > 0) {
                for (int i = 0; i < hhRec.HH_COUNT; i++) {
                    if (primaryFamilyUnitsList.isEmpty()) {
                        throw new NotEnoughPersonsException(hhRec.FAMILY_HOUSEHOLD_TYPE + ": Not enough basic family units");
                    }
                    Household household = new Household(hhRec.NUM_OF_PERSONS_PER_HH,
                                                        hhRec.FAMILY_HOUSEHOLD_TYPE,
                                                        hhRec.SA);
                    household.addFamily(primaryFamilyUnitsList.remove(0));
                    household.setPrimaryFamilyType(hhRec.getPrimaryFamilyType());
                    basicHouseholds.add(household);
                }

            }
        }

        Log.info("All households populated with primary families");
        Log.debug("Primary family " + FamilyType.COUPLE_ONLY + " formed households: " + cpl);
        Log.debug("Primary family " + FamilyType.COUPLE_WITH_CHILDREN + " formed households: " + cwc);
        Log.debug("Primary family " + FamilyType.ONE_PARENT + " formed households: " + one);
        Log.debug("Primary family " + FamilyType.OTHER_FAMILY + " formed households: " + other);
        Log.debug("Remaining Couples basic: " + coupleOnlyBasic.size());
        Log.debug("Remaining Couple with children basic: " + coupleWithChildrenBasic.size());
        Log.debug("Remaining Other family basic: " + otherFamilyBasic.size());
        Log.debug("Remaining One parent basic: " + oneParentBasicFamilies.size());
        return basicHouseholds;
    }

    /**
     * Adds second and third families to multi family households.
     *
     * @param households                        The list of households
     * @param unusedCouples                     remaining basic couple families
     * @param unusedBasicOneParentFamilies      Remaining basic one parent families
     * @param children                          remaining children
     * @param relatives                         remaining relatives
     * @param marriedMales                      remaining married males
     * @param marriedFemales                    remaining married females
     * @param loneParents                       remaining lone parents
     * @param nonPrimaryCwcProb                 The proportion of household that have Couple With child secondary and tertiary families
     *                                          out of all eligible households
     * @param relationshipDistInPrimaryFamilies The probability distribution of couples, one parent families and other families among
     *                                          primary families
     * @param familyFactory                     FamilyFactory instance.
     */
    void addNonPrimaryFamiliesToHouseholds(List<Household> households,
                                           List<Family> unusedCouples,
                                           List<Family> unusedBasicOneParentFamilies,
                                           List<Person> children,
                                           List<Person> relatives,
                                           List<Person> marriedMales,
                                           List<Person> marriedFemales,
                                           List<Person> loneParents,
                                           double nonPrimaryCwcProb,
                                           Map<FamilyType, Integer> relationshipDistInPrimaryFamilies,
                                           FamilyFactory familyFactory) {
        Log.info("Assigning known Basic One Parent families to suitable households");
        assignOneParentUnitsAsNonPrimaryFamilies(households, unusedBasicOneParentFamilies);
        Log.debug("Non-primary known: Basic One Parent families: Unassigned families: " + unusedBasicOneParentFamilies.size());
        if (!unusedBasicOneParentFamilies.isEmpty()) {
            Log.debug("Non-primary known: Basic One Parent families: Remaining Children : " + children.size());
            Log.debug("Non-primary known: Basic One Parent families: Remaining Lone Parents : " + loneParents.size());
            Log.debug("Non-primary known: Basic One Parent families: Adding unassigned persons to persons lists");
            Supplier<Stream<Person>> memSup = () -> unusedBasicOneParentFamilies.stream()
                                                                                .map(Family::getMembers)
                                                                                .flatMap(List::stream);

            List<Person> extraLoneParents = memSup.get().filter(p -> p.getRelationshipStatus() == RelationshipStatus.LONE_PARENT).collect(
                    Collectors.toList());
            List<Person> extraChildren = memSup.get()
                                               .filter(p -> p.getRelationshipStatus() != RelationshipStatus.LONE_PARENT)
                                               .collect(Collectors.toList());

            extraLoneParents.forEach(Person::clearFamilyID);
            loneParents.addAll(extraLoneParents);

            extraChildren.forEach(Person::clearFamilyID);
            children.addAll(extraChildren);
            unusedBasicOneParentFamilies.clear();

            Log.debug("Non-primary known: Basic One Parent families: Remaining Children : " + children.size());
            Log.debug("Non-primary known: Basic One Parent families: Remaining Lone Parents : " + loneParents.size());

        }

        Log.info("Assigning known Basic Couple families to suitable households");

        assignCouplesAsNonPrimaryFamilies(households, unusedCouples, children, nonPrimaryCwcProb, familyFactory);
        Log.debug("Non-primary known: Basic Couple families: Unassigned families: " + unusedCouples.size());

        if (!unusedCouples.isEmpty()) {
            Log.debug("Non-primary known: Basic Couple families: Remaining Male Married : " + marriedMales.size());
            Log.debug("Non-primary known: Basic Couple families: Remaining Female Married : " + marriedFemales.size());
            Log.debug("Non-primary known: Basic Couple families: Adding unassigned persons to persons lists");

            Supplier<Stream<Person>> familyMemberSupplier = () -> unusedCouples.stream().map(Family::getMembers).flatMap(List::stream);
            familyMemberSupplier.get().forEach(Person::clearFamilyID);
            marriedMales.addAll(familyMemberSupplier.get().filter(p -> p.getSex() == Sex.Male).collect(Collectors.toList()));
            marriedFemales.addAll(familyMemberSupplier.get().filter(p -> p.getSex() == Sex.Female).collect(Collectors.toList()));
            unusedCouples.clear();

            Log.debug("Non-primary known: Basic Couple families: Remaining Male Married : " + marriedMales.size());
            Log.debug("Non-primary known: Basic Couple families: Remaining Female Married : " + marriedFemales.size());
        }


        assignUnknownNonPrimaryFamilies(households,
                                        relationshipDistInPrimaryFamilies,
                                        nonPrimaryCwcProb,
                                        relatives,
                                        children,
                                        marriedMales,
                                        marriedFemales,
                                        loneParents,
                                        familyFactory);


    }

    private void assignUnknownNonPrimaryFamilies(List<Household> households,
                                                 Map<FamilyType, Integer> relTypeDist,
                                                 double coupleWithChildProb,
                                                 List<Person> relatives,
                                                 List<Person> children,
                                                 List<Person> marriedMales,
                                                 List<Person> marriedFemales,
                                                 List<Person> loneParents,
                                                 FamilyFactory familyFactory) {

        Log.info("Assigning unknown non-primary families to households");
        Log.debug("Non-primary unknown: Remaining Married males: " + marriedMales.size());
        Log.debug("Non-primary unknown: Remaining Married females: " + marriedFemales.size());
        Log.debug("Non-primary unknown: Remaining Relatives: " + relatives.size());
        Log.debug("Non-primary unknown: Remaining Children: " + children.size());
        Log.debug("Non-primary unknown: Remaining Lone Parents: " + loneParents.size());
        Log.debug("Non-primary unknown: Remaining Extras: " + extrasHandler.remainingExtras());
        Log.debug("Non-primary unknown: Families needed: " + households.stream()
                                                                       .mapToInt(h -> h.getExpectedFamilyCount() - h
                                                                               .getCurrentFamilyCount())
                                                                       .sum());

        Map<FamilyType, Integer> currentDist = new HashMap<>(4, 1);
        households.forEach(h -> {
            h.getFamilies().forEach(f -> {
                currentDist.compute((f.getType() == FamilyType.COUPLE_WITH_CHILDREN) ? FamilyType.COUPLE_ONLY : f.getType(),
                                    (k, v) -> (v == null) ? 1 : (v + 1));
            });
        });


        Function<FamilyType, FamilyType> probabilisticallySelectNewFamilyType = (FamilyType primaryFT) -> {

            /*First calculate cumulative probability of dist of family types that we can select.
            The probabilistically select one.*/

            Map<FamilyType, Integer> cumRelationsDist = new LinkedHashMap<>();
            int sum = 0;
            if (extrasHandler.remainingExtras() >= 2) {
                //If we have extras we can select any family type
                sum += relTypeDist.get(FamilyType.COUPLE_ONLY);
                cumRelationsDist.put(FamilyType.COUPLE_ONLY, sum);
                sum += relTypeDist.get(FamilyType.OTHER_FAMILY);
                cumRelationsDist.put(FamilyType.OTHER_FAMILY, sum);
                sum += relTypeDist.get(FamilyType.ONE_PARENT);
                cumRelationsDist.put(FamilyType.ONE_PARENT, sum);
            } else {
                if ((!(marriedFemales.isEmpty() || marriedMales.isEmpty())) || (extrasHandler.remainingExtras() >= 1 && !(marriedFemales.isEmpty() && marriedMales.isEmpty()))) {
                    //If there are enough married males or females we can have couple family units
                    sum += relTypeDist.get(FamilyType.COUPLE_ONLY);
                    cumRelationsDist.put(FamilyType.COUPLE_ONLY, sum);
                }

                if (relatives.size() >= 2 || (!relatives.isEmpty() && extrasHandler.remainingExtras() >= 1)) {
                    //if we have enough relatives we can have other family units
                    sum += relTypeDist.get(FamilyType.OTHER_FAMILY);
                    cumRelationsDist.put(FamilyType.OTHER_FAMILY, sum);
                }


                if (primaryFT == FamilyType.ONE_PARENT || primaryFT == FamilyType.COUPLE_WITH_CHILDREN) {
                    if (!loneParents.isEmpty() && !children.isEmpty()) {
                        //if we have enough lone parents and children we can have one parent family units.
                        sum += relTypeDist.get(FamilyType.ONE_PARENT);
                        cumRelationsDist.put(FamilyType.ONE_PARENT, sum);
                    }
                }
            }

            //probabilistically select the family type
            double offset = random.nextInt(sum);
            FamilyType newFamilyType = null;
            for (FamilyType ft : cumRelationsDist.keySet()) {
                if (offset < cumRelationsDist.get(ft)) {
                    newFamilyType = ft;
                    break;
                }
            }
            return newFamilyType;
        };

        int formedCouples = 0, formedCwc = 0, formedOneParent = 0, formedOtherFamily = 0;

        for (Household h : households) {

            int missingFamilies = h.getExpectedFamilyCount() - h.getCurrentFamilyCount();
            for (int i = 0; i < missingFamilies; i++) {

                FamilyType primaryFT = h.getPrimaryFamilyType();
                FamilyType newFamilyType = probabilisticallySelectNewFamilyType.apply(primaryFT);

                Family newFamily = null;
                switch (newFamilyType) {
                    case COUPLE_ONLY:
                        newFamily = familyFactory.formCoupleFamilyBasicUnits(1, marriedMales, marriedFemales).get(0);
                        formedCouples++;
                        if (primaryFT == FamilyType.COUPLE_WITH_CHILDREN
                                //Adding a couple with child family must not block having required number of families. So check if there
                                // is room for all the possible persons
                                && ((h.getExpectedFamilyCount() - h.getCurrentFamilyCount()) * 2) > (h.getExpectedSize() - h
                                .getCurrentSize())
                                && random.nextDouble() < coupleWithChildProb) {
                            newFamily = familyFactory.formCoupleWithChildFamilyBasicUnits(1,
                                                                                          new ArrayList<>(Collections.singletonList(
                                                                                                  newFamily)),
                                                                                          children).get(0);
                            formedCwc++;
                            formedCouples--;
                        }
                        break;
                    case ONE_PARENT:
                        newFamily = familyFactory.formOneParentBasicUnits(1, loneParents, children).get(0);
                        formedOneParent++;
                        break;
                    case OTHER_FAMILY:
                        newFamily = familyFactory.formOtherFamilyBasicUnits(1, relatives).get(0);
                        formedOtherFamily++;
                        break;
                    default:
                        throw new Error("Unexpected FamilyType when forming Unknown Non-primary families: " + newFamilyType);

                }
                h.addFamily(newFamily);
            }

        }


        Log.debug("Non-primary unknown: Formed Couples: " + formedCouples);
        Log.debug("Non-primary unknown: Formed Couple with Child: " + formedCwc);
        Log.debug("Non-primary unknown: Formed Other Family: " + formedOtherFamily);
        Log.debug("Non-primary unknown: Formed One Parent: " + formedOneParent);
        Log.debug("Non-primary unknown: Remaining Married males: " + marriedMales.size());
        Log.debug("Non-primary unknown: Remaining Married females: " + marriedFemales.size());
        Log.debug("Non-primary unknown: Remaining Relatives: " + relatives.size());
        Log.debug("Non-primary unknown: Remaining Children: " + children.size());
        Log.debug("Non-primary unknown: Remaining Lone Parents: " + loneParents.size());
        Log.debug("Non-primary unknown: Remaining Extras: " + extrasHandler.remainingExtras());

        Log.info("Assigning unknown non-primary families completed");
    }


    /**
     * Assigns couples as second and third families to households
     *
     * @param households        The list of households
     * @param couples           Remaining couples
     * @param children          Remaining children
     * @param nonPrimaryCwcProb The probability of having a Couple With Children family as non-primary family in an eligible household
     * @param familyFactory     The FamilyFactory instance
     */
    private void assignCouplesAsNonPrimaryFamilies(List<Household> households,
                                                   List<Family> couples,
                                                   List<Person> children,
                                                   double nonPrimaryCwcProb,
                                                   FamilyFactory familyFactory) {
        Log.info("Adding " + FamilyType.COUPLE_ONLY + " as non primary");
        Log.debug(FamilyType.COUPLE_ONLY + ": Available family units: " + couples.size());
        Log.debug(FamilyType.COUPLE_ONLY + ": Remaining Children: " + children.size());

        List<Household> eligible = households.parallelStream()
                                             .filter(h -> h.getExpectedFamilyCount() > h.getCurrentFamilyCount() && h.getExpectedSize() >= h
                                                     .getCurrentSize() + 2)
                                             .collect(Collectors.toList());

        Log.debug(FamilyType.COUPLE_ONLY + ": Total eligible households: " + eligible.size());
        Collections.shuffle(eligible, random);
        Household h;

        int added = 0, completed = 0, cwcAdded = 0;
        Set<Household> cplAddedHhs = new HashSet<>(), cwcAddedHhs = new HashSet<>();
        while (!eligible.isEmpty() && !couples.isEmpty()) {
            h = eligible.get(0);
            Family f = couples.remove(0);
            if (h.getPrimaryFamilyType() == FamilyType.COUPLE_WITH_CHILDREN
                    //To add couple with children family, the Hh must have more free slots than required by basic families
                    && ((h.getExpectedFamilyCount() - h.getCurrentFamilyCount()) * 2 > h.getExpectedSize() - h.getCurrentSize())
                    && random.nextDouble() < nonPrimaryCwcProb) {

                familyFactory.formCoupleWithChildFamilyBasicUnits(1, new ArrayList<>(Collections.singletonList(f)), children);

                //bookkeeping
                cwcAdded++;
                if (!cwcAddedHhs.add(h) && h.getExpectedFamilyCount() != 3) {
                    throw new UnsupportedOperationException("Added a 3rd family to a 2 family household");
                }

            } else {
                //bookkeeping
                added++;
                if (!cplAddedHhs.add(h) && h.getExpectedFamilyCount() != 3) {
                    throw new UnsupportedOperationException("Added a 3rd family to a 2 family household");
                }
            }
            h.addFamily(f);

            if (h.getExpectedFamilyCount() == h.getCurrentFamilyCount()) {
                eligible.remove(h);
                completed++;
            }
        }

        Log.debug(FamilyType.COUPLE_ONLY + ": Used Couples: " + added + " Updated Households: " + cplAddedHhs.size());
        Log.debug(FamilyType.COUPLE_ONLY + ": Used Couples as Couple with Children: " + cwcAdded + " Updated Households: " + cwcAddedHhs
                .size());
        Log.debug(FamilyType.COUPLE_ONLY + ": Family count completed households: " + completed);
        Log.debug(FamilyType.COUPLE_ONLY + ": Remaining eligible households: " + eligible.size());
        Log.debug(FamilyType.COUPLE_ONLY + ": Remaining Children: " + children.size());
    }


    /**
     * Assigns the available One Parent basic units to multi-family households as non-primary families. This method filters the
     * households where the
     * primary family is either ONE_PARENT or COUPLE_WITH_CHILDREN. Then households are randomly selected for each assignment. A
     * household may be selected multiple times if it can contain multiple non-primary families (e.g. 3 family households). This method
     * alters the list of available basic
     * One Parent family units. This method assumes primary family of a multifamily household only consists of its basic members.
     *
     * @param households All the households
     * @param basicUnits The list of basic One Parent family units to be added as non-primary families to multi-family (must contain
     *                   families
     *                   of only one FamilyType) households in @param households
     */
    private void assignOneParentUnitsAsNonPrimaryFamilies(List<Household> households,
                                                          List<Family> basicUnits) {

        Log.info("Adding " + FamilyType.ONE_PARENT + " as non primary");
        Log.debug(FamilyType.ONE_PARENT + ": available family units: " + basicUnits.size());

        List<Household> eligibleHhs = households.stream()
                                                .filter(h -> (FamilyType.ONE_PARENT == h.getPrimaryFamilyType()
                                                        || FamilyType.COUPLE_WITH_CHILDREN == h.getPrimaryFamilyType()) &&
                                                        (h.getExpectedFamilyCount() > h.getCurrentFamilyCount()) &&
                                                        (h.getExpectedSize() - h.getCurrentSize() >= FamilyType.ONE_PARENT.basicSize()))
                                                //has enough vacancies
                                                .collect(Collectors.toList()); //Convert to an actual list

        Log.debug(FamilyType.ONE_PARENT + ": total eligible households: " + eligibleHhs.size());

        int added = 0, completed = 0;
        while (!basicUnits.isEmpty() && !eligibleHhs.isEmpty()) {
            int randIndex = random.nextInt(eligibleHhs.size());
            Family f = basicUnits.remove(0);
            f.setType(FamilyType.ONE_PARENT);
            eligibleHhs.get(randIndex).addFamily(f);
            if (eligibleHhs.get(randIndex).getExpectedFamilyCount() == eligibleHhs.get(randIndex)
                                                                                  .getCurrentFamilyCount()) {
                eligibleHhs.remove(randIndex);
                completed++;
            }
            added++;
        }

        Log.debug(FamilyType.ONE_PARENT + ": updated households: " + added);
        Log.debug(FamilyType.ONE_PARENT + ": family count completed households: " + completed);
        Log.debug(FamilyType.ONE_PARENT + ": remaining eligible households: " + eligibleHhs.stream()
                                                                                           .filter(h -> h.getExpectedFamilyCount() > h
                                                                                                   .getCurrentFamilyCount())
                                                                                           .count());
    }

    /**
     * Completes households by adding relatives to the families that are larger than 2 members. If there is not enough relatives Extras are
     * converted to relatives. This method does not check the number of families in the household. This method modifies the input lists.
     *
     * @param households          The households in the population
     * @param relatives           The list of relatives in the population
     * @param familyHouseholdType FamilyHouseholdType of the households to be completed. All households are selected if null
     */
    void completeHouseholdsWithRelatives(List<Household> households,
                                         List<Person> relatives,
                                         FamilyHouseholdType familyHouseholdType) {
        Log.info("Fill " + ((familyHouseholdType == null) ? "All" : familyHouseholdType) + " households with relatives");
        Log.debug("Start remaining relatives: " + relatives.size());
        Log.debug("Start remaining extras: " + extrasHandler.remainingExtras());

        Collections.shuffle(relatives, random);
        //Filter the household that match the family household type.
        List<Household> availableHhs = households.stream()
                                                 .filter(hh -> (hh.getFamilyHouseholdType() == familyHouseholdType || familyHouseholdType
                                                         == null) && (hh.getExpectedSize() > hh.getCurrentSize()))
                                                 .collect(Collectors.toList());
        Log.debug((familyHouseholdType != null ? familyHouseholdType.name() : "All") + ": Eligible households: " + availableHhs.size());
        Log.debug((familyHouseholdType != null ? familyHouseholdType.name() : "All") + ": Slots to fill: " + availableHhs.stream()
                                                                                                                         .mapToInt(h -> h
                                                                                                                                 .getExpectedSize() - h
                                                                                                                                 .getCurrentSize())
                                                                                                                         .sum());
        int formed = 0;
        for (Household h : availableHhs) {
            int diff = h.getExpectedSize() - h.getCurrentSize();
            if (diff > 0) {
                Family f = h.getPrimaryFamily();
                if (relatives.size() >= diff) {
                    f.addMembers(relatives.subList(0, diff));
                    relatives.subList(0, diff).clear();
                } else {
                    int rels = relatives.size();
                    f.addMembers(relatives);
                    relatives.clear();
                    f.addMembers(extrasHandler.getPersonsFromExtras(RelationshipStatus.RELATIVE, null, null, diff - rels));


                }
                formed++;
            }
        }

        Log.info((familyHouseholdType != null ? familyHouseholdType.name() : "All") + ": updated households: " + formed);
        Log.debug("End remaining relatives: " + relatives.size());
        Log.debug("End remaining extras: " + extrasHandler.remainingExtras());
    }

    /**
     * Adds children to the primary family of the households that can have children.
     *
     * @param households    Map of all households in the population
     * @param knownChildren The list of known children in input data
     */
    void completeHouseholdsWithChildren(List<Household> households,
                                        List<Person> knownChildren) {

        Log.info("Adding all known children to households");
        Log.debug("Total known Children from data: " + knownChildren.size());
        knownChildren.sort(new AgeRange.AgeComparator().reversed());
        List<Household> parentHhs = households.stream()
                                              .filter(h -> h.getPrimaryFamilyType() == FamilyType.ONE_PARENT || h.getPrimaryFamilyType()
                                                      == FamilyType.COUPLE_WITH_CHILDREN)
                                              .sorted(Comparator.comparing(Household::getPrimaryFamily,
                                                                           new AgeRange.YoungestParentAgeComparator().reversed()))
                                              .collect(Collectors.toList());
        Log.debug("All: Eligible households: " + parentHhs.size());
        Log.debug("All: Slots to fill: " + parentHhs.stream().mapToInt(h -> h.getExpectedSize() - h.getCurrentSize()).sum());


        Iterator<Person> childItr = knownChildren.iterator();
        int updated = 0, completed = 0;
        while (childItr.hasNext()) {
            Person child = childItr.next();
            int hhIndex = selectHouseholdWithSuitablePrimaryFamilyForChild(child, parentHhs);
            if (hhIndex >= 0) {
                Family pf = parentHhs.get(hhIndex).getPrimaryFamily();
                try {
                    pf.addMember(child);
                } catch (Error er) {
                    er.printStackTrace();
                    Family current = parentHhs.stream()
                                              .map(Household::getFamilies)
                                              .flatMap(List::stream)
                                              .filter(f -> f.getID().equals(child.getFamilyID()))
                                              .collect(Collectors.toList())
                                              .get(0);
                    Log.error("Problematic family: " + current.toString());
                }
                childItr.remove();
                updated++;
                if (parentHhs.get(hhIndex).getExpectedSize() == parentHhs.get(hhIndex).getCurrentSize()) {
                    completed++;
                    parentHhs.remove(hhIndex);
                }
            }
        }

        Log.debug("Children with no suitable primary family in any eligible household: " + knownChildren.size());

        childItr = knownChildren.iterator();
        while (childItr.hasNext()) {
            Person child = childItr.next();

            for (Household h : parentHhs) {
                Family suitableFamily = selectSuitableNonPrimaryFamilyForChild(child, h);
                if (suitableFamily != null) {
                    suitableFamily.addMember(child);
                    childItr.remove();
                    updated++;
                    if (h.getExpectedSize() == h.getCurrentSize()) {
                        completed++;
                    }
                }
            }
        }

        Log.debug("Children with no suitable non-primary families in any eligible household: " + knownChildren.size());
        Log.debug("Updated households: " + updated);
        Log.debug("Completed households: " + completed);

    }


    private Family selectSuitableNonPrimaryFamilyForChild(Person child, Household h) {

        Family suitableFamily = null;

        if ((h.getPrimaryFamilyType() == FamilyType.ONE_PARENT || h.getPrimaryFamilyType() == FamilyType.COUPLE_WITH_CHILDREN)
                && h.getExpectedSize() > h.getCurrentSize()) {

            if (h.getCurrentFamilyCount() > 1
                    && (h.getFamily(1).getType() == FamilyType.ONE_PARENT || h.getFamily(1)
                                                                              .getType() == FamilyType.COUPLE_WITH_CHILDREN)
                    && h.getFamily(1).size() + 1 < h.getPrimaryFamily().size()) {
                List<Person> parents = h.getFamily(1).getParents();
                if (PopulationRules.validateParentChildAgeRule(parents.get(0).getAgeRange(),
                                                               (parents.size() == 2) ? parents.get(1).getAgeRange() : null,
                                                               child.getAgeRange())) {
                    suitableFamily = h.getFamily(1);
                }

            } else if (h.getCurrentFamilyCount() > 2
                    && (h.getFamily(2).getType() == FamilyType.ONE_PARENT || h.getFamily(2)
                                                                              .getType() == FamilyType.COUPLE_WITH_CHILDREN)
                    && h.getFamily(2).size() + 1 < h.getPrimaryFamily().size()) {
                List<Person> parents = h.getFamily(2).getParents();
                if (PopulationRules.validateParentChildAgeRule(parents.get(0).getAgeRange(),
                                                               (parents.size() == 2) ? parents.get(1).getAgeRange() : null,
                                                               child.getAgeRange())) {
                    suitableFamily = h.getFamily(2);
                }
            }

        }
        return suitableFamily;

    }

    void addExtrasAsChildrenAndRelatives(List<Household> households,
                                         List<IndRecord> indRecs,
                                         List<Person> marriedMales,
                                         List<Person> marriedFemales,
                                         List<Person> loneParents,
                                         List<Person> children) {
        Log.debug("Remaining Extras: " + extrasHandler.remainingExtras());
        Log.info("Converting unused Lone Parents, Married Males and Married Females to extras");
        Log.debug("Remaining Married Males: " + marriedMales.size());
        Log.debug("Remaining Married Females: " + marriedFemales.size());
        Log.debug("Remaining Lone Parents: " + loneParents.size());
        Log.debug("Remaining Children: " + children.size());

        extrasHandler.addToExtras(marriedFemales);
        extrasHandler.addToExtras(marriedMales);
        extrasHandler.addToExtras(loneParents);
        extrasHandler.addToExtras(children);
        Log.debug("Remaining Extras: " + extrasHandler.remainingExtras());

        Log.info("Adding extras to households as children and relatives");
        List<Household> eligible = households.stream()
                                             .filter(h -> h.getExpectedSize() > h.getCurrentSize())
                                             .collect(Collectors.toList());

        Log.debug("All: Eligible households: " + eligible.size());
        Log.debug("All: Slots to fill: " + eligible.stream().mapToInt(h -> h.getExpectedSize() - h.getCurrentSize()).sum());

        List<IndRecord> relTypes = indRecs.stream()
                                          .filter(r -> r.RELATIONSHIP_STATUS == RelationshipStatus.RELATIVE)
                                          .collect(Collectors.toList());
        int updated = 0, completed = 0, newChildren = 0, newRelatives = 0;
        while (extrasHandler.remainingExtras() != 0 && !eligible.isEmpty()) {
            int i = random.nextInt(eligible.size());
            Household h = eligible.get(i);
            Family primaryFamily = h.getPrimaryFamily();

            List<IndRecord> indTypes = new ArrayList<>(relTypes);
            if (primaryFamily.getType() == FamilyType.COUPLE_WITH_CHILDREN || primaryFamily.getType() == FamilyType.ONE_PARENT) {
                indTypes.addAll(selectChildTypes(primaryFamily.getParents(), indRecs));
            }

            int sum = indTypes.stream().mapToInt(r -> r.IND_COUNT).sum();
            int offset = random.nextInt(sum);
            int s = 0;
            Person newPerson = null;
            for (IndRecord r : indTypes) {
                s += r.IND_COUNT;
                if (offset < s) {
                    newPerson = extrasHandler.getPersonsFromExtras(r.RELATIONSHIP_STATUS, r.SEX, r.AGE_RANGE, 1).get(0);
                    if (r.RELATIONSHIP_STATUS == RelationshipStatus.RELATIVE) {
                        newRelatives++;
                    } else {
                        newChildren++;
                    }
                    break;
                }
            }
            primaryFamily.addMember(newPerson);

            if (h.getExpectedSize() == h.getCurrentSize()) {
                h.validate();
                eligible.remove(h);
                completed++;
            }
            updated++;
        }
        Log.debug("New Children from extras: " + newChildren);
        Log.debug("New Relatives from extras: " + newRelatives);
        Log.debug("Updated households: " + updated);
        Log.debug("Completed households: " + completed);
        Log.debug("Remaining incomplete households: " + eligible.size());
        Log.debug("End remaining extras: " + extrasHandler.remainingExtras());
    }

    /**
     * Finds a suitable household that the given child can be added. Rule applied here is: a child must come from an age category with at
     * least a 15 year age gap (younger) to the parent's.
     *
     * @param child      The child that needs a family household
     * @param households The list of households to select from
     * @return The index of the suitable household. Returns -1 if no suitable household was found
     */
    private int selectHouseholdWithSuitablePrimaryFamilyForChild(Person child, List<Household> households) {
        for (int i = 0; i < households.size(); i++) {
            Household h = households.get(i);
            if ((h.getPrimaryFamilyType() == FamilyType.ONE_PARENT || h.getPrimaryFamilyType() == FamilyType
                    .COUPLE_WITH_CHILDREN)
                    && h.getExpectedSize() > h.getCurrentSize()) {
                Family pf = h.getPrimaryFamily();

                List<Person> parents = pf.getParents();

                if (PopulationRules.validateParentChildAgeRule(parents.get(0).getAgeRange(),
                                                               parents.size() == 2 ? parents.get(1).getAgeRange() : null,
                                                               child.getAgeRange())) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns a suitable child types for the given two parent based on population rules.
     *
     * @param parents    The two parents
     * @param indRecords The list of IndRecords to choose children types from
     * @return The list of suitable Child IndRecords
     */
    private List<IndRecord> selectChildTypes(List<Person> parents, List<IndRecord> indRecords) {
        return indRecords.stream()
                         .filter(r -> (r.RELATIONSHIP_STATUS == RelationshipStatus.U15_CHILD
                                 || r.RELATIONSHIP_STATUS == RelationshipStatus.STUDENT
                                 || r.RELATIONSHIP_STATUS == RelationshipStatus.O15_CHILD)
                                 && (PopulationRules.validateParentChildAgeRule(parents.get(0).getAgeRange(),
                                                                                parents.size() == 2 ? parents.get(1).getAgeRange() : null,
                                                                                r.AGE_RANGE)))
                         .collect(Collectors.toList());
    }
}
