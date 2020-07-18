package interview.spring.websecurity.repo;

import interview.spring.websecurity.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
