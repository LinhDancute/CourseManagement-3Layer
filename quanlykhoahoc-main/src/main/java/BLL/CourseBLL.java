/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package BLL;

import BLL.DTO.CourseDTO;
import DAL.CourseDAL;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class CourseBLL {

    static ArrayList<CourseDTO> listCourse;
    private CourseDAL data = new CourseDAL();

    public CourseBLL() {
    }

    public static ArrayList<CourseDTO> getListCourse() {
        return listCourse;
    }

    public static void setListCourse(ArrayList<CourseDTO> listCourse) {
        CourseBLL.listCourse = listCourse;
    }

    //HIỂN THỊ
    public void loadDSCourse(String orderby) throws Exception {

        if (listCourse == null) {
            listCourse = new ArrayList<CourseDTO>();
        }
        listCourse = data.loadCourse(orderby);// gọi Layer DAL hàm đọc data từ CSDL
    }

    //THÊM
    public void addCourse(CourseDTO cs) throws Exception {
        if (listCourse == null) {
            listCourse = new ArrayList<CourseDTO>();
        }
        data.addCourse(cs);// gọi Layer DAL hàm đọc data từ CSDL
    }

    //XÓA
    public void deleteCourse(int courseID) throws Exception {
        for (CourseDTO csin : listCourse) {
            if (csin.getCourseID() == courseID) {
                try {
                    data.deleteCourse(courseID);
                    listCourse.remove(csin);// xoá trong arraylist
                } catch (Exception e) {
                    System.out.println("Khong the Xoa Course vao database !!!");
                }
                return;
            }
        }
    }

    //CẬP NHẬT
    public void updateCourse(int id, CourseDTO cs) throws Exception {
        for (int i = 0; i < listCourse.size(); i++) {
            if (listCourse.get(i).getCourseID() == cs.getCourseID()) {
                try {
                    data.updateCourse(id, cs);
                    listCourse.set(i, cs);
                } catch (Exception e) {
                    System.out.println("Khong the Cap nhat Course vao database !!!");
                }

                return;
            }
        }
    }
    
    public String remindCourseID() {
        int max = 0;
        String s = "";
        for (CourseDTO hd : listCourse) {
            int id = hd.getCourseID();
            if (id > max) {
                max = id;
            }
        }
        return s + (max + 1);
    }
    public static void main(String[] args) throws Exception {
        CourseBLL bll = new CourseBLL();
        bll.loadDSCourse("ASC");
        ArrayList<CourseDTO> ar = new ArrayList<>();
        getListCourse().forEach(s-> System.out.println(s));
    }
}
