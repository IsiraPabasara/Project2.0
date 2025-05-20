package model;

import java.sql.Timestamp;

public class Parcel {
    private int id;
    private String parcelId;
    private String senderId;
    private String receiverId;
    private String parcelContent;
    private String parcelType;
    private Timestamp createdDate;
    private String status;


    public Parcel(String parcelId, String senderId, String receiverId, String parcelContent,
                  String parcelType, String status) {
        this.parcelId = parcelId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.parcelContent = parcelContent;
        this.parcelType = parcelType;
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParcelId() {
        return parcelId;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getParcelContent() {
        return parcelContent;
    }

    public void setParcelContent(String parcelContent) {
        this.parcelContent = parcelContent;
    }

    public String getParcelType() {
        return parcelType;
    }

    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}