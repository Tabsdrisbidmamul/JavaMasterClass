package com.company;

public class Main {

    public static void main(String[] args) {
        Car porsche = new Car();
        Car holden = new Car();
        System.out.println("Model is " + porsche.getModel());  // null is the default state for a String class
        porsche.setModel("911");
        System.out.println("Model is " + porsche.getModel());
    }
}

/*
* Initialise Classes - Objects
* To instantiate a class, we treat it like a data type - so we type
*   ClassName objectName = new constructorName(argumentList);
*
* unlike data types, Classes do not have default values, and we must always initialise a class to make it an object,
* we can still do
*   Car porsche;
*
* but if we don't add 'new Car();' we have not given it a value basically
*   porsche = new Car();
*
* and we can use it from there on
*
* we can also do
*   Car porsche;
*   porsche = null;
*
* This will also give another error, we started to initialise an object, but we then said that this part-way done
* object is now null, so Java will say that this object is not complete
*
* ---------------------------------------------------------------------------------------------------------------------
* Simple Updates of field members - Objects (breaking encapsulation concepts)
* We made one of the members of the Car class public (for demonstration purposes), and we can get the member of that
* object by using the dot notation (.) to access inner parts of the object
*
*   porsche.model = "Carrera";
*
* We are telling Java that Car object called 'porsche', that one of its attributes called 'model' will its contents
* filled with the string literal "Carrera".
*
* But we should rarely do this, as that is breaking the concepts of encapsulation (i.e hiding data from other objects)
*
* ---------------------------------------------------------------------------------------------------------------------
* Accessing methods - objects (adhering to encapsulation concepts)
* Rather we want to make a public method which can set the field values and retrieve them whenever we want
* using the same dot notation, we call the objects method setModel(), and pass in the name we want model to have so
*
*   porsche.setModel("Carrera");
*
* This does not break the concepts of encapsulation, and allows better information-hiding of field members of an object
*
* The reason we would want to do this is for many reasons:
*   - validation: check that the arguments of the setter methods are what we the programmers want it to be
*
* */
