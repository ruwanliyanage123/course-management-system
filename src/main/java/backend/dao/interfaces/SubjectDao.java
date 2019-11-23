package backend.dao.interfaces;

import java.util.List;

public interface SubjectDao<Subject> {
    public abstract String[][] getAllSubjects();

    public abstract void addSubject(Subject subject, int courseID);

    public abstract Subject getOneSubject();

    public abstract void updateSubject(Subject subject);

    public abstract void deleteSubject(String subjectId);
}
