package com.example._static;

public class Main {

    public static int multiplier = 7;

    public static void main(String[] args) {
//	    StaticTest firstInstance = new StaticTest("1st Instance");
//        System.out.println(firstInstance.getName() + " is instance number " + StaticTest.getNumInstance());
//
//        StaticTest secondInstance = new StaticTest("2nd Instance");
//        System.out.println(secondInstance.getName() + " is instance number " + StaticTest.getNumInstance());
//
//        StaticTest thirdInstance = new StaticTest("3nd Instance");
//        System.out.println(secondInstance.getName() + " is instance number " + StaticTest.getNumInstance());

        // java com.example._static.Main <-- Java will look into the Main class and expect a static method to run the
        // program

        int answer = multiply(6);
        System.out.println("The answer is " + answer);
        System.out.println("The multiplier is " + multiplier);

    }

    public static int multiply(int number) {
        return number * multiplier;
    }
}

/*
* Static
* In the example above, we have created 2 instances of the same class, but when come to output it - we get that
* numInstance is 1 for each instance - rather than being 2 like we would think - this is because each instance
* will create a new member fields and methods for every non-static it can find.
*
* However if we were to make numInstance a static field - we get that numInstance is 2 when we print output the
* second instance - this is because static members/ fields are created once and all instances will share that in
* memory.
*
* When creating classes, those methods and fields do not exist till the class has been instantiated, so whenever we
* create static methods or fields - they exist already whenever we reference the class - however we cannot access
* non-static fields or methods from a static context in its own class - static methods/ fields can access non-static
* variables/ methods in another class - because the other class has to been instantiated for it to be available
*
* Non-static methods can access static methods/ fields in its own class, but the restriction is on static methods
* accessing non-static references
*
*
* */