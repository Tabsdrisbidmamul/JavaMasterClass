package com.company;

public class Main {

    public static void main(String[] args) {
        ITelephone timsPhone;
        timsPhone = new DeskPhone(123456);

        timsPhone.powerOn();
        timsPhone.callPhone(123456);
        timsPhone.answer();

        timsPhone = new MobilePhone(24565);
        timsPhone.powerOn();
        timsPhone.callPhone(24565);
        timsPhone.answer();
    }
}

/*
* Interfaces
* Interfaces in Java allow us to define a set of generic methods for a set or a group of classes to implement from
* In the interface, you actually define the method signature: return type, name, parameters and their type - but not
* any code statements. This is because a class that implements it (so pretty much inherits, but not on the same
* level) will define their own unique variation of the methods written in the implementation
*
* To know what classes should implement an interface we ask ourselves "ClassName!" can "InterfaceName" so:
*   - a Dog can Walk
*   - a Bird can Walk, Fly
*   - ...
*
* NOTE: It is common practice to name interfaces with a uppercase "I" followed by its names e.g. IButtons, IEngine etc..
*
* To define an interface we do
*   public interface ITelephone {...}
*
* this tells Java that we are creating an interface of name ITelephone
*
* within the code block is where we write the method signatures so
*
* public interface ITelephone {
*   void powerOn();
*   void dial(int phoneNumber);
*   void answer();
*   boolean callPhone(int phoneNumber);
*   boolean isRinging();
*   }
* Here we have written the method names, its return type and its parameters and their types, see how we have not
* defined if they are public or private, this is because the class that implements the interface will be enforced to
* define these methods regardless. That means every class that implements an interface must define every method, they
* cannot pick or choose which one they like.
*
* To have a class that implements an interface we do
*
*   public class DeskPhone implements ITelephone {...}
* We write implements and the interface name along with it
*
* in the actual class code block itself, we still write our fields, constructors, getters and setters - but like
* mentioned above, we must write out the methods that were defined in the interface object e.g.
*   @Override
*   public void powerOn() {
*       System.out.println("No action taken, desk phone does not have a power button");
*   }
*
* Like said, we cannot pick and choose which methods we want, but we can write out very basic features which
* essentially limit what a method can do in different classes which implement the interface
*
* Another point is the data type declaration, it is best to use the Interface as the data type, and then call the
* constructor for class that implements the interface
*   ITelephone timsPhone;
*   timsPhone = new DeskPhone(123456);
*   timsPhone = new MobilePhone(24565);
*
* Here we define the reference variable timsPhone to be of type ITelephone, but later on the code we then call the
* constructor for DeskPhone and then MobilePhone - this is basically polymorphism  - because interfaces acts as the
* base class, and every other class that implements from the interface almost acts like a child to the class - but
* instead of child, more of a contractee to a contractor
*
* An issue that can occur is when you want to access the methods say in MobilePhone class or DeskPhone class, as we
* said its initial data type is ITelephone, we can't do
*   timsPhone.getMobilePhoneNumber();
*
* That won't work, as ITelephone does not have that method, instead - we have to have timsPhone to type of
* MobilePhone to get access to that method
*   (MobilePhone) timsPhone.getMobilePhoneNumber();
*
* And this will work as it would
*
* */
