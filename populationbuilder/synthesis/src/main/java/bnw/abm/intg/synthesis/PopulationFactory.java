package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
    private List<Household> allHouseholds = new ArrayList<>();
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

        extrasHandler = new ExtrasHandler(hhRecs, indRecs, random);
        familyFactory = new FamilyFactory(random);
        householdFactory = new HouseholdFactory(hhRecs, random, extrasHandler);
    }

    List<Household> makePopulation() {

        formAllPersons();
        formAllKnownFamilies();
        formHouseholds();

        return null;
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

        Log.info("Formed all person instances");
        Log.debug("Remaining Married males: " + marriedMales.size());
        Log.debug("Remaining Married females: " + marriedFemales.size());
        Log.debug("Remaining Relatives: " + relatives.size());
        Log.debug("Remaining Children: " + children.size());
        Log.debug("Remaining Lone parents: " + loneParents.size());
        Log.debug("Remaining Lone persons: " + lonePersons.size());
        Log.debug("Remaining Group household persons: " + groupHhPersons.size());
    }

    private void formAllKnownFamilies() {

        basicCouples = familyFactory.makeMarriedCouples(marriedMales, marriedFemales);
        Log.debug("Remaining Married males: " + marriedMales.size());
        Log.debug("Remaining Married females: " + marriedFemales.size());
        Log.debug("Remaining Basic couples: " + basicCouples.size());
        //Save extra married persons for later use. If married females list is not empty we had extra females,
        // otherwise we had extra males, because makeMarriedCouples() function keeps forming couples until one list exhausts.
        extrasHandler.setExtraMarriedPersons((!marriedFemales.isEmpty()) ? marriedFemales : marriedMales);

        basicOneParentFamilies = familyFactory.makeAllOneParentBasicUnits(loneParents, children);
        Log.debug("Remaining Lone parent persons: " + loneParents.size());
        Log.debug("Remaining Children: " + children.size());

        Log.debug("Remaining Lone parent persons: " + loneParents.size());
        Log.debug("Remaining Children: " + children.size());

        //Form basic family structures and removes couples from married males and females list
        List<HhRecord> coupleWChildRecs = DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                                     FamilyHouseholdType.F1_COUPLE_WITH_CHILDREN,
                                                                                     FamilyHouseholdType.F2_COUPLE_WITH_CHILDREN,
                                                                                     FamilyHouseholdType.F3_COUPLE_WITH_CHILDREN);
        int fCount = coupleWChildRecs.stream().mapToInt(r -> r.HH_COUNT).sum();
        basicPrimaryCoupleWithChildFamilies = familyFactory.makeCoupleWithChildFamilyBasicUnits(fCount, basicCouples, children);
        Log.debug("Remaining Basic couples: " + basicCouples.size());
        Log.debug("Remaining Children: " + children.size());

        // Forms Other Family basic family structures
        List<HhRecord> otherFamiliesRecs = DataReader.getHhRecordsByPrimaryFamilyType(hhRecs,
                                                                                      FamilyHouseholdType.F1_OTHER_FAMILY,
                                                                                      FamilyHouseholdType.F2_OTHER_FAMILY,
                                                                                      FamilyHouseholdType.F3_OTHER_FAMILY);
        fCount = otherFamiliesRecs.stream().mapToInt(r -> r.HH_COUNT).sum();
        basicPrimaryOtherFamilies = familyFactory.makeOtherFamilyBasicUnits(fCount, relatives);
        Log.debug("Remaining Relatives: " + relatives.size());


    }

    private void formHouseholds() {

        List<Household> lonePersonHhs = householdFactory.formLonePersonHouseholds(lonePersons);
        List<Household> groupHouseholds = householdFactory.formGroupHouseholds(groupHhPersons);
        Map<FamilyType, List<Household>> familyHhs = householdFactory.formAllFamilyHouseholdsWithPrimaryFamilies(basicCouples,
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
                                                           nonPrimaryCwcProbability,
                                                           familyFactory);


    }
}
