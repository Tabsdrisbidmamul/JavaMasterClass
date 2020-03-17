package com.company;

public class Main {

    public static void main(String[] args) {
        // can be any single character that is in the ASCII standard or UTF-8
        char myChar = 'D';

        // to write unicode characters we have to prepend (backslash)u to tell Java that this is a unicode followed by
        // the actual unicode
        char myUnicodeChar = '\u0044';

        System.out.println(myChar);
        System.out.println(myUnicodeChar);

        char myCopyrightChar = '\u00A9';

        System.out.println(myCopyrightChar);

        boolean myTrueBooleanValue = true;
        boolean myFalseBooleanValue = false;

        // when writing boolean variable names, we write it as if it were a question
        boolean isCustomerOverTwentyOne = true;
    }
}

/*
* Char Data Type
* A char occupies two bytes of memory - or 16 bits (has a width of 16), the reason is that it is not just a single
* byte, is that it allows you to store Unicode characters
* ---------------------------------------------------------------------------------------------------------------------
* Unicode
* Unicode: is an international encoding standard for use with different languages and scripts, by which each letter,
* digit, or symbols is assigned a unique numeric value that applies across different platforms and programs
*
* In the english alphabet, we have letters from A-Z; meaning we only need 26 characters, but in other languages they
* need more
*
* Unicode allows us to represent these languages and the way it works is that by using a combination of the two bytes
*  that a char takes up in a memory it can represent and one of 65,535 different types of characters
*
* ---------------------------------------------------------------------------------------------------------------------
* Boolean
* Allows 2 choices True or False (1 or 0), in Java we have a boolean primitive type and it can be set to 2 values
* only, 'true' or 'false' (in lower case!)
*
* */
