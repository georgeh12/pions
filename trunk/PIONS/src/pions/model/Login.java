
package pions.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Observable;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class Login extends Observable {
    private String username = null;
    private String password = null;    //for AES
    private String public_key = null;  //for RSA
    private String private_key = null; //for RSA
    private String display_name = null;
    private boolean validated = false;

    //TODO generate private key
    public void generatePrivateKey() throws NotLoggedInException {
        if(validated){
            this.public_key = "";
        }
        else{
            throw new NotLoggedInException();
        }
    }

    //TODO generate public key
    public void generatePublicKey() throws NotLoggedInException {
        if(validated){
            this.public_key = "";
        }
        else{
            throw new NotLoggedInException();
        }
    }

    /**
     * Get display name
     * @return
     */
    public String getDisplayName() throws NotLoggedInException {
        if(validated){
            if(display_name != null){
                return display_name;
            }
            else{
                return username;
            }
        }
        else{
            throw new NotLoggedInException();
        }
    }
    
    /**
     * Set display name
     * @param display_name
     * @throws pions.model.ModelException.NotLoggedInException
     */
    public void setDisplayName(String display_name) throws NotLoggedInException {
        if(validated){
            this.display_name = display_name;
        }
        else{
            throw new NotLoggedInException();
        }
    }

    /**
     * Throw exception if user is not logged in.
     * @throws pions.model.ModelException.NotLoggedInException
     */
    private void validate() throws NotLoggedInException {
        if(validated == false){
            throw new NotLoggedInException();
        }
    }

    private File getFile(){
        return new File(username + ".pions");
    }

    /**
     * Only function that can manipulate username and password
     * @param username
     * @param password
     * @return
     */
    //TODO add valid exceptions
    public boolean authenticate(String username, String password)
            throws IOException, ClassNotFoundException, FileNotFoundException,
            StreamCorruptedException, ClassNotFoundException, NotLoggedInException {
        if(decryptAES(new FileInputStream(getFile())).getClass() == EmployeeSingleton.class){
            this.username = username;
            this.password = password;
            return validated = true;
        }
        else{
            return false;
        }
    }

    /**
     * Logout the user.
     */
    public void logout(){
        username = null;
        password = null;
        public_key = null;
        private_key = null;
        display_name = null;
        validated = false;
    }

    //TODO add file header and encryption
    public void saveFile() throws IOException, NotLoggedInException {
        FileOutputStream fos = new FileOutputStream(getFile());

        fos.write(encryptAES(EmployeeSingleton.getInstance()));
    }

    /**
     * Serialize object to a byte array.
     * @param object
     * @return Encrypted object
     * @throws IOException
     * @throws pions.model.ModelException.NotLoggedInException
     */
    private static byte[] serialize(Object object) throws IOException
    {
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
     * Serialize byte array to an object.
     * @param object
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws pions.model.ModelException.NotLoggedInException
     */
    private static Object deserialize(byte[] object)
            throws IOException, ClassNotFoundException
    {
        ObjectInputStream ois;

        // write object to bytes
        ois = new ObjectInputStream(new ByteArrayInputStream(object));

        return ois.readObject();
    }

    /**
     * Encrypt object using login information with AES encryption.
     * @param object
     * @return
     * @throws IOException
     * @throws pions.model.ModelException.NotLoggedInException
     */
    //TODO use AES/RSA encryption to encryptAES/serialize objects
    public byte[] encryptAES(Object object)
            throws IOException, NotLoggedInException{
        validate();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(baos);

        // write object to bytes
        oos.writeObject(object);
        oos.close();

        return baos.toByteArray();
    }

    /**
     * Decrypt object using login information with AES encryption.
     * StreamCorruptedException indicates an incorrect password.
     * @return
     * @throws StreamCorruptedException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws pions.model.ModelException.NotLoggedInException
     */
    //TODO use AES/RSA decryption to decryptAES/deserialize objects
    public Object decryptAES(InputStream is)
            throws StreamCorruptedException, IOException,
            ClassNotFoundException, NotLoggedInException {
        validate();

        ObjectInputStream ois = new ObjectInputStream(is);

        return ois.readObject();
    }
}
