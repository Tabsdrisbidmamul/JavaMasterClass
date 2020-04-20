package com.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private final Lock lock;
    private double balance;
    private String accountNumber;
    private static final String MSG = "Could not get the lock";

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.lock = new ReentrantLock();
    }

//    public synchronized void deposit(double amount) {
//        balance += amount;
//    }
//
//    public synchronized void withdraw(double amount) {
//        balance -= amount;
//    }

//    public void deposit(double amount) {
//        synchronized (this) {
//            balance += amount;
//        }
//    }
//
//    public void withdraw(double amount) {
//        synchronized (this) {
//            balance -= amount;
//        }
//    }

//    public void deposit(double amount) {
//        lock.lock();
//        try {
//            balance += amount;
//        } finally {
//            lock.unlock();
//        }
//
//    }
//
//    public void withdraw(double amount) {
//        lock.lock();
//        try {
//            balance -= amount;
//        } finally {
//            lock.unlock();
//        }
//
//    }

    public void deposit(double amount) {
        boolean status = false;
        try {
            if(lock.tryLock(1000L, TimeUnit.MILLISECONDS)) {
                try {
                    balance += amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(MSG);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Transaction status: " + status);
    }

    public void withdraw(double amount) {
        boolean status = false;
        try {
            if(lock.tryLock(1000L, TimeUnit.MILLISECONDS)) {
                try {
                    balance -= amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(MSG);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Transacting status: " + status);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccount() {
        System.out.println("Account number = " + accountNumber);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
