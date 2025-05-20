package view;

import controller.DeliveryPersonnelController;
import controller.TrackingController;
import dao.TrackingDAO;
import model.DeliveryPersonnelDAO;
import service.TrackingService;
import util.AutoTrackingSync;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class Dashboard extends JFrame {
    private JButton sendparcel;
    private JButton managedeliverypersonnel;
    private JButton viewreport;
    private JButton trackshipment;
    private JPanel mainPanel;
    private JLabel welcomeLabel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Dashboard(String adminId) {
        // Set up the frame
        setTitle("🚚 Fasttrack Logistics | Staff Dashboard");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon appIcon = new ImageIcon("D:/GitHub/Images/1.jpg");
        setIconImage(appIcon.getImage());


        // Create main panel with custom layout
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(12,23));

        // Create and style the welcome label
        welcomeLabel = new JLabel("Welcome to Admin Dashboard",  JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 25));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBackground(Color.white);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        ImageIcon sendIcon = new ImageIcon("D:/GitHub/Images/send.png");
        sendparcel = new JButton("Send Parcel", sendIcon);

        // Show text below image
        sendparcel.setHorizontalTextPosition(SwingConstants.CENTER);
        sendparcel.setVerticalTextPosition(SwingConstants.BOTTOM);


        ImageIcon deliveryicon = new ImageIcon("D:/GitHub/Images/delivery.png");
        managedeliverypersonnel = new JButton("Manage Delivery Personnel", deliveryicon);

        // Show text below image
        managedeliverypersonnel.setHorizontalTextPosition(SwingConstants.CENTER);
        managedeliverypersonnel.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon reportIcon = new ImageIcon("D:/GitHub/Images/report.png");
        viewreport = new JButton("View Report", reportIcon);

        // Show text below image
        viewreport.setHorizontalTextPosition(SwingConstants.CENTER);
        viewreport.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon shipping = new ImageIcon("D:/GitHub/Images/shipping.png");
        trackshipment = new JButton("Track Shipment", shipping);

        // Show text below image
        trackshipment.setHorizontalTextPosition(SwingConstants.CENTER);
        trackshipment.setVerticalTextPosition(SwingConstants.BOTTOM);


        // Style buttons
        styleButton(sendparcel);
        styleButton(managedeliverypersonnel);
        styleButton(viewreport);
        styleButton(trackshipment);

        // Add buttons to panel
        buttonPanel.add(sendparcel);
        buttonPanel.add(managedeliverypersonnel);
        buttonPanel.add(viewreport);
        buttonPanel.add(trackshipment);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners
        sendparcel.addActionListener(new ActionListener() {
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
                    JFrame frame = new JFrame("🚚 Fasttrack Logistics | Sender | Receiver | Parcel Details");
                    frame.setContentPane(shipmentView.getPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setSize(1400, 600);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    try {
                        // Load the image from resources folder
                        ImageIcon appIcon = new ImageIcon("D:/GitHub/Images/1.jpg");

                        if (appIcon.getImage() != null) {
                            // Scale image if needed
                            Image scaledImage = appIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                            frame.setIconImage(scaledImage);
                        }

                        // Scale the image if needed (example: 128x128)
                        Image image = appIcon.getImage();
                        Image scaledImage = image.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
                        frame.setIconImage(scaledImage);
                    } catch (Exception e2) {
                        System.err.println("Could not load logo: " + e2.getMessage());
                        // Fallback to default Java icon if logo not found
                    }
                });
                SwingUtilities.getWindowAncestor(mainPanel).dispose();

            }
        });

        managedeliverypersonnel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryPersonnelView view = new DeliveryPersonnelView();
                DeliveryPersonnelDAO dao = new DeliveryPersonnelDAO();
                DeliveryPersonnelController controller = new DeliveryPersonnelController(dao, view);

                view.setVisible(true);
                controller.refreshPersonnelList();

                SwingUtilities.getWindowAncestor(mainPanel).dispose();
            }
        });

        viewreport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(Dashboard.this, "View Report functionality coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
                new ReportView();
                dispose();
            }
        });

        trackshipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Connection conn = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/project2.0", "root", ""); // Change password if needed

                    TrackingDAO trackingDAO = new TrackingDAO(conn);

                    // Start auto-tracking thread
                    new AutoTrackingSync(conn, trackingDAO).start();

                    // You can now launch your main app interface or controller here

                    TrackingService trackingService = new TrackingService(trackingDAO);
                    TrackingView trackingView = new TrackingView();

                    TrackingController controller = new TrackingController(trackingView, trackingService);
                    controller.showView();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }            }
        });

        setContentPane(mainPanel);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(76, 168, 253)); // Blue color
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String adminId = "Admin123"; // Example admin ID passed from login
            Dashboard dashboard = new Dashboard(adminId);
            dashboard.setVisible(true);
        });
    }*/




}
