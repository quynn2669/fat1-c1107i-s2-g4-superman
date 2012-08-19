package patientinfo;

import DateTime.DateTimeTDV;
import java.awt.CardLayout;
import java.sql.*;
import java.util.Vector;
import java.util.jar.Attributes.Name;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author tug
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    DefaultTableModel model = null;
    Connection conn = null;
    ResultSet rs = null;
    String stt = "Yes";
    int choice = 0;
    int iID = 0;
    String Department = "Internal medicine";
    String FName = "";
    String DateF = "";
    String DateT = "";
    String InHospital = "";
    String Gender = "";
    int addAge = 0;
    public Patient patient = new Patient();
    int DrSTT = 0;

    public Main() {
        initComponents();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PI", "sa", "123456");
        } catch (Exception e) {
        }
        loadView();

    }

    public void loadView() {
        Reset();
        DateTimeTDV dateTDV = new DateTimeTDV();
        cbbSYearF.setModel(dateTDV.getListYear());
        cbbSYearT.setModel(dateTDV.getListYear());


    }

    public void Reset() {
        try {
            model = new DefaultTableModel();
            tblResult.setModel(model);
            tblRSL.setModel(model);
            tblRSE.setModel(model);
            txtName.setText(null);
            txtNameE.setText(null);
            txtNameL.setText(null);
            cbxByDate.setSelected(false);
            cbxByDepartment.setSelected(false);
            cbxByIn.setSelected(false);
            cbxByName.setSelected(false);
            cbxED.setSelected(false);
            cbxEN.setSelected(false);
            cbxLD.setSelected(false);
            cbxLN.setSelected(false);
        } catch (Exception e) {
            int vv = 0;
        }
        choice = 0;
        iID = 0;
        Department = "Internal medicine";
        FName = "";
        DateF = "";
        DateT = "";
        Gender = "";
        addAge = 0;
        btgAddGender.clearSelection();
    }

    public void Checklogin(String acc, String pass, String role) {
        if (acc.equals("admin") && pass.equals("admin")) {
            role = "admin";
            mnEmp.setEnabled(true);
            mnDr.setEnabled(true);
            mnAd.setEnabled(true);
            CardLayout cc = (CardLayout) pnlMain.getLayout();
            cc.show(pnlMain, "cardSearch");
            lblAccS.setText(acc);
            lblAccS1.setText(acc);
            lblAccS2.setText(acc);
            lblRole.setText(role);
            lblRole1.setText(role);
            lblRole2.setText(role);
            JOptionPane.showMessageDialog(this, "Login success!\nRole: Admin");
            txtAccount.setText(null);
            password.setText(null);
            btgRole.clearSelection();

        } else {
            try {
                String sSelect = "SELECT password,role FROM tblAccount WHERE Account = ? ";
                PreparedStatement pstmt = conn.prepareStatement(sSelect);
                pstmt.setString(1, acc);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    if (rs.getString(2) != role) {
                        JOptionPane.showMessageDialog(this, "Account or Password Wrong!!");
                    } else {
                        if (role == "Employee") {
                            mnEmp.setEnabled(true);
                            mnDr.setEnabled(false);
                            mnAd.setEnabled(false);
                            CardLayout cc = (CardLayout) pnlMain.getLayout();
                            cc.show(pnlMain, "cardSearch");
                            lblAccS.setText(acc);
                            lblAccS1.setText(acc);
                            lblAccS2.setText(acc);
                            lblRole.setText(role);
                            lblRole1.setText(role);
                            lblRole2.setText(role);
                        } else if (role == "Doctor") {
                            mnEmp.setEnabled(false);
                            mnDr.setEnabled(true);
                            mnAd.setEnabled(false);
                            CardLayout cc = (CardLayout) pnlMain.getLayout();
                            cc.show(pnlMain, "cardExamine");
                        }
                        JOptionPane.showMessageDialog(this, "Login success!\nRole: " + role);
                        txtAccount.setText(null);
                        password.setText(null);
                        btgRole.clearSelection();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Account or Password Wrong!!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setDetailsPatient() {

        tblResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblResult.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = tblResult.getSelectedRow();
                    int realIndex = tblResult.convertRowIndexToModel(selectedIndex);
                    TableModel model = tblResult.getModel();
                    dlgUpdate dialog = new dlgUpdate(new Main(), true);
                    dialog.setTxtID(model.getValueAt(realIndex, 0).toString());
                    dialog.setTxtName(model.getValueAt(realIndex, 1).toString());
                    dialog.setTxtAD(model.getValueAt(realIndex, 2).toString());
                    dialog.setTxtAge(model.getValueAt(realIndex, 3).toString());
                    dialog.setRbtMale(model.getValueAt(realIndex, 4).toString());
                    dialog.setCbbDepartment(model.getValueAt(realIndex, 7).toString());
                    dialog.setaDes(model.getValueAt(realIndex, 5).toString());
                    dialog.setaSick(model.getValueAt(realIndex, 6).toString());
                    dialog.setTxtDoctor(model.getValueAt(realIndex, 8).toString());
                    dialog.setTxtDateIn(model.getValueAt(realIndex, 9).toString());
                    dialog.setTxtDateOut(model.getValueAt(realIndex, 10).toString());
                    dialog.setTxtInHospital(model.getValueAt(realIndex, 11).toString());
                    selectedIndex = 0;
                    dialog.setVisible(true);
                    selectedIndex = 0;
//                    JOptionPane.showMessageDialog(pnlMain, patient.toString());
                }
            }
        });

    }

    public void setValuePatientExm() {

        tblRSE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblRSE.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = tblRSE.getSelectedRow();
                    int realIndex = tblRSE.convertRowIndexToModel(selectedIndex);
                    TableModel model = tblRSE.getModel();
                    dlgExamine dialog = new dlgExamine(new Main(), true);
                    dialog.setTxtID(model.getValueAt(realIndex, 0).toString());
                    dialog.setTxtName(model.getValueAt(realIndex, 1).toString());
                    dialog.setTxtAD(model.getValueAt(realIndex, 2).toString());
                    dialog.setTxtAge(model.getValueAt(realIndex, 3).toString());
                    dialog.setTxtGender(model.getValueAt(realIndex, 4).toString());
                    dialog.setCbbDepartment(model.getValueAt(realIndex, 7).toString());
                    dialog.setaDes(model.getValueAt(realIndex, 5).toString());
                    dialog.setTxtDoctor(model.getValueAt(realIndex, 8).toString());
                    dialog.setTxtDateI(model.getValueAt(realIndex, 9).toString());
                    selectedIndex = 0;
                    dialog.setVisible(true);
                    selectedIndex = 0;
//                    JOptionPane.showMessageDialog(pnlMain, patient.toString());
                }
            }
        });

    }

    public void setValuePatientIH() {

        tblRSL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblRSL.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = tblRSL.getSelectedRow();
                    int realIndex = tblRSL.convertRowIndexToModel(selectedIndex);
                    TableModel model = tblRSL.getModel();
                    dlgCheck dialog = new dlgCheck(new Main(), true);
                    dialog.settxtIDP(model.getValueAt(realIndex, 0).toString());
                    dialog.settxtNameP(model.getValueAt(realIndex, 1).toString());
                    dialog.settxtGender(model.getValueAt(realIndex, 4).toString());
                    dialog.settxtDepartment(model.getValueAt(realIndex, 7).toString());
                    dialog.settxtSick(model.getValueAt(realIndex, 6).toString());
                    dialog.settxtDes(model.getValueAt(realIndex, 5).toString());
                    dialog.settxtDateIn(model.getValueAt(realIndex, 9).toString());
                    dialog.settxtDateOut(model.getValueAt(realIndex, 10).toString());
                    selectedIndex = 0;
                    dialog.setVisible(true);
                    selectedIndex = 0;
//                    JOptionPane.showMessageDialog(pnlMain, patient.toString());
                }
            }
        });

    }

    public void allPatient() {
        try {

            CallableStatement cs = conn.prepareCall("{call selectAll}");
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());

                }
                model.addRow(v);

            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void allPatientIH() {
        try {

            CallableStatement cs = conn.prepareCall("{call selectAllIH}");
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());

                }
                model.addRow(v);

            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void searchByID() {
        try {
            CallableStatement cs = conn.prepareCall("{call findByID(?)}");
            cs.setInt(1, iID);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByName(String FName) {
        try {
            CallableStatement cs = conn.prepareCall("{call findByName(?)}");
            cs.setString(1, FName);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByIn() {
        try {
            CallableStatement cs = conn.prepareCall("{call findByIn(?)}");
            cs.setString(1, stt);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByDateIn() {
        try {
            CallableStatement cs = conn.prepareCall("{call findByDateIn(?,?)}");
            cs.setString(1, DateF);
            cs.setString(2, DateT);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByDepartment() {
        try {
            CallableStatement cs = conn.prepareCall("{call findByDepartment(?)}");
            cs.setString(1, Department);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy1And2() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy1And2(?,?)}");
            cs.setString(1, FName);
            cs.setString(2, Department);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy12And4() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy12And4(?,?,?,?)}");
            cs.setString(1, FName);
            cs.setString(2, Department);
            cs.setString(3, DateF);
            cs.setString(4, DateT);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy12And3() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy12And3(?,?,?)}");
            cs.setString(1, FName);
            cs.setString(2, Department);
            cs.setString(3, InHospital);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy123And4() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy123And4(?,?,?,?,?)}");
            cs.setString(1, FName);
            cs.setString(2, Department);
            cs.setString(3, InHospital);
            cs.setString(4, DateF);
            cs.setString(5, DateT);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy13And4() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy13And4(?,?,?,?)}");
            cs.setString(1, FName);
            cs.setString(2, InHospital);
            cs.setString(3, DateF);
            cs.setString(4, DateT);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy1And3() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy1And3(?,?)}");
            cs.setString(1, FName);
            cs.setString(2, InHospital);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy1And4() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy1And4(?,?,?)}");
            cs.setString(1, FName);
            cs.setString(2, DateF);
            cs.setString(3, DateT);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy2And3() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy2And3(?,?)}");
            cs.setString(1, Department);
            cs.setString(2, InHospital);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy23And4() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy23And4(?,?,?,?)}");
            cs.setString(1, Department);
            cs.setString(2, InHospital);
            cs.setString(3, DateF);
            cs.setString(4, DateT);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy2And4() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy2And4(?,?,?)}");
            cs.setString(1, Department);
            cs.setString(2, DateF);
            cs.setString(3, DateT);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy3And4() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy3And4(?,?,?)}");
            cs.setString(1, InHospital);
            cs.setString(2, DateF);
            cs.setString(3, DateT);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void allPatientEx() {
        try {

            CallableStatement cs = conn.prepareCall("{call selectAllEx(?)}");
            cs.setInt(1, DrSTT);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());

                }
                model.addRow(v);

            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void searchByNameEx() {
        try {
            CallableStatement cs = conn.prepareCall("{call findByNameEx(?,?)}");
            cs.setString(1, FName);
            cs.setInt(2, DrSTT);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByNameIH() {
        try {
            CallableStatement cs = conn.prepareCall("{call findByNameIH(?)}");
            cs.setString(1, FName);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByDepartmentEx() {
        try {
            CallableStatement cs = conn.prepareCall("{call findByDepartmentEx(?,?)}");
            cs.setString(1, Department);
            cs.setInt(2, DrSTT);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByDepartmentIH() {
        try {
            CallableStatement cs = conn.prepareCall("{call findByDepartmentIH(?)}");
            cs.setString(1, Department);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy1And2Ex() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy1And2Ex(?,?,?)}");
            cs.setString(1, FName);
            cs.setString(2, Department);
            cs.setInt(3, DrSTT);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBy1And2IH() {
        try {
            CallableStatement cs = conn.prepareCall("{call findBy1And2IH(?,?)}");
            cs.setString(1, FName);
            cs.setString(2, Department);
            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    v.addElement(rs.getObject(i).toString());
                }
                model.addRow(v);
            }


            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchPatientEmp() {
        if (!cbxByName.isSelected() && !cbxByDate.isSelected() && !cbxByDepartment.isSelected() && !cbxByIn.isSelected()) {
            JOptionPane.showMessageDialog(this, "Choice Search Option!!!");
        } else if (cbxByName.isSelected() && !cbxByDate.isSelected() && !cbxByDepartment.isSelected() && !cbxByIn.isSelected()) {
            if (txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else {
                FName = txtName.getText();
                searchByName(FName);
                lblTTDT.setText("" + tblResult.getRowCount());
                setDetailsPatient();
            }
        } else if (cbxByName.isSelected() && !cbxByDate.isSelected() && cbxByDepartment.isSelected() && !cbxByIn.isSelected()) {
            if (txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else {
                FName = txtName.getText();
                Department = cbbSDepartment.getSelectedItem().toString();
                searchBy1And2();
                lblTTDT.setText("" + tblResult.getRowCount());
                setDetailsPatient();
            }
        } else if (cbxByName.isSelected() && cbxByDate.isSelected() && !cbxByDepartment.isSelected() && !cbxByIn.isSelected()) {
            if (txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else {
                FName = txtName.getText();
                DateF = cbbSMonthF.getSelectedItem().toString() + "/" + cbbSDayF.getSelectedItem().toString() + "/" + cbbSYearF.getSelectedItem().toString();
                DateT = cbbSMonthT.getSelectedItem().toString() + "/" + cbbSDayT.getSelectedItem().toString() + "/" + cbbSYearT.getSelectedItem().toString();
                searchBy1And4();
                lblTTDT.setText("" + tblResult.getRowCount());
                setDetailsPatient();
            }
        } else if (cbxByName.isSelected() && cbxByDate.isSelected() && cbxByDepartment.isSelected() && !cbxByIn.isSelected()) {
            if (txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else {
                FName = txtName.getText();
                Department = cbbSDepartment.getSelectedItem().toString();
                DateF = cbbSMonthF.getSelectedItem().toString() + "/" + cbbSDayF.getSelectedItem().toString() + "/" + cbbSYearF.getSelectedItem().toString();
                DateT = cbbSMonthT.getSelectedItem().toString() + "/" + cbbSDayT.getSelectedItem().toString() + "/" + cbbSYearT.getSelectedItem().toString();
                searchBy12And4();
                lblTTDT.setText("" + tblResult.getRowCount());
                setDetailsPatient();
            }
        } else if (cbxByName.isSelected() && cbxByDate.isSelected() && cbxByDepartment.isSelected() && cbxByIn.isSelected()) {
            if (txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else {
                FName = txtName.getText();
                Department = cbbSDepartment.getSelectedItem().toString();
                DateF = cbbSMonthF.getSelectedItem().toString() + "/" + cbbSDayF.getSelectedItem().toString() + "/" + cbbSYearF.getSelectedItem().toString();
                DateT = cbbSMonthT.getSelectedItem().toString() + "/" + cbbSDayT.getSelectedItem().toString() + "/" + cbbSYearT.getSelectedItem().toString();
                InHospital = cbbSIn.getSelectedItem().toString();
                searchBy123And4();
                lblTTDT.setText("" + tblResult.getRowCount());
                setDetailsPatient();
            }
        } else if (cbxByName.isSelected() && !cbxByDate.isSelected() && cbxByDepartment.isSelected() && cbxByIn.isSelected()) {
            if (txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else {
                FName = txtName.getText();
                Department = cbbSDepartment.getSelectedItem().toString();
                InHospital = cbbSIn.getSelectedItem().toString();
                searchBy12And3();
                lblTTDT.setText("" + tblResult.getRowCount());
                setDetailsPatient();
            }
        } else if (cbxByName.isSelected() && cbxByDate.isSelected() && !cbxByDepartment.isSelected() && cbxByIn.isSelected()) {
            if (txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else {
                FName = txtName.getText();
                DateF = cbbSMonthF.getSelectedItem().toString() + "/" + cbbSDayF.getSelectedItem().toString() + "/" + cbbSYearF.getSelectedItem().toString();
                DateT = cbbSMonthT.getSelectedItem().toString() + "/" + cbbSDayT.getSelectedItem().toString() + "/" + cbbSYearT.getSelectedItem().toString();
                InHospital = cbbSIn.getSelectedItem().toString();
                searchBy13And4();
                lblTTDT.setText("" + tblResult.getRowCount());
                setDetailsPatient();
            }
        } else if (cbxByName.isSelected() && !cbxByDate.isSelected() && !cbxByDepartment.isSelected() && cbxByIn.isSelected()) {
            if (txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else {
                FName = txtName.getText();
                InHospital = cbbSIn.getSelectedItem().toString();
                searchBy1And3();
                lblTTDT.setText("" + tblResult.getRowCount());
                setDetailsPatient();
            }
        } else if (!cbxByName.isSelected() && !cbxByDate.isSelected() && cbxByDepartment.isSelected() && cbxByIn.isSelected()) {
            Department = cbbSDepartment.getSelectedItem().toString();
            InHospital = cbbSIn.getSelectedItem().toString();
            searchBy2And3();
            lblTTDT.setText("" + tblResult.getRowCount());
            setDetailsPatient();
        } else if (!cbxByName.isSelected() && cbxByDate.isSelected() && cbxByDepartment.isSelected() && cbxByIn.isSelected()) {
            Department = cbbSDepartment.getSelectedItem().toString();
            DateF = cbbSMonthF.getSelectedItem().toString() + "/" + cbbSDayF.getSelectedItem().toString() + "/" + cbbSYearF.getSelectedItem().toString();
            DateT = cbbSMonthT.getSelectedItem().toString() + "/" + cbbSDayT.getSelectedItem().toString() + "/" + cbbSYearT.getSelectedItem().toString();
            InHospital = cbbSIn.getSelectedItem().toString();
            searchBy23And4();
            lblTTDT.setText("" + tblResult.getRowCount());
            setDetailsPatient();
        } else if (!cbxByName.isSelected() && cbxByDate.isSelected() && cbxByDepartment.isSelected() && !cbxByIn.isSelected()) {
            Department = cbbSDepartment.getSelectedItem().toString();
            DateF = cbbSMonthF.getSelectedItem().toString() + "/" + cbbSDayF.getSelectedItem().toString() + "/" + cbbSYearF.getSelectedItem().toString();
            DateT = cbbSMonthT.getSelectedItem().toString() + "/" + cbbSDayT.getSelectedItem().toString() + "/" + cbbSYearT.getSelectedItem().toString();
            searchBy2And4();
            lblTTDT.setText("" + tblResult.getRowCount());
            setDetailsPatient();
        } else if (!cbxByName.isSelected() && cbxByDate.isSelected() && !cbxByDepartment.isSelected() && cbxByIn.isSelected()) {
            DateF = cbbSMonthF.getSelectedItem().toString() + "/" + cbbSDayF.getSelectedItem().toString() + "/" + cbbSYearF.getSelectedItem().toString();
            DateT = cbbSMonthT.getSelectedItem().toString() + "/" + cbbSDayT.getSelectedItem().toString() + "/" + cbbSYearT.getSelectedItem().toString();
            InHospital = cbbSIn.getSelectedItem().toString();
            searchBy3And4();
            lblTTDT.setText("" + tblResult.getRowCount());
            setDetailsPatient();
        } else if (!cbxByName.isSelected() && !cbxByDate.isSelected() && cbxByDepartment.isSelected() && !cbxByIn.isSelected()) {
            Department = cbbSDepartment.getSelectedItem().toString();
            searchByDepartment();
            lblTTDT.setText("" + tblResult.getRowCount());
            setDetailsPatient();
        } else if (!cbxByName.isSelected() && !cbxByDate.isSelected() && !cbxByDepartment.isSelected() && cbxByIn.isSelected()) {
            InHospital = cbbSIn.getSelectedItem().toString();
            searchByIn();
            lblTTDT.setText("" + tblResult.getRowCount());
            setDetailsPatient();
        } else if (!cbxByName.isSelected() && cbxByDate.isSelected() && !cbxByDepartment.isSelected() && !cbxByIn.isSelected()) {
            DateF = cbbSMonthF.getSelectedItem().toString() + "/" + cbbSDayF.getSelectedItem().toString() + "/" + cbbSYearF.getSelectedItem().toString();
            DateT = cbbSMonthT.getSelectedItem().toString() + "/" + cbbSDayT.getSelectedItem().toString() + "/" + cbbSYearT.getSelectedItem().toString();
            searchByDateIn();
            lblTTDT.setText("" + tblResult.getRowCount());
            setDetailsPatient();
        }
    }

    public void searchPatientExam() {
        if (!cbxEN.isSelected() && !cbxED.isSelected()) {
            JOptionPane.showMessageDialog(this, "Choice Search Option!!!");
        } else if (cbxEN.isSelected() && !cbxED.isSelected()) {
            if (txtNameE.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else {
                FName = txtNameE.getText();
                searchByNameEx();
                lblTTE.setText("" + tblRSE.getRowCount());
            }
        } else if (cbxEN.isSelected() && cbxED.isSelected()) {
            if (txtNameE.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else {
                FName = txtNameE.getText();
                Department = cbbEDepartment.getSelectedItem().toString();
                searchBy1And2Ex();
                lblTTE.setText("" + tblRSE.getRowCount());
            }
        } else if (!cbxEN.isSelected() && cbxED.isSelected()) {
            Department = cbbEDepartment.getSelectedItem().toString();
            searchByDepartmentEx();
            lblTTE.setText("" + tblRSE.getRowCount());
        }

    }

    public void searchPatientIH() {
        if (!cbxLN.isSelected() && !cbxLD.isSelected()) {
            JOptionPane.showMessageDialog(this, "Choice Search Option!!!");
        } else if (cbxLN.isSelected() && !cbxLD.isSelected()) {
            if (txtNameL.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else {
                FName = txtNameL.getText();
                searchByNameIH();
                lblTTL.setText("" + tblRSL.getRowCount());
            }
        } else if (cbxLN.isSelected() && cbxLD.isSelected()) {
            if (txtNameL.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Name!!!");
            } else {
                FName = txtNameL.getText();
                Department = cbbLDepartment.getSelectedItem().toString();
                searchBy1And2IH();
                lblTTL.setText("" + tblRSL.getRowCount());
            }
        } else if (!cbxLN.isSelected() && cbxLD.isSelected()) {
            Department = cbbLDepartment.getSelectedItem().toString();
            searchByDepartmentIH();
            lblTTE.setText("" + tblRSL.getRowCount());
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

        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btgAddGender = new javax.swing.ButtonGroup();
        btgGenderU = new javax.swing.ButtonGroup();
        btgRole = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        pnlLogin = new javax.swing.JPanel();
        pnlLogin1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtAccount = new javax.swing.JTextField();
        rbtDr = new javax.swing.JRadioButton();
        rbtEmp = new javax.swing.JRadioButton();
        password = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnResetLogin = new javax.swing.JButton();
        admin = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        pnlAbout = new javax.swing.JPanel();
        pnlContent2 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        pnlPatientInfomation = new javax.swing.JPanel();
        pnlContent = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResult = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        lblTTDT = new javax.swing.JLabel();
        pnlChoice = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbSDayF = new javax.swing.JComboBox();
        cbbSMonthF = new javax.swing.JComboBox();
        cbbSYearF = new javax.swing.JComboBox();
        cbbSDayT = new javax.swing.JComboBox();
        cbbSMonthT = new javax.swing.JComboBox();
        cbbSYearT = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbbSIn = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        cbbSDepartment = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        pnlRole = new javax.swing.JPanel();
        xxx = new javax.swing.JLabel();
        a = new javax.swing.JLabel();
        lblAccS = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        cbxByDepartment = new javax.swing.JCheckBox();
        cbxByName = new javax.swing.JCheckBox();
        cbxByIn = new javax.swing.JCheckBox();
        cbxByDate = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        pnlManager = new javax.swing.JPanel();
        pnlContent3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRSL = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        lblTTL = new javax.swing.JLabel();
        pnlChoice2 = new javax.swing.JPanel();
        btnSearchL = new javax.swing.JButton();
        btnReset2 = new javax.swing.JButton();
        btnAllL = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txtNameL = new javax.swing.JTextField();
        cbbLDepartment = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        pnlRole2 = new javax.swing.JPanel();
        xxx2 = new javax.swing.JLabel();
        a2 = new javax.swing.JLabel();
        lblAccS2 = new javax.swing.JLabel();
        lblRole2 = new javax.swing.JLabel();
        cbxLD = new javax.swing.JCheckBox();
        cbxLN = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        pnlExamine = new javax.swing.JPanel();
        pnlContent1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRSE = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        lblTTE = new javax.swing.JLabel();
        pnlChoice1 = new javax.swing.JPanel();
        btnSearchE = new javax.swing.JButton();
        btnReset1 = new javax.swing.JButton();
        btnAllE = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtNameE = new javax.swing.JTextField();
        cbbEDepartment = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        pnlRole1 = new javax.swing.JPanel();
        xxx1 = new javax.swing.JLabel();
        a1 = new javax.swing.JLabel();
        lblAccS1 = new javax.swing.JLabel();
        lblRole1 = new javax.swing.JLabel();
        cbxED = new javax.swing.JCheckBox();
        cbxEN = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnEmp = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        mnDr = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        mnAd = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("System Patient Info");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImages(null);
        setResizable(false);

        pnlMain.setBackground(new java.awt.Color(-1,true));
        pnlMain.setPreferredSize(new java.awt.Dimension(1205, 800));
        pnlMain.setLayout(new java.awt.CardLayout());

        pnlLogin1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel13.setText("Account:");

        jLabel14.setText("Password:");

        jLabel19.setText("Role:");

        txtAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAccountActionPerformed(evt);
            }
        });

        btgRole.add(rbtDr);
        rbtDr.setText("Doctor");
        rbtDr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtDrActionPerformed(evt);
            }
        });

        btgRole.add(rbtEmp);
        rbtEmp.setText("Employee");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnResetLogin.setText("Reset");
        btnResetLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetLoginActionPerformed(evt);
            }
        });

        btgRole.add(admin);
        admin.setText("admin");
        admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLogin1Layout = new javax.swing.GroupLayout(pnlLogin1);
        pnlLogin1.setLayout(pnlLogin1Layout);
        pnlLogin1Layout.setHorizontalGroup(
            pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogin1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(password)
                    .addGroup(pnlLogin1Layout.createSequentialGroup()
                        .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtDr)
                            .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlLogin1Layout.createSequentialGroup()
                                .addComponent(rbtEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(admin))
                            .addGroup(pnlLogin1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(btnResetLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        pnlLogin1Layout.setVerticalGroup(
            pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogin1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(rbtDr)
                    .addComponent(admin)
                    .addComponent(rbtEmp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnResetLogin))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\user-login-icon.png")); // NOI18N
        jLabel3.setText("Login");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Patient Info System");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(437, 437, 437)
                        .addComponent(jLabel3))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(470, 470, 470)
                        .addComponent(jLabel33)))
                .addContainerGap(471, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(pnlLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(337, 337, 337))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(pnlLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        pnlMain.add(pnlLogin, "cardLogin");

        pnlAbout.setBackground(new java.awt.Color(-328966,true));
        pnlAbout.setLayout(new java.awt.BorderLayout());

        pnlContent2.setPreferredSize(new java.awt.Dimension(740, 600));

        jLabel20.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Comics-Older-Superman-icon.png")); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel31.setText("Patient Info System");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel32.setText("Created by a group Superman");

        javax.swing.GroupLayout pnlContent2Layout = new javax.swing.GroupLayout(pnlContent2);
        pnlContent2.setLayout(pnlContent2Layout);
        pnlContent2Layout.setHorizontalGroup(
            pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContent2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContent2Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(441, 441, 441))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContent2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(404, 404, 404))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContent2Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(264, 264, 264))))
        );
        pnlContent2Layout.setVerticalGroup(
            pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContent2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel20)
                .addGap(40, 40, 40)
                .addComponent(jLabel31)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        pnlAbout.add(pnlContent2, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlAbout, "cardAbout");

        pnlPatientInfomation.setBackground(new java.awt.Color(-1,true));
        pnlPatientInfomation.setLayout(new java.awt.BorderLayout());

        pnlContent.setPreferredSize(new java.awt.Dimension(740, 600));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane1.setBackground(new java.awt.Color(-1,true));

        tblResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResultMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblResult);

        jLabel25.setText("Patient's Total:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTTDT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(614, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(lblTTDT, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlChoice.setForeground(new java.awt.Color(-8355712,true));
        pnlChoice.setPreferredSize(new java.awt.Dimension(330, 599));

        btnSearch.setText("Search");
        btnSearch.setPreferredSize(new java.awt.Dimension(125, 23));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.setPreferredSize(new java.awt.Dimension(125, 23));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton1.setText("All Patient");
        jButton1.setPreferredSize(new java.awt.Dimension(270, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("From");

        jLabel2.setText("To");

        cbbSDayF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cbbSMonthF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbbSMonthF.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSMonthFItemStateChanged(evt);
            }
        });
        cbbSMonthF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSMonthFActionPerformed(evt);
            }
        });

        cbbSYearF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012" }));
        cbbSYearF.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSYearFItemStateChanged(evt);
            }
        });
        cbbSYearF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSYearFActionPerformed(evt);
            }
        });

        cbbSDayT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cbbSMonthT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbbSMonthT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSMonthTItemStateChanged(evt);
            }
        });

        cbbSYearT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", " " }));
        cbbSYearT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSYearTItemStateChanged(evt);
            }
        });

        jLabel6.setText("Day");

        jLabel7.setText("Day");

        jLabel9.setText("Month");

        jLabel10.setText("Month");

        jLabel11.setText("Year");

        jLabel12.setText("Year");

        cbbSIn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        cbbSIn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSInItemStateChanged(evt);
            }
        });

        jLabel8.setText("In Hospital");

        jLabel4.setText("Name");

        cbbSDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Internal medicine", "Surgical", "Cardiovascular" }));
        cbbSDepartment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbSDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSDepartmentItemStateChanged(evt);
            }
        });

        jLabel5.setText("Department");

        pnlRole.setBorder(javax.swing.BorderFactory.createTitledBorder("Role"));

        xxx.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Keys-icon.png")); // NOI18N
        xxx.setText("Account :");

        a.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Actions-irc-operator-icon.png")); // NOI18N
        a.setText("Role :");

        lblAccS.setText("...");

        lblRole.setText("...");

        javax.swing.GroupLayout pnlRoleLayout = new javax.swing.GroupLayout(pnlRole);
        pnlRole.setLayout(pnlRoleLayout);
        pnlRoleLayout.setHorizontalGroup(
            pnlRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRoleLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnlRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlRoleLayout.createSequentialGroup()
                        .addComponent(a)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRole))
                    .addGroup(pnlRoleLayout.createSequentialGroup()
                        .addComponent(xxx)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAccS)))
                .addContainerGap(194, Short.MAX_VALUE))
        );
        pnlRoleLayout.setVerticalGroup(
            pnlRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRoleLayout.createSequentialGroup()
                .addGroup(pnlRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xxx)
                    .addComponent(lblAccS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(a)
                    .addComponent(lblRole)))
        );

        cbxByDepartment.setText("Search By Department");

        cbxByName.setText("Search By Name");
        cbxByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxByNameActionPerformed(evt);
            }
        });

        cbxByIn.setText("Search By In Hospital");
        cbxByIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxByInActionPerformed(evt);
            }
        });

        cbxByDate.setText("Search By Date");
        cbxByDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxByDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlChoiceLayout = new javax.swing.GroupLayout(pnlChoice);
        pnlChoice.setLayout(pnlChoiceLayout);
        pnlChoiceLayout.setHorizontalGroup(
            pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxByIn)
                    .addComponent(cbxByName)
                    .addComponent(pnlRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlChoiceLayout.createSequentialGroup()
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(3, 3, 3))
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbSDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbSIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChoiceLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlChoiceLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbSYearT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbSMonthT, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbSDayT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlChoiceLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbSYearF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbSMonthF, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbSDayF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(cbxByDepartment)
                    .addComponent(cbxByDate))
                .addContainerGap())
        );
        pnlChoiceLayout.setVerticalGroup(
            pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceLayout.createSequentialGroup()
                .addComponent(pnlRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxByName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(cbxByDepartment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbSDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxByIn)
                .addGap(12, 12, 12)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbbSIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(cbxByDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbbSDayF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSMonthF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(cbbSYearF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbSYearT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cbbSMonthT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbbSDayT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(-6710887,true));

        jLabel26.setFont(new java.awt.Font("Dialog", 0, 36));
        jLabel26.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\search-b-icon.png")); // NOI18N
        jLabel26.setText("Search Patient ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(374, 374, 374)
                .addComponent(jLabel26)
                .addContainerGap(394, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addComponent(pnlChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContentLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlChoice, 0, 482, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlPatientInfomation.add(pnlContent, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlPatientInfomation, "cardSearch");

        pnlManager.setBackground(new java.awt.Color(-1,true));
        pnlManager.setLayout(new java.awt.BorderLayout());

        pnlContent3.setPreferredSize(new java.awt.Dimension(740, 600));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane3.setBackground(new java.awt.Color(-1,true));

        tblRSL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblRSL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRSLMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblRSL);

        jLabel29.setText("Patient's Total:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTTL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(614, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29)
                    .addComponent(lblTTL, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlChoice2.setForeground(new java.awt.Color(-8355712,true));
        pnlChoice2.setPreferredSize(new java.awt.Dimension(330, 599));

        btnSearchL.setText("Search");
        btnSearchL.setPreferredSize(new java.awt.Dimension(125, 23));
        btnSearchL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchLActionPerformed(evt);
            }
        });

        btnReset2.setText("Reset");
        btnReset2.setPreferredSize(new java.awt.Dimension(125, 23));
        btnReset2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset2ActionPerformed(evt);
            }
        });

        btnAllL.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnAllL.setText("All Patient");
        btnAllL.setPreferredSize(new java.awt.Dimension(270, 23));
        btnAllL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllLActionPerformed(evt);
            }
        });

        jLabel23.setText("Name");

        cbbLDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Internal medicine", "Surgical", "Cardiovascular" }));
        cbbLDepartment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbLDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLDepartmentItemStateChanged(evt);
            }
        });

        jLabel24.setText("Department");

        pnlRole2.setBorder(javax.swing.BorderFactory.createTitledBorder("Role"));

        xxx2.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Keys-icon.png")); // NOI18N
        xxx2.setText("Account :");

        a2.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Actions-irc-operator-icon.png")); // NOI18N
        a2.setText("Role :");

        lblAccS2.setText("...");

        lblRole2.setText("...");

        javax.swing.GroupLayout pnlRole2Layout = new javax.swing.GroupLayout(pnlRole2);
        pnlRole2.setLayout(pnlRole2Layout);
        pnlRole2Layout.setHorizontalGroup(
            pnlRole2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRole2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnlRole2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlRole2Layout.createSequentialGroup()
                        .addComponent(a2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRole2))
                    .addGroup(pnlRole2Layout.createSequentialGroup()
                        .addComponent(xxx2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAccS2)))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        pnlRole2Layout.setVerticalGroup(
            pnlRole2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRole2Layout.createSequentialGroup()
                .addGroup(pnlRole2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xxx2)
                    .addComponent(lblAccS2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRole2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(a2)
                    .addComponent(lblRole2)))
        );

        cbxLD.setText("Search By Department");

        cbxLN.setText("Search By Name");
        cbxLN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLNActionPerformed(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\blue_medical_symbol_pc_image_500_clr.png")); // NOI18N

        jLabel18.setText("Health care for all people");

        javax.swing.GroupLayout pnlChoice2Layout = new javax.swing.GroupLayout(pnlChoice2);
        pnlChoice2.setLayout(pnlChoice2Layout);
        pnlChoice2Layout.setHorizontalGroup(
            pnlChoice2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoice2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChoice2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChoice2Layout.createSequentialGroup()
                        .addGroup(pnlChoice2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxLD)
                            .addComponent(cbxLN)
                            .addGroup(pnlChoice2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlChoice2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlChoice2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(cbbLDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(pnlChoice2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlChoice2Layout.createSequentialGroup()
                            .addComponent(pnlRole2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(pnlChoice2Layout.createSequentialGroup()
                            .addGroup(pnlChoice2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlChoice2Layout.createSequentialGroup()
                                    .addComponent(btnSearchL, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnReset2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                                .addComponent(btnAllL, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGap(13, 13, 13)))))
            .addGroup(pnlChoice2Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel18)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        pnlChoice2Layout.setVerticalGroup(
            pnlChoice2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoice2Layout.createSequentialGroup()
                .addComponent(pnlRole2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbxLN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChoice2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtNameL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(cbxLD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChoice2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbLDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnAllL, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChoice2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(-6710887,true));

        jLabel30.setFont(new java.awt.Font("Dialog", 0, 36));
        jLabel30.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\App-edit-icon (1).png")); // NOI18N
        jLabel30.setText("Log Examine");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(393, 393, 393)
                .addComponent(jLabel30)
                .addContainerGap(403, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContent3Layout = new javax.swing.GroupLayout(pnlContent3);
        pnlContent3.setLayout(pnlContent3Layout);
        pnlContent3Layout.setHorizontalGroup(
            pnlContent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContent3Layout.createSequentialGroup()
                .addComponent(pnlChoice2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlContent3Layout.setVerticalGroup(
            pnlContent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContent3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlChoice2, 0, 482, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlManager.add(pnlContent3, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlManager, "cardManager");

        pnlExamine.setBackground(new java.awt.Color(-1,true));
        pnlExamine.setLayout(new java.awt.BorderLayout());

        pnlContent1.setPreferredSize(new java.awt.Dimension(740, 600));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane2.setBackground(new java.awt.Color(-1,true));

        tblRSE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblRSE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRSEMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblRSE);

        jLabel27.setText("Patient's Total:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTTE, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(614, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27)
                    .addComponent(lblTTE, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlChoice1.setForeground(new java.awt.Color(-8355712,true));
        pnlChoice1.setPreferredSize(new java.awt.Dimension(330, 599));

        btnSearchE.setText("Search");
        btnSearchE.setPreferredSize(new java.awt.Dimension(125, 23));
        btnSearchE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchEActionPerformed(evt);
            }
        });

        btnReset1.setText("Reset");
        btnReset1.setPreferredSize(new java.awt.Dimension(125, 23));
        btnReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset1ActionPerformed(evt);
            }
        });

        btnAllE.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnAllE.setText("All Patient");
        btnAllE.setPreferredSize(new java.awt.Dimension(270, 23));
        btnAllE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllEActionPerformed(evt);
            }
        });

        jLabel21.setText("Name");

        cbbEDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Internal medicine", "Surgical", "Cardiovascular" }));
        cbbEDepartment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbEDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbEDepartmentItemStateChanged(evt);
            }
        });

        jLabel22.setText("Department");

        pnlRole1.setBorder(javax.swing.BorderFactory.createTitledBorder("Role"));

        xxx1.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Keys-icon.png")); // NOI18N
        xxx1.setText("Account :");

        a1.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Actions-irc-operator-icon.png")); // NOI18N
        a1.setText("Role :");

        lblAccS1.setText("...");

        lblRole1.setText("...");

        javax.swing.GroupLayout pnlRole1Layout = new javax.swing.GroupLayout(pnlRole1);
        pnlRole1.setLayout(pnlRole1Layout);
        pnlRole1Layout.setHorizontalGroup(
            pnlRole1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRole1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnlRole1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlRole1Layout.createSequentialGroup()
                        .addComponent(a1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRole1))
                    .addGroup(pnlRole1Layout.createSequentialGroup()
                        .addComponent(xxx1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAccS1)))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        pnlRole1Layout.setVerticalGroup(
            pnlRole1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRole1Layout.createSequentialGroup()
                .addGroup(pnlRole1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xxx1)
                    .addComponent(lblAccS1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRole1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(a1)
                    .addComponent(lblRole1)))
        );

        cbxED.setText("Search By Department");

        cbxEN.setText("Search By Name");
        cbxEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxENActionPerformed(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\blue_medical_symbol_pc_image_500_clr.png")); // NOI18N

        jLabel16.setText("Health care for all people");

        javax.swing.GroupLayout pnlChoice1Layout = new javax.swing.GroupLayout(pnlChoice1);
        pnlChoice1.setLayout(pnlChoice1Layout);
        pnlChoice1Layout.setHorizontalGroup(
            pnlChoice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoice1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChoice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChoice1Layout.createSequentialGroup()
                        .addGroup(pnlChoice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxED)
                            .addComponent(cbxEN)
                            .addGroup(pnlChoice1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNameE, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlChoice1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlChoice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(cbbEDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(pnlChoice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlChoice1Layout.createSequentialGroup()
                            .addComponent(pnlRole1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(pnlChoice1Layout.createSequentialGroup()
                            .addGroup(pnlChoice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlChoice1Layout.createSequentialGroup()
                                    .addComponent(btnSearchE, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnReset1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                                .addComponent(btnAllE, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGap(13, 13, 13)))))
            .addGroup(pnlChoice1Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel16)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        pnlChoice1Layout.setVerticalGroup(
            pnlChoice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoice1Layout.createSequentialGroup()
                .addComponent(pnlRole1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbxEN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChoice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtNameE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(cbxED)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChoice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbEDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnAllE, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChoice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(-6710887,true));

        jLabel28.setFont(new java.awt.Font("Dialog", 0, 36));
        jLabel28.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Untitled 5.png")); // NOI18N
        jLabel28.setText("Examine");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(393, 393, 393)
                .addComponent(jLabel28)
                .addContainerGap(473, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContent1Layout = new javax.swing.GroupLayout(pnlContent1);
        pnlContent1.setLayout(pnlContent1Layout);
        pnlContent1Layout.setHorizontalGroup(
            pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContent1Layout.createSequentialGroup()
                .addComponent(pnlChoice1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlContent1Layout.setVerticalGroup(
            pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContent1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlChoice1, 0, 482, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlExamine.add(pnlContent1, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlExamine, "cardExamine");

        mnEmp.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\nurse-icon.png")); // NOI18N
        mnEmp.setText("Employee");
        mnEmp.setEnabled(false);
        mnEmp.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12));
        mnEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnEmpActionPerformed(evt);
            }
        });

        jMenuItem2.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\search-b-icon (.png")); // NOI18N
        jMenuItem2.setText("Patient's Information");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnEmp.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Actions-list-add-user-icon (1).png")); // NOI18N
        jMenuItem3.setText("Add Patient");
        jMenuItem3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jMenuItem3ItemStateChanged(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnEmp.add(jMenuItem3);

        jMenuBar1.add(mnEmp);

        mnDr.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\doctor-icon (2).png")); // NOI18N
        mnDr.setText("Doctor");
        mnDr.setEnabled(false);
        mnDr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDrActionPerformed(evt);
            }
        });

        jMenuItem7.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Untitled 51.png")); // NOI18N
        jMenuItem7.setText("Examine");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        mnDr.add(jMenuItem7);

        jMenuItem6.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\App-edit-icon.png")); // NOI18N
        jMenuItem6.setText("Patient Manager");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        mnDr.add(jMenuItem6);

        jMenuBar1.add(mnDr);

        mnAd.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Keys-icon.png")); // NOI18N
        mnAd.setText("Admin");
        mnAd.setEnabled(false);

        jMenuItem5.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\add-key-icon.png")); // NOI18N
        jMenuItem5.setText("Add Account");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        mnAd.add(jMenuItem5);

        jMenuItem1.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Actions-list-add-user-icon (1).png")); // NOI18N
        jMenuItem1.setText("Add Employee");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnAd.add(jMenuItem1);

        jMenuItem4.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Actions-list-add-user-icon (1).png")); // NOI18N
        jMenuItem4.setText(" Add Doctor");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        mnAd.add(jMenuItem4);

        jMenuBar1.add(mnAd);

        jMenu2.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Actions-help-about-icon.png")); // NOI18N
        jMenu2.setText("About");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem8.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\Actions-help-about-icon (1).png")); // NOI18N
        jMenuItem8.setText("About");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        jMenu1.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\user-login-icon (2).png")); // NOI18N
        jMenu1.setText("Login");

        jMenuItem9.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\user-login-icon (1).png")); // NOI18N
        jMenuItem9.setText("Login");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem10.setIcon(new javax.swing.ImageIcon("C:\\Users\\tug\\Desktop\\fat1-c1107i-s2-g4-superman\\Image\\user-login-icon (1).png")); // NOI18N
        jMenuItem10.setText("Log out");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        CardLayout card1 = (CardLayout) pnlMain.getLayout();
        card1.show(pnlMain, "cardSearch");

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void mnEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnEmpActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            model = new DefaultTableModel();
            tblResult.setModel(model);
        } catch (Exception e) {
            int vv = 0;
        }
        searchPatientEmp();


    }//GEN-LAST:event_btnSearchActionPerformed

    private void cbbSInItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSInItemStateChanged
        // TODO add your handling code here:
        stt = cbbSIn.getSelectedItem().toString();
    }//GEN-LAST:event_cbbSInItemStateChanged

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            model = new DefaultTableModel();
            tblResult.setModel(model);
        } catch (Exception e) {
            int vv = 0;
        }
        allPatient();
        lblTTDT.setText("" + tblResult.getRowCount());
        setDetailsPatient();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbbSDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSDepartmentItemStateChanged
        // TODO add your handling code here:
        Department = cbbSDepartment.getSelectedItem().toString();
    }//GEN-LAST:event_cbbSDepartmentItemStateChanged

    private void cbbSMonthFItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSMonthFItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbSMonthF.getSelectedItem().toString());
        int year = Integer.parseInt(cbbSYearF.getSelectedItem().toString());
        cbbSDayF.setModel(tdv.getDayByMonth(month, year));
    }//GEN-LAST:event_cbbSMonthFItemStateChanged

    private void cbbSYearFItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSYearFItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbSMonthF.getSelectedItem().toString());
        int year = Integer.parseInt(cbbSYearF.getSelectedItem().toString());
        cbbSDayF.setModel(tdv.getDayByMonth(month, year));
    }//GEN-LAST:event_cbbSYearFItemStateChanged

    private void cbbSYearFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSYearFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSYearFActionPerformed

    private void cbbSYearTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSYearTItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbSMonthT.getSelectedItem().toString());
        int year = Integer.parseInt(cbbSYearT.getSelectedItem().toString());
        cbbSDayT.setModel(tdv.getDayByMonth(month, year));
    }//GEN-LAST:event_cbbSYearTItemStateChanged

    private void cbbSMonthTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSMonthTItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbSMonthT.getSelectedItem().toString());
        int year = Integer.parseInt(cbbSYearT.getSelectedItem().toString());
        cbbSDayT.setModel(tdv.getDayByMonth(month, year));
    }//GEN-LAST:event_cbbSMonthTItemStateChanged

    private void jMenuItem3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jMenuItem3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ItemStateChanged

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        dlgAddPatient dialog = new dlgAddPatient(this, rootPaneCheckingEnabled);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void cbbSMonthFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSMonthFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSMonthFActionPerformed

    private void tblResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultMouseClicked
   }//GEN-LAST:event_tblResultMouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        CardLayout cc = (CardLayout) pnlMain.getLayout();
        cc.show(pnlMain, "cardManager");
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void cbxByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxByNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxByNameActionPerformed

    private void cbxByInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxByInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxByInActionPerformed

    private void cbxByDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxByDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxByDateActionPerformed

    private void tblRSEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRSEMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblRSEMouseClicked

    private void btnSearchEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchEActionPerformed
        // TODO add your handling code here:
        try {
            model = new DefaultTableModel();
            tblRSE.setModel(model);
        } catch (Exception e) {
            int vv = 0;
        }
        searchPatientExam();
        setValuePatientExm();
    }//GEN-LAST:event_btnSearchEActionPerformed

    private void btnReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset1ActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnReset1ActionPerformed

    private void btnAllEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllEActionPerformed
        // TODO add your handling code here:
        try {
            model = new DefaultTableModel();
            tblRSE.setModel(model);
        } catch (Exception e) {
            int vv = 0;
        }
        allPatientEx();
        setValuePatientExm();
        lblTTE.setText("" + tblRSE.getRowCount());

    }//GEN-LAST:event_btnAllEActionPerformed

    private void cbbEDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbEDepartmentItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbEDepartmentItemStateChanged

    private void cbxENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxENActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxENActionPerformed

    private void mnDrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnDrActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) pnlMain.getLayout();
        card.show(pnlMain, "cardExamine");
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) pnlMain.getLayout();
        card.show(pnlMain, "cardAbout");
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void tblRSLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRSLMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblRSLMouseClicked

    private void btnSearchLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchLActionPerformed
        // TODO add your handling code here:
        try {
            model = new DefaultTableModel();
            tblRSL.setModel(model);
        } catch (Exception e) {
            int vv = 0;
        }
        searchPatientIH();
        setValuePatientIH();
    }//GEN-LAST:event_btnSearchLActionPerformed

    private void btnReset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset2ActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnReset2ActionPerformed

    private void btnAllLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllLActionPerformed
        // TODO add your handling code here:
        try {
            model = new DefaultTableModel();
            tblRSL.setModel(model);
        } catch (Exception e) {
            int vv = 0;
        }
        allPatientIH();
        setValuePatientIH();
        lblTTL.setText("" + tblRSL.getRowCount());
    }//GEN-LAST:event_btnAllLActionPerformed

    private void cbbLDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLDepartmentItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLDepartmentItemStateChanged

    private void cbxLNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxLNActionPerformed

    private void rbtDrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtDrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtDrActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String role = "";
        if (rbtDr.isSelected()) {
            role = "Doctor";
        } else if (rbtEmp.isSelected()) {
            role = "Employee";
        } else if (admin.isSelected()) {
            role = "Admin";
        }
        String pass = new String(password.getPassword());
        if (txtAccount.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Account!");
        } else if (txtAccount.getText().length() < 4) {
            JOptionPane.showMessageDialog(this, "Account length must be greater than 4 characters!");
        } else if (pass == "") {
            JOptionPane.showMessageDialog(this, "Enter Password!!");
        } else if (pass.length() < 4) {
            JOptionPane.showMessageDialog(this, "Password length must be greater than 4 characters!");
        } else if (role == "") {
            JOptionPane.showMessageDialog(this, "Choice ROle!!!");
        } else {
            Checklogin(txtAccount.getText(), pass, role);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        dlgAddAccount dlg = new dlgAddAccount(this, rootPaneCheckingEnabled);
        dlg.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        dlgAddEmployee dlg = new dlgAddEmployee(this, rootPaneCheckingEnabled);
        dlg.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        dlgAddDoctor dlg = new dlgAddDoctor(this, rootPaneCheckingEnabled);
        dlg.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void txtAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAccountActionPerformed

    private void adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminActionPerformed

    private void btnResetLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetLoginActionPerformed
        // TODO add your handling code here:
        txtAccount.setText(null);
        password.setText(null);
        btgRole.clearSelection();
    }//GEN-LAST:event_btnResetLoginActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        CardLayout cc = (CardLayout) pnlMain.getLayout();
        cc.show(pnlMain, "cardLogin");
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        CardLayout cc = (CardLayout) pnlMain.getLayout();
        cc.show(pnlMain, "cardLogin");
        Reset();
        mnEmp.setEnabled(false);
            mnDr.setEnabled(false);
            mnAd.setEnabled(false);
            txtAccount.setText(null);
            password.setText(null);
            btgRole.clearSelection();

    }//GEN-LAST:event_jMenuItem10ActionPerformed
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a;
    private javax.swing.JLabel a1;
    private javax.swing.JLabel a2;
    private javax.swing.JRadioButton admin;
    private javax.swing.ButtonGroup btgAddGender;
    private javax.swing.ButtonGroup btgGenderU;
    private javax.swing.ButtonGroup btgRole;
    private javax.swing.JButton btnAllE;
    private javax.swing.JButton btnAllL;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnReset1;
    private javax.swing.JButton btnReset2;
    private javax.swing.JButton btnResetLogin;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchE;
    private javax.swing.JButton btnSearchL;
    private javax.swing.JComboBox cbbEDepartment;
    private javax.swing.JComboBox cbbLDepartment;
    private javax.swing.JComboBox cbbSDayF;
    private javax.swing.JComboBox cbbSDayT;
    private javax.swing.JComboBox cbbSDepartment;
    private javax.swing.JComboBox cbbSIn;
    private javax.swing.JComboBox cbbSMonthF;
    private javax.swing.JComboBox cbbSMonthT;
    private javax.swing.JComboBox cbbSYearF;
    private javax.swing.JComboBox cbbSYearT;
    private javax.swing.JCheckBox cbxByDate;
    private javax.swing.JCheckBox cbxByDepartment;
    private javax.swing.JCheckBox cbxByIn;
    private javax.swing.JCheckBox cbxByName;
    private javax.swing.JCheckBox cbxED;
    private javax.swing.JCheckBox cbxEN;
    private javax.swing.JCheckBox cbxLD;
    private javax.swing.JCheckBox cbxLN;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAccS;
    private javax.swing.JLabel lblAccS1;
    private javax.swing.JLabel lblAccS2;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblRole1;
    private javax.swing.JLabel lblRole2;
    private javax.swing.JLabel lblTTDT;
    private javax.swing.JLabel lblTTE;
    private javax.swing.JLabel lblTTL;
    private javax.swing.JMenu mnAd;
    private javax.swing.JMenu mnDr;
    private javax.swing.JMenu mnEmp;
    private javax.swing.JPasswordField password;
    private javax.swing.JPanel pnlAbout;
    private javax.swing.JPanel pnlChoice;
    private javax.swing.JPanel pnlChoice1;
    private javax.swing.JPanel pnlChoice2;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlContent1;
    private javax.swing.JPanel pnlContent2;
    private javax.swing.JPanel pnlContent3;
    private javax.swing.JPanel pnlExamine;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlLogin1;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlManager;
    private javax.swing.JPanel pnlPatientInfomation;
    private javax.swing.JPanel pnlRole;
    private javax.swing.JPanel pnlRole1;
    private javax.swing.JPanel pnlRole2;
    private javax.swing.JRadioButton rbtDr;
    private javax.swing.JRadioButton rbtEmp;
    private javax.swing.JTable tblRSE;
    private javax.swing.JTable tblRSL;
    private javax.swing.JTable tblResult;
    private javax.swing.JTextField txtAccount;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNameE;
    private javax.swing.JTextField txtNameL;
    private javax.swing.JLabel xxx;
    private javax.swing.JLabel xxx1;
    private javax.swing.JLabel xxx2;
    // End of variables declaration//GEN-END:variables
}
