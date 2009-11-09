
package pions.controller;

import java.util.Iterator;
import org.w3c.dom.Document;

/**
 * Abstract Iterator design implementation.
 * @author George
 */
public class XMLIterator implements Iterator {
    protected Document xml = null;
    protected Iterator<?> iter;

    public XMLIterator(Iterator<?> iter){
        this.iter = iter;
    }
    
    public boolean hasNext() {
        return iter.hasNext();
    }

    public Document next() {
        return xml;
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
