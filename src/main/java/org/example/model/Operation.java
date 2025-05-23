package org.example.model;

public class Operation extends Customer {

    private double amount;
    private String status;

    public Operation(int amount, String status) {
        this.amount = amount;
        this.status = status;
    }

    public Operation(String firstName, String lastName, String phone, double amount, String status) {
        super(firstName, lastName, phone);
        this.amount = amount;
        this.status = status;
    }

    public Operation(int id, String firstName, String lastName, String phone, double amount, String status) {
        super(id, firstName, lastName, phone);
        this.amount = amount;
        this.status = status;
    }

    public Operation(int id, String typeOperation, double amount) {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
