package com.company;

public class Concrete extends ListItem {
    public Concrete(int data) {
        super(data);
    }

    public int compareTo(ListItem currentNode, ListItem newNode) {
        if (currentNode.getData() == newNode.getData()) {
            return 0;
        } else if (currentNode.getData() > newNode.getData()) {
            return Integer.MIN_VALUE;
        } else {    // currentNode.data < newNode.data
            return Integer.MAX_VALUE;
        }
    }
}
