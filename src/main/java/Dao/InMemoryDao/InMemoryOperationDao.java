package Dao.InMemoryDao;

import Dao.OperationDao;
import entity.Operation;
import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryOperationDao implements OperationDao {
    private static List<Operation> operations = new ArrayList();

    public void add(Operation operation){
        operations.add(operation);
    }

    @Override
    public List<Operation> getAll(User user) {
        return operations.stream().filter(op->op.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        //TODO
    }

    public List<Operation> getAll(){
        return new ArrayList<>(operations);
    }
}
