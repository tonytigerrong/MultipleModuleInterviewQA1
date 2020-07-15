package interview.hibernate.services;

import interview.hibernate.daos.EmployeeDao;
import interview.hibernate.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Qualifier(value="employeeDaoImpl")
    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public List<Employee> get() {
        return employeeDao.get();
    }

    @Override
    public Employee get(int id) {
        return employeeDao.get(id);
    }

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public void delete(int id) {
        employeeDao.delete(id);
    }

    @Override
    public List<Employee> getLastEmployee(){
        return employeeDao.getLastEmployee();
    }

    @Override
    public List<Employee> getFirstEmployee() {
        return employeeDao.getFirstEmployee();
    }
}
