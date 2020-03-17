package com.company;

import java.util.ArrayList;

public class Album {
    private String albumName;
    private SongList songs;

    public Album(String albumName) {
        this.albumName = albumName;
        this.songs = new SongList();

    }

    public void addSong(String title, double duration) {
        songs.addSong(title, duration);
    }

    public Song getSong(String title) {
        if(isSongAdded(title)){
            return songs.findSong(title);
        }
        return null;
    }

    private boolean isSongAdded(String title) {
        return songs.findSong(title) != null;
    }



    private class SongList {
        private ArrayList<Song> songs;

        public SongList() {
            this.songs = new ArrayList<>();
        }

        public void addSong(String title, double duration) {
            songs.add(new Song(title, duration));
        }

        public Song findSong(String title) {
            for(Song song: songs) {
                if(song.getTitle().equalsIgnoreCase(title)) {
                    return song;
                }
            }
            System.out.println("Song " +  title + " not in Album " + albumName);
            return null;
        }
    }
}
