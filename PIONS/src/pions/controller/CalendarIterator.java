
package pions.controller;

import com.google.gdata.data.calendar.CalendarEventEntry;
import java.util.Iterator;
import org.w3c.dom.Document;

/**
 *
 * @author George
 */
public class CalendarIterator extends XMLIterator {
    CalendarIterator(Iterator<CalendarEventEntry> iter){
        super(iter);
    }

    @Override
    public Document next() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
