/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;

import DAL.DBConnect.MyConnectUnit;
import BLL.DTO.DepartmentDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class DepartmentDAL extends MyConnectUnit{
    ArrayList<DepartmentDTO> listDepartmentDTOs;
    
    public DepartmentDAL() {
        super();
    }
    
    public ArrayList<DepartmentDTO> loadDepartment() throws Exception{
        ArrayList<DepartmentDTO> listDepartment = new ArrayList<>();
        try {
            ResultSet rs = this.Select("department");
            while (rs.next()) {                
                DepartmentDTO department = new DepartmentDTO(
                        rs.getString("DepartmentID"), 
                        rs.getString("Name"),
                        rs.getDouble("Budget"),
                        rs.getDate("StartDate"),
                        rs.getInt("Administrator")
                );
                listDepartment.add(department);
            }
            rs.close();
            this.Close();//dong ket noi;

        } catch (SQLException ex) {
            System.out.println("Lỗi load dữ liệu"+ ex.getMessage());
            throw new Exception("Không thể load dữ liệu từ CSDL", ex);
        }
        return listDepartment;
    }
}
