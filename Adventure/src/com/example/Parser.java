package com.example;

import java.util.HashMap;
import java.util.Map;

final class Parser {
    private static final Map<String, String> commands = new HashMap<>();

    public static String parser(String[] input) {
        for (String word: input) {
            if(commands.containsKey(word)) {
                return commands.get(word);
            }
        }
        System.out.println("Invalid Command");
        return null;
    }

    public static void addCommands(String K, String V) {
        commands.put(K, V);
    }
}
