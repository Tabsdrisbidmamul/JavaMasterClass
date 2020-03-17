package com.company;

public class DeluxeBurger extends Hamburger {
    private Chips chips;
    private Drinks drinks;

    public DeluxeBurger(String rollType, String meat, double basePrice, String name) {
        super(rollType, meat, basePrice, name);
        this.chips = new Chips();
        this.drinks = new Drinks();
    }

    public void addAddOn() {
        System.out.println("You cannot have anymore addons with this burger!");
    }

    public void getGrandTotal() {
        // need to add additional with the base price
        double grandTotal = 0;
        grandTotal += getBasePrice() + chips.getPrice() + drinks.getPrice();
        System.out.println("The base price of the " + DeluxeBurger.class.getSimpleName()  + " is £" + getBasePrice() +
                " The additional's added are:\n\t" +
                chips.getClass().getSimpleName() + ": £" + chips.getPrice() + " " +
                drinks.getClass().getSimpleName() + ": £" + drinks.getPrice() +
                "\nGrand Total: £ " + grandTotal
        );

    }
}
