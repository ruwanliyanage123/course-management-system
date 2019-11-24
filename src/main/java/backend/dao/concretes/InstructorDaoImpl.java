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

    public Instructor getOneInstructor() {
        return null;
    }

    public void updateInstructor(Instructor instructor) {

    }

    public void deleteInstructor(String nic) {

    }
}
