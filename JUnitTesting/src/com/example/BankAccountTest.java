package com.example;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;
    private static int count;

    @org.junit.jupiter.api.BeforeAll
    public static void beforeClass() {
        System.out.println("This executes before any test cases.\nCount = " + count++);
    }

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.AccountType.CHECKING);
        System.out.println("Running a Test...");
    }

    @org.junit.jupiter.api.Test
    public void deposit() {
        double balance = account.deposit(200.00, true);
        assertEquals(1200, balance, 0);
    }

    @org.junit.jupiter.api.Test
    public void withdraw_notBranch() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(600.00, false), "Expected to throw IllegalArgumentException");
    }

    @org.junit.jupiter.api.Test
    public void withdraw_branch() throws Exception {
        double balance = account.withdraw(600.00, true);
        assertEquals(400.00, balance, 0);
    }

    @org.junit.jupiter.api.Test
    public void getBalance_deposit() {
        account.deposit(200.00, true);
        assertEquals(1200.00, account.getBalance(), 0);
    }

    @org.junit.jupiter.api.Test
    public void getBalance_withdraw() {
        account.withdraw(200.00, true);
        assertEquals(800.00, account.getBalance(), 0);
    }

    @org.junit.jupiter.api.Test
    public void isChecking_true() {
        assertTrue(account.isChecking(), () -> "The account is NOT a checking account");
    }

    @org.junit.jupiter.api.AfterAll
    public static void afterClass() {
        System.out.println("This executes after any test cases.\nCount = " + count++);
    }

    @org.junit.jupiter.api.AfterEach
    public void teardown() {
        System.out.println("Count = " + count++);
    }

}