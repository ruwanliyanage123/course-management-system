package backend.dao.concretes;

import backend.dao.interfaces.InstructorDao;
import backend.database.DatabaseConnection;
import backend.models.Instructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InstructorDaoImpl implements InstructorDao <Instructor> {
    private static Logger logger = LoggerFactory.getLogger(InstructorDaoImpl.class);
    private DatabaseConnection databaseConnection;
    private Connection connection;

    /**
     * Inside the constructor will initialize the database connection.
     */
    public InstructorDaoImpl() {
        try {
            databaseConnection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            String message = "SQL connection problem. check host url, username, password";
            logger.error(message, e);
        }
    }

    public List<Instructor> getAllInstructors() {
        return null;
    }

    public void addInstructor(Instructor instructor) {
        connection = databaseConnection.getConnection();
        String insertQuery = "INSERT INTO instructor(nic, firstName, lastName,mobile,email,salary,city,street, lecturerHours) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, instructor.getNic());
            preparedStatement.setString(2, instructor.getFirstName());
            preparedStatement.setString(3, instructor.getLastName());
            preparedStatement.setString(4, instructor.getMobile());
            preparedStatement.setString(5, instructor.getEmail());
            preparedStatement.setString(6, String.valueOf(instructor.getSalary()));
            preparedStatement.setString(7, instructor.getCity());
            preparedStatement.setString(8, instructor.getStreet());
            preparedStatement.setInt(9, instructor.getWorkingHours());
            preparedStatement.executeUpdate();
            String message1 = "The new lecturer successfully added";
            logger.info(message1);
            connection.close();
            String message2 = "Database connection closed";
            logger.info(message2);
        } catch (SQLException e) {
            String message = "SQL exception check hostUrl, username, password";
            logger.error(message, e);
        }
    }

    public Instructor getOneInstructor() {
        return null;
    }

    public void updateInstructor(Instructor instructor) {

    }

    public void deleteInstructor(String nic) {

    }
}
