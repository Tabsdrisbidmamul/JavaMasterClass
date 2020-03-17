package com.company;

public class Node extends ListItem {
    public Node(Object data) {
        super(data);
    }

    public int compareTo(ListItem item) {
        if (item != null) {
            return ((String) super.getData()).compareTo((String) item.getData());
        } else {
            return -1;
        }
    }

    public ListItem getPrevious() {
        return this.previous;
    }

    public ListItem setPrevious(ListItem previous) {
        this.previous = previous;
        return this.previous;
    }

    public ListItem getNext() {
        return this.next;
    }

    public ListItem setNext(ListItem next) {
        this.next = next;
        return this.next;
    }

    @Override
    public String toString() {
        return "Value: " + getData();
    }
}