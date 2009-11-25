
package pions.controller.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pions.model.Contacts.Contact;

/**
 *
 * @author George
 */
public final class ContactXMLFactory extends XMLFactory<Contact> {
    public static final String CONTACT = "CONTACT";
    public static final String EMAIL = "EMAIL";
    public static final String PERSONAL = "PERSONAL";

    public Document newInstance(Contact contact) throws ParserConfigurationException{
        xml = null;

        //create a new document
        xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

        Element root = getHead(CONTACT);

        setAttribute(root, EMAIL, contact.getAddress().getAddress());
        setAttribute(root, PERSONAL, contact.getAddress().getPersonal());

        return xml;
    }
}
