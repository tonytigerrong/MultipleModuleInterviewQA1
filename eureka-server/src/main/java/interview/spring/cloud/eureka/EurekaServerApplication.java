package interview.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	/**
	 * =============== EUREKA Server/Client Demo =====================
	 * "eureka-server" project:  Eureka Server
	 * 		dependency: eureka-server
	 * 		@EnableEurekaServer
	 * 	"order-controller" and "payment-controller" projects: Eureka Client
	 * 		dependency: eureka-client
	 * 		@EnableEurekaClient
	 * 		eureka.client.service-url.default-Zone=eureka-server:port
	 * 		spring.application.name=
	 * 	 	eureka.instance.hostname=
	 * 		@LoadBalanced : add to restTemplate, invoke rest application name instead of ip:port name
	 *
	 * 	"api-gateway" project: Eureka Client
	 * 		dependency: eureka-client
	 * 		@EnableEurekaClient
	 * 		config spring.cloud.gateway.routes
	 * 	    id / url:lb://application-name / predicates: first part of uri of api-gateway, routes to url
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
