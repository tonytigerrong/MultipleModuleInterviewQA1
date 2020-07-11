package interview.spring.cloud.gateway.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private Long paymentId;
    private boolean status;
    private String transactionId;
    private Long orderId;
    private double amount;
}
