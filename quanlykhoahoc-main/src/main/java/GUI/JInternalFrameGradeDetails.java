/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

package GUI;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLException;
import DAL.DBConnect.ConnectXamppMySQL;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class JInternalFrameGradeDetails extends JInternalFrame {
    private ConnectXamppMySQL connect = new ConnectXamppMySQL();

    public JInternalFrameGradeDetails() {
        
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jComboBox1 = new JComboBox<>();
        jScrollPane1 = new JScrollPane();
        tableGradeDetails = new JTable();
        buttonClose = new JButton();

        jLabel1.setText("DANH SÁCH ĐIỂM SINH VIÊN");

        jLabel2.setText("Tên khóa học");

        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { }));

        tableGradeDetails.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sinh viên", "Họ sinh viên", "Tên sinh viên", "Điểm"
            }
        ));
        jScrollPane1.setViewportView(tableGradeDetails);

        buttonClose.setText("Đóng");
        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        updateCombo();

        jComboBox1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                comboOptionActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(200, 200, 200))
            .addGroup(layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(buttonClose)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))

                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonClose)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tableGradeDetails.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    int selectedRow = tableGradeDetails.getSelectedRow();
                    System.out.println("selected row: "+selectedRow);

                    if (selectedRow != -1 && selectedRow <= tableGradeDetails.getRowCount() -1){
                        String EnrollmentID = (String) tableGradeDetails.getValueAt(selectedRow,0);
                        String grade = (String) tableGradeDetails.getValueAt(selectedRow, 4);
                        System.out.println("ID: "+EnrollmentID+" Grade: "+grade);

                        String sql = "UPDATE StudentGrade SET Grade = ? WHERE EnrollmentID = ?";
                        try {
                            PreparedStatement pst = connect.getConnect().prepareStatement(sql);
                            pst.setString(1, String.valueOf(grade));
                            pst.setString(2, String.valueOf(EnrollmentID));
                            pst.executeUpdate();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }

                    }

                }

            }
        });

        tableGradeDetails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int selectedRow = tableGradeDetails.getSelectedRow();
                if (selectedRow >= 0) {
                    String studentID = (String) tableGradeDetails.getValueAt(selectedRow,0);
                    String grade = (String) tableGradeDetails.getValueAt(selectedRow, 4);
                    System.out.println("Clicked ID: "+studentID+" Grade: "+grade);
                }
            }

        });

        pack();
    }// </editor-fold>                        


    private void buttonCloseActionPerformed(ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        setVisible(false);
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void comboOptionActionPerformed(ActionEvent evt){
        String selectedItem = (String) jComboBox1.getSelectedItem();
        System.out.println("selected Item: "+ selectedItem);
        int courseID = getCourseID(selectedItem);
        System.out.println("CourseID: " + courseID);
        loadTableByID(courseID);
    }

    private int getCourseID(String courseTitle){
        int courseID = -1;
        String sql = "SELECT CourseID FROM `course` WHERE Title = ?";
        try{
            PreparedStatement pst = connect.getConnect().prepareStatement(sql);
            pst.setString(1, courseTitle);
            ResultSet rs = pst.executeQuery();

            if (rs.next()){
                courseID = rs.getInt("CourseID");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return courseID;
    }

    private void updateCombo(){
        String sql = "select * from course";
        try {
            PreparedStatement pst = connect.getConnect().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                jComboBox1.addItem(rs.getString("Title"));
            }
        } catch (Exception e){

        }
    }

    private void loadTableByID(int courseID){
        DefaultTableModel model = (DefaultTableModel) tableGradeDetails.getModel();
        model.setRowCount(0);

        String sql = "SELECT stg.EnrollmentID, stg.StudentID, p.FirstName, p.LastName, stg.Grade\n" +
                "FROM studentgrade AS stg \n" +
                "JOIN person AS p\n" +
                "ON stg.StudentID = p.PersonID\n" +
                "WHERE stg.CourseID = ?";

        try {
            PreparedStatement pst = connect.getConnect().prepareStatement(sql);
            pst.setString(1, String.valueOf(courseID));
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                String stt = rs.getString("EnrollmentID");
                String studentID = rs.getString("StudentID");
                String studentLastName = rs.getString("FirstName");
                String studentFirstName = rs.getString("LastName");
                String grade = rs.getString("Grade");

                model.addRow(new Object[]{stt, studentID, studentFirstName, studentLastName, grade});
            }
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton buttonClose;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JTable tableGradeDetails;

    // End of variables declaration//GEN-END:variables
}
