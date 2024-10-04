package mavenproject.connect_four;

import java.util.Scanner;

import mavenproject.connect_four.exceptions.ColumnFullException;
import mavenproject.connect_four.exceptions.InvalidBoardSizeException;
import mavenproject.connect_four.exceptions.InvalidMoveException;

public class Game {
	   private Player[] players;
	    private Board board;
	    private static Scanner scanner = new Scanner(System.in);

	    public Game() {
	        // Let's default it two players for now. Later, you can improve upon this to allow the game creator to choose how many players are involved.
	        this.players = new Player[2]; // complete line.
	        this.board = new Board(); // complete line
	    }

	    public void setUpGame() throws InvalidBoardSizeException {
	        System.out.println("Enter player 1's name: ");
	        players[0] = new Player(scanner.nextLine(), "1");
	        System.out.println("Enter player 2's name: ");
	        String playerTwoName = scanner.nextLine();
	        /** add logic to prevent a user from giving a second name that's equal to the first. Allow the user to try as long as the names are not different.*/
	        while(playerTwoName.equalsIgnoreCase(players[0].getName())){
	            System.out.println("Error! Both Players cannot have the same name.");
	            System.out.println("Enter player 2's name: ");
	            playerTwoName = scanner.nextLine();
	        }
	        /* wrap the code in here with a conditional block that enables the check described above. 
	        
	            System.out.println("Error! Both Players cannot have the same name.");
	            System.out.println("Enter player 2's name: ");
	            playerTwoName = scanner.nextLine();
	        
	        */
	        players[1] = new Player(playerTwoName, "2");

	        // set up the board using the appropriate method
			board.boardSetUp();
	        // print the board the using appropriate method
	        board.printBoard();
	    }

	    public void printWinner(Player player) {
	        // Use an appropriate Board class method to check if player is a winner
	        if(board.checkIfPlayerIsTheWinner(player.getPlayerNumber())){
	            System.out.println(player.getName() + " is the winner");
	        } 
	    }

	    public void playerTurn(Player currentPlayer) throws InvalidMoveException, ColumnFullException {
	        int col = currentPlayer.makeMove();
	       
	        if (!board.boardFull()) {
	           // call board method to add token.
	           board.addToken(col, currentPlayer.getPlayerNumber());
	        }
	        // print board
	        board.printBoard();
	    }

	    public void play(){
	        boolean noWinner = true;
	        
	        try {
	            this.setUpGame();
	        } catch (InvalidBoardSizeException e) {
	            System.out.println(e.getMessage());
	            return;
	        }
	        
	        int currentPlayerIndex = 0;
	        
	        // Check if there is no winner, if true it will keep running if false it will print the winner and terminate the program.
	        while (noWinner) {
	            // provide condition
	            if (board.boardFull()) {
	                System.out.println("Board is now full. Game Ends.");
	                return;
	            }

	            Player currentPlayer = players[currentPlayerIndex];
	            // Override default tostring for Player class
	            System.out.println(toString(currentPlayer));
	            
	            // Call playerTurn() method of Game class and handle any exception it may generate 
	            try {
	                playerTurn(currentPlayer);
	            } catch (InvalidMoveException | ColumnFullException e) {
	                System.out.println(e.getMessage());
	                currentPlayerIndex = (currentPlayerIndex == 0) ? 1 : 0; // reassign the variable to allow the game to continue. Note the index would wrap back to the first player if we are at the end. Think of using modulus (%).
	                continue;
	            }
	            
	            // Check if the current player is a winner and if true print the winner
	            if (board.checkIfPlayerIsTheWinner(currentPlayer.getPlayerNumber())) {
	                printWinner(currentPlayer);
	                noWinner = false;
	            } else { // Change the current player to the other player
	                currentPlayerIndex = (currentPlayerIndex == 0) ? 1 : 0; // reassign the variable to allow the game to continue. Note the index would wrap back to the first player if we are at the end. Think of using modulus (%).
	            }
	        }
	    }
	    
	    // To display who's turn it's upon called.
	    public String toString(Player currentPlayer) {
	    	return "It is player " + currentPlayer.getPlayerNumber() + "'s turn. " + currentPlayer;
	    }
}
