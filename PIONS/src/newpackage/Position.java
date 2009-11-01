
package newpackage;

import java.io.Serializable;

/**
 *
 * @author George
 */
public class Position implements Serializable {
    private String title;
    private Pay pay;

    protected Position(String title, boolean hourly, Double rate){
        setPosition(title);
    }

    protected void setPosition(String title){
        this.title = title;
    }

    protected void setPay(boolean hourly, Double rate){
        //If hourly is true, Pay Type is set to Hourly.
        pay = new Pay((hourly ? Pay.Type.Hourly : Pay.Type.Yearly), rate);
    }

    @Override
    protected Position clone(){
        return new Position(this.title,
                (this.pay.type == Pay.Type.Hourly ? true : false),
                this.pay.rate);
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
