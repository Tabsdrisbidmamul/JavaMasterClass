package com.company;

public class Room {
    private Floor floor;
    private Wall wall;
    private int numberOfWalls;
    private Furniture furniture;

    public Room(Floor floor, Wall wall, int numberOfWalls, Furniture furniture) {
        this.floor = floor;
        this.wall = wall;
        this.numberOfWalls = numberOfWalls;
        this.furniture = furniture;
    }

    public void takeAnItem() {
        furniture.takeAnItem();
    }

    public void openBook() {
        furniture.getShelf().getBooks().openBook();
    }

    public void sitOnAChair() {
        furniture.getChair().sit();
    }

    public Floor getFloor() {
        return floor;
    }

    public Wall getWall() {
        return wall;
    }


    public int getNumberOfWalls() {
        return numberOfWalls;
    }
}
