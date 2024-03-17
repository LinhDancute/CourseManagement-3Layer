/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package BLL;

import BLL.DTO.StudentGradeDTO;
import DAL.StudentGradeDAL;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class StudentGradeBLL {

    static ArrayList<StudentGradeDTO> listGrade;


    public void loadDSGrade() throws Exception {
        StudentGradeDAL data = new StudentGradeDAL();
        if (listGrade == null) {
            listGrade = new ArrayList<StudentGradeDTO>();
        }
        listGrade = data.loadDatabase();
    }
    
    public ArrayList<StudentGradeDTO> searchGradeWithCourseID(int courseID) {
        ArrayList<StudentGradeDTO> search = new ArrayList<>();

        for (StudentGradeDTO g : listGrade) {
            if (g.getCourseID() == courseID) {
                search.add(g);
            }
        }
        return search;
    }
}
