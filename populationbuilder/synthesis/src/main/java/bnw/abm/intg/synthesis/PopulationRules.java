package bnw.abm.intg.synthesis;

import bnw.abm.intg.synthesis.models.Person;

import java.util.List;

/**
 * @author wniroshan
 * @date 11 Jan 2018
 */
public class PopulationRules {

    /**
     * Selects a suitable child for the given parent.
     * Rule applied here is: a child must come from at least one age category younger than the parent.
     *
     * @param parent   The parent
     * @param children The list of children to select the child
     * @return Index of the child instance in the children list. Returns -1 if no suitable child is found.
     */
    static int selectChild(Person parent, List<Person> children) {
        for (int i = 0; i < children.size(); i++) { //This is should be O(1) if parent is selected from an age descending sorted list
            if (parent.getAgeRange().compareTo(children.get(i).getAgeRange()) > 0) {
                return i;
            }
        }
        return -1; //If no suitable child

    }
}
