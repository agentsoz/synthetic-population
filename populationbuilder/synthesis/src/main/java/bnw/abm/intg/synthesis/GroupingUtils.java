package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.FamilyHouseholdType;
import bnw.abm.intg.synthesis.models.RelationshipStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wniroshan
 */
public class GroupingUtils {
    static List<HhRecord> getHhsByFamilyType(List<HhRecord> hhrecs, FamilyHouseholdType... familyTypes) {
        List<HhRecord> shortlist = new ArrayList<>();
        for (int j = 0; j < familyTypes.length; j++) {
            for (int i = 0; i < hhrecs.size(); i++) {
                if (hhrecs.get(i).familyCountPerHousehold == familyTypes[j].getFamilyCount() &&
                        hhrecs.get(i).primaryFamilyType == familyTypes[j].getFamilyType()) {
                    shortlist.add(hhrecs.get(i));
                }
            }
        }
        return shortlist;
    }

    static List<IndRecord> getAgentsByRelType(List<IndRecord> indrecs, RelationshipStatus... relStates) {
        List<IndRecord> shortlist = new ArrayList<>();
        for (int j = 0; j < relStates.length; j++) {
            for (int i = 0; i < indrecs.size(); i++) {
                if (indrecs.get(i).relationshipStatus == relStates[j]) {
                    shortlist.add(indrecs.get(i));
                }
            }
        }
        return shortlist;

    }
}
