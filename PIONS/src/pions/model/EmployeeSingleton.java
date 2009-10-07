/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pions.model;

import java.io.Serializable;
import pions.model.EmployeeFactory.Employee;

/**
 *
 * @author George
 */
public class EmployeeSingleton extends Employee implements Serializable {
    private static EmployeeSingleton employee_singleton;
    
    private EmployeeSingleton(){}

    public static EmployeeSingleton getInstance(){
        if(employee_singleton == null){
            return new EmployeeSingleton();
        }

        return employee_singleton;
    }
}
