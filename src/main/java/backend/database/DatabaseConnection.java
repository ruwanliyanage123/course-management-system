package backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class used the Singleton design pattern to create and distribute the database connection
 */
public class DatabaseConnection {
    private static Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    private static DatabaseConnection databaseConnection;
    private Connection connection;

    /**
     * we have to keep constructor as private to limit the class for only one instance.
     */
    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            assert false;
            String password = "19940306";
            String username = "root";
            String hostUrl= "jdbc:mysql://localhost:3306/course_management_system";
            this.connection = DriverManager.getConnection(hostUrl, username, password);
            logger.info("Database Connection Creation successfully");
        } catch (ClassNotFoundException e) {
            String message = "Check JDBC connection";
            logger.error(message,e);
        } catch (SQLException e) {
            String message = "Check host, username, password in MySQL connection";
            logger.error(message,e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * By using static method, no need to create an instance. instead of that can direct call the method and get the connection
     * @return created database connection
     * @throws SQLException can be arise, if connection will null
     * keep method as synchronized to make sure that the method as thread safe
     */
    public synchronized static DatabaseConnection getInstance() throws SQLException {
    /**
     * This pattern is not guaranteed that for the singleton pattern. Insted of this we can use
     * 1. Double checking mechanism
     * 2. Nested static class mechanism.
     * If we use Double checking mechanism, that will cause for the deadlocking situations, then est ways is using a nested static class for this..
     */
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        if (databaseConnection.getConnection().isClosed()) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }


}
