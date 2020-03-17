package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("hasTeen " + hasTeen(9, 99, 19));
        System.out.println("hasTeen " + hasTeen(22, 15, 42));
        System.out.println("hasTeen " + hasTeen(22, 23, 34));

        System.out.println("isTeen " + isTeen(9));
        System.out.println("isTeen " + isTeen(13));
    }

    public static boolean hasTeen(int a, int b, int c) {
        return (a >= 13 && a <= 19) || (b >= 13 && b <= 19) || (c >= 13 && c <= 19);
    }

    public static boolean isTeen(int a) {
        return ( a >= 13 && a <= 19 );
    }
}
