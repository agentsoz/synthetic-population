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
    private ExtrasHandler extrasHandler;

    List<Household> makePopulation(List<HhRecord> hhRecs,
                                   List<IndRecord> indRecs,
                                   Random rand,
                                   String sa2,
                                   double nonPrimaryCoupleWithChildProbability) {
        this.sa2name = sa2;
        // printHhSummary(hhRecs)
        // printIndSummary(indRecs);

        this.random = rand;
        extrasHandler = new ExtrasHandler(hhRecs, indRecs, random);
        Log.info("Extras (difference between households and persons files): " + extrasHandler.remainingExtras());

        makeLonePersonHouseholds(hhRecs, indRecs);
        makeGroupHouseholds(hhRecs, indRecs);


        List<Person> married = PersonsFactory.makeAllPersonsByRelationshipType(indRecs, RelationshipStatus.MARRIED);
        List<Person> marriedMales = married.stream().filter(p -> p.getSex() == Sex.Male).collect(Collectors.toList());
        List<Person> marriedFemales = married.stream()
                                             .filter(p -> p.getSex() == Sex.Female)
                                             .collect(Collectors.toList());

        List<Person> relatives = PersonsFactory.makeAllPersonsByRelationshipType(indRecs, RelationshipStatus.RELATIVE);
        List<Person> loneParents = PersonsFactory.makeAllPersonsByRelationshipType(indRecs,
                                                                                   RelationshipStatus.LONE_PARENT);
        List<Person> children = PersonsFactory.makeAllPersonsByRelationshipType(indRecs,
                                                                                RelationshipStatus.U15_CHILD,
                                                                                RelationshipStatus.STUDENT,
                                                                                RelationshipStatus.O15_CHILD);

        FamilyFactory familyFactory = new FamilyFactory(rand);

        Log.debug("Remaining Married males: " + marriedMales.size());
        Log.debug("Remaining Married females: " + marriedFemales.size());
        Log.debug("Remaining Relatives: " + relatives.size());
        Log.debug("Remaining Children: " + children.size());
        Log.debug("Remaining Lone parents: " + loneParents.size());

        List<Family> basicCouples = familyFactory.makeMarriedCouples(marriedMales, marriedFemales);
        Log.debug("Remaining Married males: " + marriedMales.size());
        Log.debug("Remaining Married females: " + marriedFemales.size());
        Log.debug("Remaining Basic couples: " + basicCouples.size());

        //Save extra married persons for later use. If married females list is not empty we had extra females,
        // otherwise we had extra males,
        //because makeMarriedCouples() function keeps forming couples until one list exhausts.
        extrasHandler.setExtraMarriedPersons((!marriedFemales.isEmpty()) ? marriedFemales : marriedMales);


        List<Family> oneParentBasic = familyFactory.makeAllOneParentBasicUnits(loneParents, children);
        Log.debug("Remaining Lone parent persons: " + loneParents.size());
        Log.debug("Remaining Children: " + children.size());

        //Form basic family structures and removes couples from married males and females list
        List<HhRecord> coupleWChildRecs = DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                                     FamilyHouseholdType
                                                                                             .F1_COUPLE_WITH_CHILDREN,
                                                                                     FamilyHouseholdType
                                                                                             .F2_COUPLE_WITH_CHILDREN,
                                                                                     FamilyHouseholdType
                                                                                             .F3_COUPLE_WITH_CHILDREN);
        int fCount = coupleWChildRecs.stream().mapToInt(r -> r.HH_COUNT).sum();
        List<Family> primaryCoupleWChildFamilyBasic = familyFactory.formCoupleWithChildFamilyBasicUnits(fCount,
                                                                                                        basicCouples,
                                                                                                        children);
        Log.debug("Remaining Basic couples: " + basicCouples.size());
        Log.debug("Remaining Children: " + children.size());

        // Forms Other Family basic family structures
        List<HhRecord> otherFamiliesRecs = DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                                      FamilyHouseholdType
                                                                                              .F1_OTHER_FAMILY,
                                                                                      FamilyHouseholdType
                                                                                              .F2_OTHER_FAMILY,
                                                                                      FamilyHouseholdType
                                                                                              .F3_OTHER_FAMILY);
        fCount = otherFamiliesRecs.stream().mapToInt(r -> r.HH_COUNT).sum();
        List<Family> primaryOtherFamiliesBasic = familyFactory.makeOtherFamilyBasicUnits(fCount, relatives);
        Log.debug("Remaining Relatives: " + relatives.size());

        try {
            Map<FamilyHouseholdType, List<Household>> basicHouseholds = formHouseholdsWithPrimaryFamilies(hhRecs,
                                                                                                          basicCouples,
                                                                                                          primaryCoupleWChildFamilyBasic,
                                                                                                          oneParentBasic,
                                                                                                          primaryOtherFamiliesBasic);
            Log.debug("Remaining Couples basic: " + basicCouples.size());
            Log.debug("Remaining Couple with children basic: " + primaryCoupleWChildFamilyBasic.size());
            Log.debug("Remaining Other family basic: " + primaryOtherFamiliesBasic.size());
            Log.debug("Remaining One parent basic: " + oneParentBasic.size());

            //Fill 1 family couple only households with relatives
            Log.info("Fill " + FamilyHouseholdType.F1_COUPLE_ONLY + " households with relatives");
            Log.debug("Remaining relatives: " + relatives.size());
            Log.debug("Remaining extras: " + extrasHandler.remainingExtras());
            completeHouseholdsWithRelatives(basicHouseholds, relatives, FamilyHouseholdType.F1_COUPLE_ONLY);
            Log.debug("Remaining relatives: " + relatives.size());
            Log.debug("Remaining extras: " + extrasHandler.remainingExtras());

            //Fill 1 family other family households with relatives
            Log.info("Fill " + FamilyHouseholdType.F1_OTHER_FAMILY + " households with relatives");
            completeHouseholdsWithRelatives(basicHouseholds, relatives, FamilyHouseholdType.F1_OTHER_FAMILY);
            Log.debug("Remaining relatives: " + relatives.size());
            Log.debug("Remaining extras: " + extrasHandler.remainingExtras());
            //  We don't complete 1 family one parent and couple with children families at this stage because those
            // families can have both children and relatives. We are not sure what to use at this stage. So, will
            // complete them later.

            //Add 2nd and 3rd families to multi-family households.
            Log.info("Starting to add non-primary families to multi-family households");
            Log.debug("Remaining Children: " + children.size());
            Log.debug("Remaining Couples basic: " + basicCouples.size());
            Log.debug("Remaining Couple with children basic: " + primaryCoupleWChildFamilyBasic.size());
            Log.debug("Remaining Other family basic: " + primaryOtherFamiliesBasic.size());
            Log.debug("Remaining One parent basic: " + oneParentBasic.size());

            addNonPrimaryBasicFamiliesToHouseholds(hhRecs,
                                                   basicHouseholds,
                                                   basicCouples,
                                                   oneParentBasic,
                                                   children,
                                                   relatives,
                                                   nonPrimaryCoupleWithChildProbability,
                                                   familyFactory);

            Log.debug("Remaining relatives: " + relatives.size());
            Log.debug("Remaining children: " + children.size());
            Log.debug("Remaining extras: " + extrasHandler.remainingExtras());

            Map<RelationshipStatus, List<Person>> childrenAndRelativesFromExtras = extrasHandler.convertAllExtrasToChildrenAndRelatives(true);
            completeHouseholdsWithChildren(basicHouseholds, children, childrenAndRelativesFromExtras, familyFactory);
            if (childrenAndRelativesFromExtras.containsKey(RelationshipStatus.RELATIVE)) {
                relatives.addAll(childrenAndRelativesFromExtras.get(RelationshipStatus.RELATIVE));
            }
            completeHouseholdsWithRelatives(basicHouseholds, relatives, null);

            allHouseholds.addAll(basicHouseholds.values().stream().flatMap(List::stream).collect(Collectors.toList()));

            Log.debug("Remaining Relatives: " + relatives.size());
            Log.debug("Remaining Children: " + children.size());
            Log.debug("Remaining Extras: " + extrasHandler.remainingExtras());
        } catch (Exception npex) {
            Log.error("Family households construction failed", npex);
            Log.debug("Persons snapshot when error occurred");
            Log.debug("Remaining Couples basic: " + basicCouples.size());
            Log.debug("Remaining Relatives: " + relatives.size());
            Log.debug("Remaining Couple with children basic: " + primaryCoupleWChildFamilyBasic.size());
            Log.debug("Remaining Other family basic: " + primaryOtherFamiliesBasic.size());
            Log.debug("Remaining Children: " + children.size());
            Log.debug("Remaining Extras: " + extrasHandler.remainingExtras());
            Log.errorAndExit("Premature exit due to failure: ", ExitCode.DATA_ERROR);
        }

        for (Household h : allHouseholds) {
            if (!h.validate()) {
                Log.error("Bad state in" + h);
            }
        }

        Utils.summary(allHouseholds);
        return allHouseholds;
    }

    /**
     * Adds children to the primary family of the households that can have children.
     *
     * @param households         Map of all households in the population
     * @param knownChildren      The list of children known in input data
     * @param childrenFromExtras The children extracted from the Extras
     * @param familyFactory      FamilyFactory instance
     */
    private void completeHouseholdsWithChildren(Map<FamilyHouseholdType, List<Household>> households,
                                                List<Person> knownChildren,
                                                Map<RelationshipStatus, List<Person>> childrenFromExtras,
                                                FamilyFactory familyFactory) {

        List<Household> incompleteHhs = households.values().stream().flatMap(List::stream).collect(Collectors.toList());
        for (RelationshipStatus childType : Arrays.asList(RelationshipStatus.O15_CHILD,
                                                          RelationshipStatus.STUDENT,
                                                          RelationshipStatus.U15_CHILD)) {
            List<Person> allChildren = knownChildren.stream()
                                                    .filter(c -> c.getRelationshipStatus() == childType)
                                                    .collect(Collectors.toList());
            Log.debug("Known " + childType + ": " + allChildren.size());
            if (childrenFromExtras != null && childrenFromExtras.containsKey(childType)) {
                allChildren.addAll(childrenFromExtras.get(childType));
                Collections.shuffle(allChildren, random);
            }
            Log.debug("All " + childType + ": " + allChildren.size());

            while (!allChildren.isEmpty()) {
                Person child = allChildren.get(0);
                Collections.shuffle(incompleteHhs, random);
                int hhIndex = PopulationRules.selectHouseholdWithSuitablePrimaryFamilyForChild(child, incompleteHhs);
                if (hhIndex >= 0) {
                    Family pf = incompleteHhs.get(hhIndex).getPrimaryFamily();
                    pf.addMember(child);
                    allChildren.remove(child);
                    knownChildren.remove(child);
                } else {
                    throw new NoSuitableHouseholdException("Cannot find a household for " + child
                            .getRelationshipStatus());
                }

            }
        }

    }

    /**
     * Completes households by adding relatives to the families that are larger than 2 members. If there is not enough relatives Extras are
     * converted to relatives. This method does not check the number of families in the household. This method modifies the input lists.
     *
     * @param householdsMap       The one family households map to complete by only adding relatives
     * @param relatives           The list of relatives in the population
     * @param familyHouseholdType FamilyHouseholdType of the households to be completed. All households are selected if null
     */
    private void completeHouseholdsWithRelatives(Map<FamilyHouseholdType, List<Household>> householdsMap,
                                                 List<Person> relatives,
                                                 FamilyHouseholdType familyHouseholdType) {
        Collections.shuffle(relatives, random);
        //Filter the household that match the family household type.
        List<Household> availableHhs = householdsMap.entrySet()
                                                    .stream()
                                                    .filter(e -> e.getKey() == familyHouseholdType || familyHouseholdType == null)
                                                    .map(Map.Entry::getValue)
                                                    .flatMap(List::stream)
                                                    .collect(Collectors.toList());
        Log.debug((familyHouseholdType != null ? familyHouseholdType.name() : "All") + ": Available households: " + availableHhs.size());

        int formed = 0;
        for (Household h : availableHhs) {
            int diff = h.getExpectedSize() - h.getCurrentSize();
            if (diff > 0) {
                Family f = h.getPrimaryFamily();
                if (relatives.size() >= diff) {
                    f.addMembers(relatives.subList(0, diff));
                    relatives.subList(0, diff).clear();
                } else {
                    f.addMembers(extrasHandler.getPersonsFromExtras(RelationshipStatus.RELATIVE,
                                                                    null,
                                                                    null,
                                                                    diff - relatives.size()));
                    f.addMembers(relatives);
                    relatives.clear();
                }
                formed++;
            }

        }

        Log.info(familyHouseholdType != null ? familyHouseholdType.name() : "All" + ": Updated households: " + formed);
        Log.info(familyHouseholdType != null ? familyHouseholdType.name() : "All" + ": All households created");
    }

    private Map<FamilyType, Integer> getNonPrimaryFamilyDistribution(List<HhRecord> hhRecs,
                                                                     Map<FamilyHouseholdType, List<Household>>
                                                                             basicHouseholds,
                                                                     List<Person> marriedMales,
                                                                     List<Person> marriedFemales,
                                                                     List<Person> loneParents,
                                                                     List<Person> children,
                                                                     List<Person> relatives) {

        List<Household> slots = basicHouseholds.entrySet()
                                               .stream()
                                               .filter(e -> (e.getKey().getFamilyType() == FamilyType.ONE_PARENT ||
                                                       e.getKey().getFamilyType() == FamilyType.COUPLE_WITH_CHILDREN))
                                               .map(Map.Entry::getValue)
                                               .flatMap(List::stream)
                                               .collect(Collectors.toList());

        //Non primary lone parent families must go in a household where primary family is either couple with child or
        // lone parent. If there are more lone parent persons than suitable households, we can only confirm positions
        // for lone parent families up to the number of suitable households. If the number of lone parent persons is
        // less than the suitable number, we can confirm positions for all the lone parents. The unassigned family
        // slots need
        // to be filled by other non-primary family types.
        int remainingFamilySlots = DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                              FamilyHouseholdType.F2_ONE_PARENT,
                                                                              FamilyHouseholdType
                                                                                      .F2_COUPLE_WITH_CHILDREN)
                                             .size();
        remainingFamilySlots += DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                           FamilyHouseholdType.F3_ONE_PARENT,
                                                                           FamilyHouseholdType.F3_COUPLE_WITH_CHILDREN)
                                          .size() * 2;
        int confirmedLnParentFamilies = 0;
        if (loneParents.size() >= remainingFamilySlots) {
            confirmedLnParentFamilies = remainingFamilySlots;
            remainingFamilySlots = 0;
        } else {
            confirmedLnParentFamilies = loneParents.size();
            remainingFamilySlots = remainingFamilySlots - loneParents.size();
        }

        remainingFamilySlots += DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                           FamilyHouseholdType.F2_COUPLE_ONLY,
                                                                           FamilyHouseholdType.F2_OTHER_FAMILY).size();
        remainingFamilySlots += DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                           FamilyHouseholdType.F3_COUPLE_ONLY,
                                                                           FamilyHouseholdType.F3_OTHER_FAMILY)
                                          .size() * 2;
        int confirmedCoupleUnits = 0;
        int married = Math.min(marriedMales.size(), marriedFemales.size());
        if (married >= remainingFamilySlots) {
            confirmedCoupleUnits = remainingFamilySlots;
            remainingFamilySlots = 0;
        } else {
            confirmedCoupleUnits = married;
            remainingFamilySlots = remainingFamilySlots - married;
        }


        return null;
    }


    /**
     * Calculates the distribution of COUPLE_ONLY, COUPLE_WITH_CHILD, ONE_PARENT and OTHER_FAMILY basic units in non-primary families based
     * on observed distribution of FamilyTypes in primary families. The calculation takes into account the number of remaining couple and
     * one parent basic units.
     * <p>
     * Though there is a large number of COUPLE_WITH_CHILDREN primary family households in the population we don't expect a similar large
     * number of COUPLE_WITH_CHILDREN non-primary families in households. Because of that we count both COUPLE_ONLY and COUPLE_WITH_CHILDREN
     * primary families as COUPLE_ONLY basic units. Then we form all missing COUPLE_ONLY, ONE_PARENT and OTHER_FAMILY using extras. Next we
     * define a parameter giving the portion of 1Family-COUPLE_WITH_CHILDREN and 2 Family-COUPLE_WITH_CHILDREN households that have another
     * COUPLE_WITH_CHILDREN family as a non-primary family. The number of COUPLE_WITH_CHILDREN non-primary families are calculate based on
     * this parameter and the required families are created by converting already formed COUPLE_ONLY units to COUPLE_WITH_CHILDREN by adding
     * a child.
     *
     * @param households                          The map of households categorised by FamilyHouseholdType
     * @param unusedCoupleOnlyBasic               The list of remaining COUPLE_ONLY basic units
     * @param unusedOneParentBasic                The list of remaining ONE_PARENT basic units
     * @param nonPrimaryCoupleWithChildProportion The portion of COUPLE_WITH_CHILD non-primary families given that primary family is a
     *                                            COUPLE_WITH_CHILD family
     * @return The distribution of FamilyTypes in all non-primary families
     */
    private Map<FamilyType, Integer> getNonPrimaryFamilyDistribution(List<HhRecord> hhRecords,
                                                                     Map<FamilyHouseholdType, List<Household>> households,
                                                                     final List<Family> unusedCoupleOnlyBasic,
                                                                     final List<Family> unusedOneParentBasic,
                                                                     double nonPrimaryCoupleWithChildProportion) {


        Map<FamilyType, Integer> dist = getPrimaryFamilyDistribution(hhRecords);
        int couples = dist.get(FamilyType.COUPLE_ONLY);
        int oneParent = dist.get(FamilyType.ONE_PARENT);
        int other = dist.get(FamilyType.OTHER_FAMILY);
        int totalNonPrimary = hhRecords.stream().mapToInt(hr -> (hr.HH_COUNT * (hr.getFamilyCountPerHousehold() - 1))).sum();

        int unknownFamilies = totalNonPrimary - unusedCoupleOnlyBasic.size() - unusedOneParentBasic.size();


        Log.debug("Non-Primary Basic Families: Primary family distribution: married:" + couples + " one.parent: " +
                          oneParent + " other:" + other);
        Log.debug("Non-Primary Basic Families: Total non-primary families: " + totalNonPrimary);
        Log.debug("Non-Primary Basic Families: Existing Basic Couple Only units: " + unusedCoupleOnlyBasic.size());
        Log.debug("Non-Primary Basic Families: Existing Basic One Parent units: " + unusedOneParentBasic.size());
        Log.debug("Non-Primary Basic Families: Unknown non-primary families: " + unknownFamilies);

        Map<FamilyType, Integer> counts = new HashMap<>(5, 1);
        int newCouplesCount = 0, newOneParentFamilyCount = 0, newOtherFamilyCount = 0, newCoupleWithChildCount = 0;

        if (unknownFamilies > 0) {
            float divisor = (float) (couples + oneParent + other);
            newCouplesCount = Math.round(unknownFamilies * couples / divisor);

            //Now we are going to find how many COUPLE_WITH_CHILDREN non-primary families
            //count the households eligible to have a couple with child family as a non-primary family
            int coupleWithChildEligibleFamilyCount = (int) households.entrySet()
                                                                     .stream()
                                                                     .filter(e -> e.getKey() == FamilyHouseholdType.F2_COUPLE_WITH_CHILDREN || e
                                                                             .getKey() ==
                                                                             FamilyHouseholdType.F3_COUPLE_WITH_CHILDREN) //Eligibility criteria
                                                                     .map(Map.Entry::getValue) //Get the household lists as a stream
                                                                     .flatMap(List::stream) // Merge the lists.
                                                                     .filter(hh -> hh.getExpectedSize() >= 6)//Get the ones that can have at least 2 families of 3
                                                                     // members.
                                                                     .count();

            //Get the number of Hhs that actually have COUPLE_WITH_CHILDREN non-primary families based on proportion.
            newCoupleWithChildCount = (int) Math.round(coupleWithChildEligibleFamilyCount *
                                                               nonPrimaryCoupleWithChildProportion);

            newCouplesCount = newCouplesCount - newCoupleWithChildCount;
            counts.put(FamilyType.COUPLE_ONLY, newCouplesCount + unusedCoupleOnlyBasic.size());
            counts.put(FamilyType.COUPLE_WITH_CHILDREN, newCoupleWithChildCount);

            //one parent families based on proportions.
            newOneParentFamilyCount = Math.round(unknownFamilies * oneParent / divisor);
            //See if there is enough room for these new families
            int oneParentFamilyEligible = (int) households.entrySet()
                                                          .stream()
                                                          .filter(e -> Arrays.asList(FamilyType.ONE_PARENT, FamilyType.COUPLE_WITH_CHILDREN)
                                                                             .contains(e.getKey().getFamilyType()))//Eligibility criteria
                                                          .map(Map.Entry::getValue) //Get the household lists as a stream
                                                          .flatMap(List::stream) // Merge the lists.
                                                          .mapToInt(h -> (h.getExpectedFamilyCount() - h.getCurrentFamilyCount()))
                                                          .sum();
            newOneParentFamilyCount = ((newOneParentFamilyCount + unusedOneParentBasic.size()) > oneParentFamilyEligible) ? //too many?
                                      (oneParentFamilyEligible - unusedOneParentBasic.size()) : // If yes - only create to match free slots
                                      newOneParentFamilyCount; //If no - there are extra slots, so create all of them.
            counts.put(FamilyType.ONE_PARENT, newOneParentFamilyCount + unusedOneParentBasic.size());

            newOtherFamilyCount = unknownFamilies - (newCouplesCount + newCoupleWithChildCount +
                    newOneParentFamilyCount);
            counts.put(FamilyType.OTHER_FAMILY, newOtherFamilyCount);

            if (newOtherFamilyCount < 0) {
                Log.warn("Non-Primary Basic Families: New Basic Other Family units needed: " +
                                 newOneParentFamilyCount + ". We expected a positive count. Setting new Basic Other " +
                                 "Family units count to 0.");
                newOtherFamilyCount = 0;
            }

            if (unknownFamilies != (newCouplesCount + newOneParentFamilyCount + newOtherFamilyCount +
                    newCoupleWithChildCount)) {
                throw new IllegalStateException(
                        "Non-Primary Basic Families: The predicted sum of non-primary families is unequal to number " +
                                "of total non-primary families. \n"
                                + "New Couples: " + newCouplesCount + "\n"
                                + "New One Parent units: " + newOneParentFamilyCount + "\n"
                                + "New Couple With Children units: " + newCoupleWithChildCount + "\n"
                                + "New Other Family units: " + newOtherFamilyCount + "\n"
                                + "Existing Couples: " + unusedCoupleOnlyBasic.size() + "\n"
                                + "Existing One Parent units: " + unusedOneParentBasic.size() + "\n"
                                + "Total Non-Primary Families: " + totalNonPrimary
                );
            }
        } else if (unknownFamilies == 0) {
            Log.info("Non-Primary Basic Families: Not forming new units: " +
                             "Existing Basic Couple Only (" + unusedCoupleOnlyBasic.size() + ") and " +
                             "Basic One Parent (" + unusedOneParentBasic.size() + ") matches " +
                             "total required Non-Primary family count (" + totalNonPrimary + ")");
            counts.put(FamilyType.COUPLE_ONLY, unusedCoupleOnlyBasic.size());
            counts.put(FamilyType.ONE_PARENT, unusedOneParentBasic.size());
            counts.put(FamilyType.COUPLE_WITH_CHILDREN, 0);
            counts.put(FamilyType.OTHER_FAMILY, 0);

        } else {
            //We know there are more unused couples and one parent units than available slots. Here we try to fit units we can. The extra
            // ones need to be handle outside this method.

            //We first try to fit as many one parent units we can
            int oneParentFamilyEligible = (int) households.entrySet()
                                                          .stream()
                                                          .filter(e -> Arrays.asList(FamilyType.ONE_PARENT, FamilyType.COUPLE_WITH_CHILDREN)
                                                                             .contains(e.getKey().getFamilyType()))//Eligibility criteria
                                                          .map(Map.Entry::getValue) //Get the household lists as a stream
                                                          .flatMap(List::stream) // Merge the lists.
                                                          .mapToInt(h -> (h.getExpectedFamilyCount() - h.getCurrentFamilyCount())) //Get vacant family units in households
                                                          .sum();
            int usableOneParents = (unusedOneParentBasic.size() > oneParentFamilyEligible) ?//more unused one parent units than free slots?
                                   oneParentFamilyEligible ://yes - use only the number of units that fits the number of slots
                                   unusedOneParentBasic.size();//no (fewer unused one parents than slots) - use all unused one parent units

            //Now we try to fit couples to all remaining free slots
            int vacantAfterOneParents = totalNonPrimary - usableOneParents; //couples can fit in any slot. get vacant slots after one parent
            int usableCouples = (vacantAfterOneParents == 0) ?// No free slots?
                                0 : //Yes - no free slots for couples, so cannot use any unused couples
                                (unusedCoupleOnlyBasic.size() >= vacantAfterOneParents) ?
                                // There are free slots. But do we have extra unused couples than free slots
                                vacantAfterOneParents : // Yes - use only the number of couple units that fits the number of free slots
                                unusedCoupleOnlyBasic.size(); //No (we have less couples than free slots) fill slots with all unused couples

            //Since we know that there are more one parent + couple families than available free slots, at this stage we must have sorted out everything by now
            if (totalNonPrimary == (usableOneParents + usableCouples)) {
                throw new IllegalStateException("Non-Primary Basic Families: Too many unused Basic Couple Only (" + unusedCoupleOnlyBasic
                        .size() + ") and Basic One Parent (" + unusedOneParentBasic.size() + ") units. Total Non-Primary " + "family " +
                                                        "count: "
                                                        + totalNonPrimary + " program failed to handle this appropriately");
            }
            counts.put(FamilyType.ONE_PARENT, usableOneParents);
            counts.put(FamilyType.COUPLE_ONLY, usableCouples);
            counts.put(FamilyType.COUPLE_WITH_CHILDREN, 0);
            counts.put(FamilyType.OTHER_FAMILY, 0);

        }
        Log.debug("Non-Primary Basic Families: new non-primary families distribution: married: " + newCouplesCount +
                          " couple.with.child:" +
                          newCoupleWithChildCount + " one.parent:" + newOneParentFamilyCount + " other:" +
                          newOtherFamilyCount);

        return counts;
    }

    /**
     * Make extra married couples for non-primary COUPLE_ONLY and COUPLE_WITH_CHILDREN family units. This method uses the persons in Extras
     * and MarriedExtras list.
     *
     * @param newCouplesCount The number of new couples needed
     * @param familyFactory   The FamilyFactory instance to use when forming families
     * @return The list of newly formed couple families
     */
    private List<Family> makeNonPrimaryCoupleBasicUnits(int newCouplesCount, FamilyFactory familyFactory) {
        //We have already used all known married males and females at this stage. So we have no other way but to use
        // Extras.
        Log.debug("Non-Primary Basic Couple: remaining Extras: " + extrasHandler.remainingExtras());
        Log.debug("Non-Primary Basic Couple: remaining Married Extras: " + extrasHandler.remainingMarriedExtras());
        List<Person> extraMarriedMales = extrasHandler.getPersonsFromExtras(RelationshipStatus.MARRIED,
                                                                            Sex.Male,
                                                                            null,
                                                                            newCouplesCount);
        Log.debug("Non-Primary Basic Couple: Married males taken from extras: " + extraMarriedMales.size());
        Log.debug("Non-Primary Basic Couple: remaining Extras: " + extrasHandler.remainingExtras());
        Log.debug("Non-Primary Basic Couple: remaining Married Extras: " + extrasHandler.remainingMarriedExtras());

        List<Person> extraMarriedFemales = extrasHandler.getPersonsFromExtras(RelationshipStatus.MARRIED,
                                                                              Sex.Female,
                                                                              null,
                                                                              newCouplesCount);
        Log.debug("Non-Primary Basic Couple: Married females taken from extras: " + extraMarriedMales.size());
        Log.debug("Non-Primary Basic Couple: remaining Extras: " + extrasHandler.remainingExtras());
        Log.debug("Non-Primary Basic Couple: remaining Married Extras: " + extrasHandler.remainingMarriedExtras());

        return familyFactory.makeMarriedCouples(extraMarriedMales, extraMarriedFemales);
    }

    /**
     * Makes extra one parent basic units for non-primary ONE_PARENT family units. This method uses the persons in Extras. This method adds
     * extra persons to children list if exiting number of children is not sufficient.
     *
     * @param newOneParentFamilyCount The number of new one parent units needed
     * @param familyFactory           The FamilyFactory instance to use when forming families
     * @param loneParents             The existing list of Lone Parents
     * @param children                The existing list of children in the family
     * @return The list of newly formed one parent basic units
     */
    private List<Family> makeNonPrimaryOneParentBasicUnits(int newOneParentFamilyCount,
                                                           FamilyFactory familyFactory,
                                                           List<Person> loneParents,
                                                           List<Person> children) {

        int newLoneParentsCount = (loneParents.)
        //We have already used all Lone Parents. So using extras.
        Log.debug("Basic One-Parent: remaining Extras: " + extrasHandler.remainingExtras());
        List<Person> newLoneParents = extrasHandler.getPersonsFromExtras(RelationshipStatus.LONE_PARENT,
                                                                         null,
                                                                         null,
                                                                         newOneParentFamilyCount);
        Log.debug("Basic One-Parent: Lone Parents taken from extras: " + newLoneParents.size());
        Log.debug("Basic One-Parent: remaining Extras: " + extrasHandler.remainingExtras());

        int childrenToForm = newOneParentFamilyCount <= children.size() ?
                             0 :
                             newOneParentFamilyCount - children.size();
        Log.debug("Basic One-Parent: required Children: " + newOneParentFamilyCount);
        Log.debug("Basic One-Parent: remaining Children: " + children.size());
        if (childrenToForm > 0) {
            //We assume all children are under 15
            children.addAll(extrasHandler.getChildrenFromExtras(null, null, childrenToForm));
        }


        return familyFactory.makeAllOneParentBasicUnits(newLoneParents, children);
    }

    /**
     * Makes extra couple with child basic units for non-primary COUPLE_WITH_CHILD family units. This method uses the persons in Extras for
     * married couples if there are not enough couples. This method also adds extra persons to children list if exiting number of children
     * is not sufficient.
     *
     * @param newCoupleWithChildFamilyCount The number of new couple with child units needed
     * @param familyFactory                 The FamilyFactory instance to use when forming families
     * @param basicCouples                  The existing list of basic couple units
     * @param children                      The existing list of children in the family
     * @return The list of newly formed one parent basic units
     */
    private List<Family> makeNonPrimaryCoupleWithChildBasicUnits(int newCoupleWithChildFamilyCount,
                                                                 FamilyFactory familyFactory,
                                                                 List<Family> basicCouples,
                                                                 List<Person> children) {

        int basicCouplesToForm = newCoupleWithChildFamilyCount <= basicCouples.size() ?
                                 0 :
                                 newCoupleWithChildFamilyCount - basicCouples.size();
        Log.debug("Non-Primary Basic Couple With Children: required Couples: " + newCoupleWithChildFamilyCount);
        Log.debug("Non-Primary Basic Couple With Children: remaining Couples: " + basicCouples.size());
        if (basicCouplesToForm > 0) {
            Log.debug("Non-Primary Basic Couple With Children: adding Couples: " + basicCouplesToForm);
            basicCouples.addAll(makeNonPrimaryCoupleBasicUnits(basicCouplesToForm, familyFactory));
            Log.debug("Non-Primary Basic Couple With Children: remaining Couples: " + basicCouples.size());
        }


        int childrenToForm = newCoupleWithChildFamilyCount <= children.size() ?
                             0 :
                             newCoupleWithChildFamilyCount - children.size();
        Log.debug("Non-Primary Basic Couple With Children: required Children: " + newCoupleWithChildFamilyCount);
        Log.debug("Non-Primary Basic Couple With Children: remaining Children: " + children.size());
        if (childrenToForm > 0) {

            Log.debug("Non-Primary Basic Couple With Children: remaining Extras: " + extrasHandler.remainingExtras());
            Log.debug("Non-Primary Basic Couple With Children: adding U15 Children: " + childrenToForm);
            children.addAll(extrasHandler.getChildrenFromExtras(null, null, childrenToForm));
            Log.debug("Non-Primary Basic Couple With Children: remaining Children: " + children.size());
            Log.debug("Non-Primary Basic Couple With Children: remaining Extras: " + extrasHandler.remainingExtras());
        }

        return familyFactory.formCoupleWithChildFamilyBasicUnits(newCoupleWithChildFamilyCount, basicCouples, children);
    }

    private List<Family> makeNonPrimaryOtherFamilyBasicUnits(int newOtherFamilyCount,
                                                             FamilyFactory familyFactory,
                                                             List<Person> relatives) {

        Log.debug("Non-Primary Basic Other Family: required Other Families: " + newOtherFamilyCount);
        Log.debug("Non-Primary Basic Other Family: remaining Relatives: " + relatives.size());

        int neededRelatives = (relatives.size() >= 2 * newOtherFamilyCount) ?
                              0 :
                              (2 * newOtherFamilyCount) - relatives.size();

        if (neededRelatives > 0) {
            Log.debug("Non-Primary Basic Other Family: remaining Extras: " + extrasHandler.remainingExtras());
            Log.debug("Non-Primary Basic Other Family: adding Relatives: " + neededRelatives);
            relatives.addAll(extrasHandler.getPersonsFromExtras(RelationshipStatus.RELATIVE,
                                                                null,
                                                                null,
                                                                neededRelatives));
            Log.debug("Non-Primary Basic Other Family: remaining Relatives: " + relatives.size());
            Log.debug("Non-Primary Basic Other Family: remaining Extras: " + extrasHandler.remainingExtras());
        }
        return familyFactory.makeOtherFamilyBasicUnits(newOtherFamilyCount, relatives);
    }

    /**
     * Forms non-primary families needed to complete households
     *
     * @param hhRecords                            Household Records from data file
     * @param households                           all the households in the population
     * @param unusedCoupleOnlyBasic                The unused couple only basic units
     * @param unusedOneParentBasic                 The unused one parent basic units
     * @param children                             The list of children in the population
     * @param nonPrimaryCoupleWithChildProbability The probability of a non-primary family being a couple with child family if the primary
     *                                             family is also a couple with child family
     * @param familyFactory                        Instance of FamilyFactory class to construct required families
     */
    private void addNonPrimaryBasicFamiliesToHouseholds(List<HhRecord> hhRecords,
                                                        Map<FamilyHouseholdType, List<Household>> households,
                                                        List<Family> unusedCoupleOnlyBasic,
                                                        List<Family> unusedOneParentBasic,
                                                        List<Person> children,
                                                        List<Person> relatives,
                                                        double nonPrimaryCoupleWithChildProbability,
                                                        FamilyFactory familyFactory) {

        Map<FamilyType, Integer> nonPrimaryFamilyDist = getNonPrimaryFamilyDistribution(hhRecords,
                                                                                        households,
                                                                                        unusedCoupleOnlyBasic,
                                                                                        unusedOneParentBasic,
                                                                                        nonPrimaryCoupleWithChildProbability);

        int usableCouplesCount = nonPrimaryFamilyDist.get(FamilyType.COUPLE_ONLY) + nonPrimaryFamilyDist.get(FamilyType.COUPLE_WITH_CHILDREN);
        if (usableCouplesCount >= unusedCoupleOnlyBasic.size()) {
            // Create new non-primary couple only basic units
            int newCouplesCount = nonPrimaryFamilyDist.get(FamilyType.COUPLE_ONLY) + nonPrimaryFamilyDist.get(FamilyType.COUPLE_WITH_CHILDREN) - unusedCoupleOnlyBasic
                    .size();
            Log.debug("Non-Primary Basic Families: Remaining Couple only basic: " + unusedCoupleOnlyBasic.size());
            List<Family> newCouples = makeNonPrimaryCoupleBasicUnits(newCouplesCount, familyFactory);
            unusedCoupleOnlyBasic.addAll(newCouples);
            Log.debug("Non-Primary Basic Families: Remaining Couple only basic: " + unusedCoupleOnlyBasic.size());
        } else {
            //Remove unusable couple only basic units and add them to extras
            Collections.shuffle(unusedCoupleOnlyBasic, random);
            List<Family> extraCouples = new ArrayList<>(unusedCoupleOnlyBasic.subList(usableCouplesCount, unusedCoupleOnlyBasic.size()));
            unusedCoupleOnlyBasic.removeAll(extraCouples);
            extrasHandler.addToExtras(extraCouples.stream().map(c -> c.getMembers()).flatMap(List::stream).collect(Collectors.toList()));
        }

        //Create couple with child non-primary families
        List<Family> coupleWithChildBasic = makeNonPrimaryCoupleWithChildBasicUnits(nonPrimaryFamilyDist.get(FamilyType.COUPLE_WITH_CHILDREN),
                                                                                    familyFactory,
                                                                                    unusedCoupleOnlyBasic,
                                                                                    children);
        Log.debug("Non-Primary Basic Families: Remaining Couple only basic: " + unusedCoupleOnlyBasic.size());
        Log.debug("Non-Primary Basic Families: Remaining Children: " + children.size());
        Log.debug("Non-Primary Basic Families: Remaining Couple with child basic: " + coupleWithChildBasic.size());
        //Add couple with child families to households
        assignNonPrimaryFamilies(households, FamilyType.COUPLE_WITH_CHILDREN, coupleWithChildBasic, FamilyType.COUPLE_WITH_CHILDREN);
        Log.debug("Non-Primary Basic Families: Remaining Couple with child basic: " + coupleWithChildBasic.size());
        Log.debug("Non-Primary Basic Families: Remaining Children: " + children.size());

        //Creating one parent families as non-primary families
        if (nonPrimaryFamilyDist.get(FamilyType.ONE_PARENT) >= unusedOneParentBasic.size()) {
            int newOneParentFamilyCount = nonPrimaryFamilyDist.get(FamilyType.ONE_PARENT) - unusedOneParentBasic.size();
            unusedOneParentBasic.addAll(makeNonPrimaryOneParentBasicUnits(newOneParentFamilyCount,
                                                                          familyFactory,
                                                                          children));
            Log.debug("Non-Primary Basic Families: Remaining One parent basic: " + unusedOneParentBasic.size());
            Log.debug("Non-Primary Basic Families: Remaining Children: " + children.size());
        } else {

            Log.debug("Non-Primary Basic Families: Remaining One parent Basic: " + unusedOneParentBasic.size());
            Log.debug("Non-Primary Basic Families: Usable One parent Basic: " + nonPrimaryFamilyDist.get(FamilyType.ONE_PARENT));

            //Remove unusable couple only basic units and add them to extras
            Collections.shuffle(unusedOneParentBasic, random);
            List<Family> extraOneParentUnits = new ArrayList<>(unusedOneParentBasic.subList(nonPrimaryFamilyDist.get(FamilyType.ONE_PARENT),
                                                                                            unusedOneParentBasic.size()));
            unusedOneParentBasic.removeAll(extraOneParentUnits);

            Log.debug("Non-Primary Basic Families: Adding unusable one parent Basic to extras and children: " + extraOneParentUnits.size());
            //add lone parents to extras
            extrasHandler.addToExtras(extraOneParentUnits.stream()
                                                         .map(f -> f.getMembers())
                                                         .flatMap(List::stream)
                                                         .filter(p -> p.getRelationshipStatus() == RelationshipStatus.LONE_PARENT)
                                                         .collect(Collectors.toList()));
            Log.debug("Non-Primary Basic Families: Remaining extras: " + extrasHandler.remainingExtras());

            //add children to back children lists
            children.addAll(extraOneParentUnits.stream()
                                               .map(f -> f.getMembers())
                                               .flatMap(List::stream)
                                               .filter(p -> Arrays.asList(RelationshipStatus.U15_CHILD,
                                                                          RelationshipStatus.STUDENT,
                                                                          RelationshipStatus.O15_CHILD).contains(p.getRelationshipStatus()))
                                               .collect(Collectors.toList()));
            Log.debug("Non-Primary Basic Families: Remaining children: " + children.size());
        }


        //Add one parent families to households
        assignNonPrimaryFamilies(households,
                                 FamilyType.ONE_PARENT,//Type of the newly added family
                                 unusedOneParentBasic,
                                 FamilyType.ONE_PARENT, //List of eligible families
                                 FamilyType.COUPLE_WITH_CHILDREN);
        Log.debug("Non-Primary Basic Families: Remaining One parent basic: " + unusedOneParentBasic.size());


        // Add couple only non-primary families to households
        assignNonPrimaryFamilies(households,
                                 FamilyType.COUPLE_ONLY,
                                 unusedCoupleOnlyBasic,
                                 FamilyType.COUPLE_WITH_CHILDREN,
                                 FamilyType.ONE_PARENT,
                                 FamilyType.COUPLE_ONLY,
                                 FamilyType.OTHER_FAMILY);
        Log.debug("Non-Primary Basic Families: Remaining Couple only basic: " + unusedCoupleOnlyBasic.size());

        //Create other family non-primary families
        Log.debug("Non-Primary Basic Families: Remaining Relatives: " + relatives.size());
        List<Family> newOtherFamilies = makeNonPrimaryOtherFamilyBasicUnits(nonPrimaryFamilyDist.get(FamilyType.OTHER_FAMILY),
                                                                            familyFactory,
                                                                            relatives);
        //Add other family non-primary families to households
        assignNonPrimaryFamilies(households,
                                 FamilyType.OTHER_FAMILY,
                                 newOtherFamilies,
                                 FamilyType.COUPLE_WITH_CHILDREN,
                                 FamilyType.ONE_PARENT,
                                 FamilyType.COUPLE_ONLY,
                                 FamilyType.OTHER_FAMILY);

        // TODO: remove this
        households.values().stream().flatMap(List::stream).forEach(h -> {
            if (h.getExpectedFamilyCount() != h.getCurrentFamilyCount()) {
                throw new IllegalStateException("Family count wrong: " + h.getExpectedSize() + " person:" + h
                        .getFamilyHouseholdType() + " has only " + h
                        .getCurrentFamilyCount() + " families");
            }
        });


    }

    /**
     * Assigns the available basic units to multi-family households as non-primary families. This method filters in the households where the
     * primary family belong to any of the eligible FamilyType(s) specified. Then households are randomly selected for each assignment. A
     * household may be selected multiple times if it can contain multiple non-primary families (e.g. 3 family households). The FamilyType
     * of the newly added family is set to FamilyType specified as nonPrimaryFamilyType. This method alters the list of available basic
     * family units. This method assumes primary family of a multifamily household only consists of its basic members.
     *
     * @param householdsMap        The map of households by FamilyHouseholdType
     * @param nonPrimaryFamilyType The FamilyType of the newly added non primary families
     * @param basicUnits           The list of basic family units to be added as non-primary families to multi-family (must contain families
     *                             of only one FamilyType) households in @param households
     * @param eligibleFamilyTypes  The array of FamilyType(s) that the primary family of the household can belong to.
     */
    private void assignNonPrimaryFamilies(Map<FamilyHouseholdType, List<Household>> householdsMap,
                                          FamilyType nonPrimaryFamilyType,
                                          List<Family> basicUnits,
                                          FamilyType... eligibleFamilyTypes) {

        Log.info("Adding " + nonPrimaryFamilyType + " as non primary");
        Log.debug(nonPrimaryFamilyType + ": available family units: " + basicUnits.size());

        List<Household> eligibleHhs = householdsMap.entrySet()
                                                   .stream()
                                                   .filter(e -> Arrays.asList(eligibleFamilyTypes).contains(e.getKey().getFamilyType()) && e
                                                           .getKey()
                                                           .getFamilyCount() > 1) //Filter hhs that belong to specified family types and have multiple
                                                   // families
                                                   .map(Map.Entry::getValue) //Get the values list from each hash map entry
                                                   .flatMap(List::stream) //flatten list of value lists to one large list
                                                   .filter(h -> h.getExpectedSize() - h.getCurrentSize() >= nonPrimaryFamilyType.basicSize()) //has
                                                   // enough vacancies
                                                   .filter(h -> h.getExpectedFamilyCount() > h.getCurrentFamilyCount())
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
    }


    /**
     * Constructs all family households (except Lone person and group households) and adds a suitable basic primary family unit. Input lists
     * are altered by this method.
     *
     * @param hhRecs                  Household records from data files
     * @param coupleOnlyBasic         couple only basic family units
     * @param coupleWithChildrenBasic couple with children basic family units
     * @param oneParentBasicFamilies  one parent basic family units
     * @param otherFamilyBasic        other family basic family unis
     * @return Map of all partially formed family households against their FamilyHouseholdType
     */
    private Map<FamilyHouseholdType, List<Household>> formHouseholdsWithPrimaryFamilies(List<HhRecord> hhRecs,
                                                                                        List<Family> coupleOnlyBasic,
                                                                                        List<Family>
                                                                                                coupleWithChildrenBasic,
                                                                                        List<Family>
                                                                                                oneParentBasicFamilies,
                                                                                        List<Family> otherFamilyBasic) {

        Log.info("Populating all family households with primary families");
        Collections.shuffle(coupleOnlyBasic, random);
        Collections.shuffle(coupleWithChildrenBasic, random);
        Collections.shuffle(oneParentBasicFamilies, random);
        Collections.shuffle(otherFamilyBasic, random);

        Map<FamilyHouseholdType, List<Household>> basicHouseholds = new HashMap<>();

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
            if (hhRec.HH_COUNT > 0) {
                for (int i = 0; i < hhRec.HH_COUNT; i++) {
                    if (primaryFamilyUnitsList.isEmpty()) {
                        throw new NotEnoughPersonsException(hhRec.FAMILY_HOUSEHOLD_TYPE + ": Not enough basic family " +
                                                                    "units ");
                    }
                    Household household = new Household(hhRec.NUM_OF_PERSONS_PER_HH,
                                                        hhRec.FAMILY_HOUSEHOLD_TYPE,
                                                        sa2name);
                    household.addFamily(primaryFamilyUnitsList.remove(0));
                    household.setPrimaryFamilyType(hhRec.getPrimaryFamilyType());
                    familyHouseholds.add(household);
                    formed++;
                }

                basicHouseholds.computeIfAbsent(hhRec.FAMILY_HOUSEHOLD_TYPE, v -> {
                    return new ArrayList<>();
                }).addAll(familyHouseholds);

                Log.info(hhRec.NUM_OF_PERSONS_PER_HH + " " + hhRec.FAMILY_HOUSEHOLD_TYPE + ": formed households: " +
                                 formed);
            }
        }
        Log.info("All households populated with primary families");
        return basicHouseholds;
    }


    /**
     * Creates all lone person households and add them to the global list of households.
     *
     * @param hhRecs  Household data records
     * @param indRecs Individual data records.
     */
    private void makeLonePersonHouseholds(List<HhRecord> hhRecs, List<IndRecord> indRecs) {
        List<HhRecord> lnPersonHhs = DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                                FamilyHouseholdType
                                                                                        .LONE_PERSON);

        List<Person> allPersons = PersonsFactory.makeAllPersonsByRelationshipType(indRecs,
                                                                                  RelationshipStatus.LONE_PERSON);

        int hhCount = lnPersonHhs.get(0).HH_COUNT;// Only 1 member households have lone persons
        int diff = hhCount - allPersons.size();

        List<Household> hhList = new ArrayList<>();

        for (int i = 0; i < lnPersonHhs.get(0).HH_COUNT; i++) {
            Family f = new Family(FamilyType.LONE_PERSON);
            if (allPersons.isEmpty()) {
                throw new NotEnoughPersonsException(lnPersonHhs.get(0).FAMILY_HOUSEHOLD_TYPE + ": Not enough lone " +
                                                            "persons");
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
        List<HhRecord> grpHhRecs = DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                              FamilyHouseholdType
                                                                                      .GROUP_HOUSEHOLD);

        List<Person> grpMembers = PersonsFactory.makeAllPersonsByRelationshipType(indRecs,
                                                                                  RelationshipStatus.GROUP_HOUSEHOLD);

        List<Household> grpHhs = new ArrayList<>();
        int totalGroupHhs = 0;
        for (HhRecord hhRec : grpHhRecs) {

            int hhCount = hhRec.HH_COUNT;
            totalGroupHhs += hhRec.HH_COUNT;
            int hhSize = hhRec.NUM_OF_PERSONS_PER_HH;

            for (int i = 0; i < hhCount; i++) {
                Family f = new Family(FamilyType.GROUP_HOUSEHOLD);
                if (hhSize > grpMembers.size()) {
                    throw new NotEnoughPersonsException(hhRec.FAMILY_HOUSEHOLD_TYPE + ": Not enough group household "
                                                                + "persons");
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
