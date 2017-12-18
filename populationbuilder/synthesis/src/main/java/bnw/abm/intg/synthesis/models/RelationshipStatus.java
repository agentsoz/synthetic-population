package bnw.abm.intg.synthesis.models;

/**
 * @author wniroshan
 * @date 18 Dec 2017
 */

/**
 * This refers to RLHP	Relationship in Household categories in 2901.0 - Census of Population and Housing: Census Dictionary, 2016
 * The enums are custom fields constructed by combining several categories in RLHP.
 */
public enum RelationshipStatus {

    /**
     * RLHP: Other related individual and (Unrelated individual living in family household under Non-family member category)
     */
    Relative,
    /**
     * RLHP: Lone person under Non-family member category
     */
    LonePerson,
    /**
     * RLHP: Husband, Wife or Partner - includes registered and de facto ( including same sex) couples
     */
    Married,
    /**
     * RLHP: Child under 15
     */
    U15Child,
    /**
     * RLHP: Dependent student
     */
    Student,
    /**
     * RLHP: Non-dependent student combined
     */
    O15Child,
    /**
     * RLHP: 	Lone parent
     */
    LoneParent,
    /**
     * RLHP: Group household member under Non-family member category
     */
    GroupHousehold;
}
