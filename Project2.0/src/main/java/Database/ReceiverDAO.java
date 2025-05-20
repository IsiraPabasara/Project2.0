package Database;

import model.Receiver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiverDAO {
    // Create a new receiver
    public boolean addReceiver(Receiver receiver) {
        String sql = "INSERT INTO receiver (receiver_id, name, email, phone_number, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, receiver.getReceiverId());
            stmt.setString(2, receiver.getName());
            stmt.setString(3, receiver.getEmail());
            stmt.setString(4, receiver.getPhoneNumber());
            stmt.setString(5, receiver.getAddress());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all receivers
    public List<Receiver> getAllReceivers() {
        List<Receiver> receivers = new ArrayList<>();
        String sql = "SELECT * FROM receiver";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Receiver receiver = new Receiver();
                receiver.setId(rs.getInt("id"));
                receiver.setReceiverId(rs.getString("receiver_id"));
                receiver.setName(rs.getString("name"));
                receiver.setEmail(rs.getString("email"));
                receiver.setPhoneNumber(rs.getString("phone_number"));
                receiver.setAddress(rs.getString("address"));

                receivers.add(receiver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return receivers;
    }

    // Update a receiver
    public boolean updateReceiver(Receiver receiver) {
        String sql = "UPDATE receiver SET name = ?, email = ?, phone_number = ?, address = ? WHERE receiver_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, receiver.getName());
            stmt.setString(2, receiver.getEmail());
            stmt.setString(3, receiver.getPhoneNumber());
            stmt.setString(4, receiver.getAddress());
            stmt.setString(5, receiver.getReceiverId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a receiver
    public boolean deleteReceiver(String receiverId) {
        String sql = "DELETE FROM receiver WHERE receiver_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, receiverId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}