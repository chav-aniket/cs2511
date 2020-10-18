package unsw.enrolment;

public class Grade {
    private int mark;
    private String grade;

    public Grade(int mark) {
        this.mark = mark;
        if (mark >= 85) {
            grade = "HD";
        } else if (mark >= 75) {
            grade = "DS";
        } else if (mark >= 65) {
            grade = "CR";
        } else if (mark >= 50) {
            grade = "PS";
        } else if (mark >= 0) {
            grade = "FL";
        } else {
            grade = "UNMARKED";
        }
    }

    public int getMark() {
        return mark;
    }
    
    public String getGrade() {
        return grade;
    }
}
