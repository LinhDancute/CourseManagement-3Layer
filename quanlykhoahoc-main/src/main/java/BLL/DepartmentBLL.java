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

    public static ArrayList<DepartmentDTO> getListDepartment() {
        return listdepartmentDTOs;
    }

    public void getAllDepartments() throws Exception {
        if (listdepartmentDTOs == null) {
            listdepartmentDTOs = new ArrayList<DepartmentDTO>();
        }
        listdepartmentDTOs = departmentDAL.loadDepartment();
    }
    
    public static void main(String[] args) throws Exception {
        DepartmentBLL departmentBLL = new DepartmentBLL();
        departmentBLL.getAllDepartments();
        getListDepartment().forEach(s -> System.out.println(s));
    }
}
