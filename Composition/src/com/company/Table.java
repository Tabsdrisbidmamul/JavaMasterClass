package com.company;

public class Table {
    private int weight;
    private Dimensions dimensions;
    private String material;

    public Table(int weight, Dimensions dimensions, String material) {
        this.weight = weight;
        this.dimensions = dimensions;
        this.material = material;
    }


    public int getWeight() {
        return weight;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public String getMaterial() {
        return material;
    }
}
