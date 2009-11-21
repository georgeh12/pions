
package pions.controller;

import java.util.Iterator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ContactInfo.PhoneNumber;
import pions.model.Employee;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;
import pions.model.Positions.Position;

/**
 * Iterator design implementation.
 * @author George
 */
public class EmployeeXML {
    public static final String EMPLOYEE = "EMPLOYEE";
    public static final String NAME = "NAME";
    public static final String IS_CONTACT = "IS_CONTACT";
    public static final String POSITION = "POSITION";
    public static final String GMAIL_ADDRESS = "GMAIL_ADDRESS";
    public static final String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String ADDRESS = "ADDRESS";
    private Employee employee;

    public EmployeeXML(Employee employee) {
        this.employee = employee;
    }

    public Document get(){
        Document xml = null;
        
        try {
            //create a new document
            xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);
            
            Element root = xml.createElement(EMPLOYEE);
            Element element;
            xml.appendChild(root);
            
            //Set name
            element = xml.createElement(NAME);
            element.setNodeValue(employee.getName());
            root.appendChild(element);

            //Set isContact
            element = xml.createElement(IS_CONTACT);
            element.setNodeValue((EmployeeSingleton.getInstance().getContacts().searchContacts(employee.getGmail().getGmailAddress()) == null ? "yes" : "no"));
            root.appendChild(element);

            //Set positions
            Iterator<Position> positions = employee.getPositions().iterator();
            while (positions.hasNext()) {
                element = xml.createElement(POSITION);
                element.setNodeValue(positions.next().toString());
                root.appendChild(element);
            }

            //Set gmail address
            element = xml.createElement(GMAIL_ADDRESS);
            element.setNodeValue(employee.getGmail().getGmailAddress().getAddress());
            root.appendChild(element);

            //Set email addresses
            for (EmailAddress email : employee.getContactInfo().getEmailAddresses()) {
                element = xml.createElement(EMAIL_ADDRESS);
                element.setNodeValue(email.getAddress());
                root.appendChild(element);
            }

            //Set phone numbers
            for (PhoneNumber phone : employee.getContactInfo().getPhoneNumbers()) {
                element = xml.createElement(PHONE_NUMBER);
                element.setNodeValue(phone.toString());
                root.appendChild(element);
            }

            //Set address
            element = xml.createElement(ADDRESS);
            element.setNodeValue(employee.getContactInfo().getAddress().toString());
            root.appendChild(element);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
