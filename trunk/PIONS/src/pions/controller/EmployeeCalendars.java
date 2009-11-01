
package pions.controller;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import pions.model.CalendarCollection;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class EmployeeCalendars {
    public static CalendarCollection initCalendar(String calendar_name){
        try {
            if(EmployeeSingleton.getInstance().getGmail().isValid()){
                    return new CalendarCollection(calendar_name, true);
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            //If an exception occurred, calendar is set to default
            return new CalendarCollection(calendar_name);
        }
    }
}
