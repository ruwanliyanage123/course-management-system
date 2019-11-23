package backend.dao.interfaces;

public interface StudentDao<Student> {

    public abstract String[][] getAllStudents();

    public abstract void addStudent(Student student);

    public abstract Student getOneStudent(int studentId1);

    public abstract void updateStudent(Student student);

    public abstract void deleteStudent(int studentId);
}
