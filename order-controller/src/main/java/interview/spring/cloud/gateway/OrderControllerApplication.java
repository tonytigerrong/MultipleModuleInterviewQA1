package interview.spring.cloud.gateway;

import interview.spring.cloud.gateway.common.Payment;
import interview.spring.cloud.gateway.common.TransactionRequest;
import interview.spring.cloud.gateway.common.TransactionResponse;
import interview.spring.cloud.gateway.model.Order;
import interview.spring.cloud.gateway.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/order")
public class OrderControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderControllerApplication.class, args);
	}
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	OrderServiceImpl orderService;

	@PostMapping(value="/bookOrder")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest request){
		TransactionResponse response = orderService.save(request);
		return response;
	}
}
