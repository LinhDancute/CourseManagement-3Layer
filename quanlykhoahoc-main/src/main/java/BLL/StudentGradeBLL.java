package BLL;

import DAL.StudentGradeDAL;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class StudentGradeBLL {
    private StudentGradeDAL dal;

    public StudentGradeBLL() {
        dal = new StudentGradeDAL();
    }

    public void updateCombo(JComboBox<String> comboBox) {
        try {
            ResultSet rs = dal.getCourses();
            while (rs.next()) {
                comboBox.addItem(rs.getString("Title"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public int getCourseID(String courseTitle) {
        try {
            return dal.getCourseID(courseTitle);
        } catch (SQLException ex) {
            // Handle exception
            return -1;
        }
    }

    public DefaultTableModel loadTableByCourseID(int courseID) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("STT");
        model.addColumn("Mã sinh viên");
        model.addColumn("Họ sinh viên");
        model.addColumn("Tên sinh viên");
        model.addColumn("Điểm");
        try {
            ResultSet rs = dal.getGradeDetailsByCourseID(courseID);
            int stt = 1;
            if (rs != null){
                while (rs.next()) {
                    model.addRow(new Object[]{
                            stt++,
                            rs.getString("StudentID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("Grade")
                    });
                }
            }else {
                System.out.println("BLL is null. Check initialization.");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return model;
    }

    // Add the following method to the StudentGradeBLL class
    public DefaultTableModel loadStudentGrades() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("STT");
        model.addColumn("Mã ghi danh");
        model.addColumn("Mã sinh viên");
        model.addColumn("Mã khóa học");
        model.addColumn("Điểm");
        try {
            ResultSet rs = dal.getAllStudentGrades(); // Modify this according to your DAL method
            int stt = 1;
            if (rs != null) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                            stt++,
                            rs.getString("EnrollmentID"),
                            rs.getString("StudentID"),
                            rs.getString("CourseID"),
                            rs.getString("Grade")
                    });
                }
            } else {
                System.out.println("ResultSet is null. Check DAL method.");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return model;
    }


    public void updateGrade(String studentID, String grade) {
        if (validateInputGrade(grade)) {
            try {
                dal.updateGrade(studentID, grade);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else {
            System.out.println("Input the grade in number format");
        }
    }

    private boolean validateInputGrade(String grade){
        String regex = "\\d+";
        return grade.matches(regex);
    }

    public void saveDataToDatabase(String enrollmentID, String studentID, String courseID, String grade){
        try {
            dal.saveData(enrollmentID,studentID,courseID,grade);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
