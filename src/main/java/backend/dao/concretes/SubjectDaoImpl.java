package backend.dao.concretes;

import backend.dao.interfaces.SubjectDao;
import backend.database.DatabaseConnection;
import backend.models.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the SubjectDao<Subject> and will communicate with the database to add, delete modify and retrieve data.
 */
public class SubjectDaoImpl implements SubjectDao<Subject> {
    private Logger log = LoggerFactory.getLogger(SubjectDaoImpl.class);
    private DatabaseConnection databaseConnection;
    private Connection connection;

    public String[][] getAllSubjects() {
        return null;
    }

    /**
     * Add subject into mysql database
     *
     * @param subject  subject
     * @param courseID relevant course
     */
    public void addSubject(Subject subject, int courseID) {
        this.connection = databaseConnection.getConnection();
        PreparedStatement preparedStatement;
        String query = "INSERT INTO subject(subjectName,numberOfCredit,courseID) VALUES(?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, subject.getSubjectName());
            preparedStatement.setInt(2, subject.getNumberOfCredits());
            preparedStatement.setInt(3, courseID);
            preparedStatement.executeUpdate();
            String message1 = subject.getSubjectName() + " Subject Added successfully";
            log.info(message1);
            connection.close();
            String message2 = "Database connection closed";
            log.info(message2);
        } catch (SQLException e) {
            String message = "Database connection problem. check your Host, Username, Password and retry";
            log.error(message, e);
        }
    }

    public Subject getOneSubject() {
        return null;
    }

    public void updateSubject(Subject subject) {

    }

    public void deleteSubject(String subjectId) {

    }
}
