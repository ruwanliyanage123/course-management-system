package backend.dao.interfaces;

import java.util.List;

public interface InstructorDao <Instructor> {
    public abstract String[][] getAllInstructors();

    public abstract void addInstructor(Instructor instructor);

    public abstract Instructor getOneInstructor();

    public abstract void updateInstructor(Instructor instructor);

    public abstract void deleteInstructor(String nic);
}
