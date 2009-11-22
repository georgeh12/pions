
package pions.model;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.ModelException.ScheduleNotFoundException;

/**
 * Implements the strategy design pattern.
 * @author George
 */
public interface AbstractAlert {
    public void acceptAlert(Alert.AlertType type, EmailAddress sender)
            throws NotLoggedInException, ScheduleNotFoundException,
            AlertClassException, ServiceException, AuthenticationException,
            IOException, UnsupportedEncodingException, StreamCorruptedException,
            AddressException, NoSuchProviderException, MessagingException,
            ClassNotFoundException, NoSuchAlgorithmException;
    public void rejectAlert(Alert.AlertType type) throws AlertClassException;
    public void ignoreAlert(Alert.AlertType type) throws AlertClassException;
    public String getDetails();
}
