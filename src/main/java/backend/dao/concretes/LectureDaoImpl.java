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
        String[][] row = new String[lecturer.size()][2];
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

    public Lecture getOneLecture(String nic1) {
        return null;
    }

    public void updateLecture(Lecture lecture) {

    }

    public void deleteLecture(String nic) {

    }
}
