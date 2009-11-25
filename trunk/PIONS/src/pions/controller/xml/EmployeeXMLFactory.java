
package pions.controller.xml;

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
 * 
 */
public final class EmployeeXMLFactory extends XMLFactory<Employee> {
    public static final String EMPLOYEE = "EMPLOYEE";
    public static final String NAME = "NAME";
    public static final String IS_CONTACT = "IS_CONTACT";
    public static final String POSITIONS = "POSITIONS";
    public static final String GMAIL_ADDRESS = "GMAIL_ADDRESS";
    public static final String EMAIL_ADDRESSES = "EMAIL_ADDRESSES";
    public static final String PHONE_NUMBERS = "PHONE_NUMBERS";
    public static final String ADDRESS = "ADDRESS";

    public Document newInstance(Employee employee) throws ParserConfigurationException{
        xml = null;
        
        try {
            //create a new document
            xml = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .getDOMImplementation().createDocument(null, null, null);

            Element root = getHead(EMPLOYEE);

            //Set name
            setAttribute(root, NAME, employee.getName());

            //Set isContact
            setAttribute(root, IS_CONTACT, (EmployeeSingleton.getInstance().getContacts()
                    .searchContacts(employee.getGmail().getGmailAddress()) == null ? "yes" : "no"));

            //Set positions
            Iterator<Position> positions = employee.getPositions().iterator();
            while (positions.hasNext()) {
                addNode(root, POSITIONS, positions.next().toString());
            }

            //Set gmail address
            setAttribute(root, GMAIL_ADDRESS, employee.getGmail().getGmailAddress().getAddress());

            //Set email addresses
            for (EmailAddress email : employee.getContactInfo().getEmailAddresses()) {
                addNode(root, EMAIL_ADDRESSES, email.getAddress());
            }

            //Set phone numbers
            for (PhoneNumber phone : employee.getContactInfo().getPhoneNumbers()) {
                addNode(root, PHONE_NUMBERS, phone.toString());
            }

            //Set address
            setAttribute(root, ADDRESS, employee.getContactInfo().getAddress().toString());

        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
