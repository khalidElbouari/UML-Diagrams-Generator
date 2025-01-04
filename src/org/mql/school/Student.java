package org.mql.school;


public class Student extends Person {
    private String studentId;
    private Course course;
    public Student() {}

    public Student(String name, int age, String studentId) {
        super(name, age);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", " + super.toString() + "]";
    }
}
