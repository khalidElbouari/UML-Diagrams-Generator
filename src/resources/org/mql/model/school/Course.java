package resources.org.mql.model.school;

import java.util.List;

public class Course {
    private String courseName;
    private List<Student> students;

    public Course() {}
    public Course(String courseName, List<Student> students) {
        this.courseName = courseName;
        this.students = students;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course [courseName=" + courseName + ", students=" + students + "]";
    }
}
