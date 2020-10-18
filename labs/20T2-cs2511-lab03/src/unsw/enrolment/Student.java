package unsw.enrolment;
import java.util.ArrayList;

public class Student {

    private String zid;
    private ArrayList<Enrolment> enrolments;

	public Student(String zid) {
        this.zid = zid;
        enrolments = new ArrayList<Enrolment>();
    }

	public String getZID() {
		return zid;
    }
    
    public ArrayList<Enrolment> getEnrolments() {
        return enrolments;
    }

    public boolean enrol(CourseOffering offering) {
        if (offering.getCourse().checkPrereqs(this)) {
            Enrolment newEnrol = new Enrolment(offering, this);
            enrolments.add(newEnrol);
            return true;
        }
        return false;
    }

    public void grade(CourseOffering offering, Grade grade) {
        for (Enrolment e: enrolments) {
            if (e.getCourse() == offering.getCourse()) {
                e.setGrade(grade);
            }
        }
    }

}
