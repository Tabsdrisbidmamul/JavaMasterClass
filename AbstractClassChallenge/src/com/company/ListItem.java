package com.company;


public abstract class ListItem {
    private int data;
    private ListItem next;
    private ListItem previous;

    public ListItem(int data) {
        this.data = data;
        next = null;
        previous = null;
    }

    public abstract int compareTo(ListItem currentNode, ListItem newNode);


    public int getData() {
        return data;
    }

    public ListItem getNext() {
        return next;
    }

    public void setNext(ListItem next) {
        this.next = next;
    }

    public ListItem getPrevious() {
        return previous;
    }

    public void setPrevious(ListItem previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Data is " + data;
    }
}
