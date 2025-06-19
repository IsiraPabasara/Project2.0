package model;

import util.DBConnectionManageDeliveryPersonnel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryPersonnelDAO {

    public void addPersonnel(DeliveryPersonnel p) throws SQLException {
        String sql = "INSERT INTO delivery_personnel (delivery_personnel_id, email, name, phone_number, availability_status) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = DBConnectionManageDeliveryPersonnel.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, p.getDelivery_personnel_id());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getName());
            stmt.setString(4, p.getPhone_number());
            stmt.setString(5, p.getAvailability_status());

            stmt.executeUpdate();
        }

    }


    public List<DeliveryPersonnel> getAllPersonnel() {

        List<DeliveryPersonnel> list = new ArrayList<>();
        String sql = "SELECT * FROM delivery_personnel ORDER BY delivery_personnel_id ASC";

        try (
                Connection conn = DBConnectionManageDeliveryPersonnel.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                DeliveryPersonnel p = new DeliveryPersonnel();
                p.setDelivery_personnel_id(rs.getString("delivery_personnel_id"));
                p.setEmail(rs.getString("email"));
                p.setName(rs.getString("name"));
                p.setPhone_number(rs.getString("phone_number"));
                p.setAvailability_status(rs.getString("availability_status"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public void updatePersonnel(DeliveryPersonnel p) throws SQLException {
        String sql = "UPDATE delivery_personnel SET email = ?, name = ?, phone_number = ?, availability_status = ? WHERE delivery_personnel_id = ?";

        try (
                Connection conn = DBConnectionManageDeliveryPersonnel.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, p.getEmail());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getPhone_number());
            stmt.setString(4, p.getAvailability_status());
            stmt.setString(5, p.getDelivery_personnel_id());

            stmt.executeUpdate();
        }
    }

    public void deletePersonnel(String delivery_personnel_id) {
        String sql = "DELETE FROM delivery_personnel WHERE delivery_personnel_id = ?";

        try (
                Connection conn = DBConnectionManageDeliveryPersonnel.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, delivery_personnel_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String generateNextID() {
        String prefix = "D";
        int max = 0;

        try (
                Connection conn = DBConnectionManageDeliveryPersonnel.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT delivery_personnel_id FROM delivery_personnel");
        ) {
            while (rs.next()) {
                String id = rs.getString("delivery_personnel_id");
                if (id.startsWith(prefix)) {
                    try {
                        int num = Integer.parseInt(id.substring(prefix.length()));
                        max = Math.max(max, num);
                    } catch (NumberFormatException ignored) {
                        System.out.println("Error");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prefix + String.format("%03d", max + 1);
    }

    public boolean emailExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM delivery_personnel WHERE email = ?";
        try (
                Connection conn = DBConnectionManageDeliveryPersonnel.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    public boolean emailExistsForOtherId(String email, String id) throws SQLException {
        String query = "SELECT COUNT(*) FROM delivery_personnel WHERE email = ? AND delivery_personnel_id != ?";
        try (
                Connection conn = DBConnectionManageDeliveryPersonnel.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, email);
            stmt.setString(2, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    public DeliveryPersonnel getPersonnelById(String id) {
        String query = "SELECT * FROM delivery_personnel WHERE delivery_personnel_id = ?";
        try (
                Connection conn = DBConnectionManageDeliveryPersonnel.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                DeliveryPersonnel p = new DeliveryPersonnel();
                p.setDelivery_personnel_id(rs.getString("delivery_personnel_id"));
                p.setEmail(rs.getString("email"));
                p.setName(rs.getString("name"));
                p.setPhone_number(rs.getString("phone_number"));
                p.setAvailability_status(rs.getString("availability_status"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
