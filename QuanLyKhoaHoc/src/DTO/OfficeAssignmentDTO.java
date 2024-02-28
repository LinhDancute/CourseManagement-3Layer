package DTO;

import java.sql.Timestamp;
import java.util.Date;

public class OfficeAssignmentDTO {
    public int InstructorID;
    public String Location;
    public Timestamp Timestamp;

    public OfficeAssignmentDTO() {
    }

    public OfficeAssignmentDTO(int InstructorID, String Location, Timestamp Timestamp) {
        this.InstructorID = InstructorID;
        this.Location = Location;
        this.Timestamp = Timestamp;
    }

    public int getInstructorID() {
        return InstructorID;
    }

    public void setInstructorID(int InstructorID) {
        this.InstructorID = InstructorID;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Timestamp Timestamp) {
        this.Timestamp = Timestamp;
    }

    @Override
    public String toString() {
        return "OfficeAssignmentDTO{" + "InstructorID=" + InstructorID + ", Location=" + Location + ", Timestamp=" + Timestamp + '}';
    }
}
