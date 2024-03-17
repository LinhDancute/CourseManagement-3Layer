/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

package GUI;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLException;

import BLL.StudentGradeBLL;
import DAL.DBConnect.ConnectXamppMySQL;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class JInternalFrameGradeDetails extends JInternalFrame {


    public JInternalFrameGradeDetails() {
        initComponents();
        bll = new StudentGradeBLL();
        updateCombo();
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
                        String studentID = tableGradeDetails.getValueAt(selectedRow,1).toString();
                        Object gradeObject = tableGradeDetails.getValueAt(selectedRow, 4);
                        String grade = gradeObject != null ? gradeObject.toString() : "";
                        System.out.println("ID: "+studentID+" Grade: "+grade);

                        bll.updateGrade(studentID,grade);
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
                    String studentID = tableGradeDetails.getValueAt(selectedRow,0).toString();
                    Object gradeObject = tableGradeDetails.getValueAt(selectedRow, 4);
                    String grade = gradeObject != null ? gradeObject.toString() : "";
                    System.out.println("Clicked ID: "+studentID+" Grade: "+grade);
                }
            }

        });

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void buttonCloseActionPerformed(ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        setVisible(false);
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void comboOptionActionPerformed(ActionEvent evt){
        String selectedItem = (String) jComboBox1.getSelectedItem();
        System.out.println("selected Item: "+ selectedItem);
        int courseID = bll.getCourseID(selectedItem);
        System.out.println("CourseID: " + courseID);
        DefaultTableModel model = bll.loadTableByCourseID(courseID);
        tableGradeDetails.setModel(model); // Update the table model
    }

    private void updateCombo(){
        if (bll != null) {
            bll.updateCombo(jComboBox1);
        } else {
            System.out.println("BLL is null. Check initialization.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton buttonClose;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JTable tableGradeDetails;
    private StudentGradeBLL bll;

    // End of variables declaration//GEN-END:variables
}
