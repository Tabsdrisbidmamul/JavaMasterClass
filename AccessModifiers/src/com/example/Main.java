package com.example;

public class Main {

    public static void main(String[] args) {
        Account timsAccount = new Account("Tim");
        timsAccount.deposit(1000);
        timsAccount.withdraw(500);
        timsAccount.withdraw(-200);
        timsAccount.deposit(-20);
        timsAccount.calculateBalance();

        System.out.println("Balance on account is " + timsAccount.getBalance());
    }
}

/*
* Access modifiers
* There are four types: In Ascending order of visibility
*   - Public
*   - Protected
*   - Package
*   - Private
*
* Now top level objects - so Classes, Interfaces and enums - i.e when we create one of these - can only be
* public or package private
*
*   - public: the object is visible to all classes everywhere, whether they are in the same package or have imported
*             the package containing the public class
*
*   - package-private: the object is only available within its own package (and is visible to every class within the
*                      same package). Package-private is specified by not specifying the public keyword - so omit the
*                      public before the class keyword and the default is package-private (there is no
*                      package-private keyword)
*
* The same applies above for member fields - with the inclusion of private and protected (where protected is similar
* to package-private with the key difference is that it can be seen if it subclassed in a different package)
*
* */
