package com.company;

public abstract class ListItem {
    protected ListItem previous = null;
    protected ListItem next = null;

    protected Object data;

    public ListItem(Object data) {
        this.data = data;
    }

    public abstract int compareTo(ListItem item);

    public abstract ListItem getPrevious();
    public abstract ListItem setPrevious(ListItem previous);
    public abstract ListItem getNext();
    public abstract ListItem setNext(ListItem next);

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
