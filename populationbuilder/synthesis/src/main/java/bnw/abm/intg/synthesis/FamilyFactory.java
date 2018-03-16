package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FamilyFactory {

    private final Random random;
    private final ExtrasHandler extrasHandler;
    AgeRange.AgeComparator ageComparator = new AgeRange.AgeComparator();

    FamilyFactory(Random random, ExtrasHandler extrasHandler) {
        this.random = random;
        this.extrasHandler = extrasHandler;
    }

    /**
     * Forms a married couple by pairing a male and a female according to population rules. This updates the two input lists.
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
                Log.debug("Remaining - Married males: " + marriedMales.size());
                Log.debug("Remaining - Married females: " + marriedFemales.size());
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
     * @param count       The number of basic One Parent families to form
     * @param loneParents The list of lone parents in the population
     * @param children    The list of children
     * @return A list of basic one parent family units with one lone parent and a child
     */
    List<Family> formOneParentBasicUnits(int count, List<Person> loneParents, List<Person> children) {
        Log.debug("Basic One Parent: required units: " + count);
        if (count > loneParents.size()) {
            Log.debug("Basic One Parent: required Lone Parents: " + count);
            Log.debug("Basic One Parent: remaining Lone Parents: " + loneParents.size());
            Log.debug("Basic One Parent: remaining Extras: " + extrasHandler.remainingExtras());

            //We don't have enough Lone Parents. So using extras.
            int newLoneParentsCount = count - loneParents.size();
            loneParents.addAll(extrasHandler.getPersonsFromExtras(RelationshipStatus.LONE_PARENT,
                                                                  null, //Sex automatically decided by data distribution
                                                                  null, //Age automatically decided by data distribution
                                                                  newLoneParentsCount));

            Log.debug("Basic One Parent: Lone Parents taken from extras: " + newLoneParentsCount);
            Log.debug("Basic One Parent: remaining Extras: " + extrasHandler.remainingExtras());
        }


        if (count > children.size()) {

            Log.debug("Basic One Parent: required Children: " + count);
            Log.debug("Basic One Parent: remaining Children: " + children.size());
            Log.debug("Basic One Parent: remaining Extras: " + extrasHandler.remainingExtras());

            int childrenToForm = count - children.size();
            children.addAll(extrasHandler.getChildrenFromExtras(null, null, childrenToForm));

            Log.debug("Basic One Parent: Children taken from extras: " + childrenToForm);
            Log.debug("Basic One Parent: remaining Extras: " + extrasHandler.remainingExtras());
        }


        Collections.shuffle(loneParents, random); //Mixes male and females to remove any bias to parent's gender
        Collections.sort(loneParents, ageComparator.reversed()); //Sort by age. Males and females are still mixed
        Collections.shuffle(children, random); //Mixes children to remove any bias to a gender
        Collections.sort(children, ageComparator.reversed());

        List<Family> lnParentBasic = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (children.isEmpty()) {
                throw new NotEnoughPersonsException("One Parent Basic: Not enough children - units successfully formed: " + lnParentBasic.size());
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

        Log.info("One Parent Basic: formed units: " + lnParentBasic.size());
        if (count == lnParentBasic.size()) {
            Log.info("One Parent Basic: successful");
        } else {
            Log.warn("One Parent Basic: Discarded lone parents: " + loneParents.size());
        }
        return lnParentBasic;
    }

    /**
     * Forms basic other family units need for households with an Other Family as the primary family. A family is created by randomly
     * selecting two relatives.
     *
     * @param count     The needed number of Other Family units
     * @param relatives The list of relatives in the population
     * @return The list of basic Other Family units
     */
    List<Family> formOtherFamilyBasicUnits(int count, List<Person> relatives) {

        Log.debug("Basic Other Family: required units: " + count);
        if(count *2 > relatives.size()){
            Log.debug("Basic Other Family: required Relatives: " + count*2);
            Log.debug("Basic Other Family: remaining Relatives: " + relatives.size());
            Log.debug("Basic Other Family: remaining Extras: " + extrasHandler.remainingExtras());

            int newRelativesCount = (count*2) - relatives.size();
            relatives.addAll(extrasHandler.getPersonsFromExtras(RelationshipStatus.RELATIVE,
                                                                null,
                                                                null,
                                                                newRelativesCount));

            Log.debug("Basic Other Family: Relatives taken from extras: " + newRelativesCount);
            Log.debug("Basic Other Family: remaining Extras: " + extrasHandler.remainingExtras());
        }

        List<Family> otherFamilyBasic = new ArrayList<>();
        Collections.shuffle(relatives, random);

        for (int i = 0; i < count; i++) {
            if (relatives.size() < 2) {
                throw new NotEnoughPersonsException(
                        "Basic Other Family: Not enough Relatives - successfully formed units: " + otherFamilyBasic.size());
            }
            Family f = new Family(FamilyType.OTHER_FAMILY);
            f.addMember(relatives.remove(0));
            f.addMember(relatives.remove(0));
            otherFamilyBasic.add(f);
        }


        Log.info("Basic Other Family: formed units: " + otherFamilyBasic.size());
        if (otherFamilyBasic.size() == count) {
            Log.info("Basic Other Family: successful");
        }

        return otherFamilyBasic;
    }

    /**
     * Forms basic married couple units. Only consider heterosexual relationships. First sorts all males and females in age descending
     * order. Then pair them in order they appear in respective lists. This ensures age wise natural looking relationships. Method alters
     * input lists. If there are not enough married males or females, new instances are created from extras.
     *
     * @param count          The number of couples to make
     * @param marriedMales   list of married males
     * @param marriedFemales list of married females
     * @return list of couples
     */
    List<Family> formCoupleFamilyBasicUnits(int count, List<Person> marriedMales, List<Person> marriedFemales) {

        Log.debug("Basic Couple: required units: " + count);
        if (count > marriedMales.size()) {
            Log.debug("Basic Couple: required Married Males: " + count);
            Log.debug("Basic Couple: remaining Married Males: " + marriedMales.size());
            Log.debug("Basic Couple: remaining Extras: " + extrasHandler.remainingExtras());
            Log.debug("Basic Couple: remaining Married Extras: " + extrasHandler.remainingMarriedExtras());

            int newMalesCount = count - marriedMales.size();
            marriedMales.addAll(extrasHandler.getPersonsFromExtras(RelationshipStatus.MARRIED,
                                                                   Sex.Male,
                                                                   null,
                                                                   newMalesCount));

            Log.debug("Basic Couple: Married Males taken from extras: " + newMalesCount);
            Log.debug("Basic Couple: remaining Extras: " + extrasHandler.remainingExtras());
            Log.debug("Basic Couple: remaining Married Extras: " + extrasHandler.remainingMarriedExtras());

        }

        if (count > marriedFemales.size()) {
            Log.debug("Basic Couple: required Married Females: " + count);
            Log.debug("Basic Couple: remaining Married Females: " + marriedMales.size());
            Log.debug("Basic Couple: remaining Extras: " + extrasHandler.remainingExtras());
            Log.debug("Basic Couple: remaining Married Extras: " + extrasHandler.remainingMarriedExtras());

            int newFemalesCount = count - marriedFemales.size();
            marriedFemales.addAll(extrasHandler.getPersonsFromExtras(RelationshipStatus.MARRIED,
                                                                     Sex.Female,
                                                                     null,
                                                                     newFemalesCount));

            Log.debug("Basic Couple: Married Females taken from extras: " + newFemalesCount);
            Log.debug("Basic Couple: remaining Extras: " + extrasHandler.remainingExtras());
            Log.debug("Basic Couple: remaining Married Extras: " + extrasHandler.remainingMarriedExtras());
        }

        //Sort two lists in age descending order
        //TODO: Younger married persons may be over represented in married-extra list
        Collections.sort(marriedMales, ageComparator.reversed());
        Collections.sort(marriedFemales, ageComparator.reversed());

        int diff = marriedMales.size() - marriedFemales.size();

        List<Family> couples = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Family f = new Family(FamilyType.BASIC);
            f.addMember(marriedMales.remove(0));
            f.addMember(marriedFemales.remove(0));
            couples.add(f);
        }

        Log.info("Basic Couple: units formed: " + couples.size());
        if (diff > 0) {
            Log.warn("Basic couples: " + diff + " young married males not used for couple families. Population may be biased");
        } else if (diff < 0) {
            Log.warn("Basic couples: " + ((-1) * diff) + " young married females not used for couple families. Population may " +
                             "be biased");
        } else {
            Log.info("Basic couples: successful");
        }
        return couples;
    }

    /**
     * Forms basic couple with children family units needed for households where a couple with children is the primary family. This method
     * alters couples and children list
     *
     * @param count    The needed number of couple with children basic family units
     * @param couples  The couple units in the population
     * @param children The children in the population
     * @return Basic couple with children family units for primary families.
     */
    List<Family> formCoupleWithChildFamilyBasicUnits(int count,
                                                     List<Family> couples,
                                                     List<Person> children) {

        Log.debug("Basic Couple With Children: required units: " + count);

        if (count > couples.size()) {
            throw new NotEnoughPersonsException("Basic Couple With Children: required units: "+count+" available couples: "+couples.size());
        }

        if (count > children.size()) {

            Log.debug("Basic Couple With Children: required Children: " + count);
            Log.debug("Basic Couple With Children: remaining Children: " + children.size());
            Log.debug("Basic Couple With Children: remaining Extras: " + extrasHandler.remainingExtras());

            int childrenToForm = count - children.size();
            children.addAll(extrasHandler.getChildrenFromExtras(null, null, childrenToForm));

            Log.debug("Basic Couple With Children: Children taken from extras: " + childrenToForm);
            Log.debug("Basic Couple With Children: remaining Extras: " + extrasHandler.remainingExtras());
        }


        // Get rid of older (O15 and Student) children quickly. Otherwise we get stuck later.
        Collections.sort(couples, new AgeRange.YoungestParentAgeComparator());
        Collections.sort(children, new AgeRange.AgeComparator());

        List<Family> cplWithChildUnits = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            if (couples.isEmpty()) {
                throw new NotEnoughPersonsException(
                        "Basic Couple With Children: not enough Couples - units successfully formed: "+cplWithChildUnits.size());
            }
            if (children.isEmpty()) {
                new NotEnoughPersonsException("Basic Couple With Children: not enough children - units successfully formed: "+children.size());
            }

            Family f = couples.remove(0);
            boolean success = addChildToFamily(f, children);
            if (success) {
                f.setType(FamilyType.COUPLE_WITH_CHILDREN);
                cplWithChildUnits.add(f);
            } else {
                couples.add(f); // move to end of the list to filter out failed couples
            }

        }

        Log.info("Basic Couple With Children: units formed: " + cplWithChildUnits.size());
        if (cplWithChildUnits.size() == count) {
            Log.info("Basic Couple With Children: successful");
        }

        return cplWithChildUnits;
    }


    /**
     * Adds a new child to the family considering population rules. Returns true if a suitable child is found and added to the family.
     * Returns false of a suitable child was not found, the family is not changed.
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
