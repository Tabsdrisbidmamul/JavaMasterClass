package com.example;

public class Main {

    public static void main(String[] args) {
        String varFour = "this is private to main()";

        ScopeCheck scopeInstance = new ScopeCheck();
        scopeInstance.useInner();

        System.out.println("scopeInstance varOne is " + scopeInstance.getVarOne());
        System.out.println(varFour);

        scopeInstance.timesTwo();
        System.out.println();
        ScopeCheck.InnerClass innerClass = scopeInstance.new InnerClass();
        innerClass.timesTwo();

    }
}
/*
* Scope
* refers to the access to which objects, methods and variables have within your code
* They can be separated into global and local scope
*
* Local - Refers to objects, variables and methods within a code block - (i.e they are only accessible within that
*         instance) so this can refer to variables within a method - which are known as local variables, meaning once
*         the method has finished executing its code, all variables and references are destroyed and can no longer be
*         used outside of the method
* Global - Refers to objects, variables and methods which inside the main code block and can be used anywhere within
*          the program
*
* We created an example of ScopeCheck class and created an instance of it, and also in main() we created a variable
* called privateVar (exact same name to the one in ScopeCheck), and when ran - Java knows which one to call - this is
* because within main() its scope is restricted to only the variables and objects within its code block - and
* therefore cannot see code within ScopeCheck class (with exceptions of access modifiers - that being private,
* protected and public) it can only see the variables, methods of which has an access modifier of public
*
* Another way of seeing scope is via code blocks, so for example we name a int variable within a method which has the
* same name as a member field to that class, Java is smart enough to know that we are referring to the variable
* which we wrote in the method and not the member variable.
*
* This is because this was wrapped within another code block, and if we were to remove the line where we declare the
* int variable within the method, Java will look for the variable in the current code block, if it is not there, then
* Java will go up one - i.e go the code block that wraps around the current one we are on, and does the search again
* till it either finds it or throws an error saying that variable x does not exist
*
* Visibility
* This is crucial in encapsulation OOP practices, within our ScopeCheck class we have defined public and private
* member fields, and an Inner Class as well with its own private member fields. Now the enclosing class and Inner
* class can see everything that each has to offer - meaning
* the enclosing class ScopeCheck can see all of InnerClass can see all of ScopeCheck attributes and methods as well
* regardless of its access modifier,  but ScopeCheck must use the fully qualified name - InnerClass.var to
* get access to the field or to use the method
*
* This is different for classes outside that scope, as they are restricted to the access modifier of that constituent
*
* */
