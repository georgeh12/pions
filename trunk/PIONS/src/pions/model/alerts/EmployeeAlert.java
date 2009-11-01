
package pions.model.alerts;

import java.io.Serializable;
import pions.model.Employee;

/**
 *
 * @author George
 */
public class EmployeeAlert extends Alert implements Serializable {
    public EmployeeAlert(Employee employee, AlertType type) {
        super(type);
        set(employee);
    }
}
