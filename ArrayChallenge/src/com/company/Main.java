package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create a program using arrays that sorts a list of integers in descending order.
        // Descending order is highest value to lowest.
        // In other words if the array had the values in it 106, 26, 81, 5, 15 your program should
        // ultimately have an array with 106,81,26, 15, 5 in it.
        // Set up the program so that the numbers to sort are read in from the keyboard.
        // Implement the following methods - getIntegers, printArray, and sortIntegers
        // getIntegers returns an array of entered integers from keyboard
        // printArray prints out the contents of the array
        // and sortIntegers should sort the array and return a new array containing the sorted numbers
        // you will have to figure out how to copy the array elements from the passed array into a new
        // array and sort them and return the new sorted array.

        int[] test = getInteger(5);
        printArray(test);
        System.out.println();
        test = sortIntegers(test);
        printArray(test);
    }

    public static int[] sortIntegers(int[] array) {
    /*Bubble Sort for n items, but in descending order
    * Average time complexity ~O(n^2)
    * Worst time complexity ~O(n^2)
    * */
//    int[] sortedArray = new int[array.length];
//    for(int i=0; i<array.length; i++) {
//        sortedArray[i] = array[i];
//    }
        int[] sortedArray = Arrays.copyOf(array, array.length);

        for(int i=0; i<(sortedArray.length - 1); i++){
            for (int j=0; j<(sortedArray.length) - 1 -i; j++){
                if(sortedArray[j] < sortedArray[j+1]) {
                    int placeholder = sortedArray[j];
                    sortedArray[j] = sortedArray[j+1];
                    sortedArray[j+1] = placeholder;
                }
            }
        }
        return sortedArray;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element " + i + " value: " + array[i]);
        }
    }

    public static int[] getInteger(int number) {
        System.out.println("Enter " + number + " integers into the console");
        int[] numArray = new int[number];
        for (int i = 0; i < number; i++) {
            int element = scanner.nextInt();
            numArray[i] = element;
        }
        return numArray;
    }
}
