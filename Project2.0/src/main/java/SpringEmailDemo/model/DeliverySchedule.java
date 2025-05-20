package SpringEmailDemo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "delivery_schedule")
public class DeliverySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parcel_id", nullable = false, length = 10)
    private String parcel_id;

    @Column(name = "schedule_date", nullable = false)
    private LocalDate schedule_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_personnel_id", nullable = false)
    private DeliveryPersonnel deliveryPersonnel;

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getParcelId() {
        return parcel_id;
    }

    public void setParcelId(String parcelId) {
        this.parcel_id = parcelId;
    }

    public LocalDate getScheduleDate() {
        return schedule_date;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.schedule_date = scheduleDate;
    }

    public DeliveryPersonnel getDeliveryPersonnel() {
        return deliveryPersonnel;
    }

    public void setDeliveryPersonnel(DeliveryPersonnel deliveryPersonnel) {
        this.deliveryPersonnel = deliveryPersonnel;
    }
}
