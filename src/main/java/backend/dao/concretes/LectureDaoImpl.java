package backend.dao.concretes;

import backend.dao.interfaces.LectureDao;
import backend.database.DatabaseConnection;
import backend.models.Lecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LectureDaoImpl implements LectureDao<Lecture> {
    private static Logger logger = LoggerFactory.getLogger(LectureDaoImpl.class);
    private DatabaseConnection databaseConnection;
    private Connection connection;

    /**
     * Inside the constructor will initialize the database connection.
     */
    public LectureDaoImpl() {
        try {
            databaseConnection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            String message = "SQL connection problem. check host url, username, password";
            logger.error(message, e);
        }
    }


    public String[][] getAllLectures() {
        connection = databaseConnection.getConnection();
        String query = "SELECT * FROM lecturer";
        PreparedStatement preparedStatement;
        List<String[]> lecturer = new ArrayList<String[]>();
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
                lecturer.add(tuple);
            }
            connection.close();
            String message = "Connection closed";
            logger.info(message);
        } catch (SQLException e) {
            String message = "Check connection";
            logger.error(message, e);
        }
        String[][] row = new String[lecturer.size()][9];
        for (int i = 0; i < lecturer.size(); i++) {
            row[i] = lecturer.get(i);
        }
        return row;
    }

    public void addLecture(Lecture lecture) {
        connection = databaseConnection.getConnection();
        String insertQuery = "INSERT INTO lecturer(nic, firstName, lastName,mobile,email,salary,city,street, lecturerHours) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, lecture.getNic());
            preparedStatement.setString(2, lecture.getFirstName());
            preparedStatement.setString(3, lecture.getLastName());
            preparedStatement.setString(4, lecture.getMobile());
            preparedStatement.setString(5, lecture.getEmail());
            preparedStatement.setString(6, String.valueOf(lecture.getSalary()));
            preparedStatement.setString(7, lecture.getCity());
            preparedStatement.setString(8, lecture.getStreet());
            preparedStatement.setInt(9, lecture.getWorkingHours());
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

    public Lecture getOneLecture(String nic) {
        connection = databaseConnection.getConnection();
        String retrieveQuery = "SELECT * FROM lecturer WHERE nic = ?";
        Lecture lecture = null;
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
                lecture = new Lecture(nic1, firstName, lastName, city, street, email, mobile, salary, workingHours);
            }
            String message1 = "Course retrieved successfully";
            logger.info(message1);
        } catch (SQLException e) {
            String message = "Check the course id ";
            logger.error(message, e);
        }
        return lecture;
    }

    public void updateLecture(Lecture lecture) {
        connection = databaseConnection.getConnection();
        String connectionMessage = "Connection established for update courses";
        logger.info(connectionMessage);
        String updateQuery = "UPDATE lecturer SET firstName=?, lastName=?,mobile=?,email=?,salary=?,city=?,street=?, lecturerHours=? WHERE nic=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, lecture.getFirstName());
            preparedStatement.setString(2, lecture.getLastName());
            preparedStatement.setString(3, lecture.getMobile());
            preparedStatement.setString(4, lecture.getEmail());
            preparedStatement.setDouble(5, lecture.getSalary());
            preparedStatement.setString(6, lecture.getCity());
            preparedStatement.setString(7, lecture.getStreet());
            preparedStatement.setInt(8, lecture.getWorkingHours());
            preparedStatement.setString(9, lecture.getNic());
            preparedStatement.executeUpdate();
            String message1 = "Course updated successfully";
            logger.info(message1);
            connection.close();
            String message2 = "Database connection closed";
            logger.info(message2);
        } catch (SQLException e) {
            String message = "Check course id and enter valid inputs";
            logger.error(message, e);
        }
    }

    public void deleteLecture(String nic) {
        connection = databaseConnection.getConnection();
        String deleteQuery = "DELETE FROM lecturer WHERE nic=?";
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

