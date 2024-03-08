package BLL;

import DAL.PersonDAL;
import BLL.DTO.PersonDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
     *
     */

public class PersonBLL {
    static ArrayList<PersonDTO> listPerson;
    private PersonDAL data = new PersonDAL();
    
    public static List<PersonDTO> getListPerson(boolean isStudent) {
        try {
            PersonDAL personDAL = new PersonDAL();
            return personDAL.getListPerson(isStudent);
        } catch (Exception ex) {
            Logger.getLogger(PersonBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>(); 
    }
    
    public void loadPerson(boolean isStudent) {
        if (listPerson == null) {
            listPerson = new ArrayList<>();
        }

        try {
            listPerson = (ArrayList<PersonDTO>) data.getListPerson(isStudent); // Gọi DAL để đọc dữ liệu từ DB
        } catch (Exception e) {
            e.printStackTrace();
        }
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
