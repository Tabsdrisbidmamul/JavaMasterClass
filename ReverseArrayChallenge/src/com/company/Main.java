package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = new int[] {1, 2, 3, 4, 5, 6};
        System.out.println("Non Revered Array: " + Arrays.toString(array));

        reverse(array);
        System.out.println("Reversed Array: " + Arrays.toString(array));
    }

    private static void reverse(int[] array) {
        int pointer = 0;
        for(int i = (array.length - 1); i >= array.length / 2; i--) {
            int temp = array[pointer];
            array[pointer] = array[i];
            array[i] = temp;
            pointer++;
        }
    }
}
