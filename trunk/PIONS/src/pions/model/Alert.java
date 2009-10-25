
package pions.model;

import java.io.Serializable;

/**
 *
 * @author George
 */
public abstract class Alert implements Serializable {
    AlertType type = null;

    public void set(Object object) { }

    public void set(AlertType type){
        this.type = type;
    }

    public abstract Object get();

    public enum AlertType{
        AddSubordinate, AddManager, NewWorkSchedule, UpdatedWorkSchedule,
        SwapShift;

        public static AlertType parse(String type){
            if(type.equals(AddSubordinate.toString())){
                return AddSubordinate;
            }
            else if(type.equals(AddManager.toString())){
                return AddManager;
            }
            else if(type.equals(NewWorkSchedule.toString())){
                return NewWorkSchedule;
            }
            else if(type.equals(UpdatedWorkSchedule.toString())){
                return UpdatedWorkSchedule;
            }
            else if(type.equals(SwapShift.toString())){
                return SwapShift;
            }
            else{
                return null;
            }
        }
    }
}
