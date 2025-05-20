package SpringEmailDemo.repository;

import com.didu.SpringEmailDemo.model.DeliveryPersonnel;
import com.didu.SpringEmailDemo.model.DeliverySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryScheduleRepository extends JpaRepository<DeliverySchedule, Long> {
    List<DeliverySchedule> findByDeliveryPersonnel(DeliveryPersonnel deliveryPersonnel);
}
