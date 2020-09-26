package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("Red RidingHood", 35);
        Employee charming = new Employee("Prince Charming", 31);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);

        Function<Employee, String> getLastName = (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(" ") + 1);
        };

        String lastName = getLastName.apply(employees.get(1));
//        System.out.println(lastName);

        Function<Employee, String> getFirstName = (Employee employee) -> {
            return employee.getName().substring(0, employee.getName().indexOf(" "));
        };

        Random random1 = new Random();
        for (Employee employee: employees) {
            if(random1.nextBoolean()) {
                System.out.println(getAName(getFirstName, employee));
            } else {
                System.out.println(getAName(getLastName, employee));
            }
        }

        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(" "));
        Function<Employee, String> chainedFunction = upperCase.andThen(firstName);
        System.out.println(chainedFunction.apply(employees.get(0)));

        BiFunction<String, Employee, String> concatAge = (String name, Employee employee) -> {
            return name.concat(" " + employee.getAge());
        };

        String upperName = upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(upperName, employees.get(0)));

        IntUnaryOperator intc = i -> i + 5;
        System.out.println(intc.applyAsInt(10));

        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello World");



//        printEmployeesByAge(employees, "Employees over 30", employee -> employee.getAge() > 30);
//        printEmployeesByAge(employees, "Employees under 30", employee ->  employee.getAge() <= 30);
//
//        IntPredicate greaterThan15 = i -> i > 15;
//        IntPredicate lessThan100 = i -> i < 100;
//        System.out.println(greaterThan15.test(10));
//        int a = 20;
//        System.out.println(greaterThan15.test(a + 5));
//
//        System.out.println(greaterThan15.and(lessThan100).test(50) + "\n");
//
//        Random random = new Random();
//        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);
//        for (int i=0; i<10; i++) {
//            System.out.println(randomSupplier.get());
//        }
//
//        employees.forEach(employee -> {
//            String lastName = employee.getName().substring(employee.getName().indexOf(" ") + 1);
//            System.out.println("Last Name is: " + lastName);
//        });


    }

    private static String getAName(Function<Employee, String> getName, Employee employee) {
        return getName.apply(employee);
    }

    public static void printEmployeesByAge(List<Employee> employees, String ageText, Predicate<Employee> ageCondition) {
        System.out.println("*******************************************************");
        System.out.println(ageText);
        for (Employee employee: employees) {
            if(ageCondition.test(employee)) {
                System.out.println(employee.getName());
            }
        }
    }
}

