package io.github.agentsoz.syntheticpop.synthesis;

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

import io.github.agentsoz.syntheticpop.synthesis.models.IndRecord;
import io.github.agentsoz.syntheticpop.synthesis.models.Person;
import io.github.agentsoz.syntheticpop.synthesis.models.RelationshipStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wniroshan 18 Dec 2017
 */
public class PersonsFactory {


    static List<Person> makeAllPersonsByRelationshipType(List<IndRecord> indRecords, RelationshipStatus... relType) {
        List<IndRecord> indRecs = DataReader.getAgentsRecordsByRelationshipStatus(indRecords, relType);
        List<Person> persons = new ArrayList<>();
        for (IndRecord rec : indRecs) {
            for (int i = 0; i < rec.IND_COUNT; i++) {
                Person p = new Person();
                p.setAgeRange(rec.AGE_RANGE);
                p.setSex(rec.SEX);
                p.setRelationshipStatus(rec.RELATIONSHIP_STATUS);
                persons.add(p);
            }
        }

        return persons;
    }
}
