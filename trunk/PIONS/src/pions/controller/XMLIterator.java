
package pions.controller;

import java.util.Iterator;
import org.w3c.dom.Document;

/**
 *
 * @author George
 */
public abstract class XMLIterator<T> implements Iterator {
    protected Document xml;
    protected Iterator<T> iter;

    public XMLIterator(Iterator<T> iter) {
        this.iter = iter;
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public abstract Document next();

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
