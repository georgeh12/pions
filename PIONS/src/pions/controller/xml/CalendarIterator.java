
package pions.controller.xml;

import com.google.gdata.data.calendar.CalendarEntry;
import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

/**
 *
 * 
 */
public final class CalendarIterator extends XMLIterator<CalendarEntry> {
    private CalendarXMLFactory factory = new CalendarXMLFactory();

    public CalendarIterator(Iterator<CalendarEntry> iter){
        super(iter);
    }

    @Override
    public Document next() {
        xml = null;

        try {
            return factory.newInstance(iter.next());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
