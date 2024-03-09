/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import BLL.CourseBLL;
import BLL.CourseInstructorBLL;
import BLL.DTO.CourseDTO;
import BLL.DTO.OnlineCourseDTO;
import BLL.DTO.OnsiteCourseDTO;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ACER
 */
public class JInternalFrameCourseList extends javax.swing.JInternalFrame {
    private CourseBLL courseBLL;
    DefaultTableModel model = new DefaultTableModel();
    
    public JInternalFrameCourseList() throws Exception {
        initComponents();
        ShowDataBase("ASC");
        
        // Đặt ActionListener cho textFind
        textFind.addActionListener((ActionEvent e) -> {
            filterTable();
        });
    }

    private void insertHeader() {
        Vector header = new Vector();
        header.add("Mã khoá học");
        header.add("Tên khóa học");
        header.add("Số tín chỉ");
        header.add("Mã khoa");
        header.add("URL");
        header.add("Địa điểm");
        header.add("Ngày");
        header.add("Giờ");

        model = new DefaultTableModel(header, 0);
    }
    
    private void outModel(DefaultTableModel model, ArrayList<CourseDTO> course) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (CourseDTO cs : course) {
            data = new Vector();
            data.add(cs.getCourseID());
            data.add(cs.getTitle());
            data.add(cs.getCredits());
            data.add(cs.getDepartmentID());
            data.add((cs instanceof OnlineCourseDTO) ? ((OnlineCourseDTO) cs).getUrl() : null);
            data.add((cs instanceof OnsiteCourseDTO) ? ((OnsiteCourseDTO) cs).getLocation() : null);
            data.add((cs instanceof OnsiteCourseDTO) ? ((OnsiteCourseDTO) cs).getDays() : null);
            data.add((cs instanceof OnsiteCourseDTO) ? ((OnsiteCourseDTO) cs).getTime() : null);

            model.addRow(data);
        }
        tableCourseList.setModel(model);
    }
    
    //HIỂN THỊ DỮ LIỆU LÊN TABLE
    private void ShowDataBase(String orderby) throws Exception {
        try {
            if (CourseBLL.getListCourse() == null) {
                courseBLL.loadDSCourse(orderby);
            }
            insertHeader();
            outModel(model, CourseBLL.getListCourse());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không thể load dữ liệu ",
                    "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    // Phương thức để lọc và hiển thị sinh viên dựa trên nội dung của textFind
    private void filterTable() {
        String keyword = textFind.getText().trim(); // Lấy từ khoá tìm kiếm từ textFind

        // Kiểm tra nếu từ khoá không rỗng
        if (!keyword.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) tableCourseList.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            tableCourseList.setRowSorter(sorter);

            // Thiết lập bộ lọc để lọc dữ liệu dựa trên từ khoá
            RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + keyword);
            sorter.setRowFilter(rowFilter);
        } else {
            // Nếu từ khoá rỗng, hiển thị tất cả dữ liệu
            tableCourseList.setRowSorter(null);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableCourseList = new javax.swing.JTable();
        buttonClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        textFind = new javax.swing.JTextField();

        tableCourseList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khóa học", "Tên khóa học", "Số tín chỉ", "Mã khoa ", "Địa điểm", "URL", "Ngày", "Giờ"
            }
        ));
        jScrollPane1.setViewportView(tableCourseList);

        buttonClose.setText("Đóng");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        jLabel1.setText("DANH SÁCH KHÓA HỌC");

        jLabel3.setText("Thông tin tìm kiếm");

        textFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFind)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(309, 309, 309))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonClose)
                .addGap(349, 349, 349))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonClose)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        setVisible(false);
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void textFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFindActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCourseList;
    private javax.swing.JTextField textFind;
    // End of variables declaration//GEN-END:variables
}
