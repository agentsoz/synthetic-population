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
    RELATIVE,
    /**
     * RLHP: Lone person under Non-family member category
     */
    LONE_PERSON,
    /**
     * RLHP: Husband, Wife or Partner - includes registered and de facto ( including same sex) couples
     */
    MARRIED,
    /**
     * RLHP: Child under 15
     */
    U15_CHILD,
    /**
     * RLHP: Dependent student
     */
    STUDENT,
    /**
     * RLHP: Non-dependent student combined
     */
    O15_CHILD,
    /**
     * RLHP: 	Lone parent
     */
    LONE_PARENT,
    /**
     * RLHP: Group household member under Non-family member category
     */
    GROUP_HOUSEHOLD,

    /**
     * This is a custom type that combine U15_CHILD, STUDENT and O15_CHILD. Only to be used when generating summary reports.
     */
    CHILDREN;
}
