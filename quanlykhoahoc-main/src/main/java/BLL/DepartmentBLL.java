/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package BLL;

import DAL.DepartmentDAL;
import DTO.DepartmentDTO;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class DepartmentBLL {
    static ArrayList<DepartmentDTO> listdepartmentDTOs;
    private DepartmentDAL departmentDAL = new DepartmentDAL();

    public DepartmentBLL() {
    }
    
    public static ArrayList<DepartmentDTO> getListDepartment(DepartmentBLL departmentBLL) {
    return departmentBLL.listdepartmentDTOs;
}
    
    public ArrayList<DepartmentDTO> getAllDepartments() throws Exception {
            System.out.println("Getting all departments...");

        if (listdepartmentDTOs == null) {
            listdepartmentDTOs = new ArrayList<DepartmentDTO>();
        }

        try {
            ArrayList<DepartmentDTO> loadedDepartments = departmentDAL.loadDepartment();

            if (loadedDepartments != null) {
                listdepartmentDTOs.addAll(loadedDepartments);
                System.out.println("thành công.");
            } else {
                // Handle the case when loadedDepartments is null
                System.out.println("Lỗi load dữ liệu từ DAL.");
            }
        } catch (Exception e) {
            System.out.println("Exception while loading departments: " + e.getMessage());
            e.printStackTrace();
        }

        return listdepartmentDTOs;  // Return the list of departments
    }

    
    public static void main(String[] args) throws Exception {
        DepartmentBLL departmentBLL = new DepartmentBLL();
        departmentBLL.getAllDepartments();
        getListDepartment(departmentBLL).forEach(s -> System.out.println(s));
    }
}
