package main;
import controller.AuthController;
import view.LoginView;

public class Main {
    public static void main(String[] args) {
        AuthController controller = new AuthController(); // Replace with actual implementation
        new LoginView(controller).setVisible(true);
    }
}
