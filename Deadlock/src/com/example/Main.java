package com.example;

public class Main {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 1: Has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread 1: Has lock1 and lock2");
                }
                System.out.println("Thread 1: Released lock2");
            }
            System.out.println("Thread 1: Released lock1. Exiting...");
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 2: Has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: Waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread 2: Has lock1 and lock2");
                }
                System.out.println("Thread 2: Released lock2");
            }
            System.out.println("Thread 2: Released lock 1. Exiting...");
        }
    }
}



/*
* Deadlocks
* A deadlock occurs when 2 or more threads are blocked on locks and every thread that is blocked is holding a lock
* that another block thread wants
*
*   - Thread 1 is holding lock 1 and waiting to acquire lock 2
*   - Thread 2 is holding lock 2 and waiting to acquire lock 1
*
* Now because all the Threads are blocked but also holding the locks, they will never release the locks they are
* holding and so none of the waiting threads will actually ever run
*
* In the example above we get this as the output
* output
* Thread 1: Has lock1
* Thread 2: Has lock2
* Thread 1: Waiting for lock2
* Thread 2: Waiting for lock1
*
*
* Thread 1 obtains lock1 and sleep for 100 ms
* Thread 2 obtains lock2 and sleeps for 100ms
*
* Thread 1 wakes up and tried to obtain lock 2, but Thread 2 has it, so it blocks itself (suspends) waiting for lock2
* release
*
* Thread 2 wakes up and tried to obtain lock 1, but Thread 1 has it, so it blocks itself (suspends) waiting for lock1
* release
*
* These 2 Threads are in deadlock - and will forever wait until the Threads release the lock (which will never happen)
*
* Solutions to Deadlocks
* 1. We can create one lock for all Threads to use - but in a real world application this does not work simply because
* multiple locks on objects are the way forward with multiple threads.
*
* 2. All threads must first try to obtains the locks in the same order, the deadlock in our example is possible
* because Thread 1 tries to obtains lock1 and then lock2 - and Threads2 tries to obtain lock2 and then lock1
*
* Now if we made both threads obtain the locks in the same order, a deadlock cannot occur - because Thread1 obtains
* lock1 and then trying to obtains lock2. Thread2 will then try to obtains lock1 - but cannot because it is in use to
* it wait and suspends itself
*
* So changing the order - Thread2 tries to obtain lock1 then lock2 - this then solves the deadlock problem
*
* 3. Is to use the Lock Object rather than using synchronised blocks - so using the trylock() method  with or without
* timeout values may will prevent deadlocks (but that depends on the code)
*
*
*
* */
