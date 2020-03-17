package com.company;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    private static PlayList playList = new PlayList();

    public static void main(String[] args) {
        // Create a program that implements a playlist for songs
        // Create a Song class having Title and Duration for a song.
        // The program will have an Album class containing a list of songs.
        // The albums will be stored in an ArrayList
        // Songs from different albums can be added to the playlist and will appear in the list in the order
        // they are added.
        // Once the songs have been added to the playlist, create a menu of options to:-
        // Quit,Skip forward to the next song, skip backwards to a previous song.  Replay the current song.
        // List the songs in the playlist
        // A song must exist in an album before it can be added to the playlist (so you can only play songs that
        // you own).
        // Hint:  To replay a song, consider what happened when we went back and forth from a city before we
        // started tracking the direction we were going.
        // As an optional extra, provide an option to remove the current song from the playlist
        // (hint: listiterator.remove()

        Album album = new Album("Orion");

        album.addSong("Quasar", 90.0);
        album.addSong("Nebula", 120.0);
        album.addSong("Event Horizon", 140.0);
        album.addSong("Pillars", 150.0);
        album.addSong("Orion", 60.0);
        playList.addAlbum(album);

        album = new Album("Rainy");
        album.addSong("Thunder", 150.45);
        album.addSong("Tempest", 120.20);
        album.addSong("Rainy", 60.5);
        album.addSong("Silence", 70.89);
        playList.addAlbum(album);

        playList.addSongToPlayList(0, "Star");
        playList.addSongToPlayList(0, "Quasar");
        playList.addSongToPlayList(1, "Rainy");
        playList.addSongToPlayList(0, "Nebula");
        playList.addSongToPlayList(1, "Thunder");
        playList.addSongToPlayList(0, "Pillars");
        playList.addSongToPlayList(0, "Pillars");

        playList.play();

        // Modify the playlist challenge so that the Album class uses an inner class.
        // Instead of using an ArrayList to hold its tracks, it will use an inner class called SongList
        // The inner SongList class will use an ArrayList and will provide a method to add a song.
        // It will also provide findSong() methods which will be used by the containing Album class
        // to add songs to the playlist.
        // Neither the Song class or the Main class should be changed.
    }





}
