package com.example;

public class Series {
    public static int nSum(int num) {
        int sum = 0;
        for(int i=0; i<num+1; i++) {
            sum += i;
        }
        return sum;
    }

    public static int factorial(int num) {
        // Recursive method for factorial - maximum depth reached very quickly
//        if(num == 0 || num == 1) {
//            return num;
//        } else {
//            return factorial(num-1) * num;
//        }
        int fac = 1;
        if(num == 0 || num == 1) {
            return num;
        }
        for (int i=2; i<num+1; i++) {
            fac *= i;
        }
        return fac;
    }

    public static int fibonacci(int num) {
        // recursive method
//        if(num == 0 || num == 1) {
//            return num;
//        } else {
//            return fibonacci(num - 1) + fibonacci(num - 2);
//        }
        if (num == 0) {
            return num;
        }
        int second, first = 0;
        int currentNumber = 1;
        for (int i =1; i<num; i++) {
            second = first;
            first = currentNumber;
            currentNumber = second + first;
        }
        return currentNumber;
    }

}
