/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import BLL.CourseBLL;
import BLL.CourseInstructorBLL;
import BLL.DTO.CourseDTO;
import BLL.DTO.CourseInstructorDTO;
import BLL.DTO.DepartmentDTO;
import BLL.DTO.OnlineCourseDTO;
import BLL.DTO.OnsiteCourseDTO;
import BLL.DTO.StudentGradeDTO;
import BLL.DepartmentBLL;
import BLL.PersonBLL;
import BLL.StudentGradeBLL;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JInternalFrameCourseManagement extends javax.swing.JInternalFrame {
    private DefaultTableModel model;
    CourseBLL courseBLL = new CourseBLL();
    DepartmentBLL departmentBLL = new DepartmentBLL();
    CourseInstructorBLL courseInstructorBLL = new CourseInstructorBLL();
    StudentGradeBLL studentGradeBLL = new StudentGradeBLL();
    
    public JInternalFrameCourseManagement() throws Exception {
        initComponents();
        ShowDataBase("ASC");
        loadDepartmentID(comboboxDeparmentID);
        
        // Đặt ActionListener cho textFind
        textFind.addActionListener((ActionEvent e) -> {
            filterTable();
        });
    }
    
    //HIỂN THỊ DỮ LIỆU DepartmentID lên COMBOBOX
    public void loadDepartmentID(JComboBox cb) throws Exception {
        if (departmentBLL.getListDepartment() == null) {
            departmentBLL.loadDepartment();
        }
        ArrayList<DepartmentDTO> departmentList = departmentBLL.getListDepartment();
        for (DepartmentDTO d : departmentList) {
            cb.addItem(d.getDepartmentID());
        }
    }

    //CLEAR DỮ LIỆU TRÊN FIELD
    private void ClearAll() {
        textCourseID.setText(courseBLL.remindCourseID());
        textTitle.setText("");
        textCredit.setText("");
        comboboxDeparmentID.setSelectedItem("");
        dateDays.setDate(null);
        textTime.setText("");
        textLocation.setText("");
        textURL.setText("");
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

    
    private void RefreshDataBase(String orderby) throws Exception {
        try {
            courseBLL.loadDSCourse(orderby);
            insertHeader();
            outModel(model, CourseBLL.getListCourse());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không thể load dữ liệu ",
                    "Thông Báo Lỗi", JOptionPane.ERROR_MESSAGE);
        }

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
        tableCourse.setModel(model);
    }
    
    // Phương thức để lọc và hiển thị sinh viên dựa trên nội dung của textFind
    private void filterTable() {
        String keyword = textFind.getText().trim(); // Lấy từ khoá tìm kiếm từ textFind

        // Kiểm tra nếu từ khoá không rỗng
        if (!keyword.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) tableCourse.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            tableCourse.setRowSorter(sorter);

            // Thiết lập bộ lọc để lọc dữ liệu dựa trên từ khoá
            RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + keyword);
            sorter.setRowFilter(rowFilter);
        } else {
            // Nếu từ khoá rỗng, hiển thị tất cả dữ liệu
            tableCourse.setRowSorter(null);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        textFind = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCourse = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textCourseID = new javax.swing.JTextField();
        textTitle = new javax.swing.JTextField();
        comboboxDeparmentID = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        textCredit = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        textURL = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        textLocation = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        dateDays = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        textTime = new javax.swing.JTextField();
        buttonDepartmentList = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        buttonSave = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        Refresh = new javax.swing.JButton();
        buttonOnsite = new javax.swing.JButton();
        buttonOnline = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DesktopDetails = new javax.swing.JDesktopPane();

        jLabel4.setText("Nhập tìm kiếm");

        textFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFindActionPerformed(evt);
            }
        });

        tableCourse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khóa học", "Tên khóa học", "Mã khoa", "Số tín chỉ", "URL", "Địa điểm", "Ngày", "Giờ"
            }
        ));
        tableCourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCourseMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCourse);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Mã khóa học");

        jLabel5.setText("Tên khóa học");

        jLabel6.setText("Mã khoa");

        textCourseID.setEnabled(false);
        textCourseID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCourseIDActionPerformed(evt);
            }
        });

        jLabel8.setText("Số tín chỉ");

        textCredit.setToolTipText("");

        jLabel9.setText("URL");

        jLabel10.setText("Địa điểm");

        jLabel11.setText("Ngày");

        jLabel12.setText("Thời gian");

        textTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTimeActionPerformed(evt);
            }
        });

        buttonDepartmentList.setText("Danh sách khoa");
        buttonDepartmentList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDepartmentListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(60, 60, 60)
                        .addComponent(dateDays, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(47, 47, 47)
                        .addComponent(textTime, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2))
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textURL)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textCourseID, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboboxDeparmentID, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(25, 25, 25)
                                                .addComponent(textTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(buttonDepartmentList)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(164, 164, 164)
                                .addComponent(textLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textCourseID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(textTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboboxDeparmentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDepartmentList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textCredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(textURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(dateDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(textTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonSave.setText("Lưu");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonDelete.setText("Xóa");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        Refresh.setText("Làm mới");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });

        buttonOnsite.setText("Onsite");
        buttonOnsite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOnsiteActionPerformed(evt);
            }
        });

        buttonOnline.setText("Online");
        buttonOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOnlineActionPerformed(evt);
            }
        });

        buttonClose.setText("Đóng");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        buttonUpdate.setText("Cập nhật");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonOnsite)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonOnline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Refresh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Refresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOnsite)
                    .addComponent(buttonOnline))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonClose)
                .addContainerGap())
        );

        jLabel1.setText("QUẢN LÝ KHÓA HỌC");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(textFind)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout DesktopDetailsLayout = new javax.swing.GroupLayout(DesktopDetails);
        DesktopDetails.setLayout(DesktopDetailsLayout);
        DesktopDetailsLayout.setHorizontalGroup(
            DesktopDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        DesktopDetailsLayout.setVerticalGroup(
            DesktopDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DesktopDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(DesktopDetails)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFindActionPerformed

    private void textCourseIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCourseIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCourseIDActionPerformed

    private void textTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTimeActionPerformed

    private void buttonDepartmentListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDepartmentListActionPerformed
        JInternalFrameDepartmentList departmentList = new JInternalFrameDepartmentList();
        DesktopDetails.add(departmentList);
        departmentList.setVisible(true);
    }//GEN-LAST:event_buttonDepartmentListActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        setVisible(false);
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        
        try {
            RefreshDataBase("ASC");
            ClearAll();
            
            textURL.setVisible(true);
            textLocation.setVisible(true);
            dateDays.setVisible(true);
            textTime.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(JInternalFrameCourseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_RefreshActionPerformed

    private void buttonOnsiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOnsiteActionPerformed
        textURL.setVisible(false);
        textLocation.setVisible(true);
        dateDays.setVisible(true);
        textTime.setVisible(true);
        ClearAll();
    }//GEN-LAST:event_buttonOnsiteActionPerformed

    private void buttonOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOnlineActionPerformed
        textURL.setVisible(true);
        textLocation.setVisible(false);
        dateDays.setVisible(false);
        textTime.setVisible(false);
        ClearAll();
    }//GEN-LAST:event_buttonOnlineActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        int CourseID = Integer.parseInt(textCourseID.getText());
        String Title = textTitle.getText();
        int Credits = Integer.parseInt(textCredit.getText());
        String DepartmentID = comboboxDeparmentID.getSelectedItem().toString();
        String Location = textLocation.getText();
        String Url = textURL.getText();
        String Time = textTime.getText();
        
        java.util.Date chosenDate = dateDays.getDate();
        java.util.Date currentDate = new java.util.Date();
        java.util.Date utilDays = (chosenDate != null) ? chosenDate : currentDate;
        java.sql.Date Days = new java.sql.Date(utilDays.getTime());

        try {
            if (!textURL.getText().isEmpty()) {
                CourseDTO onlineCourse = new OnlineCourseDTO(CourseID, Title, Credits, DepartmentID, Url);

                courseBLL.addCourse(onlineCourse);
                JOptionPane.showMessageDialog(this, "Thêm mới khóa học Online thành công",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                CourseDTO onsiteCourse = new OnsiteCourseDTO(CourseID, Title, Credits, DepartmentID, Location, Days, Time);

                courseBLL.addCourse(onsiteCourse);// gọi Layer Bll Thêm khóa học
                courseBLL.updateCourse(CourseID,onsiteCourse);
                JOptionPane.showMessageDialog(this, "Lưu khóa học Onsite thành công",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

            ClearAll();
            RefreshDataBase("DESC");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Không thể tạo khóa học mới",
                    "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        ArrayList<StudentGradeDTO> studentGradeList = new ArrayList<>();
        ArrayList<CourseInstructorDTO> courseIntructorList = new ArrayList<>();

        int confirm = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa khóa học này?",
                "Thông báo", JOptionPane.YES_NO_OPTION);
        if (confirm == 0)
        try {
            int courseID = Integer.parseInt(textCourseID.getText());


            studentGradeBLL.loadDSGrade();
            courseInstructorBLL.loadDSCourseInstructor();
            studentGradeList = studentGradeBLL.searchGradeWithCourseID(courseID);
            courseIntructorList = courseInstructorBLL.searchCourseID(courseID);

            if (!studentGradeList.isEmpty() || !courseIntructorList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Khóa Học này đang có lịch học hoặc đang được phân công",
                        "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                courseBLL.deleteCourse(courseID);//gọi Layer BLL xoá 
            }

            insertHeader();// chèn header cho table
            outModel(model, CourseBLL.getListCourse());// đổ data vào table
            ClearAll();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Không thể xoá khóa học này",
                    "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
        } else
            return;
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void tableCourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCourseMouseClicked
        int selectedRow = tableCourse.getSelectedRow();
        ClearAll();
        if (selectedRow >= 0) {
            if (tableCourse.getRowSorter() != null) {
                selectedRow = tableCourse.getRowSorter().convertRowIndexToModel(selectedRow);
            }

            textCourseID.setText(tableCourse.getModel().getValueAt(selectedRow, 0).toString());
            textTitle.setText(tableCourse.getModel().getValueAt(selectedRow, 1).toString());
            textCredit.setText(tableCourse.getModel().getValueAt(selectedRow, 2).toString());
            comboboxDeparmentID.setSelectedItem(tableCourse.getModel().getValueAt(selectedRow, 3));
            if (tableCourse.getModel().getValueAt(selectedRow, 4) == null) {
                try {
                    
                    textLocation.setText(tableCourse.getModel().getValueAt(selectedRow, 5).toString());
                    
                    String dateString = tableCourse.getModel().getValueAt(selectedRow, 6).toString();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.sql.Date sqlDate = new java.sql.Date(dateFormat.parse(dateString).getTime());
                    dateDays.setDate(sqlDate);
                    
                    textTime.setText(tableCourse.getModel().getValueAt(selectedRow, 7).toString());
                    
                    textURL.setVisible(false);
                    textLocation.setVisible(true);
                    dateDays.setVisible(true);
                    textTime.setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(JInternalFrameCourseManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                
                textURL.setText(tableCourse.getModel().getValueAt(selectedRow, 4).toString());
                
                textURL.setVisible(true);
                textLocation.setVisible(false);
                dateDays.setVisible(false);
                textTime.setVisible(false);
            }
        }
    }//GEN-LAST:event_tableCourseMouseClicked

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        int CourseID = Integer.parseInt(textCourseID.getText());
        String Title = textTitle.getText();
        int Credits = Integer.parseInt(textCredit.getText());
        String DepartmentID = comboboxDeparmentID.getSelectedItem().toString();
        String Location = textLocation.getText();
        String Url = textURL.getText();
        String Time = textTime.getText();
        
        java.util.Date chosenDate = dateDays.getDate();
        java.util.Date currentDate = new java.util.Date();
        java.util.Date utilDays = (chosenDate != null) ? chosenDate : currentDate;
        java.sql.Date Days = new java.sql.Date(utilDays.getTime());

        try {
            if (!textURL.getText().isEmpty()) {
                CourseDTO onlineCourse = new OnlineCourseDTO(CourseID, Title, Credits, DepartmentID, Url);

                courseBLL.updateCourse(CourseID,onlineCourse);
                JOptionPane.showMessageDialog(this, "Cập nhật khóa học Online thành công",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                CourseDTO onsiteCourse = new OnsiteCourseDTO(CourseID, Title, Credits, DepartmentID, Location, Days, Time);

                courseBLL.addCourse(onsiteCourse);// gọi Layer Bll Thêm khóa học
                courseBLL.updateCourse(CourseID,onsiteCourse);
                JOptionPane.showMessageDialog(this, "Lưu khóa học Onsite thành công",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

            ClearAll();
            RefreshDataBase("DESC");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Không thể tạo khóa học mới",
                    "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopDetails;
    private javax.swing.JButton Refresh;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonDepartmentList;
    private javax.swing.JButton buttonOnline;
    private javax.swing.JButton buttonOnsite;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JComboBox<String> comboboxDeparmentID;
    private com.toedter.calendar.JDateChooser dateDays;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCourse;
    private javax.swing.JTextField textCourseID;
    private javax.swing.JTextField textCredit;
    private javax.swing.JTextField textFind;
    private javax.swing.JTextField textLocation;
    private javax.swing.JTextField textTime;
    private javax.swing.JTextField textTitle;
    private javax.swing.JTextField textURL;
    // End of variables declaration//GEN-END:variables
}
