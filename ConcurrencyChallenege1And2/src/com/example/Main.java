package com.example;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        /*
        * Challenge 1 - Create and Start threads
        * We could have 2 people using a joint bank account at the same time.
        * Create and start 2 threads that use the:
        *   - Same BankAccount instance and an initial balance of $1000.00
        *   - One will deposit $300.00 into the bank account, and then withdraw $50.00
        *   - The other will deposit $203.75, and then withdraw $100.00
        *
        * Solution: Done in main.java -> Added the anonymous threads
        *
        * Challenge 2 - Make the BankAccount class ThreadSafe using the synchronise keyword
        * I hope you can see that there is going to be thread interference when 2 threads are accessing the same
        * BankAccount instance at the same time. We have to make the BankAccount class threadsafe, and that is our
        * next challenge.
        *   - Use the synchronized keyword to make the BankAccount class threadSafe
        *
        * Solution: Done added synchronise(this) to the deposit() and withdraw() methods
        *
        * Challenge 3 - Make the BankAccount class ThreadSafe again using the synchronised keyword
        * We added 2 new methods:
        *   - getAccountNumber()
        *   - printAccount()
        *
        * Update the code so that the BankAccount class is ThreadSafe - use the synchronized keyword wherever necessary
        *
        * Solution: Trick question, because we are reading and outputting values - nt updating thus synchronising
        * will have a negative impact on the application
        *
        * Challenge 4 - Use Reentrant Lock
        * Instead of using the synchronized keyword, make the BankAccount class ThreadSafe using the ReentrantLock class
        *
        * Solution: Added in BankAccount.java we assign a Lock object to lock variable, and in the BankAccount
        * constructor we initialise it with a Reentrant Lock.
        * Within the deposit() and withdraw() method we use a try-finally block to lock() and unlock() the lock, as
        * we want all threads to compete for the same lock
        *
        * Challenge 5 - Use trylock() with a timeout value
        * Instead of using lock(), use the trylock() with a timeout value of 1 second.
        * If the waiting period times out, print the message "Could not get the lock" to the console
        *
        * Solution: We want to have 2 try blocks - one for the trylock() exception (which it throws and
        * InterruptedException) and the other for the critical section of code and to release the lock - we don't
        * want to release a lock if the Thread did not obtain it in the first place therefore we must release the
        * lock in the inner try-block
        *
        * Challenge 6 - Update the code so that the status variable is thread safe
        * Use whatever method you like: synchronised keyword or the lock object.
        *
        * Solution: as it is a local variable the value of that variable is stored on the Thread stack - each Thread
        * Stack unique to each Thread and cannot be accessed by one another - therefore we know that there will be no
        * Thread interference when dealing with the local variable status
        *
        * Challenge 7 - Spot and Fix the Problem
        * This is a similar application to BankAccount, but in this case also performs transfers from one account to
        * another
        * There are 3 methods
        *   - deposit(): similar to the one in BankAccount
        *   - withdraw(): similar to the one in BankAccount
        *   - transfer(): first tries to withdraw the money from the source account, if it successful, it deposits
        *                 the money into the destination account and returns true.
        *                 If it is not successful, it refunds the source account and returns false
        *
        * In the main method we start 2 bank accounts and start 2 threads. Each thread will perform a transfer using
        * an instance of the Transfer Class. The Transfer Class's run() method loops until a transfer is successful.
        *
        * The challenge is to spot what is wrong with this code and to fix it. What do we call this particular
        * situation (deadlock, livelock etc..) Why is it happening? How would we fix it?
        *
        * This dilemma is called a livelock - where the Threads are constantly active and waiting for others Threads
        * to accomplish their tasks - so they can start on their tasks. The Threads obtain a lock but never release
        * it - so by making sure that the Threads release the lock, this should solve the problem
        *
        * Releasing the locks solves the Live Lock problem
        *
        * Challenge 8 - There are many ways to solve a DeadLock
        * In the example we have - we have a DeadLock with the Threads and the locks they are trying to obtain
        *
        * The Tutor Object obtains the Tutor Lock and then tries to obtain the Student Lock (still holding onto the
        * Tutor Lock)
        *
        * The Student Object obtains the Student Locks and then tries to obtain the Tutor Lock (still holding onto
        * the Student Lock)
        *
        * These are the key steps to take when dealing with DeadLocks:
        *   1. Is a set of locks being obtains in a different order by multiple threads (that's is the case here). If
        *      so, can we force all threads to obtain the locks in the same order?
        *   2. Are we over-synchronising the code?
        *   3. Can we rewrite the code to break any circular call patterns>
        *   4. Would using ReentrantLock objects help?
        *   5. Take a look at the places where wait() is called and see if that may cause a deadlock
        *
        *
        * Challenge 9 - There are still more ways to solve DeadLocks
        *
        *
        * */
        final BankAccountEvolved account = new BankAccountEvolved("12345-678", 1000.00);
        new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(300.00);
                account.withdraw(50.00);
                System.out.println(account);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(203.75);
                account.withdraw(100.00);
                System.out.println(account);
            }
        }).start();


    }

//    private static synchronized void depositIntoAccount(BankAccount account, double deposit) {
//        account.deposit(deposit);
//
////        lock.lock();
////        try {
////            account.deposit(deposit);
////        } finally {
////            lock.unlock();
////        }
//    }

//    private static synchronized double withdrawFromBankAccount(BankAccount account, double withdraw) {
//        account.withdraw(withdraw);
//        return withdraw;
//
////        lock.lock();
////        try {
////            account.withdraw(withdraw);
////            return withdraw;
////        } finally {
////            lock.unlock();
////        }
//    }


}
