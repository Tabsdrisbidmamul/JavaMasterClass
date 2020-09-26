package com.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {

    public static void main(String[] args) {
	    try {
            DatagramSocket socket = new DatagramSocket(5000);

            while (true) {
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                System.out.println("Text received is: " + new String(buffer));

                String returnString = "Echo: " + new String(buffer);
                byte[] buffer2 = returnString.getBytes();

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, buffer2.length, address, port);
                socket.send(packet);

            }


        } catch (SocketException e) {
            System.out.println("SocketEjection: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}

/*
* UDP (User Datagram Protocol)
* When using TCP handshaking has to take place between the client and the sever, this ultimately will connect the
* client to the server - this is a bi-directional connection and is a tight coupling between the two devices
*
* The TCP protocol also has error checking with it, and will re-send packets that don't make it to the server, but
* doing all this checking requires some overhead which cuts into performance
*
* UDP on the other hand does not do handshaking between the client and the server - and the destination host (may or
* not be a server) does not actually send any responses to the recipient
*
* So we use UDP we don't need a reliable connection or a two-way connection, but where speed is essential
*
* Datagram
* UDP uses Datagram, which is a self-contained message and is not guaranteed to arrive at its destination
*
* UDP is often used for time-sensitive communication and when losing the odd message here and there will not matter
*
* Examples
* VOIP (Voice over IP) like Skype or streaming usually use UDP where speed is more important really than ensuring
* that absolutely every packet arrives at the host
*
* Another thing about UDP is that the data received is replaced bny more data immediately when it reaches the client
*
*
* UDP Server code
* - We create a DatagramSocket instance we pass the port 5000 as the constructor value
* - for our while loop we create byte array buffer - this buffer is used to store the input received from the client
*   here we allocated 50 bytes for the buffer
* - We then create a DatagramPacket instance, this will receive a packet from the socket, it will place it into the
*   buffer and the packet must be less than the length of the buffer
* - we use the method receive from the DatagramSocket instance - this is a blocking method and will block until it
*   has received a packet from a client
*
* 	    try {
            DatagramSocket socket = new DatagramSocket(5000);

            while (true) {
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                System.out.println("Text received is: " + new String(buffer));
            }


        } catch (SocketException e) {
            System.out.println("SocketEjection: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
*
* UDP Server steps
* 1. We create a DatagramSocket instance - this will be the socket used to communicate between server and client
* 2. We must create a byte buffer - the packet is in bytes anyway, makes it easier to work with - this buffer will
*    contain the packet information we receive from the socket
* 3. We create a DatagramPacket, and we tell it where it can place the packet information and the maximum length a
*    packet can be to be accepted
* 4. We now wait and listen on the Input Stream channel - this is on the Socket - by using the method receive() from
*    the DatagramSocket instance and we pass it the value of what an ideal DatagramPacket should be
*
*
* UDP Client Code
*
* - First we create an InetAddress instance and we use this to get the IP address of where the Host is being hosted on
*   using the getLocalHost() method, there is also a getByName() method, where we pass in the String representation of
*   the IP address of the Server
* - We the create the DatagramSocket instance
* - Create Input Stream to receive the packet data from the server
* - We create a byte buffer, and we convert our String message to a byte format ready to be sent off
* - We then create a DatagramPacket instance, and we tell it we the information is coming from, the length of that
*   information, the address (IP address or where the Host is located) and the port it is listening on
* - Using the send() method from the DatagramSocket instance we send off the packet to the host
* 	    try {
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
            } while (!echoString.equalsIgnoreCase("exit"));

        } catch (SocketTimeoutException e) {
            System.out.println("socket timed out");
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
*
* UDP Client Steps
* 1. We create an InetAddress instance to tell the Client where the host is located by passing in its IP Address
*    using the getByName()
* 2. We create a DatagramSocket instance
* 3. We create our Input Stream
* 4. Create a byte array buffer to have the information stored in and ready to be sent off
* 5. We create DatagramPacket instance and we tell it where the packet data is stored, the length of the packet data,
*    the address of where the Host is located and the port it is listening on
* 6. The packet is ready to be sent off, so we use the DatagramSocket instance to use the send() method passing in
*    the packet itself to the Host
*
*
* UDP send response back to the Client
*
*
* UDP Server code
* - We create an InetAddress instance and we get the address of the client from the UDP packet using the getAddress()
*   from the DatagramPacket instance
* - We then get the port from the DatagramPacket instance using the getPort() method
* - We then create a new instance of DatagramPacket passing in the new byte array buffer we construct - this can be
*   anything from using the packet sent from the client, by telling it where the data is stored, the length of the
*   data, the address of where the Client is located and the port it is listening on
* - We then use the DatagramSocket instance to use the send() method and we pass in the packet data itself
* -
*               String returnString = "Echo: " + new String(buffer);
                byte[] buffer2 = returnString.getBytes();
*
*               InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, buffer2.length, address, port);
                socket.send(packet);
*
*
* UDP Client Code
* - We create a new byte array buffer to place the information in
* - We use the DatagramPacket instance to tell it where to place the information in, and it capacity
* - We use the DatagramSocket instance to use the receive() method on the packet data we expect to come, this is
*   blocking method and will wait listen till it has received a packet
*
*
*
*               byte[] buffer2 = new byte[50];
                packet = new DatagramPacket(buffer2, buffer2.length);
                datagramSocket.receive(packet);
                System.out.println("Text received is: " + new String(buffer2));
*
*
*
*
*
*
*
*
* */