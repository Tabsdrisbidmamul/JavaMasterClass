package com.example;

import static com.example.ThreadColor.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE + "Hello from the main thread.");

        Thread anotherThread = new AnotherThread();
        anotherThread.setName("---Another Thread---");
        anotherThread.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println(ANSI_GREEN + "Hello from anonymous class thread.");
            }
        }.start();

//        Thread myRunnable = new Thread(new MyRunnable());
//        myRunnable.start();



        Thread myRunnable = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Hello from the anonymous class's implementation of run()");
                try {
                    anotherThread.join();
                    System.out.println(ANSI_RED + "AnotherThread terminated, or timed out, so I'm running again");
                } catch (InterruptedException e) {
                    System.out.println(ANSI_RED + "I couldn't wait after all. I was interrupted");
                }
            }
        });
        myRunnable.start();

//        anotherThread.interrupt();

        System.out.println(ANSI_PURPLE + "Hello again from the main thread.");


    }
}

/*
* Concurrency
* Process: is a unit of execution that has its own memory space
* Each instance of a Java Virtual Machine (JVM) runs as a process (most of them anyway)
* When we run a Java console application, we are kicking off a process - when we run a JavaFx application we are also
* kicking off a process
*
* Many people will use the term process and application interchangeably, and we will too.
* If one Java application is running and we run another one - each application has its own memory space of heap
* The first Java application cannot access the heap that belongs to the second Java application - the heap is not
* shared between them - they each have their own
*
* A Thread: is a unit of execution within a process.
* Each process can have multiple threads. In Java every process (or application) has at least one thread, the main
* thread( for Ui applications, this is called the JavaFX application thread).
* In fact just about every Java process also has multiple system threads that handle tasks like memory
* management and I/O. We the developers don't explicit create and code those threads. Our code runs on the main
* thread, or in other threads that we explicitly create.
*
* Creating a thread does not require as many resources as creating a process. Every thread created by a process
* shares the process's memory and files. This can create problems.
*
* In addition to the process's memory, or heap, each thread has what is called a thread stack - which is memory that
* only that thread can access, we'll look at what is stored in the heap vs what is stored in the thread stack
*
* So every Java application runs as a single process, and each process can have multiple threads.
* Every process has a heap, and every thread has a thread stack
*
* So why do we want to use threads? Why not use the main thread? There are 2 advantages to this:
* But first let's look at the problems first in this example:
* 1.We sometimes want to perform a task that is going to take a long time. For example we want might want to query a
* database - or we might want to fetch data from somewhere on the Internet.
* We could do this on the main thread, but the code within each main thread executes in a linear fashion. The main
* thread won't be able to do anything else while it is waiting for the data
*
* 2. Another way of putting this is that the execution of the main thread will be suspended. It has to wait for the
* data to be returned before it can execute the next line of code.
* To the user, this could appear as if our application has died or is frozen, especially when you are dealing with a
* UI application.
*
* The advantages of using threads:
* 1. Instead of tying up the main thread, we can create another thread and execute the long-running task on that thread.
* This would free up the main thread, so that it can continue executing, it can report process or accept use input
* while the long running task continues to execute in the background
*
* 2. We might want to use threads, is because an API requires us to provide one. Sometimes we have to provide the
* code that will run when a method we have called reaches a certain point in its execution.
* In this instance, we usually don't create the thread. We pass in the code that we want to run on the thread.
*
* Concurrency: which refers to an application doing more than one thing at a time
* Now that doesn't necessarily mean that the application is doing more than one thing at the same time. It means that
* process can be made on more than one task.
*
* Let's say that an application wants to download data and draw a shape on the screen.
* If it is a concurrent application, it can download a bit of data, then switch to drawing part of the shape, then
* switch back to downloading some more data, then switch back to drawing more of the shape, etc..
*
* Concurrency means that one task doesn't have to complete before another can start. Java provides thread-related
* classes so that we can create Java concurrent applications
*
* NOTE: The way threads are executed - there is no guarantee on the order of which the threads will be executed - that
*  is up to the system scheduler and the JVM - of course we can give a thread priority - but again it is not guaranteed
*
*
* Making Threads
* There are several ways to create a thread in Java - we can make a Thread by instantiating the Thread Class itself,
* make a class extends the Thread class and override the run() method - or we can make a class implements the
* Runnable interface and implement the run() method
*
* Thread instance itself
* We can make an instance of the Thread class and override its run method, but for simplicity sake we are going to
* write a class that extends from Thread and override its run() method in there
*
* We create a class called AnotherThread and have it extend Thread, then we override the run() method from Thread and
* make it simply output a String
*
*   public class AnotherThread extends Thread {
*       @Override
*       public void run() {
*           System.out.println("Hello from another thread.");
*       }
*   }
*
* In the Main Thread, we instantiate the class, and instead of calling the overridden run() method from AnotherThread
*  - we actually call the start() method - this tells the JVM to to run the run() method and kick start the thread
*
* Main.java
* Thread anotherThread = new AnotherThread();
* anotherThread.start();
*
* The same as above but using anonymous functions
*
*   Thread newThread = new Thread() {
*       @Override
*       public void run() {
*           System.out.println("Hello from new thread.");
*           }
*       };
*
*   newThread.start();
*
*                       or
*
*
*   new Thread() {
*       @Override
*       public void run() {
*               System.out.println("Hello from anonymous class thread.");
*           }
*       }.start();
*
* now if we were to use the same thread again and do this
* anotherThread.start();
*
* We will get java.lang.IllegalThreadStateException
*
* Now this is because if want to run the same code again within anotherThread - we actually have to make a new
* instance of anotherThread each time we want to run the start(). We basically cannot have 2 of
* anotherThreads running at the same time.
* Whilst we can reuse the same instance - we cannot run the start() method of that instance twice
*
* Runnable Interface
* The Runnable Interface - only has one method - run(). by implementing this method we can then pass this object a
* the Thread constructor and simply use the start() method on the reference variable
*
* What we have done is made a class that implements the Runnable interface - this allows us to make a myriad of
* classes that implement the Runnable interface and have them be Thread ready
*
* When implementing the Runnable interface we have to implement the method run() - which is basically the same method
*  in Thread
*
* MyRunnable.java
* public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(ANSI_RED + "Hello from MyRunnable's implementation of run()");
    }
}
*
*
* once done, we can make an instance of the class Thread, and we pass into the Thread constructor the declaration of
* an anonymous instance of the MyRunnable class - then we simply call start() on that reference variable and we have
* another thread initiated.
*
* Main.java
*   Thread myRunnable = new Thread(new MyRunnable());
*   myRunnable.start();
*
*
* We can also do it like this
*         Thread myRunnable = new Thread(new MyRunnable() {
            @Override
            public void run() {
                super.run();
            }
        });
        myRunnable.start();
*
*
* Runnable vs Thread
* Most of the time we use the Runnable implementation - because many of the times most classes/ methods expect a
* Runnable object to be passed - and with lambda expressions Runnable is more convenient and flexible
*
*
* Don't call run(), call start()
* If we call run() by accident - we actually don't get the expected result - instead it will actually run the main
* thread and not run the Thread we wrote - so it is always best to call the start() on a Thread you wrote - to avoid
* this error
*
* Thread.sleep()
* This method accepts milliseconds and nanoseconds as the interval for how many seconds you want a thread to be
* suspended for, now this method throws InterruptedException - meaning that another thread can likely wake the thread
* back up.
*
* Another key point is that the Thread.sleep() is OS dependent and JVM (in)dependent - meaning not always can we have
* nanoseconds attach to the milliseconds as well.
*
* Interrupts
* We interrupt a thread when we want it to stop, what it was doing and to do something else - more often then not -
* we usually do that because we want the thread to terminate
*
* There are 2 ways for a thread to know it has been interrupted:
*   - to catch the InterruptedException
*   - when the run() method does not call the InterruptedException, it should call the interrupted() method
*     periodically to check whether it has been interrupted - and that method will return true when whether is has
*     been interrupted
*
* Now has does a thread interrupt another thread, it calls the interrupt method on the Thread instance that it wants
* to interrupt - now of course it will need a a reference to the thread instance to be able to do that.
*
*
* interrupt()
* This method will interrupt the thread, forcing it to terminate
*
* join()
* This method is very useful - what this does, is that it will join Thread A to Thread B. Meaning that we can 2
* Threads connected to one another, and we can say that Thread B can run, only if Thread A has finished its job and
* terminated
*
* Now this was mentioned above - but for the second option to happen we need a reference to that Thread - which we
* have "anotherThread" - and all you do is call anotherThread.join() within Thread B - in this case myRunnable
*
*         Thread myRunnable = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Hello from the anonymous class's implementation of run()");
                try {
                    anotherThread.join(); <-
                    System.out.println(ANSI_RED + "AnotherThread terminated, or timed out, so I'm running again");
                } catch (InterruptedException e) {
                    System.out.println(ANSI_RED + "I couldn't wait after all. I was interrupted");
                }
            }
        });
        myRunnable.start();

*
* What happens if Thread A never terminates - Thread B will never execute, to mitigate this we can pass an argument
* to join - and just like sleep it will the number of milliseconds that Thread B will have to wait before realising
* that Thread A has hung - and it will execute its portion of code. During this scenario Thread A may come back to
* life and have finished its job - and in this case Thread B finished before Thread A.
*
* https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html
*
* */