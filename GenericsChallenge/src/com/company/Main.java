package com.company;

public class Main {

    public static void main(String[] args) {
	    // ArrayList<Teams> teams;
        // Collections.sort(teams);

        // Create a generic class to implement a league table for a sport.
        // The class should allow teams to be added to the list, and store
        // a list of teams that belong to the league.
        //
        // Your class should have a method to print out the teams in order,
        // with the team at the top of the league printed first.
        //
        // Only teams of the same type should be added to any particular
        // instance of the league class - the program should fail to compile
        // if an attempt is made to add an incompatible team.

        Team<Football> f1 = new Team<>("Parker-Run");
        Team<Football> f2 = new Team<>("Rippers");
        Team<Football> f3 = new Team<>("Hoopers");

        Team<Rugby> r1 = new Team<>("Rigby's");
        Team<Rugby> r2 = new Team<>("Whoppers");
        Team<Rugby> r3 = new Team<>("Lippers");

        f1.matchResult(f2, 3, 5);
        f1.matchResult(f3, 6, 2);
        f2.matchResult(f3, 8, 2);

        System.out.println();

        r1.matchResult(r2, 10, 5);
        r1.matchResult(r3, 15, 8);
        r2.matchResult(r3, 5, 20);

        System.out.println();

        LeagueTable<Team<Football>> l1 = new LeagueTable<>();
        l1.addTeam(f1);
        l1.addTeam(f2);
        l1.addTeam(f3);
//        l1.addTeam(r1);
        l1.printLeagueTable();

        System.out.println();

        LeagueTable<Team<Rugby>> l2 = new LeagueTable<>();
        l2.addTeam(r1);
        l2.addTeam(r2);
        l2.addTeam(r3);
//        l2.addTeam(f1);
        l2.printLeagueTable();

    }
}
