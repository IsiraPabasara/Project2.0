package view;

import controller.AuthController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JPanel mainPanel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton previousPageButton;
    private JLabel passwordLabel;
    private JLabel usernameLabel;

    private AuthController controller;

    public LoginView(AuthController controller) {
        this.controller = controller;

        // Frame settings
        setTitle("🚚 Fasttrack Logistics | Staff Dashboard");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon appIcon = new ImageIcon("D:/GitHub/Images/1.jpg");
        setIconImage(appIcon.getImage());

        // Main panel setup
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Center panel setup
        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);

        // Action listeners
        loginButton.addActionListener(e -> {
            String username = usernameTextField.getText();
            String password = new String(passwordField.getPassword());

            boolean success = controller.login(username, password);

            if (success) {
                String adminId = username; // Example admin ID passed from login
                Dashboard dashboard = new Dashboard(adminId);
                dashboard.setVisible(true);
                dispose();
            } else {
                // Login failed - stay on the same screen
                JOptionPane.showMessageDialog(this,
                        "Invalid username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);

                passwordField.setText("");
                usernameTextField.requestFocusInWindow();
            }
        });



        previousPageButton.addActionListener(e -> {
            RegisterView registerView = new RegisterView(controller); // Create the register view
            registerView.setVisible(true);                            // Show the register view
            dispose();                                                // Close the current login view
        });

    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(5, 6, 36));
        centerPanel.setBorder(new EmptyBorder(0, 30, 20, 30));

        centerPanel.add(Box.createVerticalGlue());

        // Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon logo = new ImageIcon("D:/GitHub/Images/1.jpg");
        Image scaledImage = logo.getImage().getScaledInstance(180, 240, Image.SCALE_SMOOTH);
        logo.setImage(scaledImage);
        logoLabel.setIcon(logo);
        logoLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        centerPanel.add(logoLabel);
        centerPanel.add(Box.createVerticalStrut(20));

        // Username
        usernameLabel = new JLabel("Username");
        styleLabel(usernameLabel);
        centerPanel.add(usernameLabel);
        centerPanel.add(Box.createVerticalStrut(8));

        usernameTextField = createTextField("Enter your username");
        centerPanel.add(usernameTextField);
        centerPanel.add(Box.createVerticalStrut(15));

        // Password
        passwordLabel = new JLabel("Password");
        styleLabel(passwordLabel);
        centerPanel.add(passwordLabel);
        centerPanel.add(Box.createVerticalStrut(8));

        passwordField = createPasswordField("Enter your password");
        centerPanel.add(passwordField);
        centerPanel.add(Box.createVerticalStrut(25));

        // Buttons Panel (same row)
        loginButton = createStyledButton("Login", "D:/GitHub/Images/login.png", new Color(0, 102, 204));
        previousPageButton = createStyledButton("Register", "D:/GitHub/Images/register.png", new Color(0, 102, 204));

        styleButton(loginButton); // Optional: if you have extra styling
        styleButton(previousPageButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);
        buttonPanel.add(previousPageButton);

        centerPanel.add(buttonPanel);

        return centerPanel;
    }

    private JButton createStyledButton(String text, String iconPath, Color bgColor) {
        JButton button = new JButton(text);
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.err.println("⚠️ Icon missing: " + iconPath);
        }

        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setIconTextGap(10);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(true);
        return button;
    }


    private JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(320, 45));
        textField.setMaximumSize(new Dimension(320, 45));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.BLACK);
        textField.setBackground(new Color(230, 245, 250));
        textField.setCaretColor(Color.BLACK);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setToolTipText(placeholder);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // No outer border
        return textField;
    }

    private JPasswordField createPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(320, 45));
        passwordField.setMaximumSize(new Dimension(320, 45));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setForeground(Color.BLACK);
        passwordField.setBackground(new Color(230, 245, 250));
        passwordField.setCaretColor(Color.BLACK);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setToolTipText(placeholder);
        passwordField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // No outer border
        return passwordField;
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 45));
        button.setMaximumSize(new Dimension(150, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // No border

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 144, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }
        });
    }

}
