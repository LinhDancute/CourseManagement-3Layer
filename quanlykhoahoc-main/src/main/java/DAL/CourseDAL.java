/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;

import BLL.DTO.CourseDTO;
import BLL.DTO.OnlineCourseDTO;
import BLL.DTO.OnsiteCourseDTO;
import DAL.DBConnect.MyConnectUnit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ACER
 */
public class CourseDAL extends MyConnectUnit{

    public CourseDAL() {
        super();

    }

    //LẤY DỮ LIỆU
    public ArrayList<CourseDTO> loadCourse(String orderby) throws Exception {
        ArrayList<CourseDTO> listCourse = new ArrayList<>();
        try {
            ResultSet rs = this.SelectCustomJoin(
                "course as c",
                "c.CourseID, c.Title, c.Credits, c.DepartmentID, conl.url, cos.Location, cos.Days, cos.Time",
                "LEFT OUTER JOIN onsitecourse as cos ON c.CourseID = cos.CourseID " +
                "LEFT OUTER JOIN onlinecourse as conl ON c.CourseID = conl.CourseID",
                "c.CourseID " + orderby
            );
            while (rs.next()) {
                if (rs.getString("URL") == null) {
                    CourseDTO cssite = new OnsiteCourseDTO(
                            rs.getInt("CourseID"), 
                            rs.getString("Title"),
                            rs.getInt("Credits"), 
                            rs.getString("DepartmentID"),
                            rs.getString("Location"), 
                            rs.getDate("Days"), 
                            rs.getString("Time")
                    );
                    listCourse.add((CourseDTO) cssite);
                } else {
                    CourseDTO cson = new OnlineCourseDTO(
                            rs.getInt("CourseID"), rs.getString("Title"),
                            rs.getInt("Credits"), rs.getString("DepartmentID"),
                            rs.getString("URL")
                    );
                    listCourse.add((CourseDTO) cson);
                }
            }
            rs.close();
            this.Close();//dong ket noi;

        } catch (SQLException ex) {
            System.out.println("Khong the load database Course");
        }
        return listCourse;
    }


    //THÊM KHÓA HỌC ONLINE
    private void addOnlineCourse(OnlineCourseDTO cs) throws Exception {
        HashMap<String, Object> InsertvaluesCourse = new HashMap<String, Object>();
        HashMap<String, Object> InsertvaluesCOnlineourse = new HashMap<String, Object>();

        InsertvaluesCourse.put("CourseID", cs.getCourseID());
        InsertvaluesCourse.put("Title", cs.getTitle());
        InsertvaluesCourse.put("Credits", cs.getCredits());
        InsertvaluesCourse.put("DepartmentID", cs.getDepartmentID());
        InsertvaluesCOnlineourse.put("URL", cs.getUrl());

        try {
            this.Insert("course", InsertvaluesCourse);
            ResultSet rs = this.SelectCustomOrderby("course", "CourseID", null, "CourseID DESC limit 1");
            while (rs.next()) {
                InsertvaluesCOnlineourse.put("CourseID", rs.getInt("CourseID"));
                System.out.print(rs.getInt("CourseID"));
            }

            this.Insert("onlinecourse", InsertvaluesCOnlineourse);
        } catch (SQLException ex) {
            System.out.println("Khong the them CourseOnline vao database !!!");
        }
    }

    private int getCourseIDNearly() throws Exception {
        ResultSet rs = this.SelectCustomOrderby("course", "CourseID", null, "CourseID DESC limit 1");
        while (rs.next()) {

            return rs.getInt("CourseID");
        }
        return -1;
    }

    //THÊM KHÓA HỌC ONSITE
    private void addOnSiteCourse(OnsiteCourseDTO cs) throws Exception {
        HashMap<String, Object> InsertvaluesCourse = new HashMap<String, Object>();
        HashMap<String, Object> InsertvaluesOnsiteCourse = new HashMap<String, Object>();

        InsertvaluesCourse.put("CourseID", cs.getCourseID());
        InsertvaluesCourse.put("Title", cs.getTitle());
        InsertvaluesCourse.put("Credits", cs.getCredits());
        InsertvaluesCourse.put("DepartmentID", cs.getDepartmentID());
        InsertvaluesOnsiteCourse.put("Location", cs.getLocation());
        InsertvaluesOnsiteCourse.put("Days", cs.getDays().toString());
        InsertvaluesOnsiteCourse.put("Time", cs.getTime());
        try {
            this.Insert("course", InsertvaluesCourse);
            InsertvaluesOnsiteCourse.put("CourseID", this.getCourseIDNearly());
            this.Insert("onsitecourse", InsertvaluesOnsiteCourse);
        } catch (Exception ex) {
            System.out.println("Khong the them CourseOnsite vao database !!!");
        }
    }

