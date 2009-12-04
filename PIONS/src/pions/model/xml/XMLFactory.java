
package pions.model.xml;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Implements the Factory design pattern
 * For public static variables, multiplicity is 1, unless stated otherwise.
 */
public abstract class XMLFactory<T> {
    
    //variables for creating an alert document
    /** XML descriptor for an XML top element node. */
    public static final String ALERT = "Alert";
    /** XML descriptor for an ALERT attribute node. */
    public static final String SENDER_PERSONAL = "SenderName";
    /** XML descriptor for an ALERT attribute node. */
    public static final String SENDER_EMAIL = "SenderEmail";
    /** XML descriptor for an ALERT attribute node. */
    public static final String ALERT_TYPE = "AlertType";
    /** XML descriptor for an ALERT attribute node. */
    public static final String DESCRIPTION = "Description";

    //variables for creating a calendar document
    /** XML descriptor for an XML top element node. */
    public static final String CALENDAR = "Calendar";
    /** XML descriptor for a CALENDAR attribute node. */
    public static final String TITLE = "Title";
    /** XML descriptor for a CALENDAR attribute node. */
    public static final String TEXT = "Text";
    /** XML descriptor for a CALENDAR element node. */
    public static final String START_TIME = "Start_Time";
    /** XML descriptor for a CALENDAR element node. */
    public static final String END_TIME = "End_Time";

    //variables for creating a contact document
    /** XML descriptor for an XML top element node. */
    public static final String CONTACT = "Contact";
    /** XML descriptor for a CONTACT attribute node. */
    public static final String CONTACT_EMAIL = "Contact_Email";
    /** XML descriptor for a CONTACT attribute node. */
    public static final String CONTACT_PERSONAL = "Contact_Name";

    //variables for creating an employee document
    /** XML descriptor for an XML top element node. */
    public static final String EMPLOYEE = "Employee";
    /** XML descriptor for an EMPLOYEE attribute node. */
    public static final String NAME = "Name";
    /** XML descriptor for an EMPLOYEE attribute node. */
    public static final String IS_CONTACT = "Is_Contact";
    /** XML descriptor for an EMPLOYEE element node. Multiplicity: 0..* */
    public static final String POSITION = "Position";
    /** XML descriptor for a POSITION attribute node. */
    public static final String _TITLE = "Title";
    /** XML descriptor for a POSITION attribute node. */
    public static final String _PAY_TYPE = "Pay_Type";
    /** XML descriptor for a POSITION attribute node. */
    public static final String _RATE = "Rate";
    /** XML descriptor for an EMPLOYEE attribute node. */
    public static final String GMAIL_ADDRESS = "Gmail_Address";
    /** XML descriptor for an EMPLOYEE element node. Multiplicity: 0..* */
    public static final String EMAIL_ADDRESS = "Email_Address";
    /** XML descriptor for an EMAIL_ADDRESS attribute node. */
    public static final String _EMAIL_ADDRESS = "Email";
    /** XML descriptor for an EMAIL_ADDRESS attribute node. */
    public static final String _PERSONAL = "Personal_Name";
    /** XML descriptor for an EMPLOYEE element node. Multiplicity: 0..* */
    public static final String PHONE_NUMBER = "Phone_Number";
    /** XML descriptor for an PHONE_NUMBER attribute node. */
    public static final String _PHONE_TYPE = "Phone_Type";
    /** XML descriptor for an PHONE_NUMBER attribute node. */
    public static final String _NUMBER = "Phone_Number";
    /** XML descriptor for an PHONE_NUMBER attribute node. */
    public static final String _EXTENSION = "Phone_Extension";
    /** XML descriptor for an EMPLOYEE element node. */
    public static final String ADDRESS = "Address";
    /** XML descriptor for an ADDRESS attribute node. */
    public static final String _STREET = "Street";
    /** XML descriptor for an ADDRESS attribute node. */
    public static final String _CITY = "City";
    /** XML descriptor for an ADDRESS attribute node. */
    public static final String _STATE = "State";
    /** XML descriptor for an ADDRESS attribute node. */
    public static final String _ZIP = "Zip";
    /** XML descriptor for the index of elements with a multiplicity of one. */
    public static final int DEFAULT_INDEX = 0;

    protected Document xml;

    protected abstract Document newInstance(T object) throws ParserConfigurationException;

    protected final Element setHead(String root_id){
        return setHead(xml, root_id);
    }

    public final static Element setHead(Document xml, String root_id){
        Element root = xml.createElement(root_id);
        root.setAttribute(root_id, root_id);
        root.setIdAttribute(root_id, true);
        xml.appendChild(root);

        return root;
    }

    protected final Element addNode(Element root, String child){
        return addNode(xml, root, child);
    }

    public final static Element addNode(Document xml, Element root, String child){
        Element child_element = xml.createElement(child);
        root.appendChild(child_element);

        return child_element;
    }

    public final static String getAttribute(Node root, String attr){
        return ((Element)root).getAttribute(attr);
    }

    public final static void setAttribute(Node root, String child, String value){
        ((Element)root).setAttribute(child, value);
    }

    public final static NodeList getElements(Node root, String tag_name){
        return ((Element)root).getElementsByTagName(tag_name);
    }

    public final static void removeNode(Element root, Node node){
        root.removeChild(node);
    }

    public final static Element getHead(Document xml, String root_id){
        return xml.getElementById(root_id);
    }
}
