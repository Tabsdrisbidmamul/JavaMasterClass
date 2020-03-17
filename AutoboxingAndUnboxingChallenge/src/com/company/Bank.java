package com.company;

import java.util.ArrayList;

public class Bank {
    private  ArrayList<Branch> branches = new ArrayList<>();

    public boolean addBranch(String branchName) {
        if(isBranchAdded(branchName)) {
            System.out.println("Branch " + branchName + " already exists");
            return false;
        }
        branches.add(Branch.makeBranch(branchName));
        return true;
    }

    public void printCustomerAndTransactions(String branchName) {
        if(isBranchAdded(branchName)){
            System.out.println("Branch: " + branchName + " have these entries");
            for (Branch branch : branches) {
                branch.printCustomer();
            }
            return;
        }
        System.out.println("Branch does not exist");
    }

    public boolean addTransactionToExistingCustomer(String customerName, double transactionAmount, String branchName) {
        if(isBranchAdded(branchName)) {
            int customerPos = findBranch(branchName);
            if( branches.get(customerPos).getCustomerTransactions(customerName) != null ) {
                return branches.get(customerPos).addTransaction(customerName, transactionAmount);
            }
            return false;
        }
        return false;
    }

    public boolean addCustomerToBranch(String customerName, double initialTransaction, String branchName) {
        if(isBranchAdded(branchName)){
            int customerPos = findBranch(branchName);
            if( branches.get(customerPos).addCustomer(customerName, initialTransaction) ) {
                return true;
            }
            System.out.println("Cannot process initial transaction request " + initialTransaction + " as customer already" +
                    " registered at branch: " + branchName);
            return false;
        }
        System.out.println("Error adding a customer");
        return false;

    }


    private boolean isBranchAdded(String branchName) {
        int branchNamePos = findBranch(branchName);
        if(branchNamePos >= 0) {
            return true;
        }
        return false;
    }

    private int findBranch(String branchName) {
        for(int i = 0; i < branches.size(); i ++) {
            if( branches.get(i).getBranchName().toLowerCase().equals(branchName.toLowerCase()) ) {
                return i;
            }
        }
        return -1;
    }
}
