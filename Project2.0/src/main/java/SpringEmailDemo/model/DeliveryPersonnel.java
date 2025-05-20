package SpringEmailDemo.model;

import jakarta.persistence.*;
@Entity
@Table(name = "delivery_personnel")
public class DeliveryPersonnel {

    @Id
    private String id;  // String ID like "D001"

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 191)
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    // ... other getters and setters
}
