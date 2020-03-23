package com.example;

import java.util.Map;

public class MainChallenge {
    /*

    Modify the program so that adding items to the shopping basket doesn't
    actually reduce the stock count but, instead, reserves the requested
    number of items.

    You will need to add a "reserved" field to the StockItem class to store the
    number of items reserved.

    Items can continue to be added to the basket, but it should not be possible to
    reserve more than the available stock of any item. An item's available stock
    is the stock count less the reserved amount.

    The stock count for each item is reduced when a basket is checked out, at which
    point all reserved items in the basket have their actual stock count reduced.

    Once checkout is complete, the contents of the basket are cleared.

    It should also be possible to "unreserve" items that were added to the basket
    by mistake.

    The program should prevent any attempt to unreserve more items than were
    reserved for a basket.

    Add code to Main that will unreserve items after they have been added to the basket,
    as well as unreserving items that have not been added to make sure that the code
    can cope with invalid requests like that.

    After checking out the baskets, display the full stock list and the contents of each
    basket that you created.

     */
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
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

        Basket basket = new Basket("basket");
        addItemToBasket(basket, "door", 6);
        addItemToBasket(basket, "cup", 40);
        addItemToBasket(basket, "chair", 5);
        removeItemFromBasket(basket, "chair", 1);
        System.out.println(stockList);
        System.out.println(basket);
        if(checkout(basket)) {
            System.out.println(stockList);
            System.out.println(basket.checkoutMessage());
        }

        basket = new Basket("online");
        addItemToBasket(basket, "car", 6);
        addItemToBasket(basket, "cup", 40);
        addItemToBasket(basket, "chair", 5);
        addItemToBasket(basket, "bread", 15);
        removeItemFromBasket(basket, "chair", 6);
        if(checkout(basket)) {
            System.out.println(stockList);
            System.out.println(basket.checkoutMessage());
        }

    }

    private static int addItemToBasket(Basket basket, String item, int quantity) {
        // retrieve the item from the stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        int toReserve = stockList.reserveStock(item, quantity);
        if(toReserve > 0) {
            basket.addToBasket(stockItem, quantity);
            return quantity;
        } else if (toReserve == -1) {
            basket.addToBasket(stockItem, stockItem.quantityInStock());
            return stockItem.quantityInStock();
        }
        return 0;
    }

    private static int removeItemFromBasket(Basket basket, String item, int quantity) {
        StockItem itemToLook = stockList.get(item);
        if(itemToLook == null) {
            System.out.println(item + " was not found in your basket");
            return 0;
        }
        if(basket.Items().containsKey(itemToLook)) {
            for (Map.Entry<StockItem, Integer> b: basket.Items().entrySet()) {
                if(b.getKey().equals(itemToLook)) {
                    stockList.unReserveStock(item, quantity);
                    basket.removeFromBasket(itemToLook, quantity);
                    return quantity;
                }
            }
        }

        return 0;
    }

    public static boolean checkout(Basket basket) {
        if(!basket.Items().isEmpty()) {
            return basket.purchaseBasket(stockList);
        }
        return false;
    }
}
