package com.company;

import javax.crypto.spec.PSource;

public class Main {

    public static void main(String[] args) {
        printYearsAndDays(525600);
        printYearsAndDays(1051200);
        printYearsAndDays(561600);
        printYearsAndDays(1440);
    }

    public static void printYearsAndDays(long minutes) {
        String msg = minutes < 0 ? "Invalid value" : minutes + " min = " + (minutes / (60 * 24 * 365) ) + " y " +
                ((minutes % (60 * 24 * 365) / 1440)) + " d ";
        System.out.println(msg);
    }
}
