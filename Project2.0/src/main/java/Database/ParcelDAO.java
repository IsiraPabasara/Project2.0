package Database;

import model.Parcel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParcelDAO {
    // Create a new parcel
    public boolean addParcel(Parcel parcel) {
        String sql = "INSERT INTO parcel (parcel_id, sender_id, receiver_id, parcel_content, parcel_type, status) VALUES (?, ?, ?, ?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, parcel.getParcelId());
            stmt.setString(2, parcel.getSenderId());
            stmt.setString(3, parcel.getReceiverId());
            stmt.setString(4, parcel.getParcelContent());
            stmt.setString(5, parcel.getParcelType());
            stmt.setString(6, parcel.getStatus());



            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all parcels
    public List<Parcel> getAllParcels() {
        List<Parcel> parcels = new ArrayList<>();
        String sql = "SELECT * FROM parcel";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Parcel parcel = new Parcel("parcel_id","sender_id","receiver_id","parcel_content","parcel_type","status");
                parcel.setId(rs.getInt("id"));
                parcel.setParcelId(rs.getString("parcel_id"));
                parcel.setSenderId(rs.getString("sender_id"));
                parcel.setReceiverId(rs.getString("receiver_id"));
                parcel.setParcelContent(rs.getString("parcel_content"));
                parcel.setParcelType(rs.getString("parcel_type"));
                parcel.setCreatedDate(rs.getTimestamp("created_date"));
                parcel.setStatus(rs.getString("status"));

                parcels.add(parcel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parcels;
    }


    // Update a parcel - modified to keep sender/receiver IDs unchanged
    public boolean updateParcel(Parcel parcel) {
        String sql = "UPDATE parcel SET parcel_content = ?, parcel_type = ?, status = ? WHERE parcel_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, parcel.getParcelContent());
            stmt.setString(2, parcel.getParcelType());
            stmt.setString(3, parcel.getStatus());
            stmt.setString(4, parcel.getParcelId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a parcel
    public boolean deleteParcel(String parcelId) {
        String sql = "DELETE FROM parcel WHERE parcel_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, parcelId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}