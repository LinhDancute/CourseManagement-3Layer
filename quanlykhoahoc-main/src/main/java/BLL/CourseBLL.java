/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package BLL;

import DAL.CourseDAL;
import DTO.CourseDTO;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class CourseBLL {

    static ArrayList<CourseDTO> listCourseDTOs;
    private CourseDAL courseDAL = new CourseDAL();

    public CourseBLL() {
    }

    public static ArrayList<CourseDTO> getListCourse() {
        return listCourseDTOs;
    }

    public void getAllCourses(String orderby) throws Exception {
        if (listCourseDTOs == null) {
            listCourseDTOs = new ArrayList<CourseDTO>();
        }
        listCourseDTOs = courseDAL.loadCourse(orderby);// gọi Layer DAL hàm đọc data từ CSDL
    }
    
    public void addCourse(CourseDTO course) throws Exception {
        if (listCourseDTOs == null) {
            listCourseDTOs = new ArrayList<CourseDTO>();
        }
        courseDAL.InsertCourse(course);// gọi Layer DAL hàm đọc data từ CSDL
    }
    
    public static void main(String[] args) throws Exception {
        CourseBLL courseBLL = new CourseBLL();
        courseBLL.getAllCourses("ASC");
        getListCourse().forEach(s -> System.out.println(s));
    }
}
