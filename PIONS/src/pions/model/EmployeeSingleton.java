
package pions.model;

import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public final class EmployeeSingleton extends Employee implements Serializable {
    public static void main(String args[]){
        try{
            EmployeeSingleton.getInstance().saveFile();
            EmployeeSingleton.getInstance().authenticate("george", "password");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static EmployeeSingleton singleton = null;
    
    private EmployeeSingleton(String name, String username, String password){
        super(name, username, password);
    }

    @Override
    public void logout(){
        super.logout();
        singleton = null;
    }

    public static void init(String name, String username, String password){
        singleton = new EmployeeSingleton(name, username, password);
    }

    public static void load(String username, String password)
            throws NotLoggedInException, StreamCorruptedException,
            ClassNotFoundException, IOException{
        singleton = Login.loadFile(username, password);
    }

    public static EmployeeSingleton getInstance() throws NotLoggedInException{
        if(singleton == null){
            throw new NotLoggedInException();
        }

        return singleton;
    }
}
