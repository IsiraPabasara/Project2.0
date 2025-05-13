package model;

public class DeliveryPersonnel {

    private String delivery_personnel_id;
    private String email;
    private String name;
    private String phone_number;
    private String availability_status;

    public String getDelivery_personnel_id() {
        return delivery_personnel_id;
    }

    public void setDelivery_personnel_id(String delivery_personnel_id) {
        this.delivery_personnel_id = delivery_personnel_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAvailability_status() {
        return availability_status;
    }

    public void setAvailability_status(String availability_status) {
        this.availability_status = availability_status;
    }
}
