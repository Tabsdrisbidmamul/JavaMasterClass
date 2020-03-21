package com.example;

import java.util.*;

public class SetMain {
    public static void main(String[] args) {
        Set<Integer> squares = new HashSet<>();
        Set<Integer> cubes = new HashSet<>();

        for(int i=1; i<=100; i++) {
            squares.add((int) Math.pow(i, 2));
            cubes.add((int) Math.pow(i, 3));
        }
        System.out.println("There are " + squares.size() + " squares and " + cubes.size() + " cubes.");
        Set<Integer> union = new HashSet<>(squares);
        union.addAll(cubes);
        System.out.println("Union contains " + union.size() + " elements");

        Set<Integer> intersection = new HashSet<>(squares);
        intersection.retainAll(cubes);
        System.out.println("Intersection contains " + intersection.size() + " elements");
        for (int i: intersection) {
            System.out.println(i + " is the square of " + (int) Math.sqrt(i) + " and the cube root is " + (int) Math.cbrt(i));
        }

        Set<String> words = new HashSet<>();
        String sentence = "one day in the year of the fox";
        String[] arrayWords = sentence.split(" ");
        words.addAll(Arrays.asList(arrayWords));

        for (String s: words) {
            System.out.println(s);
        }

        Set<String> nature = new HashSet<>();
        Set<String> divine = new HashSet<>();

        String[] natureWords = {"all", "nature", "is", "but", "art", "unknown", "to", "thee"};
        String[] divineWords = {"to", "err", "is", "human", "to", "forgive", "divine"};

        nature.addAll(Arrays.asList(natureWords));
        divine.addAll(Arrays.asList(divineWords));

        System.out.println("nature - divine:");
        Set<String> diff1 = new HashSet<>(nature);
        diff1.removeAll(divine);
        printSet(diff1);

        System.out.println("divine - nature:");
        Set<String> diff2 = new HashSet<>(divine);
        diff2.removeAll(nature);
        printSet(diff2);

        Set<String> unionTest = new HashSet<>(nature);
        unionTest.addAll(divine);
        Set<String> intersectionTest = new HashSet<>(nature);
        intersectionTest.retainAll(divine);

        System.out.println("Symmetric difference");
        unionTest.removeAll(intersectionTest);
        printSet(unionTest);

        if(nature.containsAll(divine)) {
            System.out.println("divine is a subset of nature");
        }

        if(nature.containsAll(intersectionTest)) {
            System.out.println("intersection is a subset of nature");
        }

        if(divine.containsAll(intersectionTest)) {
            System.out.println("intersection is a subset of divine");
        }

    }

    private static void printSet(Collection<String> collection) {
        for(String s : collection) {
            System.out.println("element is " + s);
        }
    }
}

/*
* Set Operations
* These are known in Java docs as 'Bulk Operations'
* Bulk operations are particularly well suited to Sets; when applied, they perform standard set-algebraic operations.
* Suppose s1 and s2 are sets. Here's what bulk operations do:
*   - s1.containsAll(s2) — returns true if s2 is a subset of s1. (s2 is a subset of s1 if set s1 contains all of the
*     elements in s2.)
*   - s1.addAll(s2) — transforms s1 into the union of s1 and s2. (The union of two sets is the set containing all of
*     the elements contained in either set.)
*   - s1.retainAll(s2) — transforms s1 into the intersection of s1 and s2. (The intersection of two sets is the set
*     containing only the elements common to both sets.)
*   - s1.removeAll(s2) — transforms s1 into the (asymmetric) set difference of s1 and s2. (For example, the set
*     difference of s1 minus s2 is the set containing all of the elements found in s1 but not in s2.)
*
* Now a thing to keep in mind, is that bulk operations are destructive - meaning they modify the Collection - in this
* the Set, so to mitigate this we created a new HashSet called union to contain the squares and cubes
*
*
* Arrays.asList(T... a)
* This method will take a class that implements the the List interface, so in this case what we really are passing
* are primitive or object arrays - this method will then return an ArrayList using the array as the constructor for
* the ArrayList.
*
* This is a convenient method to transform arrays to of Collection type and then use them straight away with other
* Collection objects:
*
*   Set<String> words = new HashSet<>();
*   String sentence = "one day in the year of the fox";
*   String[] arrayWords = sentence.split(" ");
*   words.addAll(Arrays.asList(arrayWords));
*
*                   Optimised
*
*   String sentence = "one day in the year of the fox";
*   String[] arrayWords = sentence.split(" ");
*   Set<String> words = new HashSet<>(Arrays.asList(arrayWords));
*
* What we have done is created a String array which contains all the words within the sentence variable by using the
* split() method which will extract each word once it has reached a space (whitespace), then we want to add this
* array to the the Set words, and the only way to do that is to convert the array to of Collection type - so an
* ArrayList.
*
* */
