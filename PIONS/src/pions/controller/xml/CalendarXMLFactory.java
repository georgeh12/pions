
package pions.controller.xml;

import com.google.gdata.data.extensions.EventEntry;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pions.model.Calendar;

/**
 *
 * 
 */
public final class CalendarXMLFactory extends XMLFactory<EventEntry> {
    protected Document newInstance(EventEntry current) throws ParserConfigurationException{
        xml = null;

        //create a new document
        xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

        Element root = setHead(CALENDAR);

        // Set title
        setAttribute(root, TITLE, Calendar.parseTitle(current));

        setAttribute(root, TEXT, Calendar.parseText(current));
        
        setAttribute(root, START_TIME, Calendar.parseStartTime(current));
        setAttribute(root, END_TIME, Calendar.parseEndTime(current));

        return xml;
    }
}
