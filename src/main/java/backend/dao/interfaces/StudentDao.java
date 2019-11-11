package backend.dao.interfaces;

import java.util.List;

public interface StudentDao<Student> {

    public abstract List<Student> getAllCourses();

    public abstract void addCourse(Student student);

    public abstract Student getOneCourse();

    public abstract void updateCourse(Student course);

    public abstract void deleteCourse(int studentId);
}
