package com.example;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();
    private static RandomAccessFile ra;

    /*NOTE: locations_random.dat file is malformed, no matter what the code you use, the file is always malformed*/

    public static void main(String[] args) throws IOException {
        try(RandomAccessFile rao = new RandomAccessFile("locations_random.dat", "rwd")) {
            // Write int (4 bytes) this is the number of locations from our locations map
            rao.writeInt(locations.size());

            // This again will be an int (4 bytes) to contain the start offset for a location
            // we get the number locations and multiply it by 3 lots of 4 (so 3 Integers) and and that's because we
            // want it to contain: The location ID, the offset for the location and the size or length of the
            // location record
            //
            // We are calculating the indexSize by calculating the number of locations, by the number of ints
            // contained within in each record.
            //
            // On the next line we calculate the current position of the file pointer to the index size to account
            // for the values that we have already written to the file, we also have to account for the integer we
            // are about to write to the file the location section offset, we just calculated so we also have to add
            // the number of bytes to an integer
            //
            // this will give us the offset for the location section
            // We have to cast to an int - because the file pointer is a long value
            int indexSize = locations.size() * 3 * Integer.BYTES; // -> Integer.BYTES = 4
            int locationStart = (int) (indexSize + rao.getFilePointer() + Integer.BYTES);
            rao.writeInt(locationStart);

            // this will contain the file pointer at the time when we wrote the location offsets - this is needed
            // because we are going to plug in the indexes after we have populated the file with locations - you
            // can't make an index (ID) without first having data to base it on
            long indexStart = rao.getFilePointer();

            // startPointer gets the offset from the previous write we did at rao.writeInt(locationStart);
            // we do this because we need to know length of the record - so we are effectively doing the
            // file pointer(end of record) - start(start of record) as the file pointer will be ahead of start always
            //
            // we then use the seek method to move the file pointer to the first location's offset, we only have to
            // do this once, because after that we are going to write all the data sequentially
            int startPointer = locationStart;
            rao.seek(startPointer);

            for(Location location : locations.values()) {
                // for each location we are writing the location ID and its description and then also its exits for
                // each location
                rao.writeInt(location.getLocationID());
                rao.writeUTF(location.getDescription());

                StringBuilder builder = new StringBuilder();
                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        builder.append(direction);
                        builder.append(",");
                        builder.append(location.getExits().get(direction));
                        builder.append(",");
                        // What we are wilting here is direction, location ID...
                        //N,1,U,2,....
                    }

                }
                // We write the exits to file
                rao.writeUTF(builder.toString());

                // Here we create the Index Record (the ID, the start offset and the length)
                IndexRecord record = new IndexRecord(startPointer, (int) (rao.getFilePointer() - startPointer));

                // We make the ID and add it to the map we made at the top
                index.put(location.getLocationID(), record);

                // we then update the startPointer to point to the end of the record readying it for the next
                // record
                startPointer = (int) rao.getFilePointer();

            }
            // Move the file pointer to the end of where we wrote the location offsets - and this will be the
            // start of the IDs
            rao.seek(indexStart);

            // loop over the IDs we generated from our location records and write them to the file, after every
            // write remember the file pointer will move to point to the next available space
            for(Integer locationID : index.keySet()) {
                rao.writeInt(locationID);
                rao.writeInt(index.get(locationID).getStartByte());
                rao.writeInt(index.get(locationID).getLength());
            }

        }

    }

    //  1. The first 4 bytes will contain the number of locations (bytes 0-3)
    //  2. The next 4 bytes will contain the start offset of the location section (bytes 4-7)
    //  3. The next section of the file will contain the index (the index is 1692 bytes long -> (bytes 8-1699)
    //  4. the final section of the file will contain the location records (the data) (bytes 1700-onwards)

    static {
        try {
            // Open the file in rwd mode
           ra = new RandomAccessFile("locations_rand.dat", "rwd");

           // read in the number of locations
           int numLocations = ra.readInt();

           // read in the offset of locations
           long locationStartPoint = ra.readInt();

           // read the all the indexes and essentially load them into memory (by putting them into a Map)
           while (ra.getFilePointer() < locationStartPoint) {
               // remember the file pointer will move one after every read/ write
               int locationId = ra.readInt();
               int locationStart = ra.readInt();
               int locationLength = ra.readInt();

               // make the record
               IndexRecord record = new IndexRecord(locationStart, locationLength);

               // put into memory
               index.put(locationId, record);
           }
        } catch (IOException e) {
            System.out.println("IOException in static initializer: " + e.getMessage());
        }

    }

    public Location getLocation(int locationId) throws IOException {
        IndexRecord record = index.get(locationId);
        ra.seek(record.getStartByte());
        int id = ra.readInt();
        String description = ra.readUTF();
        String exits = ra.readUTF();
        String[] exitPart = exits.split(",");

        Location location = new Location(locationId, description, null);

        if(locationId != 0) {
            for(int i=0; i<exitPart.length; i++) {
                System.out.println("exitPart = " + exitPart[i]);
                System.out.println("exitPart[+1] = " + exitPart[i+1]);
                String direction = exitPart[i];
                int destination = Integer.parseInt(exitPart[++i]);
                location.addExit(direction, destination);
            }
        }

        return location;
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }

    public void close() throws IOException {
        ra.close();
    }
}

