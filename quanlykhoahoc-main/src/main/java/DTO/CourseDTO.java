package DTO;
public class CourseDTO {
    
    public int CourseID;
    public String Title;
    public int Credits;
    public int DepartmentID;

    public CourseDTO() {
    }

    public CourseDTO(int CourseID, String title, int Credits, int DepartmentID) {
        this.CourseID = CourseID;
        this.Title = title;
        this.Credits = Credits;
        this.DepartmentID = DepartmentID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public int getCredits() {
        return Credits;
    }

    public void setCredits(int Credits) {
        this.Credits = Credits;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    
    
    
    
}
