package StudentCourseRegistration;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String name;
    private List<Course> enrolledCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public List<Course> getEnrolledCourses() { return enrolledCourses; }

    public void addCourse(Course c) { enrolledCourses.add(c); }
    public void removeCourse(Course c) { enrolledCourses.remove(c); }

    @Override
    public String toString() {
        return studentId + " - " + name + " | Courses: " + enrolledCourses.size();
    }
}