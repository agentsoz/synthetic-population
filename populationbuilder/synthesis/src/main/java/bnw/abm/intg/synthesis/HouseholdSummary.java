package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.Family;
import bnw.abm.intg.synthesis.models.Household;

import java.util.*;

/**
 * @author wniroshan 08 Mar 2018
 */
public class HouseholdSummary {
    /**
     * Groups households by household types
     *
     * @param allHouseholds The list of households
     * @return Map of household types and household instances
     */
    static Map<String, List<Household>> groupHouseholdsByHouseholdType(List<Household> allHouseholds) {

        Map<String, List<Household>> householdsByType = new HashMap<>();

        for (Household household : allHouseholds) {
            Family primaryFamily = household.getPrimaryFamily();
            String key = household.getCurrentSize() + ":" + household.getCurrentFamilyCount() + ":" + primaryFamily
                    .getType();
            if (householdsByType.containsKey(key)) {
                householdsByType.get(key).add(household);
            } else {
                householdsByType.put(key, new ArrayList<>(Arrays.asList(household)));
            }
        }
        return householdsByType;
    }
}
