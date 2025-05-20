package view;

import model.AssignDeliveryPersonnel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AssignDeliveryView extends JFrame {
    private JComboBox<String> personnelDropdown;
    private JButton nextButton;
    private JButton backButton;
    public String enteredcode;
    public String enteredpersonnelid;

    public AssignDeliveryView(List<String> availablePersonnelIds, ActionListener nextListener, ActionListener backListener,String enteredcode) {
        //Icon
        //ImageIcon icon = new ImageIcon(getClass().getResource("/images/Fast.jpg"));
        //setIconImage(icon.getImage());

        this.enteredcode=enteredcode;


        setTitle("FastTrack Logistics | Assign Delivery Personnel");
        setSize(500, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center the window
        setLayout(new BorderLayout());

        // Create a main panel with BoxLayout for vertical alignment
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // padding

        // Stylish label
        JLabel label = new JLabel("Assign Delivery Personnel", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Dropdown
        personnelDropdown = new JComboBox<>(availablePersonnelIds.toArray(new String[0]));
        personnelDropdown.setMaximumSize(new Dimension(300, 30));
        personnelDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Spacers
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(label);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(personnelDropdown);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel, BorderLayout.CENTER);

        // Bottom panel for buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // padding

        backButton = new JButton("Back");
        nextButton = new JButton("Next");

        styleButton(backButton);
        styleButton(nextButton);

        bottomPanel.add(backButton, BorderLayout.WEST);
        bottomPanel.add(nextButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // Listeners
        nextButton.addActionListener(nextListener);
        backButton.addActionListener(backListener);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Set the Look and Feel to Metal
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Metal".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

// Create and show the main application window
                SwingUtilities.invokeLater(() -> {
                    Shipment_view shipmentView = new Shipment_view();
                    JFrame frame = new JFrame("Shipment Management System");
                    frame.setContentPane(shipmentView.getPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setSize(1400, 600);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                });
                SwingUtilities.getWindowAncestor(mainPanel).dispose();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    enteredpersonnelid  = getSelectedPersonnelId();
                    String personnelId = enteredpersonnelid;

                    //int parcelId = 123; // You can change this or pass it from previous form
                    ScheduleForm scheduleForm = new ScheduleForm(enteredcode, personnelId);
                    scheduleForm.setVisible(true);
                    dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Delivery Personnel ID");
                }

            }
        });

    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 51, 102)); // dark blue
        button.setForeground(Color.WHITE); // white text
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 40));
    }

    public String getSelectedPersonnelId() {
        return (String) personnelDropdown.getSelectedItem();
    }

}
