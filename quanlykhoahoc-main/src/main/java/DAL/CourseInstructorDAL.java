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
        try {
            ResultSet rs = this.SelectCustomOrderby("course as c , person as p, courseinstructor as ci",
                    "ci.ID,c.CourseID,c.Title, p.PersonID, p.Lastname, p.Firstname",
                    "c.CourseID=ci.CourseID AND p.PersonID=ci.PersonID",
                    "ci.ID " + orderby);
            while (rs.next()) {
                CourseInstructorDTO csin = new CourseInstructorDTO(
                        rs.getInt("ID"), 
                        rs.getInt("CourseID"),
                        rs.getInt("PersonID"),
                        rs.getString("Title"), 
                        rs.getString("Lastname") + " " + rs.getString("Firstname")
                );
                list.add(csin);
            }
            rs.close();
            this.Close();//dong ket noi;

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
    public void updateCourseInstructor(int id, CourseInstructorDTO csin) throws Exception {
        HashMap<String, Object> Updatevalues = new HashMap<String, Object>();

        Updatevalues.put("CourseID", csin.getCourseID());
        Updatevalues.put("PersonID", csin.getPersonID());

        try {
            this.Update("courseinstructor", Updatevalues, "ID ='" + id + "'");
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
