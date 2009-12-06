
package pions.view.contactinfo;

import javax.swing.JOptionPane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pions.controller.ContactInfo;
import pions.controller.xml.XMLFactory;

/**
 *
 * 
 */
public class MyContactInfo extends javax.swing.JPanel {

    private static final String SAVE_BUTTON = "Save";

    private Document xml = null;
    private Element head = null;

    /** Creates new form ContactInfo */
    public MyContactInfo() {
        initComponents();

        initXML();
    }

    private void initXML(){
        xml = ContactInfo.newInstance();
        head = XMLFactory.getHead(xml, XMLFactory.EMPLOYEE);

        initName();
        initPositions();
        initAddress();
        initPhoneNumbers();
        initEmailAddresses();
    }

    private void initName(){
        field_name.setText(XMLFactory.getAttribute(head, XMLFactory.NAME));
    }

    private void initPositions(){
        combobox_position.removeAllItems();
        NodeList position_nodes = XMLFactory.getElements(head, XMLFactory.POSITION);

        //Displays the position title only
        for(int i = 0; i < position_nodes.getLength(); i ++){
            combobox_position.addItem(XMLFactory.getAttribute(position_nodes.item(i), XMLFactory._TITLE));
        }
    }

    private void initAddress(){
        initStates();

        Node address = XMLFactory.getElements(head, XMLFactory.ADDRESS).item(XMLFactory.DEFAULT_INDEX);

        field_street.setText(XMLFactory.getAttribute(address, XMLFactory._STREET));
        field_city.setText(XMLFactory.getAttribute(address, XMLFactory._CITY));
        combobox_state.setSelectedItem(XMLFactory.getAttribute(address, XMLFactory._STATE));
        field_zip.setText(XMLFactory.getAttribute(address, XMLFactory._ZIP));
    }

    private void initStates(){
        for(String state: ContactInfo.getStates()){
            combobox_state.addItem(state);
        }
    }

    private void initPhoneNumbers(){
        combobox_phone.removeAllItems();
        NodeList phone_nodes = XMLFactory.getElements(head, XMLFactory.PHONE_NUMBER);

        //Displays the phone number and extension
        for(int i = 0; i < phone_nodes.getLength(); i ++){
            String extension = XMLFactory.getAttribute(phone_nodes.item(i), XMLFactory._EXTENSION);

            combobox_phone.addItem(
                    XMLFactory.getAttribute(phone_nodes.item(i), XMLFactory._PHONE_TYPE)
                    + ": "
                    + XMLFactory.getAttribute(phone_nodes.item(i), XMLFactory._NUMBER)
                    + (extension.isEmpty() ? "" : " x") + extension);
        }
    }

    private void initEmailAddresses(){
        combobox_email.removeAllItems();
        NodeList email_nodes = XMLFactory.getElements(head, XMLFactory.EMAIL_ADDRESS);

        //Displays the phone number and extension
        for(int i = 0; i < email_nodes.getLength(); i ++){
            combobox_email.addItem(
                    "<" + XMLFactory.getAttribute(email_nodes.item(i), XMLFactory._EMAIL_ADDRESS) + "> "
                    + XMLFactory.getAttribute(email_nodes.item(i), XMLFactory._PERSONAL));
        }
    }

    private void getPosition(int index){
        field_title.setText(XMLFactory.getAttribute(
                XMLFactory.getElements(head, XMLFactory.POSITION).item(index),
                XMLFactory._TITLE));

        String phone_type = XMLFactory.getAttribute(
                XMLFactory.getElements(head, XMLFactory.POSITION).item(index),
                XMLFactory._PAY_TYPE);
        if(phone_type.equals(radio_hourly.getText())){
            radio_hourly.setSelected(true);
        }
        else if(phone_type.equals(radio_salary.getText())){
            radio_salary.setSelected(true);
        }

        spinner_rate.getModel().setValue(Double.parseDouble(XMLFactory.getAttribute(
                XMLFactory.getElements(head, XMLFactory.POSITION).item(index),
                XMLFactory._RATE)));
    }

