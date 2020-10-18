package unsw.enrolment;

import java.util.ArrayList;

public class CalculatedGrade implements GradeInterface {
    private String name;
    private ArrayList<Grade> grades;
    private String grade;

    public CalculatedGrade(String name, Grade mark) {
        this.name = name;
        this.grades = new ArrayList<Grade>();
        addMark(mark);
        // if (mark < 50)
        //     grade = "FL";
        // else if (mark < 65)
        //     grade = "PS";
        // else if (mark < 75)
        //     grade = "DN";
        // else
        //     grade = "HD";
    }

    public String getName() {
        return name;
    }

    public boolean isPassing() {
        return getMark() >= 50;
    }

    public void addMark(Grade mark) {
        grades.add(mark);
    }

    public int getMark() {
        int total = 0, count = 0;
        for (Grade g: this.grades) {
            total += g.getMark();
            count++;
        }
        return total/count;
    }
}