package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;

import java.util.*;

public class Utils {
    static void summary(List<Household> allHouseholds) {
        print("------------ Formed households -----------");
        int wanted = 0;
        print("Total households formed: " + allHouseholds.size());
        print("Distribution of missing persons in formed households by size:");
        int[] count = new int[9];
        for (Household h : allHouseholds) {
            if (h.TARGETSIZE - h.currentSize() < 0) {
                print(h.TARGETSIZE + " " + h.currentSize());
            }
            count[h.TARGETSIZE] += (h.TARGETSIZE - h.currentSize());
        }
        print("size\t persons");
        for (int i = 1; i < count.length; i++) {
            print(i + "\t " + count[i]);
            wanted += count[i];
        }
        print("Total missing persons: " + wanted);
    }

    static void printHhSummary(List<HhRecord> hhrecs, List<Household> allHouseholds) {
        int ttlHhs = 0;
        int coupleOnly = 0, coupleYesChild = 0, oneParentFamily = 0;
        List<Map<String, Integer>> householdInfo = new ArrayList<Map<String, Integer>>(9);
        householdInfo.add(new LinkedHashMap<>());// Dummy for 0th element;

        Map<String, Integer> map = null;
        int previousHhSize = 0;
        for (HhRecord hhRec : hhrecs) {
            if (hhRec.numOfPersonsPerHh != previousHhSize) {
                previousHhSize = hhRec.numOfPersonsPerHh;
                map = new LinkedHashMap<>();
                householdInfo.add(map);
            }
            map.put(hhRec.familyCountPerHousehold + "" + hhRec.primaryFamilyType, 0);
            if (hhRec.primaryFamilyType == FamilyType.COUPLEONLY) {
                coupleOnly += (hhRec.hhCount);
            }
            if (hhRec.primaryFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN) {
                coupleYesChild += hhRec.hhCount;
            }
            if (hhRec.primaryFamilyType == FamilyType.ONEPARENT) {
                oneParentFamily += hhRec.hhCount;
            }
        }

        for (Household household : allHouseholds) {
            Family primaryFamily = household.getFamilies().get(0);
            Map<String, Integer> hhmap = householdInfo.get(household.TARGETSIZE);
            int hhcount = hhmap.get(household.TARGETFAMLYCOUNT + "" + primaryFamily.getType());
            hhmap.put(household.TARGETFAMLYCOUNT + "" + primaryFamily.getType(), hhcount + 1);
        }
        System.out.println("Total Households: " + ttlHhs);
        System.out.println("Couple Only Hhs: " + coupleOnly);
        System.out.println("Couple with children Hhs: " + coupleYesChild);
        System.out.println("One parent family Hhs: " + oneParentFamily);

        print("size\tDesctiption\t\t\t\t\t\t\ttarget\tcurrent");
        for (HhRecord hhrec : hhrecs) {
            int numberOfPersons = hhrec.numOfPersonsPerHh;
            String familyDescription = hhrec.familyCountPerHousehold + " Family: " + hhrec.primaryFamilyType.description();

            /* Just esthetics - nothing important */
            int stringlengthdifference = 62 - familyDescription.length();
            String tabspace = new String(new char[stringlengthdifference]).replace("\0", " ");

            print(numberOfPersons + "\t" + familyDescription + tabspace + "\t" + hhrec.hhCount + "\t"
                    + householdInfo.get(numberOfPersons).get(hhrec.familyCountPerHousehold + "" + hhrec.primaryFamilyType));
        }

    }

    static void printIndSummary(List<IndRecord> indRecords) {
        int ttlInds = 0, marriedMale = 0, marriedFemale = 0, children = 0, lnp = 0;
        for (IndRecord indrec : indRecords) {
            ttlInds += indrec.indCount;
            if (indrec.sex == Sex.Female && indrec.relationshipStatus == RelationshipStatus.Married) {
                marriedFemale += indrec.indCount;
            }
            if (indrec.relationshipStatus == RelationshipStatus.Married && indrec.sex == Sex.Male) {
                marriedMale += indrec.indCount;
            }
            if (indrec.relationshipStatus == RelationshipStatus.Child) {
                children += indrec.indCount;
            }
            if (indrec.relationshipStatus == RelationshipStatus.LonePerson) {
                lnp += indrec.indCount;
            }
        }

        System.out.println("Total Individuals: " + ttlInds);
        System.out.println("Male Married: " + marriedMale);
        System.out.println("Female Married: " + marriedFemale);
        System.out.println("Children: " + children);
        System.out.println("Lone parents: " + lnp);
    }

    static void print(Object l) {
        System.out.println(l.toString());
    }


    /**
     * Randomly decides True or False. Occurrence of True can be biased by specifying the ratio for being True
     *
     * @param rand Random object
     * @param bias By how much should occurrence of True be biased. e.g. if bias == 0.25, probability of method returning True is 1/4.
     * @return true or false
     */
    static boolean selectTrueOrFalseRandomlyWithBias(Random rand, double bias) {
        double r = rand.nextDouble();
        return (r < bias) ? true : false;
    }

    static int pickRandomly(Random rand, int... fromvals) {
        int r = rand.nextInt(fromvals.length);
        return fromvals[r];
    }

}
