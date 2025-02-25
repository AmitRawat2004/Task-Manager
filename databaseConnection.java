import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/taskmanager";
    private static final String USER = "root"; // Change this if needed
    private static final String PASSWORD = "rawat2001"; // Add your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

