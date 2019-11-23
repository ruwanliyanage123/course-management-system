package backend.dao.concretes;

import backend.dao.interfaces.LectureDao;
import backend.database.DatabaseConnection;
import backend.models.Lecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LectureDaoImpl implements LectureDao<Lecture> {
    private static Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);
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


    public List<Lecture> getAllLectures() {
        return null;
    }

    public void addLecture(Lecture lecture) {
        connection = databaseConnection.getConnection();
        String insertQuery = "INSERT INTO student(nic, firstName, lastName,mobile,email,salary,city,street, lecturerHours) VALUES (?,?,?,?,?,?,?,?,?)";
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

    public Lecture getOneLecture() {
        return null;
    }

    public void updateLecture(Lecture lecture) {

    }

    public void deleteLecture(String nic) {

    }
}
