package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player implements ISaveable {
    private String playerName;
    private int health;
    private String weapon;

    public Player(String playerName, int health, String weapon) {
        this.playerName = playerName;
        this.health = health < 0 ? 100 : health;
        this.weapon = weapon;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public List<String> save() {
        List<String> saveRecord = new ArrayList<String>();
        saveRecord.add(0, playerName);
        saveRecord.add(1, Integer.toString(health));
        saveRecord.add(2, weapon);
        return saveRecord;
    }

    @Override
    public void load(List<String> savedData) {
        if(savedData != null && savedData.size() > 0) {
            playerName = savedData.get(0);
            health = Integer.parseInt(savedData.get(1));
            weapon = savedData.get(2);
        }
    }

    @Override
    public String toString() {
        return "Player name is " + playerName +
                "\nPlayer health is " + health +
                "\nThe weapon you have currently equipped is " + weapon;
    }
}
