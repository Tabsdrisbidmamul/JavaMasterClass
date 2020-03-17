package com.company;

public class Furniture {
    private Chair chair;
    private Table table;
    private Shelf shelf;

    public Furniture(Chair chair, Table table, Shelf shelf) {
        this.chair = chair;
        this.table = table;
        this.shelf = shelf;
    }

    public void takeAnItem() {
        shelf.takeABook();
    }

    public void sitOnChair() {
        chair.sit();
    }

    public Chair getChair() {
        return chair;
    }

    public Table getTable() {
        return table;
    }

    public Shelf getShelf() {
        return shelf;
    }
}
