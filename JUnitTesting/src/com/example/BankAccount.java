package com.example;


public class BankAccount {
    private String firstName;
    private String lastName;
    private double balance;

    enum AccountType {
        CHECKING, SAVINGS;
    }

    private AccountType accountType;

    public BankAccount(String firstName, String lastName, double balance, AccountType typeOfAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.accountType = typeOfAccount;
    }

    // The branch arg is true if the cust is performing the transaction at a branch with a teller
    // false if the cust is performing the transaction at an ATM
    public double deposit(double amount, boolean branch) {
        balance += amount;
        return balance;
    }

    // The branch arg is true if the cust is performing the transaction at a branch with a teller
    // false if the cust is performing the transaction at an ATM
    public double withdraw(double amount, boolean branch) {
        if((amount > 500.00) && !branch) {
            throw new IllegalArgumentException();
        }
        balance -= amount;
        return balance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isChecking() {
        return accountType == AccountType.CHECKING;
    }

    // more method that use firstName, lastName and perform other functions
}
