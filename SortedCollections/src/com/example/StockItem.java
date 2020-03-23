package com.example;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityStock = 0; // can be initialised later
    private int reservedCount;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0; // or here (but you wouldn't normally do both)
        this.reservedCount = 0;
    }

    public StockItem(String name, double price, int quantityStock) {
        this(name, price);
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int quantityInStock() {
        return quantityStock;
    }

    public int getReservedCount() {
        return reservedCount;
    }

    public void setPrice(double price) {
        if(price > 0.0) {
            this.price = price;
        }
    }

    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }

    public void adjustReserveStock(int reserveCount) {
        int newReserveCount = this.reservedCount + reserveCount;

        if(newReserveCount > 0) {
            this.reservedCount = newReserveCount;
        }
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if(newQuantity >= 0) {
            this.quantityStock = newQuantity;
        }
    }


    @Override
    public boolean equals(Object obj) {
//        System.out.println("Entering StockItem.equals");
        if(obj == this) {
            return true;
        }

        if((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return this.name.equalsIgnoreCase(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem o) {
//        System.out.println("entering StockItem.compareTo");
        if(this == o) {
            return 0;
        }
        if(o != null) {
            return this.name.compareToIgnoreCase(o.getName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " : " + this.price;
    }
}
