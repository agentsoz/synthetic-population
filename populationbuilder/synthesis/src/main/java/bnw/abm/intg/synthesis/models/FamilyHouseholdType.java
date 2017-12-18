package bnw.abm.intg.synthesis.models;

/**
 * @author wniroshan
 * @date 18/12/17
 */

/**
 * This enum relates to HCFMD - Family Household Composition (Dwelling) in
 * 2901.0 - Census of Population and Housing: Census Dictionary, 2016. 	'Visitors only household' and 'Other non-classifiable household'
 * are ignored.
 */
public enum FamilyHouseholdType {

    F1COUPLEONLY(1, FamilyType.COUPLEONLY),
    F1COUPLEWITHCHILDREN(1, FamilyType.COUPLEFAMILYWITHCHILDREN),
    F1ONEPARENT(1, FamilyType.ONEPARENT),
    F1OTHERFAMILY(1, FamilyType.OTHERFAMILY),
    F2COUPLEONLY(2, FamilyType.COUPLEONLY),
    F2COUPLEWITHCHILDREN(2, FamilyType.COUPLEFAMILYWITHCHILDREN),
    F2ONEPARENT(2, FamilyType.ONEPARENT),
    F2OTHERFAMILY(2, FamilyType.OTHERFAMILY),
    F3COUPLEONLY(3, FamilyType.COUPLEONLY),
    F3COUPLEWITHCHILDREN(3, FamilyType.COUPLEFAMILYWITHCHILDREN),
    F3ONEPARENT(3, FamilyType.ONEPARENT),
    F3OTHERFAMILY(3, FamilyType.OTHERFAMILY),
    LONEPERSON(1, FamilyType.LONEPERSON),
    GROUPHOUSEHOLD(1, FamilyType.GROUPHOUSEHOLD);

    private int familyCount;
    private FamilyType familyType;

    FamilyHouseholdType(int familyCount, FamilyType familyType) {
        this.familyCount = familyCount;
        this.familyType = familyType;
    }

    public int getFamilyCount() {
        return this.familyCount;
    }

    public FamilyType getFamilyType() {
        return this.familyType;
    }

}
