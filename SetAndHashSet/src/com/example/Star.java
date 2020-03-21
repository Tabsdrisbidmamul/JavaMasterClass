package com.example;

public class Star extends HeavenlyBody {
    public Star(String name, double orbitalPeriod) {
        super(name, orbitalPeriod, BodyTypes.STAR);
    }

    @Override
    public boolean addSatellite(HeavenlyBody satellite) {
        if(satellite.getBodyType() != BodyTypes.STAR) {
            return super.addSatellite(satellite);
        }
        System.out.println("You cannot add " + satellite.getName() + " to " + this.getName());
        return false;
    }
}
