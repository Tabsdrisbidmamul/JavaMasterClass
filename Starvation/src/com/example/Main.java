package com.example;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

public class Main {
    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(ThreadColor.ANSI_RED), "Priority 10");
        Thread t2 = new Thread(new Worker(ThreadColor.ANSI_BLUE), "Priority 8");
        Thread t3 = new Thread(new Worker(ThreadColor.ANSI_GREEN), "Priority 6");
        Thread t4 = new Thread(new Worker(ThreadColor.ANSI_CYAN), "Priority 4");
        Thread t5 = new Thread(new Worker(ThreadColor.ANSI_PURPLE), "Priority 2");

        t1.setPriority(10);
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(4);
        t5.setPriority(2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    private static class Worker implements Runnable {
        private int runCount = 1;
        private String threadColor;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {
            for(int i=0; i<100; i++) {
                lock.lock();
                try {
                    System.out.format(threadColor + "%s: runCount = %d\n", Thread.currentThread().getName(),
                            runCount++);
                    // execute critical section of code
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

/*
*
* Thread Starvation
* Now when starvation occurs, its not that they will never progress because they will never get a lock but they
* rarely have the opportunity to run and progress.
*
* So starvation often occurs due to thread priority when we assign a high priority to a thread we are suggesting to
* the OS that it it should try run the thread before other waiting threads.
*
*
* Priority
* We can set the priority of a Thread - but this is just a suggestion to the OS; it will not necessarily mean that
* the Thread with the highest priority will run more likely
*
* But back to the topic of Priority - of there are several threads blocking waiting to obtain a lock, it is not first
* come, first serve basis - the OS will choose which thread will obtain the lock and do its task. Priority, fairness
* etc. are all just suggestions to the OS and JVM Threads a picked and chosen at random, there is no guarantee that
* a Thread with higher priority will run - however it increases the odds of it running - which is better than 0
*
*
* To set a Priority
* You use the setPriority() method, the higher the int value passed means the better the chances of it running
*   t1.setPriority(10);
*
* Now setting Priorities creates a more likely chance that Starvation to happen - to mitigate this the order of which
*  thread to run to obtain a lock is important - just like Deadlocks the order of which a Thread tries to obtain a
* lock is important, the same can be said for Starvation
*
*
* Fair Lock
* Try to come as "first come first serve" - now in the Reentrant implementation can use a fair lock - now not all
* locks are fair locks - so always read the lock in the documentation before using it
*
* Passing in the boolean value true tells the constructor that this is to be a fair lock implementation
* private static ReentrantLock lock = new ReentrantLock(true);
*
* So when using fair locks it is possible for threads to still have to wait a long time to run, the only thing a fair
*  lock guarantees is the "first come first serve" ordering for getting the lock
* - Fairness in acquiring the lock is guaranteed not fairness in threads scheduling; So it is possible that the
*   thread that gets the lock will execute a task that takes a long time
* - The trylock() method doesn't honour the fairness settings, so it will not be "first come, first serve" ordering
* - When using fair locks, with a lot of threads, performance will be impacted to ensure fairness - there has to be
*   an extra layer of processing that manages which thread gets the lock so that can ultimately slow things down
*
* Live Lock
* This type of Lock is similar to a Deadlock, but instead of the threads being blocked - they are actually constantly
* active and and usually waiting for all the other threads to complete their tasks - now since all the threads are
* waiting for others to complete - none of them can actually progress
*
* Let's say that Thread A will loop until Thread B completes its task and Thread B will loop until Thread A completes
* its task
*
* Thread A and Thread B can get into a state in which they are both looping and waiting for the other to complete
* This is known as a "Live Lock". The threads will never progress but they are not actually blocked
*
*
*
* */