    private void getPhoneNumber(int index){
        String phone_type = XMLFactory.getAttribute(
                XMLFactory.getElements(head, XMLFactory.PHONE_NUMBER).item(index),
                XMLFactory._PHONE_TYPE);
        if(phone_type.equals(radio_home.getText())){
            radio_home.setSelected(true);
        }
        else if(phone_type.equals(radio_cell.getText())){
            radio_cell.setSelected(true);
        }
        else if(phone_type.equals(radio_work.getText())){
            radio_work.setSelected(true);
        }
        else if(phone_type.equals(radio_fax.getText())){
            radio_fax.setSelected(true);
        }

        field_number.setText(XMLFactory.getAttribute(
                XMLFactory.getElements(head, XMLFactory.PHONE_NUMBER).item(index),
                XMLFactory._NUMBER));
        field_extension.setText(XMLFactory.getAttribute(
                XMLFactory.getElements(head, XMLFactory.PHONE_NUMBER).item(index),
                XMLFactory._EXTENSION));
    }

    private void getEmailAddress(int index){
        field_email.setText(XMLFactory.getAttribute(
                XMLFactory.getElements(head, XMLFactory.EMAIL_ADDRESS).item(index),
                XMLFactory._EMAIL_ADDRESS));
        field_personal.setText(XMLFactory.getAttribute(
                XMLFactory.getElements(head, XMLFactory.EMAIL_ADDRESS).item(index),
                XMLFactory._PERSONAL));
    }

    private void clearPosition(){
        field_title.setText("");
        spinner_rate.getModel().setValue(0);
        radiogroup_position.clearSelection();
    }

    private void clearPhone(){
        radiogroup_phone.clearSelection();
        field_number.setText("");
        field_extension.setText("");
    }

    private void clearEmail(){
        field_email.setText("");
        field_personal.setText("");
    }

    private void setPositionEnabled(boolean enabled){
        combobox_position.setEnabled(enabled);
        button_position_delete.setEnabled(enabled);
        button_position.setEnabled(enabled);

        if(enabled){
            togglebutton_position.setText(org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(MyContactInfo.class).getString("togglebutton_position.text"));
        }
        else{
            togglebutton_position.setText(SAVE_BUTTON);
        }
    }

    private void setPhoneEnabled(boolean enabled){
        combobox_phone.setEnabled(enabled);
        button_phone_delete.setEnabled(enabled);
        button_phone.setEnabled(enabled);

        if(enabled){
            togglebutton_phone.setText(org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(MyContactInfo.class).getString("togglebutton_phone.text"));
        }
        else{
            togglebutton_phone.setText(SAVE_BUTTON);
        }
    }

    private void setEmailEnabled(boolean enabled){
        combobox_email.setEnabled(enabled);
        button_email_delete.setEnabled(enabled);
        button_email.setEnabled(enabled);

        if(enabled){
            togglebutton_email.setText(org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(MyContactInfo.class).getString("togglebutton_email.text"));
        }
        else{
            togglebutton_email.setText(SAVE_BUTTON);
        }
    }

    private void removeNode(int index, String node){
            XMLFactory.removeNode(head, XMLFactory.getElements(head, node).item(index));
    }

    private void showPositionError(){
        JOptionPane.showConfirmDialog(this,
                "Please select a position from the drop-down list.",
                "No Position Selected",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE);
    }

    private void showEmailError(){
        JOptionPane.showConfirmDialog(this,
                "Please select an e-mail address from the drop-down list.",
                "No E-mail Address Selected",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE);
    }

    private void showPhoneError(){
        JOptionPane.showConfirmDialog(this,
                "Please select a phone number from the drop-down list.",
                "No Phone Number Selected",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE);
    }

    private void setName(){
        XMLFactory.setAttribute(head, XMLFactory.NAME, field_name.getText());
    }

    private void setAddress(){
        Node address = XMLFactory.getElements(head, XMLFactory.ADDRESS).item(XMLFactory.DEFAULT_INDEX);

        XMLFactory.setAttribute(address, XMLFactory._STREET, field_street.getText());
        XMLFactory.setAttribute(address, XMLFactory._CITY, field_city.getText());
        XMLFactory.setAttribute(address, XMLFactory._STATE, combobox_state.getSelectedItem().toString());
        XMLFactory.setAttribute(address, XMLFactory._ZIP, field_zip.getText());
    }

    private String getPayType(){
        String pay_type = null;
        if(radio_salary.isSelected()){
            pay_type = radio_salary.getText();
        }
        else {
            //Default pay type
            pay_type = radio_hourly.getText();
        }

        return pay_type;
    }

