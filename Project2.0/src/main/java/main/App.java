package main;

import controller.LoginController;
import view.Login;

public class App {
    public static void main(String[] args) {
        Login loginForm = new Login();
        new LoginController(loginForm);
        loginForm.setVisible(true);
    }
}
