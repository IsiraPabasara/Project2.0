package view;

import controller.DeliveryPersonnelController;
import model.DeliveryPersonnelDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    private JButton sendparcel;
    private JButton managedeliverypersonnel;
    private JButton viewreport;
    private JButton trackshipment;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Dashboard()  {
        sendparcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


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

            }
        });

        trackshipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dashboard");
            Dashboard dashboard = new Dashboard();
            frame.setContentPane(dashboard.getMainPanel()); // set the designed panel
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400); // adjust as needed
            frame.setLocationRelativeTo(null); // center the window
            frame.setVisible(true);
        });

    }*/


}
