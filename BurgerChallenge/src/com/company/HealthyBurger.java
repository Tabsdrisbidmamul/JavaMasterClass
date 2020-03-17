package com.company;

public class HealthyBurger extends Hamburger {
    private int addOns = 0;
    
    public HealthyBurger(String meat, double basePrice, String name) {
        super("Brown Rye", meat, basePrice, name);
    }

    public void getGrandTotal() {
        // need to add additional with the base price
        int grandTotal = 0;
        grandTotal += getBasePrice() + (getLettuce().getPrice() + getTomato().getPrice() + getCheese().getPrice() +
                getCarrot().getPrice());
        System.out.println("The base price of the " + HealthyBurger.class.getSimpleName() + " is £" + getBasePrice() +
                " The additional's added are:\n\t" +
                getLettuce().getClass().getSimpleName() + ": £" + getLettuce().getPrice() + " " +
                getTomato().getClass().getSimpleName() + ": £" + getTomato().getPrice() + " " +
                getCheese().getClass().getSimpleName() + ": £" + getCheese().getPrice() + " " +
                getCarrot().getClass().getSimpleName() + ": £" + getCarrot().getPrice() +
                "\nGrand Total: £ " + grandTotal
        );

    }
    protected void addItem(int amount, AddOn name) {
        name.incrementAmount(amount);
        this.addOns += amount;
        System.out.println(amount + " "+ name.getClass().getSimpleName() + " was added");
    }

    protected boolean isMaxAddOns() {
        return addOns == 6;
    }

    public void addAddOn(int amount, String addOnName) {
        amount = Math.abs(amount);
        if( (amount + addOns <= 6) && (addOns <= 6)) {
            switch (addOnName.toLowerCase()) {
                case "lettuce":
                    addItem(amount, getLettuce());
                    break;
                case "tomato":
                    addItem(amount, getTomato());
                    break;
                case "cheese":
                    addItem(amount, getCheese());
                    break;
                case "carrot":
                    addItem(amount, getCarrot());
                    break;
                default:
                    System.out.println("We don't have that " + addOnName + " on the menu");
            }
        } else {
            System.out.println("Too many addons were attempted to be added, now adding " + (6 - addOns) + " of " +
                    addOnName);
            addAddOn((6-addOns), addOnName);
            return;
        }
        if (isMaxAddOns()) {
            System.out.println("You have added 6 addons, you cannot add anymore");
        } else {
            System.out.println("You have: " + (6 - addOns) + " addons, would you like to add more?" );
        }
    }

    

}
