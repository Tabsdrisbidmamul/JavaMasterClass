package com.company;


public class Floor {
    private String material;
    private Area area;

    public Floor(String material, Area area) {
        this.material = material;
        this.area = area;
    }

    public Area getArea() {
        return area;
    }

    public String getMaterial() {
        return material;
    }
}
