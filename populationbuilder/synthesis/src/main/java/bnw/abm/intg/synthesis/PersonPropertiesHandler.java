package bnw.abm.intg.synthesis;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import bnw.abm.intg.synthesis.models.AgeRange;
import bnw.abm.intg.synthesis.models.Family;
import bnw.abm.intg.synthesis.models.Household;
import bnw.abm.intg.synthesis.models.Person;
import com.google.common.util.concurrent.AtomicDouble;

/**
 * Handles person level property assignments like Relationships and age
 * 
 * @author Bhagya N. Wickramasinghe
 *
 */
public class PersonPropertiesHandler {

	public static void buildRelationships(List<Household> allHouseholds, Random rand) {

		for (Household household : allHouseholds) {
			for (Family family : household.getFamilies()) {
				for (Person iam : family.getMembers()) {
					for (Person member : family.getMembers()) {
						if (iam == member) {
							// This is myself
							continue;
						}
						switch (iam.getType()) {
						case Married:
							switch (member.getType()) {
							case Married:
								iam.setPartner(member);
								break;
							case Child:
								iam.setChild(member);
								break;
							case Relative:
								iam.setRelative(member);
								break;
							default:
								throw new Error("Impossible configuration: I am a: " + iam.getType() + " but other memeber is a: "
										+ member.getType());
							}
							break;
						case LoneParent:
							switch (member.getType()) {
							case Child:
								iam.setChild(member);
								break;
							case Relative:
								iam.setRelative(member);
								break;
							default:
								throw new Error("Impossible configuration: I am a: " + iam.getType() + " but other memeber is a: "
										+ member.getType());
							}
							break;
						case Child:
							switch (member.getType()) {
							case Married:
								iam.setParent(member);
								break;
							case LoneParent:
								iam.setParent(member);
								break;
							case Child:
								iam.setSibling(member);
								break;
							case Relative:
								iam.setRelative(member);
								break;
							default:
								throw new Error("Impossible configuration: I am a: " + iam.getType() + " but other memeber is a: "
										+ member.getType());
							}
							break;
						case Relative:
							switch (member.getType()) {
							case GroupHousehold:
							case LonePerson:
								throw new Error("Impossible configuration: I am a: " + iam.getType() + " but other memeber is a: "
										+ member.getType());
							default:
								iam.setRelative(member);
								break;
							}
							break;
						case LonePerson:
							throw new Error("Impossible configuration: I am a: " + iam.getType()
									+ " and other memeber is not supposed to be here: " + member.getType());
						case GroupHousehold:
							switch (member.getType()) {
							case GroupHousehold:
								iam.setGroupHouseholdMember(member);
								break;
							default:
								throw new Error("Impossible configuration: I am a: " + iam.getType() + " but other memeber is a: "
										+ member.getType());
							}
							break;
						default:
							throw new Error("Impossible configuration: I am a: " + iam.getType() + " and I am not from this world!");

						}
					}
				}
			}
		}
	}

	/**
	 * Assign a random age within person's age bracket (not very good, this alter's actual year by year age distribution
	 * 
	 * @param ageRange
	 * @param random
	 * @return
	 */
	private static int getRandomAge(AgeRange ageRange, Random random) {
		int ageBound = ageRange.max() - ageRange.min() + 1;
		int age = random.nextInt(ageBound);
		return age + ageRange.min();
	}

	/**
	 * Assign age to each person in population, while preserving overall age distribution in population
	 * 
	 * @param allHouseholds
	 *            all households in population
	 * @param ageDistribution
	 *            Overall age distribution of the population
	 * @param random
	 *            Random number generator instance
	 */
	public static void assignAge(List<Household> allHouseholds, Map<Integer, Double> ageDistribution, Random random) {
		// Defining anonymous function for getting a list of person percentages in a given age range
		Function<AgeRange, List<Double>> personPercentsInAgeRange = ageRange -> ageDistribution.entrySet().stream()
				.filter(e -> (e.getKey() >= ageRange.min() & e.getKey() <= ageRange.max())).map(e -> e.getValue()).collect(Collectors.toList());

		for (Household h : allHouseholds) {
			for (Person p : h.getMembers()) {
				List<Double> agePercentages = personPercentsInAgeRange.apply(p.getAgeCat());// List of age precentages in this person's age range
				double percentagesSum = agePercentages.stream().mapToDouble(e -> e).sum(); // Sum - going to calculate probability
				// Probability this person falling in each age-year
				List<Double> ageProbability = agePercentages.stream().map(ap -> ap / percentagesSum).collect(Collectors.toList());
				AtomicDouble sum = new AtomicDouble(0);// so we can use addAndGet
				// Get cumulative distribution of age probabilities
				List<Double> cumAgeProbability = ageProbability.stream().sequential().mapToDouble(sum::addAndGet).boxed()
						.collect(Collectors.toList());

				double ageOffSet = random.nextDouble(); // Deciding age within the age range randomly

				// Find to which age-year this person belongs to and update person
				for (int i = 0; i < cumAgeProbability.size(); i++) {
					if (ageOffSet <= cumAgeProbability.get(i)) {
						p.setAge(p.getAgeCat().min() + i);
						break;
					}
				}
			}
		}
	}
}
