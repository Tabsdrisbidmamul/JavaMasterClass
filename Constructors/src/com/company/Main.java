package com.company;

public class Main {

    public static void main(String[] args) {
        // Create a new class for a bank account
        // Create fields for the account number, balance, customer name, email and phone number.
        //
        // Create getters and setters for each field
        // Create two additional methods
        // 1. To allow the customer to deposit funds (this should increment the balance field).
        // 2. To allow the customer to withdraw funds. This should deduct from the balance field,
        // but not allow the withdrawal to complete if their are insufficient funds.
        // You will want to create various code in the Main class (the one created by IntelliJ) to
        // confirm your code is working.
        // Add some System.out.println's in the two methods above as well.

        BankAccount bankAccount = new BankAccount(); //"0125256", 100000.0, "Idris", "email@email.com", "+010 2568 4568"
        bankAccount.withdraw(10.0);
        System.out.println("Account Number: " + bankAccount.getAccountNumber());
        System.out.println("Customer Name: " + bankAccount.getCustomerName());
        System.out.println("Email: " + bankAccount.getEmail());
        System.out.println("Phone Number: " + bankAccount.getPhoneNumber());
        System.out.println("Current Balance: " + bankAccount.getBalance());
        bankAccount.deposit(12500.0);
        bankAccount.withdraw(60000.0);
        System.out.println("Current Balance: " + bankAccount.getBalance());

        BankAccount idrisAccount = new BankAccount("Idris", "Idris@email.com",
                "+44 123-456");
        System.out.println(idrisAccount.getAccountNumber() + " name " + idrisAccount.getCustomerName());

        // Create a new class VipCustomer
        // it should have 3 fields name, credit limit, and email address.
        // create 3 constructors
        // 1st constructor empty should call the constructor with 3 parameters with default values
        // 2nd constructor should pass on the 2 values it receives and add a default value for the 3rd
        // 3rd constructor should save all fields.
        // create getters only for this using code generation of intellij as setters wont be needed
        // test and confirm it works.

        VipCustomer defaultCustomer = new VipCustomer();
        System.out.println(defaultCustomer.getName() + " has a credit limit of " + defaultCustomer.getCreditLimit() + 
                " their email address is " + defaultCustomer.getEmailAddress());
        
        VipCustomer phil = new VipCustomer("Phil" , 100.0);
        System.out.println(phil.getName() + " has a credit limit of " + phil.getCreditLimit() +
                " their email address is " + phil.getEmailAddress());
        
        VipCustomer richie = new VipCustomer("Richie Rich", 1000.0, "Rich@rich.com");
        System.out.println(richie.getName() + " has a credit limit of " + richie.getCreditLimit() +
                " their email address is " + richie.getEmailAddress());
    }
}

/*
* Constructor
* The constructor's role in Java is to create objects, either by passing in the arguments or not (when creating an
* object, and if no constructor was made - Java will automatically create an empty constructor)
*
* to make a constructor, it goes like this:
*   public ClassName(paramList) {
*       this.field = paramList[value1];
*       ...
*   }
*
* the constructor's should mainly always be public, and they can be overloaded to have different paramList for each
* case scenario
*
* Another thing we can do, is called overloaded constructors within another, so for example with BankAccount we call
* the constructor with a full parameter list within the empty constructor, we can do this we the 'this()' keyword,
* and pass in default values to be constructed when the object is created
*
* ---------------------------------------------------------------------------------------------------------------------
* This
* As mentioned before, we use the keyword with OOP
*
* constructors
* in this case, we use 'this' as if we are calling a method - in fact a constructor - by doing so, we tell Java to
* look in the Class definition and look at the constructor lists we have written, and create an object based on the
* parameter list we pass in as parameters to the constructor.
*
* This is a very convenient trick, as we reducing the amount of code we have to type, and it can be used off the fly
* - so we might want to create an constructor which accepted 7 parameters, we have a constructors that has an
* parameter list of 6 (the first 6 parameters are the same) so we can do a
*   this(paramList);
*   this.field = paramName;
*
* that's save us a lot of typing an makes reading a lot easier
* NOTE: this() keyword in the constructor must be the first line, as when Java comes to make the object with its
*       attributes, they must be in order
*
*
* */
