/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;

import BLL.DTO.StudentGradeDTO;
import DAL.DBConnect.MyConnectUnit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class StudentGradeDAL extends MyConnectUnit{

    public ArrayList<StudentGradeDTO> loadDatabase() throws Exception {
        ArrayList<StudentGradeDTO> dsach = new ArrayList<>();
        try {
            ResultSet rs = this.Select("studentgrade");
            while (rs.next()) {
                StudentGradeDTO g = new StudentGradeDTO(rs.getInt("EnrollmentID"), rs.getInt("CourseID"),
                        rs.getInt("StudentID"), rs.getFloat("Grade"));

                dsach.add(g);
            }
            rs.close();
            this.Close();
        } catch (SQLException ex) {
            System.out.println("Khong the load database StudentGrade: " + ex);

        }
        return dsach;
    }
}
