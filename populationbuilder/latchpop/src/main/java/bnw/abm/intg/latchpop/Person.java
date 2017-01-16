/**
 * 
 */
package bnw.abm.intg.latchpop;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bnw.abm.intg.util.BNWLogger;

/**
 * @author Bhagya N. Wickramasinghe 19 May 2016
 */
public class Person {
	Logger logger = BNWLogger.getLogger();
	private static long IDCounter = 0;
	private String personID;
	private PersonType type;
	private ChildType childType;
	private int age;
	private Sex sex;
	private AgeRange ageCat;

	private Person partner;
	private List<Person> children;
	private Person mother;
	private Person father;
	private List<Person> relatives;
	private List<Person> siblings;
	private List<Person> groupHouseholdMembers;

	public Person() {
		this.personID = String.valueOf(IDCounter++);
	}

	public ChildType getChildType() {
		if (type == PersonType.Child && childType == null) {
			throw new Error("ChilType is not set");
		}
		return childType;
	}

	public void setChildType(ChildType childType) {
		this.childType = childType;
	}

	public PersonType getType() {
		return type;
	}

	public void setType(PersonType type) {
		this.type = type;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public AgeRange getAgeCat() {
		return ageCat;
	}

	public void setAgeCat(AgeRange ageCat) {
		this.ageCat = ageCat;
	}

	public String getID() {
		return this.personID;
	}

	public Person getPartner() {
		return partner;
	}

	void setPartner(Person partner) {
		if (this.partner == null) {
			this.partner = partner;
		} else {
			throw new Error("Already has a partner");
		}
	}

	public List<Person> getChildren() {
		return children;
	}

	void setChildren(List<Person> children) {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.addAll(children);
	}

	void setChild(Person child) {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.add(child);
	}

	public Person getMother() {
		return mother;
	}

	void setMother(Person mother) {
		if (this.mother == null) {
			this.mother = mother;
		} else {
			throw new Error("Child already has a mother");
		}

	}

	public Person getFather() {
		return father;
	}

	void setFather(Person father) {
		if (this.father == null) {
			this.father = father;
		} else {
			throw new Error("Child already has a father");
		}

	}

	List<Person> getSiblings() {
		return this.siblings;
	}

	void setSibling(Person sibling) {
		if (this.siblings == null) {
			this.siblings = new ArrayList<>();
		}
		this.siblings.add(sibling);
	}

	void setParent(Person parent) {
		if (parent.type == PersonType.Married | parent.type == PersonType.LoneParent) {
			if (parent.sex == Sex.Male) {
				setFather(parent);
			} else if (parent.sex == Sex.Female) {
				setMother(parent);
			} else {
				throw new Error("Parent's Sex is not Male or Female: " + parent.sex);
			}
		} else {
			throw new Error("This person cannot be a parent: " + parent.type);
		}
	}

	void setParents(List<Person> parents) {
		for (Person parent : parents) {
			setParent(parent);
		}
	}

	public List<Person> getRelatives() {
		return relatives;
	}

	void setRelatives(List<Person> relatives) {
		if (this.relatives == null) {
			this.relatives = new ArrayList<>();
		}
		this.relatives.addAll(relatives);
	}

	void setRelative(Person relative) {
		if (this.relatives == null) {
			this.relatives = new ArrayList<>();
		}
		this.relatives.add(relative);
	}

	public List<Person> getGroupHouseholdMembers() {
		return groupHouseholdMembers;
	}

	void setGroupHouseholdMember(Person groupHouseholdMember) {
		if (this.groupHouseholdMembers == null) {
			this.groupHouseholdMembers = new ArrayList<>();
		}
		this.groupHouseholdMembers.add(groupHouseholdMember);
	}

	boolean validate() {
		String logprefix = "Person validation failed: ";
		switch (this.type) {
		case LonePerson:
			if (father == null & mother == null & partner == null & children == null & relatives == null
					& siblings == null & groupHouseholdMembers == null) {

				return true;
			} else {
				logger.log(Level.SEVERE, logprefix + " Lone person");
			}
			break;
		case Relative:
			if (father == null & mother == null & partner == null & children == null & relatives != null
					& siblings == null & groupHouseholdMembers == null) {

				return true;
			} else {
				logger.log(Level.SEVERE, logprefix + " Relative");
			}
			break;
		case Married:
			if (father == null & mother == null & partner != null & siblings == null & groupHouseholdMembers == null) {
				return true;
			} else {
				logger.log(Level.SEVERE, logprefix + " Married");
			}
			break;
		case Child:
			if ((father != null | mother != null) & partner == null & children == null & groupHouseholdMembers == null
					& childType != null) {
				return true;
			} else {
				logger.log(Level.SEVERE, logprefix + " Child");
			}
			break;
		case LoneParent:
			if (father == null & mother == null & partner == null & children != null & siblings == null
					& groupHouseholdMembers == null) {
				return true;
			} else {
				logger.log(Level.SEVERE, logprefix + " Lone parent");
			}
			break;
		case GroupHousehold:
			if (father == null & mother == null & partner == null & children == null & relatives == null
					& siblings == null & groupHouseholdMembers != null) {
				return true;
			} else {
				logger.log(Level.SEVERE, logprefix + " Group household");
			}
			break;
		default:
			throw new Error("An alian impersonating a person: " + this.type);
		}
		return false;
	}

}

enum Sex {
	Male, Female;
}

enum PersonType {
	Relative, LonePerson, Married, Child, LoneParent, GroupHousehold;
}

enum ChildType {
	U15Child, Student, O15Child;
}

enum AgeRange {
	A0_14(0, 14), A15_24(15, 24), A25_39(25, 39), A40_54(40, 54), A55_69(55, 69), A70_84(70, 84), A85_99(85,
			99), A100_110(100, 110);
	private int min, max;
	private boolean isEmpty = true;

	AgeRange(int min, int max) {
		this.min = min;
		this.max = max;
	}

	boolean isEmpty() {
		return isEmpty;
	}

	void markNotEmpty(boolean status) {
		this.isEmpty = !status;
	}

	int min() {
		return this.min;
	}

	int max() {
		return this.max;
	}
}