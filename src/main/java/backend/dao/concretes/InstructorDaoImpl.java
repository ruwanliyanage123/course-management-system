package backend.dao.concretes;

import backend.dao.interfaces.InstructorDao;
import backend.database.DatabaseConnection;
import backend.models.Instructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public String[][] getAllInstructors() {
        connection = databaseConnection.getConnection();
        String query = "SELECT * FROM instructor";
        PreparedStatement preparedStatement;
        List<String[]> instructor = new ArrayList<String[]>();
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String[] tuple = new String[9];
                tuple[0] = resultSet.getString(1);
                tuple[1] = resultSet.getString(2);
                tuple[2] = resultSet.getString(3);
                tuple[3] = resultSet.getString(4);
                tuple[4] = resultSet.getString(5);
                tuple[5] = resultSet.getString(6);
                tuple[6] = resultSet.getString(7);
                tuple[5] = resultSet.getString(8);
                tuple[6] = String.valueOf(resultSet.getInt(9));
                instructor.add(tuple);
            }
            connection.close();
            String message = "Connection closed";
            logger.info(message);
        } catch (SQLException e) {
            String message = "Check connection";
            logger.error(message, e);
        }
        String[][] row = new String[instructor.size()][9];
        for (int i = 0; i < instructor.size(); i++) {
            row[i] = instructor.get(i);
        }
        return row;
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
            String message1 = "The new instructor successfully added";
            logger.info(message1);
            connection.close();
            String message2 = "Database connection closed";
            logger.info(message2);
        } catch (SQLException e) {
            String message = "SQL exception check hostUrl, username, password";
            logger.error(message, e);
        }
    }

    public Instructor getOneInstructor(String nic) {
        connection = databaseConnection.getConnection();
        String retrieveQuery = "SELECT * FROM instructor WHERE nic = ?";
        Instructor instructor = null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, nic);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nic1 = resultSet.getString(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String mobile = resultSet.getString(4);
                String email = resultSet.getString(5);
                double salary = Double.parseDouble(resultSet.getString(6));
                String city = resultSet.getString(7);
                String street = resultSet.getString(8);
                int workingHours = resultSet.getInt(9);
                instructor = new Instructor(nic1,firstName, lastName, city, street, email, mobile, salary);
                instructor.setWorkingHours(workingHours);
            }
            String message1 = "Course retrieved successfully";
            logger.info(message1);
        } catch (SQLException e) {
            String message = "Check the course id ";
            logger.error(message, e);
        }
        return instructor;
    }

    /**
     * to update an instructor
     * @param instructor instructor
     */
    public void updateInstructor(Instructor instructor) {
        connection = databaseConnection.getConnection();
        String connectionMessage = "Connection established for update courses";
        logger.info(connectionMessage);
        String updateQuery = "UPDATE instructor SET firstName=?, lastName=?,mobile=?,email=?,salary=?,city=?,street=?, lecturerHours=? WHERE nic=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, instructor.getFirstName());
            preparedStatement.setString(2, instructor.getLastName());
            preparedStatement.setString(3, instructor.getMobile());
            preparedStatement.setString(4, instructor.getEmail());
            preparedStatement.setDouble(5, instructor.getSalary());
            preparedStatement.setString(6, instructor.getCity());
            preparedStatement.setString(7, instructor.getStreet());
            preparedStatement.setInt(8, instructor.getWorkingHours());
            preparedStatement.setString(9, instructor.getNic());
            preparedStatement.executeUpdate();
            String message1 = "Instructor updated successfully";
            logger.info(message1);
            connection.close();
            String message2 = "Database connection closed";
            logger.info(message2);
        } catch (SQLException e) {
            String message = "Check course id and enter valid inputs";
            logger.error(message, e);
        }
    }

    /**
     * delete
     * @param nic nic
     */
    public void deleteInstructor(String nic) {
        connection = databaseConnection.getConnection();
        String deleteQuery = "DELETE FROM instructor WHERE nic=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, nic);
            preparedStatement.executeUpdate();
            String message1 = "Successfully deleted";
            logger.info(message1);
            connection.close();
            String message2 = "Connection closed";
            logger.info(message2);
        } catch (SQLException e) {
            String message = "Make sure that courseID is already exists. Check courseID and try again";
            logger.error(message, e);
        }
    }
}
