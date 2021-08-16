package service;

import Dao.Hibernate.HibernateUserDao;
import Dao.InMemoryDao.InMemoryUserDao;
import Dao.UserDao;
import entity.User;

public class UserService {

    private UserDao userDao= new HibernateUserDao();

    public void add(User user){
        userDao.add(user);
    }

    public boolean exists(String username, String password){
        return userDao.exists(username, password);
    }

    public boolean exists(String username){
        return userDao.exists(username);
    }

    public User getByUsername(String username){
        return userDao.getByUsername(username);
    }
}
