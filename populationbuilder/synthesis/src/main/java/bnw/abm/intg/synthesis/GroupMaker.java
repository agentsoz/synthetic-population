package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.GlobalConstants.ExitCode;
import bnw.abm.intg.util.Log;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class GroupMaker {

    private final int f1 = 1, f2 = 2, f3 = 3;
    private final String coupleOnly = "Couple family with no children";
    private final String coupleYesChild = "Couple family with children";
    private final String oneParent = "One parent family";
    private final String otherFamily = "Other family";
    private final String lonePerson = "Lone person household";
    private final String groupHousehold = "Group household";
    private List<Household> allHouseholds = new ArrayList<>();
    private Random random = null;
    private String sa2name;

    private double maleProbability, relativeProbability, maleLoneParentProbability;
    private ExtrasHandler extrasHander;

    public GroupMaker(double maleProbability, double relativeProbability, double femaleLoneParentProbability) {
        this.maleProbability = maleProbability;
        this.relativeProbability = relativeProbability;
        this.maleLoneParentProbability = femaleLoneParentProbability;
    }

    List<Household> makePopulation(List<HhRecord> hhRecs, List<IndRecord> indrecs, Random rand, String sa2) {
        this.sa2name = sa2;
        // printHhSummary(hhRecs)
        // printIndSummary(indrecs);

        this.random = rand;
        extrasHander = new ExtrasHandler(hhRecs, indrecs, maleProbability, random);
        Log.info("Extras (difference between households and persons files): " + extrasHander.remainingExtras());

        makeLonePersonsHhs(hhRecs, indrecs);
        makeGroupHouseholds(hhRecs, indrecs);


        List<Person> married = PersonsFactory.makeAllPersonsByRelationshipType(indrecs, RelationshipStatus.MARRIED);
        List<Person> marriedMales = married.stream().filter(p -> p.getSex() == Sex.Male).collect(Collectors.toList());
        List<Person> marriedFemales = married.stream()
                .filter(p -> p.getSex() == Sex.Female)
                .collect(Collectors.toList());

        List<Person> relatives = PersonsFactory.makeAllPersonsByRelationshipType(indrecs, RelationshipStatus.RELATIVE);
        List<Person> loneParents = PersonsFactory.makeAllPersonsByRelationshipType(indrecs,
                                                                                   RelationshipStatus.LONE_PARENT);
        List<Person> children = PersonsFactory.makeAllPersonsByRelationshipType(indrecs,
                                                                                RelationshipStatus.U15_CHILD,
                                                                                RelationshipStatus.STUDENT,
                                                                                RelationshipStatus.O15_CHILD);

        FamilyFactory familyFactory = new FamilyFactory(rand);


        Log.debug("Remaining Married males: " + marriedMales.size());
        Log.debug("Remaining Married females: " + marriedFemales.size());
        Log.debug("Remaining Relatives: " + relatives.size());
        Log.debug("Remaining Children: " + children.size());
        Log.debug("Remaining Lone parents: " + loneParents.size());

        List<Family> basicCouples = familyFactory.makeAllMarriedCouples(marriedMales, marriedFemales);
        Log.debug("Remaining Married males: " + marriedMales.size());
        Log.debug("Remaining Married females: " + marriedFemales.size());
        Log.debug("Remaining Basic couples: " + basicCouples.size());

        List<Family> oneParentBasic = familyFactory.makeAllOneParentBasicStructs(loneParents, children);
        Log.debug("Remaining Lone parent persons: " + loneParents.size());
        Log.debug("Remaining Children: " + children.size());

        //Form basic family structures and removes couples from married males and females list
        List<HhRecord> coupleWChildRecords = DataReader.getHhsByFamilyType(hhRecs,
                                                                           FamilyHouseholdType.F1_COUPLE_WITH_CHILDREN,
                                                                           FamilyHouseholdType.F2_COUPLE_WITH_CHILDREN,
                                                                           FamilyHouseholdType.F3_COUPLE_WITH_CHILDREN);
        List<Family> primaryCoupleWChildFamilyBasic = familyFactory.makePrimaryCoupleWithChildFamilyBasicStructs(
                coupleWChildRecords, basicCouples, children);
        Log.debug("Remaining Basic couples: " + basicCouples.size());
        Log.debug("Remaining Children: " + children.size());

        // Forms Other Family basic family structures
        List<HhRecord> otherFamilyRecords = DataReader.getHhsByFamilyType(hhRecs,
                                                                          FamilyHouseholdType.F1_OTHER_FAMILY,
                                                                          FamilyHouseholdType.F2_OTHER_FAMILY,
                                                                          FamilyHouseholdType.F3_OTHER_FAMILY);
        List<Family> primaryOtherFamiliesBasic = familyFactory.makeAllPrimaryOtherFamilyBasicStructs(
                otherFamilyRecords, relatives);
        Log.debug("Remaining Relatives: " + relatives.size());

        try {
            Map<Integer, Map<FamilyHouseholdType, List<Household>>> basicHouseholds = formHouseholdsWithPrimaryFamilies(
                    hhRecs,
                    basicCouples,
                    primaryCoupleWChildFamilyBasic,
                    oneParentBasic,
                    primaryOtherFamiliesBasic);
            Log.debug("Remaining Couples basic: " + basicCouples.size());
            Log.debug("Remaining Couple with children basic: " + primaryCoupleWChildFamilyBasic.size());
            Log.debug("Remaining Other family basic: " + primaryOtherFamiliesBasic.size());
            Log.debug("Remaining One parent basic: " + oneParentBasic.size());

            fillCoupleOnly1FamilyHouseholds(basicHouseholds.values()
                                                    .stream()
                                                    .map(m -> m.get(FamilyHouseholdType.F1_COUPLE_ONLY))
                                                    .filter(l -> l != null)
                                                    .flatMap(List::stream)
                                                    .collect(Collectors.toList()),//Filters one family couple only households from basicHouseholds
                                            relatives);
            Log.debug("Remaining relatives: " + relatives.size());
            Log.debug("Remaining extras: " + extrasHander.remainingExtras());


            //            Log.debug("Remaining primary couple only family basic structures: " + primaryCoupleOnlyFamilyBasic.size());
            Log.debug("Remaining married males (not expected to change): " + marriedMales.size());
            Log.debug("Remaining married females (not expected to change): " + marriedFemales.size());
            Log.debug("Remaining relatives: " + relatives.size());
            Log.debug("Remaining extras: " + extrasHander.remainingExtras());

        } catch (NotEnoughPersonsException npex) {
            Log.debug("Remaining Couples basic: " + basicCouples.size());
            Log.debug("Remaining Relatives: " + relatives.size());
            Log.debug("Remaining Couple with children basic: " + primaryCoupleWChildFamilyBasic.size());
            Log.debug("Remaining Other family basic: " + primaryOtherFamiliesBasic.size());
            Log.debug("Remaining Children: " + children.size());
            Log.debug("Remaining Extras: " + extrasHander.remainingExtras());
            Log.errorAndExit("Family households construction failed", npex, ExitCode.DATA_ERROR);
        }

        //        try {
        //            formOtherFamily1FamilyHouseholds(hhRecs, primaryOtherFamiliesBasic);
        //            Log.debug("Remaining primary other family basic structures: " + primaryOtherFamiliesBasic.size());
        //            Log.debug("Remaining relatives: " + relatives.size());
        //            Log.debug("Remaining extras: " + extrasHander.remainingExtras());
        //

        //
        //            formCoupleWithChild1FamilyHouseholds(hhRecs, primaryCoupleWChildFamilyBasic);
        //            Log.debug("Remaining primary couple w/ children basic structures: " + primaryCoupleWChildFamilyBasic.size
        //                    ());
        //            Log.debug("Remaining children: " + children.size());
        //            Log.debug("Remaining relatives: " + relatives.size());
        //            Log.debug("Remaining extras: " + extrasHander.remainingExtras());
        //
        //            formLoneParent1FamilyHouseholds(hhRecs);
        //            Log.debug("Remaining lone parent basic structures: " + loneParentBasic.size());
        //            Log.debug("Remaining children: " + children.size());
        //            Log.debug("Remaining relatives: " + relatives.size());
        //            Log.debug("Remaining extras: " + extrasHander.remainingExtras());
        //
        //            List<Household> multiFamilyHhWith1Family = formPrimaryFamiliesForMultiFamilyHouseholds(hhRecs,
        //                                                                                                   primaryCoupleOnlyFamilyBasic,
        //                                                                                                   primaryCoupleWChildFamilyBasic,
        //                                                                                                   loneParentBasic,
        //                                                                                                   primaryOtherFamiliesBasic);
        //            Log.debug("Remaining married males: " + marriedMales.size());
        //            Log.debug("Remaining married females: " + marriedFemales.size());
        //            Log.debug("Remaining lone parent basic structures: " + loneParentBasic.size());
        //            Log.debug("Remaining children: " + children.size());
        //            Log.debug("Remaining relatives: " + relatives.size());
        //            Log.debug("Remaining extras: " + extrasHander.remainingExtras());
        //
        //            formBasicStructuresFor2ndAnd3rdFamiliesInMultiFamilyHouseholds(hhRecs, indrecs);
        //            Log.debug("Remaining married males: " + marriedMales.size());
        //            Log.debug("Remaining married females: " + marriedFemales.size());
        //            Log.debug("relatives: " + relatives.size());
        //            Log.debug("children: " + children.size());
        //            Log.debug("Lone parent: " + loneParentBasic.size());
        //            Log.debug("Other family: " + primaryOtherFamiliesBasic.size());
        //            Log.debug("Non primary other families: " + nonPrimaryOtherFamilies.size());
        //            Log.debug("Extras: " + extrasHander.remainingExtras());
        //
        //            addNonPrimaryFamiliesToMultiFamilyHousehold(multiFamilyHhWith1Family);
        //        } catch (Exception e) {
        //            Log.error("Error in population construction", e);
        //        } finally {
        //
        //
        //            Log.debug("------------ Discarded persons and family structures -----------");
        //            Log.debug("primary couple with child basic structures: " + primaryCoupleWChildFamilyBasic.size());
        //            Log.debug("primary couple only: " + primaryCoupleOnlyFamilyBasic.size());
        //            Log.debug("primary other family: " + primaryOtherFamiliesBasic.size());
        //            Log.debug("basic lone parents: " + loneParentBasic.size());
        //            Log.debug("Remaining married males: " + marriedMales.size());
        //            Log.debug("Remaining married females: " + marriedFemales.size());
        //            Log.debug("relatives: " + relatives.size());
        //            Log.debug("lone parents: " + loneParents.size());
        //            Log.debug("children: " + children.size());
        //            Log.debug("Extras: " + extrasHander.remainingExtras());
        //            Log.debug("All formed households: " + allHouseholds.size());
        //
        //            //            Utils.printHhSummary(hhRecs);
        //        }
        Utils.summary(allHouseholds);
        return allHouseholds;
    }

    //    private void formBasicStructuresFor2ndAnd3rdFamiliesInMultiFamilyHouseholds(List<HhRecord> householdRecords,
    //                                                                                List<IndRecord> individualRecords) {
    //        String logPrefix = "Basic structures for non-primary families in multi-family households: ";
    //        int familiesWithMarriedCouples = 0, loneParentFamilies = 0, otherFamilies = 0;
    //
    //        List<HhRecord> f2Households = DataReader.getHhsByFamilyType(householdRecords,
    //                                                                    FamilyHouseholdType.F2_ONE_PARENT,
    //                                                                    FamilyHouseholdType.F2_COUPLE_WITH_CHILDREN,
    //                                                                    FamilyHouseholdType.F2_COUPLE_ONLY,
    //                                                                    FamilyHouseholdType.F2_OTHER_FAMILY);
    //        int f2SecondCount = 0;
    //        for (HhRecord hhRecord : f2Households) {
    //            if (hhRecord.primaryFamilyType == FamilyType.COUPLE_WITH_CHILDREN | hhRecord.primaryFamilyType ==
    //                    FamilyType.COUPLE_ONLY) {
    //                familiesWithMarriedCouples += hhRecord.hhCount;
    //            } else if (hhRecord.primaryFamilyType == FamilyType.ONE_PARENT) {
    //                loneParentFamilies += hhRecord.hhCount;
    //            } else if (hhRecord.primaryFamilyType == FamilyType.OTHER_FAMILY) {
    //                otherFamilies += hhRecord.hhCount;
    //            }
    //            f2SecondCount += hhRecord.hhCount;
    //        }
    //        List<HhRecord> f3Households = DataReader.getHhsByFamilyType(householdRecords,
    //                                                                    FamilyHouseholdType.F3_ONE_PARENT,
    //                                                                    FamilyHouseholdType.F3_COUPLE_WITH_CHILDREN,
    //                                                                    FamilyHouseholdType.F3_COUPLE_ONLY,
    //                                                                    FamilyHouseholdType.F3_OTHER_FAMILY);
    //        int f3SecondCount = 0, f3ThirdCount = 0;
    //        for (HhRecord hhRecord : f3Households) {
    //            if (hhRecord.primaryFamilyType == FamilyType.COUPLE_WITH_CHILDREN | hhRecord.primaryFamilyType ==
    //                    FamilyType.COUPLE_ONLY) {
    //                familiesWithMarriedCouples += hhRecord.hhCount;
    //            } else if (hhRecord.primaryFamilyType == FamilyType.ONE_PARENT) {
    //                loneParentFamilies += hhRecord.hhCount;
    //            } else if (hhRecord.primaryFamilyType == FamilyType.OTHER_FAMILY) {
    //                otherFamilies += hhRecord.hhCount;
    //            }
    //            f3SecondCount += hhRecord.hhCount;
    //            f3ThirdCount += hhRecord.hhCount;
    //        }
    //
    //        List<HhRecord> f1Households = DataReader.getHhsByFamilyType(householdRecords,
    //                                                                    FamilyHouseholdType.F1_ONE_PARENT,
    //                                                                    FamilyHouseholdType.F1_COUPLE_WITH_CHILDREN,
    //                                                                    FamilyHouseholdType.F1_COUPLE_ONLY,
    //                                                                    FamilyHouseholdType.F1_OTHER_FAMILY);
    //        for (HhRecord hhRecord : f1Households) {
    //            if (hhRecord.primaryFamilyType == FamilyType.COUPLE_WITH_CHILDREN | hhRecord.primaryFamilyType ==
    //                    FamilyType.COUPLE_ONLY) {
    //                familiesWithMarriedCouples += hhRecord.hhCount;
    //            } else if (hhRecord.primaryFamilyType == FamilyType.ONE_PARENT) {
    //                loneParentFamilies += hhRecord.hhCount;
    //            } else if (hhRecord.primaryFamilyType == FamilyType.OTHER_FAMILY) {
    //                otherFamilies += hhRecord.hhCount;
    //            }
    //
    //        }
    //
    //        int totalRequiredNonPrimaryFamilies = f2SecondCount + f3SecondCount + f3ThirdCount;
    //        int creatableNonPrimaryOtherFamiliesWithExistingRelatives = relatives.size() / 2;
    //        //TODO: We assume heterosexual marital partnerships. marriedFemales.size() is not correct if we consider
    //        // homosexual partnerships
    //        int possibleCouples = marriedFemales.size() > marriedMales.size() ? marriedMales.size() : marriedFemales.size();
    //        int totalAvailableBasicStructs = possibleCouples + loneParentBasic.size() +
    //                creatableNonPrimaryOtherFamiliesWithExistingRelatives;
    //        int newNonPrimaryMarriedCpls = 0, newNonPrimaryLoneParents = 0, newNonPrimaryOtherFamilies = 0;
    //
    //
    //        if (totalAvailableBasicStructs < totalRequiredNonPrimaryFamilies) {
    //
    //            int extraNonPrimaryFamiliesToCreate = totalRequiredNonPrimaryFamilies - totalAvailableBasicStructs;
    //            //We know the total number of extra families we want. But we don't know how many from each family type.
    //            //So we divide the number among the family types according to distribution we observed in the population.
    //            int totalPrimaryFamilyBasicStructs = familiesWithMarriedCouples + loneParentFamilies + otherFamilies;
    //            newNonPrimaryMarriedCpls = Math.round((familiesWithMarriedCouples / (float)
    //                    totalPrimaryFamilyBasicStructs) * extraNonPrimaryFamiliesToCreate);
    //            newNonPrimaryLoneParents = Math.round((loneParentFamilies / (float) totalPrimaryFamilyBasicStructs) *
    //                                                          extraNonPrimaryFamiliesToCreate);
    //            newNonPrimaryOtherFamilies = extraNonPrimaryFamiliesToCreate - (newNonPrimaryLoneParents +
    //                    newNonPrimaryMarriedCpls);
    //            newNonPrimaryOtherFamilies += creatableNonPrimaryOtherFamiliesWithExistingRelatives;
    //
    //            List<AgeRange> marriedAges = new ArrayList<>(Arrays.asList(AgeRange.A25_39,
    //                                                                       AgeRange.A40_54,
    //                                                                       AgeRange.A55_69,
    //                                                                       AgeRange.A70_84));
    //
    //            for (int i = 0; i < newNonPrimaryMarriedCpls; i++) {
    //                Collections.shuffle(marriedAges, random);
    //                if (marriedFemales.size() == marriedMales.size()) {
    //                    Person male = extrasHander.getPersonFromExtras(RelationshipStatus.MARRIED,
    //                                                                   marriedAges.get(0),
    //                                                                   Sex.Male);
    //                    marriedMales.add(male);
    //
    //                    Person female = extrasHander.getPersonFromExtras(RelationshipStatus.MARRIED,
    //                                                                     marriedAges.get(0),
    //                                                                     Sex.Female);
    //                    marriedFemales.add(female);
    //                } else if (marriedFemales.size() < marriedMales.size()) {
    //                    Person female = extrasHander.getPersonFromExtras(RelationshipStatus.MARRIED,
    //                                                                     marriedAges.get(0),
    //                                                                     Sex.Female);
    //                    marriedFemales.add(female);
    //                } else {
    //                    Person male = extrasHander.getPersonFromExtras(RelationshipStatus.MARRIED,
    //                                                                   marriedAges.get(0),
    //                                                                   Sex.Male);
    //                    marriedMales.add(male);
    //                }
    //            }
    //            Log.info(logPrefix + " new married couples: " + newNonPrimaryMarriedCpls);
    //
    //            for (int i = 0; i < newNonPrimaryLoneParents; i++) {
    //                Family family = new Family(FamilyType.BASIC);
    //
    //                Person child = null;
    //
    //                if (children.size() != 0) {
    //                    child = children.remove(0);
    //                    family.addMember(child);
    //                } else {
    //                    /*TODO: Implement proper age selection for children. Parent's age need to increase accordingly
    //                    For now selecting an age range for the child. For simplicity we consider child as 0-14 U15Child
    //                    or 15-24 student. We ignore independent children over 15 for now*/
    //                    extrasHander.addMembersToFamilyFromExtras(family,
    //                                                              Utils.tossCoinWithBias(random,
    //                                                                                     0.5) ? RelationshipStatus.U15_CHILD : RelationshipStatus.STUDENT,
    //                                                              1);
    //                    child = family.getMembers().get(0);
    //                }
    //                /*
    //                Creating the lone parent. We assume parent's age come from 3 age categories above child's age
    //                 */
    //                Sex parentSex = Utils.getSexRandomly(random, maleLoneParentProbability);
    //                AgeRange ageRange;
    //                int childAgeIndex = Arrays.asList(AgeRange.values()).indexOf(child.getAgeRange());
    //                ageRange = Utils.getRandomlyWithoutShuffling(random,
    //                                                             Arrays.asList(AgeRange.values())
    //                                                                     .subList(childAgeIndex + 1, childAgeIndex + 4));
    //                Person parent = extrasHander.getPersonFromExtras(RelationshipStatus.LONE_PARENT, ageRange, parentSex);
    //                family.addMember(parent);
    //                loneParentBasic.add(family);
    //            }
    //
    //
    //            Log.info(logPrefix + " lone parent basic: " + newNonPrimaryLoneParents);
    //        } else {
    //            // At this stage we can form all requiredNonPrimaryOtherFamilies from existing relatives
    //            //TODO: change marriedFemale.size() when considering homosexual marital partnerships
    //            newNonPrimaryOtherFamilies = totalRequiredNonPrimaryFamilies - (marriedFemales.size() + loneParentBasic
    //                    .size());
    //        }
    //
    //
    //        List<AgeRange> agesAll = new ArrayList<>(Arrays.asList(AgeRange.values()));
    //        nonPrimaryOtherFamilies = new ArrayList<>();
    //        for (int i = 0; i < newNonPrimaryOtherFamilies; i++) {
    //            Family family = new Family(FamilyType.OTHER_FAMILY);
    //            Person rel1, rel2;
    //            if (relatives.size() < 2) {
    //
    //                rel1 = extrasHander.getPersonFromExtras(RelationshipStatus.RELATIVE,
    //                                                        Utils.getRandomlyWithoutShuffling(random, agesAll),
    //                                                        Utils.getSexRandomly(random, maleProbability));
    //                rel2 = extrasHander.getPersonFromExtras(RelationshipStatus.RELATIVE,
    //                                                        (rel1.getAgeRange() == AgeRange.A0_14) ?
    //                                                                Utils.getRandomlyWithoutShuffling(random,
    //                                                                                                  agesAll.subList(0,
    //                                                                                                                  agesAll.size() - 1))
    //                                                                : Utils.getRandomlyWithoutShuffling(random, agesAll),
    //                                                        Utils.getSexRandomly(random, maleProbability));
    //            } else {
    //                rel1 = relatives.remove(0);
    //                rel2 = relatives.remove(0);
    //            }
    //            family.addMember(rel1);
    //            family.addMember(rel2);
    //            nonPrimaryOtherFamilies.add(family);
    //        }
    //
    //        Log.info(logPrefix + " other family basic: " + newNonPrimaryOtherFamilies);
    //    }


    /**
     * Constructs all family households (except Lone person and group households) and adds a suitable basic primary family unit.
     * Input lists are altered by this method.
     *
     * @param hhRecs                  Household records from data files
     * @param coupleOnlyBasic         couple only basic family units
     * @param coupleWithChildrenBasic couple with children basic family units
     * @param oneParentBasicFamilies  one parent basic family units
     * @param otherFamilyBasic        other family basic family unis
     * @return Map of all partially formed family households against their Household Size and FamilyHouseholdType
     */
    private Map<Integer, Map<FamilyHouseholdType, List<Household>>> formHouseholdsWithPrimaryFamilies(List<HhRecord> hhRecs,
                                                                                                      List<Family>
                                                                                                              coupleOnlyBasic,
                                                                                                      List<Family> coupleWithChildrenBasic,
                                                                                                      List<Family> oneParentBasicFamilies,
                                                                                                      List<Family> otherFamilyBasic) {

        Log.info("Populating all family households with primary families");
        Collections.shuffle(coupleOnlyBasic, random);
        Collections.shuffle(coupleWithChildrenBasic, random);
        Collections.shuffle(oneParentBasicFamilies, random);
        Collections.shuffle(otherFamilyBasic, random);

        Map<Integer, Map<FamilyHouseholdType, List<Household>>> basicHouseholds = new HashMap<>();

        for (HhRecord hhRec : hhRecs) {

            List<Family> primaryFamilyUnitsList;
            switch (hhRec.getPrimaryFamilyType()) {
                case COUPLE_ONLY:
                    primaryFamilyUnitsList = coupleOnlyBasic;
                    break;
                case COUPLE_WITH_CHILDREN:
                    primaryFamilyUnitsList = coupleWithChildrenBasic;
                    break;
                case ONE_PARENT:
                    primaryFamilyUnitsList = oneParentBasicFamilies;
                    break;
                case OTHER_FAMILY:
                    primaryFamilyUnitsList = otherFamilyBasic;
                    break;
                default:
                    continue;
            }

            int formed = 0;
            List<Household> familyHouseholds = new ArrayList<>();
            if (hhRec.hhCount > 0) {
                for (int i = 0; i < hhRec.hhCount; i++) {
                    if (primaryFamilyUnitsList.isEmpty()) {
                        throw new NotEnoughPersonsException(hhRec.FAMILY_HOUSEHOLD_TYPE + ": Not enough basic family units ");
                    }
                    Household household = new Household(hhRec.NUM_OF_PERSONS_PER_HH,
                                                        hhRec.FAMILY_HOUSEHOLD_TYPE,
                                                        sa2name);
                    household.addFamily(primaryFamilyUnitsList.remove(0));
                    household.getFamilies().get(0).setType(hhRec.getPrimaryFamilyType());
                    familyHouseholds.add(household);
                    formed++;
                }

                Map<FamilyHouseholdType, List<Household>> tempHouseholds = basicHouseholds.computeIfAbsent(hhRec.NUM_OF_PERSONS_PER_HH,
                                                                                                           v -> {
                                                                                                               return new HashMap<>();
                                                                                                           });
                List<Household> result = tempHouseholds.putIfAbsent(hhRec.FAMILY_HOUSEHOLD_TYPE, familyHouseholds);
                if (result != null) {
                    throw new UnsupportedOperationException(hhRec.FAMILY_HOUSEHOLD_TYPE + " already exists in the households map");
                }

                Log.info(hhRec.NUM_OF_PERSONS_PER_HH+" "+hhRec.FAMILY_HOUSEHOLD_TYPE + ": formed households: " + formed);
            }
        }
        Log.info("All households populated with primary families");
        return basicHouseholds;
    }


    //    private void addNonPrimaryFamiliesToMultiFamilyHousehold(List<Household> multiFamilyHouseholdWith1Family) {
    //
    //        String logPrefix = "Multi-family households: ";
    //        List<FamilyType> threeOrMoreMember = new ArrayList<>(Arrays.asList(FamilyType.COUPLE_WITH_CHILDREN,
    //                                                                           FamilyType.COUPLE_ONLY,
    //                                                                           FamilyType.OTHER_FAMILY,
    //                                                                           FamilyType.ONE_PARENT));
    //        List<FamilyType> twoMember = new ArrayList<>(Arrays.asList(FamilyType.COUPLE_ONLY,
    //                                                                   FamilyType.OTHER_FAMILY,
    //                                                                   FamilyType.ONE_PARENT));
    //        int twoFamilyHhs = 0, threeFamilyHhs = 0;
    //        // multiFamilyHouseholdWith1Family list has Other and CoupleOnly families at the top. So, we will be forming
    //        // them first
    //        for (Household household : multiFamilyHouseholdWith1Family) {
    //            Family firstFamily = household.getFamilies().get(0);
    //            FamilyType firstFamilyType = firstFamily.getType();
    //            List<Person> firstFamilyNewMembers = new ArrayList<>();
    //            Family secondFamily = null;
    //            FamilyType secondFamilyType = null;
    //            List<Person> secondFamilyNewMembers = new ArrayList<>();
    //            Family thirdFamily = null;
    //            FamilyType thirdFamilyType = null;
    //            List<Person> thirdFamilyNewMembers = new ArrayList<>();
    //
    //            int neededMembers = 0;
    //
    //            if (threeOrMoreMember.isEmpty() & twoMember.isEmpty()) {
    //                break;
    //            }
    //
    //
    //            if (household.TARGETFAMLYCOUNT == 2) {
    //                // Select secondary family for 2 family household
    //                secondFamilyType = selectFamilyType(household, FamilyType.UNDEFINED);
    //                if (secondFamilyType == null) {
    //                    Log.warn(logPrefix + "Second family type selection failed: First family: " + firstFamilyType +
    //                                     " Second family: null");
    //                    throw new Error("Second family type null");
    //                }
    //                secondFamily = getNewFamily(secondFamilyType);
    //                if (secondFamily.getMembers().isEmpty()) {
    //                    throw new Error("Second family is empty");
    //                }
    //
    //                neededMembers = household.TARGETSIZE - (household.currentSize() + secondFamily.size());
    //
    //            } else if (household.TARGETFAMLYCOUNT == 3) {
    //                // Select secondary family for 3 family household
    //                secondFamilyType = selectFamilyType(household, FamilyType.UNDEFINED);
    //                if (secondFamilyType == null) {
    //                    Log.warn(logPrefix + "Second family type selection failed: First family: " + firstFamilyType +
    //                                     "Second family: null");
    //                    throw new Error("Second family type null");
    //                }
    //                secondFamily = getNewFamily(secondFamilyType);
    //                if (secondFamily.getMembers().isEmpty()) {
    //                    throw new Error("Second family is empty");
    //                }
    //
    //                // Select tertiary family for 3 family household
    //                thirdFamilyType = selectFamilyType(household, secondFamilyType);
    //                if (thirdFamilyType == null) {
    //                    Log.warn(logPrefix + "Third family type selection failed: First family: " + firstFamilyType +
    //                                     "Second family: " + secondFamilyType + "Third family: null");
    //                    throw new Error("Third family type null");
    //                }
    //
    //                thirdFamily = getNewFamily(thirdFamilyType);
    //                if (thirdFamily.getMembers().isEmpty()) {
    //                    throw new Error("Third family is empty");
    //                }
    //                neededMembers = household.TARGETSIZE - (household.currentSize() + secondFamily.size() + thirdFamily
    //                        .size());
    //
    //            } else {
    //                throw new Error(logPrefix + "Unexpected number of families: " + household.TARGETFAMLYCOUNT);
    //            }
    //
    //            if (neededMembers != 0) {
    //                // All needed members goes into first family. Second family only has minimum required persons
    //                if (firstFamilyType == FamilyType.COUPLE_WITH_CHILDREN | firstFamilyType == FamilyType.ONE_PARENT) {
    //                    int relativeMembers = 0;
    //                    if (relatives.size() > 0 & neededMembers > 2) { // We don't want too many
    //                        // relatives from Extra agents
    //                        relativeMembers = 1;
    //                    }
    //                    int childMembers = neededMembers - relativeMembers;
    //
    //                    if (children.size() + relatives.size() > neededMembers) {
    //                        // IF children.size + relative.size > neededMembers AND childMembers + relativeMembers ==
    //                        // neededMembers
    //                        // THEN children.size >= childMembers OR relative.size >= relativeMembers
    //                        //
    //                        // Since we know sum of relatives and children is larger than neededMembers, only 1 of the below
    //                        // 2 conditions is True at a time. i.e. if one array is smaller than our liking then the other
    //                        // is sure to be large enough to get enough number of persons.
    //
    //                        if (children.size() < childMembers) {
    //                            childMembers = children.size();
    //                            relativeMembers = neededMembers - childMembers;
    //                        }
    //                        if (relatives.size() < relativeMembers) {
    //                            relativeMembers = relatives.size();
    //                            childMembers = neededMembers - relativeMembers;
    //                        }
    //                        firstFamilyNewMembers.addAll(children.subList(0, childMembers));
    //                        children.subList(0, childMembers).clear();
    //                        firstFamilyNewMembers.addAll(relatives.subList(0, relativeMembers));
    //                        relatives.subList(0, relativeMembers).clear();
    //                    } else {
    //                        fillChildrenAndRelativesUsingExtras(childMembers,
    //                                                            relativeMembers,
    //                                                            firstFamilyNewMembers,
    //                                                            logPrefix);
    //                    }
    //                } else { // First family is a couple with no children or other family. They only can have relatives
    //                    if (secondFamilyType == FamilyType.ONE_PARENT) { // But if second family is a lone parent family
    //                        // they can have
    //                        // children
    //
    //                        int relativeMembers = (neededMembers > 3) ? neededMembers - 1 : neededMembers; // Don't want
    //                        // too many children in second
    //                        // family
    //                        int childMembers = neededMembers - relativeMembers;
    //
    //                        if (children.size() + relatives.size() > neededMembers) {
    //                            if (children.size() < childMembers) {
    //                                childMembers = children.size();
    //                                relativeMembers = neededMembers - childMembers;
    //                            }
    //                            if (relatives.size() < relativeMembers) {
    //                                relativeMembers = relatives.size();
    //                                childMembers = neededMembers - relativeMembers;
    //                            }
    //
    //                            secondFamilyNewMembers.addAll(children.subList(0, childMembers)); // Children in second
    //                            // family
    //                            children.subList(0, childMembers).clear();
    //                            firstFamilyNewMembers.addAll(relatives.subList(0, relativeMembers)); // Relatives to
    //                            // first family
    //                            relatives.subList(0, relativeMembers).clear();
    //
    //                        } else {
    //                            fillChildrenAndRelativesUsingExtras(childMembers, 0, secondFamilyNewMembers, logPrefix);
    //                            fillChildrenAndRelativesUsingExtras(0, relativeMembers, firstFamilyNewMembers, logPrefix);
    //                        }
    //                    } else if (thirdFamilyType == FamilyType.ONE_PARENT) {
    //                        int relativeMembers = (neededMembers > 3) ? neededMembers - 1 : neededMembers; // Don't want
    //                        // too many children in third
    //                        int childMembers = neededMembers - relativeMembers;
    //
    //                        if (children.size() + relatives.size() > neededMembers) {
    //                            if (children.size() < childMembers) {
    //                                childMembers = children.size();
    //                                relativeMembers = neededMembers - childMembers;
    //                            }
    //                            if (relatives.size() < relativeMembers) {
    //                                relativeMembers = relatives.size();
    //                                childMembers = neededMembers - relativeMembers;
    //                            }
    //                            thirdFamilyNewMembers.addAll(children.subList(0, childMembers)); // Children in third family
    //                            children.subList(0, childMembers).clear();
    //                            firstFamilyNewMembers.addAll(relatives.subList(0, relativeMembers)); // Relatives to
    //                            // first family
    //                            relatives.subList(0, relativeMembers).clear();
    //
    //                        } else {
    //                            fillChildrenAndRelativesUsingExtras(childMembers, 0, thirdFamilyNewMembers, logPrefix);
    //                            fillChildrenAndRelativesUsingExtras(0, relativeMembers, firstFamilyNewMembers, logPrefix);
    //                        }
    //                    } else { // First, second and third family are either couple only or other family.
    //                        if (relatives.size() >= neededMembers) {
    //                            firstFamilyNewMembers.addAll(relatives.subList(0, neededMembers));
    //                            relatives.subList(0, neededMembers).clear();
    //                        } else {
    //                            fillChildrenAndRelativesUsingExtras(0, neededMembers, firstFamilyNewMembers, logPrefix);
    //                        }
    //                    }
    //                }
    //            }
    //
    //            int currentSize = 0;
    //            if (household.TARGETFAMLYCOUNT == 2) {
    //                twoFamilyHhs++;
    //                currentSize = firstFamily.size() + firstFamilyNewMembers.size() + secondFamily.size() +
    //                        secondFamilyNewMembers.size();
    //            } else if (household.TARGETFAMLYCOUNT == 3) {
    //                currentSize = firstFamily.size() + firstFamilyNewMembers.size() + secondFamily.size() +
    //                        secondFamilyNewMembers.size() + thirdFamily.size() + thirdFamilyNewMembers.size();
    //                threeFamilyHhs++;
    //            }
    //
    //            if (household.TARGETSIZE == currentSize) {
    //                /* household is complete */
    //                firstFamily.addMembers(firstFamilyNewMembers);
    //                secondFamily.addMembers(secondFamilyNewMembers);
    //                household.addFamily(secondFamily);
    //                if (household.TARGETFAMLYCOUNT == 3) {
    //                    thirdFamily.addMembers(thirdFamilyNewMembers);
    //                    household.addFamily(thirdFamily);
    //                }
    //
    //                if (household.TARGETFAMLYCOUNT == household.familyCount()) {
    //                    allHouseholds.add(household);
    //                }
    //            }
    //
    //        } // end of foreach household
    //        Log.info(logPrefix + "Two family households formed: " + twoFamilyHhs);
    //        Log.info(logPrefix + "Three family households formed: " + threeFamilyHhs);
    //        if (multiFamilyHouseholdWith1Family.size() == (twoFamilyHhs + threeFamilyHhs)) {
    //            Log.info(logPrefix + "All households created");
    //        }
    //
    //    }
    //
    //
    //    private void fillChildrenAndRelativesUsingExtras(int childMembers, int relativeMembers, List<Person>
    //            familyMembers, String logprefix) {
    //        int missingChildren = 0, missingRelatives = 0;
    //
    //        if (children.size() > childMembers) {
    //            familyMembers.addAll(children.subList(0, childMembers));
    //            children.subList(0, childMembers).clear();
    //        } else {
    //            missingChildren = childMembers - children.size();
    //            familyMembers.addAll(children);
    //            children.clear();
    //        }
    //        if (relatives.size() > relativeMembers) {
    //            familyMembers.addAll(relatives.subList(0, relativeMembers));
    //            relatives.subList(0, relativeMembers).clear();
    //        } else {
    //            missingRelatives = relativeMembers - relatives.size();
    //            familyMembers.addAll(relatives);
    //            relatives.clear();
    //        }
    //
    //        if (missingChildren + missingRelatives > 0) {
    //            if (extrasHander.remainingExtras() >= missingChildren + missingRelatives) {
    //                Log.info(logprefix + "Not enough children and/or relatives in main lists. Drawing persons from extras");
    //                for (int i = 0; i < (missingChildren + missingRelatives); i++) {
    //                    if (i < missingChildren) {
    //
    //                        familyMembers.add(extrasHander.getPersonFromExtras(RelationshipStatus.U15_CHILD,
    //                                                                           AgeRange.A0_14,
    //                                                                           Utils.getSexRandomly(random,
    //                                                                                                maleProbability)));
    //                    } else {
    //                        familyMembers.add(extrasHander.getPersonFromExtras(RelationshipStatus.RELATIVE,
    //                                                                           Utils.getRandomlyWithoutShuffling(
    //                                                                                   random,
    //                                                                                   Arrays.asList(AgeRange.values())
    //                                                                           ),
    //                                                                           Utils.getSexRandomly(random,
    //                                                                                                maleProbability)));
    //                    }
    //                }
    //            } else {
    //                Log.warn(logprefix + "Not enough extras to complete the household with children");
    //                if (relatives.size() >= missingChildren) {
    //                    Log.info(logprefix + "Completing the household by replacing required children with available " +
    //                                     "relatives");
    //                    familyMembers.addAll(relatives.subList(0, missingChildren));
    //                    relatives.subList(0, missingChildren).clear();
    //                } else {
    //                    Log.warn(logprefix + "Children: " + children.size() + " Relatives: " + relatives.size() + " " +
    //                                     "Extras: " + extrasHander.remainingExtras() + " Required: " + (missingChildren +
    //                            missingRelatives));
    //                    throw new Error(logprefix + "Cannot form more households. All extra persons consumed");
    //                }
    //            }
    //        }
    //    }
    //
    //    private Family getNewFamily(FamilyType newFamilyType) {
    //        Family newFamily = null;
    //        if (newFamilyType == FamilyType.COUPLE_WITH_CHILDREN) {
    //            newFamily = new Family(FamilyType.COUPLE_WITH_CHILDREN);
    //            newFamily.addMember(marriedMales.remove(0));
    //            newFamily.addMember(marriedFemales.remove(0));
    //            if (children.isEmpty()) {
    //                extrasHander.addMembersToFamilyFromExtras(newFamily, RelationshipStatus.U15_CHILD, 1);
    //            } else {
    //                newFamily.addMember(children.remove(0));
    //            }
    //        } else if (newFamilyType == FamilyType.COUPLE_ONLY) {
    //            newFamily = new Family(FamilyType.COUPLE_ONLY);
    //            newFamily.addMember(marriedMales.remove(0));
    //            newFamily.addMember(marriedFemales.remove(0));
    //        } else if (newFamilyType == FamilyType.ONE_PARENT) {
    //            newFamily = loneParentBasic.remove(0);
    //            newFamily.setType(FamilyType.ONE_PARENT);
    //        } else if (newFamilyType == FamilyType.OTHER_FAMILY) {
    //            newFamily = nonPrimaryOtherFamilies.remove(0);
    //            newFamily.setType(FamilyType.OTHER_FAMILY);
    //        } else if (newFamilyType == null) {
    //            Log.warn("Multi-family households: Family Type is null");
    //        } else {
    //            Log.warn("Multi-family households: Family Type selected for second family does not exist: " +
    //                             newFamilyType);
    //            throw new Error("Unrecognised family type: " + newFamilyType);
    //        }
    //        return newFamily;
    //    }
    //
    //
    //    private FamilyType selectFamilyType(Household household, FamilyType secondFamilyType) {
    //        List<FamilyType> threeOrMoreMember = new ArrayList<>(Arrays.asList(FamilyType.COUPLE_WITH_CHILDREN,
    //                                                                           FamilyType.COUPLE_ONLY,
    //                                                                           FamilyType.OTHER_FAMILY,
    //                                                                           FamilyType.ONE_PARENT));
    //        List<FamilyType> twoMember = new ArrayList<>(Arrays.asList(FamilyType.COUPLE_ONLY,
    //                                                                   FamilyType.OTHER_FAMILY,
    //                                                                   FamilyType.ONE_PARENT));
    //
    //        boolean canNextFamilyBeCplYsChld = household.getFamilies().get(0).getType() == FamilyType
    //                .COUPLE_WITH_CHILDREN && (household.TARGETSIZE - household.currentSize() - secondFamilyType.basicSize
    //                ()) >= 3;
    //
    //        FamilyType nextFamilyType = null;
    //
    //        if (children.isEmpty()) {
    //            threeOrMoreMember.remove(FamilyType.COUPLE_WITH_CHILDREN);
    //            twoMember.remove(FamilyType.COUPLE_WITH_CHILDREN);
    //        }
    //        if (marriedMales.isEmpty() | marriedFemales.isEmpty()) {
    //            threeOrMoreMember.remove(FamilyType.COUPLE_ONLY);
    //            twoMember.remove(FamilyType.COUPLE_ONLY);
    //        }
    //        if (nonPrimaryOtherFamilies.isEmpty()) {
    //            threeOrMoreMember.remove(FamilyType.OTHER_FAMILY);
    //            twoMember.remove(FamilyType.OTHER_FAMILY);
    //        }
    //        if (loneParentBasic.isEmpty()) {
    //            threeOrMoreMember.remove(FamilyType.ONE_PARENT);
    //            twoMember.remove(FamilyType.ONE_PARENT);
    //        }
    //
    //        if (canNextFamilyBeCplYsChld) {
    //            if (!threeOrMoreMember.isEmpty()) {
    //                nextFamilyType = threeOrMoreMember.get(random.nextInt(threeOrMoreMember.size()));
    //            } else {
    //                Log.warn("Multi-family households: Unable to form secondary or tertiary families. Aborting");
    //                return null;
    //            }
    //        } else if (household.TARGETSIZE - household.currentSize() >= 2) {
    //            if (!twoMember.isEmpty()) {
    //                nextFamilyType = twoMember.get(random.nextInt(twoMember.size()));
    //            } else {
    //                Log.warn("Multi-family households: All two member family structures consumed. Resorting to form " +
    //                                 "only Couple with children secondary and tertiary families");
    //                return null;
    //            }
    //
    //        } else {
    //            Log.warn("Multi-family households: Cannot form a secondary family with only 1 vacant slot for members: "
    //                             + "ABORTING! ");
    //            throw new Error("Multi-family household that has less than 2 persons in secondary family");
    //        }
    //        return nextFamilyType;
    //    }
    //
    //    private void formOtherFamily1FamilyHouseholds(List<HhRecord> hhRecs, List<Family> othrFamilyBasic) {
    //        String logprefix = "One family, Other Family: ";
    //        List<HhRecord> otherFmlyrec = DataReader.getHhsByFamilyType(hhRecs, FamilyHouseholdType.F1_OTHER_FAMILY);
    //        //Get one family other-family hh records
    //        List<Household> hhs = new ArrayList<>();
    //        int takenFromExtras = 0;
    //        int total1FOtherFamily = 0;
    //        for (HhRecord householdRecord : otherFmlyrec) {
    //            total1FOtherFamily += householdRecord.hhCount;
    //
    //            for (int i = 0; i < householdRecord.hhCount; i++) {
    //                if (othrFamilyBasic.isEmpty()) {
    //                    Log.errorAndExit(logprefix + "Not enough other family basic structures",
    //                                     GlobalConstants.ExitCode.UNDEF);
    //                }
    //
    //                Family family = othrFamilyBasic.get(0);
    //                if (householdRecord.numOfPersonsPerHh > 2) {
    //                    int remMems = householdRecord.numOfPersonsPerHh - family.size();
    //                    if (relatives.size() < remMems) {
    //                        int missing = remMems - relatives.size();
    //                        extrasHander.addMembersToFamilyFromExtras(family, RelationshipStatus.RELATIVE, missing);
    //                        takenFromExtras += missing;
    //                        //Add the ones we can get from relatives list
    //                        addMembersToFamily(family, relatives, relatives.size());
    //                    } else {
    //                        addMembersToFamily(family, relatives, remMems);
    //                    }
    //                }
    //                othrFamilyBasic.remove(0);
    //                family.setType(householdRecord.primaryFamilyType);
    //                Household h = new Household(householdRecord.numOfPersonsPerHh,
    //                                            householdRecord.familyCountPerHousehold,
    //                                            sa2name);
    //                h.addFamily(family);
    //                hhs.add(h);
    //            }
    //        }
    //        Log.info(logprefix + "formed households: " + hhs.size());
    //        if (takenFromExtras > 0) {
    //            Log.info(logprefix + "Number taken from extras as Relatives: " + takenFromExtras);
    //        }
    //        if (hhs.size() == total1FOtherFamily) {
    //            Log.info(logprefix + "All households created");
    //        }
    //        allHouseholds.addAll(hhs);
    //    }
    //
    //    private void formLoneParent1FamilyHouseholds(List<HhRecord> hhRecs) {
    //        String logprefix = "One family, Lone parent: ";
    //        List<HhRecord> lnparentrec = DataReader.getHhsByFamilyType(hhRecs, FamilyHouseholdType.F1_ONE_PARENT);
    //        // int neededMembers = 0;
    //        List<Household> loneParentHouseholds = new ArrayList<>();
    //        int totalLoneParenHhs = 0;
    //        for (HhRecord hhrec : lnparentrec) {
    //            if (loneParentBasic.isEmpty()) {
    //                continue;
    //            }
    //            totalLoneParenHhs += hhrec.hhCount;
    //            for (int i = 0; i < hhrec.hhCount; i++) {
    //                if (loneParentBasic.isEmpty()) {
    //                    Log.warn(logprefix + "Not enough lone parent basic structures");
    //                    break;
    //                }
    //                Family family = loneParentBasic.remove(0);
    //                family.setType(FamilyType.ONE_PARENT);
    //
    //                if (hhrec.numOfPersonsPerHh > family.size())
    //                    addChildrenAndRelativesToFamilyFromMainLists(family, hhrec.numOfPersonsPerHh);
    //
    //                if (hhrec.numOfPersonsPerHh > family.size()) {
    //                    int neededMembers = hhrec.numOfPersonsPerHh - family.size();
    //                    if (neededMembers > 0) { //We have exhausted all known children and relatives. So select from
    //                        // extras.
    //                        int relativesCount = Utils.tossCoinWithBias(random,
    //                                                                    relativeProbability) ? 1 : 0;
    //                        int childrenCount = neededMembers - relativesCount;
    //
    //                        extrasHander.addMembersToFamilyFromExtras(family, RelationshipStatus.RELATIVE, relativesCount);
    //                        //TODO: Child can be U15, STUDENT or O15.
    //                        extrasHander.addMembersToFamilyFromExtras(family, RelationshipStatus.U15_CHILD, childrenCount);
    //                    }
    //                }
    //                Household household = new Household(hhrec.numOfPersonsPerHh, hhrec.familyCountPerHousehold, sa2name);
    //                household.addFamily(family);
    //                //                loneParentHouseholds.add(household);
    //                allHouseholds.add(household);
    //            }
    //
    //        }
    //        Log.info("One family, Lone parent: formed households: " + loneParentHouseholds.size());
    //        if (loneParentHouseholds.size() == totalLoneParenHhs) {
    //            Log.info("One family, Lone parent: All households created");
    //        }
    //        //        allHouseholds.addAll(loneParentHouseholds);
    //    }
    //
    //    private int addChildrenAndRelativesToFamilyFromMainLists(Family family, int maxHhSize) {
    //        //We can complete the family using children or relatives. We randomly add a child or a relative
    //        //to the family. The while loop treats children and relatives as a one large virtual list. We
    //        //randomly pick an index and add the corresponding the child or relative to the family.
    //        int missingMembers = maxHhSize - family.size(), addCount = 0;
    //        while (missingMembers > 0 && !(children.isEmpty() && relatives.isEmpty())) {
    //            int randIndex = random.nextInt(relatives.size() + children.size());
    //            if (randIndex < relatives.size()) {
    //                family.addMember(relatives.remove(randIndex));
    //            } else {
    //                //Children section of the virtual list starts at the end of relatives list. So we have to
    //                //offset the index
    //                family.addMember(children.remove(randIndex - relatives.size()));
    //            }
    //            addCount++;
    //            missingMembers--;
    //        }
    //        return addCount;
    //    }
    //
    //    private void formCoupleWithChild1FamilyHouseholds(List<HhRecord> hhRecs, List<Family> cplYsChldPrimaryFamilies) {
    //        String logprefix = "One Family, Couple with children: ";
    //        List<HhRecord> cplYsChldrec = DataReader.getHhsByFamilyType(hhRecs,
    //                                                                    FamilyHouseholdType.F1_COUPLE_WITH_CHILDREN);
    //        List<Household> hhs = new ArrayList<>();
    //
    //        int unformed = 0;
    //        for (HhRecord householdRecord : cplYsChldrec) {
    //            if (cplYsChldPrimaryFamilies.isEmpty()) {
    //                unformed += householdRecord.hhCount;
    //                continue;
    //            }
    //            for (int i = 0; i < householdRecord.hhCount; i++) {
    //                if (cplYsChldPrimaryFamilies.isEmpty()) {
    //                    unformed += (householdRecord.hhCount - i);
    //                    Log.warn(logprefix + "Not enough Couple with children primary families");
    //                    break;
    //                }
    //
    //                Family family = cplYsChldPrimaryFamilies.get(0);
    //
    //                //We can complete the family using children or relatives. We randomly add a child or a relative
    //                //to the family. The while loop treats children and relatives as a one large virtual list. We
    //                //randomly pick an index and add the corresponding the child or relative to the family.
    //                int addedCount = addChildrenAndRelativesToFamilyFromMainLists(family,
    //                                                                              householdRecord.numOfPersonsPerHh);
    //
    //                int remMems = householdRecord.numOfPersonsPerHh - family.size();
    //                if (remMems > 0) { //We have exhausted all known children and relatives. So select from extras.
    //                    int relativesCount = Utils.tossCoinWithBias(random, relativeProbability) ? 1 : 0;
    //                    int childrenCount = remMems - relativesCount;
    //
    //                    extrasHander.addMembersToFamilyFromExtras(family, RelationshipStatus.RELATIVE, relativesCount);
    //                    //TODO: Child can come from either U15, STUDENT or O15
    //                    extrasHander.addMembersToFamilyFromExtras(family, RelationshipStatus.U15_CHILD, childrenCount);
    //                }
    //
    //                cplYsChldPrimaryFamilies.remove(0);
    //                family.setType(householdRecord.primaryFamilyType);
    //                Household h = new Household(householdRecord.numOfPersonsPerHh,
    //                                            householdRecord.familyCountPerHousehold,
    //                                            sa2name);
    //                h.addFamily(family);
    //                hhs.add(h);
    //            }
    //        }
    //        Log.info(logprefix + "formed households: " + hhs.size());
    //        if (unformed > 0) {
    //            Log.warn(logprefix + "unformed households: " + unformed);
    //        } else {
    //            Log.info(logprefix + "All households created");
    //        }
    //        allHouseholds.addAll(hhs);
    //    }
    //

    /**
     * Completes couple only 1 family households by adding relatives to the families that are larger than 2 members. If there is not enough relatives
     * Extras are converted to relatives. This method modifies the input lists.
     *
     * @param coupleOnly1FHhs The couple only one family households list
     * @param relatives       The list of relatives in the population
     */
    private void fillCoupleOnly1FamilyHouseholds(List<Household> coupleOnly1FHhs, List<Person> relatives) {
        String logprefix = "One Family, Couple only: ";
        Collections.shuffle(relatives, random);
        int formed = 0;

        for (Household hh : coupleOnly1FHhs) {
            int diff = hh.getExpectedSize() - hh.getCurrentSize();
            if (diff > 0) {
                Family f = hh.getFamilies().get(0);
                if (relatives.size() > diff) {
                    f.addMembers(relatives.subList(0, diff));
                    relatives.subList(0, diff).clear();
                } else {
                    extrasHander.addMembersToFamilyFromExtras(f, RelationshipStatus.RELATIVE, diff - relatives.size());
                }
                formed++;
            }

        }

        Log.info(FamilyHouseholdType.F1_COUPLE_ONLY + "formed households: " + formed);
        Log.info(logprefix + "All households created");
    }

    //    /**
    //     * Add a specified number of members to the family from a list of persons. The persons are selected
    //     * from the source list from top to bottom. After adding the members the members are removed from
    //     * the source list.
    //     *
    //     * @param family     The family instance to which members are added
    //     * @param sourceList The persons list from which members are selected
    //     * @param count      The number of persons to add
    //     */
    //    private void addMembersToFamily(Family family, List<Person> sourceList, int count) {
    //        List<Person> selected = sourceList.subList(0, count);
    //        family.addMembers(selected);
    //        selected.clear();
    //    }
    //
    //

    /**
     * Creates all lone person households and add them to the global list of households.
     *
     * @param hhRecs  Household data records
     * @param indRecs Individual data records.
     */
    private void makeLonePersonsHhs(List<HhRecord> hhRecs, List<IndRecord> indRecs) {
        List<HhRecord> lnPersonHhs = DataReader.getHhsByFamilyType(hhRecs, FamilyHouseholdType.LONE_PERSON);

        List<Person> allPersons = PersonsFactory.makeAllPersonsByRelationshipType(indRecs,
                                                                                  RelationshipStatus.LONE_PERSON);

        int hhCount = lnPersonHhs.get(0).hhCount;// Only 1 member households have lone persons
        int diff = hhCount - allPersons.size();

        List<Household> hhList = new ArrayList<>();

        for (int i = 0; i < lnPersonHhs.get(0).hhCount; i++) {
            Family f = new Family(FamilyType.LONE_PERSON);
            if (allPersons.isEmpty()) {
                throw new NotEnoughPersonsException(lnPersonHhs.get(0).FAMILY_HOUSEHOLD_TYPE + ": Not enough lone persons");
            }
            f.addMember(allPersons.remove(0));
            Household h = new Household(1, FamilyHouseholdType.LONE_PERSON, sa2name);
            h.addFamily(f);
            hhList.add(h);
        }
        allHouseholds.addAll(hhList);

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

    /**
     * Creates all group households and add them to the global list of households.
     *
     * @param hhRecs  Household data records
     * @param indRecs Individual data records.
     */
    private void makeGroupHouseholds(List<HhRecord> hhRecs, List<IndRecord> indRecs) {
        List<HhRecord> grpHhRecs = DataReader.getHhsByFamilyType(hhRecs, FamilyHouseholdType.GROUP_HOUSEHOLD);

        List<Person> grpMembers = PersonsFactory.makeAllPersonsByRelationshipType(indRecs,
                                                                                  RelationshipStatus.GROUP_HOUSEHOLD);

        List<Household> grpHhs = new ArrayList<>();
        int totalGroupHhs = 0;
        for (HhRecord hhRec : grpHhRecs) {

            int hhCount = hhRec.hhCount;
            totalGroupHhs += hhRec.hhCount;
            int hhSize = hhRec.NUM_OF_PERSONS_PER_HH;

            for (int i = 0; i < hhCount; i++) {
                Family f = new Family(FamilyType.GROUP_HOUSEHOLD);
                if (hhSize > grpMembers.size()) {
                    throw new NotEnoughPersonsException(hhRec.FAMILY_HOUSEHOLD_TYPE + ": Not enough group household persons");
                }
                f.addMembers(grpMembers.subList(0, hhSize));
                grpMembers.subList(0, hhSize).clear();

                Household h = new Household(hhRec.NUM_OF_PERSONS_PER_HH, FamilyHouseholdType.GROUP_HOUSEHOLD, sa2name);
                h.addFamily(f);

                grpHhs.add(h);
            }

        }
        Log.info("Group households: Households formed: " + grpHhs.size());
        Log.info("Group households: All Households created");
        if (!grpMembers.isEmpty()) {
            Log.warn("Group households: Discarded group household persons: " + grpMembers.size());
        }
        allHouseholds.addAll(grpHhs);
    }


}
