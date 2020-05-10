package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTestParameterized {

    private BankAccount account;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.AccountType.CHECKING);
        System.out.println("Running a Test...");
    }

    public static Stream<Arguments> testConditions() {
        return Stream.of(
                Arguments.of(100.00, true, 1100.00),
                Arguments.of(200.00, true, 1200.00),
                Arguments.of(325.14, true, 1325.14),
                Arguments.of(489.33, true, 1489.33),
                Arguments.of(1000.00, true, 2000.00)
        );
    }

    @ParameterizedTest(name = "Run {index}: deposit={0}, branch={1}, expected={2}")
    @MethodSource("testConditions")
    public void deposit(double amount, boolean branch, double expected) {
        account.deposit(amount, branch);
        assertEquals(expected, account.getBalance(), .01);
    }

}
