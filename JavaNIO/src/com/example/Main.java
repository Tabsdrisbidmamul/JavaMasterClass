package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            Pipe pipe = Pipe.open();

            Runnable writer = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SinkChannel sinkChannel = pipe.sink();
                        ByteBuffer buffer = ByteBuffer.allocate(56);

                        for (int i=0; i<10; i++) {
                            String currentTime = "The time is: " + System.currentTimeMillis();

                            buffer.put(currentTime.getBytes());
                            buffer.flip();

                            while (buffer.hasRemaining()) {
                                sinkChannel.write(buffer);
                            }

                            buffer.flip();
                            Thread.sleep(100);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            Runnable reader = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SourceChannel sourceChannel = pipe.source();
                        ByteBuffer buffer = ByteBuffer.allocate(56);

                        for (int i=0; i<10; i++) {
                            int bytesRead = sourceChannel.read(buffer);
                            byte[] timeString = new byte[bytesRead];
                            buffer.flip();
                            buffer.get(timeString);
                            System.out.println("Reader Thread: " + new String(timeString));
                            buffer.flip();
                            Thread.sleep(100);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            new Thread(writer).start();
            new Thread(reader).start();

        } catch (IOException e) {
            e.printStackTrace();
        }


//        try (FileOutputStream binFile = new FileOutputStream("data.dat");
//             FileChannel binChannel = binFile.getChannel()) {
//
//            ByteBuffer buffer = ByteBuffer.allocate(100);
//            byte[] outputBytes = "Hello World!".getBytes(StandardCharsets.UTF_8);
//            byte[] outputBytes2 = "Nice to meet you".getBytes(StandardCharsets.UTF_8);
            /*
            * The reason we can chain these put() methods together is the buffer.put<dataTypes> for all variants
            * return the buffer itself so we are essentially doing is buffer.put for each of them but using the
            * returning value from each put and chaining them together
            * */

//            buffer.put(outputBytes).putInt(245).putInt(-98765).put(outputBytes2).putInt(1000);

//            read(ByteBuffer) - reads bytes beginning at the channel's current position, and after the read,
//                               updates the position accordingly.
//                               Note that now we're talking about the channel's position, not the byte buffer's position.
//                               Of course, the bytes will be placed into the buffer starting at its current position.
//            write(ByteBuffer) - the same as read, except it writes. There's one exception.
//                              If a datasource is opened in APPEND mode, then the first write will take place starting
//                              at the end of the datasource, rather than at position 0. After the write, the position
//                              will be updated accordingly.
//            position() - returns the channel's position.
//            position(long) - sets the channel's position to the passed value.
//            truncate(long) - truncates the size of the attached datasource to the passed value.
//            size() - returns the size of the attached datasource

//            buffer.put(outputBytes);
//            long int1Pos = outputBytes.length;
//            buffer.putInt(245);
//            long int2Pos = int1Pos + Integer.BYTES;
//            buffer.putInt(-98765);
//            buffer.put(outputBytes2);
//            long int3Pos = int2Pos + Integer.BYTES + outputBytes2.length;
//            buffer.putInt(1000);
//            buffer.flip(); // make buffer read mode and reset pos to 0
//            binChannel.write(buffer);
//
//            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
//            FileChannel channel = ra.getChannel();
//
//            ByteBuffer readBuffer = ByteBuffer.allocate(100);
//            channel.position(int3Pos);
//            channel.read(readBuffer);
//            readBuffer.flip();
//            System.out.println("int3 = " + readBuffer.getInt());
//            readBuffer.flip();
//
//            channel.position(int2Pos);
//            channel.read(readBuffer);
//            readBuffer.flip();
//            System.out.println("int2 = " + readBuffer.getInt());
//            readBuffer.flip();
//
//            channel.position(int1Pos);
//            channel.read(readBuffer);
//            readBuffer.flip();
//            System.out.println("int1 = " + readBuffer.getInt());
//            readBuffer.flip();
//
//            RandomAccessFile copyFile = new RandomAccessFile("datacopy.dat", "rw");
//            FileChannel copyChannel = copyFile.getChannel();
//            channel.position(0);
////            long numTransferred = copyChannel.transferFrom(channel, 0, channel.size());
//            long numTransferred = channel.transferTo(0, channel.size(), copyChannel);
//            System.out.println("Num transferred: " + numTransferred);
//
//            channel.close();
//            ra.close();
//            copyChannel.close();

//            byte[] outputString = "Hello, World!".getBytes(StandardCharsets.UTF_8);
//            long str1Pos = 0;
//            long newInt1Pos = outputBytes.length;
//            long newInt2Pos = newInt1Pos + Integer.BYTES;
//            byte[] outputString2 = "Nice to meet you".getBytes(StandardCharsets.UTF_8);
//            long str2Pos = newInt2Pos + Integer.BYTES;
//            long newInt3Pos = str2Pos + outputBytes2.length;
//
//            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
//            intBuffer.putInt(245);
//            intBuffer.flip();
//            binChannel.position(int1Pos);
//            binChannel.write(intBuffer);
//
//            intBuffer.flip();
//            intBuffer.putInt(-98765);
//            intBuffer.flip();
//            binChannel.position(int2Pos);
//            binChannel.write(intBuffer);
//
//            intBuffer.flip();
//            intBuffer.putInt(1000);
//            intBuffer.flip();
//            binChannel.position(int3Pos);
//            binChannel.write(intBuffer);
//
//            binChannel.position(str1Pos);
//            binChannel.write(ByteBuffer.wrap(outputString));
//
//            binChannel.position(str2Pos);
//            binChannel.write(ByteBuffer.wrap(outputString2));

//            ByteBuffer readBuffer = ByteBuffer.allocate(100);
//            channel.read(readBuffer);
//            readBuffer.flip(); // make buffer to write mode and reset pos to 0
//            byte[] inputString = new byte[outputBytes.length];
//            readBuffer.get(inputString); // read inputString length amount of bytes to get the String
//            System.out.println("inputString = " + new String(inputString));
//            System.out.println("int1 = " + readBuffer.getInt());
//            System.out.println("int2 = " + readBuffer.getInt());
//            byte[] inputString2 = new byte[outputBytes2.length];
//            readBuffer.get(inputString2); // read inputString length amount of bytes to get the String
//            System.out.println("inputString2 = " + new String(inputString2));
//            System.out.println("int3 = " + readBuffer.getInt());


//            ByteBuffer buffer = ByteBuffer.allocate(outputBytes.length);
//
//            buffer.put(outputBytes);
//            buffer.flip();
//            int numBytes = binChannel.write(buffer);
//            System.out.println("numBytes written was: " + numBytes);
//
//            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
//            intBuffer.putInt(245);
//            intBuffer.flip();
//            numBytes = binChannel.write(intBuffer);
//            System.out.println("numBytes written was: " + numBytes);
//
//            intBuffer.flip();
////            intBuffer.clear();
//            intBuffer.putInt(-98765);
//            intBuffer.flip();
//            numBytes = binChannel.write(intBuffer);
//            System.out.println("numBytes written was: " + numBytes);
//
//            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
//            FileChannel channel = ra.getChannel();
//            outputBytes[0] = 'a';
//            outputBytes[1] = 'b';
//            buffer.flip();
//            long numBytesRead = channel.read(buffer);
//            if(buffer.hasArray()) {
////                System.out.println("byte buffer = " + new String(outputBytes));
//                System.out.println("byte buffer = " + new String(buffer.array()));
//            }
//
//            // absolute read
//            intBuffer.flip();
//            numBytes = channel.read(intBuffer);
//            System.out.println(intBuffer.getInt(0));
//
//            intBuffer.flip();
//            numBytes = channel.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt(0));
//            System.out.println(intBuffer.getInt());
//
//            // relative read
//            //            intBuffer.flip();
//            //            numBytes = channel.read(intBuffer);
//            //            intBuffer.flip();
//            //            System.out.println(intBuffer.getInt());
//            //
//            //            intBuffer.flip();
//            //            numBytes = channel.read(intBuffer);
//            //            intBuffer.flip();
//            //            System.out.println(intBuffer.getInt());
//
//            channel.close();
//            ra.close();

            // java io
//            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
//            byte[] b = new byte[outputBytes.length];
//            ra.read(b);
//            System.out.println(new String(b));
//
//            long in1 = ra.readInt();
//            long int2 = ra.readInt();
//            System.out.println(in1);
//            System.out.println(int2);


//            FileInputStream file = new FileInputStream("data.txt");
//            FileChannel channel = file.getChannel();

//            Path dataPath = FileSystems.getDefault().getPath("data.txt");
//            Files.write(dataPath, "\nLine 5".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
//            List<String> lines = Files.readAllLines(dataPath);
//            for (String line: lines) {
//                System.out.println(line);
//            }

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

/*
* Java NIO
* To work with files using NIO module, we need to understand 3 things:
*   - Channels
*   - Buffers
*   - Selectors
*
* Channels
* These are the piping that connects to the data stream - and these are bi-directional meaning that we can both read
* and write with one instance
*
* Buffers
* These are the containers that will buffer and hold a static amount of data that is read from the data source via
* the channel, we can allocate this static after the channel has been "opened"
*
* Selectors
* This is basically allowing one thread to handle multiple channels - and stopping other threads from basically
* "sleeping" whilst the other thread gets the work done
*
* Opening a channel
* There are 2 ways to do open a channel:
*   1. Create a channel from an open file instance
*   2. Use the FileChannel.open() method
*
* Channel from an Open file
* We get the FileChannel instance from 3 classes
*   - FileInputStream
*   - FileOutputStream
*   - RandomAccessFile
*
* FileInputStream Example
* Rules to getting the FileChannel:
*   1. Create the instance of FileInputStream, FileOutputStream or RandomAccessFile.
*   2. Create an instance of FileChannel, and the set the reference variable equal to the FileInputStream,
*      FileOutputStream or RandomAccessFile instance and using the dot notation retrieve the method .getChannel()
*
*   - FileInputStream file = new FileInputStream("data.txt");
*   - FileChannel channel = file.getChannel();
*
* Now I did say that channels are bidirectional, but that is not the case in this example, as FileInputStream is for
* reading - the channel we got from that instance can only read data sources unfortunately - the same is for
* FileOutputStream - and for RandomAccessFile that depends on the parameter passed for file mode (r: read, w: write,
* rw: read and write etc..)
*
* Another potential issue arises from the buffer capacity - as not all lines within the file are the same, they can
* vary meaning that if we have a buffer size of 25:
*   - line 1 has 25 bytes
*   - line 2 has 100 bytes
*
* We get all the contents of line 1, and on the next read we get a partial from the line 2 - meaning we have to read
* that line again. This is a big problem - because how many bytes should we read from a line? is a difficult question
* to ask each time, so instead in Java 7 they introduced Files class which contains static methods to tackle these
* problems:
*
* Reading from a file with NIO
*   - Path dataPath = FileSystems.getDefault().getPath("data.txt");
*   - List<String> lines = Files.readAllLines(dataPath);
*
* Here we read all lines from a text file, and this method will return a list of Strings - which we put into an
* ArrayList of only String objects
*
*
* Writing to a file with NIO
* Now when we come to write to a file (that being .txt) each write is actually an isolated write, meaning we call the
* method - which opens the file - adds a line of text then closes the file. So if we wanted to add more than one line
* - we typically use StringBuilder to get the job done
*
* The Files.write() writes bytes and not Strings - so we will have String.getBytes() to convert the String characters
*  to bytes
*
*   - Files.write(dataPath, "\nLine 4".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
*
* 1 param: the path to the file
* 2 param: the bytes we want to be added to the file - so we write a String and convert it to bytes - and making sure
*          that it uses the UTF-8 character set
* 3 param: the write mode - so we can either overwrite the existing file, etc... in this case we are appending to the
*          file
*
*
* Writing Binary Files
* Now when writing bin files, we will still use the FileOutputStream or RandomAccessFile (in rw, w, or rwd etc..) to
* achieve a write channel, we do the basic procedures to get the channel as well, but what we also want to do is
* store our String in a byte array - the method getBytes() returns an array of byte elements
*
*   FileOutputStream binFile = new FileOutputStream("data.dat");
*   FileChannel binChannel = binFile.getChannel();
*   byte[] outputBytes = "Hello World!".getBytes(StandardCharsets.UTF_8);
*   ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
*
* ByteBuffer.wrap()
* This will create a buffer that has an occupied space equal to the parameter length
* First it wraps the byte array into the buffer - so now the buffer is backed by the byte array - now this important
* because if either the buffer or the byte change - this causes the other half to change as well.
*
* When used this set the start position of the buffer to zero, the buffer's capacity will be set to the byte array
* length -and the buffer's mark will be undefined (which can be set later)
*
* Now that we got the buffer done, we use the channel to write the data (remember the channel is the piping that
* connects the buffer to the data source - that being the disk)
*   binChannel.write(buffer); -> this method returns the number of bytes written, so we can sout on it to find out
*                                how many bytes were written
*
* We pass the buffer as the argument - as that is where the data is contained
*
* NOTE: The wrap() resets the buffer position to zero, so when we call the write() method - it actually starts
* reading the contents of buffer marked at the position - hence why we don't need to reset the position to zero
*
* Now that's it when it comes to writing the bin file - we are now meant to close the FileOutputStream and the
* channel - so its best to stick them into a try-resources
*
*
* flip()
* NOTE: When using flip, when we create the buffer - and add items to it, we are writing values to the buffer, now
* wean we flip it, we are telling to the channel that the buffer is now in read-mode and can read the buffer now
*
* The code at the bottom does not write any data to the .dat file, this because the position is actually set to the
* end of where put the int value 245, so we have to call the flip() method the buffer to reset the position to 0, so
* the write method can the contents of the buffer
*   ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
*   intBuffer.putInt(245);
*   intBuffer.flip();
*   numBytes = binChannel.write(intBuffer);
*   System.out.println("numBytes written was: " + numBytes);
*
* clear()
* When we wrote the buffer we allocated the size to be 4 bytes long, so if we run the code below - we will get an
* overflow error and IndexOutOfBounds error - simply because we have gone over the capacity of buffer - so we can 2
* things call flip() and have the contents of the old buffer overwritten by our new data or call clear() method to
* empty the contents of the buffer and start afresh
*   intBuffer.flip();
*   //intBuffer.clear();
*   intBuffer.putInt(-98765);
*   intBuffer.flip();
*   numBytes = binChannel.write(intBuffer);
*   System.out.println("numBytes written was: " + numBytes);
*
*
* Reading Binary Files
* Now remember that the buffer and byte array are backed by one another - so in this example if we don't call flip()
* the contents of the sout will show "abllo World!" - so by calling flip() on the buffer
*   RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
*   FileChannel channel = ra.getChannel();
*   outputBytes[0] = 'a';
*   outputBytes[1] = 'b';
*   buffer.flip();
*   long numBytesRead = channel.read(buffer);
*   if(buffer.hasArray()) {
*       System.out.println("byte buffer = " + new String(buffer.array()));
*   }
*
* As we know that the buffer and the byte array are linked - without the flip() we are simply changing the contents
* of the buffer at the end. So when calling the flip() we are telling it to read the data.dat and dump the contents
* into the buffer so when we read it - we are actually reading the the contents of the file and not messing around
* with our buffer which we made right at the top.
*
* So now knowing that we can do this to get the integers using the intBuffer we made near the top
*
* Relative Read
* This is where we call getInt(), you can pass a param to it to indicate the index position to where to start
* reading, as we didn't pass anything it will start reading from the current index at position - hence why we call
* flip before the getInt() to make sure the index of position was at 0
*   intBuffer.flip();
*   numBytes = channel.read(intBuffer);
*   intBuffer.flip();
*   System.out.println(intBuffer.getInt());
*
*   intBuffer.flip();
*   numBytes = channel.read(intBuffer);
*   intBuffer.flip();
*   System.out.println(intBuffer.getInt());

*   channel.close();
*   ra.close();
*
* The pattern
*   flip()
*   read buffer
*   flip()
*   get contents
*
* we flip at the top, to make buffer which we initially had into read mode
* we read the buffer
* We flip again to reset the position to 0
* get the contents
*
*
* Absolute Read
* Here we an index position to getInt(), and we don't need to call the flip() method again - so this in a way makes
* the code a lot easier to read
*    intBuffer.flip();
*    numBytes = channel.read(intBuffer);
*    System.out.println(intBuffer.getInt(0));
*
*    intBuffer.flip();
*    numBytes = channel.read(intBuffer);
*    System.out.println(intBuffer.getInt(0));
*
* One drawback to this, is that after the call to getInt(0) the buffer position was not updated, it remained where it
*  was before - so at the very of the intBuffer
*
* At the very end we close the RandomAccessFile and Channel - mentioned near the top - because we didn't call them in
* the try with resources block
*
*
* Now if we mix up the abs read and relative read like so
*   intBuffer.flip();
*   numBytes = channel.read(intBuffer);
*   intBuffer.flip();
*   System.out.println(intBuffer.getInt(0));
*   System.out.println(intBuffer.getInt());
*
* We actually get -98765 twice, this is because absolute read doesn't update the buffer position - and therefore can
* make it when reading relatively a much greater trouble to deal with
*
* Mantra to remember: when something goes wrong, flip
*
* Single Buffer Write
*           ByteBuffer buffer = ByteBuffer.allocate(100);
            byte[] outputBytes = "Hello World!".getBytes(StandardCharsets.UTF_8);

            buffer.put(outputBytes);
            buffer.putInt(245);
            buffer.putInt(-98765);
            byte[] outputBytes2 = "Nice to meet you".getBytes(StandardCharsets.UTF_8);
            buffer.put(outputBytes2);
            buffer.putInt(1000);
            buffer.flip();
            binChannel.write(buffer);

* So instead of the trek we did at the top, this time we allocate ~100 bytes to the buffer and write everything - so
* Strings integers etc. into 1 buffer then write it to the bin file using the channel - not only is this cleaner and
* easier to read but we have only used the flip method once which makes understanding broken code that much easier
*
* Copying Files from one to another using Channels
* We create a new instance of RandomAccessFile and get its channel - as usual. Next we use the method transferFrom()
* which is in the channel instance
*
* transferFrom()
* This method is in the FileChannel class, as what this does is copy the source channel file and dump its content
* into the destination channel - the channel that called the transferFrom() method
*
* 1st param: The source channel - the channel that is connected to the file you want to copy
* 2nd param: The position of where you want to start reading from - relative position
* 3rd param: size, so how many bytes you want to take out and copy
*
*   RandomAccessFile copyFile = new RandomAccessFile("datacopy.dat", "rw");
*   FileChannel copyChannel = copyFile.getChannel();
*   long numTransferred = copyChannel.transferFrom(channel, 0, channel.size());
*   System.out.println("Num transferred: " + numTransferred);
*
* Now if we run this, we actually get a weird error in our datacopy.dat file, essentially what happened was the
* source channel position is set to one byte after the last int was written to it, so when we passed 0 to
* transferFrom() method, we didn't set the channel position to pos 0 - we said read all the contents of the file - but
* it starts reading after the last int was written - so we get some very odd characters in the copy file.
*
* This is because the transferFrom() method uses the relative position and not absolute position
*
* To fix this, we simply set the source channel position to 0 before we call the transferFrom() method, and we will
* get an exact copy of the file
*
* transferTo()
* Instead of using the destination channel to call the method, we use the source channel to call this method which
* will copy the contents of the source channel and dump them into the destination channel instead
*
* 1st param: the position: relative
* 2nd param: how many bytes you want read
* 3rd param: the destination channel
*
*   long numTransferred = channel.transferTo(0, channel.size(), copyChannel);
*
*
* Pipes
* NOTE: Refresh yourself on concurrency - Threads to understand what is happening - but effectively we have a writer
* thread which is writing contents to a buffer in its write channel - whilst at the same time we have a reader thread
* which is looking at the writer thread channel and transferring the contents to its pipe the "reader" pipe which we
* can then output to the console.
*
* You might be wondering how, the first line after the opening try we declare the pipe - this is what links the two
* pipes source and sink channel
*
* sink dealing with writing
* source dealing with reading
*
* So we can utilise 2 threads to write and read simultaneously (100 ms apart) and we can get the contents of the
* theoretical file (the writer thread has written to) and read the file using the reader thread and gets its contents
* to the console
*
* Pipes in Java NIO utilised the channels - essentially we can one or more threads do the writing of a file in the
* "write" pipe whilst at time we can one or more threads be looking at the write pipe contents and be reading it in
* and transfer the contents to their "read" pipe which we the programmer can use
*
*         try {
            Pipe pipe = Pipe.open();

            Runnable writer = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SinkChannel sinkChannel = pipe.sink();
                        ByteBuffer buffer = ByteBuffer.allocate(56);

                        for (int i=0; i<10; i++) {
                            String currentTime = "The time is: " + System.currentTimeMillis();

                            buffer.put(currentTime.getBytes());
                            buffer.flip();

                            while (buffer.hasRemaining()) {
                                sinkChannel.write(buffer);
                            }

                            buffer.flip();
                            Thread.sleep(100);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            Runnable reader = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SourceChannel sourceChannel = pipe.source();
                        ByteBuffer buffer = ByteBuffer.allocate(56);

                        for (int i=0; i<10; i++) {
                            int bytesRead = sourceChannel.read(buffer);
                            byte[] timeString = new byte[bytesRead];
                            buffer.flip();
                            buffer.get(timeString);
                            System.out.println("Reader Thread: " + new String(timeString));
                            buffer.flip();
                            Thread.sleep(100);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            new Thread(writer).start();
            new Thread(reader).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

*
* */
