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
    private ExtrasHandler extrasHandler;

    public GroupMaker(double maleProbability, double relativeProbability, double femaleLoneParentProbability) {
        this.maleProbability = maleProbability;
        this.relativeProbability = relativeProbability;
        this.maleLoneParentProbability = femaleLoneParentProbability;
    }

    List<Household> makePopulation(List<HhRecord> hhRecs,
                                   List<IndRecord> indrecs,
                                   Random rand,
                                   String sa2,
                                   double nonPrimaryCoupleWithChildProbability) {
        this.sa2name = sa2;
        // printHhSummary(hhRecs)
        // printIndSummary(indrecs);

        this.random = rand;
        extrasHandler = new ExtrasHandler(hhRecs, indrecs, maleProbability, random);
        Log.info("Extras (difference between households and persons files): " + extrasHandler.remainingExtras());

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

        List<Family> basicCouples = familyFactory.makeMarriedCouples(marriedMales, marriedFemales);
        Log.debug("Remaining Married males: " + marriedMales.size());
        Log.debug("Remaining Married females: " + marriedFemales.size());
        Log.debug("Remaining Basic couples: " + basicCouples.size());

        //Save extra married persons for later use. If married females list is not empty we had extra females, otherwise we had extra males,
        //because makeMarriedCouples() function keeps forming couples until one list exhausts.
        extrasHandler.setExtraMarriedPersons((!marriedFemales.isEmpty()) ? marriedFemales : marriedMales);


        List<Family> oneParentBasic = familyFactory.makeAllOneParentBasicUnits(loneParents, children);
        Log.debug("Remaining Lone parent persons: " + loneParents.size());
        Log.debug("Remaining Children: " + children.size());

        //Form basic family structures and removes couples from married males and females list
        List<HhRecord> coupleWChildRecs = DataReader.getHouseholdsRecordsByPrimaryFamilyType(hhRecs,
                                                                                             FamilyHouseholdType.F1_COUPLE_WITH_CHILDREN,
                                                                                             FamilyHouseholdType.F2_COUPLE_WITH_CHILDREN,
                                                                                             FamilyHouseholdType.F3_COUPLE_WITH_CHILDREN);
        int fCount = coupleWChildRecs.stream().mapToInt(r -> r.HH_COUNT).sum();
        List<Family> primaryCoupleWChildFamilyBasic = familyFactory.makeCoupleWithChildFamilyBasicUnits(fCount,
                                                                                                        basicCouples,
                                                                                                        children);
        Log.debug("Remaining Basic couples: " + basicCouples.size());
        Log.debug("Remaining Children: " + children.size());

        // Forms Other Family basic family structures
        List<HhRecord> otherFamiliesRecs = DataReader.getHouseholdsRecordsByPrimaryFamilyType(hhRecs,
                                                                                              FamilyHouseholdType.F1_OTHER_FAMILY,
                                                                                              FamilyHouseholdType.F2_OTHER_FAMILY,
                                                                                              FamilyHouseholdType.F3_OTHER_FAMILY);
        fCount = otherFamiliesRecs.stream().mapToInt(r -> r.HH_COUNT).sum();
        List<Family> primaryOtherFamiliesBasic = familyFactory.makeOtherFamilyBasicUnits(fCount, relatives);
        Log.debug("Remaining Relatives: " + relatives.size());

        try {
            Map<FamilyHouseholdType, List<Household>> basicHouseholds = formHouseholdsWithPrimaryFamilies(
                    hhRecs,
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
            //We don't complete 1 family one parent and couple with children families at this stage because those families
            //can have both children and relatives. We are not sure what to use at this stage. So, will complete them later.

            //Add 2nd and 3rd families to multi-family households.
            Log.info("Starting to add non-primary families to multi-family households");
            Log.debug("Remaining Children: " + children.size());
            Log.debug("Remaining Couples basic: " + basicCouples.size());
            Log.debug("Remaining Couple with children basic: " + primaryCoupleWChildFamilyBasic.size());
            Log.debug("Remaining Other family basic: " + primaryOtherFamiliesBasic.size());
            Log.debug("Remaining One parent basic: " + oneParentBasic.size());

            addNonPrimaryBasicFamiliesToHouseholds(basicHouseholds,
                                                   basicCouples,
                                                   oneParentBasic,
                                                   children,
                                                   relatives,
                                                   nonPrimaryCoupleWithChildProbability,
                                                   familyFactory);
            //            Log.debug("Remaining primary couple only family basic structures: " + primaryCoupleOnlyFamilyBasic.size());
            Log.debug("Remaining married males (not expected to change): " + marriedMales.size());
            Log.debug("Remaining married females (not expected to change): " + marriedFemales.size());
            Log.debug("Remaining relatives: " + relatives.size());
            Log.debug("Remaining extras: " + extrasHandler.remainingExtras());

        } catch (NotEnoughPersonsException npex) {
            Log.debug("Remaining Couples basic: " + basicCouples.size());
            Log.debug("Remaining Relatives: " + relatives.size());
            Log.debug("Remaining Couple with children basic: " + primaryCoupleWChildFamilyBasic.size());
            Log.debug("Remaining Other family basic: " + primaryOtherFamiliesBasic.size());
            Log.debug("Remaining Children: " + children.size());
            Log.debug("Remaining Extras: " + extrasHandler.remainingExtras());
            Log.errorAndExit("Family households construction failed", npex, ExitCode.DATA_ERROR);
        }

        Utils.summary(allHouseholds);
        return allHouseholds;
    }

    /**
     * Calculates the number of additional COUPLE_ONLY, ONE_PARENT and OTHER_FAMILY basic units that needs to be created
     * for non-primary families based on distributions observed in primary families. The calculation takes into account
     * the number of existing couple and one parent basic units. Both COUPLE_ONLY and COUPLE_WITH_CHILDREN primary
     * families are counted as COUPLE_ONLY basic units. This is because though there is a large number of
     * COUPLE_WITH_CHILDREN primary family households in the population we don't expect we don't expect a similar large
     * number of COUPLE_WITH_CHILDREN non-primary families in households. The idea here is counting the total number of
     * couples and later converting a suitable portion of couples to COUPLE_WITH_CHILDREN family units. This portion
     * determined based on domain knowledge.
     *
     * @param households            The map of households categorised by FamilyHouseholdType
     * @param unusedCoupleOnlyBasic The list of remaining COUPLE_ONLY basic units
     * @param unusedOneParentBasic  The list of remaining ONE_PARENT basic units
     * @return The number of additional family units to create by FamilyType
     */
    private Map<FamilyType, Integer> calculateUnknownNonPrimaryFamilyDistribution(Map<FamilyHouseholdType, List<Household>> households,
                                                                                  List<Family> unusedCoupleOnlyBasic,
                                                                                  List<Family> unusedOneParentBasic) {
        int couples = 0, coupleWithChild = 0, oneParent = 0, other = 0, totalNonPrimary = 0;
        for (FamilyHouseholdType fht : households.keySet()) {
            totalNonPrimary += households.get(fht)
                    .size() * (fht.getFamilyCount() - 1); //Count 1 for each 2 family hh and 2 for each 3 family hh
            switch (fht.getFamilyType()) {
                case COUPLE_ONLY:
                    couples += households.get(fht).size();
                    break;
                case COUPLE_WITH_CHILDREN:
                    couples += households.get(fht).size();
                    break;
                case ONE_PARENT:
                    oneParent += households.get(fht).size();
                    break;
                case OTHER_FAMILY:
                    other += households.get(fht).size();
                    break;
                default:
                    throw new IllegalStateException("The Family Type of the primary family cannot be: " + fht.getFamilyType());
            }
        }
        int unknownFamilies = totalNonPrimary - unusedCoupleOnlyBasic.size() - unusedOneParentBasic.size();

        Log.debug("Non-Primary Basic Families: Primary family distribution: couples:" + couples + " one.parent:" + oneParent + " other:" + other);
        Log.debug("Non-Primary Basic Families: Total non-primary families: " + totalNonPrimary);
        Log.debug("Non-Primary Basic Families: new non-primary families to form: " + unknownFamilies);

        Map<FamilyType, Integer> counts = new HashMap<>(4, 1);
        int newCouplesCount = 0, newOneParentFamilyCount = 0, newOtherFamilyCount = 0;
        if (unknownFamilies > 0) {
            float divisor = (float) (couples + oneParent + other);
            newCouplesCount = Math.round(unknownFamilies * couples / divisor);
            counts.put(FamilyType.COUPLE_ONLY, newCouplesCount);

            newOneParentFamilyCount = Math.round(unknownFamilies * oneParent / divisor);
            counts.put(FamilyType.ONE_PARENT, newOneParentFamilyCount);

            newOtherFamilyCount = unknownFamilies - (newCouplesCount + newOneParentFamilyCount);
            counts.put(FamilyType.OTHER_FAMILY, newOtherFamilyCount);


            if (unknownFamilies < (newCouplesCount + newOneParentFamilyCount + newOtherFamilyCount)) {
                throw new IllegalStateException(
                        "The sum of new non-primary couples, one parent units and other families is larger than the actual unknown family count");
            }
        }
        Log.debug("Non-Primary Basic Families: new non-primary families distribution: couples:" + newCouplesCount + " one.parent:" + newOneParentFamilyCount + " other:" + newOtherFamilyCount);

        return counts;

    }

    /**
     * Make extra married couples for non-primary COUPLE_ONLY and COUPLE_WITH_CHILDREN family units. This method uses
     * the persons in Extras and MarriedExtras list.
     *
     * @param newCouplesCount The number of new couples needed
     * @param familyFactory   The FamilyFactory instance to use when forming families
     * @return The list of newly formed couple families
     */
    private List<Family> makeNonPrimaryCoupleBasicUnits(int newCouplesCount, FamilyFactory familyFactory) {
        //We have already used all known married males and females at this stage. So we have no other way but to use Extras.
        List<Person> extraMarriedMales = extrasHandler.getPersonsFromExtras(RelationshipStatus.MARRIED,
                                                                            Sex.Male, null,
                                                                            newCouplesCount);
        Log.debug("Non-Primary Basic Families: The number of married males from extras: " + extraMarriedMales.size());
        Log.debug("Non-Primary Basic Families: Remaining Extras: " + extrasHandler.remainingExtras());
        Log.debug("Non-Primary Basic Families: Remaining Married Extras: " + extrasHandler.remainingExtraMarried());

        List<Person> extraMarriedFemales = extrasHandler.getPersonsFromExtras(RelationshipStatus.MARRIED,
                                                                              Sex.Female, null,
                                                                              newCouplesCount);
        Log.debug("Non-Primary Basic Families: The number of married females from extras: " + extraMarriedMales.size());
        Log.debug("Non-Primary Basic Families: Remaining Extras: " + extrasHandler.remainingExtras());
        Log.debug("Non-Primary Basic Families: Remaining Married Extras: " + extrasHandler.remainingExtraMarried());

        return familyFactory.makeMarriedCouples(extraMarriedMales, extraMarriedFemales);
    }

    /**
     * Makes extra one parent basic units for non-primary ONE_PARENT family units. This method uses the persons in
     * Extras. This method adds extra persons to children list if exiting number of children is not sufficient.
     *
     * @param newOneParentFamilyCount The number of new one parent units needed
     * @param familyFactory           The FamilyFactory instance to use when forming families
     * @param children                The existing list of children in the family
     * @return The list of newly formed one parent basic units
     */
    private List<Family> makeNonPrimaryOneParentBasicUnits(int newOneParentFamilyCount,
                                                           FamilyFactory familyFactory,
                                                           List<Person> children) {
        //We have already used all Lone Parents. So using extras.
        List<Person> newLoneParents = extrasHandler.getPersonsFromExtras(RelationshipStatus.LONE_PARENT,
                                                                         null, null,
                                                                         newOneParentFamilyCount);
        Log.debug("Non-Primary Basic Families: The number of lone parents from extras: " + newLoneParents.size());
        Log.debug("Non-Primary Basic Families: Remaining Extras: " + extrasHandler.remainingExtras());

        int neededChildren = (children.size() >= newOneParentFamilyCount) ? 0 : newOneParentFamilyCount - children.size();
        //We assume all the needed children are U15
        List<Person> newChildren = extrasHandler.getPersonsFromExtras(RelationshipStatus.U15_CHILD,
                                                                      null,
                                                                      null,
                                                                      neededChildren);
        Log.debug("Non-Primary Basic Families: The number of children from extras: " + newChildren.size());
        Log.debug("Non-Primary Basic Families: Remaining Extras: " + extrasHandler.remainingExtras());
        children.addAll(newChildren);
        Log.debug("Non-Primary Basic Families: Remaining Children: " + children.size());

        return familyFactory.makeAllOneParentBasicUnits(newLoneParents, children);
    }
    
    /**
     * Forms non-primary families needed to complete households
     *
     * @param households                           all the households in the population
     * @param unusedCoupleOnlyBasic                The unused couple only basic units
     * @param unusedOneParentBasic                 The unused one parent basic units
     * @param children                             The list of children in the population
     * @param nonPrimaryCoupleWithChildProbability The probability of a non-primary family being a couple with child
     *                                             family if the primary family is also a couple with child family
     * @param familyFactory                        Instance of FamilyFactory class to construct required families
     */
    private void addNonPrimaryBasicFamiliesToHouseholds(Map<FamilyHouseholdType, List<Household>> households,
                                                        List<Family> unusedCoupleOnlyBasic,
                                                        List<Family> unusedOneParentBasic,
                                                        List<Person> children,
                                                        List<Person> relatives,
                                                        double nonPrimaryCoupleWithChildProbability,
                                                        FamilyFactory familyFactory) {

        Map<FamilyType, Integer> newFamilyDist = calculateUnknownNonPrimaryFamilyDistribution(households,
                                                                                              unusedCoupleOnlyBasic,
                                                                                              unusedOneParentBasic);


        unusedCoupleOnlyBasic.addAll(makeNonPrimaryCoupleBasicUnits(newFamilyDist.get(FamilyType.COUPLE_ONLY),
                                                                    familyFactory));
        Log.debug("Non-Primary Basic Families: Remaining Couple only basic: " + unusedCoupleOnlyBasic.size());

        //Count the couple with children households that can have 6 or more members. These can have couple with children family for non-primary
        int coupleWithChildEligibleFamilyCount = (int) households.entrySet()
                .stream()
                .filter(e -> e.getKey() == FamilyHouseholdType.F2_COUPLE_WITH_CHILDREN || e.getKey() == FamilyHouseholdType.F3_COUPLE_WITH_CHILDREN)
                .map(e -> e.getValue()) //Get the a list of households lists (technically its a stream of multiple lists)
                .flatMap(List::stream) // merge the lists
                .filter(hh -> hh.getExpectedSize() >= 6) //filter ones that can have 6 or more members (so we can have 2 families each with 3 members)
                .count();
        int newCoupleWithChildCount = (int) Math.round(coupleWithChildEligibleFamilyCount * nonPrimaryCoupleWithChildProbability);
        if (newCoupleWithChildCount > 0) {
            Log.debug("Non-Primary Basic Families: Remaining Couple only basic: " + unusedCoupleOnlyBasic.size());
            Log.debug("Non-Primary Basic Families: Remaining Children: " + children.size());
            List<Family> coupleWithChildFamilies = familyFactory.makeCoupleWithChildFamilyBasicUnits(
                    newCoupleWithChildCount,
                    unusedCoupleOnlyBasic,
                    children);
            assignNonPrimaryFamilies(households,
                                     FamilyType.COUPLE_WITH_CHILDREN,
                                     coupleWithChildFamilies,
                                     FamilyType.COUPLE_WITH_CHILDREN);

        }
        Log.debug("Non-Primary Basic Families: Remaining Children: " + children.size());
        Log.debug("Non-Primary Basic Families: Remaining Couple only basic: " + unusedCoupleOnlyBasic.size());

        //Creating and adding one parent families as non-primary families
        unusedOneParentBasic.addAll(makeNonPrimaryOneParentBasicUnits(newFamilyDist.get(FamilyType.ONE_PARENT),
                                                                      familyFactory,
                                                                      children));
        Log.debug("Non-Primary Basic Families: Remaining One parent basic: " + unusedOneParentBasic.size());


        if (!unusedOneParentBasic.isEmpty()) {
            assignNonPrimaryFamilies(households,
                                     FamilyType.ONE_PARENT,//Type of the newly added family
                                     unusedOneParentBasic,
                                     FamilyType.ONE_PARENT, //List of eligible families
                                     FamilyType.COUPLE_WITH_CHILDREN);
        }

        Log.debug("Non-Primary Basic Families: Remaining One parent basic: " + unusedOneParentBasic.size());


        if (!unusedCoupleOnlyBasic.isEmpty()) {
            assignNonPrimaryFamilies(households,
                                     FamilyType.COUPLE_ONLY,
                                     unusedCoupleOnlyBasic,
                                     FamilyType.COUPLE_WITH_CHILDREN,
                                     FamilyType.ONE_PARENT,
                                     FamilyType.COUPLE_ONLY,
                                     FamilyType.OTHER_FAMILY);
        }

        int newOtherFamilyCount = newFamilyDist.get(FamilyType.OTHER_FAMILY);
        if (newOtherFamilyCount > 0) {
            int neededRelatives = (relatives.size() >= 2 * newOtherFamilyCount) ? 0 : (2 * newOtherFamilyCount) - relatives
                    .size();
            List<Person> newRelatives = extrasHandler.getPersonsFromExtras(RelationshipStatus.RELATIVE,
                                                                           null,
                                                                           null,
                                                                           neededRelatives);
            relatives.addAll(newRelatives);
            List<Family> newOtherFamilies = familyFactory.makeOtherFamilyBasicUnits(1, relatives);

            assignNonPrimaryFamilies(households,
                                     FamilyType.OTHER_FAMILY,
                                     newOtherFamilies,
                                     FamilyType.COUPLE_WITH_CHILDREN,
                                     FamilyType.ONE_PARENT,
                                     FamilyType.COUPLE_ONLY,
                                     FamilyType.OTHER_FAMILY);
        }

        households.values().stream().flatMap(List::stream).forEach(h -> {
            if (h.getExpectedFamilyCount() != h.getCurrentFamilyCount()) {
                throw new IllegalStateException("Family count wrong: " + h.getExpectedSize() + ":" + h.getFamilyHouseholdType() + " has only " + h
                        .getCurrentFamilyCount());
            }
        });


    }

    /**
     * * Assigns the available basic units to multi-family households as non-primary families. This method filters in
     * the households where the primary family belong to any of the eligible FamilyType(s) specified. Then households
     * are randomly selected for each assignment. A household may be selected multiple times if it can contain multiple
     * non-primary families (e.g. 3 family households). The FamilyType of the newly added family is set to FamilyType
     * specified as nonPrimaryFamilyType. This method alters the list of available basic family units. This method
     * assumes primary family of a multifamily household only consists of its basic members.
     *
     * @param householdsMap        The map of households by FamilyHouseholdType
     * @param nonPrimaryFamilyType The FamilyType of the newly added non primary families
     * @param basicUnits           The list of basic family units to be added as non-primary families to multi-family
     *                             (must contain families of only one FamilyType) households in @param households
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
                .filter(e -> Arrays.asList(eligibleFamilyTypes).contains(e.getKey().getFamilyType()) && e.getKey()
                        .getFamilyCount() > 1) //Filter hhs that belong to specified family types and have multiple families
                .map(e -> e.getValue()) //Get the values list from each hash map entry
                .flatMap(List::stream) //flatten list of value lists to one large list
                .filter(h -> h.getExpectedSize() - h.getCurrentSize() >= basicUnits.get(0)
                        .size()) //has enough vacancies
                .filter(h -> h.getExpectedFamilyCount() > h.getCurrentFamilyCount())
                .collect(Collectors.toList()); //Convert to an actual list

        Log.debug(nonPrimaryFamilyType + ": total eligible households: " + eligibleHhs.size());

        int added = 0, completed = 0;
        while (!basicUnits.isEmpty()) {
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
     * Constructs all family households (except Lone person and group households) and adds a suitable basic primary
     * family unit. Input lists are altered by this method.
     *
     * @param hhRecs                  Household records from data files
     * @param coupleOnlyBasic         couple only basic family units
     * @param coupleWithChildrenBasic couple with children basic family units
     * @param oneParentBasicFamilies  one parent basic family units
     * @param otherFamilyBasic        other family basic family unis
     * @return Map of all partially formed family households against their FamilyHouseholdType
     */
    private Map<FamilyHouseholdType, List<Household>> formHouseholdsWithPrimaryFamilies(List<HhRecord> hhRecs,
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
                        throw new NotEnoughPersonsException(hhRec.FAMILY_HOUSEHOLD_TYPE + ": Not enough basic family units ");
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

                Log.info(hhRec.NUM_OF_PERSONS_PER_HH + " " + hhRec.FAMILY_HOUSEHOLD_TYPE + ": formed households: " + formed);
            }
        }
        Log.info("All households populated with primary families");
        return basicHouseholds;
    }


    /**
     * Completes householdsMap by adding relatives to the families that are larger than 2 members. If there is not
     * enough relatives Extras are converted to relatives. This method does not check the number of families in the
     * household. This method modifies the input lists.
     *
     * @param householdsMap       The one family households map to complete by only adding relatives
     * @param relatives           The list of relatives in the population
     * @param familyHouseholdType FamilyHouseholdType of the households to be completed
     */
    private void completeHouseholdsWithRelatives(Map<FamilyHouseholdType, List<Household>> householdsMap,
                                                 List<Person> relatives,
                                                 FamilyHouseholdType familyHouseholdType) {
        Collections.shuffle(relatives, random);
        Log.debug(familyHouseholdType + ": Available households: " + householdsMap.get(familyHouseholdType).size());
        int formed = 0;
        for (Household h : householdsMap.get(familyHouseholdType)) {
            int diff = h.getExpectedSize() - h.getCurrentSize();
            if (diff > 0) {
                Family f = h.getPrimaryFamily();
                if (relatives.size() > diff) {
                    f.addMembers(relatives.subList(0, diff));
                    relatives.subList(0, diff).clear();
                } else {
                    extrasHandler.addMembersToFamilyFromExtras(f, RelationshipStatus.RELATIVE, diff - relatives.size());
                }
                formed++;
            }

        }

        Log.info(familyHouseholdType + ": Updated households: " + formed);
        Log.info(familyHouseholdType + ": All households created");
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
        List<HhRecord> lnPersonHhs = DataReader.getHouseholdsRecordsByPrimaryFamilyType(hhRecs,
                                                                                        FamilyHouseholdType.LONE_PERSON);

        List<Person> allPersons = PersonsFactory.makeAllPersonsByRelationshipType(indRecs,
                                                                                  RelationshipStatus.LONE_PERSON);

        int hhCount = lnPersonHhs.get(0).HH_COUNT;// Only 1 member households have lone persons
        int diff = hhCount - allPersons.size();

        List<Household> hhList = new ArrayList<>();

        for (int i = 0; i < lnPersonHhs.get(0).HH_COUNT; i++) {
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
        List<HhRecord> grpHhRecs = DataReader.getHouseholdsRecordsByPrimaryFamilyType(hhRecs,
                                                                                      FamilyHouseholdType.GROUP_HOUSEHOLD);

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
