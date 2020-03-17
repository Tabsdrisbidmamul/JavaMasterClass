package com.company;

public class Main {

    public static void main(String[] args) {
        Hamburger hamburger = new Hamburger("White Bread", "Extra Meaty Meat", 1.00,
                "Meaty Meat Whopper");
        hamburger.addAddOn(2, "Lettuce");
        hamburger.addAddOn(3, "cheese");
        hamburger.getGrandTotal();

        System.out.println();

        HealthyBurger healthyBurger = new HealthyBurger("Vegan Meat", 1.50, "Vegan Smasher");
        healthyBurger.addAddOn(1, "tomAto");
        healthyBurger.addAddOn(3, "CARROTS");
        healthyBurger.addAddOn(4, "lettuce");
        healthyBurger.addAddOn(2, "lettuce");
        healthyBurger.getGrandTotal();

        System.out.println();

        DeluxeBurger deluxeBurger = new DeluxeBurger("White Bread", "12oz meat", 2.00,
                "Deluxe Special");
        deluxeBurger.addAddOn();
        deluxeBurger.getGrandTotal();
    }
}
