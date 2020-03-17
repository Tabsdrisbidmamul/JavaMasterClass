package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class PlayList {
    private LinkedList<Song> playList;
    private ArrayList<Album> album;

    public PlayList() {
        this.album = new ArrayList<>();
        this.playList = new LinkedList<>();
    }

    public void showPlayList() {
        System.out.println("Now showing ");
        for (Song song : playList) {
            System.out.println("\t" + song);
        }
    }


    public void addSongToPlayList(int albumPos, String title) {
        Song song = album.get(albumPos).getSong(title);
        if(song != null) {
            playList.add(song);
        }
    }

    public void addAlbum(Album album) {
        this.album.add(album);
    }


    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean goingForward = true;
        ListIterator<Song> listIterator = playList.listIterator();

        if(playList.isEmpty()) {
            System.out.println("There are no songs in your playlist");
        } else {
            System.out.println("Now playing " + listIterator.next());
            printMenu();
        }

        while (!quit) {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Turning off playlist");
                    quit = true;
                    break;
                case 1:
                    if(!goingForward) {
                        if(listIterator.hasNext()) {
                            listIterator.next();
                        }
                        goingForward = true;
                    }
                    if(listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next());
                    } else {
                        System.out.println("Reached the end of the playlist");
                        goingForward = false;
                    }
                    break;
                case 2:
                    if(goingForward){
                        if(listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        goingForward = false;
                    }
                    if(listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous());
                    } else {
                        System.out.println("At the start of the playlist");
                        goingForward = true;
                    }
                    break;
                case 3:
                    if(goingForward){
                        if(listIterator.hasPrevious()) {
                            System.out.println("Now Replaying " + listIterator.previous());
                        }
                        goingForward = false;
                    } else if(!goingForward) {
                        if(listIterator.hasNext()) {
                            System.out.println("Now Replaying " + listIterator.next());
                        }
                        goingForward = true;
                    }
                    break;
                case 4:
                    if(playList.size() > 0) {
                        System.out.println("Now Removing song from playlist");
                        listIterator.remove();
                        if(listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next());
                        } else if(listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous());
                        }
                    }
                    break;
                case 5:
                    showPlayList();
            }
        }

    }

    private void printMenu() {
        System.out.println("Available actions:\npress");
        System.out.println("0 - to quit\n" +
                "1 - to skip to next song\n" +
                "2 - to go back to previous song\n" +
                "3 - to replay current song\n" +
                "4 - to remove current song from playlist\n" +
                "5 - to show play list");
    }
}
