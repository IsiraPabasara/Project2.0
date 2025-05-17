package view;

import controller.DeliveryPersonnelController;
import model.DeliveryPersonnel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.io.File;

import javax.swing.border.EmptyBorder;

public class DeliveryPersonnelView extends JFrame {

    private DeliveryPersonnelController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public DeliveryPersonnelView() {

        setTitle("🚚 Fasttrack Logistics | Manage Delivery Personnel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("D:/GitHub/1.jpg");
        setIconImage(icon.getImage());

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"Delivery Personnel ID", "Email", "Name", "Phone Number", "Availability Status"}, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.setToolTipText("Select a row to update or delete a personnel");

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        // Font for buttons
        Font btnFont = new Font("Segoe UI", Font.BOLD, 13);

        // Create colorful buttons
        JButton addButton = new JButton("➕ Add Personnel");
        addButton.setBackground(new Color(33, 150, 243)); // green
        addButton.setForeground(Color.WHITE);

        JButton updateButton = new JButton("✏️ Update Personnel");
        updateButton.setBackground(new Color(33, 150, 243)); // blue
        updateButton.setForeground(Color.WHITE);

        JButton deleteButton = new JButton("❌ Delete Personnel");
        deleteButton.setBackground(new Color(33, 150, 243)); // red
        deleteButton.setForeground(Color.WHITE);

        JButton refreshButton = new JButton("🔄 Refresh");
        refreshButton.setBackground(new Color(33, 150, 243)); // amber
        refreshButton.setForeground(Color.BLACK);

        JButton backButton = new JButton("🔙 Back");
        backButton.setBackground(new Color(33, 150, 243)); // gray
        backButton.setForeground(Color.WHITE);

// Apply styling
        JButton[] buttons = {addButton, updateButton, deleteButton, refreshButton, backButton};
        for (JButton btn : buttons) {
            btn.setFont(btnFont);
            btn.setFocusPainted(false);
            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.setPreferredSize(new Dimension(160, 35));
            controlPanel.add(btn);
        }


        add(controlPanel, BorderLayout.SOUTH);

        // Navigation
        backButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Dashboard");
                Dashboard dashboard = new Dashboard();
                frame.setContentPane(dashboard.getMainPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
            SwingUtilities.getWindowAncestor(backButton).dispose();
        });

        // Add Personnel
        addButton.addActionListener(e -> openPersonnelDialog("Add Personnel", null));

        // Update Personnel
        updateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                DeliveryPersonnel p = new DeliveryPersonnel();
                p.setDelivery_personnel_id(tableModel.getValueAt(selectedRow, 0).toString());
                p.setEmail(tableModel.getValueAt(selectedRow, 1).toString());
                p.setName(tableModel.getValueAt(selectedRow, 2).toString());
                p.setPhone_number(tableModel.getValueAt(selectedRow, 3).toString());
                p.setAvailability_status(tableModel.getValueAt(selectedRow, 4).toString());
                openPersonnelDialog("Update Personnel", p);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a personnel to update.");
            }
        });

        // Delete Personnel
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String id = tableModel.getValueAt(selectedRow, 0).toString();
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this personnel?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deletePersonnel(id);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a personnel to delete.");
            }
        });

        // Refresh
        refreshButton.addActionListener(e -> controller.refreshPersonnelList());
    }

    private void openPersonnelDialog(String title, DeliveryPersonnel existingPersonnel) {
        JTextField idField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JTextField nameField = new JTextField(15);
        JTextField phoneField = new JTextField(15);
        JTextField statusField = new JTextField(15);

        if (existingPersonnel != null) {
            idField.setText(existingPersonnel.getDelivery_personnel_id());
            idField.setEditable(false);
            emailField.setText(existingPersonnel.getEmail());
            nameField.setText(existingPersonnel.getName());
            phoneField.setText(existingPersonnel.getPhone_number());
            statusField.setText(existingPersonnel.getAvailability_status());
        }

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5));
        panel.add(new JLabel("Personnel ID:"));
        panel.add(idField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneField);
        panel.add(new JLabel("Availability:"));
        panel.add(statusField);

        int result = JOptionPane.showConfirmDialog(this, panel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            DeliveryPersonnel p = new DeliveryPersonnel();
            p.setDelivery_personnel_id(idField.getText());
            p.setEmail(emailField.getText());
            p.setName(nameField.getText());
            p.setPhone_number(phoneField.getText());
            p.setAvailability_status(statusField.getText());

            if (existingPersonnel == null) {
                controller.addPersonnel(p);
            } else {
                controller.updatePersonnel(p);
            }
        }
    }

    public void setController(DeliveryPersonnelController controller) {
        this.controller = controller;
    }

    public void displayPersonnel(List<DeliveryPersonnel> personnelList) {
        tableModel.setRowCount(0);
        for (DeliveryPersonnel p : personnelList) {
            tableModel.addRow(new Object[]{
                    p.getDelivery_personnel_id(),
                    p.getEmail(),
                    p.getName(),
                    p.getPhone_number(),
                    p.getAvailability_status()
            });
        }
    }
}
