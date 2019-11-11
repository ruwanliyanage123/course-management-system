package backend.models;

public class Instructor extends Staff {

    private int practicalHours;

    public Instructor(String nic, String firstName, String lastName, String city, String street,
            String email, String mobile, double salary) {
        super(nic, firstName, lastName, city, street, email, mobile, salary);
    }

    @Override
    public int getWorkingHours() {
        return practicalHours;
    }

    @Override
    public void setWorkingHours(int practicalHours) {
        this.practicalHours = practicalHours;
    }
}
