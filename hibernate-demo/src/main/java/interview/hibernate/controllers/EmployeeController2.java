package interview.hibernate.controllers;

import interview.hibernate.HibernateDemoApplication;
import interview.hibernate.daos.EmployeeDao;
import interview.hibernate.exception.NoEmployeeException;
import interview.hibernate.models.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
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
    private static final Logger LOGGER = LogManager.getLogger(EmployeeController2.class.getName());
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

        }else if(method.equals("lock")){
            SessionFactory sessionFactory = session.getSessionFactory();
            Session session1 = sessionFactory.openSession();
            Employee e1 = session1.get(Employee.class,1);
            session1.beginTransaction();
            /**
             * LockMode:
             * 1. Write or UpGrade or Pessimistic_Write : for updated or inserted, lock immediately
             * 2. Read             : for a shared lock
             * 3. Pessimistic_Force_Increment: increment version immediately
             *
             */
            session1.lock(e1, LockMode.UPGRADE); // lock the record of e1, others can't update the record in the meantime
            e1.setName("LockMode.UpGrade");
            session1.merge(e1);
            session1.getTransaction().commit();

        }else if(method.equals("load")){
            // Load: return a fake object with attributes are null , without hit database. extract all attributes when real use
            // Load: return a proxy from Cache
            Employee employee1 = session.load(Employee.class,1);
            // Get: return a real object with all attributes setup.
            Employee employee2 = session.get(Employee.class,2);
            LOGGER.info(employee1);
            LOGGER.info(employee2);
        }else if(method.equals("refresh")){
            Employee employee = session.get(Employee.class,2);
            LOGGER.info(employee);
            session.refresh(employee); // reload employee from database again, select again
            LOGGER.info(employee);
        }else if(method.equals("evict.merge")){
            /**
             * evict: detach object from session wait for long time update or user input
             * merge: reattach object into session and hit database for update
             */


            SessionFactory sessionFactory = session.getSessionFactory();
            Session session1 = sessionFactory.openSession();
            Session session2 = sessionFactory.openSession();
            // session 1
            Employee em1 = session1.get(Employee.class,2);
            session1.evict(em1);
            // wait modify em object
            em1.setName("evict.merge");
            
            // session 2
            Transaction tx = session2.beginTransaction();
            session2.merge(em1);
            session2.flush();
            tx.commit();
        }

        return method + ": ok!";
    }

}
