package com.company;

public class Main {

    public static void main(String[] args) {
	    int count = 0;
	    while (count != 5) {
            System.out.println("Count value is " + count);
            count++;
        }

        System.out.println();

	    count = 0;
        while(true) {
            if (count == 5) {
                break;
            }
            System.out.println("Count value is " + count);
            count++;
        }

//        count = 6;
        do {
            System.out.println("Count value was " + count);
            count++;

            if (count > 100) {
                break;
            }
        } while (count != 5);

        // identical to the whiles loop above
	    for(count=0; count < 5; count++) {
            System.out.println("Count value is " + count);
        }

        // Create a method called isEvenNumber that takes a parameter of type int
        // Its purpose is to determine if the argument passed to the method is
        // an even number or not.
        // return true if an even number, otherwise return false;

        System.out.println(isEvenNumber(5));
        System.out.println(isEvenNumber(2));
        System.out.println(isEvenNumber(-4));
        System.out.println(isEvenNumber(6));

        int start = 4;
        int finish = 20;
        int counter = 0;

        while( start <= finish ) {
            start++;
            if (!isEvenNumber(start)) {
                continue;
            }
            System.out.println("Even number " + start + " was found");
            counter++;

            if(counter == 5) {
                break;
            }
        }
        System.out.println("Total number of even numbers found " + counter);


        // Modify the while code above
        // Make it also record the total number of even numbers it has found
        // and break once 5 are found
        // and at the end, display the total number of even numbers found
    }

    public static boolean isEvenNumber(int number) {
        return number > 0 && number % 2 == 0;
    }
}

/*
* While
* The while loop allows us to loop a code block an unknown amount of times - until a particular condition is met
* (sometimes called an indefinite loop)
*
* while(condition) {
*   -- do something here
*   // increment condition (usually that is the case to make sure the condition is actually met, and stop infinite
*   loops)
* }
*
*
* Do While
* The do while loop is similar to the while loop, but it will always run the code at least once, regardless if the
* condition is true or false
*
* do {
*   -- do something here
* // increment counter
* } while (condition)
*
* so the code that is to be executed is placed in the code block, and the while condition is placed at the end of the
*  code block, but effectively will run the same way as the while loop
*
* Continue: keyword used in loops, basically allows the current iteration to be skipped - not executing any of thr
* code in the code block, and will restart effectively at the next iteration (+1 to the loop)
*
* Break: keyword used in loops, stops the loop execution and jumps out of it, to the end of the code block to the
* next line of code
* */
