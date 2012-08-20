/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * dlgAddRoom_Bed.java
 *
 * Created on Aug 20, 2012, 3:32:59 PM
 */
package patientinfo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author tug
 */
public class dlgAddRoom_Bed extends javax.swing.JDialog {

    /** Creates new form dlgAddRoom_Bed */
    Connection conn = null;

    public dlgAddRoom_Bed(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PI", "sa", "123456");
        } catch (Exception e) {
        }
        cbbRoom.setModel(getListRoom());
        cbbRoomD.setModel(getListRoom());
        cbbBedDel.setModel(getListBed());
    }

    public DefaultComboBoxModel getListRoom() {
        DefaultComboBoxModel listRoom = new DefaultComboBoxModel();
        try {

            String sSelect = "Select id from tblRoom";
            PreparedStatement pstmt = conn.prepareStatement(sSelect);
            ResultSet rs = pstmt.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    listRoom.addElement(rs.getObject(i).toString());
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listRoom;
    }

    public void deleteRoom() {
        int check = 0;
        int room = Integer.parseInt(cbbRoomD.getSelectedItem().toString());
        try {

            String sSelect = "Select Stt from tblBed where Room = ?";
            PreparedStatement pstmt = conn.prepareStatement(sSelect);
            pstmt.setInt(1, room);
            ResultSet rs = pstmt.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    if (rs.getObject(i).toString().equals("Using")) {
                        check = 1;
                    }
                }
            }
            if (check == 1) {
                JOptionPane.showMessageDialog(this, "Room is using!!!");
            } else {
                String sDel = "Delete  from tblBed where Room = ?";
                PreparedStatement pstmtd = conn.prepareStatement(sDel);
                pstmtd.setInt(1, room);
                pstmtd.executeUpdate();
                JOptionPane.showMessageDialog(this, "Remove Success!");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteBed() {
        int check = 0;
        int bed = Integer.parseInt(cbbBedDel.getSelectedItem().toString());
        try {

            String sSelect = "Select Stt from tblBed where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sSelect);
            pstmt.setInt(1, bed);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals("Using")) {
                    check = 1;
                }
            }
            if (check == 1) {
                JOptionPane.showMessageDialog(this, "Bed is using!!!");
            } else {
                String sDel = "Delete  from tblBed where id = ?";
                PreparedStatement pstmtd = conn.prepareStatement(sDel);
                pstmtd.setInt(1, bed);
                pstmtd.executeUpdate();
                JOptionPane.showMessageDialog(this, "Remove Success!");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public DefaultComboBoxModel getListBed() {
        DefaultComboBoxModel listbed = new DefaultComboBoxModel();
        try {

            String sSelect = "Select id from tblbed";
            PreparedStatement pstmt = conn.prepareStatement(sSelect);
            ResultSet rs = pstmt.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    listbed.addElement(rs.getObject(i).toString());
                }
            }
            if (!rs.next()) {
                listbed.addElement("Empty bed");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listbed;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnAddBed = new javax.swing.JButton();
        cbbRoom = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAddRoom = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnDelBed = new javax.swing.JButton();
        cbbBedDel = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbbRoomD = new javax.swing.JComboBox();
        btnDelRoom = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bed1.png"))); // NOI18N
        jLabel1.setText("Add, Remove Room _ Bed");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Add"));

        btnAddBed.setText("Add Bed");
        btnAddBed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBedActionPerformed(evt);
            }
        });

        cbbRoom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "(empty)" }));
        cbbRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbRoomActionPerformed(evt);
            }
        });

        jLabel3.setText("Add bed:");

        jLabel2.setText("Add room:");

        btnAddRoom.setText("Add room");
        btnAddRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRoomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbRoom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddRoom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddBed)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnAddRoom))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbbRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddBed))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Remove"));

        btnDelBed.setText("Remove");
        btnDelBed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelBedActionPerformed(evt);
            }
        });

        cbbBedDel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "(empty)" }));
        cbbBedDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbBedDelActionPerformed(evt);
            }
        });

        jLabel4.setText("Delete Bed:");

        jLabel5.setText("Delete Room:");

        cbbRoomD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "(empty)" }));
        cbbRoomD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbRoomDActionPerformed(evt);
            }
        });

        btnDelRoom.setText("Remove");
        btnDelRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelRoomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbRoomD, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbBedDel, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelBed))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelRoom)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbRoomD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelRoom))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbBedDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelBed))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRoomActionPerformed
        // TODO add your handling code here:
        try {
            CallableStatement cs = conn.prepareCall("{call addRoom}");
            cs.executeUpdate();
            JOptionPane.showMessageDialog(this, "Add Success!!!");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAddRoomActionPerformed

    private void cbbRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbRoomActionPerformed

    private void btnAddBedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBedActionPerformed
        // TODO add your handling code here:
        try {
            CallableStatement cs = conn.prepareCall("{call addBed(?)}");
            cs.setString(1, cbbRoom.getSelectedItem().toString());
            cs.executeUpdate();
            JOptionPane.showMessageDialog(this, "Add Success!!!");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAddBedActionPerformed

    private void btnDelBedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelBedActionPerformed
        // TODO add your handling code here:
        deleteBed();
    }//GEN-LAST:event_btnDelBedActionPerformed

    private void cbbBedDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbBedDelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbBedDelActionPerformed

    private void cbbRoomDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbRoomDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbRoomDActionPerformed

    private void btnDelRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelRoomActionPerformed
        // TODO add your handling code here:
        deleteRoom();
    }//GEN-LAST:event_btnDelRoomActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                dlgAddRoom_Bed dialog = new dlgAddRoom_Bed(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAddBed;
    private javax.swing.JButton btnAddRoom;
    private javax.swing.JButton btnDelBed;
    private javax.swing.JButton btnDelRoom;
    private javax.swing.JComboBox cbbBedDel;
    private javax.swing.JComboBox cbbRoom;
    private javax.swing.JComboBox cbbRoomD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
