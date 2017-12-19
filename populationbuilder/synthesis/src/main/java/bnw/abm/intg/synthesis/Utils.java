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
            if (hhRec.primaryFamilyType == FamilyType.COUPLE_ONLY) {
                coupleOnly += (hhRec.hhCount);
            }
            if (hhRec.primaryFamilyType == FamilyType.COUPLE_WITH_CHILDREN) {
                coupleYesChild += hhRec.hhCount;
            }
            if (hhRec.primaryFamilyType == FamilyType.ONE_PARENT) {
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
            String familyDescription = hhrec.familyCountPerHousehold + " Family: " + hhrec.primaryFamilyType
                    .description();

            /* Just aesthetics - nothing important */
            int stringLengthDifference = 62 - familyDescription.length();
            String tabSpace = new String(new char[stringLengthDifference]).replace("\0", " ");

            print(numberOfPersons + "\t" + familyDescription + tabSpace + "\t" + hhrec.hhCount + "\t" + householdInfo
                    .get(numberOfPersons).get(hhrec.familyCountPerHousehold + "" + hhrec.primaryFamilyType));
        }

    }

    static void printIndSummary(List<IndRecord> indRecords) {
        int totalPersons = 0, marriedMale = 0, marriedFemale = 0, children = 0, lnp = 0;
        for (IndRecord indRec : indRecords) {
            totalPersons += indRec.indCount;
            if (indRec.sex == Sex.Female && indRec.relationshipStatus == RelationshipStatus.MARRIED) {
                marriedFemale += indRec.indCount;
            }
            if (indRec.relationshipStatus == RelationshipStatus.MARRIED && indRec.sex == Sex.Male) {
                marriedMale += indRec.indCount;
            }
            if (indRec.relationshipStatus == RelationshipStatus.U15_CHILD || indRec.relationshipStatus ==
                    RelationshipStatus.STUDENT || indRec.relationshipStatus == RelationshipStatus.O15_CHILD) {
                children += indRec.indCount;
            }
            if (indRec.relationshipStatus == RelationshipStatus.LONE_PERSON) {
                lnp += indRec.indCount;
            }
        }

        System.out.println("Total Individuals: " + totalPersons);
        System.out.println("Male MARRIED: " + marriedMale);
        System.out.println("Female MARRIED: " + marriedFemale);
        System.out.println("Children: " + children);
        System.out.println("Lone parents: " + lnp);
    }

    private static void print(Object l) {
        System.out.println(l.toString());
    }


    /**
     * Randomly decides True or False. Occurrence of True can be biased by specifying the ratio for being True
     *
     * @param rand Random object
     * @param bias By how much should occurrence of True be biased. e.g. if bias == 0.25, probability of method
     *             returning True is 1/4.
     * @return true or false
     */
    static boolean selectTrueOrFalseRandomlyWithBias(Random rand, double bias) {
        double r = rand.nextDouble();
        return (r < bias);
    }

    static int pickRandomly(Random rand, int... values) {
        int r = rand.nextInt(values.length);
        return values[r];
    }

}
