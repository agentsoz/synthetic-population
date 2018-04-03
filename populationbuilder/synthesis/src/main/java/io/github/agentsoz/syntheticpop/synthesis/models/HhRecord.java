package io.github.agentsoz.syntheticpop.synthesis.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author wniroshan 27 Mar 2018
 */
public class HhRecord implements Serializable {
    private static final long serialVersionUID = -5929056113924287411L;
    public final int NUM_OF_PERSONS_PER_HH;
    public final int HH_COUNT;
    public final FamilyHouseholdType FAMILY_HOUSEHOLD_TYPE;
    public final String SA;

    public HhRecord(int nofPersons, int familyCountPerHh, FamilyType familyType, int hhCount, String sa) {
        this.NUM_OF_PERSONS_PER_HH = nofPersons;
        this.HH_COUNT = hhCount;
        this.SA = sa;

        FamilyHouseholdType tempFamilyHhtype = null;
        for (FamilyHouseholdType familyHouseholdType : FamilyHouseholdType.values()) {
            if (familyHouseholdType.getFamilyCount() == familyCountPerHh && familyHouseholdType.getFamilyType() ==
                    familyType) {
                tempFamilyHhtype = familyHouseholdType;
                break;
            }
        }
        FAMILY_HOUSEHOLD_TYPE = tempFamilyHhtype;

    }

    public FamilyType getPrimaryFamilyType() {

        return this.FAMILY_HOUSEHOLD_TYPE.getFamilyType();
    }

    public int getFamilyCountPerHousehold() {
        return this.FAMILY_HOUSEHOLD_TYPE.getFamilyCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        HhRecord hhRecord = (HhRecord) o;
        return NUM_OF_PERSONS_PER_HH == hhRecord.NUM_OF_PERSONS_PER_HH &&
                HH_COUNT == hhRecord.HH_COUNT &&
                FAMILY_HOUSEHOLD_TYPE == hhRecord.FAMILY_HOUSEHOLD_TYPE &&
                Objects.equals(SA, hhRecord.SA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NUM_OF_PERSONS_PER_HH, HH_COUNT, FAMILY_HOUSEHOLD_TYPE, SA);
    }

}
