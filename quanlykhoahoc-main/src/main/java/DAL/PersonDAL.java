package DAL;

import BLL.DTO.PersonDTO;
import DAL.DBConnect.ConnectXamppMySQL;
import DAL.DBConnect.MyConnectUnit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAL extends MyConnectUnit {

    public List<PersonDTO> getListPerson(boolean isStudent) throws Exception {
        ArrayList<PersonDTO> listPerson = new ArrayList<>();
        
         try {
            String condition;
            if (isStudent) {
                // Lấy danh sách sinh viên (HireDate = Null)
                condition = "HireDate IS NULL";
            } else {
                // Lấy danh sách giảng viên (EnrollmentDate = Null)
                condition = "EnrollmentDate IS NULL";
            }
            ResultSet resultSet = this.Select("person", condition);
            while (resultSet.next()) {
                PersonDTO person = new PersonDTO();
                person.setPersonID(resultSet.getInt("PersonID"));
                person.setFirstname(resultSet.getString("Firstname"));
                person.setLastname(resultSet.getString("Lastname"));
                person.setHireDate(resultSet.getDate("HireDate"));
                person.setEnrollmentDate(resultSet.getDate("EnrollmentDate"));
                listPerson.add(person);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.Close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listPerson;
    }

    public static boolean addStudent(PersonDTO student) throws Exception {
    String sql = "INSERT INTO person (Firstname, Lastname, HireDate, EnrollmentDate) VALUES (?, ?, ?, ?)";

    try (ConnectXamppMySQL connect = new ConnectXamppMySQL(); 
         Connection conn = connect.getConnect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, student.getFirstname());
        pstmt.setString(2, student.getLastname());

        // Assuming HireDate is a mandatory field
        if (student.getHireDate() != null) {
            pstmt.setDate(3, new java.sql.Date(student.getHireDate().getTime()));
        } else {
            // Provide a default value or handle as needed
            // Example: pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            pstmt.setNull(3, java.sql.Types.DATE);
        }

        pstmt.setDate(4, new java.sql.Date(student.getEnrollmentDate().getTime()));

        int rowsInserted = pstmt.executeUpdate();
        return rowsInserted > 0;
    }
}


    public static boolean updateStudent(PersonDTO student) throws Exception {
        String sql = "UPDATE person SET Firstname = ?, Lastname = ?, EnrollmentDate = ? WHERE PersonID = ?";

        try (ConnectXamppMySQL connect = new ConnectXamppMySQL(); 
         Connection conn = connect.getConnect();
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

    public static boolean deleteStudent(int studentID) throws Exception {
        String sql = "DELETE FROM person WHERE PersonID = ?";

        try (ConnectXamppMySQL connect = new ConnectXamppMySQL(); 
         Connection conn = connect.getConnect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentID);
            int rowsDeleted = pstmt.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean addLecture(PersonDTO lecture) throws Exception {
        String sql = "INSERT INTO person (Firstname, Lastname, HireDate, EnrollmentDate) VALUES (?, ?,? ,NULL)";

        try (ConnectXamppMySQL connect = new ConnectXamppMySQL(); 
         Connection conn = connect.getConnect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lecture.getFirstname());
            pstmt.setString(2, lecture.getLastname());
            pstmt.setDate(3, new java.sql.Date(lecture.getHireDate().getTime()));

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateLecture(PersonDTO lecture) throws Exception {
        String sql = "UPDATE person SET Firstname = ?, Lastname = ?, HireDate = ? WHERE PersonID = ?";

        try (ConnectXamppMySQL connect = new ConnectXamppMySQL(); 
         Connection conn = connect.getConnect();
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

    public static boolean deleteLecture(int lectureID) throws Exception {
        String sql = "DELETE FROM person WHERE PersonID = ?";

        try (ConnectXamppMySQL connect = new ConnectXamppMySQL(); 
         Connection conn = connect.getConnect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, lectureID);
            int rowsDeleted = pstmt.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            return false;
        }
    }
}
