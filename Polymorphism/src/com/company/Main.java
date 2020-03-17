package com.company;

import java.lang.management.MonitorInfo;

class Movie{
    private String name;

    public Movie(String name) {
        this.name = name;
    }

    public String plot() {
        return "No plot here";
    }

    public String getName() {
        return name;
    }
}

class Jaws extends Movie {
    public Jaws() {
        super("Jaws");
    }

    @Override
    public String plot() {
        return "A shark eats lots people";
    }
}

class IndependenceDay extends Movie {
    public IndependenceDay() {
        super("Independence Day");
    }

    @Override
    public String plot() {
        return "Aliens attempt to take over planet Earth";
    }
}

class MazeRunner extends Movie {
    public MazeRunner() {
        super("Maze Runner");
    }

    @Override
    public String plot() {
        return "Kids try and escape a maze";
    }
}

class StarWars extends Movie {
    public StarWars() {
        super("Star Wars");
    }

    @Override
    public String plot() {
        return "Imperial Forces try take to over the Universe ";
    }
}

class ForgettableMovie extends Movie {
    public ForgettableMovie() {
        super("Forgettable Movie");
    }

    // no plot method
}



public class Main {

    public static void main(String[] args) {
        for (int i=1; i<11; i++) {
            Movie movie = randomMovie();
            System.out.println("Movie #" + i + " : " +  movie.getName() + "\n" + "Plot: " + movie.plot() + "\n");
        }



    }

    public static Movie randomMovie() {
        int randomNumber = (int) ( (Math.random() * 5) + 1);
        System.out.println("Random Number generated was: " + randomNumber);
        switch (randomNumber) {
            case 1:
                return new Jaws();
            case 2:
                return new IndependenceDay();
            case 3:
                return new MazeRunner();
            case 4:
                return new StarWars();
            case 5:
                return new ForgettableMovie();
        }
        return null;
    }
}

/*
* Polymorphism
* In OOP, polymorphism refers to the ability to process objects differently depending on their data-types or class -
* more specifically it is the ability to redefine methods for derived classes.
*
* NOTE: Java allows only one public class per file, hence we have just written all the classes in this file as class
* with no access modifier
*
* So our example above, we have created a generic class called 'Movie' which will act as a base class for all other
* objects which will be a more specific movie. Movie contains a constructor to initialise the 'name' of the movie, it
* has 2 methods, one is 'plot()' which returns a String literal which explains what the movie is about - in this case
* we have a String saying "no plot found" , and a getter method 'getName()' to retrieve the name of the movie.
*
* we have created 5 classes, 4 of the 5 are movie titles - which contain a constructor which calls the super
* constructor from the base class, and contains a method which overrides the plot() method with its owm movie plot.
* The last class is just there as a placeholder and only references the super constructor from the base class, but
* does not contain the plot() method.
*
* NOTE: classes which extend from a base class, are a sub-type of that object - so all the titles Jaws(), StarWars()
* etc.. are not only object type Jaws, StarWars etc... but also object type Movie - hence we can return only one
* object type, but have 5 different objects being put out
*
* We write a static method in Main of type Movie, to randomly generate a subclass of Movie and return it
* We use a for loop to go over these statements over 10 times
*   Movie movie = randomMovie();
*   System.out.println("Movie #" + i + " : " +  movie.getName() + "\n" + "Plot: " + movie.plot() + "\n");
*
* We are creating a variable called movie of type Movie, which will hold the return value from the method which
* randomly generates subclasses of Movie (i.e. Jaws, StarWars, MazeRunner ....) and we output it to the console, the
* iteration we are on, the movie name and the plot.
*
* The beauty behind this is, that even though we calling methods from the Movie class (getName() and plot()) we are
* getting return values from the subclasses (Jaws, StarWars etc...) all in one variable -> this is polymorphism
*
* The ability to process each subclass object differently, and redefine methods as well using one variable, instead
* of list of variables and a series of if's and switch statements
* So for example movie variable in the loop holds the return value of the instantiated Jaws class
*   - 1. Jaws is of type Movie, so it is perfectly valid to hold this object in the variable Movie
*   - 2. when we run movie.getName() : we are given Jaws; this is because this object used the super constructor in
*        the base class Movie - but we did not make any variable to hold the accounted Jaws object, Java saw that we
*        have got object Jaws, but we are running a method called getName() - so it looks in Jaws Class to see if it
*        has the method. No. so we go up to the superclass and see it exists there, so it runs the one in parent class
*   - 3. When we run movie.plot : we are given the plot for Jaws ("A shark eats lots people"), Java is smart enough
*        to know that we have a method in Jaws called plot() so it runs the one in Jas and not in the parent class.
*
* its like if we had made 6 reference variables one for each class: movie, jaws, independenceDay... and we ran a
* series of if statements to determine the object being returned, then running the appropriate reference variable to
* retrieve the method, it becomes a huge mess of things - so it much easier to let Java handle the heavy work, and we
* can reference one variable.
*
* if for example we had ForgettableMovie - which does not have plot() method - Java will run the method in the parent
* class and will not throw any errors there!
*
* So in general: we can polymorphism to allow an object (reference variable) to be processed differently for
* different object types (those that derive from a base class; like Animal -> Dog. A Dog can be of object type Dog,
* but can also be object type Animal; as it inherits from Animal) and redefine method calls, and return values or how
* it is executed for that respected class.
* */


