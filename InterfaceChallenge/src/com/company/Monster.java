package com.company;

import java.util.ArrayList;
import java.util.List;

public class Monster implements ISaveable {
    private String name;
    private int hitPoints;
    private int strength;

    public Monster(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public List<String> save() {
        ArrayList<String> values = new ArrayList<>();
        values.add(0, name);
        values.add(1, Integer.toString(hitPoints));
        values.add(2, Integer.toString(strength));
        return values;
    }

    @Override
    public void load(List<String> savedData) {
        if(savedData != null && savedData.size() > 0) {
            name = savedData.get(0);
            hitPoints = Integer.parseInt(savedData.get(1));
            strength = Integer.parseInt(savedData.get(2));
        }
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                '}';
    }
}
