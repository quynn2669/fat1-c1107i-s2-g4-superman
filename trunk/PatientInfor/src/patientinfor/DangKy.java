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
public class DangKy extends javax.swing.JFrame {

    /**
     * Creates new form DangKy
     */
    Connection conn = null;
    String choice = "";

    public DangKy() {
        initComponents();
        try {
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
            String sID = txtID.getText();
            String sName = txtHoTen.getText();
            CallableStatement cs = conn.prepareCall("{call timKiemBS(?,?)}");
            cs.setString(1, sID);
            cs.setString(2, sName);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                String sTaiKhoan = txtAccount.getText();
                String sPass = encrypMD5(new String(password.getPassword()));

                String sSelect = "SELECT TaiKhoan FROM tblBacSi";
                PreparedStatement pstmt = conn.prepareStatement(sSelect);
                ResultSet rsp = pstmt.executeQuery();
                if (rsp.next()) {
                    CallableStatement cs1 = conn.prepareCall("{call taiKhoanBS(?,?,?,?)}");
                    cs1.setString(1, sID);
                    cs1.setString(2, sName);
                    cs1.setString(3, sTaiKhoan);
                    cs1.setString(4, sPass);
                    cs1.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Dang ky thanh cong!");
                    new DangKy().setVisible(false);
                } else {
                    for (int i = 1; i >= 0; i++) {
                        if (rsp.getString(i) == sTaiKhoan) {
                            JOptionPane.showMessageDialog(this, "Da co nguoi su dung tai khoan nay!");
                            break;
                        } else if (rsp.getString(i).isEmpty()) {
                            CallableStatement cs1 = conn.prepareCall("{call taiKhoanBS(?,?,?,?)}");
                            cs1.setString(1, sID);
                            cs1.setString(2, sName);
                            cs1.setString(3, sTaiKhoan);
                            cs1.setString(4, sPass);
                            cs1.executeUpdate();
                            JOptionPane.showMessageDialog(this, "Dang ky thanh cong!");
                            new DangKy().setVisible(false);
                        }
                    }
                }

            } else {
                JOptionPane.showMessageDialog(this, "Nhap Sai ID hoac Ten");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DKNV() {
        try {
            String sID = txtID.getText();
            String sName = txtHoTen.getText();
            CallableStatement cs = conn.prepareCall("{call timKiemNV(?,?)}");
            cs.setString(1, sID);
            cs.setString(2, sName);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                String sTaiKhoan = txtAccount.getText();
                String sPass = encrypMD5(new String(password.getPassword()));

                String sSelect = "SELECT TaiKhoan FROM tblNhanVien";
                PreparedStatement pstmt = conn.prepareStatement(sSelect);
                ResultSet rsp = pstmt.executeQuery();
                for (int i = 1; i >= 0; i++) {
                    if (rsp.getString(i) == sTaiKhoan) {
                        JOptionPane.showMessageDialog(this, "Da co nguoi su dung tai khoan nay!");
                        break;
                    } else {
                        CallableStatement cs1 = conn.prepareCall("{call taiKhoanNV(?,?,?,?)}");
                        cs1.setString(1, sID);
                        cs1.setString(2, sName);
                        cs1.setString(3, sTaiKhoan);
                        cs1.setString(2, sPass);
                        cs1.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Dang ky thanh cong!");
                        new DangKy().setVisible(true);
                    }
                }


            } else {
                JOptionPane.showMessageDialog(this, "Nhap Sai ID hoac Ten");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtAccount = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        btnDangKy = new javax.swing.JButton();
        btnLamLai = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rbtnBacSi = new javax.swing.JRadioButton();
        rbtnNhanVien = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ma So ");

        jLabel2.setText("Ho Ten");

        jLabel3.setText("Ten Dang Nhap");

        jLabel4.setText("Mat Khau");

        btnDangKy.setText("Dang Ky");
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });

        btnLamLai.setText("Lam Lai");
        btnLamLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamLaiActionPerformed(evt);
            }
        });

        jLabel5.setText("Chuc vu");

        rbtnBacSi.setText("Bac Si");
        rbtnBacSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnBacSiActionPerformed(evt);
            }
        });

        rbtnNhanVien.setText("Nhan Vien");
        rbtnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnDangKy, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnLamLai, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtID)
                        .addComponent(txtHoTen)
                        .addComponent(txtAccount)
                        .addComponent(password))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbtnBacSi)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnNhanVien)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
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
                    .addComponent(txtAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rbtnBacSi)
                    .addComponent(rbtnNhanVien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangKy)
                    .addComponent(btnLamLai))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyActionPerformed
        // TODO add your handling code here:
        if (txtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chua nhap ma so!");
        } else if (txtHoTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chua nhap ho ten!");
        } else if (txtAccount.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chua nhap tai khoan!");
        } else if (new String(password.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chua nhap Mat khau!");
        } else if (choice == "BS") {
            DKBS();
        } else if (choice == "NV") {
            DKNV();
        } else {
            JOptionPane.showMessageDialog(this, "Chua Chon chuc vu!");
        }
    }//GEN-LAST:event_btnDangKyActionPerformed

    private void rbtnBacSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnBacSiActionPerformed
        // TODO add your handling code here:
        choice = "BS";


    }//GEN-LAST:event_rbtnBacSiActionPerformed

    private void rbtnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNhanVienActionPerformed
        // TODO add your handling code here:
        choice = "NV";

    }//GEN-LAST:event_rbtnNhanVienActionPerformed

    private void btnLamLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamLaiActionPerformed
        // TODO add your handling code here:
        txtAccount.setText(null);
        txtHoTen.setText(null);
        txtID.setText(null);
        password.setText(null);
        choice = "";
    }//GEN-LAST:event_btnLamLaiActionPerformed

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
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DangKy().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangKy;
    private javax.swing.JButton btnLamLai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField password;
    private javax.swing.JRadioButton rbtnBacSi;
    private javax.swing.JRadioButton rbtnNhanVien;
    private javax.swing.JTextField txtAccount;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
