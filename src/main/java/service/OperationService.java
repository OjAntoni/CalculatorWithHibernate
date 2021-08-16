package service;

import Dao.Hibernate.HibernateOperationDao;
import Dao.OperationDao;
import entity.Operation;
import entity.User;
import Dao.InMemoryDao.InMemoryOperationDao;

import java.util.List;
import java.util.stream.Collectors;

public class OperationService {

    OperationDao opStorage = new HibernateOperationDao();

    public void add(Operation op){
        if(op==null) return;
        opStorage.add(op);
    }

    public double calculate(double num1, double num2, String operation){
        return switch (operation){
            case "sum" -> num1+num2;
            case "sub" -> num1-num2;
            case "div" -> num1/num2;
            case "mult" -> num1*num2;
            default -> 0.0;
        };
    }

    public List<Operation> getAll(User user) {
        return opStorage.getAll(user);
    }

    public void delete(long id){
        opStorage.delete(id);
    }

    public static String getSymbol(String operation){
        return switch (operation){
            case "sum" -> "+";
            case "sub" -> "-";
            case "div" -> "/";
            case "mult" -> "*";
            default -> "?";
        };
    }
}
