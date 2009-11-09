
package pions.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author George
 */
public class Positions implements Serializable {
    private ArrayList<Position> positions = new ArrayList<Position>();

    public Position add(String title, boolean hourly, double rate){
        Position position = new Position(title, hourly, rate);
        positions.add(position);
        return position;
    }

    public void set(int index, String title, boolean hourly, double rate){
        positions.get(index).set(title, hourly, rate);
    }

    public Position remove(int index){
        return positions.remove(index);
    }

    public Iterator<Position> iterator(){
        return positions.iterator();
    }

    public static class Position implements Serializable {
        public enum Type{
            Hourly, Yearly;
        }
        private String title;
        private Type type;
        private double rate;

        private Position(String title, boolean hourly, double rate){
            set(title, hourly, rate);
        }

        public void set(String title, boolean hourly, double rate){
            this.title = title;
            //If hourly is true, Pay Type is set to Hourly.
            type = (hourly ? Type.Hourly : Type.Yearly);
            this.rate = rate;
        }

        @Override
        public String toString(){
            return title;
        }
    }
}
