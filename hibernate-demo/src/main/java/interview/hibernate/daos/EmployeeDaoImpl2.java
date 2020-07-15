package interview.hibernate.daos;

import interview.hibernate.models.Employee;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class EmployeeDaoImpl2 implements EmployeeDao {
    @Autowired
    private EntityManager entityManager;
    public HibernateTemplate getHibernateTemplate(){
        Session session = this.entityManager.unwrap(Session.class);
        SessionFactory sessionFactory = (SessionFactory) session.getSessionFactory();
        return new HibernateTemplate(sessionFactory);
    }
    @Override
    public List<Employee> get() {
        return getHibernateTemplate().execute(new HibernateCallback<List<Employee>>() {
            @Override
            public List<Employee> doInHibernate(Session session) throws HibernateException {
                Criteria criteria = session.createCriteria(Employee.class);
                return criteria.add(Expression.eq("id",1)).list();
            }
        });
    }

    @Override
    public List<Employee> getLastEmployee() {
        return null;
    }

    @Override
    public List<Employee> getFirstEmployee() {
        return null;
    }

    @Override
    public Employee get(int id) {
        return null;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void delete(int id) {

    }
}
