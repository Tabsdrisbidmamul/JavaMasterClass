package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your year of birth");

        boolean hasNextInt = scanner.hasNextInt();

        if (hasNextInt) {
            int yearOfBirth = scanner.nextInt();
            scanner.nextLine();  // handle next line character (enter key)

            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            int age = 2020 - yearOfBirth;

            if (age >= 0 && age <= 100) {
                System.out.println("Your name is " + name + ", and you are " + age + " years old.");
            } else {
                System.out.println("Invalid year of birth");
            }
        } else {
            System.out.println("Unable to parse year of birth");
        }

        scanner.close();
    }
}

/*
* Scanner Class
* This is a built-in class within Java, to create a Scanner object, we must instantiate it, by passing in 'System.in'
* as an argument to the Scanner Class constructor - this tells Java that it should read input (from the keyboard, etc)
*
* After doing that, we want to get input from the user, so we use the Scanner methods to do - which is nextLine (for
* the majority of the characters) but if we want to parse the String to a data type - so String to int, we can use
* the Scanner methods which are next<data type name> which will look at the input String and convert it to the
* respective data type (errors: if the user passes in the wrong input, error will occur as it will expect say an int,
* float etc.)
*
* after that, you can save the input within a variable and use it for whatever
*
* after each use of the scanner, it is recommended to close the scanner - i.e tell Java to terminate the Scanner's
* use of memory; we are calling the garbage collector, and freeing up system resources effectively.
* */
