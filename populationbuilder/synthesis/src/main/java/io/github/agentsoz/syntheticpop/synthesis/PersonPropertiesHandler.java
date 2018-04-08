package io.github.agentsoz.syntheticpop.synthesis;

import com.google.common.util.concurrent.AtomicDouble;
import io.github.agentsoz.syntheticpop.synthesis.models.AgeRange;
import io.github.agentsoz.syntheticpop.synthesis.models.Family;
import io.github.agentsoz.syntheticpop.synthesis.models.Household;
import io.github.agentsoz.syntheticpop.synthesis.models.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Handles person level property assignments like Relationships and age
 *
 * @author wniroshan 18 Dec 2017
 */
public class PersonPropertiesHandler {

    public static void buildRelationships(List<Household> allHouseholds) {

        for (Household household : allHouseholds) {
            for (Family family : household.getFamilies()) {
                for (Person iam : family.getMembers()) {
                    for (Person member : family.getMembers()) {
                        if (iam == member) {
                            // This is myself
                            continue;
                        }
                        switch (iam.getRelationshipStatus()) {
                            case MARRIED:
                                switch (member.getRelationshipStatus()) {
                                    case MARRIED:
                                        iam.setPartner(member);
                                        break;
                                    //below is same as U15_CHILD | STUDENT | O15_CHILD
                                    case U15_CHILD:
                                    case STUDENT:
                                    case O15_CHILD:
                                        iam.setChild(member);
                                        break;
                                    case RELATIVE:
                                        iam.setRelative(member);
                                        break;
                                    default:
                                        throw new Error("Impossible configuration: I am a: " + iam.getRelationshipStatus() + " but " +
                                                                "" + "" + "other memeber is a: " + member.getRelationshipStatus());
                                }
                                break;
                            case LONE_PARENT:
                                switch (member.getRelationshipStatus()) {
                                    case U15_CHILD:
                                    case STUDENT:
                                    case O15_CHILD:
                                        iam.setChild(member);
                                        break;
                                    case RELATIVE:
                                        iam.setRelative(member);
                                        break;
                                    default:
                                        throw new Error("Impossible configuration: I am a: " + iam.getRelationshipStatus() + " but " +
                                                                "" + "" + "other memeber is a: " + member.getRelationshipStatus());
                                }
                                break;

                            case U15_CHILD:
                            case STUDENT:
                            case O15_CHILD:
                                switch (member.getRelationshipStatus()) {
                                    case MARRIED:
                                        iam.setParent(member);
                                        break;
                                    case LONE_PARENT:
                                        iam.setParent(member);
                                        break;
                                    case U15_CHILD:
                                    case STUDENT:
                                    case O15_CHILD:
                                        iam.setSibling(member);
                                        break;
                                    case RELATIVE:
                                        iam.setRelative(member);
                                        break;
                                    default:
                                        throw new Error("Impossible configuration: I am a: " + iam.getRelationshipStatus() + " but " +
                                                                "" + "" + "other memeber is a: " + member.getRelationshipStatus());
                                }
                                break;
                            case RELATIVE:
                                switch (member.getRelationshipStatus()) {
                                    case GROUP_HOUSEHOLD:
                                    case LONE_PERSON:
                                        throw new Error("Impossible configuration: I am a: " + iam.getRelationshipStatus() + " but " +
                                                                "" + "" + "other memeber is a: " + member.getRelationshipStatus());
                                    default:
                                        iam.setRelative(member);
                                        break;
                                }
                                break;
                            case LONE_PERSON:
                                throw new Error("Impossible configuration: I am a: " + iam.getRelationshipStatus() + " and other "
                                                        + "memeber is not supposed to be here: " + member.getRelationshipStatus());
                            case GROUP_HOUSEHOLD:
                                switch (member.getRelationshipStatus()) {
                                    case GROUP_HOUSEHOLD:
                                        iam.setGroupHouseholdMember(member);
                                        break;
                                    default:
                                        throw new Error("Impossible configuration: I am a: " + iam.getRelationshipStatus() + " but " +
                                                                "" + "" + "other memeber is a: " + member.getRelationshipStatus());
                                }
                                break;
                            default:
                                throw new Error("Impossible configuration: I am a: " + iam.getRelationshipStatus() + " and I am " +
                                                        "not" + " from this world!");

                        }
                    }
                }
            }
        }
    }

