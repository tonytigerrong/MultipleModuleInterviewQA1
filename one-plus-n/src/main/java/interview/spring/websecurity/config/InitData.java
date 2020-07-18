package interview.spring.websecurity.config;

import interview.spring.websecurity.model.Department;
import interview.spring.websecurity.model.Employee;
import interview.spring.websecurity.repo.DepartmentRepo;
import interview.spring.websecurity.repo.EmployeeRepo;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InitData {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private DepartmentRepo departmentRepo;

    @Bean
    public void init(){
        /**
         * Department -------------> Employee ( 1-n )
         * mappedby(one side)        joincolumn(many side|forgein key) // use both or either
         * cascade = CascadType.All : save it, sub attributes auto saved as cascade type.
         */
        Department department1 = new Department();    Department department2 = new Department();     Department department3 = new Department();
        department1.setDeptName("Dev");               department2.setDeptName("Product");            department3.setDeptName("Manager");

        Employee employee1 = new Employee();
        employee1.setName("David");
        employee1.setPassword("password1");
        employee1.setDepartment(department1);

        Employee employee2 = new Employee();
        employee2.setName("Smith");
        employee2.setPassword("password2");
        employee2.setDepartment(department1);

        Employee employee3 = new Employee();
        employee3.setName("Philip");
        employee3.setPassword("password1");
        employee3.setDepartment(department1);

            List<Employee> employees = new ArrayList<>();
            employees.add(employee1);
            employees.add(employee2);
            employees.add(employee3);
            department1.setEmployees(employees);

        Employee employee4 = new Employee();
        employee4.setName("David1");
        employee4.setPassword("password1");
        employee4.setDepartment(department2);

        Employee employee5 = new Employee();
        employee5.setName("Smith1");
        employee5.setPassword("password1");
        employee5.setDepartment(department2);

        Employee employee6 = new Employee();
        employee6.setName("Tony1");
        employee6.setPassword("password1");
        employee6.setDepartment(department2);

        List<Employee> employees1 = new ArrayList<>();
        employees1.add(employee4);
        employees1.add(employee5);
        employees1.add(employee6);
        department2.setEmployees(employees1);

        departmentRepo.save(department1);  // cascadetype.all in department side
//      employeeRepo.saveAll(employees); // cascadetype.all in employee side
        departmentRepo.save(department2);


    }
}
