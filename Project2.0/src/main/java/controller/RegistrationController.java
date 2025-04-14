package controller;

import view.Registration;
import view.Login;
import model.User;


import javax.swing.*;

public class RegistrationController {
    private Registration view;

    public RegistrationController(Registration view) {
        this.view = view;
        this.view.addRegisterListener(e -> registerUser());
        this.view.addPreviousListener(e -> goToLoginPage());
    }

    private void registerUser() {
        String username = view.getUsername();
        String password = view.getPassword();
        String address = view.getAddress();
        String telephone = view.getTelephoneNumber();

        if (username.isEmpty() || password.isEmpty() || address.isEmpty() || telephone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = new User(username, password, address, telephone);
        if (user.save()) {
            JOptionPane.showMessageDialog(view, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goToLoginPage() {
        view.dispose(); // Close the current registration form
        Login loginForm = new Login();
        new LoginController(loginForm); // Reinitialize login form and controller
        loginForm.setVisible(true);
    }
}
