package backend.dao.concretes;

import backend.dao.interfaces.CourseDao;
import backend.database.DatabaseConnection;
import backend.models.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CourseDaoImpl implements CourseDao<Course> {
    private static Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
    private DatabaseConnection databaseConnection;
    private Connection connection;

    /**
     * Inside the constructor will initialize the database connection.
     */
    public CourseDaoImpl() {
        try {
            databaseConnection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            String message = "SQL connection problem. check host url, username, password";
            logger.error(message, e);
        }
    }

    public List<Course> getAllCourses() {
        return null;
    }

    /**
     * For add a new course
     *
     * @param cour new course which needed to be add into database
     */
    public void addCourse(Course cour) {
        connection = databaseConnection.getConnection();
        String insertQuery = "INSERT INTO course(courseName) VALUES(?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, cour.getCourseName());
            preparedStatement.executeUpdate();
            String message1 = "The new course successfully added";
            logger.info(message1);
            connection.close();
            String message2 = "Database connection closed";
            logger.info(message2);
        } catch (SQLException e) {
            String message = "SQL exception check hostUrl, username, password";
            logger.error(message, e);
        }
    }

    public Course getOneCourse() {
        return null;
    }

    public void updateCourse(Course course) {

    }

    public void deleteCourse(int courseId) {

    }
}
