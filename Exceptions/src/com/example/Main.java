package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//	    int x = 90; int y = 0;
//        System.out.println("Divide LBYL: " + divideLBYL(x, y));
//        System.out.println("Divide EAFP: " + divideEAFP(x, y));
//        System.out.println("Divide: " + divide(x, y));
        System.out.println("Enter a number");
        int x = getIntEAFP();
        System.out.println("x is " + x);
    }

    private static int getInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static int getIntLBYL() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = true;
        System.out.println("Please enter an integer ");
        String input = scanner.next();
        for (int i=0; i<input.length(); i++) {
            if(!Character.isDigit(input.charAt(i))) {
                isValid = false;
                break;
            }
        }

        if(isValid) {
            return Integer.parseInt(input);
        }

        return 0;
    }

    private static int getIntEAFP() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an integer ");
        try{
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return 0;
        }
    }

    private static int divideLBYL(int x, int y) {
        if (y != 0) {
            return x / y;
        } else {
            return 0;
        }
    }

    private static int divideEAFP(int x, int y) {
        try {
            return x / y;
        } catch (ArithmeticException e) {
            return 0;
        }
    }

    private static int divide(int x, int y) {
        return x / y;
    }
}

/*
* Exceptions
* There are 2 approaches when dealing with errors in programming
*   - LBYL: Look Before You Leap
*   - EAFP: Easy to Ask for forgiveness than Permission
*
* In Java it is more common to to LBYL: we have seen this a lot when an object is not null before we attempt to use
* it for example
*
* The EAFP is to go ahead and perform the operation then respond to an exception if something goes wrong - so
* trapping and handling exceptions is pretty simple
*
* Try-catch block
* The try catch block follows the EAFP saying - meaning lets try this bit of code, and if it works carry on, and if
* fails well we can try forgive ourselves by catching the exception (the program will  not crash if this is done)
*
* try {
*   <snippet> do something here
* } catch (exception e) {
*   <snippet> handle error here
* }
*
* The methods above
*   - getIntLBYL
*   - getIntEAFP
*
* demonstrate the different approaches when it comes to handling errors, whilst both are right - they can handle an
* error - however the readability of both are drastically different - where one is literally reading it off from
* pseudocode - the other requires a bit of throught to figure out what is happening.
*
* Now there is clear distinction on which approach is better, they both will have the same outcome in the end - it
* just matters how elegant and readable the code is - sometimes LBYL can be better than EAFP - and vice versa. It
* really depends on the situation
*
* An Exception:
* Is an event which occurred during the execution of a program that disrupts the normal flow of the program's
* instructions
*
* or
*
* Something went wrong?
*
* We can answer that question with,
*   - What went wrong?
*   - Can we do something about it?
*
* Exceptions and runtime exceptions are classes defined in the java.lang and different types of exceptions can be
* called subclasses of these 2 exceptions
*
* It is best to catch specific exceptions and not generalising the exception to catch all to say
*
* */