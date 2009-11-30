
package pions.controller.xml;

import com.google.gdata.data.DateTime;
import com.google.gdata.data.extensions.EventEntry;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class may not correctly parse xml from google in future releases,
 * because
 * because the functions that google provided did not work.
 * 
 */
public final class CalendarXMLFactory extends XMLFactory<EventEntry> {
    protected Document newInstance(EventEntry current) throws ParserConfigurationException{
        xml = null;

        //create a new document
        xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

        Element root = setHead(CALENDAR);

        // Set title
        setAttribute(root, TITLE, current.getTitle().getPlainText());

        setAttribute(root, TEXT, current.getPlainTextContent());
        
        //This last part is messy because gdata refused to parse the times
        String xml_blob = current.getXmlBlob().getBlob();
        String lookup = "";
        int index = xml_blob.indexOf(lookup = "<gd:when") + lookup.length();
        int start_index1 = xml_blob.indexOf(lookup = "startTime='", index) + lookup.length();
        int start_index2 = xml_blob.indexOf(lookup = "'", start_index1);
        int end_index1 = xml_blob.indexOf(lookup = "endTime='", start_index2) + lookup.length();
        int end_index2 = xml_blob.indexOf(lookup = "'", end_index1);
        setAttribute(root, START_TIME, DateTime.parseDateTime(xml_blob.substring(start_index1, start_index2)).toUiString());
        setAttribute(root, END_TIME, DateTime.parseDateTime(xml_blob.substring(end_index1, end_index2)).toUiString());

        return xml;
    }
}
