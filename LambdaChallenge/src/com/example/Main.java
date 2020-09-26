package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            String myString = "Let's split this string up into an array";
            String[] parts = myString.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };

        Function<String, String> everySecondChar = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        System.out.println(everySecondCharacter(everySecondChar, "1234567890"));

        Supplier<String> iLoveJava = () -> "I love Java!";
        String supplierResult = iLoveJava.get();
        System.out.println(supplierResult);

        List<String> topNames2015 = Arrays.asList(
                "Amelia",
                "Olivia",
                "emily",
                "Isla",
                "Ava",
                "oliver",
                "Jack",
                "Charlie",
                "harry",
                "Jacob"
        );

        System.out.println("---------------------------------------");
        List<String> names = topNames2015.stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase())
                .peek(System.out::println)
                .sorted(String::compareTo)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);


        long count = topNames2015.stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase())
                .filter(name -> name.startsWith("A"))
                .count();

        System.out.println(count);
    }

    private static String everySecondCharacter(Function<String, String> everySecondChar, String t) {
        return everySecondChar.apply(t);
    }
}
