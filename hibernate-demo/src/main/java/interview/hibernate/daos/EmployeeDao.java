package interview.hibernate.daos;

import interview.hibernate.models.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> get();
    public List<Employee> getLastEmployee();
    public List<Employee> getFirstEmployee();
    Employee get(int id);
    void save(Employee employee);
    void delete(int id);
}
