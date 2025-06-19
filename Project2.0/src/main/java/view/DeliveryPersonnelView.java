package view;

import controller.DeliveryPersonnelController;
import model.DeliveryPersonnel;
import model.DeliveryPersonnelDAO;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DeliveryPersonnelView extends JFrame {

    private DeliveryPersonnelController controller;
    private DeliveryPersonnelDAO DAO;
    private JTable table;
    private DefaultTableModel tableModel;

    public DeliveryPersonnelView() {

        setTitle("🚚 Fasttrack Logistics | Manage Delivery Personnel");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon appIcon = new ImageIcon("D:/GitHub/Images/1.jpg");
        setIconImage(appIcon.getImage());

        // Create top search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JTextField searchField = new JTextField(18);
        JButton searchButton = createStyledButton("Search", "D:/GitHub/Images/search.png", new Color(48, 48, 48));
        JButton resetButton = createStyledButton("Reset", "D:/GitHub/Images/reset.png", new Color(48, 48, 48));
        searchButton.setPreferredSize(new Dimension(120, 30));
        resetButton.setPreferredSize(new Dimension(120, 30));
        searchField.setPreferredSize(new Dimension(200, 30));

        searchPanel.add(new JLabel("Search by ID:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(resetButton);

        add(searchPanel, BorderLayout.NORTH);


        tableModel = new DefaultTableModel(new String[]{"Delivery Personnel ID", "Email", "Name", "Phone Number", "Availability Status"}, 0);
        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setFillsViewportHeight(true);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 35));
        table.setGridColor(Color.lightGray);
        table.setShowGrid(true);
        table.setSelectionBackground(new Color(200, 220, 255));

        final int[] hoveredRow = {-1};
        table.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                hoveredRow[0] = table.rowAtPoint(e.getPoint());
                table.repaint();
            }
        });

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (row == hoveredRow[0] && !isSelected) {
                    c.setBackground(new Color(232, 245, 255));
                } else if (isSelected) {
                    c.setBackground(new Color(180, 205, 255));
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(0, 10, 0, 10));
        add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        Font btnFont = new Font("Segoe UI", Font.BOLD, 13);

        JButton addButton = createStyledButton("Add Personnel", "D:/GitHub/Images/plus.png", new Color(48, 48, 48));
        JButton updateButton = createStyledButton("Update Personnel", "D:/GitHub/Images/edit.png", new Color(48, 48, 48));
        JButton deleteButton = createStyledButton("Delete Personnel", "D:/GitHub/Images/delete.png", new Color(48, 48, 48));
        JButton refreshButton = createStyledButton("Refresh", "D:/GitHub/Images/refresh.png", new Color(48, 48, 48));
        JButton backButton = createStyledButton("Back", "D:/GitHub/Images/back.png", new Color(48, 48, 48));

        JButton[] buttons = {addButton, updateButton, deleteButton, refreshButton, backButton};
        for (JButton btn : buttons) {
            btn.setFont(btnFont);
            btn.setPreferredSize(new Dimension(180, 40));
            controlPanel.add(btn);
        }

        add(controlPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            String id = searchField.getText().trim();
            if (!id.isEmpty()) {
                DeliveryPersonnel p = controller.searchPersonnelById(id);
                if (p != null) {
                    displayPersonnel(List.of(p));
                } else {
                    JOptionPane.showMessageDialog(this, "No personnel found with ID: " + id);
                    searchField.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a Personnel ID to search.");
                searchField.setText("");
            }
        });

        resetButton.addActionListener(e->{
            controller.refreshPersonnelList();
            searchField.setText("");
        });


        backButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                String adminId = "Fake User";
                Dashboard dashboard = new Dashboard(adminId);
                dashboard.setVisible(true);
            });
            SwingUtilities.getWindowAncestor(backButton).dispose();
        });

        addButton.addActionListener(e -> {
            try {
                openPersonnelDialog("Add Personnel", null);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                DeliveryPersonnel p = new DeliveryPersonnel();
                p.setDelivery_personnel_id(tableModel.getValueAt(selectedRow, 0).toString());
                p.setEmail(tableModel.getValueAt(selectedRow, 1).toString());
                p.setName(tableModel.getValueAt(selectedRow, 2).toString());
                p.setPhone_number(tableModel.getValueAt(selectedRow, 3).toString());
                p.setAvailability_status(tableModel.getValueAt(selectedRow, 4).toString());
                try {
                    openPersonnelDialog("Update Personnel", p);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a personnel to update.");
            }
        });

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

        refreshButton.addActionListener(e -> controller.refreshPersonnelList());
    }

    private JButton createStyledButton(String text, String iconPath, Color bgColor) {
        JButton button = new JButton(text);
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.err.println("⚠️ Icon missing: " + iconPath);
        }

        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setIconTextGap(10);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(true);
        return button;
    }

    private void openPersonnelDialog(String title, DeliveryPersonnel existingPersonnel) throws SQLException {
        JTextField idField = new JTextField(18);
        JTextField emailField = new JTextField(18);
        JTextField nameField = new JTextField(18);
        JTextField phoneField = new JTextField(18);
        JTextField statusField = new JTextField(18);

        idField.setPreferredSize(new Dimension(200, 28));
        idField.setEditable(false);

        if (existingPersonnel != null) {
            idField.setText(existingPersonnel.getDelivery_personnel_id());
            emailField.setText(existingPersonnel.getEmail());
            nameField.setText(existingPersonnel.getName());
            phoneField.setText(existingPersonnel.getPhone_number());
            statusField.setText(existingPersonnel.getAvailability_status());
        } else {
            String nextId = DAO.generateNextID();
            idField.setText(nextId != null ? nextId : "Auto-generated");
        }

        JPanel panel = new JPanel(new GridLayout(0, 2, 0, 15));
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
            String id = idField.getText();
            String email = emailField.getText().trim();
            if(email.isEmpty()){
                JOptionPane.showMessageDialog(this, "Cannot add the user. Please enter a Email","Input Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (existingPersonnel == null) {
                if (DAO.emailExists(email)) {
                    JOptionPane.showMessageDialog(this, "Email already exists. Please use a different email.", "Duplicate Email", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                if (!existingPersonnel.getEmail().equalsIgnoreCase(email) && DAO.emailExistsForOtherId(email, id)) {
                    JOptionPane.showMessageDialog(this, "This email is already used by another personnel.", "Duplicate Email", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            DeliveryPersonnel p = new DeliveryPersonnel();
            p.setDelivery_personnel_id(id);
            p.setEmail(email);
            p.setName(nameField.getText().trim());
            p.setPhone_number(phoneField.getText().trim());
            p.setAvailability_status(statusField.getText().trim());

            if (existingPersonnel == null) {
                controller.addPersonnel(p);
            } else {
                controller.updatePersonnel(p);
            }
        }
    }

    public void setController(DeliveryPersonnelController controller) {
        this.controller = controller;
        this.DAO = controller.getDAO();
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

    static class RoundedBorder extends AbstractBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.DARK_GRAY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x + 1, y + 1, width - 3, height - 3, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(8, 12, 8, 12);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(8, 12, 8, 12);
            return insets;
        }
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dashboard");
            Dashboard dashboard = new Dashboard();
            frame.setContentPane(dashboard.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }*/
}
