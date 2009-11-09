
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
public class EmployeeIterator extends XMLIterator {
    public static final String EMPLOYEE = "EMPLOYEE";
    public static final String NAME = "NAME";
    public static final String IS_CONTACT = "IS_CONTACT";
    public static final String POSITIONS = "POSITIONS";
    public static final String POSITION = "POSITION";
    public static final String GMAIL_ADDRESS = "GMAIL_ADDRESS";
    public static final String EMAIL_ADDRESSES = "EMAIL_ADDRESSES";
    public static final String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static final String PHONE_NUMBERS = "PHONE_NUMBERS";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String ADDRESS = "ADDRESS";

    public EmployeeIterator(Iterator<Employee> iter) throws ParserConfigurationException {
        super(iter);
    }

    @Override
    public Document next(){
        xml = null;
        
        try {
            //create a new document
            xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

            Element employee = xml.createElement(EMPLOYEE);
            xml.appendChild(employee);

            Element positions = xml.createElement(POSITIONS);
            Element email_addresses = xml.createElement(EMAIL_ADDRESSES);
            Element phone_numbers = xml.createElement(PHONE_NUMBERS);
            employee.appendChild(positions);
            employee.appendChild(email_addresses);
            employee.appendChild(phone_numbers);

            Employee current = (Employee)iter.next();
            
            //Set name
            employee.setAttribute(NAME, current.getName());

            //Set isContact
            employee.setAttribute(IS_CONTACT, (EmployeeSingleton.getInstance().getContacts().searchContacts(current.getGmail().getGmailAddress()) == null ? "yes" : "no"));

            //Set positions
            int count = 0;
            Iterator<Position> position_iter = current.getPositions().iterator();
            while (position_iter.hasNext()) {
                positions.setAttribute(POSITION + count, position_iter.next().toString());
                count++;
            }

            //Set gmail address
            employee.setAttribute(GMAIL_ADDRESS, current.getGmail().getGmailAddress().getAddress());
            
            //Set email addresses
            count = 0;
            for (EmailAddress email : current.getContactInfo().getEmailAddresses()) {
                email_addresses.setAttribute(EMAIL_ADDRESS + count, email.getAddress());
                count++;
            }

            //Set phone numbers
            count = 0;
            for (PhoneNumber phone : current.getContactInfo().getPhoneNumbers()) {
                phone_numbers.setAttribute(PHONE_NUMBER + count, phone.toString());
                count++;
            }

            //Set address
            employee.setAttribute(ADDRESS, current.getContactInfo().getAddress().toString());
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return super.next();
    }
}
