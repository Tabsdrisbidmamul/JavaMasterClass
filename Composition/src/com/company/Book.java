package com.company;

public class Book {
    private int weight;
    private int numberOfPages;
    private Dimensions dimensions;

    public Book(int weight, int numberOfPages,Dimensions dimensions) {
        this.weight = weight;
        this.numberOfPages = numberOfPages;
        this.dimensions = dimensions;

    }

    public void openBook() {
        System.out.println("Book opened");
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
    public int getWeight() {
        return weight;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }
}
