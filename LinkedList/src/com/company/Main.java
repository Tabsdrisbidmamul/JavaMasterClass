package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer("Tim", 54.96);
        Customer customer1;
        customer1 = customer;
        customer1.setBalance(12.18);
        System.out.println("Balance for customer " + customer.getName() + " is " + customer.getBalance());

        ArrayList<Integer> integerArrayList = new ArrayList<>();

        integerArrayList.add(1);
        integerArrayList.add(3);
        integerArrayList.add(4);

        for (int i=0; i<integerArrayList.size(); i++){
            System.out.println(i + ": " + integerArrayList.get(i));
        }

        integerArrayList.add(1, 2);
        System.out.println();

        for (int i=0; i<integerArrayList.size(); i++){
            System.out.println(i + ": " + integerArrayList.get(i));
        }
    }
}

/*
* Linked List
* Index     Address     Value
* 0         100         34
* 1         104         18
* 2         108         91
* 3         112         57
* 4         116         453
* 5         120         68
* 6         124         6
*
* When creating LinkedLists, Java will allocate 4 bytes of memory for each integer (2 ^ (32 bits/ 4 bytes) - 1), this
* is because it needs this amount to store the highest amount of an integer - and Java will attempt to do this
* contiguously there is a formula that Java uses to access different array elements at given index positions
*
* It does this by using the base address - usually the address at index 0, plus 4 * the index number so
*   (base address) + (4 * index position)
*   100 + (3 * 4)
*   100 + 12
*   112
*   Index Position (112) --> Value  (57)
*
* This example is for the primitive type int, in the case of a double or a long they use 64 bits or 8 bytes of memory
* so the formula will change instead of (4bytes*x) it will be (8bytes*x)
*
* What is mentioned above works well with fixed values or constants like the primitive types, but what about Strings
* or objects where their amount of memory they use is variable, Java does this instead
*
* Index     Address     String Address          Address     Done
* 0         100         1034                    1024        Hello World
* 1         104         1037                    1034        Tim
* 2         108         1046                    1037        Australia
* 3         112         1024                    1046        Java
* 4         116         4000                    1050        Array
* 5         120         1050                    1055        ArrayList
* 6         124         1055                    4000        Done
*
* We can see that the formula here is using 8 bytes, (base address) + (index pos * 8 bytes)
* But what is interesting is that at each address, the value being stored in the array is actually a memory address /
* reference to where String is actually stored in memory
*
* So at index position 0, the value stored here is a String address of 1034 which points to "Tim" so (1034 --> "Tim")
* And the order is preserved as well, so when outputted, the Strings are
*   Tim
*   Australia
*   Java
*   Hello World
*   Done
*   Array
*   ArrayList
*
* What is happening is that Java is going through the array, seeing that String address's are stored, so it jumps to
* that address where it is stored, and retrieving it and outputting it to the console
*
* NOTE: The actual String address do not need to be contiguous, as they Java is jumping to those address when needed
* Another point is when the the code execution has gone out of scope, so it has passed the array, then Java's garbage
* collector will terminate the variable and free-up space for later use
*
* LinkedLists
*
*   Tim | 1037 --> Australia | 1046 --> Java | 1024 --> Hello World | 4000 --> Done | 1050 --> Array --> 1055 -->
*   ArrayList | null --> null
*
* The LinkedList contains the data value and the actual memory address of where the next item is in the array
* In the example using an ArrayList
*           integerArrayList.add(1, 2);
* Where we are inserting the value 2 at index position 1, every element after at index position 1 needs to move down
* one to allow the value of 2 to be inserted - when doing this in an Array with thousands or millions of objects,
* this will be very time consuming and resource hungry as the computer is doing a lot of work
*
* But with LinkedLists, if we want to insert an item into the list, all we have to do is locate the position where we
* want it (say after Tim), then all we have to do is change the pointer at Tim to point to the String object "Splash"
* and have Splash point to Australia afterwards.
*
*   Tim | 1037 --> Australia | 1046
*   Tim | 1200 -->  Splash | 1037 -->  Australia | 1046 --> ...
*
* We can also do the same where we want to remove entries from the list, say Hello World we simply change the
* pointers of Java to point to Done - which is Hello World's neighbour effectively
*
*   Java | 1024 --> Hello World | 4000 --> Done | 1050 --> ...
*   Java | 4000 --> Done | 1050 --> ...
*
* Now Hello World is still pointing to Done, but as nothing is referring to Hello World (i.e pointing to Hello World)
* Java's garbage collector will destroy Hello World basically freeing up memory
*
* Java have developed the linkedList object as a double LinkedList
*
*
* LinkedList initialise
* This initialises the LinkedList, and you can do the methods below
*   LinkedList<String> placedToVisit = new LinkedList<>();
*
* LinkedList methods
* Add
* This will add the specified object passed to the linked list, and it is added at the end, so it has a pointer to
* the end of the linked list and essentially appends it
*   stringListIterator.add(newCity);
*
* This will add the object at index position 1, so every other object at 1 and below will be pushed down and the the
* object passed will be added
*   stringListIterator.add(1, newCity);
*
* Remove
* This will remove the last object in the linked list
*   stringListIterator.remove();
*
* This will remove the object at index position 4 from the linked list, and every other item is moved up by one
*   stringListIterator.remove(4);
*
* Next
* This will move the current pointer to the next item along the linked list
*   stringListIterator.next()
*
* Previous
* This will move the current pointer to the previous item before the current pointer
*   stringListIterator.previous();
*
* Iterator
* This is a an object in Java that allows us to essentially do a for loop over a iterable - such as LinkedLists
*   Iterator<String> iterator = linkedList.iterator();
*
* We use the .hasNext() method so
*   iterator.hasNext()
* to check if the current item points to another object in the LinkedList - if so return true, else return false
* (usually when the LinkedList points to null, the end of the list)
*
* We use the .next() method so
*   iterator.next()
* To move to the next object in the LinkedList, once the .next() method has been executed, the pointer will move to
* the next object - whether we want it or not, pairing this with the .previous() method, allows us to move back one
* if say we have to get the next object in the LinkedList for a comparison - but we want to go back and then add it
* between ([after .next() has been executed] pointer - 1) and pointer
*
* ListIterator
* This is an object that allows us to go over a LinkedList
*    ListIterator<String> stringListIterator = linkedList.listIterator()
*
* Has .hasNext() and .next() like above
*
* .getFirst()
* Will retrieve the first object in a LinkedList
*   cities.getFirst()
*
* .isEmpty()
* Checks if the size of the LinkedList == 0 - in other words is it empty
*   cities.isEmpty()
*
* .hasPrevious()
* Returns a boolean value, depending if there is an object previous to the current pointer
*
*
* String.compareTo()
* A String comparison tool and that will compare objectA with objectB and return an int value depending on its results
*   stringListIterator.next().compareTo(newCity)
*                   or
*   "Abacus".compareTo("Calculator")
*
* We compare the String A (Abacus) with String B (Calculator) and we are saying
* is Abacus > Calculator which is false
*
* compareTo will return a int value
*   - > 0 StringA > StringB
*   - < 0 StringA < StringB
*   - == 0 StringA == StringB
*
*
* Java has implemented the LinkedList in a different way that what we might think, the LinkedList does not have a
* current element pointer, instead its pointer lies in-between elements that would be returned when calling to a
* previous() and the element that would be returned when calling next()
* NOTE: an iterator that has a list of length, has n+1 possible cursor positions (^)
* When previous() or next() is called, it actually returns the item that is next to the caret, so say we are at caret
* position in-between Tim and Splash, and we go forward - so next(), it will return Splash and move the caret to
* Australia
*
*
*       Tim | 1200 -->  Splash | 1037 -->  Australia | 1046 --> ...
*      ^               ^                  ^                     ^
*
* This is because if it worked using current element pointer's, then when traversing the LinkedList - forwards and
* backwards - you never actually have left the element that you started at. This causes basically an infinite loop
* effectively and such by having the cursor hover between elements fixes that issue
*
* So to get it to work, you need a flag to check if we are going forwards or backwards and basically call previous() /
* next() twice depending on the direction
* We set the goingForward flag to true
*
* check to see if we are going forward by using the NOT operator on the goingForward flag
* We see that we have been going backwards, so we see if the list has item that can go next(), if so, move the caret
* pointer to the next item ready to show
*   if(!goingForward) {
*       if(listIterator.hasNext()) {
*           listIterator.next();
*       }
*       goingForward = true;
*   }
*
* check to see if we are going backwards by using the NOT operator on the goingForward flag
* We see that we have been going forwards, so we see if the list has item that can go previous(), if so, move the caret
* pointer to the previous item ready to show
*   if(goingForward) {
*       if(listIterator.hasPrevious()) {
*           listIterator.previous();
*       }
*       goingForward = false;
*    }
 * */
