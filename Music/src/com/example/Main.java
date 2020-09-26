package com.example;

import com.example.model.Artist;
import com.example.model.Datasource;
import com.example.model.SongArtist;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.open()) {
            System.out.println("Cannot open data source");
            return;
        }

        List<Artist> artists = datasource.queryArtist(Datasource.Order.ORDER_BY_ASC);
        if(isNullOrEmpty(artists)) {
            System.out.println("No artists!");
            return;
        }

        for (Artist artist: artists) {
            System.out.printf("Artist ID: %s, Artist Name: %s\n",
                    artist.getId(), artist.getName());
        }

        List<String> albumsForArtist = datasource.queryAlbumsForArtist(
                "Carole King", Datasource.Order.ORDER_BY_DESC);

        if(isNullOrEmpty(albumsForArtist)) {
            System.out.println("Empty List");
            return;
        }

        for (String album: albumsForArtist) {
            System.out.printf("Album Name %s\n", album);
        }


        List<SongArtist> songArtistList = datasource.queryForSongArtist("Go Your Own Way",
                Datasource.Order.ORDER_BY_ASC);

        if(isNullOrEmpty(songArtistList)) {
            System.out.println("Empty List");
            return;
        }

        songArtistList.forEach((SongArtist record) -> System.out.printf("Artist Name: %s Album Name: %s track: %d\n",
                record.getAlbumName(), record.getArtistName(), record.getTrack()));

        datasource.querySongsMetadata();

        int[] arr = datasource.getCount(Datasource.TABLE_SONGS);
        if (isNullOrEmpty(arr)) {
            System.out.println("Array is empty");
            return;
        }

        System.out.printf("Number of songs is %d\nMinimum ID is %d\n", arr[0], arr[1]);

//        datasource.createViewForSongsArtists();
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("\nEnter a song title: ");
//        String title = scanner.nextLine();
//
//        List<SongArtist> songArtistViewList = datasource.querySongInfoView(title);
//
//        if(isNullOrEmpty(songArtistList)) {
//            System.out.println("Empty List");
//            return;
//        }
//
//        songArtistViewList.forEach(songArtist -> {
//            System.out.printf("Artist Name: %s, Album Name: %s, track: %d\n",
//                    songArtist.getArtistName(), songArtist.getAlbumName(), songArtist.getTrack());
//        });

        datasource.insertSong("Touch of Grey", "Grateful Dead", "In The Dark", 1);
        datasource.insertSong("Like A Rolling Stone", "Bob Dylan", "Bob Dylan's Greatest Hits", 5);
        datasource.insertSong("Bird Dog", "Everly Brothers", "All-Time Greatest Hits", 7);

        datasource.close();

    }

    private static boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    private static boolean isNullOrEmpty(int[] arr) {
        return arr == null || arr.length == 0;
    }
}

