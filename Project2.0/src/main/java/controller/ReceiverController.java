package controller;

import Database.ReceiverDAO;
import model.Receiver;

import java.util.List;

public class ReceiverController {
    private ReceiverDAO receiverDAO;

    public ReceiverController() {
        this.receiverDAO = new ReceiverDAO();
    }

    public boolean addReceiver(String receiverId, String name, String email, String phoneNumber, String address) {
        Receiver receiver = new Receiver(receiverId, name, email, phoneNumber, address);
        return receiverDAO.addReceiver(receiver);
    }

    public List<Receiver> getAllReceivers() {
        return receiverDAO.getAllReceivers();
    }

    public boolean updateReceiver(String receiverId, String name, String email, String phoneNumber, String address) {
        Receiver receiver = new Receiver(receiverId, name, email, phoneNumber, address);
        return receiverDAO.updateReceiver(receiver);
    }

    public boolean deleteReceiver(String receiverId) {
        return receiverDAO.deleteReceiver(receiverId);
    }
}