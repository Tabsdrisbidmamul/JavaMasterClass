package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(1);
        items.add(2);
        items.add(3);
//        items.add("text");
        items.add(4);
        items.add(5);

        printDouble(items);
    }

    private static void printDouble(ArrayList<Integer> n) {
        for (int i : n) {
            System.out.println( i * 2);
        }
    }
}

/*
* Generics
* We can create methods which can take parameters, and we can replace the parameters in the method
* Generics allow us to create classes, interfaces and methods that takes as parameters, called 'type parameters'
* which is different to the regular parameter
*
*         ArrayList items = new ArrayList();
        items.add(1);
        items.add(2);
        items.add(3);
        items.add("text");
        items.add(4);
        items.add(5);

        printDouble(items);
    }

    private static void printDouble(ArrayList n) {
        for (Object i : n) {
            System.out.println((Integer) i * 2);
        }
    }

* The code above demonstrates the use of using generics, here we have used ArrayList in its raw format (i.e not
* specified the type object to which it hold) and as such, we are able to add Integers, but at some point we added a
* String. Now the IDE is not throwing any errors, and it is till runtime where we get the exception that object type
* String can not be casted to String.
* This form of programming was the case in Java 1.5, but now with type checking and parameterised type which we can
* provide to a generic- it is recommended to use generics where possible - this code still work for backwards
* compatibility.
*
* To Declare Parameterised Type
* You use the angle brackets <> and place in the type of data of which this object will hold
*   ArrayList<Integer> = new ArrayList<Integer>();
*                       or
*   ArrayList<Integer> = new ArrayList<>();
* in the later builds of Java we can omit the second usage we have to type Integer as a type parameter to ArrayList
* to the constructor this was introduced in Java 1.6 - but for backwards compatibility it is best to write it as the
* top variant, but for ease of use we can omit it and replace it with a diamond '<>'
*
* Wildcard ?
* In Java there is generic wildcard e.g. ? extends E, ? super E. And there is unknown wildcard just ?.

Imagine you have the following class hierarchy:

public class A { }
public class B extends A { }
public class C extends A { }
There are three ways to define a collection (variable) using generic wildcards. These are:

List<?>           listUknown = new ArrayList<A>();
List<? extends A> listUknown = new ArrayList<A>();
List<? super   A> listUknown = new ArrayList<A>();
The Unknown Wildcard

List<?> means a list typed to an unknown type. This could be a List<A>, a List<B>, a List<String> etc.

Since the you do not know what type the List is typed to, you can only read from the collection, and you can only treat the objects read as being Object instances. Here is an example:

public void processElements(List<?> elements){
   for(Object o : elements){
      System.out.println(o);
   }
}
The processElements() method can now be called with any generic List as parameter. For instance a List<A>, a List<B>, List<C>, a List<String> etc. Here is a valid example:

List<A> listA = new ArrayList<A>();

processElements(listA);
Extends Wildcard boundary

List<? extends A> means a List of objects that are instances of the class A, or subclasses of A (e.g. B and C).

When you know that the instances in the collection are of instances of A or subclasses of A, it is safe to read the instances of the collection and cast them to A instances. Here is an example:

public void processElements(List<? extends A> elements){
   for(A a : elements){
      System.out.println(a.getValue());
   }
}
You can now call the processElements() method with either a List<A>, List<B> or List<C>. Hence, all of these examples are valid:

List<A> listA = new ArrayList<A>();
processElements(listA);

List<B> listB = new ArrayList<B>();
processElements(listB);

List<C> listC = new ArrayList<C>();
processElements(listC);
The processElements() method still cannot insert elements into the list, because you don't know if the list passed as parameter is typed to the class A, B or C.

Super Wildcard Boundary

List<? super A> means that the list is typed to either the A class, or a superclass of A.

When you know that the list is typed to either A, or a superclass of A, it is safe to insert instances of A or subclasses of A (e.g. B or C) into the list. Here is an example:

public static void insertElements(List<? super A> list){
    list.add(new A());
    list.add(new B());
    list.add(new C());
}
All of the elements inserted here are either A instances, or instances of A's superclass. Since both B and C extend A, if A had a superclass, B and C would also be instances of that superclass.

You can now call insertElements() with either a List<A>, or a List typed to a superclass of A. Thus, this example is now valid:

List<A>      listA      = new ArrayList<A>();
insertElements(listA);

List<Object> listObject = new ArrayList<Object>();
insertElements(listObject);

The insertElements() method cannot read from the list though, except if it casts the read objects to Object. The elements already present in the list when insertElements() is called could be of any type that is either an A or superclass of A, but it is not possible to know exactly which class it is. However, since any class eventually subclass Object you can read objects from the list if you cast them to Object.

More examples

The big difference between

public <T extends Animal> void takeThing(ArrayList<T> list)
and

public void takeThing(ArrayList<? extends Animal> list)
is that in the former method you can refer to "T" within the method as the concrete class that was given. In the second method you cannot do this.

Here a more complex example to illustrate this:
* // here i can return the concrete type that was passed in
public <T extends Animal> Map<T, String> getNamesMap(ArrayList<T> list) {
    Map<T, String> names = new HashMap<T, String>();
    for (T animal : list) {
        names.put(animal, animal.getName()); // i assume there is a getName method
    }
    return names;
}

// here i have to use general Animal
public Map<Animal, String> getNamesMap(ArrayList<? extends Animal> list) {
    Map<Animal, String> names = new HashMap<Animal, String>();
    for (Animal animal : list) {
        names.put(animal, animal.getName()); // i assume there is a getName method
    }
    return names;
}
*
* With the first method if you pass in an List of Cats you get a Map with Cat as key. The second method would always return a Map with general Animal key.

In short

If you write ? extends T you say "anything that is a T or more specific". For example: a List<Shape> can have only Shapes in it, while a List<? extends Shape> can have Shapes, Circles, Rectangles, etc.

If you write ? super T you say "anything that is a T or more general". This is less often used, but has it's use cases. A typical example would be a callback: if you want to pass a Rectangle back to a callback, you can use Callback<? super Rectangle>, since a Callback<Shape> will be able to handle Rectangles as well.

You can also look at ? extends T  as anything that extends T, or in case of ? extends Number , anything that extends Number e.g. BigDecimal, etc

To answer your question yes i use generics very often i wrote so many utility methods in my life with generics and without generics. When you work on big projects you will see real power of generics, writing utility methods etc, they save a lot of time.

Wildcards are most useful in method parameters. They allow for the necessary flexibility in method interfaces.

People are often confused when to use extends and when to use super bounds. The rule of thumb is the get-put principle. If you get something from a parametrized container, use extends.

int totalFuel(List<? extends Vehicle> list) {
    int total = 0;
    for(Vehicle v : list) {
        total += v.getFuel();
    }
    return total;
}

The method totalFuel gets Vehicles from the list, asks them about how much fuel they have, and computes the total.

If you put objects into a parametrized container, use super.

int totalValue(Valuer<? super Vehicle> valuer) {
    int total = 0;
    for(Vehicle v : vehicles) {
        total += valuer.evaluate(v);
    }
    return total;
}
The method totalValue puts Vehicles into the Valuer.

It's useful to know that extends bound is much more common than super.

One more tip: if you are intimidated by wildcards (which is natural in the beginning), try to write the explicitly parametrized version first. In typical usage the two versions are equivalent. Eventually, you'll figure out when you can get rid of type parameters and use wildcards.
*
* */
