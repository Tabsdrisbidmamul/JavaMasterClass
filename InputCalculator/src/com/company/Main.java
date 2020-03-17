package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        inputThenPrintSumAndAverage();
    }

    public static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        long avg;
        int count = 0;

        while (true) {
            if (!scanner.hasNextInt()) {
                break;
            } else {
                int number = scanner.nextInt();

                sum += number;
                count++;
            }
            scanner.nextLine();
        }
        avg = sum == 0 && count == 0 ? 0: Math.round( (double) sum / count);
        System.out.println("SUM = " + sum + " AVG = " + avg);

        scanner.close();
    }
}
