
package pions.controller;

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
    public static final String EXTENSIONS = "EXTENSIONS";
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

            Element entry = xml.createElement(CALENDAR);
            xml.appendChild(entry);

            CalendarEntry current = iter.next();

            // Set title
            entry.setAttribute(TITLE, current.getTitle().getPlainText());

            entry.setAttribute(TEXT, current.getPlainTextContent());

            entry.appendChild(xml.createElement(EXTENSIONS));

            int count = 0;
            Iterator<Extension> extensions = current.getExtensions().iterator();
            while(extensions.hasNext()){
                entry.setAttribute(EXTENSION + count, extensions.next().toString());
                count++;
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
