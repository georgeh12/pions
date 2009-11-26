
package pions.controller.xml;

import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import pions.model.Contacts.Contact;

/**
 *
 * 
 */
public final class ContactIterator extends XMLIterator<Contact> {

    public ContactIterator(Iterator<Contact> iter) {
        super(iter);
    }

    @Override
    public Document next() {
        xml = null;

        try {
            xml = AbstractXMLFactory.newInstance(iter.next());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
