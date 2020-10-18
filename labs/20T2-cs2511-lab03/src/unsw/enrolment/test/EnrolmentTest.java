package unsw.enrolment.test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Lecture;
import unsw.enrolment.Student;
import unsw.enrolment.Tutorial;
import unsw.enrolment.Enrolment;
import unsw.enrolment.Grade;

public class EnrolmentTest {

    public static void main(String[] args) {

        // Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        // TODO Create some sessions for the courses
        Lecture mon1511A = new Lecture("Baxter Building", DayOfWeek.MONDAY, LocalTime.now(), 
            LocalTime.now(), "Ashesh");
        comp1511Offering.addSession(mon1511A);
        Tutorial tue1511B = new Tutorial("Baxter Building", DayOfWeek.TUESDAY, LocalTime.now(), 
            LocalTime.now(), "Adam");
        comp1511Offering.addSession(tue1511B);

        // TODO Create a student
        Student genn = new Student("z5555555");

        // TODO Enrol the student in COMP1511 for T1 (this should succeed)
        if (genn.enrol(comp1511Offering)) {
            System.out.println("Test Passed!");
        }

        // TODO Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        if (!genn.enrol(comp1531Offering)) {
            System.out.println("Test Passed!");
        }

        // TODO Give the student a passing grade for COMP1511
        Grade genn1511Grade = new Grade(64);
        genn.grade(comp1511Offering, genn1511Grade);

        // TODO Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        if (genn.enrol(comp2521Offering)) {
            System.out.println("Test Passed!");
        }
    }
}
