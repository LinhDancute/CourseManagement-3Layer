/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BLL.PersonBLL;
import DAL.PersonDAL;
import DTO.PersonDTO;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Win10pro
 */
public class PersonTable {
    
    public static void showDataStudent(JTable tableStudent) {
        // Xóa dữ liệu cũ trước khi hiển thị mới
        DefaultTableModel model = (DefaultTableModel) tableStudent.getModel();
        model.setRowCount(0);

        // Lấy danh sách sinh viên từ cơ sở dữ liệu
        List<PersonDTO> listStudent = PersonBLL.getListPerson(true);

        // Điền dữ liệu vào bảng
        int stt = 1;
        for (PersonDTO student : listStudent) {
            Object[] row = {
                stt,
                student.getPersonID(), // Mã sinh viên
                student.getFirstname(), // Họ sinh viên
                student.getLastname(), // Tên sinh viên
                student.getEnrollmentDate() // Ngày ghi danh
            };
            model.addRow(row);
            stt++;
        }
    }


    void addMouseListener(MouseAdapter mouseAdapter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void showDataLectures(JTable tableLectures) {
        // Xóa dữ liệu cũ trước khi hiển thị mới
        DefaultTableModel model = (DefaultTableModel) tableLectures.getModel();
        model.setRowCount(0);
        
        // Lấy danh sách sinh viên từ cơ sở dữ liệu
        List<PersonDTO> listLectures = PersonBLL.getListPerson(false);
        
        // Điền dữ liệu vào bảng
        int stt = 1;
        for (PersonDTO lecture : listLectures) {
            Object[] row = {
                stt,
                lecture.getPersonID(), // Mã sinh viên
                lecture.getFirstname(), // Họ sinh viên
                lecture.getLastname(), // Tên sinh viên
                lecture.getHireDate() // Ngày ghi danh
            };
            model.addRow(row);
            stt++;
        }
    }
    
}
