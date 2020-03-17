package com.company;

public class Wall {
    private String material;
    private com.company.Area area;

    public Wall(String material, com.company.Area area) {
        this.material = material;
        this.area = area;
    }

    public String getMaterial() {
        return material;
    }

    public Area getArea() {
        return area;
    }
}
