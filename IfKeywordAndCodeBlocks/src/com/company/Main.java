package com.company;

public class Main {

    public static void main(String[] args) {
        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

//        if (score < 5000 && score > 1000) {
//            System.out.println("Your score was less than 5000, but greater than 1000");
//        } else if (score < 1000){
//            System.out.println("Your score was less than 1000");
//        } else {
//            System.out.println("Got here");
//        }

        if (gameOver) {
            int finalScore = score + (levelCompleted * bonus);
            System.out.println("Your final score was " + finalScore);
        }

        if (gameOver) {
            score = 10000;
            levelCompleted = 8;
            bonus = 200;
            int finalScore = score + (levelCompleted * bonus);
            System.out.println("Your final score was " + finalScore);
        }


        // int savedFinalScore = finalScore;

        // challenge: print out a second score to the screen with the following: score set to 10,000,
        // levelCompleted set to 8, bonus set to 200, but make sure the first printout above still displays as well
    }
}

/*
* If - then - else statement
* The if, then, else statement allows us to execute certain portions of code given a particular test was true; if
* that is the case where the boolean condition is true, that section of code is therefore ran and then will skip or
* consequently not run the other else if or else parts of the code and move onto where the if-then-else ends
*
* if (cond number 1) {
*   execute code
* } else if (cond number 2) {
*   execute code
* } else {
*   default cond, execute code here
* }
* --------------------------------------------------------------------------------------------------------------------
* Scope
* In Java, when we use code blocks, we can use variables that are outside the code block and use them inside that
* code block, the same can be said - we can create variables within a code block. However we cannot used the newly
* created variables that were in the code block outside of it - that's because Java's garbage collector will come and
* delete any variables that are inside the code block once it has reached the end of that code block
*   -  if (gameOver) {
            int finalScore = score + (levelCompleted * bonus);
            System.out.println("Your final score was " + finalScore);
        }

        int savedFinalScore = finalScore;
*
* Here we can use the variables that are outside the local scope (code block), however we cannot use the variables
* from the local scope in the outer scope (i.e. int savedFinalScore = finalScore;)
*
* */
