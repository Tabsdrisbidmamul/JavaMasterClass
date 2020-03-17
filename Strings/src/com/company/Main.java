package com.company;

public class Main {

    public static void main(String[] args) {
        String myString = "This is a string";

        System.out.println("myString is equal to " + myString);

        myString = myString + ", and this is more.";

        System.out.println("myString is equal to " + myString);

        myString = myString + " \u00A9 2019";

        System.out.println("myString is equal to " + myString);

        String numberString = "250.55";
        numberString = numberString + "45.95";

        // String appended here, to convert we have to use the Wrapped Class methods to do so
        System.out.println(numberString);


        // Java looks at the contents of myInt/doubleNumber and converts it to a String, thus we have an appended value
        // here. but the variables myInt and doubleNumber are still the same data type - they have not changed
        String lastString = "10";
        int myInt = 50;
        double doubleNumber = 120.47d;
        lastString = lastString + doubleNumber;

        System.out.println("LastString is equal to " + lastString);



    }
}
/*
* String
* The String is a data type in Java, which is not a primitive type, it is actually a Class - but is favoured a lot in
* Java to make it easier to use than a regular Class
*
* What is a String?
* a String is a sequence of characters, in case of the char type - it can only hold one Unicode value - but a String
* can hold a sequence of characters - a large number in fact ~2.14B limited to max range of int (2^32), due to the
* memory allocated to the String
*
* Strings in Java are Immutable
* You cannot delete characters out of the string literal, because Strings in Java are immutable, they cannot be
* changed after its creations - what happens when we place a new string in the variable - we are simply creating a
* new String
*
* For out results above, the number converted String is not appended to the lastString, but Java makes a new String
* and the old one has been discarded
*
* This method is inefficient, as appending values like this consumes resources to create and discard String - that is
* why we use the StringBuffer Class to mitigate this, as it can be changed.
* ---------------------------------------------------------------------------------------------------------------------
*
* */