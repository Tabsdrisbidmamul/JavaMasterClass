package com.company;

public class Main {

    public static void main(String[] args) {
	    printMegaBytesAndKiloBytes(2500);
	    printMegaBytesAndKiloBytes(-1024);
	    printMegaBytesAndKiloBytes(5000);
	    printMegaBytesAndKiloBytes(1024);
	    printMegaBytesAndKiloBytes(3000);
    }

    public static void printMegaBytesAndKiloBytes(int kiloBytes) {
        if ( kiloBytes < 0 ) {
            System.out.println("Invalid Value");
        } else {
            long megaBytes = (long)Math.floor((double)kiloBytes / 1024);
            long remainKilobytes = kiloBytes % 1024;
            System.out.println(kiloBytes + " KB = " + megaBytes + " MB " + "and " + remainKilobytes + " KB");
        }
    }
}
