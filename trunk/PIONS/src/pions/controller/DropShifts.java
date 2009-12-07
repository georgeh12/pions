
package pions.controller;

import com.google.gdata.data.extensions.EventEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import pions.model.Alert.AlertType;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.dropshift.DropShift;

/**
 *
 * 
 */
public final class DropShifts {
    
    private static EventEntry shift_event = null;

    public static void setCurrentShift(int index){
        shift_event = Calendars.getWorkEvent(index);
    }

    public static void sendDropShift() {
        try{
            new DropShift(shift_event).acceptAlert(AlertType.DropShift);
        } catch(NotLoggedInException e){
            e.printStackTrace();
        } catch(AlertClassException e){
            e.printStackTrace();
        } catch(AuthenticationException e){
            e.printStackTrace();
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        } catch(ServiceException e){
            e.printStackTrace();
        } catch(StreamCorruptedException e){
            e.printStackTrace();
        } catch(AddressException e){
            e.printStackTrace();
        } catch(NoSuchProviderException e){
            e.printStackTrace();
        } catch(MessagingException e){
            e.printStackTrace();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
