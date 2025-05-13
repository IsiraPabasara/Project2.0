package view;

import controller.AuthController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    private JPanel panel1;
    private JTextField usernameTextField;
    private JTextField passwordField;
    private JButton registerButton;
    private JButton previousPageButton;

    private AuthController controller;

    public RegisterView(AuthController controller) {
        this.controller = controller;

        setTitle("Register");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText().trim();
                String password = passwordField.getText().trim();

                // Validation
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterView.this,
                            "Username and password must not be empty.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.length() < 6) {
                    JOptionPane.showMessageDialog(RegisterView.this,
                            "Password must be at least 6 characters long.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                controller.register(username, password);
                dispose();
            }
        });

        previousPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showLoginView();
                dispose();
            }
        });
    }
}
