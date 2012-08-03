/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package patientinfor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author tug
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    String choice = "";
    Connection conn = null;
    
    public Login() {
        initComponents();
        try {
            setTitle("Dang nhap he thong!");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PI","sa","123456");
           
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
     
     
        
     public void DNBS(){
        try {
            CallableStatement cs = conn.prepareCall("{call DNBS(?,?)}");
            cs.setString(1, txtTaiKhoan.getText());
            cs.setString(2,encrypMD5(new String(pass.getPassword())));
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(this, "Dang Nhap Thanh Cong!");
            }else{    
                JOptionPane.showMessageDialog(this, "Sai Tai khoan hoac mat khau!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
     }
     public void DNNV(){
        try {
            CallableStatement cs = conn.prepareCall("{call DNNV(?,?)}");
            cs.setString(1, txtTaiKhoan.getText());
            cs.setString(2,encrypMD5(new String(pass.getPassword())));
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(this, "Dang Nhap Thanh Cong!");
            }else{    
                JOptionPane.showMessageDialog(this, "Sai Tai khoan hoac mat khau!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        txtTaiKhoan = new javax.swing.JTextField();
        rbtnBacSi = new javax.swing.JRadioButton();
        rbtnNhanVien = new javax.swing.JRadioButton();
        btnLogin = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnDangKy = new javax.swing.JButton();
        pass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tai Khoan");

        jLabel2.setText("Mat Khau");

        txtTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaiKhoanActionPerformed(evt);
            }
        });

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

        btnLogin.setText("Dang Nhap");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnReset.setText("Lam Lai");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel3.setText("Chuc Vu");

        btnDangKy.setText("Dang ky");
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbtnBacSi)
                                .addGap(49, 49, 49)
                                .addComponent(rbtnNhanVien))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pass, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnBacSi)
                    .addComponent(rbtnNhanVien)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnReset)
                    .addComponent(btnDangKy))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        if(txtTaiKhoan.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Chua nhap ten tai khoan!");
        }else if(new String (pass.getPassword()).isEmpty()){
            JOptionPane.showMessageDialog(this, "Chua nhap mat khau!");
        } else if ("BS".equals(choice)) {
            DNBS();
        } else if ("NV".equals(choice)) {
            DNNV();
        } else {
            JOptionPane.showMessageDialog(this, "Chua Chon chuc vu!");
        }
       
    }//GEN-LAST:event_btnLoginActionPerformed

    private void rbtnBacSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnBacSiActionPerformed
        // TODO add your handling code here:
        choice = "BS";
    }//GEN-LAST:event_rbtnBacSiActionPerformed

    private void rbtnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNhanVienActionPerformed
        // TODO add your handling code here:
        choice = "NV";
    }//GEN-LAST:event_rbtnNhanVienActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtTaiKhoan.setText("");
        pass.setText("");
        choice ="";
        rbtnBacSi.setSelected(false);
        rbtnNhanVien.setSelected(false);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyActionPerformed
        // TODO add your handling code here:
        
        new dlgDangKy(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnDangKyActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Login().setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangKy;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField pass;
    private javax.swing.JRadioButton rbtnBacSi;
    private javax.swing.JRadioButton rbtnNhanVien;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}