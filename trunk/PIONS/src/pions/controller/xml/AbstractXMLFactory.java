
package pions.controller.xml;

import com.google.gdata.data.extensions.EventEntry;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import pions.model.Alert;
import pions.model.Contacts.Contact;
import pions.model.Employee;
import pions.model.EmployeeSingleton;

/**
 * Implements Abstract Factory design pattern
 *
 */
public class AbstractXMLFactory {
    private static Document newAlert(Alert object) throws ParserConfigurationException {
        return new AlertXMLFactory().newInstance(object);
    }

    private static Document newEventEntry(EventEntry object) throws ParserConfigurationException {
        return new CalendarXMLFactory().newInstance(object);
    }

    private static Document newContact(Contact object) throws ParserConfigurationException {
        return new ContactXMLFactory().newInstance(object);
    }

    private static Document newEmployee(Employee object) throws ParserConfigurationException {
        return new EmployeeXMLFactory().newInstance(object);
    }
    
    public static Document newInstance(Object object) throws ParserConfigurationException {
        if(object.getClass() == Alert.class){
            return newAlert((Alert) object);
        }
        else if(object.getClass() == EventEntry.class){
            return newEventEntry((EventEntry) object);
        }
        else if(object.getClass() == Contact.class){
            return newContact((Contact) object);
        }
        else if(object.getClass() == Employee.class || object.getClass() == EmployeeSingleton.class){
            return newEmployee((Employee) object);
        }
        else {
            throw new UnsupportedOperationException();
        }
    }
}
