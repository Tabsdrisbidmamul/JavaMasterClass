package com.example;

// Challenge:
// In the following interface declaration, what is the visibility of:
//
// 1. The Accessible Interface? Package-private
// 2. the int variable SOME_CONSTANT? public static final
// 3. methodA? public abstract
// 4. methodB and methodC? public abstract

interface Accessible {
    int SOME_CONSTANT = 100;
    public void methodA();
    void methodB();
    boolean methodC();
}
