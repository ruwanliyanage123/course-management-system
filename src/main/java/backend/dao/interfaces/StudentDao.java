package backend.dao.interfaces;

import java.util.List;

public interface StudentDao<Student> {

    public abstract List<Student> getAllStudents();

    public abstract void addStudent(Student student);

    public abstract Student getOneStudent();

    public abstract void updateStudent(Student student);

    public abstract void deleteStudent(int studentId);
}
