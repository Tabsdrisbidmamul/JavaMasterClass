package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter how many numbers you want to enter");
        int number = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please enter [" + number + "]s");
        int[] array = readIntegers(number);

        int min = findMin(array);
        System.out.println("The minimum element is " + min);
    }

    private static int[] readIntegers(int count) {
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

//    private static int findMin(int[] array) {
//        int min = array[0];
//        for (int value : array) {
//            if (value < min) {
//                min = value;
//            }
//        }
//        return min;
//    }

    private static int findMin(int[] array) {
        Arrays.sort(array);
        return array[0];
    }
}
