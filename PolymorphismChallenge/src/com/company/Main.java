package com.company;

class Car {
    private String name;
    private boolean engine;
    private int cylinder;
    private int wheels;

    public Car(String name, int cylinder) {
        this.name = name;
        this.cylinder = cylinder;
        engine = true;
        wheels = 4;
    }

    public String startEngine() {
        return ("Being called from the Car Class\n\tThe engine for the car been ignited");
    }

    public String accelerate(int accelerationRate) {
        return ("Being called from the car Class\n\tThe car is accelerating at " + accelerationRate + "!");
    }

    public String brake() {
        return ("Being called from the Car Class\n\tThe car is applying its brakes");
    }

    public int getCylinder() {
        return cylinder;
    }

    public String getName() {
        return name;
    }
}

class Audi extends Car {
    public Audi() {
        super("RS8", 12);
    }

    public String startEngine() {
        return ("Being called from Audi Class\n\tThe engine and its " + getCylinder() + " has been " +
                "ignited!");
    }

    public String accelerate(int accelerationRate) {
        return ("Being called from Audi Class\n\tThe car is accelerating " + accelerationRate + "m/s its going" +
                "it looks a bit dangerous here!");
    }

    public String brake() {
        return ("Being called from Audi Class\n\tThe car is slowing down, it looks a bit safer now");
    }
}

class Fiat extends Car {
    public Fiat() {
        super("Punta", 4);
    }

    @Override
    public String startEngine() {
        return ("Being called from the Fiat Class\n\tThe car takes forever to start up with its " +
                getCylinder() + " cylinders!!");
    }

    @Override
    public String accelerate(int accelerationRate) {
        return ("Being called from the Fiat Class\n\tThe car is slowly but surely accelerating at " +
                accelerationRate + "m/s");
    }
// no brake for this 
//    @Override
//    public String brake() {
//        return ("Being called from the Fiat Class\n\tI don't think we need to brake on this...?");
//    }
}

class Volkswagen extends Car {
    public Volkswagen() {
        super("Polo", 6);
    }

    @Override
    public String startEngine() {
        return ("Being called from the Volkswagen Class\n\tThe car sure does take its time to start up");
    }

    @Override
    public String accelerate(int accelerationRate) {
        return ("Being called from the Volkswagen Class\n\tIt can go fast if we stretch the gears on this " +
                "thing " + accelerationRate + "m/s");
    }

    @Override
    public String brake() {
        return ("Being called from the Volkswagen Class\n\tI think the brakes exploded not to long ago");
    }
}

public class Main {

    public static void main(String[] args) {
        // We are going to go back to the car analogy.
        // Create a base class called Car
        // It should have a few fields that would be appropriate for a generice car calss.
        // engine, cylinders, wheels, etc.
        // Constructor should initialize cylinders (number of) and name, and set wheels to 4
        // and engine to true. Cylinders and names would be passed parameters.
        //
        // Create appropriate getters
        //
        // Create some methods like startEngine, accelerate, and brake
        //
        // show a message for each in the base class
        // Now create 3 sub classes for your favorite vehicles.
        // Override the appropriate methods to demonstrate polymorphism in use.
        // put all classes in the one java file (this one).
        for (int i=1; i<4; i++) {
            Car car = randomGenerate();
            System.out.println("Car #" + i + " : " + car.getName() + " has started its engine " + car.startEngine() +
            " has now started driving " + car.accelerate(5) + " oh it looks like its stopping " + car.brake());
        }

    }

    public static Car randomGenerate() {
        int randomNumber = (int) (Math.random() * 3) + 1;
        switch (randomNumber) {
            case 1:
                return new Audi();
            case 2:
                return new Fiat();
            case 3:
                return new Volkswagen();
        }
        return null;
    }
}
