package model;

import java.sql.*;
import java.util.HashMap;

public class User {


    private String username;
    private String password;
    private String address;
    private String telephoneNumber;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/fasttrack";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public User(String username, String password, String address, String telephoneNumber) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    public boolean save() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO user (username, password, address, telephoneNumber, role) VALUES (?, ?, ?, ?, 'customer')")) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, address);
            statement.setString(4, telephoneNumber);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean authenticate(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM user WHERE username = ? AND password = ?")) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String authenticateAndGetRole(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT role FROM user WHERE username = ? AND password = ?")) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("role");
                } else {
                    return null; // Invalid credentials
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }





}
