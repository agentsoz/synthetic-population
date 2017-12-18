package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.Log;

import java.util.*;

public class FamilyConstructor {

    final private List<Person> marriedMales, marriedFemales, children, relatives;
    final private Random random;

    FamilyConstructor(List<Person> marriedMales, List<Person> marriedFemales, List<Person> children, List<Person> relatives, Random random) {
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
            Log.warn("Other Family Basic Primary Families: Unformed strcutres: " + unfomredFamilycount);
        } else {
            Log.info("Other Family Basic Primary Families: All structres created");
        }
        return otherFamilyBasic;
    }
//TODO: Delete this code if not used
//    List<Family> makeAllMarriedCouples(List<HhRecord> hhrecs, List<IndRecord> indrec) {
//        List<IndRecord> married = GroupingUtils.getAgentsByRelType(indrec, imarried);
//        List<Person> maleMarried = new ArrayList<>();
//        List<Person> femaleMarried = new ArrayList<>();
//
//        for (IndRecord ind : married) {
//            for (int i = 0; i < ind.indCount; i++) {
//                Person p = new Person();
//                p.setSex(ind.sex);
//                p.setAgeCat(ind.ageRange);
//                p.setType(ind.relationshipStatus);
//                if (p.getSex() == Sex.Male) {
//                    maleMarried.add(p);
//                } else {
//                    femaleMarried.add(p);
//                }
//            }
//        }
//
//        int cpls = Math.min(maleMarried.size(), femaleMarried.size());
//        int diff = maleMarried.size() - femaleMarried.size();
//
//        List<Family> fl = new ArrayList<>();
//        for (int i = 0; i < cpls; i++) {
//            Family f = new Family(FamilyType.BASIC);
//            f.addMember(maleMarried.remove(0));
//            f.addMember(femaleMarried.remove(0));
//
//            fl.add(f);
//        }
//
//        Log.info("Married couples: Couples formed: " + cpls);
//        if (diff > 0) {
//            Log.warn("Married couples: Discarded married males: " + diff);
//        } else if (diff < 0) {
//            Log.warn("Married couples: Discarded married females: " + ((-1) * diff));
//        } else {
//            Log.info("Married couples: All couples created");
//        }
//        return fl;
//    }

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
                Family f = new Family(FamilyType.COUPLEFAMILYWITHCHILDREN);
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

                Family f = new Family(FamilyType.COUPLEFAMILYWITHCHILDREN);
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
