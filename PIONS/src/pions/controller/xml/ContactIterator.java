
package pions.controller.xml;

import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import pions.model.Contacts.Contact;

/**
 *
 * @author George
 */
public class ContactIterator extends XMLIterator<Contact> {
    private ContactXMLFactory factory = new ContactXMLFactory();

    public ContactIterator(Iterator<Contact> iter) {
        super(iter);
    }

    @Override
    public Document next() {
        xml = null;

        try {
            xml = factory.newInstance(iter.next());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
