package interview.spring.cloud.configserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@RestController
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Autowired
	RestTemplate restTemplate;
	@Value("${websecurity.web.hellocontroller.cloudConfigTest}")
	String url;
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	@GetMapping("/client")
	public List clientTest(){
		return restTemplate.getForObject(url,List.class);
	}
}
