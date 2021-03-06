package Dao.Hibernate;


import entity.Operation;
import entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null) {
            Configuration configuration = new Configuration();

            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
            settings.put(Environment.USER, "postgres");
            settings.put(Environment.PASS, "Your password");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            //settings.put(Environment.DEFAULT_SCHEMA, "postgres.Calc");
            settings.put(Environment.HBM2DDL_AUTO, "create-drop");


            configuration.setProperties(settings);

            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Operation.class);

            ServiceRegistry build = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(build);
        }
        return sessionFactory;
    }
}
