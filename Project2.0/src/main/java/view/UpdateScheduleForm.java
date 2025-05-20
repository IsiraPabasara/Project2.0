package view;

import com.toedter.calendar.JDateChooser;
import controller.ScheduleController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateScheduleForm extends JFrame {

    private JTextField scheduleIdField;
    private JDateChooser newDateChooser;
    private ScheduleController controller;

    public UpdateScheduleForm() {
        controller = new ScheduleController();

        // Light theme colors
        Color backgroundColor = Color.WHITE;
        Color labelColor = new Color(33, 33, 33); // dark gray
        Color accentColor = new Color(3, 35, 75); // blue
        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Update Delivery Schedule");
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
        mainPanel.add(makeLabel("Enter Schedule ID:", font, labelColor), gbc);
        scheduleIdField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(scheduleIdField, gbc);

        // New Date
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(makeLabel("New Delivery Date:", font, labelColor), gbc);
        newDateChooser = new JDateChooser();
        newDateChooser.setPreferredSize(new Dimension(150, 25));
        gbc.gridx = 1;
        mainPanel.add(newDateChooser, gbc);

        // Update Button
        JButton updateBtn = new JButton("Update");
        updateBtn.setFont(font);
        updateBtn.setBackground(accentColor);
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFocusPainted(false);
        updateBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateBtn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        updateBtn.addActionListener((ActionEvent e) -> {
            String scheduleId = scheduleIdField.getText().trim();
            Date newDate = newDateChooser.getDate();

            if (scheduleId.isEmpty() || newDate == null) {
                JOptionPane.showMessageDialog(this, "Please enter a Schedule ID and select a new date.");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(newDate);

            controller.updateSchedule(scheduleId, formattedDate);
            JOptionPane.showMessageDialog(this, "Schedule updated successfully!");
        });

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(updateBtn, gbc);

        setTitle("FastTrack Logistics | Update Schedule");
        ImageIcon appIcon = new ImageIcon("D:/GitHub/Images/1.jpg");
        setIconImage(appIcon.getImage());
        setContentPane(mainPanel);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private JLabel makeLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }
}
