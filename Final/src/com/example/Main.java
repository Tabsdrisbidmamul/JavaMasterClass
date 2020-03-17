package com.example;

public class Main {

    public static void main(String[] args) {
//	    SomeClass one = new SomeClass("one");
//	    SomeClass two = new SomeClass("two");
//	    SomeClass three = new SomeClass("three");
//
//		System.out.println(one.getInstanceNumber());
//		System.out.println(two.getInstanceNumber());
//		System.out.println(three.getInstanceNumber());
//
////		one.instanceNumber = 4;
//
//		System.out.println(Math.PI);
//
////		Math m = new Math();
//
//		int pw = 674312;
//		Password password = new ExtendedPassword(pw);
//		password.storePassword();
//		password.letMeIn(pw);
//		password.letMeIn(1);

		System.out.println("Main method called");
		SIBTest test = new SIBTest();
		test.someMethod();
		System.out.println("Owner is " + SIBTest.owner);
    }
}

/*
* Final
* You generally use them to define constant values, however strictly speaking they are not actually constants,
* because they can be modified only once and any modification must be performed before the class constructor finishes
* That means we can declare a final field when we first declare it, or in the constructor
*
* In the above example we have created a final int variable which will store the contents of the classCounter
* variable, as this variable is final - its value will not change after initialisation - therefore even though the
* static field is shared between each instance - the final variable is there to make sure that each instance copies
* what the static field is at that moment of being initialised.
*
* Generally we would assign constants as 'static final' this is because all instances share this constant so it makes
* sense to leave this at class level instead of having every instance have one
*
* Now when saying that a class is final, means that it cannot be subclassed - because final references are always
* constant
*
* The same applies to methods, say if we do want the class to be subclass, but only a selection of its methods to not
* be overwritten - we do this by telling Java that these methods in the base class will be final - meaning that when
* the base class is extended from, those final methods cannot be overwritten, keeping the integrity of the function
* in the base class into the extended class the same
*
* Static Initialisation Blocks
* These are blocks of static code, and is an advanced technique that is rarely used but it goes likes in Class calls
*
* 	static {
*		Do something;
*	 }
*
* The SIBs are called first before the constructor, and can be a good way to initialise static variable to have a
* hardcode value or use a static method to calculate a value to store in.
*
* The code below is en example of bad practice, because we have placed a SIB after the constructor call, this can
* lead to confusion - but in hindsight - both SIB's are called before the constructor call
*
*
public class SIBTest {
    public static final String owner;

    static {
        owner = "tim";
        System.out.println("SIBTest static initialisation block called");
    }

    public SIBTest() {
        System.out.println("SIB constructor called");
    }

    static {
        System.out.println("2nd initialisation block called");
    }
*
* */