    private void setPosition(int index){
        Node position = XMLFactory.getElements(head, XMLFactory.POSITION).item(index);

        XMLFactory.setAttribute(position, XMLFactory._TITLE, field_title.getText());
        XMLFactory.setAttribute(position, XMLFactory._PAY_TYPE, getPayType());
        XMLFactory.setAttribute(position, XMLFactory._RATE, spinner_rate.getValue().toString());
    }

    private String getPhoneType(){
        String phone_type = null;
        if(radio_cell.isSelected()){
            phone_type = radio_cell.getText();
        }
        else if(radio_work.isSelected()){
            phone_type = radio_work.getText();
        }
        else if(radio_fax.isSelected()){
            phone_type = radio_fax.getText();
        }
        else{
            //Default value of Home
            phone_type = radio_home.getText();
        }

        return phone_type;
    }

    private void setPhoneNumber(int index){
        Node phone_number = XMLFactory.getElements(head, XMLFactory.PHONE_NUMBER).item(index);

        XMLFactory.setAttribute(phone_number, XMLFactory._PHONE_TYPE, getPhoneType());
        XMLFactory.setAttribute(phone_number, XMLFactory._NUMBER, field_number.getText());
        XMLFactory.setAttribute(phone_number, XMLFactory._EXTENSION, field_extension.getText());
    }

