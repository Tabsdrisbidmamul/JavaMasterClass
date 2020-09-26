package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    try (Socket socket = new Socket("localhost", 5000)) {

            socket.setSoTimeout(5000);
            // Client received request from Host Stream
            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Client sending out request to Host stream
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            // Client Request to Host (Message String)
            String echoString;

            // Host response to Client request (Message String)
            String response;

            do {
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();

                stringToEcho.println("Client sending this message to Host (server): " + echoString);
                if (!echoString.equalsIgnoreCase("exit")) {
                    response = echoes.readLine();
                    System.out.println("Received response from Host (server): " + response);
                }
            } while (!echoString.equalsIgnoreCase("exit"));

        } catch (SocketException e) {
            System.out.println("The Socket Time out");
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
