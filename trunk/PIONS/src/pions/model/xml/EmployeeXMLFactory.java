
package pions.model.xml;

import java.util.Iterator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pions.model.ContactInfo.Address;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ContactInfo.PhoneNumber;
import pions.model.Employee;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;
import pions.model.Positions.Position;

/**
 *
 * 
 */
public final class EmployeeXMLFactory extends XMLFactory<Employee> {
    protected Document newInstance(Employee employee) throws ParserConfigurationException{
        xml = null;
        
        try {
            //create a new document
            xml = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .getDOMImplementation().createDocument(null, null, null);

            Element root = setHead(EMPLOYEE);

            //Set name
            setAttribute(root, NAME, employee.getName());

            //Set isContact
            setAttribute(root, IS_CONTACT, (EmployeeSingleton.getInstance().getContacts()
                    .searchContacts(employee.getGmail().getGmailAddress()) == null ? "yes" : "no"));

            //Set positions
            Iterator<Position> positions = employee.getPositions().iterator();
            while (positions.hasNext()) {
                Position position = positions.next();

                Element element = addNode(root, POSITION);
                setAttribute(element, _TITLE, position.getTitle());

                if(employee.equals(EmployeeSingleton.getInstance())){
                    setAttribute(element, _PAY_TYPE, position.getPayType().name());
                    setAttribute(element, _RATE, Double.toString(position.getRate()));
                }
            }

            //Set gmail address
            setAttribute(root, GMAIL_ADDRESS, employee.getGmail().getGmailAddress().getAddress());

            //Set email addresses
            for (EmailAddress email : employee.getContactInfo().getEmailAddresses()) {
                Element element = addNode(root, EMAIL_ADDRESS);
                setAttribute(element, _EMAIL_ADDRESS, email.getAddress());
                setAttribute(element, _PERSONAL, email.getPersonal());
            }

            //Set phone numbers
            for (PhoneNumber phone : employee.getContactInfo().getPhoneNumbers()) {
                Element element = addNode(root, PHONE_NUMBER);
                setAttribute(element, _PHONE_TYPE, phone.getType().name());
                setAttribute(element, _NUMBER, phone.toStringPhone());
                setAttribute(element, _EXTENSION, phone.toStringExtension());
            }

            //Set address
            {
                Address address = employee.getContactInfo().getAddress();

                Element element = addNode(root, ADDRESS);
                setAttribute(element, _STREET, address.getStreet());
                setAttribute(element, _CITY, address.getCity());
                setAttribute(element, _STATE, address.getState().toAbbrev());
                setAttribute(element, _ZIP, address.getZip());
            }
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
