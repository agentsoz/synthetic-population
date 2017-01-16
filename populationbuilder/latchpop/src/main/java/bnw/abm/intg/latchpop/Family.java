/**
 * 
 */
package bnw.abm.intg.latchpop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bhagya N. Wickramasinghe 19 May 2016
 */
public class Family {
    private static long IDCounter = 0;
    private static Map<String, Person> allMembersAlreadyInFamilies = new HashMap<>();
    private List<Person> members;
    private FamilyType type;
    private String familyID;

    public Family(FamilyType type) {
	this.members = new ArrayList<Person>();
	this.setType(type);
	this.familyID = String.valueOf(IDCounter++);
    }

    public String getID() {
	return this.familyID;
    }

    /**
     * Number of members in the family
     * 
     * @return the number of members
     */
    public int size() {
	return getMembers().size();
    }

    /**
     * @return the members
     */
    List<Person> getMembers() {
	return members;
    }

    /**
     * @param member
     *            the member to add
     */
    void addMember(Person member) {
	if (this.members.contains(member)) {
	    throw new Error("This person already a member of this family");
	} else {
	    this.members.add(member);
	    Family.allMembersAlreadyInFamilies.put(member.getID(), member);
	}
    }

    void addMembers(List<Person> members) {
	if (this.members.stream().anyMatch(e -> members.contains(e))) {
	    throw new Error("At least one of the new members already exists in this family");
	} else {
	    this.members.addAll(members);
	}
    }

    /**
     * @return the type
     */
    FamilyType getType() {
	return type;
    }

    /**
     * @param type
     *            the type to set
     */
    void setType(FamilyType type) {
	if (this.type == null || this.type == FamilyType.BASIC || this.type == FamilyType.UNDEFINED | this.type == type) {
	    this.type = type;
	} else {
	    throw new Error("Trying to overwrite " + this.type + " with " + type);
	}
    }

    int numberOfChildren() {
	int nofChildren = 0;
	for (Person member : this.members) {
	    if (member.getType() == PersonType.Child) {
		nofChildren++;
	    }
	}
	return nofChildren;
    }

    boolean validate() {
	switch (this.type) {
	case COUPLEFAMILYWITHCHILDREN:
	    return hasMarriedCouple() & hasChildren() & noLoneParents() & noGroupOrLonePersons();
	case COUPLEONLY:
	    return (hasMarriedCouple() & !hasChildren() & noLoneParents() & noGroupOrLonePersons());
	case LONEPARENT:
	    return (hasALoneParent() & hasChildren() & noneMarried() & noGroupOrLonePersons());
	case OTHERFAMILY:
	    return onlyRelatives() & noGroupOrLonePersons();
	case LONEPERSON:
	    return onlyALonePerson();
	case GROUPHOUSEHOLD:
	    return onlyGroupHouseholds();
	default:
	    throw new Error("An alien family: " + this.type);
	}
    }

    private boolean hasALoneParent() {
	return members.stream().filter(member -> member.getType() == PersonType.LoneParent).count() == 1;
    }

    private boolean noLoneParents() {
	return members.stream().noneMatch(member -> member.getType() == PersonType.LoneParent);
    }

    private boolean hasChildren() {
	return members.stream().anyMatch(member -> member.getType() == PersonType.Child);
    }

    private boolean noneMarried() {
	return members.stream().noneMatch(member -> member.getType() == PersonType.Married);
    }

    private boolean hasMarriedCouple() {
	return members.stream().filter(person -> person.getType() == PersonType.Married).count() == 2;
    }

    private boolean onlyRelatives() {
	return members.stream().allMatch(person -> person.getType() == PersonType.Relative);
    }

    private boolean onlyGroupHouseholds() {
	return members.stream().allMatch(person -> person.getType() == PersonType.GroupHousehold);
    }

    private boolean noGroupOrLonePersons() {
	return members.stream().noneMatch(person -> person.getType() == PersonType.GroupHousehold | person.getType() == PersonType.LonePerson);
    }

    private boolean onlyALonePerson() {
	return members.size() == 1 && members.stream().filter(person -> person.getType() == PersonType.LonePerson).count() == 1;
    }
}

enum FamilyType {
    COUPLEFAMILYWITHCHILDREN("Couple family with children", 2, 0, 1, 0, 0, 0),
    COUPLEONLY("Couple family with no children", 2, 0, 0, 0, 0, 0),
    LONEPERSON("Lone person", 0, 0, 0, 1, 0, 0),
    LONEPARENT("One parent family", 0, 1, 1, 0, 0, 0),
    OTHERFAMILY("Other family", 0, 0, 0, 0, 2, 0),
    GROUPHOUSEHOLD("Group household", 0, 0, 0, 0, 0, 2),
    BASIC("Married basic or Lone parent basic ", 0, 0, 0, 0, 0, 0),
    UNDEFINED("Undefined", 0, 0, 0, 0, 0, 0);

    private String type;
    private int basicChildren, basicMarried, basicLonePersons, basicLoneParents, basicRelatives, basicGroupHouseholdPersons;

    FamilyType(String type, int basicMarried, int basicLoneParents, int basicChildren, int basicLonePersons, int basicRelatives,
	    int basicGroupHouseholdPersons) {
	this.type = type;
	this.basicMarried = basicMarried;
	this.basicLoneParents = basicLoneParents;
	this.basicChildren = basicChildren;
	this.basicLonePersons = basicLonePersons;
	this.basicRelatives = basicRelatives;
	this.basicGroupHouseholdPersons = basicGroupHouseholdPersons;

    }

    String description() {
	return type;
    }

    int basicSize() {
	return basicChildren + basicMarried + basicLonePersons + basicLoneParents + basicRelatives + basicGroupHouseholdPersons;
    }

    int basicChildren() {
	return basicChildren;
    }

    int basicGroupHouseholdPersons() {
	return basicGroupHouseholdPersons;
    }

    int basicMarriedPersons() {
	return basicMarried;
    }

    int basicLonePersons() {
	return basicLonePersons;
    }

    int basicLoneParents() {
	return basicLoneParents;
    }

    int basicRelatives() {
	return basicRelatives;
    }
}
