
package pions.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * 
 */
public final class EmployeeSingleton extends Employee implements Serializable {
    /*public static void main(String args[]){
        try{
            EmployeeSingleton.getInstance().saveFile();
            EmployeeSingleton.getInstance().authenticate("george", "password");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }*/

    private static EmployeeSingleton singleton = null;
    
    private EmployeeSingleton(String name, String username, String password,
            String gmail_address, String gmail_password)
            throws FileNotFoundException, StreamCorruptedException,
            ClassNotFoundException, IOException{
        super(name, username, password, gmail_address, gmail_password);
    }

    /**
     * Removes the EmployeeSingleton.
     */
    public void logout(){
        singleton = null;
    }

    public static void init(String name, String username, String password,
            String gmail_address, String gmail_password)
            throws FileNotFoundException, StreamCorruptedException,
            ClassNotFoundException, IOException{
        singleton = new EmployeeSingleton(name, username, password,
                gmail_address, gmail_password);
    }

    public static void login(String username, String password)
            throws StreamCorruptedException,
            ClassNotFoundException, IOException{
        singleton = Login.loadFile(username, password);
    }

    public static boolean isLoggedIn(){
        return singleton != null;
    }

    public static EmployeeSingleton getInstance() throws NotLoggedInException{
        if(singleton == null){
            throw new NotLoggedInException();
        }

        return singleton;
    }
}
