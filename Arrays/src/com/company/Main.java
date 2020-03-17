package com.company;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//	    int[] myIntArray = new int[25]; //{1, 2, 3, 4, 5, 6, 7, 8 ,9 ,10};
////	    myIntArray[0] = 45;
////	    myIntArray[1] = 476;
////        myIntArray[5] = 50;  // element 6 is filled with value of 50
//        for(int i=0; i<myIntArray.length; i++) {
//            myIntArray[i] = i*10;
//        }
//
//        printArray(myIntArray);

        int[] myIntegers = getIntegers(5);
        for (int i=0; i<myIntegers.length; i++) {
            System.out.println("Element " + i + " typed value was " + myIntegers[i]);
        }
        System.out.println("The average is " + getAverage(myIntegers));



    }

    public static double getAverage(int[] array) {
        int sum = 0;
        for (int i=0; i<array.length; i++) {
            sum += array[i];
        }
        return (double) sum / array.length;
    }

    public static int[] getIntegers(int number) {
        System.out.println("Enter " + number + " integer values \r");
        int[] values = new int[number];

        for (int i=0; i<values.length; i++) {
            values[i] = scanner.nextInt();
            scanner.nextLine();
        }
        return values;
    }

    public static void printArray(int[] array) {
        for (int i=0; i<array.length; i++) {
            System.out.println("Element " + i + ", value is " + array[i]);
        }
    }
}

/*
* Arrays
* To make an array in Java we write square brackets [] right after the data type declaration
*   int[] ....
* This tells Java that we want to make a integer array
*
* after the declaration we can do 2 things, assign an given amount of space that the array can hold, or define the
* values already - and indirectly tell Java how much space to allocate to the array - by the number of values we have
* passed
*
*   int[] a = new int[10];
*
*           or
*   int[] a = {1, 2, 3, 4, 5, 6, 7, 8 ,9 ,10}; // this is an array initializer block {} and this was of initialising
*   // an array is known as an anonymous array
*
* NOTE: We cannot place a different data type/ object type in the array if we have defined it as another data type
* so with int array 'a', we can only place in integer values, Java will not accept doubles, longs or other objects etc.
*
* We could also use a for loop to initialise the values of the array like so
*   for(int i=0; i<a.length; i++) {
*       a[i] = i * 10;
*   }
*
* Arrays is a data structure that allows us to store multiple values of the same type into a single variable
* example: the default values of numeric array elements are set to zero, when initialised
*
* Arrays are zero indexed, meaning we start counting at 0, of course if we try to access an index which is out of
* range of the array, Java will give us an ArrayIndexOutOfBoundsException - which tells us that the index is out of
* range
*
* To access array elements - we use square brackets [int a] with an integer value to access the array index - this is
* know as the array access operator
*
*
*
* */