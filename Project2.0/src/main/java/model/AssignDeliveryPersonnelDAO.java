package model;// model.DeliveryPersonnelDAO.java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignDeliveryPersonnelDAO {
    private Connection connection;

    public AssignDeliveryPersonnelDAO(Connection connection) {
        this.connection = connection;
    }

    public List<AssignDeliveryPersonnel> getAvailablePersonnel() throws SQLException {
        List<AssignDeliveryPersonnel> list = new ArrayList<>();
        String query = "SELECT * FROM delivery_personnel WHERE availability_status = 'Available'";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            AssignDeliveryPersonnel dp = new AssignDeliveryPersonnel();
            dp.setId(rs.getInt("id"));
            dp.setDeliveryPersonnelId(rs.getString("delivery_personnel_id"));
            dp.setName(rs.getString("name"));
            dp.setEmail(rs.getString("email"));
            dp.setPhoneNumber(rs.getString("phone_number"));
            dp.setAvailabilityStatus(rs.getString("availability_status"));
            list.add(dp);
        }
        return list;
    }

    public void updateAvailability(String deliveryPersonnelId, String status) throws SQLException {
        String query = "UPDATE delivery_personnel SET availability_status = ? WHERE delivery_personnel_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, status);
        ps.setString(2, deliveryPersonnelId);
        ps.executeUpdate();
    }
}
