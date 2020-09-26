package music.db;

import music.core.Album;
import music.core.Artist;
import music.core.SongArtist;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "music.db";

    public static final String CONNECTION_STRING = "jdbc:sqlite:" + "music.db\\src\\music\\db\\" + DB_NAME;

    // album table
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUMS_ID = "_id";
    public static final String COLUMN_ALBUMS_NAME = "name";
    public static final String COLUMN_ALBUMS_ARTIST = "artist";
    public static final int INDEX_ALBUMS_ID = 1;
    public static final int INDEX_ALBUMS_NAME = 2;
    public static final int INDEX_ALBUMS_ARTIST = 3;

    // artists table
    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTISTS_ID = "_id";
    public static final String COLUMN_ARTISTS_NAME = "name";
    public static final int INDEX_ARTISTS_ID = 1;
    public static final int INDEX_ARTISTS_NAME = 2;

    // songs table
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONGS_ID = "_id";
    public static final String COLUMN_SONGS_TRACK = "track";
    public static final String COLUMN_SONGS_TITLE = "title";
    public static final String COLUMN_SONGS_ALBUM = "album";
    public static final int INDEX_SONGS_ID = 1;
    public static final int INDEX_SONGS_TRACK = 2;
    public static final int INDEX_SONGS_TITLE = 3;
    public static final int INDEX_SONGS_ALBUM = 4;

    // Query Constant for Artists
    public static final String QUERY_ARTIST = String.format(
            "SELECT * FROM %s ", TABLE_ARTISTS
    );

    public static final String QUERY_ARTISTS_SORT = String.format(
            "ORDER BY %s COLLATE NOCASE ", COLUMN_ARTISTS_NAME
    );

    // Query Constant for Albums
    public static final String QUERY_ALBUMS_BY_ARTIST_START = String.format(
                    "SELECT %s.%s FROM %s " +
                    "INNER JOIN %s " +
                    "ON %s.%s = %s.%s " +
                    "WHERE %s.%s = \"",
                    TABLE_ALBUMS, COLUMN_ALBUMS_NAME, TABLE_ALBUMS,
                    TABLE_ARTISTS,
                    TABLE_ALBUMS, COLUMN_ALBUMS_ARTIST, TABLE_ARTISTS, COLUMN_ARTISTS_ID,
                    TABLE_ARTISTS, COLUMN_ARTISTS_NAME);

    public static final String QUERY_BY_ALBUMS_ARTIST_SORT = String.format(
            "ORDER BY %s.%s COLLATE NOCASE ", TABLE_ALBUMS, COLUMN_ALBUMS_NAME);

    // Query for albums joined on artists
    public static final String QUERY_FOR_SONG_ARTIST = String.format(
                    "SELECT %s.%s AS \"Artists Name\", %s.%s AS \"Album Name\", %s.%s FROM %s " +
                    "INNER JOIN %s ON %s.%s = %s.%s " +
                    "INNER JOIN %s ON %s.%s = %s.%s " +
                    "WHERE %s.%s = \"",
            TABLE_ARTISTS, COLUMN_ARTISTS_NAME, TABLE_ALBUMS,
            COLUMN_ALBUMS_NAME, TABLE_SONGS, COLUMN_SONGS_TRACK,
            TABLE_SONGS,

            TABLE_ALBUMS, TABLE_SONGS, COLUMN_SONGS_ALBUM,
            TABLE_ALBUMS, COLUMN_ALBUMS_ID,

            TABLE_ARTISTS, TABLE_ALBUMS, COLUMN_ALBUMS_ARTIST,
            TABLE_ARTISTS, COLUMN_ARTISTS_ID,

            TABLE_SONGS, COLUMN_SONGS_TITLE
    );

    public static final String QUERY_FOR_SONG_ARTIST_SORT = String.format(
            "ORDER BY %s.%S, %s.%s COLLATE NOCASE ",
            TABLE_ARTISTS, COLUMN_ARTISTS_NAME,
            TABLE_ALBUMS, COLUMN_ALBUMS_NAME
    );

    // View for Songs, Artists and Albums : cols Artist Name, Album Name Song Track and Song Title
    public static final String VIEW_ARTISTS_SONG = "artists_list";
    public static final String CREATE_VIEW_ARTISTS_FOR_SONG = String.format(
                    "CREATE VIEW IF NOT EXISTS %s AS " +
                    "SELECT %s.%s AS \"Artists Name\", %s.%s AS \"Albums Name\", %s.%s, %s.%s FROM %s " +
                    "INNER JOIN %s ON %s.%s = %s.%s " +
                    "INNER JOIN %s ON %s.%s = %s.%s " +
                    "ORDER BY %s.%s, %s.%s, %s.%s ",
            VIEW_ARTISTS_SONG,

            TABLE_ARTISTS, COLUMN_ARTISTS_NAME, TABLE_ALBUMS, COLUMN_ALBUMS_NAME, TABLE_SONGS, COLUMN_SONGS_TRACK,
            TABLE_SONGS, COLUMN_SONGS_TITLE, TABLE_SONGS,

            TABLE_ALBUMS, TABLE_SONGS, COLUMN_SONGS_ALBUM, TABLE_ALBUMS, COLUMN_ALBUMS_ID,

            TABLE_ARTISTS, TABLE_ALBUMS, COLUMN_ALBUMS_ARTIST, TABLE_ARTISTS, COLUMN_ARTISTS_ID,

            TABLE_ARTISTS, COLUMN_ARTISTS_NAME, TABLE_ALBUMS, COLUMN_ALBUMS_NAME, TABLE_SONGS, COLUMN_SONGS_TRACK

    );


    // Query Song-Artist-Album View
    public static final String QUERY_VIEW_SONG_INFO = String.format(
            "SELECT \"%s\", \"%s\", %s FROM %s " +
            "WHERE %s = \"",

            "Artists Name", "Albums Name", COLUMN_SONGS_TRACK, VIEW_ARTISTS_SONG,
            COLUMN_SONGS_TITLE
    );

    public static final String QUERY_VIEW_SONG_INFO_PREP = String.format(
            "SELECT \"%s\", \"%s\", %s FROM %s " +
             "WHERE %s = ?",
            "Artists Name", "Albums Name", COLUMN_SONGS_TRACK, VIEW_ARTISTS_SONG,
            COLUMN_SONGS_TITLE
    );

    // Insert Artist, Album and Song singular record
    public static final String INSERT_ARTIST = String.format(
            "INSERT INTO %s(%s) VALUES(?)",
            TABLE_ARTISTS, COLUMN_ARTISTS_NAME
    );

    public static final String INSERT_ALBUM = String.format(
            "INSERT INTO %s(%s, %s) VALUES(?, ?)",
            TABLE_ALBUMS, COLUMN_ALBUMS_NAME, COLUMN_ALBUMS_ARTIST
    );

    public static final String INSERT_SONG = String.format(
            "INSERT INTO %s(%s, %s, %s) VALUES(?, ?, ?)",
            TABLE_SONGS, COLUMN_SONGS_TRACK, COLUMN_SONGS_TITLE, COLUMN_SONGS_ALBUM
    );

    public static final String QUERY_ARTIST_FOR_ID = String.format(
            "SELECT %s FROM %s WHERE %s = ?",
            COLUMN_ARTISTS_ID, TABLE_ARTISTS, COLUMN_ARTISTS_NAME
    );

    public static final String QUERY_ALBUM_FOR_ID = String.format(
            "SELECT %s FROM %s WHERE %s = ?",
            COLUMN_ALBUMS_ID, TABLE_ALBUMS, COLUMN_ALBUMS_NAME
    );

    public static final String QUERY_SONG_FOR_ID = String.format(
            "SELECT %s FROM %s WHERE %s = ?",
            COLUMN_SONGS_ID, TABLE_SONGS, COLUMN_SONGS_TITLE
    );

    public static final String QUERY_ALBUMS_BY_ARTIST_ID = String.format(
            "SELECT * FROM %s " +
            "WHERE %s = ? " +
            "ORDER BY %s COLLATE NOCASE",
            TABLE_ALBUMS, COLUMN_ALBUMS_ARTIST, COLUMN_ALBUMS_NAME
    );

    // Update Artist
    public static final String QUERY_UPDATE_ARTIST = String.format(
            "UPDATE %s " +
             "SET %s = ? " +
             "WHERE %s = ?",
             TABLE_ARTISTS, COLUMN_ARTISTS_NAME, COLUMN_ARTISTS_ID
    );

    public enum Order {
        ORDER_BY_NONE,
        ORDER_BY_ASC,
        ORDER_BY_DESC

    }

    private Connection conn;
    private PreparedStatement querySongInfoView;
    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;
    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;
    private PreparedStatement querySongs;
    private PreparedStatement queryAlbumsByArtist;
    private PreparedStatement queryUpdateArtist;

    private static Datasource instance = new Datasource();

    private Datasource() { }

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUM, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONG);
            queryArtist = conn.prepareStatement(QUERY_ARTIST_FOR_ID);
            queryAlbum = conn.prepareStatement(QUERY_ALBUM_FOR_ID);
            querySongs = conn.prepareStatement(QUERY_SONG_FOR_ID);
            queryAlbumsByArtist = conn.prepareStatement(QUERY_ALBUMS_BY_ARTIST_ID);
            queryUpdateArtist = conn.prepareStatement(QUERY_UPDATE_ARTIST);

            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect to the database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if(querySongInfoView != null) {
                querySongInfoView.close();
            }

            if(insertIntoArtists != null) {
                insertIntoArtists.close();
            }

            if(insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }

            if(insertIntoSongs != null) {
                insertIntoSongs.close();
            }

            if(queryArtist != null) {
                queryArtist.close();
            }

            if(queryAlbum != null) {
                queryAlbum.close();
            }

            if(querySongs != null) {
                querySongs.close();
            }

            if(queryAlbumsByArtist != null) {
                queryAlbumsByArtist.close();
            }

            if (queryUpdateArtist != null) {
                queryUpdateArtist.close();
            }

            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing database connection: " + e.getMessage());
        }
    }

    /*
    * Ideally we would use try-with-resources to open the connection to the DB, but in this example we are enforcing
    * ourselves to make sure we manually close the DB connection ourselves to hammer the point to always close
    * connection or DB objects that are occupying resources
    *
    * EDIT: This method will open a connection to the DB, but as it however escapes from the try-catch block after
    * executing the connection is then closed - any code that attempts to use the DB will throw a null pointer
    * exception because there is no DB object to work with
    * */
    public boolean connect() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect to the database: " + e.getMessage());
            return false;
        }
    }

    public List<SongArtist> queryForSongArtist(String title, Order sortOrder) {
        // SELECT artists.name AS "Artist Name", albums.name AS "Album Name", songs.track FROM songs
        //INNER JOIN albums ON songs.album = albums._id
        //INNER JOIN artists ON albums.artist = artists._id
        //WHERE songs.title = "Heartless";

        StringBuilder sqlStatement = new StringBuilder(QUERY_FOR_SONG_ARTIST).append(String.format("%s\" ", title));

        if(sortOrder != Order.ORDER_BY_NONE) {
            sqlStatement.append(QUERY_FOR_SONG_ARTIST_SORT);
            if(sortOrder == Order.ORDER_BY_DESC) {
                sqlStatement.append("DESC");
            } else {
                sqlStatement.append("ASC");
            }
        }

        System.out.println("SQL Statement: " + sqlStatement.toString());


        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sqlStatement.toString())) {

            List<SongArtist> songArtistList = new ArrayList<>();

            retrieveRecordData(results, songArtistList);

            return songArtistList;

        } catch (SQLException e) {
            System.out.println("Error parsing SQL Statement: " + e.getMessage());
            return null;
        }
    }

    public List<Artist> queryArtist(Order sortOrder) {

        // SELECT * FROM artists
        // ORDER BY name COLLATE NOCASE ASC

        StringBuilder sb = new StringBuilder(QUERY_ARTIST);
        if(sortOrder != Order.ORDER_BY_NONE) {
            sb.append(QUERY_ARTISTS_SORT);
            if(sortOrder == Order.ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        System.out.println("SQL Statement: " + sb.toString());


        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted: " + e.getMessage());
                }
                Artist artist = new Artist();
                artist.setId(results.getInt(INDEX_ARTISTS_ID));
                artist.setName(results.getString(INDEX_ARTISTS_NAME));

                artists.add(artist);
            }
            return artists;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryAlbumsForArtist(String artistName, Order sortOrder) {

        //SELECT albums.name FROM albums
        //INNER JOIN artists
        //ON albums.artist = artists._id
        //WHERE artists.name = "Pink Floyd"
        //ORDER BY albums.name COLLATE NOCASE ASC;

        StringBuilder sqlStatement = new StringBuilder(
                (String.format(QUERY_ALBUMS_BY_ARTIST_START + "%s\" ", artistName)));

        if(sortOrder != Order.ORDER_BY_NONE) {
            sqlStatement.append(QUERY_BY_ALBUMS_ARTIST_SORT);

            if(sortOrder == Order.ORDER_BY_DESC) {
                sqlStatement.append("DESC");
            } else {
                sqlStatement.append("ASC");
            }
        }

        System.out.println("SQL Statement " + sqlStatement.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sqlStatement.toString())){

            List<String> queryAlbumsForArtistList = new ArrayList<>();

            while (results.next()) {
                queryAlbumsForArtistList.add(results.getString(1));
            }

            return queryAlbumsForArtistList;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }

    }
    
    public void querySongsMetadata() {
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

    public int[] getCount(String table) {
        String sqlStatement = String.format("SELECT COUNT(*) AS \"Count\", MIN(_id) AS \"Minimum ID\"  FROM %s", table);

        try (Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery(sqlStatement)){

            int count = results.getInt(1);
            int min = results.getInt(2);

            return new int[]{count, min};
        } catch (SQLException e) {
            System.out.println("Query Failed: " + e.getMessage());
            return new int[]{-1};
        }
     }

    public boolean createViewForSongsArtists() {
        //CREATE VIEW IF NOT EXISTS artists_list AS
        //SELECT artists.name AS "Artists Name", albums.name AS "Albums Name", songs.track, songs.title FROM songs
        //INNER JOIN albums ON songs.album = albums._id
        //INNER JOIN artists ON albums.artist = artists._id
        //ORDER BY artists.name, albums.name, songs.track;

        try (Statement statement = conn.createStatement()) {
            statement.execute(CREATE_VIEW_ARTISTS_FOR_SONG);
            System.out.printf("%s was created\n", VIEW_ARTISTS_SONG);
            return true;
        } catch (SQLException e) {
            System.out.println("Create View Failed: " + e.getMessage());
            return false;
        }
    }

//    public List<SongArtist> querySongInfoView(String title) {
//        try (Statement statement = conn.createStatement();
//        ResultSet results = statement.executeQuery(QUERY_VIEW_SONG_INFO + String.format("%s\"", title))){
//            List<SongArtist> songArtistList= new ArrayList<>();
//
//            retrieveRecordData(results, songArtistList);
//
//            return songArtistList;
//        } catch (SQLException e) {
//            System.out.println("Query Failed: " + e.getMessage());
//            return null;
//        }
//    }

    public List<SongArtist> querySongInfoView(String title) {
        //SELECT "Artists Name", "Albums Name", track FROM artists_list
        //WHERE title = "Go Your Own Way";
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

    public List<Album> queryAlbumForArtistId(int artistId) {
        try {
            queryAlbumsByArtist.setInt(1, artistId);
            ResultSet results = queryAlbumsByArtist.executeQuery();

            List<Album> albumsList  = new ArrayList<>();

            while (results.next()) {
                Album album = new Album();

                album.setId(results.getInt(1));
                album.setName(results.getString(2));
                album.setArtistId(artistId);

                albumsList.add(album);
            }

            return albumsList;

        } catch (SQLException e) {
            System.out.println("Query Failed: " + e.getMessage());
            return null;
        }
    }

    public boolean updateArtistById(int artistId, String name) {
        try {
            queryUpdateArtist.setString(1, name);
            queryUpdateArtist.setInt(2, artistId);

            int affectedRows = queryUpdateArtist.executeUpdate();

            if(affectedRows != 1) {
                throw new SQLException("Update Error affected the current row");
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Update Failed: " + e.getMessage());
            return false;
        }
    }

    private void retrieveRecordData(ResultSet results, List<SongArtist> songArtistList) throws SQLException {
        while (results.next()) {
            SongArtist songArtist = new SongArtist();

            songArtist.setArtistName(results.getString(1));
            songArtist.setAlbumName(results.getString(2));
            songArtist.setTrack(results.getInt(3));

            songArtistList.add(songArtist);
        }
    }


    private int insertArtist(String name) throws SQLException {
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

    private int insertAlbum(String name, int artistId) throws SQLException {
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

    public void insertSong(String title, String artist, String album, int track) {
        try {
            querySongs.setString(1, title);
            ResultSet results = querySongs.executeQuery();

            if(results.next()) {
                System.out.printf("Song \"%s\" already on file exiting insertion\n", title);
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
                System.out.printf("Song \"%s\" was inserted", title);
                conn.commit();
            } else {
                throw new SQLException("The Song insert failed");
            }

        } catch (Exception e) {
            System.out.printf("Error Inserting Song \"%s\": %s\n", title, e.getMessage());
            try {
                System.out.println("Performing Rollback");
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Error Rolling Back - backup DB may be corrupted: " + e.getMessage());
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


}