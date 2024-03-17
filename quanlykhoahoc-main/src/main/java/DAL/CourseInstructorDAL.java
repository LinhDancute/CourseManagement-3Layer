/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;

import BLL.DTO.CourseInstructorDTO;
import DAL.DBConnect.ConnectXamppMySQL;
import DAL.DBConnect.MyConnectUnit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ACER
 */
public class CourseInstructorDAL extends MyConnectUnit {

    private ConnectXamppMySQL connect;

    public CourseInstructorDAL() {
        super();
        connect = new ConnectXamppMySQL();
    }

    //LẤY DỮ LIỆU
    public ArrayList<CourseInstructorDTO> loadDatabase() throws Exception {
        ArrayList<CourseInstructorDTO> list = new ArrayList<>();
        System.out.println("array list ci: " + list);
        try {
            ResultSet rs = this.SelectCustomJoin(
                    "courseinstructor as ci",
                    "ci.CourseID, p.PersonID, CONCAT(p.FirstName, ' ', p.LastName) AS LectureName, c.Title as TitleCourse",
                    "JOIN person as p on p.PersonID = ci.PersonID "
                    + "JOIN course as c on c.CourseID = ci.CourseID ",
                    "ci.CourseID ASC");
            System.out.println("result rs: " + rs);
            while (rs.next()) {
                CourseInstructorDTO csin = new CourseInstructorDTO(
                        rs.getInt("CourseID"),
                        rs.getInt("PersonID"),
                        rs.getString("TitleCourse"),
                        rs.getString("LectureName")
                );
                list.add(csin);
            }
            rs.close();
            this.Close();

        } catch (SQLException ex) {
            System.out.println("Khong the load database CourseInstructor: " + ex);
        }

        return list;
    }

    //THÊM MỚI PHÂN CÔNG GIẢNG DẠY
    public void addCourseInstructor(CourseInstructorDTO csin) throws Exception {
        HashMap<String, Object> Insertvalues = new HashMap<String, Object>();

        Insertvalues.put("CourseID", csin.getCourseID());
        Insertvalues.put("PersonID", csin.getPersonID());
        try {
            this.Insert("courseinstructor", Insertvalues);
        } catch (SQLException ex) {
            System.out.println("Khong the them CourseInstructor vao database !!!");
        }
    }
    //CẬP NHẬT PHÂN CÔNG GIẢNG DẠY
    public void updateCourseInstructor(CourseInstructorDTO csin, int courseID, int personID) throws Exception {
        HashMap<String, Object> updateValues = new HashMap<String, Object>();

        String courseName = csin.getTitleCourse();
        String courseIDQuery = "SELECT CourseID FROM course WHERE Title = '" + courseName + "'";
        ResultSet courseIDResultSet = connect.excuteQuery(courseIDQuery);
        int newCourseID = -1;

        if (courseIDResultSet.next()) {
            newCourseID = courseIDResultSet.getInt("CourseID");
        } else {
            throw new SQLException("CourseID not found for CourseName: " + courseName);
        }

        String lectureName = csin.getLectureName();
        String personIDQuery = "SELECT PersonID FROM person WHERE CONCAT(FirstName, ' ', LastName) = '" + lectureName + "'";
        ResultSet personIDResultSet = connect.excuteQuery(personIDQuery);
        int newPersonID = -1;

        if (personIDResultSet.next()) {
            newPersonID = personIDResultSet.getInt("PersonID");
        } else {
            throw new SQLException("PersonID not found for LectureName: " + lectureName);
        }

        updateValues.put("CourseID", newCourseID);
        updateValues.put("PersonID", newPersonID);

        String condition = "CourseID = " + courseID + " AND PersonID = " + personID;

        try {
            this.Update("courseinstructor", updateValues, condition);
            System.out.println("Update successfully");
        } catch (SQLException ex) {
            System.out.println("Failed to update course instructor: " + ex.getMessage());
        }
    }



    //XÓA
    public void delete(int courseID, int teacherID) {
        try {
            this.Delete("courseinstructor",
                    "CourseID ='" + courseID + "'AND PersonID ='" + teacherID + "'");
        } catch (Exception e) {
            System.out.println("Lỗi không thể xóa courseinstructor item !!");
        }

    }

    public static void main(String args[]) {
        // TODO code application logic here
    }
}
