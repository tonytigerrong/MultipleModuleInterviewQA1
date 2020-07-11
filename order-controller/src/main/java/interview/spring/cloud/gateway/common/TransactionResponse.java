package interview.spring.cloud.gateway.common;

import interview.spring.cloud.gateway.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Order order;
    private String paymentTransactionId;
    private boolean paymentStatus;
    private double amount;
}
