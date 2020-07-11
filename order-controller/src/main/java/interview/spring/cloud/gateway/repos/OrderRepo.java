package interview.spring.cloud.gateway.repos;

import interview.spring.cloud.gateway.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
