
package pions.view.aboutus;

/**
 *
 * 
 */
public class AboutUs extends javax.swing.JPanel {

    /** Creates new form AboutUs */
    public AboutUs() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_title = new javax.swing.JLabel();
        label_description_name = new javax.swing.JLabel();
        label_description_title = new javax.swing.JLabel();
        label_description_version = new javax.swing.JLabel();
        label_description_vendor = new javax.swing.JLabel();
        label_description_homepage = new javax.swing.JLabel();
        label_description_description = new javax.swing.JLabel();
        label_value_name = new javax.swing.JLabel();
        label_value_title = new javax.swing.JLabel();
        label_value_version = new javax.swing.JLabel();
        label_value_vendor = new javax.swing.JLabel();
        label_value_homepage = new javax.swing.JLabel();
        scrollpane_value_description = new javax.swing.JScrollPane();
        textarea_value_description = new javax.swing.JTextArea();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 400));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(AboutUs.class);
        label_title.setFont(resourceMap.getFont("label_title.font")); // NOI18N
        label_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_title.setText(resourceMap.getString("label_title.text")); // NOI18N
        label_title.setName("label_title"); // NOI18N

        label_description_name.setText(resourceMap.getString("label_description_name.text")); // NOI18N
        label_description_name.setName("label_description_name"); // NOI18N

        label_description_title.setText(resourceMap.getString("label_description_title.text")); // NOI18N
        label_description_title.setName("label_description_title"); // NOI18N

        label_description_version.setText(resourceMap.getString("label_description_version.text")); // NOI18N
        label_description_version.setName("label_description_version"); // NOI18N

        label_description_vendor.setText(resourceMap.getString("label_description_vendor.text")); // NOI18N
        label_description_vendor.setName("label_description_vendor"); // NOI18N

        label_description_homepage.setText(resourceMap.getString("label_description_homepage.text")); // NOI18N
        label_description_homepage.setName("label_description_homepage"); // NOI18N

        label_description_description.setText(resourceMap.getString("label_description_description.text")); // NOI18N
        label_description_description.setName("label_description_description"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("pions/resources/PIONS"); // NOI18N
        label_value_name.setText(bundle.getString("Application.name")); // NOI18N
        label_value_name.setName("label_value_name"); // NOI18N

        label_value_title.setText(bundle.getString("Application.title")); // NOI18N
        label_value_title.setName("label_value_title"); // NOI18N

        label_value_version.setText(bundle.getString("Application.version")); // NOI18N
        label_value_version.setName("label_value_version"); // NOI18N

        label_value_vendor.setText(bundle.getString("Application.vendor")); // NOI18N
        label_value_vendor.setName("label_value_vendor"); // NOI18N

        label_value_homepage.setText(bundle.getString("Application.homepage")); // NOI18N
        label_value_homepage.setName("label_value_homepage"); // NOI18N

        scrollpane_value_description.setName("scrollpane_value_description"); // NOI18N

        textarea_value_description.setBackground(resourceMap.getColor("textarea_value_description.background")); // NOI18N
        textarea_value_description.setEditable(false);
        textarea_value_description.setLineWrap(true);
        textarea_value_description.setRows(3);
        textarea_value_description.setText(bundle.getString("Application.description")); // NOI18N
        textarea_value_description.setWrapStyleWord(true);
        textarea_value_description.setName("textarea_value_description"); // NOI18N
        scrollpane_value_description.setViewportView(textarea_value_description);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_title, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(label_description_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_description_title, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_description_version, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_description_vendor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_description_homepage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_description_description, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_value_name)
                    .addComponent(label_value_title)
                    .addComponent(label_value_version)
                    .addComponent(label_value_vendor)
                    .addComponent(label_value_homepage)
                    .addComponent(scrollpane_value_description, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_title)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_description_name)
                    .addComponent(label_value_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_description_title)
                    .addComponent(label_value_title))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_description_version)
                    .addComponent(label_value_version))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_description_vendor)
                    .addComponent(label_value_vendor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_description_homepage)
                    .addComponent(label_value_homepage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_description_description)
                    .addComponent(scrollpane_value_description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(164, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label_description_description;
    private javax.swing.JLabel label_description_homepage;
    private javax.swing.JLabel label_description_name;
    private javax.swing.JLabel label_description_title;
    private javax.swing.JLabel label_description_vendor;
    private javax.swing.JLabel label_description_version;
    private javax.swing.JLabel label_title;
    private javax.swing.JLabel label_value_homepage;
    private javax.swing.JLabel label_value_name;
    private javax.swing.JLabel label_value_title;
    private javax.swing.JLabel label_value_vendor;
    private javax.swing.JLabel label_value_version;
    private javax.swing.JScrollPane scrollpane_value_description;
    private javax.swing.JTextArea textarea_value_description;
    // End of variables declaration//GEN-END:variables

}