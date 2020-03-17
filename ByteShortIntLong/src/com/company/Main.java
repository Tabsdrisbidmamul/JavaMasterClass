package com.company;

public class Main {

    public static void main(String[] args) {
	    int myValue = 10000;

	    int myMinIntValue = Integer.MIN_VALUE;
	    int myMaxIntValue = Integer.MAX_VALUE;

		System.out.println("Integer Minimum Value = " + myMinIntValue);
		System.out.println("Integer Maximum Value = " + myMaxIntValue);

		System.out.println("Busted MAX Value =  " + (myMaxIntValue + 1));
		System.out.println("Busted MIN Value =  " + (myMaxIntValue - 1));

		// If we manually add 1 to this value, IDE will flag it as an error
		int myMaxIntTest = 2_147_483_647;

		byte myMinByteValue = Byte.MIN_VALUE;
		byte myMaxByteValue = Byte.MAX_VALUE;

		System.out.println("Byte Minimum Value = " + myMinByteValue);
		System.out.println("Byte Maximum Value = " + myMaxByteValue);


		short myMinShortValue = Short.MIN_VALUE;
		short myMaxShortValue = Short.MAX_VALUE;

		System.out.println("Short Minimum Value = " + myMinShortValue);
		System.out.println("Short Maximum Value = " + myMaxShortValue);

		// always need to append uppercase 'L' to long values, it forces Java to treat this number as a long type and
		// not integer type because an int can fit into a long
		long myLongValue = 100L;

		long myMinLongValue = Long.MIN_VALUE;
		long myMaxLongValue = Long.MAX_VALUE;

		System.out.println("Long Minimum Value = " + myMinLongValue);
		System.out.println("Long Maximum Value = " + myMaxLongValue);

		// if we manually enter a value that is greater than an int, Java throws an error, saying that we need to
		// indicate that this is a long value and not an integer (as it is too larger to fit into integer rang), so we
		// must append it with an uppercase 'L'
		long bigLongLiteralValue = 2_147_483_647_234L;

		System.out.println(bigLongLiteralValue);

		// Java is smart, and can convert an integer literal (RHS value) to the short type for us, as it can see that
		// the integer literal can fit into the short range of values
		short bigShortLiteralValue = 32767;

		int myTotal = (myMinIntValue / 2);

		// when we use literal values, they are treated as int types, the same is seen when using variables, it will
		// treat it as an int type when placed on the RHS because Java will always assume that we are using int types,
		// thus we must be explicit to Java and cast the variable to the required type to remove the error that is
		// made when doing so
		byte myNewByteValue = (byte) (myMinByteValue / 2);
		short myNewShortValue = (short) (myMinShortValue / 2);


		byte byteVariable = (byte) 10;
		short shortVariable = (short) 32000;
		int intVariable = 1000000;

		long sumOfThree = 50000L + (10L * (byteVariable + shortVariable + intVariable));

		System.out.println(sumOfThree);

	}
}

/*
*
* Primitive Types
* These are the most basic data types, there are eight primitive data types:
*   - boolean
*   - byte
*   - char
*   - short
*   - int
*   - long
*   - float
*   - double
* These are the basic building blocks of data manipulation
*
* ---------------------------------------------------------------------------------------------------------------------
* Packages
* Is a way to organise your Java projects, for now consider them as folders with company in our example
* being a sub-folder of com
* companies user their domain names reversed, so company.com becomes com.company
*
* ---------------------------------------------------------------------------------------------------------------------
* Wrapper Class
* Java uses the concept of a wrapper class for all eight primitive types, in this case "int" -> "Integer" and by
* doing this it gives us ways to perform operations on an int - so in this case we are using Integer to access the
* min and max value of int data type
*
* ---------------------------------------------------------------------------------------------------------------------
* Overflow and Underflow
* If you try to put a value larger than the maximum value in Java, or a value smaller than the minimum value in Java
* then you will get an Overflow (i.e our busted max value) or an Underflow (i.e our busted min value)
*
* The computer just skips back to the minimum number or the maximum number, which is usually not what you want
* ---------------------------------------------------------------------------------------------------------------------
* Size of Primitive Types and Width
* A Byte occupies 8 bits, a bit is not directly represented in a primitive type (we have boolean which is not really
* the same thing), so a byte occupies 8 bits - so we say that the byte has a width of 8
*
* A Short can store a larger range of numbers and occupies 16 bits and has a width of 16
*
* An Int has a much larger range, and occupies 32 bits, and has a width of 32
*
* A long has a even larger range, and occupied 64 bits, and has a width of 64
* 
* The point here is that each primitive type occupies a different amount of memory - int need 4x amount of memory
* compared to byte
*
* NOTE: don't need to know them for general use, but could come up as an interview question
*
* ---------------------------------------------------------------------------------------------------------------------
* Casting In Java
* It means to treat or convert a number from one type to another, We put the type we want the number to be in
* parenthesis like this:
* 	(byte) (myMinByteValue / 2);
*
*
* */