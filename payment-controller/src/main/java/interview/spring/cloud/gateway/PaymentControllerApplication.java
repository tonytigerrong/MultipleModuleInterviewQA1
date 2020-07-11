package interview.spring.cloud.gateway;

import interview.spring.cloud.gateway.model.Payment;
import interview.spring.cloud.gateway.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/payment")
public class PaymentControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentControllerApplication.class, args);
	}

	@Autowired
	PaymentServiceImpl paymentService;
	@PostMapping(value="/doPayment")
	public Payment doPayment(@RequestBody Payment pay){
		return paymentService.save(pay);
	}
}
