/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

/**
 *
 * @author ACER
 */
public class JFrameMenu extends javax.swing.JFrame {
    
    /**
     * Creates new form JFrameMenu
     */
    public JFrameMenu() {
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DesktopShow = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuitemCM = new javax.swing.JMenuItem();
        menuitemSM = new javax.swing.JMenuItem();
        menuitemLM = new javax.swing.JMenuItem();
        menuitemCIM = new javax.swing.JMenuItem();
        menuitemSGM = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuitemCoursesList = new javax.swing.JMenuItem();
        menuitemLecturesList = new javax.swing.JMenuItem();
        menuitemDepartmentsList = new javax.swing.JMenuItem();
        menuitemStudentList = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout DesktopShowLayout = new javax.swing.GroupLayout(DesktopShow);
        DesktopShow.setLayout(DesktopShowLayout);
        DesktopShowLayout.setHorizontalGroup(
            DesktopShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1500, Short.MAX_VALUE)
        );
        DesktopShowLayout.setVerticalGroup(
            DesktopShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );

        jMenu1.setText("Danh mục quản lý");

        menuitemCM.setText("Quản lý khóa học");
        menuitemCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemCMActionPerformed(evt);
            }
        });
        jMenu1.add(menuitemCM);

        menuitemSM.setText("Quản lý học viên");
        menuitemSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemSMActionPerformed(evt);
            }
        });
        jMenu1.add(menuitemSM);

        menuitemLM.setText("Quản lý giảng viên");
        menuitemLM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemLMActionPerformed(evt);
            }
        });
        jMenu1.add(menuitemLM);

        menuitemCIM.setText("Quản lý phân công giảng dạy");
        menuitemCIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemCIMActionPerformed(evt);
            }
        });
        jMenu1.add(menuitemCIM);

        menuitemSGM.setText("Quản lý kết quả khóa học ");
        menuitemSGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemSGMActionPerformed(evt);
            }
        });
        jMenu1.add(menuitemSGM);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Danh mục danh sách");

        menuitemCoursesList.setText("Danh sách khóa học");
        menuitemCoursesList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemCoursesListActionPerformed(evt);
            }
        });
        jMenu2.add(menuitemCoursesList);

        menuitemLecturesList.setText("Danh sách giảng viên");
        menuitemLecturesList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemLecturesListActionPerformed(evt);
            }
        });
        jMenu2.add(menuitemLecturesList);

        menuitemDepartmentsList.setText("Danh sách khoa");
        menuitemDepartmentsList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemDepartmentsListActionPerformed(evt);
            }
        });
        jMenu2.add(menuitemDepartmentsList);

        menuitemStudentList.setText("Danh sách sinh viên");
        menuitemStudentList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemStudentListActionPerformed(evt);
            }
        });
        jMenu2.add(menuitemStudentList);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(DesktopShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(DesktopShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //QUẢN LÝ KHÓA HỌC
    private void menuitemCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemCMActionPerformed
        DesktopShow.removeAll();
        DesktopShow.repaint();
        
        JInternalFrameCourseManagement courseManagement = new JInternalFrameCourseManagement();
        DesktopShow.add(courseManagement);
        courseManagement.setVisible(true);
    }//GEN-LAST:event_menuitemCMActionPerformed

    //QUẢN LÝ PHÂN CÔNG GIẢNG DẠY
    private void menuitemCIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemCIMActionPerformed
        DesktopShow.removeAll();
        DesktopShow.repaint();
        
        JInternalFrameCourseInstructorManagement courseInstructorManagement = new JInternalFrameCourseInstructorManagement();
        DesktopShow.add(courseInstructorManagement);
        courseInstructorManagement.setVisible(true);
    }//GEN-LAST:event_menuitemCIMActionPerformed
    
    //QUẢN LÝ GIẢNG VIÊN
    private void menuitemLMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemLMActionPerformed
        DesktopShow.removeAll();
        DesktopShow.repaint();
        
        JInternalFrameLecturesManagement lecturesManagement = new JInternalFrameLecturesManagement();
        DesktopShow.add(lecturesManagement);
        lecturesManagement.setVisible(true);
    }//GEN-LAST:event_menuitemLMActionPerformed

    //QUẢN LÝ KẾT QUẢ KHÓA HỌC
    private void menuitemSGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemSGMActionPerformed
        DesktopShow.removeAll();
        DesktopShow.repaint();
        
        JInternalFrameStudentGradeManagement studentGradeManagement = new JInternalFrameStudentGradeManagement();
        DesktopShow.add(studentGradeManagement);
        studentGradeManagement.setVisible(true);
    }//GEN-LAST:event_menuitemSGMActionPerformed

    //QUẢN LÝ HỌC VIÊN
    private void menuitemSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemSMActionPerformed
        DesktopShow.removeAll();
        DesktopShow.repaint();
        
        JInternalFrameStudentManagement studentManagement = new JInternalFrameStudentManagement();
        DesktopShow.add(studentManagement);
        studentManagement.setVisible(true);
    }//GEN-LAST:event_menuitemSMActionPerformed

    //DANH SÁCH GIẢNG VIÊN
    private void menuitemLecturesListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemLecturesListActionPerformed
        JInternalFrameLecturesList lecturesList = new JInternalFrameLecturesList();
        DesktopShow.add(lecturesList);
        lecturesList.setVisible(true);
    }//GEN-LAST:event_menuitemLecturesListActionPerformed

    //DANH SÁCH KHÓA HỌC
    private void menuitemCoursesListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemCoursesListActionPerformed
        JInternalFrameCourseList courseList = new JInternalFrameCourseList();
        DesktopShow.add(courseList);
        courseList.setVisible(true);
    }//GEN-LAST:event_menuitemCoursesListActionPerformed

    //DANH SÁCH KHOA
    private void menuitemDepartmentsListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemDepartmentsListActionPerformed
        JInternalFrameDepartmentList departmentList = new JInternalFrameDepartmentList();
        DesktopShow.add(departmentList);
        departmentList.setVisible(true);
    }//GEN-LAST:event_menuitemDepartmentsListActionPerformed

    //DANH SÁCH SINH VIÊN
    private void menuitemStudentListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemStudentListActionPerformed
        JInternalFrameStudentList studentList = new JInternalFrameStudentList();
        DesktopShow.add(studentList);
        studentList.setVisible(true);
    }//GEN-LAST:event_menuitemStudentListActionPerformed

    public javax.swing.JDesktopPane getDesktopShow() {
        return DesktopShow;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopShow;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuitemCIM;
    private javax.swing.JMenuItem menuitemCM;
    private javax.swing.JMenuItem menuitemCoursesList;
    private javax.swing.JMenuItem menuitemDepartmentsList;
    private javax.swing.JMenuItem menuitemLM;
    private javax.swing.JMenuItem menuitemLecturesList;
    private javax.swing.JMenuItem menuitemSGM;
    private javax.swing.JMenuItem menuitemSM;
    private javax.swing.JMenuItem menuitemStudentList;
    // End of variables declaration//GEN-END:variables
}
