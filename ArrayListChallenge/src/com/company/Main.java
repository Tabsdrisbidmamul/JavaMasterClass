package com.company;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("+(44) 1234 5678");

    public static void main(String[] args) {
        // Create a program that implements a simple mobile phone with the following capabilities.
        // Able to store, modify, remove and query contact names.
        // You will want to create a separate class for Contacts (name and phone number).
        // Create a master class (MobilePhone) that holds the ArrayList of Contacts
        // The MobilePhone class has the functionality listed above.
        // Add a menu of options that are available.
        // Options:  Quit, print list of contacts, add new contact, update existing contact, remove contact
        // and search/find contact.
        // When adding or updating be sure to check if the contact already exists (use name)
        // Be sure not to expose the inner workings of the ArrayList to MobilePhone
        // e.g. no ints, no .get(i) etc
        // MobilePhone should do everything with Contact objects only.

        boolean quit = false;
        int choice = 0;
        printInstructions();
        while ((!quit)) {
            System.out.println("Enter your choice");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    printContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    searchForContact();
                    break;
                case 6:
                    quit = true;
                    break;
                default:
                    System.out.println("Re-Enter your choice from the menu");
                    printInstructions();
            }
        }
    }

    private static void printInstructions() {
        System.out.println("\nEnter from the choices below: ");
        System.out.println("\t 0 - To show the menu");
        System.out.println("\t 1 - To show all contacts");
        System.out.println("\t 2 - To add a new contact");
        System.out.println("\t 3 - To update an existing contact");
        System.out.println("\t 4 - To remove an existing contact");
        System.out.println("\t 5 - To search for an existing contact");
        System.out.println("\t 6 - To quit the program");
    }

    private static void printContacts() {
        System.out.println("Contacts: ");
        mobilePhone.printContacts();
    }

    private static void addContact() {
        System.out.println("Enter the name and phone of the new contact");
        System.out.println("Enter the name");
        String name = scanner.nextLine();

        System.out.println("Enter their phone number");
        String phoneNumber = scanner.nextLine();

        if(mobilePhone.addNewContact(Contact.createContact(name, phoneNumber))) {
            System.out.println("New Contact has added");
        }
    }

    private static void updateContact() {
        System.out.println("Enter the existing contact details name that are to be changed");
        String oldName = scanner.nextLine();

        Contact contactInPhone = mobilePhone.getContact(oldName);
        if(contactInPhone != null) {
            System.out.println("Enter the details of the new contact");
            System.out.print("Name: ");
            String newName = scanner.nextLine();

            System.out.print("Enter their phone number");
            String newPhoneNumber = scanner.nextLine();

            if(mobilePhone.updateContact(contactInPhone, Contact.createContact(newName, newPhoneNumber))) {
                System.out.println("Contact has been updated");
            }
        } else {
            System.out.println("Contact Not Found");
        }

    }

    private static void removeContact() {
        System.out.println("Enter the name of the contact you want to remove");
        String nameToRemove = scanner.nextLine();

        Contact contact = mobilePhone.getContact(nameToRemove);
        if (contact != null) {
            mobilePhone.removeContact(contact);
        } else {
            System.out.println("Contact Not Found");
        }
    }

    private static void searchForContact() {
        System.out.println("Enter the name of the contact you wish to see if it is in your phone");
        String name = scanner.nextLine();

        Contact contact = mobilePhone.getContact(name);
        if(contact != null ) {
            mobilePhone.inContacts(contact);
        } else {
            System.out.println("Contact Not Found");
        }

    }
}
