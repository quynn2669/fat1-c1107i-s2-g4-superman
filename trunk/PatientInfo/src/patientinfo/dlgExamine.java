/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DetailPatient.java
 *
 * Created on Aug 17, 2012, 7:55:23 PM
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tug
 */
public class dlgExamine extends javax.swing.JDialog {

    /** Creates new form DetailPatient */
    public Patient patientDlg = new Patient();
    String In = "";
    Connection conn = null;
    String date = "";
    DefaultTableModel model = null;
    String doctor = "";

    public dlgExamine(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PI", "sa", "123456");
        } catch (Exception e) {
        }
        DateTimeTDV dateTDV = new DateTimeTDV();
        cbbYO.setModel(dateTDV.getListYear());
        cbbDoctor.setModel(getListDoctor());


    }

    public void rsExam() {
        txtID.setText(null);
        txtName.setText(null);
        txtAD.setText(null);
        txtAge.setText(null);
        txtDateI.setText(null);
        txtDoctor.setText(null);
        btgGender.clearSelection();
        btgIn.clearSelection();
        cbbDepartment.setSelectedIndex(0);
        cbbDoctor.setSelectedIndex(0);
        cbbDO.setSelectedIndex(0);
        cbbYO.setSelectedIndex(0);
        cbbMO.setSelectedIndex(0);
        patientDlg = new Patient();
        In = "";
        date = "";
        model = null;
        doctor = "";
        if (txtID.getText().isEmpty()) {
            btnOk.setEnabled(false);
        } else {
            btnOk.setEnabled(true);
        }
    }

    public DefaultComboBoxModel getListDoctor() {
        DefaultComboBoxModel doctorName = new DefaultComboBoxModel();
        try {

            String sSelect = "Select FullName from tblDoctor";
            PreparedStatement pstmt = conn.prepareStatement(sSelect);
            ResultSet rs = pstmt.executeQuery();

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

    dlgExamine() {
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
        btgIn = new javax.swing.ButtonGroup();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtAD = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        cbbDepartment = new javax.swing.JComboBox();
        cbbYO = new javax.swing.JComboBox();
        cbbMO = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbbDO = new javax.swing.JComboBox();
        txtDateI = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        aDes = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        aSick = new javax.swing.JTextArea();
        btnOk = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rbtYes = new javax.swing.JRadioButton();
        rbtNo = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        cbbDoctor = new javax.swing.JComboBox();
        txtDoctor = new javax.swing.JTextField();
        cbxUpdateDoctor = new javax.swing.JCheckBox();
        txtGender = new javax.swing.JTextField();
        btnRS = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Details Patient");

        jLabel27.setText("Full Name:");

        jLabel28.setText("Address:");

        jLabel29.setText("Age:");

        jLabel30.setText("Gender:");

        jLabel31.setText("Department:");

        jLabel32.setText("Description:");

        jLabel33.setText("Sick:");

        jLabel34.setText("Doctor's Manager:");

        jLabel36.setText("DateIn:");

        jLabel37.setText("In Hospital:");

        txtName.setEditable(false);

        txtAD.setEditable(false);

        txtAge.setEditable(false);

        cbbDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Internal medicine", "Surgical", "Cardiovascular" }));
        cbbDepartment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbDepartmentItemStateChanged(evt);
            }
        });

        cbbYO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012" }));
        cbbYO.setEnabled(false);
        cbbYO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbYOItemStateChanged(evt);
            }
        });
        cbbYO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbYOActionPerformed(evt);
            }
        });

        cbbMO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbbMO.setEnabled(false);
        cbbMO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbMOItemStateChanged(evt);
            }
        });
        cbbMO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMOActionPerformed(evt);
            }
        });

        jLabel9.setText("Month");

        jLabel11.setText("Year");

        jLabel6.setText("Day");

        cbbDO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cbbDO.setEnabled(false);

        txtDateI.setEditable(false);

        aDes.setColumns(20);
        aDes.setEditable(false);
        aDes.setRows(5);
        jScrollPane1.setViewportView(aDes);

        aSick.setColumns(20);
        aSick.setRows(5);
        jScrollPane2.setViewportView(aSick);

        btnOk.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\health-care-shield-icon.png")); // NOI18N
        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jLabel3.setText("ID:");

        txtID.setEditable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36));
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Untitled 5.png")); // NOI18N
        jLabel1.setText("Examine");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(306, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(278, 278, 278))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btgIn.add(rbtYes);
        rbtYes.setText("Yes");
        rbtYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtYesActionPerformed(evt);
            }
        });

        btgIn.add(rbtNo);
        rbtNo.setText("No");
        rbtNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtNoActionPerformed(evt);
            }
        });

        jLabel2.setText("Set Date Out");

        cbbDoctor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "(empty)" }));

        txtDoctor.setEditable(false);

        cbxUpdateDoctor.setText("Update Doctor");
        cbxUpdateDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUpdateDoctorActionPerformed(evt);
            }
        });

        txtGender.setEditable(false);

        btnRS.setText("Reset");
        btnRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(450, 450, 450))
                    .addComponent(cbxUpdateDoctor)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel27)
                                            .addComponent(jLabel29))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtAge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                            .addComponent(txtAD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                            .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                            .addComponent(txtGender, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                            .addComponent(cbbDoctor, javax.swing.GroupLayout.Alignment.TRAILING, 0, 317, Short.MAX_VALUE)
                                            .addComponent(txtDoctor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel31)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel36))
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDateI, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rbtYes)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rbtNo))
                                            .addComponent(cbbDepartment, 0, 298, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbbYO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbbMO, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(10, 10, 10)
                                                        .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel6)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(cbbDO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addGap(6, 6, 6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 364, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel32)
                                    .addGap(6, 6, 6))
                                .addComponent(jLabel33))
                            .addComponent(btnRS, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33)))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txtDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxUpdateDoctor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDateI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(rbtYes)
                    .addComponent(rbtNo))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbDO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cbbYO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRS, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbDepartmentItemStateChanged
        // TODO add your handling code here:
}//GEN-LAST:event_cbbDepartmentItemStateChanged

    private void cbbYOItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbYOItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbMO.getSelectedItem().toString());
        int year = Integer.parseInt(cbbYO.getSelectedItem().toString());
        cbbDO.setModel(tdv.getDayByMonth(month, year));
}//GEN-LAST:event_cbbYOItemStateChanged

    private void cbbYOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbYOActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cbbYOActionPerformed

    private void cbbMOItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbMOItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbMO.getSelectedItem().toString());
        int year = Integer.parseInt(cbbYO.getSelectedItem().toString());
        cbbDO.setModel(tdv.getDayByMonth(month, year));
}//GEN-LAST:event_cbbMOItemStateChanged

    private void cbbMOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMOActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cbbMOActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
        if (cbxUpdateDoctor.isSelected()) {
            doctor = cbbDoctor.getSelectedItem().toString();
        } else {
            doctor = txtDoctor.getText();
        }
        if (aSick.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Sick!!");
        } else if (In.equals("")) {
            JOptionPane.showMessageDialog(this, "Choice In Hospital??");
        } else if (In.equals("Yes")) {
            date = cbbMO.getSelectedItem().toString() + "/" + cbbDO.getSelectedItem().toString() + "/" + cbbYO.getSelectedItem().toString();
            try {
                CallableStatement cs = conn.prepareCall("{call ExamineYes(?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, txtID.getText());
                cs.setString(2, txtName.getText());
                cs.setString(3, txtGender.getText());
                cs.setString(4, cbbDepartment.getSelectedItem().toString());
                cs.setString(5, aSick.getText());
                cs.setString(6, aDes.getText());
                cs.setString(7, aSick.getText());
                cs.setString(8, txtDateI.getText());
                cs.setString(9, date);
                cs.setString(10, txtDateI.getText());
                cs.setString(11, cbbDoctor.getSelectedItem().toString());
                cs.executeUpdate();
                try {
                    CallableStatement cs1 = conn.prepareCall("{call ExamineP(?,?,?,?,?,?)}");
                    cs.setString(1, cbbDepartment.getSelectedItem().toString());
                    cs1.setString(2, txtID.getText());
                    cs1.setString(3, aSick.getText());
                    cs1.setString(6, "Yes");
                    cs1.setInt(7, 1);
                    cs1.setString(5, date);
                    cs1.setString(4, cbbDoctor.getSelectedItem().toString());
                    cs1.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Examine success!");
                    rsExam();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else {
            try {
                CallableStatement cs1 = conn.prepareCall("{call ExamineP(?,?,?,?,?,?)}");
                cs1.setString(1, txtID.getText());
                cs1.setString(2, aSick.getText());
                cs1.setString(5, "No");
                cs1.setInt(6, 1);
                cs1.setString(4, date);
                cs1.setString(3, cbbDoctor.getSelectedItem().toString());
                cs1.executeUpdate();
                JOptionPane.showMessageDialog(this, "Examine success!");
                btgIn.clearSelection();
                aSick.setText(null);
                rsExam();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void rbtYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtYesActionPerformed
        // TODO add your handling code here:
        cbbYO.setEnabled(true);
        cbbMO.setEnabled(true);
        cbbDO.setEnabled(true);
        In = "Yes";
    }//GEN-LAST:event_rbtYesActionPerformed

    private void rbtNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtNoActionPerformed
        // TODO add your handling code here:
        cbbYO.setEnabled(false);
        cbbDO.setEnabled(false);
        cbbMO.setEnabled(false);
        In = "No";
    }//GEN-LAST:event_rbtNoActionPerformed

    private void cbxUpdateDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUpdateDoctorActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cbxUpdateDoctorActionPerformed

    private void btnRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSActionPerformed
        // TODO add your handling code here:
        btgIn.clearSelection();
        aSick.setText(null);
    }//GEN-LAST:event_btnRSActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                dlgExamine dialog = new dlgExamine(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextArea aDes;
    private javax.swing.JTextArea aSick;
    private javax.swing.ButtonGroup btgGender;
    private javax.swing.ButtonGroup btgIn;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnRS;
    private javax.swing.JComboBox cbbDO;
    private javax.swing.JComboBox cbbDepartment;
    private javax.swing.JComboBox cbbDoctor;
    private javax.swing.JComboBox cbbMO;
    private javax.swing.JComboBox cbbYO;
    private javax.swing.JCheckBox cbxUpdateDoctor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbtNo;
    private javax.swing.JRadioButton rbtYes;
    private javax.swing.JTextField txtAD;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtDateI;
    private javax.swing.JTextField txtDoctor;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

    public void setaDes(String a) {
        aDes.setText(a);
    }

    public void setCbbDepartment(String c) {
        if ("Internal medicine".equals(c)) {
            cbbDepartment.setSelectedIndex(0);
        } else if ("Surgical".equals(c)) {
            cbbDepartment.setSelectedIndex(1);
        } else {
            cbbDepartment.setSelectedIndex(2);
        }
    }

    public void setTxtGender(String s) {
        txtGender.setText(s);
    }

    public void setTxtAD(String s) {
        txtAD.setText(s);
    }

    public void setTxtAge(String s) {
        txtAge.setText(s);
    }

    public void setTxtDateI(String d) {
        txtDateI.setText(d);
    }

    public void setTxtDoctor(String s) {
        txtDoctor.setText(s);
    }

    public void setTxtName(String s) {
        txtName.setText(s);
    }

    public void setTxtID(String s) {
        txtID.setText(s);
    }
}
