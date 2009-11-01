
package pions.model.alerts;

import java.io.IOException;
import java.io.Serializable;
import java.util.Observable;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public abstract class Alert extends Observable implements Serializable {
    private AlertType type = null;
    private Object object = null;

    protected Alert(AlertType type){
        this.type = type;
    }

    public void set(Object object){
        this.object = object;
    }

    public Object get(){
        return object;
    }

    /**
     * Subclasses should implement their own method for serialization,
     * if necessary.
     * @return
     * @throws pions.model.ModelException.NotLoggedInException
     * @throws IOException
     */
    public byte[] getBytes() throws NotLoggedInException, IOException {
        return EmployeeSingleton.getInstance().encryptRSA(object);
    }

    public AlertType getType(){
        return type;
    }

    @Override
    public String toString(){
        return type.toString();
    }

    //TODO add an alert to transfer an EmployeeSingleton's data file
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

        //TODO add description
        @Override
        public String toString(){
            switch(this){
                case AddSubordinate:
                    break;
                case AddManager:
                    break;
                case NewWorkSchedule:
                    break;
                case UpdatedWorkSchedule:
                    break;
                case SwapShift:
                    break;
                default:
            }

            return this.name();
        }
    }
}
