package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.Log;

import java.util.*;

public class FamilyConstructor {

    final private List<Person> marriedMales, marriedFemales, children, relatives;
    final private Random random;

    FamilyConstructor(List<Person> marriedMales, List<Person> marriedFemales, List<Person> loneParents, List<Person> children, List<Person> relatives, Random random) {
        this.children = children;
        this.relatives = relatives;
        this.marriedMales = marriedMales;
        this.marriedFemales = marriedFemales;
        this.random = random;
    }



    List<Family> makeAllBasicLoneParentStructs(List<Person> lnParents) {
        List<Family> lnParentBasic = new ArrayList<>();
        int lnpcnt = lnParents.size();
        for (int i = 0; i < lnpcnt; i++) {
            if (children.isEmpty()) {
                Log.warn("Lone Parent Basic: Not enough children");
                Log.warn("Lone Parent Basic: Discarded Lone Parents: " + lnParents.size());
            }
            Family f = new Family(FamilyType.BASIC);
            f.addMember(lnParents.remove(0));
            f.addMember(children.remove(0));
            lnParentBasic.add(f);
        }

        Log.info("Lone Parent Basic: formed strucures: " + lnParentBasic.size());
        if (lnParents.isEmpty()) {
            Log.info("Lone Parent Basic: All structures created");
        }
        return lnParentBasic;
    }

    List<Family> makeAllPrimaryOtherFamilyBasicStructs(List<HhRecord> otherFamilyRecords) {


        List<Family> otherFamilyBasic = new ArrayList<>();
        Collections.shuffle(relatives, random);
        int unfomredFamilycount = 0;
        for (HhRecord hhrec : otherFamilyRecords) {
            if (relatives.size() < 2) {
                unfomredFamilycount += (hhrec.hhCount);
                continue;
            }
            for (int i = 0; i < hhrec.hhCount; i++) {
                if (relatives.size() < 2) {
                    unfomredFamilycount += (hhrec.hhCount - i);
                    Log.warn(
                            "Other Family Basic Primary Families: Not engough Relatives to form more Basic Other Family structures");
                    break;
                }
                Family fm = new Family(hhrec.primaryFamilyType);
                fm.addMember(relatives.remove(0));
                fm.addMember(relatives.remove(0));
                otherFamilyBasic.add(fm);
            }
        }

        Log.info("Other Family Basic Primary Families: Structures formed: " + otherFamilyBasic.size());
        if (unfomredFamilycount > 0) {
            Log.warn("Other Family Basic Primary Families: Unformed structures: " + unfomredFamilycount);
        } else {
            Log.info("Other Family Basic Primary Families: All structures created");
        }
        return otherFamilyBasic;
    }

    /**
     * Forms basic married couple units.
     *
     * @param marriedMales list of married males
     * @param marriedFemales list of married females
     * @return list of couples
     */
    List<Family> makeAllMarriedCouples(List<Person> marriedMales, List<Person> marriedFemales) {

        //Sort two lists in age descending order
        AgeRange.AgeComparator ageComparator = new AgeRange.AgeComparator();
        Collections.sort(marriedMales, ageComparator.reversed());
        Collections.sort(marriedFemales,ageComparator.reversed());

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
            Log.warn("Forming Married couples: " + diff+" young married males discarded. Population may be biased");
        } else if (diff < 0) {
            Log.warn("Forming Married couples: " + ((-1) * diff)+" young married females discarded. Population may be biased");
        } else {
            Log.info("Forming Married couples: no issues");
        }
        return fl;
    }

    List<Family> makePrimaryCoupleWithChildFamilyBasicStructs(List<HhRecord> cplYesChldRecords) {
        List<Family> cplwChld = new ArrayList<>();
        int unformed = 0;
        for (HhRecord hhrec : cplYesChldRecords) {
            if (marriedMales.isEmpty() || marriedFemales.isEmpty() || children.isEmpty()) {
                unformed += hhrec.hhCount;
            }
            for (int i = 0; i < hhrec.hhCount; i++) {
                if (marriedMales.isEmpty()) {
                    Log.warn("Couple With Children Basic Primary Families: Not enough married males");
                    unformed += (hhrec.hhCount - 1);
                    break;
                }
                if (marriedFemales.isEmpty()) {
                    Log.warn("Couple With Children Basic Primary Families: Not enough married females");
                    unformed += (hhrec.hhCount - 1);
                    break;
                }
                if (children.isEmpty()) {
                    Log.warn("Couple With Children Basic Primary Families: Not enough children");
                    unformed += (hhrec.hhCount - 1);
                    break;
                }
                Family f = new Family(FamilyType.COUPLE_WITH_CHILDREN);
                f.addMember(marriedMales.remove(0));
                f.addMember(marriedFemales.remove(0));
                f.addMember(children.remove(0));
                cplwChld.add(f);
            }
        }
        Log.info("Couple With Children Basic Primary Families: formed structures: " + cplwChld.size());
        if (unformed > 0) {
            Log.warn("Couple With Children Basic Primary Families: Unformed structers: " + unformed);
        } else {
            Log.info("Couple With Children Basic Primary Families: All strucures created");
        }
        return cplwChld;
    }

    List<Family> makePrimaryCoupleOnlyFamilyBasicStructs(List<HhRecord> coupleOnlyRecords) {

        List<Family> cplOnly = new ArrayList<>();
        int unformed = 0;
        for (HhRecord hhrec : coupleOnlyRecords) {
            if (marriedMales.isEmpty()) {
                unformed += hhrec.hhCount;
                continue;
            }
            if (marriedFemales.isEmpty()) {
                unformed += hhrec.hhCount;
                continue;
            }
            for (int i = 0; i < hhrec.hhCount; i++) {
                if (marriedMales.isEmpty()) {
                    Log.warn("Couple Only Primary Families: Not enough married males");
                    unformed += (hhrec.hhCount - i);
                    break;
                }
                if (marriedFemales.isEmpty()) {
                    Log.warn("Couple Only Primary Families: Not enough married females");
                    unformed += (hhrec.hhCount - i);
                    break;
                }

                Family f = new Family(FamilyType.COUPLE_ONLY);
                f.addMember(marriedMales.remove(0));
                f.addMember(marriedFemales.remove(0));

                cplOnly.add(f);
            }
        }
        Log.info("Couple Only Primary Families: formed structures: " + cplOnly.size());
        if (unformed > 0) {
            Log.warn("Couple Only Primary Families: Unformed structers: " + unformed);
        } else {
            Log.info("Couple Only Primary Families: All strucures created");
        }
        return cplOnly;
    }
}
