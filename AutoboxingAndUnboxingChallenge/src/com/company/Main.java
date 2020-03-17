package com.company;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        // You job is to create a simple banking application.
        // There should be a Bank class
        // It should have an arraylist of Branches
        // Each Branch should have an arraylist of Customers
        // The Customer class should have an arraylist of Doubles (transactions)
        // Customer:
        // Name, and the ArrayList of doubles.
        // Branch:
        // Need to be able to add a new customer and initial transaction amount.
        // Also needs to add additional transactions for that customer/branch
        // Bank:
        // Add a new branch
        // Add a customer to that branch with initial transaction
        // Add a transaction for an existing customer for that branch
        // Show a list of customers for a particular branch and optionally a list
        // of their transactions
        // Demonstration autoboxing and unboxing in your code
        // Hint: Transactions
        // Add data validation.
        // e.g. check if exists, or does not exist, etc.
        // Think about where you are adding the code to perform certain actions

        boolean quit = false;
        int choice = 0;
        printInstructions();
        while (!quit){
            System.out.print("Enter your choice below: (0 to show the menu again) \n");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    addNewBranch();
                    break;
                case 2:
                    addNewCustomer();
                    break;
                case 3:
                    addTransactionToCustomer();
                    break;
                case 4:
                    printBank();
                    break;
                case 5:
                    quit = true;
                    break;
                default:
                    System.out.println("Re-Enter from the choices below");
                    printInstructions();
                    break;
            }
        }

    }

    private static void printInstructions() {
        System.out.println("Enter from the choices below:\n" +
                            "\t 0 - To show the menu again\n" +
                            "\t 1 - To add a new branch\n" +
                            "\t 2 - To add a customer with an initial transaction\n" +
                            "\t 3 - To add a transaction to an existing customer\n" +
                            "\t 4 - To show all customers in a given branch, and the customer transactions\n" +
                            "\t 5 - To quit the program\n");
    }

    private static void addNewBranch() {
        System.out.println("Enter the new name of the branch you are adding");
        String branchName = scanner.nextLine();

        if(bank.addBranch(branchName)) {
            System.out.println("Branch " + branchName + " added.");
        } else {
            System.out.println("Error adding the branch");
        }
    }

    private static void addNewCustomer() {
        System.out.println("Enter the following: Branch Name, Customer Name and an Initial Transaction");

        System.out.print("Enter Branch Name: ");
        String branchName = scanner.nextLine();

        System.out.print("Enter the Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter the Initial Transaction amount to be placed into the Customer" + customerName +
                " account: ");
        double initialTransaction = scanner.nextDouble();
        scanner.nextLine();

        if(bank.addCustomerToBranch(customerName, initialTransaction, branchName)) {
            ;
        } else {
            System.out.println("Error adding Customer " + customerName + " to Branch " + branchName);
        }
    }

    private static void addTransactionToCustomer() {
        System.out.println("Enter the following: Branch Name, Customer Name and the Transaction Amount\n");

        System.out.print("Enter the Branch Name: ");
        String branchName = scanner.nextLine();

        System.out.print("Enter the Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter the Transaction amount to be placed into the Customer" + customerName +
                " account: ");
        double initialTransaction = scanner.nextDouble();
        scanner.nextLine();

        if(bank.addTransactionToExistingCustomer(customerName, initialTransaction, branchName)) {
            System.out.println("Transaction was successful");
        } else {
            System.out.println("Transaction failed");
        }
    }

    private static void printBank() {
        System.out.println("Please enter the Branch Name you want to see");

        String branchName = scanner.nextLine();
        bank.printCustomerAndTransactions(branchName);
        System.out.println();
    }
}
