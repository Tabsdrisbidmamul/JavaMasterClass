package com.example;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Message message = new Message();
        new Thread(new Writer(message)).start();
        new Thread(new Reader(message)).start();
    }
}

class Message {
    private String message;
    private boolean empty = true;

    /*
    * Read() will be used by the consumer to read the message
    *
    * */
    public synchronized String read() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = true;
        notifyAll();
        return message;
    }

    /*
    * Write() will be used by the producer to write the message
    *
    * */
    public synchronized void write(String message) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}

class Writer implements Runnable {
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String[] messages = {
                "Humpty Dumpy sat on a wall",
                "Humpty Dumpy had a great fall",
                "All the king's horses and all the king's men",
                "Couldn't put Humpty together again"
        };

        Random random = new Random();
        for (String msg: messages) {
            message.write(msg);

            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        message.write("Finished");
    }
}

class Reader implements Runnable {
    private Message message;

    public Reader(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (String msg = message.read(); !msg.equalsIgnoreCase("Finished"); msg = message.read()) {
            System.out.println(msg);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


/*
* Producer and Consumer example
* We are going to create a class which will create a message
* We are going to have 2 synchronised methods one which will create a message and the other which will read the message
*
* The methods are synchronised because we don't want say the Writer Thread to write a new message whilst the Read
* Thread is reading the message
*
*
* wait(): when a thread calls the wait method, it will suspend execution and release whatever locks it is holding
* until another thread issues a notification that something important has happened
*
* notify and notifyAll(): a thread will issue this call when something has happened
*
* These 3 methods are linked together - and with synchronisation - so in our example above the read() will call
* the wait() method within its loop and will call the notifyAll after the empty variable instance has been updated
*
* We always want to call wait() within a for loop that testing for whatever condition we are waiting on because when
* a thread is notified to wake up there is no guarantee that it is being woken up because the condition is waiting on
* has changed so it is possible the OS has woken the thread for another reason or it could have woken up because
* wait() threw an InterruptedException
*
* So we always want to call wait() within a loop so that when it returns because there has been a notification of
* some sort we will go back to the beginning of the loop we check whatever condition we are interested in and then we
* call wait() again if the condition hasn't changed
*
* Never assume a thread waking up has had the waiting condition changed
*
* We use the notifyAll() simply because we are not looking to wake up a specific thread, if of course there are some
* threads that do similar tasks of course use the notify() or when there are a lot of threads
*
* Waking up all of them will result in a huge performance hit
*
* Now we saw in Threads, that a thread can be suspended in many points of execution but there are few atomic rules
* that a thread cannot be suspended during an execution
* A thread cannot be suspended when:
* - reading or writing reference variables
* - assignment: myObjectA = myObjectB a thread cannot suspend during this operation
* - if reading or writing primitive variables except of those type long and double - so the JVM may require 2
*   operations to read and write longs and doubles and a thread can be suspended during each operation
* - reading and writing variables that are declared "volatile"
*
* Example
* A Thread cannot be suspended to myInt = 10;
* But it can be suspended on myDouble = 1.234
*
* Some Collections are not thread-safe: ArrayList is not thread-safe
* So we can do collections.synchronize(pass in ArrayList) but we still be responsible for synchronising the
* iteration of the ArrayList
*
* Using the debugger will show the changes of Message.message and Message.empty variables being changed over the
* course of the program
* */
