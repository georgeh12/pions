/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pions.model;

import java.io.Serializable;

/**
 *
 * @author George
 */
class Position implements Serializable {
    private String title;
    private Pay pay;

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
