package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> someBingoNumbers = Arrays.asList(
                "N40", "N36",
                "B12", "B6",
                "G53", "G49", "G60", "G50", "g64",
                "I26", "I17", "I29",
                "O71"
        );

        List<String> gNumbers = new ArrayList<>();

//        someBingoNumbers.forEach(number -> {
//            if (number.toUpperCase().startsWith("G")) {
//                gNumbers.add(number);
//            }
//        });
//
//        gNumbers.sort((str1, str2) -> str1.compareToIgnoreCase(str2)); // same as String::compareToIgnoreCase
//        gNumbers.forEach(System.out::println);

        someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);

        Stream<String> ioNumbers = Stream.of("I26", "I17", "I29", "O71");
        Stream<String> inNumbers = Stream.of("N40", "N36", "I26", "I17", "I29", "O71");
        Stream<String> concatStream = Stream.concat(ioNumbers, inNumbers);
        System.out.println("------------------------------------------------");
        System.out.println(concatStream
                .distinct()
                .peek(System.out::println)
                .count());


        Employee john = new Employee("John Doe", 30);
        Employee jane = new Employee("Jane Deer", 25);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        Department hr = new Department("Human Resources");
        hr.addEmployee(jane);
        hr.addEmployee(jack);
        hr.addEmployee(snow);

        Department accounting = new Department("Accounting");
        accounting.addEmployee(john);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

//        List<String> sortedGNumbers = someBingoNumbers
////                .stream()
////                .map(String::toUpperCase)
////                .filter(s->s.startsWith("G"))
////                .sorted()
////                .collect(Collectors.toList());

        List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s->s.startsWith("G"))
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        System.out.println("--------------------------");
        sortedGNumbers.forEach(System.out::println);

//        Map<Integer, List<Employee>> groupByAge = departments.stream()
//                .flatMap(department -> department.getEmployees().stream())
//                .collect(Collectors.groupingBy(Employee::getAge));

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1: e2)
                .ifPresent(System.out::println);

        Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                })
                .count();

    }
}


