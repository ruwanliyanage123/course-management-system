package backend.models;

public class Lecture extends Staff {
    private int lectureHours;

    public Lecture(String nic, String firstName, String lastName, String city, String street,
            String email, String mobile, double salary, int lectureHours) {
        super(nic, firstName, lastName, city, street, email, mobile, salary);
        this.lectureHours = lectureHours;
    }

    @Override
    public int getWorkingHours() {
        return lectureHours;
    }

    @Override
    public void setWorkingHours(int lectureHours) {
        this.lectureHours = lectureHours;
    }
}
