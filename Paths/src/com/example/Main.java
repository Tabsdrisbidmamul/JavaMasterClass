package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {

    public static void main(String[] args) {
        try {
            Path filePath = FileSystems.getDefault().getPath("Examples", "Dir1", "file1.txt");
            long size = Files.size(filePath);
            System.out.println("File size: " + size);
            System.out.println("Last modified: " + Files.getLastModifiedTime(filePath));

            BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
            System.out.println("Size: " + attr.size());
            System.out.println("Last modified: " + attr.lastAccessTime());
            System.out.println("Created: " + attr.creationTime());
            System.out.println("Is directory: " + attr.isDirectory());
            System.out.println("Is regular file: " + attr.isRegularFile());

//            Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir2\\Dir3\\Dir4\\Dir5\\Dir6\\Dir7");
//            Files.createDirectories(dirToCreate);

//            Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir4");
//            Files.createDirectory(dirToCreate);

//            Path fileToMake = FileSystems.getDefault().getPath("Examples", "file2.txt");
//            Files.createFile(fileToMake);

//            Path fileToDelete = FileSystems.getDefault().getPath("Examples", "Dir1", "file1copy.txt");
//            Files.deleteIfExists(fileToDelete);

//            Path fileToRename = FileSystems.getDefault().getPath("Examples", "file1.txt");
//            Path destination = FileSystems.getDefault().getPath("Examples", "file1.txt");
//            Files.move(fileToRename, destination);

//            Path fileToMove = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
//            Path destination = FileSystems.getDefault().getPath("Examples", "Dir1" , "file1copy.txt");
//            Files.move(fileToMove, destination);

//            Path sourceFile = FileSystems.getDefault().getPath("Examples", "file1.txt");
//            Path copyFile = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
//            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
//
//            sourceFile = FileSystems.getDefault().getPath("Examples", "Dir1");
//            copyFile = FileSystems.getDefault().getPath("Examples", "Dir4");
//            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


//        Path path = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt");
//        printFile(path);
//
//        Path filePath = FileSystems.getDefault().getPath("files\\SubdirectoryFile.txt");
//        printFile(filePath);
//
////        filePath = FileSystems.getDefault().getPath("files", "SubdirectoryFile.txt");
//        filePath = Paths.get(".", "files", "SubdirectoryFile.txt");
//        printFile(filePath);
//
//        Path randomPath = Paths.get("C:\\Users\\ricep\\Desktop\\JavaMasterClass\\OutThere.txt");
////        randomPath = Paths.get("C:\\" , "Users", "ricep", "Desktop", "JavaMasterClass", "OutThere.txt");
//        printFile(randomPath);
//
//        randomPath = FileSystems.getDefault().getPath("..\\OutThere.txt");
//        printFile(randomPath);
//
//        filePath = Paths.get(".");
//        System.out.println(filePath.toAbsolutePath());
//
//        Path path2 = FileSystems.getDefault().getPath(".", "files", "..", "files", "SubdirectoryFile.txt");
//        System.out.println(path2.normalize().toAbsolutePath());
//        printFile(path2.normalize());
//
//        Path path3 = FileSystems.getDefault().getPath("thisfiledoesntexist.txt");
//        System.out.println(path3.toAbsolutePath());
//
//        Path path4 = Paths.get("C:\\Test\\ING\\abcdef\\whatever.txt");
//        System.out.println(path4.toAbsolutePath());
//
//        filePath = FileSystems.getDefault().getPath("files");
//        System.out.println("Exists: " + Files.exists(filePath));
//        System.out.println("Exists: " + Files.exists(path4));


    }

    private static void printFile(Path path) {
        try (BufferedReader fileReader = Files.newBufferedReader(path)){
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

/*
* Paths
* In JavaNIO there are 2 classes with similar names Path and Paths
* Path is an interface which outlines the basic methods a class that implements Path to have
* Paths is a class with only 1 method get() which will retrieve a file path when a String literal is passed
*
* There are 2 types of paths
*   - Relative Path: Where the root node is not specified
*   - Absolute Path: The root node is specified
*
* Root Node: This can be seen as the very top most level node where all directories derive from so on Windows machine
* that is usually C:\
*
* Every Directory and File can be seen as a node within a graph of directories so C:\Users\Desktop\File.txt
* So Users, Desktop and File.txt all can be seen as nodes within this graph and on Window machines they are usually
* separated by a delimiter (backslash "\" on Mac and Linux its forward slash "/")
*
* This is necessary as to differentiate nodes from one another but also within a directory node no 2 children nodes
* can have the same name within that parent node - so we cannot have 2 Files.txt files within Desktop directory as
* the names are conflicting with one another
*
* Relative Path
* Here we use the getDefault() method in FileSystems - this will essentially get the current Working Directory - that
*  being the root node for this project - and this allows us to relatively access the text file WorkingDirectoryFile
* .txt as we have used a method to notify Java that this is in the WorkingDirectory
*   Path path = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt");
*
* Relative Path accessing sub folder
* In this example we are accessing a sub folder within the parent directory, we have to use double backslash to
* access the file - this is because in Strings java will parse the \ for ASCII characters starting from 0-31 so the
* double is used to indicate this is for Paths
*   Path filePath = FileSystems.getDefault().getPath("files\\SubdirectoryFile.txt");
*
* We also can do it like this:
*   filePath = FileSystems.getDefault().getPath("files", "SubdirectoryFile.txt");
*
* The getPath() method actually accepts an unlimited amount of params to its method call (in the source code its
* params are: String first, String... more -> the ... basically means to accept a List of arguments.
*
* What we have done is basically done exactly like the String literal for SubdirectoryFile.txt but instead passed
* each node as a param to the method
*
* Absolute Path
* With an absolute path - the file location must be exact - a mistake will result in a
* NoSuchFIleFoundException. We must also use Paths -> this is different from Path
* Paths.get() method to retrieve an absolute path
*
*   Path randomPath = Paths.get("C:\\Users\\ricep\\Desktop\\JavaMasterClass\\OutThere.txt");
*
* The get() method also allows you to enter each node of a directory or file as an argument - so we can do:
*   randomPath = Paths.get("C:\\" , "Users", "ricep", "Desktop", "JavaMasterClass", "OutThere.txt");
*
* The same can be achieved like this
*   randomPath = FileSystems.getDefault().getPath("..\\OutThere.txt");
*
* As we know the OutThere.txt is one level up the current project - we pass .. meaning go up one and you will find
* the text file there
*
* .. : go up one level of the current working directory
* . : access the current working directory
*
*
* normalise()
* So in this example we made a variable path2 which contains a path with a mix of dots(.) and double dots(..) now
* this will still get us the directory graph ./files/SubdirectoryFile.txt - but as we have done quite a few mixes
* here when we come to print out the absolute path we will get a funny mix of dots and double dots
*
* The method normalise() does is remove those dots from the print and return the roots name instead like so
*   Path path2 = FileSystems.getDefault().getPath(".", "files", "..", "files", "SubdirectoryFile.txt");
*   System.out.println(path2.normalize().toAbsolutePath());
*
* From the sout
*   C:\Users\ricep\Desktop\JavaMasterClass\Paths\files\SubdirectoryFile.txt
*
* Problems with File
* In Java 7 there were a list of problems with the File class methods - simply there were not enough error checking
* and platform dependence was becoming a thing - so to mitigate this NIO.File.Files mainly fixed the cocktails of
* problems
*
* symbolic link: effectively they are shortcuts to many things - applications, network locations etc.
*
* Example - Paths that don't exist
* This bit of code will run and print out the path - there is no exception thrown about paths that don't exist - it
* is only when you come to use that path in an instance of a reader, writer etc. is when it wil break
* So it is best to check if the path exists first before creating it
*
* so doing a LBYL or EAFP approach can mitigate either of them
*
*   Path path3 = FileSystems.getDefault().getPath("thisfiledoesntexist.txt");
*   System.out.println(path3.toAbsolutePath());
*
*   Path path4 = Paths.get("C:\\Test\\ING\\abcdef\\whatever.txt");
*   System.out.println(path4.toAbsolutePath());
*
* Using the method exists in the Files class will return a boolean value whether or not the path does actually exist
* on the System
*   Files.exists(filePath)
*
* But of course even if the file does exist - you may not have permission to use it, there 3 methods
*   - Files.isWritable()
*   - Files.isReadable()
*   - Files.isExecutable()
*
* Which all check the various permissions a file has
*
* Copying a file
* We create 2 paths, one is the source file, and the other is the destination file - in this it does not exists yet
* anyway because we are going to create it - we use the Files.copy() method - passing in source path and destination
* Path and this will create a copy of the file
*   try {
*       Path sourceFile = FileSystems.getDefault().getPath("Examples", "file1.txt");
*       Path copyFile = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
*       Files.copy(sourceFile, copyFile);
*   } catch (IOException e) {
*       System.out.println(e.getMessage());
*       e.printStackTrace();
*   }
*
* if we run the code again - we will get an error as the file already exists - if we do this, by giving the 3rd param
* which says replace any existing file
*   Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
*
* You can also copy directories as well
* However when we run this code, we get an empty directory - and we don't actually get the contents of Dir1 within
* Dir4, this behaviour is the default behaviour when copying directories - and to get the all the contents from one
* directory to another we need to do something called "Walking the tree" - where we recursively go through the
* directory node and get all the files
*   sourceFile = FileSystems.getDefault().getPath("Examples", "Dir1");
*   copyFile = FileSystems.getDefault().getPath("Examples", "Dir4");
*   Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
*
* Moving a file
* To move a file, its the same layout as above - but we need to make sure that the destination also has the same
* fiename we want to move into said place
* Again the move() method has a 3rd param which allows us to specify different options to replace the file etc.
*   StandardCopyOption.REPLACE_EXISTING, etc...
*
*   Path fileToMove = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
*   Path destination = FileSystems.getDefault().getPath("Examples", "Dir1" , "file1copy.txt");
*   Files.move(fileToMove, destination);
*
* Renaming a file
* It uses the same method move() - because we are actually moving the file
*   Path fileToRename = FileSystems.getDefault().getPath("Examples", "file1.txt");
*   Path destination = FileSystems.getDefault().getPath("Examples", "file1.txt");
*   Files.move(fileToRename, destination);
*
*
* Deleting a file
* You pass in the path, and call the method delete() and pass in the path - of course if the file does not exist an
* Exception will be raised - to avoid that we can use the method deleteIfExists() and this will do LBYL check to make
*  sure the file actually exists first before deleting it
*   Path fileToDelete = FileSystems.getDefault().getPath("Examples", "Dir1", "file1copy.txt");
*   Files.delete(fileToDelete);
*
* Creating a File
* Usually when creating a file - you usually want to add contents to that file - suing a stream or channel - but in
* NIO.Files there is a method to create an empty file
*
*   Path fileToMake = FileSystems.getDefault().getPath("Examples", "file2.txt");
*   Files.createFile(fileToMake);
*
* Creating a directory
*   Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir4");
*   Files.createDirectory(dirToCreate);
*
* Creating multiple directories
* This will create directories, and if they don;t exist it will add them as well
*   Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir2\\Dir3\\Dir4\\Dir5\\Dir6\\Dir7");
*   Files.createDirectories(dirToCreate);
*
* File attributes or metadata
* Metadata or file attributes are information that describe the file - so its creation time, last modified time, its
* size, is it a file etc....
*
* We can use the Files specific methods to retrieve metadata about a file like so
*
*   System.out.println("File size: " + Files.size(filePath));
*   System.out.println("Last modified: " + Files.getLastModifiedTime(filePath));
*
* We can also get all the attributes in one shot using the BasicFileAttribute interface, and we the use the method
* found in Files called readAttributes(), which we pass in the file path, and BasicFileAttributes.class - the last
* one is important or we will not be able to get access to the BasicFileAttribute methods - think of it as a link
* between the interface and the methods
*
*   BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
*   System.out.println("Size: " + attr.size());
*   System.out.println("Last modified: " + attr.lastAccessTime());
*   System.out.println("Created: " + attr.creationTime());
*   System.out.println("Is directory: " + attr.isDirectory());
*   System.out.println("Is regular file: " + attr.isRegularFile());
*
*
*
* */