/*
* Streams
* These are completely unrelated to I/O streams
* Streams in this context mean a sequence of computation so another way of putting it is a set of:
* - A series of computational steps that are chain together
*
* Now we saw that we can chain Predicates, Functions and we do the same with Streams
*
* Now we going back to scope with lambdas
* We wrote our forEach expression using a lambda expression - which is basically an inner code block - so we have to
* reference variables that we want to use for later purpose outside the lambda expression - and it also be final or
* effective final
*
* The List gNumbers is because although we are updating its contents its reference variable does not change (we are
* not creating a new object with that variable name)
*
* Hence the lambda works
*
* Method reference
* https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
* We used a forEach (the Consumer interface) to iterate over our List of Numbers, we used this lambda to output the
* contents of the list
*   gNumbers.forEach(number -> System.out.println(number))
*
* We can see that this lambda expression is actually invoking a method call, it isn't actually doing nothing more
* than a placeholder to call a method for us.
*
* Java has a way to reduce the expression even further, by doing something like this:
*   gNumbers.forEach(System.out::println);
*
* This is method referencing - it is exactly the same as (number -> System.out.println(number))
*
* What we do is reference the Class where the method is contained (System.out) and use 2 colons (::) followed by the
* method call
*
* No parameters are need here, because as it acts exactly like a lambda - it actually infers the number of arguments
* to match the parameter list in the method call by what the forEach is providing - which is one String object for
* every iteration
*
* Streams
* https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
* A sequence of elements supporting sequential and parallel aggregate operations
*
* What does this mean
*
* A Stream is a set of object references, the stream() method which was added to the Collections class in Java 8
* Creates a Stream from a collection
*
* Now each object reference in the stream corresponds to an in the collection and the ordering of the object
* reference matches the ordering of the collection
*
* Now when we to use a stream that uses a collection as a source - the stream() method is the first call we always make
*
* Now in this example someBingoNumbers is the source for the Stream
* Now the Collection someBingoNumbers does not actually change - when using a Stream
* And when using any Stream operations have to meet 2 requirements
* 1. They must be non-interfering: which means that they don't change the stream source in anyway
* 2. They must be stateless - so the result of an operation cannot depend on any state outside of the operation (an
*    example would be that it cannot depend on a variable values in a previous step (of the method calls))
*
* So with these rules stated, when using streams, every operation should be seen as an individual step that is
* operation on a stream argument
*
* So we call the stream() method someBingoNumbers - this creates a Stream object collection of all the Objects from
* someBingoNumbers - and we can work on this new "Collection" without having to worry that someBingoNumbers being
* modified
*
* Streams.map()
* This function accepts a Function<T, R> instance - we can use an anonymous instance, a lambda or a method reference
* As we simply want to convert all the Strings to uppercase we can use method reference here as the argument being
* passed to String will be all the Objects within the Stream collection
*
* As toUpperCase() belongs in the String class we reference String, followed by the 2 colons (::) then the method
* toUpperCase
*
* So String::toUpperCase
*
* Now the Stream.map() returns a stream - in this case we have mapped the method toUpperCase() to all the String
* objects in the Stream collection of someBingoNumber
*
* Its still the same Functional programming Map - where normally it would have been written map(collection, method)
* in this case we call map() on the stream collection - so in hindsight we are operating on the Stream , by calling
* the map() method on it
* map(stream, method)
*
* Now the next thing to understand is that when call filter(), we are operating on the return value from map() - so
* when we are chaining we are operating on the previous computation result
*
* Now the map() is done, and it has returned a Stream of mapped uppercase Strings from someBingoNumbers
*
* Stream.filter()
* this method accepts a Predicate instance - we use a lambda to override the test() method in the Predicate interface
*  and we simply want remove any items in the List that don't start with the uppercase letter "G" - we already made
* sure that every letter in the List was uppercase in the map()
*
* filter() method will also return a Stream collection of only Strings that start with "G"
*
* Stream.sorted()
* we sort the Stream Collection based on the natural ordering of the sorted() method
* There is also an overloaded constructor for sorted() which accepts a Comparator instance to do further sorting
* based on your judgment
*
* Stream.forEach() we call the forEach method and simply have output the the value the Consumer has at the point in
* the iteration.
*
* The forEach() method in this call is different from the Collections.forEach() call - as this one works specifically
* on Stream Collections
*         someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);
*
* Terminal Operation
* These are methods which basically end the chain of Stream
* Now the forEach() method is known as a terminal operation - which means that it ends the chain of return of Streams
*
*
* Round Up
* When a chain is evaluated, a stream pipeline is created. The stream, pipeline consists of a source, zero or more
* intermediate operations (methods that return a stream pipeline) and a terminal operation (methods that end the chain)
*
* In our example we used a collection as the source, but we could also have used an array, I/O channel and we can
* build streams from scratch
*
*
* Stream.of()
* This method allows us to create a Stream pipeline from scratch, and the it used generics the Object parameterised
* passed to the diamond must be the same in the .of() method argument list as its is (...)
*
* Stream.concat()
* This method will concatenate 2 Stream pipeline together granted they are of the same Object type
*
* Stream.peek()
* This method accepts a Consumer instance - to override accept() method and returns a Stream pipeline
* This method acts purely for debugging - so in this case we use to output the current contents of the input stream

*       Stream<String> ioNumbers = Stream.of("I26", "I17", "I29", "O71");
        Stream<String> inNumbers = Stream.of("N40", "N36", "I26", "I17", "I29", "O71");
        Stream<String> concatStream = Stream.concat(ioNumbers, inNumbers);
        System.out.println("------------------------------------------------");
        System.out.println(concatStream
                .distinct()
                .peek(System.out::println)
                .count());
*
*
* Stream.flatmap()
* returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped
* stream produced by applying the provided mapping function to each element
*
* The flatmap method take a Function<T, R> instance - basically it will apply the function to each subsequent stream
* - producing in the example below from 2 stream pipelines to 1 by essentially going over the nested lists in the
* departments List and apply the function department.getEmployee.stream()
*
* We are then applying another Stream on top the returned Lists that are gathered back from the getEmployees
*
* this basically allows in this example to iterate over a nested list
*
*         departments.stream()
              .flatMap(department -> department.getEmployees().stream())
              .forEach(System.out::println);
*
*
* Stream.collect()
* The collect method allows us essentially store the Stream into a variable
*
* We use the Collectors.toList() method here - the Collectors is an Interface that belongs to the Stream interface
* and this allows us to convert the Stream pipeline into a List.
*
* Of course there are several overloaded methods and constructors that allow us to be more specific as to how we save
* it - for example, like an ArrayList etc..
*
*         List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s->s.startsWith("G"))
                .sorted()
                .collect(Collectors.toList());
*
*
* Now there 3 params it expects:
* 1. The Supplier (Supplier<T>: Something to work with - here we pass ArrayList::new - meaning we want to construct a
* new ArrayList (() -> new ArrayList<String>())
* 2. the Accumulator (BiConsumer<T, ? super U>): This function is what will add the elements to the Supplier (container)
* 3. The Combiner (BiConsumer<T, U>): This function will also add elements to the Supplier (the container) and must
* be compatible with the  the Accumulator.
*
* Depending on the runtime the JVM will swap between the Accumulator and Combiner to add single and multiple elements
* to improve efficiency
*
* It also supports groupingBy - where we can essentially do almost like a DB GROUPBY by x and on the dataset where we
*  have given the condition of WHERE on a column or constraint
*
*        Map<Integer, List<Employee>> groupByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(Employee::getAge));
*
*
*         List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s->s.startsWith("G"))
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
*
*
* Stream.reduce()
* This method will return an instance of Optional<T> and it accepts a (BinaryOperator<T> accumulator)
* reduce() like from functional programming will reduce or minimise the size od of the list to one or so elements
* from the list - effectively retrieving very specific bits of data
*
* Here we use reduce to procure the youngest employee from the departments, we also use the ifPresent() method, as
* reduce is a terminal operation (it does not return a stream pipeline) - mainly because what it returns is usually 1
* item - and so we pass it println
*
*         departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1: e2)
                .ifPresent(System.out::println);
*
*
* Lazily Evaluated
* Stream are lazily evaluated - what that means is that the code for a stream will not be done unless there is a
* terminal operation there to tell Java that the Stream code is finished - lets evaluate it now
* The reason to this, is because if we have a series of intermediate operations (the methods that mainly return
* streams), then we usually need something to pop the results out of the Stream pipeline (these are the terminal
* operations)
*
* The code below uses the Stream.filer() method - which we pass a lambda expression to fill in the Predicate - and
* this code will not run - rather it is ignored
*         Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                });
*
* Now running this code the Stream is now evaluated and we see the arguments of the lambda expression being outputted
*         Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                })
                .count();
*
* 
*
* */
