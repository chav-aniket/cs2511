package staff;

import staff.StaffMember;
import java.time.LocalDate;

public class Lecturer extends StaffMember{
    private String school;
    private char status;

    public Lecturer(String school, char status, String name, int salary, LocalDate hireDate, LocalDate endDate) {
        super(name, salary, hireDate, endDate);
        this.school = school;
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString().replace("]", ", school: "+this.school+", status: "+this.status+"]");
    }

    @Override
    public boolean equals(Object A) {
        if (!super.equals(A)) {
            return false;
        }
        Lecturer staff = (Lecturer) A;
        if (super.equals(A) && school.equals(staff.getSchool()) && status == staff.getStatus()) {
                return true;
        }
        return false;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getSchool() {
        return this.school;
    }

    public char getStatus() {
        return this.status;
    }
}
