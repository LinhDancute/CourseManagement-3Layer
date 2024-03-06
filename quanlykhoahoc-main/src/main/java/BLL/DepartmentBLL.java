/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package BLL;

import DAL.DepartmentDAL;
import BLL.DTO.DepartmentDTO;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class DepartmentBLL {
    static ArrayList<DepartmentDTO> listDepartment;
    private DepartmentDAL data = new DepartmentDAL();

    public DepartmentBLL() {
    }
    
    public static ArrayList<DepartmentDTO> getListDepartment(){
        return listDepartment;
    }
    
    public void loadDepartment() throws Exception {

        if (listDepartment == null) {
            listDepartment = new ArrayList<DepartmentDTO>();
        }
        listDepartment = data.loadDepartment();
    }
    
    public static void main(String[] args) throws Exception {
        DepartmentBLL dll = new DepartmentBLL();
        dll.loadDepartment();
        getListDepartment().forEach(s->System.out.println(s));
    }
}
