package io.github.agentsoz.syntheticpop.synthesis.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author wniroshan 27 Mar 2018
 */
public class IndRecord implements Serializable {
    private static final long serialVersionUID = -958963263790472511L;
    final public RelationshipStatus RELATIONSHIP_STATUS;
    final public Sex SEX;
    final public AgeRange AGE_RANGE;
    final public int IND_COUNT;
    final public String SA;

    public IndRecord(RelationshipStatus relStatus, Sex sex, AgeRange ageRange, int individualsCount, String sa) {
        this.AGE_RANGE = ageRange;
        this.SEX = sex;
        this.RELATIONSHIP_STATUS = relStatus;
        this.IND_COUNT = individualsCount;
        this.SA = sa;
        if (individualsCount > 0) {
            this.AGE_RANGE.markNotEmpty(true);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        IndRecord indRecord = (IndRecord) o;
        return IND_COUNT == indRecord.IND_COUNT &&
                RELATIONSHIP_STATUS == indRecord.RELATIONSHIP_STATUS &&
                SEX == indRecord.SEX &&
                AGE_RANGE == indRecord.AGE_RANGE &&
                Objects.equals(SA, indRecord.SA);
    }

    @Override
    public int hashCode() {

        return Objects.hash(RELATIONSHIP_STATUS, SEX, AGE_RANGE, IND_COUNT, SA);
    }
}
