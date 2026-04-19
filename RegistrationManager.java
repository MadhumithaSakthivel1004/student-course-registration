package StudentCourseRegistration;

import java.util.*;

public class RegistrationManager {
    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();

    public void addStudent(Student s) { students.put(s.getStudentId(), s); }
    public void addCourse(Course c) { courses.put(c.getCourseCode(), c); }

    public synchronized void enrollStudent(String studentId, String courseCode)
            throws InvalidStudentException, CourseNotFoundException, CourseFullException {
        Student s = students.get(studentId);
        if (s == null) throw new InvalidStudentException("Student not found: " + studentId);

        Course c = courses.get(courseCode);
        if (c == null) throw new CourseNotFoundException("Course not found: " + courseCode);

        c.incrementEnrollment();
        s.addCourse(c);
        System.out.println("Enrolled " + s.getName() + " in " + c.getTitle());
    }

    public synchronized void dropCourse(String studentId, String courseCode)
            throws InvalidStudentException, CourseNotFoundException {
        Student s = students.get(studentId);
        if (s == null) throw new InvalidStudentException("Student not found: " + studentId);

        Course c = courses.get(courseCode);
        if (c == null) throw new CourseNotFoundException("Course not found: " + courseCode);

        if (s.getEnrolledCourses().contains(c)) {
            s.removeCourse(c);
            c.decrementEnrollment();
            System.out.println("Dropped " + s.getName() + " from " + c.getTitle());
        } else {
            System.out.println("Student not enrolled in this course.");
        }
    }

    public void viewDetails() {
        System.out.println("\n--- Students ---");
        students.values().forEach(System.out::println);

        System.out.println("\n--- Courses ---");
        courses.values().forEach(System.out::println);
    }

    public void simulateMultithreadedRegistration() {
        Course c = new Course("C101", "Concurrent Programming", 2);
        addCourse(c);

        Student s1 = new Student("S1", "Alice");
        Student s2 = new Student("S2", "Bob");
        Student s3 = new Student("S3", "Charlie");

        addStudent(s1);
        addStudent(s2);
        addStudent(s3);

        Runnable task1 = () -> { try { enrollStudent("S1", "C101"); } catch (Exception e) { System.out.println(e.getMessage()); } };
        Runnable task2 = () -> { try { enrollStudent("S2", "C101"); } catch (Exception e) { System.out.println(e.getMessage()); } };
        Runnable task3 = () -> { try { enrollStudent("S3", "C101"); } catch (Exception e) { System.out.println(e.getMessage()); } };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);

        t1.start(); t2.start(); t3.start();

        try { t1.join(); t2.join(); t3.join(); } catch (InterruptedException e) { e.printStackTrace(); }

        viewDetails();
    }
}