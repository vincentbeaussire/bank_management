package org.example.dao;

import org.example.model.Customer;
import org.example.model.Operation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperationDao extends BaseDao<Operation> {
    public OperationDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Operation element) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Operation element) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Operation element) throws SQLException {
        return false;
    }

    @Override
    public Operation get(int id) throws SQLException {
        Operation operation = null;
        request = "SELECT * FROM operations WHERE id = ?";
        statement = connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if (resultSet.next()){
            operation = new Operation(resultSet.getInt("id"),
                    resultSet.getString("type_operation"),
                    resultSet.getDouble("amount"));
        }
        return operation;
    }

    @Override
    public List<Operation> get() throws SQLException {
        List<Operation> operations = new ArrayList<>();
        request = "SELECT * FROM operations WHERE compte_id = ?";
        statement = connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()){
            Operation operation = new Operation(resultSet.getInt("id"),
                    resultSet.getString("type_operation"),
                    resultSet.getDouble("amount"));
            operations.add(operation);
        }
        return operations;
    }

    @Override
    public boolean addBankAccount(Operation element) throws SQLException {
        return false;
    }

    @Override
    public boolean updateBalance(Operation element) throws SQLException {
        return false;
    }

    @Override
    public static boolean addOperation(Operation element) throws SQLException {
        request = "INSERT INTO operations (bank_account_id, type_operation, amount) VALUES (?, ?, 0)";
        statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1,element.getId());
        statement.setString(2,element.getStatus());
        statement.setDouble(3,element.getAmount());

        int nbRow = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if (resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        return nbRow == 1;
    }
}
