package backend.dao.interfaces;

import java.util.List;

public interface CourseDao<Course> {

    public abstract List<Course> getAllCourses();

    public abstract void addCourse(Course course);

    public abstract Course getOneCourse(int courseID);

    public abstract void updateCourse(Course course, int courseId);

    public abstract void deleteCourse(int courseId);
}