    //THÊM MỚI KHÓA HỌC
    public void addCourse(CourseDTO cs) {
        try {
            if (cs instanceof OnlineCourseDTO) {
                this.addOnlineCourse((OnlineCourseDTO) cs);
            } else {
                this.addOnSiteCourse((OnsiteCourseDTO) cs);
            }
        } catch (Exception ex) {
            System.out.println("Khong the them Courses vao database !!!");
        }
    }

    //CẬP NHẬT KHÓA HỌC ONLINE
    private void updateOnlineCourse(int id, OnlineCourseDTO cs) throws Exception {

        HashMap<String, Object> UpdatevaluesCourseOnline = new HashMap<String, Object>();
        UpdatevaluesCourseOnline.put("url", cs.getUrl());
        try {
            this.Update("onlinecourse", UpdatevaluesCourseOnline, "CourseID ='" + id + "'");
        } catch (SQLException ex) {
            System.out.println("Khong the cap nhat CourseOnline !!!");
        }
    }
    
    //CẬP NHẬT KHÓA HỌC ONSITE
    private void updateOnsiteCourse(int id, OnsiteCourseDTO cs) throws Exception {

        HashMap<String, Object> UpdatevaluesCourseOnsite = new HashMap<String, Object>();
        UpdatevaluesCourseOnsite.put("Location", cs.getLocation());
        UpdatevaluesCourseOnsite.put("Days", cs.getDays());
        UpdatevaluesCourseOnsite.put("Time", cs.getTime());

        try {
            this.Update("onsitecourse", UpdatevaluesCourseOnsite, "CourseID ='" + id + "'");
        } catch (SQLException ex) {
            System.out.println("Khong the cap nhat CourseOnline !!!");
        }
    }

    //CẬP NHẬT KHÓA HỌC
    public void updateCourse(int id, CourseDTO cs) throws Exception {
        HashMap<String, Object> UpdatevaluesCourse = new HashMap<String, Object>();
        UpdatevaluesCourse.put("CourseID", cs.getCourseID());
        UpdatevaluesCourse.put("Title", cs.getTitle());
        UpdatevaluesCourse.put("Credits", cs.getCredits());
        UpdatevaluesCourse.put("DepartmentID", cs.getDepartmentID());
        try {

            this.Update("course", UpdatevaluesCourse, "CourseID ='" + id + "'");
            ResultSet rs = this.Select("onlinecourse", "CourseID ='" + id + "'");
            if (cs instanceof OnsiteCourseDTO) {
                this.updateOnsiteCourse(id, (OnsiteCourseDTO) cs);
            } else {
                this.updateOnlineCourse(id, (OnlineCourseDTO) cs);
            }

        } catch (SQLException ex) {
            System.out.println("Khong the Cap nhat Course vao database !!!");
        }
    }

    //XÓA KHÓA HỌC
    public void deleteCourse(int courseID) {

        try {
            ResultSet rs = this.Select("onlinecourse", "CourseID ='" + courseID + "'");
            int check = -1;
            while (rs.next()) {
                check = rs.getInt("CourseID");
            }
            System.out.println("check: " + check);
            if (check == -1) {
                this.Delete("onsitecourse", "CourseID ='" + courseID + "'");
                this.Delete("course", "CourseID ='" + courseID + "'");
            } else {
                this.Delete("onlinecourse", "CourseID ='" + courseID + "'");
                this.Delete("course", "CourseID ='" + courseID + "'");
            }
        } catch (Exception e) {
            System.out.println("Lỗi không thể xóa course item !!");
        }
    }
    
    //LẤY DỮ LIỆU Tilte ỨNG VỚI CourseID
    public String getCourseTitleByID(int courseID) throws Exception {
        String title = null;
        try {
            ResultSet rs = this.Select("course", "CourseID = " + courseID);
            if (rs.next()) {
                title = rs.getString("Title");
            }
        } catch (SQLException ex) {
            System.out.println("Khong the lay Course Title theo CourseID !!!");
        }
        return title;
    }
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
