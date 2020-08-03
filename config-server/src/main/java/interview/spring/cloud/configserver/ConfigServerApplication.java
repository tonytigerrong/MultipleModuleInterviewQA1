package interview.spring.cloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableConfigServer
@RefreshScope
@Configuration
public class ConfigServerApplication {
	/**
	 * mapping server config
	 * http://localhost:8081/actuator/default
	 *
	 *
	 * /**
	 * 	 *
	 * 	 * =========  SPRING BOOT CONFIG SERVER DEMO =======================
	 * 	 *   All configuration stored in GIT
	 * 	 *  	https://github.com/tonytigerrong/ConfigServe
	 * 	 *  		application.properties (Key / Value)
	 * 	 *
	 * 	 *  "config-server" project, includes dependency of cloud-config-server, to extract config from GIT
	 * 	 *  	@EnableConfigServer : label as config server
	 * 	 * 	 	@RefreshScope
	 * 	 *  	spring.cloud.config.server.git.uri=git's url
	 * 	 *  "client" project, includes dependency of cloud-starter-config, to extreact config from "config-server"
	 * 	 *		@EnableAutoConfiguration : for application class
	 * 	 *		@RefreshScope : for controller class
	 * 	 *      @Value("${websecurity.web.hellocontroller.cloudConfigTest}") : for attribute, value injection
	 * 	 *   	spring.cloud.config.uri=config-server:port
	 * 	 *
	 * 	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
