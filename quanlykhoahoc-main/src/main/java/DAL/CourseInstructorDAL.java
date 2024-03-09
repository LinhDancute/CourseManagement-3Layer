/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;

import BLL.DTO.CourseInstructorDTO;
import DAL.DBConnect.MyConnectUnit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ACER
 */
public class CourseInstructorDAL extends MyConnectUnit{

    public CourseInstructorDAL() {
        super();

    }

    //LẤY DỮ LIỆU
    public ArrayList<CourseInstructorDTO> loadDatabase(String orderby) throws Exception {
        ArrayList<CourseInstructorDTO> list = new ArrayList<>();
        System.out.println("array list ci: " + list);
        try {
//            String query = "SELECT ci.CourseID, c.Title, ci.PersonID, CONCAT(p.FirstName, ' ', p.LastName) AS LectureName " +
//                    "FROM courseinstructor ci " +
//                    "JOIN course c ON ci.CourseID = c.CourseID " +
//                    "JOIN person p ON ci.PersonID = p.PersonID " +
//                    "ORDER BY " + orderby;
//            ResultSet rs = this.SelectCustom("courseinstructor", "ci.CourseID, c.Title, ci.PersonID, p.FirstName, p.LastName", null, query);
//                ResultSet rs = this.SelectCustomJoin(
//                "couseinstructor as ci",
//                "ci.CourseID, p.PersonID, p.LastName",
//                "LEFT OUTER JOIN person as p on p.PersonID = ci.PersonID ",
//                "ci.CourseID " + orderby
//            );
            ResultSet rs = this.Select("courseinstructor");
            System.out.println("result rs: " + rs);
            while (rs.next()) {
                CourseInstructorDTO csin = new CourseInstructorDTO(
                        rs.getInt("CourseID"),
                        rs.getInt("PersonID")
//                        rs.getString("TitleCourse")
//                        rs.getString("LectureName")
                );
                list.add(csin);
                System.out.println("Number of records retrieved1: " + list.size());
            }
            System.out.println("Number of records retrieved2: " + list.size());
            rs.close();
            this.Close(); // Close the database connection

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
    public void updateCourseInstructor(CourseInstructorDTO csin) throws Exception {
        HashMap<String, Object> Updatevalues = new HashMap<String, Object>();

        Updatevalues.put("CourseID", csin.getCourseID());
        Updatevalues.put("PersonID", csin.getPersonID());

        try {
            this.Update("courseinstructor", Updatevalues, null);
        } catch (SQLException ex) {
            System.out.println("Khong the Cap nhat CourseInstructor vao database !!!");
        }
    }

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
