package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.Log;

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

        int hhCount = lnPersonHhs.get(0).HH_COUNT;// Only 1 member households have lone persons
        int diff = hhCount - lonePersons.size();

        List<Household> hhList = new ArrayList<>();

        for (int i = 0; i < lnPersonHhs.get(0).HH_COUNT; i++) {
            Family f = new Family(FamilyType.LONE_PERSON);
            if (lonePersons.isEmpty()) {
                throw new NotEnoughPersonsException(lnPersonHhs.get(0).FAMILY_HOUSEHOLD_TYPE + ": Not enough lone " +
                                                            "persons");
            }
            f.addMember(lonePersons.remove(0));
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
                Family f = new Family(FamilyType.GROUP_HOUSEHOLD);
                if (hhSize > groupHhPersons.size()) {
                    throw new NotEnoughPersonsException(hhRec.FAMILY_HOUSEHOLD_TYPE + ": Not enough group household persons");
                }
                f.addMembers(groupHhPersons.subList(0, hhSize));
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
    List<Household> formAllFamilyHouseholdsWithPrimaryFamilies(
            List<Family> coupleOnlyBasic,
            List<Family> coupleWithChildrenBasic,
            List<Family> oneParentBasicFamilies,
            List<Family> otherFamilyBasic) {

        Log.info("Populating all family households with primary families");
        Collections.shuffle(coupleOnlyBasic, random);
        Collections.shuffle(coupleWithChildrenBasic, random);
        Collections.shuffle(oneParentBasicFamilies, random);
        Collections.shuffle(otherFamilyBasic, random);

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
                        throw new NotEnoughPersonsException(hhRec.FAMILY_HOUSEHOLD_TYPE + ": Not enough basic family " +
                                                                    "units ");
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
     * @param households                   The list of households
     * @param unusedCouples                remaining basic couple families
     * @param unusedBasicOneParentFamilies Remaining basic one parent families
     * @param children                     remaining children
     * @param relatives                    remaining relatives
     * @param marriedMales                 remaining married males
     * @param marriedFemales               remaining married females
     * @param loneParents                  remaining lone parents
     * @param nonPrimaryCwcProb            The proportion of household that have Couple With child secondary and tertiary families out of
     *                                     all eligible households
     * @param relDistInPrimaryFamilies     The probability distribution of couples, one parent families and other families among primary
     *                                     families
     * @param familyFactory                FamilyFactory instance.
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
                                           Map<FamilyType, Integer> relDistInPrimaryFamilies,
                                           FamilyFactory familyFactory) {
        Log.info("Assigning known Basic One Parent families to suitable households");
        assignNonPrimaryFamilies(households,
                                 FamilyType.ONE_PARENT,
                                 unusedBasicOneParentFamilies,
                                 FamilyType.ONE_PARENT,
                                 FamilyType.COUPLE_WITH_CHILDREN);
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
                                        relDistInPrimaryFamilies,
                                        nonPrimaryCwcProb,
                                        relatives,
                                        children,
                                        marriedMales,
                                        marriedFemales,
                                        loneParents,
                                        familyFactory);


    }

    private void assignUnknownNonPrimaryFamilies(List<Household> households,
                                                 Map<FamilyType, Integer> relDist,
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
                                                                       .mapToInt(h -> h.getExpectedFamilyCount() - h.getCurrentFamilyCount())
                                                                       .sum());

        Map<FamilyType, Integer> currentDist = new HashMap<>(4, 1);
        households.forEach(h -> {
            h.getFamilies().forEach(f -> {
                currentDist.compute((f.getType() == FamilyType.COUPLE_WITH_CHILDREN) ? FamilyType.COUPLE_ONLY : f.getType(),
                                    (k, v) -> (v == null) ? 1 : (v + 1));
            });
        });


        Function<FamilyType[], Family> createSuitableFamily = (FamilyType[] fTypes) -> {
            Arrays.sort(fTypes, Comparator.comparingInt((FamilyType ft) -> Utils.getAbsoluteError(relDist, currentDist, ft, 1)));

            Family newFamily = null;
            switch (fTypes[0]) {
                case COUPLE_ONLY:
                    newFamily = familyFactory.formCoupleFamilyBasicUnits(1, marriedMales, marriedFemales).get(0);
                    break;
                case OTHER_FAMILY:
                    newFamily = familyFactory.formOtherFamilyBasicUnits(1, relatives).get(0);
                    break;
                case ONE_PARENT:
                    newFamily = familyFactory.formOneParentBasicUnits(1, loneParents, children).get(0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected Family Type: " + fTypes[0]);
            }
            return newFamily;
        };

        int formedCouples = 0, formedCwc = 0, formedOneParent = 0, formedOtherFamily = 0;
        for (Household h : households) {

            int missingFamilies = h.getExpectedFamilyCount() - h.getCurrentFamilyCount();
            for (int i = 0; i < missingFamilies; i++) {
                FamilyType primaryFT = h.getPrimaryFamilyType();
                Family newFamily = null;

                if (primaryFT == FamilyType.COUPLE_ONLY || primaryFT == FamilyType.OTHER_FAMILY) {

                    newFamily = createSuitableFamily.apply(new FamilyType[]{FamilyType.COUPLE_ONLY, FamilyType.OTHER_FAMILY});
                    h.addFamily(newFamily);
                    if (newFamily.getType() == FamilyType.COUPLE_ONLY) ++formedCouples;
                    else ++formedOtherFamily;


                } else if (primaryFT == FamilyType.ONE_PARENT) {

                    newFamily = createSuitableFamily.apply(new FamilyType[]{FamilyType.COUPLE_ONLY, FamilyType.OTHER_FAMILY, FamilyType.ONE_PARENT});
                    h.addFamily(newFamily);
                    ++formedOneParent;

                } else if (primaryFT == FamilyType.COUPLE_WITH_CHILDREN) {

                    newFamily = createSuitableFamily.apply(new FamilyType[]{FamilyType.COUPLE_ONLY, FamilyType.OTHER_FAMILY, FamilyType.ONE_PARENT});

                    if (newFamily.getType() == FamilyType.COUPLE_ONLY
                            && random.nextDouble() < coupleWithChildProb
                            && h.getExpectedSize() - h.getCurrentSize() >= FamilyType.COUPLE_WITH_CHILDREN.basicSize()) {

                        newFamily = familyFactory.formCoupleWithChildFamilyBasicUnits(1,
                                                                                      new ArrayList<>(Arrays.asList(newFamily)),
                                                                                      children).get(0);
                        ++formedCwc;
                    } else {
                        ++formedCouples;
                    }

                    h.addFamily(newFamily);
                }
                currentDist.compute(newFamily.getType(), (k, v) -> v == 0 ? 1 : v + 1);
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
     * Converts a portion of Couple only units to Couple With Children families and assigns them as non-primary families
     *
     * @param households        The list of all households
     * @param nonPrimaryCwcProb The user defined portion of Couple With Children families among non-primary families
     * @return The number of families
     */
    void assignCoupleWithChildAsNonPrimaryFamilies(List<Household> households,
                                                   double nonPrimaryCwcProb,
                                                   List<Family> couples,
                                                   List<Person> children,
                                                   List<Person> marriedMales,
                                                   List<Person> marriedFemales,
                                                   FamilyFactory familyFactory) {

        int eligibleCount = (int) Math.round(households.stream()
                                                       .filter(h -> h.getExpectedFamilyCount() > h.getCurrentFamilyCount() &&
                                                               h.getPrimaryFamilyType() == FamilyType.COUPLE_WITH_CHILDREN &&
                                                               h.getExpectedSize() - h.getCurrentSize() >= FamilyType.COUPLE_WITH_CHILDREN
                                                                       .basicSize())
                                                       .count() * nonPrimaryCwcProb);
        if (eligibleCount > couples.size()) {
            couples.addAll(familyFactory.formCoupleFamilyBasicUnits(eligibleCount - couples.size(), marriedMales, marriedFemales));
        }
        List<Family> basicCoupleWithChildFamilies = familyFactory.formCoupleWithChildFamilyBasicUnits(eligibleCount, couples, children);
        assignNonPrimaryFamilies(households,
                                 FamilyType.COUPLE_WITH_CHILDREN,
                                 basicCoupleWithChildFamilies,
                                 FamilyType.COUPLE_WITH_CHILDREN);
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
    void assignCouplesAsNonPrimaryFamilies(List<Household> households,
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
        Household selectedHh;

        int added = 0, completed = 0, cwcAdded = 0;
        Set<Household> cplAddedHhs = new HashSet<>(), cwcAddedHhs = new HashSet<>();
        while (!eligible.isEmpty() && !couples.isEmpty()) {
            selectedHh = eligible.get(0);
            Family f2 = couples.remove(0);
            if (selectedHh.getPrimaryFamilyType() == FamilyType.COUPLE_WITH_CHILDREN
                    && (selectedHh.getExpectedSize() - selectedHh.getCurrentSize() >= 3)
                    && random.nextDouble() < nonPrimaryCwcProb) {

                familyFactory.addChildToFamily(f2, children);
                //bookkeeping
                cwcAdded++;
                if (cwcAddedHhs.add(selectedHh) == false && selectedHh.getExpectedFamilyCount() != 3) {
                    throw new UnsupportedOperationException("Added a 3rd family to a 2 family household");
                }

            } else {
                //bookkeeping
                added++;
                if (cplAddedHhs.add(selectedHh) == false && selectedHh.getExpectedFamilyCount() != 3) {
                    throw new UnsupportedOperationException("Added a 3rd family to a 2 family household");
                }
            }
            selectedHh.addFamily(f2);

            if (selectedHh.getExpectedFamilyCount() == selectedHh.getCurrentFamilyCount()) {
                eligible.remove(selectedHh);
                completed++;
            }
        }

        Log.debug(FamilyType.COUPLE_ONLY + ": Used Couples: " + added + " Updated Households: " + cplAddedHhs.size());
        Log.debug(FamilyType.COUPLE_ONLY + ": Used Couples as Couple with Children: " + cwcAdded + " Updated Households: " + cwcAddedHhs.size());
        Log.debug(FamilyType.COUPLE_ONLY + ": Family count completed households: " + completed);
        Log.debug(FamilyType.COUPLE_ONLY + ": Remaining eligible households: " + eligible.size());
        Log.debug(FamilyType.COUPLE_ONLY + ": Remaining Children: " + children.size());
    }


    /**
     * Assigns the available basic units to multi-family households as non-primary families. This method filters in the households where the
     * primary family belong to any of the eligible FamilyType(s) specified. Then households are randomly selected for each assignment. A
     * household may be selected multiple times if it can contain multiple non-primary families (e.g. 3 family households). The FamilyType
     * of the newly added family is set to FamilyType specified as nonPrimaryFamilyType. This method alters the list of available basic
     * family units. This method assumes primary family of a multifamily household only consists of its basic members.
     *
     * @param households           All the households
     * @param nonPrimaryFamilyType The FamilyType of the newly added non primary families
     * @param basicUnits           The list of basic family units to be added as non-primary families to multi-family (must contain families
     *                             of only one FamilyType) households in @param households
     * @param eligibleFamilyTypes  The array of FamilyType(s) that the primary family of the household can belong to.
     */
    private void assignNonPrimaryFamilies(List<Household> households,
                                          FamilyType nonPrimaryFamilyType,
                                          List<Family> basicUnits,
                                          FamilyType... eligibleFamilyTypes) {

        Log.info("Adding " + nonPrimaryFamilyType + " as non primary");
        Log.debug(nonPrimaryFamilyType + ": available family units: " + basicUnits.size());

        List<Household> eligibleHhs = households.stream()
                                                .filter(h -> (Arrays.asList(eligibleFamilyTypes).contains(h.getPrimaryFamilyType())) &&
                                                        (h.getExpectedFamilyCount() > h.getCurrentFamilyCount()) &&
                                                        (h.getExpectedSize() - h.getCurrentSize() >= nonPrimaryFamilyType.basicSize()))
                                                //has enough vacancies
                                                .collect(Collectors.toList()); //Convert to an actual list

        Log.debug(nonPrimaryFamilyType + ": total eligible households: " + eligibleHhs.size());

        int added = 0, completed = 0;
        while (!basicUnits.isEmpty() && !eligibleHhs.isEmpty()) {
            int randIndex = random.nextInt(eligibleHhs.size());
            Family f = basicUnits.remove(0);
            f.setType(nonPrimaryFamilyType);
            eligibleHhs.get(randIndex).addFamily(f);
            if (eligibleHhs.get(randIndex).getExpectedFamilyCount() == eligibleHhs.get(randIndex)
                                                                                  .getCurrentFamilyCount()) {
                eligibleHhs.remove(randIndex);
                completed++;
            }
            added++;
        }

        Log.debug(nonPrimaryFamilyType + ": updated households: " + added);
        Log.debug(nonPrimaryFamilyType + ": family count completed households: " + completed);
        Log.debug(nonPrimaryFamilyType + ": remaining eligible households: " + eligibleHhs.stream()
                                                                                          .filter(h -> h.getExpectedFamilyCount() > h.getCurrentFamilyCount())
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
                                                                                                                         .mapToInt(h -> h.getExpectedSize() - h
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
                    f.addMembers(extrasHandler.getPersonsFromExtras(RelationshipStatus.RELATIVE, null, null, diff - relatives.size()));
                    f.addMembers(relatives);
                    relatives.clear();
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
     * @param knownChildren The list of knwon children in input data
     */
    void completeHouseholdsWithChildren(List<Household> households,
                                        List<Person> knownChildren) {

        Log.info("Adding all known children to households");
        Log.debug("Total known Children from data: " + knownChildren.size());
        knownChildren.sort(new AgeRange.AgeComparator().reversed()); // Do older children first because its hard to find families for them

        int updated = 0, completed = 0;
        while (!knownChildren.isEmpty()) {
            Person child = knownChildren.get(0);
            Collections.shuffle(households, random);
            int hhIndex = PopulationRules.selectHouseholdWithSuitablePrimaryFamilyForChild(child, households);
            if (hhIndex >= 0) {
                Family pf = households.get(hhIndex).getPrimaryFamily();
                try {
                    pf.addMember(child);
                } catch (Error er) {
                    er.printStackTrace();
                    Family current = households.stream()
                                               .map(h -> h.getFamilies())
                                               .flatMap(List::stream)
                                               .filter(f -> f.getID()
                                                             .equals(child.getFamilyID()))
                                               .collect(Collectors.toList())
                                               .get(0);
                    Log.error("Problematic family: " + current.toString());
                }
                knownChildren.remove(child);
                updated++;
                if (households.get(hhIndex).getExpectedSize() == households.get(hhIndex).getCurrentSize()) {
                    completed++;
                }
            } else {
                throw new NoSuitableHouseholdException("Cannot find a household for " + child.getRelationshipStatus());
            }
        }
        Log.debug("Updated households: " + updated);
        Log.debug("Completed households: " + completed);

    }

    void addExtrasAsChildrenAndRelatives(List<Household> households, List<IndRecord> indRecs) {

        List<Household> eligible = households.stream()
                                             .filter(h -> h.getExpectedSize() > h.getCurrentSize())
                                             .collect(Collectors.toList());

        while (extrasHandler.remainingExtras() != 0) {
            int i = random.nextInt(eligible.size());
            Household h = eligible.get(i);
            Family primaryFamily = h.getPrimaryFamily();

            Person youngestParent = primaryFamily.getYoungestParent();
            List<IndRecord> indTypes = new ArrayList<>();
            if (youngestParent != null) {
                indTypes.addAll(PopulationRules.selectChildTypes(youngestParent, indRecs));
            }
            indTypes.addAll(indRecs.stream()
                                   .filter(r -> r.RELATIONSHIP_STATUS == RelationshipStatus.RELATIVE)
                                   .collect(Collectors.toList()));

            int sum = indTypes.stream().mapToInt(r -> r.IND_COUNT).sum();
            int offset = random.nextInt(sum);
            int s = 0;
            Person newPerson = null;
            for (IndRecord r : indTypes) {
                s += r.IND_COUNT;
                if (offset < s) {
                    newPerson = extrasHandler.getPersonFromExtras(r);
                    break;
                }
            }
            primaryFamily.addMember(newPerson);

            if (h.getExpectedSize() == h.getCurrentSize()) {
                h.validate();
                eligible.remove(h);
            }

        }
    }
}
