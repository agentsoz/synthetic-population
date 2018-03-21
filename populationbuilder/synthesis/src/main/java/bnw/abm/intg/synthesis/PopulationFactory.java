package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.GlobalConstants;
import bnw.abm.intg.util.Log;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    List<Household> makePopulation() {

        formAllPersons();
        formAllKnownFamilies();
        List<Household> allHouseholds = null;
        try {
            allHouseholds = new ArrayList<>(formHouseholds());
            for (Household h : allHouseholds) {
                if (!h.validate()) {
                    Log.error("Bad state in" + h);
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
            Log.errorAndExit("Household constrcuction failed", GlobalConstants.ExitCode.PROGERROR);
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

        Log.info("Forming Basic Couple families: " + Math.min(marriedMales.size(), marriedFemales.size()));
        basicCouples = familyFactory.formCoupleFamilyBasicUnits(Math.min(marriedMales.size(), marriedFemales.size()),
                                                                marriedMales,
                                                                marriedFemales);
        Log.debug("Remaining Married males: " + marriedMales.size());
        Log.debug("Remaining Married females: " + marriedFemales.size());
        Log.debug("Remaining Basic couples: " + basicCouples.size());

        Log.info("Forming Basic One Parent families: " + loneParents.size());
        basicOneParentFamilies = familyFactory.formOneParentBasicUnits(loneParents.size(), loneParents, children);
        Log.debug("Remaining Lone parent persons: " + loneParents.size());
        Log.debug("Remaining Children: " + children.size());
        Log.debug("Remaining Basic One Parent families: " + basicOneParentFamilies.size());

        //Form basic family structures and removes couples from married males and females list
        List<HhRecord> coupleWChildRecs = DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                                     FamilyHouseholdType.F1_COUPLE_WITH_CHILDREN,
                                                                                     FamilyHouseholdType.F2_COUPLE_WITH_CHILDREN,
                                                                                     FamilyHouseholdType.F3_COUPLE_WITH_CHILDREN);
        int fCount = coupleWChildRecs.stream().mapToInt(r -> r.HH_COUNT).sum();
        Log.info("Forming Basic Couple with Children families: " + fCount);
        basicPrimaryCoupleWithChildFamilies = familyFactory.formCoupleWithChildFamilyBasicUnits(fCount, basicCouples, children);
        Log.debug("Remaining Basic couples: " + basicCouples.size());
        Log.debug("Remaining Children: " + children.size());
        Log.debug("Remaining Basic Couple with Children families: " + basicPrimaryCoupleWithChildFamilies.size());

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
        householdFactory.addExtrasAsChildrenAndRelatives(familyHhs, indRecs);
        householdFactory.completeHouseholdsWithRelatives(familyHhs,relatives, null);

        allHouseholds.addAll(familyHhs);

        return allHouseholds;
    }


    private Map<FamilyType, Integer> getRelationshipDistInPrimaryFamilies(List<HhRecord> hhRecords) {
        int couples = 0, oneParent = 0, other = 0;

        //lambda function to get primary family count, which is the number of relationships of a given type
        Function<HhRecord, Integer> getFamilyCount = (HhRecord hhRec) -> {
            return hhRec.HH_COUNT * (hhRec.getFamilyCountPerHousehold() - 1);
        };

        for (HhRecord hhRec : hhRecords) {

            //The count of total missing non-primary families. For a 2F household we count 1 family and for a 3F household we count 2
            switch (hhRec.getPrimaryFamilyType()) {
                case COUPLE_ONLY:
                    couples += getFamilyCount.apply(hhRec);
                    break;
                case COUPLE_WITH_CHILDREN:
                    couples += getFamilyCount.apply(hhRec);
                    break;
                case ONE_PARENT:
                    oneParent += getFamilyCount.apply(hhRec);
                    break;
                case OTHER_FAMILY:
                    other += getFamilyCount.apply(hhRec);
                    break;
                case LONE_PERSON:
                    break;
                case GROUP_HOUSEHOLD:
                    break;
                default:
                    throw new IllegalStateException("Unrecognised family type: " + hhRec.getPrimaryFamilyType());
            }
        }
        Map<FamilyType, Integer> dist = new HashMap<>(4, 1);
        dist.put(FamilyType.COUPLE_ONLY, couples);
        dist.put(FamilyType.ONE_PARENT, oneParent);
        dist.put(FamilyType.OTHER_FAMILY, other);
        return dist;
    }
}
