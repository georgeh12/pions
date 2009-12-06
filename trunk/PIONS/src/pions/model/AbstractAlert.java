
package pions.model;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 * Implements the strategy design pattern.
 * 
 */
public interface AbstractAlert {
    public void acceptAlert(Alert.AlertType type)
            throws NotLoggedInException,
            AlertClassException, ServiceException, AuthenticationException,
            IOException, UnsupportedEncodingException, StreamCorruptedException,
            AddressException, NoSuchProviderException, MessagingException,
            ClassNotFoundException, NoSuchAlgorithmException, URISyntaxException;
    public void rejectAlert(Alert.AlertType type) throws AlertClassException;
    public void ignoreAlert(Alert.AlertType type) throws AlertClassException;
    public String getDetails();
}
