package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {
//        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
//            @Override
//            public boolean accept(Path path) throws IOException {
//                return Files.isRegularFile(path);
//            }
//        };

        DirectoryStream.Filter<Path> filter = p -> Files.isRegularFile(p);

        Path directory = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir2");
//        Path directory = FileSystems.getDefault().getPath("FileTree\\Dir2");
        try(DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)) {
            for(Path file: contents) {
                System.out.println(file);
            }
        } catch (IOException | DirectoryIteratorException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


        String separator = File.separator;
        System.out.println(separator);
        separator = FileSystems.getDefault().getSeparator();
        System.out.println(separator);

        try {
            Path tempFile = Files.createTempFile("myapp", ".appext");
            System.out.println(tempFile.toAbsolutePath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for (FileStore store: stores) {
            System.out.println("Volume name/ Drive letter: " + store);
            System.out.println("Volume name: " + store.name());
        }

        Iterable<Path> rootPath = FileSystems.getDefault().getRootDirectories();
        for (Path path: rootPath) {
            System.out.println(path);
        }

        System.out.println("---Walking Tree for Dir2---");
        Path dir2Path = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir2");
        try {
            Files.walkFileTree(dir2Path, new PrintNames());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

//        System.out.println("---Copy Dir2 to Dir4\\Dir2Copy---");
//        Path copyPath = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir4" + File.separator +
//                "Dir2Copy");
//        try {
//            Files.walkFileTree(dir2Path, new CopyFiles(dir2Path, copyPath));
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
        File file = new File("\\Examples\\file.txt");
        Path convertedPath = file.toPath();
        System.out.println("Converted Path: " + convertedPath);

        File parent = new File("\\Examples");
        File resolvedFile = new File(parent, "dir\\file.txt");
        System.out.println(resolvedFile.toPath());

        resolvedFile = new File("\\Examples", "\\dir4\\file.txt");
        System.out.println(resolvedFile.toPath());

        Path parentPath = Paths.get("\\Examples");
        Path childRelativePath = Paths.get("dir\\file.txt");
        System.out.println(parentPath.resolve(childRelativePath));

        File workingDirectory = new File("").getAbsoluteFile();
        System.out.println("Working Directory: " + workingDirectory.getAbsolutePath());

        System.out.println("---printing out Dir2 contents---");
        File dir2File = new File(workingDirectory, "\\FileTree\\Dir2");
        String[] dir2Contents = dir2File.list();
        for (String s: dir2Contents) {
            System.out.println(s);
        }

        System.out.println("---printing Dir2 contents using listFiles()---");
        File[] dir2Files = dir2File.listFiles();
        for (File s: dir2Files) {
            System.out.println(s.getName());
        }
    }
}

/*
* DirectoryStream (Interface)
* We use a DirectoryStream to see the contents of a particular Path given
* The DirectoryStream when declared expects another object to be passed as a parametrised type - so for generics to
* limit down what can be seen or done.
*
* Here we pass the object Path - in this case we want to see what available paths exist within this directory (Dir2)
* We also use the constructor Files.newDirectoryStream - passing in the file path we want to look at, and the
* DirectoryStream is an Iterable - meaning it will return a List of Paths - so we can iterate through it to output
* the paths that are in Dir2
*
* Now we also want to catch IOExceptions and DirectoryIteratorException as well - as these can commonly occur when
* dealing with this interface
*
*   Path directory = FileSystems.getDefault().getPath("FileTree\\Dir2");
*   try(DirectoryStream<Path> contents = Files.newDirectoryStream(directory)) {
*       for(Path file: contents) {
*           System.out.println(file);
*        }
*   } catch (IOException | DirectoryIteratorException e) {
*       System.out.println(e.getMessage());
*       e.printStackTrace();
*   }
*
*
* Now the constructor newDirectoryStream also accepts a 2nd argument - which can be almost seen as regex but with a
* few quirks to it - and this is known as a "glob"
*
* example (glob)
* *: matches any string (which can contain any number of characters)
*   - *.dat: will match any path with the .dat extension
*   - *.{dat, txt}: will match any path that has the extension .dat or .txt
*
* ?: matches exactly one character
*   - ???: would match any path that contains exactly 3 characters
*
* more examples
*   - myfile*: matches any paths that being with myfile
*   - b?*.txt: matches any paths that are least 2 characters long and begin with the character b (the ? forces a
*              second character, and the * matches 0 or more characters)
*
* If the glob isn't enough you can always pass a regex
*
* More at https://docs.oracle.com/javase/8/docs/api/java/nio/file/FileSystem.html#getPathMatcher-java.lang.String-
*
* So if we pass the String "*.dat" as the 2nd param we will only get file3.dat in the console
*
*   DirectoryStream<Path> contents = Files.newDirectoryStream(directory, "*.dat")
*
* Now there is another param you can, which is the filter, this uses the DirectoryStream.Filter<Obj> - what we are
* doing is overriding a method in this Interface - which only has one method accept - and what we want to do is place
* our filter criteria within the return - which will return true or false depending on whatever test you want - so in
* this case we are testing if the file is a regular file
*
*   DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
*       @Override
*       public boolean accept(Path path) throws IOException {
*               return Files.isRegularFile(path);
*           }
*       };
*
* We than pass the filter to the directory constructor
*   DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)
*
* Separator
* Now in MS-DOS the separator to separate nodes from one another in a path is backslash (\) and on UNIX it is forward
*  slash (/) now in the recent examples we have hardcoded in the file separator - but in the real world we would
* rather have Java find out the system default separator and use that instead
*
* File.separator and FileSystems.getDefault().getSeparator() will return the String representation of the separator -
*  we can then like we normally would do this to a Path -> "FileTree" + File.separator + "Dir2"
*
*   String separator = File.separator;
*   System.out.println(separator);
*   separator = FileSystems.getDefault().getSeparator();
*   System.out.println(separator);
*
* Temp Files
* We can create temporary files with the Files.createTempFile() method.
* The method accepts 3 params:
* 1st param: the prefix - this String is prepended to the file name - the OS will generate a series of alphanumeric
*            characters after the prefix - to keep it unique
* 2nd param: The suffix - this is basically the file extension
* 3rd param: File attributes, you can specify file attributes to the temp file - but usually you would not do that -
*            simply because it is a temp file
*
* On a Windows machine the temp file is saved at this location:
* C:\Users\ricep\AppData\Local\Temp\myapp9353871142335414030.appext
*
*   try {
*       Path tempFile = Files.createTempFile("myapp", ".appext");
*       System.out.println(tempFile.toAbsolutePath());
*   } catch (IOException e) {
*       System.out.println(e.getMessage());
*       e.printStackTrace();
*   }
*
*
* FileStore
* This is basically all the drives on the machine - and is system specific so on Windows we will get the drive letter
* and their volume name on Linux we will get all connected devices etc.
*
*    Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
*       for (FileStore store: stores) {
*           System.out.println(store);
*           System.out.println(store.name());
*       }
*
* Root Paths
* This is basically the the volume label/ letter for Windows and for Unix it wil most likely be (/)
*
*   Iterable<Path> rootPath = FileSystems.getDefault().getRootDirectories();
*       for (Path path: rootPath) {
*           System.out.println(path);
*       }
*
*
* Walking the tree
* We start from a root node being the parent directory (a folder) and we basically visit every file that is a child
* of the root node, but we also walk through sub folders (sub root nodes) and walk through their children as well
* (basically grand children of the root node)
*
* We use the FileVisitor interface to walk the tree - using this interface we can use methods within it at each stage
* of the traversal process
* Here are the methods we want to implement
*   - preVisitDirectory(): this method accepts a reference to the directory, and the BasicFileAttributes instance for
*                          the directory. It is called before the entries in the directory are visited
*   - postVisitDirectory(): this method accepts a reference to the directory and an exception object (when necessary).
*                           It is called after entries in the directory, and all its descendants have been visited.
*                           The exception parameter will be set when an exception happens during the traversal of the
*                           entries and descendants.
*
* There is a way to skip files as you are traversing, so not every file has to have been visited for this method to
* be called. Every file has to be visited or explicitly skipped. Of course if an exception is thrown and not handled,
* the traversal will prematurely end and postVisitDirectory() will be called
*
*   - visitFile(): The method we care the most. This method accepts a reference to the file and a BasicFileAttributes
*                  instance. This is where you the code that will operate on the file. It is only called for files.
*   - visitFileFailed(): This is called when a file cannot be accessed. The exception that is thrown is passed to the
*                        method. You can then decide what to with it (throw it, print it, or anything else that makes
*                        sense for the application and operation being performed). Can be called for files and
*                        directories.
*
* Now in the JDK there is a class called SimpleFileVisitor with just the methods you want to implement
*
* Now we use the preVisit for copying directories and files
* the postVisit for deleting files or directories
*
* Think of them as pre/ post traversal of a graph
*
* Copying Files
* We are going to copy FileTree\Dir2\ <- all of its contents to
* FileTree\Dir4\Dir2Copy\
*
* Now we need to understand the root source which is to say the root node where say a given file is in within a sub
* root node - so in this case we have file1.txt which is in Dir3 which is in Dir2 which also is in FileTree - we are
* going to say that FileTree\Dir2 is the root node of Dir3 - which it is we have to travel 2 level to get to it
*
* in pseudocode it will look like this
* sourcePath = "FileTree\Dir2\Dir3\file1.txt";
* sourceRoot = "FileTree\Dir2";
* targetRoot = "FileTree\Dir4\Dir2Copy";
* relativizedPath = sourceRoot.relativize(sourcePath); which is "Dir3\file.txt"
* resolvedPathForCopy = targetRoot.resolve(relativizedPath); which is "FileTree\Dir4\Dir2Copy\Dir3\file.txt"
*
* resolve()
* this is a method in Path - and what it will do is basically concatenate 2 paths together
*
* relativize()
* This is a method in PAth - and what it will do is basically cut off the root node from the path leaving only the
* relative path
*
* ex. FileTree\Dir1\file1\.txt
*     --------------
*       Root Node
*
*       relativize()
*
*           |
*
*        file1.txt
*
*
*   System.out.println("---Copy Dir2 to Dir4\\Dir2Copy---");
*   Path copyPath = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir4" + File.separator +
*           "Dir2Copy");
*   try {
*           Files.walkFileTree(dir2Path, new CopyFiles(dir2Path, copyPath));
*       } catch (IOException e) {
*           System.out.println(e.getMessage());
*       }
*
* We made a class called CopyFiles which contains visitFile, preVisitDirectory and visitFileFailed overridden methods
* now for preVisitDirectory we made the code so that for Dir3 within Dir2 - we told it to copy the directories - the
* method walkFileTree will actually then go into Dir3 and copy the files to Dir3 copy
*
* The next method visitFile will be copying files - so leaf nodes of root node - so that's all the files in Dir2 and
* Dir3, this structure is remembered and put into our newly created Dir4\Dir2Copy which created when instantiating
* CopyFile class and passed it to walkFileTree.
*
* Mapping Java.io methods to Java.nio
* Many old examples of code and on the web used java.io - so there needs to be methods which basically translate the
* java.io methods and its result to java.nio compatible results
*
* All File objects have the toPath() method - which will convert the old File path to a nio.Path
*
*       File file = new File("\\Examples\\file.txt");
        Path convertedPath = file.toPath();
        System.out.println("Converted Path: " + convertedPath);

        File parent = new File("\\Examples");
        File resolvedFile = new File(parent, "dir\\file.txt");
        System.out.println(resolvedFile.toPath());

        resolvedFile = new File("\\Examples", "\\dir4\\file.txt");
        System.out.println(resolvedFile.toPath());
*
*
* The equivalent methods of nio move(), delete() and createFile()
* move() -> renameTo()
* delete() -> delete()
* createFile() -> createNewFile()
*
*
* In java nio we used the FileSystems.getDefault() -> which returns a path of the working directory to achieve the
* same in java.io we do this
*
*   File workingDirectory = new File("").getAbsoluteFile();
*   System.out.println("Working Directory: " + workingDirectory.getAbsolutePath());
*
* when you pass an empty string to the File constructor it translate it to the working directory
*
*
* Now in nio we had methods (DirectoryStream<>) which returns a list of Objects which we can iterate through to see
* the contents of a directory we can also achieve this in java.io using 2 methods lis() and listFiles()
*
* list()
* This will return a list of Strings of the names of the files and Directories within that given path - and this of
* course like DirectoryStream will not go any levels deeper into sub folders - we will need to walk the tree for that
*       System.out.println("---printing out Dir2 contents---");
        File dir2File = new File(workingDirectory, "\\FileTree\\Dir2");
        String[] dir2Contents = dir2File.list();
        for (String s: dir2Contents) {
            System.out.println(s);
        }
*
* listFiles()
* This method will return a list of File objects instead - and again it will stick to the first level of entry and
* not go any deeper into other directory levels
*       System.out.println("---printing Dir2 contents using listFiles()---");
        File[] dir2Files = dir2File.listFiles();
        for (File s: dir2Files) {
            System.out.println(s.getName());
        }
*
*
* NOTE: to take away from this section - Java.NIO is the best to work with file systems - so Paths waking the tree etc.
* But Java.io is easier to use when working with streams when compared to channels
*
*
* */