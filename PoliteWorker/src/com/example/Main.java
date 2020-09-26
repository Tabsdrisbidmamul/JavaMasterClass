package com.example;

public class Main {

    public static void main(String[] args) {
        final Worker worker1 = new Worker("Worker 1", true);
        final Worker worker2 = new Worker("Worker 2", true);

        final SharedResource sharedResource = new SharedResource(worker1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker1.work(sharedResource, worker2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker2.work(sharedResource, worker1);
            }
        }).start();
    }
}


/*
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
* From our example, the code is perpetually in a Live Lock, and it really depends on the code (this is for nearly all
* Live Locks) there isn't really one solution that fits them all
*
* Now with this problem in mind - it doesn't necessarily have to be a loop - but when using a multi-threaded
* application if a thread which is doing a task does not block (when it should) and remains live is a potential bug
* and can be overcome by the programmer by making sure that the thread is blocked instead of being live
*
* The next potential problem is Slipped Condition
* This is a specific of race Condition (Thread Interference)
* This can occur when a thread can be suspended between reading a condition and acting on it
*
* Example
* We have 2 Threads reading from a buffer (Thread A and Thread B)
* Each Thread does the following:
*   - 1. Check the status
*   - 2. If its an "OK", then it will read data from the buffer
*   - 3. If the data is "EOF", it sets the status to EOF and terminates. If the data isn't EOF it sets the status to
*        "OK"
*
* If we haven't synchronised the code properly, the following can happen:
*   - 1. Thread A checks the status and gets OK. It suspends
*   - 2. Thread B checks the status and gets OK. It reads EOF from the buffer and sets the status to EOF, then
*        terminates
*   - 3. Thread A wakes up, and tries to run again. It tries to read data from the buffer, but whoops, there is no
*        data - it throws and exception or crashes
*
* Because the Threads can interfere with each other when checking and setting the condition, Thread A tried to do
* something based on obsolete information -wWhen it checked the status it was "OK" at the time.
*
* But by the time it acted on the condition it checked, the status had been updated by Thread B. Unfortunately Thread
*  A doesn't see the updated information and because of that it does something erroneous
*
* The solution to this is the same for any thread interference - use synchronised blocks or locks on cortical section
* of code
*
* If the code is already synchronised, then sometimes the placement of the synchronisation may be causing the problem
* When using multiple locks, the order in which the locks can be acquired can also result in a slipped condition
*
*
* Thread Issues
* Atomic Action: We have seen when a thread is running, it can suspended when it is in the middle of doing something
*
* example: If a thread is calling System.out.println() method, it can be suspended in the middle of executing the
* method. it may have evaluated the argument that is being passed, but it is suspended before it can print the result
*
* Or alternatively, it may be partway through evaluating the argument when it is suspended.
*
* Essentially, System.out.println() is not an atomic action
*
* An Atomic Action means that it cannot be suspended in the middle of being executed. It either completes, or it
* does not happen at all. Once a thread starts to run, an atomic action, we can be confident that it will not suspend
* until it has completed the action
*
* We said atomic actions were:
* 1. Reading and Writing reference variables. For example, the statement myObject1= myObject2 would be atomic. A
* thread cannot be suspended in the middle of executing this statement
*
* 2. Reading and Writing primitive variables (except long and double. The JVM may require 2 operations to read and
* write longs and doubles, and a thread can be suspended between each operation) A thread cannot be suspended in the
* middle of executing myInt = 10;, but it can be suspended in the middle of executing myDouble = 1.234;
*
* 3. Reading and Writing all variables declared "volatile"
*
* http://tutorials.jenkov.com/java-concurrency/volatile.html
*
* Volatile:
* The Java volatile keyword is used to mark a Java variable as "being stored in main memory". More precisely that means,
* that every read of a volatile variable will be read from the computer's main memory, and not from the CPU cache, and
* that every write to a volatile variable will be written to main memory, and not just to the CPU cache.
*
* Volatile variables solve the issue of visibility: (illustration in the .html file)
* When multi-threaded applications work on values, then copy the value from main memory to the CPU cache - and most
* CPUs have more than one core, the each core will have a value from main memory via the Thread.
*
* Now if Thread 1 updates the value of the counter - the change is made in the CPU cache first - and not updated to
* main memory right away. So that means if Thread 2 reads the value counter from main memory - it will read an older
* version of the counter.
*
* There is now a conflict between the value of counter (the one updated value in CPU cache, and the older version in
* main memory)
*
* Volatile sorts out the issue of visibility by making sure that every update is referenced from CPU cache back to main
* memory and not stored in the CPU cache for quite some time. It also ensures the value read from main memory is the
* most update value
*
* To make a variable volatile we place volatile before the data type declaration
*   volatile int myInt = 10;
*
* However volatile does not sort all the issues - we still have to synchronise these variables in critical sections
* of code or we will run into thread interference - the way Java manages memory, it is possible to get memory
* inconsistency errors when multiple threads can read and write the same variable.
*
* You see Threads can run simultaneously (so far we have been blocking and suspending threads making it look like it
* works one at a time, but Threads in reality can run simultaneously) so without synchronise we can have a situation
* where Thread 1 and Thread 2 read the value counter and store it in their cache - Thread 1 increments counter and
* the JVM will immediately write the value to main memory - Thread 2 does the same as Thread 1 with its value in
* cache - but as you can see we will get the value of 2.
*
* Thread 2 in this case was using a stale value in its cache in this instance
*
* Thread 2 did not re-read the updated counter and then increment the new value (2) to get 3. Hence a wait(), notify,
* notifyAll() would have worked here etc...
*
* Using volatile variables is dependent - but they are mainly used on longs and doubles to make them atomic that way
* - but adding blocks of synchronisation is dependent on what the code is doing - say reading and writing variables
* with multiple threads
*
* java.util.concurrent.atomic
* This package ensures that the classes we are using provides reading and writing variables is atomic
*
* So for our counter variable problem we can use one of the classes within the atomic package - and what these
* provide over us declaring a volatile variable is that they support "lock-free thread-safe programming on single
* variables" so for our case we can use the AtomicInteger object, this also means we do not have to worry about
* thread interference
*
* https://docs.oracle.com/javase/8/docs/api/index.html?java/util/concurrent/atomic/package-summary.html
* https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html
*
* The Atomic classes for the following types: boolean, integer, integer array, long, long array, object reference and
* double
*
* The Atomic classes have the set() and get() methods that allow us to set a value, and get the current value - but
* the atomic classes are really meant to be used when incrementing and decrementing values - mainly used for loop
* counters or generating a sequence of numbers (for some reason?)
* And not for the replacement of ordinary primitive types!
*
* Another method of importance is the compareAndSet() method, this method takes 2 params
* 1st param: the expected value
* 2nd param: the new value
*
* if the expected value passed does not match the real value in memory it will return false and not set the value in
* memory
*
* NOTE: Daemon thread in Java. Daemon thread is a low priority thread that runs in background to perform tasks such as
* garbage collection. Properties: They can not prevent the JVM from exiting when all the user threads finish their
* execution.
*
* */