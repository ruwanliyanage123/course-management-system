package backend.dao.interfaces;

import java.util.List;

public interface SubjectDao<Subject> {
    public abstract List<Subject> getAllSubjects();

    public abstract void addSubject(Subject subject);

    public abstract Subject getOneSubject();

    public abstract void updateSubject(Subject subject);

    public abstract void deleteSubject(String subjectId);
}
