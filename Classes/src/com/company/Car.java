package com.company;

public class Car {

    private int doors;
    private int wheels;
    // We know that String is a class, so we are effectively calling a class within another class
    // further shows that Classes are basically user-defined data types
    private String model;
    private String engine;
    private String colour;

    public void setModel(String model) {
        String validModel = model.toLowerCase();
        if (validModel.equals("carrera") || validModel.equals("commodore")) {
            this.model = model;
        } else {
            this.model = "Unknown";
        }
    }

    public String getModel() {
        return this.model;
    }

}

/*
* Classes - OOP
* Object Orientated paradigm is a way to write 'new' user defined data types - it allows us to do a lot more than the
* what 8 primitive data types are capable of
*
* Classes can be seen as blueprints to what an object will use when creating itself
* Objects have characteristics
*   - State: Each object will have its own characteristics - of course collectively they are still the same - but how
*     they are done differentiates them from the other - so for a car its state could be engineOn() engineOff()
*
*   - Behaviour: The methods that the object can execute - so for a car it can go forward(), backwards() start()
*     turnOff()
*
*   - Attributes: are what you could say the 'physical' appearance of the object - so with the car you say its
*     attributes are its: colour, engine size, number of doors
*
* ---------------------------------------------------------------------------------------------------------------------
* This keyword
* In Java, we can use the 'this' keyword to further benefit us when writing in OOP
*
* Field names
* in the code above, we wrote a public method to set the name of the model of the object, and we passed in the
* parameter 'model'; but now you will see there is a naming conflict - how do we tell Java which model is which (i.e
* one that belongs to the class, and the other being the parameter name)
*
* we use the
*   this.model = model
*
* This tells Java that [this.] refers to the object, we are getting setting the variable model in the class Car to be
* equal to the parameter in the method setModel()
*
* ---------------------------------------------------------------------------------------------------------------------
* setter and getter methods
* in Java OOP, we generally want to use these methods called setters and getters
*
* setters: they allow us to change or set the contents of an attribute of that given object
*   public void setName(String name) {
*       this.name = name;
*   }
*
* getters: they allows us to retrieve the contents of an attribute(s)
*   public String getName() {
*       return this.name;
*   }
*
*
* */
