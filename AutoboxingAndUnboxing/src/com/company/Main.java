package com.company;

import java.util.ArrayList;


class intClass {
    private int myValue;

    public intClass(int myValue) {
        this.myValue = myValue;
    }

    public int getMyValue() {
        return myValue;
    }

    public void setMyValue(int myValue) {
        this.myValue = myValue;
    }
}

public class Main {

    public static void main(String[] args) {
	    String[] strArray = new String[10];
	    int[] intArray = new int[10];

        ArrayList<String >srtArrayList = new ArrayList<>();
        srtArrayList.add("Text");

//        ArrayList<int> intArrayList = new ArrayList<int>();
        ArrayList<intClass> intClassArrayList = new ArrayList<>();
        intClassArrayList.add(new intClass(54));

//        Integer integer = 54; // new Integer(54) deprecated feature
//        Double doubleValue = 12.25; //new Double(12.25); deprecated feature

        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for(int i = 0; i < 11; i ++) {
            integerArrayList.add(i); // (Integer.valueOf(i)) deprecated feature unnecessary
        }

        for(int i = 0; i < 11; i ++) {
            System.out.println(i + " --> " + integerArrayList.get(i)); //.intValue() not needed anymore,
            // can be done without it
        }

        ArrayList<Double> myDoubleValues = new ArrayList<>();
        for (double dbl = 0.0; dbl < 11.0; dbl += 0.5) {
         myDoubleValues.add(Double.valueOf(dbl));
        }

        for (int i = 0; i < myDoubleValues.size(); i ++) {
            System.out.println(i + "  --> " + myDoubleValues.get(i).doubleValue());
        }

    }
}

/*
* Autoboxing and Unboxing
* In Java many methods or Java special tools require objects to be passed to it, (i.e they need to a class)
* With Java, we cannot use the 8 primitive types, as they are not classes, instead we could this
*
* class intClass {
    private int myValue;

    public intClass(int myValue) {
        this.myValue = myValue;
    }

    public int getMyValue() {
        return myValue;
    }

    public void setMyValue(int myValue) {
        this.myValue = myValue;
    }
}
*
* This essentially makes a 'Wrapper' class for the int type, where we make a class, that accepts an int value in the
* constructor, and can be get and set. But this makes it a bit complicated now, and bulky as wee have to write more
* code to have data types wrapped around a class to get the job done
*
* Java instead have their own wrapper class for each of the 8 primitive types
*   - Integer
*   - Long
*   - Short
*   - Byte
*   - Double
*   - Float
*   - Boolean
*   - Character
* They work just like a normal class, where we can use the new operator to initialise the class to make it into an
* object, now by doing this, we can now use in tools like ArrayList<>, where they require an object to work.
*
*   ArrayList<intClass> intClassArrayList = new ArrayList<>();
*
* NOTE: They are some lines of code commented out, because those features using Autoboxing and Unboxing are
* deprecated and instead use a more simple approach, where we literally pass it the primitive data type and not have
* to use any of the methods that exist - of course you can still us them, from a backwards compatible standpoint
* Autoboxing:
*   Where we convert an primitive data type to its respected Wrapper Class object
*   int -> Integer
*
* Unboxing:
*   Where we convert the Wrapper object back to its respected primitive data type
*   Integer -> int
*
*   Integer integer = 54; // new Integer(54) deprecated feature - initialises the Integer class with primitive 54
*
*   integerArrayList.add(i); // (Integer.valueOf(i)) deprecated feature unnecessary - this will 'Autobox' mean wrap
*   the primitive into an Integer object
*
*   System.out.println(i + " --> " + integerArrayList.get(i)); //.intValue() not needed anymore,
*   - this will transform the object Integer back into a primitive int value also known as Unboxing
*
* Integer myIntValue = 56; // Integer.valueOf(56) --> Done at compile time
* int myInt = myIntValue; // myIntValue.intValue() --> Done at compile time
*
* */