package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(getLargestPrime(21));
    }

    public static int getLargestPrime(int number) {
        if (number <=1) return -1;

        int maxPrimeNumber;
        for (maxPrimeNumber = 2; maxPrimeNumber < number; maxPrimeNumber++) {
            if (number % maxPrimeNumber == 0) {
                number /= maxPrimeNumber;
                maxPrimeNumber--;
            }
        }
        return maxPrimeNumber;
    }

}
