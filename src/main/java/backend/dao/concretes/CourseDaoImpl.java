package backend.dao.concretes;

import backend.dao.interfaces.CourseDao;
import backend.database.DatabaseConnection;
import backend.models.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    /**
     * to return all courses
     * @return list of courses
     */
    public String[][] getAllCourses() {
        connection = databaseConnection.getConnection();
        String query = "SELECT * FROM course";
        PreparedStatement preparedStatement;
        List<String[]> courses = new ArrayList<String[]>();
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String[] tuple = new String[2];
                tuple[0] = String.valueOf(resultSet.getInt(1));
                tuple[1] = resultSet.getString(2);
                courses.add(tuple);
            }
            connection.close();
            String message = "Connection closed";
            logger.info(message);
        } catch (SQLException e) {
            String message = "Check connection";
            logger.error(message, e);
        }
        String[][] row = new String[courses.size()][2];
        for (int i = 0; i < courses.size(); i++) {
            row[i] = courses.get(i);
        }
        return row;
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

    /**
     * will return the given object
     *
     * @param courseID need to refer course
     * @return the desired object
     */
    public Course getOneCourse(int courseID) {
        connection = databaseConnection.getConnection();
        String retrieveQuery = "SELECT * FROM course WHERE courseID = ?";
        Course course = null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setInt(1, courseID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                course = new Course(resultSet.getString(2));
            }
            String message1 = "Course retrieved successfully";
            logger.info(message1);
        } catch (SQLException e) {
            String message = "Check the course id ";
            logger.error(message, e);
        }
        return course;
    }

    /**
     * Will update course
     * @param course replacing object
     * @param courseId need to refer object
     */
    public void updateCourse(Course course, int courseId) {
        connection = databaseConnection.getConnection();
        String connectionMessage = "Connection established for update courses";
        logger.info(connectionMessage);
        String updateQuery = "UPDATE course SET courseName=? WHERE courseID =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1,course.getCourseName());
            preparedStatement.setInt(2,courseId);
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

    /**
     * Will delete the given course from databse
     *
     * @param courseId needed to refer course uniquely.
     */
    public void deleteCourse(int courseId) {
        connection = databaseConnection.getConnection();
        String deleteQuery = "DELETE FROM course WHERE courseID = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, courseId);
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
