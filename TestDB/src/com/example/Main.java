package com.example;

import java.nio.file.FileSystems;
import java.sql.*;

public class Main {
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + FileSystems.getDefault().getPath(DB_NAME);

    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";


    public static void main(String[] args) {

        try (
                Connection conn = DriverManager.getConnection(CONNECTION_STRING);

                Statement statement = conn.createStatement();
        ) {
//            Connection conn = DriverManager.getConnection("jdbc:sqlite:" +
//                    FileSystems.getDefault().getPath("testjava.db"));
//
//            Statement statement = conn.createStatement();
            conn.setAutoCommit(false);
            statement.execute(String.format("DROP TABLE IF EXISTS %s", TABLE_CONTACTS));

            statement.execute(String.format("CREATE TABLE IF NOT EXISTS %1$s (%2$s TEXT, %3$s INTEGER, %4$s TEXT)",
                    TABLE_CONTACTS, COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL));

            insertContact(statement, "Tim", 456789, "tim@email.com");

//            statement.execute(String.format("INSERT INTO %1$s VALUES('%2$s', %3$d, '%4$s')",
//                    TABLE_CONTACTS,
//                    "Tim", 456789, "tim@emai.com"));

            insertContact(statement, "Joe", 45632, "joe@email.com");

//            statement.execute(String.format("INSERT INTO %s (%s, %s, %s) " +
//                            "VALUES('%s', %d, '%s')",
//                    TABLE_CONTACTS,
//                    COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL,
//                    "Joe", 45632, "joe@email.com"));

            insertContact(statement, "Jane", 4829484, "jane@email.com");

//            statement.execute(String.format("INSERT INTO %s (%s, %s, %s) " +
//                            "VALUES('%s', %d, '%s')",
//                    TABLE_CONTACTS,
//                    COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL,
//                    "Jane", 4829484, "jane@email.com"));

            insertContact(statement, "Fido", 9038, "dog@email.com");

//            statement.execute(String.format("INSERT INTO %s (%s, %s, %s) " +
//                            "VALUES('%s', %d, '%s')",
//                    TABLE_CONTACTS,
//                    COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL,
//                    "Fido", 9038, "dog@email.com"));

            statement.execute(String.format("UPDATE %s SET %s = %d WHERE %s = \"%s\"" ,
                    TABLE_CONTACTS, COLUMN_PHONE, 55489897, COLUMN_NAME, "Jane"));

            statement.execute(String.format("DELETE FROM %s WHERE %s = \"%s\"",
                    TABLE_CONTACTS, COLUMN_NAME, "Joe"));

            ResultSet results = statement.executeQuery(String.format("SELECT * FROM %s", TABLE_CONTACTS));

            while (results.next()) {
                System.out.println(results.getString(COLUMN_NAME) + " "
                        + results.getInt(COLUMN_PHONE) + " "
                        + results.getString(COLUMN_EMAIL));
            }

            results.close();

            conn.commit();


//            statement.execute("INSERT INTO contacts " +
//                                   "VALUES('Tim', 6545678, 'tim@email.com')");

//            statement.execute("INSERT INTO contacts " +
//                    "VALUES('Joe', 45672, 'joe@email.com')");
//
//            statement.execute("INSERT INTO contacts " +
//                    "VALUES('Jane', 4587956, 'jane@email.com')");
//
//            statement.execute("INSERT INTO contacts " +
//                    "VALUES('Jill', 122548, 'jill@email.com')");

//            statement.execute("UPDATE contacts SET phone=5566789 " +
//                    "WHERE name='Jane'");

//            statement.execute("DELETE FROM contacts WHERE name='Joe'");

//            statement.execute("SELECT * FROM contacts");
//            ResultSet results = statement.getResultSet();
//            ResultSet results = statement.executeQuery("SELECT * FROM contacts");
//
//            while (results.next()) {
//                System.out.println(results.getString("name") + " "
//                        + results.getInt("phone") + " "
//                        + results.getString("email"));
//            }
//
//            results.close();
//
//            conn.commit();

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute(String.format("INSERT INTO %s (%s, %s, %s) " +
                        "VALUES('%s', %d, '%s')",
                TABLE_CONTACTS,
                COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL,
                name, phone, email));
    }
}

/*
 * JDBC
 * JDBC (Java Database Connectivity)
 * This is essentially a driver that acts between a java application and the DB - and allows communication between the
 * 2 clients
 *
 * We are going to use DB Browser for SQLite (GUI application which will allow us to see the Data a lot easier than
 * the command line)
 *
 * NOTE: If we are getting odd errors - go to DB Browser and go to File > Close Database Connection
 * This will release the lock it has on any SQLite DB - when using the DB Browser it obtain a lock on the DB and any
 * other application attempting to communicate to it will not be allowed
 *
 * We downloaded the .jar file that contains the packages to use the JDBC and added it to the project
 *
 * Connection
 * This class allows us to open a connection a to DB and it will also obtain relevant information about the DB - so
 * its table, schema etc.
 *
 * It will throw an SQLException - so make sure to catch it
 *
 * Connection conn = DriverManager.getConnection("jdbc:sqlite:" + FileSystems.getDefault().getPath("testjava.db"));
 *
 * NOTE: conn.setAutocommit(false) - this will turn off auto-commit - and you can use the method conn.commit() to
 * commit the changed to the DB so the changed are saved
 *
 * DriveManager
 * Is a class that allows communication between the JDBC Drivers and Java
 *
 * getCommunication()
 * for JDBC 4.0 and onwards
 * This method will establish a connection to the specified URI (the DB basically)
 * We always have to prefix the DB Path with jdbc:sqlite to tell the JDBC that this is the URI to the DB
 *
 * the old way consisted of using CLass.forName("org.sql.JDBC");
 *
 * Statement
 * This class allows us to open a connection to the SQL interpreter from our Connection instance - basically we can
 * execute SQL statements
 *
 * Here we use the Connection instance to call the createStatements() method - this allows us to create a Statement
 * object and allows SQL statements to be sent and parsed to the SQL DB
 *
 * the execute() method will return a boolean value true if the ResultSet went through and false if the update did not
 * make it
 *
 *   Statement statement = conn.createStatement();
 *   statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
 *
 * NOTE: we do not need to add a semi colon (;) to the end of every SQL statement - this is because the JDBC
 * recognises SQL statements and will terminate where appropriate
 *
 * NOTE: after every statement object has finished executing its queries - the JDBC will commit the data to the DB
 * immediately - this to make sure that the data is kept in the Db in an event it crashes or you logout
 *
 *
 * DB Objects
 * The instances we have created so far are known as DB objects - and these connections etc use resources (i.e like
 * when opening a file using a char or byte stream or channels with NIO they all require a persistent connection and
 * that uses resources)
 *
 * Whenever we use DB Objects we always have to make sure that we are closing them
 * So the 2 instances: Connection and Statement must be closed (they do in-face implement AutoCloseable which means
 * they can be used in the try-with-resources)
 *
 * The order matters as well, we close the from bottom-up
 * so Statements are closed first then the we keep going up to the Connection
 *
 * if we closed the Connection first, the Statement which is attached tot he Connection cannot close and will throw
 * and Exception because of this
 *
 * statement.close(); conn.close(); - in a finally block or use them in the try-resources block
 *
 *
 * ResultSets
 * ResultSets are created from Statements execute() method, this method returns a boolean value true if a ResultSet
 * was returned and false if not
 *
 * ResultSets are DB objects which represent tables etc. so they will contain the column info and all the record
 * information from a query
 *
 * ResultSets are only returned when we query the DB (so when we want to retrieve data from the DB) the other latter
 * when we CREATE, UPDATE or DELETE data from the DB is where the ResultSet is not returned
 *
 * The ResultSet is also a DB object - so we must also close it as well - and this must be closed before a Statement
 * object - as it is associated with a Statement object
 *
 *  results.close(); <- before statement.close()
 *  statement.close();
 *
 * As mentioned the execute() method returns a boolean value - so we can run a LBYL check before getting the ResultSet
 * we use the getResultSet() method on the Statement instance to retrieve the ResultSet - we can then loop over it to
 * retrieve the data from the columns
 *
 * The cursor
 * The cursor is a DB cursor which acts as a pointer to where to read the next bit of information - when the next()
 * is first called it will move the cursor to the first record, first cell
 *
 * after every subsequent call to next() will the move the cursor to the next record
 *
 * we use the next() method to the cursor forward to the next record (row)
 *
 * the call getString(), getInt() ... will retrieve the appropriate data type from the column specified in when passed
 * the String argument
 *
 * So here we retrieve the name, phone and email from each column, from the ResultSet query
 *
 *          statement.execute("SELECT * FROM contacts");
            ResultSet results = statement.getResultSet();

            while (results.next()) {
                System.out.println(results.getString("name") + " "
                        + results.getInt("phone") + " "
                        + results.getString("email"));
            }

            results.close();
 *
 *
 * NOTE: it is very important to use know that Statements and ResultSets are best used with a one-to-one relationship
 * that means we use a new instance of ResultSet and Statement each time whenever we want to query the table - this
 * is because of the cursor in the ResultSet which also updates in the Statement object as well
 *
 * In our example we reused the Statement object with a ResultSet - but the internal pointer was not updated so it
 * wak okay but always a new instance of each when retrieving data
 *
 * rule: new instance always for when using ResultSet and Statement objects
 *
 *
 * There is an easier way of executing queries using the executeQueries() method in the Statement class like so
 * This returns a ResultSet for whenever you are executing a query - and ResultSet will work as normal when using
 * next() etc..
 *  ResultSet results = statement.executeQuery("SELECT * FROM contacts");
 *
 * and when calling the getString() ... we can use the column indices instead of column names - this is faster and
 * the preferred way of doing it:
 * The column indexed start from 1 onwards
 *
 *  System.out.println(results.getString(1) + " "
                        + results.getInt(2) + " "
                        + results.getString(3));
 *
 *
 * */
