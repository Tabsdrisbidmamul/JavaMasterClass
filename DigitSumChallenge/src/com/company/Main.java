package com.company;

public class Main {

//    Write a method with the name sumDigits that has one int parameter called number.
//
//    If parameter is >= 10 then the method should process the number and return sum of all digits, otherwise return -1 to indicate an invalid value.
//
//    The numbers from 0-9 have 1 digit so we donft want to process them, also we donft want to process negative numbers, so also return -1 for negative numbers.
//
//    For example calling the method sumDigits(125) should return 8 since 1 + 2 + 5 = 8.
//
//    Calling the method sumDigits(1) should return -1 as per requirements described above.
//
//    Add some code to the main method to test out the sumDigits method to determine that it is working correctly for valid and invalid values passed as arguments.
//
//            Hint:
//    Use n % 10 to extract the least-significant digit.
//    Use n = n / 10 to discard the least-significant digit.
//    The method needs to be static like other methods so far in the course.
//
//    Tip:
//    Create a project with the name DigitSumChallenge.

    public static void main(String[] args) {
        System.out.println(sumDigits(125));
        System.out.println(sumDigits(-1));
        System.out.println(sumDigits(1));
        System.out.println(sumDigits(123));
        System.out.println(sumDigits(12345));
        System.out.println(sumDigits(-123));
        System.out.println(sumDigits(10));

    }

    private static int sumDigits(int number) {
        if( number < 10 ) {
            return -1;
        }

        int sum = 0;
        while (number > 0) {

            // 125 -> 125 / 10 = 12
            // 12 * 10 = 120
            // 125 - 120 = 5
            // thus 125 % 10 = 5
            int numberToAdd = number % 10;
            sum += numberToAdd;
            number /= 10;
        }
        return sum;
    }
}
