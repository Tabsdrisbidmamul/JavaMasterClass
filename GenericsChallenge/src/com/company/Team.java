package com.company;

import java.util.ArrayList;
import java.util.List;

public class Team<T extends Player> implements Comparable<Team<T>> {
    private String name;
    private int won;
    private int lost;
    private int tied;
    private int played;

    private List<T> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
        this.won = 0;
        this.lost = 0;
        this.tied = 0;
        this.played = 0;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPlayers() {
        return this.members.size();
    }

    public boolean addPlayer(T player) {
        if(members.contains(player)) {
            System.out.println(player.getName() + " already on the team");
            return false;
        } else {
            members.add(player);
            System.out.println(player.getName() + " added to " + this.name);
            return true;
        }
    }

    public void matchResult(Team<T> opponent, int ourScore, int theirScore) {
        String msg;
        if(ourScore > theirScore) {
            won++;
            msg = " beat ";
        } else if(ourScore == theirScore) {
            tied++;
            msg = " drew with ";
        } else {
            lost++;
            msg = " lost to ";
        }
        played++;
        if (opponent != null) {
            System.out.println(this.getName() + msg + opponent.getName());
            opponent.matchResult(null, theirScore, ourScore);
        }
    }

    public int ranking() {
        return (won * 2) + tied;
    }

    @Override
    public int compareTo(Team<T> team) {
        if(this.ranking() > team.ranking()) {
            return -1;
        } else if(this.ranking() < team.ranking()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Team: " + name + ": " + ranking() + "";
    }
}
