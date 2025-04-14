package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {

    private JButton adminButton;
    private JButton staffButton;
    private JButton customerButton;
    private JPanel contentPane;
    private JLabel resultlbl;

    public Dashboard(String role) {
        setContentPane(contentPane);
        setTitle("Dashboard");
        setSize(400, 500); // Set the size you want
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        resultlbl.setText("Welcome, " + role + "!");

        // Adjust button visibility and access based on the role
        switch (role.toLowerCase()) {
            case "customer":
                adminButton.setVisible(false);
                staffButton.setVisible(false);
                break;
            case "staff":
                adminButton.setVisible(false);
                break;
            case "admin":
                // Admin has access to all buttons; no need to disable anything
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid role!", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0); // Exit the application if role is invalid
        }
    }

    // Methods to add listeners for buttons
    public void addAdminListener(ActionListener listener) {
        if (adminButton != null) adminButton.addActionListener(listener);
    }

    public void addStaffListener(ActionListener listener) {
        if (staffButton != null) staffButton.addActionListener(listener);
    }

    public void addCustomerListener(ActionListener listener) {
        if (customerButton != null) customerButton.addActionListener(listener);
    }
}
