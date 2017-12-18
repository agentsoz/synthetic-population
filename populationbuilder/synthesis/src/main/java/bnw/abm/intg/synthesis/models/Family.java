/**
 *
 */
package bnw.abm.intg.synthesis.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wniroshan 19 May 2016
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
    public List<Person> getMembers() {
        return members;
    }

    /**
     * Adds a member to this family
     *
     * @param member the member to add
     */
    public  void addMember(Person member) {
        if (this.members.contains(member)) {
            throw new Error("This person already a member of this family");
        } else {
            this.members.add(member);
            Family.allMembersAlreadyInFamilies.put(member.getID(), member);
        }
    }

    /**
     * Adds multiple members to this family
     *
     * @param members list of members
     */
    public  void addMembers(List<Person> members) {
        if (this.members.stream().anyMatch(e -> members.contains(e))) {
            throw new Error("At least one of the new members already exists in this family");
        } else {
            this.members.addAll(members);
        }
    }

    /**
     * Gets the FamilyType of this family
     *
     * @return the type
     */
    public FamilyType getType() {
        return type;
    }

    /**
     * Sets the FamilType of this family
     *
     * @param type the type to set
     */
    public void setType(FamilyType type) {
        if (this.type == null || this.type == FamilyType.BASIC || this.type == FamilyType.UNDEFINED | this.type == type) {
            this.type = type;
        } else {
            throw new Error("Trying to overwrite " + this.type + " with " + type);
        }
    }

    /**
     * Gets number of children in this family
     *
     * @return the number of children
     */
    public int numberOfChildren() {
        int nofChildren = 0;
        for (Person member : this.members) {
            if (member.getType() == RelationshipStatus.Child) {
                nofChildren++;
            }
        }
        return nofChildren;
    }

    /**
     * Validates this family to check if this has the correct structure according to FamilyType
     *
     * @return True if structure of this family is correct according to its FamilyType
     */
    public boolean validate() {
        switch (this.type) {
            case COUPLEFAMILYWITHCHILDREN:
                return hasMarriedCouple() & hasChildren() & noLoneParents() & noGroupOrLonePersons();
            case COUPLEONLY:
                return (hasMarriedCouple() & !hasChildren() & noLoneParents() & noGroupOrLonePersons());
            case ONEPARENT:
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
        return members.stream().filter(member -> member.getType() == RelationshipStatus.LoneParent).count() == 1;
    }

    private boolean noLoneParents() {
        return members.stream().noneMatch(member -> member.getType() == RelationshipStatus.LoneParent);
    }

    private boolean hasChildren() {
        return members.stream().anyMatch(member -> member.getType() == RelationshipStatus.Child);
    }

    private boolean noneMarried() {
        return members.stream().noneMatch(member -> member.getType() == RelationshipStatus.Married);
    }

    private boolean hasMarriedCouple() {
        return members.stream().filter(person -> person.getType() == RelationshipStatus.Married).count() == 2;
    }

    private boolean onlyRelatives() {
        return members.stream().allMatch(person -> person.getType() == RelationshipStatus.Relative);
    }

    private boolean onlyGroupHouseholds() {
        return members.stream().allMatch(person -> person.getType() == RelationshipStatus.GroupHousehold);
    }

    private boolean noGroupOrLonePersons() {
        return members.stream().noneMatch(person -> person.getType() == RelationshipStatus.GroupHousehold | person.getType() == RelationshipStatus.LonePerson);
    }

    private boolean onlyALonePerson() {
        return members.size() == 1 && members.stream().filter(person -> person.getType() == RelationshipStatus.LonePerson).count() == 1;
    }
}
