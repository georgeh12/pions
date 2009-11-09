
package pions.controller;

import java.util.ArrayList;
import java.util.Iterator;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;
import pions.model.Positions.Position;

/**
 *
 * @author George
 */
public class Positions {
    public static void addPosition(String title, boolean hourly, double rate){
        try {
            EmployeeSingleton.getInstance().getPositions().add(title, hourly, rate);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static void setPosition(int index, String title, boolean hourly, double rate){
        try {
            EmployeeSingleton.getInstance().getPositions().set(index, title, hourly, rate);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static void removePosition(int index){
        try {
            EmployeeSingleton.getInstance().getPositions().remove(index);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getPositions(){
        ArrayList<String> positions = new ArrayList<String>();

        try {
            Iterator<Position> iter = EmployeeSingleton.getInstance().getPositions().iterator();
            while (iter.hasNext()) {
                positions.add(iter.next().toString());
            }
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            return positions;
        }
    }
}
