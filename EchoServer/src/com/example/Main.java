package com.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)){
//            Socket socket = serverSocket.accept();
//            System.out.println("Client connected");
//
//            // Host Listening to Request Stream
//            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            // Host Sending out response Stream
//            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);


            while (true) {
                new Echoer(serverSocket.accept()).start();
//                Socket socket = serverSocket.accept();
//                Echoer echoer = new Echoer(socket);
//                echoer.start();
            }

        } catch (IOException e) {
            System.out.println("Server Exception: " + e.getMessage());
        }
    }
}

/*
* Networking Terminology
* network: Is a system of computers connected together so they can share resources and communicate with each other
* Networking: Refers to how the connected computers communicate
*
* These days networking is usually discussed in the context of the Internet, but computers may also communicate
* across a private network (called intranets). Many networking began from intranets and are still common today in
* businesses - e.g. office workers do not have a printer at their desks, they share a printer - when they want to
* print a document - the document is sent to the printer over the business intranet
*
* A common network configuration is client/ server
* - that one or more hosts on the network are acting as servers, and the other hosts are clients that connect to the
*   server. At a high level, that is how the Internet operates
* - The browser is the client and when you type in a web address, it connects to the server that has the files for
*   the web site at that address
* - As we mentioned that you can have a client/ server interaction on the same same host - e.g. the MySQL database
*   comes with a workbench you can use to perform database operations. The workbench is the client and it connects to
*   the MySQL database server
* - Another example is the local web development, it is possible to run an apache or IIS server on your computer and
*   connect to it using a browser on your computer. That is often what web developers do when they are working on a
*   website
*
* Transport Protocols
* Computer on a network communicate with each other using a transport protocol, in Java we can use the TCP and UDP
* protocols in this lecture
*
* Generally a computer will have one physical connection to a network and anything sent to the computer from other
* hosts on the network will arrive through that connection. But sometimes the same computer will be running multiple
* applications that want data from the network - e.g. at any one time, you might have a browser open, a chat
* application open, be streaming music etc.
*
* When data arrives at one physical connection to the network, how does it get routes to the target application?
* Ports
* Each application that needs data from the network is assigned a port (this includes clients connection to a server
* that is on the same machine). When data arrives, the port number is used to route the data to the application that
* is waiting for it.
*
* Internet Protocol (IP) Address
* IPv4 - x.x.x.x (composed of 4 bytes) written as 4 integers separated by dots -  192.168.0.45
* IPv6 - x.x.x.x x.x.x.x x.x.x.x x.x.x.x (composed of 16 bytes) written in hexadecimal and separated by colons - fe80::41bd:9993:f606:5902%12
*
* These are unique identifiers to identify a device on a network, at the time IPv4 was enough (4 billion), however
* with more and more smart appliances we need more addresses - so IPv6 which was introduced covered (2^128)
*
* java.net package
* This package contains 2 sets of APIs: the low-level and high-level API
*
* Low-level API
* You will have to be more aware of networking concepts, still when using Java to do network coding, you are always
* working with abstractions regardless of whether you use high or low level API
*
* When using the low level API we will be using sockets to establish connections send requests and receive responses
* A socket is one end-point of the two-way connections. The client will have a socket and the server will have a socket
*
* When you have multiple clients connecting to the same server, they will use the same port number, but each client
* will have its own socket. You will the Socket class for the client socket and the ServerSocket class for the
* server's socket
*
* We are going to make 2 applications one for the Server and the other for the Client - which will mimic a client/
* server model
*
* Server Application
* We use a try-resources here to open a connection as the ServerSocket - and we pass the value 5000 which is the port
* the ServerSocket is going to connect to.
*
* After we have have established a ServerSocket instance, we do all forms of communication via a Socket instance so
* we use the method accept() from the ServerSocket class
*
* accept()
* Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
* Meaning this will run indefinitely until it has got a connection - in this case it will not go to the next line
* (the println) as we have not implemented the client application to communicate to the server yet
* Because it has not done a handshake to any clients yet
*
* To receive and respond we use Input/ Output Streams to manage that
*
* Input Stream
* This Stream is going to listen and receive requests from any client wishing to communicate to the server
* We use a BufferedReader to instance to do so and on the RHS we use InputStreamReader and its value is from using
* socket.getInputStream() method
*
* getInputStream()
* Returns an input stream for this socket.
* If this socket has an associated channel then the resulting input stream delegates all of its operations to the
* channel. If the channel is in non-blocking mode then the input stream's read operations will throw an
* java.nio.channels.IllegalBlockingModeException. (if you are Java.NIO)
*
* This will basically delegate all the work of the Socket to our BufferedReader - which allows us to listen to request
*
* Output Stream
* This Stream will send out requests back to the client machines from the host (server)
* We use a PrintWriter instance and simply pass the socket.getOutputStream() method and its seconds argument is true
* (which says to the PrintWriter to set autoFlush to true - which will clear the Output Stream after every request
* has been sent out to a client device)
*
* getOutputStream()
* Returns an output stream for this socket.
* If this socket has an associated channel then the resulting output stream delegates all of its operations to the
* channel. If the channel is in non-blocking mode then the output stream's write operations will throw an
* java.nio.channels.IllegalBlockingModeException (if using java.NIO)
*
* Basically allows us to get request out from the host to the clients
*
* in the while loop we basically want an infinite loop - as the connection between the Host (Server) to the Client
* must always be maintained until the Client disconnects - it is unlikely that the Host will disconnect from the
* Client (but it can)
*
* We store all Client request (in this case String messages) using the Input Stream by using the readLine() method
* in the BufferedReader Class
*
* We check if the request (input Sting) wants to disconnect current connection - if so break out of the loop
* If not we use the Output Stream - to respond to the Client by echoing back the Input String with the String literal
* "Echo from sever: " prefixed to it
*
* The IOException covers nearly all of the Exceptions caused from Socket and I/O
*
*         try (ServerSocket serverSocket = new ServerSocket(5000)){
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // Host Listening to Request Stream
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Host Sending out response Stream
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);


            while (true) {
                String echoString = input.readLine();
                if(echoString.equalsIgnoreCase("exit")) {
                    break;
                }
                output.println("Echo from server: " + echoString);
            }

        } catch (IOException e) {
            System.out.println("Server Exception: " + e.getMessage());
        }
*
*
* Client Application
* This follows a similar pattern to the Server application
* In the trr we use a Socket instance, we pass in 2 arguments (the String value of the IP address of the server) and
* the second is the port the server is connected to
*
* We use the Input and Output Streams like last time - one for listening to responses from the Host and the other to
* send request to the Host
*
* we use a do-while loop to ensure at least that the client connects to the server at least once (the client can type
* "exit" straight away - but that will not exit the loop - ensuring that the client has at least connected to the
* server once in its lifetime
*
* We use a scanner to read input from the console and send that out to the host by using the Output Stream
*
* We use the BufferedRead to read in the responses from the Host
*
*
* 	    try (Socket socket = new Socket("localhost", 5000)){
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
                if(!echoString.equalsIgnoreCase("exit")) {
                    response = echoes.readLine();
                    System.out.println("Received response from Host (server): " + response);
                }
            } while (!echoString.equalsIgnoreCase("exit"));


        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
*
* Now these are 2 separate projects in IntelliJ - we run the Server application first then the Client - once started
* we get the message "Client Connected" from the Server meaning a connection has been established and the accept()
* method has stopped blocking because a connection has been made - we send messages in via the client and the server
* will send responses back to the Client which we see in the Client application console
*
*
* Multi-Threaded Server
* After doing some re-juggling around with the code - we can simulate the Server to allow multiple hosts connections
* however there is a limit to that
*
* Problems
* - The Host has to wait for the first client connection to send in a request before being able to communicate to the
*   another client
* - The Host can timeout or seem unresponsive to multiple clients connecting
* - The Host may not terminate (shutdown) when all clients have disconnected (this can be mitigated by manually
*   shutting down the host, because mainly you want to run Servers 24/7)
*
* In all it leads to a very unresponsive experience - to fix these problems we use Threads to handle each individual
* client
*
*
* Thread Class
* This class takes nearly all of the original Server code and places it into this Thread class
*
* What we ultimately want, is each Thread to have their own Input/ Output Stream to handle the Client requests - and
* to do this each instance of a client (so when a new client joins) we basically want to run the accept() method
* again - to allow us a connection to the client on that socket (hence we have the constructor that accepts a Socket
* instance)
*
* In this code we have to manually close the Socket connection - because we are not opening a Socket - rather taking
* over you could say a portion of it
*
*
* ----------------------------------------------------------------------------------------------------------------------
*                                              Socket Port: 5000
*               _______________________________________|___________________________________________________
*               |                                                                                         |
*  Thread 1 Connected (Client 1)            Thread 2 Disconnected (Client 2)             Thread 3 Connected (Client3)
*
* ----------------------------------------------------------------------------------------------------------------------
*
* They each share the instance of the Socket but not owning it to say the least, when a Thread closes the Socket - it
* closes its own Socket and not the main Socket to say the least
*
* That is how we can multiple Threads share a Server Socket instance amongst themselves
*
* When we run the code the accept method will normally be blocking on each individual Thread until a Client has
* connected, when a client has connected then the code again starts blocking waiting for input
*
* The blocking is contained within each individual Thread and they do not interfere with one another, this leads to a
*  more responsive user experience
*
*
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

                if(echoString.equalsIgnoreCase("exit")) {
                    break;
                }

                output.println(echoString);
            }


        } catch (IOException e) {
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
*
*
* Socket Timeout
* We can set a timeout value for the client when connecting to a Host, the client can do various things after a
* timeout, they can try to reconnect, terminate the connection or do something else
*
* This is to report back to the user, so to the end-user it does not seem that the application has died or crashed
*
* We also need to catch SocketTimeoutException, when using this bit of code
*   socket.setSoTimeout(5000);
*
*
*
*
* */
