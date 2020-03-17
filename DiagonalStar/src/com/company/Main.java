package com.company;

public class Main {

    public static void main(String[] args) {
	    printSquareStar(5);
	    printSquareStar(8);
	    printSquareStar(2);

    }

    public static void printSquareStar(int number) {
        if (number < 5) {
            System.out.println("Invalid Value");
        } else {
            for (int i=0; i < number; i++) {    // i is currentRow
                for(int j = 0; j < number; j++) {   // j is currentColumn
                    if (
                            i == 0
                            || i == (number - 1)
                            || j == 0
                            || j == (number - 1)
                            || j == i
                            || j == (number - 1) - i
                    ) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
}
