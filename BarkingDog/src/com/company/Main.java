package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(shouldWakeUp(true, 1));
        System.out.println(shouldWakeUp(false, 2));
        System.out.println(shouldWakeUp(true, 8));
        System.out.println(shouldWakeUp(true, -1));
    }

    public static boolean shouldWakeUp(boolean barking, int hourOfDay) {
        if ( barking && (hourOfDay < 0 || hourOfDay > 23) ) {
            return false;
        } else return (barking && hourOfDay < 8) || (barking && hourOfDay > 22);
    }
}
