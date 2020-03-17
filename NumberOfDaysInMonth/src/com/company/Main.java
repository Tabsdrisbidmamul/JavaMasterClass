package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(isLeapYear(-1600));
        System.out.println(isLeapYear(1600));
        System.out.println(isLeapYear(2017));
        System.out.println(isLeapYear(2000));
        System.out.println(isLeapYear(2020));
        System.out.println(isLeapYear(2018));

        System.out.println(getDaysInMonth(1 , 2020));
        System.out.println(getDaysInMonth(2, 2020));
        System.out.println(getDaysInMonth(2, 2018));
        System.out.println(getDaysInMonth(-1, 2020));
        System.out.println(getDaysInMonth(1, -2020));

    }

    public static boolean isLeapYear(int year) {
        return (year >= 1 && year <= 9999) && (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    public static int getDaysInMonth(int month, int year) {
        if ( (month < 1 || month > 12) || (year < 1 || year > 9999) ) {
            return -1;
        }
        int daysInMonth = 0;
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                daysInMonth = 31;
                break;
            case 4: case 6: case 9: case 11:
                daysInMonth = 30;
                break;
            case 2:
                daysInMonth = isLeapYear(year) ? 29 : 28;
                break;
            default:
                daysInMonth = 0;
                break;
        }
        return daysInMonth;
    }
}
