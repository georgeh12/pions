
package pions.controller;

import java.util.Iterator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pions.model.Contacts.Contact;

/**
 *
 * @author George
 */
public class ContactIterator extends XMLIterator<Contact> {
    public static final String CONTACT = "CONTACT";
    public static final String EMAIL = "EMAIL";

    public ContactIterator(Iterator<Contact> iter) {
        super(iter);
    }

    @Override
    public Document next() {
        xml = null;

        try {
            //create a new document
            xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

            Element contact = xml.createElement(CONTACT);
            xml.appendChild(contact);

            Contact current = iter.next();

            contact.setAttribute(EMAIL, current.getAddress().toString());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
