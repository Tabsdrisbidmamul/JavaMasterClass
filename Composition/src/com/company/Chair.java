package com.company;

public class Chair {
    private int weight;
    private String material;

    public Chair(int weight, String material) {
        this.weight = weight;
        this.material = material;
    }

    public void sit() {
        System.out.println("Now sitting on chair...");
    }

    public int getWeight() {
        return weight;
    }

    public String getMaterial() {
        return material;
    }
}
