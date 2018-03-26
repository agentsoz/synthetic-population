/**
 *
 */
package bnw.abm.intg.synthesis.models;

import bnw.abm.intg.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author wniroshan 19 May 2016
 */
public class Person {

    private static long IDCounter = 0;
    private final String personID;
    private String familyID;
    private RelationshipStatus type;
    private int age;
    private Sex sex;
    private AgeRange ageRange;

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

    public RelationshipStatus getRelationshipStatus() {
        return type;
    }

    public void setRelationshipStatus(RelationshipStatus type) {
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

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }

    public String getID() {
        return this.personID;
    }

    public void clearFamilyID() {
        this.familyID = null;
    }

    public String getFamilyID() {
        return this.familyID;
    }

    public void setFamilyID(String familyID) {
        if (this.familyID != null) {
            throw new Error("A person can only belong to one family");
        } else {
            this.familyID = familyID;
        }
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
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

    /**
     * Checks whether this person is a child
     *
     * @return True if this person is a child, else false
     */
    public boolean isChild() {
        if (this.getRelationshipStatus() == RelationshipStatus.U15_CHILD || this.getRelationshipStatus() == RelationshipStatus.STUDENT ||
                this
                        .getRelationshipStatus() == RelationshipStatus.O15_CHILD) {
            return true;
        }
        return false;
    }

    public void setChild(Person child) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        if (this.mother == null) {
            this.mother = mother;
        } else {
            throw new Error("Child already has a mother");
        }

    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        if (this.father == null) {
            this.father = father;
        } else {
            throw new Error("Child already has a father");
        }

    }

    public List<Person> getSiblings() {
        return this.siblings;
    }

    public void setSibling(Person sibling) {
        if (this.siblings == null) {
            this.siblings = new ArrayList<>();
        }
        this.siblings.add(sibling);
    }

    public void setParent(Person parent) {
        if (parent.type == RelationshipStatus.MARRIED | parent.type == RelationshipStatus.LONE_PARENT) {
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

    public void setParents(List<Person> parents) {
        for (Person parent : parents) {
            setParent(parent);
        }
    }

    public List<Person> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Person> relatives) {
        if (this.relatives == null) {
            this.relatives = new ArrayList<>();
        }
        this.relatives.addAll(relatives);
    }

    public void setRelative(Person relative) {
        if (this.relatives == null) {
            this.relatives = new ArrayList<>();
        }
        this.relatives.add(relative);
    }

    public List<Person> getGroupHouseholdMembers() {
        return groupHouseholdMembers;
    }

    public void setGroupHouseholdMember(Person groupHouseholdMember) {
        if (this.groupHouseholdMembers == null) {
            this.groupHouseholdMembers = new ArrayList<>();
        }
        this.groupHouseholdMembers.add(groupHouseholdMember);
    }

    public boolean validate() {
        String logPrefix = "Person validation failed: ";
        switch (this.type) {
            case LONE_PERSON:
                if (father == null & mother == null & partner == null & children == null & relatives == null &
                        siblings == null & groupHouseholdMembers == null) {

                    return true;
                } else {
                    Log.error(logPrefix + " Lone person");
                }
                break;
            case RELATIVE:
                if (father == null & mother == null & partner == null & children == null & relatives != null &
                        siblings == null & groupHouseholdMembers == null) {

                    return true;
                } else {
                    Log.error(logPrefix + " RELATIVE");
                }
                break;
            case MARRIED:
                if (father == null & mother == null & partner != null & siblings == null & groupHouseholdMembers ==
                        null) {
                    return true;
                } else {
                    Log.error(logPrefix + " MARRIED");
                }
                break;
            //Logically equivalent to case (U15_CHILD | STUDENT | O15_CHILD)
            case U15_CHILD:
            case STUDENT:
            case O15_CHILD:
                if ((father != null | mother != null) & partner == null & children == null & groupHouseholdMembers ==
                        null) {
                    return true;
                } else {
                    Log.error(logPrefix + " U15_CHILD | STUDENT | O15_CHILD");
                }
                break;
            case LONE_PARENT:
                if (father == null & mother == null & partner == null & children != null & siblings == null &
                        groupHouseholdMembers == null) {
                    return true;
                } else {
                    Log.error(logPrefix + " Lone parent");
                }
                break;
            case GROUP_HOUSEHOLD:
                if (father == null & mother == null & partner == null & children == null & relatives == null &
                        siblings == null & groupHouseholdMembers != null) {
                    return true;
                } else {
                    Log.error(logPrefix + " Group household");
                }
                break;
            default:
                throw new Error("An alian impersonating a person: " + this.type);
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + this.getSex() + "," + this.getRelationshipStatus() + "," + this.getAgeRange() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(personID, person.personID) &&
                Objects.equals(familyID, person.familyID) &&
                type == person.type &&
                sex == person.sex &&
                ageRange == person.ageRange &&
                Objects.equals(partner, person.partner) &&
                Objects.equals(children, person.children) &&
                Objects.equals(mother, person.mother) &&
                Objects.equals(father, person.father) &&
                Objects.equals(relatives, person.relatives) &&
                Objects.equals(siblings, person.siblings) &&
                Objects.equals(groupHouseholdMembers, person.groupHouseholdMembers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(personID,
                            familyID,
                            type,
                            age,
                            sex,
                            ageRange,
                            partner,
                            children,
                            mother,
                            father,
                            relatives,
                            siblings,
                            groupHouseholdMembers);
    }
}