    /**
     * Assign a random age within person's age bracket (not very good, this alter's actual year by year age
     * distribution
     *
     * @param ageRange age range
     * @param random   Random instance
     * @return randomly selected exact age
     */
    private static int getRandomAge(AgeRange ageRange, Random random) {
        int ageBound = ageRange.max() - ageRange.min() + 1;
        int age = random.nextInt(ageBound);
        return age + ageRange.min();
    }

    /**
     * Assign age to each person in population, while preserving overall age distribution in population
     *
     * @param allHouseholds   all households in population
     * @param ageDistribution Overall age distribution of the population
     * @param random          Random number generator instance
     */
    public static void assignAge(List<Household> allHouseholds, List<Double> ageDistribution, Random random) {

        for (Household h : allHouseholds) {
            for (Person p : h.getMembers()) {
                assignAge(p, ageDistribution, random);
            }
        }
    }

    /**
     * Assign suitable age to person based on population rules and observed age distribution in the population
     *
     * @param p               The person
     * @param ageDistribution The age probability distribution
     * @param random          Random instance
     */
    private static void assignAge(Person p, List<Double> ageDistribution, Random random) {
        int[] eligibleAges = getPotentialAges(p);

        // List of age percentages in this person's age range
        List<Double> agePercentages = ageDistribution.subList(eligibleAges[0], eligibleAges[1]);
        double percentagesSum = agePercentages.stream().mapToDouble(e -> e).sum(); // Sum - going to calculate probability
        // Probability this person falling in each age-year
        List<Double> ageProbability = agePercentages.stream().map(ap -> ap / percentagesSum).collect(Collectors.toList());
        AtomicDouble sum = new AtomicDouble(0);// so we can use addAndGet
        // Get cumulative distribution of age probabilities
        List<Double> cumAgeProbability = ageProbability.stream().sequential().mapToDouble(sum::addAndGet).boxed().collect(Collectors
                                                                                                                                  .toList());
        double ageOffSet = random.nextDouble(); // Deciding age within the age range randomly

        // Find to which age-year this person belongs to and update person
        for (int i = 0; i < cumAgeProbability.size(); i++) {
            if (ageOffSet <= cumAgeProbability.get(i)) {
                p.setAge(eligibleAges[0] + i);
                break;
            }
        }
    }

    /**
     * Finds the age interval of the person based on the population rules
     *
     * @param p The person
     * @return The age interval
     */
    private static int[] getPotentialAges(Person p) {

        List<Integer> eligibleAgeRanges = IntStream.range(p.getAgeRange().min(), p.getAgeRange().max() + 1)
                                                   .boxed()
                                                   .collect(Collectors.toList());

        Person youngestParent = Stream.of(p.getMother(), p.getFather())
                                      .filter(Objects::nonNull)
                                      .min(new AgeRange.AgeComparator())
                                      .orElse(null);
        if (youngestParent != null) {
            int youngestParentAge = youngestParent.getAge() >= 0 ? youngestParent.getAge() : youngestParent.getAgeRange().min();

            for (int a = p.getAgeRange().min(); a <= p.getAgeRange().max(); a++) {
                if (!PopulationRules.validateParentChildAgeRule(youngestParentAge, a)) {
                    eligibleAgeRanges.remove((Integer) a);
                }
            }
        }

        if (p.getChildren() != null) {
            Person oldestChild = p.getChildren().stream()
                                  .filter(Objects::nonNull)
                                  .max(new AgeRange.AgeComparator())
                                  .orElse(null);

            if (oldestChild != null) {
                int oldestChildAge = oldestChild.getAge() >= 0 ? oldestChild.getAge() : oldestChild.getAgeRange().min();
                int min = eligibleAgeRanges.get(0), max = eligibleAgeRanges.get(eligibleAgeRanges.size() - 1);
                for (int age = min; age <= max; age++) {
                    if (!PopulationRules.validateParentChildAgeRule(age, oldestChildAge)) {
                        eligibleAgeRanges.remove((Integer) age);
                    }
                }
            }
        }

        eligibleAgeRanges.sort(Comparator.naturalOrder());
        if (eligibleAgeRanges.isEmpty()) {
            throw new Error("No eligible ages in the interval");
        }
        return new int[]{eligibleAgeRanges.get(0), eligibleAgeRanges.get(eligibleAgeRanges.size() - 1)};
    }
}
