package com.company;

public class Printer {
    private double tonerLevel = 100.0;
    private int numberOfPagesPrinted;
    private boolean duplexPrinter;

    public Printer(double tonerLevel, boolean duplexPrinter) {
        if(tonerLevel <= 0.0 || tonerLevel > 100.0) {
            System.out.println("Toner is empty, cannot print - please refill!");
        } else {
            this.tonerLevel = tonerLevel;
        }
        this.numberOfPagesPrinted = 0;
        this.duplexPrinter = duplexPrinter;
    }

    public void fillUpToner(double topUp) {
        if( !(( (Math.abs(topUp) / 100) + (tonerLevel / 100) ) <= 1.0) ) {
            System.out.println("cannot fill up the toner");
        } else {
            tonerLevel += topUp;
            System.out.println("Toner top upped, remaining toner level is " + tonerLevel + "%");
        }
    }


    public void printPage(int page) {
        if (duplexPrinter) {
            numberOfPagesPrinted += (page / 2) + (page % 2);
            System.out.println("Page printed (duplex printer):\n\tTotal number of pages printed is " + numberOfPagesPrinted);
        } else {
            numberOfPagesPrinted += page;
            System.out.println("Page printed:\n\tTotal number of pages printed is " + numberOfPagesPrinted);
        }
    }

    public double getTonerLevel() {
        return tonerLevel;
    }

    public int getNumberOfPagesPrinted() {
        return numberOfPagesPrinted;
    }

    public boolean isDuplexPrinter() {
        return duplexPrinter;
    }
}
