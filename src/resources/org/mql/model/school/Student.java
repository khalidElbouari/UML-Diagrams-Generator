package resources.org.mql.model.school;


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

    
    public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
    public String toString() {
        return "Student [studentId=" + studentId + ", " + super.toString() + "]";
    }
}
