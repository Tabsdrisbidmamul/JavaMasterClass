package com.example;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Example {
    public static void main(String[] args) {
        try {
            int result = divide();
            System.out.println(result);
        } catch (ArithmeticException | NoSuchElementException e) {
            System.out.println(e.toString());
            System.out.println("Unable to perform division, autopilot shutdown");
        }
    }

    private static int divide() { //  throws ArithmeticException, NoSuchElementException
        int x, y;
//        try{
        x = getInt();
        y = getInt();
        System.out.println("x is " + x + ", y is " + y);
        return x / y;
//        } catch (NoSuchElementException e) {
//            throw new NoSuchElementException("No suitable input");
//        } catch (ArithmeticException e) {
//            throw new ArithmeticException("Attempt to divide by zero");
//        }
    }

    private static int getInt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an integer");
        while (true) {
            try {
                return scanner.nextInt();
            } catch (NoSuchElementException e) {
                scanner.nextLine();
                System.out.println("Please enter a number from 0 to 9");
            }
        }
    }
}

/*
* Call Stack
* Is a list of of method called in the program execution, now usually it will show the method calls all the way to the
* point where it crashed, and each thread of execution has it own call stack, and the thread is shown as the first
* line of the stack trace
*
* For this example, the thread will be "main" - the method main()
*
* So every time a new method is called, it is actually placed onto the stack and when a method returns, it is then
* removed from the stack.
*
* So usually when we get a stack trace, it is best to start from the bottom and then work our way up
*
* So we said that all exceptions inherit from a parent class of Exceptions, but going back to the StackTrace, the way
* it works like this
*
*
* Example - lets say we passed letters instead of numbers to the console when it asked for input
* The exception mainly occurs at the bottom of the stack - mainly where we the programmer wore the code in main()
* etc, it will then go up the stack to the previous method that called it - so in this case we can say that
* main() called divide(), and within divide the method getInt() was called, it will then go to the nextInt() method
* within java.util.Scanner - and from there we keep going the stack - to see if any method can handle this exception
* - and we go all the way to the top of the stack we come to the java.util.Scanner.throwFor() - here this method does
* a LBYL check and depending on conditional statement if will use the command
*   - throw new InputMismatchException();
* This is where the StackTrace ends, as Java has finally reached an exception. You see when we call the method
* nextInt() - it will go through this:
*
* Exception in thread "main" java.util.InputMismatchException
	at java.base/java.util.Scanner.throwFor(Scanner.java:939)   <-
	at java.base/java.util.Scanner.next(Scanner.java:1594)      <- All these methods were called to do nextInt()
	at java.base/java.util.Scanner.nextInt(Scanner.java:2258)   <-
	at java.base/java.util.Scanner.nextInt(Scanner.java:2212)   <-
	at com.example.Example.getInt(Example.java:21)
	at com.example.Example.divide(Example.java:12)
	at com.example.Example.main(Example.java:7)
*
* Usually stacks are implemented going down the stack, but when it comes to debugging - we start from the bottom to
* see what went wrong - and if nothing can handle the exception - so there is not a single catch
* (InputMismatchException) within the methods that were called for nextInt() - then Java runtime will terminate the
* program, and output the StackTrace to the console
*
* Now this mainly due to how Java devs wrote the the Scanner, they want the programmer to handle for InputMismatch
* for their users, so really it will be on us to handle these types of exceptions as every implementation that uses
* the Scanner will be different for every program written.
*
* Now when looking at the hierarchy for these exceptions, we can go all the way to the parent class throwable which
* prints out the StackTrace, but the more specific exceptions like InputMismatchException or NoSuchElementException -
*  they all extend the parent class so their constructors mainly call the superclass - super() - but of course they
* too might have some extra code to do something more specific after calling super() -
*
* but the point is that going up the hierarchy - if there is an exception that can handle the "something went wrong
* here" then that exception will be shown in the StackTrace rather than the parent class. This is not only convenient
* for us programmer to help us debug, but we can also do something like this: if we know that the exception we are
* going to get is an InputMismatchException and NoSuchElementException. The InputMismatchException extends from the
* NoSuchElementException, so we can actually place the NoSuchElementException in the catch block, and the
* InputMisMatchException will be covered by the NoSuchElementException when triggered
*
*                   NoSuchElementException
*                              |
*                              |
*                    InputMismatchException
*
* To allow multi-catch exceptions - the two or more exceptions must be disjoint - that means they must not relate or
* extend from each other - so we cannot multi-catch NoSuchElement and InputMismatch as they both are related to other
*  but IOException and InputMismatchException will work otherwise
*
* Another note: its always important to make sure that the catch block code we write itself doesn't not raise
* another exception - or it will defeat the purpose of the try-catch
*
* Its always best to make the catch block as simple as possible to avoid these errors from occurring
*
* Scope:
        try{
            int x = getInt();
        } catch () {
            x = getInt();
        }
*
* When we try this we get an error, this is because of scope of try - the code braces represent scope remember, so
* any attempt to reference x outside the try block will be telling Java to find x when only exist in try{} so we have
*  reference x outside the try, so we can use it both in try and catch like so
*
*       int x, y;
        try{
            x = getInt();
        } catch () {
            x = getInt()
        }
*
* Throw
* Now in our divide() method there is not much we can do about the Divide by Zero or NoSuchElement exceptions, so the
*  best we can do is to throw an exception ourselves -
*     private static int divide() throws ArithmeticException {
        int x, y;
        try{
            x = getInt();
            y = getInt();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No suitable input");
        }

        System.out.println("x is " + x + ", y is " + y);
        try {
            return x / y;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Attempt to divide by zero");
        }
    }
*
* Here we expect these types of errors to come about, so in our catch block we throw a an exception object to the
* StackTrace - this not only makes the StackTrace more concise but easier to read as Java does need to propagate up
* the hierarchy to find the exception handler or something that throws an exception object to deal with it
*
*   - throw new ArithmeticException("No suitable input")
*
* What we have done used the keyword throw - which will basically an exitpoint out of the StackTrace, and we have to
* construct an Exception so use the new operator followed by the ExceptionClass that will be outputted to the
* StackTrace saying what Exception we got. We can also pass in a String literal which is useful to us programmers as
* we can get a message shown there to tell us basically what went wrong.
*
* No suitable input
* Exception in thread "main" java.lang.ArithmeticException: No suitable input
	at com.example.Example.divide(Example.java:19)
	at com.example.Example.main(Example.java:9)
*
* divide by zero
* Exception in thread "main" java.lang.ArithmeticException: Attempt to divide by zero
	at com.example.Example.divide(Example.java:26)
	at com.example.Example.main(Example.java:9)
*
* The code above is a bit clumsy, in which we have 2 try blocks - and its actually recommended to merge the 2
* together like so
*     private static int divide() throws ArithmeticException {
        int x, y;
        try{
            x = getInt();
            y = getInt();
            System.out.println("x is " + x + ", y is " + y);
            return x / y;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No suitable input");
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Attempt to divide by zero");
        }
    }
*
* Its now more readable, and the bulk of the code we are trying is  all in the try block - so its all in one place now
* Now the whenever an exception occurs in the try block, Java will look at the catch blocks one at a time, and if
* finds one that matches the current Exception the dive into that catch block and ignore every other catch block
*
* NOTE: multi-catch
* catch(NoSuchElement | IOException e) we use single pipe(|) for bitwise logical OR
*
* In a real world example we would actually catch the Exceptions in the main() rather than have them all in the
* divide(), simply because it makes the code more readable
* */