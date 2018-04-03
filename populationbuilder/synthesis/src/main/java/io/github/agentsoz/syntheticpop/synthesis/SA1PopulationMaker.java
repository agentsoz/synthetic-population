package io.github.agentsoz.syntheticpop.synthesis;

import io.github.agentsoz.syntheticpop.synthesis.models.Household;

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
         * Iterated over the household types, assigning the households under each hh type to the SA1 that they will
         * belong to.
         */
        for (String hhType : hhDistBySA1sByType.keySet()) {
            List<Household> householdsOfSelectedType = hhsByType.get(hhType);
            if (householdsOfSelectedType != null) { // If no Hhs in this type nothing to do.

                Collections.shuffle(householdsOfSelectedType, random);
                //Get the number of households of the current hh type under each SA1
                Map<String, Integer> hhsCountOfSelectedTypeBySA1 = hhDistBySA1sByType.get(hhType);

                for (String sa1 : hhsCountOfSelectedTypeBySA1.keySet()) {
                    int hhCountInSA1 = hhsCountOfSelectedTypeBySA1.get(sa1);

                    List<Household> newHouseholds = householdsOfSelectedType.subList(0, hhCountInSA1);
                    newHouseholds.forEach(h -> h.setSA1Code(sa1));
                    householdsOfSelectedType.subList(0, hhCountInSA1).clear();
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