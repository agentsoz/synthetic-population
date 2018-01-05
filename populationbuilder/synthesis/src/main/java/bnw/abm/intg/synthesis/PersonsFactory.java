package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.Person;
import bnw.abm.intg.synthesis.models.RelationshipStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wniroshan 18 Dec 2017
 */
public class PersonsFactory {

    List<Person> makeAllPersonsByRelationshipType(List<IndRecord> indrec, RelationshipStatus... relType) {
        List<IndRecord> indRecs = DataReader.getAgentsByRelType(indrec, relType);
        List<Person> persons = new ArrayList<>();
        for (IndRecord rec : indRecs) {
            for (int i = 0; i < rec.indCount; i++) {
                Person p = new Person();
                p.setAgeRange(rec.ageRange);
                p.setSex(rec.sex);
                p.setRelationshipStatus(rec.relationshipStatus);
                persons.add(p);
            }
        }

        return persons;
    }
}
