package view;

import controller.ReportController;
import model.ReportEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Month;
import java.util.List;

public class ReportView extends JFrame {

    private JLabel footerLabel;
    private JComboBox<String> monthSelector;
    private DefaultTableModel model;

    public ReportView() {
        // Set Nimbus Look & Feel for modern UI
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Nimbus look and feel not available, using default.");
        }

        setTitle("Monthly Parcel Report");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Header panel with title and month selector
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(15, 189, 201));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80));

        JLabel titleLabel = new JLabel("Monthly Parcel Report", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.DARK_GRAY);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Month selector
        monthSelector = new JComboBox<>();
        for (Month month : Month.values()) {
            monthSelector.addItem(month.toString());
        }
        monthSelector.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        monthSelector.addActionListener(e -> reloadData());

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(15, 80, 85));
        leftPanel.add(new JLabel("Select Month: ")).setForeground(Color.WHITE);
        leftPanel.add(monthSelector);
        headerPanel.add(leftPanel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        // Column names
        String[] columnNames = {
                "Parcel ID", "Parcel Type", "Sender Email", "Sender Name",
                "Receiver Email", "Receiver Name", "Delivery Personnel", "Schedule Date"
        };

        // Table setup
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setIntercellSpacing(new Dimension(1, 1));
        table.setSelectionBackground(new Color(20, 110, 180));
        table.setSelectionForeground(Color.WHITE);
        table.setBackground(new Color(122, 137, 138));
        table.setForeground(Color.WHITE);

        // Header styling
        JTableHeader header = table.getTableHeader();
        Color originalHeaderColor = new Color(25, 82, 103);
        header.setBackground(originalHeaderColor);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        // Header hover effect
        header.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                header.setForeground(Color.BLUE);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                header.setForeground(Color.BLACK);
            }
        });

        // Center align cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setForeground(Color.WHITE);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(25, 82, 103));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // Footer panel
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(26, 191, 176));

        // Back button
        JButton backButton = new JButton("Go Back");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setFocusPainted(false);
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            dispose(); // or redirect to the relevant page
        });
        footerPanel.add(backButton, BorderLayout.WEST);

        backButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                try {
                    // Set the Look and Feel to Metal
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Metal".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                    SwingUtilities.updateComponentTreeUI(SwingUtilities.getRoot(backButton)); // Update the current UI
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Create and show the Dashboard
                Dashboard dashboard = new Dashboard("FAKE");
                dashboard.setVisible(true);
            });

            // Dispose of the current window
            SwingUtilities.getWindowAncestor(backButton).dispose();
        });




        // Total entries label
        footerLabel = new JLabel("Total Entries: 0");
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footerLabel.setForeground(Color.LIGHT_GRAY);
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(new Color(10, 25, 49));
        rightPanel.add(footerLabel);
        footerPanel.add(rightPanel, BorderLayout.EAST);

        add(footerPanel, BorderLayout.SOUTH);

        // Fetch and populate data
        loadData();

        setVisible(true);
    }

    private void loadData() {
        model.setRowCount(0);
        ReportController controller = new ReportController();
        String selectedMonth = (String) monthSelector.getSelectedItem();
        List<ReportEntry> reportData = controller.fetchReportByMonth(selectedMonth);

        for (ReportEntry entry : reportData) {
            model.addRow(new Object[]{
                    entry.getParcelId(),
                    entry.getParcelType(),
                    entry.getSenderEmail(),
                    entry.getSenderName(),
                    entry.getReceiverEmail(),
                    entry.getReceiverName(),
                    entry.getDeliveryPersonnel(),
                    entry.getScheduleDate()
            });
        }

        footerLabel.setText("Total Entries: " + model.getRowCount());
    }

    private void reloadData() {
        loadData();
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(ReportView::new);
    }*/

}
