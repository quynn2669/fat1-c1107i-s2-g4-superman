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
    String gioiTinh = "";

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
            int check = 0;
            String sName = txtHoTen.getText();
            String sID = txtID.getText();
            String sDiaChi = txtDiaChi.getText();
            int tuoi = 0;
            try {
                tuoi = Integer.parseInt(txtTuoi.getText());
            } catch (NumberFormatException e) {
                check = 3;
            }
            if (check == 0) {

                CallableStatement cs = conn.prepareCall("{call timKiemBS(?)}");
                cs.setString(1, sID);
                ResultSet rs = cs.executeQuery();
                if (rs.next()) {
                    check = 1;
                } else {
                    check = 0;
                }

                if (check == 1) {
                    JOptionPane.showMessageDialog(this, "ID bi trung!!!");
                } else {
                    String sTaiKhoan = txtTaiKhoan.getText();
                    String sPass = encrypMD5(new String(pass.getPassword()));

                    CallableStatement cs1 = conn.prepareCall("{call timKiemTKBS(?)}");
                    cs1.setString(1, sTaiKhoan);
                    ResultSet rs1 = cs1.executeQuery();
                    if (rs1.next()) {
                        check = 2;
                    } else {
                        check = 0;
                    }
                    if (check == 2){
                        JOptionPane.showMessageDialog(this, "Tai khoan bi trung!!!");
                    } else{
                        CallableStatement cs2 = conn.prepareCall("{call ghiBS(?,?,?,?,?,?,?)}");
                        cs2.setString(1, sID);
                        cs2.setString(2, sName);
                        cs2.setString(3, sDiaChi);
                        cs2.setInt(4, tuoi);
                        cs2.setString(5, gioiTinh);
                        cs2.setString(6, sTaiKhoan);
                        cs2.setString(7, sPass);
                        cs2.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Dang ky thanh cong!");

                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nhap sai tuoi!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void DKNV() {
        try {
            int check = 0;
            String sName = txtHoTen.getText();
            String sID = txtID.getText();
            String sDiaChi = txtDiaChi.getText();
            int tuoi = 0;
            try {
                tuoi = Integer.parseInt(txtTuoi.getText());
            } catch (NumberFormatException e) {
                check = 3;
            }
            if (check == 0) {

                CallableStatement cs = conn.prepareCall("{call timKiemNV(?)}");
                cs.setString(1, sID);
                ResultSet rs = cs.executeQuery();
                if (rs.next()) {
                    check = 1;
                } else {
                    check = 0;
                }

                if (check == 1) {
                    JOptionPane.showMessageDialog(this, "ID bi trung!!!");
                } else {
                    String sTaiKhoan = txtTaiKhoan.getText();
                    String sPass = encrypMD5(new String(pass.getPassword()));

                    CallableStatement cs1 = conn.prepareCall("{call timKiemTKNV(?)}");
                    cs1.setString(1, sTaiKhoan);
                    ResultSet rs1 = cs1.executeQuery();
                    if (rs1.next()) {
                        check = 2;
                    } else {
                        check = 0;
                    }
                    if (check == 2){
                        JOptionPane.showMessageDialog(this, "Tai khoan bi trung!!!");
                    } else{
                        CallableStatement cs2 = conn.prepareCall("{call ghiNV(?,?,?,?,?,?,?)}");
                        cs2.setString(1, sID);
                        cs2.setString(2, sName);
                        cs2.setString(3, sDiaChi);
                        cs2.setInt(4, tuoi);
                        cs2.setString(5, gioiTinh);
                        cs2.setString(6, sTaiKhoan);
                        cs2.setString(7, sPass);
                        cs2.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Dang ky thanh cong!");

                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nhap sai tuoi!");
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
        btnGroupGioiTinh = new javax.swing.ButtonGroup();
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtTuoi = new javax.swing.JTextField();
        rbtnNam = new javax.swing.JRadioButton();
        rbtnNu = new javax.swing.JRadioButton();

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

        jLabel6.setText("Dia Chi");

        jLabel7.setText("Tuoi");

        jLabel8.setText("Gioi Tinh");

        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        btnGroupGioiTinh.add(rbtnNam);
        rbtnNam.setText("Nam");
        rbtnNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNamActionPerformed(evt);
            }
        });

        btnGroupGioiTinh.add(rbtnNu);
        rbtnNu.setText("Nu");
        rbtnNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtTaiKhoan)
                        .addComponent(txtHoTen)
                        .addComponent(txtID)
                        .addComponent(pass)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(rbtnBacSi)
                            .addGap(18, 18, 18)
                            .addComponent(rbtnNhanVien))
                        .addComponent(txtDiaChi)
                        .addComponent(txtTuoi))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbtnNam)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnNu)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(rbtnNam)
                    .addComponent(rbtnNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnBacSi)
                    .addComponent(rbtnNhanVien))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangKy)
                    .addComponent(btnReset))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyActionPerformed
        // TODO add your handling code here:
        if (txtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chua nhap ma so!");
        } else if (txtHoTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chua nhap ho ten!");
        } else if (gioiTinh == "") {
            JOptionPane.showMessageDialog(this, "Chua nhap chon gioi tinh!");
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
        txtDiaChi.setText(null);
        txtTuoi.setText(null);
        txtHoTen.setText(null);
        txtID.setText(null);
        txtTaiKhoan.setText(null);
        pass.setText(null);
        btnGroupChucVu.clearSelection();
        btnGroupGioiTinh.clearSelection();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void rbtnNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNamActionPerformed
        // TODO add your handling code here:
        gioiTinh = "Nam";
    }//GEN-LAST:event_rbtnNamActionPerformed

    private void rbtnNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNuActionPerformed
        // TODO add your handling code here:
        gioiTinh = "Nu";
    }//GEN-LAST:event_rbtnNuActionPerformed

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
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPasswordField pass;
    private javax.swing.JRadioButton rbtnBacSi;
    private javax.swing.JRadioButton rbtnNam;
    private javax.swing.JRadioButton rbtnNhanVien;
    private javax.swing.JRadioButton rbtnNu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTuoi;
    // End of variables declaration//GEN-END:variables
}
