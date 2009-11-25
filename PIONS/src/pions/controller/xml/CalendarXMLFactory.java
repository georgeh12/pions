
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
 * @author George
 */
public final class CalendarXMLFactory extends XMLFactory<CalendarEntry> {
    public static final String CALENDAR = "CALENDAR";
    public static final String TITLE = "TITLE";
    public static final String TEXT = "TEXT";
    public static final String EXTENSION = "EXTENSION";

    public Document newInstance(CalendarEntry current) throws ParserConfigurationException{
        xml = null;

        //create a new document
        xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

        Element root = getHead(CALENDAR);

        // Set title
        setAttribute(root, TITLE, current.getTitle().getPlainText());

        setAttribute(root, TEXT, current.getPlainTextContent());

        Iterator<Extension> extensions = current.getExtensions().iterator();
        while(extensions.hasNext()){
            setAttribute(root, EXTENSION, extensions.next().toString());
        }

        return null;
    }
}
