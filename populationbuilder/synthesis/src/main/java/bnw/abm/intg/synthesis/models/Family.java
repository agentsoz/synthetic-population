/**
 *
 */
package bnw.abm.intg.synthesis.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wniroshan 19 May 2016
 */
public class Family {
    private static long IDCounter = 0;
    private static Map<String, Person> allMembersAlreadyInFamilies = new HashMap<>();
    private List<Person> members;
    private FamilyType type;
    private String familyID;

    public Family() {
        this.members = new ArrayList<>();
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
    public void addMember(Person member) {
        if (this.members.contains(member)) {
            throw new Error("This person already a member of this family");
        } else {
            member.setFamilyID(this.getID());
            this.members.add(member);
        }

        if(this.getType() != null && !this.validate()){
            throw new Error("Malformed family: " + this);
        }
    }

    /**
     * Adds multiple members to this family
     *
     * @param members list of members
     */
    public void addMembers(List<Person> members) {
        if (this.members.stream().anyMatch(e -> members.contains(e))) {
            throw new Error("At least one of the new members already exists in this family");
        } else {
            members.forEach(m -> m.setFamilyID(this.getID()));
            this.members.addAll(members);
        }
        if(this.getType() != null && !this.validate()){
            throw new Error("Malformed family: " + this);
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
     * Sets the FamilyType of this family
     *
     * @param type the type to set
     */
    public void setType(FamilyType type) {
        if (type == FamilyType.COUPLE_WITH_CHILDREN && this.getType() == FamilyType.COUPLE_ONLY) {
            this.type = type;
        } else if (this.type == null | this.type == type) {
            this.type = type;
            if (!this.validate()) {
                throw new Error("Malformed family: " + this);
            }
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
            if (member.isChild()) {
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
            case COUPLE_WITH_CHILDREN:
                return hasMarriedCouple() & hasChildren() & noLoneParents() & noGroupOrLonePersons() & childrenYoungerThanParents();
            case COUPLE_ONLY:
                return (hasMarriedCouple() & !hasChildren() & noLoneParents() & noGroupOrLonePersons());
            case ONE_PARENT:
                return hasALoneParent() & hasChildren() & noneMarried() & noGroupOrLonePersons() & childrenYoungerThanParents();
            case OTHER_FAMILY:
                return onlyRelatives() & noGroupOrLonePersons();
            case LONE_PERSON:
                return onlyALonePerson();
            case GROUP_HOUSEHOLD:
                return onlyGroupHouseholds();
            default:
                throw new Error("An alien family: " + this.type);
        }
    }

    private boolean childrenYoungerThanParents() {
        return members.stream()
                      .filter(m -> m.getRelationshipStatus() == RelationshipStatus.U15_CHILD || m.getRelationshipStatus() == RelationshipStatus.STUDENT || m
                              .getRelationshipStatus() == RelationshipStatus.O15_CHILD)
                      .max(new AgeRange.AgeComparator().reversed())
                      .orElseThrow(AssertionError::new)
                      .getAgeRange().max() < this.getYoungestParent().getAgeRange().min();
    }

    private boolean hasALoneParent() {
        return members.stream()
                      .filter(member -> member.getRelationshipStatus() == RelationshipStatus.LONE_PARENT)
                      .count() == 1;
    }

    private boolean noLoneParents() {
        return members.stream().noneMatch(member -> member.getRelationshipStatus() == RelationshipStatus.LONE_PARENT);
    }

    private boolean hasChildren() {
        return members.stream().anyMatch(member -> member.isChild());
    }

    private boolean noneMarried() {
        return members.stream().noneMatch(member -> member.getRelationshipStatus() == RelationshipStatus.MARRIED);
    }

    private boolean hasMarriedCouple() {
        return members.stream()
                      .filter(person -> person.getRelationshipStatus() == RelationshipStatus.MARRIED)
                      .count() == 2;
    }

    private boolean onlyRelatives() {
        return members.stream().allMatch(person -> person.getRelationshipStatus() == RelationshipStatus.RELATIVE);
    }

    private boolean onlyGroupHouseholds() {
        return members.stream()
                      .allMatch(person -> person.getRelationshipStatus() == RelationshipStatus.GROUP_HOUSEHOLD);
    }

    private boolean noGroupOrLonePersons() {
        return members.stream()
                      .noneMatch(person -> person.getRelationshipStatus() == RelationshipStatus.GROUP_HOUSEHOLD | person
                              .getRelationshipStatus() == RelationshipStatus.LONE_PERSON);
    }

    private boolean onlyALonePerson() {
        return members.size() == 1 && members.stream()
                                             .filter(person -> person.getRelationshipStatus() == RelationshipStatus
                                                     .LONE_PERSON)
                                             .count() == 1;
    }

    /**
     * Returns the youngest parent in the family if there is one, otherwise return null
     *
     * @return The youngest Person instance
     */
    public Person getYoungestParent() {
        return this.getMembers()
                   .stream()
                   .filter(m -> m.getRelationshipStatus() == RelationshipStatus.MARRIED ||
                           m.getRelationshipStatus() == RelationshipStatus.LONE_PARENT)
                   .min(new AgeRange.AgeComparator()).orElse(null);

    }

    @Override
    public String toString() {
        return "Family id:" + this.getID() + " type:" + getType()
                + " | Current: size:" + size() + " members:" + getMembers().stream().map(m -> "(" + m.getSex() + "," + m
                .getRelationshipStatus() + "," + m.getAgeRange() + ")").collect(
                Collectors.toList());
    }
}