/*
* MetaData
* In several SQL implementations getting the schema of a DB is different for each one - in the command line we simply
*  type .schema and we were able to get the structure of the DB - in JDBC we have to use ResultSetMetaData to pull
* that information out - as long as we the table name
*
* Everything so far is the same - we simply use ResultSetMetaData instance and retrieve the metadata from the
* ResultSet from the previous executed query - using getMetaData()
*
* We then get the number of columns so we know how many times to loop through the ResultSet and we output the results
*  using getColumnNames(i) passing in i (it is ten based index)
*
*     public void querySongsMetadata() {
        String sql = "SELECT * FROM " + TABLE_SONGS;

        try (Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery(sql)){

            ResultSetMetaData meta = results.getMetaData();
            int numColumns = meta.getColumnCount();
            for (int i=1; i<=numColumns; i++) {
                System.out.printf("Column %d in the Songs table is names %s\n", i, meta.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*
*
* SQL Injection attack
* So far we have been concatenating all of our SQL Statements together - and so far that has not lead to any problems
* However when take user input in to account - then a problem arises, they can concatenate SQL statements into the
* input - where we would normally expect a value that will be queried
*
*       Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter a song title: ");
        String title = scanner.nextLine();
*
*
*       Enter a song title: Go Your Own Way" or 1=1 or "
*
* Effectively we were sending: SELECT "Artists Name", "Albums Name", track, FROM artist_list
*                              WHERE title = "Go Your Own Way" or 1=1 or ""
*
* This will return all the records in the table that we are retrieving data from - as 1=1 is always true
* This is is known as SQL Injection Attack - where we blindly accept the input values and concatenate them to the SQL
* statements that we have
*
* PreparedStatement -> child to Statement
* This class aims to tackle the SQL Injection Attack - so instead of blindly concatenating values to our statements -
* we could have placeholders where their values change on given input
*
* So the only difference to what we have been so far with the other constants is that we have placed a question mark
* (?) where we would normally accept a input value - we don't need to place quotation marks around the question mark
* (?) the DB understands that a String value will be passed (inferring from the TEXT constraint on the column when we
*  made the DB)
*
* When we are ready to perform the query we replace the question mark with the actual title we want in this case when
* the query is executing
*
*    public static final String QUERY_VIEW_SONG_INFO_PREP = String.format(
            "SELECT %s, %s, %s FROM %s" +
            "WHERE %s = ?",
            "Artists Name", "Albums Name", COLUMN_SONGS_TRACK, VIEW_ARTISTS_SONG,
            COLUMN_SONGS_TITLE
    );
*
* Benefits
* - mitigate ourselves from a SQL Injection Attack
* - Performance benefits - we need to make a instance variable to the Prepared Statement because we want it to be
*   compiled only once - if we compiled it every time then that would have a massive impact on the performance in a
*   Enterprise environment
*
* Preparing the PreparedStatement using an Instance variable
* We create an Instance variable of PreparedStatement
*   private PreparedStatement querySongInfoView;
*
* We then initialise it in the open() method - remembering we are only going to do this once - so having it done when
* the connection is established is a good spot
*
* We pass in the statement we want it to execute using the preparedStatement() method from the Connection instance
*   querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
*
* Every time we want to use this statement all we have to do is replace the question mark (?) for a value we want
*
* After the changed we have made - we re-wrote the method to query the view to use the PreparedStatement instead
* We go back to the normal try-catch block (no try-resources) and the 2 lines that we changed are:
*
* (SQL is 1 based): for every Question Mark (?) we refer to it with its index position, we then pass in the value it
* will be
* The value used will determine which method is going to be used - in this case we are passing in a String - thus we
* are using the setString() method, if say it was an int value we use the setInt() method instead
*       querySongInfoView.setString(1, title);
*
* We got everything sorted out, we pre-compiled the SQL statement in the open() method when we instantiated the
* PreparedStatement, all we need do is execute the statement using the executeStatement() method and get a ResultSet
      ResultSet results = querySongInfoView.executeQuery();
*
*
*     public List<SongArtist> querySongInfoView(String title) {
        try {
            querySongInfoView.setString(1, title);
            ResultSet results = querySongInfoView.executeQuery();
            List<SongArtist> songArtistList = new ArrayList<>();

            retrieveRecordData(results, songArtistList);

            return songArtistList;
        } catch (SQLException e) {
            System.out.println("Query Failed: " + e.getMessage());
            return null;
        }
    }
*
* In the close() method which we wrote, we close the PreparedStatement there, since we did not use the try-resource
* approach. Must make sure to close the PreparedStatement before the Connection
*
* How does the PreparedStatement mitigate the SQL Injection Attack - it uses the value as a literal so instead of
* this when concatenating
*
* Effectively we were sending: SELECT "Artists Name", "Albums Name", track, FROM artist_list
*                              WHERE title = "Go Your Own Way" or 1=1 or ""
*
* Since in the DB, there is not a song with "Go Your Own Way or 1=1 or "" as a title - no record were returned - and
* forces an exit
*
* Literal: were sending: SELECT "Artists Name", "Albums Name", track, FROM artist_list
*                              WHERE title = "Go Your Own Way or 1=1 or ""
*
* We can pass in a second parameter to the prepareStatement() method Statement.RETURN_GENERATED_KEYS - what this does
* it will return the generated primary key when inserting the record into the table (JDBC is an auto-incrementing
* table)
*
*           insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUM, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONG);
*
* Transactions
* These can be seen a sequence of SQL Statements that are treated as a single logical unit - if any of the statements
* fail, the results of the previous statements in the transaction can be rolled back or just not saved. It is as if
* they never happened.
*
* For example
* We have 2 bank accounts source (£1000.00) and destination (£100.00) We want to send £200.00 from the source to the
* destination so source (£800.00) and destination (£300.00)
*
* So we will need 2 SQL Update Statements for that to happen, if any of them fails - dodgy internet connection - then
* you can say that the £200.00 has effectively gone missing - you could write some failure code to mitigate that -
* but isn't easy to do
*
* If we used Transactions - then changes made will be forgotten in an event of failure
*
* JDBC auto-commits everything we have done so from the CRUD (C, U and D) are always auto-committed
*
* Database Transactions must be ACID-compliant
* 1. Atomicity: This relates to what we have just discussed. If a series of SQL Statements change the database, then
*    either all the changes are committed or, none of them are
*
* 2. Consistency: Before a transaction begins, the database is in a valid state. When it completes, the database is
*    still in a valid state
*
* 3. Isolation: Until the changes committed by a transaction are completed, they will not be visible to other
*    connections. Transactions cannot depend on each other
*
* 4. Durability: Once the changes performed by a transaction are committed to the database, they are permanent. If an
*    application crashes or the database server goers down (in the case of a client/ server database like MySQL), the
*    changes made by the transaction are still there when the application runs again, or the database comes back up
*
* In SQLite every operation with C, U and D are made to be an transaction and are auto-committed (even with
* auto-commit off it is still made to be a transaction)
*
* When working with SQLite, the following commands are used for transactions:
* 1. BEGIN TRANSACTION - we use this to manually start a transaction.
*
* 2. END TRANSACTION - we use this to end a transaction. Committing changes automatically ends a transactions. Also
*    ending transaction also commits any changes. In other words END TRANSACTION and COMMIT are aliases. We only have to
*    use one when we want to end a transaction and commit the changes
*
* 3. COMMIT - we use this to commit the changes made by a transaction. As mentioned, this ends the transaction, so we
*  do not need to also run the END TRANSACTION command
*
* 4. ROLLBACK - this rolls back any uncommitted changes and ends the transaction.
*    NOTE: that it can only rollback changes that have occurred since the last COMMIT or ROLLBACK
*
*
* Inserting
* After our discussion about Transactions we are now going to further develop the application to accommodate
* insertion for songs
*
* We wrote several PreparedStatements which query the tables as well as statements for insertion of those records
* Here we have insertArtists where we will take only the Artist name (the _id is auto-generated)
* We first check if the Artist is on file by setting the queryArtist with the String title (passing in the value for
* the PreparedStatement)
*
* Execute it and run results.next() -> this moves the cursor to the first record of the ResultSet, if it passes we
* return the _id that is already associated with the Artist
*
* If not, then we have to make a new Artist and insert it into the table
*
* We pass the value of the Artist name to the PreparedStatement in insertIntoArtists and we use a new method
* executeUpdate()
* This method returns an int value, that being the number of rows that has been inserted - in this case we know we
* are only going to get one so we check if no more than one row has been inserted
*
* generatedKeys()
* depending on the insert, more than one key can be generated - and we return the getInt() on it passing in the
* column we want from it
*     private int insertArtist(String name) throws SQLException {
        queryArtist.setString(1, name);
        ResultSet results = queryArtist.executeQuery();

        if(results.next()) {
            return results.getInt(1);
        } else {
            // Insert Artist
            insertIntoArtists.setString(1, name);
            int affectedRows = insertIntoArtists.executeUpdate();

            if(affectedRows != 1) {
                throw  new SQLException("Could not insert Artist");
            }

            ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Could not get _id for Artist");
            }
        }
    }
*
*
* insert Album
*     private int insertAlbum(String name, int artistId) throws SQLException {
        queryAlbum.setString(1, name);
        ResultSet results = queryAlbum.executeQuery();

        if(results.next()) {
            return results.getInt(1);
        } else {
            // Insert Album
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistId);
            int affectedRows = insertIntoAlbums.executeUpdate();

            if(affectedRows != 1) {
                throw  new SQLException("Could not insert Album");
            }

            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Could not get _id for Album");
            }
        }
    }
*
*
* Now we made the insertSong() and insertAlbum() throw the SQLException instead of handling it is because it goes
* back to what we said in Transactions and the banking example - we initially have 2 inserts happening when we call
* the insertSong() method - we insert an artist then the album - and if any one of them fails we want to backout and
* do a rollback on the current changes - hence by throwing an SQLException, the method will go to the catch block and
* run the rollback() method which reverts the DB to its previous state before the updates
*
* We first check if the song is already on file, is so we exit out of the insert() method simply to avoid duplicates
* on file
*
* We set the autoCommit() behaviour to false - this allows us to control the transaction as one whole transaction -
* we want one transaction for the whole insertion of song and not separate ones
*
* We do the usual - and check if only one has been updated - if so commit the changes to the DB and that will end the
* transaction
*
* In our finally block we set the autoCommit behaviour back to true - because we want the default behaviour for all
* C, U and D in the CRUD for DB queries
*
* We set the Auto commit false here because we wanted only 1 transaction for the song insertion - as there are a lot
* of steps involved (inserting an artist then album) any of those inserts can fail - and if so we need to immediately
* backout and rollback
*
    public void insertSong(String title, String artist, String album, int track) {
        try {
            querySongs.setString(1, title);
            ResultSet results = querySongs.executeQuery();

            if(results.next()) {
                System.out.printf("Song %s already on file exiting insertion\n", title);
                return;
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving Song: " + e.getMessage());
        }

        try {
            conn.setAutoCommit(false);

            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);

            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);

            int affectedRows = insertIntoSongs.executeUpdate();

            if (affectedRows == 1) {
                System.out.println("Song was inserted");
                conn.commit();
            } else {
                throw new SQLException("The Song insert failed");
            }

        } catch (SQLException e) {
            System.out.println("Error Inserting Song: " + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Error Rolling Back - backup Db may be corrupted: " + e.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behaviour");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error setting Auto-Commit to true: " + e.getMessage());
            }
        }

    }
*
* There is a hidden bug in the code above, where we try catch a SQLException when we try to insert a song into the
* file, we can get this error
*
* If we change this line to
*   insertIntoSongs.setInt(3, albumId);
*
* We actually get an ArrayOutOfBoundsException - this in turn does not run the rollback() method and goes
* to the finally block where set autoCommit() back to true
*   insertIntoSongs.setInt(8, albumId);
*
* Setting the autoCommit() to true has the side effect of committing to the DB - and in this case we successfully
* insert both artist and album into the DB which gets committed - these 2 are linked to one another but there is no
* song on file to link them the two with each other
*
* so changing the catch from catching SQLExceptions was can simply catch all types of Exceptions which will cover all
* cases because we really want the rollback() code run
*
* rollback()
* This method can actually accept a SavePoint object - this pretty much a save at a specific point in the DB
* lifecycle and we can rollback to that SavePoint by creating it in the lifecycle of the program and rolling back on
* that SavePoint when it makes sense to
*
*
*
*
* */
