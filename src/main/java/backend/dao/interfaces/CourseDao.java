package backend.dao.interfaces;

import java.util.List;

/**
*Need to add the api documentation for this Dao
*/
public interface CourseDao<Course> {

    public abstract String[][] getAllCourses();

    public abstract void addCourse(Course course);

    public abstract Course getOneCourse(int courseID);

    public abstract void updateCourse(Course course, int courseId);

    public abstract void deleteCourse(int courseId);
}
