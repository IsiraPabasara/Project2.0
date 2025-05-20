package model;

// model.DeliveryPersonnel.java
public class AssignDeliveryPersonnel {
    private int id;
    private String deliveryPersonnelId;
    private String email;
    private String name;
    private String phoneNumber;
    private String availabilityStatus;

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for deliveryPersonnelId
    public String getDeliveryPersonnelId() {
        return deliveryPersonnelId;
    }

    public void setDeliveryPersonnelId(String deliveryPersonnelId) {
        this.deliveryPersonnelId = deliveryPersonnelId;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter for availabilityStatus
    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}