    private void setEmailAddress(int index){
        Node email_address = XMLFactory.getElements(head, XMLFactory.EMAIL_ADDRESS).item(index);

        XMLFactory.setAttribute(email_address, XMLFactory._EMAIL_ADDRESS, field_email.getText());
        XMLFactory.setAttribute(email_address, XMLFactory._PERSONAL, field_personal.getText());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radiogroup_phone = new javax.swing.ButtonGroup();
        radiogroup_position = new javax.swing.ButtonGroup();
        label_title = new javax.swing.JLabel();
        label_firstdirections = new javax.swing.JLabel();
        label_name = new javax.swing.JLabel();
        label_street = new javax.swing.JLabel();
        label_address = new javax.swing.JLabel();
        label_city = new javax.swing.JLabel();
        label_zip = new javax.swing.JLabel();
        label_state = new javax.swing.JLabel();
        label_emailaddresses = new javax.swing.JLabel();
        label_phone = new javax.swing.JLabel();
        field_name = new javax.swing.JTextField();
        field_street = new javax.swing.JTextField();
        field_city = new javax.swing.JTextField();
        field_zip = new javax.swing.JTextField();
        combobox_state = new javax.swing.JComboBox();
        combobox_email = new javax.swing.JComboBox();
        combobox_phone = new javax.swing.JComboBox();
        togglebutton_phone = new javax.swing.JToggleButton();
        togglebutton_email = new javax.swing.JToggleButton();
        button_save = new javax.swing.JButton();
        button_revert = new javax.swing.JButton();
        radio_home = new javax.swing.JRadioButton();
        radio_cell = new javax.swing.JRadioButton();
        radio_work = new javax.swing.JRadioButton();
        radio_fax = new javax.swing.JRadioButton();
        field_number = new javax.swing.JTextField();
        label_number = new javax.swing.JLabel();
        label_ext = new javax.swing.JLabel();
        field_extension = new javax.swing.JTextField();
        button_phone = new javax.swing.JButton();
        field_email = new javax.swing.JTextField();
        label_email = new javax.swing.JLabel();
        button_email = new javax.swing.JButton();
        field_personal = new javax.swing.JTextField();
        label_personal = new javax.swing.JLabel();
        button_phone_delete = new javax.swing.JButton();
        button_email_delete = new javax.swing.JButton();
        label_positions = new javax.swing.JLabel();
        combobox_position = new javax.swing.JComboBox();
        button_position_delete = new javax.swing.JButton();
        button_position = new javax.swing.JButton();
        radio_hourly = new javax.swing.JRadioButton();
        radio_salary = new javax.swing.JRadioButton();
        label_pay = new javax.swing.JLabel();
        label_position_title = new javax.swing.JLabel();
        field_title = new javax.swing.JTextField();
        togglebutton_position = new javax.swing.JToggleButton();
        spinner_rate = new javax.swing.JSpinner();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 400));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(MyContactInfo.class);
        label_title.setFont(resourceMap.getFont("label_title.font")); // NOI18N
        label_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_title.setText(resourceMap.getString("label_title.text")); // NOI18N
        label_title.setName("label_title"); // NOI18N

        label_firstdirections.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_firstdirections.setText(resourceMap.getString("label_firstdirections.text")); // NOI18N
        label_firstdirections.setName("label_firstdirections"); // NOI18N

        label_name.setText(resourceMap.getString("label_name.text")); // NOI18N
        label_name.setName("label_name"); // NOI18N

        label_street.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_street.setText(resourceMap.getString("label_street.text")); // NOI18N
        label_street.setName("label_street"); // NOI18N

        label_address.setFont(resourceMap.getFont("label_address.font")); // NOI18N
        label_address.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_address.setText(resourceMap.getString("label_address.text")); // NOI18N
        label_address.setName("label_address"); // NOI18N

        label_city.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_city.setText(resourceMap.getString("label_city.text")); // NOI18N
        label_city.setName("label_city"); // NOI18N

        label_zip.setText(resourceMap.getString("label_zip.text")); // NOI18N
        label_zip.setName("label_zip"); // NOI18N

        label_state.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_state.setText(resourceMap.getString("label_state.text")); // NOI18N
        label_state.setName("label_state"); // NOI18N

        label_emailaddresses.setFont(resourceMap.getFont("label_emailaddresses.font")); // NOI18N
        label_emailaddresses.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_emailaddresses.setText(resourceMap.getString("label_emailaddresses.text")); // NOI18N
        label_emailaddresses.setName("label_emailaddresses"); // NOI18N

        label_phone.setFont(resourceMap.getFont("label_phone.font")); // NOI18N
        label_phone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_phone.setText(resourceMap.getString("label_phone.text")); // NOI18N
        label_phone.setName("label_phone"); // NOI18N

        field_name.setText(resourceMap.getString("field_name.text")); // NOI18N
        field_name.setName("field_name"); // NOI18N

        field_street.setText(resourceMap.getString("field_street.text")); // NOI18N
        field_street.setName("field_street"); // NOI18N

        field_city.setText(resourceMap.getString("field_city.text")); // NOI18N
        field_city.setName("field_city"); // NOI18N

        field_zip.setColumns(5);
        field_zip.setText(resourceMap.getString("field_zip.text")); // NOI18N
        field_zip.setName("field_zip"); // NOI18N

        combobox_state.setMaximumRowCount(5);
        combobox_state.setName("combobox_state"); // NOI18N

        combobox_email.setMaximumRowCount(5);
        combobox_email.setName("combobox_email"); // NOI18N

        combobox_phone.setMaximumRowCount(5);
        combobox_phone.setName("combobox_phone"); // NOI18N

        togglebutton_phone.setText(resourceMap.getString("togglebutton_phone.text")); // NOI18N
        togglebutton_phone.setName("togglebutton_phone"); // NOI18N
        togglebutton_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglebutton_phoneActionPerformed(evt);
            }
        });

        togglebutton_email.setText(resourceMap.getString("togglebutton_email.text")); // NOI18N
        togglebutton_email.setName("togglebutton_email"); // NOI18N
        togglebutton_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglebutton_emailActionPerformed(evt);
            }
        });

        button_save.setText(resourceMap.getString("button_save.text")); // NOI18N
        button_save.setName("button_save"); // NOI18N
        button_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_saveActionPerformed(evt);
            }
        });

        button_revert.setText(resourceMap.getString("button_revert.text")); // NOI18N
        button_revert.setName("button_revert"); // NOI18N
        button_revert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_revertActionPerformed(evt);
            }
        });

        radiogroup_phone.add(radio_home);
        radio_home.setText(resourceMap.getString("radio_home.text")); // NOI18N
        radio_home.setName("radio_home"); // NOI18N

        radiogroup_phone.add(radio_cell);
        radio_cell.setText(resourceMap.getString("radio_cell.text")); // NOI18N
        radio_cell.setName("radio_cell"); // NOI18N

        radiogroup_phone.add(radio_work);
        radio_work.setText(resourceMap.getString("radio_work.text")); // NOI18N
        radio_work.setName("radio_work"); // NOI18N

        radiogroup_phone.add(radio_fax);
        radio_fax.setText(resourceMap.getString("radio_fax.text")); // NOI18N
        radio_fax.setName("radio_fax"); // NOI18N

        field_number.setColumns(12);
        field_number.setText(resourceMap.getString("field_number.text")); // NOI18N
        field_number.setName("field_number"); // NOI18N

        label_number.setText(resourceMap.getString("label_number.text")); // NOI18N
        label_number.setName("label_number"); // NOI18N

        label_ext.setText(resourceMap.getString("label_ext.text")); // NOI18N
        label_ext.setName("label_ext"); // NOI18N

        field_extension.setColumns(5);
        field_extension.setText(resourceMap.getString("field_extension.text")); // NOI18N
        field_extension.setName("field_extension"); // NOI18N

        button_phone.setText(resourceMap.getString("button_phone.text")); // NOI18N
        button_phone.setName("button_phone"); // NOI18N
        button_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_phoneActionPerformed(evt);
            }
        });

        field_email.setText(resourceMap.getString("field_email.text")); // NOI18N
        field_email.setName("field_email"); // NOI18N

        label_email.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_email.setText(resourceMap.getString("label_email.text")); // NOI18N
        label_email.setName("label_email"); // NOI18N

        button_email.setText(resourceMap.getString("button_email.text")); // NOI18N
        button_email.setName("button_email"); // NOI18N
        button_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_emailActionPerformed(evt);
            }
        });

        field_personal.setText(resourceMap.getString("field_personal.text")); // NOI18N
        field_personal.setName("field_personal"); // NOI18N

        label_personal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_personal.setText(resourceMap.getString("label_personal.text")); // NOI18N
        label_personal.setName("label_personal"); // NOI18N

        button_phone_delete.setText(resourceMap.getString("button_phone_delete.text")); // NOI18N
        button_phone_delete.setName("button_phone_delete"); // NOI18N
        button_phone_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_phone_deleteActionPerformed(evt);
            }
        });

        button_email_delete.setText(resourceMap.getString("button_email_delete.text")); // NOI18N
        button_email_delete.setName("button_email_delete"); // NOI18N
        button_email_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_email_deleteActionPerformed(evt);
            }
        });

        label_positions.setFont(resourceMap.getFont("label_positions.font")); // NOI18N
        label_positions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_positions.setText(resourceMap.getString("label_positions.text")); // NOI18N
        label_positions.setName("label_positions"); // NOI18N

        combobox_position.setName("combobox_position"); // NOI18N

        button_position_delete.setText(resourceMap.getString("button_position_delete.text")); // NOI18N
        button_position_delete.setName("button_position_delete"); // NOI18N
        button_position_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_position_deleteActionPerformed(evt);
            }
        });

        button_position.setText(resourceMap.getString("button_position.text")); // NOI18N
        button_position.setName("button_position"); // NOI18N
        button_position.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_positionActionPerformed(evt);
            }
        });

        radiogroup_position.add(radio_hourly);
        radio_hourly.setText(resourceMap.getString("radio_hourly.text")); // NOI18N
        radio_hourly.setName("radio_hourly"); // NOI18N

        radiogroup_position.add(radio_salary);
        radio_salary.setText(resourceMap.getString("radio_salary.text")); // NOI18N
        radio_salary.setName("radio_salary"); // NOI18N

        label_pay.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_pay.setText(resourceMap.getString("label_pay.text")); // NOI18N
        label_pay.setName("label_pay"); // NOI18N

        label_position_title.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_position_title.setText(resourceMap.getString("label_position_title.text")); // NOI18N
        label_position_title.setName("label_position_title"); // NOI18N

        field_title.setText(resourceMap.getString("field_title.text")); // NOI18N
        field_title.setName("field_title"); // NOI18N

        togglebutton_position.setText(resourceMap.getString("togglebutton_position.text")); // NOI18N
        togglebutton_position.setName("togglebutton_position"); // NOI18N
        togglebutton_position.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglebutton_positionActionPerformed(evt);
            }
        });

        spinner_rate.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), Double.valueOf(0.0d), null, Double.valueOf(1.0d)));
        spinner_rate.setName("spinner_rate"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label_name)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_name, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(label_state)
                                    .addComponent(label_city, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label_street))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(field_city, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                    .addComponent(field_street, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(combobox_state, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(label_zip)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(field_zip, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(label_address, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label_position_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label_pay))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(field_title, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(togglebutton_position, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(spinner_rate, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radio_hourly)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radio_salary)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(button_position, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(combobox_position, 0, 217, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button_position_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label_positions, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label_phone, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label_number)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(field_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label_ext)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(field_extension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label_email, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label_personal))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(field_personal, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                            .addComponent(field_email, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
                                    .addComponent(combobox_email, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(radio_home)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(radio_cell)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radio_work)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radio_fax))
                                    .addComponent(combobox_phone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(button_email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(togglebutton_email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button_email_delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(togglebutton_phone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button_phone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button_phone_delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(label_emailaddresses, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(button_save, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(button_revert, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label_firstdirections, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_firstdirections)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_phone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combobox_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_phone_delete))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(togglebutton_phone)
                            .addComponent(radio_home)
                            .addComponent(radio_cell)
                            .addComponent(radio_work)
                            .addComponent(radio_fax))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button_phone)
                            .addComponent(label_number)
                            .addComponent(field_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_ext)
                            .addComponent(field_extension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(label_emailaddresses)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combobox_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_email_delete))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_email)
                            .addComponent(field_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(togglebutton_email))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(field_personal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_personal)
                            .addComponent(button_email)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_name)
                            .addComponent(field_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(label_positions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combobox_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_position_delete))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_position_title)
                            .addComponent(field_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(togglebutton_position))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_pay)
                            .addComponent(spinner_rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_position)
                            .addComponent(radio_salary)
                            .addComponent(radio_hourly))
                        .addGap(18, 18, 18)
                        .addComponent(label_address)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_street)
                            .addComponent(field_street, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_city)
                            .addComponent(field_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_state)
                            .addComponent(combobox_state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field_zip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_zip))))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_revert)
                    .addComponent(button_save))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_saveActionPerformed
        if(togglebutton_position.isSelected()
                || togglebutton_phone.isSelected()
                || togglebutton_email.isSelected()){
            JOptionPane.showConfirmDialog(this,
                    "Please finish editing before saving.",
                    "Finish Editing",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showConfirmDialog(this,
                    "Your changes have been saved.",
                    "Success",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            setName();
            setAddress();

            ContactInfo.commit(xml);
        }
    }//GEN-LAST:event_button_saveActionPerformed

    private void togglebutton_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglebutton_phoneActionPerformed
        int index = combobox_phone.getSelectedIndex();

        if(togglebutton_phone.isSelected()){
            if(index == -1){
                togglebutton_phone.setSelected(false);
                showPhoneError();
            }
            else{
                setPhoneEnabled(false);

                getPhoneNumber(index);
            }
        }
        else{
            setPhoneNumber(index);
            setPhoneEnabled(true);
            clearPhone();
            initPhoneNumbers();
        }
    }//GEN-LAST:event_togglebutton_phoneActionPerformed

    private void togglebutton_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglebutton_emailActionPerformed
        int index = combobox_email.getSelectedIndex();
        
        if(togglebutton_email.isSelected()){
            if(index == -1){
                togglebutton_email.setSelected(false);
                showEmailError();
            }
            else{
                setEmailEnabled(false);

                getEmailAddress(index);
            }
        }
        else{
            setEmailAddress(index);
            setEmailEnabled(true);
            clearEmail();
            initEmailAddresses();
        }
    }//GEN-LAST:event_togglebutton_emailActionPerformed

    private void button_phone_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_phone_deleteActionPerformed
        int index = combobox_phone.getSelectedIndex();
        
        if(index == -1){
            showPhoneError();
        }
        else{
            removeNode(index, XMLFactory.PHONE_NUMBER);
            initPhoneNumbers();
        }
    }//GEN-LAST:event_button_phone_deleteActionPerformed

    private void button_email_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_email_deleteActionPerformed
        int index = combobox_email.getSelectedIndex();

        if(index == -1){
            showEmailError();
        }
        else{
            removeNode(index, XMLFactory.EMAIL_ADDRESS);
            initEmailAddresses();
        }
    }//GEN-LAST:event_button_email_deleteActionPerformed

    private void button_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_phoneActionPerformed
        Element phone_number = XMLFactory.addNode(xml, head, XMLFactory.PHONE_NUMBER);
        XMLFactory.setAttribute(phone_number, XMLFactory._PHONE_TYPE, getPhoneType());
        XMLFactory.setAttribute(phone_number, XMLFactory._NUMBER, field_number.getText());
        XMLFactory.setAttribute(phone_number, XMLFactory._EXTENSION, field_extension.getText());

        clearPhone();
        initPhoneNumbers();
    }//GEN-LAST:event_button_phoneActionPerformed

    private void button_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_emailActionPerformed
        Element email_address = XMLFactory.addNode(xml, head, XMLFactory.EMAIL_ADDRESS);
        XMLFactory.setAttribute(email_address, XMLFactory._EMAIL_ADDRESS, field_email.getText());
        XMLFactory.setAttribute(email_address, XMLFactory._PERSONAL, field_personal.getText());

        clearEmail();
        initEmailAddresses();
    }//GEN-LAST:event_button_emailActionPerformed

    private void togglebutton_positionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglebutton_positionActionPerformed
        int index = combobox_position.getSelectedIndex();

        if(togglebutton_position.isSelected()){
            if(index == -1){
                togglebutton_position.setSelected(false);
                showPositionError();
            }
            else{
                setPositionEnabled(false);

                getPosition(index);
            }
        }
        else{
            setPosition(index);
            setPositionEnabled(true);
            clearPosition();
            initPositions();
        }
    }//GEN-LAST:event_togglebutton_positionActionPerformed

    private void button_position_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_position_deleteActionPerformed
        int index = combobox_position.getSelectedIndex();

        if(index == -1){
            showPositionError();
        }
        else{
            removeNode(index, XMLFactory.POSITION);
            initPositions();
        }
    }//GEN-LAST:event_button_position_deleteActionPerformed

    private void button_positionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_positionActionPerformed
        Element position = XMLFactory.addNode(xml, head, XMLFactory.POSITION);
        XMLFactory.setAttribute(position, XMLFactory._TITLE, field_title.getText());
        XMLFactory.setAttribute(position, XMLFactory._PAY_TYPE, getPayType());
        XMLFactory.setAttribute(position, XMLFactory._RATE, spinner_rate.getValue().toString());

        clearPosition();
        initPositions();
    }//GEN-LAST:event_button_positionActionPerformed

    private void button_revertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_revertActionPerformed
        initXML();
    }//GEN-LAST:event_button_revertActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_email;
    private javax.swing.JButton button_email_delete;
    private javax.swing.JButton button_phone;
    private javax.swing.JButton button_phone_delete;
    private javax.swing.JButton button_position;
    private javax.swing.JButton button_position_delete;
    private javax.swing.JButton button_revert;
    private javax.swing.JButton button_save;
    private javax.swing.JComboBox combobox_email;
    private javax.swing.JComboBox combobox_phone;
    private javax.swing.JComboBox combobox_position;
    private javax.swing.JComboBox combobox_state;
    private javax.swing.JTextField field_city;
    private javax.swing.JTextField field_email;
    private javax.swing.JTextField field_extension;
    private javax.swing.JTextField field_name;
    private javax.swing.JTextField field_number;
    private javax.swing.JTextField field_personal;
    private javax.swing.JTextField field_street;
    private javax.swing.JTextField field_title;
    private javax.swing.JTextField field_zip;
    private javax.swing.JLabel label_address;
    private javax.swing.JLabel label_city;
    private javax.swing.JLabel label_email;
    private javax.swing.JLabel label_emailaddresses;
    private javax.swing.JLabel label_ext;
    private javax.swing.JLabel label_firstdirections;
    private javax.swing.JLabel label_name;
    private javax.swing.JLabel label_number;
    private javax.swing.JLabel label_pay;
    private javax.swing.JLabel label_personal;
    private javax.swing.JLabel label_phone;
    private javax.swing.JLabel label_position_title;
    private javax.swing.JLabel label_positions;
    private javax.swing.JLabel label_state;
    private javax.swing.JLabel label_street;
    private javax.swing.JLabel label_title;
    private javax.swing.JLabel label_zip;
    private javax.swing.JRadioButton radio_cell;
    private javax.swing.JRadioButton radio_fax;
    private javax.swing.JRadioButton radio_home;
    private javax.swing.JRadioButton radio_hourly;
    private javax.swing.JRadioButton radio_salary;
    private javax.swing.JRadioButton radio_work;
    private javax.swing.ButtonGroup radiogroup_phone;
    private javax.swing.ButtonGroup radiogroup_position;
    private javax.swing.JSpinner spinner_rate;
    private javax.swing.JToggleButton togglebutton_email;
    private javax.swing.JToggleButton togglebutton_phone;
    private javax.swing.JToggleButton togglebutton_position;
    // End of variables declaration//GEN-END:variables

}
