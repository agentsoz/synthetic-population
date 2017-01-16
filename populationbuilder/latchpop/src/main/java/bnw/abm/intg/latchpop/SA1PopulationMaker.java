/**
 * 
 */
package bnw.abm.intg.latchpop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Bhagya N. Wickramasinghe 2 Jun 2016
 */
public class SA1PopulationMaker {

    Map<String, List<Household>> householdsBySA1 = new LinkedHashMap<>();
    Map<String, Map<String, List<Household>>> householdsBySA1ByType = new LinkedHashMap<>();
    Random random;

    public SA1PopulationMaker(Random random) {
	this.random = random;
    }

    Map<String, List<Household>> distributePopulationToSA1s(Map<String, Map<String, Integer>> householdsCountBySA1sByType,
	    Map<String, List<Household>> householdsByType, Random random) {

	for (String hhType : householdsCountBySA1sByType.keySet()) {
	    List<Household> householdsOfSelectedType = householdsByType.get(hhType);
	    if (householdsOfSelectedType == null) {
		householdsOfSelectedType = new ArrayList<>();
	    }
	    Collections.shuffle(householdsOfSelectedType, random);
	    Map<String, Integer> householdCountOfSelectedTypeBySA1 = householdsCountBySA1sByType.get(hhType);
	    Map<String, List<Household>> tempHholdsOfSA1 = new LinkedHashMap<>();
	    for (String sa1 : householdCountOfSelectedTypeBySA1.keySet()) {
		int hhCountOfSelectedTypeInThisSA1 = householdCountOfSelectedTypeBySA1.get(sa1);
		List<Household> existingHouseholds = householdsBySA1.get(sa1);
		// System.out.println(hhType+" "+sa1+" "+hhCountOfSelectedTypeInThisSA1);
		List<Household> newHouseholds = householdsOfSelectedType.subList(0, hhCountOfSelectedTypeInThisSA1);
		newHouseholds.stream().forEach(h -> h.setSA1Code(sa1));
		if (existingHouseholds == null) {
		    existingHouseholds = new ArrayList<>(newHouseholds);
		    householdsBySA1.put(sa1, existingHouseholds);
		} else {
		    existingHouseholds.addAll(newHouseholds);
		}
		List<Household> allocatedHhs = new ArrayList<>(householdsOfSelectedType.subList(0, hhCountOfSelectedTypeInThisSA1));
		tempHholdsOfSA1.put(sa1, allocatedHhs);
		householdsOfSelectedType.subList(0, hhCountOfSelectedTypeInThisSA1).clear();
		// householdsBySA1.put(sa1, householdsOfSelectedType.subList(0, hhCountOfSelectedTypeInThisSA1));
		// System.out.println();
	    }
	    householdsBySA1ByType.put(hhType, tempHholdsOfSA1);
	}
	return householdsBySA1;
    }

    void assignTENLLDtoHouseholds(Path tenlldLocation, String tenlldFileName, Map<String, List<Household>> householdsBySA1) throws IOException {
	for (String sa1 : householdsBySA1.keySet()) {
	    List<List<String>> tenlldProps = DataReader
		    .readTenlldDistribution(Paths.get(tenlldLocation + File.separator + sa1 + File.separator + tenlldFileName));
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
		hholds.subList(end, hholds.size()).stream().forEach(h -> h.setTenlld(tenlldProps.get(random.nextInt(4)).get(0)));
	    }
	}
    }
}