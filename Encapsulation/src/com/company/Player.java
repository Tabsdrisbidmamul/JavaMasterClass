package com.company;

public class Player {
    public String fullname;
    public int health;
    public String weapon;

    public void loseHealth(int damage) {
        health -= damage;
        if(health <= 0) {
            System.out.println("Player knocked out");
            // reduce number of lives remaining for the player
        }
    }

    public int healthReaming() {
        return health;
    }
}
