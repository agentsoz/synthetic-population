package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@FunctionalInterface
interface makeFamilyFunction<F1, F2, F3, R> {
    public R apply(F1 f1, F2 f2, F3 f3);
}


public class FamilyFactory {

    final private Random random;
    AgeRange.AgeComparator ageComparator = new AgeRange.AgeComparator();

    FamilyFactory(Random random) {
        this.random = random;
    }

    /**
     * Forms a married couple by pairing a male and a female according to population rules. This updates the two
     * input lists.
     *
     * @param marriedMales   The list of married males
     * @param marriedFemales The list of married females
     * @return The new couple family unit
     */
    Family makeBasicMarriedCouple(List<Person> marriedMales, List<Person> marriedFemales) {

        //Find a suitable husband and a wife. There can be more married persons in one gender than the other. So we
        // pick the first person from the smaller list, because it is easier (probabilistically) to find a partners
        // in correct age category from the larger list.
        Person wife, husband;

        if (marriedMales.size() >= marriedFemales.size()) {
            husband = marriedMales.remove(random.nextInt(marriedMales.size()));
            int wifeIndex = PopulationRules.selectWife(husband, marriedFemales);
            if (wifeIndex >= 0) {
                wife = marriedFemales.remove(wifeIndex);
            } else {
                Log.debug("Remaining - Married males: "+marriedMales.size());
                Log.debug("Remaining - Married females: "+marriedFemales.size());
                throw new NoSuitablePersonException("Cannot find a suitable wife for husband in age " + husband
                        .getAgeRange());
            }
        } else {
            wife = marriedFemales.remove(random.nextInt(marriedFemales.size()));
            int husbandIndex = PopulationRules.selectWife(wife, marriedMales);
            if (husbandIndex >= 0) {
                husband = marriedMales.remove(husbandIndex);
            } else {
                throw new NoSuitablePersonException("Cannot find a suitable husband for wife in age " + wife
                        .getAgeRange());
            }
        }

        Family f = new Family(FamilyType.COUPLE_ONLY);
        f.addMember(husband);
        f.addMember(wife);
        return f;
    }

    /**
     * Creates a basic couple with child family with two parents and a child.
     *
     * @param marriedMales   The list of married males
     * @param marriedFemales The list of married females
     * @param children       The list of children
     * @return A couple with child basic family instance
     */
    Family makeBasicCoupleWithChildFamily(List<Person> marriedMales,
                                          List<Person> marriedFemales,
                                          List<Person> children) {
        Family family = makeBasicMarriedCouple(marriedMales, marriedFemales);
        boolean success = addChildToFamily(family, children);
        if (!success) {
            throw new NoSuitablePersonException("Cannot find a suitable child for family: " + family);
        } else {
            family.setType(FamilyType.COUPLE_WITH_CHILDREN);
            return family;
        }
    }

    /**
     * Pairs a lone parent with a suitable child. This alters input lists.
     *
     * @param loneParents The list of lone parents in the population
     * @param children    The list of children
     * @return The basic one parent family unit
     */
    Family makeBasicOneParentFamily(List<Person> loneParents, List<Person> children) {

        Family family = new Family(FamilyType.ONE_PARENT);
        Person loneParent = loneParents.remove(random.nextInt(loneParents.size()));
        family.addMember(loneParent);
        boolean success = addChildToFamily(family, children);
        if (!success) {
            throw new NoSuitablePersonException("Cannot find a suitable child for family: " + family);
        } else {
            return family;
        }
    }

    /**
     * Creates a new Other Family unit by pairing two relatives
     *
     * @param relatives The list of relatives
     * @return The new other family unit
     */
    Family makeBasicOtherFamily(List<Person> relatives) {
        if (relatives.size() < 2) {
            throw new NotEnoughPersonsException(
                    "Other Family Basic Primary Families: Not enough Relatives to form more Basic Other Family " +
                            "structures");
        }
        Family family = new Family(FamilyType.OTHER_FAMILY);
        family.addMember(relatives.remove(random.nextInt(relatives.size())));
        family.addMember(relatives.remove(random.nextInt(relatives.size())));
        return family;
    }


    /**
     * Pairs all the lone parents with a suitable child. This alters input lists.
     *
     * @param loneParents The list of lone parents in the population
     * @param children    The list of children
     * @return A list of basic one parent family units with one lone parent and a child
     */
    List<Family> makeAllOneParentBasicUnits(List<Person> loneParents, List<Person> children) {
        Collections.shuffle(loneParents, random); //Mixes male and females to remove any bias to parent's gender
        Collections.sort(loneParents, ageComparator.reversed()); //Sort by age. Males and females are still mixed
        Collections.shuffle(children, random); //Mixes children to remove any bias to a gender
        Collections.sort(children, ageComparator.reversed());

        List<Family> lnParentBasic = new ArrayList<>();
        int loneParentsCount = loneParents.size();
        for (int i = 0; i < loneParentsCount; i++) {
            if (children.isEmpty()) {
                Log.warn("One Parent Basic: Discarded lone parents: " + loneParents.size());
                throw new NotEnoughPersonsException("One Parent Basic: Not enough children"); //TODO: implement
                // taking children from extras
            }

            Family f = new Family(FamilyType.BASIC);
            Person loneParent = loneParents.remove(0);
            f.addMember(loneParent);
            boolean success = addChildToFamily(f, children);
            if (success) {
                lnParentBasic.add(f);
            } else {
                loneParents.add(loneParent);
            }
        }

        Log.info("One Parent Basic: formed structures: " + lnParentBasic.size());
        if (loneParents.isEmpty()) {
            Log.info("One Parent Basic: All structures created");
        } else {
            Log.warn("One Parent Basic: Discarded lone parents: " + loneParents.size());
        }
        return lnParentBasic;
    }

