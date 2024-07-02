package task2;

/**
 * Coded By: Era Boy
 * Version: v0.1.0
 **/

public class Module {
    private int marks;
    private String grade;

    public Module() {
    }

    public Module(int marks) {
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }

    private String calculateGrade(int marks) {
        if (marks >= 80) {
            return "Distinction";
        } else if (marks >= 70) {
            return "Merit";
        } else if (marks >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    public int getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }


}
