
package pions.view;

import java.awt.Component;
import java.awt.Desktop;
import pions.view.contactinfo.MyContactInfo;
import pions.view.contactinfo.ContactIList;
import pions.view.login.Login;
import pions.view.login.LoginSuccess;
import pions.view.employees.SubordinatesEList;
import pions.view.calendars.DisplayCalendar;
import pions.view.alerts.SavedAlertIList;
import pions.view.alerts.UpdateAlerts;
import pions.view.alerts.ActiveAlertsIList;
import pions.view.aboutus.AboutUs;
import pions.view.aboutus.AboutUsAux;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EventObject;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jdesktop.application.Application.ExitListener;
import pions.PIONS;
import pions.controller.Employees;
import pions.view.employees.DisplayEmployee;

/**
 * Implements the Singleton design pattern.
 * 
 */
public class PIONSView {
    private static PIONSView instance = new PIONSView();
    private JPanel idle = new IdleScreen();
    private transient volatile JFrame contact_list = null;

    private JMenu file = new JMenu("File");
    private JMenu contact = new JMenu("Contact");
    private JMenu alerts = new JMenu("Alert");
    private JMenu window = new JMenu("Window");
    private JMenu help = new JMenu("Help");

    private JMenuItem menu_login;
    private JMenuItem menu_quit;
    private JMenuItem menu_contacts;
    private JMenuItem menu_contactinfo;
    private JMenuItem menu_updatealerts;
    private JMenuItem menu_activealerts;
    private JMenuItem menu_savedalerts;
    private JMenuItem menu_manager;
    private JMenuItem menu_subordinates;
    private JMenuItem menu_calendars;
    private JMenuItem menu_aboutus;

    private PIONSView(){
        JFrame main_frame = getFrame();

        initMenu();
        setMenuVisible(false);

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

        PIONS.getApplication().show(main_frame);

        // Somehow this sets the content pane height to 400
        main_frame.setSize(600, 400 + main_frame.getHeight() - main_frame.getContentPane().getHeight());
        main_frame.getContentPane().setLayout(new GridLayout(1,2));
        main_frame.setResizable(false);

        setPanel1(new Login());
        setPanel2(new IdleScreen());
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

        //Contact Menu

        menu_contacts = contact.add(new JMenuItem("Contacts"));
        menu_contacts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_contactsActionPerformed(evt);
            }
        });

        //ContactInfo Menu

        menu_contactinfo = contact.add(new JMenuItem("Contact Info"));
        menu_contactinfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_contactinfoActionPerformed(evt);
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

        menu_calendars = window.add(new JMenuItem("Calendars"));
        menu_calendars.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_calendarsActionPerformed(evt);
            }
        });

        menu_manager = window.add(new JMenuItem("Manager"));
        menu_manager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_managerActionPerformed(evt);
            }
        });

        menu_subordinates = window.add(new JMenuItem("Subordinates"));
        menu_subordinates.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menu_subordinatesActionPerformed(evt);
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
        menu.add(contact);
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

    public void fullScreen(){
        JFrame main_frame = getFrame();

        main_frame.getContentPane().remove(1);

        main_frame.validate();
        main_frame.repaint();
        main_frame.transferFocus();
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

    public void setMenuVisible(boolean visible){
        contact.setVisible(visible);
        alerts.setVisible(visible);
        window.setVisible(visible);
    }

    public ContactIList getContactList(){
        return (ContactIList)getContactFrame().getContentPane();
    }

    public void setContactsVisible(boolean visible){
        getContactFrame().setVisible(visible);
    }

    /**
     * Uses double-check locking to allow for multiple threads. Seems like
     * overkill for a single frame desktop application.
     * @return
     */
    private JFrame getContactFrame(){
        if(contact_list == null){
            synchronized(JFrame.class){
                contact_list = new JFrame("Contacts"){
                    @Override
                    public void setVisible(boolean visible){
                        if(visible){
                            contact_list.setBounds(getFrame().getX() + getFrame().getWidth(),
                                    getFrame().getY(),
                                    300, 400 + getFrame().getHeight() - getFrame().getContentPane().getHeight());
                        }
                        super.setVisible(visible);
                    }
                };
                
                contact_list.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                contact_list.setContentPane(new ContactIList());
            }
        }
        
        return contact_list;
    }

    public static void openLink(Component parentComponent, String link){
        if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(
                parentComponent,
                "This operation will open a new internet browser window.",
                "Continue?",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE)){

            try{
                Desktop.getDesktop().browse(new URI(link));
            } catch (URISyntaxException e) {
                JOptionPane.showConfirmDialog(
                        parentComponent,
                        "Link provided is invalid.",
                        "Error Opening Link",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(
                        parentComponent,
                        "Unable to open link as requested.",
                        "Error Opening Link",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException e) {
                JOptionPane.showConfirmDialog(
                        parentComponent,
                        "The link does not exist.",
                        "Error Opening Link",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void menu_loginActionPerformed(ActionEvent evt){
        setMain((Employees.isLoggedIn() ? new LoginSuccess() : new Login()));
    }

    private void menu_quitActionPerformed(ActionEvent evt){
        PIONS.getApplication().exit(evt);
    }

    /**
     * Sets the contact frame visible if it is hidden, or hidden if it is visible.
     * @param evt
     */
    private void menu_contactsActionPerformed(ActionEvent evt){
        getContactFrame().setVisible(true);
    }

    private void menu_contactinfoActionPerformed(ActionEvent evt){
        setMain(new MyContactInfo());
        fullScreen();
    }
    
    private void menu_updatealertsActionPerformed(ActionEvent evt){
        setMain(new UpdateAlerts());
    }

    private void menu_activealertsActionPerformed(ActionEvent evt){
        setMain(new ActiveAlertsIList());
    }

    private void menu_savedalertsActionPerformed(ActionEvent evt){
        setMain(new SavedAlertIList());
    }

    private void menu_managerActionPerformed(ActionEvent evt){
        setMain(new DisplayEmployee(Employees.getManagerXML()));
    }

    private void menu_subordinatesActionPerformed(ActionEvent evt){
        setMain(new SubordinatesEList());
    }

    private void menu_calendarsActionPerformed(ActionEvent evt){
        setMain(new DisplayCalendar());
    }

    private void menu_aboutusActionPerformed(ActionEvent evt){
        setMain(new AboutUs());
        setAux(new AboutUsAux());
    }
}
