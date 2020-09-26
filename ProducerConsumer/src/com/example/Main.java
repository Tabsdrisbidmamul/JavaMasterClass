package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.Main.EOF;

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);
//        ReentrantLock bufferLock = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_YELLOW);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_WHITE + "I'm being printed from the Callable class");
                return "This is the callable result";
            }
        });

        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Thread running the task was interrupted");
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}

class MyProducer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;
//    private ReentrantLock bufferLock;

    public MyProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
//        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};
        for (String num : nums) {
            try {
                System.out.println(color + "Adding... " + num);
                buffer.put(num);

                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
                e.printStackTrace();
            }
        }
        System.out.println(color + "Adding EOF and exiting...");

        try {
            buffer.put("EOF");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class MyConsumer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;
//    private ReentrantLock bufferLock;

    public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (buffer) {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    if (buffer.peek().equals(EOF)) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.take());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
* Drawbacks of using Synchronisation blocks
* 1. Threads that are blocked waiting to execute synchronise code cannot be interrupted - once they are blocked,
*    their stuck there until they get the lock for the object the code is synchronising on - which can lead to problems
*
* 2. Synchronisation block must be within the same method, in other words we cannot start a synchronised block in one
*    method and end the synchronisation block in another - as the Thread obtains the lock in one Synchronisation block
*    - they cannot then gain another lock
*
* 3. We cannot test to see if an object intrinsic lock is available or find out any other information about that
*    lock, also if the lock is not available we cannot timeout after we waited for the lock for a while.
*    When we reach the beginning of a synchronised block; we can either get the lock and continue executing or block at
*    that line of code until we get the lock.
*
* 4. If multiple threads are waiting to get a lock, it is not first come first serve - there is not a set order in
*    which the JVM will choose the next thread that gets the lock
*    e.g. so the first thread that blocked, could be the last thread to get the lock and vice versa
*
* Instead of using Synchronisation - we can use classes that implement the Java.util.concurrent.locks.lock interface
*
* Reentrant Lock
* Reentrant: is written so that the same copy in memory can be shared by multiple users or Threads in this case
* If a thread is already holding a reentrant lock, when it reaches the code that requires the same lock it can
* continue executing, it doesn't have to obtain the lock again
*
* Think of it like this: We have Thread A and we know it is going to execute a bit of code that is in a synchronised
* block - and as mentioned with drawbacks with synchronisation - it will have to wait to get a lock - but with
* reentrant we are basically giving a lock early on the course - basically getting it ready when it reaches that
* synchronisation block
*
* We create an instance of the ReentrantLock - now not all objects within the locks.lock are Reentrant - the
* explanation above exampling reentrant
*   ReentrantLock bufferLock = new ReentrantLock();
*
* And when using the Reentrant object we wrap the the statements of code with a lock() and unlock() methods like so
*
* Previously we had
* synchronize(buffer) {
*   buffer.add(num)
* }
*
* we do this instead
*
* bufferLock.lock()
* buffer.add(num)
* bufferLock.unlock()
*
*
* we are essentially wrapping the code which we would synchronise with lock() and unlock()
* Now when we call lock() we are giving the Thread the lock(), and when calling unlock() we are releasing the lock
* for other Threads to obtain
*
* Drawback to the lock() and unlock():
* We as the programmer will have to always write the unlock() after the critical section of statements we want
* thread-safe - unlike the synchronisation where the lock is released after reaching the end of the synchronisation
* block - when using ReentrantLock we have to release the lock ourselves
*
* Now the explanation above about using lock() and unlock() is not the recommenced way of using it:
* Problems:
* - The code will get messy having multiple unlock()'s everywhere where would expect the code to go some place else
* and the lock() not being released is a big issue [there is a maximum count on the number of lock()'s a Thread can
* hold, reach too many an exception will occur]
*
* - Releasing the lock() when the Thread hasn't obtained the lock will throw IllegalMonitorStateException - because a
*  Thread cannot release a lock for not having one
*
* Recommended approach
* The recommended approach is to use a try-finally block like so:
* We firstly make sure that the Thread obtains the lock before entering the critical section of code and then we
* pace our critical section within a try block, and lastly we place the unlock() within the finally block
*
*               bufferLock.lock();
                try {
                    buffer.add(num);
                } finally {
                    bufferLock.unlock();
                }
*
* Advantages:
* 1. We can now catch any exceptions that may occur within the critical section (It wasn't done in this example)
* 2. We now that if anything goes wrong with the critical section (try block) the finally block will always run and
* release the lock the current Thread has obtained
*
* trylock()
* this method is in the ReentrantLock class, and this will return a boolean value and the lock - which tests to see if
* another Thread (let's say Thread A)  has obtained the lock - if false the lock is already in use the Thread asking
* (lets say Thread B) can do several things:
* - will not block (suspend) and wait till the lock is released, instead it can do something else.
* - block and wait for the lock
* - ...
*
* If true, that means the lock is available - the Thread asking for the lock will obtain the lock and start executing
* the critical section of code
*
* Timeout period for trylock()
* 1st param: value we want
* 2nd param: time unt
*
* trylock(3, TimeUnit.SECONDS) {....}
* catch(InterruptedException e) {...}
*
* When using the overloaded method it will throw a checked InterruptedException exception so it must be caught
*
* https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html
*
* ExecutorService
* This is an interface found in the util.concurrent package and what this does; it manages the creation, the
* lifecycle and handling of Threads (multiple ones as well) this removes the fuss of us programmers creating Threads
* - we still have to provide a Runnable object to the Executive Service - but it generally will optimise and manages
* Threads far better than us
*
* This service makes use of "Threads Pools": is a managed set of Threads - there is more to this (but this is a rough
*  outline)
*
* Now the ExecutorService interface actually extends the Executor interface which only has one method execute(); this
*  method is here purely to remove
*
*   new Thread(RunnableObj).start
*
*               to
*
*   executorService.execute(RunnableObj)
*
*
* We create an instance of ExecutorService, and we have to use factory methods to instantiate it, so we use the
* method newFixedThreadPool(nThreads), and we pass in the maximum number of threads we expect we are going to use
* when dealing with tasks
*
* Now a note to mention is that when we want the ExecutorService to do a task and delegate it to it, if the maximum
* number of threads has been reached then the task will be put onto a queue till a thread within the thread pool has
* been terminated and freed up space within the thread pool
*
* ExecutorService executorService = Executors.newFixedThreadPool(3);
*
*       executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);
*
* When using the ExecutorService we as the programmer have to manually shutdown the instance or else the application
* will reaming live even after the main thread has been terminated
* - executorService.shutdown();
*
* Now when shutdown() is called it will wait for any Threads to terminate before exiting the application
* after the shutdown() method it will not accept any new tasks - this is an orderly shutdown if we want the service
* to shutdown immediately we use the shutdownNow() method - in this case the service will halt any tasks and throw
* away any tasks in the queue (no guarantees it will do that though)
*
* Now some threads may never terminate so it is best to shutdown() in a orderly fashion
*
* Thread return values
* If we want Threads to return we use a Callable object in conjunction with the Future object as well
*
*       Future<String> future = executorService.submit(new Callable<String>() { <- anonymous calls of Callable
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_WHITE + "I'm being printed from the Callable class");
                return "This is the callable result";
            }
        });

        try {
            System.out.println(future.get());
        } catch (ExecutionException e) { <- Callable exception
            System.out.println("Something went wrong");
            e.printStackTrace();
        } catch (InterruptedException e) { <- Thread can be interrupted as well
            System.out.println("Thread running the task was interrupted");
            e.printStackTrace();
        }
