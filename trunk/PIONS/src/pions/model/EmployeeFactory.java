/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pions.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author George
 */
public class EmployeeFactory {
    public static class Employee extends Login implements Serializable {
        private ContactInfo contact_info;
        private ArrayList<Position> position;
        private ArrayList<Employee> superiors = new ArrayList<Employee>();
        private ArrayList<Employee> subordinates = new ArrayList<Employee>();

        /**
         * Adds a new superior for the current EmployeeFactory.
         * @param new_superior
         */
        private void addAbove(Employee new_superior) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Adds a new subordinate for the current EmployeeFactory.
         * @param new_subordinate
         */
        private void addBelow(Employee new_subordinate) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
