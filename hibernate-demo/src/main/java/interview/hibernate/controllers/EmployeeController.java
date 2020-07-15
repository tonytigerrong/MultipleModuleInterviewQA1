package interview.hibernate.controllers;

import interview.hibernate.models.Employee;
import interview.hibernate.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Qualifier(value="employeeServiceImpl")
    @Autowired
    private EmployeeService employeeService;
    @GetMapping(value="/getEmployees")
    public List<Employee> getEmployees(){
        return employeeService.get();
    }
    @GetMapping(value="/getEmployee/{id}")
    public Employee getEmployee(@PathVariable int id){
        Employee employee = employeeService.get(id);
        if(employee == null)
            throw new RuntimeException("Employee {"+String.valueOf(id)+"} is not exits!");
        return employee;
    }
    @PostMapping(value="/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return employee;
    }
    @PutMapping(value="/updateEmployee")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return employee;
    }
    @DeleteMapping(value="/deleteEmployee")
    public String deleteEmployee(@RequestParam("id") int id){
        employeeService.delete(id);
        return "Delete id='"+String.valueOf(id)+"' is successfully!";
    }
}
