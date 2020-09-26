package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    try {
            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket datagramSocket = new DatagramSocket();


            Scanner scanner = new Scanner(System.in);
            String echoString;

            do{
                System.out.println("Enter a string to be echoed: ");
                echoString = scanner.nextLine();

                byte[] buffer = echoString.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
                datagramSocket.send(packet);

                byte[] buffer2 = new byte[50];
                packet = new DatagramPacket(buffer2, buffer2.length);
                datagramSocket.receive(packet);
                System.out.println("Text received is: " + new String(buffer2));

            } while (!echoString.equalsIgnoreCase("exit"));

        } catch (SocketTimeoutException e) {
            System.out.println("socket timed out");
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
