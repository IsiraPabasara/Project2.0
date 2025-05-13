package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/delivery_app";
    private static final String USER = "root"; // your WAMP username
    private static final String PASSWORD = ""; // your WAMP password (often blank by default)

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean usernameExists(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // If a record is found, username exists

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // If error, assume username does not exist (handle better later)
        }
    }


}
