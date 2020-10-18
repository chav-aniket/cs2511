package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;

public class Enrolment {

    private CourseOffering offering;
    private ArrayList<GradeInterface> grades;
    private GradeInterface prac;
    private GradeInterface exam;
    private GradeInterface finalMark;

    private Student student;
    private List<Session> sessions;

    private ArrayList<Log> subscribers;

    public Enrolment(CourseOffering offering, Student student, Session... sessions) {
        this.offering = offering;
        this.student = student;
        this.grades = new ArrayList<GradeInterface>(); // Student has not completed course yet.
        this.finalMark = null;
        student.addEnrolment(this);
        offering.addEnrolment(this);
        this.sessions = new ArrayList<>();
        for (Session session : sessions) {
            this.sessions.add(session);
        }
        this.subscribers = new ArrayList<Log>();
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    public Student getStudent() {
        return student;
    }

    public void addPrac(GradeInterface mark) {
        this.prac = mark;
    }

    public void addExam(GradeInterface mark) {
        this.exam = mark;
    }

    public int getFinal() {
        if (finalMark == null) {
            finalMark = new Grade("Final Mark", prac.getMark() + exam.getMark());
        }
        return finalMark.getMark();
    }

    public boolean hasPassed() {
        if (finalMark != null) {
            return finalMark.isPassing();
        }
        return false;
    }

//    Whole course marks can no longer be assigned this way.
    public void assignMark(GradeInterface mark) {
        grades.add(mark);
        publishInfo(mark);
    }

    public void subscribe(Log sub) {
        subscribers.add(sub);
    }

    public void publishInfo(GradeInterface mark) {
        for (Log l: subscribers) {
            l.update(mark);
        }
    }

}
