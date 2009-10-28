
package pions.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Observable;
import pions.model.ModelException.NotLoggedInException;

/**
 * This class is not Serializable so the username or passwords, besides
 * the PublicKey, will not be saved.
 * @author George
 */
public class Login extends Observable {
    protected PublicKey public_key = null;
    private String username = null;
    private String password = null;    //for AES
    private String private_key = null; //for RSA
    private String display_name = null;
    private boolean validated = false;

    //TODO fix once public key is done
    public String getPublicKey(){
        return public_key.get();
    }

    //TODO generate RSA keys
    public void generateRSAKeys() throws NotLoggedInException {
        validate();

        this.private_key = "";
        public_key = new PublicKey("");
    }

    /**
     * Get display name
     * @return
     */
    public String getDisplayName() throws NotLoggedInException {
        validate();

        if(display_name != null){
            return display_name;
        }
        else{
            return username;
        }
    }

    protected void setLogin(String username, String password){

    }
    
    /**
     * Set display name
     * @param display_name
     * @throws pions.model.ModelException.NotLoggedInException
     */
    public void setDisplayName(String display_name) throws NotLoggedInException {
        validate();
        
        this.display_name = display_name;
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
        return getFile(username);
    }

    private static File getFile(String username){
        return new File(username + ".pions");
    }

    /**
     * Checks that the file storing the Employee's information can be decrypted.
     * @param username
     * @param password
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws StreamCorruptedException
     * @throws ClassNotFoundException
     * @throws pions.model.ModelException.NotLoggedInException
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
        //TODO
    }

    //TODO add file header and encryption
    public void saveFile() throws IOException, NotLoggedInException {
        FileOutputStream fos = new FileOutputStream(getFile());

        fos.write(encryptAES(EmployeeSingleton.getInstance()));
    }

    //TODO add file header and encryption
    public static EmployeeSingleton loadFile(String username, String password) throws IOException,
            NotLoggedInException, StreamCorruptedException, ClassNotFoundException {
        return (EmployeeSingleton)decryptAES(new FileInputStream(getFile(username)));
    }
    
    /**
     * Encrypt object using login information with AES encryption.
     * @param object
     * @return
     * @throws IOException
     * @throws pions.model.ModelException.NotLoggedInException
     */
    //TODO use AES encryption to encryptAES/serialize objects
    public static byte[] encryptAES(Object object)
            throws IOException, NotLoggedInException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(baos);

        // write object to bytes
        oos.writeObject(object);
        oos.close();

        return baos.toByteArray();
    }

    //TODO use RSA encryption to encryptAES/serialize objects
    public ByteArrayOutputStream encryptRSA(Object object)
            throws IOException, NotLoggedInException{
        validate();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(baos);

        // write object to bytes
        oos.writeObject(object);
        oos.close();

        return baos;
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
    //TODO use AES decryption to decryptAES/deserialize objects
    public static Object decryptAES(InputStream is)
            throws StreamCorruptedException, IOException,
            ClassNotFoundException, NotLoggedInException {
        ObjectInputStream ois = new ObjectInputStream(is);

        return ois.readObject();
    }

    //TODO use RSA decryption to decryptAES/deserialize objects
    public Object decryptRSA(InputStream is)
            throws StreamCorruptedException, IOException,
            ClassNotFoundException, NotLoggedInException {
        validate();

        ObjectInputStream ois = new ObjectInputStream(is);

        return ois.readObject();
    }
    
    private class PublicKey implements Serializable {
        private String public_key = null;  //for RSA

        public PublicKey(String public_key) {
            this.public_key = public_key;
        }

        public String get(){
            return this.public_key;
        }
    }
}
