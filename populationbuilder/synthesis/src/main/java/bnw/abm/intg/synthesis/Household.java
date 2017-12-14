/**
 *
 */
package bnw.abm.intg.synthesis;

import bnw.abm.intg.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bhagya N. Wickramasinghe 19 May 2016
 */
public class Household {


    private static long IDCounter = 0;
    private static Map<String, Family> familiesAddedToHouseholds = new HashMap<>();
    private List<Family> families;
    final int TARGETSIZE;
    final int TARGETFAMLYCOUNT;
    private String householdID;
    private String tenlld;
    private String SA1_7DIG11;
    private String sa2name;
    private String SA2MAINCODE;

    public Household(int targetSize, int targetFamilyCount, String sa2) {
        this.families = new ArrayList<Family>();
        this.TARGETSIZE = targetSize;
        this.TARGETFAMLYCOUNT = targetFamilyCount;
        this.sa2name = sa2;
        this.householdID = String.valueOf(IDCounter++);
    }

    public void setSA2MainCode(String mainCode) {
        this.SA2MAINCODE = mainCode;
    }

    public String getSA2MainCode() {
        return this.SA2MAINCODE;
    }

    public String getSA2Name() {
        return this.sa2name;
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
    public int currentSize() {
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
    public int familyCount() {
        return families.size();
    }

    public List<Person> getMembers() {
        List<Person> members = new ArrayList<>();
        for (Family family : families) {
            members.addAll(family.getMembers());
        }
        return members;
    }

    public boolean validate() {
        if (this.currentSize() != this.TARGETSIZE | this.familyCount() != this.TARGETFAMLYCOUNT) {
            Log.warn("Househld validation: Current size: " + this.currentSize() + " Expected size: " + this.TARGETSIZE
                    + " Current families: " + familyCount() + " Expected families: " + this.TARGETFAMLYCOUNT);
            return false;
        }
        if (getFamilies().get(0).getType() != FamilyType.COUPLEFAMILYWITHCHILDREN
                & getFamilies().stream().filter(family -> family.getType() == FamilyType.COUPLEFAMILYWITHCHILDREN).count() > 0) {
            Log.warn(
                    "Househld validation: Primary family: " + getFamilies().get(0).getType() + " Secondary: " + getFamilies().get(1).getType());
            return false;
        }

        return true;

    }

}
