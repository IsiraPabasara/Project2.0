package view;

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




}
