/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;

import DAL.DBConnect.MyConnectUnit;
import DTO.CourseDTO;
import DTO.OnlineCourseDTO;
import DTO.OnsiteCourseDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ACER
 */
public class CourseDAL extends MyConnectUnit{

    ArrayList<CourseDTO> listCourseDTOs;
    
    public CourseDAL() {
        super();
    }
    
    //HIỂN THỊ DỮ LIỆU LÊN TABLE
    public ArrayList<CourseDTO> loadCourse(String orderby) throws Exception{
        ArrayList<CourseDTO> listCourse = new ArrayList<>();
        try {
            ResultSet resultSet = this.SelectCustomJoin("course as c",
                                                        "c.CourseID, c.Title, c.Credits, c.DepartmentID, col.URL, cos.Location, cos.Days, cos.Time",
                                                        "LEFT JOIN onlinecourse as col ON c.CourseID = col.CourseID LEFT JOIN onsitecourse as cos ON c.CourseID = cos.CourseID ", 
                                                        "c.CourseID");
            while (resultSet.next()) {                
                if (resultSet.getString("url") == null) {
                    CourseDTO onsiteCourse = new OnsiteCourseDTO(
                            resultSet.getInt("CourseID"), 
                            resultSet.getString("Title"),
                            resultSet.getInt("Credits"), 
                            resultSet.getString("DepartmentID"),
                            resultSet.getString("Location"), 
                            resultSet.getDate("Days"), 
                            resultSet.getTime("Time")
                    );
                    listCourse.add((CourseDTO) onsiteCourse);
                } else {
                    CourseDTO onlineCourse = new OnlineCourseDTO(
                            resultSet.getInt("CourseID"), 
                            resultSet.getString("Title"),
                            resultSet.getInt("Credits"), 
                            resultSet.getString("DepartmentID"),
                            resultSet.getString("url")
                    );
                    listCourse.add((CourseDTO) onlineCourse);
                }
            }
            resultSet.close();
            this.Close();//dong ket noi;

        } catch (SQLException ex) {
            System.out.println("Lỗi load dữ liệu"+ ex.getMessage());
            throw new Exception("Không thể load dữ liệu từ CSDL", ex);
        }
        return listCourse;
    }
    
    //THÊM MỚI KHÓA HỌC
    public void InsertCourse(CourseDTO course) {
        try {
            if (course instanceof OnlineCourseDTO) {
                this.InsertOnlineCourse((OnlineCourseDTO) course);
            } else {

                this.InsertOnsiteCourse((OnsiteCourseDTO) course);
            }
        } catch (Exception ex) {
            System.out.println("Khong the them Courses vao database !!!");
        }
    }
    
    //THÊM MỚI KHÓA HỌC ONLINE
    private void InsertOnlineCourse(OnlineCourseDTO onlineCourseDTO) throws Exception {
        HashMap<String, Object> InsertvaluesCourse = new HashMap<String, Object>();
        HashMap<String, Object> InsertvaluesOnlineCourse = new HashMap<String, Object>();

        InsertvaluesCourse.put("CourseID", onlineCourseDTO.getCourseID());
        InsertvaluesCourse.put("Title", onlineCourseDTO.getTitle());
        InsertvaluesCourse.put("Credits", onlineCourseDTO.getCredits());
        InsertvaluesCourse.put("DepartmentID", onlineCourseDTO.getDepartmentID());
        InsertvaluesOnlineCourse.put("URL", onlineCourseDTO.getUrl());

        try {
            this.Insert("course", InsertvaluesCourse);
            ResultSet resultSet = this.SelectCustomOrderby("course", "CourseID", null, "CourseID DESC limit 1");
            while (resultSet.next()) {
                InsertvaluesOnlineCourse.put("CourseID", resultSet.getInt("CourseID"));
                System.out.print(resultSet.getInt("CourseID"));
            }

            this.Insert("onlinecourse", InsertvaluesOnlineCourse);
        } catch (SQLException ex) {
            System.out.println("Khong the them CourseOnline vao database !!!");
        }
    }
    
    //THÊM MỚI KHÓA HỌC ONSITE
    private void InsertOnsiteCourse(OnsiteCourseDTO onsiteCourseDTO) throws Exception {
        HashMap<String, Object> InsertvaluesCourse = new HashMap<String, Object>();
        HashMap<String, Object> InsertvaluesOnsiteCourse = new HashMap<String, Object>();

        InsertvaluesCourse.put("CourseID", onsiteCourseDTO.getCourseID());
        InsertvaluesCourse.put("Title", onsiteCourseDTO.getTitle());
        InsertvaluesCourse.put("Credits", onsiteCourseDTO.getCredits());
        InsertvaluesCourse.put("DepartmentID", onsiteCourseDTO.getDepartmentID());
        InsertvaluesOnsiteCourse.put("Location", onsiteCourseDTO.getLocation());
        InsertvaluesOnsiteCourse.put("Days", onsiteCourseDTO.getDays());
        InsertvaluesOnsiteCourse.put("Time", onsiteCourseDTO.getTime());
        try {
            this.Insert("course", InsertvaluesCourse);
              ResultSet rs = this.SelectCustomOrderby("course", "CourseID", null, "CourseID DESC limit 1");
              while (rs.next()) {
                  InsertvaluesOnsiteCourse.put("CourseID", rs.getInt("CourseID"));
//            InsertvaluesOnsiteCourse.put("CourseID", this.getCourseIDNearly());
              }
            this.Insert("onsitecourse", InsertvaluesOnsiteCourse);

        } catch (Exception ex) {
            System.out.println("Khong the them CourseOnsite vao database !!!");
        }
    }
}
