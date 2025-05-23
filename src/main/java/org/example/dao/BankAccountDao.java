package org.example.dao;

import org.example.model.BankAccount;
import org.example.model.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BankAccountDao extends BaseDao<BankAccount> {

    public BankAccountDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(BankAccount element) throws SQLException {
        return false;
    }

    @Override
    public boolean update(BankAccount element) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(BankAccount element) throws SQLException {
        return false;
    }

    @Override
    public boolean addBankAccount (BankAccount element) throws SQLException {
        request = "INSERT INTO bank_accounts (customer_id, balance) VALUES (?, 0)";
        statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1,element.getId());
        statement.setDouble(2,element.getBalance());
        int nbRow = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if (resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        return nbRow == 1;
    }

    @Override
    public boolean updateBalance(BankAccount element) throws SQLException {
        request = "UPDATE bank_accounts SET balance = ? WHERE id = ?";
        statement = connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        statement.setDouble(2, element.getBalance());
        int nbRow = statement.executeUpdate();
        return nbRow == 1;
    }

    @Override
    public boolean addOperation(BankAccount element) throws SQLException {
        return false;
    }


    @Override
    public BankAccount get(int id) throws SQLException {
        BankAccount bankAccount = null;
        request = "SELECT * FROM bank_accounts WHERE id = ?";
        statement = connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if (resultSet.next()){
            bankAccount = new BankAccount(resultSet.getInt("id"));
            bankAccount = new BankAccount(resultSet.getDouble("balance"));
        }
        return bankAccount;
    }

    @Override
    public List<BankAccount> get() throws SQLException {
        return List.of();
    }
}
