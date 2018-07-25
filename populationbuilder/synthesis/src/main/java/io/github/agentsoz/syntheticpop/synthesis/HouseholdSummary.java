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

import io.github.agentsoz.syntheticpop.synthesis.models.Family;
import io.github.agentsoz.syntheticpop.synthesis.models.Household;

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
