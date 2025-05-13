package controller;

import model.User;
import model.UserDAO;
import util.DBConnection;
import view.HomeView;
import view.LoginView;
import view.RegisterView;
import javax.swing.JOptionPane;

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

    public void register(String username, String password) {
        if (DBConnection.usernameExists(username)) {
            JOptionPane.showMessageDialog(null,
                    "Username already exists. Please choose a different username.",
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
        } else {
            User user = new User(username, password);
            userDAO.register(user);

            JOptionPane.showMessageDialog(null,
                    "Registration successful! You can now log in.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            showLoginView();
        }
    }

    public void login(String username, String password) {
        User user = userDAO.login(username, password);
        if (user != null) {
            new HomeView(user);
        } else {
            System.out.println("Login failed. Try again.");
            showLoginView();
            JOptionPane.showMessageDialog(null,
                    "Username does not exist.",
                    "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
