package backend;

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
    private String hostUrl ="";
    private String username="";
    private String password="";

    /**
     * we have to keep constructor as private to limit the class for only one instance.
     */
    private DatabaseConnection() {
        try {
            Class.forName("");
            assert false;
            this.connection = DriverManager.getConnection(hostUrl, username, password);
        } catch (ClassNotFoundException e) {
            String message = "Check JDBC connection";
            logger.error(message,e);
        } catch (SQLException e) {
            String message = "Check host, username, password in MySQL connection";
            logger.error(message,e);
        }
    }

    private Connection getConnection() {
        return connection;
    }

    /**
     * By using static method, no need to create an instance. instead of that can direct call the method and get the connection
     * @return created database connection
     * @throws SQLException can be arise, if connection will null
     */
    public static DatabaseConnection getInstance() throws SQLException {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        if (databaseConnection.getConnection().isClosed()) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }
}
