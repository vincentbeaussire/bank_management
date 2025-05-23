package org.example.service;

import org.example.dao.BankAccountDao;
import org.example.dao.CustomerDao;
import org.example.model.BankAccount;
import org.example.model.Customer;
import org.example.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BankAccountService {

    private BankAccountDao bankAccountDao;
    private Connection connection;


    public BankAccountService() {
        try {
            connection = new DatabaseManager().getConnection();
            bankAccountDao = new BankAccountDao(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean createBankAccount(int id) {
        BankAccount bankAccount = new BankAccount(id);
        try {
            if (bankAccountDao.save(bankAccount)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateBalanceAccount(BankAccount bankAccount) {
        try {
            if (bankAccountDao.update(bankAccount)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public BankAccount getBankAccount(int id) {
        try {
            return bankAccountDao.get(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteBankAccount(int id) {
        BankAccount bankAccount = null;
        try {
            bankAccount = bankAccountDao.get(id);
            if (bankAccount != null) {
                return bankAccountDao.delete(bankAccount);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<BankAccount> getAllBankAccount() {
        try {
            return bankAccountDao.get();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
