package io.github.agentsoz.syntheticpop.synthesis.models;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2020 by its authors. See AUTHORS file.
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
