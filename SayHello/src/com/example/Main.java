package com.example;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        final PolitePerson jane = new PolitePerson("Jane");
        final PolitePerson john = new PolitePerson("John");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                jane.sayHello(john);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                john.sayHello(jane);
            }
        });

        t1.setName("Thread1");
        t2.setName("Thread2");

        t1.start();
        t2.start();

    }

    static class PolitePerson {
        private final String name;

        public PolitePerson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void sayHello(PolitePerson person) {
            lock.lock();
            try {
                // more likely to get a deadlock with format instead of println
                System.out.format("%s: %s has said hello to me!%n", this.name, person.getName()); // %n it newline
                person.sayHelloBack(this);
            } finally {
                lock.unlock();
            }

        }

        public void sayHelloBack(PolitePerson person) {
            System.out.format("%s: %s has said hello back to me!%n", this.name, person.getName());
        }
    }
}


/*
* More on Deadlocks
* The reason why both objects are able to output their hello - is because the intrinsic lock an object has that a
* Thread gets is different for every object - so we have the jane object and the john object
*
* Thread 1 - jane obj
* Thread 2 - john obj
*
*
*   // 1. Thread1 acquires the lock on the jane object and enters the sayHello() method.
    // It prints to the console, then suspends.

    // 2. Thread2 acquires the lock on the john object and enters the sayHello() method.
    // It prints to the console, then suspends.

    // 3. Thread1 runs again and wants to say hello back to the john object. It tries to call the sayHelloBack() method
    // using the john object that was passed into the sayHello() method,
    // but Thread2 is holding the john lock, so Thread1 suspends.

    // 4. Thread2 runs again and wants to say hello back to the jane object. It tries to call the sayHelloBack() method
    // using the jane object that was passed into the sayHello() method,
    // but Thread1 is holding the jane lock, so Thread2 suspends.
*
*
* The solution above uses a Reentrant lock - and what this is basically making sure that Thread is not suspended
* during the execution of the statements therefore allowing always for Thread 1 or 2 to be able to execute
*
*
* Thread Starvation
* Now when starvation occurs, its not that they will never progress because they will never get a lock but they
* rarely have the opportunity to run and progress.
*
* So starvation often occurs due to thread priority when we assign a high priority to a thread we are suggesting to
* the OS that it it should try run the thread before other waiting threads.
*
* */