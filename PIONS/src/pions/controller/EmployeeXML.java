
package pions.controller;

import java.util.ArrayList;
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
    public static final String POSITIONS = "POSITIONS";
    public static final String POSITION = "POSITION";
    public static final String GMAIL_ADDRESS = "GMAIL_ADDRESS";
    public static final String EMAIL_ADDRESSES = "EMAIL_ADDRESSES";
    public static final String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static final String PHONE_NUMBERS = "PHONE_NUMBERS";
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
            xml.appendChild(root);

            Element positions = xml.createElement(POSITIONS);
            Element email_addresses = xml.createElement(EMAIL_ADDRESSES);
            Element phone_numbers = xml.createElement(PHONE_NUMBERS);
            root.appendChild(positions);
            root.appendChild(email_addresses);
            root.appendChild(phone_numbers);
            
            //Set name
            root.setAttribute(NAME, employee.getName());

            //Set isContact
            root.setAttribute(IS_CONTACT, (EmployeeSingleton.getInstance().getContacts().searchContacts(employee.getGmail().getGmailAddress()) == null ? "yes" : "no"));

            //Set positions
            int count = 0;
            Iterator<Position> position_iter = employee.getPositions().iterator();
            while (position_iter.hasNext()) {
                positions.setAttribute(POSITION + count, position_iter.next().toString());
                count++;
            }

            //Set gmail address
            root.setAttribute(GMAIL_ADDRESS, employee.getGmail().getGmailAddress().getAddress());
            
            //Set email addresses
            count = 0;
            for (EmailAddress email : employee.getContactInfo().getEmailAddresses()) {
                email_addresses.setAttribute(EMAIL_ADDRESS + count, email.getAddress());
                count++;
            }

            //Set phone numbers
            count = 0;
            for (PhoneNumber phone : employee.getContactInfo().getPhoneNumbers()) {
                phone_numbers.setAttribute(PHONE_NUMBER + count, phone.toString());
                count++;
            }

            //Set address
            root.setAttribute(ADDRESS, employee.getContactInfo().getAddress().toString());
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
