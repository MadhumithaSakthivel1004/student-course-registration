package StudentCourseRegistration;

import java.util.concurrent.atomic.AtomicInteger;

public class Course {
    private String courseCode;
    private String title;
    private int capacity;
    private AtomicInteger enrolledStudents;

    public Course(String courseCode, String title, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.capacity = capacity;
        this.enrolledStudents = new AtomicInteger(0);
    }

    public String getCourseCode() { return courseCode; }
    public String getTitle() { return title; }
    public int getCapacity() { return capacity; }
    public int getEnrolledStudents() { return enrolledStudents.get(); }

    public synchronized void incrementEnrollment() throws CourseFullException {
        if (enrolledStudents.get() >= capacity) {
            throw new CourseFullException("Course " + courseCode + " is full.");
        }
        enrolledStudents.incrementAndGet();
    }

    public synchronized void decrementEnrollment() {
        if (enrolledStudents.get() > 0) {
            enrolledStudents.decrementAndGet();
        }
    }

    @Override
    public String toString() {
        return courseCode + " - " + title + " (Capacity: " + capacity +
               ", Enrolled: " + enrolledStudents.get() + ")";
    }
}