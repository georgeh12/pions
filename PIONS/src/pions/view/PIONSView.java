
package pions.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pions.PIONS;

/**
 * Implements the Observer and Singleton design pattern
 * @author George
 */
public class PIONSView implements Observer {
    private static PIONSView instance = new PIONSView();
    private JPanel panel1 = null;
    private JPanel panel2 = null;

    private PIONSView(){
        JFrame main_frame = getFrame();

        main_frame.setPreferredSize(new Dimension(600,400));
        main_frame.getContentPane().setLayout(new GridLayout(1,2));
        main_frame.addWindowListener(new PIONSWindowListener());

        //setPanel1(new Login());
        setPanel1(new EmployeeDetails());
        setPanel2(new IdleScreen());

        PIONS.getApplication().show(main_frame);
    }

    public static PIONSView getInstance(){
        return instance;
    }

    public JFrame getFrame(){
        return PIONS.getApplication().getMainFrame();
    }

    public void setOtherPanel(JPanel this_panel, JPanel other_panel){
        if(panel1 == this_panel){
            setPanel2(other_panel);
        }
        else if(panel2 == this_panel){
            setPanel1(other_panel);
        }
    }

    public void setThisPanel(JPanel this_panel, JPanel other_panel){
        if(panel1 == this_panel){
            setPanel1(other_panel);
        }
        else if(panel2 == this_panel){
            setPanel2(other_panel);
        }
    }

    public void setPanel1(JPanel panel){
        JFrame main_frame = getFrame();

        if(main_frame.getContentPane().getComponentCount() > 0)main_frame.getContentPane().remove(0);
        main_frame.getContentPane().add(panel, 0);
        panel1 = panel;

        main_frame.validate();
        main_frame.transferFocus();
    }

    public void setPanel2(JPanel panel){
        JFrame main_frame = getFrame();

        if(main_frame.getContentPane().getComponentCount() > 1)main_frame.getContentPane().remove(1);
        main_frame.getContentPane().add(panel, 1);
        panel2 = panel;

        main_frame.validate();
        main_frame.transferFocus();
    }

    /**
     * Implements the observer design pattern. Shows a message to the user
     * when it is notified by a class it is observing.
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        JOptionPane.showMessageDialog(getFrame(), arg, "System Message", JOptionPane.INFORMATION_MESSAGE);
    }

    private class PIONSWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e){
            //TODO implement PIONSPanel (another todo) interface to give panels a window closing function.
        }
    }
}
