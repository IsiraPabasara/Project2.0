package controller;

import model.DeliveryPersonnel;
import model.DeliveryPersonnelDAO;
import view.DeliveryPersonnelView;

import javax.swing.*;
import java.util.List;

public class DeliveryPersonnelController {

    private DeliveryPersonnelDAO dao;
    private DeliveryPersonnelView view;

    // Main constructor
    public DeliveryPersonnelController(DeliveryPersonnelDAO dao, DeliveryPersonnelView view) {
        this.dao = dao != null ? dao : new DeliveryPersonnelDAO(); // Fallback in case null is passed
        this.view = view;
        if (this.view != null) {
            this.view.setController(this); // Link view with controller
        }
        refreshPersonnelList();
    }

    // Convenience constructor (if view or DAO are initialized later)
    public DeliveryPersonnelController() {
        this.dao = new DeliveryPersonnelDAO();
    }

    public void addPersonnel(DeliveryPersonnel p) {
        try {
            // Generate auto-ID only for new personnel
            p.setDelivery_personnel_id(dao.generateNextID());

            // Validate uniqueness
            if (dao.emailExists(p.getEmail())) {
                JOptionPane.showMessageDialog(view, "Email already exists!");
                return;
            }

            dao.addPersonnel(p);
            refreshPersonnelList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Failed to add personnel: " + e.getMessage());
        }
    }

    public void updatePersonnel(DeliveryPersonnel p) {
        try {
            // Check if email already exists for another ID
            if (dao.emailExistsForOtherId(p.getEmail(), p.getDelivery_personnel_id())) {
                JOptionPane.showMessageDialog(view, "Email already exists for another personnel!");
                return;
            }

            dao.updatePersonnel(p);
            refreshPersonnelList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Failed to update personnel: " + e.getMessage());
        }
    }

    public void deletePersonnel(String delivery_personnel_id) {
        try {
            dao.deletePersonnel(delivery_personnel_id);
            refreshPersonnelList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Failed to delete personnel: " + e.getMessage());
        }
    }

    public void refreshPersonnelList() {
        try {
            List<DeliveryPersonnel> personnelList = dao.getAllPersonnel();
            if (view != null) {
                view.displayPersonnel(personnelList);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Failed to load personnel list: " + e.getMessage());
        }
    }

    // Optional: For view or testing
    public void setView(DeliveryPersonnelView view) {
        this.view = view;
        if (this.view != null) {
            this.view.setController(this);
            refreshPersonnelList();
        }
    }

    public DeliveryPersonnelDAO getDAO() {
        return this.dao;
    }

    public DeliveryPersonnel searchPersonnelById(String id) {
        return dao.getPersonnelById(id);
    }


}
