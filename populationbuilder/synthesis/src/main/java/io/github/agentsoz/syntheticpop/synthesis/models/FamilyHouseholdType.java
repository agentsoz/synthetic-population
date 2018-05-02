package io.github.agentsoz.syntheticpop.synthesis.models;

/**
 * @author wniroshan 18/12/17
 */

/**
 * This enum relates to HCFMD - Family Household Composition (Dwelling) in
 * 2901.0 - Census of Population and Housing: Census Dictionary, 2016. 	'Visitors only household' and 'Other non-classifiable household'
 * are ignored.
 */
public enum FamilyHouseholdType {

    F1_COUPLE_ONLY(1, FamilyType.COUPLE_ONLY),
    F1_COUPLE_WITH_CHILDREN(1, FamilyType.COUPLE_WITH_CHILDREN),
    F1_ONE_PARENT(1, FamilyType.ONE_PARENT),
    F1_OTHER_FAMILY(1, FamilyType.OTHER_FAMILY),
    F2_COUPLE_ONLY(2, FamilyType.COUPLE_ONLY),
    F2_COUPLE_WITH_CHILDREN(2, FamilyType.COUPLE_WITH_CHILDREN),
    F2_ONE_PARENT(2, FamilyType.ONE_PARENT),
    F2_OTHER_FAMILY(2, FamilyType.OTHER_FAMILY),
    F3_COUPLE_ONLY(3, FamilyType.COUPLE_ONLY),
    F3_COUPLE_WITH_CHILDREN(3, FamilyType.COUPLE_WITH_CHILDREN),
    F3_ONE_PARENT(3, FamilyType.ONE_PARENT),
    F3_OTHER_FAMILY(3, FamilyType.OTHER_FAMILY),
    LONE_PERSON(1, FamilyType.LONE_PERSON),
    GROUP_HOUSEHOLD(1, FamilyType.GROUP_HOUSEHOLD);

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
