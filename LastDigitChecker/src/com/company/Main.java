package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(hasSameLastDigit(41, 22, 71));
        System.out.println(hasSameLastDigit(23, 32, 42));
        System.out.println(hasSameLastDigit(9, 99, 999 ));
//        System.out.println(isValid(10));
//        System.out.println(isValid(468));
//        System.out.println(isValid(1051));
//        System.out.println((isValid(9) ||  isValid(99) || isValid(999)));

    }

    public static boolean hasSameLastDigit(int a, int b, int c) {
        if (isValid(a) && isValid(b) && isValid(c)) {
            int leastSigDigit_a = a % 10;
            int leastSigDigit_b = b % 10;
            int leastSigDigit_c = c % 10;

            return  (leastSigDigit_a == leastSigDigit_b) || (leastSigDigit_b == leastSigDigit_c) ||
                    (leastSigDigit_a == leastSigDigit_c);
        }
        return false;
    }

    public static boolean isValid(int a) {
        return a >= 10 && a <= 1000;
    }
}
