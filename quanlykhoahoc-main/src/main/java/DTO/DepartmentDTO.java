package DTO;

import java.util.Date;
public class DepartmentDTO {
    
    public String DepartmentID;
    public String Name;
    public Double Budget;
    public Date StartDate;
    public int Administrator;

    public DepartmentDTO() {
    }

    public DepartmentDTO(String DepartmentID, String Name, Double Budget, Date StartDate, int Administrator) {
        this.DepartmentID = DepartmentID;
        this.Name = Name;
        this.Budget = Budget;
        this.StartDate = StartDate;
        this.Administrator = Administrator;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Double getBudget() {
        return Budget;
    }

    public void setBudget(Double Budget) {
        this.Budget = Budget;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public int getAdministrator() {
        return Administrator;
    }

    public void setAdministrator(int Administrator) {
        this.Administrator = Administrator;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" + "DepartmentID=" + DepartmentID + ", Name=" + Name + ", Budget=" + Budget + ", StartDate=" + StartDate + ", Administrator=" + Administrator + '}';
    }
    
    
    
    
}
