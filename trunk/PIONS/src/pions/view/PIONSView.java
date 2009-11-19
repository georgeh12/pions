
package pions.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jdesktop.application.Application.ExitListener;
import pions.PIONS;
import pions.controller.Alerts;
import pions.controller.Contacts;
import pions.controller.Employees;

/**
 * Implements the Observer and Singleton design pattern
 * @author George
 */
public class PIONSView {
    private static PIONSView instance = new PIONSView();
    private JPanel idle = new IdleScreen();

    private JMenu file = new JMenu("File");
    private JMenu alerts = new JMenu("Alert");
    private JMenu window = new JMenu("Window");
    private JMenu help = new JMenu("Help");

    private JMenuItem menu_login;
    private JMenuItem menu_quit;
    private JMenuItem menu_contacts;
    private JMenuItem menu_updatealerts;
    private JMenuItem menu_activealerts;
    private JMenuItem menu_savedalerts;
    private JMenuItem menu_managers;
    private JMenuItem menu_subordinates;
    private JMenuItem menu_calendars;
    private JMenuItem menu_aboutus;

    private PIONSView(){
        JFrame main_frame = getFrame();

        main_frame.setPreferredSize(new Dimension(600,400));
        main_frame.getContentPane().setLayout(new GridLayout(1,2));

        //TODO implement PIONSPanel interface to give panels a window closing function.
        PIONS.getApplication().addExitListener(new ExitListener(){
            public boolean canExit(EventObject evt) {
                return JOptionPane.YES_OPTION ==
                        JOptionPane.showConfirmDialog(getFrame(),
                        "Are you sure you want to exit?",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION);
            }
            public void willExit(EventObject evt) {
                if(Employees.isLoggedIn()) Employees.logout();
            }
        });

        initMenu();

        //setPanel1(new Login());
        setPanel1(new Login());
        setPanel2(new IdleScreen());

        PIONS.getApplication().show(main_frame);
    }

    /**
     * Initializes the menu bar and adds an ActionListener to each menu item.
     */
    private void initMenu(){
        JMenuBar menu = new JMenuBar();

        //File Menu

        menu_login = file.add(new JMenuItem("Login"));
        menu_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_loginActionPerformed(evt);
            }
        });

        menu_quit = file.add(new JMenuItem("Quit"));
        menu_quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_quitActionPerformed(evt);
            }
        });

        //Alert Menu

        menu_updatealerts = alerts.add(new JMenuItem("Update Alerts"));
        menu_updatealerts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_updatealertsActionPerformed(evt);
            }
        });

        menu_activealerts = alerts.add(new JMenuItem("Active Alerts"));
        menu_activealerts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_activealertsActionPerformed(evt);
            }
        });

        menu_savedalerts = alerts.add(new JMenuItem("Saved Alerts"));
        menu_savedalerts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_savedalertsActionPerformed(evt);
            }
        });

        //Window Menu

        menu_contacts = window.add(new JMenuItem("Contacts"));
        menu_contacts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_contactsActionPerformed(evt);
            }
        });

        menu_managers = window.add(new JMenuItem("Managers"));
        menu_managers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_managersActionPerformed(evt);
            }
        });

        menu_subordinates = window.add(new JMenuItem("Subordinates"));
        menu_subordinates.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_subordinatesActionPerformed(evt);
            }
        });

        menu_calendars = window.add(new JMenuItem("Calendars"));
        menu_calendars.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_calendarsActionPerformed(evt);
            }
        });

        //Help Menu

        menu_aboutus = help.add(new JMenuItem("About Us"));
        menu_aboutus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_aboutusActionPerformed(evt);
            }
        });
        
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

    public void setMain(JPanel panel){
        setPanel1(panel);
        setPanel2(idle);
    }

    public void resetAux(){
        setPanel2(idle);
    }

    public void setAux(JPanel panel){
        setPanel2(panel);
    }

    private void setPanel1(JPanel panel){
        JFrame main_frame = getFrame();

        if(main_frame.getContentPane().getComponentCount() > 0)main_frame.getContentPane().remove(0);
        main_frame.getContentPane().add(panel, 0);

        main_frame.validate();
        main_frame.repaint();
        main_frame.transferFocus();
    }

    private void setPanel2(JPanel panel){
        JFrame main_frame = getFrame();

        if(main_frame.getContentPane().getComponentCount() > 1)main_frame.getContentPane().remove(1);
        main_frame.getContentPane().add(panel, 1);

        main_frame.validate();
        main_frame.repaint();
        main_frame.transferFocus();
    }

    private void menu_loginActionPerformed(ActionEvent evt){
        setMain((Employees.isLoggedIn() ? new LoginSuccess() : new Login()));
    }

    private void menu_quitActionPerformed(ActionEvent evt){
        PIONS.getApplication().exit(evt);
    }
    
    private void menu_updatealertsActionPerformed(ActionEvent evt){
        setMain(new UpdateAlerts());
    }

    private void menu_activealertsActionPerformed(ActionEvent evt){
        setMain(new ActiveAlerts(Alerts.getActiveAlertIterator()));
    }

    private void menu_savedalertsActionPerformed(ActionEvent evt){
        setMain(new SavedAlerts(Alerts.getSavedAlertIterator()));
    }

    private void menu_contactsActionPerformed(ActionEvent evt){
        setMain(new ContactList(Contacts.getContactIterator()));
    }

    private void menu_managersActionPerformed(ActionEvent evt){
        setMain(new Managers(Employees.getManagers()));
    }

    private void menu_subordinatesActionPerformed(ActionEvent evt){
        setMain(new Subordinates(Employees.getSubordinates()));
    }

    private void menu_calendarsActionPerformed(ActionEvent evt){
        setMain(new Calendars());
    }

    private void menu_aboutusActionPerformed(ActionEvent evt){
        setMain(new AboutUs());
    }
}
