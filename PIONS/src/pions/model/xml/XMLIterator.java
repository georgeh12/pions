
package pions.model.xml;

import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

/**
 *
 * 
 */
public class XMLIterator<T> implements Iterator {
    protected Iterator<T> iter;
    protected Document xml;

    public XMLIterator(Iterator<T> iter) {
        this.iter = iter;
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public Document next(){
        xml = null;

        try {
            return AbstractXMLFactory.newInstance(iter.next());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return xml;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
