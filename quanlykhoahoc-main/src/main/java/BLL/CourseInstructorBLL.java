/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package BLL;

import BLL.DTO.CourseInstructorDTO;
import DAL.CourseInstructorDAL;
import DAL.DBConnect.MyConnectUnit;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class CourseInstructorBLL extends MyConnectUnit{
    static ArrayList<CourseInstructorDTO> listCourseInstructor;
     private CourseInstructorDAL courseInstructorDAL=new CourseInstructorDAL();
     
    public CourseInstructorBLL() {
    }
    
    public static ArrayList<CourseInstructorDTO> getListCourseInstructor() {
        return listCourseInstructor;
    }
    
    public static void setListCourseInstructor(ArrayList<CourseInstructorDTO> listCourseInstructor) {
        CourseInstructorBLL.listCourseInstructor = listCourseInstructor;
    }
    
    //LẤY DỮ LIỆU
    public void  loadDSCourseInstructor() throws Exception{
        if(listCourseInstructor==null) listCourseInstructor = new ArrayList<CourseInstructorDTO>();
        System.out.println("list ci: " + listCourseInstructor);
        listCourseInstructor=courseInstructorDAL.loadDatabase();
        System.out.println("listci2: " + listCourseInstructor);
    }
    
    //THÊM 
    public void addCourseInstructor(CourseInstructorDTO csin) throws Exception{
        try{
            courseInstructorDAL.addCourseInstructor(csin);
            listCourseInstructor.add(csin);
        } 
        catch(Exception ex){
            System.out.println("Khong the them CourseInstructor Item vao database ");
        }
        
    }
    
    //XÓA
    public void deleteCourseInstructor(int courseID,int teacherID) throws Exception{
        for(CourseInstructorDTO courseInstructor : listCourseInstructor )
        {
            if(courseInstructor.getCourseID()==courseID)
            {   
                try {
                    courseInstructorDAL.delete(courseID,teacherID); 
                   listCourseInstructor.remove(courseInstructor);// xoá trong arraylist 
                } catch (Exception e) {
                    System.out.println("Khong the Xoa CourseInstructor vao database !!!");
                } 
                return;
            }
        }
        
    }
    
    //SỬA
    public void updateCourseInstructor(CourseInstructorDTO csin, int courseID, int personID) throws Exception{
         for(int i = 0 ; i < listCourseInstructor.size() ; i++)
        {
            if(listCourseInstructor.get(i).getCourseID()==csin.getCourseID())
            {
                try {
                    courseInstructorDAL.updateCourseInstructor(csin,courseID,personID);
                    listCourseInstructor.set(i, csin);               
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Khong the Cap nhat CourseInstructor vao database !!!");
                }
                return;
            }
        }
    }   

    public boolean isCourseInstructorExists(int courseID, int personID) throws Exception {
        String condition = "CourseID = " + courseID + " AND PersonID = " + personID;
        ResultSet rs = this.SelectCustom("courseinstructor", "*", condition);
        return rs.next(); // Returns true if entry exists, false otherwise
    }
}
