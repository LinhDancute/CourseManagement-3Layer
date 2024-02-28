package DTO;

import java.sql.Time;
import java.util.Date;

public class CourseOnsiteDTO extends CourseDTO {
    public int CourseID;
    public String Location;
    public Date Days;
    public Time Time;

    public CourseOnsiteDTO() {
        super();
    }
    public CourseOnsiteDTO(int CourseID, String title, int Credits, int DepartmentID,String Location, Date Days, Time Time) {
        this.CourseID = CourseID;
        this.Title = title;
        this.Credits = Credits;
        this.DepartmentID = DepartmentID;
        this.Location = Location;
        this.Days = Days;
        this.Time = Time;
    }
    public CourseOnsiteDTO(int CourseID, String Location, Date Days, Time Time) {
        super();
        this.CourseID = CourseID;
        this.Location = Location;
        this.Days = Days;
        this.Time = Time;
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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public Date getDays() {
        return Days;
    }

    public void setDays(Date Days) {
        this.Days = Days;
    }

    public Time getTime() {
        return Time;
    }

    public void setTime(Time Time) {
        this.Time = Time;
    }

    @Override
    public String toString() {
        return "CourseOnsiteDTO{" + "CourseID=" + CourseID + ", Location=" + Location + ", Days=" + Days + ", Time=" + Time + '}';
    }
    
    
}
