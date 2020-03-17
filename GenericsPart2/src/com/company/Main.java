package com.company;

public class Main {

    public static void main(String[] args) {
	    FootballPlayer joe = new FootballPlayer("Joe");
	    BaseballPlayer pat = new BaseballPlayer("Pat");
	    SoccerPlayer beckham = new SoccerPlayer("Beckham");

	    Team<FootballPlayer> adelaideCrows = new Team<>("AdelaideCrows");
	    adelaideCrows.addPlayer(joe);
//	    adelaideCrows.addPlayer(pat);
//	    adelaideCrows.addPlayer(beckham);

		System.out.println(adelaideCrows.numPlayers());

		Team<BaseballPlayer> baseballTeam = new Team<>("Chicago Cubs");
		baseballTeam.addPlayer(pat);

		Team<SoccerPlayer> brokenTeam = new Team<>("Won't work");
		brokenTeam.addPlayer(beckham);

		Team<FootballPlayer> melbourne = new Team<>("Melbourne");
		FootballPlayer banks = new FootballPlayer("Gordon");
		melbourne.addPlayer(banks);

		Team<FootballPlayer> hawthorn = new Team<>("Hawthorn");
		Team<FootballPlayer> fremantle = new Team<>("Fremantle");

		hawthorn.matchResult(fremantle, 1, 0);
		hawthorn.matchResult(adelaideCrows, 3, 8);

		adelaideCrows.matchResult(fremantle, 2, 1);
//		adelaideCrows.matchResult(baseballTeam, 1, 1);

		System.out.println("Rankings");
		System.out.println(adelaideCrows.getName() + ": " + adelaideCrows.ranking());
		System.out.println(melbourne.getName() + ": " + melbourne.ranking());
		System.out.println(fremantle.getName() + ": " + fremantle.ranking());
		System.out.println(hawthorn.getName() + ": " + hawthorn.ranking());

		System.out.println(adelaideCrows.compareTo(melbourne));
		System.out.println(adelaideCrows.compareTo(hawthorn));
		System.out.println(hawthorn.compareTo(adelaideCrows));
		System.out.println(melbourne.compareTo(fremantle));
    }

}

/*
* Creating your own generic classes
* So in the class definition, you want to add the diamond (angle brackets) and inside of it will be the uppercase
* letter 'T' - Java will replace that T (think of it as a parameter in this case) with the actual type that is passed
* to the class
* 	public class Team<T> {...}
*
* And in the the ArrayList we made to hold the players, we also pass in T to its diamond - so we are passing the type
* parameter that the class Team will receive when instantiated
* 	private ArrayList<T> members = new ArrayList<>();
*
* And any methods within the Team class which would have in-fact received the Player object will now be replaced with
* the letter T as well
* 	public boolean addPlayer(T player) {...}
*
* Now we run into errors here within the addPlayer method when we call player.getName(), because at the time of
* coding, Java does not know what object this will holding, so there are ways to go around it, and that is casting
* the call to Player
* 	<snippet>---(((Player) player).getName() + " is already on this team")
* NOTE: When casting a reference variable to another Object type, make sure that the cast is directed at the
* reference variable and nothing else so
*
* 	( (Player) player ) --> reference variable is wrapped around a bracket, this is because we have to use another
* bracket to cast the variable to Player object so in total 2 brackets were used
* 	( ( (Player) player ).getName() + " is already on this team" )
*
* Once that's done, we can now use the dot notation to call methods within the Player class, !See how we are calling
* getName() with the dot (.) outside of the bracket, and not within player variable, this is because we have to cast
* the actual reference, before getting access to any of the Player methods!
*
* Restrict Type Parameter
* Within our own defined class, we can actually provide any object within the diamond, this means that any Object is
* valid as an argument to this generic - now this a problem because we can provide a String object to Team
* 	Team<String> brokenTeam = new Team<>("Won't work");
* 	brokenTeam.addPlayer("no-one");
*
* Now the problem is within the method addPlayer - we do a cast of parameter T to Player, now String cannot be casted
*  to Player and obviously throw an error there
*
* Java has this thing called parameter upper bound, where we restrict the parameters the class T can accept (almost
* like how we define the data type of the a parameter within a method but a different of course)
* 	public class Team<T extends Player> {...}
*
* We are telling Java that the type parameter passed, must be an Object that extends Player or is a subclass of player,
* and by typing it with 'extends Player' we are saying that Player is the upper bound of T
*
* Now the error with passing in String as the type parameter, will throw an error in the IDE and Java compile time
* saying that String is not a subclass of Player - so pass a different object of type Player
* But as a good consequence of that, we can get rid of the cast within addPlayer(), because we said that T's upper
* bound is of type Player, so Java has an idea that any object type passed to Team - will be of type Player
*	<snippet>---(((Player) player).getName() + " is already on this team")
* 								to
* 	<snippet>---(player.getName() + " is already on this team");
*
* Few points
* 	- Classes and interfaces can use generics - this ensures that both interfaces and classes are operating on 'X'
* 	object type
* 	- In Team we used only 1 bound Player, but you could instead have multiple bounds, but they will have to be
* 	implementing interfaces
* 	- If implementing multiple bounds, class must be listed first, then interfaces
* 	Team<T extends Player & Coach & Manager) after Player we place ampersand (&) and list the interfaces
*
* We can have a generic within a generic
* So in Team we implement an interface called Comparable<> which takes an object type as a generic, and from there we
*  pass in Team, but we want only Teams that are of same type - so Football vs. Football etc so we pass another
* diamond but this time to Team with type T, the type that extends from Player when initialised
* 	<snippet> implements Comparable< Team< T > >
*
* */
