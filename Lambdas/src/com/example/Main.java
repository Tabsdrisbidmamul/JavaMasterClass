package com.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        new Thread(()-> {
//            System.out.println("Printing from the Lambda");
//            System.out.println("Line 2");
//            System.out.printf("This is line %d\n", 3);
//        }).start();

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

//        Collections.sort(employees,
//                (employee1, employee2)->employee1.getName().compareToIgnoreCase(employee2.getName()));
//        for (Employee employee: employees) {
//            System.out.println(employee.getName());
//        }

        employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });

//        for (Employee employee: employees) {
//            System.out.println(employee.getName());
//            new Thread( () -> System.out.println(employee.getAge()) ).start();
//        }

//        System.out.println("--------------------------------------------------------------------------------------");
//        for (int i=0; i<employees.size(); i++) {
//            Employee employee = employees.get(i);
//            System.out.println(employee.getName());
//            new Thread( () -> System.out.println(employee.getAge())).start();
//        }

//        String sillyString = doStringStuff(new UpperConcat() {
//            @Override
//            public String upperAndConcat(String s1, String s2) {
//                return s1.toUpperCase() + s2.toUpperCase();
//            }
//        },
//        employees.get(0).getName(), employees.get(1).getName());

//        UpperConcat uc = (s1, s2) -> {
//            String result = s1.toUpperCase() + s2.toUpperCase();
//            return result;
//        };
//        String sillyString = doStringStuff(uc, employees.get(0).getName(), employees.get(1).getName());

//        String sillyString = doStringStuff(((s1, s2) -> s1.toUpperCase() + s2.toUpperCase()),
//                employees.get(0).getName(), employees.get(1).getName());
//
//        System.out.println(sillyString);
//
//        AnotherClass anotherClass = new AnotherClass();
//        String result = anotherClass.doSomething();
//        System.out.println(result);

    }

    public static final String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }

}

