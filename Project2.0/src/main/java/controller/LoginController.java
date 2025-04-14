package controller;

import model.User;
import view.Login;
import controller.DashboardController;
import view.Registration;

import javax.swing.*;

public class LoginController {
    private Login view;

    public LoginController(Login view) {
        this.view = view;

        // Add action listeners for buttons
        this.view.addLoginListener(e -> loginUser());
        this.view.addRegisterListener(e -> goToRegistrationPage());
    }

    private void loginUser() {
        // Retrieve username and password from the form
        String username = view.getUsername();
        String password = view.getPassword();

        // Validate fields
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Authenticate user and retrieve role
        String role = User.authenticateAndGetRole(username, password); // Simulated database call
        if (role != null) {
            JOptionPane.showMessageDialog(view, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Dispose of the login form
            view.dispose();

            // Pass role to the DashboardController
            new DashboardController(role);
        } else {
            JOptionPane.showMessageDialog(view, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goToRegistrationPage() {
        // Dispose of the login form and navigate to the registration page
        view.dispose();
        // Add logic to initialize and show the Registration Form
        Registration Registratioform = new Registration();
        new RegistrationController(Registratioform); // Reinitialize login form and controller
        Registratioform.setVisible(true);
    }


}
