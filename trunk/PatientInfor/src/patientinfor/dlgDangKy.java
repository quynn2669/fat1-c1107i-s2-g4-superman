/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package patientinfor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author tug
 */
public class dlgDangKy extends javax.swing.JDialog {

    /**
     * Creates new form dlgDangKy
     */
    Connection conn = null;
    String choice = "";

    public dlgDangKy(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            setTitle("Dang ky tai khoan!");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PI", "sa", "123456");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encrypMD5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(pass.getBytes());

            byte[] digest = md.digest();
            BigInteger bigInterger = new BigInteger(1, digest);

            String hashText = bigInterger.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public void DKBS() {
        try {
            String sName = txtHoTen.getText();
            String sID = txtID.getText();
            CallableStatement cs = conn.prepareCall("{call timKiemBS}");
            ResultSet rs = cs.executeQuery();
            for (int i = 1; i <= rs.getRow(); i++) {
                if (rs.getString(i).equals(sID)) {
                    JOptionPane.showMessageDialog(this, "ID bi trung`");
                    break;
                } else {
                    String sTaiKhoan = txtTaiKhoan.getText();
                    String sPass = encrypMD5(new String(pass.getPassword()));

                    String sSelect = "SELECT TaiKhoan FROM tblBacSi";
                    PreparedStatement pstmt = conn.prepareStatement(sSelect);
                    ResultSet rsp = pstmt.executeQuery();
                    if (!rsp.next()) {
                        CallableStatement cs1 = conn.prepareCall("{call taiKhoanBS(?,?,?,?)}");
                        cs1.setString(1, sID);
                        cs1.setString(2, sName);
                        cs1.setString(3, sTaiKhoan);
                        cs1.setString(4, sPass);
                        cs1.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Dang ky thanh cong!");
                        break;
                    } else {
                        for (int y = 1; y <= rsp.getRow(); y++) {
                            if (rsp.getString(y).equals(sTaiKhoan)) {
                                JOptionPane.showMessageDialog(this, "Da co nguoi su dung tai khoan nay!");
                                break;
                            } else {
                                CallableStatement cs1 = conn.prepareCall("{call taiKhoanBS(?,?,?,?)}");

                                cs1.setString(1, sID);
                                cs1.setString(2, sName);
                                cs1.setString(3, sTaiKhoan);
                                cs1.setString(4, sPass);
                                cs1.executeUpdate();
                                JOptionPane.showMessageDialog(this, "Dang ky thanh cong!");
                                break;
                            }

                        }
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DKNV() {
        try {
            String sName = txtHoTen.getText();
            String sID = txtID.getText();
            CallableStatement cs = conn.prepareCall("{call timKiemNV}");
            ResultSet rs = cs.executeQuery();
            for (int i = 1; i <= rs.getRow(); i++) {
                if (rs.getString(i).equals(sID)) {
                    JOptionPane.showMessageDialog(this, "ID bi trung`");
                    break;
                } else {
                    String sTaiKhoan = txtTaiKhoan.getText();
                    String sPass = encrypMD5(new String(pass.getPassword()));

                    String sSelect = "SELECT TaiKhoan FROM tblNhanVien";
                    PreparedStatement pstmt = conn.prepareStatement(sSelect);
                    ResultSet rsp = pstmt.executeQuery();
                    if (!rsp.next()) {
                        CallableStatement cs1 = conn.prepareCall("{call taiKhoanNV(?,?,?,?)}");
                        cs1.setString(1, sID);
                        cs1.setString(2, sName);
                        cs1.setString(3, sTaiKhoan);
                        cs1.setString(4, sPass);
                        cs1.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Dang ky thanh cong!");
                    } else {
                        for (int y = 1; y <= rsp.getRow(); y++) {
                            if (rsp.getString(y).equals(sTaiKhoan)) {
                                JOptionPane.showMessageDialog(this, "Da co nguoi su dung tai khoan nay!");
                                break;
                            } else {
                                CallableStatement cs1 = conn.prepareCall("{call taiKhoanNV(?,?,?,?)}");

                                cs1.setString(1, sID);
                                cs1.setString(2, sName);
                                cs1.setString(3, sTaiKhoan);
                                cs1.setString(4, sPass);
                                cs1.executeUpdate();
                                JOptionPane.showMessageDialog(this, "Dang ky thanh cong!");
                                break;
                            }

                        }
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupChucVu = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        btnDangKy = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        pass = new javax.swing.JPasswordField();
        rbtnBacSi = new javax.swing.JRadioButton();
        rbtnNhanVien = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Ma So");

        jLabel2.setText("Ho ten");

        jLabel3.setText("Tai Khoan");

        jLabel4.setText("Mat Khau");

        btnDangKy.setText("Dang Ky");
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });

        btnReset.setText("Lam lai");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnGroupChucVu.add(rbtnBacSi);
        rbtnBacSi.setText("Bac Si");
        rbtnBacSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnBacSiActionPerformed(evt);
            }
        });

        btnGroupChucVu.add(rbtnNhanVien);
        rbtnNhanVien.setText("Nhan Vien");
        rbtnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNhanVienActionPerformed(evt);
            }
        });

        jLabel5.setText("Chuc Vu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbtnBacSi)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnNhanVien))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtTaiKhoan)
                        .addComponent(txtHoTen)
                        .addComponent(txtID)
                        .addComponent(pass)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnBacSi)
                    .addComponent(rbtnNhanVien)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangKy)
                    .addComponent(btnReset))
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyActionPerformed
        // TODO add your handling code here:
        if (txtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chua nhap ma so!");
        } else if (txtHoTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chua nhap ho ten!");
        } else if (txtTaiKhoan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chua nhap tai khoan!");
        } else if (new String(pass.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chua nhap Mat khau!");
        } else if ("BS".equals(choice)) {
            DKBS();
        } else if ("NV".equals(choice)) {
            DKNV();
        } else {
            JOptionPane.showMessageDialog(this, "Chua Chon chuc vu!");
        }
    }//GEN-LAST:event_btnDangKyActionPerformed

    private void rbtnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNhanVienActionPerformed
        // TODO add your handling code here:
        choice = "NV";
    }//GEN-LAST:event_rbtnNhanVienActionPerformed

    private void rbtnBacSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnBacSiActionPerformed
        // TODO add your handling code here:
        choice = "BS";
    }//GEN-LAST:event_rbtnBacSiActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        choice = "";
        txtHoTen.setText(null);
        txtID.setText(null);
        txtTaiKhoan.setText(null);
        pass.setText(null);
        btnGroupChucVu.clearSelection();
    }//GEN-LAST:event_btnResetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dlgDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                dlgDangKy dialog = new dlgDangKy(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangKy;
    private javax.swing.ButtonGroup btnGroupChucVu;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField pass;
    private javax.swing.JRadioButton rbtnBacSi;
    private javax.swing.JRadioButton rbtnNhanVien;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
