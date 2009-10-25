
package pions.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author George
 */
public class Employee extends Login implements Serializable {
    public Gmail gmail;
    public ContactInfo contact_info;
    private ArrayList<Position> positions = new ArrayList<Position>();
    private ArrayList<Employee> managers = new ArrayList<Employee>();
    private ArrayList<Employee> subordinates = new ArrayList<Employee>();

    public void addPosition(String title, boolean hourly, double rate){
        positions.add(new Position(title, hourly, rate));
    }

    public void setPosition(int index, String title, boolean hourly, double rate){
        positions.set(index, new Position(title, hourly, rate));
    }

    /**
     * Returns a clone of positions.
     * @return
     */
    public ArrayList<Position> getPositions(){
        return (ArrayList<Position>) positions.clone();
    }

    /**
     * Adds a new manager for the current Employee.
     * @param new_manager
     */
    public void addManager(Employee new_manager) {
        managers.add(new_manager);
    }

    /**
     * Adds a new subordinate for the current Employee.
     * @param new_subordinate
     */
    public void addSubordinate(Employee new_subordinate) {
        subordinates.add(new_subordinate);
    }

    /**
     * Inserts the manager between this employee and their managers.
     * Clears the current managers.
     * @param manager
     */
    public void addAbove(Employee manager) {
        managers.clear();
        managers.add(manager);
    }

    /**
     * Inserts the subordinate between this employee and their subordinates.
     * Clears the current subordinates.
     * @param subordinate
     */
    public void addBelow(Employee subordinate) {
        subordinates.clear();
        subordinates.add(subordinate);
    }
}
