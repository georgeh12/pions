
package pions.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pions.PIONS;
import pions.controller.Employees;

/**
 * Implements the Observer and Singleton design pattern
 * @author George
 */
public class PIONSView implements Observer {
    private static PIONSView instance = new PIONSView();
    private JPanel panel1 = null;
    private JPanel panel2 = null;

    //TODO implement listener classes for menu options
    private JMenuItem menu_quit;
    private JMenuItem menu_login;
    private JMenuItem menu_managers;
    private JMenuItem menu_subordinates;
    private JMenuItem menu_calendars;
    private JMenuItem menu_checkalerts;
    private JMenuItem menu_activealerts;
    private JMenuItem menu_savedalerts;
    private JMenuItem menu_aboutus;

    private PIONSView(){
        JFrame main_frame = getFrame();

        main_frame.setPreferredSize(new Dimension(600,400));
        main_frame.getContentPane().setLayout(new GridLayout(1,2));
        main_frame.addWindowListener(new PIONSWindowListener());

        initMenu();

        //setPanel1(new Login());
        setPanel1(new Login());
        setPanel2(new IdleScreen());

        PIONS.getApplication().show(main_frame);
    }

    private void initMenu(){
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        menu_quit = file.add(new JMenuItem("Quit"));
        JMenu alerts = new JMenu("Alert");
        menu_checkalerts = alerts.add(new JMenuItem("Check For New Alerts"));
        menu_activealerts = alerts.add(new JMenuItem("Active Alerts"));
        menu_savedalerts = alerts.add(new JMenuItem("Saved Alerts"));
        JMenu window = new JMenu("Window");
        menu_login = window.add(new JMenuItem("Login"));
        menu_managers = window.add(new JMenuItem("Managers"));
        menu_subordinates = window.add(new JMenuItem("Subordinates"));
        menu_calendars = window.add(new JMenuItem("Calendars"));
        JMenu help = new JMenu("Help");
        menu_aboutus = help.add(new JMenuItem("About Us"));
        menu.add(file);
        menu.add(alerts);
        menu.add(window);
        menu.add(help);
        getFrame().setJMenuBar(menu);
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

    private void setPanel1(JPanel panel){
        JFrame main_frame = getFrame();

        if(main_frame.getContentPane().getComponentCount() > 0)main_frame.getContentPane().remove(0);
        main_frame.getContentPane().add(panel, 0);
        panel1 = panel;

        main_frame.validate();
        main_frame.repaint();
        main_frame.transferFocus();
    }

    private void setPanel2(JPanel panel){
        JFrame main_frame = getFrame();

        if(main_frame.getContentPane().getComponentCount() > 1)main_frame.getContentPane().remove(1);
        main_frame.getContentPane().add(panel, 1);
        panel2 = panel;

        main_frame.validate();
        main_frame.repaint();
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

    //TODO implement PIONSPanel interface to give panels a window closing function.
    /**
     * This listener will logout the employee before the window closes.
     */
    private class PIONSWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e){
            if(Employees.isLoggedIn()) Employees.logout();
        }
    }
}
