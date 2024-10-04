package mavenproject.connect_four;

import java.util.Scanner;

public class Game {
	   private Player[] players;
	    private Board board;
	    private static Scanner scanner = new Scanner(System.in);

	    public Game() {
	        // Let's default it two players for now. Later, you can improve upon this to allow the game creator to choose how many players are involved.
	        this.players = new Player[2]; // complete line.
	        this.board = new Board(); // complete line
	    }

	    public void setUpGame() {
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

	    public void playerTurn(Player currentPlayer) {
	        int col = currentPlayer.makeMove();
	       
	        if (!board.boardFull()) {
	           // call board method to add token.
	           board.addToken(col, currentPlayer.getPlayerNumber());
	        }
	        // print board
	        board.printBoard();
	    }

	    public void play() {
	        boolean noWinner = true;
	        this.setUpGame();
	        int currentPlayerIndex = 0;

	        while (noWinner) {
	            // provide condition
	            if (board.boardFull()) {
	                System.out.println("Board is now full. Game Ends.");
	                return;
	            }

	            Player currentPlayer = players[currentPlayerIndex];
	            // Override default tostring for Player class
	            System.out.println(toString(currentPlayer));
	            playerTurn(currentPlayer);
	            if (board.checkIfPlayerIsTheWinner(currentPlayer.getPlayerNumber())) {
	                printWinner(currentPlayer);
	                noWinner = false;
	            } else {
	                currentPlayerIndex = (currentPlayerIndex == 0) ? 1 : 0; // reassign the variable to allow the game to continue. Note the index would wrap back to the first player if we are at the end. Think of using modulus (%).
	            }
	        }
	    }
	    
	    public String toString(Player currentPlayer) {
	    	return "It is player " + currentPlayer.getPlayerNumber() + "'s turn. " + currentPlayer;
	    }
}
