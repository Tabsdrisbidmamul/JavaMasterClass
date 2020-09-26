package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread {
    private Socket socket;

    public Echoer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Server Request Thread Stream
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Server Response Thread Stream
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String echoString = input.readLine();
                System.out.println("Received client input: " + echoString);

                if(echoString.equalsIgnoreCase("exit")) {
                    break;
                }

                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    System.out.println("Thread Interrupted");
                }

                output.println(echoString);
            }


        } catch (IOException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
