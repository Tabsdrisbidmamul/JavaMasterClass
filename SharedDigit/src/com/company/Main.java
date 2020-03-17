package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(hasSharedDigit(12, 23));
        System.out.println(hasSharedDigit(15, 26));
        System.out.println(hasSharedDigit(9, 9));
    }

    public static boolean hasSharedDigit(int a, int b) {
        if ( (a < 10 || a > 99) || (b < 10 || b > 99) ) return false;

        int hold = a;
        while (b > 0) {
            int digitTwoToCompare = b % 10;

            a = hold;
            while (a > 0) {
                int digitOneToCompare = a % 10;
                if (digitOneToCompare == digitTwoToCompare) {
                    return true;
                }
                a /= 10;
            }
            b /= 10;
        }
        return false;
    }
}
