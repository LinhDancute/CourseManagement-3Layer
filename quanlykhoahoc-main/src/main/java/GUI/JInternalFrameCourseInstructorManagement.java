/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import BLL.CourseBLL;
import BLL.CourseInstructorBLL;
import BLL.DTO.CourseDTO;
import BLL.DTO.CourseInstructorDTO;
import BLL.DTO.PersonDTO;
import BLL.PersonBLL;
import DAL.DBConnect.MyConnectUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JInternalFrameCourseInstructorManagement extends javax.swing.JInternalFrame implements ItemListener{
    private DefaultTableModel model;
    CourseInstructorBLL courseInstructorBLL = new CourseInstructorBLL();
    CourseBLL courseBLL = new CourseBLL(); 
    PersonBLL personBLL = new PersonBLL();
    private MyConnectUnit myConnectUnit;
    
    public JInternalFrameCourseInstructorManagement() {
        try {
            initComponents();
            this.myConnectUnit = new MyConnectUnit();
            try {
                ShowDataBase();
            } catch (Exception ex) {
                Logger.getLogger(JInternalFrameCourseInstructorManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            loadCourseName(comboboxCourseName);
            loadLectureName(comboboxLectureName);

            comboboxCourseName.addItemListener(this);
            comboboxLectureName.addItemListener(this);

//            itemStateChanged(new ItemEvent(comboboxCourseName, ItemEvent.ITEM_STATE_CHANGED, comboboxCourseName.getSelectedItem(), ItemEvent.SELECTED));

           
            // Đặt ActionListener cho textFind
            textFind.addActionListener((ActionEvent e) -> {
            filterTable();
        });
        } catch (Exception ex) {
            Logger.getLogger(JInternalFrameCourseInstructorManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //HIỂN THỊ CourseID TƯƠNG ỨNG Title LÊN FIELD
    //HIỂN THỊ PersonID TƯƠNG ỨNG FirstName+LastName LÊN FIELD
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == comboboxCourseName) {
                String selectedTitle = (String) comboboxCourseName.getSelectedItem();
                try {
                    int courseID = courseBLL.getCourseIDByTitle(selectedTitle);
                    textCourseID.setText(String.valueOf(courseID));

                    loadLectureNamebyCourse(comboboxLectureName, courseID);
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == comboboxLectureName) {
                String selectedLectureName = (String) comboboxLectureName.getSelectedItem();
                try {
                    int personID = personBLL.getPersonIDByLectureName(selectedLectureName);
                    textLectureID.setText(String.valueOf(personID));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    
    //HIỂN THỊ CourseName LÊN COMBOBOX
    public void loadCourseName(JComboBox cb) throws Exception {
        if (courseBLL.getListCourse()== null) {
            courseBLL.loadDSCourse(title);
        }
        ArrayList<CourseDTO> courseList = courseBLL.getListCourse();
        for (CourseDTO c : courseList) {
            cb.addItem(c.getTitle());
        }
    }
    
    // HIỂN THỊ LectureName LÊN COMBOBOX
    public void loadLectureNamebyCourse(JComboBox cb, int selectedCourseID) throws Exception {
        ArrayList<PersonDTO> allPersons = (ArrayList<PersonDTO>) personBLL.getListPerson(false);

        // Lấy PersonID của những giảng viên đã được phân công giảng dạy khóa học
        ArrayList<Integer> assignedPersonIDs = personBLL.getAssignedPersonIDs(selectedCourseID);
        System.out.println("GUI personids: " + assignedPersonIDs);
        cb.removeAllItems();
        // Hiển thị những giảng viên chưa được phân công cho khóa học đó
        for (PersonDTO person : allPersons) {
            if (assignedPersonIDs.contains(person.getPersonID())) {
                String lectureName = person.getFirstname() + " " + person.getLastname();
                
                cb.addItem(lectureName);
                System.out.println("cb: " + lectureName);
            }
        }
    }

    
    // HIỂN THỊ LectureName LÊN COMBOBOX
    public void loadLectureName(JComboBox cb) throws Exception {
        ArrayList<PersonDTO> personList = (ArrayList<PersonDTO>) personBLL.getListPerson(false);
        System.out.println("Lecturer List: " + personList);
        
        for (PersonDTO person : personList) {
            cb.addItem(person.getFirstname()+ " " + person.getLastname());
        }
    }
    
    //XÓA TRẮNG
    private void ClearAll() {
        textCourseID.setText("");
        textLectureID.setText("");
    }

    //HIỂN THỊ DỮ LIỆU
    private void ShowDataBase() throws Exception {
        try {
            if (CourseInstructorBLL.getListCourseInstructor() == null) {
                courseInstructorBLL.loadDSCourseInstructor();
            }
            insertHeader();
            outModel(model, CourseInstructorBLL.getListCourseInstructor());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không thể load dữ liệu",
                    "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //RELOAD
    private void RefreshDataBase() throws Exception {
        try {
            courseInstructorBLL.loadDSCourseInstructor();
            insertHeader();
            outModel(model, CourseInstructorBLL.getListCourseInstructor());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không thể load dữ liệu",
                    "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }
  
    private void insertHeader() {
        Vector header = new Vector();
        header.add("Mã khoá học");
        header.add("Tên khóa học");
        header.add("Mã giảng viên");
        header.add("Tên giảng viên");

        model = new DefaultTableModel(header, 0);

    }

    private void outModel(DefaultTableModel model, ArrayList<CourseInstructorDTO> courseinstructor) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (CourseInstructorDTO courseInstructor : courseinstructor) {
            data = new Vector();
            data.add(courseInstructor.getCourseID());
            data.add(courseInstructor.getTitleCourse());
            data.add(courseInstructor.getPersonID());
            data.add(courseInstructor.getLectureName()); 

            model.addRow(data);
        }
        tableCourseInstructor.setModel(model);
    }
    
    // Phương thức để lọc và hiển thị sinh viên dựa trên nội dung của textFind
    private void filterTable() {
        String keyword = textFind.getText().trim(); // Lấy từ khoá tìm kiếm từ textFind

        // Kiểm tra nếu từ khoá không rỗng
        if (!keyword.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) tableCourseInstructor.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            tableCourseInstructor.setRowSorter(sorter);

            // Thiết lập bộ lọc để lọc dữ liệu dựa trên từ khoá
            RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + keyword);
            sorter.setRowFilter(rowFilter);
        } else {
            // Nếu từ khoá rỗng, hiển thị tất cả dữ liệu
            tableCourseInstructor.setRowSorter(null);
        }
    }

    public JInternalFrameCourseInstructorManagement(DefaultTableModel model, JDesktopPane DesktopDetails, JButton buttonClose, JButton buttonDelete, JButton buttonFind, JButton buttonRefresh, JButton buttonSave, JComboBox<String> comboboxCourseName, JComboBox<String> comboboxLectureName, JLabel jLabel1, JLabel jLabel4, JLabel jLabel5, JLabel jLabel6, JLabel jLabel8, JPanel jPanel1, JPanel jPanel3, JPanel jPanel4, JPanel jPanel5, JPanel jPanel6, JScrollPane jScrollPane1, JTable tableCourseInstructor, JTextField textCourseID, JTextField textEnrollmentID, JTextField textFind, JTextField textLectureID) {
        this.model = model;
        this.DesktopDetails = DesktopDetails;
        this.buttonClose = buttonClose;
        this.buttonDelete = buttonDelete;
        this.buttonRefresh = buttonRefresh;
        this.buttonSave = buttonSave;
        this.comboboxCourseName = comboboxCourseName;
        this.comboboxLectureName = comboboxLectureName;
        this.jLabel1 = jLabel1;
        this.jLabel4 = jLabel4;
        this.jLabel6 = jLabel6;
        this.jLabel8 = jLabel8;
        this.jPanel1 = jPanel1;
        this.jPanel3 = jPanel3;
        this.jPanel4 = jPanel4;
        this.jPanel5 = jPanel5;
        this.jPanel6 = jPanel6;
        this.jScrollPane1 = jScrollPane1;
        this.tableCourseInstructor = tableCourseInstructor;
        this.textCourseID = textCourseID;
        this.textFind = textFind;
        this.textLectureID = textLectureID;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DesktopDetails = new javax.swing.JDesktopPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboboxLectureName = new javax.swing.JComboBox<>();
        comboboxCourseName = new javax.swing.JComboBox<>();
        textLectureID = new javax.swing.JTextField();
        textCourseID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        textFind = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCourseInstructor = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        buttonSave = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();

        javax.swing.GroupLayout DesktopDetailsLayout = new javax.swing.GroupLayout(DesktopDetails);
        DesktopDetails.setLayout(DesktopDetailsLayout);
        DesktopDetailsLayout.setHorizontalGroup(
            DesktopDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        DesktopDetailsLayout.setVerticalGroup(
            DesktopDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Tên giảng viên");

        jLabel8.setText("Tên khóa học");

        textLectureID.setEnabled(false);

        textCourseID.setEnabled(false);

        jLabel2.setText("Mã giảng viên");

        jLabel3.setText("Mã khóa học");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(comboboxLectureName, javax.swing.GroupLayout.Alignment.LEADING, 0, 180, Short.MAX_VALUE)
                    .addComponent(comboboxCourseName, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textLectureID, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(textCourseID))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comboboxCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCourseID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboboxLectureName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textLectureID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel4.setText("Nhập tìm kiếm");

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
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(textFind, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setText("QUẢN LÝ PHÂN CÔNG GIẢNG DẠY");

        tableCourseInstructor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã giảng viên", "Tên giảng viên", "Mã khóa học", "Tên khóa học"
            }
        ));
        tableCourseInstructor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCourseInstructorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCourseInstructor);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
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

        buttonRefresh.setText("Làm mới");
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });

        buttonClose.setText("Đóng");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        buttonUpdate.setText("Sửa");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonClose)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(172, 172, 172)
                            .addComponent(jLabel1))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(34, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(290, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(157, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFindActionPerformed

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
        try {
            RefreshDataBase();
            ClearAll();
        } catch (Exception ex) {
            Logger.getLogger(JInternalFrameCourseInstructorManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        setVisible(false);
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        try {
            CourseInstructorDTO courseInstructor = new CourseInstructorDTO();
            if (!textCourseID.getText().isEmpty() && !textLectureID.getText().isEmpty()) {
                courseInstructor.setCourseID(Integer.parseInt(textCourseID.getText()));
                courseInstructor.setPersonID(Integer.parseInt(textLectureID.getText()));
                courseInstructorBLL.addCourseInstructor(courseInstructor);
                JOptionPane.showMessageDialog(this, "Tạo mới phân công giảng dạy thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                
                ClearAll();
                RefreshDataBase();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên cho CourseID và PersonID", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thực hiện thao tác", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void tableCourseInstructorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCourseInstructorMouseClicked
        int selectedRow = tableCourseInstructor.getSelectedRow();
        ClearAll();
        if (selectedRow >= 0) {
            if (tableCourseInstructor.getRowSorter() != null) {
                selectedRow = tableCourseInstructor.getRowSorter().convertRowIndexToModel(selectedRow);
            }

            textCourseID.setText(tableCourseInstructor.getModel().getValueAt(selectedRow, 0).toString());
            comboboxCourseName.setSelectedItem(tableCourseInstructor.getModel().getValueAt(selectedRow, 1));
            textLectureID.setText(tableCourseInstructor.getModel().getValueAt(selectedRow, 2).toString());
            comboboxLectureName.setSelectedItem(tableCourseInstructor.getModel().getValueAt(selectedRow, 3));
        }
    }//GEN-LAST:event_tableCourseInstructorMouseClicked

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa phân công này?",
                "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (confirm == 0)
        try {
            int teacherID = Integer.parseInt(textLectureID.getText());
            int courseID = Integer.parseInt(textCourseID.getText());
            courseInstructorBLL.deleteCourseInstructor(courseID, teacherID);//gọi Layer BLL xoá phân công     
            insertHeader();// chèn header cho table
            outModel(model, CourseInstructorBLL.getListCourseInstructor());// đổ data vào table
            ClearAll();
            RefreshDataBase();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Không thể xoá CourseInstructor ",
                    "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
        } else
            return;
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        try {
            CourseInstructorDTO courseInstructor = new CourseInstructorDTO();
            if (!textCourseID.getText().isEmpty() && !textLectureID.getText().isEmpty()) {
                int CourseID = Integer.parseInt(textCourseID.getText());
                int PersonID = Integer.parseInt(textLectureID.getText());
//                String CourseName = comboboxCourseName.getSelectedItem().toString();
//                String LectureName = comboboxLectureName.getSelectedItem().toString();


                courseInstructorBLL.updateCourseInstructor(courseInstructor, CourseID,PersonID);
                JOptionPane.showMessageDialog(this, "Cập nhật phân công giảng dạy thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                
                ClearAll();
                RefreshDataBase();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên cho CourseID và PersonID", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thực hiện thao tác", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonUpdateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopDetails;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JComboBox<String> comboboxCourseName;
    private javax.swing.JComboBox<String> comboboxLectureName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCourseInstructor;
    private javax.swing.JTextField textCourseID;
    private javax.swing.JTextField textFind;
    private javax.swing.JTextField textLectureID;
    // End of variables declaration//GEN-END:variables
    
//    public ArrayList<String> getAvailableLectureNames(String courseTitle) {
//        ArrayList<String> availableLectureNames = new ArrayList<>();
//        try {
//            // Build the SQL query
//            String customColumns = "CONCAT(p.FirstName, ' ', p.LastName) AS LectureName";
//            String joinClause = "courseinstructor as ci " +
//                                "JOIN person as p ON p.PersonID = ci.PersonID " +
//                                "JOIN course as c ON c.CourseID = ci.CourseID";
//            String condition = "c.Title = ?";
//            String orderBy = "ci.CourseID ASC";
//            
//            ResultSet rs = myConnectUnit.SelectCustomJoin("ci.CourseID, p.PersonID", joinClause, orderBy);
//            
//            // Process the result set
//            while (rs.next()) {
//                String lectureName = rs.getString("LectureName");
//                availableLectureNames.add(lectureName);
//            }
//
//            // Close the ResultSet
//            rs.close();
//        } catch (SQLException ex) {
//            // Handle SQL exceptions
//            ex.printStackTrace();
//        } catch (Exception e) {
//            // Handle other exceptions
//            e.printStackTrace();
//        }
//        return availableLectureNames;
//    }
//    private void updateCourseInstructor(CourseInstructorDTO csin) throws Exception {
//    HashMap<String, Object> updateValues = new HashMap<String, Object>();
//
//    // Set the values to be updated
//    updateValues.put("CourseID", csin.getCourseID());
//    updateValues.put("PersonID", csin.getPersonID());
//            System.out.println("c,p:" + updateValues);
//
//
//    // Create the condition for the update query
////    String condition = "CourseID = " + existingCourseID + " AND PersonID = " + existingPersonID;
////    System.out.println("c,p2:" + condition);
//    try {
//        // Perform the update operation
//        myConnectUnit.Update("courseinstructor", updateValues, null);
//        System.out.println("Update successfully");
//    } catch (SQLException ex) {
//        System.out.println("Khong the Cap nhat CourseInstructor vao database !!!");
//    }
//}

}
