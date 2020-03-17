package com.company;

public class Main {

    public static void main(String[] args) {
        int result = 1 + 2; // 1 + 2 = 3
        System.out.println("1 + 2 = " + result);

        // to show that the value of previousResult is always independent of result value (i.e) think of it as
        // literals previousResult = 3 (result), that is it, when result is updated again, previousResult won't
        // change, as to change it, you must explicitly state the new result
        int previousResult = result;
        System.out.println("previousResult = " + previousResult);

        result = result - 1; // 3 - 1 = 2
        System.out.println("3 - 1 = " + result);
        System.out.println("previousResult = " + previousResult);

        result *= 10; // 2 * 10 = 20
        System.out.println("2 * 10 = " + result);

        result /= 5; // 20 / 5 = 4
        System.out.println("20 / 5 = " + result);

        result %= 3; // the remainder of (4 % 3) = 1
        System.out.println("4 % 3 = " + result);

        // result = result + 1
        result++;
        System.out.println("1 + 1 = " + result);

        result--; // 2 - 1 = 1
        System.out.println("2 - 1 = " + result);

        // result = result + 2
        result += 2; // 1 + 2 = 3
        System.out.println("1 + 2 = " + result);

        // result = result * 10
        result *= 10; // 3 * 10
        System.out.println("3 * 10 = " + result);

        // result = result / 3
        result /= 3; // 30 / 3 = 10
        System.out.println("30 / 3 = " + result);

        // result = result - 2
        result -= 2; // 10 - 2 = 8
        System.out.println("10 - 2 = " + result);

        boolean isAlien = false;
        if (isAlien == false) { // can be simplified to (!isAlien)
            System.out.println("It is not an alien!");
        }

        int topScore = 80;
        if (topScore < 100) {
            System.out.println("You got the high score!");
        }

        int secondTopScore = 95;
        if ((topScore > secondTopScore) && (topScore < 100)) {
            System.out.println("Greater than second top score and less than 100");
        }

        if ((topScore > 90) || (secondTopScore <= 90)) {
            System.out.println("Either or both of the conditions are true");
        }

        int newValue = 50;
        if (newValue == 50) {
            System.out.println("This is an true");
        }

        boolean isCar = false;
        if (isCar) { // isCar = true does not flag an error, as Java expects to find an expression which
            // evaluates to a boolean type
            System.out.println("This is not supposed to happen");
        }

        // the if-then statement won't run, this is because of the ternary operator; it works like this
        // data_type varName = cond ? true : false
        // we have 3 operands, the cond, true and false
        // so we have a condition, and if the condition is true (usually a boolean expression) then set the variable
        // to the second operand (in this case true), if the condition evaluated to false, then set the variable to the
        // third operand (in this case false)
        // isCar = true;
        boolean wasCar = isCar ? true : false;
        if (wasCar) {
            System.out.println("wasCar is true");
        }

        // challenge: testing what we have learned on operators
        double firstDouble = 20.00d;
        double secondDouble = 80.00d;
        double newResult = (firstDouble + secondDouble) * 100.00d;
        double remainder = newResult % 40.00d;

        boolean isRemainderZero = (remainder == 0.0d) ? true : false;
        System.out.println(isRemainderZero);

        if (!isRemainderZero) {
            System.out.println("Got some remainder");
        }
    }
}

/*
* What are Operators?
* Operators in Java are special symbols that perform specific operations on 1, 2 or 3 operands and then return a result
* We have seen the + operator in previous projects:
*   - double doubleNumber = 120.47d;
*   - lastString = lastString + doubleNumber
*
* What is an Operand?
* An operand is a term used to describe any object that is manipulated by an operator (i.e.)
*   - int myVar = 15 + 12;
*   - the plus (+) is the operator, and 15 and 12 are the operands, variables used instead of literals are also
*   operands
*   - double mySalary = hoursWorked * hourlyRate
*   - hoursWorked and hourlyRate are operands, and the multiplication (*) is the operator
*
* What is an Expression?
* An expression is formed by combining variables, literals, method return values and operators
*   - int myValue = 15 + 12
*   - 15 + 12 is the expression which has (or returns) 27 in this case
*
* ---------------------------------------------------------------------------------------------------------------------
* if-then Statements in Java
* is the most basic of all control flow statements, it tells your program to execute a certain section of code if
* only a particular test (an expression) evaluates to true - this is known as 'Conditional Logic'
*
* Conditional Logic
* uses specific statements in Java to allow us to check a condition and execute code based on whether that condition
* (the expression) is true or false
*
* ---------------------------------------------------------------------------------------------------------------------
* Logical AND and Logical OR
* The AND operator comes in 2 variants, as does the OR operator
*
* && is the logical AND which operates on boolean operands - checking if a given condition is true or false
* & is the bitwise operator, which operates on the bit level
*
* || is the logical OR operator, and again operates on boolean operands - checking if given condition is true or false
* | is the bitwise operator, which operates on the bit level
*
* ---------------------------------------------------------------------------------------------------------------------
* The NOT Operator
* !/ NOT operator is also known as the Logical Complement Operator
* for use with booleans, it tests the alternate value - we saw (isCar) tests for true, by adding a ! operator before
* the value, we can check the opposite - false in this case
*
* ---------------------------------------------------------------------------------------------------------------------
* Ternary Operator (? :)
* is a shortcut to assigning one of two values to a variable depending on a given condition
* it is a shortcut of the if-then-else statement
*
* example
*   - int ageOfClient = 20;
*   - boolean isEighteenOrOver = ageOfClient == 20 ? true : false;
* We do a equal to comparison where the first operand would be saying is the variable contents of ageOfClient equal
* to 20? (which it is), thus set the variable isEighteenOrOver to  the second operand (in this case true)
*
* Operand one - the condition; ageOfClient == 20; it needs to return true or false
* Operand two - (true) is the value to assign to the variable isEighteenOrOver if the condition (operand one) is true
* Operand three - (false) is the value to assign to the variable isEighteenOrOver if the condition (operand one) is
* false
*
* placing parenthesis around the boolean condition can make the code more readable
*   - boolean isEighteenOrOver = (ageOfClient == 20) ? true : false;
*
* */