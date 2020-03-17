package com.company;

public class Vehicle {
    private String name;
    private int wheels;
    private int doors;
    private double engineSize;


    public Vehicle(String name, int wheels, int doors, double engineSize) {
        this.name = name;
        this.wheels = wheels;
        this.doors = doors;
        this.engineSize = engineSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

}
