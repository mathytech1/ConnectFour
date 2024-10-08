package mavenproject.connect_four;

import java.util.ArrayList;
import java.util.Scanner;

import mavenproject.connect_four.exceptions.ColumnFullException;
import mavenproject.connect_four.exceptions.InvalidBoardSizeException;
import mavenproject.connect_four.exceptions.InvalidMoveException;

public class Game {
	   private ArrayList<Player> players;
	    private Board board;
	    private static Scanner scanner = new Scanner(System.in);

	    public Game() {
	        // Let's default it two players for now. Later, you can improve upon this to allow the game creator to choose how many players are involved.
	        this.players = new ArrayList<Player>(); // complete line.
	        this.board = new Board(); // complete line
	    }

	    public void setUpGame() throws InvalidBoardSizeException {
	    	int loop = 1, counter = 1;
	    	boolean duplicate = false;
	    	String name;
	    	
	    	System.out.println("Enter player " + counter + "'s name: ");
	    	name = scanner.nextLine();
	    	players.add(new Player(name, String.valueOf(counter)));
	    	
	    	do {
	    		System.out.println("Enter player " + (counter + 1) + "'s name: ");
	    		name = scanner.nextLine();
	    		
	    		for(int i = 0; i < players.size(); i++) {
	    			if(players.get(i).getName().equalsIgnoreCase(name)) {
	    				duplicate = true;
	    				 break;
	    			}
	    		}
	    		if(!duplicate) {
	    			players.add(new Player(name, String.valueOf((counter + 1))));
	    			System.out.println("Press 1-9 to continue entering the players / 0 if you have entered all the players!");
	    			loop = scanner.nextInt();
	    			scanner.nextLine();
	    			counter++;
	    		}else {
	    			System.out.println("'" + name + "' already exists!");
	    			duplicate = false;
	    		}
	    		
	    	}while(loop != 0);
	    	
	        /* wrap the code in here with a conditional block that enables the check described above. 
	        
	            System.out.println("Error! Both Players cannot have the same name.");
	            System.out.println("Enter player 2's name: ");
	            playerTwoName = scanner.nextLine();
	        
	        */
	    	
	        // set up the board using the appropriate method
			board.boardSetUp();
	        // print the board the using appropriate method
	        board.printBoard();
	    }

	    public void printWinner(Player player, int inaRow) {
	        // Use an appropriate Board class method to check if player is a winner
	        if(board.checkIfPlayerIsTheWinner(player.getPlayerNumber(), inaRow)){
	            System.out.println(player.getName() + " is the winner");
	        } 
	    }

	    public void playerTurn(Player currentPlayer) throws InvalidMoveException, ColumnFullException {
	        int col = currentPlayer.makeMove();
	        // Returns true if the token is added to the board
	        boolean isAdded = false;
	       
	        if (!board.boardFull()) {
	           // call board method to add token and store the return value on isAdded
	           isAdded = board.addToken(col, currentPlayer.getPlayerNumber());
	        }
	        // print board
	        board.printBoard();
	        // If the token is added, check if the player wants to undo the last move
	        if(isAdded) {
	        	System.out.println("Press 0 to undo or 1 to continue!!!");
	            int op = scanner.nextInt();
	            if(op==0) {
	            	// Undo the last move
	         	    board.undoMove(col, currentPlayer.getPlayerNumber());
	         	    // Call the playerTurn() to allow the player to replay
	         	    playerTurn(currentPlayer);
	            }
	            // Print the updated board
	            board.printBoard();
	            
	        }
	        
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
	        
	        System.out.println("Press 4. Four In a Row");
	        System.out.println("Press 5. Five In a Row");
	        System.out.println("Press 6. Six In a Row");
	        int inaRow = scanner.nextInt(); 
	        
	        System.out.println("You have 10 minutes to complete this game.");
	        // Get the starting time
	        long startTime = System.currentTimeMillis();
	        long endTime = System.currentTimeMillis();
	        // Check if there is no winner and the time is less than 5 minutes, if true it will keep running if false it will print the winner and terminate the program.
	        while (noWinner && (endTime - startTime <= 300000)) {
	            // provide condition
	            if (board.boardFull()) {
	                System.out.println("Board is now full. Game Ends.");
	                return;
	            }
	            
	            Player currentPlayer = players.get(currentPlayerIndex);
	           // Player currentPlayer = players[currentPlayerIndex];
	            // Get the remaining time in minutes
	            int minutes = (int)(300000 - (endTime - startTime))/(1000*60);
	            
	            int seconds = (int)(300000 - (endTime - startTime)) % (1000);
	            // Override default tostring for Player class
	            System.out.println(toString(currentPlayer) + ". " + minutes + " minutes and " + seconds + " seconds left!");
	            
	            // Call playerTurn() method of Game class and handle any exception it may generate 
	            try {
	                playerTurn(currentPlayer);
	            } catch (InvalidMoveException | ColumnFullException e) {
	                System.out.println(e.getMessage());
	                currentPlayerIndex = (currentPlayerIndex == players.size() - 1) ? 0 : currentPlayerIndex + 1; // reassign the variable to allow the game to continue. Note the index would wrap back to the first player if we are at the end. Think of using modulus (%).
	                continue;
	            }
	            
	            // Check if the current player is a winner and if true print the winner
	            if (board.checkIfPlayerIsTheWinner(currentPlayer.getPlayerNumber(), inaRow)) {
	                printWinner(currentPlayer, inaRow);
	                noWinner = false;
	            } else { // Change the current player to the other player
	            	currentPlayerIndex = (currentPlayerIndex == players.size() - 1) ? 0 : currentPlayerIndex + 1;; // reassign the variable to allow the game to continue. Note the index would wrap back to the first player if we are at the end. Think of using modulus (%).
	            }
	            endTime = System.currentTimeMillis();
	        }
	        if((endTime - startTime > 300000) && noWinner) {
	        	System.out.println("Time is up!!!");
	        }
	    }
	    
	    // To display who's turn it's upon called.
	    public String toString(Player currentPlayer) {
	    	return "It is player " + currentPlayer.getPlayerNumber() + "'s turn. " + currentPlayer;
	    }
}
