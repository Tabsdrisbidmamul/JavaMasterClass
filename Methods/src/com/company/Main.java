package com.company;

public class Main {

    public static void main(String[] args) {
        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        int finalScore = calculateScore(gameOver, score, levelCompleted, bonus);
        System.out.println("Your final score was " + finalScore);

        score = 10000;
        levelCompleted = 8;
        bonus = 200;

        finalScore = calculateScore(gameOver, score, levelCompleted, bonus);
        System.out.println("Your final score was " + finalScore);

        // Challenge: Create a method called displayHighScorePosition
        // it should allow a player's name and their position on the high score table
        // you should then output a message saying like "[player's name] managed to get into position [position] on
        // the high score table"
        //
        // Create s 2nd method called calculateHighScorePosition
        // it should be sent one argument only, the player score
        // it should return an int
        // the data should be
        // 1 if the score is >= 1000
        // 2 if the score is >= 500 and < 1000
        // 3 if the score is >= 100 and < 500
        // 4 in all other cases
        // call both methods and display the results of the following
        // a score of 1500, 900, 400 and 50
        
        int highScorePostion = calculateHighScorePosition(1000);
        displayHighScorePosition("Paul Steins", highScorePostion);
        
        highScorePostion = calculateHighScorePosition(900);
        displayHighScorePosition("Smith Gordon", highScorePostion);

        highScorePostion = calculateHighScorePosition(400);
        displayHighScorePosition("Mick Linwood", highScorePostion);
        
        highScorePostion = calculateHighScorePosition(50);
        displayHighScorePosition("Jerry Down", highScorePostion);
        
    }

    public static void displayHighScorePosition(String playerName, int position) {
        System.out.println(playerName + " managed to get into position " + position + " on the high score table");
    }

    public static int calculateHighScorePosition(int playerScore) {
        if (playerScore >= 1000) {
            return 1;
        } else if (playerScore >= 500) {
            return 2;
        } else if (playerScore >= 100) {
            return 3;
        }
        return 4;
    }

    public static int calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {
        if (gameOver) {
            int finalScore = score + (levelCompleted * bonus);
            finalScore += 2000;
            return finalScore;
        }
        return -1;
    }


}
/*
* Methods
* When writing methods, we must ensure these keywords are given
* [modifier] (optional: static) (optional:return data type) (if method returns values) (optional: void) methodName
* (paramList) {
*   code body
* }
* static: we don;t have to define an object, we can run the method anytime, anywhere, without making an object of the
* class that houses this method
*
* data type: explicitly state to Java what data type we returning and to ensure that the correct data type is held in
* correct variables etc.
*
* void: to tell Java that this method does not return a value
*
* paramList: we must state the data type and the parameter name for the list of arguments the method can accept
*
* ---------------------------------------------------------------------------------------------------------------------
* Calling a method
* To call a method in Java, we simply write: methodName([if given]argumentList);
* Java will then jump to the method and execute the method body of code, once completed it will jump back to where it
* left off
*
* If you want to hold the return value from the method, store it in a variable with the same data type as the
* method's return data type
*
*   - public static void calculatedScore(boolean gameOver, int score, int levelsCompleted, int bonus) {
        if (gameOver) {
            int finalScore = score + (levelCompleted * bonus);
            finalScore += 1000;
            return finalScore;
        }
        return -1;
    }
*
* int finalScore = calculatedScore(boolean gameOver, int score, int levelsCompleted, int bonus);
*
* NOTE:When Java reaches a return statement, it will immediately jump out of the method with the return value to the
* point where it was called
*
* */
