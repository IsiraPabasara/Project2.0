package view;

import controller.AuthController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterView extends JFrame {
    private JPanel mainPanel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backToLoginButton;
    private JLabel passwordLabel;
    private JLabel usernameLabel;

    private AuthController controller;

    public RegisterView(AuthController controller) {
        this.controller = controller;

        setTitle("🚚 Fasttrack Logistics | Register");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon appIcon = new ImageIcon("D:/GitHub/Images/1.jpg");
        setIconImage(appIcon.getImage());

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);

        registerButton.addActionListener(e -> {
            String username = usernameTextField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty.",
                        "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean registered = controller.register(username, password);

            if (registered) {
                dispose();
                new LoginView(controller).setVisible(true);
            }
            // If not registered, do nothing, stay on registration page
        });


        backToLoginButton.addActionListener(e -> {
            new LoginView(controller).setVisible(true);
            dispose();
        });
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(5, 6, 36));
        centerPanel.setBorder(new EmptyBorder(0, 30, 20, 30));

        centerPanel.add(Box.createVerticalGlue());

        JLabel logoLabel = new JLabel();
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon logo = new ImageIcon("D:/GitHub/Images/1.jpg");
        Image scaledImage = logo.getImage().getScaledInstance(180, 240, Image.SCALE_SMOOTH);
        logo.setImage(scaledImage);
        logoLabel.setIcon(logo);
        logoLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        centerPanel.add(logoLabel);
        centerPanel.add(Box.createVerticalStrut(20));

        usernameLabel = new JLabel("Create Username");
        styleLabel(usernameLabel);
        centerPanel.add(usernameLabel);
        centerPanel.add(Box.createVerticalStrut(8));

        usernameTextField = createTextField("Choose a username");
        centerPanel.add(usernameTextField);
        centerPanel.add(Box.createVerticalStrut(15));

        passwordLabel = new JLabel("Create Password");
        styleLabel(passwordLabel);
        centerPanel.add(passwordLabel);
        centerPanel.add(Box.createVerticalStrut(8));

        passwordField = createPasswordField("Choose a password");
        centerPanel.add(passwordField);
        centerPanel.add(Box.createVerticalStrut(25));

        registerButton = createStyledButton("Register", "D:/GitHub/Images/register.png", new Color(34, 139, 34));
        backToLoginButton = createStyledButton("Back", "D:/GitHub/Images/back.png", new Color(70, 130, 180));

        styleButton(registerButton);
        styleButton(backToLoginButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(registerButton);
        buttonPanel.add(backToLoginButton);

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
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
        passwordField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return passwordField;
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(150, 45));
        button.setMaximumSize(new Dimension(150, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(button.getBackground().brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(button.getBackground().darker());
            }
        });
    }
}
