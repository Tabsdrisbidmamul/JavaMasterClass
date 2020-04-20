package com.example;

public class Main {

    public static void main(String[] args) {
        Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();

    }
}


class Countdown {
    private int i;

    public void doCountdown() {
        String color;

        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }

        synchronized (this) {
            for (this.i = 10; this.i > 0; this.i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
            }
        }
    }
}

class CountdownThread extends Thread {
    private Countdown threadCountdown;

    public CountdownThread(Countdown threadCountdown) {
        this.threadCountdown = threadCountdown;
    }

    @Override
    public void run() {
        threadCountdown.doCountdown();
    }
}


/*
* Thread Variables and Heap
* We mentioned this briefly in the beginnings of Threads
*
* Each process has allocated memory known as a "heap" and each Thread has its own space of memory known as the
* "Thread Stack"
*
* Every Thread shares and can see the heap - but separate Threads can only see their own Thread Stack and Thread A
* cannot see or access Thread B's Thread Stack
*
* Now the rules goes something like this
*   - local variables are given the to the Thread Stack - so each Thread will have an instance of a local variable
*     and only that Thread can see it
*   - Whereas instance variables so field members are in the heap - so all the Threads can see and share that resource
*
* Now when multiple threads are working with the same object - they in fact are actually sharing that object - so
* they don't have copy of the object rather they are working with it directly
* So if one thread changes the object variables value - all the other threads will see that new value change from
* that point onwards
*
* Thread 1 (t1)
* Thread 2 (t2)
* So when t1 executes its bit of code and gets suspended - t2 will wake up and see the new value of i when it runs
* its bit of code - but it will not see or know the value of i when t1 is suspended. t2 only knows the value of i
* after the change - but it does not the value of i before the change - the one t1 has.
*
* And that is why each Thread is skipping a number in the output, and sometimes the order of the value is not in
* descending order as well
*
* Thread Interference or Race Condition
* Now when 2 or more threads are working on the same object and they may be writing or updating values on the same
* object - this is known as Thread Interference or Race Condition - this is because the Threads are benign to one
* another and are simply writing or updating the values to the object - but as the objects values are all in the heap
* the Threads are what you could say overwriting the updated values and this can lead to funny results
*
* So in our example the for loop is made of many steps - the assignment, the boolean condition and the decrementing -
* and the println as well has many steps involved as well - so there are many steps within those 2 bits where the
* Thread can basically suspend or pause in the moment - as it is based on the OS and JVM when each Thread can run and
* pause - and this therefore leads to many scenarios where Thread 1 and Thread 2 decrement the value of i, print it
* or be suspended then print - etc...
*
* Thread 2: i = 10
  Thread 2: i = 9
  Thread 2: i = 8
  Thread 2: i = 7
  Thread 2: i = 6
  Thread 2: i = 5
  Thread 2: i = 4
  Thread 2: i = 3
  Thread 2: i = 2
  Thread 2: i = 1
  Thread 1: i = 10
*
*
* In this example what happened here is that
* - Thread 2 assigns the value 10 to i and momentarily is suspended there in the for loop.
* - Thread 1 looks at the for loop as well as assigns 10 to i it then pauses there for a very long time.
* - Whilst Thread 1 is paused, Thread 2 outputs the message then decrements i and carries on with the for loop as normal
* - Thread 2 has suspended on the for loop condition and the OS and JVM go back to Thread 1. Thread 1 carries on with
*   the execution that it was left on. It then looks at i which is 1 and exits out of the for loop.
* - Thread 2 wakes up from suspension, sees that i is not > 0 because at this point i is now 0
*
* Why is i 1 when Thread 1 looks back at it again - this is because i is an instance variable which is in the heap -
* this is seen by all Threads working on the same object, as a result - we don't which Thread will be suspended and
* for how long.
*
* IN this example - Both Thread 1 and Thread 2 assign 10 to i - and which ever gets paused will let the other Thread
* decrement i - or be suspended in mid outputting - which in turn can lead to the output above.
*
* Thread 1 and Thread 2 both have know that i = 10 at the time of execution. But Thread 1 was suspended for a very
* long time, whilst Thread 2 was busy decrementing i and outputting it to the console. When finally it may have
* suspended at the for loop condition or terminated Thread 1 finally gets to do the next part of the for loop - check
* the condition, and output the message (1 > 0) it outputs the println, and decrements i from 1 to 0, and breaks out
* of the for loop
*
* Thread 2 wakes up from suspension and check the for loop condition, 0 > 0 is false and breaks out of the for loop.
*
* To mitigate this, we could create 2 separate objects and have the Threads work on the different objects like so
* This is what we get we pass 2 separate objects to work on. But in the real world applications - this solution will
* rarely work
*
* Thread 2: i = 10
  Thread 1: i = 10
  Thread 2: i = 9
  Thread 1: i = 9
  Thread 2: i = 8
  Thread 1: i = 8
  Thread 2: i = 7
  Thread 1: i = 7
  Thread 2: i = 6
  Thread 1: i = 6
  Thread 2: i = 5
  Thread 1: i = 5
  Thread 2: i = 4
  Thread 1: i = 4
  Thread 2: i = 3
  Thread 1: i = 3
  Thread 2: i = 2
  Thread 1: i = 2
  Thread 2: i = 1
  Thread 1: i = 1
*
* synchronized
* Java provides a way to get around this, and the process of controlling when threads execute code and therefore when
*  they can access the heap is called "synchronisation".
*
* We can synchronise methods and statements, now when a methods is synchronised only one thread can execute the
* method at a time. So when a method is synchronised and we have Thread A executing code from that method, and any
* other Threads that want to run that method say Thread B, C and D. They will be suspended until Thread A has
* finished running the method and exits it, then another thread can run synchronised method, and the same rules apply
* to the other Threads - they will be suspended until the next Thread chosen (lets say B) Thread B has finished and
* exited the method etc...
*
* If a class has 3 synchronised methods, then only one of these methods can ever run at a time and only on one thread
* Since only one thread can run a synchronised method at a time, Threads cannot interleave when running a
* synchronised method - so this prevents Thread Interference within synchronised methods but not outside
* non-synchronised methods
*
* so if an instance variable which isn't synchronised and is used within a synchronised method - then we may still
* see thread interference
*
* So when working with threads, we have to synchronise all areas where we think thread interference may happen
*
* So adding the keyword synchronised to out method doCountdown()
*   - public synchronized void doCountdown() {...} this now prevents thread interference - because either Thread 1 or
*     Thread 2 have to finish executing the code and exit out of the method before the next Thread can start executing
*     the code
*
* That is one way to prevent a Race Condition, but we can also synchronize a block of statements rather than an
* entire
*
* So every Java object has what is called an "intrinsic lock" and will also see this reference to as a monitor - so
* we can synchronise a block of statements that work with an object by forcing threads to acquire the objects lock
* before they execute the statement block.
*
* Now only one thread can hold the lock at a time, so other threads that want lock will be suspended until the
* running thread releases the lock, then one and only one thread can get the waiting threads can get the lock and
* continue executing.
*
*         synchronized (this) {
            for (this.i = 10; this.i > 0; this.i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
            }
        }
*
* As mentioned we have to pass an object to the synchronized keyword and something that is common between the 2
* threads, and wrap the for loop with code braces - this will effectively create an intrinsic lock on this block of
* statements
*
* Now when we placed the local variable color as the argument to synchronized, it did not work - this is because it
* is a local variable which is unique to each Thread Stack and not a known value between the the 2
*
* We cannot use the instance variable i - as it is a primitive type and that worn't work
*
* But what does work is "this" referring to the object itself.
*
* Why?
* Each Thread has copy of the object references - being the location to the where in memory those values are stored
* and not the object itself - and in that way it is unique for both. So when we pass the reference "this" to synchronized
* we are saying is work on the Object references
*
* Another reason is that the object passes is common between the 2 Threads as well
*
* This will output in a synchronized set of results
*
* NOTE: you cannot synchronize a constructor - this is because the threads cannot operate on an object that hasn't
* been created yet - so really one thread is busy making the constructor the other threads cannot fathom to work on
* something that hasn't been created yet
*
* NOTE: the one exception to the local variables cannot be used in the synchronized(param) is Strings, this is
* because Java stores all its String in a String pool - so they are all in one place - and if 2 String object have
* the same value, they are the same object in memory - so to say 2 threads are sharing the same object.
*
* The example above color, it can have 2 values: ANSI_CYAN and ANSI_PURPLE hence it is considered a separate object
* in memory.
*
* We can also use synchronise on static methods and use static objects and when we do that, the lock is owned by the
* class object associated with the objects class
*
* Synchronisation is reentrant - that means if that if a thread acquires an objects's lock and within the
* synchronised code it will then call a method that is using the same object to synchronise some code - the thread
* can keep executing because it already has the objects lock
*
* in other words, a thread can acquire a lock it already owns now if this wasn't the case synchronisation would be a
* lot trickier
*
* The term critical section used when discussing threads and synchronisation
* Critical section: refers to the code that is referencing a shared resource like a variable, only one thread at a
* time should to be able to execute a critical section.
*
* The term thread-safe used when a class or method is thread-safe what that means is that developer has synchronised
* all the critical sections within the code so that we as a developer don't have to worry about thread interference.
*
* One last point, only synchronise the code that must be synchronised - in our example we synchronised the for loop -
* this in turn means that there is less code to synchronise - by synchronising the whole method - well there is too
* much code to synchronise - this could affect the performance of an application
*
*
* */