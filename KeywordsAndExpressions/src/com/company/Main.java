package com.company;

public class Main {

    public static void main(String[] args) {
        double kilometers = (100 * 1.609344);
        int highScore = 50;

        // the conditional check in brackets is an expression - not the if or the code block, just what is within the
        // brackets is a boolean expression
        if(highScore == 50) {
            // With the method call, we pass in a string literal argument, this too is an expression - only the
            // argument, not the method call itself
            System.out.println("This is an expression");
        }

        // challenge: the code below - define which part is an expression
        int score = 100; // score = 100; -> expression
        if(score > 90) { // score > 90 -> expression
            System.out.println("You got the high score"); // "You got the high score" -> expression
            score = 0; // -> expression
        }

    }
}

/*
* Keywords
* There are 51 reserved keywords in Java 49 are in use and 2 are not in use
*
* Expressions and Statements
* kilometers = (100 * 1.609344); -> makes the expression, a variable name, given the assignment operator (=) to a set
* of literal values
*
* double kilometers = (100 * 1.609344); -> adding the data type (double) and ending it with a semi-colon qualifies it
* as a Java statement
*   - double kilometers = (100 * 1.609344);
* */