package DAL;

import DAL.DBConnect.ConnectXamppMySQL;

import java.sql.*;

public class StudentGradeDAL {
    private Connection connection;

    public StudentGradeDAL() {
        try {
            connection = ConnectXamppMySQL.getConnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getGradeDetailsByCourseID(int courseID) throws SQLException {
        String sql = "SELECT  stg.StudentID, p.FirstName, p.LastName, stg.Grade " +
                "FROM studentgrade AS stg " +
                "JOIN person AS p ON stg.StudentID = p.PersonID " +
                "WHERE stg.CourseID = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, courseID);
        return pst.executeQuery();
    }

    public int getCourseID(String courseTitle) throws SQLException {
        String sql = "SELECT CourseID FROM `course` WHERE Title = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, courseTitle);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt("CourseID");
        }
        return -1;
    }

    public ResultSet getCourses() throws SQLException {
        String sql = "SELECT Title FROM course";
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }

    public void updateGrade(String studentID, String grade) throws SQLException {
        String sql = "UPDATE StudentGrade SET Grade = ? WHERE StudentID = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, grade);
        pst.setString(2, studentID);
        pst.executeUpdate();
    }
}
