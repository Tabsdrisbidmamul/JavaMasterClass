package com.company;

public class Car extends Vehicle {
    private final static String GEAR_CHANGE_MSG = "The vehicle does not have that many gears";

    private int gears;
    private int steering;
    private int speed;

    public Car(String name, int wheels, int doors, double engineSize, int gears, int steering, int speed) {
        super(name, wheels, doors, engineSize);
        this.gears = gears;
        this.steering = steering;
        this.speed = speed;
    }

    public void increaseSpeed(int speed) {
        if(speed < 0) {
            System.out.println("You want to go faster! Not slower...");
        } else {
            this.speed += speed;
        }
    }

    public void decreaseSpeed(int speed) {
        if(this.speed - speed < 0) {
            System.out.println("You slowing down to a stop, speed is a scalar not a vector!");
        } else {
            this.speed -= speed;
        }
    }

    public void steer(int angleToTurn) {
        if(angleToTurn < -360 || angleToTurn > 360) {
            System.out.println("only one full lock on the steering wheel is allowed on cars!");
        } else {
            steering += angleToTurn;
        }
    }

    public void increaseGear(int changeGearUp) {
        if(changeGearUp <= 0 || changeGearUp > 7) {
            System.out.println(GEAR_CHANGE_MSG);
        } else {
            gears += changeGearUp;
        }
    }

    public void decreaseGear(int changeGearDown) {
        if(changeGearDown <= 0 || changeGearDown > 7) {
            System.out.println(GEAR_CHANGE_MSG);
        } else {
            gears -= changeGearDown;
        }
    }

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }

    public int getSteering() {
        return steering;
    }

    public void setSteering(int steering) {
        this.steering = steering;
    }
}
