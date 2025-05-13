package view;

import model.User;

import javax.swing.*;

public class HomeView extends JFrame {
    public HomeView(User user) {
        setTitle("Home - " + user.getRole());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername() + "! (Role: " + user.getRole() + ")");
        add(welcomeLabel);

        setLocationRelativeTo(null);
        setVisible(true);


    }
}
