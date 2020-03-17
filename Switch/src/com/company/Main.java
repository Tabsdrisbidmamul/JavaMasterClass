package com.company;

public class Main {

    public static void main(String[] args) {
//        int value = 1;
//        if (value == 1) {
//            System.out.println("Value was 1");
//        } else if (value == 2) {
//            System.out.println("Value was 2");
//        } else {
//            System.out.println("Was not 1 or 2");
//        }

        int switchValue = 3;

        switch (switchValue) {
            case 1:
                System.out.println("Value was 1");
                break;

            case 2:
                System.out.println("Value was 2");
                break;

            case 3: case 4: case 5:
                System.out.println("Was 3, or a 4, or a 5");
                System.out.println("Actually it was a " + switchValue);
                break;

            default:
                System.out.println("Was not 1, 2, 3, 4 or 5");
        }

        // challenge:
        //      - create a new switch statement using char instead of int
        //      - create a new char variable and create
        //      - a new switch statement testing for:
        //          - A
        //          - B
        //          - C
        //          - D
        //          - E
        //  Display a message if any of these are found and then break, add a default which displays a message saying
        //  not found

        char character = 'A';
        switch (character) {
            case 'A':
                System.out.println("Value was 'A'");
                break;

            case 'B':
                System.out.println("Value was 'B'");
                break;

            case 'C':
                System.out.println("Value was 'C'");
                break;

            case 'D':
                System.out.println("Value was 'D");
                break;

            case 'E':
                System.out.println("Value was 'E");
                break;

            default:
                System.out.println("Value was not 'A', 'B', 'C', 'D', or 'E'");
                break;
        }

        String month = "JANUARY";
        switch (month.toLowerCase()) {
            case "january":
                System.out.println("Jan");
                break;

            case "June":
                System.out.println("Jun");
                break;

            default:
                System.out.println("Not sure");
        }
    }
}

/*
* Switch Statement
* The switch statement allows us to replicate an if-then-else statement, but we only test values against one variable
* (data type).
*
* int a = 2;
* switch(a) {
*   case 1:
*       -- do something if value is 1
*       break;
*   case 2:
*       -- do something if value is 2
*       break;
*   default:
*       -- do something if none of the values are 1 or 2
*       break;
* }
* The switch statement is compatiable with these 'primitive' data types char, int, byte - objects do work, but in
* Java 8 and above
* You must always place a 'break' at the end of every case, or the code will go through to another case - thus you
* will get unexpected output when executing
*
* They are both identical when executing
*
* if (a == 1) {
*   do something if value is 1
* } else if (a == 2) {
*   do something if value is 2
* } else {
*   do something if none of the values are 1 or 2
* }
*
* The if-then-else statement allows flexibility where you can test against multiple/ different values
* The switch statement only allows to be tested against one value
* */
