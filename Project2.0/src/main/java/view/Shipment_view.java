package view;

import controller.AssignDeliveryController;
import controller.CustomerController;
import controller.ParcelController;
import controller.ReceiverController;
import model.AssignDeliveryPersonnel;
import model.Customer;
import model.Parcel;
import model.Receiver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

public class Shipment_view {
    private JPanel panel1;
    private JTable Ctable;
    private JTextField C_Address;
    private JTextField R_Address;
    private JTextField R_Number;
    private JTextField R_Email;
    private JTextField R_Name;
    private JTextField R_Id;
    private JTextField C_Number;
    private JTextField C_Email;
    private JTextField C_Name;

    //Creating a variable to hold customer id
    public String customerIDID;

    //Creating a variable to hold sender id
    public String senderIDID;

    private JTextField C_Id;
    private JTable Rtable;
    private JTextField P_Id;
    private JTextField P_Content;
    private JComboBox P_Type;
    private JTable Ptable;
    private JButton C_add;
    private JButton C_remove;
    private JButton C_update;
    private JButton R_add;
    private JButton R_remove;
    private JButton R_update;
    private JButton p_add;
    private JButton Nextbtn;
    private JButton P_update;
    private JComboBox P_Status;
    private JSpinner P_DateSpinner;
    private JButton P_remove;
    private JButton Backbtn;
    private JButton refreshButton;
    public JTextField P_SenderId;
    public JTextField P_ReceiverId;

    private CustomerController customerController;
    private ReceiverController receiverController;
    private ParcelController parcelController;

    public String enteredcode;

    private void configureTables() {




        // Set consistent row height
        int rowHeight = 20;
        Ctable.setRowHeight(rowHeight);
        Rtable.setRowHeight(rowHeight);
        Ptable.setRowHeight(rowHeight);

        // Optional: set grid color for better visibility
        Color gridColor = new Color(0, 0, 0);
        Ctable.setGridColor(gridColor);
        Rtable.setGridColor(gridColor);
        Ptable.setGridColor(gridColor);

        // Optional: interleave row colors for better readability
        Ctable.setFillsViewportHeight(true);
        Rtable.setFillsViewportHeight(true);
        Ptable.setFillsViewportHeight(true);
    }


    private void createUIComponents() {
        // Initialize custom components (if any)
        Ctable = new JTable(); // Example
        Rtable = new JTable();
        Ptable = new JTable();
        P_Type = new JComboBox<>(new String[]{"Fragile", "Danger", "Heavy", "Valuable"});
        P_Status = new JComboBox<>(new String[]{"Processing", "Packed", "Shipped", "Delivered", "Cancelled"});

        // Initialize Date Spinner (for Created Date selection)
        SpinnerDateModel dateModel = new SpinnerDateModel();
        P_DateSpinner = new JSpinner(dateModel);

        // Set date format (e.g., "yyyy-MM-dd HH:mm:ss")
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(
                P_DateSpinner,
                "yyyy-MM-dd HH:mm:ss"
        );
        P_DateSpinner.setEditor(dateEditor);
    }

    private void refreshAllData() {
        try {
            // Show loading indicator
            Ctable.setEnabled(false);
            Rtable.setEnabled(false);
            Ptable.setEnabled(false);

            // Refresh all data
            loadCustomerData();
            loadReceiverData();
            loadParcelData();

            // Clear selection and fields
            Ctable.clearSelection();
            Rtable.clearSelection();
            Ptable.clearSelection();
            clearCustomerFields();
            clearReceiverFields();
            clearParcelFields();

            JOptionPane.showMessageDialog(panel1, "Data refreshed successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel1,
                    "Error refreshing data: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Re-enable tables
            Ctable.setEnabled(true);
            Rtable.setEnabled(true);
            Ptable.setEnabled(true);
        }
    }

    public Shipment_view() {
        // Initialize controllers
        customerController = new CustomerController();
        receiverController = new ReceiverController();
        parcelController = new ParcelController();

        // Load initial data
        loadCustomerData();
        loadReceiverData();
        loadParcelData();

        configureTables();

        // Add action listeners
        C_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });

