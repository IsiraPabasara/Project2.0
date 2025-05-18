package controller;

import model.User;
import model.UserDAO;
import util.DBConnection;
import view.LoginView;
import view.RegisterView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthController {
    private UserDAO userDAO;
    private DBConnection dbConnection;

    public AuthController() {
        userDAO = new UserDAO();
        dbConnection = new DBConnection();
    }

    public void showRegisterView() {
        new RegisterView(this);
    }

    public void showLoginView() {
        new LoginView(this);
    }

    public boolean register(String username, String password) {
        if (DBConnection.usernameExists(username)) {
            JOptionPane.showMessageDialog(null,
                    "Username already exists. Please choose a different username.",
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            User user = new User(username, password);
            userDAO.register(user);

            JOptionPane.showMessageDialog(null,
                    "Registration successful! You can now log in.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }


    // ✅ Updated login method: checks DB, returns true if username+password match
    public boolean login(String username, String password) {
        try (Connection conn = dbConnection.getConnection()) {
            String query = "SELECT * FROM staff WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // true if user exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
