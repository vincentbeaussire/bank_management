package org.example.model;

public class BankAccount extends Customer {

    double balance;

    public BankAccount(double balance) {
        this.balance = 0;
    }

    public BankAccount(String firstName, String lastName, String phone, double balance) {
        super(firstName, lastName, phone);
        this.balance = balance;
    }

    public BankAccount(int id, String firstName, String lastName, String phone, double balance) {
        super(id, firstName, lastName, phone);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}
