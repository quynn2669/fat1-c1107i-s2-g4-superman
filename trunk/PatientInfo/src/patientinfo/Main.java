package patientinfo;

import DateTime.DateTimeTDV;
import java.awt.CardLayout;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
    int choicesearch = 0;
    int iID = 0;
    String Department = "Internal medicine";
    String Name = "";
    String DateF = "";
    String DateT = "";

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
        cbbYearF.setModel(dateTDV.getListYear());
        cbbYearT.setModel(dateTDV.getListYear());
        
    }

    public void Reset() {
        txtID.setEditable(false);
        txtName.setEditable(false);
        cbbSDepartment.setEnabled(false);
        cbbIn.setEnabled(false);
        btnG1.clearSelection();
        txtID.setText(null);
        txtName.setText(null);
        cbbSDepartment.setEnabled(false);
        cbbIn.setEnabled(false);
        choicesearch = 0;
        iID = 0;
        Department = "Internal medicine";
        Name = "";
        cbbDayF.setEnabled(false);
        cbbMonthF.setEnabled(false);
        cbbYearF.setEnabled(false);
        cbbDayT.setEnabled(false);
        cbbMonthT.setEnabled(false);
        cbbYearT.setEnabled(false);
    }

    public void allPatient() {
        try {
            model = new DefaultTableModel();
            tblResult.setModel(model);
            String sSelect = "Select * from displayPatient";
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByID() {
        try {
            model = new DefaultTableModel();
            tblResult.setModel(model);
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void searchByName() {
        try {
            model = new DefaultTableModel();
            tblResult.setModel(model);
            CallableStatement cs = conn.prepareCall("{call findByName(?)}");
            cs.setString(1, Name);
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchByIn() {
        try {
            model = new DefaultTableModel();
            tblResult.setModel(model);
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void searchByDateIn() {
        try {
            model = new DefaultTableModel();
            tblResult.setModel(model);
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchByDepartment() {
        try {
            model = new DefaultTableModel();
            tblResult.setModel(model);
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
        pnlMain = new javax.swing.JPanel();
        pnlAddPatient = new javax.swing.JPanel();
        pnlAdd = new javax.swing.JPanel();
        pnlPatientInfo = new javax.swing.JPanel();
        txtAddFName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtAddLName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtAddAddress = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtAddAge = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        rbtAddMale = new javax.swing.JRadioButton();
        rbtAddFMale = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        cbbAddFaculty = new javax.swing.JComboBox();
        pnlSearch = new javax.swing.JPanel();
        pnlChoice = new javax.swing.JPanel();
        pnlID = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        pnlName = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        pnlFacultyOfDisease = new javax.swing.JPanel();
        cbbSDepartment = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        pnlFacultyOfDisease1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbDayF = new javax.swing.JComboBox();
        cbbMonthF = new javax.swing.JComboBox();
        cbbYearF = new javax.swing.JComboBox();
        cbbDayT = new javax.swing.JComboBox();
        cbbMonthT = new javax.swing.JComboBox();
        cbbYearT = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rbtID = new javax.swing.JRadioButton();
        rbtName = new javax.swing.JRadioButton();
        rbtSDepartment = new javax.swing.JRadioButton();
        rbtDate = new javax.swing.JRadioButton();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        rbtIn = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbbIn = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        pnlContent = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResult = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
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
        getContentPane().setLayout(new java.awt.CardLayout());

        pnlMain.setLayout(new java.awt.CardLayout());

        pnlAddPatient.setPreferredSize(new java.awt.Dimension(1185, 885));
        pnlAddPatient.setLayout(new java.awt.BorderLayout());

        pnlAdd.setPreferredSize(new java.awt.Dimension(400, 885));

        pnlPatientInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Patient's Infomation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));

        jLabel13.setText("First Name");

        jLabel14.setText("Last Name");

        jLabel15.setText("Address");

        jLabel16.setText("Age");

        jLabel17.setText("Gender");

        btgAddGender.add(rbtAddMale);
        rbtAddMale.setText("Male");

        btgAddGender.add(rbtAddFMale);
        rbtAddFMale.setText("Female");

        jLabel18.setText("Dicription");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel19.setText("Department");

        cbbAddFaculty.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Internal medicine", "Surgical", "Cardiovascular" }));
        cbbAddFaculty.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbAddFaculty.setEnabled(false);
        cbbAddFaculty.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAddFacultyItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlPatientInfoLayout = new javax.swing.GroupLayout(pnlPatientInfo);
        pnlPatientInfo.setLayout(pnlPatientInfoLayout);
        pnlPatientInfoLayout.setHorizontalGroup(
            pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addComponent(rbtAddMale)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtAddFMale))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbAddFaculty, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtAddAge)
                        .addComponent(txtAddFName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        .addComponent(txtAddLName, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtAddAddress, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        pnlPatientInfoLayout.setVerticalGroup(
            pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtAddLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtAddAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtAddAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(rbtAddMale)
                    .addComponent(rbtAddFMale))
                .addGap(18, 18, 18)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbbAddFaculty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(170, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlAddLayout = new javax.swing.GroupLayout(pnlAdd);
        pnlAdd.setLayout(pnlAddLayout);
        pnlAddLayout.setHorizontalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPatientInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlAddLayout.setVerticalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPatientInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(343, Short.MAX_VALUE))
        );

        pnlAddPatient.add(pnlAdd, java.awt.BorderLayout.LINE_START);

        pnlMain.add(pnlAddPatient, "addPatient");

        pnlSearch.setLayout(new java.awt.BorderLayout());

        pnlChoice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        pnlChoice.setPreferredSize(new java.awt.Dimension(400, 599));

        pnlID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnlID.setForeground(new java.awt.Color(204, 204, 204));

        jLabel3.setText("Enter ID");

        txtID.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout pnlIDLayout = new javax.swing.GroupLayout(pnlID);
        pnlID.setLayout(pnlIDLayout);
        pnlIDLayout.setHorizontalGroup(
            pnlIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIDLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(28, 28, 28)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        pnlIDLayout.setVerticalGroup(
            pnlIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnlName.setForeground(new java.awt.Color(204, 204, 204));

        jLabel4.setText("Name");

        javax.swing.GroupLayout pnlNameLayout = new javax.swing.GroupLayout(pnlName);
        pnlName.setLayout(pnlNameLayout);
        pnlNameLayout.setHorizontalGroup(
            pnlNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        pnlNameLayout.setVerticalGroup(
            pnlNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        pnlFacultyOfDisease.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnlFacultyOfDisease.setForeground(new java.awt.Color(204, 204, 204));

        cbbSDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Internal medicine", "Surgical", "Cardiovascular" }));
        cbbSDepartment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbSDepartment.setEnabled(false);
        cbbSDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSDepartmentItemStateChanged(evt);
            }
        });

        jLabel5.setText("Department");

        javax.swing.GroupLayout pnlFacultyOfDiseaseLayout = new javax.swing.GroupLayout(pnlFacultyOfDisease);
        pnlFacultyOfDisease.setLayout(pnlFacultyOfDiseaseLayout);
        pnlFacultyOfDiseaseLayout.setHorizontalGroup(
            pnlFacultyOfDiseaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFacultyOfDiseaseLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbSDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFacultyOfDiseaseLayout.setVerticalGroup(
            pnlFacultyOfDiseaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFacultyOfDiseaseLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlFacultyOfDiseaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbSDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlFacultyOfDisease1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnlFacultyOfDisease1.setForeground(new java.awt.Color(204, 204, 204));
        pnlFacultyOfDisease1.setPreferredSize(new java.awt.Dimension(270, 133));

        jLabel1.setText("From");

        jLabel2.setText("To");

        cbbDayF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cbbMonthF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbbMonthF.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbMonthFItemStateChanged(evt);
            }
        });

        cbbYearF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012" }));
        cbbYearF.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbYearFItemStateChanged(evt);
            }
        });
        cbbYearF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbYearFActionPerformed(evt);
            }
        });

        cbbDayT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cbbMonthT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbbMonthT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbMonthTItemStateChanged(evt);
            }
        });

        cbbYearT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", " " }));
        cbbYearT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbYearTItemStateChanged(evt);
            }
        });

        jLabel6.setText("Day");

        jLabel7.setText("Day");

        jLabel9.setText("Month");

        jLabel10.setText("Month");

        jLabel11.setText("Year");

        jLabel12.setText("Year");

        javax.swing.GroupLayout pnlFacultyOfDisease1Layout = new javax.swing.GroupLayout(pnlFacultyOfDisease1);
        pnlFacultyOfDisease1.setLayout(pnlFacultyOfDisease1Layout);
        pnlFacultyOfDisease1Layout.setHorizontalGroup(
            pnlFacultyOfDisease1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFacultyOfDisease1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFacultyOfDisease1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFacultyOfDisease1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbYearT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMonthT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7))
                    .addGroup(pnlFacultyOfDisease1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbYearF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMonthF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFacultyOfDisease1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbDayF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbDayT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFacultyOfDisease1Layout.setVerticalGroup(
            pnlFacultyOfDisease1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFacultyOfDisease1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlFacultyOfDisease1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbbDayF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMonthF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(cbbYearF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(25, 25, 25)
                .addGroup(pnlFacultyOfDisease1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12)
                    .addComponent(cbbYearT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cbbMonthT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbbDayT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

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

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setEnabled(false);

        jLabel8.setText("In Hospital");

        cbbIn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        cbbIn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbInItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbbIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("All Patient");
        jButton1.setPreferredSize(new java.awt.Dimension(270, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlChoiceLayout = new javax.swing.GroupLayout(pnlChoice);
        pnlChoice.setLayout(pnlChoiceLayout);
        pnlChoiceLayout.setHorizontalGroup(
            pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceLayout.createSequentialGroup()
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(rbtID))
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(rbtSDepartment)))
                .addContainerGap(231, Short.MAX_VALUE))
            .addGroup(pnlChoiceLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pnlName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlFacultyOfDisease, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlFacultyOfDisease1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlChoiceLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtDate)
                            .addComponent(rbtIn)
                            .addComponent(rbtName))))
                .addGap(0, 80, Short.MAX_VALUE))
        );
        pnlChoiceLayout.setVerticalGroup(
            pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtSDepartment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFacultyOfDisease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFacultyOfDisease1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(303, Short.MAX_VALUE))
        );

        pnlSearch.add(pnlChoice, java.awt.BorderLayout.LINE_START);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 483, Short.MAX_VALUE))
        );

        pnlSearch.add(pnlContent, java.awt.BorderLayout.LINE_END);

        pnlMain.add(pnlSearch, "cardSearch");

        getContentPane().add(pnlMain, "card3");

        jMenu1.setText("Login");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItem1.setText("Admin");
        jMenu1.add(jMenuItem1);

        jMenuItem4.setText("Employee");
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Doctor");
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Employee");
        jMenu2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Search");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

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
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Doctor");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItem6.setText("Patient Manager");
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        CardLayout card1 = (CardLayout) pnlMain.getLayout();
        card1.show(pnlMain, "cardSearch");

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void rbtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtIDActionPerformed
        // TODO add your handling code here:
        Reset();
        rbtID.setSelected(true);
        choicesearch = 1;
        txtID.setEditable(true);
    }//GEN-LAST:event_rbtIDActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        model = new DefaultTableModel();
        tblResult.setModel(model);
        switch (choicesearch) {
            case 1:
                if (txtID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter ID!");
                } else {
                    try {
                        iID = Integer.parseInt(txtID.getText());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "ID valid!");
                        break;
                    }
                   searchByID();
                }
                break;
            case 2:
                if (txtName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter Name!");
                } else {
                    Name = txtName.getText();
                   searchByName();
                }
                break;
            case 3:
                searchByDepartment();
                break;
            case 4:
                searchByIn();
                break;
            case 5:
                int dayF = Integer.parseInt(cbbDayF.getSelectedItem().toString());
                int monthF = Integer.parseInt(cbbMonthF.getSelectedItem().toString());
                int yearF = Integer.parseInt(cbbYearF.getSelectedItem().toString());
                int dayT = Integer.parseInt(cbbDayT.getSelectedItem().toString());
                int monthT = Integer.parseInt(cbbMonthT.getSelectedItem().toString());
                int yearT = Integer.parseInt(cbbYearT.getSelectedItem().toString());
                DateF = monthF+"/"+dayF+"/"+yearF;
                DateT = monthT+"/"+dayT+"/"+yearT;
                searchByDateIn();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Please Choice Option Search!");
    }//GEN-LAST:event_btnSearchActionPerformed
    }
    private void rbtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtNameActionPerformed
        // TODO add your handling code here:
        Reset();
        rbtName.setSelected(true);
        choicesearch = 2;
        txtName.setEditable(true);
    }//GEN-LAST:event_rbtNameActionPerformed

    private void tblResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultMouseClicked
            }//GEN-LAST:event_tblResultMouseClicked

    private void rbtSDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtSDepartmentActionPerformed
        // TODO add your handling code here:
        Reset();
        rbtSDepartment.setSelected(true);
        choicesearch = 3;
        cbbSDepartment.setEnabled(true);
    }//GEN-LAST:event_rbtSDepartmentActionPerformed

    private void rbtInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtInActionPerformed
        // TODO add your handling code here:
        Reset();
        rbtIn.setSelected(true);
        choicesearch = 4;
        cbbIn.setEnabled(true);
    }//GEN-LAST:event_rbtInActionPerformed

    private void cbbInItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbInItemStateChanged
        // TODO add your handling code here:
        stt = cbbIn.getSelectedItem().toString();
    }//GEN-LAST:event_cbbInItemStateChanged

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void rbtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtDateActionPerformed
        // TODO add your handling code here:
        Reset();
        rbtDate.setSelected(true);
        choicesearch = 5;
        cbbDayF.setEnabled(true);
        cbbMonthF.setEnabled(true);
        cbbYearF.setEnabled(true);
        cbbDayT.setEnabled(true);
        cbbMonthT.setEnabled(true);
        cbbYearT.setEnabled(true);
    }//GEN-LAST:event_rbtDateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        allPatient();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbbSDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSDepartmentItemStateChanged
        // TODO add your handling code here:
        Department = cbbSDepartment.getSelectedItem().toString();
    }//GEN-LAST:event_cbbSDepartmentItemStateChanged

    private void cbbMonthFItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbMonthFItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbMonthF.getSelectedItem().toString());
        int year = Integer.parseInt(cbbYearF.getSelectedItem().toString());
        cbbDayF.setModel(tdv.getDayByMonth(month, year));
    }//GEN-LAST:event_cbbMonthFItemStateChanged

    private void cbbYearFItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbYearFItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbMonthF.getSelectedItem().toString());
        int year = Integer.parseInt(cbbYearF.getSelectedItem().toString());
        cbbDayF.setModel(tdv.getDayByMonth(month, year));
    }//GEN-LAST:event_cbbYearFItemStateChanged

    private void cbbYearFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbYearFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbYearFActionPerformed

    private void cbbYearTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbYearTItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbMonthT.getSelectedItem().toString());
        int year = Integer.parseInt(cbbYearT.getSelectedItem().toString());
        cbbDayT.setModel(tdv.getDayByMonth(month, year));
    }//GEN-LAST:event_cbbYearTItemStateChanged

    private void cbbMonthTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbMonthTItemStateChanged
        // TODO add your handling code here:
        DateTimeTDV tdv = new DateTimeTDV();
        int month = Integer.parseInt(cbbMonthT.getSelectedItem().toString());
        int year = Integer.parseInt(cbbYearT.getSelectedItem().toString());
        cbbDayT.setModel(tdv.getDayByMonth(month, year));
    }//GEN-LAST:event_cbbMonthTItemStateChanged

    private void jMenuItem3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jMenuItem3ItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem3ItemStateChanged

    private void cbbAddFacultyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAddFacultyItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbAddFacultyItemStateChanged

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) pnlMain.getLayout();
        card.show(pnlMain, "addPatient");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
    private javax.swing.ButtonGroup btgAddGender;
    private javax.swing.ButtonGroup btnG1;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cbbAddFaculty;
    private javax.swing.JComboBox cbbDayF;
    private javax.swing.JComboBox cbbDayT;
    private javax.swing.JComboBox cbbIn;
    private javax.swing.JComboBox cbbMonthF;
    private javax.swing.JComboBox cbbMonthT;
    private javax.swing.JComboBox cbbSDepartment;
    private javax.swing.JComboBox cbbYearF;
    private javax.swing.JComboBox cbbYearT;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel pnlAdd;
    private javax.swing.JPanel pnlAddPatient;
    private javax.swing.JPanel pnlChoice;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlFacultyOfDisease;
    private javax.swing.JPanel pnlFacultyOfDisease1;
    private javax.swing.JPanel pnlID;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlName;
    private javax.swing.JPanel pnlPatientInfo;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JRadioButton rbtAddFMale;
    private javax.swing.JRadioButton rbtAddMale;
    private javax.swing.JRadioButton rbtDate;
    private javax.swing.JRadioButton rbtID;
    private javax.swing.JRadioButton rbtIn;
    private javax.swing.JRadioButton rbtName;
    private javax.swing.JRadioButton rbtSDepartment;
    private javax.swing.JTable tblResult;
    private javax.swing.JTextField txtAddAddress;
    private javax.swing.JTextField txtAddAge;
    private javax.swing.JTextField txtAddFName;
    private javax.swing.JTextField txtAddLName;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
