
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
public class CalendarIterator extends XMLIterator<CalendarEntry> {

    public static final String CALENDAR = "CALENDAR";
    public static final String TITLE = "TITLE";
    public static final String TEXT = "TEXT";
    public static final String EXTENSION = "EXTENSION";

    CalendarIterator(Iterator<CalendarEntry> iter){
        super(iter);
    }

    @Override
    public Document next() {
        xml = null;

        try {
            //create a new document
            xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

            Element root = xml.createElement(CALENDAR);
            Element element;
            xml.appendChild(root);

            CalendarEntry current = iter.next();

            // Set title
            element = xml.createElement(TITLE);
            element.setNodeValue(current.getTitle().getPlainText());
            root.appendChild(element);

            element = xml.createElement(TEXT);
            element.setNodeValue(current.getPlainTextContent());
            root.appendChild(element);

            Iterator<Extension> extensions = current.getExtensions().iterator();
            while(extensions.hasNext()){
                element = xml.createElement(EXTENSION);
                element.setNodeValue(extensions.next().toString());
                root.appendChild(element);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