    /**
     * Forms basic other family units need for households with an Other Family as the primary family. A family is
     * created by randomly selecting two relatives.
     *
     * @param count     The needed number of Other Family units
     * @param relatives The list of relatives in the population
     * @return The list of basic Other Family units
     */
    List<Family> makeOtherFamilyBasicUnits(int count, List<Person> relatives) {


        List<Family> otherFamilyBasic = new ArrayList<>();
        Collections.shuffle(relatives, random);

        for (int i = 0; i < count; i++) {
            if (relatives.size() < 2) {
                throw new NotEnoughPersonsException(
                        "Other Family Basic Primary Families: Not enough Relatives to form more Basic Other Family " +
                                "structures");
            }
            Family f = new Family(FamilyType.OTHER_FAMILY);
            f.addMember(relatives.remove(0));
            f.addMember(relatives.remove(0));
            otherFamilyBasic.add(f);
        }


        Log.info("Other Family Basic Primary Families: Structures formed: " + otherFamilyBasic.size());
        if (otherFamilyBasic.size() == count) {
            Log.info("Other Family Basic Primary Families: All structures created");
        }

        return otherFamilyBasic;
    }

    /**
     * Forms basic married couple units. Only consider heterosexual relationships. First sorts all males and females in
     * age descending order. Then pair them in order they appear in respective lists. This ensures age wise natural
     * looking relationships. Method alters input lists.
     *
     * @param marriedMales   list of married males
     * @param marriedFemales list of married females
     * @return list of couples
     */
    List<Family> makeMarriedCouples(List<Person> marriedMales, List<Person> marriedFemales) {

        //Sort two lists in age descending order
        //TODO: Younger married pesons may be over represented in married-extra list
        Collections.sort(marriedMales, ageComparator.reversed());
        Collections.sort(marriedFemales, ageComparator.reversed());

        int cpls = Math.min(marriedMales.size(), marriedFemales.size());
        int diff = marriedMales.size() - marriedFemales.size();

        List<Family> fl = new ArrayList<>();
        for (int i = 0; i < cpls; i++) {
            Family f = new Family(FamilyType.BASIC);
            f.addMember(marriedMales.remove(0));
            f.addMember(marriedFemales.remove(0));
            fl.add(f);
        }

        Log.info("Forming Married couples: units formed: " + cpls);
        if (diff > 0) {
            Log.warn("Forming Married couples: " + diff + " young married males discarded. Population may be biased");
        } else if (diff < 0) {
            Log.warn("Forming Married couples: " + ((-1) * diff) + " young married females discarded. Population may " +
                             "be biased");
        } else {
            Log.info("Forming Married couples: successful");
        }
        return fl;
    }

    /**
     * Forms basic couple with children family units needed for households where a couple with children is the primary
     * family. This method alters couples and children list
     *
     * @param count    The needed number of couple with children basic family units
     * @param couples  The couple units in the population
     * @param children The children in the population
     * @return Basic couple with children family units for primary families.
     */
    List<Family> makeCoupleWithChildFamilyBasicUnits(int count,
                                                     List<Family> couples,
                                                     List<Person> children) {

        // Get rid of O15 and Student children quickly. Otherwise we get stuck later.
        Collections.sort(couples, new AgeRange.YoungestParentAgeComparator());
        Collections.sort(children, new AgeRange.AgeComparator());

        List<Family> coupleWithChildFamilies = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            if (couples.isEmpty()) {
                throw new NotEnoughPersonsException(
                        "Forming Basic Couple With Children Families: Not enough couples");
            }
            if (children.isEmpty()) {
                new NotEnoughPersonsException("Forming Basic Couple With Children Families: Not enough children");
            }

            Family f = couples.remove(0);
            boolean success = addChildToFamily(f, children);
            if (success) {
                f.setType(FamilyType.COUPLE_WITH_CHILDREN);
                coupleWithChildFamilies.add(f);
            } else {
                couples.add(f); // move to end of the list to filter out failed couples
            }

        }

        Log.info("Forming Basic Couple With Children Families: units formed: " + coupleWithChildFamilies.size());
        if (coupleWithChildFamilies.size() == count) {
            Log.info("Forming Basic Couple With Children Families: successful");
        }

        return coupleWithChildFamilies;
    }

    /**
     * Adds a new child to the family considering population rules. Returns true if a suitable child is found and added
     * to the family. Returns false of a suitable child was not found, the family is not changed.
     *
     * @param family   The family to add a child
     * @param children The list of children to select a child from
     * @return True if a suitable child was found and added to the child, else false.
     */
    boolean addChildToFamily(Family family, List<Person> children) {
        Person youngestParent = family.getMembers()
                .stream()
                .filter(m -> m.getRelationshipStatus() == RelationshipStatus.MARRIED ||
                        m.getRelationshipStatus() == RelationshipStatus.LONE_PARENT)
                .min(new AgeRange.AgeComparator())
                .get();

        int cIndex = PopulationRules.selectChild(youngestParent, children);
        if (cIndex >= 0) {
            family.addMember(children.remove(cIndex));
            return true;
        } else {
            return false;
        }


    }
}
