package com.company;

public class Shelf {
    private int shelves;
    private com.company.Book books;
    private String material;
    private int weight;
    private Dimensions dimensions;

    public Shelf(int shelves, Book books, String material, int weight, Dimensions dimensions) {
        this.shelves = shelves;
        this.books = books;
        this.material = material;
        this.weight = weight;
        this.dimensions = dimensions;
    }

    public void takeABook() {
        System.out.println("Book taken off the shelf");
    }

    public com.company.Book getBooks() {
        return books;
    }

    public String getMaterial() {
        return material;
    }

    public int getWeight() {
        return weight;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public int getShelves() {
        return shelves;
    }
}
