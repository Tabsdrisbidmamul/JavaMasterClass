package com.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> currentBasket;
    private final Map<Key, Double> purchasedBasket;

    public Basket(String name) {
        this.name = name;
        this.currentBasket = new TreeMap<>();
        this.purchasedBasket = new TreeMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inBasket = currentBasket.getOrDefault(item, 0);
            currentBasket.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

//    public boolean removeFromBasket(StockItem item) {
//        if ((item != null) && currentBasket.containsKey(item)) {
//            currentBasket.remove(item);
//            return true;
//        }
//        return false;
//    }

    public boolean removeFromBasket(StockItem item, int quantity) {
        if ((item != null) && currentBasket.containsKey(item)) {
            if(currentBasket.get(item) - quantity > 0) {
                int newQuantity = currentBasket.get(item) - quantity;
                currentBasket.put(item, newQuantity);
                return true;
            } else if (currentBasket.get(item) - quantity == 0) {
                currentBasket.remove(item);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean purchaseBasket(StockList list) {
        if(!(currentBasket.isEmpty())) {
            for (Map.Entry<StockItem, Integer> item: currentBasket.entrySet()) {
                purchasedBasket.put(new Key(item.getKey(), item.getValue()), item.getKey().getPrice());
                list.sellStock(item.getKey().getName(), item.getValue());
            }
            currentBasket.clear();
            return true;
        }
        return false;
    }
    
    public String checkoutMessage() {
        if(!purchasedBasket.isEmpty()) {
            String s = "\nCheckout Page:";
            s =
                    s + "\nYour Shopping basket " + name + " contains " + purchasedBasket.size() + (purchasedBasket.size() == 1 ? " items" :
                    " items ") + "\n";
            double totalCost = 0.0;
            for (Map.Entry<Key, Double> item : purchasedBasket.entrySet()) {
                s = s + item.getKey().getStockItem() + ". " + item.getKey().getQuantity() + " bought\n";
                totalCost += item.getKey().getQuantity() * item.getValue();
            }
            return s + "Total cost " + String.format("%.2f", totalCost) + "\n";
        }
        return null;
    }


    public Map<Key, Double> ItemsPurchased() {
        return Collections.unmodifiableMap(purchasedBasket);
    }

    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(currentBasket);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + currentBasket.size() + (currentBasket.size() == 1 ? " items" :
                " items ") + "\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : currentBasket.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " reserved\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + String.format("%.2f", totalCost) + "\n";
    }

    private final class Key implements Comparable<Key> {
        private final StockItem stockItem;
        private final int quantity;

        private Key(StockItem stockItem, int quantity) {
            this.stockItem = stockItem;
            this.quantity = quantity;
        }

        public StockItem getStockItem() {
            return stockItem;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public int hashCode() {
            return this.stockItem.getName().hashCode() + 69;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this) {
                return true;
            }

            if((obj == null) || (obj.getClass() != this.getClass())) {
                return false;
            }

            String objName = ((Key) obj).getStockItem().getName();
            return this.getStockItem().getName().equalsIgnoreCase(objName);
        }

        @Override
        public int compareTo(Key o) {
            if(this == o) {
                return 0;
            }
            if(o != null) {
                return this.stockItem.getName().compareToIgnoreCase(o.getStockItem().getName());
            }
            throw new NullPointerException();
        }
    }
}
