
package pions.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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

/**
 *
 * @author George
 */
public class Gmail implements Serializable {
    //TODO testing purposes only
    public static void main(String args[]){
        try{
            Gmail gmail = new Gmail("pionstest@gmail.com", "PIONSpassword");
            
            //gmail.sendAlert(AlertType.SwapShift, "Hello World!".getBytes());

            //gmail.received_alerts = 1;
            //gmail.received_alerts = 2;
            ArrayList<Message> messages = gmail.retrieveAlerts();
            System.out.println(messages.get(0).getHeader(alert_header)[0] +
                    messages.get(0).getMessageNumber());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private final static String host = "pop.gmail.com";
    private final static String store = "pop3s";
    private final static String folder_name = "Inbox";
    private final static String subject = "PIONS MESSAGE SYSTEM";
    private final static String alert_header = AlertType.class.getName();
    private String gmail_username;
    private String gmail_password;
    private int received_alerts = 0;

    public Gmail(String gmail_username, String gmail_password) {
        setGmail(gmail_username, gmail_password);
    }

    public void setGmail(String gmail_username, String gmail_password){
        this.gmail_username = gmail_username;
        this.gmail_password = gmail_password;
    }

    public String getUsername(){
        return gmail_username;
    }

    //TODO parse alerts

    private Folder getFolder(Store store)
            throws NoSuchProviderException, MessagingException {
        Folder folder = store.getFolder(folder_name);

        return folder;
    }

    private Store connect(Session session) throws NoSuchProviderException, MessagingException{
        //Connect to Gmail server
        Store new_store = session.getStore(store);
        new_store.connect(host, gmail_username, gmail_password);
        
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

    public ArrayList<Message> retrieveAlerts() throws NoSuchProviderException, MessagingException, IOException {
        Session session = getSession();

        Folder folder = getFolder(connect(session));
        folder.open(Folder.READ_WRITE);
        Message alerts[] = folder.getMessages(received_alerts + 1, folder.getMessageCount());

        ArrayList<Message> new_alerts = new ArrayList<Message>();
        for(Message current: alerts){
            received_alerts = current.getMessageNumber();
            
            if(current.getSubject().compareTo(subject) == 0){
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

        //Set message content
        Address recipient = new InternetAddress(gmail_username);
        message.addRecipient(RecipientType.TO, recipient);
        message.setSubject(subject);
        Multipart multipart = new MimeMultipart();

        //Add message text
        BodyPart mail_text = new MimeBodyPart();
        mail_text.setText(type.toString());
        multipart.addBodyPart(mail_text);
        message.addHeader(alert_header, type.name());

        //Add attachment
        BodyPart mail_attachment = new MimeBodyPart();
        mail_attachment.setContent(attachment, "application/octet-stream");
        multipart.addBodyPart(mail_attachment);

        //Set content
        message.setContent(multipart);

        //Send message
        Transport transport = session.getTransport("smtps");
        try {
            transport.connect(host, gmail_username, gmail_password);
            transport.sendMessage(message, message.getAllRecipients());
        } finally {
            transport.close();
        }
    }

    public enum AlertType{
        AddSubordinate, AddManager, NewWorkSchedule, UpdatedWorkSchedule,
        SwapShift;

        private AlertType parse(String type){
            if(type.equals(AddSubordinate.toString())){
                return AddSubordinate;
            }
            else if(type.equals(AddManager.toString())){
                return AddManager;
            }
            else if(type.equals(NewWorkSchedule.toString())){
                return NewWorkSchedule;
            }
            else if(type.equals(UpdatedWorkSchedule.toString())){
                return UpdatedWorkSchedule;
            }
            else if(type.equals(SwapShift.toString())){
                return SwapShift;
            }
            else{
                return null;
            }
        }
    }
}
