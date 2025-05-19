package dao;

import model.Tracking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackingDAO {
    private final Connection conn;

    public TrackingDAO(Connection conn) {
        this.conn = conn;
    }

    // Insert tracking record
    public boolean insertTracking(Tracking tracking) {
        String sql = "INSERT INTO tracking (parcel_id, tracking_id, status, status_changed_time, ontime) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tracking.getParcelId());
            stmt.setString(2, tracking.getTrackingId());
            stmt.setString(3, tracking.getStatus());
            stmt.setTimestamp(4, Timestamp.valueOf(tracking.getStatusChangedTime()));
            stmt.setString(5, tracking.getOntime());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Search tracking by tracking_id
    public Tracking getTrackingByTrackingId(String trackingId) {
        String sql = "SELECT * FROM tracking WHERE tracking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trackingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToTracking(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Show all tracking records
    public List<Tracking> getAllTracking() {
        List<Tracking> list = new ArrayList<>();
        String sql = "SELECT * FROM tracking";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToTracking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update a tracking record
    public boolean updateTracking(Tracking tracking) {
        String sql = "UPDATE tracking SET status = ?, status_changed_time = ?, ontime = ? WHERE tracking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tracking.getStatus());
            stmt.setTimestamp(2, Timestamp.valueOf(tracking.getStatusChangedTime()));
            stmt.setString(3, tracking.getOntime());
            stmt.setString(4, tracking.getTrackingId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete tracking record
    public boolean deleteTrackingByTrackingId(String trackingId) {
        String sql = "DELETE FROM tracking WHERE tracking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trackingId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper to convert ResultSet into Tracking object
    private Tracking mapResultSetToTracking(ResultSet rs) throws SQLException {
        Tracking tracking = new Tracking();
        tracking.setId(rs.getInt("id"));
        tracking.setParcelId(rs.getInt("parcel_id"));
        tracking.setTrackingId(rs.getString("tracking_id"));
        tracking.setStatus(rs.getString("status"));
        tracking.setStatusChangedTime(rs.getTimestamp("status_changed_time").toLocalDateTime());
        tracking.setOntime(rs.getString("ontime"));
        return tracking;
    }
}
