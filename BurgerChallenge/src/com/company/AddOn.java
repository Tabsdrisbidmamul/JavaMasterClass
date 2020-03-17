package com.company;

public class AddOn {
    private double price;
    private int amount = 0;
    private boolean added = false;

    public AddOn(double price) {
        this.price = price;
        this.amount = 0;
    }

    public double getPrice() {
        return price * amount;
    }

    public int getAmount() {
        return amount;
    }

    public void incrementAmount(int amount) {
        this.amount += amount;
    }

    public void setAddedToTrue() {
        added = true;
    }
}
