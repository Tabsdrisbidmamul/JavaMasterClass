package com.company;

import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Double> transactions = new ArrayList<>();

    public Customer(String name, double initialTransaction) {
        this.name = name;
        this.transactions.add(initialTransaction);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }

    public void addTransaction(double transaction) {
        transactions.add(transaction);
    }

    public void printTransactions() {
        for (Double transaction : transactions) {
            System.out.println("Transaction amount: " + transaction);
        }
    }

    public static Customer makeCustomer(String name, double transaction) {
        return new Customer(name, transaction);
    }
}
