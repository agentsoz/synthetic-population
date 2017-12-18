/**
 *
 */
package bnw.abm.intg.synthesis.models;

import bnw.abm.intg.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wniroshan
 * @date 19 May 2016
 */
public class Person {

    private static long IDCounter = 0;
    private String personID;
    private RelationshipStatus type;
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

    public RelationshipStatus getType() {
        return type;
    }

    public void setType(RelationshipStatus type) {
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
        if (parent.type == RelationshipStatus.Married | parent.type == RelationshipStatus.LoneParent) {
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
                    Log.error(logprefix + " Lone person");
                }
                break;
            case Relative:
                if (father == null & mother == null & partner == null & children == null & relatives != null
                        & siblings == null & groupHouseholdMembers == null) {

                    return true;
                } else {
                    Log.error(logprefix + " Relative");
                }
                break;
            case Married:
                if (father == null & mother == null & partner != null & siblings == null & groupHouseholdMembers == null) {
                    return true;
                } else {
                    Log.error(logprefix + " Married");
                }
                break;
                //Logically equivalent to case (U15Child | Student | O15Child)
            case U15Child:
            case Student:
            case O15Child:
                if ((father != null | mother != null) & partner == null & children == null & groupHouseholdMembers == null) {
                    return true;
                } else {
                    Log.error(logprefix + " U15Child | Student | O15Child");
                }
                break;
            case LoneParent:
                if (father == null & mother == null & partner == null & children != null & siblings == null
                        & groupHouseholdMembers == null) {
                    return true;
                } else {
                    Log.error(logprefix + " Lone parent");
                }
                break;
            case GroupHousehold:
                if (father == null & mother == null & partner == null & children == null & relatives == null
                        & siblings == null & groupHouseholdMembers != null) {
                    return true;
                } else {
                    Log.error(logprefix + " Group household");
                }
                break;
            default:
                throw new Error("An alian impersonating a person: " + this.type);
        }
        return false;
    }

}