*
* When running the code, get the callable results at the end of the processing the consumers and producer do - and
* this is because the number of threads we have allowed in the Thread Pool - so to have the Callable results appear
* whilst the producer and consumer are working we increment or increase the value of Threads allowed in the Thread
* pool to 4 or greater - and when ran we see the Callable results a lot earlier on the than before
*
* https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html
* https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html
*
*
* ArrayBlockingQueue
* This Array is a Thread-safe object meaning we don't have synchronise blocks of code - or use a reentrant lock when
* using the methods of this object - they have been done for us.
*
* We changed the implementation so that we use the ArrayBlockQueue and not an ArrayList like like the previous
* implementation - when using the ArrayBlockingQueue - we have to specific the size of the Array - its static and
* will not grow in size - we put size here because we know we are going to add 5 items to the Array followed by an
* EOF - but also the Consumers will also remove items off from the Array as well - so we general want +1 the size of
* the Array to the items we are putting into it (so 5 items + 1 = 6)
*
* Now this Array is FIFO (First In First Out) meaning when we put(), peek() or take() from the Array it is always the
*  first item that came in
* ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);
*
* put(Obj)
* We use the method put to add an item to the Array
*
* peek()
* This method will return the the first item from the Array but not remove it - allowing easy access to show what is
* in the Array
*
* take()
* This method will remove the first item from the list and return it to the caller
*
*
* Now when running this code we can run into an error - a null pointer exception
* Why?
* When we the peek command, Thread A can be suspended during that process, and the other consumer Thread B can easily
* remove the item from the list, then when Thread A wakes up again - it will peek into the Array only to get a null
* pointer exception when we call the .equals() method on it
*
* Now what this means that thread-safe operations can sometimes lead to these problems and the really the only way to
* solve them is to wrap the code giving the problem (.peek()) and the rest of those statements round a synchronised
* block
*
* */
