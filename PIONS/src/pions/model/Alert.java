
package pions.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Observable;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.swapshift.SwapShift;

/**
 * This class acts as a Mediator between the model.Gmail class and all the
 * Objects which can be stored in an Alert.
 * @author George
 */
public class Alert extends Observable implements Serializable {
    private EmailAddress sender = null;
    private AlertType type = null;
    private Object object = null;

    public Alert(EmailAddress sender, Object object, AlertType type)
            throws AlertClassException{
        if(type.getAssociatedClass() != object.getClass()){
            throw new AlertClassException(type.getAssociatedClass(), object.getClass());
        }

        this.sender = sender;
        this.object = object;
        this.type = type;
    }

    public Alert(Object object, AlertType type) throws AlertClassException, NotLoggedInException{
        this(EmployeeSingleton.getInstance().getGmail().getGmailAddress(),
                object, type);
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

    public EmailAddress getAddress(){
        return sender.clone();
    }

    public AlertType getType(){
        return type;
    }

    @Override
    public String toString(){
        return type.toString() + ":\n" + object.toString();
    }

    //TODO add an alert to transfer an EmployeeSingleton's data file
    public enum AlertType{
        AddManager, AddSubordinate, NewWorkSchedule, UpdatedWorkSchedule,
        SwapShift;

        public Class getAssociatedClass(){
            switch(this){
                case AddManager:
                case AddSubordinate:
                    return Employee.class.getClass();
                case NewWorkSchedule:
                case UpdatedWorkSchedule:
                    return CalendarData.class.getClass();
                case SwapShift:
                    return SwapShift.class.getClass();
                default:
                    return null;
            }
        }

        public static AlertType parse(String type){
            if(type.equals(AddManager.toString())){
                return AddManager;
            }
            else if(type.equals(AddSubordinate.toString())){
                return AddSubordinate;
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
                case AddManager:
                    break;
                case AddSubordinate:
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