/*
* Foreach
* The foreach method accepts an object of type Consumer
*
* Consumer
* This interface has 2 implementations - one is a default (andThen()) and the other requires us to implement (accept
* ()) - thus making it valid to use lambda on.
*
* The Consumer method for forEach actually calls the accept() method and not by the programmer - in this case the
* Consumer calls the accept method on each of the Employee objects that are passed to it - consuming them and not
* returning anything from it and then repeating the process on every other Employee object passed to it in the
* iteration.
*
* Thus making it perfect to do outputting in one line making for concise and readable code over the enhanced for loop
* and old-style for loops
*
* Predicate
* Predicate - we used this interface back in JavaFX, but essentially it allows us to map a test condition to the
* Predicate object, which we can use in if, while, for conditions etc.
* This is the structure of the Predicate we used in the JavaFX
*
*       Predicate t1 = new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return false;
            }
        };
*
*
* Predicate is a FunctionalInterface, the other methods are using default implementation meaning that the test()
* method is the only one we need to implement - which is us putting together a test condition which will return a
* boolean true or false.
*
* We made this method, which accepts the List of employees, then a message which will act as the header and lastly
* the Predicate object - more specifically the test() implementation
*
* We use a lambda expression in this case to say that we are going to be using Employee objects (which we defined in
* the parameterised type) and we want it to return true or false if the Employee object's age value is > or < than
* the value we want to test against.
*
*     printEmployeesByAge(employees, "Employees over 30", employee -> employee.getAge() > 30);
      printEmployeesByAge(employees, "Employees under 30", employee ->  employee.getAge() <= 30);

*
*     public static void printEmployeesByAge(List<Employee> employees, String ageText, Predicate<Employee> ageCondition) {
        System.out.println("*******************************************************");
        System.out.println(ageText);
        for (Employee employee: employees) {
            if(ageCondition.test(employee)) { <- we use the Predicate test() here to check the employee age
                System.out.println(employee.getName());
            }
        }
    }
*
* When ran, this run identical to enhanced for loop, old style for loop or forEach where we are testing the age as we
* might normally would
*
* We can still use an anonymous instance of Predicate to override the test() method and it will still work - but
* really it is a matter of preference of whether or not you are going to lambda over the anonymous instance
*
* There are variation of Predicate, like IntPredicate, LongPredicate and the same goes for Consumer as well - where
* they are more specific to the data type that is going to passed to it.
*
* Here we assign Predicate to a variable - so we can use later any point on the program, and the test() method
* returns boolean true or false depending on the argument passed to the
*       IntPredicate intp = i -> i > 15;
        System.out.println(intp.test(10));
        int a = 20;
        System.out.println(intp.test(a + 5));
*
*
* Chaining Predicates
* We can chain predicates, the methods and, or and negate return a new IntPredicate (there are more methods, but
* these are more specific to IntPredicate)
*
* the method and() will accept another IntPredicate instance to popped into it - and it does exactly what it says it
* will boolean AND the 2 Predicates test() features together where then you can call test() on the call that called
* and() (as and will return the Boolean AND of the IntPredicate(s))
*
* So here what we are doing is AND'ing:
* - the test i > 15
* - the test i < 100
*
* (i < 15) && (i > 100) on the test value 50
*
* System.out.println(greaterThan15.and(lessThan100).test(50));
*
*
* Supplier - @FunctionalInterface
* This interface will generate a number of Objects that is passed to it
* The opposite to Consumer - where it will return values and not need any parameters passed to it
* The Supplier expects a parameterised type to be given - this is what will generate the objects within the Supplier
* object
* Like Predicate there are more favours to it
*
* We use a lambda expression to override the get() method - what we is random.nextInt(1000) and each call to get()
* will call the nextInt(1000) and generate a new number
*
*       Random random = new Random();
        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);
        for (int i=0; i<10; i++) {
            System.out.println(randomSupplier.get());
        }
*
* Function<T, R> (@FunctionalInterface)
* This interface essentially acts a function that takes one argument (T) and produces a result (R)
* What this does is basically bypass creating a function normally (method within a class) but also we can use lambda
* expressions to bypass creating anonymous instances of the Function interface (in which case we are simply
* overriding apply())
*
*       Function<Employee, String> getLastName = (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(" ") + 1);
        };
*
* Now like the Predicate, Supplier and Consumer - Function allows a more modular design with the code, if we wanted
* to have an object run different algorithms for different states it is in, it is far easier to write several
* Function using lambda expressions and pass them into a method which accepts a Function instance we can simply run
* that Function (lambda expression) for that point of the Object state
*
* This is far easier to read and to write - compared to say writing an interface for basically a placeholder function
* in where we write several classes to implement that Interface function
*
* Function also comes in different flavours - like LongToInt (accepts a long and return an int value) etc.
*
* Now like with Predicate, we can chain Function's together, each call to the andThen() method, the argument passed
* to the andThen() method will work on the result from the Function calling the andThen() method
*
*       Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(" "));
        Function chainedFunction = upperCase.andThen(firstName);
        System.out.println(chainedFunction.apply(employees.get(0)));
*
*
* The compose() works in the opposite way to andThen(), where it will call the second Function first (the one passed as
*  an argument) then call the first Function with the result from the second Function
*
* BiInterface
* The BiInterface is made up of Predicate, Supplier, Function etc.. but they all have Bi prefixed to them - this
* means they accept 2 arguments and not one
*
* BiFunction<T, U, R> @FunctionalInterface
* This Interface accepts 2 arguments (T and U) and will result in R
* The BiFunction's andThen() method can be used on a singular Function<T, R> because we are returning one result to
* be placed into Function
*
* it does not work the other way round, Function<T, R>.andThen(BiFunction<T, U, R>) - because BiFunction requires 2
* arguments - Function<T, R> will only result in one result R
*
*       String upperName = upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(upperName, employees.get(0)));
*
*
* UnaryOperator (@FunctionalInterface) depends on the type
* The UnaryOperator prefixed with any mathematical primitive data type so IntUnaryOperator etc.. when used in
* conjuction with lambda - results in a mathematical formulae you can use one the go
*
*       IntUnaryOperator intc = i -> i + 5;
*       System.out.println(intc.applyAsInt(10));
*
*
* NOTE: Consumer can use the andThen() method - but as it does not return a result the return result is lost
*
*       Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello World");
*
*
*/
