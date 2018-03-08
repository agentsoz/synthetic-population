package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.Household;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author wniroshan 2 Jun 2016
 */
public class SA1PopulationMaker {

    Map<String, List<Household>> householdsBySA1 = new LinkedHashMap<>();
    Map<String, Map<String, List<Household>>> householdsBySA1ByType = new LinkedHashMap<>();
    Random random;

    public SA1PopulationMaker(Random random) {
        this.random = random;
    }

    /**
     * Distributes households among the SA1s in the corresponding SA2
     * @param hhDistBySA1sByType The number of households distribution by SA1 and type
     * @param hhsByType The household instances distribution by household type
     * @param random The random number generator.
     */
    void distributePopulationToSA1s(Map<String, Map<String, Integer>> hhDistBySA1sByType,
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
                Map<String, List<Household>> tempHholdsOfSA1 = new LinkedHashMap<>();
                for (String sa1 : hhsCountOfSelectedTypeBySA1.keySet()) {
                    int hhCountInSA1 = hhsCountOfSelectedTypeBySA1.get(sa1);

                    List<Household> newHouseholds = householdsOfSelectedType.subList(0, hhCountInSA1);
                    newHouseholds.stream().forEach(h -> h.setSA1Code(sa1));
                    householdsOfSelectedType.subList(0, hhCountInSA1).clear();
                }
            }
        }
    }

    void assignTENLLDtoHouseholds(Path tenlldLocation,
                                  String tenlldFileName,
                                  Map<String, List<Household>> householdsBySA1) throws IOException {
        for (String sa1 : householdsBySA1.keySet()) {
            List<List<String>> tenlldProps = DataReader
                    .readTenlldDistribution(Paths.get(tenlldLocation + File.separator + sa1 + File.separator +
                                                              tenlldFileName));
            List<Household> hholds = householdsBySA1.get(sa1);
            int hholdsCount = hholds.size();
            Collections.shuffle(hholds, random);
            tenlldProps.remove(0);
            int start = 0, end = 0;
            for (List<String> tenlldType : tenlldProps) {
                String tenlldName = tenlldType.get(0);
                float hhsProportion = Float.parseFloat(tenlldType.get(1));
                int nofHhs = Math.round(hholdsCount * hhsProportion);
                start = end;
                end = start + nofHhs;
                if (end > hholds.size()) {
                    end = hholds.size();
                }
                hholds.subList(start, end).stream().forEach(h -> h.setTenlld(tenlldName));
            }
            if (end < hholds.size()) {
                hholds.subList(end, hholds.size()).stream().forEach(h -> h.setTenlld(tenlldProps.get(random.nextInt(4))
                                                                                             .get(0)));
            }
        }
    }
}