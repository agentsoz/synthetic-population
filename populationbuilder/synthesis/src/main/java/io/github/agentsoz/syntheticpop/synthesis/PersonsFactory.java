package io.github.agentsoz.syntheticpop.synthesis;

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
