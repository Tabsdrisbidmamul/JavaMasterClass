package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
//    private static Map<Integer, Location> locations = new HashMap<>();
    private static Locations locations = new Locations();

    public static void main(String[] args) {
        // Change the program to allow players to type full words, or phrases, then move to the
        // correct location based upon their input.
        // The player should be able to type commands such as "Go West", "run South", or just "East"
        // and the program will move to the appropriate location if there is one.  As at present, an
        // attempt to move in an invalid direction should print a message and remain in the same place.
        //
        // Single letter commands (N, W, S, E, Q) should still be available.
        Scanner scanner = new Scanner(System.in);

        int loc = 64;
        Parser.addCommands("QUIT", "Q");
        Parser.addCommands("NORTH", "N");
        Parser.addCommands("EAST", "E");
        Parser.addCommands("SOUTH", "S");
        Parser.addCommands("WEST", "W");
        Parser.addCommands("UP", "U");
        Parser.addCommands("DOWN", "D");

        while (true) {
            System.out.println(locations.get(loc).getDescription());
            if(loc == 0) {
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are ");

            for (String exit: exits.keySet()) {
                System.out.print(exit + ", ");
            }

            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1) {
                String[] words = direction.split(" ");
                direction = Parser.parser(words);
            }

            if(exits.containsKey(direction)) {
                loc = exits.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }
}

/*
* Immutable classes
*   1. Don't provide "setter" methods — methods that modify fields or objects referred to by fields.
*   2. Make all fields final and private.
*   3. Don't allow subclasses to override methods. The simplest way to do this is to declare the class as final. A more
*      sophisticated approach is to make the constructor private and construct instances in factory methods.
*   4. If the instance fields include references to mutable objects, don't allow those objects to be changed:
*       Don't provide methods that modify the mutable objects.
*       Don't share references to the mutable objects. Never store references to external, mutable objects passed to
*       the constructor; if necessary, create copies, and store references to the copies. Similarly, create copies of
*       your internal mutable objects when necessary to avoid returning the originals in your methods.
*
* */
