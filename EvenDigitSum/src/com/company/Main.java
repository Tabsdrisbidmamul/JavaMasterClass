package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(getEvenDigitSum(123456789));
        System.out.println(getEvenDigitSum(252));
        System.out.println(getEvenDigitSum(-22));
    }

    public static int getEvenDigitSum(int number) {
        if (number < 0) return -1;

        int sum = 0;
        while(number > 0) {
            int leastDigit = number % 10;
            if(leastDigit % 2 == 0) {
                sum += leastDigit;
            }
            number /= 10;
        }
        return sum;
    }
}
