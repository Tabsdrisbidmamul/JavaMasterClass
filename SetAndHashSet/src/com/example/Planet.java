package com.example;

public class Planet extends HeavenlyBody {
    public Planet(String name, double orbitalPeriod) {
        super(name, orbitalPeriod, BodyTypes.PLANET);
    }

    @Override
    public boolean addSatellite(HeavenlyBody satellite) {
        if( !(satellite.getBodyType() == BodyTypes.MOON)) {
            System.out.println("You cannot add " + satellite.getName() + " to " + this.getName());
            return false;
        }
        return super.addSatellite(satellite);
    }
}
