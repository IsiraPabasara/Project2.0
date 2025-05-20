package view;

import model.Tracking;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TrackingView extends JFrame {

    // Components
    private JTextField txtSearch;
    private JComboBox<String> cbStatus, cbOntime;
    private JButton btnSearch, btnShowAll, btnUpdate, btnDelete, btnRefresh, btnBack;
    private JTable table;
    private DefaultTableModel tableModel;

    public TrackingView() {
        setTitle("🚚 Fasttrack Logistics | Order Tracking");
        setSize(950, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon appIcon = new ImageIcon("D:/GitHub/Images/1.jpg");
        setIconImage(appIcon.getImage());

        Color backgroundColor = new Color(245, 248, 250);
        Color panelColor = new Color(230, 238, 245);
        Color buttonColor = new Color(60, 120, 180);
        Color buttonTextColor = Color.WHITE;

        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 13);

        setLayout(new BorderLayout());
        getContentPane().setBackground(backgroundColor);

        // Top Panel - Search + Filters
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        topPanel.setBackground(panelColor);
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblSearch = new JLabel("Tracking ID:");
        lblSearch.setFont(labelFont);

        txtSearch = new JTextField(15);
        btnSearch = new JButton("Search");
        btnShowAll = new JButton("Show All");

        styleButton(btnSearch, buttonColor, buttonTextColor, buttonFont);
        styleButton(btnShowAll, buttonColor, buttonTextColor, buttonFont);

        topPanel.add(lblSearch);
        topPanel.add(txtSearch);
        topPanel.add(btnSearch);
        topPanel.add(btnShowAll);

        // Center Panel - Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Parcel ID", "Tracking ID", "Status", "Status Time", "Ontime"}, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        // Bottom Panel - Actions
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        bottomPanel.setBackground(panelColor);
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(labelFont);
        cbStatus = new JComboBox<>(new String[]{"processing", "packed", "shipped", "at the hub", "out for delivery", "delivered"});

        JLabel lblOntime = new JLabel("Ontime:");
        lblOntime.setFont(labelFont);
        cbOntime = new JComboBox<>(new String[]{"on time", "delayed"});

        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");
        btnBack = new JButton("Previous Page");

        for (JButton btn : new JButton[]{btnUpdate, btnDelete, btnRefresh, btnBack}) {
            styleButton(btn, buttonColor, buttonTextColor, buttonFont);
        }

        bottomPanel.add(lblStatus);
        bottomPanel.add(cbStatus);
        bottomPanel.add(lblOntime);
        bottomPanel.add(cbOntime);
        bottomPanel.add(btnUpdate);
        bottomPanel.add(btnDelete);
        bottomPanel.add(btnRefresh);
        bottomPanel.add(btnBack);

        // Add Panels
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button, Color bg, Color fg, Font font) {
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFocusPainted(false);
        button.setFont(font);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    // Public getters
    public JTextField getTxtSearch() { return txtSearch; }
    public JButton getBtnSearch() { return btnSearch; }
    public JButton getBtnShowAll() { return btnShowAll; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnRefresh() { return btnRefresh; }
    public JButton getBtnBack() { return btnBack; }
    public JComboBox<String> getCbStatus() { return cbStatus; }
    public JComboBox<String> getCbOntime() { return cbOntime; }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public void populateTable(List<Tracking> list) {
        tableModel.setRowCount(0); // Clear existing
        for (Tracking t : list) {
            tableModel.addRow(new Object[]{
                    t.getId(),
                    t.getParcelId(),
                    t.getTrackingId(),
                    t.getStatus(),
                    t.getStatusChangedTime(),
                    t.getOntime()
            });
        }
    }
}
