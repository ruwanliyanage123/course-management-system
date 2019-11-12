package backend.models;

/**
 * course model
 */
public class Course {
    private int courseId;
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
