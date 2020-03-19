package com.example;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private final int locationId;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationId, String description, Map<String, Integer> exits) {
        this.locationId = locationId;
        this.description = description;
        if(exits != null) {
            this.exits = new HashMap<>(exits);
        } else {
            this.exits = new HashMap<>();
        }
        this.exits.put("Q", 0);


    }


    public int getLocationId() {
        return locationId;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        // return essentially a copy, so no outside classes can change exits
        return new HashMap<>(exits);
    }
}
