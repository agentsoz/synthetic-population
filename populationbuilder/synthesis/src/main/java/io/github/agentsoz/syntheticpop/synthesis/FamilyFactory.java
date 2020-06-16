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

import io.github.agentsoz.syntheticpop.synthesis.models.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FamilyFactory {

    private final Random random;
    private final ExtrasHandler extrasHandler;
    private AgeRange.AgeComparator ageComparator = new AgeRange.AgeComparator();

    FamilyFactory(Random random, ExtrasHandler extrasHandler) {
        this.random = random;
        this.extrasHandler = extrasHandler;
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
        if (count > loneParents.size()) {//We don't have enough Lone Parents. So using extras.

            //Form lone parents older than the oldest child. Otherwise we may not be able to find children for newly formed parents
            children.sort(ageComparator.reversed());
            List<AgeRange> loneParentAges = Stream.of(AgeRange.values())
                                                  .filter(pa -> PopulationRules.validateParentChildAgeRule(pa,
                                                                                                           null,
                                                                                                           children.get(0).getAgeRange()))
                                                  .collect(Collectors.toList());
            int newLoneParentsCount = count - loneParents.size();
            loneParents.addAll(extrasHandler.getPersonsFromExtras(Collections.singletonList(RelationshipStatus.LONE_PARENT),
                                                                  null, //Sex automatically decided by data distribution
                                                                  loneParentAges,
                                                                  newLoneParentsCount));
        }


        if (count > children.size()) {
            loneParents.sort(ageComparator);
            List<AgeRange> childAges = Stream.of(AgeRange.values())
                                             .filter(ca -> PopulationRules.validateParentChildAgeRule(loneParents.get(0).getAgeRange(),
                                                                                                      null,
                                                                                                      ca))
                                             .collect(Collectors.toList());
            int childrenToForm = count - children.size();
            children.addAll(extrasHandler.getChildrenFromExtras(null, childAges, childrenToForm));
        }


        Collections.shuffle(loneParents, random); //Mixes male and females to remove any bias to parent's gender
        loneParents.sort(ageComparator.reversed());//Sort by age. Males and females are still mixed
        Collections.shuffle(children, random); //Mixes children to remove any bias to a gender
        children.sort(ageComparator.reversed());

        List<Family> lnParentBasic = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (children.isEmpty()) {
                throw new NotEnoughPersonsException("One Parent Basic: Not enough children - units successfully formed: " + lnParentBasic
                        .size());
            }

            Family f = new Family();
            Person loneParent = loneParents.remove(0);
            f.addMember(loneParent);
            Person child = getChildForFamily(f, children);
            if (child != null) {
                f.addMember(child);
                f.setType(FamilyType.ONE_PARENT);
                lnParentBasic.add(f);
            } else {
                loneParents.add(loneParent);
            }
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

        if (count * 2 > relatives.size()) {

            int newRelativesCount = (count * 2) - relatives.size();
            relatives.addAll(extrasHandler.getPersonsFromExtras(RelationshipStatus.RELATIVE,
                                                                null,
                                                                null,
                                                                newRelativesCount));
        }

        List<Family> otherFamilyBasic = new ArrayList<>();
        Collections.shuffle(relatives, random);

        for (int i = 0; i < count; i++) {
            if (relatives.size() < 2) {
                throw new NotEnoughPersonsException(
                        "Basic Other Family: Not enough Relatives - successfully formed units: " + otherFamilyBasic.size());
            }
            Family f = new Family();
            f.addMember(relatives.remove(0));
            f.addMember(relatives.remove(0));
            f.setType(FamilyType.OTHER_FAMILY);
            otherFamilyBasic.add(f);
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

        if (count > marriedMales.size()) {

            int newMalesCount = count - marriedMales.size();
            marriedMales.addAll(extrasHandler.getPersonsFromExtras(RelationshipStatus.MARRIED,
                                                                   Sex.Male,
                                                                   null,
                                                                   newMalesCount));
        }

        if (count > marriedFemales.size()) {

            int newFemalesCount = count - marriedFemales.size();
            marriedFemales.addAll(extrasHandler.getPersonsFromExtras(RelationshipStatus.MARRIED,
                                                                     Sex.Female,
                                                                     null,
                                                                     newFemalesCount));
        }

        //Sort two lists in age descending order
        //TODO: Younger married persons may be over represented in married-extra list
        marriedMales.sort(ageComparator.reversed());
        marriedFemales.sort(ageComparator.reversed());

        int diff = marriedMales.size() - marriedFemales.size();

        List<Family> couples = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Family f = new Family();
            f.addMember(marriedMales.remove(0));
            f.addMember(marriedFemales.remove(0));
            f.setType(FamilyType.COUPLE_ONLY);
            couples.add(f);
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
        if (count <= 0) {
            return new ArrayList<>();
        }

        if (count > couples.size()) {
            throw new NotEnoughPersonsException("Basic Couple With Children: required units: " + count + " available couples: " + couples
                    .size());
        }

        if (count > children.size()) {
            couples.sort(new AgeRange.YoungestParentAgeComparator());
            List<AgeRange> childAges = Stream.of(AgeRange.values())
                                             .filter(ca -> PopulationRules.validateParentChildAgeRule(couples.get(0)
                                                                                                             .getMembers()
                                                                                                             .get(0)
                                                                                                             .getAgeRange(),
                                                                                                      couples.get(0)
                                                                                                             .getMembers()
                                                                                                             .get(1)
                                                                                                             .getAgeRange(),
                                                                                                      ca))
                                             .collect(Collectors.toList());
            int childrenToForm = count - children.size();
            children.addAll(extrasHandler.getChildrenFromExtras(null, childAges, childrenToForm));
        }

//        Collections.shuffle(couples, random);
//        children.sort(ageComparator.reversed());

        List<Family> cplWithChildUnits = new ArrayList<>();
        Collections.shuffle(children, random);

        boolean success = false;
        Iterator<Person> childrenItr = children.iterator();
        while (childrenItr.hasNext()) {
            Person child = childrenItr.next();
            Family f = getParentCoupleForChild(child, couples);

            if (f != null) {
                childrenItr.remove();
                f.setType(FamilyType.COUPLE_WITH_CHILDREN);
                f.addMember(child);
                cplWithChildUnits.add(f);
                if (count == cplWithChildUnits.size()) {
                    break;
                }
            }
        }
        if (cplWithChildUnits.size() != count) {
            throw new NotEnoughPersonsException(
                    "Basic Couple With Children: cannot not form all requested units - units successfully formed: " + cplWithChildUnits
                            .size());
        }

        return cplWithChildUnits;
    }


    /**
     * Returns a suitable child for the family considering population rules and removes the selected child from the children list. Returns
     * null of a suitable child was not found.
     *
     * @param family   The family to add a child
     * @param children The list of children to select a child from
     * @return Suitable child instance or null
     */
    private Person getChildForFamily(Family family, List<Person> children) {
        List<Person> parents = family.getParents();

        List<Person> suitableChildren = children.stream()
                                                .filter(c -> PopulationRules.validateParentChildAgeRule(parents.get(0).getAgeRange(),
                                                                                                        parents.size() == 2 ?
                                                                                                        parents.get(1).getAgeRange() :
                                                                                                        null,
                                                                                                        c.getAgeRange()))
                                                .sorted(ageComparator)
                                                .collect(Collectors.toList());
        if (suitableChildren.isEmpty()) {
            return null;
        } else {
            int offset = random.nextInt(suitableChildren.size());
            Person newChild = suitableChildren.get(offset);
            children.remove(newChild);
            return newChild;
        }
    }


    /**
     * Returns a suitable family for the specified child considering population rules. Returns null if no suitable family
     *
     * @param child    The child looking for a family
     * @param families The list of families to select from
     * @return The selected family.
     */
    private Family getParentCoupleForChild(Person child, List<Family> families) {

        List<Family> suitableCouples = families.parallelStream().filter(f -> {
            List<Person> parents = f.getParents();
            return PopulationRules.validateParentChildAgeRule(parents.get(0), parents.size() == 2 ? parents.get(1) : null, child);
        }).collect(Collectors.toList());

        if (suitableCouples.isEmpty()) {
            return null;
        } else {
            int offset = random.nextInt(suitableCouples.size());
            Family newFamily = suitableCouples.get(offset);
            families.remove(newFamily);
            return newFamily;
        }
    }
}
