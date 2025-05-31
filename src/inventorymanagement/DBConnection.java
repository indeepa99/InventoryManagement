package inventorymanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/inventory";
    private static final String USER = "root"; // Change if your MySQL user is different
    private static final String PASS = "";     // Change if your MySQL password is not empty

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
} 