
package pions.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * 
 */
public class Positions implements Serializable {
    private ArrayList<Position> positions = new ArrayList<Position>();

    public void set(ArrayList<Position> positions){
        this.positions = positions;
    }

    public Iterator<Position> iterator(){
        return positions.iterator();
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer("Position(s): ");
        Iterator<Position> iter = iterator();

        if(iter.hasNext()){
            buffer.append(iter.next().toString());
        }
        else{
            buffer.append("none");
        }
        
        while(iter.hasNext()){
            buffer.append(", " + iter.next().toString());
        }

        return buffer.toString();
    }

    public static class Position implements Serializable {
        public enum PayType{
            Salary, Hourly;
        }
        private String title;
        private PayType type;
        private double rate;

        public Position(String title, PayType type, double rate){
            set(title, type, rate);
        }

        private void set(String title, PayType type, double rate){
            this.title = title;
            this.type = type;
            this.rate = rate;
        }

        public String getTitle(){
            return title;
        }

        public PayType getPayType(){
            return type;
        }

        public double getRate(){
            return rate;
        }

        @Override
        public String toString(){
            return title;
        }
    }
}
