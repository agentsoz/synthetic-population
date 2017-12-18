package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.Person;
import bnw.abm.intg.synthesis.models.RelationshipStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wniroshan
 * @date 18 Dec 2017
 */
public class PersonsConstructor {

    List<Person> makeAllPersonsByRelationshipType(List<IndRecord> indrec, RelationshipStatus... relType) {
        List<IndRecord> indRecs = GroupingUtils.getAgentsByRelType(indrec, relType);
        List<Person> persons = new ArrayList<>();
        for (IndRecord rec : indRecs) {
            for (int i = 0; i < rec.indCount; i++) {
                Person p = new Person();
                p.setAgeCat(rec.ageRange);
                p.setSex(rec.sex);
                p.setType(rec.relationshipStatus);
                persons.add(p);
            }
        }

        return persons;
    }
}
