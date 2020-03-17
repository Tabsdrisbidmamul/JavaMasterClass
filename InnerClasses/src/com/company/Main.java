package com.company;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Button btnPrint = new Button("Print");

    public static void main(String[] args) {
//        Gearbox mcLaren = new Gearbox(6);
//        mcLaren.operateClutch(true);
//        mcLaren.changeGear(1);
//        mcLaren.operateClutch(false);
//        System.out.println(mcLaren.wheelSpeed(1000));
//        mcLaren.changeGear(2);
//        System.out.println(mcLaren.wheelSpeed(3000));
//        mcLaren.operateClutch(true);
//        mcLaren.changeGear(3);
//        mcLaren.operateClutch(false);
//        System.out.println(mcLaren.wheelSpeed(6000));

          // Local Inner Class
//        class ClickListener implements Button.OnClickListener {
//            public ClickListener() {
//                System.out.println("I've been attached");
//            }
//
//            @Override
//            public void onClick(String title) {
//                System.out.println(title + " was clicked");
//            }
//        }
//
//        btnPrint.setOnClickListener(new ClickListener());

        // Anonymous Class
        btnPrint.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(String title) {
                System.out.println(title + " was clicked");
            }
        });
        listen();
    }
    private static void listen() {
        boolean quit = false;
        while (!quit) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    btnPrint.onClick();
            }
        }
    }
}

/*
* Inner Classes
* They are mainly used when referring to the outer class, so for example - you would not talk about gears of car
* without referring to the gearbox within the car, thus you can say gears is an inner class - or helper class to
* gearbox, as gearbox depends on the gear class to function properly.
*
* There are 4 types of inner classes
*   - Static inner class
*   - Non-static inner class
*   - Inner class defined in a code block (usually a method) : Local Class
*   - Anonymous inner class
*
* This keyword
* When referring to the this keyword within the a inner class, it is referring to the instance variables itself and
* not the top level class that it is contained in.
* Where a variable or parameter name is the same within the inner class and outer class, you will use 'this
* .variableName' to refer to the inner class fields, and 'OuterClassName.variableName' to refer to the containing
* class fields
*
* Where variables are named the same in top and bottom level classes is known as 'shadowing', and it is recommended
* to avoid this, to avoid confusion and errors
*
* Instantiating Inner Classes
* To instantiate an inner class, we must first instantiate the outer class first, or we will run into an error.
*   Gearbox mcLaren = new Gearbox(6);
*   Gearbox.Gear first = mcLaren.new Gear(1, 12.3);
*
* The first line we instantiate the Gearbox class, but on the second line we instantiate the Gear class, but we had
* to reference that we are looking into the Gearbox class first, then access the Gear class, and to create an object
* from the Gear class, we have to reference the Gearbox class reference type variable followed by dot notation to
* access the actual Gearbox class, where we tell Java that we are going to create an object of type Gear
*
* See we cannot do this
*   Gearbox mcLaren = new Gearbox(6);
*   Gearbox.Gear first = new Gearbox.Gear(1, 12.3);
*
*                           or
*
*   Gearbox mcLaren = new Gearbox(6);
*   Gearbox.Gear first = new mcLaren.Gear(1, 12.3);
*
* We get an error, Java is saying that there is no enclosing class, meaning we are trying to create a Gear object
* without having a Gearbox object to put it in.
* You could say that Gears live within a Gearbox, so in that sense we are tyring to make Gears, without any housing
* Gearbox to put it in.
*
* Local Class
* They are called Local, because when defined within a method, all references are local to that method (i.e when
* exited out of the method, every object within the method is destroyed to free up space)
*
* The example above simulates a graphics API, where we are simulating clicking buttons on a screen - we
* use keyboard input as if we are clicking
*
* These inner classes are best defined in methods - so in the example we defined it in the main method, and from
* there we assigned a local class as an object reference to a Class which also implements an interface within itself,
* and we said that the local class will implement the interface, as we will pass it as an object reference, from
* there we can define methods within the local class and call it from the Class which implements an interface and we
* can use the local class from within the outer class
* This is useful if we are going to assign an objects the same methods/ fields/ functionality several times - like
* pressing a button on the screen
*
* Anonymous Inner Class
* They are similar to Local Inner Classes, except providing the same functionality each time its used, anonymous
* classes are more of a one time thing, where they offer unique functionality for say pressing a special button
*
* These are defined within parenthesis or when being assigned to a variable, like the example above
*
*
*
* */