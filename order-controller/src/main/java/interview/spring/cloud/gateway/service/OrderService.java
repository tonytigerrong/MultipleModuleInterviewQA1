package interview.spring.cloud.gateway.service;

import interview.spring.cloud.gateway.common.TransactionRequest;
import interview.spring.cloud.gateway.common.TransactionResponse;
import interview.spring.cloud.gateway.model.Order;

public interface OrderService {
    TransactionResponse save(TransactionRequest request);
}
