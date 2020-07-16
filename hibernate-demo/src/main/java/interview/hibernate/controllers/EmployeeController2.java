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
//        Employee employeeInTransaction2 = new Employee("TestName2","male","QA", LocalDate.of(2010,1,31));

        if(method.equals("save")){
            /**
             * save: can effect both inside and outside of session
             */
            Transaction tx = session.beginTransaction();
            session.save(employeeInTransaction1); // can effect in session
            tx.commit(); // transaction close
            session.evict(employeeInTransaction1); // detach entity from session
            session.save(employeeInTransaction1); // can effect outside session also
        }else if(method.equals("persist")){
            /**
             * persist: 1. change entity from transient state to persistent state, not hit to database
             *          if commit, will hit database
             *          2. only effect inside session
             *          3. not effect outside session
             */
            Transaction tx = session.beginTransaction();
            session.persist(employeeInTransaction1);  // can only effect inside transaction
            tx.commit(); // transaction close
            session.evict(employeeInTransaction1); // detach entity from session
            session.persist(employeeInTransaction1); // can NOT effect outside transaction, throw Exception

        }else if(method.equals("saveorupdate")){

            Transaction tx = session.beginTransaction();
            session.save(employeeInTransaction1);
            tx.commit();
            session.evict(employeeInTransaction1); // detach entity from session
            tx = session.beginTransaction();
            employeeInTransaction1.setName("TestName3");
            session.saveOrUpdate(employeeInTransaction1); // if id != null then update;
            tx.commit();

        }else if(method.equals("merge")){
            /**
             * 1. if there is a entity instance in session, can't use update, use merge
            */
            SessionFactory sessionFactory = session.getSessionFactory();
            Session session1 = sessionFactory.openSession();
            Session session2 = sessionFactory.openSession();
            Employee e1 = session1.get(Employee.class,1);
            e1.setName("Fake");
            // in session2 , there is entity{id=1}
            session2.beginTransaction();
            Employee e2 = session2.get(Employee.class,1);
            session2.merge(e1);
            session2.getTransaction().commit();
        }else if(method.equals("update")){
            /**
             * 2. if there is a entity instance in session, can't use update, use merge
             */
            SessionFactory sessionFactory = session.getSessionFactory();
            Session session1 = sessionFactory.openSession();
            Session session2 = sessionFactory.openSession();
            Employee e1 = session1.get(Employee.class,1);
            e1.setName("Fake");
            // in session2 , there is entity{id=1}
            session2.beginTransaction();
                Employee e2 = session2.get(Employee.class,1);
                session2.update(e1);
            session2.getTransaction().commit();

        }

        return method + ": ok!";
    }

}
