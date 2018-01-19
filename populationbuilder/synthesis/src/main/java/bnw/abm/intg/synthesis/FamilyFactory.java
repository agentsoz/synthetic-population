package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FamilyFactory {

    final private Random random;
    AgeRange.AgeComparator ageComparator = new AgeRange.AgeComparator();

    FamilyFactory(Random random) {
        this.random = random;
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

            int cIndex = PopulationRules.selectChild(loneParents.get(0), children);
            if (cIndex >= 0) {
                Family f = new Family(FamilyType.BASIC);
                f.addMember(loneParents.remove(0));
                f.addMember(children.remove(cIndex));
                lnParentBasic.add(f);
            } else {
                // Cannot find a suitable child. Move parent to end of the list so the remaining ones after the loop
                // are the ones that couldn't find children
                loneParents.add(loneParents.remove(0));
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

        Log.info("Forming Married couples: Couples formed: " + cpls);
        if (diff > 0) {
            Log.warn("Forming Married couples: " + diff + " young married males discarded. Population may be biased");
        } else if (diff < 0) {
            Log.warn("Forming Married couples: " + ((-1) * diff) + " young married females discarded. Population may " +
                             "be biased");
        } else {
            Log.info("Forming Married couples: no issues");
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
        //Shuffle lists to avoid any bias
        Collections.shuffle(couples, random);
        Collections.shuffle(children, random);

        List<Family> coupleWithChildFamilies = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            if (couples.isEmpty()) {
                throw new NotEnoughPersonsException(
                        "Couple With Children Basic Primary Families: Not enough couples");
            }
            if (children.isEmpty()) {
                new NotEnoughPersonsException("Couple With Children Basic Primary Families: Not enough children");
            }

            Person youngestParent = Collections.min(couples.get(0).getMembers(), ageComparator);
            int cIndex = PopulationRules.selectChild(youngestParent, children);
            if (cIndex >=0 ) {
                Family f = couples.remove(0);
                f.addMember(children.remove(cIndex));
                f.setType(FamilyType.COUPLE_WITH_CHILDREN);
                coupleWithChildFamilies.add(f);
            } else {
                couples.add(couples.remove(0)); // move to end of the list to filter out failed couples
            }
        }

        Log.info("Couple With Children Basic Primary Families: formed structures: " + coupleWithChildFamilies.size());
        if (coupleWithChildFamilies.size() == count) {
            Log.info("Couple With Children Basic Primary Families: All structures created");
        }

        return coupleWithChildFamilies;
    }

    /**
     * Adds a new child to the family considering population rules. Returns true if a suitable child is found and
     * added to the family. Returns false of a suitable child was not found.
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
