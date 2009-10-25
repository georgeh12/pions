
package pions.model;

import java.io.Serializable;

/**
 *
 * @author George
 */
public class EmployeeSingleton extends Employee implements Serializable {
    //TODO testing purposes only
    public static void main(String args[]){
        try{
            EmployeeSingleton.getInstance().saveFile();
            EmployeeSingleton.getInstance().authenticate("george", "gay slippers");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static EmployeeSingleton employee_singleton;
    
    private EmployeeSingleton(){}

    public static EmployeeSingleton getInstance(){
        if(employee_singleton == null){
            return new EmployeeSingleton();
        }

        return employee_singleton;
    }
}
