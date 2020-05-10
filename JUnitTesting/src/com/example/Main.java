package com.example;

public class Main {

    public static void main(String[] args) {
        System.out.println("The application is running");
    }
}

/*
* Testing
* This is a very important stage in the development cycle
* Typically we usually start with Unit Teasing
*
* Unit Testing
* This is typically done by the developer or the development team - and when talking about unit testing it usually
* refers to a method
*
* Unit Testing Framework
* This is a framework which will run automated tests - and when usually when working in a team there will be a Unit
* Test suite which will run when there is a new build - and iff 100% test pass (great!) and if not then the failed
* tests will tell the developers what most recent changes to the code broke part of an application
*
* JUnit
* This is a popular testing framework that we can use to run Unit Tests, and is pre-bundled with IntelliJ (but you
* will have to set it up yourself)
*
* Creating Tests
* The long way would be to go to project structure and add the JUnit lib and dependencies via the root lib folder in
* IntelliJ
*
* The quick way:
* We write our class as normal (BankAccount), and drag the cursor to the BankAccount declaration (the line where
*   public class BankAccount { <- This line
*       ....
*   }
*
* Alt + Enter it, to open a menu, and you want to click "Create Tests"
*
* A dialogue window will open:
*
* For Testing Library: make sure you click the latest version of JUnit (at the time of writing it is JUnit5)
* Then for every new project you want to click the option "fix" - a new dialogue pane opens - simply just press "OK"
* don't download any new JUnits unless otherwise
*
* The new class will BankAccountTest - simply the original class name with the word "test" concatenated to it
*
* At the very bottom and checkbox of methods will appear - check the ones you want to test
*
* BankAccountTest
* When this class is created - we get errors saying that it cannot find the class path for the imports - to fix this
* we go
*
* File > Project Structure > modules > and to the right of JUnit5 click the dropdown menu and instead of tests being
* selected click compile and press OK
*
* Another way is to go the class BankAccountTest - and drag the cursor to any highlighted keyword in red
* press Alt + Enter and press the option add JUnit5 to ClassPath - and making sure you just press "OK" to the Dialog
* Pane that pops up
*
* The reason why these errors occur is because JUnit when added is configured to be only be used during tests - so
* when compiling JUnit code - the JVM does not know what to do with it
*
* We have a separate class which handles testing - and there x number of method within the class to the number of
* methods you checked in the previous step
*
* As each method will test each method in the BankAccount class
*
* The decoration: @org.junit.jupiter.api.Test
* Tells the JVM that this is method is a test
*
* Creating Test Run Configuration
* Go to BankAccountTest class, and right click at the bottom of the code block - not anywhere within the
* BankAccountTest code block - but outside
*
* and click the menu option Create BankAccountTest...
* All the options in the Dialog Pane are correct and so press "OK"
* Next to the run button (at the top right of the screen) we can see BankAccountTest in the dropdown for different
* run configurations
*
* When running the BankAccountTests we see that all our tests have passed - this is wrong because there is nothing in
* these test methods (they are known as "stubs"))
*
* You want to place the method fail() and the argument can be a String like fail("This test has yet to be implemented");
* When running now - we see that all tests have failed - which now shows correctly that we have not implemented a test
*
*   @org.junit.jupiter.api.Test
    void deposit() {
        fail("This test has yet to be implemented");
    }

    @org.junit.jupiter.api.Test
    void withdraw() {
        fail("This test has yet to be implemented");
    }

    @org.junit.jupiter.api.Test
    void getBalance() {
        fail("This test has yet to be implemented");
    }
*
* Testing
* With these methods, they should always be public and return type of void
* They should also be annotated with @org.junit.jupiter.api.Test
*
* All tests should be independent - meaning that they do not depend on another method other than themselves
*
* Now all test method names should be meaningful and they indicate what they are doing
*
* Assert
* We do tests by asserting values to the tester
* Assert is mainly reserved for testing and will make sure (depending on the assert) that actual values are within
* the expected value range
*
* The assert will tell the testing suite that if a particular method has passed a test or not
*
* We can have multiple asserts within a test - however it is best practice to have one assert per test and very
* rarely no more than that
*
* assertEquals()
* This method has several constructors to it
* The main 2 arguments are the expected value and the actual value (1st and 2nd param)
* The assertEquals() will give a pass to a test if the actual value is equal to or within range of the expected value
* and a fail otherwise
*
* Here we say that the variable balance should be equal to 1200.00
* The second use of assertEquals is that the third param is the delta (A range of where the actual value can be
* within the expected value - this is mainly for floats and doubles)
*
*       assertEquals(1200, balance, 0);
        assertEquals(1200.00, account.getBalance(), 0);
*
*
* assertNotEquals()
* We can use this instead of assertEquals() when we don't want the actual value to be equal to a specific value
*
* assertArrayEquals()
* This method will compare the 2 array instances by checking if their lengths are the same, their contents and order
* are the same as well
* We cannot use the assertEquals() because that method will only check if the memory references are the same
*
* assertTrue()
* Passing in an value that will expect a result of boolean true
*   assertTrue(account.isChecking());
*
* assertFalse()
* Passing in an value that will expect a result of boolean false
*   assertTrue(accountEvo.isChecking());
*
* This works for both cases
* We can pass a instance of a Supplier which can simply be a message that anyone reading through the test suite can
* see that this particular test has failed and this is a reason why
*   assertTrue(account.isChecking(), () -> "The account is NOT a checking account");
*
* assertNull() and assertNotNull()
* We can uses these methods to check if the reference passed returns a null pointer/ values
*
* assertSame() and assertNotSame()
* We use these methods when we want to check whether 2 instances are the same
*
* assertThat()
* This method compares the actual value against a matcher (Not the Matcher class in the JDK, but in the JUnit Matcher
* class). This is more powerful than the other assert methods, since we can compare the actual value against a range
* of values (This method was only available in >= JUnit 4.4)
*
* BeforeEach
* @org.junit.jupiter.api.BeforeEach
*
* This annotation tells the test suite to execute this method before every test (every test being each method) so in
* our setup() method we are essentially creating a new instance of the BankAccount class for each test - meaning that
* every test has a fresh and new instance of BankAccount to work with
*
*   private BankAccount account;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.AccountType.CHECKING);
        System.out.println("Running a Test...");
    }
*
* AfterEach
* @org.junit.jupiter.api.AfterEach
*
* This annotation tells the test suite that his method will run after every test (mainly used for clean up code)
*
* BeforeAll
* @org.junit.jupiter.api.BeforeAll
*
* This annotation tells the test suite that method should run before all the tests and only once (this is mainly used
* for costly operations like opening DB connections or files, network sockets etc..)
*
* All methods that are annotated with a BeforeAll must be static as they will execute before the class is loaded
*
* AfterAll
* @org.junit.jupiter.api.AfterAll
*
* This annotation tells the test suite that these methods should run after all the tests and only once
*
* All methods that are annotated with a AfterAll must be static
*
* Now the order of how the test cases are executed in the order we specify (the order of the methods) but any output
* from the methods can be random as they spooled off to an I/O Thread to handle
*
* Exceptions
* When we write a test case specifically that we know that it is going to throw an Exception (in that case that test
* case is a pass because it threw what we were looking for)
*
* Assertions.assertThrow()
* This method has 3 overloaded variants - the one we used goes like this
* 1st param: the Exception itself followed by dot (.) class - telling Java to match it to that Exception class
*
* 2nd param: an instance of Executable - this is a class specific to JUnit which is a @FunctionalInterface which we
* can use a lambda to override the execute() method essentially we pass in the code we want it to do
*
* 3rd param: A string message if it fails
*
*   @org.junit.jupiter.api.Test
    public void withdraw_notBranch() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(600.00, false);
        }, "Expected to throw IllegalArgumentException");
    }
*
* In older versions of Junit4 we would have done this instead to get the argument caught
*
* We weap it around a try-catch block, and the processing is done in the try block, and the cathc block is purely
* there to catch the exception
*       try {
            account.withdraw(600.00, false);
            fail("Should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
*
*
*
* Parameterized testing
* We would normally have to create a new class for each Parameterized testing we would want to do
*
* This form of testing allows us to run a test case n times with different sets of data for each iterative run (we
* pass different sets of data by defining them in some place (method etc) and having JUnit to compile it altogether
* to run n times
*
* Imports that we need are the first three
*
* 2 things we need to do to get this working are
* 1. public static Stream<Arguments> method
* 2. This annotation @ParameterizedTest(name = "Run {index}: deposit={0}, branch={1}, expected={2}") to get the tests
*    started
*
* We create a public static Stream method of type Arguments (this is in the JUnit library), we do this to create test
* data. This of course must be returned or JUnit will not have test data to work with
*
* The values in the nested Stream goes like this
* (amount, branch, expected)
* - The amount and branch are part of the input we give to deposit() method in BankAccount - so you can see the values
*   in the nested Stream can vary
* - The last value expected is the value we of course expect when we pass in the amount to already balance in
*   BankAccount
*
* Next is the test case we must annotate like follow:
* The Parameterized can take an arbitrary String of (name = {index}: {[arguments]})
* The name being that we want it to run the test case (index being the placeholder for the test case method name)
* The arguments follows the same order as the params we pass to the test method - these are just like you would think
*  they relate back to values in the nested Streams, and you have to name it followed by giving the index value of param
*
* Next we use the @MethodSource to gather the test data from the public static method
*
* And within the testcase method we simply pass in the param name where we would might normally put hardcoded values
* into the asserts - thus allowing a more modular test case here
*   @ParameterizedTest(name = "Run {index}: deposit={0}, branch={1}, expected={2}")
*   @MethodSource("testConditions")
*
*
*   import org.junit.jupiter.params.ParameterizedTest;
    import org.junit.jupiter.params.provider.Arguments;
    import org.junit.jupiter.params.provider.MethodSource;

    import java.util.stream.Stream;

    import static org.junit.jupiter.api.Assertions.*;
*
*   private BankAccount account;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.AccountType.CHECKING);
        System.out.println("Running a Test...");
    }

    public static Stream<Arguments> testConditions() {
        return Stream.of(
                Arguments.of(100.00, true, 1100.00),
                Arguments.of(200.00, true, 1200.00),
                Arguments.of(325.14, true, 1325.14),
                Arguments.of(489.33, true, 1489.33),
                Arguments.of(1000.00, true, 2000.00)
        );
    }

    @ParameterizedTest(name = "Run {index}: deposit={0}, branch={1}, expected={2}")
    @MethodSource("testConditions")
    public void deposit(double amount, boolean branch, double expected) {
        account.deposit(amount, branch);
        assertEquals(expected, account.getBalance(), .01);
    }
*
*
*
*
* */