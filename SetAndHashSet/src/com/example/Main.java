package com.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static Map<String, HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();

    public static void main(String[] args) {
                /*
        Modify the previous HeavenlyBody example so that the HeavenlyBody
        class also has a "bodyType" field. This field will store the
        type of HeavenlyBody (such as STAR, PLANET, MOON, etc).

        You can include as many types as you want, but must support at
        least PLANET and MOON.

        For each of the types that you support, subclass the HeavenlyBody class
        so that your program can create objects of the appropriate type.

        Although astronomers may shudder at this, our solar systems will
        allow two bodies to have the same name as long as they are not the
        same type of body: so you could have a star called "BetaMinor" and
        an asteroid also called "BetaMinor", for example.

        Hint: This is much easier to implement for the Set than it is for the Map,
        because the Map will need a key that uses both fields.

        There is a restriction that the only satellites that planets can have must
        be moons. Even if you don't implement a STAR type, though, your program
        should not prevent one being added in the future (and a STAR's satellites
        can be almost every kind of HeavenlyBody).

        Test cases:
        1. The planets and moons that we added in the previous video should appear in
        the solarSystem collection and in the sets of moons for the appropriate planets.

        2. a.equals(b) must return the same result as b.equals(a) - equals is symmetric.

        3. Attempting to add a duplicate to a Set must result in no change to the set (so
        the original value is not replaced by the new one).

        4. Attempting to add a duplicate to a Map results in the original being replaced
        by the new object.

        5. Two bodies with the same name but different designations can be added to the same set.

        6. Two bodies with the same name but different designations can be added to the same map,
        and can be retrieved from the map.
*/
        HeavenlyBody temp = new Planet("Mercury", 88);
        solarSystem.put(temp.getName() + "_" + temp.getBodyType(), temp);
        planets.add(temp);

        temp = new Planet("Venus", 225);
        solarSystem.put(temp.getName() + "_" + temp.getBodyType(), temp);
        planets.add(temp);

        temp = new Planet("Earth", 365);
        solarSystem.put(temp.getName() + "_" + temp.getBodyType(), temp);
        planets.add(temp);

        HeavenlyBody tempMoon = new Moon("Moon", 27);
        solarSystem.put(tempMoon.getName() + "_" + tempMoon.getBodyType(),  tempMoon);
        temp.addSatellite(tempMoon);

        temp = new Planet("Mars", 687);
        solarSystem.put(temp.getName() + "_" + temp.getBodyType(), temp);
        planets.add(temp);

        tempMoon = new Moon("Deimos", 1.3);
        solarSystem.put(tempMoon.getName() + "_" + tempMoon.getBodyType(),  tempMoon);
        temp.addSatellite(tempMoon); // temp is still Mars

        tempMoon = new Moon("Phobos", 0.3);
        solarSystem.put(tempMoon.getName() + "_" + tempMoon.getBodyType(),  tempMoon);
        temp.addSatellite(tempMoon); // temp is still Mars

        temp = new Planet("Jupiter", 4332);
        solarSystem.put(temp.getName() + "_" + temp.getBodyType(), temp);
        planets.add(temp);

        tempMoon = new Moon("Io", 1.8);
        solarSystem.put(tempMoon.getName() + "_" + tempMoon.getBodyType(),  tempMoon);
        temp.addSatellite(tempMoon); // temp is still Jupiter

        tempMoon = new Moon("Europa", 3.5);
        solarSystem.put(tempMoon.getName() + "_" + tempMoon.getBodyType(),  tempMoon);
        temp.addSatellite(tempMoon); // temp is still Jupiter

        tempMoon = new Moon("Ganymede", 7.1);
        solarSystem.put(tempMoon.getName() + "_" + tempMoon.getBodyType(),  tempMoon);
        temp.addSatellite(tempMoon); // temp is still Jupiter

        tempMoon = new Moon("Callisto", 16.7);
        solarSystem.put(tempMoon.getName() + "_" + tempMoon.getBodyType(),  tempMoon);
        temp.addSatellite(tempMoon); // temp is still Jupiter

        temp = new Planet("Saturn", 10759);
        solarSystem.put(temp.getName() + "_" + temp.getBodyType(), temp);
        planets.add(temp);

        temp = new Planet("Uranus", 30660);
        solarSystem.put(temp.getName() + "_" + temp.getBodyType(), temp);
        planets.add(temp);

        temp = new Planet("Neptune", 165);
        solarSystem.put(temp.getName() + "_" + temp.getBodyType(), temp);
        planets.add(temp);

        temp = new Planet("Pluto", 248);
        solarSystem.put(temp.getName() + "_" + temp.getBodyType(), temp);
        planets.add(temp);

//        System.out.println("Planets");
//        for (HeavenlyBody planet: planets) {
//            System.out.println("\t" + planet.getName());
//        }
//
//        HeavenlyBody body = solarSystem.get("Jupiter");
//        System.out.println("Moons of " + body.getName());
//        for (HeavenlyBody jupiterMoon : body.getSatellites()) {
//            System.out.println("\t" + jupiterMoon.getName());
//        }
//
//        Set<HeavenlyBody> moons = new HashSet<>();
//        for (HeavenlyBody planet : planets) {
//            moons.addAll(planet.getSatellites());
//        }
//
//        System.out.println("All Moons");
//        for (HeavenlyBody moon : moons) {
//            System.out.println("\t" + moon.getName());
//        }

        HeavenlyBody pluto = new Planet("Pluto", 842);
        planets.add(pluto);

        System.out.println(pluto.equals(temp));
        System.out.println(temp.equals(pluto));

        HeavenlyBody earth = new Star("Earth", 8000.0);
        planets.add(earth);

        System.out.println(solarSystem.put(earth.getName() + "_" + earth.getBodyType(), earth));

        earth = new Star("Earth", 1.0);
        planets.add(earth);

        System.out.println(solarSystem.put(earth.getName() + "_" + earth.getBodyType(), earth));

        System.out.println("Planets");
        for (HeavenlyBody planet : planets) {
            System.out.println("\t" + planet.getName() + " : " + planet.getOrbitalPeriod());
        }

        System.out.println("Solar System");
        for (String name : solarSystem.keySet()) {
            System.out.println("\t" + solarSystem.get(name));
        }

        System.out.println(getHeavenlyBodyObject("Earth", HeavenlyBody.BodyTypes.STAR));
        System.out.println(getHeavenlyBodyObject("Earth", HeavenlyBody.BodyTypes.PLANET));

    }

    private static HeavenlyBody getHeavenlyBodyObject(String name, HeavenlyBody.BodyTypes bodyType) {
        String key = name + "_" + bodyType;
        if(solarSystem.containsKey(key)) {
            return solarSystem.get(key);
        }
        return null;
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
* The equals method for class Object implements the most discriminating possible equivalence relation on objects;
* that is, for any non-null reference values x and y, this method returns true if and only if x and y refer to the
* same object (x == y has the value true).
*
* The rules for hashCode() method override
*   - Whenever it is invoked on the same object more than once during an execution of a Java application, the hashCode
*     method must consistently return the same integer, provided no information used in equals comparisons on the
*     object is modified. This integer need not remain consistent from one execution of an application to another
*     execution of the same application.
*   - If two objects are equal according to the equals(Object) method, then calling the hashCode method on each of the
*     two objects must produce the same integer result.
*   - It is not required that if two objects are unequal according to the equals(java.lang.Object) method, then calling
*     the hashCode method on each of the two objects must produce distinct integer results. However, the programmer
*     should be aware that producing distinct integer results for unequal objects may improve the performance of hash
*     tables.
* As much as is reasonably practical, the hashCode method defined by class Object does return distinct integers for
* distinct objects. (This is typically implemented by converting the internal address of the object into an integer,
* but this implementation technique is not required by the Java™ programming language
*
* */
