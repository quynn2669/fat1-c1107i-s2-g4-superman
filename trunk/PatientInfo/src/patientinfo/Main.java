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
    String LName = "";
    String Gender = "";
    int addAge = 0;
    public Patient patient = new Patient();

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
        cbbAddYearIn.setModel(dateTDV.getListYear());
        cbbCUY.setModel(dateTDV.getListYear());

    }

    public void Reset() {
        try {
            model = new DefaultTableModel();
            tblResult.setModel(model);

        } catch (Exception e) {
            int vv = 0;
        }
        txtID.setEditable(false);
        txtName.setEditable(false);
        cbbSDepartment.setEnabled(false);
        cbbSIn.setEnabled(false);
        btnG1.clearSelection();
        txtID.setText(null);
        txtName.setText(null);
        cbbSDepartment.setEnabled(false);
        cbbSIn.setEnabled(false);
        choice = 0;
        iID = 0;
        Department = "Internal medicine";
        FName = "";
        cbbSDayF.setEnabled(false);
        cbbSMonthF.setEnabled(false);
        cbbSYearF.setEnabled(false);
        cbbSDayT.setEnabled(false);
        cbbSMonthT.setEnabled(false);
        cbbSYearT.setEnabled(false);
        DateF = "";
        DateT = "";
        LName = "";
        Gender = "";
        addAge = 0;
        txtAddFName.setText(null);
        txtAddAddress.setText(null);
        txtAddAge.setText(null);
        txtAddDoctor.setText(null);
        aDescript.setText(null);
        btgAddGender.clearSelection();
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
                    DetailPatient dialog = new DetailPatient(new Main(),true);
                    dialog.setTxtID(model.getValueAt(realIndex, 0).toString());
                    dialog.setTxtName(model.getValueAt(realIndex, 1).toString());
                    dialog.setTxtAD(model.getValueAt(realIndex, 2).toString());
                    dialog.setTxtAge(model.getValueAt(realIndex, 3).toString());
                    dialog.setRbtMale(model.getValueAt(realIndex, 4).toString());
                    dialog.setCbbDepartment(model.getValueAt(realIndex, 5).toString());
                    dialog.setaDes(model.getValueAt(realIndex, 6).toString());
                    dialog.setaSick(model.getValueAt(realIndex, 7).toString());
                    dialog.setTxtDoctor(model.getValueAt(realIndex, 8).toString());
                    dialog.setTxtDateIn(model.getValueAt(realIndex, 9).toString());
                    dialog.setTxtDateOut( model.getValueAt(realIndex, 10).toString());
                    dialog.setTxtInHospital( model.getValueAt(realIndex, 11).toString());
                    dialog.setVisible(true);
                    selectedIndex = 0;
//                    JOptionPane.showMessageDialog(pnlMain, patient.toString());
                }
            }
        });

    }


    public void setCUPatient() {

        tblCURS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblCURS.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = tblCURS.getSelectedRow();
                    int realIndex = tblCURS.convertRowIndexToModel(selectedIndex);
                    TableModel model = tblCURS.getModel();
                    iID = Integer.parseInt(model.getValueAt(realIndex, 0).toString());
                    txtCUFName.setText(model.getValueAt(realIndex, 1).toString());
                    txtCUAge.setText(model.getValueAt(realIndex, 3).toString());
                    txtCUGender.setText(model.getValueAt(realIndex, 4).toString());
                    aCUD.setText(model.getValueAt(realIndex, 6).toString());
                    Department = model.getValueAt(realIndex, 6).toString();
                    if ("Internal medicine".equals(Department)) {
                        cbbCUDepartment.setSelectedIndex(0);
                    } else if ("Surgical".equals(Department)) {
                        cbbCUDepartment.setSelectedIndex(1);
                    } else if ("Cardiovascular".equals(Department)) {
                        cbbCUDepartment.setSelectedIndex(2);
                    }
                    txtCUDoctor.setText(model.getValueAt(realIndex, 8).toString());
                    if (iID != 0) {
                        btnCUOk.setEnabled(true);
                    } else {
                        btnCUOk.setEnabled(true);
                    }
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
            lblTTDT.setText("" + tblResult.getRowCount());
            setDetailsPatient();
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
            lblTTDT.setText("" + tblResult.getRowCount());

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
            lblTTDT.setText("" + tblResult.getRowCount());
            setDetailsPatient();
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
            lblTTDT.setText("" + tblResult.getRowCount());
            setDetailsPatient();
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
            lblTTDT.setText("" + tblResult.getRowCount());

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByDepartmentCU() {
        try {
            try {
                model = new DefaultTableModel();
                tblResult.setModel(model);
            } catch (Exception e) {
                int vv = 0;
            }
            CallableStatement cs = conn.prepareCall("{call findByDepartmentCU(?)}");
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
            try {
                tblCURS.setModel(model);
            } catch (Exception e) {
                int loi = 0;
            }
            lblTTCU.setText("" + tblCURS.getRowCount());
            rs.close();
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

        btnG1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btgAddGender = new javax.swing.ButtonGroup();
        btgGenderU = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        pnlPatientInfomation = new javax.swing.JPanel();
        pnlContent = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResult = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        lblTTDT = new javax.swing.JLabel();
        pnlChoice = new javax.swing.JPanel();
        rbtID = new javax.swing.JRadioButton();
        rbtName = new javax.swing.JRadioButton();
        rbtSDepartment = new javax.swing.JRadioButton();
        rbtDate = new javax.swing.JRadioButton();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        rbtIn = new javax.swing.JRadioButton();
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
        txtID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        cbbSDepartment = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        pnlAddPatient = new javax.swing.JPanel();
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
        txtAddDoctor = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        btnAddPatient = new javax.swing.JButton();
        btnAddReset = new javax.swing.JButton();
        pnlCheckUp = new javax.swing.JPanel();
        pnlCheckUp1 = new javax.swing.JPanel();
        pnlUpdatePatientInfo1 = new javax.swing.JPanel();
        txtCUFName = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtCUAge = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        aCUD = new javax.swing.JTextArea();
        jLabel55 = new javax.swing.JLabel();
        cbbCUDepartment = new javax.swing.JComboBox();
        jLabel56 = new javax.swing.JLabel();
        cbbCUY = new javax.swing.JComboBox();
        cbbCUM = new javax.swing.JComboBox();
        cbbCUD = new javax.swing.JComboBox();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtCUDoctor = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        aSick = new javax.swing.JTextArea();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        cbbCUInHospital = new javax.swing.JComboBox();
        txtCUGender = new javax.swing.JTextField();
        btnCUOk = new javax.swing.JButton();
        btnCURS = new javax.swing.JButton();
        pnlContentUpdate1 = new javax.swing.JPanel();
        pnlSU1 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        txtFNSCU = new javax.swing.JTextField();
        btnSUP1 = new javax.swing.JButton();
        btlAllCU = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        cbbCUSDepartment = new javax.swing.JComboBox();
        pnlResult1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblCURS = new javax.swing.JTable();
        jLabel62 = new javax.swing.JLabel();
        lblTTCU = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnLogin = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        mnEmp = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        mnDr = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

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
        setResizable(false);

        pnlMain.setBackground(new java.awt.Color(-1,true));
        pnlMain.setPreferredSize(new java.awt.Dimension(1205, 800));
        pnlMain.setLayout(new java.awt.CardLayout());

        pnlPatientInfomation.setBackground(new java.awt.Color(-1,true));
        pnlPatientInfomation.setLayout(new java.awt.BorderLayout());

        pnlContent.setBackground(new java.awt.Color(-1,true));
        pnlContent.setPreferredSize(new java.awt.Dimension(740, 600));

        jPanel1.setBackground(new java.awt.Color(-1,true));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(-8355712,true)));

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTTDT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(626, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(lblTTDT, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlChoice.setBackground(new java.awt.Color(-1,true));
        pnlChoice.setForeground(new java.awt.Color(-8355712,true));
        pnlChoice.setPreferredSize(new java.awt.Dimension(330, 599));

        btnG1.add(rbtID);
        rbtID.setText("Search By ID");
        rbtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtIDActionPerformed(evt);
            }
        });

        btnG1.add(rbtName);
        rbtName.setText("Search By Name");
        rbtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtNameActionPerformed(evt);
            }
        });

        btnG1.add(rbtSDepartment);
        rbtSDepartment.setText("Search By Department");
        rbtSDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtSDepartmentActionPerformed(evt);
            }
        });

        btnG1.add(rbtDate);
        rbtDate.setText("Search By Date");
        rbtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtDateActionPerformed(evt);
            }
        });

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

        btnG1.add(rbtIn);
        rbtIn.setText("Search By In Hospital");
        rbtIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtInActionPerformed(evt);
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

        txtID.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel3.setText("Enter ID");

        jLabel4.setText("Name");

        cbbSDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Internal medicine", "Surgical", "Cardiovascular" }));
        cbbSDepartment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbSDepartment.setEnabled(false);
        cbbSDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSDepartmentItemStateChanged(evt);
            }
        });

        jLabel5.setText("Department");

        javax.swing.GroupLayout pnlChoiceLayout = new javax.swing.GroupLayout(pnlChoice);
        pnlChoice.setLayout(pnlChoiceLayout);
        pnlChoiceLayout.setHorizontalGroup(
            pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addComponent(rbtDate)
                        .addContainerGap())
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtID)
                            .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlChoiceLayout.createSequentialGroup()
                                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(8, 8, 8))
                                .addComponent(rbtIn, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rbtSDepartment, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rbtName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlChoiceLayout.createSequentialGroup()
                                    .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                    .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlChoiceLayout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbbSYearT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbbSMonthT, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel7))
                                        .addGroup(pnlChoiceLayout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbbSYearF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbbSMonthF, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel6)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbbSDayT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbbSDayF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(15, Short.MAX_VALUE))))
            .addGroup(pnlChoiceLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbSIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
            .addGroup(pnlChoiceLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbSDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChoiceLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlChoiceLayout.setVerticalGroup(
            pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtID)
                .addGap(14, 14, 14)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtName)
                .addGap(19, 19, 19)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rbtSDepartment)
                .addGap(19, 19, 19)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbSDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtIn)
                .addGap(18, 18, 18)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbbSIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(rbtDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbSDayF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSMonthF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(cbbSYearF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addGap(25, 25, 25)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbbSYearT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cbbSMonthT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbbSDayT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(-8355712,true));

        jLabel26.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel26.setText("Search Patient ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(408, 408, 408)
                .addComponent(jLabel26)
                .addContainerGap(428, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addComponent(pnlChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContentLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlChoice, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(117, 117, 117))
        );

        pnlPatientInfomation.add(pnlContent, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlPatientInfomation, "cardSearch");

        pnlAddPatient.setPreferredSize(new java.awt.Dimension(1185, 500));
        pnlAddPatient.setLayout(new java.awt.BorderLayout());

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

        btgAddGender.add(rbtAddMale);
        rbtAddMale.setText("Male");
        rbtAddMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAddMaleActionPerformed(evt);
            }
        });

        btgAddGender.add(rbtAddFMale);
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
                    .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                            .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel24)
                                .addComponent(jLabel20))
                            .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addComponent(jLabel21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbbAddYearIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel22)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbbAddMonthIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cbbAddDayIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addComponent(txtAddDoctor))))
                        .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAddFName))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPatientInfoLayout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAddAddress))
                        .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                            .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel19)
                                .addComponent(jLabel18)
                                .addComponent(jLabel17)
                                .addComponent(jLabel16))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbbAddDepartment, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAddAge, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(31, Short.MAX_VALUE))
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
                    .addComponent(txtAddAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbAddDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtAddDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbAddYearIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(cbbAddMonthIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbAddDayIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)))
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
                        .addGap(64, 64, 64)
                        .addComponent(btnAddPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddReset, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAddLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlPatientInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        pnlAddLayout.setVerticalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(pnlPatientInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddPatient)
                    .addComponent(btnAddReset))
                .addContainerGap(242, Short.MAX_VALUE))
        );

        pnlAddPatient.add(pnlAdd, java.awt.BorderLayout.LINE_START);

        pnlMain.add(pnlAddPatient, "addPatient");

        pnlCheckUp.setPreferredSize(new java.awt.Dimension(1185, 500));
        pnlCheckUp.setLayout(new java.awt.BorderLayout());

        pnlCheckUp1.setPreferredSize(new java.awt.Dimension(400, 500));

        txtCUFName.setEditable(false);
        txtCUFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCUFNameActionPerformed(evt);
            }
        });

        jLabel23.setText("Full Name");

        jLabel52.setText("Age");

        txtCUAge.setEditable(false);

        jLabel53.setText("Gender");

        jLabel54.setText("Description");

        aCUD.setColumns(20);
        aCUD.setEditable(false);
        aCUD.setRows(5);
        jScrollPane5.setViewportView(aCUD);

        jLabel55.setText("Department");

        cbbCUDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Internal medicine", "Surgical", "Cardiovascular" }));
        cbbCUDepartment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbCUDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbCUDepartmentItemStateChanged(evt);
            }
        });

        jLabel56.setText("Date Out");

        cbbCUY.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012" }));
        cbbCUY.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbCUYItemStateChanged(evt);
            }
        });
        cbbCUY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCUYActionPerformed(evt);
            }
        });

        cbbCUM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbbCUM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbCUMItemStateChanged(evt);
            }
        });

        cbbCUD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel57.setText("Year");

        jLabel58.setText("Month");

        jLabel59.setText("Day");

        jLabel60.setText("Doctor");

        txtCUDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCUDoctorActionPerformed(evt);
            }
        });

        aSick.setColumns(20);
        aSick.setRows(5);
        jScrollPane7.setViewportView(aSick);

        jLabel63.setText("Sick");

        jLabel64.setText("In Hospital");

        cbbCUInHospital.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        cbbCUInHospital.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbCUInHospital.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbCUInHospitalItemStateChanged(evt);
            }
        });

        txtCUGender.setEditable(false);

        javax.swing.GroupLayout pnlUpdatePatientInfo1Layout = new javax.swing.GroupLayout(pnlUpdatePatientInfo1);
        pnlUpdatePatientInfo1.setLayout(pnlUpdatePatientInfo1Layout);
        pnlUpdatePatientInfo1Layout.setHorizontalGroup(
            pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUpdatePatientInfo1Layout.createSequentialGroup()
                .addGroup(pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54)
                    .addComponent(jLabel23)
                    .addComponent(jLabel63)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel55)
                    .addComponent(jLabel60)
                    .addGroup(pnlUpdatePatientInfo1Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel57)))
                .addGap(7, 7, 7)
                .addGroup(pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCUGender, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addGroup(pnlUpdatePatientInfo1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(cbbCUY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbCUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbCUD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCUDoctor)
                    .addComponent(cbbCUDepartment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbCUInHospital, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane5)
                    .addComponent(txtCUAge)
                    .addComponent(txtCUFName, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                .addGap(64, 64, 64))
            .addGroup(pnlUpdatePatientInfo1Layout.createSequentialGroup()
                .addComponent(jLabel64)
                .addContainerGap())
        );
        pnlUpdatePatientInfo1Layout.setVerticalGroup(
            pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdatePatientInfo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCUFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCUAge)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel53)
                    .addComponent(txtCUGender))
                .addGroup(pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpdatePatientInfo1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel54))
                    .addGroup(pnlUpdatePatientInfo1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlUpdatePatientInfo1Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addGap(64, 64, 64))
                    .addGroup(pnlUpdatePatientInfo1Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addGroup(pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(cbbCUInHospital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(cbbCUDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(txtCUDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUpdatePatientInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57)
                    .addComponent(cbbCUY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58)
                    .addComponent(cbbCUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59)
                    .addComponent(cbbCUD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        btnCUOk.setText("Ok");
        btnCUOk.setEnabled(false);
        btnCUOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCUOkActionPerformed(evt);
            }
        });

        btnCURS.setText("Reset");
        btnCURS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCURSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCheckUp1Layout = new javax.swing.GroupLayout(pnlCheckUp1);
        pnlCheckUp1.setLayout(pnlCheckUp1Layout);
        pnlCheckUp1Layout.setHorizontalGroup(
            pnlCheckUp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCheckUp1Layout.createSequentialGroup()
                .addGroup(pnlCheckUp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCheckUp1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnCUOk, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCURS, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCheckUp1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlUpdatePatientInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        pnlCheckUp1Layout.setVerticalGroup(
            pnlCheckUp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCheckUp1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlUpdatePatientInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(pnlCheckUp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCUOk)
                    .addComponent(btnCURS))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pnlCheckUp.add(pnlCheckUp1, java.awt.BorderLayout.LINE_START);

        jLabel61.setText("Patient's Name");

        btnSUP1.setText("Search");
        btnSUP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSUP1ActionPerformed(evt);
            }
        });

        btlAllCU.setText("All Patients");
        btlAllCU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlAllCUActionPerformed(evt);
            }
        });

        jLabel65.setText("Department");

        cbbCUSDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Internal medicine", "Surgical", "Cardiovascular" }));
        cbbCUSDepartment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbCUSDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbCUSDepartmentItemStateChanged(evt);
            }
        });
        cbbCUSDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCUSDepartmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSU1Layout = new javax.swing.GroupLayout(pnlSU1);
        pnlSU1.setLayout(pnlSU1Layout);
        pnlSU1Layout.setHorizontalGroup(
            pnlSU1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSU1Layout.createSequentialGroup()
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFNSCU, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnSUP1)
                .addGap(1, 1, 1)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbCUSDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlAllCU, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlSU1Layout.setVerticalGroup(
            pnlSU1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSU1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSU1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFNSCU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSUP1)
                    .addComponent(jLabel65)
                    .addComponent(cbbCUSDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btlAllCU))
                .addContainerGap())
        );

        tblCURS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tblCURS);

        jLabel62.setText("Total: ");

        javax.swing.GroupLayout pnlResult1Layout = new javax.swing.GroupLayout(pnlResult1);
        pnlResult1.setLayout(pnlResult1Layout);
        pnlResult1Layout.setHorizontalGroup(
            pnlResult1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResult1Layout.createSequentialGroup()
                .addGroup(pnlResult1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResult1Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTTCU, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlResult1Layout.setVerticalGroup(
            pnlResult1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlResult1Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addGroup(pnlResult1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(lblTTCU, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlContentUpdate1Layout = new javax.swing.GroupLayout(pnlContentUpdate1);
        pnlContentUpdate1.setLayout(pnlContentUpdate1Layout);
        pnlContentUpdate1Layout.setHorizontalGroup(
            pnlContentUpdate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentUpdate1Layout.createSequentialGroup()
                .addGroup(pnlContentUpdate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlResult1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSU1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        pnlContentUpdate1Layout.setVerticalGroup(
            pnlContentUpdate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentUpdate1Layout.createSequentialGroup()
                .addComponent(pnlSU1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlResult1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        pnlCheckUp.add(pnlContentUpdate1, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlCheckUp, "checkUpPatient");

        mnLogin.setText("Login");

        jMenuItem1.setText("Admin");
        mnLogin.add(jMenuItem1);

        jMenuItem4.setText("Employee");
        mnLogin.add(jMenuItem4);

        jMenuItem5.setText("Doctor");
        mnLogin.add(jMenuItem5);

        jMenuBar1.add(mnLogin);

        mnEmp.setText("Employee");
        mnEmp.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12));
        mnEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnEmpActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Search");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnEmp.add(jMenuItem2);

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

        mnDr.setText("Doctor");

        jMenuItem6.setText("Patient Manager");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        mnDr.add(jMenuItem6);

        jMenuBar1.add(mnDr);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        CardLayout card1 = (CardLayout) pnlMain.getLayout();
        card1.show(pnlMain, "cardSearch");

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void rbtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtIDActionPerformed
        // TODO add your handling code here:
        choice = 1;
        txtID.setEditable(true);
        txtName.setEditable(false);
        cbbSDepartment.setEnabled(false);
        cbbSDayF.setEnabled(false);
        cbbSDayT.setEnabled(false);
        cbbSMonthF.setEnabled(false);
        cbbSMonthT.setEnabled(false);
        cbbSYearF.setEnabled(false);
        cbbSYearT.setEnabled(false);
        cbbSIn.setEnabled(false);

    }//GEN-LAST:event_rbtIDActionPerformed

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
        model = new DefaultTableModel();
        tblResult.setModel(model);
        switch (choice) {
            case 1:
                if (txtID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter ID!");
                } else {

                    int check = 0;
                    try {
                        ;
                        iID = Integer.parseInt(txtID.getText());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "ID valid!");
                        check = 1;
                    }
                    if (check == 0) {
                        searchByID();
                    }
                }
                break;
            case 2:
                if (txtName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter Name!");
                } else {
                    FName = txtName.getText();
                    searchByName(FName);
                    setDetailsPatient();
                }
                break;
            case 3:
                searchByDepartment();
                setDetailsPatient();
                break;
            case 4:
                searchByIn();
                break;
            case 5:
                int dayF = Integer.parseInt(cbbSDayF.getSelectedItem().toString());
                int monthF = Integer.parseInt(cbbSMonthF.getSelectedItem().toString());
                int yearF = Integer.parseInt(cbbSYearF.getSelectedItem().toString());
                int dayT = Integer.parseInt(cbbSDayT.getSelectedItem().toString());
                int monthT = Integer.parseInt(cbbSMonthT.getSelectedItem().toString());
                int yearT = Integer.parseInt(cbbSYearT.getSelectedItem().toString());
                DateF = monthF + "/" + dayF + "/" + yearF;
                DateT = monthT + "/" + dayT + "/" + yearT;
                searchByDateIn();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Please Choice Option Search!");
    }//GEN-LAST:event_btnSearchActionPerformed
    }
    private void rbtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtNameActionPerformed
        // TODO add your handling code here:
        choice = 2;
        txtName.setEditable(true);
        txtID.setEditable(false);
        cbbSDepartment.setEnabled(false);
        cbbSDayF.setEnabled(false);
        cbbSDayT.setEnabled(false);
        cbbSMonthF.setEnabled(false);
        cbbSMonthT.setEnabled(false);
        cbbSYearF.setEnabled(false);
        cbbSYearT.setEnabled(false);
        cbbSIn.setEnabled(false);
    }//GEN-LAST:event_rbtNameActionPerformed

    private void rbtSDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtSDepartmentActionPerformed
        // TODO add your handling code here:
        choice = 3;
        cbbSDepartment.setEnabled(true);
        txtID.setEditable(false);
        txtName.setEditable(false);

        cbbSDayF.setEnabled(false);
        cbbSDayT.setEnabled(false);
        cbbSMonthF.setEnabled(false);
        cbbSMonthT.setEnabled(false);
        cbbSYearF.setEnabled(false);
        cbbSYearT.setEnabled(false);
        cbbSIn.setEnabled(false);
    }//GEN-LAST:event_rbtSDepartmentActionPerformed

    private void rbtInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtInActionPerformed
        // TODO add your handling code here:
        choice = 4;
        cbbSIn.setEnabled(true);
        txtID.setEditable(false);
        txtName.setEditable(false);
        cbbSDepartment.setEnabled(false);
        cbbSDayF.setEnabled(false);
        cbbSDayT.setEnabled(false);
        cbbSMonthF.setEnabled(false);
        cbbSMonthT.setEnabled(false);
        cbbSYearF.setEnabled(false);
        cbbSYearT.setEnabled(false);

    }//GEN-LAST:event_rbtInActionPerformed

    private void cbbSInItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSInItemStateChanged
        // TODO add your handling code here:
        stt = cbbSIn.getSelectedItem().toString();
    }//GEN-LAST:event_cbbSInItemStateChanged

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void rbtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtDateActionPerformed
        // TODO add your handling code here:

        txtID.setEditable(false);
        txtName.setEditable(false);
        cbbSDepartment.setEnabled(false);
        cbbSIn.setEnabled(false);
        cbbSDayF.setEnabled(true);
        cbbSMonthF.setEnabled(true);
        cbbSYearF.setEnabled(true);
        cbbSDayT.setEnabled(true);
        cbbSMonthT.setEnabled(true);
        cbbSYearT.setEnabled(true);
        choice = 5;

    }//GEN-LAST:event_rbtDateActionPerformed

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

    private void cbbAddDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAddDepartmentItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbAddDepartmentItemStateChanged

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) pnlMain.getLayout();
        card.show(pnlMain, "addPatient");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
    }//GEN-LAST:event_cbbAddMonthInItemStateChanged

    private void rbtAddMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAddMaleActionPerformed
        // TODO add your handling code here:
        Gender = "Male";
    }//GEN-LAST:event_rbtAddMaleActionPerformed

    private void rbtAddFMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAddFMaleActionPerformed
        // TODO add your handling code here:
        Gender = "Female";
    }//GEN-LAST:event_rbtAddFMaleActionPerformed

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
        } else if (txtAddDoctor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Doctor's Name!");
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
                    cs.setString(7, txtAddDoctor.getText());
                    cs.setString(8, DateTemp);
                    cs.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Add Completed!");
                    Reset();

                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }//GEN-LAST:event_btnAddPatientActionPerformed

    private void btnAddResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddResetActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnAddResetActionPerformed

    private void txtAddFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddFNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddFNameActionPerformed

    private void cbbSMonthFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSMonthFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSMonthFActionPerformed

    private void tblResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultMouseClicked
   }//GEN-LAST:event_tblResultMouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        CardLayout cc = (CardLayout) pnlMain.getLayout();
        cc.show(pnlMain, "checkUpPatient");
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void txtCUFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCUFNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCUFNameActionPerformed

    private void cbbCUDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbCUDepartmentItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCUDepartmentItemStateChanged

    private void cbbCUYItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbCUYItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbCUM.getSelectedItem().toString());
        int year = Integer.parseInt(cbbCUY.getSelectedItem().toString());
        cbbCUD.setModel(tdv.getDayByMonth(month, year));
    }//GEN-LAST:event_cbbCUYItemStateChanged

    private void cbbCUYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCUYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCUYActionPerformed

    private void cbbCUMItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbCUMItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbCUM.getSelectedItem().toString());
        int year = Integer.parseInt(cbbCUY.getSelectedItem().toString());
        cbbCUD.setModel(tdv.getDayByMonth(month, year));
    }//GEN-LAST:event_cbbCUMItemStateChanged

    private void txtCUDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCUDoctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCUDoctorActionPerformed

    private void btnCUOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCUOkActionPerformed
        // TODO add your handling code here:
        if (aSick.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Sick!");
        } else {
            try {
                String sick = aSick.getText();
                Department = cbbCUDepartment.getSelectedItem().toString();
                String date = cbbCUM.getSelectedItem().toString() + "/" + cbbCUD.getSelectedItem().toString() + "/"
                        + cbbCUY.getSelectedItem().toString();
                CallableStatement cs = conn.prepareCall("{call checkUpPatient(?,?,?,?,?,?,?)}");
                cs.setInt(1, iID);
                cs.setString(2, sick);
                cs.setString(3, Department);
                cs.setString(4, txtCUDoctor.getText());
                cs.setString(5, date);
                cs.setString(6, cbbCUInHospital.getSelectedItem().toString());
                cs.setInt(7, 1);
                cs.executeUpdate();
                JOptionPane.showMessageDialog(this, "Check Up Success!");

            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnCUOkActionPerformed

    private void btnCURSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCURSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCURSActionPerformed

    private void btnSUP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSUP1ActionPerformed
        // TODO add your handling code here:
        if (txtFNSCU.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Name!!");
        } else {
            try {
                model = new DefaultTableModel();
                tblCURS.setModel(model);

            } catch (Exception e) {
                int vv = 0;
            }
            FName = txtFNSCU.getText();
            searchByName(FName);
            lblTTCU.setText("" + tblCURS.getRowCount());
            tblCURS.setModel(model);
            setCUPatient();
        }

    }//GEN-LAST:event_btnSUP1ActionPerformed

    private void btlAllCUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlAllCUActionPerformed
        // TODO add your handling code here:
        try {
            model = new DefaultTableModel();
            tblCURS.setModel(model);

        } catch (Exception e) {
            int vv = 0;
        }
        try {
            String sSelect = "Select * from tblPatient where DrSTT = 0";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sSelect);

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
            tblCURS.setModel(model);
            lblTTCU.setText("" + tblCURS.getRowCount());
            setCUPatient();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btlAllCUActionPerformed

    private void cbbCUInHospitalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbCUInHospitalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCUInHospitalItemStateChanged

    private void cbbCUSDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbCUSDepartmentItemStateChanged
        // TODO add your handling code here:
        try {
            Department = cbbCUSDepartment.getSelectedItem().toString();
            searchByDepartmentCU();
        } catch (ArrayIndexOutOfBoundsException e) {
            int loi = 0;
        }
        try {
            setCUPatient();
        } catch (Exception e) {
            int loi = 0;
        }
    }//GEN-LAST:event_cbbCUSDepartmentItemStateChanged

    private void cbbCUSDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCUSDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCUSDepartmentActionPerformed
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
    private javax.swing.JTextArea aCUD;
    private javax.swing.JTextArea aDescript;
    private javax.swing.JTextArea aSick;
    private javax.swing.ButtonGroup btgAddGender;
    private javax.swing.ButtonGroup btgGenderU;
    private javax.swing.JButton btlAllCU;
    private javax.swing.JButton btnAddPatient;
    private javax.swing.JButton btnAddReset;
    private javax.swing.JButton btnCUOk;
    private javax.swing.JButton btnCURS;
    private javax.swing.ButtonGroup btnG1;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSUP1;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cbbAddDayIn;
    private javax.swing.JComboBox cbbAddDepartment;
    private javax.swing.JComboBox cbbAddMonthIn;
    private javax.swing.JComboBox cbbAddYearIn;
    private javax.swing.JComboBox cbbCUD;
    private javax.swing.JComboBox cbbCUDepartment;
    private javax.swing.JComboBox cbbCUInHospital;
    private javax.swing.JComboBox cbbCUM;
    private javax.swing.JComboBox cbbCUSDepartment;
    private javax.swing.JComboBox cbbCUY;
    private javax.swing.JComboBox cbbSDayF;
    private javax.swing.JComboBox cbbSDayT;
    private javax.swing.JComboBox cbbSDepartment;
    private javax.swing.JComboBox cbbSIn;
    private javax.swing.JComboBox cbbSMonthF;
    private javax.swing.JComboBox cbbSMonthT;
    private javax.swing.JComboBox cbbSYearF;
    private javax.swing.JComboBox cbbSYearT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblTTCU;
    private javax.swing.JLabel lblTTDT;
    private javax.swing.JMenu mnDr;
    private javax.swing.JMenu mnEmp;
    private javax.swing.JMenu mnLogin;
    private javax.swing.JPanel pnlAdd;
    private javax.swing.JPanel pnlAddPatient;
    private javax.swing.JPanel pnlCheckUp;
    private javax.swing.JPanel pnlCheckUp1;
    private javax.swing.JPanel pnlChoice;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlContentUpdate1;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlPatientInfo;
    private javax.swing.JPanel pnlPatientInfomation;
    private javax.swing.JPanel pnlResult1;
    private javax.swing.JPanel pnlSU1;
    private javax.swing.JPanel pnlUpdatePatientInfo1;
    private javax.swing.JRadioButton rbtAddFMale;
    private javax.swing.JRadioButton rbtAddMale;
    private javax.swing.JRadioButton rbtDate;
    private javax.swing.JRadioButton rbtID;
    private javax.swing.JRadioButton rbtIn;
    private javax.swing.JRadioButton rbtName;
    private javax.swing.JRadioButton rbtSDepartment;
    private javax.swing.JTable tblCURS;
    private javax.swing.JTable tblResult;
    private javax.swing.JTextField txtAddAddress;
    private javax.swing.JTextField txtAddAge;
    private javax.swing.JTextField txtAddDoctor;
    private javax.swing.JTextField txtAddFName;
    private javax.swing.JTextField txtCUAge;
    private javax.swing.JTextField txtCUDoctor;
    private javax.swing.JTextField txtCUFName;
    private javax.swing.JTextField txtCUGender;
    private javax.swing.JTextField txtFNSCU;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
