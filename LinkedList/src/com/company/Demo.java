package com.company;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        LinkedList<String> placedToVisit = new LinkedList<>();
        addInOrder(placedToVisit, "Sydney");
        addInOrder(placedToVisit, "Melbourne");
        addInOrder(placedToVisit, "Brisbane");
        addInOrder(placedToVisit, "Perth");
        addInOrder(placedToVisit, "Canberra");
        addInOrder(placedToVisit, "Adelaide");
        addInOrder(placedToVisit, "Darwin");
        printList(placedToVisit);

        addInOrder(placedToVisit, "Alice Springs");
        addInOrder(placedToVisit, "Darwin");

        printList(placedToVisit);

        visit(placedToVisit);

    }

    private static void printList(LinkedList<String> linkedList) {
        Iterator<String> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println("Now visiting " + iterator.next());
        }
        System.out.println("--------------------------------------------------------");
    }

    private static boolean addInOrder(LinkedList<String> linkedList, String newCity) {
        // When we initialise the ListIterator, it sets up the iterator, but does not point to the first item, we
        // have to do a .next() to do that, or we will get an error, as Java does not know where to get the item from
        ListIterator<String> stringListIterator = linkedList.listIterator();
        while (stringListIterator.hasNext()) {
            int comparison = stringListIterator.next().compareTo(newCity);
            if(comparison == 0) {
                // equal, do not add
                System.out.println(newCity + " is already included as a destination");
                return false;
            } else if (comparison > 0) {
                // comparison is saying that Brisbane > Adelaide which is right
                // new City, should appear before this one
                // Brisbane -> Adelaide
                // We do this, because the .next() at when we initialise the ListIterator will get the next item, but
                // also moves the pointer to that item as well, do we need to move the pointer back one and then add it
                // pointer: Brisbane --> .previous()
                // pointer one before Brisbane --> newCity --> Brisbane
                // That's what it should look like
                stringListIterator.previous();
                stringListIterator.add(newCity);
                return true; 
            } else if (comparison < 0) {
                // move on next city
                // so say the pointer is at Adelaide, and we pass Brisbane, we cannot add Brisbane as B comes after A
                // We are placing items before - so at comparison > 0
                // We have already done a .next(0 so we have effectively already moved on to the next city
            }
        }
        stringListIterator.add(newCity);
        return true;
    }

    private static void visit(LinkedList<String> cities) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean goingForward = true;
        ListIterator<String> listIterator = cities.listIterator();

        if(cities.isEmpty()) {
            System.out.println("No cities in the itinerary");
            return;
        } else {
            System.out.println("Now visiting " + listIterator.next());
            printMenu();
        }

        while (!quit) {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Holiday (Vacation) over");
                    quit = true;
                    break;
                case 1:
                    if(!goingForward) {
                        if(listIterator.hasNext()) {
                            listIterator.next();
                        }
                        goingForward = true;
                    }
                    if(listIterator.hasNext()) {
                        System.out.println("Now visiting " + listIterator.next());
                    } else {
                        System.out.println("Reached the end of the list");
                        goingForward = false;
                    }
                    break;
                case 2:
                    if(goingForward) {
                        if(listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        goingForward = false;
                    }
                    if(listIterator.hasPrevious()) {
                        System.out.println("Now visiting " + listIterator.previous());
                    } else {
                        System.out.println("We are at the start of the list");
                        goingForward = true;
                    }
                    break;
                case 3:
                    printMenu();
                    break;
            }
        }

    }
    private static void printMenu() {
        System.out.println("Available actions:\npress");
        System.out.println("0 - to quit\n" +
                "1 - go to next city\n" +
                "2 - go to previous city\n" +
                "3 - print menu options");
    }
}
