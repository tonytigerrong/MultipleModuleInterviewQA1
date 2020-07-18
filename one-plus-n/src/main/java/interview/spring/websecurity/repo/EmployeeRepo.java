package interview.spring.websecurity.repo;

import interview.spring.websecurity.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
