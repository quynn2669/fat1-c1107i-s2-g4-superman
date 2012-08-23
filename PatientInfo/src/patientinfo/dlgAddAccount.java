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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author tug
 */
public class dlgAddAccount extends javax.swing.JDialog {

    /** Creates new form dlgAddPatient */
    String Role = "";
    int ID = 0;
    Connection conn = null;
    String tbl = "";

    public dlgAddAccount(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PI", "sa", "123456");
        } catch (Exception e) {
        }
    }

    public void reset() {
        txtID.setText(null);
        txtAccount.setText(null);
        txtAddFName.setText(null);
        btgRole.clearSelection();
    }

    public boolean checkEmp(int id, String Name) {
        boolean check = true;
        try {
            String sSelect = "Select FullName from tblEmployee where ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sSelect);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(Name)) {
                    check = true;
                } else {
                    check = false;
                }
            }else {
                check = false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check;
    }

    public boolean checkDr(int id, String Name) {
        boolean check = true;
        try {
            String sSelect = "Select FullName from tblDoctor where ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sSelect);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(Name)) {
                    check = true;
                } else {
                    check = false;
                }
            } else {
                check = false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check;
    }

    public boolean checkAc(int id, String Name, String role) {
        boolean check = true;
        try {
            String sSelect = "Select Name,Role from tblAccount where ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sSelect);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(Name) && rs.getString(2).equals(role)) {
                    check = true;
                } else {
                    check = false;
                }
            } else {
                check = false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgRole = new javax.swing.ButtonGroup();
        pnlAdd = new javax.swing.JPanel();
        pnlPatientInfo = new javax.swing.JPanel();
        txtAddFName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtAccount = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        rbtEm = new javax.swing.JRadioButton();
        rbtDoc = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        btnAdd = new javax.swing.JButton();
        btnAddReset = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlAdd.setBorder(javax.swing.BorderFactory.createTitledBorder("Account's Infomation"));
        pnlAdd.setPreferredSize(new java.awt.Dimension(400, 500));

        txtAddFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddFNameActionPerformed(evt);
            }
        });

        jLabel13.setText("Full Name:");

        jLabel15.setText("ID: ");

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel16.setText("Account:");

        jLabel17.setText("Role:");

        btgRole.add(rbtEm);
        rbtEm.setText("Employee");
        rbtEm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtEmActionPerformed(evt);
            }
        });

        btgRole.add(rbtDoc);
        rbtDoc.setText("Doctor");
        rbtDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtDocActionPerformed(evt);
            }
        });

        jLabel18.setText("Password:");

        javax.swing.GroupLayout pnlPatientInfoLayout = new javax.swing.GroupLayout(pnlPatientInfo);
        pnlPatientInfo.setLayout(pnlPatientInfoLayout);
        pnlPatientInfoLayout.setHorizontalGroup(
            pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(rbtEm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbtDoc))
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(pass, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPatientInfoLayout.createSequentialGroup()
                        .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(txtAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(txtAddFName, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))))
                .addGap(76, 76, 76))
        );
        pnlPatientInfoLayout.setVerticalGroup(
            pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(txtAddFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(36, 36, 36)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtDoc)
                    .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtEm)
                        .addComponent(jLabel17)))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
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
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap()
                .addComponent(pnlPatientInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnAddReset)))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add.png"))); // NOI18N
        jLabel1.setText("Add Account");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel1)
                .addContainerGap(95, Short.MAX_VALUE))
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
                .addComponent(pnlAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAddFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddFNameActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtAddFNameActionPerformed

    private void rbtEmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtEmActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_rbtEmActionPerformed

    private void rbtDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtDocActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_rbtDocActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:

        if (rbtEm.isSelected()) {
            Role = "employee";
        } else if (rbtDoc.isSelected()) {
            Role = "doctor";
        }
        int check = 0;
        String password = new String(pass.getPassword());
        if (txtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter ID!");
            check = 1;
        } else if (txtID.getText().length() > 0) {
            try {
                ID = Integer.parseInt(txtID.getText());
            } catch (NumberFormatException e) {
                check = 1;

                JOptionPane.showMessageDialog(this, "ID valid!!!");
            }
        }
        if (check == 0) {
            if (txtAddFName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else if (txtAccount.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Account!!!");
            } else if (txtAccount.getText().length() < 4) {
                JOptionPane.showMessageDialog(this, "Account length must be greater than 4 characters!!!");
            } else if (password.length() == 0) {
                JOptionPane.showMessageDialog(this, "Enter Password!!!");
            } else if (password.length() < 4) {
                JOptionPane.showMessageDialog(this, "Password length must be greater than 4 characters!!!");
            } else if (Role.equals("")) {
                JOptionPane.showMessageDialog(this, "Choice Role!");
            } else if (Role.equals("doctor")) {
                if (checkDr(ID, txtAddFName.getText())) {
                    if (checkAc(ID, txtAddFName.getText(), Role)) {
                        JOptionPane.showMessageDialog(this, "You already have the Account!");
                    } else {
                        try {
                            String sUpdate = "INSERT INTO tblAccount VALUES(?,?,?,?,?)";
                            PreparedStatement pstmt = conn.prepareStatement(sUpdate);
                            pstmt.setInt(1, ID);
                            pstmt.setString(2, txtAddFName.getText());
                            pstmt.setString(3, Role);
                            pstmt.setString(4, txtAccount.getText());
                            pstmt.setString(5, password);
                            pstmt.executeUpdate();
                            JOptionPane.showMessageDialog(this, "Add success!!");
                            reset();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Wrong ID or name!!!");
                }
            } else if (Role.equals("employee")) {
                if (checkEmp(ID, txtAddFName.getText())) {
                    if (checkAc(ID, txtAddFName.getText(), Role)) {
                        JOptionPane.showMessageDialog(this, "You already have the Account!");
                    } else {
                        try {
                            String sUpdate = "INSERT INTO tblAccount VALUES(?,?,?,?,?)";
                            PreparedStatement pstmt = conn.prepareStatement(sUpdate);
                            pstmt.setInt(1, ID);
                            pstmt.setString(2, txtAddFName.getText());
                            pstmt.setString(3, Role);
                            pstmt.setString(4, txtAccount.getText());
                            pstmt.setString(5, password);
                            JOptionPane.showMessageDialog(this, "Add success!!");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Wrong ID or name!!!");
                }
            }
        }
}//GEN-LAST:event_btnAddActionPerformed

    private void btnAddResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddResetActionPerformed
        // TODO add your handling code here:
        reset();

}//GEN-LAST:event_btnAddResetActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                dlgAddAccount dialog = new dlgAddAccount(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup btgRole;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField pass;
    private javax.swing.JPanel pnlAdd;
    private javax.swing.JPanel pnlPatientInfo;
    private javax.swing.JRadioButton rbtDoc;
    private javax.swing.JRadioButton rbtEm;
    private javax.swing.JTextField txtAccount;
    private javax.swing.JTextField txtAddFName;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
