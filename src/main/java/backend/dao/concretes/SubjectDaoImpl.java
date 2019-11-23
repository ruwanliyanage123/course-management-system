package backend.dao.concretes;

import backend.dao.interfaces.SubjectDao;
import backend.database.DatabaseConnection;
import backend.models.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the SubjectDao<Subject> and will communicate with the database to add, delete modify and retrieve data.
 */
public class SubjectDaoImpl implements SubjectDao<Subject> {
    private Logger log = LoggerFactory.getLogger(SubjectDaoImpl.class);
    private DatabaseConnection databaseConnection;
    private Connection connection;

    public SubjectDaoImpl() {
        try {
            databaseConnection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            String message = "DatabaseConnection class not initiated";
            log.error(message, e);
        }
    }

    /**
     * to retrieve subjects from database
     *
     * @return rows of the table
     */
    public String[][] getAllSubjects() {
        String query = "SELECT * FROM subject";
        PreparedStatement preparedStatement;
        List<String[]> list = new ArrayList<String[]>();
        try {
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String[] tuple = new String[4];
                tuple[0] = String.valueOf(resultSet.getInt(1));
                tuple[1] = resultSet.getString(2);
                tuple[2] = String.valueOf(resultSet.getInt(3));
                tuple[3] = String.valueOf(resultSet.getInt(4));
                list.add(tuple);
            }
            String message1 = "Subject retrieved successfully";
            log.info(message1);
            connection.close();
            String message2 = "Database connection closed";
            log.info(message2);
        } catch (SQLException e) {
            String message = "Database connection problem. check your Host, Username, Password and retry";
            log.error(message, e);
        }
        String[][] rows = new String[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            rows[i] = list.get(i);
        }
        return rows;
    }

    /**
     * Add subject into mysql database
     *
     * @param subject subject
     */
    public void addSubject(Subject subject) {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO subject(subjectName,numberOfCredit,courseID) VALUES(?,?,?)";
        try {
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, subject.getSubjectName());
            preparedStatement.setInt(2, subject.getNumberOfCredits());
            preparedStatement.setInt(3, subject.getCourseID());
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

    /**
     * to retrieve a one subject
     * @param subjectID subject id
     * @return subject
     */
    public Subject getOneSubject(String subjectID) {
        String query = "SELECT * FROM subject WHERE subjectID = ?";
        PreparedStatement preparedStatement;
        Subject subject = null;
        try {
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,Integer.parseInt(subjectID));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int studentID = resultSet.getInt(1);
                String subjectName  = resultSet.getString(2);
                int numberOfCredit = resultSet.getInt(3);
                int courseID = resultSet.getInt(4);
                subject = new Subject(subjectName,numberOfCredit,courseID);
                subject.setSubjectId(studentID);
            }
            String message1 = "Subject retrieved successfully";
            log.info(message1);
            connection.close();
            String message2 = "Database connection closed";
            log.info(message2);
        } catch (SQLException e) {
            String message = "Database connection problem. check your Host, Username, Password and retry";
            log.error(message, e);
        }
        return subject;
    }

    /**
     * to update subject  table
     * @param subject subject
     */
    public void updateSubject(Subject subject) {
        PreparedStatement preparedStatement;
        String query = "UPDATE subject SET subjectName = ?, numberOfCredit =?, courseID= ? WHERE subjectID =?";
        try {
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, subject.getSubjectName());
            preparedStatement.setInt(2, subject.getNumberOfCredits());
            preparedStatement.setInt(3, subject.getCourseID());
            preparedStatement.setInt(4,subject.getSubjectId());
            preparedStatement.executeUpdate();
            String message1 = subject.getSubjectName() + " Subject updated successfully";
            log.info(message1);
            connection.close();
            String message2 = "Database connection closed";
            log.info(message2);
        } catch (SQLException e) {
            String message = "Database connection problem. check your Host, Username, Password and retry";
            log.error(message, e);
        }
    }

    /**
     * to detete subject
     * @param subjectId subject id
     */
    public void deleteSubject(int subjectId) {
        PreparedStatement preparedStatement;
        String query = "DELETE FROM subject WHERE subjectID=?";
        try {
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,subjectId);
            preparedStatement.executeUpdate();
            String message1 = "subject "+subjectId+" Deleted successfully";
            log.info(message1);
            connection.close();
            String message2 = "Database connection closed";
            log.info(message2);
        } catch (SQLException e) {
            String message = "Database connection problem. check your Host, Username, Password and retry";
            log.error(message, e);
        }
    }
}
