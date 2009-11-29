
package pions.controller.xml;

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
    /** XML descriptor for an XML top node. */
    public static final String ALERT = "ALERT";
    /** XML descriptor for an ALERT inner node. */
    public static final String SENDER_PERSONAL = "SENDER_PERSONAL";
    /** XML descriptor for an ALERT inner node. */
    public static final String SENDER_EMAIL = "SENDER_EMAIL";
    /** XML descriptor for an ALERT inner node. */
    public static final String TYPE = "TYPE";
    /** XML descriptor for an ALERT inner node. */
    public static final String DESCRIPTION = "DESCRIPTION";

    //variables for creating a calendar document
    /** XML descriptor for an XML top node. */
    public static final String CALENDAR = "CALENDAR";
    /** XML descriptor for a CALENDAR 1st inner node. */
    public static final String TITLE = "TITLE";
    /** XML descriptor for a CALENDAR 2nd inner node. */
    public static final String TEXT = "TEXT";
    /** XML descriptor for a CALENDAR inner node. Multiplicity: 0..* */
    public static final String EXTENSION = "EXTENSION";
    /** XML descriptor for a EXTENSION inner node. */
    public static final String EXTENSION_DEFAULT = "EXTENSION_DEFAULT";

    //variables for creating a contact document
    /** XML descriptor for an XML top node. */
    public static final String CONTACT = "CONTACT";
    /** XML descriptor for a CONTACT 1st inner node. */
    public static final String CONTACT_EMAIL = "CONTACT_EMAIL";
    /** XML descriptor for a CONTACT 2nd inner node. */
    public static final String CONTACT_PERSONAL = "CONTACT_PERSONAL";

    //variables for creating an employee document
    /** XML descriptor for an XML top node. */
    public static final String EMPLOYEE = "EMPLOYEE";
    /** XML descriptor for an EMPLOYEE 1st inner node. */
    public static final String NAME = "NAME";
    /** XML index for NAME descriptor. */
    public static final int NAME_INDEX = 0;
    /** XML descriptor for an EMPLOYEE 2nd inner node. */
    public static final String IS_CONTACT = "IS_CONTACT";
    /** XML index for IS_CONTACT descriptor. */
    public static final int IS_CONTACT_INDEX = 0;
    /** XML descriptor for an EMPLOYEE inner node. Multiplicity: 0..* */
    public static final String POSITION = "POSITION";
    /** XML index for POSITION descriptor. */
    public static final int POSITION_INDEX = 0;
    /** XML descriptor for a POSITION 1st inner node. */
    public static final String _TITLE = "_TITLE";
    /** XML index for _TITLE descriptor. */
    public static final int _TITLE_INDEX = 0;
    /** XML descriptor for a POSITION 2nd inner node. */
    public static final String _PAY_TYPE = "_PAY_TYPE";
    /** XML index for _PAY_TYPE descriptor. */
    public static final int _PAY_TYPE_INDEX = 1;
    /** XML descriptor for a POSITION 3rd inner node. */
    public static final String _RATE = "_RATE";
    /** XML index for _RATE descriptor. */
    public static final int _RATE_INDEX = 2;
    /** XML descriptor for an EMPLOYEE inner node. */
    public static final String GMAIL_ADDRESS = "GMAIL_ADDRESS";
    /** XML index for GMAIL_ADDRESS descriptor. */
    public static final int GMAIL_ADDRESS_INDEX = 0;
    /** XML descriptor for an EMPLOYEE inner node. Multiplicity: 0..* */
    public static final String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    /** XML descriptor for an EMAIL_ADDRESS 1st inner node. */
    /** XML index for EMAIL_ADDRESS descriptor. */
    public static final int EMAIL_ADDRESS_INDEX = 0;
    public static final String _EMAIL_ADDRESS = "_EMAIL_ADDRESS";
    /** XML index for _EMAIL_ADDRESS descriptor. */
    public static final int _EMAIL_ADDRESS_INDEX = 0;
    /** XML descriptor for an EMAIL_ADDRESS 2nd inner node. */
    public static final String _PERSONAL = "_PERSONAL";
    /** XML index for _PERSONAL descriptor. */
    public static final int _PERSONAL_INDEX = 1;
    /** XML descriptor for an EMPLOYEE inner node. Multiplicity: 0..* */
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    /** XML index for PHONE_NUMBER descriptor. */
    public static final int PHONE_NUMBER_INDEX = 0;
    /** XML descriptor for an PHONE_NUMBER 1st inner node. */
    public static final String _PHONE_TYPE = "_PHONE_TYPE";
    /** XML index for _PHONE_TYPE descriptor. */
    public static final int _PHONE_TYPE_INDEX = 0;
    /** XML descriptor for an PHONE_NUMBER 2nd inner node. */
    public static final String _NUMBER = "_NUMBER";
    /** XML index for _NUMBER descriptor. */
    public static final int _NUMBER_INDEX = 1;
    /** XML descriptor for an PHONE_NUMBER 3rd inner node. */
    public static final String _EXTENSION = "_EXTENSION";
    /** XML index for _EXTENSION descriptor. */
    public static final int _EXTENSION_INDEX = 2;
    /** XML descriptor for an EMPLOYEE inner node. */
    public static final String ADDRESS = "ADDRESS";
    /** XML index for ADDRESS descriptor. */
    public static final int ADDRESS_INDEX = 0;
    /** XML descriptor for an ADDRESS 1st inner node. */
    public static final String _STREET = "_STREET";
    /** XML index for _STREET descriptor. */
    public static final int _STREET_INDEX = 0;
    /** XML descriptor for an ADDRESS 2nd inner node. */
    public static final String _CITY = "_CITY";
    /** XML index for _CITY descriptor. */
    public static final int _CITY_INDEX = 1;
    /** XML descriptor for an ADDRESS 3rd inner node. */
    public static final String _STATE = "_STATE";
    /** XML index for _STATE descriptor. */
    public static final int _STATE_INDEX = 2;
    /** XML descriptor for an ADDRESS 4th inner node. */
    public static final String _ZIP = "_ZIP";
    /** XML index for _ZIP descriptor. */
    public static final int _ZIP_INDEX = 3;

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

    public final static String getAttr(Node root){
        return root.getNodeValue();
    }

    public final static void setAttribute(Element root, String child, String value){
        root.setAttribute(child, value);
    }

    public final static NodeList getElements(Element root, String tag_name){
        return root.getElementsByTagName(tag_name);
    }

    public final static void removeNode(Element root, Node node){
        root.removeChild(node);
    }

    public final static Element getHead(Document xml, String root_id){
        return xml.getElementById(root_id);
    }
}
