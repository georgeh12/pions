/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pions.model;

import pions.model.PIONSModelException.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author George
 */
public class Login {
    private String username;
    private String password;
    private boolean validated = false;
    private String display_name;

    /**
     * Throw exception if user is not logged in.
     * @throws pions.model.PIONSModelException.NotLoggedInException
     */
    private void validate() throws NotLoggedInException {
        if(validated == false){
            throw new NotLoggedInException();
        }
    }

    /**
     * Only function that can manipulate username and password
     * @param username
     * @param password
     * @return
     */
    public boolean authenticate(String username, String password) {
        validated = true;
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Logout the user
     */
    public void logout(){
        validated = false;
    }

    /**
     * Get display name
     * @return
     */
    public String getDisplayName(){
        return display_name;
    }

    /**
     * Set display name
     * @param display_name
     */
    public void setDisplayName(String display_name){
        this.display_name = display_name;
    }

    /**
     * Encrypt object using login information with AES encryption.
     * @param object
     * @return Encrypted object
     * @throws IOException
     * @throws pions.model.PIONSModelException.NotLoggedInException
     */
    public static byte[] serialize(Object object) throws IOException, NotLoggedInException
    {
        //TODO use AES encryption to serialize objects
        //validate();

        ObjectOutputStream oos;
        ByteArrayOutputStream baos;

        // write object to bytes
        baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        oos.close();

        return baos.toByteArray();
    }

    /**
     * Decrypt object using login information with AES encryption.
     * InvalidHeaderException indicates a failed username/password combination.
     * @param object
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws pions.model.PIONSModelException.NotLoggedInException
     */
    public static Object deserialize(byte[] object) throws IOException, ClassNotFoundException, NotLoggedInException
    {
        //TODO use AES decryption to deserialize objects
        //validate();

        ObjectInputStream ois;
        ByteArrayInputStream bais;

        // write object to bytes
        bais = new ByteArrayInputStream(object);
        ois = new ObjectInputStream(bais);

        return ois.readObject();
    }
}
