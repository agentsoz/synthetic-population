package io.github.agentsoz.syntheticpop.synthesis;

import io.github.agentsoz.syntheticpop.synthesis.models.FamilyType;
import io.github.agentsoz.syntheticpop.synthesis.models.HhRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wniroshan 15 Mar 2018
 */
public class PrimaryFamilyManager {

    /**
     * Find the distribution of couples, one parent and other family distribution in primary families. Couple with children families are
     * counted as couple units
     *
     * @param hhRecords HhRecords
     * @return The distribution
     */
    private Map<FamilyType, Integer> getRelationshipsDistributionInPrimaryFamilies(List<HhRecord> hhRecords) {
        Map<FamilyType, Integer> distribution = new HashMap<>(3, 1);
        int couples = 0, oneParent = 0, otherFamily = 0;
        for (HhRecord hhRecord : hhRecords) {
            switch (hhRecord.FAMILY_HOUSEHOLD_TYPE.getFamilyType()) {
                case COUPLE_WITH_CHILDREN:
                    couples += hhRecord.HH_COUNT;
                    break;
                case COUPLE_ONLY:
                    couples += hhRecord.HH_COUNT;
                    break;
                case ONE_PARENT:
                    oneParent += hhRecord.HH_COUNT;
                    break;
                case OTHER_FAMILY:
                    otherFamily += hhRecord.HH_COUNT;
                    break;
                case LONE_PERSON:
                    break;
                case GROUP_HOUSEHOLD:
                    break;
                default:
                    throw new IllegalStateException("Unrecognised Family Type: " + hhRecord.FAMILY_HOUSEHOLD_TYPE.getFamilyType());
            }
        }
        distribution.put(FamilyType.COUPLE_ONLY, couples);
        distribution.put(FamilyType.ONE_PARENT, oneParent);
        distribution.put(FamilyType.OTHER_FAMILY, otherFamily);
        return distribution;
    }


}
