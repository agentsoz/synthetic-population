package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author wniroshan 15 Mar 2018
 */
public class NonPrimaryFamilyManager {

    private final Random random;
    private final ExtrasHandler extrasHandler;

    public NonPrimaryFamilyManager(Random random, ExtrasHandler extrasHandler) {
        this.random = random;
        this.extrasHandler = extrasHandler;
    }

    void addNonPrimaryFamilies(Map<FamilyType, Integer> primaryFamilyRelDist,
                               Map<FamilyHouseholdType, List<Household>> households,
                               List<Family> unusedCouples,
                               List<Family> unusedOneParentBasic) {

        int couples = primaryFamilyRelDist.get(FamilyType.COUPLE_ONLY);
        int oneParent = primaryFamilyRelDist.get(FamilyType.ONE_PARENT);
        int other = primaryFamilyRelDist.get(FamilyType.OTHER_FAMILY);


    }

    private void assignOneParentBasicToNonPrimaryFamilies(Map<FamilyHouseholdType, List<Household>> households,
                                                          List<Family> unusedOneParentBasic) {
        assignNonPrimaryFamilies(households,
                                 FamilyType.ONE_PARENT,
                                 unusedOneParentBasic,
                                 FamilyType.ONE_PARENT,
                                 FamilyType.COUPLE_WITH_CHILDREN);
        if (!unusedOneParentBasic.isEmpty()) {
            extrasHandler.addToExtras(unusedOneParentBasic.stream()
                                                          .map(f -> f.getMembers())
                                                          .flatMap(List::stream)
                                                          .filter(m -> m.getRelationshipStatus() == RelationshipStatus.LONE_PARENT)
                                                          .collect(
                                                                  Collectors.toList()));

        }
    }

    /**
     * Assigns the available basic units to multi-family households as non-primary families. This method filters in the households where the
     * primary family belong to any of the eligible FamilyType(s) specified. Then households are randomly selected for each assignment. A
     * household may be selected multiple times if it can contain multiple non-primary families (e.g. 3 family households). The FamilyType
     * of the newly added family is set to FamilyType specified as nonPrimaryFamilyType. This method alters the list of available basic
     * family units. This method assumes primary family of a multifamily household only consists of its basic members.
     *
     * @param householdsMap        The map of households by FamilyHouseholdType
     * @param nonPrimaryFamilyType The FamilyType of the newly added non primary families
     * @param basicUnits           The list of basic family units to be added as non-primary families to multi-family (must contain families
     *                             of only one FamilyType) households in @param households
     * @param eligibleFamilyTypes  The array of FamilyType(s) that the primary family of the household can belong to.
     */
    private void assignNonPrimaryFamilies(Map<FamilyHouseholdType, List<Household>> householdsMap,
                                          FamilyType nonPrimaryFamilyType,
                                          List<Family> basicUnits,
                                          FamilyType... eligibleFamilyTypes) {

        Log.info("Adding " + nonPrimaryFamilyType + " as non primary");
        Log.debug(nonPrimaryFamilyType + ": available family units: " + basicUnits.size());

        List<Household> eligibleHhs = householdsMap.entrySet()
                                                   .stream()
                                                   .filter(e -> Arrays.asList(eligibleFamilyTypes).contains(e.getKey().getFamilyType()) && e
                                                           .getKey()
                                                           .getFamilyCount() > 1) //Filter hhs that belong to specified family types and have multiple
                                                   // families
                                                   .map(Map.Entry::getValue) //Get the values list from each hash map entry
                                                   .flatMap(List::stream) //flatten list of value lists to one large list
                                                   .filter(h -> h.getExpectedSize() - h.getCurrentSize() >= nonPrimaryFamilyType.basicSize()) //has
                                                   // enough vacancies
                                                   .filter(h -> h.getExpectedFamilyCount() > h.getCurrentFamilyCount())
                                                   .collect(Collectors.toList()); //Convert to an actual list

        Log.debug(nonPrimaryFamilyType + ": total eligible households: " + eligibleHhs.size());

        int added = 0, completed = 0;
        while (!basicUnits.isEmpty() && !eligibleHhs.isEmpty()) {
            int randIndex = random.nextInt(eligibleHhs.size());
            Family f = basicUnits.remove(0);
            f.setType(nonPrimaryFamilyType);
            eligibleHhs.get(randIndex).addFamily(f);
            if (eligibleHhs.get(randIndex).getExpectedFamilyCount() == eligibleHhs.get(randIndex)
                                                                                  .getCurrentFamilyCount()) {
                eligibleHhs.remove(randIndex);
                completed++;
            }
            added++;
        }

        Log.debug(nonPrimaryFamilyType + ": updated households: " + added);
        Log.debug(nonPrimaryFamilyType + ": family count completed households: " + completed);
    }


}
