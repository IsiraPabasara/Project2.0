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
        setSize(400, 500); // set the size you want
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        resultlbl.setText("Welcome, " + role + "!");
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
