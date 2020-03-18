package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Theatre theatre = new Theatre("Olympian", 8, 12);
//	    List<Theatre.Seat> seatCopy = new ArrayList<>(theatre.seats);
//	    printList(seatCopy);
//
//	    seatCopy.get(1).reserve();
	    if(theatre.reserveSeat("D12")) {
            System.out.println("Please pay for D12");
        } else {
            System.out.println("Seat already reserved");
        }

        if(theatre.reserveSeat("D12")) {
            System.out.println("Please pay for D12");
        } else {
            System.out.println("Seat already reserved");
        }

        if(theatre.reserveSeat("B13")) {
            System.out.println("Please pay for B13");
        } else {
            System.out.println("Seat already reserved");
        }

        List<Theatre.Seat> reverseSeats = new ArrayList<>(theatre.getSeats());
        Collections.reverse(reverseSeats);
        printList(reverseSeats);

        List<Theatre.Seat> priceSeats = new ArrayList<>(theatre.getSeats());
        priceSeats.add(theatre.new Seat("B00", 13.00));
        priceSeats.add(theatre.new Seat("A00", 13.00));
        Collections.sort(priceSeats, Theatre.PRICE_ORDER);
        printList(priceSeats);

//        Collections.shuffle(seatCopy);
//        System.out.println("Printing seatCopy");
//        printList(seatCopy);
//        System.out.println("Printing Theatre.seats");
//        printList(theatre.seats);
//
//        Theatre.Seat minSeat = Collections.min(seatCopy);
//        Theatre.Seat maxSeat = Collections.max(seatCopy);
//        System.out.println("Min seat number is " + minSeat.getSeatNumber());
//        System.out.println("Max seat number is " + maxSeat.getSeatNumber());
//
//        sortList(seatCopy);
//        System.out.println("Printing sorted seatCopy");
//        printList(seatCopy);
//
//        List<Theatre.Seat> newList = new ArrayList<>(theatre.seats.size());
//        Collections.copy(newList, theatre.seats);

    }

    public static void printList(List<Theatre.Seat> list) {
        for (Theatre.Seat seat : list) {
            System.out.print(" " + seat.getSeatNumber() + " £" + seat.getPrice());
        }
        System.out.println();
        System.out.println("***********************************************************************");
    }

//    public static void sortList(List<? extends Theatre.Seat> list) {
//        for (int i=0; i<list.size() - 1; i++) {
//            for (int j=i+1; j < list.size(); j++) {
//                if(list.get(i).compareTo(list.get(j)) > 0) {
//                    Collections.swap(list, i, j);
//                }
//            }
//        }
//    }
}

