package service;

import dao.TrackingDAO;
import model.Tracking;

import java.time.LocalDateTime;
import java.util.List;

public class TrackingService {
    private final TrackingDAO trackingDAO;

    public TrackingService(TrackingDAO trackingDAO) {
        this.trackingDAO = trackingDAO;
    }

    // Auto-generate tracking ID like "TRK-00001"
    public String generateTrackingId(int nextId) {
        return String.format("TRK-%05d", nextId);
    }

    // Add tracking record
    public boolean addTracking(int parcelId) {
        try {
            List<Tracking> all = trackingDAO.getAllTracking();
            int nextId = all.size() + 1;
            String trackingId = generateTrackingId(nextId);

            Tracking tracking = new Tracking();
            tracking.setParcelId(parcelId);
            tracking.setTrackingId(trackingId);
            tracking.setStatus("processing");
            tracking.setStatusChangedTime(LocalDateTime.now());
            tracking.setOntime("on time");

            return trackingDAO.insertTracking(tracking);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Search by tracking ID
    public Tracking searchTracking(String trackingId) {
        return trackingDAO.getTrackingByTrackingId(trackingId);
    }

    // List all records
    public List<Tracking> getAllTrackings() {
        return trackingDAO.getAllTracking();
    }

    // Update tracking info
    public boolean updateTracking(Tracking tracking) {
        return trackingDAO.updateTracking(tracking);
    }

    // Delete tracking by ID
    public boolean deleteTracking(String trackingId) {
        return trackingDAO.deleteTrackingByTrackingId(trackingId);
    }
}
