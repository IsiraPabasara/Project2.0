package controller;

import model.Tracking;
import service.TrackingService;
import view.TrackingView;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.List;

public class TrackingController {
    private final TrackingView view;
    private final TrackingService service;

    public TrackingController(TrackingView view, TrackingService service) {
        this.view = view;
        this.service = service;

        initController();
    }

    private void initController() {
        view.getBtnSearch().addActionListener(e -> searchTracking());
        view.getBtnShowAll().addActionListener(e -> showAll());
        view.getBtnUpdate().addActionListener(e -> updateTracking());
        view.getBtnDelete().addActionListener(e -> deleteTracking());
        view.getBtnRefresh().addActionListener(e -> refreshTable());
        view.getBtnBack().addActionListener(e -> view.dispose()); // Close window
    }

    private void searchTracking() {
        String trackingId = view.getTxtSearch().getText().trim();
        if (trackingId.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a tracking ID to search.");
            return;
        }

        Tracking t = service.searchTracking(trackingId);
        if (t != null) {
            view.getTableModel().setRowCount(0);
            view.getTableModel().addRow(new Object[]{
                    t.getId(),
                    t.getParcelId(),
                    t.getTrackingId(),
                    t.getStatus(),
                    t.getStatusChangedTime(),
                    t.getOntime()
            });
        } else {
            JOptionPane.showMessageDialog(view, "Tracking record not found.");
        }
    }

    private void showAll() {
        List<Tracking> list = service.getAllTrackings();
        view.populateTable(list);
    }

    private void updateTracking() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Please select a record to update.");
            return;
        }

        String trackingId = view.getTableModel().getValueAt(selectedRow, 2).toString();
        String newStatus = view.getCbStatus().getSelectedItem().toString();
        String newOntime = view.getCbOntime().getSelectedItem().toString();

        Tracking t = service.searchTracking(trackingId);
        if (t != null) {
            t.setStatus(newStatus);
            t.setOntime(newOntime);
            t.setStatusChangedTime(LocalDateTime.now());

            if (service.updateTracking(t)) {
                JOptionPane.showMessageDialog(view, "Tracking updated.");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(view, "Update failed.");
            }
        }
    }

    private void deleteTracking() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Please select a record to delete.");
            return;
        }

        String trackingId = view.getTableModel().getValueAt(selectedRow, 2).toString();
        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete tracking ID: " + trackingId + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (service.deleteTracking(trackingId)) {
                JOptionPane.showMessageDialog(view, "Tracking deleted.");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(view, "Deletion failed.");
            }
        }
    }

    private void refreshTable() {
        showAll();
    }

    public void showView() {
        view.setVisible(true);
        showAll(); // Load data initially
    }
}
