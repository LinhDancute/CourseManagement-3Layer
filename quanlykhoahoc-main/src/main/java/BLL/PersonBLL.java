package BLL;

import DAL.PersonDAL;
import BLL.DTO.PersonDTO;
import java.util.ArrayList;
import java.util.List;

/**
     *
     */

public class PersonBLL {
    static ArrayList<PersonDTO> listPerson;
    private PersonDAL data = new PersonDAL();
    
    public static List<PersonDTO> getListPerson(boolean isStudent) {
        // Gọi phương thức tương ứng trong PersonDAL và trả về kết quả
        return PersonDAL.getListPerson(isStudent);
    }
    
    public void loadPerson(boolean isStudent) throws Exception {
        if (listPerson == null) {
            listPerson = new ArrayList<PersonDTO>();
        }
        listPerson = (ArrayList<PersonDTO>) data.getListPerson(isStudent);// gọi Layer DAL hàm đọc data từ CSDL
    }

    
    public static boolean addStudent(PersonDTO student) throws Exception {
        return PersonDAL.addStudent(student);
    }
    
    public static boolean updateStudent(PersonDTO student) throws Exception {
        return PersonDAL.updateStudent(student);
    }
    
    public static boolean deleteStudent(int studentID) throws Exception {
        return PersonDAL.deleteStudent(studentID);
    }
     
     public static boolean addLecture(PersonDTO lecture) throws Exception {
        return PersonDAL.addLecture(lecture);
    }
    
    public static boolean updateLecture(PersonDTO lecture) throws Exception {
        return PersonDAL.updateLecture(lecture);
    }
    
    public static boolean deleteLecture(int lectureID) throws Exception {
        return PersonDAL.deleteLecture(lectureID);
    }

}
