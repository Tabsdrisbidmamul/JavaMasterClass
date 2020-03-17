package org.exmaple.mypackage;


import org.w3c.dom.Node;

public class Main {

    public static void main(String[] args) {
	    Node node = null;

    }
}

/*
* Packages
* We can think of it, as a way of grouping related Classes and Interfaces together
*
* The actual package mechanism provides a way to manage the name space of object types and it also extends the access
* protection - beyond the traditional access modifiers we've seen
*
* Namespace can be seen as effectively a different area, so each package has its own area to work with, and this
* means that classes and interfaces in packages are part of that namespace - this avoids naming conflicts
*
* Classes within packages have access to one another, and classes outside the packages are restricted to the access
* modifiers given to them
*
* We cannot say use a class called Node - and then try to import 2 packages which implement the Node Class/ Interface
*   import org.w3c.dom.Node;
*   import javafx.scene.Node; <-- This will throw an error as we have imported a package that contains a class Node
*
* So the only way to get around this is to import the first Node class from org.w3c.dom.Node;
* and in the main method()
*   Node node = null;   <-- This Node class refers to w3c Node
*   javafx.scene.Node anotherNode = null; <-- Here we specifically referring to javafx Node this is known as the
*   fully qualified name; but using this method means we have not import javafx at all, instead if we want to use
*   this at all - we have to write 'javafx.scene.Node' each time to get the Node from that class
*
* package com.company;
* This line at the top tells Java that this class/ interface belongs to this package, now a package can almost be
* seen as a folder directory
* com
* |
* |___company
*     |
*     |__Main.java
*
* Renaming packages in Intellij is as simply right clicking it, refactor and rename - and this will create a whole
* new folder structure for the project, whilst also retaining the old package as well
*
* Creating your own packages
* This process is done using IntelliJ GUI to do this
* If you want your code to be auto runnable - leave the Main.java in the package - and this will run, if not - delete
* the Main.java - as this will not be needed
*
* We create a new project (give it a meaningful name) and the package structure -> com.example/company.[name]
* Place the classes/ interfaces in the package
*
* Now go to File > project structure > Artifacts
* Press the + symbol, and click 'from modules with dependencies'
* There it will state the module (project name with all the packages and code) and this is optional if you want to
* include the Main class, you can - and this will make it a auto runnable code - but usually you won't do that.
* Click ok, and the .jar file has been created - they are basically .zip files for Java
* The .jar file is usually found in the out\Artifacts\ProjectName_jar\ProjectName.jar
* Then in that same project go to Build > Build Project
* Once that has processed at the bottom left hand corner
* go to Build > Build Artifacts > click Java
* Once that has processed, your jar file is ready to be used
*
* To add modules into different namespaces - so different projects
* Go to File > Project Structure > Libraries
* Press the + symbol and click Java
* Locate where the .jar file is, and click ok, and click ok to have it added to the current project, now you can use
* code from other projects within another
* */

