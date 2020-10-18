package staff;

import java.time.LocalDate;

/**
 * A staff member
 * @author Robert Clifton-Everest
 *
 */
public class StaffMember {
    private String name;
    private float salary;
    private LocalDate hireDate;
    private LocalDate endDate;
    
    public StaffMember(String name, int salary, LocalDate hireDate, LocalDate endDate) {
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[" + getClass() + ", name: " + name + ", salary: " +
            salary + ", hireDate: " + hireDate + ", endDate: " + endDate + "]";
    }

    @Override
    public boolean equals(Object A) {
        if (A == null || A.getClass() != getClass()) {
            return false;
        }
        StaffMember staff = (StaffMember) A;
        if (name.equals(staff.getName()) && salary == staff.getSalary() &&
            hireDate.equals(staff.getHireDate()) && endDate.equals(staff.getEndDate())) {
                return true;
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    public void setPeriod(LocalDate start, LocalDate end) {
        this.hireDate = start;
        this.endDate = end;
    }

    public String getName() {
        return this.name;
    }

    public float getSalary() {
        return this.salary;
    }

    public LocalDate getHireDate() {
        return this.hireDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }
}
