package mavenproject.connect_four;

import java.util.Scanner;

public class Player {
	private String name;
    // Add other instance variable(s)
    private String playerNumber;
    // Question: should scanner be static or not?
    private static Scanner scanner = new Scanner(System.in);// complete line

    public Player(String name, String playerNumber) {
        // complete constructor
    	this.name = name;
    	this.playerNumber = playerNumber;
    }

    // create getter methods
    // Return a player name
    public String getName(){
      return name;
    }
    
    // Return the player number
    public String getPlayerNumber(){
      return playerNumber;
    }
    
    // Read the user input, which column they want to put, and returns it
    public int makeMove() {
        System.out.println("Make your move. What column do you want to put a token in?");
        int column = scanner.nextInt(); // receive user input
        return column;
    }
    
    // Print player information upon called
    public String toString() {
        return ("Player " + playerNumber + " is " + name);
    }
}
