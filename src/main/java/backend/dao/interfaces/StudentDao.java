package backend.dao.interfaces;

public interface StudentDao<Student> {

    /**
    *No need to add the public abstract because those are default keywords for the interface methods 
    */
    public abstract String[][] getAllStudents();

    public abstract void addStudent(Student student);

    public abstract Student getOneStudent(int studentId1);

    public abstract void updateStudent(Student student);

    public abstract void deleteStudent(int studentId);
}
