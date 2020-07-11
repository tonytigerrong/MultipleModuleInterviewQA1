package interview.spring.cloud.gateway.service;

import interview.spring.cloud.gateway.model.Payment;

public interface PaymentService {
    Payment save(Payment payment);
}
