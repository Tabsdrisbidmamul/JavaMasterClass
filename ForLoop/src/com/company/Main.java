package com.company;

public class Main {

    public static void main(String[] args) {
//        System.out.println("10,000 at 2% interest = " + calculateInterest(10000.0, 2.0));
//        System.out.println("10,000 at 3% interest = " + calculateInterest(10000.0, 3.0));
//        System.out.println("10,000 at 4% interest = " + calculateInterest(10000.0, 4.0));
//        System.out.println("10,000 at 5% interest = " + calculateInterest(10000.0, 5.0));

        // challenge: Call calculateInterest method with 10,000 as the amount,
        // and the interest rate with (2, 3, 4, 5, 6, 7 and 8)
        // print the results to the console window

        for( double i=2; i < 9; i++) {
            System.out.println("10,000 at " + i + "% interest = " +
                    String.format("%.2f", calculateInterest(10000.0,i)));
        }

        System.out.println("");

        for( double i=8; i > 1 ; i--) {
            System.out.println("10,000 at " + i + "% interest = " +
                    String.format("%.2f", calculateInterest(10000.0,i)));
        }

        System.out.println("");

        // challenge: create a for loop to go over prime numbers - and once 3 are found break out of the loop
        //  - if prime number found, output it to the console, and increment the count
        //  - once 3 prime numbers are found, break out of the loop

        for(int i=50, count=0; i < 300; i++) {
            if (isPrime(i)) {
                System.out.println("Prime number found: " + i);
                count++;
            } if (count == 10) {
                System.out.println("Loop ended");
                break;
            }
        }

    }

    public static boolean isPrime(int n) {
        if ( n == 1 ) {
            return false;
        }
        for ( int i=2; i < Math.sqrt(n); i++ ) {
            if ( n % i == 0 ) {
                return false;
            }
        }
        return true;
    }

    public static double calculateInterest(double amount, double interestRate) {
        return (amount * (interestRate / 100));
    }
}

/*
* For Loop
* The For loop will continually execute the expressions within its code block for a given amount of time, when a
* condition is met
*
* for(initialise; termination; increment) {}
*
* The basic structure of a for loop:
*   - initialise: the code that is ran once when the for loop is started
*   - termination: the condition that must be for the for loop to exit out of the loop and start executing the next
*     lines of code at the end of the code block; the condition must evaluate to false for the loop to terminate
*   - increment: this code is invoked each time the loop has gone round, common things to place in there is a number
*     or value to keep track of the loop
*
* if increment is variable++ and the loop begins at 0 (int variable=0), then the number of loops that will be
* executed termination - initialisation -> 5-0 = 5; thus 5 loops will be executed
* */