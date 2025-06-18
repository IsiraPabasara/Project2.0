package view;

import SpringEmailDemo.DeliveryPersonnelNotification;
import com.toedter.calendar.JDateChooser;
import controller.ScheduleController;
import org.springframework.boot.SpringApplication;
import repository.SpringEmailDemoApplicationCustomer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;

public class ScheduleForm extends JFrame {

    private String packageId;
    private String personnelId;

    private ScheduleController controller;
    private JDateChooser dateChooser;
    private JButton scheduleButton;
    private JTextField scheduleIdField;

    public ScheduleForm(String packageId, String personnelId) {
        this.packageId = packageId;
        this.personnelId = personnelId;

        controller = new ScheduleController();

        // Light theme colors
        Color backgroundColor = Color.WHITE;
        Color labelColor = new Color(33, 33, 33); // dark gray
        Color accentColor = new Color(3, 35, 75); // dark blue
        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Schedule Delivery");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(accentColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;

        // Schedule ID
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(makeLabel("Schedule ID:", font, labelColor), gbc);
        scheduleIdField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(scheduleIdField, gbc);

        // Parcel ID
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(makeLabel("Parcel ID:", font, labelColor), gbc);
        gbc.gridx = 1;
        mainPanel.add(makeLabel(String.valueOf(packageId), font, labelColor), gbc);

        // Delivery Personnel ID
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(makeLabel("Delivery Personnel ID:", font, labelColor), gbc);
        gbc.gridx = 1;
        mainPanel.add(makeLabel(String.valueOf(personnelId), font, labelColor), gbc);

        // Date chooser
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(makeLabel("Delivery Date:", font, labelColor), gbc);
        dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(new Dimension(150, 25));
        gbc.gridx = 1;
        mainPanel.add(dateChooser, gbc);

        // Buttons row (grid layout row for full-width buttons)
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(backgroundColor);

        // Update Button (left aligned)
        JButton updateButton = new JButton("Update");
        updateButton.setFont(font);
        updateButton.setBackground(accentColor);
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        updateButton.setPreferredSize(new Dimension(120, 35));
        updateButton.addActionListener(e -> {
            UpdateScheduleForm updateForm = new UpdateScheduleForm();
            updateForm.setVisible(true);
            dispose();
        });
        buttonPanel.add(updateButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Glue to push the next button to the right
        buttonPanel.add(Box.createHorizontalGlue());

        // Confirm & Schedule Button (right aligned)
        scheduleButton = new JButton("Confirm & Schedule");
        scheduleButton.setFont(font);
        scheduleButton.setBackground(accentColor);
        scheduleButton.setForeground(Color.WHITE);
        scheduleButton.setFocusPainted(false);
        scheduleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        scheduleButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        scheduleButton.setPreferredSize(new Dimension(200, 35));
        scheduleButton.addActionListener((ActionEvent e) -> {
            String scheduleId = scheduleIdField.getText().trim();
            Date selectedDate = dateChooser.getDate();

            if (scheduleId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Schedule ID.");
                return;
            }

            if (selectedDate == null) {
                JOptionPane.showMessageDialog(this, "Please select a date.");
                return;
            }

            controller.saveSchedule(scheduleId, selectedDate, packageId, personnelId);
            JOptionPane.showMessageDialog(this, "Schedule saved!");

            String[] defaultArgs = new String[] {};
            new Thread(() -> {
                SpringApplication.run(DeliveryPersonnelNotification.class, defaultArgs);
                SpringApplication.run(SpringEmailDemoApplicationCustomer.class, defaultArgs);
            }).start();
        });
        buttonPanel.add(scheduleButton);

        mainPanel.add(buttonPanel, gbc);


        //window
        setTitle("FastTrack Logistics | Schedule Delivery");
        ImageIcon appIcon = new ImageIcon("D:/GitHub/Images/1.jpg");
        setIconImage(appIcon.getImage());
        setContentPane(mainPanel);
        setSize(500, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JLabel makeLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }
}
