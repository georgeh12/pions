
package pions.model;

import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class EmployeeSingleton extends Employee implements Serializable {
    //TODO testing purposes only
    public static void main(String args[]){
        try{
            EmployeeSingleton.getInstance().saveFile();
            EmployeeSingleton.getInstance().authenticate("george", "password");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static EmployeeSingleton employee_singleton;
    
    private EmployeeSingleton(String username, String password){
        super(username, password);
    }

    public static void init(String username, String password){
        employee_singleton = new EmployeeSingleton(username, password);
    }

    public static void load(String username, String password)
            throws NotLoggedInException, StreamCorruptedException,
            ClassNotFoundException, IOException{
        employee_singleton = Login.loadFile(username, password);
    }

    public static EmployeeSingleton getInstance() throws NotLoggedInException{
        if(employee_singleton == null){
            throw new NotLoggedInException();
        }

        return employee_singleton;
    }
}
