
package pions.model;

import com.sun.mail.util.BASE64DecoderStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.NoSuchProviderException;
import javax.mail.MessagingException;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.NotLoggedInException;
import pions.model.Alert.AlertType;
import pions.model.ModelException.MessageParserException;

/**
 * Implements Observer design pattern
 * @author George
 */
public class Gmail extends Observable implements Serializable {
    private static Gmail gmail = null;
    private final static String HOST = "pop.gmail.com";
    private final static String STORE = "pop3s";
    private final static String FOLDER_NAME = "Inbox";
    private final static String SUBJECT = "PIONS Alert";
    private final static String ALERT_HEADER = AlertType.class.getName();
    private final static int ATTACHMENT_INDEX = 0;
    private EmailAddress gmail_address;
    private String gmail_password;
    private int received_alerts = 0;
    private ArrayList<Alert> active_alerts = new ArrayList<Alert>();
    private ArrayList<Alert> saved_alerts = new ArrayList<Alert>();

    private Gmail() { }

    static Gmail getInstance(){
        if(gmail == null){
            gmail = new Gmail();
        }

        return gmail;
    }

    public Iterator<Alert> getActiveAlerts(){
        return active_alerts.iterator();
    }

    public Iterator<Alert> getSavedAlerts(){
        return saved_alerts.iterator();
    }

    public void deleteSavedAlert(int index) {
        saved_alerts.remove(index);
    }

    public boolean isValid(){
        try {
            connect(getSession());
            return true;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }

    public void setGmail(EmailAddress gmail_username, String gmail_password){
        this.gmail_address = gmail_username;
        this.gmail_password = gmail_password;
    }

    public EmailAddress getGmailAddress(){
        return gmail_address;
    }

    String getPassword(){
        return gmail_password;
    }

    /**
     * New alerts are placed into the active_alerts list. Returns any errors
     * received while parsing messages. Throws errors if they occur while
     * messages are being retrieved from Gmail.
     * @return
     * @throws NoSuchProviderException
     * @throws MessagingException
     * @throws IOException
     */
    public ArrayList<Exception> parseAlerts() throws NoSuchProviderException,
            MessagingException, IOException {
        ArrayList<Message> new_alerts = retrieveAlerts();
        ArrayList<Exception> message_exceptions = new ArrayList<Exception>();

        for(Message current: new_alerts){
            AlertType alert_type = null;
            
            try {
                alert_type = AlertType.parse(current.getHeader(ALERT_HEADER)[ATTACHMENT_INDEX]);

                //Retrieves the attachment and decodes it using decryptRSA()
                Object object = Alert.decryptAlert(
                        ((InternetAddress)current.getFrom()[0]).getAddress(), alert_type,
                        (BASE64DecoderStream)((Multipart) current.getContent()).getBodyPart(0).getContent());

                //Strategy design pattern at work. Providing an alternative to subclassing
                Alert add_alert =
                        new Alert((AbstractAlert)alert_type.getAssociatedClass().cast(object),
                        alert_type);

                active_alerts.add(add_alert);
            } catch (Exception e) {
                e.initCause(new MessageParserException(new_alerts.indexOf(current), alert_type));
                message_exceptions.add(e);
            }
        }

        //Observer design pattern
        if(active_alerts.size() > 0)
            notifyObservers("You have " + active_alerts.size() + " active alerts!");
        
        return message_exceptions;
    }

    public Alert saveAlert(int index){
        Alert alert = active_alerts.remove(index);
        saved_alerts.add(alert);

        return alert;
    }

    private Folder getFolder(Store store)
            throws NoSuchProviderException, MessagingException {
        Folder folder = store.getFolder(FOLDER_NAME);

        return folder;
    }

    private Store connect(Session session) throws NoSuchProviderException, MessagingException{
        //Connect to Gmail server
        Store new_store = session.getStore(STORE);
        new_store.connect(HOST, gmail_address.getAddress(), gmail_password);
        
        return new_store;
    }

    private Session getSession()
            throws NoSuchProviderException, MessagingException {
        //Get properties from System
        Properties props = (Properties) System.getProperties().clone();
        props.put("mail.smtp.host", "true");
        Session session = Session.getInstance(props, null);

        return session;
    }

    private ArrayList<Message> retrieveAlerts() throws NoSuchProviderException, MessagingException, IOException {
        Session session = getSession();

        Folder folder = getFolder(connect(session));
        folder.open(Folder.READ_WRITE);
        Message alerts[] = folder.getMessages(received_alerts + 1, folder.getMessageCount());

        ArrayList<Message> new_alerts = new ArrayList<Message>();
        for(Message current: alerts){
            received_alerts = current.getMessageNumber();
            
            if(current.getSubject().compareTo(SUBJECT) == 0){
                new_alerts.add(current);
            }
        }

        return new_alerts;
    }

    /**
     * Sends a message to the gmail address provided, with the attachment and
     * isSuperior boolean as the message text.
     * @param attachment
     * @param isSuperior
     */
    public void sendAlert(ArrayList<EmailAddress> recipients, Alert alert)
            throws AddressException, NoSuchProviderException,
            MessagingException, NotLoggedInException, IOException,
            StreamCorruptedException, ClassNotFoundException {
        //Encrypt the message contents
        byte[] attachment = alert.getBytes();

        Session session = getSession();
        MimeMessage message = new MimeMessage(session);

        //Add message content
        for(EmailAddress email_address: recipients){
            message.addRecipient(RecipientType.TO, new InternetAddress(email_address.getAddress()));
        }
        message.setFrom(new InternetAddress(alert.getAddress().getAddress()));
        message.setSubject(SUBJECT);
        Multipart multipart = new MimeMultipart();

        //Add message header
        message.addHeader(ALERT_HEADER, alert.getType().name());

        //Add attachment
        BodyPart mail_attachment = new MimeBodyPart();
        mail_attachment.setContent(attachment, "application/octet-stream");
        multipart.addBodyPart(mail_attachment, ATTACHMENT_INDEX);

        //Set content
        message.setContent(multipart);

        //Send message
        Transport transport = session.getTransport("smtps");
        try {
            transport.connect(HOST, gmail_address.getAddress(), gmail_password);
            transport.sendMessage(message, message.getAllRecipients());
        } finally {
            transport.close();
        }
    }
}
