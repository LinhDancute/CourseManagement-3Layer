package DAL;

import DTO.PersonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAL.DBConnect.MyDatabaseManager;

public class PersonDAL {

    /**
     *
     * @param isStudent
     * @return
     */
    public static List<PersonDTO> getListPerson(boolean isStudent) {
    List<PersonDTO> listPerson = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    
    try {
        connection = MyDatabaseManager.getConnection();
        String sql = "SELECT * FROM person WHERE ";
        
        if (isStudent) {
            // Lấy danh sách sinh viên (HireDate = Null)
            sql += "HireDate IS NULL";
        } else {
            // Lấy danh sách giảng viên (EnrollmentDate = Null)
            sql += "EnrollmentDate IS NULL";
        }
        
        statement = connection.prepareStatement(sql);
        resultSet = statement.executeQuery();
        
        while (resultSet.next()){
            PersonDTO person = new PersonDTO();
            person.setPersonID(resultSet.getInt("PersonID"));
            person.setFirstname(resultSet.getString("Firstname"));
            person.setLastname(resultSet.getString("Lastname"));
            person.setHireDate(resultSet.getDate("HireDate"));
            person.setEnrollmentDate(resultSet.getDate("EnrollmentDate"));
            listPerson.add(person);
        }
        
    } catch(SQLException e){
        // Xử lý ngoại lệ, ví dụ: in lỗi ra console
        e.printStackTrace();
    } finally{
        try{
            // Đóng tất cả các tài nguyên
            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        } catch(SQLException e){
            // Xử lý ngoại lệ khi đóng tài nguyên
            e.printStackTrace();
        }
    }
    
    return listPerson;
}

    
    public static boolean addStudent(PersonDTO student){
        String sql = "INSERT INTO person (Firstname, Lastname, HireDate, EnrollmentDate) VALUES (?, ?,NULL ,?)";
        
        try (Connection conn = MyDatabaseManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1, student.getFirstname());
            pstmt.setString(2, student.getLastname());
            pstmt.setDate(3, new java.sql.Date(student.getEnrollmentDate().getTime()));
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
            
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateStudent(PersonDTO student) {
        String sql = "UPDATE person SET Firstname = ?, Lastname = ?, EnrollmentDate = ? WHERE PersonID = ?";

        try (Connection conn = MyDatabaseManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getFirstname());
            pstmt.setString(2, student.getLastname());
            pstmt.setDate(3, new java.sql.Date(student.getEnrollmentDate().getTime()));
            pstmt.setInt(4, student.getPersonID());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            return false;
        }
    }
    
     public static boolean deleteStudent(int studentID) {
        String sql = "DELETE FROM person WHERE PersonID = ?";
        
        try (Connection conn = MyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, studentID);
            int rowsDeleted = pstmt.executeUpdate();
            
            return rowsDeleted > 0;
            
        } catch (SQLException e) {
            return false;
        }
    }
     
     public static boolean addLecture(PersonDTO lecture){
        String sql = "INSERT INTO person (Firstname, Lastname, HireDate, EnrollmentDate) VALUES (?, ?,? ,NULL)";
        
        try (Connection conn = MyDatabaseManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1, lecture.getFirstname());
            pstmt.setString(2, lecture.getLastname());
            pstmt.setDate(3, new java.sql.Date(lecture.getHireDate().getTime()));
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
            
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean updateLecture(PersonDTO lecture) {
        String sql = "UPDATE person SET Firstname = ?, Lastname = ?, HireDate = ? WHERE PersonID = ?";

        try (Connection conn = MyDatabaseManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lecture.getFirstname());
            pstmt.setString(2, lecture.getLastname());
            pstmt.setDate(3, new java.sql.Date(lecture.getHireDate().getTime()));
            pstmt.setInt(4, lecture.getPersonID());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            return false;
        }
    }
    
    public static boolean deleteLecture(int lectureID) {
        String sql = "DELETE FROM person WHERE PersonID = ?";
        
        try (Connection conn = MyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, lectureID);
            int rowsDeleted = pstmt.executeUpdate();
            
            return rowsDeleted > 0;
            
        } catch (SQLException e) {
            return false;
        }
    }
     
    

}
