package SpringEmailDemo.repository;

import com.didu.SpringEmailDemo.model.DeliveryPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPersonnelRepository extends JpaRepository<DeliveryPersonnel, Long> {
    DeliveryPersonnel findTopByOrderByIdDesc(); // Optional for future use
}