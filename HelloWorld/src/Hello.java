public class Hello {
    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        System.out.println("Hello Name!");

        int myFirstNumber = (10 + 5) + (2 * 10);  // we have given type (a.k.a data type), name and literal value (RHS of the equal sign)
        int mySecondNumber = 12;
        int myThirdNumber = myFirstNumber * 2;
        int myTotal = myFirstNumber + mySecondNumber + myThirdNumber;
        int myLastOne = 1000 - myTotal;

        System.out.println(myFirstNumber);
        System.out.println(mySecondNumber);
        System.out.println(myThirdNumber);
        System.out.println(myTotal);
        System.out.println(myLastOne);
    }

}



/*
* public: is  a Java keyword and an access modified
* access modifier: allows us to define the scope or how other parts of your code or even someone else's code can access
* this code
*
* class: keyword is used to define a class with the name following the keyword - 'Hello' in this case, left and right
* curly braces to define the class block
*
* To define a class, it requires an optional access modifier (public, private, protected etc) followed by the class
* keyword, followed by the name and finally the left and right curly braces
* The left and right curly braces are defining the class body - anything between them is 'part' of this class
* (we can have data and code within)
* ---------------------------------------------------------------------------------------------------------------------
* Defining the main method
* Method: it is a collection of one or more statements that perform an operation
*
* We will be using a special method called the main method that Java looks for when running a program - it is the entry
* point of any Java code
*
* static: [fill in once gone through major parts of the course]
*
* void: it is used to indicate to Java that the method will not return a value when called
*
*
* Code Block: is used to define a block of code, it is mandatory to have one in a method declaration (i.e like the
* curly braces in the main method) and it is here where we will be adding statements to perform certain tasks.
*
* statement: is a complete command to be executed and can include one or more expressions
* ---------------------------------------------------------------------------------------------------------------------
* Variables
*
* Variables are a way to store information in our computer, and they can be defined in a program and can be accessed
* by a name we give them, and the computer does the back-end work of storing it the computer's random access memory
*
* Collectively variables can store various bits of information, and in Java they are known as 'Data Types' (keywords)
*
* To define a variable we need to do
*   - specify the data type (int, double, long, byte etc)
*   - give the variable a name
*   - (optional) add an expression to initialise the variable with a value
*
* Declaration Statement: used to define a variable by indicating the data type, and the name and optionally to set
* the variable to a certain value
*
* String Literals: any sequence of characters surrounded by double quotes "" is a String literal, meaning its value
* cannot be changed - unlike a variable
*
* Operators: Java operators will perform an operation (+, -, *, /, %, ^, etc...)
*
* ---------------------------------------------------------------------------------------------------------------------
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
* */