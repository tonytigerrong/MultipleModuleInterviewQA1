package interview.spring.websecurity.controller;

import interview.spring.websecurity.model.Department;
import interview.spring.websecurity.model.Employee;
import interview.spring.websecurity.repo.DepartmentRepo;
import interview.spring.websecurity.repo.EmployeeRepo;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class NPlusOneController {
    @Autowired
    EntityManager entityManager;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    @GetMapping(value="/getAllEmployees")
    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    @GetMapping(value="/getAllDepartments")
    public List<Department> getAllDepartments(){
        /**
         * this function show how fetchtype.lazy solve n+1 issue, so have to return null
         * if return departments, hiberante will fetch employees for each department
         *
         * There are 3 ways to solve n+1 problem
         * 1. fetch = FetchType.Lazy ****
         * 2. using "join fetch", join department and employee tables (by sql)
         * 3. Root.fetch (by hibernate code)
         */
        List<Department> departments = departmentRepo.findAll();
        return null;
    }
    @GetMapping(value="/getAllDeparts")
    public List<Department> getAllDeparts(){
        /**
         *  There are 3 ways to solve n+1 problem
         *    1. fetch = FetchType.Lazy
         *    2. using "join fetch", join department and employee tables (by sql) ****
         *    3. Root.fetch (by hibernate code)
         */
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("From Department d Join Fetch d.employees", Department.class);
        List<Department> departments = query.getResultList();
        return null;
    }

    @GetMapping(value="getAllDeparts2")
    public List<Department> getAllDeparts2(){
        /**
         *  There are 3 ways to solve n+1 problem
         *    1. fetch = FetchType.Lazy
         *    2. using "join fetch", join department and employee tables (by sql)
         *    3. Root.fetch (by hibernate code)  ****
         */
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
        root.fetch("employees", JoinType.LEFT);
        query.select(root);
        List<Department> departments = session.createQuery(query).getResultList();
        return null;
    }
}
