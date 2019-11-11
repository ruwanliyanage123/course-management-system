package backend.dao.interfaces;

import java.util.List;

public interface SubjectDao<Subject> {
    public abstract List<Subject> getAllCourses();

    public abstract void addCourse(Subject student);

    public abstract Subject getOneCourse();

    public abstract void updateCourse(Subject course);

    public abstract void deleteCourse(int subjectId);
}
