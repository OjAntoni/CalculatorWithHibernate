package Dao.Hibernate;

import Dao.UserDao;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUserDao implements UserDao {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void add(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            logger.warn("exception occurred while adding " + user +e.getMessage());
            session.getTransaction().rollback();
            throw (e);
        } finally {
            session.close();
        }


    }

    @Override
    public boolean exists(String username) {
        boolean exists;
        Session session = sessionFactory.openSession();
        exists = session.createQuery("from User where username = :username")
                .setParameter("username",username)
                .setMaxResults(1).uniqueResult() != null;
        session.close();
        return exists;
    }

    @Override
    public User getByUsername(String username) {
        Session session = sessionFactory.openSession();
        Criteria usernameCriteria = session.createCriteria(User.class).add(Restrictions.eq("username", username));
        Object o = usernameCriteria.uniqueResult();
        if(o!=null){
            User user = (User) o;
            System.out.println(user);
            return user;
        }
        return null;
    }

    @Override
    public boolean exists(String username, String password) {
        boolean exists;
        Session session = sessionFactory.openSession();
        try {
            exists = session.createQuery("from User where username = :username and password = :password")
                    .setParameter("username",username)
                    .setParameter("password", password)
                    .setMaxResults(1).uniqueResult() != null;
        } catch (Exception e){
            logger.warn(String.format("exception occurred while checking for existence: %s %s %s"),username,password,e.getMessage());
            session.getTransaction().rollback();
            throw (e);
        } finally {
            session.close();
        }
        return exists;
    }
}
