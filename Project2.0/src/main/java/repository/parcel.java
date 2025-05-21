package repository;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "parcel")
public class parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parcel_id", nullable = false, unique = true, length = 10)
    private String parcelId;

    @Column(name = "sender_id", nullable = false, length = 10)
    private String senderId;

    @Column(name = "receiver_id", nullable = false, length = 10)
    private String receiverId;

    @Column(name = "parcel_content", nullable = false, length = 100)
    private String parcelContent;

    @Column(name = "parcel_type", nullable = false, length = 20)
    private String parcelType;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    // Constructors
    public parcel() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getParcelId() {
        return parcelId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getParcelContent() {
        return parcelContent;
    }

    public String getParcelType() {
        return parcelType;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public void setParcelContent(String parcelContent) {
        this.parcelContent = parcelContent;
    }

    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
