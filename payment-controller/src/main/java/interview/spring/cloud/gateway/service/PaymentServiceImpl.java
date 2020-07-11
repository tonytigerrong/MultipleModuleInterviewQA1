package interview.spring.cloud.gateway.service;

import interview.spring.cloud.gateway.model.Payment;
import interview.spring.cloud.gateway.repos.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepo paymentRepo;
    @Override
    public Payment save(Payment payment) {
        String transactionId = UUID.randomUUID().toString();
        payment.setTransactionId(transactionId);
        payment.setStatus(getPaymentStatus());
        return paymentRepo.save(payment);
    }

    public boolean getPaymentStatus(){
        return new Random().nextBoolean();
    }
}
