package backend.dao.concretes;

import backend.dao.interfaces.StudentDao;
import backend.database.DatabaseConnection;
import backend.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public List<Student> getAllStudents() {
        return null;
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

    public Student getOneStudent() {
        return null;
    }

    public void updateStudent(Student student) {

    }

    public void deleteStudent(int studentId) {

    }
}
