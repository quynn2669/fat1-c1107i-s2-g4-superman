/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * dlgAddPatient.java
 *
 * Created on Aug 18, 2012, 10:46:52 AM
 */
package patientinfo;

import DateTime.DateTimeTDV;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author tug
 */
public class dlgAddPatient extends javax.swing.JDialog {

    /** Creates new form dlgAddPatient */
    String Gender = "";
    int addAge = 0;
    Connection conn = null;

    public dlgAddPatient(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PI", "sa", "123456");
        } catch (Exception e) {
        }
        DateTimeTDV dateTDV = new DateTimeTDV();
        cbbAddYearIn.setModel(dateTDV.getListYear());

        cbbDoctor.setModel(getListDoctor());
    }
    public void reset(){
        txtAddAddress.setText(null);
        txtAddAge.setText(null);
        txtAddFName.setText(null);
        aDescript.setText(null);
        btgGender.clearSelection();
        cbbAddDepartment.setSelectedIndex(0);
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbAddMonthIn.getSelectedItem().toString());
        int year = Integer.parseInt(cbbAddYearIn.getSelectedItem().toString());
        cbbAddDayIn.setModel(tdv.getDayByMonth(month, year));
    }
    public DefaultComboBoxModel getListDoctor() {
        DefaultComboBoxModel doctorName = new DefaultComboBoxModel();
        try {

            String sSelect = "Select FullName from tblDoctor";
            PreparedStatement pstmt = conn.prepareStatement(sSelect);
            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()){
                doctorName.addElement("(empty)");
            }
            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    doctorName.addElement(rs.getObject(i).toString());
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return doctorName;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGender = new javax.swing.ButtonGroup();
        pnlAdd = new javax.swing.JPanel();
        pnlPatientInfo = new javax.swing.JPanel();
        txtAddFName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtAddAddress = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtAddAge = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        rbtAddMale = new javax.swing.JRadioButton();
        rbtAddFMale = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        aDescript = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        cbbAddDepartment = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        cbbAddYearIn = new javax.swing.JComboBox();
        cbbAddMonthIn = new javax.swing.JComboBox();
        cbbAddDayIn = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        cbbDoctor = new javax.swing.JComboBox();
        btnAddPatient = new javax.swing.JButton();
        btnAddReset = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlAdd.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient's Info"));
        pnlAdd.setPreferredSize(new java.awt.Dimension(400, 500));

        txtAddFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddFNameActionPerformed(evt);
            }
        });

        jLabel13.setText("Full Name");

        jLabel15.setText("Address");

        jLabel16.setText("Age");

        jLabel17.setText("Gender");

        btgGender.add(rbtAddMale);
        rbtAddMale.setText("Male");
        rbtAddMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAddMaleActionPerformed(evt);
            }
        });

        btgGender.add(rbtAddFMale);
        rbtAddFMale.setText("Female");
        rbtAddFMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAddFMaleActionPerformed(evt);
            }
        });

        jLabel18.setText("Description");

        aDescript.setColumns(20);
        aDescript.setRows(5);
        jScrollPane2.setViewportView(aDescript);

        jLabel19.setText("Department");

        cbbAddDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Internal medicine", "Surgical", "Cardiovascular" }));
        cbbAddDepartment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbAddDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAddDepartmentItemStateChanged(evt);
            }
        });

        jLabel20.setText("Date In");

        cbbAddYearIn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012" }));
        cbbAddYearIn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAddYearInItemStateChanged(evt);
            }
        });
        cbbAddYearIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbAddYearInActionPerformed(evt);
            }
        });

        cbbAddMonthIn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbbAddMonthIn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAddMonthInItemStateChanged(evt);
            }
        });

        cbbAddDayIn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel21.setText("Year");

        jLabel22.setText("Month");

        jLabel24.setText("Doctor");

        jLabel50.setText("Day");

        cbbDoctor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "(empty)" }));

        javax.swing.GroupLayout pnlPatientInfoLayout = new javax.swing.GroupLayout(pnlPatientInfo);
        pnlPatientInfo.setLayout(pnlPatientInfoLayout);
        pnlPatientInfoLayout.setHorizontalGroup(
            pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(rbtAddMale)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtAddFMale))
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel15))
                            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel13))
                            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16)))
                            .addComponent(jLabel20)
                            .addComponent(jLabel24)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbDoctor, 0, 247, Short.MAX_VALUE)
                            .addComponent(cbbAddDepartment, 0, 247, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPatientInfoLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbAddYearIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel22)
                                .addGap(6, 6, 6)
                                .addComponent(cbbAddMonthIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbAddDayIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(txtAddAge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(txtAddFName, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(txtAddAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))))
                .addContainerGap(89, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlPatientInfoLayout.setVerticalGroup(
            pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(23, 23, 23)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtAddAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtAddAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtAddFMale)
                    .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtAddMale)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbbAddDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbbDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cbbAddYearIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(cbbAddMonthIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(cbbAddDayIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap())
        );

        btnAddPatient.setText("Add");
        btnAddPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPatientActionPerformed(evt);
            }
        });

        btnAddReset.setText("Reset");
        btnAddReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAddLayout = new javax.swing.GroupLayout(pnlAdd);
        pnlAdd.setLayout(pnlAddLayout);
        pnlAddLayout.setHorizontalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddLayout.createSequentialGroup()
                .addGroup(pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnAddPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddReset, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAddLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlPatientInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlAddLayout.setVerticalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(pnlPatientInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddPatient)
                    .addComponent(btnAddReset)))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Actions-list-add-user-icon.png")); // NOI18N
        jLabel1.setText("Add Patient");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel1)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAddFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddFNameActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtAddFNameActionPerformed

    private void rbtAddMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAddMaleActionPerformed
        // TODO add your handling code here:
        Gender = "Male";
}//GEN-LAST:event_rbtAddMaleActionPerformed

    private void rbtAddFMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAddFMaleActionPerformed
        // TODO add your handling code here:
        Gender = "Female";
}//GEN-LAST:event_rbtAddFMaleActionPerformed

    private void cbbAddDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAddDepartmentItemStateChanged
        // TODO add your handling code here:
}//GEN-LAST:event_cbbAddDepartmentItemStateChanged

    private void cbbAddYearInItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAddYearInItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbAddMonthIn.getSelectedItem().toString());
        int year = Integer.parseInt(cbbAddYearIn.getSelectedItem().toString());
        cbbAddDayIn.setModel(tdv.getDayByMonth(month, year));
}//GEN-LAST:event_cbbAddYearInItemStateChanged

    private void cbbAddYearInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbAddYearInActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cbbAddYearInActionPerformed

    private void cbbAddMonthInItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAddMonthInItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbAddMonthIn.getSelectedItem().toString());
        int year = Integer.parseInt(cbbAddYearIn.getSelectedItem().toString());
        cbbAddDayIn.setModel(tdv.getDayByMonth(month, year));
}//GEN-LAST:event_cbbAddMonthInItemStateChanged

    private void btnAddPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPatientActionPerformed
        // TODO add your handling code here:
        boolean check = true;
        if (txtAddFName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Full Name!");
        } else if (txtAddAddress.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Address!");
        } else if (txtAddAge.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Age!");
        } else if ("".equals(Gender)) {
            JOptionPane.showMessageDialog(this, "Choice Gender!");
        } else if (aDescript.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Decription!");
        } else {
            try {
                addAge = Integer.parseInt(txtAddAge.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Age valid!!");
                check = false;
            }
            if (check) {
                try {
                    String DateTemp = cbbAddMonthIn.getSelectedItem().toString() + "/"
                            + cbbAddDayIn.getSelectedItem().toString() + "/" + cbbAddYearIn.getSelectedItem().toString();
                    CallableStatement cs = conn.prepareCall("{call empAddPatient(?,?,?,?,?,?,?,?)}");
                    cs.setString(1, txtAddFName.getText());

                    cs.setString(2, txtAddAddress.getText());
                    cs.setInt(3, addAge);
                    cs.setString(4, Gender);
                    cs.setString(5, aDescript.getText());
                    cs.setString(6, cbbAddDepartment.getSelectedItem().toString());
                    cs.setString(7, cbbDoctor.getSelectedItem().toString());
                    cs.setString(8, DateTemp);
                    cs.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Add Completed!");
                    reset();

                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
}//GEN-LAST:event_btnAddPatientActionPerformed

    private void btnAddResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddResetActionPerformed
        // TODO add your handling code here:
        reset();

}//GEN-LAST:event_btnAddResetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                dlgAddPatient dialog = new dlgAddPatient(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea aDescript;
    private javax.swing.ButtonGroup btgGender;
    private javax.swing.JButton btnAddPatient;
    private javax.swing.JButton btnAddReset;
    private javax.swing.JComboBox cbbAddDayIn;
    private javax.swing.JComboBox cbbAddDepartment;
    private javax.swing.JComboBox cbbAddMonthIn;
    private javax.swing.JComboBox cbbAddYearIn;
    private javax.swing.JComboBox cbbDoctor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlAdd;
    private javax.swing.JPanel pnlPatientInfo;
    private javax.swing.JRadioButton rbtAddFMale;
    private javax.swing.JRadioButton rbtAddMale;
    private javax.swing.JTextField txtAddAddress;
    private javax.swing.JTextField txtAddAge;
    private javax.swing.JTextField txtAddFName;
    // End of variables declaration//GEN-END:variables
}
