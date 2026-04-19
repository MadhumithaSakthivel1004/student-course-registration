package StudentCourseRegistration;

public class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}