package io.github.agentsoz.syntheticpop.synthesis.models;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2019 by its authors. See AUTHORS file.
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

import io.github.agentsoz.syntheticpop.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class Household {


    private static long IDCounter = 1;
    private static Map<String, Family> familiesAddedToHouseholds = new HashMap<>();
    private int expectedSize;
    private FamilyHouseholdType familyHhType;
    private String SA2_NAME;
    private List<Family> families;
    private String householdID;
    private String address;

    private String tenlld;
    private String SA1_7DIG11;
    private String SA2_MAINCODE;

    public Household(int expectedSize, FamilyHouseholdType expectedFamilyHouseholdType, String sa2) {
        this.expectedSize = expectedSize;
        this.familyHhType = expectedFamilyHouseholdType;
        this.SA2_NAME = sa2;

        this.families = new ArrayList<>();
        this.householdID = String.valueOf(IDCounter++);
    }

    public Household() {}

    public String getSA2MainCode() {
        return this.SA2_MAINCODE;
    }

    public void setSA2MainCode(String mainCode) {
        this.SA2_MAINCODE = mainCode;
    }

    public String getSA2Name() {
        return this.SA2_NAME;
    }

    public String getSA1Code() {
        return SA1_7DIG11;
    }

    public void setSA1Code(String sa1code) {
        if (this.SA1_7DIG11 == null) {
            this.SA1_7DIG11 = sa1code;
        } else {
            throw new Error("Already has an SA1: " + this.SA1_7DIG11);
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the tenlld
     */
    public String getTenlld() {
        return tenlld;
    }

    /**
     * @param tenlld the tenlld to set
     */
    public void setTenlld(String tenlld) {
        if (this.tenlld == null) {
            this.tenlld = tenlld;
        } else {
            throw new Error("Overwriting tenure and landlord type");
        }
    }

    public String getID() {
        return householdID;
    }

    public void setID(String ID) {
        this.householdID = ID;
    }

    /**
     * @return the families
     */
    public List<Family> getFamilies() {
        return families;
    }

    /**
     * @param family the family to add
     */
    public void addFamily(Family family) {
        if (this.getCurrentFamilyCount() == this.getExpectedFamilyCount()) {
            throw new Error("The household already has " + this.getExpectedFamilyCount() + " families");
        }
        if (this.getCurrentSize() + family.size() > this.getExpectedSize()) {
            throw new Error("Adding new family will exceed expected household size. expected size: " + this.getExpectedSize() + " current" +
                                    " size: " + this
                    .getCurrentSize() + " new family size: " + family.size());
        }
        if (this.families.contains(family)) {
            throw new Error("Family already exists in this household");
        } else {

            this.families.add(family);
            Household.familiesAddedToHouseholds.put(family.getID(), family);
        }
    }

    /**
     * Number of members in households
     *
     * @return number of members
     */
    public int getCurrentSize() {
        int size = 0;
        for (Family family : families) {
            size += family.size();
        }
        return size;
    }

    /**
     * Number of families in household
     *
     * @return number of families
     */
    public int getCurrentFamilyCount() {
        return families.size();
    }

    public List<Person> getMembers() {
        List<Person> members = new ArrayList<>();
        for (Family family : families) {
            members.addAll(family.getMembers());
        }
        return members;
    }

    public int getExpectedFamilyCount() {
        return this.familyHhType.getFamilyCount();
    }

    public FamilyHouseholdType getFamilyHouseholdType() {
        return familyHhType;
    }

    public boolean validate() {
        if (this.getCurrentSize() != this.getExpectedSize() | this.getCurrentFamilyCount() != this.getExpectedFamilyCount()) {
            Log.warn("Household validation: Current size: " + this.getCurrentSize() + " Expected size: " + this.getExpectedSize()
                             + " Current families: " + getCurrentFamilyCount() + " Expected families: " + this.getExpectedFamilyCount());
            return false;
        }
        if (getPrimaryFamilyType() != FamilyType.COUPLE_WITH_CHILDREN
                & getFamilies().stream()
                               .filter(family -> family.getType() == FamilyType.COUPLE_WITH_CHILDREN)
                               .count() > 0) {
            Log.warn(
                    "Household validation: Primary family: " + getPrimaryFamilyType() + " Secondary: " + getFamily(1).getType());
            return false;
        }

        if (getPrimaryFamilyType() != this.familyHhType.getFamilyType()) {
            Log.warn(
                    "Household validation: Expected primary family: " + this.familyHhType.getFamilyType() + " Actual primary family: " +
                            getPrimaryFamilyType());
            return false;
        }

        return true;

    }

    public int getExpectedSize() {
        return expectedSize;
    }

    /**
     * Get the family in the household by index
     *
     * @param i index
     * @return i-th family
     */
    public Family getFamily(int i) {
        return families.get(i);
    }

    public FamilyType getPrimaryFamilyType() {
        return families.get(0).getType();
    }

    public void setPrimaryFamilyType(FamilyType familyType) {
        families.get(0).setType(familyType);
    }

    public Family getPrimaryFamily() {
        return families.get(0);
    }

    @Override
    public String toString() {
        return "Household id:" + this.getID() + " type:" + getExpectedSize() + "-" + getFamilyHouseholdType()
                + " | Current: size:" + getCurrentSize() + " families:" + getCurrentFamilyCount()
                + " 1st:" + getPrimaryFamilyType()
                + " 2nd:" + ((getCurrentFamilyCount() > 1) ? getFamily(1).getType() : null)
                + " 3rd:" + ((getCurrentFamilyCount() > 2) ? getFamily(2).getType() : null);
    }
}
