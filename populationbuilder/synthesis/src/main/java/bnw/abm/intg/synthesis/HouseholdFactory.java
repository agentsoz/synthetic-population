package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.Family;
import bnw.abm.intg.synthesis.models.FamilyHouseholdType;
import bnw.abm.intg.synthesis.models.Household;
import bnw.abm.intg.synthesis.models.Person;
import bnw.abm.intg.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wniroshan 13 Mar 2018
 */
public class HouseholdFactory {
    private FamilyFactory familyFactory;

    HouseholdFactory(FamilyFactory familyFactory) {
        this.familyFactory = familyFactory;
    }

    private Household createNewHousehold(HhRecord hhRecord, Family primaryFamily) {
        Household h = new Household(hhRecord.HH_COUNT, hhRecord.FAMILY_HOUSEHOLD_TYPE, hhRecord.SA);
        h.addFamily(primaryFamily);
        h.setPrimaryFamilyType(hhRecord.getPrimaryFamilyType());
        return h;
    }

    Map<FamilyHouseholdType, List<Household>> formHouseholdsWithPrimaryFamilies(List<HhRecord> hhRecs,
                                                                                List<Person> marriedMales,
                                                                                List<Person> marriedFemales,
                                                                                List<Person> children,
                                                                                List<Person> loneParents,
                                                                                List<Person> relatives) {

        Map<FamilyHouseholdType, List<Household>> basicHouseholds = new HashMap<>();
        for (HhRecord hhRec : hhRecs) {
            List<Household> newHouseholds = null;
            switch (hhRec.getPrimaryFamilyType()) {
                case COUPLE_ONLY:
                    newHouseholds = new ArrayList<>(hhRec.HH_COUNT);
                    for (int i = 0; i < hhRec.HH_COUNT; i++) {
                        Family f = familyFactory.makeBasicMarriedCouple(marriedMales, marriedFemales);
                        Household household = createNewHousehold(hhRec, f);
                        newHouseholds.add(household);
                    }
                    basicHouseholds.put(hhRec.FAMILY_HOUSEHOLD_TYPE, newHouseholds);
                    break;
                case COUPLE_WITH_CHILDREN:
                    newHouseholds = new ArrayList<>(hhRec.HH_COUNT);
                    for (int i = 0; i < hhRec.HH_COUNT; i++) {
                        Family f = familyFactory.makeBasicCoupleWithChildFamily(marriedMales, marriedFemales, children);
                        Household household = createNewHousehold(hhRec, f);
                        newHouseholds.add(household);
                    }
                    basicHouseholds.put(hhRec.FAMILY_HOUSEHOLD_TYPE, newHouseholds);
                    break;
                case ONE_PARENT:
                    newHouseholds = new ArrayList<>(hhRec.HH_COUNT);
                    for (int i = 0; i < hhRec.HH_COUNT; i++) {
                        Family f = familyFactory.makeBasicOneParentFamily(loneParents, children);
                        Household household = createNewHousehold(hhRec, f);
                        newHouseholds.add(household);
                    }
                    basicHouseholds.put(hhRec.FAMILY_HOUSEHOLD_TYPE, newHouseholds);
                    break;
                case OTHER_FAMILY:
                    newHouseholds = new ArrayList<>(hhRec.HH_COUNT);
                    for (int i = 0; i < hhRec.HH_COUNT; i++) {
                        Family f = familyFactory.makeBasicOtherFamily(relatives);
                        Household household = createNewHousehold(hhRec, f);
                        newHouseholds.add(household);
                    }
                    basicHouseholds.put(hhRec.FAMILY_HOUSEHOLD_TYPE, newHouseholds);
                    break;
                default:
                    continue;
            }
        }
        Log.info("All households populated with primary families");
        return basicHouseholds;

    }
}
