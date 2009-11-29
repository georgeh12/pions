
package pions.controller.xml;

import com.google.gdata.data.Extension;
import com.google.gdata.data.calendar.CalendarEntry;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * 
 */
public final class CalendarXMLFactory extends XMLFactory<CalendarEntry> {
    protected Document newInstance(CalendarEntry current) throws ParserConfigurationException{
        xml = null;

        //create a new document
        xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

        Element root = setHead(CALENDAR);

        // Set title
        setAttribute(root, TITLE, current.getTitle().getPlainText());

        setAttribute(root, TEXT, current.getPlainTextContent());

        Iterator<Extension> extensions = current.getExtensions().iterator();
        while(extensions.hasNext()){
            setAttribute(addNode(root, EXTENSION), EXTENSION_DEFAULT, extensions.next().toString());
        }

        return null;
    }
}
