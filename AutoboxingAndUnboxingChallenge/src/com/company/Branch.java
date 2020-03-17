package com.company;

import java.util.ArrayList;

public class Branch {
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private String branchName;

    public Branch(String branchName) {
        this.branchName = branchName;
    }

    public static Branch makeBranch(String branchName) {
        return new Branch(branchName);
    }

    public ArrayList<Double> getCustomerTransactions(String name) {
        if(isCustomerAdded(name)){
            int customerPos = findCustomer(name);
            return customers.get(customerPos).getTransactions();
        }
        return null;
    }

    public void printCustomer() {
        for (Customer customer : customers) {
            System.out.println("Customer " + customer.getName() + " has made these transactions ");
            customer.printTransactions();
        }
    }

    public String getBranchName() {
        return branchName;
    }

    public boolean addCustomer(String name, double initialTransaction) {
        if(!(isCustomerAdded(name))) {
            customers.add(Customer.makeCustomer(name, initialTransaction));
            System.out.println("Customer " + name + " was added, with an initial transaction of " + initialTransaction);
            return true;
        }
        System.out.println("Customer " + name + " is already registered at this branch ");
        return false;
    }

    public void addCustomer(String name) {
        addCustomer(name, 0.0);
    }

    public boolean addTransaction(String name, double transactionAmount) {
        if(isCustomerAdded(name)) {
            customers.get(findCustomer(name)).addTransaction(transactionAmount);
            System.out.println("Transaction of amount " + transactionAmount + " was added to your account " + name);
            return true;
        }
        System.out.println("Transaction failed");
        return false;
    }

    private boolean isCustomerAdded(String name) {
        int customerNamePos = findCustomer(name);
        if(customerNamePos >= 0) {
            return true;
        }
        return false;
    }

    private int findCustomer(String name) {
        for(int i = 0; i < customers.size(); i ++) {
            if(customers.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

}
