package com.example;

public class Moon extends HeavenlyBody {
    public Moon(String name, double orbitalPeriod) {
        super(name, orbitalPeriod, BodyTypes.MOON);
    }

    @Override
    public boolean addSatellite(HeavenlyBody satellite) {
        System.out.println("You cannot add a satellite to " + satellite.getName());
        return false;
    }
}
