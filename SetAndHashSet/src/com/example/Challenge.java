package com.example;

public class Challenge {
    /**
     * When overriding the equals() method in the HeavenlyBody class, we
     * were careful to make sure that it would not return true if a HeavenlyBody
     * was compared to a subclass of itself.
     *
     * We did that to demonstrate that method, but it was actually
     * unnecessary in the HeavenlyBody class.
     *
     * The mini challenge is just a question: why was it unnecessary?
     *
     * Hint: If you are stuck, check out Lecture 97
     *
     * Answer:
     *
     * The HeavenlyBody class is declared final, so cannot be subclassed.
     * The Java String class is also final, which is why it can safely
     * use the instanceof method without having to worry about
     * comparisons with a subclass.
     *
     * Because of polymorphism - as classes that inherit from a base class - it then becomes of that type, so when
     * passing in a subclass of that type to the equals() method, Java will automatically cast it to the base class,
     * the reason we did a check was to make sure we were only dealing with base classes and not subclasses. The
     * reason it is unnecessary is because we will be returning false anyway if a subclass type is compared to a base
     * class type - because in essence they are different objects
     *
     * Answer: HeavenlyBody class is declared final - meaning that it cannot be subclassed - thus it is safe to do
     * the comparison check ups
     *
     **/
}
