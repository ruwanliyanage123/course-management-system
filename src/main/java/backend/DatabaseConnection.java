package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class used the Singleton design pattern to create and distribute the database connection
 */
public class DatabaseConnection {
    private static DatabaseConnection databaseConnection;
    private Connection connection;
    private String hostUrl;
    private String username;
    private String password;

    /**
     * we have to keep constructor as private to limit the class for only one instance.
     */
    private DatabaseConnection() {
        try {
            Class.forName("");
            assert hostUrl != null;
            this.connection = DriverManager.getConnection(hostUrl, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * By using static method, no need to create an instance. instead of that can direct call the method and get the connection
     * @return created database connection
     * @throws SQLException
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
