package interview.hibernate.controllers;

import interview.hibernate.daos.EmployeeDao;
import interview.hibernate.exception.NoEmployeeException;
import interview.hibernate.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.PropertyException;
import java.util.List;

@RestController
@RequestMapping("/api2")
public class EmployeeController2 {

    @Qualifier("employeeDaoImpl2")
    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping(value="/getEmployees")
    public List<Employee> getEmployees(){
        return employeeDao.get();
    }

}
