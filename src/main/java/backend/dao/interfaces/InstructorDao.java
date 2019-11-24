package backend.dao.interfaces;

public interface InstructorDao <Instructor> {
    public abstract String[][] getAllInstructors();

    public abstract void addInstructor(Instructor instructor);

    public abstract Instructor getOneInstructor(String nic1);

    public abstract void updateInstructor(Instructor instructor);

    public abstract void deleteInstructor(String nic);
}
