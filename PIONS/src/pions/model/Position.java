
package pions.model;

import java.io.Serializable;

/**
 *
 * @author George
 */
class Position implements Serializable {
    private String title;
    private Pay pay;

    Position(String title){
        this.title = title;
    }

    public static class Pay{
        public enum Type{
            Hourly, Yearly;
        }

        private Type type;
        private Double rate;

        protected Pay(Type type, Double rate){
            this.type = type;
            this.rate = rate;
        }
    }
}
