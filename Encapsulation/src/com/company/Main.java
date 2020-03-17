package com.company;

public class Main {

    public static void main(String[] args) {
//	    Player player = new Player();
//	    player.name = "Idris";
//	    player.health = 20;
//	    player.weapon = "Sword";
//
//	    int damage = 10;
//	    player.loseHealth(damage);
//        System.out.println("Remaining health = " + player.healthReaming());
//
//        damage = 11;
//        player.health = 200;
//		player.loseHealth(damage);
//		System.out.println("Remaining health = " + player.healthReaming());

//		EnhancedPlayer player = new EnhancedPlayer("Idirs", 200, "Sword");
//		System.out.println("Initial health is " + player.getHealth());

		// Create a class and demonstrate proper encapsulation techniques
		// the class will be called Printer
		// It will simulate a real Computer Printer
		// It should have fields for the toner Level, number of pages printed, and
		// also whether its a duplex printer (capable of printing on both sides of the paper).
		// Add methods to fill up the toner (up to a maximum of 100%), another method to
		// simulate printing a page (which should increase the number of pages printed).
		// Decide on the scope, whether to use constructors, and anything else you think is needed.

		Printer printer = new Printer(75.0, false);
		printer.fillUpToner(5.0);
		System.out.println("initial page count = " + printer.getNumberOfPagesPrinted());
		printer.printPage(49);

    }
}

/*
* Encapsulation
* A technique used in OOP, to prevent other classes or code from accessing the inner workings of a class - this is
* different from security, as encapsulation is only within classes; and not anything to drastic with modern security
* measures
*
* Bad encapsulation code example - Class Player
* In Player class, we have defined the 3 instance variables as 'public' - this access modifier means that any class
* can see these fields and can modify them outside the class
* Problems
* 	- 1. We did not make a constructor, so we have to initialise these fields outside of the object creation.
* 		 This means that anytime we change the name of the field variables, we then have to make that name change for
*  		 instance we did to initialise the filed variables
* 	- 2. We wrote methods which calculate the player health, using our validation and formulae, as we made the fields
*  		 public, we can access them directly and change the value of them at anytime - basically nullifying the
*        formulas we made to calculate health loss
* 	- 3. Every Class has access to not only the methods, but the instance variables - and can do the above at anytime
*  		 when they want - effectively making logic errors at every point when we want to do behaviours for the class
*
* Good encapsulation code example - Class EnhancedPlayer
* In EnhancedPlayer class, we have defined 3 instance variables as 'private' - this access modifier means that only
* the class itself can see these fields and manipulate them, outside classes cannot access them directly - they can
* only get to them via public methods (if any were written for them)
* Solved
* 	- 1. We made a constructor, so when initialising the Class to create an object, we have to pass in arguments to
* 		 initialise the fields for the object to use
* 	- 2. We made those fields private, so outside classes cannot access them directly and cannot change their values
* 		 either
* 	- 3. As the fields are private, we can actually change the field name within the class, and associated references
*  		 to it within the class, but we can actually keep the same method(s) name - (i.e. getHealth() returns
* 		 hitPoints), any programmer does not actually know that the original name was health changed to hitPoints, so
*  		 we are keeping all inside information to a minimum - no outside classes can see the names of private
* 		 statements within the class.
*
* */