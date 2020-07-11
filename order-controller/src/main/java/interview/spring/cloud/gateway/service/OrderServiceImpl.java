package interview.spring.cloud.gateway.service;

import interview.spring.cloud.gateway.common.Payment;
import interview.spring.cloud.gateway.common.TransactionRequest;
import interview.spring.cloud.gateway.common.TransactionResponse;
import interview.spring.cloud.gateway.model.Order;
import interview.spring.cloud.gateway.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    RestTemplate restTemplate;
    //@Value() need to be extriacted from cloud config server
    String PAYMENT_SERVER_NAME = "PAYMENT-CONTROLLER"; // for server name: don't use under score, use dash!!!
    String url = "http://"+PAYMENT_SERVER_NAME+"/payment/doPayment";
    @Override
    public TransactionResponse save(TransactionRequest request) {
        Order order = request.getOrder();
        Order _order =  orderRepo.save(order);
        Payment payment = new Payment();
        payment.setAmount(_order.getPrice());
        payment.setOrderId(_order.getId());
        Payment paymentResponse = restTemplate.postForObject(url, payment,Payment.class);
        return new TransactionResponse(_order, paymentResponse.getTransactionId(),paymentResponse.isStatus(),_order.getPrice());
    }
}
