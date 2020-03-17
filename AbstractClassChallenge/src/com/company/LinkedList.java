package com.company;

public class LinkedList {
    private Concrete head;
    private Concrete concrete;

    public LinkedList(int data) {
        this.concrete = new Concrete(data);
    }

    public void add(int data) {
        if(this.head != null) {
            Concrete newNode = new Concrete(data);
            System.out.println(newNode);
            ListItem currentNode = this.head;
            System.out.println(currentNode);

//            while(currentNode.getNext() != null) {
//                currentNode = currentNode.getNext();
//
//                int comparison = concrete.compareTo(currentNode, newNode);
//                if(comparison == 0) {
//                    System.out.println("Duplicates not allowed!");
//                } else if (comparison > 0) {
//                    continue;
//                } else if(comparison < 0) {
//                    ListItem previousNode = currentNode.getPrevious();
//                    previousNode.setNext(newNode);
//                    newNode.setNext(currentNode);
//                }
//            }
            while(currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
            System.out.println(currentNode);
            newNode.setPrevious(currentNode);
            System.out.println(newNode);
        } else {
            this.head = new Concrete(data);
        }

    }

    public void printList(LinkedList linkedList) {
        System.out.println("Printing LinkedList: ");
        while (head.getNext() != null) {
            System.out.println("\tContents are: " + head.getData());
            head = (Concrete) head.getNext();
        }
    }

}
