package view;

import controller.AuthController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JPanel panel1;
    private JTextField usernameTextField;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton previousPageButton;


    private AuthController controller;

    public LoginView(AuthController controller) {
        this.controller = controller;

        setTitle("Login");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = passwordField.getText();
                controller.login(username, password);
                dispose();
            }
        });

        previousPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showRegisterView();
                dispose();
            }
        });
    }
}
