package com.example;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if(item != null) {
            // check if already have quantities of this item
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // if there are already stocks on this item, adjust the quantity
            if(inStock != item) {
                item.adjustStock(inStock.quantityInStock());
            }
            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }

    public int reserveStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);
//        System.out.println(inStock);
//        System.out.println("quantity in stock " + inStock.quantityInStock());
//        System.out.println("I want to get this " + quantity);
//        System.out.println("reserved count is " + inStock.getReservedCount());
//        System.out.print("quantity in stock - reserved count = ");
//        System.out.println(inStock.quantityInStock() - inStock.getReservedCount() );
//        System.out.println(inStock.quantityInStock() - inStock.getReservedCount() >= quantity);
//        System.out.println(inStock.quantityInStock() < quantity);

        if((inStock != null) && (inStock.quantityInStock() - inStock.getReservedCount() >= quantity) && (quantity > 0)) {
            inStock.adjustReserveStock(quantity);
            return quantity;
        } else if ((inStock != null) && (inStock.quantityInStock() < quantity || inStock.quantityInStock() - inStock.getReservedCount() < quantity)) {
            System.out.println("There are only "+ inStock.quantityInStock() + " " + (inStock.quantityInStock() == 1 ?
                    inStock.getName() : inStock.getName() + "'s") + " available, we are placing the maximum stock to " +
                    "be reserved into your basket");
            inStock.adjustReserveStock(inStock.quantityInStock());
            return -1;
        }
        System.out.println("There are no more " + ((inStock != null) ? inStock.getName() :
                " unknown") + " available");
        return 0;
    }

    public int unReserveStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);

        if((inStock != null) && (inStock.getReservedCount() >= quantity) && (quantity > 0) ) {
            inStock.adjustReserveStock(-quantity);
            return quantity;
        } else {
            System.out.println("Sorry, you attempted to unreserve " + ((inStock != null) ? inStock.getName() :
                    " unknown") + " however there are only " + ((inStock != null) ? inStock.getReservedCount() :" " +
                    "unknown") + " reserved at the moment");
            return 0;
        }
    }

    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);

        if((inStock != null) && (inStock.quantityInStock() >= quantity) && (quantity > 0)) {
            inStock.adjustStock(-quantity);
            return quantity;
        }
        System.out.println("There are no more " + ((inStock != null) ? inStock.getName() :
                " unknown") + " in stock");
        return 0;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, Double> PriceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue();
            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s = s + stockItem + ". There are " + stockItem.quantityInStock() + " in stock. Value of items: ";
            s = s + String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;
        }
        return s + "Total stock value " + String.format("%.2f", totalCost);
    }
}
