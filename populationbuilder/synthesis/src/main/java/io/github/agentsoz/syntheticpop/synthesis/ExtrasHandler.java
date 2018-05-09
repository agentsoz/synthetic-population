package io.github.agentsoz.syntheticpop.synthesis;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2018 by its authors. See AUTHORS file.
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
import io.github.agentsoz.syntheticpop.util.Log;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wniroshan 18 Dec 2017
 */
class ExtrasHandler {

    final Random random;
    private final List<IndRecord> indRecords;
    private List<Person> extras = new ArrayList<>();

    ExtrasHandler(List<IndRecord> indRecords, Random random) {
        this.random = random;
        this.indRecords = indRecords;
    }

    void formExtras(List<HhRecord> hhRecs) {
        int personsInHh = 0;
        int personsInInd = 0;

        for (HhRecord hhRec : hhRecs) {
            personsInHh += (hhRec.HH_COUNT * hhRec.NUM_OF_PERSONS_PER_HH);
        }
        for (IndRecord inRec : indRecords) {
            personsInInd += inRec.IND_COUNT;
        }

        int extraPersons = personsInHh - personsInInd;
        if (extraPersons > 0) {
            for (int i = 0; i < extraPersons; i++) {
                extras.add(new Person());
            }
        } else {
            Log.debug("There are " + Math.abs(extraPersons) + " more persons in the persons file than the households file");
        }

    }

    int remainingExtras() {
        return extras.size();
    }

    /**
     * Generates persons from the Extras based on specified RelationshipStatus, AgeRange and Sex characteristics. If a characteristic is set
     * to null, value for that particular characteristic is selected according to the observed probability distribution of all categories
     * under that characteristic. For example, if ageRange is null, but relStatus and sex is given, ages are assigned to agents
     * probabilistically based on the age distribution of persons in the specified relStatus and sex category.
     *
     * @param relStatus The RelationshipStatus of the persons
     * @param sex       The Sex of the persons
     * @param ageRange  The AgeRange of the persons
     * @param count     The number of persons to create
     * @return The list of newly created persons persons
     */
    List<Person> getPersonsFromExtras(RelationshipStatus relStatus, Sex sex, AgeRange ageRange, int count) {
        return getPersonsFromExtras(relStatus == null ? null : Collections.singletonList(relStatus),
                                    sex == null ? null : Collections.singletonList(sex),
                                    ageRange == null ? null : Collections.singletonList(ageRange),
                                    count);
    }


    List<Person> getPersonsFromExtras(List<RelationshipStatus> relStatus, List<Sex> sex, List<AgeRange> ageRange, int count) {

        List<Person> persons = new ArrayList<>(count);

        Iterator<Person> extrasIterator = this.extras.iterator();
        while (extrasIterator.hasNext() && persons.size() < count) {
            Person p = extrasIterator.next();

            if (sex != null && ageRange != null && sex.contains(p.getSex()) && ageRange.contains(p.getAgeRange())) {
                persons.add(p);
                extrasIterator.remove();
            }

        }

        count -= persons.size();
        if (count > 0) {
            if (this.extras.size() >= count) {
                List<Person> selected = this.extras.subList(0, count);
                persons.addAll(selected);
                this.extras.removeAll(selected);
            } else {
                throw new NotEnoughPersonsException("There are not enough persons in extras. Requested: " + count + " available: " + this
                        .extras
                        .size());
            }
        }


        //Proportionally set the properties of persons we selected
        if (!persons.isEmpty()) {
            List<IndRecord> dist = indRecords.stream()
                                             .filter(r -> (relStatus == null || relStatus.contains(r.RELATIONSHIP_STATUS)) //filter by
                                                     // relationship. If relationship status is null get all records
                                                     && (ageRange == null || ageRange.contains(r.AGE_RANGE))// Filter by age range, or
                                                     // get all if not specified
                                                     && (sex == null || sex.contains(r.SEX)))// Filter by sex, or get all if not specified
                                             .collect(Collectors.toList());

            int sum = dist.stream().mapToInt(r -> r.IND_COUNT).sum();

            for (Person p : persons) {
                int offset = random.nextInt(sum);
                int s = 0;
                for (IndRecord r : dist) {
                    s += r.IND_COUNT;
                    if (offset < s) {
                        setProperties(p, r.RELATIONSHIP_STATUS, r.SEX, r.AGE_RANGE);
                        break;
                    }
                }
            }
        }
        return persons;
    }

    /**
     * Generates children based on specified sex and ageRange. RelationshipStatus of all children produced with this method is assumed to be
     * U15_CHILD. If sex and ageRanges are given as null they are assigned probabilistically according to the observed population
     * distributions.
     *
     * @param sex      Sex of the children
     * @param ageRange AgeRange of the children
     * @param count    Number children to form
     * @return A list of children
     */
    List<Person> getChildrenFromExtras(List<Sex> sex, List<AgeRange> ageRange, int count) {
        return getPersonsFromExtras(Arrays.asList(RelationshipStatus.U15_CHILD, RelationshipStatus.STUDENT, RelationshipStatus.O15_CHILD),
                                    sex,
                                    ageRange,
                                    count);
    }


    /**
     * Add persons to extras list. Each persons relationship status is set to null. Age and Sex is not changed. The input person list is
     * cleared.
     *
     * @param persons The list of persons to add to extras
     */
    void addToExtras(List<Person> persons) {
        persons.forEach(p -> {
            p.setRelationshipStatus(null);
            p.clearFamilyID();
        });
        this.extras.addAll(persons);
        persons.clear();
    }

    /**
     * Set properties of a person. Overwrites already set properties.
     *
     * @param p         The person
     * @param relStatus Relationship Status
     * @param sex       Sex
     * @param ageRange  Age range
     */
    private void setProperties(Person p, RelationshipStatus relStatus, Sex sex, AgeRange ageRange) {
        p.setRelationshipStatus(relStatus);
        p.setAgeRange(ageRange);
        p.setSex(sex);
    }
}
