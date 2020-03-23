package com.example;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.5, 200);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        for (String s : stockList.Items().keySet()) {
            System.out.println(s);
        }

        Basket basket = new Basket("basket");
        sellItem(basket, "car", 1);
        System.out.println(basket);

        sellItem(basket, "car", 1);
        System.out.println(basket);

        sellItem(basket, "car", 1);
        sellItem(basket, "spanner", 5);
        System.out.println(basket);

        sellItem(basket, "juice", 4);
        sellItem(basket, "cup", 12);
        sellItem(basket, "bread", 1);
        System.out.println(basket);

        System.out.println(stockList);

        // Error, unmodifiable map cannot be modified
//        temp = new StockItem("pen", 1.12);
//        stockList.Items().put(temp.getName(), temp);

        stockList.Items().get("car").adjustStock(3000);
        stockList.get("car").adjustStock(-1000);
        System.out.println(stockList);
        for (Map.Entry<String, Double> price: stockList.PriceList().entrySet()) {
            System.out.println(price.getKey() + " costs " + price.getValue());
        }

    }

    public static int sellItem(Basket basket, String item, int quantity) {
        // retrieve the item from the stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.sellStock(item, quantity) != 0) {
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        return 0;
    }
}

/*
* LinkedHashMap()
* Just like a HashMap() it contains all the methods, however one glaring difference is that the order is retained
* when inputted, so there is no chaotic order like the HashMap
*
* TreeMap()
* We use the TreeMap on the Basket class, and this produces an alphabetical sorted map of our basket, this requires
* the compareTo method from the Comparable<E> interface, and it will sort based on key values, thus your Key object
* must implement the compareTo method to return a sorted map of all the values
* Of course there is a cost to when using a TreeMap, as all the items must be checked against one another using the
* compareTo method when being inserted into the map - this is one major drawback when compared to using the HashMap
* as they are placed in a chaotic order
*
* Collections.unmodifiableMap(Collection)
* This will return a "read-only" view of the map you reference as the argument, this is very good security measure,
* as in previous projects we returned a copy of the map - and this copy still has full access to methods to put and
* remove from this copy reference (of course not affecting the original map) but seemingly weird being able to do
* that. With this method you get a read-only view meaning attempting to modify the mao reference will result in
* "UnsupportedOperationException" exception
*
* However we know that Collection itself cannot be modified - the individual objects within the Collection can - so
* we can get and object and call one of the object methods on that object - bearing in mind this the retrieved object
* method and not the Collection method - and it will work without any errors
*
* Bottom-Line: the Collection is unmodifiable, but the individual objects can be modified
*
* The way around this is of course, do not return a unmodifiable Collection with the actual objects within it, rather
* retrieve primitive types from the object, and store them using the respected primitive type, this way you are not
* only avoiding the modifying of the Collection but also of the objects as well
*
*
* */