/*
* Collection
* In Java we have 4 interfaces which subclass Collection framework
*
*               Collection
*   ________________|________________
*   |          |          |         |
*   Set        List      Queue    Deque
*   |
*   |
*   SortedSet
*
* And the Map framework
*
*   Map
*   |
*   |
*   SortedMap
*
*
* We said that our seats object will have a data type of List and that its constructor will be an ArrayList
* To be even more generic, we can instead say List and use the Collection framework, by doing so we are allowing our
* seats object to use classes which implement Set, List, Queue and Deque and use their constructors from there as
* long as it is on the same level of hierarchy
*   Collection<Seat> seats = new HashSet<>();
*   Collection<Seat> seats = new LinkedHashSet<>();
*   ...
*
* These all work, because we are using interfaces which subclass the Collection framework
*
* What we cannot do, is go down another level in the hierarchy and still expect the same result, so if we do
*   Collection<Seat> seats = new TreeSet<>();
*
* This is because SortedSet is a child class of Set, and grandchild of Collection - with the way it is set out, we
* can cannot go one level below the four interfaces - this is because as we go further down the hierarchy classes
* which implement these interfaces become more specialised and require additional requirements - which is steering
* away from generic(ness) that we are after.
*
* Binary Search
* A binary search is a much more efficient approach to finding an item within a sorted list, and is better in
* performance than what we did in the brute force search - where we looked at every possible item until we found it
*
* The Collection framework can implement a binary search, but we to first make the class/ object that is going to use
* the search to implement the Comparable<> interface
*
* Comparable<>
* This uses generics, so it requires either the type of the class - which is usually the class name, or any other class.
* We must override the compareTo method - which takes the type you passed to the type parameter and it must an
* integer value to which it compares the object that calls the method and the object that is passed to the parameter
*
* Usually you want it like: where obj1.compareTo(obj2)
*   if obj1 > obj2 -> return a +ve integer
*   if obj1 < obj2 -> return a -ve integer
*   if obj1 == obj2 -> return 0
*
* In the reserveSeat() method, we are going to use the binary search in the 'Collections' framework like so:
* NOTE: we must revert the seats object back into List<> as the binarySearch() will only work classes which implement
* NOTE: The Collections framework consists of static methods which operate on object type Collection
* 1 of the 4 interfaces mentioned above
*
* int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
* 1st param: The List we are doing the search on
* 2nd param: The key that we matching to (the item we want to find)
* 3rd param: The object type, we pass null here
*
* The binarySearch() method will return an index position as to where the key is in the list, if its >= 0 then its
* found the item in the list, and less than 0 means that it did not find it
*
* Shallow Copy
* In the example above we made a new ArrayList object which copies the ArrayList from theatre to seatCopy, by passing
*  the ArrayList in the Theatre class to the constructor in the ArrayList, now this a shallow copy, because even
* though we have created a new object which is a new ArrayList, we pass in a reference to another ArrayList which
* holds the values of the Seat objects, we have effectively copied the memory addresses to which the Seat object is
* stored in memory.
*
* So the code above demonstrates, as we reserve a seat in Seat copy, then try to reserve the exact same seat in the
* original List, we see that they both have been reserved - further proving the point we have only copied memory
* address of the Seat objects, and not created new Seat objects based on the Object data from the original Seat
* objects in the original ArrayList
*
* Of course they are still 2 ArrayList objects, holding the same data, so when reverse seatCopy - the content of the
* list is revered, whilst the original array is still the same
*
* Bottom-Line: 2 separate Array references, same data held within them
*
* Collections.reverse(Collection c)
* This will accept an object of type Collection and will reverse the order of the list
*
* Collections.shuffle(Collection c)
* This will accept an object of type Collection and will randomly (psudo) randomly shuffle the elements into a
* different order
*
* Collections.min(Collection c)
* Collections.max(Collection c)
* Both accept a object of type Collection and will return the maximum and minimum element within the list, and it
* will do that depending on the results you gave to compareTo - the list of course does not need to be ordered to do
* this
*
* Collections.swap(Collection c, index i, index j)
* This will take 3 params,
* 1st: object of type Collection
* 2nd: the index position in the list which we want to swap
* 3rd: the index position in the list which we want to swap
*
* This swaps the position of i and j in the list
*
*
* Collections.copy(ListToHold, ListWithElements)
* *Produces a shallow copy*
* For this method to work, the ListToHold - must not only have the same size as the ListWithElements, but must also
* be filled - (i.e have objects instantiated within it) if not - then we get an IndexOutOfBoundsError
*
* Comparator<>
* Like the compareTo interface, this interface requires us to make a new class for it to be used, unlike the
* Comparable where it was implemented, the Comparator is a just a class instance which we pass in to Collections.sort
* (list, Comparator object)
*
* In Theatre, we made use of anonymous classes (or initialisation) to do this
* static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if (seat1.getPrice() < seat2.getPrice()) {
                return -1;
            } else if (seat1.getPrice() > seat2.getPrice()) {
                return 1;
            } else {
                return 0;
            }
        }
    };
* Unlike the compareTo, we are able to compare a bit further to what we want, so in this case price of the each seat,
*  and returning 1, -1, or 0 depending on the result, and its the same as to compareTo
*   if obj1 > obj2 -> return a +ve integer
*   if obj1 < obj2 -> return a -ve integer
*   if obj1 == obj2 -> return 0
*
* But what is interesting, is that when sorted, the order is retrained - so when ordering by price, it will order of
* course by its price, but it will not swap elements around in the list -> so when we added to new Seats to the list
* above B00 and A00 of price 13, B00 came before A00 - thus showing order is retained there.
*
* The problem arises when using the compare() method found in the Comparator interface, with our compareTo() method
* found in Comparable interface - we have set up so that we doing checks on Seat objects, and if they are equal we
* are returning 0 when they are.
*
* But the with the compare() method, we are not comparing Seat objects entirely - rather one attribute within the
* Seat object - that being price - now the problem is several seats can have the same price therefore the method will
*  return 0 indicating that they are equal - yes the price is the same, however the actual Seat object itself is not
* - and this throws off the sorting in Maps, HashSets etc. thus it is recommenced to use compareTo() with those data
* structures, and compare() in certain scenarios
*
* Maps
* Now maps do not directly subclass the Collection framework - but it is still part of it (don't really think about
* how or why!)
*
* Maps replace the obsolete dictionaries (key : value), and all key's must be unique within the map
*
* Map takes 2 type parameters - 1 being the key, and the other being the value - so it looks like this
*   Map<K, V> map = new HashMap<>();
*   Map<String, String> languages = new HashMap<>();
*
* Put()
* To add a key-value pair to the map we use the put(key, value) method, with the 2 values corresponding to their
* respected object type
*   languages.put("1", "tower");
*
* The put() method also returns an object value (the object being the same value as the value passed when initialing
* the map) so when you put in a brand-new fresh key-value pair into the map, it will return null - indicating this
* the first time you have placed a key-value like that
*   languages.put("1", "tower"); -> null
*
* But if you put the same key-value pair into the map again, you will get a return value of the old value from the
* first time you entered the key-value
*   languages.put("1", "castle"); -> tower
*
* Telling us that was the original value for that key, but this still adds the key-value pair to the map, whether we
* want it or not - to get it around that we the containsKey() method
*
* ContainsKey()
* This method will check if a key already exists in the map, by passing in a key reference - and it will return tru
* or false depending if it exists
*
* Get()
* To retrieve values back from the map, we use the get(key) method, passing in the key exactly to the map object
* reference
*   languages.get("1");
*   -> tower
*
* Now unlike dictionaries, the map interface and its associated classes do not throw errors when typing a key that
* already exists within the map, rather it will overwrite the old key's value with the new one specified
*   languages.put("1", "castle");
*   languages.get("1");
*   -> castle
*
* To loop over the keys in the map, we can use a for-each loop, and using the keySet() method - which will basically
* return a set of all the keys
*   for(Object k : map.keySet()) {
*       System.out.println(k + " : " + map.get(k));
*   }
*
* Remove()
* This method takes the key as the argument, and optionally the value as well, this method also returns true/ false
* depending whether or not if it was successful or not
*   languages.remove("Lisp");
*
* If the optional value was passed, then not only does the key match the key in the map, nut also the value that is
* assigned to the key
*   languages.remove("Algol", "a family of algorithmic languages")
*
* Replace()
* You can pass in 3 params to it
* 1st param: Key
* 2nd param: Old Value (optional)
* 3rd param: New Value
*
* This to also returns true or false depending if it was able to do the replacement, if the optional value was not
* passed then it will lookup the key, and change its value to the new one passed
* if the old value was passes, then the key and the old value must match before the new value can be updated
*
*
* */
