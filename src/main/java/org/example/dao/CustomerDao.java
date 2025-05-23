package org.example.dao;

import org.example.model.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDao extends BaseDao<Customer>{
    public CustomerDao(Connection connection) {
        super(connection);
    }

    // Création d'un compte client
    @Override
    public boolean save(Customer element) throws SQLException {
        request = "INSERT INTO customers (first_name,last_name, phone) VALUES (?,?, ?)";
        statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,element.getFirstName());
        statement.setString(2,element.getLastName());
        statement.setString(3, element.getPhone());
        int nbRow = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if (resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        return nbRow == 1;
    }

    // Modification d'un compte client
    @Override
    public boolean update(Customer element) throws SQLException {
        request = "UPDATE customers SET first_name = ?, last_name = ?, phone = ?, balance = ? WHERE id = ?";
        statement = connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        statement.setString(2,element.getFirstName());
        statement.setString(3,element.getLastName());
        statement.setString(4, element.getPhone());
        int nbRow = statement.executeUpdate();
        return nbRow == 1;
    }

    // Suppression d'un compte client
    @Override
    public boolean delete(Customer element) throws SQLException {
        request = "DELETE FROM customers WHERE id = ?";
        statement = connection.prepareStatement(request);
        statement.setInt(1,element.getId());
        int nbRow = statement.executeUpdate();
        return nbRow == 1;
    }

    // Afficher un compte client
    @Override
    public Customer get(int id) throws SQLException {
        Customer customer = null;
        request = "SELECT * FROM customers WHERE id = ?";
        statement = connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if (resultSet.next()){
            customer = new Customer(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("phone"));
        }
        return customer;
    }

    // Afficher tous les comptes clients
    @Override
    public List<Customer> get() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        request = "SELECT * FROM customers";
        statement = connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()){
            Customer customer = new Customer(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("phone"));
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public boolean addBankAccount(Customer element) throws SQLException {
        return false;
    }

    @Override
    public boolean updateBalance(Customer element) throws SQLException {
        return false;
    }

    @Override
    public boolean addOperation(Customer element) throws SQLException {
        return false;
    }
}
