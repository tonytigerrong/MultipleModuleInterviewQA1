package interview.spring.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {
	/**
	 * 	"api-gateway" project: Eureka Client
	 * 		dependency: eureka-client
	 * 		@EnableEurekaClient
	 * 		config: spring.cloud.gateway.routes
	 * 	    	id / url:lb://application-name / predicates: first part of uri of api-gateway, routes to url
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
