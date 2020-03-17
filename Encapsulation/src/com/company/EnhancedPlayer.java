package com.company;

public class EnhancedPlayer {
    private String name;
    private int hitpoints = 100;
    private String weapon;

    public EnhancedPlayer(String name, int health, String weapon) {
        this.name = name;
        this.hitpoints = health > 0 && health <= 100 ? health : 100;
        this.weapon = weapon;
    }

    public void loseHealth(int damage) {
        hitpoints -= damage;
        if(hitpoints <= 0) {
            System.out.println("Player knocked out");
            // reduce number of lives remaining for the player
        }
    }

    public int getHealth() {
        return hitpoints;
    }
}
