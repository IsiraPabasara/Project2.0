package controller;// controller.AssignDeliveryController.java

import model.AssignDeliveryPersonnel;
import model.AssignDeliveryPersonnelDAO;
import view.AssignDeliveryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class AssignDeliveryController {
    private AssignDeliveryView view;
    private AssignDeliveryPersonnelDAO dao;

    public AssignDeliveryController() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2.0", "root", "");
            dao = new AssignDeliveryPersonnelDAO(conn);

            List<AssignDeliveryPersonnel> availableList = dao.getAvailablePersonnel();
            List<String> ids = availableList.stream().map(AssignDeliveryPersonnel::getDeliveryPersonnelId).toList();

            view = new AssignDeliveryView(
                    ids,
                    new NextButtonListener(),
                    new BackButtonListener()
            );
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class NextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String selectedId = view.getSelectedPersonnelId();
                dao.updateAvailability(selectedId, "Unavailable");
                JOptionPane.showMessageDialog(view, "Personnel assigned and marked as unavailable.");
                // Next step: proceed to package selection or close
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error assigning personnel.");
            }
        }
    }

    class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            // Optionally return to a previous view
        }
    }
}
