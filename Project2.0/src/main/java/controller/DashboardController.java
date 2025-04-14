package controller;

import view.Dashboard;

import javax.swing.*;

public class DashboardController {
    private Dashboard view;

    public DashboardController(String role) {
        // Initialize the view
        view = new Dashboard(role);

        // Add action listeners
        view.addAdminListener(e -> openAdminPanel());
        view.addStaffListener(e -> openStaffPanel());
        view.addCustomerListener(e -> openCustomerPanel());

        // Show the dashboard
        view.setVisible(true);
    }

    private void openAdminPanel() {
        JOptionPane.showMessageDialog(view, "Opening Admin Panel...");
        // Logic to open Admin Panel
    }

    private void openStaffPanel() {
        JOptionPane.showMessageDialog(view, "Opening Staff Panel...");
        // Logic to open Staff Panel
    }

    private void openCustomerPanel() {
        JOptionPane.showMessageDialog(view, "Opening Customer Panel...");
        // Logic to open Customer Panel
    }
}