/*
* FileWriter
* The FileWriter class will allow us to write data to a file by using the write() method - when creating the
* FileWriter object, it will throw an error saying that it needs to handle an IOException - we can either use a
* try-catch block to catch the Exception or we can tell the parent method (the method that contains the actual method
* calls of write()) that it will throw an IOException like so
*   - public static void main(String[] args) throws IOException {...}
*
* Now actually looking at the code
    public static void main(String[] args) {
        FileWriter locFile = null;
        try {
            locFile = new FileWriter("locations.txt");
            for(Location location: locations.values()) {
                locFile.write(location.getLocationId() + "," + location.getDescription() + "\n");
            }
        } catch (IOException e) {
            System.out.println("In catch block");
            e.printStackTrace();
        } finally {
            System.out.println("In finally block");
            try{
                if(locFile != null) {
                    locFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
*
* We have to do 2 things:
*   - 1. Resolve the checked exception
*   - 2. use the close() method to close the data stream
*
* 1. Resolve the checked exception
* As mentioned in Exceptions, there 2 types of exceptions in Java: checked and unchecked exceptions, and as the name
* goes we must always resolve checked exceptions (in the IDE they come up with a red underline) and the program will
* not compile unless it is sorted it out
*
* 2. use the close() method to close the data stream
* The close() method is vital if you are not using the try-with-resources variant, as the file is not being closed
* automatically, you must do it manually. If the output file is not closed, this could lead to data corruption, the
* data stream not working a intended and only one process of data stream will be used at one time - meaning its
* hogging the process unless closed of course.
*
* Now the close() method itself throws an IOException, so we need to catch that as well if we are placing it in the
* finally block, so within the finally block we do another try-catch block
*
* Now this leads us to another variation of the the try block, try-finally. This finally block will run regardless of
* any exceptions that were caught etc. hence this very useful when closing files/ data streams. However this
* approach is of course now been replaced with try-with-resources of course
*
*
* Now with our current bit of code, we are doing a lot to catch IOExceptions, however we are really not doing
* anything with it, and rather catching it, we can actually throw an IOException to notify the calling method and to
* the user that the files being read have a problem - and ultimately the program itself (the game) is not going to work
*
*     public static void main(String[] args) throws IOException {
        FileWriter locFile = null;
        try {
            locFile = new FileWriter("locations.txt");
            for(Location location: locations.values()) {
                locFile.write(location.getLocationId() + "," + location.getDescription() + "\n");
                throw new IOException("test exception thrown while writing to file");
            }
        } finally {
            System.out.println("In finally block");
            if (locFile != null) {
                System.out.println("Attempting to close locFile");
                locFile.close();
            }
        }
    }
*
* Now as we throwing an IOException, we do not need to catch the Exceptions anymore, so we can rid of them.
* Now wh place the throw new IOException in the for-loop, this is for testing purposes, and its to see:
*   1. The file is created
*   2. The contents of the file contain a location ID and its description (we are only going to get one in the file,
*      as the loop will break out after the throw new Exception
*   3. And if you remember, the finally block is always executed no matter what, so we can check also if the locFile
*      is also closed as well
*
* throwing exceptions and assertions are great for debugging purposes, but for production code they should be removed
* as they will result in runtime errors within the code
*
*
* Try-With-Resources
* This feature was implemented in Java 7
* As mentioned previously, the try-resources will close the data stream for us automatically, so we don't need to
* worry about closing the file at all, of course the Exceptions still persist so we can still retain the throws
* IOException, as this is still valid for our program
*
* To do the try-with-resources, we do this:
* next to the try keyword, we place a parenthesises, and within them, we declare the FileWriter (basically any writer
*  or reader that implements the AutoClosable - most do, but always check in the Java Docs just to make sure.
*   - try(FileWriter fw = new FileWriter("textFile.txt") {...}
*
* By doing this we can omit the finally block altogether, as now Java is doing this for us - and the code looks much
* cleaner now
*
*     public static void main(String[] args) throws IOException {
        try(FileWriter locFile = new FileWriter("locations.txt")) {
            for (Location location: locations.values()) {
                locFile.write(location.getLocationId() + "," + location.getDescription() + "\n");
            }
        }
*
*
* Now there is a difference between the 2 methods, and that's got to do with the how the errors are thrown back up
* the Stack
*
* With the first method: try-finally blocks:
*   - when an error occurs in the try block - and not in the finally block the error is thrown back up the stack and we
*     can see that and vice versa with the finally block as well
*   - When an error occurs in the try and in the finally block, what happens is that the finally block's error will
*     suppress the try block error from being shown - so the programmer will think the error is coming from closing
*     the file, when another error in the try block was also happening at the same time
*
* With the second method try-resources:
* We don't see the errors conflicting, if an error happens in both try and effectively finally (Java closes the file
* for us) the error that gets suppressed is the finally block, and the error that is thrown back up the stack is the
* try block which is better - because that's where the error is most likely going to happen - and the closing the
* file/ data stream does not really interest us as much now
*
*
* Try-resources multiple files being opened
* We can have multiple files being opened for the instance of that data stream
* try(FileWriter locFile = new FileWriter("locations.txt");
      FileWriter directions = new FileWriter("directions.txt"))
*
* We actually place a semi-colon(;) after the end of the first FileWriter, basically saying this is the end of the
* first statement
*
*
* Reading files
* There are many ways to read files, we are going to use the Scanner, and passing in the FileReader Object this will
* allow us to use the Scanner class to effectively go through the file and allow us to read the data within the file
*
* Now everything else is pretty much the same in the first method to write to a file
*       Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("locations.txt"));
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: " + loc + ": " + description);
                Map<String, Integer> tempExits = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExits));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }
*
* Buffered Reader
* The buffered reader will read text from the input stream and buffers the characters into a character array. So what
* this basically means, is instead of reading a few characters at a time, it will try to locate all the characters
* from the file and have it ready for us to read. This is more efficient then the approach above, as seek times on a
* hard drive can get long if we read a few characters at a time, the head will have to go to multiple sectors to find
* the information
*
* We place the FileReader within the BufferedReader as our new Scanner Object to be passed. This is now more
* efficient to read and get data from the file
*
*         try {
            scanner = new Scanner(new BufferedReader(new FileReader("directions.txt")));
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);

                System.out.println(loc + ": " + direction + ": " + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }
*
*
* Byte Data
* As mentioned above, we can store character data and byte data
* The advantage of storing data in byte format is very simple, we can convert any of the 8 primitive types to byte
* data and we can read it just like we would - we don't have to faff about determining what type the data is as it is
* basically one type now when using byte - this also does apply to String literals - even though they are not part
* of the 8 primitive types we can still manipulate Strings to be stored as bytes
*
* Now the bytes streams follow very similarly data streams - but instead of using FileReader and FileWriter classes
* we have to FileInputStream and FileOutputStream classes and we can buffer them as well to save disk seek times by
* reading in large chunks of data, likewise writing large chunks of data as well
*
* Write
*   - FileOutputStream
*   - BufferedOutputStream
*
* Read
*   - FileInputStream
*   - BufferedInputStream
*
* Here we use a .dat file to store our binary data, one its mostly recommended to store it and compared to our .txt
* example, we are storing both locations and exits together in one file.
*
* Writing Binary Files (primitive types)
*   - Now when writing to the binary file, we first must wrap our FileOutputStream with a BufferedOutputStream to start
*     with
*   - We have to use the DataOutputStream class to wrap the .dat file and the objects on the right side as well, this
*     is because this class provides us with methods to easily write different primitive types to the file
*     Why?
*     As mentioned above, we can store pretty much all the 8 types and Strings - so the file must be able to
*     differentiate between the types - of course we can write them just as it is, but it makes it harder for us when it
*     comes back to say reading the file
*   - so we use the writeInt() to write int types to the file, and as int types have about -/+((2^16) /- 1)
*     combinations the binary file will actually allocate 4 bytes to this bit of data - as all the data is stored as
*     raw bits
*   - For Strings we use the writeUTF() method, which will take a String that belongs to the UTF encoding -> mainly
*     UTF-8 (again 4 bytes here or more depending on the character set)
*
* The example below illustrates what we discussed above
*
*         try (
                DataOutputStream locFile =
                        new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))
        ) {
            for(Location location: locations.values()) {
                locFile.writeInt(location.getLocationId());
                locFile.writeUTF(location.getDescription());

                System.out.println("Writing location: " + location.getLocationId() + ": " + location.getDescription() );
                System.out.println("Writing " + (location.getExits().size() - 1) + "  exits"); // subtract 1 because

                // we don't want opt 0 (quit)
                locFile.writeInt(location.getExits().size() - 1);

                for (String direction: location.getExits().keySet()) {
                    if(!direction.equalsIgnoreCase("Q")) {
                        System.out.println("\t\t" + direction + ", " + location.getExits().get(direction));

                        locFile.writeUTF(direction);
                        locFile.writeInt(location.getExits().get(direction));
                    }
                }
            }

        }
*
*
* Reading Binary Files (primitive types)
* Provided we know the order of which the data types were written to the file, we can then use that same order to
* read in the values which correspond to their type
*
*   - Now when reading binary files we have to use DataInputStream, we then go ahead and create the constructor by
*     passing in new objects for BufferedInputStream and lastly FileInputStream, and pass the file name of the binary
*     file
*   - Now knowing the order of which the data types were written to the binary file, we can use the same order to
*     read the data back in to their respected types like so:
*       - readInt() and the readUTF() method will read the int and String. So when we wrote the file, we stored the
*         locId(int) first, so we of course have to read the int back in, this order carries on all the way till the
*         end
*   - Now with our Exceptions, we are going to basically wait till the code reaches the EOF on the binary file, once
*     it has reached that, we tell our while loop to break out - the ordering of the catch blocks are important, as
*     IOException is a parent class to EOFException - so by EOF first we can always make sure that the while loop
*     will break properly
*
*
*       boolean eof = false;
        try(DataInputStream locFile =
                    new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
            while (!eof) {
                Map<String, Integer> exits = new LinkedHashMap<>();
                int locId = locFile.readInt();
                String description = locFile.readUTF();
                int numExits = locFile.readInt();
                System.out.println("Read location " + locId + " : " + description);
                System.out.println("Found " + numExits + " exits");
                for (int i=0; i<numExits; i++) {
                    String direction = locFile.readUTF();
                    int destination = locFile.readInt();
                    exits.put(direction, destination);
                    System.out.println("\t\t" + direction + ", " + destination);
                }
                locations.put(locId, new Location(locId, description, exits));

            }
        } catch (EOFException e) {
            eof = true;
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
*
*
* Storing Objects in binary files
* So what we saw above is the very basic way of storing data types - but we had to call a method for each type:
* writeInt(), readInt() ..., now this could be a problem if we had an object that had 20 fields - we would have to
* call 20 methods to write and read the data
*
* Java provides a way to store an entire object - and then be reassembled when read back in to the program - this
* process is known as "Serialisation" - and we the methods
*   - ObjectInputStream()
*   - ObjectOutputStream()
*
* Now to allow an object to stored in a binary file, we have to make the class serializable - we do this by making
* the class implement the Serializable interface - this interface has no methods - but this is to the JVM that this
* object is going to be used for storage and then be reassembled as well
*
* Now when making a class Serializable - its strongly recommended that we declare a long field called "serialVersionUID"
*   - private long serialVersionUID;
*
* If we don't do this, the compiler will give us a warning - and when not passed the compiler will calculate a
* default value, but there are many variants or different implementations of compilers and they will give different
* default values - and this can lead to different problems down the road if we change the compiler for the application
*
* What is this serialVersionUID used for?
* Think of it as a version number for the class - if we don't set it, then the compiler will set it for us based on
* the number of fields, number of methods, etc... and if we ever change the class - so changing the number of fields/
*  methods, we then have to change the serialVersionUID as well
*
* When we come to read the Object from the stream, the runtime checks the stored serialVersionUID against the one
* contained within the compiled class file: if they don't match there is a compatibility problem and the runtime will
*  throw an InvalidClassException
*
* Now when it comes to many of the Java objects, they mainly all implement Serializable, however for our own custom -
* so written - objects, they too must also implement Serializable. so for example if we had a class we wanted to make
* Serializable, and within that the object - we fields of another object type we wrote, then we need to modify that
* field Object to also be Serializable
*
* Now all primitive types are serializable, this is because their wrapper classes implement Serializable
*
* Writing binary files (objects)
*   - As mentioned before, all objects within the class and their constituent objects fields themselves must also
*    implement the interface Serializable or else this isn't going to work
*   - We use the ObjectOutputStream to write objects, but we can still use BufferedOutputStream and FileOutputStream
*    like before.
*   - We use the writeObject() method and this will of course take an object to be written to file
*
* The ObjectOutputStream and ObjectInputStream both implement the DataOutputStream/ DataInputStream - so we can still
* use the writeInt() readInt() ect... if we wanted to. because of that Objects Streams like this can have a mix of
* objects and primitive types
*
*
*
*         try (
                ObjectOutputStream locFile =
                        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))
        ) {
            for (Location location: locations.values()) {
                locFile.writeObject(location);
            }
        }
*
* Reading Binary Files (Objects)
*   - We use the ObjectInputStream here instead, and the other goodies can still be used
*   - Order still pertains here, but as we only wrote objects, we are literally gathering the objects back from the file
*   - We use the method readObject(), but here we have to cast the return value back to the object type we want so in
*    this case Location, this is because the objects are stored as Object types, as not Location - so we must always do
*    that
*   - We must also catch a new Exception, ClassNotFoundException, this is because we are casting the Objects to
*    Location this error can be produced
*
*
*       boolean eof = false;
        try(ObjectInputStream locFile =
                    new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
            while (!eof) {
                Location location = (Location) locFile.readObject();
                System.out.println("Read location " + location.getLocationId() + ": " + location.getDescription());
                System.out.println("Found " + location.getExits().size() + " exits");

                locations.put(location.getLocationId(), location);
            }
        } catch (EOFException e) {
            eof = true;
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
            e.printStackTrace();
        }
*
* RandomAccess
* Random Access is where we read bytes of data at different offsets and not sequentially in order like we have done
* at the top; so say we want to read bytes 20-50, or write bytes to 70-90 etc.
*
* offset: is a byte location in a the file: so if the offset is at value 100, that would mean the file pointer is
* pointer to byte value 100
*
* Now this also required the use of a filePointer, and this offset is also remembered for each read/ write, so if
* the file pointer is at 100 when it read its last value, when we come to use the file pointer again it will still be
* at 100 when reading
*
* Now if were reading an int at position 100 bytes, it will be 100-103 (int is 4 bytes, and binary is 0 indexed)
* Now by reading that int, the file pointer has now advanced to 103
*
*
* Now when using random access files, we refer to each set of related data as a record -> so our fields in Location
* etc. are considered as records of data
*
* Now with objects, if they all had the same length in terms of their record (so all the fields etc. were say all
* ints) then that record length will be 4 bytes long + extras, but we have basically a fixed record length - knowing
* that we can make a formula saying that (n-1) * 4 will get us the file pointer to point to a new record.
*
* The case above isn't what we got for our Location object - as we have varying descriptions and exits - so we need
* to add an index to Location, this will basically act as Primary Key in that regard and we can jump around to
* different records (usually an index is a unique identifier - and within it it contains a unique identifier for each
*  record, the offset in file and length of the record -> and in Java that will sum to 12 bytes)
*
* so for our Location object its index will contain the Location ID, the offset in file and the record length
*
* Now the index will usually be smaller than the Location object data itself - and when that is the cse we want to
* load all the indexes into memory - as access RAM is faster than disk, and when the index is larger the object data
* itself we won't do that - memory is tight as you know
*
* So for our Location example
*
*   1. The first 4 bytes will contain the number of locations (bytes 0-3)
*   2. The next 4 bytes will contain the start offset of the location section (bytes 4-7)
*   3. The next section of the file will contain the index (the index is 1692 bytes long -> (bytes 8-1699)
*   4. the final section of the file will contain the location records (the data) (bytes 1700-onwards)
*
* We generally will use the seek() method when we want to jump about offsets within the file
*
* RandomAccessFile (primitive)
* One drawback to this method is that we cannot read/ write objects
* This method allows us to write in RandomAccess - and the file pointer will always point to zero when we are opening
* or creating a new file - so we don't have to faff about with the seek() method
*
*   - try(RandomAccessFile rao = new RandomAccessFile("locations_random.dat", "rwd"))
*
* When using the RandomAccessFile, we have to specify the file name and the mode - this mode will tell the
* constructor whether we are reading, writing or doing both - in this case we are but we also want it to be
* synchronised - meaning that it is thread safe so we won't have different threads changing the values within it
*
*
*
* */