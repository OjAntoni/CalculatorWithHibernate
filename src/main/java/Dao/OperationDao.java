package Dao;

import entity.Operation;
import entity.User;

import java.util.List;

public interface OperationDao {
    void add(Operation operation);
    List<Operation> getAll(User user);
    void delete(long id);
}
