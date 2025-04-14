package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Registration extends JFrame {
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField addressField;
    private JTextField telephoneField;
    private JButton registerButton;
    private JButton loginButton;
    private JPanel contentPane;


    public Registration() {
        setContentPane(contentPane);
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getText());
    }

    public String getAddress() {
        return addressField.getText();
    }

    public String getTelephoneNumber() {
        return telephoneField.getText();
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public void addPreviousListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }


}
