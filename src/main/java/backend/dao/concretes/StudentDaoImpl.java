package backend.dao.concretes;

import backend.dao.interfaces.StudentDao;
import backend.database.DatabaseConnection;
import backend.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.StandardLocation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao<Student> {
    private static Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);
    private DatabaseConnection databaseConnection;
    private Connection connection;

    /**
     * Inside the constructor will initialize the database connection.
     */
    public StudentDaoImpl() {
        try {
            databaseConnection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            String message = "SQL connection problem. check host url, username, password";
            logger.error(message, e);
        }
    }

    /**
     * to return all courses
     *
     * @return list of courses
     */
    public String[][] getAllStudents() {
        connection = databaseConnection.getConnection();
        String query = "SELECT * FROM student";
        PreparedStatement preparedStatement;
        List<String[]> student = new ArrayList<String[]>();
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String[] tuple = new String[7];
                tuple[0] = String.valueOf(resultSet.getInt(1));
                tuple[1] = resultSet.getString(2);
                tuple[2] = resultSet.getString(3);
                tuple[3] = resultSet.getString(4);
                tuple[4] = resultSet.getString(5);
                tuple[5] = resultSet.getString(6);
                tuple[6] = resultSet.getString(7);
                student.add(tuple);
            }
            connection.close();
            String message = "Connection closed";
            logger.info(message);
        } catch (SQLException e) {
            String message = "Check connection";
            logger.error(message, e);
        }
        String[][] row = new String[student.size()][2];
        for (int i = 0; i < student.size(); i++) {
            row[i] = student.get(i);
        }
        return row;
    }

    public void addStudent(Student student) {
        connection = databaseConnection.getConnection();
        String insertQuery = "INSERT INTO student(firstName,lastName,city,street,email,mobile) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getCity());
            preparedStatement.setString(4, student.getStreet());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setString(6, student.getMobile());
            preparedStatement.executeUpdate();
            String message1 = "The new student successfully added";
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
     * to take one student
     *
     * @param studentId studentid
     * @return student
     */
    public Student getOneStudent(int studentId) {
        connection = databaseConnection.getConnection();
        String retrieveQuery = "SELECT * FROM student WHERE studentID = ?";
        Student student = null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String studentId1 = String.valueOf(resultSet.getInt(1));
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String city = resultSet.getString(4);
                String street = resultSet.getString(5);
                String email = resultSet.getString(6);
                String mobile = resultSet.getString(7);
                student = new Student(firstName, lastName, city, street, email, mobile);
                student.setStudentId(studentId1);
            }
            String message1 = "Course retrieved successfully";
            logger.info(message1);
        } catch (SQLException e) {
            String message = "Check the course id ";
            logger.error(message, e);
        }
        return student;
    }

    public void updateStudent(Student student) {
        connection = databaseConnection.getConnection();
        String connectionMessage = "Connection established for update courses";
        logger.info(connectionMessage);
        String updateQuery = "UPDATE student SET firstName=?,lastName=?,city=?,street=?,email=?,mobile =? WHERE studentID =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getCity());
            preparedStatement.setString(4, student.getStreet());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setString(6, student.getMobile());
            preparedStatement.setInt(7, Integer.parseInt(student.getStudentId()));
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
     * delete
     * @param studentId studenti id
     */
    public void deleteStudent(int studentId) {
        connection = databaseConnection.getConnection();
        String deleteQuery = "DELETE FROM student WHERE studentID = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, studentId);
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
