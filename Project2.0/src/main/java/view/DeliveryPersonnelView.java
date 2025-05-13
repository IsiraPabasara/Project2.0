package view;

import controller.DeliveryPersonnelController;
import model.DeliveryPersonnel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.io.File;

public class DeliveryPersonnelView extends JFrame {

    private DeliveryPersonnelController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public DeliveryPersonnelView() {

        setTitle("Logo | Fasttrack Logistics | Add Delivery Personnel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("D:/GitHub/1.jpg");
        setIconImage(icon.getImage());

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"Delivery Personnel ID", "Email", "Name", "Phone Number", "Availability Status"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Control panel
        JPanel controlPanel = new JPanel();

        JButton addButton = new JButton("Add Personnel");
        JButton updateButton = new JButton("Update Personnel");
        JButton deleteButton = new JButton("Delete Personnel");

        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");

        controlPanel.add(addButton);
        controlPanel.add(updateButton);
        controlPanel.add(deleteButton);
        controlPanel.add(refreshButton);
        controlPanel.add(backButton);

        add(controlPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Dashboard");
                Dashboard dashboard = new Dashboard();
                frame.setContentPane(dashboard.getMainPanel()); // set the designed panel
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 400); // adjust as needed
                frame.setLocationRelativeTo(null); // center the window
                frame.setVisible(true);
            });
            SwingUtilities.getWindowAncestor(backButton).dispose();

        });

        // Add Personnel action
        addButton.addActionListener(e -> {

            String delivery_personnel_id = JOptionPane.showInputDialog(null,"Enter Delivery Personnel ID:","Input",JOptionPane.PLAIN_MESSAGE);
            String email = JOptionPane.showInputDialog(null,"Enter Email:","Input",JOptionPane.PLAIN_MESSAGE);
            String name = JOptionPane.showInputDialog(null,"Enter Name:","Input",JOptionPane.PLAIN_MESSAGE);
            String phone_number = JOptionPane.showInputDialog(null,"Enter Phone Number:","Input",JOptionPane.PLAIN_MESSAGE);
            String availability_status = JOptionPane.showInputDialog(null,"Enter Availability Status:","Input",JOptionPane.PLAIN_MESSAGE);

            DeliveryPersonnel p = new DeliveryPersonnel();

            p.setDelivery_personnel_id(delivery_personnel_id);
            p.setEmail(email);
            p.setName(name);
            p.setPhone_number(phone_number);
            p.setAvailability_status(availability_status);

            controller.addPersonnel(p);

        });

        // Update Personnel action
        updateButton.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) {

                String delivery_personnel_id = tableModel.getValueAt(selectedRow, 0).toString();
                String email = JOptionPane.showInputDialog("Enter New Email:",tableModel.getValueAt(selectedRow, 1));
                String name = JOptionPane.showInputDialog("Enter New Name:", tableModel.getValueAt(selectedRow, 2));
                String phone_number = JOptionPane.showInputDialog("Enter New Phone:", tableModel.getValueAt(selectedRow, 3));
                String availability_status = JOptionPane.showInputDialog("Enter New Availability:", tableModel.getValueAt(selectedRow, 4));


                DeliveryPersonnel p = new DeliveryPersonnel();
                p.setDelivery_personnel_id(delivery_personnel_id);
                p.setEmail(email);
                p.setName(name);
                p.setPhone_number(phone_number);
                p.setAvailability_status(availability_status);

                controller.updatePersonnel(p);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a personnel to update.");
            }
        });

        // Delete Personnel action
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {

                String delivery_personnel_id = tableModel.getValueAt(selectedRow, 0).toString();
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this personnel?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deletePersonnel(delivery_personnel_id);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a personnel to delete.");
            }
        });

        refreshButton.addActionListener(e -> {
            controller.refreshPersonnelList();
        });

    }

    public void setController(DeliveryPersonnelController controller) {
        this.controller = controller;//view know which controller to talk to when buttons are clicked.
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
