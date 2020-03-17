package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //-Read the numbers from the console entered by the user and print the minimum and maximum number the user has entered.
        //-Before the user enters the number, print the message gEnter number:h
        //-If the user enters an invalid number, break out of the loop and print the minimum and maximum number.
        //
        //Hint:
        //-Use an endless while loop.
        //
        //Bonus:
        //-Create a project with the name MinAndMaxInputChallenge.

        Scanner scanner = new Scanner(System.in);

        int min = 0;
        int max = 0;

        while (true) {
            System.out.println("Enter number: ");

            if (!scanner.hasNextInt()) {
                break;
            } else {
                int numberInput = scanner.nextInt();

                if (min == 0 && max == 0) {
                    min = numberInput;
                    max = numberInput;
                } else {
                    min = Math.min(min, numberInput);
                    max = Math.max(max, numberInput);
                }
            }
            scanner.nextLine();
        }
        System.out.println("Minimum number entered: " + min + "\nMaximum number entered: " + max);

        scanner.close();
    }
}
