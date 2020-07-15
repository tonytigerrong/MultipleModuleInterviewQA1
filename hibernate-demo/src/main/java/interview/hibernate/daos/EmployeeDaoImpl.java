package interview.hibernate.daos;

import interview.hibernate.models.Employee;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaDelete;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private EntityManager entityManager;
    @Transactional
    @Override
    public List<Employee> get() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Employee", Employee.class);
        List<Employee> list =  query.getResultList();
        return list;
    }
    @Transactional
    @Override
    public Employee get(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Employee.class,id);
    }
    @Transactional
    @Override
    public void save(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);
    }
    @Transactional
    @Override
    public void delete(int id) {
        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.load(Employee.class,id);
        session.delete(employee);
    }

    public List<Employee> getLastEmployee(){
        String sql = "select * from (select * from tbl_employee order by id desc) as t limit 1";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.addEntity(Employee.class);
        List<Employee> results = query.list();
        return results;
    }

    public Session getSession(){
        return entityManager.unwrap(Session.class);
    }
}
