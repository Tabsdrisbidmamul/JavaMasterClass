package com.company;

public class Main {

    public static void main(String[] args) {
//        System.out.println(getDigitCount(100));
//        System.out.println(getDigitCount(10));
//        System.out.println(getDigitCount(1));
//        System.out.println(getDigitCount(-1));

        System.out.println(reverse(-2));

//        numberToWords(123);
//        numberToWords(1010);
//        numberToWords(1000);
//        numberToWords(-12);
    }

    public static void numberToWords(int number) {
        if (number < 0 ) System.out.println("Invalid Value");

        if (number == 0) System.out.print("Zero ");

        if (reverse(number) == -1) {
            return;
        } else {
            int reversedNumber = reverse(number);
            int originalDigitCountNumber = getDigitCount(number);
            int reversedDigitCountNumber = getDigitCount(reversedNumber);

            while (reversedNumber > 0) {
                int digit = reversedNumber % 10;

                switch (digit) {
                    case 0:
                        System.out.print("Zero ");
                        break;
                    case 1:
                        System.out.print("One ");
                        break;
                    case 2:
                        System.out.print("Two ");
                        break;
                    case 3:
                        System.out.print("Three ");
                        break;
                    case 4:
                        System.out.print("Four ");
                        break;
                    case 5:
                        System.out.print("Five ");
                        break;
                    case 6:
                        System.out.print("Six ");
                        break;
                    case 7:
                        System.out.print("Seven ");
                        break;
                    case 8:
                        System.out.print("Eight ");
                        break;
                    case 9:
                        System.out.print("Nine ");
                        break;
                    default:
                        System.out.print("Invalid Value");
                        break;
                }
                reversedNumber /= 10;

            }
            while (originalDigitCountNumber > reversedDigitCountNumber) {
                System.out.print("Zero ");
                reversedDigitCountNumber++;
            }
        }
    }

    public static int reverse(int number) {
        int orginalNumber = number;
        number = Math.abs(number);
        int reverse = 0;

        while (number > 0) {
            int lastDigit = number % 10;
            reverse *= 10;
            reverse += lastDigit;
            number /= 10;
        }

        return orginalNumber < 0 ? -1 * (reverse) : reverse;
    }

    public static int getDigitCount(int number) {
        if (number < 0) return -1;

        if(number == 0 ) return 1;

        int count = 0;
        while (number > 0) {
            int digit = number % 10;
            number /= 10;
            count++;
        }
        return count;
    }
}
