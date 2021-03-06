package interview.hibernate;

import interview.hibernate.models.Employee;
import interview.hibernate.services.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
public class HibernateDemoApplication {
	private static final Logger LOGGER = LogManager.getLogger(HibernateDemoApplication.class.getName());

	public static void main(String[] args) {
		LOGGER.debug("Debug Message Logged !!!");
		LOGGER.info("Info Message Logged !!!");
		LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
		SpringApplication.run(HibernateDemoApplication.class, args);
	}
	@Autowired
	EmployeeService employeeService;
	@Bean
	public void initialData(){
		Employee employee1 = new Employee("David","male","Dev", LocalDate.of(2020,7,14));
		Employee employee2 = new Employee("Smith","female","Pro", LocalDate.of(2010,8,4));
		Employee employee3 = new Employee("Tony","male","Dev", LocalDate.of(2020,9,24));
		employeeService.save(employee1);
		employeeService.save(employee2);
		employeeService.save(employee3);
	}
}
