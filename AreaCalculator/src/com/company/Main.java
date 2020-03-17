package com.company;

public class Main {
    private static final double PI = Math.PI;

    public static void main(String[] args) {
        System.out.println(area(5.0d));
        System.out.println(area(-1.0d));
        System.out.println(area(0.0d));
        System.out.println(area(5, 6));
        System.out.println(area(-1.0, 8));
        System.out.println(area(-1.0d));
        System.out.println(area(0.0d));
    }

    private static double area(double radius) {
        return radius < 0.0d ? -1.0d : PI * Math.pow(radius, 2);
    }

    private static double area(double length, double width) {
        return length < 0.0d || width < 0.0d ? -1.0d : length * width;
    }
}
