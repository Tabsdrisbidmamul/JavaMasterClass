package com.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Challenge7 {
    public static void main(String[] args) {
        BankAccountEvolved account1 = new BankAccountEvolved("12345-678", 500.00);
        BankAccountEvolved account2 = new BankAccountEvolved("98765-432", 1000.00);

        new Thread(new Transfer(account1, account2, 10.00), "Transfer1").start();
        new Thread(new Transfer(account2, account1, 55.88), "Transfer2").start();
    }
}

class BankAccountEvolved {
    private double balance;
    private String accountNumber;
    private Lock lock = new ReentrantLock();

    BankAccountEvolved(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public boolean withdraw(double amount) {
        if (lock.tryLock()) {
            try {
                try {
                    // Simulate database access
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                }
                balance -= amount;
                System.out.printf("%s: Withdrew %f | balance: %f\n", Thread.currentThread().getName(), amount,
                        this.balance);
                return true;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (lock.tryLock()) {
            try {
                try {
                    // Simulate database access
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                }
                balance += amount;
                System.out.printf("%s: Deposited %f | balance: %f\n", Thread.currentThread().getName(), amount, this.balance);
                return true;
            } finally {
                lock.unlock();
            }

        }
        return false;
    }

    public boolean transfer(BankAccountEvolved destinationAccount, double amount) {
        if (withdraw(amount)) {
            if (destinationAccount.deposit(amount)) {
                return true;
            }
            else {
                // The deposit failed. Refund the money back into the account.
                System.out.printf("%s: Destination account busy. Refunding money\n",
                        Thread.currentThread().getName());
                while (!deposit(amount)) {
                    continue;
                }
            }
        }

        return false;
    }
}

class Transfer implements Runnable {
    private BankAccountEvolved sourceAccount, destinationAccount;
    private double amount;

    Transfer(BankAccountEvolved sourceAccount, BankAccountEvolved destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public void run() {
        while (!sourceAccount.transfer(destinationAccount, amount))
            continue;
        System.out.printf("%s completed\n", Thread.currentThread().getName());
    }
}