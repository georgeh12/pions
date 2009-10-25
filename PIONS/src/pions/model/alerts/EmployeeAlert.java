
package pions.model.alerts;

import java.io.Serializable;
import pions.model.Employee;

/**
 *
 * @author George
 */
public class EmployeeAlert extends Alert implements Serializable {
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
