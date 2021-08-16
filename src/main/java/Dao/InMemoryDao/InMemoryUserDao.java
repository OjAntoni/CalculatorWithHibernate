package Dao.InMemoryDao;

import Dao.UserDao;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDao implements UserDao {
    private static List<User> users = new ArrayList<>();

    public void add(User u){
        users.add(u);
    }

    @Override
    public boolean exists(String username, String password) {
        return users.stream().anyMatch(u->u.getUsername().equals(username) && u.getPassword().equals(password));
    }

    @Override
    public boolean exists(String username) {
        return users.stream().anyMatch(u->u.getUsername().equals(username));
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

}
