package SpringEmailDemo.repository;

import SpringEmailDemo.model.DeliveryPersonnel;
import SpringEmailDemo.model.DeliverySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryScheduleRepository extends JpaRepository<DeliverySchedule, Long> {
    List<DeliverySchedule> findByDeliveryPersonnel(DeliveryPersonnel deliveryPersonnel);
}
