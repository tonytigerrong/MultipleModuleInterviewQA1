package interview.hibernate.controllers;

import interview.hibernate.daos.EmployeeDao;
import interview.hibernate.exception.NoEmployeeException;
import interview.hibernate.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.xml.bind.PropertyException;
import java.time.LocalDate;
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

    @Autowired
    private EntityManager entityManager;
    @GetMapping(value="/testSaveVsPersist")
    public String testSaveVsPersist(@RequestParam("method") String method){
        Employee em =null;
        Session session = entityManager.unwrap(Session.class);
        Employee employeeInTransaction1 = new Employee("TestName1","male","QA", LocalDate.of(2010,1,31));
        Employee employeeInTransaction2 = new Employee("TestName2","male","QA", LocalDate.of(2010,1,31));

        if(method.equals("save")){
            Transaction tx = session.beginTransaction();
            session.save(employeeInTransaction1); // can effect in transaction
            tx.commit(); // transaction close
            session.save(employeeInTransaction2); // can effect outside transaction aslo
        }else if(method.equals("persist")){
            Transaction tx = session.beginTransaction();
            session.persist(employeeInTransaction1);  // can only effect inside transaction
            tx.commit(); // transaction close
            session.persist(employeeInTransaction2); // can NOT effect outside transaction

        }

        return String.valueOf(employeeInTransaction1.getId()) + "|" +String.valueOf(employeeInTransaction2.getId());
    }

}
