package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static GroceryList groceryList = new GroceryList();

    public static void main(String[] args) {
        boolean quit = false;
        int choice = 0;
        printInstructions();
        while (!quit) {
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    groceryList.printGroceryList();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    modifyItem();
                    break;
                case 4:
                    removeItem();
                    break;
                case 5:
                    searchForItem();
                    break;
                case 6:
                    processArrayList();
                case 7:
                    quit = true;
                    break;
                default:
                    System.out.println("Please re-enter your choice within the range of 066 (inclusive)");
            }
        }
    }

    private static void printInstructions(){
        System.out.println("\nEnter any of:");
        System.out.println("\t 0 - To print choice options");
        System.out.println("\t 1 - To print the list of grocery items");
        System.out.println("\t 2 - To add an item to the list");
        System.out.println("\t 3 - To modify an item in the list");
        System.out.println("\t 4 - To remove an item from the list");
        System.out.println("\t 5 - To search for an item in the list");
        System.out.println("\t 6 - To quit the application");

    }

    private static void addItem() {
        System.out.print("Please enter the grocery item: ");
        groceryList.addGroceryItem(scanner.nextLine());
    }

    private static void modifyItem() {
        System.out.print("Please enter current item name: ");
        String itemNo = scanner.nextLine();
        System.out.print("Enter replacement item: ");
        String newItem = scanner.nextLine();
        groceryList.modifyGroceryItem(itemNo, newItem);

    }

    private static void removeItem() {
        System.out.print("Enter the item name: ");
        String itemNo = scanner.nextLine();
        groceryList.removeGroceryItem(itemNo);

    }

    private static void searchForItem() {
        System.out.print("Item to search for: ");
        String searchItem = scanner.nextLine();
        if(groceryList.onFile(searchItem)) {
            System.out.println("Found " + searchItem + " in your grocery list");
        } else {
            System.out.println(searchItem + " is not in the grocery list");
        }
    }

    private static void processArrayList() {
        ArrayList<String> newArray = new ArrayList<String>();
        newArray.addAll(groceryList.getGroceryList());

        ArrayList<String> nextArray = new ArrayList<String>(groceryList.getGroceryList());

        String[] myArray = new String[groceryList.getGroceryList().size()];
        myArray = groceryList.getGroceryList().toArray(myArray);
    }


}

/*
* List and ArrayList
* As we know, when defining an array object, its size is static, so its amount of memory is pre-allocated when
* instantiated, and the only way to resize it, i.e to grow or shrink - is too de-reference the variable pointer to
* that array object in memory and re-reference a new array to a new reference variable - and if we wanted to copy the
* old contents to the new sized array - we can do like this
*
*   private static Scanner scanner = new Scanner(System.in);
    private static int[] baseData = new int[10];

    public static void main(String[] args) {
        System.out.println("Enter 10 integers");
        getInput();
        printArray(baseData);
        resizeArray();
        System.out.println("Enter 12 integers");
        getInput();
        printArray(baseData);
    }

    private static void getInput() {
        for (int i = 0; i < baseData.length; i++) {
            baseData[i] = scanner.nextInt();
        }
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i ++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private static void resizeArray() {
        int[] original = baseData;

        baseData = new int[12];
        for (int i = 0; i < original.length; i++) {
            baseData[i] = original[i];
        }
    }
*
* This is very tedious and resource consuming, so instead - use Java's ArrayList and List interfaces instead
*
* ArrayList
* ArrayList in Java is an object that allows us store objects in a list format similar to arrays - but for objects
* It also does not need to state a static amount of space in memory as it is a dynamic list which grows and shrinks
* when needed
*
* To create an ArrayList we do
*   private ArrayList<String> groceryList = new ArrayList<String>();
*
* As ArrayList stores objects, we have to use a generic operator to state to Java that this will store this object in
* its memory buffer, we give it a reference variable name, and then use the new operator to instantiate it, and we
* use the constructor ArrayList<object>, passing in the same object as we did on the left side, followed by
* parenthesis, this is to say we are calling the empty constructor.
*
* Now with ArrayLis, we have to use its methods to access the list, instead of the array access operator ([]), so
* Add
*   arrayList.add(object type) -> appends the object to the list
*
* Retrieve
*   arrayList.get(index position) -> retrieves the object from the list
*
* modify
*   arrayList.set(index position, new object type) -> changes the old object with the new object at the index position
*
* remove
*   arrayList.remove(index position) -> removes the object from the list, and the list will move all the objects down
*   one to reflect that change (like a circular queue)
*
* search/ contains
*   arrayList.contains(object type) -> returns boolean value true if it is in the list, false otherwise
*   arrayList.indexOf(object type) -> return int value of index position if it is in the list, -1 otherwise
*
* To copy the contents of one ArrayList to another we can do either of the two
*
* Here We instantiated a new ArrayList object, and called the addAll method which accepts an ArrayList, and can place
* an ArrayList here for it to be copied
*
*   ArrayList<String> newArray = new ArrayList<String>();
*   newArray.addAll(groceryList.getGroceryList());
*
*                               Or
*
* When instantiating an ArrayList object, we can pass in the ArrayList we want to copy as an argument to the
* constructor and it will copy the contents of the first ArrayList to the second ArrayList
*
*   ArrayList<String> nextArray = new ArrayList<String>(groceryList.getGroceryList());
*
* If we wanted to convert an ArrayList back to a normal Array, we can do this
*
*   String[] myArray = new String[groceryList.getGroceryList().size()];
*   myArray = groceryList.getGroceryList().toArray(myArray);
*
* Here we create a String array, and have its static size equal to the size of the ArrayList object, and then we use
* the ArrayList method to convert the ArrayList back to an array using the .toArray(array) passing in the array
* reference variable as an argument.
*
* */
