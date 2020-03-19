package com.example;

import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static Map<String, HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();

    public static void main(String[] args) {
        HeavenlyBody temp = new HeavenlyBody("Mercury", 88);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        temp = new HeavenlyBody("Venus", 225);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        temp = new HeavenlyBody("Earth", 365);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        HeavenlyBody tempMoon = new HeavenlyBody("Moon", 27);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon);

        temp = new HeavenlyBody("Mars", 687);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        tempMoon = new HeavenlyBody("Deimos", 1.3);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon); // temp is still Mars

        tempMoon = new HeavenlyBody("Phobos", 0.3);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon); // temp is still Mars

        temp = new HeavenlyBody("Jupiter", 4332);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        tempMoon = new HeavenlyBody("Io", 1.8);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon); // temp is still Jupiter

        tempMoon = new HeavenlyBody("Europa", 3.5);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon); // temp is still Jupiter

        tempMoon = new HeavenlyBody("Ganymede", 7.1);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon); // temp is still Jupiter

        tempMoon = new HeavenlyBody("Callisto", 16.7);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon); // temp is still Jupiter

        temp = new HeavenlyBody("Saturn", 10759);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        temp = new HeavenlyBody("Uranus", 30660);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        temp = new HeavenlyBody("Neptune", 165);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        temp = new HeavenlyBody("Pluto", 248);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        System.out.println("Planets");
        for (HeavenlyBody planet: planets) {
            System.out.println("\t" + planet.getName());
        }

        HeavenlyBody body = solarSystem.get("Jupiter");
        System.out.println("Moons of " + body.getName());
        for (HeavenlyBody jupiterMoon : body.getSatellites()) {
            System.out.println("\t" + jupiterMoon.getName());
        }

        Set<HeavenlyBody> moons = new HashSet<>();
        for (HeavenlyBody planet : planets) {
            moons.addAll(planet.getSatellites());
        }

        System.out.println("All Moons");
        for (HeavenlyBody moon : moons) {
            System.out.println("\t" + moon.getName());
        }

        HeavenlyBody pluto = new HeavenlyBody("Pluto", 842);
        planets.add(pluto);

        for (HeavenlyBody planet : planets) {
            System.out.println(planet.getName() + " : " + planet.getOrbitalPeriod());
        }

    }
}

/*
* Sets
* Sets are an unordered collection of items, and all items must all be unique (no duplicates are not allowed)
* NOTE: There are ordered set as well - TreeSets or LinkedHashSets
* Now with sets, you can check to see if the set is empty, get its size, add and remove items and iterate it through
* it - but what you cannot do is retrieve an item from the set - you will have to use a list for that
*
* Sets are similar in Maps in terms of theory - where only unique values are only allowed - where maps has a
* key-value relationship - Sets only allows keys themselves and no values
*
* Sets are deceived from the Collections interface itself - there it uses methods that are found in Collections and
* not in Maps and SortedMaps
*
* addAll()
* Accepts an argument which is of type <? extends type parameter passed to Set when initialised>
* This method is basically a union (intersection) of sets, as the items in the set are unique, when we do a addAll()
* we are basically gathering the all the unique values and putting them into a new Set with only the unique values
* that we plucked out from other sets.
*
* equals()
* With our Sets, in the above code we intentionally added the planet Pluto twice with different orbital periods, but
* this should not be the case as all items in the Set should be unique, the way Java defined uniqueness is by the
* equal() method which every object obtains as it is subclassed from the Object class. There the equal() method is
* very basic and it checks if that 2 references point to the same object in memory then it is equal.
*
* Thus we must always override the equals() method when using Sets, and in Map(s) if they are to be used as keys - to
* make sure that uniqueness is maintained all the time
*
* If you overriding the equals() method, then the hashCode() method must also be overwritten - because not only are
* we checking for equality we also checking if their hash codes are equal as well
*
* HashCode - is an integer representation of the memory address - by default, its a random integer that is unique for
* each instance - as every time the program runs different memory address are given for each object - thus it will
* rarely be the same
*
* Bottom-Line: if 2 objects have the same hash code, then they must be equal!!
*              But if they are equal - they don't necessarily have the same hash code
*
* Hash code takes further precedent over equal
*
* The way hashCodes work is like this, every time we go to add an element into a HashSet or what class that
* implements Sets - it will store the elements in memory buckets basically
*
*   Bucket 1       Bucket 2       Bucket 3
*    Pluto         tempPluto      Asteroid
*
* Each bucket is linked to a particular HashCode, when calling planets.add(pluto) - Java stores pluto inside a
* bucket and links it to the value of pluto.hashCode(), now anytime an element with the same hashCode is inserted
* into the set, it will not replace pluto, however tempPluto has a different hashCode, thus it will be stored in a
* separate bucket and will be considered a totally different object
*
* Now when HashSet searches for an element inside it, it first generates the element hash code and looks for a bucket
* which corresponds to this hash code
*
* So if we override hashCode so that it returns an int value - say id's for planets - then pluto will be replaced by
* tempPluto - as they will have hashCode based not only on the equals() method but also on their planetId
*
*
*
* The rules for equals() method override
*   - It is reflexive: for any non-null reference value x, x.equals(x) should return true.
*   - It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if
*     y.equals(x) returns true.
*   - It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z)
*     returns true, then x.equals(z) should return true.
*   - It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently
*     return true or consistently return false, provided no information used in equals comparisons on the objects is
*     modified.
*   - For any non-null reference value x, x.equals(null) should return false.
*
* */
