package unsw.enrolment;

public class Grade implements GradeInterface{
    private String name;
    private int mark;
    private String grade;

    public Grade(String name, int mark) {
        this.name = name;
        this.mark = mark;
        if (mark < 50)
            grade = "FL";
        else if (mark < 65)
            grade = "PS";
        else if (mark < 75)
            grade = "DN";
        else
            grade = "HD";
    }

    public String getName() {
        return name;
    }

    public boolean isPassing() {
        return mark >= 50;
    }

    public int getMark() {
        return mark;
    }
}
