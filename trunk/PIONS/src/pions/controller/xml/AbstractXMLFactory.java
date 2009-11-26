
package pions.controller.xml;

import com.google.gdata.data.calendar.CalendarEntry;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import pions.model.Alert;
import pions.model.Contacts.Contact;
import pions.model.Employee;

/**
 * Implements Abstract Factory design pattern
 *
 */
public class AbstractXMLFactory {
    public static Document newInstance(Alert object) throws ParserConfigurationException {
        return new AlertXMLFactory().newInstance(object);
    }

    public static Document newInstance(CalendarEntry object) throws ParserConfigurationException {
        return new CalendarXMLFactory().newInstance(object);
    }

    public static Document newInstance(Contact object) throws ParserConfigurationException {
        return new ContactXMLFactory().newInstance(object);
    }

    public static Document newInstance(Employee object) throws ParserConfigurationException {
        return new EmployeeXMLFactory().newInstance(object);
    }
}
