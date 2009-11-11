
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
import java.io.StreamCorruptedException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import pions.model.ModelException.NotLoggedInException;

/**
 * This class is not Serializable so the username or passwords, besides
 * the PublicKey, will not be saved.
 * @author George
 */
public abstract class Login {
    private String username = null; //for AES
    private String password = null; //for AES
    private KeyPair RSA_keys = null; //for RSA
    private boolean validated = false;

    protected Login(String username, String password) {
        setCredentials(username, password);
    }

    protected void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected String getUsername(){
        return username;
    }

    /**
     * This generates a new key pair for encrypting messages.
     * Never throws IOException.
     * @throws pions.model.ModelException.NotLoggedInException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    protected KeyPair generateRSAKeys() throws NotLoggedInException,
            NoSuchAlgorithmException, IOException {
        validate();
        
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048); //11 bits

        return kpg.genKeyPair();
    }

    /**
     * Throw exception if user is not logged in.
     * @throws pions.model.ModelException.NotLoggedInException
     */
    protected void validate() throws NotLoggedInException {
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
    protected boolean authenticate(String username, String password)
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

    public void deleteFile() {
        getFile().delete();
    }

    //TODO add file header and encryption
    public void saveFile() throws IOException, NotLoggedInException {
        FileOutputStream fos = new FileOutputStream(getFile());

        fos.write(encryptAES(EmployeeSingleton.getInstance()));
    }

    //TODO add file header and encryption
    public static EmployeeSingleton loadFile(String username, String password) throws IOException,
            StreamCorruptedException, ClassNotFoundException {
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
    public final static byte[] encryptAES(Object object)
            throws IOException, NotLoggedInException{
        return getBytes(object);
    }

    //TODO use RSA encryption to encryptAES/serialize objects
    public byte[] encryptRSA(PrivateKey key_pair, Object object)
            throws IOException, NotLoggedInException, StreamCorruptedException,
            ClassNotFoundException {
        return getBytes(object);
    }

    public static byte[] getBytes(Object object) throws IOException{
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
    //TODO use AES decryption to decryptAES/deserialize objects
    public final static Object decryptAES(InputStream is)
            throws StreamCorruptedException, IOException,
            ClassNotFoundException {
        return getObject(is);
    }

    //TODO use RSA decryption to decryptAES/deserialize objects
    public Object decryptRSA(PublicKey public_key, InputStream is)
            throws StreamCorruptedException, IOException,
            ClassNotFoundException, NotLoggedInException {
        validate();

        return getObject(is);
    }

    public static Object getObject(InputStream is)
            throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(is);

        return ois.readObject();
    }
}
