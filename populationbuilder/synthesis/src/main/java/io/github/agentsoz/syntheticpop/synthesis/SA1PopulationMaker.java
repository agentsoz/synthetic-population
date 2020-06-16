package io.github.agentsoz.syntheticpop.synthesis;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2020 by its authors. See AUTHORS file.
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

import io.github.agentsoz.syntheticpop.synthesis.models.Household;
import io.github.agentsoz.syntheticpop.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author wniroshan 2 Jun 2016
 */
public class SA1PopulationMaker {

    /**
     * Distributes households among the SA1s in the corresponding SA2
     *
     * @param hhDistBySA1sByType The number of households distribution by SA1 and type
     * @param hhsByType          The household instances distribution by household type
     * @param random             The random number generator.
     */
    static void distributePopulationToSA1s(Map<String, Map<String, Integer>> hhDistBySA1sByType,
                                           Map<String, List<Household>> hhsByType,
                                           Random random) {
        /*
         * Iterates over the household types, assigning the households under each hh type to the SA1 that they should
         * belong to.
         */
        for (String hhType : hhDistBySA1sByType.keySet()) {
            List<Household> householdsOfSelectedType = hhsByType.get(hhType);
            if (householdsOfSelectedType != null && !householdsOfSelectedType.isEmpty()) { // If no Hhs in this type nothing to do.

                Collections.shuffle(householdsOfSelectedType, random);
                //Get the number of households of the current hh type under each SA1
                Map<String, Integer> hhsCountOfSelectedTypeBySA1 = hhDistBySA1sByType.get(hhType);

                for (String sa1 : hhsCountOfSelectedTypeBySA1.keySet()) {
                    int hhCountInSA1 = hhsCountOfSelectedTypeBySA1.get(sa1);
                    List<Household> newHouseholds = householdsOfSelectedType.subList(0, hhCountInSA1);
                    newHouseholds.forEach(h -> h.setSA1Code(sa1));
                    newHouseholds.forEach(h -> h.getMembers().forEach(m -> m.setSA1Code(sa1)));

                    householdsOfSelectedType.subList(0, hhCountInSA1).clear();
                }

                if (householdsOfSelectedType.size() != 0){
                    Log.warn("Some households were not assigned to SA1s: Household type: "+ hhType+" "+householdsOfSelectedType.size());
                }
            }
        }
    }

    static void assignTENLLDtoHouseholds(Path tenlldLocation,
                                         String tenlldFileName,
                                         Map<String, List<Household>> householdsBySA1,
                                         Random random) throws IOException {
        for (String sa1 : householdsBySA1.keySet()) {
            List<List<String>> tenlldProps = DataReader
                    .readTenlldDistribution(Paths.get(tenlldLocation + File.separator + sa1 + File.separator +
                                                              tenlldFileName));
            List<Household> households = householdsBySA1.get(sa1);
            int hhCount = households.size();
            Collections.shuffle(households, random);
            tenlldProps.remove(0);
            int start = 0, end = 0;
            for (List<String> tenlldType : tenlldProps) {
                String tenlldName = tenlldType.get(0);
                float hhsProportion = Float.parseFloat(tenlldType.get(1));
                int nofHhs = Math.round(hhCount * hhsProportion);
                start = end;
                end = start + nofHhs;
                if (end > households.size()) {
                    end = households.size();
                }
                households.subList(start, end).forEach(h -> h.setTenlld(tenlldName));
            }
            if (end < households.size()) {
                households.subList(end, households.size()).forEach(h -> h.setTenlld(tenlldProps.get(random.nextInt(4)).get(0)));
            }
        }
    }
}
