package org.mql.school;

import java.util.ArrayList;
import java.util.List;


public class School {
    private String schoolName;
    private List<Course> courses;

    public School() {
        courses = new ArrayList<>();
    }

    public School(String schoolName) {
        this();
        this.schoolName = schoolName;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "School [schoolName=" + schoolName + ", courses=" + courses + "]";
    }
}
