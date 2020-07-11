package interview.spring.cloud.gateway.repos;

import interview.spring.cloud.gateway.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
