package Dao.Hibernate;

import Dao.OperationDao;
import entity.Operation;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateOperationDao implements OperationDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void add(Operation operation) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(operation);
            session.getTransaction().commit();
        } catch (Exception e){
            logger.warn("exception occurred while adding " + operation +e.getMessage());
            session.getTransaction().rollback();
            throw (e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Operation> getAll(User user) {
        Session session = sessionFactory.openSession();
        Query<Operation> query = session.createQuery("from Operation where user.username = :username", Operation.class);
        query.setParameter("username", user.getUsername());
        List<Operation> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Operation operation1 = session.get(Operation.class, id);
            System.out.println(operation1);
            session.delete(operation1);
            session.getTransaction().commit();
        } catch (Exception e){
            logger.warn("exception occurred while deleting by id=" + id + e.getMessage());
            session.getTransaction().rollback();
            throw (e);
        } finally {
            session.close();
        }
    }
}
