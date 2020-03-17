package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeagueTable<T extends Team> {
    private List<T> teams = new ArrayList<>();

    public void printLeagueTable() {
        Collections.sort(teams);
        for(T team : teams) {
            System.out.println(team);
        }
    }

    public boolean addTeam(T team) {
        if(teams.contains(team)) {
            return false;
        }
        teams.add(team);
        return true;
    }
}
