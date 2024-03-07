/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package BLL;

import BLL.DTO.CourseInstructorDTO;
import DAL.CourseInstructorDAL;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class CourseInstructorBLL {
    static ArrayList<CourseInstructorDTO> listCourseInstructor;
     private CourseInstructorDAL courseInstructorDAL=new CourseInstructorDAL();
    public CourseInstructorBLL() {
    }
    
    //LẤY DỮ LIỆU
    public void  loadDSCourseInstructor(String orderby) throws Exception{
       
        if(listCourseInstructor==null) listCourseInstructor = new ArrayList<CourseInstructorDTO>();
        listCourseInstructor=courseInstructorDAL.loadDatabase(orderby);
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
    public void updateCourseInstructor(int id, CourseInstructorDTO csin) throws Exception{
         for(int i = 0 ; i < listCourseInstructor.size() ; i++)
        {
            if(listCourseInstructor.get(i).getCourseID()==csin.getCourseID())
            {
                try {
                    courseInstructorDAL.updateCourseInstructor(id,csin);
                    listCourseInstructor.set(i, csin);               
                } catch (Exception e) {
                    System.out.println("Khong the Cap nhat CourseInstructor vao database !!!");
                   
                }
                
                return;
            }
        }
    }
    
    public static void setListCourseInstructor(ArrayList<CourseInstructorDTO> listCourseInstructor) {
        CourseInstructorBLL.listCourseInstructor = listCourseInstructor;
    }

    public static ArrayList<CourseInstructorDTO> getListCourseInstructor() {
        return listCourseInstructor;
    }
    public  void setlistCourseInstructor(ArrayList<CourseInstructorDTO> listCourseInstructor) {
        CourseInstructorBLL.listCourseInstructor = listCourseInstructor;
    }

    public  ArrayList<CourseInstructorDTO> getlistCourseInstructor() {
        return listCourseInstructor;
    }
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
