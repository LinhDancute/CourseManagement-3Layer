package BLL.DTO;

public class CourseInstructorDTO {
    private int CourseID;
    private int PersonID;
//    private String TitleCourse;
//    private String LectureName;
    
    public CourseInstructorDTO() {
    }
    public CourseInstructorDTO(int CourseID, int PersonID) {
        this.CourseID = CourseID;
        this.PersonID = PersonID;
//        this.LectureName = LectureName;
//        this.TitleCourse = TitleCourse;
    }
    public int getCourseID() {
        return CourseID;
    }   
    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }
    public int getPersonID() {
        return PersonID;
    }
    public void setPersonID(int PersonID) {
        this.PersonID = PersonID;
    }
//    public String getTitleCourse() {
//        return TitleCourse;
//    }
//    public void setTitleCourse(String TitleCourse) {
//        this.TitleCourse = TitleCourse;
//    }
//    public String getLectureName() {
//        return LectureName;
//    }
//    public void setLectureName(String LectureName) {
//        this.LectureName = LectureName;
//    }
    
    @Override
    public String toString() {
        return "CourseInstructorDTO{" + "CourseID=" + CourseID + ", PersonID=" + PersonID + '}';
    } 
}
