
package pions.controller.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pions.model.Contacts.Contact;

/**
 *
 * 
 */
public final class ContactXMLFactory extends XMLFactory<Contact> {
    protected Document newInstance(Contact contact) throws ParserConfigurationException{
        xml = null;

        //create a new document
        xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

        Element root = getHead(CONTACT);

        setAttribute(root, CONTACT_EMAIL, contact.getAddress().getAddress());
        setAttribute(root, CONTACT_PERSONAL, contact.getAddress().getPersonal());

        return xml;
    }
}
