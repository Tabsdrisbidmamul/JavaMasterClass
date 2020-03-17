package com.company;

public class Hamburger {
    private String rollType;
    private String meat;
    private double basePrice;
    private String name;
    private int addOns = 0;
    private Lettuce lettuce;
    private Tomato tomato;
    private Cheese cheese;
    private Carrot carrot;

    public Hamburger(String rollType, String meat, double basePrice, String name) {
        this.rollType = rollType;
        this.meat = meat;
        this.basePrice = basePrice;
        this.name = name;
        this.lettuce = new Lettuce();
        this.tomato = new Tomato();
        this.cheese = new Cheese();
        this.carrot = new Carrot();
    }

    public void getGrandTotal() {
        // need to add additional with the base price
        double grandTotal = 0;
        grandTotal += basePrice + (lettuce.getPrice() + tomato.getPrice() + cheese.getPrice() + carrot.getPrice());
        System.out.println("The base price of the " +  Hamburger.class.getSimpleName() + " is £" + basePrice +
                " The additional's added are:\n\t" +
                lettuce.getClass().getSimpleName() + ": £" + lettuce.getPrice() + " " +
                tomato.getClass().getSimpleName() + ": £" + tomato.getPrice() + " " +
                cheese.getClass().getSimpleName() + ": £" + cheese.getPrice() + " " +
                carrot.getClass().getSimpleName() + ": £" + carrot.getPrice() +
                "\nGrand Total: £ " + grandTotal
        );

    }

    public Lettuce getLettuce() {
        return lettuce;
    }

    public Tomato getTomato() {
        return tomato;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public Carrot getCarrot() {
        return carrot;
    }


    protected boolean isMaxAddOns() {
        return addOns == 4;
    }

    protected void addItem(int amount, AddOn name) {
        name.incrementAmount(amount);
        this.addOns += amount;
        System.out.println(amount + " "+ name.getClass().getSimpleName() + " was added");
    }

    public void addAddOn(int amount, String addOnName) {
        amount = Math.abs(amount);
        if( (amount + addOns <= 4) && (addOns <= 4)) {
            switch (addOnName.toLowerCase()) {
                case "lettuce":
                    addItem(amount, lettuce);
                    break;
                case "tomato":
                    addItem(amount, tomato);
                    break;
                case "cheese":
                    addItem(amount, cheese);
                    break;
                case "carrot":
                    addItem(amount, carrot);
                    break;
                default:
                    System.out.println("We don't have that " + addOnName + " on the menu");
            }
        } else {
            System.out.println("Too many addons were attempted to be added, now adding " + (4 - addOns) + " of " +
                    addOnName);
            addAddOn((4-addOns), addOnName);
            return;

        }
        if (isMaxAddOns()) {
            System.out.println("You have added 4 addons, you cannot add anymore");
        } else {
            System.out.println("You have: " + (4 - addOns) + " addons, would you like to add more?" );
        }
    }

    public double getBasePrice() {
        return basePrice;
    }


}
