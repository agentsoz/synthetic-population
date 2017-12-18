package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.GlobalConstants;
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
    private List<Family> loneParentBasic = null, nonPrimaryOtherFamilies = null;
    private List<Person> relatives = null, loneParents = null, children = null, extras = null, marriedMales = null, marriedFemales = null;
    private List<HhRecord> hhrecs;
    private List<IndRecord> indrecs;
    private double sexRatio, relativeProbability, femaleLoneParentProbability;
    private FamilyConstructor familyConstuct;
    private PersonsConstructor personConstruct;

    public GroupMaker(double maleProbability, double relativeProbability, double femaleLoneParentProbability) {
        this.sexRatio = maleProbability;
        this.relativeProbability = relativeProbability;
        this.femaleLoneParentProbability = femaleLoneParentProbability;
    }

    List<Household> makePopulation(List<HhRecord> hhrecs, List<IndRecord> indrecs, Random rand, String sa2) {
        this.sa2name = sa2;
        this.hhrecs = hhrecs;
        this.indrecs = indrecs;

        // printHhSummary(hhrecs)
        // printIndSummary(indrecs);

        this.random = rand;
        extras = getExtras(hhrecs, indrecs);
        Log.info("Extras (difference between households and persons files): " + extras.size());
        makeLonePersonsHhs(hhrecs, indrecs);
        makeGroupHouseholds(hhrecs, indrecs);

        personConstruct = new PersonsConstructor();
//        marriedStructs = familyConstuct.makeAllMarriedCouples(hhrecs, indrecs);
        List<Person> married = personConstruct.makeAllPersonsByRelationshipType(indrecs, RelationshipStatus.Married);
        marriedMales = married.stream().filter(p -> p.getSex() == Sex.Male).collect(Collectors.toList());
        marriedFemales = married.stream().filter(p -> p.getSex() == Sex.Female).collect(Collectors.toList());
        relatives = personConstruct.makeAllPersonsByRelationshipType(indrecs, RelationshipStatus.Relative);
        loneParents = personConstruct.makeAllPersonsByRelationshipType(indrecs, RelationshipStatus.LoneParent);
        children = personConstruct.makeAllPersonsByRelationshipType(indrecs,
                                                                    RelationshipStatus.U15Child,
                                                                    RelationshipStatus.Student,
                                                                    RelationshipStatus.O15Child);

        familyConstuct = new FamilyConstructor(marriedMales, marriedFemales, children, relatives, rand);


        Log.debug("married males: " + married.size());
        Log.debug("relatives: " + relatives.size());
        Log.debug("children: " + children.size());
        Log.debug("Lone parents: " + loneParents.size());

        loneParentBasic = familyConstuct.makeAllBasicLoneParentStructs(loneParents);

        List<HhRecord> otherFamilyRecords = GroupingUtils.getHhsByFamilyType(hhrecs,
                                                                             FamilyHouseholdType.F1OTHERFAMILY,
                                                                             FamilyHouseholdType.F2OTHERFAMILY,
                                                                             FamilyHouseholdType.F3OTHERFAMILY);
        List<Family> primaryOtherFamiliesBasic = familyConstuct.makeAllPrimaryOtherFamilyBasicStructs(otherFamilyRecords);

        //Form basic family structs and removes couples from married males and females list
        List<HhRecord> coupleWChildRecords = GroupingUtils.getHhsByFamilyType(hhrecs,
                                                                              FamilyHouseholdType.F1COUPLEWITHCHILDREN,
                                                                              FamilyHouseholdType.F2COUPLEWITHCHILDREN,
                                                                              FamilyHouseholdType.F3COUPLEWITHCHILDREN);
        List<Family> primaryCoupleWChildFamilyBasic = familyConstuct.makePrimaryCoupleWithChildFamilyBasicStructs(
                coupleWChildRecords);

        //Form basic family structs and removes couples from marriedStructs list
        List<HhRecord> cplOnlyHhrecs = GroupingUtils.getHhsByFamilyType(hhrecs,
                                                                        FamilyHouseholdType.F1COUPLEONLY,
                                                                        FamilyHouseholdType.F2COUPLEONLY,
                                                                        FamilyHouseholdType.F3COUPLEONLY);
        List<Family> primaryCoupleOnlyFamilyBasic = familyConstuct.makePrimaryCoupleOnlyFamilyBasicStructs(hhrecs);

        Log.debug("Remaining married males: " + marriedMales.size());
        Log.debug("Remaining married females: " + marriedFemales.size());
        Log.debug("Remaining relatives: " + relatives.size());
        Log.debug("Remaining children: " + children.size());
        Log.debug("Remaining lone parents: " + loneParents.size());

        try {
            formOtherFamily1FamilyHouseholds(hhrecs, primaryOtherFamiliesBasic);
            Log.debug("Remaining primary other family basic structures: " + primaryOtherFamiliesBasic.size());
            Log.debug("Remaining relatives: " + relatives.size());
            Log.debug("Remaining extras: " + extras.size());

            formCoupleOnly1FamilyHouseholds(hhrecs, primaryCoupleOnlyFamilyBasic);
            Log.debug("Remaining primary couple only family basic structures: " + primaryCoupleOnlyFamilyBasic.size());
            Log.debug("Remaining married males (not expected to change): " + marriedMales.size());
            Log.debug("Remaining married females (not expected to change): " + marriedFemales.size());
            Log.debug("Remaining relatives: " + relatives.size());
            Log.debug("Remaining extras: " + extras.size());

            formCoupleWithChild1FamilyHouseholds(hhrecs, primaryCoupleWChildFamilyBasic);
            Log.debug("Remaining primary couple w/ children basic structures: " + primaryCoupleWChildFamilyBasic.size());
            Log.debug("Remaining children: " + children.size());
            Log.debug("Remaining relatives: " + relatives.size());
            Log.debug("Remaining extras: " + extras.size());

            formLoneParent1FamilyHouseholds(hhrecs);
            Log.debug("Remaining lone parent basic structures: " + loneParentBasic.size());
            Log.debug("Remaining children: " + children.size());
            Log.debug("Remaining relatives: " + relatives.size());
            Log.debug("Remaining extras: " + extras.size());

            List<Household> multiFamilyHhWith1Family = formPrimaryFamiliesForMultiFamilyHouseholds(hhrecs,
                                                                                                   primaryCoupleOnlyFamilyBasic,
                                                                                                   primaryCoupleWChildFamilyBasic,
                                                                                                   loneParentBasic,
                                                                                                   primaryOtherFamiliesBasic);

            formBasicStructuresFor2ndAnd3rdFamiliesInMultiFamilyHouseholds(hhrecs, indrecs);
            Log.debug("Remaining married males: " + marriedMales.size());
            Log.debug("Remaining married females: " + marriedFemales.size());
            Log.debug("relatives: " + relatives.size());
            Log.debug("children: " + children.size());
            Log.debug("Lone parent: " + loneParentBasic.size());
            Log.debug("Other family: " + primaryOtherFamiliesBasic.size());
            Log.debug("Non primary other families: " + nonPrimaryOtherFamilies.size());
            Log.debug("Extras: " + extras.size());

            addNonPrimaryFamiliesToMultiFamilyHousehold(multiFamilyHhWith1Family);
        } catch (Exception e) {
            Log.error("Error in populatin construction", e);
        } finally {


            Log.debug("------------ Discarded persons and family structures -----------");
            Log.debug("primary couple with child basic structures: " + primaryCoupleWChildFamilyBasic.size());
            Log.debug("primary couple only: " + primaryCoupleOnlyFamilyBasic.size());
            Log.debug("primary other family: " + primaryOtherFamiliesBasic.size());
            Log.debug("basic lone parents: " + loneParentBasic.size());
            Log.debug("Remaining married males: " + marriedMales.size());
            Log.debug("Remaining married females: " + marriedFemales.size());
            Log.debug("relatives: " + relatives.size());
            Log.debug("lone parents: " + loneParents.size());
            Log.debug("children: " + children.size());
            Log.debug("Extras: " + extras.size());
            Log.debug("All formed households: " + allHouseholds.size());

//            Utils.printHhSummary(hhrecs);
        }
        Utils.summary(allHouseholds);
        return allHouseholds;
    }

    private void formBasicStructuresFor2ndAnd3rdFamiliesInMultiFamilyHouseholds(List<HhRecord> householdRecords, List<IndRecord> individualRecords) {
        String logprefix = "Basic structures for non-primary families in multi-family households: ";
        int familiesWithMarriedCouples = 0, loneParentFamilies = 0, otherFamilies = 0;

        List<HhRecord> f2Households = GroupingUtils.getHhsByFamilyType(householdRecords,
                                                                       FamilyHouseholdType.F2ONEPARENT,
                                                                       FamilyHouseholdType.F2COUPLEWITHCHILDREN,
                                                                       FamilyHouseholdType.F2COUPLEONLY,
                                                                       FamilyHouseholdType.F2OTHERFAMILY);
        int f2secondcount = 0;
        for (HhRecord hhRecord : f2Households) {
            if (hhRecord.primaryFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN | hhRecord.primaryFamilyType == FamilyType.COUPLEONLY) {
                familiesWithMarriedCouples += hhRecord.hhCount;
            } else if (hhRecord.primaryFamilyType == FamilyType.ONEPARENT) {
                loneParentFamilies += hhRecord.hhCount;
            } else if (hhRecord.primaryFamilyType == FamilyType.OTHERFAMILY) {
                otherFamilies += hhRecord.hhCount;
            }
            f2secondcount += hhRecord.hhCount;
        }
        List<HhRecord> f3Households = GroupingUtils.getHhsByFamilyType(householdRecords,
                                                                       FamilyHouseholdType.F3ONEPARENT,
                                                                       FamilyHouseholdType.F3COUPLEWITHCHILDREN,
                                                                       FamilyHouseholdType.F3COUPLEONLY,
                                                                       FamilyHouseholdType.F3OTHERFAMILY);
        int f3secondcount = 0, f3thirdcount = 0;
        for (HhRecord hhRecord : f3Households) {
            if (hhRecord.primaryFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN | hhRecord.primaryFamilyType == FamilyType.COUPLEONLY) {
                familiesWithMarriedCouples += hhRecord.hhCount;
            } else if (hhRecord.primaryFamilyType == FamilyType.ONEPARENT) {
                loneParentFamilies += hhRecord.hhCount;
            } else if (hhRecord.primaryFamilyType == FamilyType.OTHERFAMILY) {
                otherFamilies += hhRecord.hhCount;
            }
            f3secondcount += hhRecord.hhCount;
            f3thirdcount += hhRecord.hhCount;
        }

        List<HhRecord> f1Households = GroupingUtils.getHhsByFamilyType(householdRecords,
                                                                       FamilyHouseholdType.F1ONEPARENT,
                                                                       FamilyHouseholdType.F1COUPLEWITHCHILDREN,
                                                                       FamilyHouseholdType.F1COUPLEONLY,
                                                                       FamilyHouseholdType.F1OTHERFAMILY);
        for (HhRecord hhRecord : f1Households) {
            if (hhRecord.primaryFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN | hhRecord.primaryFamilyType == FamilyType.COUPLEONLY) {
                familiesWithMarriedCouples += hhRecord.hhCount;
            } else if (hhRecord.primaryFamilyType == FamilyType.ONEPARENT) {
                loneParentFamilies += hhRecord.hhCount;
            } else if (hhRecord.primaryFamilyType == FamilyType.OTHERFAMILY) {
                otherFamilies += hhRecord.hhCount;
            }

        }

        int totalRequiredNonPrimaryFamilies = f2secondcount + f3secondcount + f3thirdcount;
        int creatableNonPrimaryOtherFamiliesWithExistingRelatives = relatives.size() / 2;
        int totalAvailableBasicStructs = marriedStructs.size() + loneParentBasic.size() + creatableNonPrimaryOtherFamiliesWithExistingRelatives;
        int newNonPrimaryMarriedCpls = 0, newNonPrimaryLoneParents = 0, newNonPrimaryOtherFamilies = 0;


        if (totalAvailableBasicStructs < totalRequiredNonPrimaryFamilies) {

            int extraNonPrimaryFamiliesToCreate = totalRequiredNonPrimaryFamilies - totalAvailableBasicStructs;
            //We know the total number of extra families we want. But we don't know how many from each family type.
            //So we divide the number among the family types according to distribution we observed in the population.
            int totalPrimaryFamilyBasicStructs = familiesWithMarriedCouples + loneParentFamilies + otherFamilies;
            newNonPrimaryMarriedCpls = Math
                    .round((familiesWithMarriedCouples / (float) totalPrimaryFamilyBasicStructs) * extraNonPrimaryFamiliesToCreate);
            newNonPrimaryLoneParents = Math.round((loneParentFamilies / (float) totalPrimaryFamilyBasicStructs) * extraNonPrimaryFamiliesToCreate);
            newNonPrimaryOtherFamilies = extraNonPrimaryFamiliesToCreate - (newNonPrimaryLoneParents + newNonPrimaryMarriedCpls);
            newNonPrimaryOtherFamilies += creatableNonPrimaryOtherFamiliesWithExistingRelatives;

            List<AgeRange> marriedAges = new ArrayList<>(Arrays.asList(AgeRange.A25_39,
                                                                       AgeRange.A40_54,
                                                                       AgeRange.A55_69,
                                                                       AgeRange.A70_84));
            for (int i = 0; i < newNonPrimaryMarriedCpls; i++) {
                Collections.shuffle(marriedAges, random);
                Person male = extras.remove(0);
                male.setSex(Sex.Male);
                male.setType(RelationshipStatus.Married);
                male.setAgeCat(marriedAges.get(0));

                Person female = extras.remove(0);
                female.setSex(Sex.Female);
                female.setType(RelationshipStatus.Married);
                female.setAgeCat(marriedAges.get(0));

                Family family = new Family(FamilyType.BASIC);
                family.addMember(male);
                family.addMember(female);
                marriedStructs.add(family);
            }
            Log.info(logprefix + " new marriedStructs couples: " + newNonPrimaryMarriedCpls);

            List<AgeRange> loneParentAges = new ArrayList<>(Arrays.asList(AgeRange.A25_39,
                                                                          AgeRange.A40_54,
                                                                          AgeRange.A55_69));
            for (int i = 0; i < newNonPrimaryLoneParents; i++) {
                Person child = null;
                if (children.size() != 0) {
                    child = children.remove(0);
                }

                Person parent = extras.remove(0);
                parent.setSex(Utils.selectTrueOrFalseRandomlyWithBias(random,
                                                                      femaleLoneParentProbability) ? Sex.Female : Sex.Male);
                parent.setType(RelationshipStatus.LoneParent);
                List<AgeRange> parentAges = new ArrayList<>();
                if (child != null) {
                    for (int j = 0; j < AgeRange.values().length; j++) {
                        if (child.getAgeCat().max() < AgeRange.values()[j].min()) {
                            parentAges.add(AgeRange.values()[j]);
                            if (parentAges.size() == 2) {
                                if (child.getAgeCat() == AgeRange.A0_14) {
                                    parentAges.add(AgeRange.A40_54);
                                }
                                break;
                            }
                        }
                    }
                    Collections.shuffle(parentAges, random);
                    parent.setAgeCat(parentAges.get(0));
                } else {
                    Collections.shuffle(loneParentAges, random);
                    parent.setAgeCat(loneParentAges.get(0));
                }

                if (child == null) {
                    child = extras.remove(0);
                    child.setSex(Utils.selectTrueOrFalseRandomlyWithBias(random, sexRatio) ? Sex.Male : Sex.Female);
                    List<AgeRange> childages = new ArrayList<>();
                    for (int j = 0; j < AgeRange.values().length; j++) {
                        if (parent.getAgeCat().min() > AgeRange.values()[j].max() & !AgeRange.values()[j].isEmpty()) {
                            childages.add(AgeRange.values()[j]);
                        }
                    }
                    Collections.shuffle(childages, random);
                    child.setAgeCat(childages.get(0));
                    if (childages.get(0).max() == 14) {
                        child.setType(RelationshipStatus.U15Child);
                    } else {
                        child.setType(RelationshipStatus.Student);
                    }
                }
                Family family = new Family(FamilyType.BASIC);
                family.addMember(parent);
                family.addMember(child);

                loneParentBasic.add(family);
            }
            Log.info(logprefix + " lone parent basic: " + newNonPrimaryLoneParents);
        } else {
            // At this stage we can form all requiredNonPromaryOtherFamilies from existing relatives
            newNonPrimaryOtherFamilies = totalRequiredNonPrimaryFamilies - (marriedStructs.size() + loneParentBasic.size());
        }


        List<AgeRange> agesAll = new ArrayList<>(Arrays.asList(AgeRange.values()));
        nonPrimaryOtherFamilies = new ArrayList<>();
        for (int i = 0; i < newNonPrimaryOtherFamilies; i++) {
            Family family = new Family(FamilyType.OTHERFAMILY);
            Person rel1, rel2;
            if (relatives.size() < 2) {
                Collections.shuffle(agesAll, random);
                rel1 = extras.remove(0);
                rel1.setSex(Utils.selectTrueOrFalseRandomlyWithBias(random, sexRatio) ? Sex.Male : Sex.Female);
                rel1.setType(RelationshipStatus.Relative);
                rel1.setAgeCat(agesAll.get(0));

                rel2 = extras.remove(0);
                rel2.setSex(Utils.selectTrueOrFalseRandomlyWithBias(random, sexRatio) ? Sex.Male : Sex.Female);
                rel2.setType(RelationshipStatus.Relative);
                if (rel1.getAgeCat() == AgeRange.A0_14) {
                    Collections.shuffle(agesAll, random);
                    rel2.setAgeCat(agesAll.get(0));
                } else {
                    Collections.shuffle(agesAll, random);
                    rel2.setAgeCat(agesAll.get(0));
                }
            } else {
                rel1 = relatives.remove(0);
                rel2 = relatives.remove(0);
            }
            family.addMember(rel1);
            family.addMember(rel2);
            nonPrimaryOtherFamilies.add(family);
        }

        Log.info(logprefix + " other family basic: " + newNonPrimaryOtherFamilies);
    }

    private List<Person> getExtras(List<HhRecord> hhrecs, List<IndRecord> indrecs) {
        int personsInHh = 0;
        int personsInInds = 0;
        List<Person> extras = new ArrayList<>();
        for (HhRecord hhrec : hhrecs) {
            personsInHh += (hhrec.hhCount * hhrec.numOfPersonsPerHh);
        }
        for (IndRecord inrec : indrecs) {
            personsInInds += inrec.indCount;
        }

        int extraPersons = personsInHh > personsInInds ? (personsInHh - personsInInds) : 0;
        for (int i = 0; i < extraPersons + 100; i++) {
            extras.add(new Person());
        }
        return extras;
    }

    // List<Person> drawFromExtras(RelationshipStatus relationshipStatus, int count) {
    // List<Person> selected = extras.subList(0, count);
    // for (Person person : selected) {
    // person.setType(relationshipStatus;
    // }
    // extras.subList(0, count).clear();
    // return selected;
    // }


    private List<Household> addPrimaryFamilytoMultiFamily(List<HhRecord> hhrecs, List<Family> primaryFamilies, String logprefix, FamilyHouseholdType familyHouseholdType) {
        List<HhRecord> selectedHouseholdRecords = GroupingUtils.getHhsByFamilyType(hhrecs, familyHouseholdType);
        List<Household> partiallyFormedMultiFamilyHouseholds = new ArrayList<>();
        int formed = 0, unformed = 0;
        for (HhRecord householdRecord : selectedHouseholdRecords) {
            if (primaryFamilies.isEmpty()) {
                unformed += householdRecord.hhCount;
                continue;
            }
            for (int i = 0; i < householdRecord.hhCount; i++) {
                if (primaryFamilies.isEmpty()) {
                    Log.warn(logprefix + ": Not enough couple only primary families ");
                    unformed += (householdRecord.hhCount - i);
                    break;
                }
                Household household = new Household(householdRecord.numOfPersonsPerHh,
                                                    householdRecord.familyCountPerHousehold,
                                                    sa2name);
                household.addFamily(primaryFamilies.remove(0));
                household.getFamilies().get(0).setType(householdRecord.primaryFamilyType);
                partiallyFormedMultiFamilyHouseholds.add(household);
                formed++;
            }
        }
        Log.info(logprefix + ": formed primary families: " + formed);
        if (unformed > 0) {
            Log.warn(logprefix + ": unformed primary families: " + unformed);
        } else {
            Log.info(logprefix + ": All primary families of households created");
        }
        return partiallyFormedMultiFamilyHouseholds;
    }

    private List<Household> formPrimaryFamiliesForMultiFamilyHouseholds(List<HhRecord> hhrecs, List<Family> cplOnlyPrimaryFamilies,
                                                                        List<Family> cplYsChldPrimaryFamilies, List<Family> loneParentFamilies, List<Family> primaryOtherFamilies) {
        List<Household> multiFamilyHhs = null;
        List<Household> tempHhWith1Family;

        multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs,
                                                       cplOnlyPrimaryFamilies,
                                                       "Two family, Couple only households",
                                                       FamilyHouseholdType.F2COUPLEONLY);
        tempHhWith1Family = new ArrayList<>(multiFamilyHhs);
        multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs,
                                                       cplOnlyPrimaryFamilies,
                                                       "Three family, Couple only households",
                                                       FamilyHouseholdType.F3COUPLEONLY);
        tempHhWith1Family.addAll(multiFamilyHhs);
        multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs,
                                                       primaryOtherFamilies,
                                                       "Two family, Other family households",
                                                       FamilyHouseholdType.F2OTHERFAMILY);
        tempHhWith1Family.addAll(multiFamilyHhs);
        multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs,
                                                       primaryOtherFamilies,
                                                       "Three family, Other family households",
                                                       FamilyHouseholdType.F3OTHERFAMILY);
        tempHhWith1Family.addAll(multiFamilyHhs);
        multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs,
                                                       cplYsChldPrimaryFamilies,
                                                       "Two family, Couple with children households",
                                                       FamilyHouseholdType.F2COUPLEWITHCHILDREN);
        tempHhWith1Family.addAll(multiFamilyHhs);
        multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs,
                                                       cplYsChldPrimaryFamilies,
                                                       "Three family, Couple with children households",
                                                       FamilyHouseholdType.F3COUPLEWITHCHILDREN);
        tempHhWith1Family.addAll(multiFamilyHhs);
        multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs,
                                                       loneParentFamilies,
                                                       "Two family, Lone parent households",
                                                       FamilyHouseholdType.F2ONEPARENT);
        tempHhWith1Family.addAll(multiFamilyHhs);
        multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs,
                                                       loneParentFamilies,
                                                       "Three family, Lone parent households",
                                                       FamilyHouseholdType.F3ONEPARENT);
        tempHhWith1Family.addAll(multiFamilyHhs);
        return tempHhWith1Family;

    }


    private void addNonPrimaryFamiliesToMultiFamilyHousehold(List<Household> multiFamilyHouseholdWith1Family) {

        String logprefix = "Multi-family househods: ";
        List<FamilyType> threeOrMoreMember = new ArrayList<>(
                Arrays.asList(FamilyType.COUPLEFAMILYWITHCHILDREN,
                              FamilyType.COUPLEONLY,
                              FamilyType.OTHERFAMILY,
                              FamilyType.ONEPARENT));
        List<FamilyType> twoMember = new ArrayList<>(Arrays.asList(FamilyType.COUPLEONLY,
                                                                   FamilyType.OTHERFAMILY,
                                                                   FamilyType.ONEPARENT));
        int twoFamilyHhs = 0, threeFamilyHhs = 0;
        // multiFamilyHouseholdWith1Family list has Other and CoupleOnly families at the top. So, we will be forming them first
        for (Household household : multiFamilyHouseholdWith1Family) {
            Family firstFamily = household.getFamilies().get(0);
            FamilyType firstFamilyType = firstFamily.getType();
            List<Person> firstFamilyNewMembers = new ArrayList<>();
            Family secondFamily = null;
            FamilyType secondFamilyType = null;
            List<Person> secondFamilyNewMembers = new ArrayList<>();
            Family thirdFamily = null;
            FamilyType thirdFamilyType = null;
            List<Person> thirdFamilyNewMembers = new ArrayList<>();

            int neededMembers = 0;

            if (threeOrMoreMember.isEmpty() & twoMember.isEmpty()) {
                break;
            }


            if (household.TARGETFAMLYCOUNT == 2) {
                // Select secondary family for 2 family household
                secondFamilyType = selectFamilyType(household, FamilyType.UNDEFINED);
                if (secondFamilyType == null) {
                    Log.warn(logprefix + "Second family type selection failed: First family: " + firstFamilyType
                                     + "Second family: null");
                    throw new Error("Second family type null");
                }
                secondFamily = getNewFamily(secondFamilyType);
                if (secondFamily.getMembers().isEmpty()) {
                    throw new Error("Second family is empty");
                }

                neededMembers = household.TARGETSIZE - (household.currentSize() + secondFamily.size());

            } else if (household.TARGETFAMLYCOUNT == 3) {
                // Select secondary family for 3 family household
                secondFamilyType = selectFamilyType(household, FamilyType.UNDEFINED);
                if (secondFamilyType == null) {
                    Log.warn(logprefix + "Second family type selection failed: First family: " + firstFamilyType
                                     + "Second family: null");
                    throw new Error("Second family type null");
                }
                secondFamily = getNewFamily(secondFamilyType);
                if (secondFamily.getMembers().isEmpty()) {
                    throw new Error("Second family is empty");
                }

                // Select tertiary family for 3 family household
                thirdFamilyType = selectFamilyType(household, secondFamilyType);
                if (thirdFamilyType == null) {
                    Log.warn(logprefix + "Third family type selection failed: First family: " + firstFamilyType
                                     + "Second family: " + secondFamilyType + "Third family: null");
                    throw new Error("Third family type null");
                }

                thirdFamily = getNewFamily(thirdFamilyType);
                if (thirdFamily.getMembers().isEmpty()) {
                    throw new Error("Third family is empty");
                }
                neededMembers = household.TARGETSIZE - (household.currentSize() + secondFamily.size() + thirdFamily.size());

            } else {
                throw new Error(logprefix + "Unexpected number of families: " + household.TARGETFAMLYCOUNT);
            }

            if (neededMembers != 0) {
                // All needed members goes into first family. Second family only has minimum required persons
                if (firstFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN | firstFamilyType == FamilyType.ONEPARENT) {
                    int relativeMembers = 0;
                    if (relatives.size() > 0 & neededMembers > 2) { // We don't want too many
                        // relatives from
                        // Extra agents
                        relativeMembers = 1;
                    }
                    int childMembers = neededMembers - relativeMembers;

                    if (children.size() + relatives.size() > neededMembers) {
                        // IF children.size + relative.size > neededMembers AND childMembers + relativeMembers == neededMembers
                        // THEN children.size >= childMembers OR relative.size >= relativeMembers
                        //
                        // Since we know sum of relatives and children is larger than neededMembers, only 1 of the below
                        // 2 conditions is True at a time. i.e. if one array is smaller than our liking then the other
                        // is sure to be large enough to get enough number of persons.

                        if (children.size() < childMembers) {
                            childMembers = children.size();
                            relativeMembers = neededMembers - childMembers;
                        }
                        if (relatives.size() < relativeMembers) {
                            relativeMembers = relatives.size();
                            childMembers = neededMembers - relativeMembers;
                        }
                        firstFamilyNewMembers.addAll(children.subList(0, childMembers));
                        children.subList(0, childMembers).clear();
                        firstFamilyNewMembers.addAll(relatives.subList(0, relativeMembers));
                        relatives.subList(0, relativeMembers).clear();
                    } else {
                        fillChildrenAndRelativesUsingExtras(childMembers,
                                                            relativeMembers,
                                                            firstFamilyNewMembers,
                                                            logprefix);
                    }
                } else { // First family is a couple with no children or other family. They only can have relatives
                    if (secondFamilyType == FamilyType.ONEPARENT) { // But if second family is a lone parent family they can have
                        // children

                        int relativeMembers = (neededMembers > 3) ? neededMembers - 1 : neededMembers; // Don't want too many children in second
                        // family
                        int childMembers = neededMembers - relativeMembers;

                        if (children.size() + relatives.size() > neededMembers) {
                            if (children.size() < childMembers) {
                                childMembers = children.size();
                                relativeMembers = neededMembers - childMembers;
                            }
                            if (relatives.size() < relativeMembers) {
                                relativeMembers = relatives.size();
                                childMembers = neededMembers - relativeMembers;
                            }

                            secondFamilyNewMembers.addAll(children.subList(0,
                                                                           childMembers)); // Children in second family
                            children.subList(0, childMembers).clear();
                            firstFamilyNewMembers.addAll(relatives.subList(0,
                                                                           relativeMembers)); // Relatives to first family
                            relatives.subList(0, relativeMembers).clear();

                        } else {
                            fillChildrenAndRelativesUsingExtras(childMembers, 0, secondFamilyNewMembers, logprefix);
                            fillChildrenAndRelativesUsingExtras(0, relativeMembers, firstFamilyNewMembers, logprefix);
                        }
                    } else if (thirdFamilyType == FamilyType.ONEPARENT) {
                        int relativeMembers = (neededMembers > 3) ? neededMembers - 1 : neededMembers; // Don't want too many
                        // children in third
                        int childMembers = neededMembers - relativeMembers;

                        if (children.size() + relatives.size() > neededMembers) {
                            if (children.size() < childMembers) {
                                childMembers = children.size();
                                relativeMembers = neededMembers - childMembers;
                            }
                            if (relatives.size() < relativeMembers) {
                                relativeMembers = relatives.size();
                                childMembers = neededMembers - relativeMembers;
                            }
                            thirdFamilyNewMembers.addAll(children.subList(0, childMembers)); // Children in third family
                            children.subList(0, childMembers).clear();
                            firstFamilyNewMembers.addAll(relatives.subList(0,
                                                                           relativeMembers)); // Relatives to first family
                            relatives.subList(0, relativeMembers).clear();

                        } else {
                            fillChildrenAndRelativesUsingExtras(childMembers, 0, thirdFamilyNewMembers, logprefix);
                            fillChildrenAndRelativesUsingExtras(0, relativeMembers, firstFamilyNewMembers, logprefix);
                        }
                    } else { // First, second and third family are either couple only or other family.
                        if (relatives.size() >= neededMembers) {
                            firstFamilyNewMembers.addAll(relatives.subList(0, neededMembers));
                            relatives.subList(0, neededMembers).clear();
                        } else {
                            fillChildrenAndRelativesUsingExtras(0, neededMembers, firstFamilyNewMembers, logprefix);
                        }
                    }
                }
            }

            int currentSize = 0;
            if (household.TARGETFAMLYCOUNT == 2) {
                twoFamilyHhs++;
                currentSize = firstFamily.size() + firstFamilyNewMembers.size() + secondFamily.size() + secondFamilyNewMembers
                        .size();
            } else if (household.TARGETFAMLYCOUNT == 3) {
                currentSize = firstFamily.size() + firstFamilyNewMembers.size() + secondFamily.size() + secondFamilyNewMembers
                        .size()
                        + thirdFamily.size() + thirdFamilyNewMembers.size();
                threeFamilyHhs++;
            }

            if (household.TARGETSIZE == currentSize) {
                /* household is complete */
                firstFamily.addMembers(firstFamilyNewMembers);
                secondFamily.addMembers(secondFamilyNewMembers);
                household.addFamily(secondFamily);
                if (household.TARGETFAMLYCOUNT == 3) {
                    thirdFamily.addMembers(thirdFamilyNewMembers);
                    household.addFamily(thirdFamily);
                }

                if (household.TARGETFAMLYCOUNT == household.familyCount()) {
                    allHouseholds.add(household);
                }
            }

        } // end of foreach household
        Log.info(logprefix + "Two family households formed: " + twoFamilyHhs);
        Log.info(logprefix + "Three family households formed: " + threeFamilyHhs);
        if (multiFamilyHouseholdWith1Family.size() == (twoFamilyHhs + threeFamilyHhs)) {
            Log.info(logprefix + "All households created");
        }

    }


    private void fillChildrenAndRelativesUsingExtras(int childMembers, int relativeMembers, List<Person> familyMembers, String logprefix) {
        int missingChildren = 0, missingRelatives = 0;

        if (children.size() > childMembers) {
            familyMembers.addAll(children.subList(0, childMembers));
            children.subList(0, childMembers).clear();
        } else {
            missingChildren = childMembers - children.size();
            familyMembers.addAll(children);
            children.clear();
        }
        if (relatives.size() > relativeMembers) {
            familyMembers.addAll(relatives.subList(0, relativeMembers));
            relatives.subList(0, relativeMembers).clear();
        } else {
            missingRelatives = relativeMembers - relatives.size();
            familyMembers.addAll(relatives);
            relatives.clear();
        }

        if (missingChildren + missingRelatives > 0) {
            if (extras.size() >= missingChildren + missingRelatives) {
                Log.info(logprefix + "Not enough children and/or relatives in main lists. Drawing persons from extras");
                for (int i = 0; i < (missingChildren + missingRelatives); i++) {
                    if (i < missingChildren) {
                        Person child = extras.remove(0);
                        child.setSex(Utils.selectTrueOrFalseRandomlyWithBias(random, sexRatio) ? Sex.Male : Sex.Female);
                        child.setType(RelationshipStatus.U15Child);
                        child.setAgeCat(AgeRange.A0_14);
                        familyMembers.add(child);
                    } else {
                        List<AgeRange> ageslist = new ArrayList<>(Arrays.asList(AgeRange.values()));
                        Person relative = extras.remove(0);
                        relative.setSex(Utils.selectTrueOrFalseRandomlyWithBias(random,
                                                                                sexRatio) ? Sex.Male : Sex.Female);
                        relative.setType(RelationshipStatus.Relative);
                        Collections.shuffle(ageslist);
                        relative.setAgeCat(ageslist.get(0));
                        familyMembers.add(relative);
                    }
                }
            } else {
                Log.warn(logprefix + "Not enough extras to complete the household with children");
                if (relatives.size() >= missingChildren) {
                    Log.info(logprefix + "Completing the household by replacing required children with available relatives");
                    familyMembers.addAll(relatives.subList(0, missingChildren));
                    relatives.subList(0, missingChildren).clear();
                } else {
                    Log.warn(
                            logprefix + "Children: " + children.size() + " Relatives: " + relatives.size() + " Extras: " + extras
                                    .size() + " Required: " + (missingChildren + missingRelatives));
                    throw new Error(logprefix + "Cannot form more households. All extra persons consumed");
                }
            }
        }
    }

    private Family getNewFamily(FamilyType newFamilyType) {
        Family newFamily = null;
        if (newFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN) {
            newFamily = marriedStructs.remove(0);
            newFamily.addMember(children.remove(0));
            newFamily.setType(newFamilyType);
        } else if (newFamilyType == FamilyType.COUPLEONLY) {
            newFamily = marriedStructs.remove(0);
            newFamily.setType(newFamilyType);
        } else if (newFamilyType == FamilyType.ONEPARENT) {
            newFamily = loneParentBasic.remove(0);
            newFamily.setType(newFamilyType);
        } else if (newFamilyType == FamilyType.OTHERFAMILY) {
            newFamily = nonPrimaryOtherFamilies.remove(0);
            newFamily.setType(newFamilyType);
        } else if (newFamilyType == null) {
            Log.warn("Multi-family households: Family Type is null");
        } else {
            Log.warn("Multi-family households: Family Type selected for second family does not exist: " + newFamilyType);
            throw new Error("Unrecognised family type: " + newFamilyType);
        }
        return newFamily;
    }


    private FamilyType selectFamilyType(Household household, FamilyType secondFamilyType) {
        List<FamilyType> threeOrMoreMember = new ArrayList<>(
                Arrays.asList(FamilyType.COUPLEFAMILYWITHCHILDREN,
                              FamilyType.COUPLEONLY,
                              FamilyType.OTHERFAMILY,
                              FamilyType.ONEPARENT));
        List<FamilyType> twoMember = new ArrayList<>(Arrays.asList(FamilyType.COUPLEONLY,
                                                                   FamilyType.OTHERFAMILY,
                                                                   FamilyType.ONEPARENT));

        boolean canNextFamilyBeCplYsChld = (household.TARGETSIZE - household.currentSize() - secondFamilyType.basicSize()) >= 3
                && household.getFamilies().get(0).getType() == FamilyType.COUPLEFAMILYWITHCHILDREN;

        FamilyType nextFamilyType = null;

        if (children.isEmpty() | marriedStructs.isEmpty()) {
            threeOrMoreMember.remove(FamilyType.COUPLEFAMILYWITHCHILDREN);
            twoMember.remove(FamilyType.COUPLEFAMILYWITHCHILDREN);
        }
        if (nonPrimaryOtherFamilies.isEmpty()) {
            threeOrMoreMember.remove(FamilyType.OTHERFAMILY);
            twoMember.remove(FamilyType.OTHERFAMILY);
        }
        if (marriedStructs.isEmpty()) {
            threeOrMoreMember.remove(FamilyType.COUPLEONLY);
            twoMember.remove(FamilyType.COUPLEONLY);
        }
        if (loneParentBasic.isEmpty()) {
            threeOrMoreMember.remove(FamilyType.ONEPARENT);
            twoMember.remove(FamilyType.ONEPARENT);
        }

        if (canNextFamilyBeCplYsChld) {
            if (!threeOrMoreMember.isEmpty()) {
                nextFamilyType = threeOrMoreMember.get(random.nextInt(threeOrMoreMember.size()));
            } else {
                Log.warn("Multi-family households: Unable to form secondary or tertiary families. Aborting");
                return null;
            }

        } else if (household.TARGETSIZE - household.currentSize() >= 2) {
            if (!twoMember.isEmpty()) {
                nextFamilyType = twoMember.get(random.nextInt(twoMember.size()));
            } else {

                Log.warn(
                        "Multi-family households: All two member family structures consumed. Resorting to form only Couple with children secondary and tertiary families");
                return null;
            }

        } else {
            Log.warn(
                    "Multi-family households: Cannot form a secondary family with only 1 vacant slot for members: ABORTING! ");
            throw new Error("Multi-family household that has less than 2 persons in secondary family");
        }
        return nextFamilyType;
    }

    void formOtherFamily1FamilyHouseholds(List<HhRecord> hhrecs, List<Family> othrFamilyBasic) {
        String logprefix = "One family, Other Family: ";
        List<HhRecord> otherFmlyrec = GroupingUtils.getHhsByFamilyType(hhrecs,
                                                                       FamilyHouseholdType.F1OTHERFAMILY);//Get one family other-family hh records
        List<Household> hhs = new ArrayList<>();
        int takenFromExtras = 0;
        int total1FOtherFamily = 0;
        for (HhRecord householdRecord : otherFmlyrec) {
            total1FOtherFamily += householdRecord.hhCount;

            for (int i = 0; i < householdRecord.hhCount; i++) {
                if (othrFamilyBasic.isEmpty()) {
                    Log.errorAndExit(logprefix + "Not enough other family basic structures",
                                     GlobalConstants.EXITCODE.UNDEF);
                }

                Family family = othrFamilyBasic.get(0);
                if (householdRecord.numOfPersonsPerHh > 2) {
                    int remMems = householdRecord.numOfPersonsPerHh - family.size();
                    if (relatives.size() < remMems) {
                        int missing = remMems - relatives.size();
                        familyConstuct.addMembersToFamilyFromExtras(family,
                                                                    RelationshipStatus.Relative,
                                                                    missing,
                                                                    random);
                        takenFromExtras += missing;
                        //Add the ones we can get from relatives list
                        addMembersToFamily(family, relatives, relatives.size());
                    } else {
                        addMembersToFamily(family, relatives, remMems);
                    }
                }
                othrFamilyBasic.remove(0);
                family.setType(householdRecord.primaryFamilyType);
                Household h = new Household(householdRecord.numOfPersonsPerHh,
                                            householdRecord.familyCountPerHousehold,
                                            sa2name);
                h.addFamily(family);
                hhs.add(h);
            }
        }
        Log.info(logprefix + "formed households: " + hhs.size());
        if (takenFromExtras > 0) {
            Log.info(logprefix + "Number taken from extras as Relatives: " + takenFromExtras);
        }
        if (hhs.size() == total1FOtherFamily) {
            Log.info(logprefix + "All households created");
        }
        allHouseholds.addAll(hhs);
    }

    private void formLoneParent1FamilyHouseholds(List<HhRecord> hhrecs) {
        String logprefix = "One family, Lone parent: ";
        List<HhRecord> lnparentrec = GroupingUtils.getHhsByFamilyType(hhrecs, FamilyHouseholdType.F1ONEPARENT);
        // int neededMembers = 0;
        List<Household> loneParentHouseholds = new ArrayList<>();
        int totalLoneParenHhs = 0;
        for (HhRecord hhrec : lnparentrec) {
            if (loneParentBasic.isEmpty()) {
                continue;
            }
            totalLoneParenHhs += hhrec.hhCount;
            for (int i = 0; i < hhrec.hhCount; i++) {
                if (loneParentBasic.isEmpty()) {
                    Log.warn(logprefix + "Not enough lone parent basic structures");
                    break;
                }
                Family family = loneParentBasic.remove(0);
                family.setType(FamilyType.ONEPARENT);

                if (hhrec.numOfPersonsPerHh > family.size())
                    addChildrenAndRelativesToFamilyFromMainLists(family, hhrec.numOfPersonsPerHh);

                if (hhrec.numOfPersonsPerHh > family.size()) {
                    int neededMembers = hhrec.numOfPersonsPerHh - family.size();
                    if (neededMembers > 0) { //We have exhausted all known children and relatives. So select from extras.
                        int relativesCount = Utils.selectTrueOrFalseRandomlyWithBias(random,
                                                                                     relativeProbability) ? 1 : 0;
                        int childrenCount = neededMembers - relativesCount;

                        familyConstuct.addMembersToFamilyFromExtras(family,
                                                                    RelationshipStatus.Relative,
                                                                    relativesCount,
                                                                    random);
                        //TODO: Child can be U15, Student or O15.
                        familyConstuct.addMembersToFamilyFromExtras(family,
                                                                    RelationshipStatus.U15Child,
                                                                    childrenCount,
                                                                    random);
                    }
                }
                Household household = new Household(hhrec.numOfPersonsPerHh, hhrec.familyCountPerHousehold, sa2name);
                household.addFamily(family);
//                loneParentHouseholds.add(household);
                allHouseholds.add(household);
            }

        }
        Log.info("One family, Lone parent: formed households: " + loneParentHouseholds.size());
        if (loneParentHouseholds.size() == totalLoneParenHhs) {
            Log.info("One family, Lone parent: All households created");
        }
//        allHouseholds.addAll(loneParentHouseholds);
    }

    private int addChildrenAndRelativesToFamilyFromMainLists(Family family, int maxHhSize) {
        //We can complete the family using children or relatives. We randomly add a child or a relative
        //to the family. The while loop treats children and relatives as a one large virtual list. We
        //randomly pick an index and add the corresponding the child or relative to the family.
        int missingMembers = maxHhSize - family.size(), addCount = 0;
        while (missingMembers > 0 && !(children.isEmpty() && relatives.isEmpty())) {
            int randIndex = random.nextInt(relatives.size() + children.size());
            if (randIndex < relatives.size()) {
                family.addMember(relatives.remove(randIndex));
            } else {
                //Children section of the virtual list starts at the end of relatives list. So we have to
                //offset the index
                family.addMember(children.remove(randIndex - relatives.size()));
            }
            addCount++;
            missingMembers--;
        }
        return addCount;
    }

    private void formCoupleWithChild1FamilyHouseholds(List<HhRecord> hhrecs, List<Family> cplYsChldPrimaryFamilies) {
        String logprefix = "One Family, Couple with children: ";
        List<HhRecord> cplYsChldrec = GroupingUtils.getHhsByFamilyType(hhrecs,
                                                                       FamilyHouseholdType.F1COUPLEWITHCHILDREN);
        List<Household> hhs = new ArrayList<>();

        int unformed = 0;
        for (HhRecord householdRecord : cplYsChldrec) {
            if (cplYsChldPrimaryFamilies.isEmpty()) {
                unformed += householdRecord.hhCount;
                continue;
            }
            for (int i = 0; i < householdRecord.hhCount; i++) {
                if (cplYsChldPrimaryFamilies.isEmpty()) {
                    unformed += (householdRecord.hhCount - i);
                    Log.warn(logprefix + "Not enough Couple with children primary families");
                    break;
                }

                Family family = cplYsChldPrimaryFamilies.get(0);

                //We can complete the family using children or relatives. We randomly add a child or a relative
                //to the family. The while loop treats children and relatives as a one large virtual list. We
                //randomly pick an index and add the corresponding the child or relative to the family.
                int addedCount = addChildrenAndRelativesToFamilyFromMainLists(family,
                                                                              householdRecord.numOfPersonsPerHh);

                int remMems = householdRecord.numOfPersonsPerHh - family.size();
                if (remMems > 0) { //We have exhausted all known children and relatives. So select from extras.
                    int relativesCount = Utils.selectTrueOrFalseRandomlyWithBias(random, relativeProbability) ? 1 : 0;
                    int childrenCount = remMems - relativesCount;

                    familyConstuct.addMembersToFamilyFromExtras(family,
                                                                RelationshipStatus.Relative,
                                                                relativesCount,
                                                                random);
                    //TODO: Child can come from either U15, Student or O15
                    familyConstuct.addMembersToFamilyFromExtras(family,
                                                                RelationshipStatus.U15Child,
                                                                childrenCount,
                                                                random);
                }

                cplYsChldPrimaryFamilies.remove(0);
                family.setType(householdRecord.primaryFamilyType);
                Household h = new Household(householdRecord.numOfPersonsPerHh,
                                            householdRecord.familyCountPerHousehold,
                                            sa2name);
                h.addFamily(family);
                hhs.add(h);
            }
        }
        Log.info(logprefix + "formed households: " + hhs.size());
        if (unformed > 0) {
            Log.warn(logprefix + "unformed households: " + unformed);
        } else {
            Log.info(logprefix + "All households created");
        }
        allHouseholds.addAll(hhs);
    }

    private void formCoupleOnly1FamilyHouseholds(List<HhRecord> hhrecs, List<Family> cplOnlyPrimaryFamilies) {
        String logprefix = "One Family, Couple only: ";
        List<HhRecord> cplOnlyrec = GroupingUtils.getHhsByFamilyType(hhrecs, FamilyHouseholdType.F1COUPLEONLY);
        List<Household> hhs = new ArrayList<>();
        int unformed = 0;
        for (HhRecord householdRecord : cplOnlyrec) {
            if (cplOnlyPrimaryFamilies.isEmpty()) {
                unformed += householdRecord.hhCount;
                continue;
            }
            for (int i = 0; i < householdRecord.hhCount; i++) {
                if (cplOnlyPrimaryFamilies.isEmpty()) {
                    unformed += (householdRecord.hhCount - i);
                    Log.warn(logprefix + "Not enough Couple Only Primary Families");
                    break;
                }

                Family family = cplOnlyPrimaryFamilies.get(0);
                if (householdRecord.numOfPersonsPerHh > 2) {
                    int remMems = householdRecord.numOfPersonsPerHh - family.size();
                    if (relatives.size() < remMems) {
                        int neededFromExtras = remMems - relatives.size();
                        addMembersToFamily(family, relatives, relatives.size());
                        familyConstuct.addMembersToFamilyFromExtras(family,
                                                                    RelationshipStatus.Relative,
                                                                    neededFromExtras,
                                                                    random);
                    } else {
                        addMembersToFamily(family, relatives, remMems);
                    }
                }
                cplOnlyPrimaryFamilies.remove(0);
                family.setType(householdRecord.primaryFamilyType);
                Household h = new Household(householdRecord.numOfPersonsPerHh,
                                            householdRecord.familyCountPerHousehold,
                                            sa2name);
                h.addFamily(family);
                hhs.add(h);
            }
        }
        Log.info(logprefix + "formed households: " + hhs.size());
        if (unformed > 0) {
            Log.warn(logprefix + "unformed households: " + unformed);
        } else {
            Log.info(logprefix + "All households created");
        }
        allHouseholds.addAll(hhs);
    }

    /**
     * Add a specified number of members to the family from a list of persons. The persons are selected
     * from the source list from top to bottom. After adding the members the members are removed from
     * the source list.
     *
     * @param family     The family instance to which members are added
     * @param sourceList The persons list from which members are selected
     * @param count      The number of persons to add
     */
    private void addMembersToFamily(Family family, List<Person> sourceList, int count) {
        List<Person> selected = sourceList.subList(0, count);
        family.addMembers(selected);
        selected.clear();
    }


    private void makeLonePersonsHhs(List<HhRecord> hhrecs, List<IndRecord> indrec) {
        List<HhRecord> lnPersonHhs = GroupingUtils.getHhsByFamilyType(hhrecs, FamilyHouseholdType.LONEPERSON);
        List<IndRecord> lnPersonInds = GroupingUtils.getAgentsByRelType(indrec, ilnperson);

        List<Person> allpersons = new ArrayList<>();
        for (IndRecord lnp : lnPersonInds) {
            for (int j = 0; j < lnp.indCount; j++) {
                Person p = new Person();
                p.setSex(lnp.sex);
                p.setType(lnp.relationshipStatus);
                p.setAgeCat(lnp.ageRange);
                allpersons.add(p);
            }
        }
        HhRecord hhrec = lnPersonHhs.get(0); // Only 1 member households have lone persons

        int hhcnt = hhrec.hhCount;
        int diff = hhcnt - allpersons.size();
        int familyCount = Math.min(hhcnt, allpersons.size());
        List<Household> hhlist = new ArrayList<>();

        for (int i = 0; i < familyCount; i++) {
            Family f = new Family(hhrec.primaryFamilyType);
            f.addMember(allpersons.remove(0));
            Household h = new Household(hhrec.numOfPersonsPerHh, hhrec.familyCountPerHousehold, sa2name);
            h.addFamily(f);
            hhlist.add(h);
        }
        allHouseholds.addAll(hhlist);

        Log.info("Lone person households: Households formed: " + hhlist.size());
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

    private void makeGroupHouseholds(List<HhRecord> hhrecs, List<IndRecord> indrec) {
        List<HhRecord> grpHhrecs = GroupingUtils.getHhsByFamilyType(hhrecs, FamilyHouseholdType.GROUPHOUSEHOLD);
        List<IndRecord> grpIndrecs = GroupingUtils.getAgentsByRelType(indrec, igrpInd);

        List<Person> grpmems = new ArrayList<>();
        for (IndRecord grprec : grpIndrecs) {
            for (int i = 0; i < grprec.indCount; i++) {
                Person gm = new Person();
                gm.setAgeCat(grprec.ageRange);
                gm.setSex(grprec.sex);
                gm.setType(grprec.relationshipStatus);
                grpmems.add(gm);
            }
        }
        List<Household> grpHhs = new ArrayList<>();
        int totalGroupHhs = 0;
        for (HhRecord hhrec : grpHhrecs) {

            int hhcnt = hhrec.hhCount;
            totalGroupHhs += hhrec.hhCount;
            int hhsize = hhrec.numOfPersonsPerHh;
            if (hhcnt > 0 & hhsize > grpmems.size()) {
                Log.warn("Group households: Unformed " + hhsize + " member households: " + hhcnt);
                continue;
            }
            for (int i = 0; i < hhcnt; i++) {
                Family f = new Family(hhrec.primaryFamilyType);
                if (hhsize > grpmems.size()) {
                    Log.warn("Group households: Persons discarded: " + grpmems.size());
                    Log.warn("Group households: Unformed " + hhsize + " member households: " + (hhcnt - i));
                    break;
                }
                f.addMembers(grpmems.subList(0, hhsize));
                grpmems.subList(0, hhsize).clear();

                Household h = new Household(hhrec.numOfPersonsPerHh, hhrec.familyCountPerHousehold, sa2name);
                h.addFamily(f);

                grpHhs.add(h);
            }

        }
        Log.info("Group households: Households formed: " + grpHhs.size());
        allHouseholds.addAll(grpHhs);
        if (totalGroupHhs == grpHhs.size()) {
            Log.info("Group households: All Households created");
        } else {
            Log.warn("Group households: Households created: " + grpHhs.size() + " Required: " + totalGroupHhs);
        }
    }


}
