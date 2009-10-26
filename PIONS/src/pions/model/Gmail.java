
package pions.model;

import pions.model.alerts.SwapShiftAlert;
import pions.model.alerts.WorkScheduleAlert;
import pions.model.alerts.EmployeeAlert;
import com.sun.mail.util.BASE64DecoderStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Properties;
import javax.mail.Address;
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
import pions.model.alerts.Alert.AlertType;
import pions.model.ModelException.MessageParserException;
import pions.model.alerts.Alert;
import pions.model.swapshift.SwapShift;

/**
 *
 * @author George
 */
public class Gmail extends Observable implements Serializable {
    //TODO testing purposes only
    public static void main(String args[]){
        try{
            Gmail gmail = new Gmail("pionstest@gmail.com", "PIONSpassword");
            
            //gmail.sendAlert(AlertType.SwapShiftMachine, EmployeeSingleton.getInstance().encryptRSA(new SwapShiftMachine()));

            //gmail.received_alerts = 1;
            //gmail.received_alerts = 2;
            ArrayList<Exception> errors = gmail.parseAlerts();
            if(errors.size() > 0){
                System.out.println(errors);
            }
            System.out.println(gmail.active_alerts.get(0));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private final static String HOST = "pop.gmail.com";
    private final static String STORE = "pop3s";
    private final static String FOLDER_NAME = "Inbox";
    private final static String SUBJECT = "PIONS Alert";
    private final static String ALERT_HEADER = AlertType.class.getName();
    private String gmail_username;
    private String gmail_password;
    private int received_alerts = 0;
    private ArrayList<Alert> active_alerts = new ArrayList<Alert>();
    private ArrayList<Alert> saved_alerts = new ArrayList<Alert>();

    public Gmail() { }

    public Gmail(String gmail_username, String gmail_password) {
        setGmail(gmail_username, gmail_password);
    }

    public void setGmail(String gmail_username, String gmail_password){
        this.gmail_username = gmail_username;
        this.gmail_password = gmail_password;

        notifyObservers();
    }

    public String getUsername(){
        return gmail_username;
    }

    String getPassword(){
        return gmail_username;
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
                alert_type = AlertType.parse(current.getHeader(ALERT_HEADER)[0]);

                //Retrieves the attachment and decodes it using decryptRSA()
                Object object = EmployeeSingleton.getInstance().decryptRSA(
                        (BASE64DecoderStream)((Multipart) current.getContent()).getBodyPart(0).getContent());

                //Strategy design pattern at work.
                Alert add_alert = null;

                switch (alert_type) {
                    case AddSubordinate:
                        add_alert = new EmployeeAlert((Employee)object);
                        add_alert.set(AlertType.AddSubordinate);
                        active_alerts.add(add_alert);
                        break;
                    case AddManager:
                        add_alert = new EmployeeAlert((Employee)object);
                        add_alert.set(AlertType.AddManager);
                        active_alerts.add(add_alert);
                        break;
                    case NewWorkSchedule:
                        add_alert = new WorkScheduleAlert((CalendarCollection)object);
                        add_alert.set(AlertType.NewWorkSchedule);
                        break;
                    case UpdatedWorkSchedule:
                        add_alert = new WorkScheduleAlert((CalendarCollection)object);
                        add_alert.set(AlertType.UpdatedWorkSchedule);
                        break;
                    case SwapShift:
                        add_alert = new SwapShiftAlert((SwapShift)object);
                        add_alert.set(AlertType.SwapShift);
                        break;
                    default:
                        break;
                }

                active_alerts.add(add_alert);
            } catch (Exception e) {
                e.initCause(new MessageParserException(new_alerts.indexOf(current), alert_type));
                message_exceptions.add(e);
            }
        }
        
        return message_exceptions;
    }

    public void saveAlert(int index){
        saved_alerts.add(active_alerts.remove(index));
    }

    private Folder getFolder(Store store)
            throws NoSuchProviderException, MessagingException {
        Folder folder = store.getFolder(FOLDER_NAME);

        return folder;
    }

    private Store connect(Session session) throws NoSuchProviderException, MessagingException{
        //Connect to Gmail server
        Store new_store = session.getStore(STORE);
        new_store.connect(HOST, gmail_username, gmail_password);
        
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
    public void sendAlert(AlertType type, byte[] attachment)
            throws AddressException, NoSuchProviderException, MessagingException {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);

        //Add message content
        Address recipient = new InternetAddress(gmail_username);
        message.addRecipient(RecipientType.TO, recipient);
        message.setSubject(SUBJECT);
        Multipart multipart = new MimeMultipart();

        //Add message header
        message.addHeader(ALERT_HEADER, type.name());

        //Add attachment
        BodyPart mail_attachment = new MimeBodyPart();
        mail_attachment.setContent(attachment, "application/octet-stream");
        multipart.addBodyPart(mail_attachment);

        //Set content
        message.setContent(multipart);

        //Send message
        Transport transport = session.getTransport("smtps");
        try {
            transport.connect(HOST, gmail_username, gmail_password);
            transport.sendMessage(message, message.getAllRecipients());
        } finally {
            transport.close();
        }
    }
}
