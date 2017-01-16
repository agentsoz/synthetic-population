package bnw.abm.intg.latchpop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import bnw.abm.intg.util.BNWLogger;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class GroupMaker {

    private double sexRatio, relativeProbability, femaleLoneParentProbability;

    public GroupMaker(double maleProbability, double relativeProbability, double femaleLoneParentProbability) {
	this.sexRatio = maleProbability;
	this.relativeProbability = relativeProbability;
	this.femaleLoneParentProbability = femaleLoneParentProbability;
    }

    Logger logger = BNWLogger.getLogger();
    List<Household> allHouseholds = new ArrayList<>();
    Random rand = null;

    int i1fCplOnly = 0, i1fCplChld = 1, i1f1P = 2, i1fOthr = 3, i2fCplOnly = 4, i2fCplChld = 5, i2f1P = 6, i2fOthr = 7, i3fCplOnly = 8,
	    i3fCplChld = 9, i3f1P = 10, i3fOthr = 11, ilnPerhh = 12, igrpHh = 13;
    int fmTypes = 14;

    int imarried = 0, ilnparent = 1, iu15c = 2, istu = 3, io15c = 4, igrpInd = 5, ilnperson = 6, irelt = 7;
    int ageCats = 8;
    int sexCats = 2;
    String sa2name;

    List<Family> married = null, loneParentBasic = null, nonPrimaryOtherFamilies = null;;
    List<Person> relatives = null, loneParents = null, children = null, extras = null;
    List<HhRecord> hhrecs;
    List<IndRecord> indrecs;

    List<Household> makePopulation(List<HhRecord> hhrecs, List<IndRecord> indrecs, Random rand, String sa2) {
	this.sa2name = sa2;;
	this.hhrecs = hhrecs;
	this.indrecs = indrecs;

	// printHhSummary(hhrecs);
	// printIndSummary(indrecs);

	this.rand = rand;
	extras = getExtras(hhrecs, indrecs);
	print("Extras: " + extras.size());
	makeLonePersonsHhs(hhrecs, indrecs);
	makeGroupHhs(hhrecs, indrecs);

	married = makeAllMarriedCouples(hhrecs, indrecs);
	relatives = makeAllPersonsByRelationshipType(indrecs, irelt);
	List<Family> primaryOtherFamiliesBasic = makeAllPrimaryOtherFamilyBasicStructs(hhrecs);
	loneParents = makeAllPersonsByRelationshipType(indrecs, ilnparent);
	children = makeAllPersonsByRelationshipType(indrecs, iu15c, istu, io15c);
	print("Children: " + children.size());
	loneParentBasic = makeAllBasicLoneParentStructs(loneParents);
	List<Family> primaryCoupleWChildFamilyBasic = makePrimaryCoupleWithChildFamilyBasicStructs(hhrecs);//Form basic family structs and removes couples from married list
	List<Family> primaryCoupleOnlyFamilyBasic = makePrimaryCoupleOnlyFamilyBasicStructs(hhrecs);//Form basic family structs and removes couples from married list

	print("Remaining relatives: " + relatives.size());
	formOtherFamily1FamilyHouseholds(hhrecs, primaryOtherFamiliesBasic);
	print("Remaining primary other family structures: " + primaryOtherFamiliesBasic.size());
	print("Remaining cpl only: " + primaryCoupleOnlyFamilyBasic.size());
	formCoupleOnly1FamilyHouseholds(hhrecs, primaryCoupleOnlyFamilyBasic);
	print("Remaining relatives: " + relatives.size());
	print("Remaining cpl only: " + primaryCoupleOnlyFamilyBasic.size());
	formCoupleWithChild1FamilyHouseholds(hhrecs, primaryCoupleWChildFamilyBasic);
	print("Remaining children: " + children.size());
	print("Remaining relatives: " + relatives.size());
	formLoneParent1FamilyHouseholds(hhrecs); //Forms basic family structs and removes LP and child pairs from loneParentBasic list
	print("Remaning lone parent basic structures: " + loneParentBasic.size());

	List<Household> multiFamilyHhWith1Family = formPrimaryFamiliesForMultiFamilyHouseholds(hhrecs, primaryCoupleOnlyFamilyBasic,
		primaryCoupleWChildFamilyBasic, loneParentBasic, primaryOtherFamiliesBasic);

	formBasicStructuresFor2ndAnd3rdFamiliesInMultiFamilyHouseholds(hhrecs, indrecs);
	print("all married: " + married.size());
	print("relatives: " + relatives.size());
	print("children: " + children.size());
	print("Lone parent: " + loneParentBasic.size());
	print("Other family: " + primaryOtherFamiliesBasic.size());
	print("Non primary other families: " + nonPrimaryOtherFamilies.size());
	print("Extras: " + extras.size());

	addNonPrimaryFamiliesToMultiFamilyHousehold(multiFamilyHhWith1Family);

	print("------------ Discarded persons and family structures -----------");
	print("primary couple with child basic structures: " + primaryCoupleWChildFamilyBasic.size());
	print("primary couple only: " + primaryCoupleOnlyFamilyBasic.size());
	print("primary other family: " + primaryOtherFamiliesBasic.size());
	print("basic lone parents: " + loneParentBasic.size());
	print("all married: " + married.size());
	print("relatives: " + relatives.size());
	print("lone parents: " + loneParents.size());
	print("children: " + children.size());
	print("Extras: " + extras.size());

	// printHhSummary(hhrecs);
	return allHouseholds;
    }

    void formBasicStructuresFor2ndAnd3rdFamiliesInMultiFamilyHouseholds(List<HhRecord> householdRecords, List<IndRecord> individualRecords) {
	String logprefix = "Basic structures for non-primary families in multi-family households: ";
	int familiesWithMarriedCouples = 0, loneParentFamilies = 0, otherFamilies = 0;

	List<HhRecord> f2Households = getHhsByFamlyType(householdRecords, i2f1P, i2fCplChld, i2fCplOnly, i2fOthr);
	int f2secondcount = 0;
	for (HhRecord hhRecord : f2Households) {
	    if (hhRecord.primaryFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN | hhRecord.primaryFamilyType == FamilyType.COUPLEONLY) {
		familiesWithMarriedCouples += hhRecord.hhCount;
	    } else if (hhRecord.primaryFamilyType == FamilyType.LONEPARENT) {
		loneParentFamilies += hhRecord.hhCount;
	    } else if (hhRecord.primaryFamilyType == FamilyType.OTHERFAMILY) {
		otherFamilies += hhRecord.hhCount;
	    }
	    f2secondcount += hhRecord.hhCount;
	}
	List<HhRecord> f3Households = getHhsByFamlyType(householdRecords, i3f1P, i3fCplChld, i3fCplOnly, i3fOthr);
	int f3secondcount = 0, f3thirdcount = 0;
	for (HhRecord hhRecord : f3Households) {
	    if (hhRecord.primaryFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN | hhRecord.primaryFamilyType == FamilyType.COUPLEONLY) {
		familiesWithMarriedCouples += hhRecord.hhCount;
	    } else if (hhRecord.primaryFamilyType == FamilyType.LONEPARENT) {
		loneParentFamilies += hhRecord.hhCount;
	    } else if (hhRecord.primaryFamilyType == FamilyType.OTHERFAMILY) {
		otherFamilies += hhRecord.hhCount;
	    }
	    f3secondcount += hhRecord.hhCount;
	    f3thirdcount += hhRecord.hhCount;
	}

	List<HhRecord> f1Households = getHhsByFamlyType(householdRecords, i1f1P, i1fCplChld, i1fCplOnly, i1fOthr);
	for (HhRecord hhRecord : f1Households) {
	    if (hhRecord.primaryFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN | hhRecord.primaryFamilyType == FamilyType.COUPLEONLY) {
		familiesWithMarriedCouples += hhRecord.hhCount;
	    } else if (hhRecord.primaryFamilyType == FamilyType.LONEPARENT) {
		loneParentFamilies += hhRecord.hhCount;
	    } else if (hhRecord.primaryFamilyType == FamilyType.OTHERFAMILY) {
		otherFamilies += hhRecord.hhCount;
	    }

	}

	int totalRequiredNonPrimaryFamilies = f2secondcount + f3secondcount + f3thirdcount;
	int creatableNonPrimaryOtherFamiliesWithExistingRelatives = relatives.size()/2;
	int totalAvailableBasicStructs = married.size()+loneParentBasic.size()+creatableNonPrimaryOtherFamiliesWithExistingRelatives;
        int newNonPrimaryMarriedCpls = 0, newNonPrimaryLoneParents = 0, newNonPrimaryOtherFamilies = 0;
	
	
        if (totalAvailableBasicStructs < totalRequiredNonPrimaryFamilies) {
            
            int extraNonPrimaryFamiliesToCreate = totalRequiredNonPrimaryFamilies - totalAvailableBasicStructs;

            int totalPrimaryFamilyBasicStructs = familiesWithMarriedCouples + loneParentFamilies + otherFamilies;
            newNonPrimaryMarriedCpls = Math
                    .round((familiesWithMarriedCouples / (float) totalPrimaryFamilyBasicStructs) * extraNonPrimaryFamiliesToCreate);
            newNonPrimaryLoneParents = Math.round((loneParentFamilies / (float) totalPrimaryFamilyBasicStructs) * extraNonPrimaryFamiliesToCreate);
            newNonPrimaryOtherFamilies = extraNonPrimaryFamiliesToCreate - (newNonPrimaryLoneParents + newNonPrimaryMarriedCpls);
            newNonPrimaryOtherFamilies += creatableNonPrimaryOtherFamiliesWithExistingRelatives;
            
            List<AgeRange> marriedAges = new ArrayList<>(Arrays.asList(AgeRange.A25_39, AgeRange.A40_54, AgeRange.A55_69, AgeRange.A70_84));
            for (int i = 0; i < newNonPrimaryMarriedCpls; i++) {
                Collections.shuffle(marriedAges, rand);
                Person male = extras.remove(0);
                male.setSex(Sex.Male);
                male.setType(PersonType.Married);
                male.setAgeCat(marriedAges.get(0));

                Person female = extras.remove(0);
                female.setSex(Sex.Female);
                female.setType(PersonType.Married);
                female.setAgeCat(marriedAges.get(0));

                Family family = new Family(FamilyType.BASIC);
                family.addMember(male);
                family.addMember(female);
                married.add(family);
            }
            logger.log(Level.INFO, logprefix + " new married couples: " + newNonPrimaryMarriedCpls);

            List<AgeRange> loneParentAges = new ArrayList<>(Arrays.asList(AgeRange.A25_39, AgeRange.A40_54, AgeRange.A55_69));
            for (int i = 0; i < newNonPrimaryLoneParents; i++) {
                Person child = null;
                if (children.size() != 0) {
                    child = children.remove(0);
                }

                Person parent = extras.remove(0);
                parent.setSex(selectTrueOrFalseRandomlyWithBias(rand, femaleLoneParentProbability) ? Sex.Female : Sex.Male);
                parent.setType(PersonType.LoneParent);
                List<AgeRange> parentAges = new ArrayList<>();
                if (child != null) {
                    for (int j = 0; j < AgeRange.values().length; j++) {
                        if (child.getAgeCat().max() < AgeRange.values()[j].min()) {
                            parentAges.add(AgeRange.values()[j]);
                            if (parentAges.size() == 2) {
                                if (child.getAgeCat() == AgeRange.A0_14) {
                                    parentAges.add(AgeRange.A40_54);
                                }
                                break;
                            }
                        }
                    }
                    Collections.shuffle(parentAges);
                    parent.setAgeCat(parentAges.get(0));
                } else {
                    Collections.shuffle(loneParentAges, rand);
                    parent.setAgeCat(loneParentAges.get(0));
                }

                if (child == null) {
                    child = extras.remove(0);
                    child.setSex(selectTrueOrFalseRandomlyWithBias(rand, sexRatio) ? Sex.Male : Sex.Female);
                    child.setType(PersonType.Child);
                    List<AgeRange> childages = new ArrayList<>();
                    for (int j = 0; j < AgeRange.values().length; j++) {
                        if (parent.getAgeCat().min() > AgeRange.values()[j].max() & !AgeRange.values()[j].isEmpty()) {
                            childages.add(AgeRange.values()[j]);
                        }
                    }
                    Collections.shuffle(childages, rand);
                    child.setAgeCat(childages.get(0));
                    if (childages.get(0).max() == 14) {
                        child.setChildType(ChildType.U15Child);
                    } else {
                        child.setChildType(ChildType.Student);
                    }
                }
                Family family = new Family(FamilyType.BASIC);
                family.addMember(parent);
                family.addMember(child);

                loneParentBasic.add(family);
            }
            logger.log(Level.INFO, logprefix + " lone parent basic: " + newNonPrimaryLoneParents);
        }else{
            // At this stage we can form all requiredNonPromaryOtherFamilies from existing relatives
            newNonPrimaryOtherFamilies = totalRequiredNonPrimaryFamilies - (married.size() + loneParentBasic.size());
        }
        
        
	List<AgeRange> agesAll = new ArrayList<>(Arrays.asList(AgeRange.values()));
	nonPrimaryOtherFamilies = new ArrayList<>();
	for (int i = 0; i < newNonPrimaryOtherFamilies; i++) {
	    Family family = new Family(FamilyType.OTHERFAMILY);
	    Person rel1, rel2;
	    if (relatives.size() < 2) {
		Collections.shuffle(agesAll, rand);
		rel1 = extras.remove(0);
		rel1.setSex(selectTrueOrFalseRandomlyWithBias(rand, sexRatio) ? Sex.Male : Sex.Female);
		rel1.setType(PersonType.Relative);
		rel1.setAgeCat(agesAll.get(0));

		rel2 = extras.remove(0);
		rel2.setSex(selectTrueOrFalseRandomlyWithBias(rand, sexRatio) ? Sex.Male : Sex.Female);
		rel2.setType(PersonType.Relative);
		if (rel1.getAgeCat() == AgeRange.A0_14) {
		    Collections.shuffle(agesAll, rand);
		    rel2.setAgeCat(agesAll.get(0));
		} else {
		    Collections.shuffle(agesAll, rand);
		    rel2.setAgeCat(agesAll.get(0));
		}
	    } else {
		rel1 = relatives.remove(0);
		rel2 = relatives.remove(0);
	    }
	    family.addMember(rel1);
	    family.addMember(rel2);
	    nonPrimaryOtherFamilies.add(family);
	}

	logger.log(Level.INFO, logprefix + " other family basic: " + newNonPrimaryOtherFamilies);
    }

    List<Person> getExtras(List<HhRecord> hhrecs, List<IndRecord> indrecs) {
	int personsInHh = 0;
	int personsInInds = 0;
	List<Person> extras = new ArrayList<>();
	for (HhRecord hhrec : hhrecs) {
	    personsInHh += (hhrec.hhCount * hhrec.numOfPersonsPerHh);
	}
	for (IndRecord inrec : indrecs) {
	    personsInInds += inrec.indCount;
	}

	int extraPersons = personsInHh > personsInInds? (personsInHh-personsInInds): 0;
	for (int i = 0; i < extraPersons; i++) {
	    extras.add(new Person());
	}
	return extras;
    }

    // List<Person> drawFromExtras(PersonType personType, int count) {
    // List<Person> selected = extras.subList(0, count);
    // for (Person person : selected) {
    // person.setType(personType;
    // }
    // extras.subList(0, count).clear();
    // return selected;
    // }

    void print(Object l) {
	System.out.println(l.toString());
    }

    /**
     * Randomly decides True or False. Occurrence of True can be biased by specifying the ratio for being True
     * 
     * @param rand
     *            Random object
     * @param bias
     *            By how much should occurrence of True be biased. e.g. if bias == 0.25, probability of method returning True is 1/4.
     * @return true or false
     */
    boolean selectTrueOrFalseRandomlyWithBias(Random rand, double bias) {
	double r = rand.nextDouble();
	return (r < bias) ? true : false;
    }

    int pickRandomly(int... fromvals) {
	int r = rand.nextInt(fromvals.length);
	return fromvals[r];
    }

    void summary() {
	print("------------ Formed households -----------");
	int wanted = 0;
	print("Total households formed: " + allHouseholds.size());
	print("Distribution of missing persons in formed households by size:");
	int[] count = new int[9];
	for (Household h : allHouseholds) {
	    if (h.TARGETSIZE - h.currentSize() < 0) {
		print(h.TARGETSIZE + " " + h.currentSize());
	    }
	    count[h.TARGETSIZE] += (h.TARGETSIZE - h.currentSize());
	}
	print("size\t persons");
	for (int i = 1; i < count.length; i++) {
	    print(i + "\t " + count[i]);
	    wanted += count[i];
	}
	print("Total missing persons: " + wanted);
    }

    void printHhSummary(List<HhRecord> hhrecs) {
	int ttlHhs = 0;
	int cplonly = 0, cplYeschld = 0, oneparentfamily = 0;
	List<Map<String, Integer>> householdInfo = new ArrayList<Map<String, Integer>>(9);
	householdInfo.add(new LinkedHashMap<>());// Dummy for 0th element;

	Map<String, Integer> map = null;
	int previousHhsize = 0;
	for (HhRecord hhrec : hhrecs) {
	    if (hhrec.numOfPersonsPerHh != previousHhsize) {
		previousHhsize = hhrec.numOfPersonsPerHh;
		map = new LinkedHashMap<>();
		householdInfo.add(map);
	    }
	    map.put(hhrec.familyCountPerHousehold + "" + hhrec.primaryFamilyType, 0);
	    if (hhrec.primaryFamilyType == FamilyType.COUPLEONLY) {
		cplonly += (hhrec.hhCount);
	    }
	    if (hhrec.primaryFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN) {
		cplYeschld += hhrec.hhCount;
	    }
	    if (hhrec.primaryFamilyType == FamilyType.LONEPARENT) {
		oneparentfamily += hhrec.hhCount;
	    }
	}

	for (Household household : allHouseholds) {
	    Family primaryFamily = household.getFamilies().get(0);
	    Map<String, Integer> hhmap = householdInfo.get(household.TARGETSIZE);
	    int hhcount = hhmap.get(household.TARGETFAMLYCOUNT + "" + primaryFamily.getType());
	    hhmap.put(household.TARGETFAMLYCOUNT + "" + primaryFamily.getType(), hhcount + 1);
	}
	System.out.println("Total Households: " + ttlHhs);
	System.out.println("Couple Only Hhs: " + cplonly);
	System.out.println("Couple with children Hhs: " + cplYeschld);
	System.out.println("One parent family Hhs: " + oneparentfamily);

	print("size\tDesctiption\t\t\t\t\t\t\ttarget\tcurrent");
	for (HhRecord hhrec : hhrecs) {
	    int numberOfPersons = hhrec.numOfPersonsPerHh;
	    String familyDescription = hhrec.familyCountPerHousehold + " Family: " + hhrec.primaryFamilyType.description();

	    /* Just esthetics - nothing important */
	    int stringlengthdifference = 62 - familyDescription.length();
	    String tabspace = new String(new char[stringlengthdifference]).replace("\0", " ");

	    print(numberOfPersons + "\t" + familyDescription + tabspace + "\t" + hhrec.hhCount + "\t"
		    + householdInfo.get(numberOfPersons).get(hhrec.familyCountPerHousehold + "" + hhrec.primaryFamilyType));
	}

    }

    void printIndSummary(List<IndRecord> indRecords) {
	int ttlInds = 0, marriedMale = 0, marriedFemale = 0, children = 0, lnp = 0;
	for (IndRecord indrec : indRecords) {
	    ttlInds += indrec.indCount;
	    if (indrec.sex == Sex.Female && indrec.personType == PersonType.Married) {
		marriedFemale += indrec.indCount;
	    }
	    if (indrec.personType == PersonType.Married && indrec.sex == Sex.Male) {
		marriedMale += indrec.indCount;
	    }
	    if (indrec.personType == PersonType.Child) {
		children += indrec.indCount;
	    }
	    if (indrec.personType == PersonType.LonePerson) {
		lnp += indrec.indCount;
	    }
	}

	System.out.println("Total Individuals: " + ttlInds);
	System.out.println("Male Married: " + marriedMale);
	System.out.println("Female Married: " + marriedFemale);
	System.out.println("Children: " + children);
	System.out.println("Lone parents: " + lnp);
    }

    List<Household> addPrimaryFamilytoMultiFamily(List<HhRecord> hhrecs, List<Family> primaryFamilies, String logprefix, int hhcode) {
	List<HhRecord> selectedHouseholdRecords = getHhsByFamlyType(hhrecs, hhcode);
	List<Household> partiallyFormedMultiFamilyHouseholds = new ArrayList<>();
	int formed = 0, unformed = 0;
	for (HhRecord householdRecord : selectedHouseholdRecords) {
	    if (primaryFamilies.isEmpty()) {
		unformed += householdRecord.hhCount;
		continue;
	    }
	    for (int i = 0; i < householdRecord.hhCount; i++) {
		if (primaryFamilies.isEmpty()) {
		    logger.log(Level.WARNING, logprefix + ": Not enough couple only primary families ");
		    unformed += (householdRecord.hhCount - i);
		    break;
		}
		Household household = new Household(householdRecord.numOfPersonsPerHh, householdRecord.familyCountPerHousehold, sa2name);
		household.addFamily(primaryFamilies.remove(0));
		household.getFamilies().get(0).setType(householdRecord.primaryFamilyType);
		partiallyFormedMultiFamilyHouseholds.add(household);
		formed++;
	    }
	}
	logger.log(Level.INFO, logprefix + ": formed primary families: " + formed);
	if (unformed > 0) {
	    logger.log(Level.WARNING, logprefix + ": unformed primary families: " + unformed);
	} else {
	    logger.log(Level.INFO, logprefix + ": All primary families of households created");
	}
	return partiallyFormedMultiFamilyHouseholds;
    }

    List<Household> formPrimaryFamiliesForMultiFamilyHouseholds(List<HhRecord> hhrecs, List<Family> cplOnlyPrimaryFamilies,
	    List<Family> cplYsChldPrimaryFamilies, List<Family> loneParentFamilies, List<Family> primaryOtherFamilies) {
	List<Household> multiFamilyHhs = null, tempHhWith1Family = new ArrayList<>();
	multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs, cplOnlyPrimaryFamilies, "Two family, Couple only households", i2fCplOnly);
	tempHhWith1Family.addAll(multiFamilyHhs);
	multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs, cplOnlyPrimaryFamilies, "Three family, Couple only households", i3fCplOnly);
	tempHhWith1Family.addAll(multiFamilyHhs);
	multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs, primaryOtherFamilies, "Two family, Other family households", i2fOthr);
	tempHhWith1Family.addAll(multiFamilyHhs);
	multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs, primaryOtherFamilies, "Three family, Other family households", i3fOthr);
	tempHhWith1Family.addAll(multiFamilyHhs);
	multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs, cplYsChldPrimaryFamilies, "Two family, Couple with children households",
		i2fCplChld);
	tempHhWith1Family.addAll(multiFamilyHhs);
	multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs, cplYsChldPrimaryFamilies, "Three family, Couple with children households",
		i3fCplChld);
	tempHhWith1Family.addAll(multiFamilyHhs);
	multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs, loneParentFamilies, "Two family, Lone parent households", i2f1P);
	tempHhWith1Family.addAll(multiFamilyHhs);
	multiFamilyHhs = addPrimaryFamilytoMultiFamily(hhrecs, loneParentFamilies, "Three family, Lone parent households", i3f1P);
	tempHhWith1Family.addAll(multiFamilyHhs);
	return tempHhWith1Family;

    }

    void addNonPrimaryFamiliesToMultiFamilyHousehold(List<Household> multiFamilyHouseholdWith1Family) {

	String logprefix = "Multi-family househods: ";
	List<FamilyType> threeOrMoreMember = new ArrayList<>(
		Arrays.asList(FamilyType.COUPLEFAMILYWITHCHILDREN, FamilyType.COUPLEONLY, FamilyType.OTHERFAMILY, FamilyType.LONEPARENT));
	List<FamilyType> twoMember = new ArrayList<>(Arrays.asList(FamilyType.COUPLEONLY, FamilyType.OTHERFAMILY, FamilyType.LONEPARENT));
	int twoFamilyHhs = 0, threeFamilyHhs = 0;
	// multiFamilyHouseholdWith1Family list has Other and CoupleOnly families at the top. So, we will be forming them first
	for (Household household : multiFamilyHouseholdWith1Family) {
	    Family firstFamily = household.getFamilies().get(0);
	    FamilyType firstFamilyType = firstFamily.getType();
	    List<Person> firstFamilyNewMembers = new ArrayList<>();
	    Family secondFamily = null;
	    FamilyType secondFamilyType = null;
	    List<Person> secondFamilyNewMembers = new ArrayList<>();
	    Family thirdFamily = null;
	    FamilyType thirdFamilyType = null;
	    List<Person> thirdFamilyNewMembers = new ArrayList<>();

	    if (threeOrMoreMember.isEmpty() & twoMember.isEmpty()) {
		break;
	    }

	    if (household.TARGETFAMLYCOUNT == 2) {
		// Select secondary family for 2 family household
		secondFamilyType = selectFamilyType(household, FamilyType.UNDEFINED);
		if (secondFamilyType == null) {
		    logger.log(Level.WARNING, logprefix + "Second family type selection failed: First family: " + firstFamilyType
			    + "Second family:" + secondFamilyType);
		    throw new Error("Second family type null");
		}
		secondFamily = getNewFamily(secondFamilyType);
		if (secondFamily.getMembers().isEmpty()) {
		    throw new Error("Second family is empty");
		}

	    } else if (household.TARGETFAMLYCOUNT == 3) {
		// Select secondary family for 3 family household
		secondFamilyType = selectFamilyType(household, FamilyType.UNDEFINED);
		if (secondFamilyType == null) {
		    logger.log(Level.WARNING, logprefix + "Second family type selection failed: First family: " + firstFamilyType
			    + "Second family:" + secondFamilyType);
		    throw new Error("Second family type null");
		}
		secondFamily = getNewFamily(secondFamilyType);
		if (secondFamily.getMembers().isEmpty()) {
		    throw new Error("Second family is empty");
		}

		// Select tertiary family for 3 family household
		thirdFamilyType = selectFamilyType(household, secondFamilyType);
		if (thirdFamilyType == null) {
		    logger.log(Level.WARNING, logprefix + "Third family type selection failed: First family: " + firstFamilyType
			    + "Second family: " + secondFamilyType + "Third family: " + thirdFamilyType);
		    throw new Error("Third family type null");
		}

		thirdFamily = getNewFamily(thirdFamilyType);
		if (thirdFamily.getMembers().isEmpty()) {
		    throw new Error("Third family is empty");
		}
	    } else {
		throw new Error(logprefix + "Unexpected number of families: " + household.TARGETFAMLYCOUNT);
	    }

	    int neededMembers = 0;
	    if (household.TARGETFAMLYCOUNT == 2) {
		neededMembers = household.TARGETSIZE - (household.currentSize() + secondFamily.size());
	    } else if (household.TARGETFAMLYCOUNT == 3) {
		neededMembers = household.TARGETSIZE - (household.currentSize() + secondFamily.size() + thirdFamily.size());
	    }
	    if (neededMembers != 0) {
		// All needed members goes into first family. Second family only has minimum required persons
		if (firstFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN | firstFamilyType == FamilyType.LONEPARENT) {
		    int relativeMembers = 0;
		    if (relatives.size() > 0 & neededMembers > 2) { // We don't want too many
														   // relatives from
			// Extra agents
			relativeMembers = 1;
		    }
		    int childMembers = neededMembers - relativeMembers;

		    if (children.size() + relatives.size() > neededMembers) {
			// IF children.size + relative.size > neededMembers AND childMembers + relativeMembers == neededMembers
			// THEN children.size >= childMembers OR relative.size >= relativeMembers
			//
			// Since we know sum of relatives and children is larger than neededMembers, only 1 of the below
			// 2 conditions is True at a time. i.e. if one array is smaller than our liking then the other
			// is sure to be large enough to get enough number of persons.

			if (children.size() < childMembers) {
			    childMembers = children.size();
			    relativeMembers = neededMembers - childMembers;
			}
			if (relatives.size() < relativeMembers) {
			    relativeMembers = relatives.size();
			    childMembers = neededMembers - relativeMembers;
			}
			firstFamilyNewMembers.addAll(children.subList(0, childMembers));
			children.subList(0, childMembers).clear();
			firstFamilyNewMembers.addAll(relatives.subList(0, relativeMembers));
			relatives.subList(0, relativeMembers).clear();
		    } else {
			fillChildrenAndRelativesUsingExtras(childMembers, relativeMembers, firstFamilyNewMembers, logprefix);
		    }
		} else { // First family is a couple with no children or other family. They only can have relatives
		    if (secondFamilyType == FamilyType.LONEPARENT) { // But if second family is a lone parent family they can have
								     // children

			int relativeMembers = (neededMembers > 3) ? neededMembers - 1 : neededMembers; // Don't want too many children in second
												       // family
			int childMembers = neededMembers - relativeMembers;

			if (children.size() + relatives.size() > neededMembers) {
			    if (children.size() < childMembers) {
				childMembers = children.size();
				relativeMembers = neededMembers - childMembers;
			    }
			    if (relatives.size() < relativeMembers) {
				relativeMembers = relatives.size();
				childMembers = neededMembers - relativeMembers;
			    }

			    secondFamilyNewMembers.addAll(children.subList(0, childMembers)); // Children in second family
			    children.subList(0, childMembers).clear();
			    firstFamilyNewMembers.addAll(relatives.subList(0, relativeMembers)); // Relatives to first family
			    relatives.subList(0, relativeMembers).clear();

			} else {
			    fillChildrenAndRelativesUsingExtras(childMembers, 0, secondFamilyNewMembers, logprefix);
			    fillChildrenAndRelativesUsingExtras(0, relativeMembers, firstFamilyNewMembers, logprefix);
			}
		    } else if (thirdFamilyType == FamilyType.LONEPARENT) {
			int relativeMembers = (neededMembers > 3) ? neededMembers - 1 : neededMembers; // Don't want too many
												       // children in third
			int childMembers = neededMembers - relativeMembers;

			if (children.size() + relatives.size() > neededMembers) {
			    if (children.size() < childMembers) {
				childMembers = children.size();
				relativeMembers = neededMembers - childMembers;
			    }
			    if (relatives.size() < relativeMembers) {
				relativeMembers = relatives.size();
				childMembers = neededMembers - relativeMembers;
			    }
			    thirdFamilyNewMembers.addAll(children.subList(0, childMembers)); // Children in third family
			    children.subList(0, childMembers).clear();
			    firstFamilyNewMembers.addAll(relatives.subList(0, relativeMembers)); // Relatives to first family
			    relatives.subList(0, relativeMembers).clear();

			} else {
			    fillChildrenAndRelativesUsingExtras(childMembers, 0, thirdFamilyNewMembers, logprefix);
			    fillChildrenAndRelativesUsingExtras(0, relativeMembers, firstFamilyNewMembers, logprefix);
			}
		    } else { // First, second and third family are either couple only or other family.
			if (relatives.size() >= neededMembers) {
			    firstFamilyNewMembers.addAll(relatives.subList(0, neededMembers));
			    relatives.subList(0, neededMembers).clear();
			} else {
			    fillChildrenAndRelativesUsingExtras(0, neededMembers, firstFamilyNewMembers, logprefix);
			}
		    }
		}
	    }

	    int currentSize = 0;
	    if (household.TARGETFAMLYCOUNT == 2) {
		twoFamilyHhs++;
		currentSize = firstFamily.size() + firstFamilyNewMembers.size() + secondFamily.size() + secondFamilyNewMembers.size();
	    } else if (household.TARGETFAMLYCOUNT == 3) {
		currentSize = firstFamily.size() + firstFamilyNewMembers.size() + secondFamily.size() + secondFamilyNewMembers.size()
			+ thirdFamily.size() + thirdFamilyNewMembers.size();
		threeFamilyHhs++;
	    }

	    if (household.TARGETSIZE == currentSize) {
		/* household is complete */
		firstFamily.addMembers(firstFamilyNewMembers);
		secondFamily.addMembers(secondFamilyNewMembers);
		household.addFamily(secondFamily);
		if (household.TARGETFAMLYCOUNT == 3) {
		    thirdFamily.addMembers(thirdFamilyNewMembers);
		    household.addFamily(thirdFamily);
		}

		if (household.TARGETFAMLYCOUNT == household.familyCount()) {
		    allHouseholds.add(household);
		}
	    }

	} // end of foreach household
	logger.log(Level.INFO, logprefix + "Two family households formed: " + twoFamilyHhs);
	logger.log(Level.INFO, logprefix + "Three family households formed: " + threeFamilyHhs);
	if (multiFamilyHouseholdWith1Family.size() == (twoFamilyHhs + threeFamilyHhs)) {
	    logger.log(Level.INFO, logprefix + "All households created");
	}

    }

    void fillChildrenAndRelativesUsingExtras(int childMembers, int relativeMembers, List<Person> familyMembers, String logprefix) {
	int missingChildren = 0, missingRelatives = 0;

	if (children.size() > childMembers) {
	    familyMembers.addAll(children.subList(0, childMembers));
	    children.subList(0, childMembers).clear();
	} else {
	    missingChildren = childMembers - children.size();
	    familyMembers.addAll(children);
	    children.clear();
	}
	if (relatives.size() > relativeMembers) {
	    familyMembers.addAll(relatives.subList(0, relativeMembers));
	    relatives.subList(0, relativeMembers).clear();
	} else {
	    missingRelatives = relativeMembers - relatives.size();
	    familyMembers.addAll(relatives);
	    relatives.clear();
	}

	if (missingChildren + missingRelatives > 0) {
	    if (extras.size() >= missingChildren + missingRelatives) {
		BNWLogger.logonce(Level.INFO, logprefix + "Not enough children and/or relatives in main lists. Drawing persons from extras");
		for (int i = 0; i < (missingChildren + missingRelatives); i++) {
		    if (i < missingChildren) {
			Person child = extras.remove(0);
			child.setSex(selectTrueOrFalseRandomlyWithBias(rand, sexRatio) ? Sex.Male : Sex.Female);
			child.setType(PersonType.Child);
			child.setAgeCat(AgeRange.A0_14);
			child.setChildType(ChildType.U15Child);
			familyMembers.add(child);
		    } else {
			List<AgeRange> ageslist = new ArrayList<>(Arrays.asList(AgeRange.values()));
			Person relative = extras.remove(0);
			relative.setSex(selectTrueOrFalseRandomlyWithBias(rand, sexRatio) ? Sex.Male : Sex.Female);
			relative.setType(PersonType.Relative);
			Collections.shuffle(ageslist);
			relative.setAgeCat(ageslist.get(0));
			familyMembers.add(relative);
		    }
		}
	    } else {
		logger.log(Level.WARNING, logprefix + "Not enough extras to complete the household with children");
		if(relatives.size() >= missingChildren){
		    logger.log(Level.INFO, logprefix + "Completing the household by replacing required children with available relatives");
		    familyMembers.addAll(relatives.subList(0, missingChildren));
	            relatives.subList(0, missingChildren).clear();
		}else{
		    logger.log(Level.WARNING,
	                        logprefix + "Children: " + children.size() + " Relatives: " + relatives.size() + " Extras: " + extras.size());
	                        throw new Error(logprefix + "Cannot form more households. All extra persons consumed");
		}
	    }
	}
    }

    Family getNewFamily(FamilyType newFamilyType) {
	Family newFamily = null;
	if (newFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN) {
	    newFamily = married.remove(0);
	    newFamily.addMember(children.remove(0));
	    newFamily.setType(newFamilyType);
	} else if (newFamilyType == FamilyType.COUPLEONLY) {
	    newFamily = married.remove(0);
	    newFamily.setType(newFamilyType);
	} else if (newFamilyType == FamilyType.LONEPARENT) {
	    newFamily = loneParentBasic.remove(0);
	    newFamily.setType(newFamilyType);
	} else if (newFamilyType == FamilyType.OTHERFAMILY) {
	    newFamily = nonPrimaryOtherFamilies.remove(0);
	    newFamily.setType(newFamilyType);
	} else if (newFamilyType == null) {
	    logger.log(Level.WARNING, "Multi-family households: Family Type is null");
	} else {
	    logger.log(Level.WARNING, "Multi-family households: Family Type selected for second family does not exist: " + newFamilyType);
	    throw new Error("Unrecognised family type: " + newFamilyType);
	}
	return newFamily;
    }

    List<Person> getNewFamilyRemainingMembers(FamilyType newFamilyType) {
	// we have already checked whether required agents exist, so can remove without issues
	List<Person> newFamilyNewMembers = new ArrayList<>();
	if (newFamilyType == FamilyType.COUPLEFAMILYWITHCHILDREN) {
	    newFamilyNewMembers.add(children.remove(0));
	} else if (newFamilyType == FamilyType.OTHERFAMILY) {
	    newFamilyNewMembers.addAll(relatives.subList(0, 2));
	    relatives.subList(0, 2).clear();
	} else if (newFamilyType == FamilyType.COUPLEONLY | newFamilyType == FamilyType.LONEPARENT) {
	} else if (newFamilyType == null) {
	    logger.log(Level.WARNING, "Multi-family households: Family Type is null");
	    throw new Error("Unrecognised family type: " + newFamilyType);
	} else {
	    logger.log(Level.WARNING, "Multi-family households: Family Type selected for second family does not exist: " + newFamilyType);
	    throw new Error("Unrecognised family type: " + newFamilyType);
	}
	return newFamilyNewMembers;
    }

    FamilyType selectFamilyType(Household household, FamilyType secondFamilyType) {
	List<FamilyType> threeOrMoreMember = new ArrayList<>(
		Arrays.asList(FamilyType.COUPLEFAMILYWITHCHILDREN, FamilyType.COUPLEONLY, FamilyType.OTHERFAMILY, FamilyType.LONEPARENT));
	List<FamilyType> twoMember = new ArrayList<>(Arrays.asList(FamilyType.COUPLEONLY, FamilyType.OTHERFAMILY, FamilyType.LONEPARENT));

	boolean canNextFamilyBeCplYsChld = (household.TARGETSIZE - household.currentSize() - secondFamilyType.basicSize()) >= 3
		&& household.getFamilies().get(0).getType() == FamilyType.COUPLEFAMILYWITHCHILDREN;

	FamilyType nextFamilyType = null;

	if (children.isEmpty() | married.isEmpty()) {
	    threeOrMoreMember.remove(FamilyType.COUPLEFAMILYWITHCHILDREN);
	    twoMember.remove(FamilyType.COUPLEFAMILYWITHCHILDREN);
	}
	if (nonPrimaryOtherFamilies.isEmpty()) {
	    threeOrMoreMember.remove(FamilyType.OTHERFAMILY);
	    twoMember.remove(FamilyType.OTHERFAMILY);
	}
	if (married.isEmpty()) {
	    threeOrMoreMember.remove(FamilyType.COUPLEONLY);
	    twoMember.remove(FamilyType.COUPLEONLY);
	}
	if (loneParentBasic.isEmpty()) {
	    threeOrMoreMember.remove(FamilyType.LONEPARENT);
	    twoMember.remove(FamilyType.LONEPARENT);
	}

	if (canNextFamilyBeCplYsChld) {
	    if (!threeOrMoreMember.isEmpty()) {
		nextFamilyType = threeOrMoreMember.get(rand.nextInt(threeOrMoreMember.size()));
	    } else {
		logger.log(Level.WARNING, "Multi-family households: Unable to form secondary or tertiary families. Aborting");
		return null;
	    }

	} else if (household.TARGETSIZE - household.currentSize() >= 2) {
	    if (!twoMember.isEmpty()) {
		nextFamilyType = twoMember.get(rand.nextInt(twoMember.size()));
	    } else {

		logger.log(Level.WARNING,
			"Multi-family households: All two member family structures consumed. Resorting to form only Couple with children secondary and tertiary families");
		return null;
	    }

	} else {
	    logger.log(Level.WARNING, "Multi-family households: Cannot form a secondary family with only 1 vacant slot for members: ABORTING! ");
	    throw new Error("Multi-family household that has less than 2 persons in secondary family");
	}
	return nextFamilyType;
    }

    void formOtherFamily1FamilyHouseholds(List<HhRecord> hhrecs, List<Family> othrFamilyBasic) {
	String logprefix = "One family, Other Family: ";
	List<HhRecord> otherFmlyrec = getHhsByFamlyType(hhrecs, i1fOthr);
	List<Household> hhs = new ArrayList<>();
	int takenFromExtras = 0;
	int total1FOtherFamily = 0;
	for (HhRecord householdRecord : otherFmlyrec) {
	    if (othrFamilyBasic.isEmpty()) {
		continue;
	    }

	    total1FOtherFamily += householdRecord.hhCount;

	    for (int i = 0; i < householdRecord.hhCount; i++) {
		if (othrFamilyBasic.isEmpty()) {
		    logger.log(Level.WARNING, logprefix + "Not enough other family basic structures");
		    break;
		}

		Family family = othrFamilyBasic.get(0);
		if (householdRecord.numOfPersonsPerHh > 2) {
		    int remMems = householdRecord.numOfPersonsPerHh - family.size();
		    if (relatives.size() < remMems) {
			List<Person> newMembers = new ArrayList<>();
			int needed = remMems - relatives.size();
			family.addMembers(relatives.subList(0, relatives.size()));
			fillChildrenAndRelativesUsingExtras(0, needed, newMembers, logprefix);
			takenFromExtras += needed;
			family.addMembers(newMembers);
			print("comes here");

		    } else {
			family.addMembers(relatives.subList(0, remMems));
			relatives.subList(0, remMems).clear();
		    }
		}
		othrFamilyBasic.remove(0);
		family.setType(householdRecord.primaryFamilyType);
		Household h = new Household(householdRecord.numOfPersonsPerHh, householdRecord.familyCountPerHousehold, sa2name);
		h.addFamily(family);
		hhs.add(h);
	    }
	}
	logger.log(Level.INFO, logprefix + "formed households: " + hhs.size());
	if (takenFromExtras > 0) {
	    logger.log(Level.INFO, logprefix + "Taken from extras as Relatives: " + takenFromExtras);
	}
	if (hhs.size() == total1FOtherFamily) {
	    logger.log(Level.INFO, logprefix + "All households created");
	}
	allHouseholds.addAll(hhs);
    }

    void formLoneParent1FamilyHouseholds(List<HhRecord> hhrecs) {
	String logprefix = "One family, Lone parent: ";
	List<HhRecord> lnparentrec = getHhsByFamlyType(hhrecs, i1f1P);
	// int neededMembers = 0;
	List<Household> loneParentHouseholds = new ArrayList<>();
	int totalLoneParenHhs = 0;
	for (HhRecord hhrec : lnparentrec) {
	    if (loneParentBasic.isEmpty()) {
		continue;
	    }
	    totalLoneParenHhs += hhrec.hhCount;
	    for (int i = 0; i < hhrec.hhCount; i++) {
		if (loneParentBasic.isEmpty()) {
		    logger.log(Level.WARNING, logprefix + "Not enough lone parent basic structures");
		    break;
		}
		Family family = loneParentBasic.remove(0);
		family.setType(FamilyType.LONEPARENT);
		int neededMembers = hhrec.numOfPersonsPerHh - family.size();
		if (neededMembers > 0) {
		    int relativeMembers = 0;
		    boolean hasRelative = selectTrueOrFalseRandomlyWithBias(rand, relativeProbability);
		    if (relatives.size() > 0 && hasRelative && neededMembers > 2) {
			relativeMembers = 1;
		    }
		    int childMembers = neededMembers - relativeMembers;
		    if (children.size() + relatives.size() > neededMembers) {
			if (children.size() < childMembers) {
			    childMembers = children.size();
			    relativeMembers = neededMembers - childMembers;
			}

			if (relatives.size() < relativeMembers) {
			    relativeMembers = relatives.size();
			    childMembers = neededMembers - relativeMembers;
			}
			family.addMembers(children.subList(0, childMembers));
			children.subList(0, childMembers).clear();
			family.addMembers(relatives.subList(0, relativeMembers));
			relatives.subList(0, relativeMembers).clear();
		    } else {
			List<Person> newPersons = new ArrayList<>();
			fillChildrenAndRelativesUsingExtras(childMembers, relativeMembers, newPersons, logprefix);
			family.addMembers(newPersons);
		    }
		}
		Household household = new Household(hhrec.numOfPersonsPerHh, hhrec.familyCountPerHousehold, sa2name);
		household.addFamily(family);
		loneParentHouseholds.add(household);
	    }

	}
	logger.log(Level.INFO, "One family, Lone parent: formed households: " + loneParentHouseholds.size());
	if (loneParentHouseholds.size() == totalLoneParenHhs) {
	    logger.log(Level.INFO, "One family, Lone parent: All households created");
	}
	allHouseholds.addAll(loneParentHouseholds);
    }

    void formCoupleWithChild1FamilyHouseholds(List<HhRecord> hhrecs, List<Family> cplYsChldPrimaryFamilies) {
	String logprefix = "One Family, Couple with children: ";
	List<HhRecord> cplYsChldrec = getHhsByFamlyType(hhrecs, i1fCplChld);
	List<Household> hhs = new ArrayList<>();

	int unformed = 0;
	for (HhRecord householdRecord : cplYsChldrec) {
	    if (cplYsChldPrimaryFamilies.isEmpty()) {
		unformed += householdRecord.hhCount;
		continue;
	    }
	    for (int i = 0; i < householdRecord.hhCount; i++) {
		if (cplYsChldPrimaryFamilies.isEmpty()) {
		    unformed += (householdRecord.hhCount - i);
		    logger.log(Level.WARNING, logprefix + "Not enough Couple with children primary families");
		    break;
		}

		Family family = cplYsChldPrimaryFamilies.get(0);

		int remMems = householdRecord.numOfPersonsPerHh - family.size();
		boolean hasRelative = selectTrueOrFalseRandomlyWithBias(rand, 0.19);
		if (remMems > 0) {
		    if (hasRelative & !relatives.isEmpty()) {
			--remMems; // We delay adding relative to family until we are sure this family can be
				   // formed successfully
		    }
		    if (children.size() < remMems) {
			List<Person> extraChildren = new ArrayList<>();
			fillChildrenAndRelativesUsingExtras(remMems, 0, extraChildren, logprefix);
			family.addMembers(extraChildren);
			BNWLogger.logonce(Level.INFO, logprefix + "Not enough children in main list. Drawing persons from extras");
		    } else {
			family.addMembers(children.subList(0, remMems));
			children.subList(0, remMems).clear();
		    }
		    if (hasRelative && !relatives.isEmpty()) {
			family.addMember(relatives.remove(0));
		    }
		}

		cplYsChldPrimaryFamilies.remove(0);
		family.setType(householdRecord.primaryFamilyType);
		Household h = new Household(householdRecord.numOfPersonsPerHh, householdRecord.familyCountPerHousehold, sa2name);
		h.addFamily(family);
		hhs.add(h);
	    }
	}
	logger.log(Level.INFO, logprefix + "formed households: " + hhs.size());
	if (unformed > 0) {
	    logger.log(Level.WARNING, logprefix + "unformed households: " + unformed);
	} else {
	    logger.log(Level.INFO, logprefix + "All households created");
	}
	allHouseholds.addAll(hhs);
    }

    void formCoupleOnly1FamilyHouseholds(List<HhRecord> hhrecs, List<Family> cplOnlyPrimaryFamilies) {
	String logprefix = "One Family, Couple only: ";
	List<HhRecord> cplOnlyrec = getHhsByFamlyType(hhrecs, i1fCplOnly);
	List<Household> hhs = new ArrayList<>();
	int unformed = 0;
	for (HhRecord householdRecord : cplOnlyrec) {
	    if (cplOnlyPrimaryFamilies.isEmpty()) {
		unformed += householdRecord.hhCount;
		continue;
	    }
	    for (int i = 0; i < householdRecord.hhCount; i++) {
		if (cplOnlyPrimaryFamilies.isEmpty()) {
		    unformed += (householdRecord.hhCount - i);
		    logger.log(Level.WARNING, "One Family, Couple only: Not enough Couple Only Primary Families");
		    break;
		}

		Family family = cplOnlyPrimaryFamilies.get(0);
		if (householdRecord.numOfPersonsPerHh > 2) {
		    int remMems = householdRecord.numOfPersonsPerHh - family.size();
		    if (relatives.size() < remMems) {
			List<Person> extraRelatives = new ArrayList<>();
			fillChildrenAndRelativesUsingExtras(0, remMems, extraRelatives, logprefix);
			family.addMembers(extraRelatives);
		    } else {
			family.addMembers(relatives.subList(0, remMems));
			relatives.subList(0, remMems).clear();
		    }
		}
		cplOnlyPrimaryFamilies.remove(0);
		family.setType(householdRecord.primaryFamilyType);
		Household h = new Household(householdRecord.numOfPersonsPerHh, householdRecord.familyCountPerHousehold, sa2name);
		h.addFamily(family);
		hhs.add(h);
	    }
	}
	logger.log(Level.INFO, "One Family, Couple only: formed households: " + hhs.size());
	if (unformed > 0) {
	    logger.log(Level.WARNING, "One Family, Couple only: unformed households: " + unformed);
	} else {
	    logger.log(Level.INFO, "One Family, Couple only: All households created");
	}
	allHouseholds.addAll(hhs);
    }

    List<Person> makeAllPersonsByRelationshipType(List<IndRecord> indrec, int... relType) {
	List<IndRecord> indRecs = getAgentsByRelType(indrec, relType);
	List<Person> persons = new ArrayList<>();
	for (IndRecord rec : indRecs) {
	    for (int i = 0; i < rec.indCount; i++) {
		Person p = new Person();
		p.setAgeCat(rec.ageRange);
		p.setSex(rec.sex);
		p.setType(rec.personType);
		p.setChildType(rec.childType);
		persons.add(p);
	    }
	}

	return persons;
    }

    List<Family> makePrimaryCoupleOnlyFamilyBasicStructs(List<HhRecord> hhrecs) {
	List<HhRecord> cplOnlyHhrecs = getHhsByFamlyType(hhrecs, i1fCplOnly, i2fCplOnly, i3fCplOnly);
	List<Family> cplOnly = new ArrayList<>();
	int unformed = 0;
	for (HhRecord hhrec : cplOnlyHhrecs) {
	    if (married.isEmpty()) {
		unformed += hhrec.hhCount;
		continue;
	    }
	    for (int i = 0; i < hhrec.hhCount; i++) {
		if (married.isEmpty()) {
		    logger.log(Level.WARNING, "Couple Only Primary Families: Not enough married couples");
		    unformed += (hhrec.hhCount - i);
		    break;
		}
		Family f = married.remove(0);
		f.setType(hhrec.primaryFamilyType);
		cplOnly.add(f);
	    }
	}
	logger.log(Level.INFO, "Couple Only Primary Families: formed structures: " + cplOnly.size());
	if (unformed > 0) {
	    logger.log(Level.WARNING, "Couple Only Primary Families: Unformed structers: " + unformed);
	} else {
	    logger.log(Level.INFO, "Couple Only Primary Families: All strucures created");
	}
	return cplOnly;
    }

    List<Family> makePrimaryCoupleWithChildFamilyBasicStructs(List<HhRecord> householdRecords) {
	List<HhRecord> cplYesChldFamilies = getHhsByFamlyType(householdRecords, i1fCplChld, i2fCplChld, i3fCplChld);
	List<Family> cplwChld = new ArrayList<>();
	int unformed = 0;
	for (HhRecord hhrec : cplYesChldFamilies) {
	    if (married.isEmpty() || children.isEmpty()) {
		unformed += hhrec.hhCount;
	    }
	    for (int i = 0; i < hhrec.hhCount; i++) {
		if (married.isEmpty()) {
		    logger.log(Level.WARNING, "Couple With Children Basic Primary Families: Not enough married couples");
		    unformed += (hhrec.hhCount - 1);
		    break;
		}
		if (children.isEmpty()) {
		    logger.log(Level.WARNING, "Couple With Children Basic Primary Families: Not enough children");
		    unformed += (hhrec.hhCount - 1);
		    break;
		}
		Family f = married.remove(0);
		f.setType(hhrec.primaryFamilyType);
		f.addMember(children.remove(0));
		cplwChld.add(f);
	    }
	}
	logger.log(Level.INFO, "Couple With Children Basic Primary Families: formed structures: " + cplwChld.size());
	if (unformed > 0) {
	    logger.log(Level.WARNING, "Couple With Children Basic Primary Families: Unformed structers: " + unformed);
	} else {
	    logger.log(Level.INFO, "Couple With Children Basic Primary Families: All strucures created");
	}
	return cplwChld;
    }

    List<Family> makeAllBasicLoneParentStructs(List<Person> lnParents) {
	List<Family> lnParentBasic = new ArrayList<>();
	int lnpcnt = lnParents.size();
	for (int i = 0; i < lnpcnt; i++) {
	    if (children.isEmpty()) {
		logger.log(Level.WARNING, "Lone Parent Basic: Not enough children");
		logger.log(Level.WARNING, "Lone Parent Basic: Discarded Lone Parents: " + lnParents.size());
	    }
	    Family f = new Family(FamilyType.BASIC);
	    f.addMember(lnParents.remove(0));
	    f.addMember(children.remove(0));
	    lnParentBasic.add(f);
	}

	logger.log(Level.INFO, "Lone Parent Basic: formed strucures: " + lnParentBasic.size());
	if (lnParents.isEmpty()) {
	    logger.log(Level.INFO, "Lone Parent Basic: All structures created");
	}
	return lnParentBasic;
    }

    List<Family> makeAllPrimaryLoneParentFamilies(List<HhRecord> hhrecs, List<Family> basicLoneParentFamilies) {
	List<HhRecord> lnParentRecs = getHhsByFamlyType(hhrecs, i1f1P, i2f1P, i3f1P);
	List<Family> lnParentFamilies = new ArrayList<>();
	int unformed = 0;
	for (HhRecord lnprec : lnParentRecs) {
	    if (basicLoneParentFamilies.isEmpty()) {
		unformed += lnprec.hhCount;
		continue;
	    }
	    for (int i = 0; i < lnprec.hhCount; i++) {
		if (basicLoneParentFamilies.isEmpty()) {
		    logger.log(Level.WARNING, "Primary Lone Parent Families: Not engough Basic Lone Parent Family structures");
		    unformed += (lnprec.hhCount - i);
		    break;
		}

		Family f = basicLoneParentFamilies.remove(0);
		f.setType(lnprec.primaryFamilyType);
		lnParentFamilies.add(f);

	    }
	}
	logger.log(Level.INFO, "Primary Lone Parent Families: Structures created: " + lnParentFamilies.size());
	if (unformed != 0) {
	    logger.log(Level.WARNING, "Primary Lone Parent Families: Discarded structures: " + unformed);
	} else {
	    logger.log(Level.INFO, "Primary Lone Parent Families: All structures created");
	}
	return lnParentFamilies;
    }

    List<Family> makeAllPrimaryOtherFamilyBasicStructs(List<HhRecord> hhrecs) {
	List<HhRecord> otherFamily = getHhsByFamlyType(hhrecs, i1fOthr);
	otherFamily.addAll(getHhsByFamlyType(hhrecs, i2fOthr));
	otherFamily.addAll(getHhsByFamlyType(hhrecs, i3fOthr));

	List<Family> otherFamilyBasic = new ArrayList<>();
	Collections.shuffle(relatives, rand);
	int unfomredFamilycount = 0;
	for (HhRecord hhrec : otherFamily) {
	    if (relatives.size() < 2) {
		unfomredFamilycount += (hhrec.hhCount);
		continue;
	    }
	    for (int i = 0; i < hhrec.hhCount; i++) {
		if (relatives.size() < 2) {
		    unfomredFamilycount += (hhrec.hhCount - i);
		    logger.log(Level.WARNING,
			    "Other Family Basic Primary Families: Not engough Relatives to form more Basic Other Family structures");
		    break;
		}
		Family fm = new Family(hhrec.primaryFamilyType);
		fm.addMember(relatives.remove(0));
		fm.addMember(relatives.remove(0));
		otherFamilyBasic.add(fm);
	    }
	}

	logger.log(Level.INFO, "Other Family Basic Primary Families: Structures formed: " + otherFamilyBasic.size());
	if (unfomredFamilycount > 0) {
	    logger.log(Level.WARNING, "Other Family Basic Primary Families: Unformed strcutres: " + unfomredFamilycount);
	} else {
	    logger.log(Level.INFO, "Other Family Basic Primary Families: All structres created");
	}
	return otherFamilyBasic;
    }

    List<Family> makeAllMarriedCouples(List<HhRecord> hhrecs, List<IndRecord> indrec) {
	List<IndRecord> married = getAgentsByRelType(indrec, imarried);
	List<Person> maleMarried = new ArrayList<>();
	List<Person> femaleMarried = new ArrayList<>();

	for (IndRecord ind : married) {
	    for (int i = 0; i < ind.indCount; i++) {
		Person p = new Person();
		p.setSex(ind.sex);
		p.setAgeCat(ind.ageRange);
		p.setType(ind.personType);
		if (p.getSex() == Sex.Male) {
		    maleMarried.add(p);
		} else {
		    femaleMarried.add(p);
		}
	    }
	}

	int cpls = Math.min(maleMarried.size(), femaleMarried.size());
	int diff = maleMarried.size() - femaleMarried.size();

	List<Family> fl = new ArrayList<>();
	for (int i = 0; i < cpls; i++) {
	    Family f = new Family(FamilyType.BASIC);
	    f.addMember(maleMarried.remove(0));
	    f.addMember(femaleMarried.remove(0));

	    fl.add(f);
	}

	logger.log(Level.INFO, "Married couples: Couples formed: " + cpls);
	if (diff > 0) {
	    logger.log(Level.WARNING, "Married couples: Discared married males: " + diff);
	} else if (diff < 0) {
	    logger.log(Level.WARNING, "Married couples: Discarded married females: " + ((-1) * diff));
	} else {
	    logger.log(Level.INFO, "Married couples: All couples created");
	}
	return fl;
    }

    void makeLonePersonsHhs(List<HhRecord> hhrecs, List<IndRecord> indrec) {
	List<HhRecord> lnPersonHhs = getHhsByFamlyType(hhrecs, ilnPerhh);
	List<IndRecord> lnPersonInds = getAgentsByRelType(indrec, ilnperson);

	List<Person> allpersons = new ArrayList<>();
	for (IndRecord lnp : lnPersonInds) {
	    for (int j = 0; j < lnp.indCount; j++) {
		Person p = new Person();
		p.setSex(lnp.sex);
		p.setType(lnp.personType);
		p.setAgeCat(lnp.ageRange);
		allpersons.add(p);
	    }
	}
	HhRecord hhrec = lnPersonHhs.get(0); // Only 1 member households have lone persons

	int hhcnt = hhrec.hhCount;
	int diff = hhcnt - allpersons.size();
	int familyCount = Math.min(hhcnt, allpersons.size());
	List<Household> hhlist = new ArrayList<>();

	for (int i = 0; i < familyCount; i++) {
	    Family f = new Family(hhrec.primaryFamilyType);
	    f.addMember(allpersons.remove(0));
	    Household h = new Household(hhrec.numOfPersonsPerHh, hhrec.familyCountPerHousehold, sa2name);
	    h.addFamily(f);
	    hhlist.add(h);
	}
	allHouseholds.addAll(hhlist);

	logger.log(Level.INFO, "Lone person households: Households formed: " + hhlist.size());
	if (diff > 0) {
	    logger.log(Level.WARNING, "Lone person households: Persons discarded: " + 0);
	    logger.log(Level.WARNING, "Lone person households: Unformed 1 member households: " + diff);
	} else if (diff < 0) {
	    logger.log(Level.WARNING, "Lone person households: Persons discarded: " + ((-1) * diff));
	    logger.log(Level.WARNING, "Lone person households: Unformed 1 member households: " + 0);
	} else {
	    logger.log(Level.INFO, "Lone person households: All required households created");
	}

    }

    void makeGroupHhs(List<HhRecord> hhrecs, List<IndRecord> indrec) {
	List<HhRecord> grpHhrecs = getHhsByFamlyType(hhrecs, igrpHh);
	List<IndRecord> grpIndrecs = getAgentsByRelType(indrec, igrpInd);

	List<Person> grpmems = new ArrayList<>();
	for (IndRecord grprec : grpIndrecs) {
	    for (int i = 0; i < grprec.indCount; i++) {
		Person gm = new Person();
		gm.setAgeCat(grprec.ageRange);
		gm.setSex(grprec.sex);
		gm.setType(grprec.personType);
		grpmems.add(gm);
	    }
	}
	List<Household> grpHhs = new ArrayList<>();
	int totalGroupHhs = 0;
	for (HhRecord hhrec : grpHhrecs) {

	    int hhcnt = hhrec.hhCount;
	    totalGroupHhs += hhrec.hhCount;
	    int hhsize = hhrec.numOfPersonsPerHh;
	    if (hhcnt > 0 & hhsize > grpmems.size()) {
		logger.log(Level.WARNING, "Group households: Unformed " + hhsize + " member households: " + hhcnt);
		continue;
	    }
	    for (int i = 0; i < hhcnt; i++) {
		Family f = new Family(hhrec.primaryFamilyType);
		if (hhsize > grpmems.size()) {
		    logger.log(Level.WARNING, "Group households: Persons discarded: " + grpmems.size());
		    logger.log(Level.WARNING, "Group households: Unformed " + hhsize + " member households: " + (hhcnt - i));
		    break;
		}
		f.addMembers(grpmems.subList(0, hhsize));
		grpmems.subList(0, hhsize).clear();

		Household h = new Household(hhrec.numOfPersonsPerHh, hhrec.familyCountPerHousehold, sa2name);
		h.addFamily(f);

		grpHhs.add(h);
	    }

	}
	logger.log(Level.INFO, "Group households: Households formed: " + grpHhs.size());
	allHouseholds.addAll(grpHhs);
	if (totalGroupHhs == grpHhs.size()) {
	    logger.log(Level.INFO, "Group households: All Households created");
	} else {
	    logger.log(Level.WARNING, "Group households: Households created: " + grpHhs.size() + " Required: " + totalGroupHhs);
	}
    }

    List<HhRecord> getHhsByFamlyType(List<HhRecord> hhrecs, int... familytype) {
	List<HhRecord> shortlist = new ArrayList<>();
	for (int j = 0; j < familytype.length; j++) {
	    for (int i = 0; i < hhrecs.size(); i++) {
		if ((i % fmTypes) == familytype[j]) {
		    shortlist.add(hhrecs.get(i));
		}
	    }
	}
	return shortlist;
    }

    List<IndRecord> getAgentsByRelType(List<IndRecord> indrecs, int... relState) {
	List<IndRecord> shortlist = new ArrayList<>();
	for (int j = 0; j < relState.length; j++) {
	    for (int i = 0; i < indrecs.size(); i++) {
		if ((i / (ageCats * sexCats)) == relState[j]) {
		    shortlist.add(indrecs.get(i));
		}
	    }
	}
	return shortlist;

    }
}