        // Add refresh button listener
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshAllData();
            }
        });

        C_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomer();
            }
        });

        C_remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });

        R_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReceiver();
            }
        });

        R_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateReceiver();
            }
        });

        R_remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteReceiver();
            }
        });

        p_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addParcel();
            }
        });

        P_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateParcel();
            }
        });

        P_remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteParcel();
            }
        });

        // Add selection listeners to tables
        Ctable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displaySelectedCustomer();
            }
        });

        Rtable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displaySelectedReceiver();
            }
        });

        Ptable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displaySelectedParcel();
            }
        });
        Backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        // Set the Look and Feel to Metal
                        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                            if ("Metal".equals(info.getName())) {
                                UIManager.setLookAndFeel(info.getClassName());
                                break;
                            }
                        }
                        SwingUtilities.updateComponentTreeUI(SwingUtilities.getRoot(Backbtn)); // Update the current UI
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    // Create and show the Dashboard
                    Dashboard dashboard = new Dashboard("FAKE");
                    dashboard.setVisible(true);
                });

                // Dispose of the current window
                SwingUtilities.getWindowAncestor(Backbtn).dispose();
            }
        });
        Nextbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssignDeliveryController(enteredcode);
                SwingUtilities.getWindowAncestor(Backbtn).dispose();
            }
        });
    }

    private void loadCustomerData() {
        List<Customer> customers = customerController.getAllCustomers();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Set column names
        model.setColumnIdentifiers(new String[]{"ID", "C_ID", "Name", "Email", "Phone", "Address"});

        for (Customer customer : customers) {
            model.addRow(new Object[]{
                    customer.getId(),
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
                    customer.getAddress()
            });
        }

        Ctable.setModel(model);
        Ctable.getTableHeader().setReorderingAllowed(false);
        Ctable.getTableHeader().setResizingAllowed(true);
    }

    private void loadReceiverData() {
        List<Receiver> receivers = receiverController.getAllReceivers();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Set column names
        model.setColumnIdentifiers(new String[]{"ID", "R_ID", "Name", "Email", "Phone", "Address"});

        for (Receiver receiver : receivers) {
            model.addRow(new Object[]{
                    receiver.getId(),
                    receiver.getReceiverId(),
                    receiver.getName(),
                    receiver.getEmail(),
                    receiver.getPhoneNumber(),
                    receiver.getAddress()
            });
        }

        Rtable.setModel(model);
        Rtable.getTableHeader().setReorderingAllowed(false);
        Rtable.getTableHeader().setResizingAllowed(true);
    }

    private void loadParcelData() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Set column names
        model.setColumnIdentifiers(new String[]{"ID", "P_ID", "S_ID", "R_ID", "Content", "Type", "Created Date", "Status"});

        for (Parcel parcel : parcelController.getAllParcels()) {
            String formattedDate = "";
            if (parcel.getCreatedDate() != null) {
                formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(parcel.getCreatedDate());
            }

            model.addRow(new Object[]{
                    parcel.getId(),
                    parcel.getParcelId(),
                    parcel.getSenderId(),
                    parcel.getReceiverId(),
                    parcel.getParcelContent(),
                    parcel.getParcelType(),
                    formattedDate,
                    parcel.getStatus()
            });
        }

        Ptable.setModel(model);
        Ptable.getTableHeader().setReorderingAllowed(false);
        Ptable.getTableHeader().setResizingAllowed(true);
    }

    private void addCustomer() {
        String customerId = C_Id.getText();
        String name = C_Name.getText();
        String email = C_Email.getText();
        String phoneNumber = C_Number.getText();
        String address = C_Address.getText();
        customerIDID = customerId;



        if (customerController.addCustomer(customerId, name, email, phoneNumber, address)) {
            JOptionPane.showMessageDialog(panel1, "Customer added successfully!");
            loadCustomerData();
            clearCustomerFields();
        } else {
            JOptionPane.showMessageDialog(panel1, "Failed to add customer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCustomer() {
        String customerId = C_Id.getText();
        String name = C_Name.getText();
        String email = C_Email.getText();
        String phoneNumber = C_Number.getText();
        String address = C_Address.getText();

        if (customerController.updateCustomer(customerId, name, email, phoneNumber, address)) {
            JOptionPane.showMessageDialog(panel1, "Customer updated successfully!");
            loadCustomerData();
        } else {
            JOptionPane.showMessageDialog(panel1, "Failed to update customer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCustomer() {
        String customerId = C_Id.getText();

        if (customerController.deleteCustomer(customerId)) {
            JOptionPane.showMessageDialog(panel1, "Customer deleted successfully!");
            loadCustomerData();
            clearCustomerFields();
        } else {
            JOptionPane.showMessageDialog(panel1, "Failed to delete customer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addReceiver() {
        String receiverId = R_Id.getText();
        String name = R_Name.getText();
        String email = R_Email.getText();
        String phoneNumber = R_Number.getText();
        String address = R_Address.getText();
        senderIDID = receiverId;

        if (receiverController.addReceiver(receiverId, name, email,phoneNumber,address)) {
            JOptionPane.showMessageDialog(panel1, "Receiver added successfully!");
            loadReceiverData();
            clearReceiverFields();
        } else {
            JOptionPane.showMessageDialog(panel1, "Failed to add receiver.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateReceiver() {
        String receiverId = R_Id.getText();
        String name = R_Name.getText();
        String email = R_Email.getText();
        String phoneNumber = R_Number.getText();
        String address = R_Address.getText();

        if (receiverController.updateReceiver(receiverId, name, email,phoneNumber,address)) {
            JOptionPane.showMessageDialog(panel1, "Receiver updated successfully!");
            loadReceiverData();
        } else {
            JOptionPane.showMessageDialog(panel1, "Failed to update receiver.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteReceiver() {
        String receiverId = R_Id.getText();

        if (receiverController.deleteReceiver(receiverId)) {
            JOptionPane.showMessageDialog(panel1, "Receiver deleted successfully!");
            loadReceiverData();
            clearReceiverFields();
        } else {
            JOptionPane.showMessageDialog(panel1, "Failed to delete receiver.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addParcel() {
        try {

            String parcelId = P_Id.getText();
            String senderId = customerIDID;
            String receiverId = senderIDID;
            String content = P_Content.getText();
            String type = (String) P_Type.getSelectedItem();
            String status = (String) P_Status.getSelectedItem();
            enteredcode=P_Id.getText();

            AssignDeliveryPersonnel personnelPage = new AssignDeliveryPersonnel(enteredcode);

            if (parcelController.addParcel(parcelId, senderId, receiverId, content, type, status)) {
                JOptionPane.showMessageDialog(panel1, "Parcel added successfully!");
                loadParcelData();
                clearParcelFields();
            } else {
                JOptionPane.showMessageDialog(panel1, "Failed to add parcel.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel1, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateParcel() {
        try {
            String parcelId = P_Id.getText();
            String content = P_Content.getText();
            String type = (String) P_Type.getSelectedItem();
            String status = (String) P_Status.getSelectedItem();

            // Only pass the fields you want to update
            if (parcelController.updateParcel(parcelId, content, type, status)) {
                JOptionPane.showMessageDialog(panel1, "Parcel updated successfully!");
                loadParcelData();
            } else {
                JOptionPane.showMessageDialog(panel1, "Failed to update parcel.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel1, "Error updating parcel: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void deleteParcel() {
        String parcelId = P_Id.getText();

        if (parcelController.deleteParcel(parcelId)) {
            JOptionPane.showMessageDialog(panel1, "Parcel deleted successfully!");
            loadParcelData();
            clearParcelFields();
        } else {
            JOptionPane.showMessageDialog(panel1, "Failed to delete parcel.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displaySelectedCustomer() {
        int selectedRow = Ctable.getSelectedRow();
        if (selectedRow >= 0) {
            C_Id.setText(Ctable.getValueAt(selectedRow, 1).toString());
            C_Name.setText(Ctable.getValueAt(selectedRow, 2).toString());
            C_Email.setText(Ctable.getValueAt(selectedRow, 3).toString());
            C_Number.setText(Ctable.getValueAt(selectedRow, 4).toString());
            C_Address.setText(Ctable.getValueAt(selectedRow, 5).toString());
        }
    }

    private void displaySelectedReceiver() {
        int selectedRow = Rtable.getSelectedRow();
        if (selectedRow >= 0) {
            R_Id.setText(Rtable.getValueAt(selectedRow, 1).toString());
            R_Name.setText(Rtable.getValueAt(selectedRow, 2).toString());
            R_Email.setText(Rtable.getValueAt(selectedRow, 3).toString());
            R_Number.setText(Rtable.getValueAt(selectedRow, 4).toString());
            R_Address.setText(Rtable.getValueAt(selectedRow, 5).toString());
        }
    }

    private void displaySelectedParcel() {
        int selectedRow = Ptable.getSelectedRow();
        if (selectedRow >= 0) {
            P_Id.setText(Ptable.getValueAt(selectedRow, 1).toString());
            P_Content.setText(Ptable.getValueAt(selectedRow, 4).toString());
            P_Type.setSelectedItem(Ptable.getValueAt(selectedRow, 6).toString());

            // Set date in JSpinner
            try {
                String dateStr = Ptable.getValueAt(selectedRow, 6).toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(dateStr);
                P_DateSpinner.setValue(date);
            } catch (Exception e) {
                P_DateSpinner.setValue(new Date()); // Default to current date if parsing fails
            }
            P_Type.setSelectedItem(Ptable.getValueAt(selectedRow, 5).toString());
            P_Status.setSelectedItem(Ptable.getValueAt(selectedRow, 7).toString());
        }
    }

    private void clearCustomerFields() {
        C_Id.setText("");
        C_Name.setText("");
        C_Email.setText("");
        C_Number.setText("");
        C_Address.setText("");
    }

    private void clearReceiverFields() {
        R_Id.setText("");
        R_Name.setText("");
        R_Email.setText("");
        R_Number.setText("");
        R_Address.setText("");
    }

    private void clearParcelFields() {
        P_Id.setText("");
        P_Content.setText("");
        P_Type.setSelectedIndex(0);
        P_DateSpinner.setValue(new Date()); // Reset to current date
        P_Status.setSelectedIndex(0);
    }

    public JPanel getPanel() {
        return panel1;
    }

    /*public static void main(String[] args) {
        JFrame frame = new JFrame("Fast Track |Customer Details ,Receiver Details & Parcel Details");

        try {
            // Load the image from resources folder
            ImageIcon logoIcon = new ImageIcon(Main.class.getResource("/images/Fast Track logo.png"));

            if (logoIcon.getImage() != null) {
                // Scale image if needed
                Image scaledImage = logoIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                frame.setIconImage(scaledImage);
            }

            // Scale the image if needed (example: 128x128)
            Image image = logoIcon.getImage();
            Image scaledImage = image.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
            frame.setIconImage(scaledImage);
        } catch (Exception e) {
            System.err.println("Could not load logo: " + e.getMessage());
            // Fallback to default Java icon if logo not found
        }

        frame.setContentPane(new Shipment_view().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1400, 600);
        frame.setVisible(true);
    }*/
}