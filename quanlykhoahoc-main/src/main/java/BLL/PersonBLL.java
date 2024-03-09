package BLL;

import DAL.PersonDAL;
import BLL.DTO.PersonDTO;
import DAL.DBConnect.MyConnectUnit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonBLL extends MyConnectUnit{
    private ArrayList<PersonDTO> listPerson;
    private PersonDAL data = new PersonDAL();
    
    public List<PersonDTO> getListPerson(boolean isStudent) {
        try {
            PersonDAL personDAL = new PersonDAL();
            listPerson = (ArrayList<PersonDTO>) personDAL.getListPerson(isStudent);
            System.out.println("get list person: " + listPerson);
            return listPerson;
        } catch (Exception ex) {
            Logger.getLogger(PersonBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>(); 
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

    public int getPersonIDByLectureName(String selectedLectureName) {
        System.out.println("listPerson: " + listPerson);

        if (listPerson != null) {
            for (PersonDTO person : listPerson) {
                String fullName = (person.getFirstname() != null ? person.getFirstname() : "") +
                                  " " +
                                  (person.getLastname() != null ? person.getLastname() : "");

                if (fullName.trim().equalsIgnoreCase(selectedLectureName.trim())) {
                    return person.getPersonID();
                }
            }
        }
        return -1;
    }

     public String getLectureNameByID(int personID) throws Exception {
        String lectureName = null;
        try {
            ResultSet rs = this.Select("person", "PersonID = " + personID);
            if (rs.next()) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                lectureName = firstName + " " + lastName;
            }
        } catch (SQLException ex) {
            System.out.println("Khong the lay Lecture Name theo PersonID !!!");
        }
        return lectureName;
    }
}
