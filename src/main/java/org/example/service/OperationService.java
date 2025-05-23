package org.example.service;

import org.example.dao.BankAccountDao;
import org.example.dao.OperationDao;
import org.example.model.BankAccount;
import org.example.model.Customer;
import org.example.model.Operation;
import org.example.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OperationService {

    private OperationDao operationDao;
    private Connection connection;

    public OperationService() {
        try {
            connection = new DatabaseManager().getConnection();
            operationDao = new OperationDao(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean createOperation(int amount, String status) {
        Operation operation = new Operation(amount, status);
        try {
            Operation Operation = null;
            if (OperationDao.addOperation(Operation)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateOperation(Operation operation) {
        try {
            if (operationDao.update(operation)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Operation getOperation(int id) {
        try {
            return operationDao.get(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Operation> getAllOperation() {
        try {
            return operationDao.get();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