class CodeToRun implements Runnable {
    @Override
    public void run() {
        System.out.println("Printing from the Runnable");
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

@FunctionalInterface
interface UpperConcat {
    String upperAndConcat(String s1, String s2);
}

class AnotherClass {
    public String doSomething() {
//        System.out.println("The AnotherClass class's name is: " + getClass().getSimpleName());
//        return Main.doStringStuff(new UpperConcat() {
//            @Override
//            public String upperAndConcat(String s1, String s2) {
//                System.out.println("The anonymous class's name is: " + getClass().getSimpleName());
//                return s1.toUpperCase() + s2.toUpperCase();
//            }
//        }, "String1", "String2");

        int i = 0;
        System.out.println("The AnotherClass class's name is: " + getClass().getSimpleName());
        return Main.doStringStuff(((s1, s2) -> {
            System.out.println("The lambda expressions class's name is: " + getClass().getSimpleName());
            System.out.println("i in the lambda expression: " + i);
            return s1.toUpperCase() + s2.toUpperCase();
        }), "String1", "String2");
    }

    public void printValue() {
        int number = 25;

        Runnable r = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The value is " + number);
        };

        new Thread(r).start();
    }
}

/*
* Lambdas
* Introduced in Java 8, and they provide us an easier way to work with interfaces that have only got one method - and
*  they are typically used in placed where we have anonymous classes involved
*
* Now when starting a thread either a class that implements Runnable or an anonymous instance of Runnable - we have
* to write a lot of filler code to instantiate the Runnable interface so we can to get the the run() method. But what
*  we really cared about is the println() we simply wanted to output a message from a Runnable object.
*
* Lambda provides a way to remove the "filler" code and simply have Java fill in for us and we the programmer can put
* in the println() in a more concise and easier to understand code
*
* new Thread(()-> System.out.println("Printing from the Lambda")).start();
* This part particular is the Lambda expression
*   - ()-> System.out.println("Printing from the Lambda")
*
* Now every Lambda expression has got 3 parts: and you can think of it as a quick way of writing a function
*   1. Argument List: arguments of course
*   2. Arrow Token: (->)
*   3. The body: the code
*
* ()-> System.out.println("Printing from the Lambda")
* Lets dissect this expression
*   - (): This is an empty argument list, we are passing in an empty argument list - we don't need to provide any
*         extra information to our body of code
*   - ->: This is the arrow token which translate the argument list to the body of code - in this case we don't
*         provide any arguments, so our body of code is simply a fruitless function (procedure)
*   - System.out.println("Printing from the Lambda"): The body of code which is processed as normal
*
* Now how does the JVM know how to interpret the lambda expression:
* - Well in the Thread class constructor, it accepts a Runnable object
* - Java also knows that the Runnable object also does not need any arguments (the same as our argument list)
* - Java also knows that the Runnable interface only has one method
*
* You can see where we are going with this, Java will best try to match the Lambda expression to the Threads
* constructor - in this case a Thread Constructor - which accepts an Object (we know Runnable, but there could be
* more... for example) which takes no parameters and only has one method within that instance.
*
* The Runnable object best matches those descriptions - so the JVM will pop the body of code into the run() method of
* the Runnable.run() and then pass that back up the to the Thread class which then is able to start()
*
* Now lambda expressions can only be used with interfaces that only have one method, and sometimes the interfaces can
* be referred to as "functional interfaces".
*
* Now this isn't always the case, which is most of the time. Any interface that has this annotation
* @FunctionalInterface
*
* basically saying we can use lambda expressions on it
*
* In IntelliJ in the gutter (left hand side with all the line numbers for code), there lambda expressions are marked,
* and by clicking on it we are taken to what the JVM possible believed the lambda expression should add its code to
*
* We can also have multiple lines of code within our lambda expressions
* We have to wrap the body of code with curly braces {}, and end each line of code with a semi-colon (;) as you would
*  normally do.
*       new Thread(()-> {
            System.out.println("Printing from the Lambda");
            System.out.println("Line 2");
            System.out.printf("This is line %d\n", 3);
        }).start();
*
* Running this, output multiple lines of code now
*
*
* Now within the documentation for the Comparator interface
* https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
*
* It has the @FunctionalInterface atop of the declaration for Comparator
*
* Meaning we can a lambda expression on it, and for Collections.sort(), where we want to sort the ArrayList
* employees, using a Comparator - we can do so using a lambda expression.
*
* We know for it to work we need to override the compare() method, and we want to compare 2 Employee objects
* - For our argument list, we pass 2 params both of Employee type
* - pass the arrow token to tell Java this is a lambda expression
* - Write the body of code, which is the compareTo from the String Class
*
* NOTE: we don't write return there, in that case - Java knows the compareTo returns a value - which in this case is
* going to use that for the return value of compare() in Comparator
*
*         Collections.sort(employees,
                (Employee employee1, Employee employee2)->employee1.getName().compareToIgnoreCase(employee2.getName()));
*
* Argument List: (Employee employee1, Employee employee2)
* Arrow Token: ->
* Bode of code we want to run: employee1.getName().compareToIgnoreCase(employee2.getName())
*
* Java will try to best match the argument list to a method within the Comparator interface - which the method are
* mainly distinct from one another (the argument list)
*
* NOTE: As the compare method() expects to comparing Employee objects we can actually omit the Employee from the
* argument like this
*
* (employee1, employee2) and it will still work
*
* NOTE: If only one argument is passed to the argument list, there is no need to wrap it around parenthesis
* (actionEvent -> System.out.println("You clicked me!"))
*
*
* Return types
* With the employee example above we used a lambda expression to replace the anonymous class for the Comparator - the
*  compare() method needed to return an int value for the Comparator to do the sorting - but in the lambda expression
* we never did explicit type return or state it - it just worked!
*
* Why?
* Because the JVM will infer that the lambda will do a return with the code it has, you see the method expected a int
* value to be returned hence the code we gave to the lambda body did that.
*
* We made an interface UpperConcat with one method - it would also be annotate it with @FunctionalInterface, for both
* the compiler and reader to be able to infer that this interface supports lambda (it will still do even without, but
* its best for readability)
*
* There are ways we can about this, we make the lambda expression into a variable, and pass it into the method
* or we write the lambda in the method argument list
*
//       UpperConcat uc = (s1, s2) -> s1.toUpperCase() + s2.toUpperCase();
//       String sillyString = doStringStuff(uc, employees.get(0).getName(), employees.get(1).getName());

        String sillyString = doStringStuff(((s1, s2) -> s1.toUpperCase() + s2.toUpperCase()),
                employees.get(0).getName(), employees.get(1).getName());

        System.out.println(sillyString);

    public static final String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
*
*
* Return within Lambda
* If we include a curly brace and write multiple lines within the lambda body - we can store local variables and also
* use the return keyword as well in this case
*
* //        UpperConcat uc = (s1, s2) -> {
//            String result = s1.toUpperCase() + s2.toUpperCase();
//            return result;
//        };
//        String sillyString = doStringStuff(uc, employees.get(0).getName(), employees.get(1).getName());

        String sillyString = doStringStuff(((s1, s2) -> {
            String result = s1.toUpperCase() + s2.toUpperCase();
        return result;
                }),
                employees.get(0).getName(), employees.get(1).getName());

        System.out.println(sillyString);
*
* Nested Blocks
* We can write a nested block anywhere by simply writing curly braces ({})
* Now we know about scope - and how outer blocks cannot access variables or references within the inner blocks. This
* is because the JVM garbage collector will come to essentially terminate the variable and free up space
*
* Now we need to understand this - variables are stored on the stack (which is regularly cleaned up to free up space
* (as it is static and can hold only so much) where as objects are stored in the heap (dynamic memory space)
*
* If we define a local variable outside a code block and want to use it inside a code block (say to increment a
* counter and output it, we can do that fine)
*
* But if we were to use that same local variable and try to use increment it and use it within an anonymous class/
* interface instance. We can't do that, the compiler will throw an error. This is because an anonymous class instance
* may still persist after its creation whereas a local variable will not - so it is like saying to use a local
* variable within an object that may disappear at any notice.
*
* To get around that, we have to declare the local variable final - so that the variable is on the heap and not on
* the stack.
*
*
*
* When we come to output the class's name for the container class and the lambda - we get AnotherClass for results,
* this is because lambda is not an anonymous class (which in that case does not output anything - it has no name when
* defined).
*
        System.out.println("The AnotherClass class's name is: " + getClass().getSimpleName());
        return Main.doStringStuff(((s1, s2) -> {
            System.out.println("The lambda expressions class's name is: " + getClass().getSimpleName());
            return s1.toUpperCase() + s2.toUpperCase();
        }), "String1", "String2");
*
* This brings us back to lambda expressions, we can use variables are effectively final (i.e. they are declared final
* or their value never changes. If any one of those rules are not followed, we will get an error - if we want to use
* that variable within a lambda expression)
*
*       int i = 0;
        i++;
        System.out.println("The AnotherClass class's name is: " + getClass().getSimpleName());
        return Main.doStringStuff(((s1, s2) -> {
            System.out.println("The lambda expressions class's name is: " + getClass().getSimpleName());
            System.out.println("i in the lambda expression: " + i);
            return s1.toUpperCase() + s2.toUpperCase();
        }), "String1", "String2");
*
*
* Running this bit of code will yield an error - because it is not effectively final or final - to get around this we
* either don't change the value of i, or make a new final variable which holds the updated value of i (say finalI =
* i;), and replace the reference in the sout statement from i to finalI
*
* Now we have to imagine that any lambda expression is within a code block itself - meaning we cannot reference any
* arguments within the argument list like:
*  s1 = "hello"; -> this will throw an error because the variable s1 has stopped existing after the code reached the
*  end of the lambda expression
*
* To drive the point, we are going to look at this Runnable example
* - What we are doing is assigning a local variable the value 25
* - Then creating a new Runnable instance using a lambda to bypass the anonymous instance of the run() method
* - We let the Thread sleep for 5 seconds
* - And at the end of it we output the value 25
*
* What is going to happen is, while the Thread is suspended (asleep), the local variable 25 will be destroyed -
* meaning it will not exist. So how does the lambda know what value to print
*
* Now this goes back to the discussion of effectively final or final - if the value does not change - then when the
* code enters the lambda - the value of number in its local scope (so this under the wraps of the JVM) will be set
* and if in the event the local variable is destroyed, the lambda expression has, what is effectively its own copy of
*  the value of number
*
*     public void printValue() {
        int number = 25;

        Runnable r = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The value is " + number);
        };

        new Thread(r).start();
    }
*
* Another example in Main.main()
* We output the name's of the employees as well their age as well within new unjammed Threads - and depending on the
* timing we get the age for each employee in varying orders - but this proves really well that lambdas have hidden
* functionality that make handling with variable easier (as long as they do not change (effectively final) or are
* declared final)
*         for (Employee employee: employees) {
            System.out.println(employee.getName());
            new Thread( () -> System.out.println(employee.getAge()) ).start();
        }
*
* Now we might be wondering that the local variable within the for loop employee changes - it is assigned a ned value
* over each iteration and is declared outside the lambda
*
* Well the variable employee is effectively final because a new employee variable is created for each iteration
*
* if we use an old-style for loop
*         for (int i=0; i<employees.size(); i++) {
            Employee employee = employees.get(i);
            System.out.println(employee.getName());
            new Thread( () -> System.out.println(employee.getAge())).start();
        }
*
* A new variable employee is created for each iteration
*
*
*  but if we do this
*
*
* Employee employee;
*        for (int i=0; i<employees.size(); i++) {
            employee = employees.get(i);
            System.out.println(employee.getName());
            new Thread( () -> System.out.println(employee.getAge())).start();
        }
*
* We run into a compile time error saying that the variable employee is not final or effectively final
* This is because we are re-using the variable employee instated of creating a new Object reference where we did in
* the first example using the enhanced for-each loop
*
* forEach() method
* This method will only work on objects that are extends Iterable, like ArrayList
*       employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });
*
* We use a lambda expression to bypass the anonymous instance of Consumer to override the method accept(), which
* will take the contents of the body and simply do as normal like a for loop
*
*
* */

