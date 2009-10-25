
package pions.model;

/**
 *
 * @author George
 */
public class EmployeeAlert extends Alert {
    private Employee employee = null;

    public EmployeeAlert(Employee employee){
        set(employee);
    }

    public void set(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Object get() {
        return employee;
    }
}
