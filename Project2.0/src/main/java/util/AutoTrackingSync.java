package util;

import dao.TrackingDAO;
import model.Tracking;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class AutoTrackingSync extends Thread {
    private final Connection connection;
    private final TrackingDAO trackingDAO;
    private final Set<Integer> trackedParcelIds = new HashSet<>();

    public AutoTrackingSync(Connection connection, TrackingDAO trackingDAO) {
        this.connection = connection;
        this.trackingDAO = trackingDAO;
    }

    @Override
    public void run() {
        System.out.println("✅ AutoTrackingSync started...");

        while (true) {
            try {
                // Query parcels that do not have a tracking entry yet
                PreparedStatement ps = connection.prepareStatement(
                        "SELECT id FROM parcel WHERE id NOT IN (SELECT parcel_id FROM tracking)");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int parcelId = rs.getInt("id");

                    // Skip if already synced this session
                    if (trackedParcelIds.contains(parcelId)) continue;

                    // Create and insert tracking entry
                    Tracking tracking = new Tracking();
                    tracking.setParcelId(parcelId);
                    tracking.setTrackingId(generateTrackingId(parcelId));
                    tracking.setStatus("processing");
                    tracking.setStatusChangedTime(LocalDateTime.now());
                    tracking.setOntime("on time");

                    if (trackingDAO.insertTracking(tracking)) {
                        System.out.println("✅ Tracking created for parcel ID: " + parcelId);
                        trackedParcelIds.add(parcelId);
                    }
                }

                Thread.sleep(3000); // Poll every 3 seconds

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String generateTrackingId(int parcelId) {
        return String.format("TRK-%05d", parcelId); // e.g., TRK-00001
    }
}
