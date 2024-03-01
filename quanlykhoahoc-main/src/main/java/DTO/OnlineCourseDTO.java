package DTO;
public class OnlineCourseDTO extends CourseDTO {
    public int CourseID;
    public String url;

    public OnlineCourseDTO() {
        super();
    }

    public OnlineCourseDTO(String url) {
        super();
        this.url = url;
    }
    
     public OnlineCourseDTO(int CourseID, String title, int Credits, String DepartmentID,String url) {
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

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String DepartmentID) {
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
