package unsw.enrolment;
import java.util.ArrayList;
import java.util.List;

/**
 * A course in the enrolment system.
 * @author Robert Clifton-Everest
 *
 */
public class Course {

    private String courseCode;
    private String title;
    private int uoc;
    private List<Course> prereqs;
    private List<CourseOffering> courseOfferings;


    public Course(String courseCode, String title) {
        this.courseCode = courseCode;
        this.title = title;
        this.prereqs = new ArrayList<Course>();
        this.courseOfferings = new ArrayList<CourseOffering>();
    }

    public boolean checkPrereqs(Student student) {
        if (prereqs.size() == 0) return true;

        ArrayList<Enrolment> enrols = student.getEnrolments();
        for (Enrolment enrol: enrols) {
            for (Course prereq: prereqs) {
                if (enrol.getCourse() == prereq && enrol.getGrade().getMark() >= 50) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addPrereq(Course course) {
        prereqs.add(course);
    }

    public void addOffering(CourseOffering offering) {
        courseOfferings.add(offering);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return title;
    }

    public int getUOC() {
        return uoc;
    }

}
