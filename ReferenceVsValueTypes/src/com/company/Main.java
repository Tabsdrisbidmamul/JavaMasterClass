package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	    int myIntValue = 10;
	    int anotherIntValue = myIntValue;

        System.out.println("myIntValue = " + myIntValue);
        System.out.println("anotherIntValue = " + anotherIntValue);

        anotherIntValue++;

        System.out.println("myIntValue = " + myIntValue);
        System.out.println("anotherIntValue = " + anotherIntValue);

        int[] myIntArray = new int[5];
        int[] anotherIntArray = myIntArray;

        System.out.println("myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("anotherIntArray = " + Arrays.toString(anotherIntArray));

        anotherIntArray[0] = 1;

        System.out.println("after change myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("after change anotherIntArray = " + Arrays.toString(anotherIntArray));

        anotherIntArray = new int[] {4, 5, 6, 7 ,8};

        modifyArray(myIntArray);

        System.out.println("after modify myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("after modify anotherIntArray = " + Arrays.toString(anotherIntArray));
    }

    private static void modifyArray(int[] array) {
        /*
        * when an object reference is passed as a parameter - so an Array object - the reference itself gets copied -
        *  so instead of having say on object pointing to where the array is in memory, we have 2 references being
        * pointed to memory of where the array is - and in this case, all the changes are made via the copied
        * reference and not the actual argument that was passed to the method.
        * */


        array[0] = 2;

        /*
        * We have actually de-referenced the copied reference variable by using the new operator and setting it equal
        * to an anonymous array.
        * */
        array = new int[] {1, 2, 3, 4, 5};
    }
}


/*
* Value Types
* These are the 8 primitive data types in Java, int, long, short, byte, boolean, char, float and double
* When assigning a variable to any of these types, they hold an actual value (i.e when in looking in memory - these
* variables actually hold the contents to what they are assigned to when coded)
*
* Its different for reference types, as they hold another memory address (one different from their own) to where the
* information is stored (so for objects, the variable is a reference type, as it only hold information about where
* the data is stored - and does not anything about it, other than where it is)
*
*
* */