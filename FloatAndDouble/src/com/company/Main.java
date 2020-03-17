package com.company;

public class Main {

    public static void main(String[] args) {
        float myMinFloatValue = Float.MIN_VALUE;
        float myMaxFloatValue = Float.MAX_VALUE;

        System.out.println("Float Minimum Value = " + myMinFloatValue);
        System.out.println("Float Maximum Value = " + myMaxFloatValue);

        double myMinDoubleValue = Double.MIN_VALUE;
        double myMaxDoubleValue = Double.MAX_VALUE;

        System.out.println("Double Minimum Value = " + myMinDoubleValue);
        System.out.println("Double Maximum Value = " + myMaxDoubleValue);

        int myIntValue = 5 / 3;
        // its good practice to place 'f' and 'd' when writing literals for floats and doubles
        // if we remove 'f' from 5.25 we get an error, because the default is assumed to be a double, so you can
        // either cast it to be a float or append the literal with 'f'
        float myFloatValue = 5f / 3f;
        double myDoubleValue = 5d / 3d;

        System.out.println("MyIntValue = " + myIntValue);
        System.out.println("MyFloatValue = " + myFloatValue);
        System.out.println("MyDoubleValue = " + myDoubleValue);


        // challenge: convert a given number of pounds to kg
        double pounds = 5d;
        double poundsToKilograms = pounds * 0.45359237d;
        System.out.println("Pound mass is: " + pounds + " to Kilograms is: " + poundsToKilograms);

        // we can easily write underscores for a double values
        double pi = 3.1415927d;
        double anotherNumber = 3_000_000.4_567_890d;

        System.out.println(pi);
        System.out.println(anotherNumber);
    }
}

/*
* Floating Point Numbers
* Unlike whole numbers, floating point numbers have fractional parts that we express with a decimal point (i.e. 3.14159)
* Floating point numbers are real numbers, we use floating point numbers when we need more precision in calculations
* 
* Precision
* Refers to the format and the amount of space occupied by the type.
* Single Precision: occupies 32 bits (width of 32) range from 1.4E-45 to 3.4028235E+38
* Double Precision: occupies 64 bits (width of 64) range from 4.9E-324 to 1.7976931348623157E+308
*
* In general float and double are great floating point operations, but both are not great to use where precise
* calculations are requires - this is due to how floating point numbers are stored, not a Java problem and such
*
* Java has a class called 'BigDecimal' to mitigate this problem, so for precise calculations (i.e. currencies) do not
* use floating point numbers
*
* but for general calculations, floats and doubles are fine.
*
* ---------------------------------------------------------------------------------------------------------------------
*
* */
