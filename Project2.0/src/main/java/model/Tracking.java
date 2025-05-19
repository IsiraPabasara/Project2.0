package model;

import java.time.LocalDateTime;

public class Tracking {
    private int id;
    private int parcelId;
    private String trackingId;
    private String status;
    private LocalDateTime statusChangedTime;
    private String ontime;

    // Constructor
    public Tracking() {}

    public Tracking(int id, int parcelId, String trackingId, String status, LocalDateTime statusChangedTime, String ontime) {
        this.id = id;
        this.parcelId = parcelId;
        this.trackingId = trackingId;
        this.status = status;
        this.statusChangedTime = statusChangedTime;
        this.ontime = ontime;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParcelId() {
        return parcelId;
    }

    public void setParcelId(int parcelId) {
        this.parcelId = parcelId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStatusChangedTime() {
        return statusChangedTime;
    }

    public void setStatusChangedTime(LocalDateTime statusChangedTime) {
        this.statusChangedTime = statusChangedTime;
    }

    public String getOntime() {
        return ontime;
    }

    public void setOntime(String ontime) {
        this.ontime = ontime;
    }
}
