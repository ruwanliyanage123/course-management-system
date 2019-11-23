package backend.dao.interfaces;

import java.util.List;

public interface SubjectDao<Subject> {
    public abstract String[][] getAllSubjects();

    public abstract void addSubject(Subject subject);

    public abstract Subject getOneSubject(String subjectId);

    public abstract void updateSubject(Subject subject);

    public abstract void deleteSubject(int subjectId);
}
