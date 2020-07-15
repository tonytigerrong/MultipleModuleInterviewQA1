package interview.hibernate.services;

import interview.hibernate.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee>  get();
    Employee get(int id);
    void save(Employee employee);
    void delete(int id);
}
