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
            if (h.getExpectedSize() - h.getCurrentSize() < 0) {
                print(h.getExpectedSize() + " " + h.getCurrentSize());
            }
            count[h.getExpectedSize()] += (h.getExpectedSize() - h.getCurrentSize());
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
            if (hhRec.NUM_OF_PERSONS_PER_HH != previousHhSize) {
                previousHhSize = hhRec.NUM_OF_PERSONS_PER_HH;
                map = new LinkedHashMap<>();
                householdInfo.add(map);
            }
            map.put(hhRec.getFamilyCountPerHousehold() + "" + hhRec.getPrimaryFamilyType(), 0);
            if (hhRec.getPrimaryFamilyType() == FamilyType.COUPLE_ONLY) {
                coupleOnly += (hhRec.HH_COUNT);
            }
            if (hhRec.getPrimaryFamilyType() == FamilyType.COUPLE_WITH_CHILDREN) {
                coupleYesChild += hhRec.HH_COUNT;
            }
            if (hhRec.getPrimaryFamilyType() == FamilyType.ONE_PARENT) {
                oneParentFamily += hhRec.HH_COUNT;
            }
        }

        for (Household household : allHouseholds) {
            Family primaryFamily = household.getFamilies().get(0);
            Map<String, Integer> hhmap = householdInfo.get(household.getExpectedSize());
            int hhcount = hhmap.get(household.getExpectedFamilyCount() + "" + primaryFamily.getType());
            hhmap.put(household.getExpectedFamilyCount() + "" + primaryFamily.getType(), hhcount + 1);
        }
        System.out.println("Total Households: " + ttlHhs);
        System.out.println("Couple Only Hhs: " + coupleOnly);
        System.out.println("Couple with children Hhs: " + coupleYesChild);
        System.out.println("One parent family Hhs: " + oneParentFamily);

        print("size\tDesctiption\t\t\t\t\t\t\ttarget\tcurrent");
        for (HhRecord hhrec : hhrecs) {
            int numberOfPersons = hhrec.NUM_OF_PERSONS_PER_HH;
            String familyDescription = hhrec.getFamilyCountPerHousehold() + " Family: " + hhrec.getPrimaryFamilyType()
                                                                                               .description();

            /* Just aesthetics - nothing important */
            int stringLengthDifference = 62 - familyDescription.length();
            String tabSpace = new String(new char[stringLengthDifference]).replace("\0", " ");

            print(numberOfPersons + "\t" + familyDescription + tabSpace + "\t" + hhrec.HH_COUNT + "\t" + householdInfo
                    .get(numberOfPersons).get(hhrec.getFamilyCountPerHousehold() + "" + hhrec.getPrimaryFamilyType()));
        }

    }

    static void printIndSummary(List<IndRecord> indRecords) {
        int totalPersons = 0, marriedMale = 0, marriedFemale = 0, children = 0, lnp = 0;
        for (IndRecord indRec : indRecords) {
            totalPersons += indRec.IND_COUNT;
            if (indRec.SEX == Sex.Female && indRec.RELATIONSHIP_STATUS == RelationshipStatus.MARRIED) {
                marriedFemale += indRec.IND_COUNT;
            }
            if (indRec.RELATIONSHIP_STATUS == RelationshipStatus.MARRIED && indRec.SEX == Sex.Male) {
                marriedMale += indRec.IND_COUNT;
            }
            if (indRec.RELATIONSHIP_STATUS == RelationshipStatus.U15_CHILD || indRec.RELATIONSHIP_STATUS ==
                    RelationshipStatus.STUDENT || indRec.RELATIONSHIP_STATUS == RelationshipStatus.O15_CHILD) {
                children += indRec.IND_COUNT;
            }
            if (indRec.RELATIONSHIP_STATUS == RelationshipStatus.LONE_PERSON) {
                lnp += indRec.IND_COUNT;
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
     * @param bias By how much should occurrence of True be biased. e.g. if bias == 0.25, probability of method returning True is 1/4.
     * @return true or false
     */
    static boolean tossCoinWithBias(Random rand, double bias) {
        double r = rand.nextDouble();
        return (r < bias);
    }

    /**
     * Randomly returns Male or Female based on the specified probability
     *
     * @param random          Random number generator
     * @param maleProbability The probability of returning Male
     * @return Sex of the person
     */
    static Sex getSexRandomly(Random random, double maleProbability) {
        return random.nextDouble() < maleProbability ? Sex.Male : Sex.Female;
    }

    /**
     * Selects an element randomly from the list without shuffling it. The element is not removed from the list.
     *
     * @param rand   Random instance
     * @param values The list to select an element from
     * @param <T>    Any instance
     * @return The randomly selected element
     */
    static <T> T getRandomlyWithoutShuffling(Random rand, List<T> values) {
        int r = rand.nextInt(values.size());
        return values.get(r);
    }

    /**
     * Produces the absolute error between expected and current distributions if current distribution's property k is changed by v value
     *
     * @param expected The expected distribution
     * @param current  The current distribution
     * @param k        The property in the distribution to change (key)
     * @param v        The magnitude of the change (can be -/+)
     * @param <K>      The type of the Keys in distribution maps
     * @return The absolute error
     */
    static <K> int getAbsoluteError(Map<K, Integer> expected, Map<K, Integer> current, K k, double v) {

        double newCount = current.get(k) + 1;
        int error = 0;

        for (K ck : expected.keySet()) {
            if (ck == k) {
                error += Math.abs(expected.get(ck) - newCount);
            } else {
                error += Math.abs(expected.get(ck) - current.get(k));
            }
        }
        return error;

    }

    static int getGaussianIndex(Random rand, int listSize){
        double rd = rand.nextGaussian();
        int listmid = (int)Math.round(listSize/(double)2);
        int listquart = (int)Math.round(listSize/(double)6);

        double index = rd*listquart+listmid;
        return (index <0)? 0: (listSize <= index)? listSize -1: (int)Math.round(index);
    }

}
