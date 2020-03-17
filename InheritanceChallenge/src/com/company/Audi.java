package com.company;

public class Audi extends Car {

    private String model;

    public Audi(int doors, double engineSize, int gears, int steering, int speed,
                String model) {
        super("Audi", 4, doors, engineSize, gears, steering, speed);
        this.model = model;
    }

    public void openBootViaKey() {
        System.out.println("Boot has opened");
    }

    public void steer(int angleToTurn) {
        if(angleToTurn < -720 || angleToTurn > 720) {
            System.out.println("Only 2 full locks are allowed on an Audi ");
        } else {
            int steering = super.getSteering();
            steering += angleToTurn;
            super.setSteering(steering);
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
