package org.example.service;

import org.example.dao.CustomerDao;
import org.example.model.Customer;
import org.example.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private CustomerDao customerDao;
    private Connection connection;


    public CustomerService() {
        try {
            connection = new DatabaseManager().getConnection();
            customerDao = new CustomerDao(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean createCustomer(int id, String firstName, String lastName, String phone) {
        Customer customer = new Customer(id, firstName, lastName, phone);
        try {
            if (customerDao.save(customer)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateCustomer(Customer customer) {
        try {
            if (customerDao.update(customer)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Customer getCustomer(int id) {
        try {
            return customerDao.get(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteCustomer(int id) {
        Customer customer = null;
        try {
            customer = customerDao.get(id);
            if (customer != null) {
                return customerDao.delete(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Customer> getAllCustomer() {
        try {
            return customerDao.get();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
