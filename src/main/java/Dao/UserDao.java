package Dao;

import entity.User;

public interface UserDao {
    void add(User user);
    boolean exists(String username, String password);
    boolean exists(String username);
    User getByUsername(String username);
}
