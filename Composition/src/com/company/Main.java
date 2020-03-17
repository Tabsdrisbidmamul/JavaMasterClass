package com.company;

import javax.print.DocFlavor;

public class Main {

    public static void main(String[] args) {
        Dimensions dimensions = new Dimensions(20, 20, 5);
        Case theCase = new Case("220B", "Dell", "240", dimensions);

        Monitor theMonitor = new Monitor("27inch Beast", "Acer", 27,
                new Resolution(2540, 1440));
        // you can instantiate classes to objects, just by using the new operator and the constructor name with its
        // arguments, instead of making a new variable - if it is not need (lower level class)

        MotherBoard theMotherBoard = new MotherBoard("BJ-200", "Asus", 4, 6,
                "V2.44");

        PC thePC = new PC(theCase, theMonitor, theMotherBoard);
        thePC.powerUp();

        // Challenge
        // Create a single room of a house using composition.
        // Think about the things that should be included in the room.
        // Maybe physical parts of the house but furniture as well
        // Add at least one method to access an object via a getter and
        // then that objects public method as you saw in the previous video
        // then add at least one method to hide the object e.g. not using a getter
        // but to access the object used in composition within the main class
        // like you saw in this video.

        Floor floor = new Floor("Oad Wood", new Area(50, 40));
        Wall wall = new Wall("Maple Wood", new Area(50, 50));

        Chair chair = new Chair(20, "Oak Wood");
        Table table = new Table(80, new Dimensions(40, 30, 60), "Oak Wood");
        Shelf shelf = new Shelf(
                5,
                new Book(5, 500, new Dimensions(4, 5, 10)),
                "Oak Wood",
                50,
                new Dimensions(50, 40 ,10)
        );

        Furniture furniture = new Furniture(chair, table, shelf);
        Room room = new Room(floor, wall, 4, furniture);
        room.openBook();
        room.sitOnAChair();


    }
}

/*
* Composition
* In OOP, it is a form of aggregation - i.e (association, composition), where association is where if the containing
* parent element was destroyed, the object that is aggregated with that object will still exist
* composition is the opposite, there is a strong relationship between objects (known as "has-a" relationship) and if
* the containing parent element is destroyed, then the contained elements too are destroyed - they will not exist.
*
* In Java to make a composite relation - ask yourself does this object "has-a" [type] of relation -> so in this
* example we have a PC -> which is composed of a case, motherboard and a monitor (not to be confused with the
* inheritance relation with a vehicle and a car)
*
* we do this by making instance variables that are of that type of Object so
*
*   public class PC {
*       private Case theCase;
*       private Monitor monitor;
*       private Motherboard motherboard;
*
*       ...
*   }
*
* We have defined that in class that will contain all these objects that it is composed of, as instance variables -
* in this case we can say these field members are like data types and the variable names represent reference variables
*
* We write out the constructors and getters methods as usual
* and write out the necessary Class definition for each object - and for each object they too may have a "has-a"
* relationship with other objects as well - so the (Case) theCase has "Dimensions dimensions", as it is composed of set
* dimensions
* The (Monitor) "Resolution resolution" as it is composed a resolution as well
*
* we instantiate each class, in the Main method, and create a PC
* but unlike inheritance where we can access methods from subclasses to superclasses, we cannot do that here in
* composition, instead we chain dot notation with the containing class to its composed objects down the "hierarchy"
* to access each respectful constituent classes
*
*   thePC.getMonitor().drawPixelAt(1500, 1200, "red");
*   thePC.getMotherBoard().loadProgram("Windows 1.0");
*   thePC.getTheCase().pressPowerButton();
*
* here we use the getter methods which we defined in the containing class (PC) - what we have essentially done is
* access the PC class to return the reference variable where we can use it to access its own class methods
*
*   thePC.monitor.drawPixelAt(1500, 1200, "red");
*   thePC.motherboard.loadProgram("Windows 1.0");
*   thePC.theCase.pressPowerButton();
*
* so it acts like this visually
*
*
* */
