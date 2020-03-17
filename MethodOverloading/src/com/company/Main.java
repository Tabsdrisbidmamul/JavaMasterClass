package com.company;

public class Main {

    public static void main(String[] args) {
        int newScore = calculateScore("Phil" , 500);
        System.out.println("New score is " + newScore);
        calculateScore(75);
        calculateScore();

        // challenge: create a method called calcFeetAndInchesToCentimeters
        // it needs to have two params
        // feet is the first param, inches is the 2nd param
        //
        // check if feet is >= 0
        // check if 0 <= inches <= 12
        // return -1 if either of the checks fails
        //
        // if params are valid, calculate how many centimeters comprise the feet and inches passed to this method and
        // return that value
        //
        // create a second method of the same name but with only one param, inches is that param
        // check if its >= 0, return -1 if false
        // but if true, calculate how many feet are in the inches
        // 1 inch = 2.54cm and one foot = 12 inches

        System.out.println(calcFeetAndInchesToCentimeters(20, 12));
        System.out.println(calcFeetAndInchesToCentimeters(-5, 25));
        System.out.println(calcFeetAndInchesToCentimeters(-5, -20));
        System.out.println(calcFeetAndInchesToCentimeters(20, -20));
        System.out.println(calcFeetAndInchesToCentimeters(100));


    }

    public static int calculateScore(String playerName, int score) {
        System.out.println("Player " + playerName + " scored " + score + " points");
        return score * 1000;
    }

    public static int calculateScore(int score) {
        System.out.println("Unnamed player scored " + score + " points");
        return score * 1000;
    }

    public static int calculateScore() {
        System.out.println("No player name, no player score");
        return 0;
    }

    public static double calcFeetAndInchesToCentimeters(double feet, double inches) {
        if ( (feet < 0) || ((inches < 0) || (inches > 12)) ) {
            return -1;
        } else {
            return (inches * 2.54) + ((feet * 12) * 2.54);
        }
    }

    public static double calcFeetAndInchesToCentimeters(double inches) {
        if (inches < 0) {
            return -1;
        } else {
            double feet = (int) (inches / 12);
            double remainingInches = (int) (inches % 12);
            return calcFeetAndInchesToCentimeters(feet, remainingInches );
        }
    }


}

/*
* Method Overloading
* In Java it is a statically typed language - meaning that we have define every object's type etc. This becomes a
* problem when we want our methods to accept different data types but function exactly the same as its other half. We
* can do several things:
*   - one is to change the number of parameters the method accepts
*   - change the data type of the parameters
*
* This style is know as a unique method signature, (i.e. every overloaded method has different data types -
* regardless the number of parameters given) - even with the same number of parameters, if you try to change the
* return type, Java will accept that method as an overloaded counterpart
*
*
* ---------------------------------------------------------------------------------------------------------------------
*
*
*/