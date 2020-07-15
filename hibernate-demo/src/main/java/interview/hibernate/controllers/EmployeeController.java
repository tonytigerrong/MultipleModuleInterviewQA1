package interview.hibernate.controllers;

import interview.hibernate.exception.NoEmployeeException;
import interview.hibernate.exception.NoEntity;
import interview.hibernate.models.Employee;
import interview.hibernate.services.EmployeeService;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Procedure("application/json")
    public ResponseEntity getEmployee(@PathVariable int id) throws PropertyException {
        ResponseEntity responseEntity;
        NoEmployeeException ex;
        Employee employee = employeeService.get(id);
        if(employee == null) {
            throw new NoEmployeeException("Employee not Found!",Integer.toString(id));
        }else{
            responseEntity = new ResponseEntity(employee,HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping(value="/getLastEmployee")
    @Procedure("application/json")
    public ResponseEntity getLastEmployee(){
        List<Employee> employees = employeeService.getLastEmployee();
        return new ResponseEntity(employees,HttpStatus.OK);
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

    @ExceptionHandler(NoEmployeeException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public NoEntity defaultException(NoEmployeeException e, WebRequest request){
        String[] attributes = request.getAttributeNames(WebRequest.SCOPE_REQUEST);
        // get path variable of request via WebRequest
        HashMap pathAttributes = (HashMap) request.getAttribute("org.springframework.web.servlet.View.pathVariables",WebRequest.SCOPE_REQUEST);
        Integer id = (Integer)pathAttributes.get("id");
        String idString = String.valueOf(id);
        NoEntity noEntity = new NoEntity(idString,"Employee","Not Found");
        return noEntity;
    }
}
