package DTO;
public class CourseOnlineDTO extends CourseDTO {
    public int CourseID;
    public String url;

    public CourseOnlineDTO() {
        super();
    }

    public CourseOnlineDTO(String url) {
        super();
        this.url = url;
    }
    
     public CourseOnlineDTO(int CourseID, String title, int Credits, int DepartmentID,String url) {
        this.CourseID = CourseID;
        this.Title = title;
        this.Credits = Credits;
        this.DepartmentID = DepartmentID;
        this.url = url;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
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
    
    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CourseOnlineDTO{" + "CourseID=" + CourseID + ", url=" + url + '}';
    }
    
    
}
