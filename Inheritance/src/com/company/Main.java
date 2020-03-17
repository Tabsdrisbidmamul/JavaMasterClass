package com.company;

public class Main {

    public static void main(String[] args) {
        Animal animal = new Animal("Animal", 1, 1, 5, 5);
        Dog dog = new Dog("Yorkie", 8, 20, 2, 4, 1, 20, "long silky");
        dog.eat();
        dog.walk();
        dog.run();
    }
}

/*
* Inheritance
* Where Classes can be a subclass to a superclass
* the superclass or parent class will contain generic or an overall structure of a particular object
* the subclass will contain the more specifics of that object
*
* to state that a class is inheriting from a superclass:
*   public class SubClassName extends SuperClassName {
*       ....
*   }
*
*   public class Dog extends Animal {
*       ...
*   }
*
* This tells Java that this class is going to be a subclass to the superclass, you then need to define a constructor
* that will essentially do a 'super()' on the superclass constructor, pass in the argument list, and any new instance
* variables that are part of the constructor
*
* In the Animal class, we have generic methods like eat(), move() etc. these methods are public and can be seen other
* classes and subclasses as well, this allows us to override these generic methods to be more specific in the Dog
* class, so for example ->
*   (Animal) move(): moves an animal at 5 m/s
*   (Dog) move() [overridden]: moves the dog at 7 m/s
*
* ---------------------------------------------------------------------------------------------------------------------
* super()
* Used in inheritance, it tells Java that we are going to use a method, constructor etc. from the superclass within
* the subclass
*
* in this case, we are using super() in the context, call the constructor from the class we are extending from - i.e
* the superclass
*
*   public Dog(String name, int brain, int body, int size, int weight) {
*       super(name, brain, body, size, weight);
*   }
*
* We have created a constructor for the Dog class, and we then have to create the common link between the two via the
* constructor - the base characteristics between an Animal and a Dog
*
* super.[methodName]
* Used in inheritance, it tells Java that we are going to use the method that is in the superclass within the subclass
* this is useful: one we may have written a method which overrides the original method signature - but we might want
* to use the original method so
*   super.eat();
*
* this tells Java that we want to call the eat() method from the superclass
*
* The Java compiler adds a default super() if we don't add it - no arguments - this call must be the first statement
* in a constructor - so for example if we don't put in an empty constructor, Java adds that automatically, as well
* the super variant as well.
*
* A constructor can have a call to super() and this() - but not both!
*
* ---------------------------------------------------------------------------------------------------------------------
* Definitions and explanation about classes
* We are going to use the analogy of a house to explain these concepts
*
* Class: is basically the blueprint for a house, using the blueprints we can build as many houses as we like based on
* those plans -> this is us creating a 'public class House {...}' typing in the
*   - attributes (fields, instance variable) or the characteristics of the house, so a house may have the attributes
*     that it is made out of bricks
*
*   - behaviours (methods [procedures - void methods, functions - return type methods]) or the things that a house
*     can do {functions and procedures in a class are known as methods}, so a house may have method that turns the
*     lights on indoors
*
*   - states each object will have different characteristics, running different behaviours (whilst the general
*     behaviour is the same, the characteristics may not be) and it signifies whether object has something on or off,
*     so a house may have its light on indoors.
*
* Instantiate: each house you build is an object (also known as an instance) -> this is as using the new operator to
* make an object using a constructor
*
* Reference: each house you build has an address (a physical location), in other words if you want to tell others
* where you live, you write your address on a piece of paper and give that information to them - this is basically a
* reference.
* You can copy that reference as many times as you like, but there is still just one house that has it - in other
* words you are copying the piece of paper with that address, not the house itself
* We can pass references as parameters to constructors and methods
*
* So if we have make a class called House
* and create objects of that house
*   House blueHouse = new House("blue");
*   House anotherHouse = blueHouse;
*
* We have instantiated the House class and created 2 House objects (blueHouse and anotherHouse)
* blueHouse is a variable name which references the instantiated House in memory as
*   blueHouse -> objType: House
*                color: "blue"
*
* blueHouse does not equal this object, it just points to it in memory - this is known as a reference (as it is
* referencing an object in memory)
*
* now anotherHouse does not equal blueHouse, as blueHouse is a reference to an object in memory, another points to
* the same physical memory location as blueHouse does so
*
*   anotherHouse -> objType: House
*                   color: "blue"
*
* so if we were to make a change to either of the two reference variables
*   blueHouse.setColor("yellow") or
*   anotherHouse.setColor("yellow")
*
* we will get that the object's color has changed to yellow
*
*   blueHouse -> ObjType: House
*                color: "yellow"
*
*   anotherHouse -> objType: House
*                   color: "yellow"
*
* they both point to the same object so if we make a change to the object using either of the two reference
* variables, the only one object in memory will change
*
* so if we do
*   House greenhouse = new House("green")
*   anotherHouse = greenHouse
*
* we have created a new object - because we used the new operator (instantiate a new object)
* on the next line, we have 'de-referenced' anotherHouse, so instead of pointing to the object in memory where the
* house is "yellow", it will now point to the house we the color is "green"
*
*   greenHouse -> ObjType: House
*                 color: "green"
*
*   anotherHouse -> objType: House
*                   color: "green"
*
*
*
* */
