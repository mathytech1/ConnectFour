package mavenproject.connect_four;

import java.util.Arrays;
import java.util.Scanner;

import mavenproject.connect_four.exceptions.ColumnFullException;
import mavenproject.connect_four.exceptions.InvalidBoardSizeException;
import mavenproject.connect_four.exceptions.InvalidMoveException;

public class Board {
    // add instance variables
    private String[][] board;
    Scanner scanner = new Scanner(System.in);
    public void boardSetUp() throws InvalidBoardSizeException {
        System.out.println("------ Board Set up -------");
        System.out.println("Number of rows: ");
        int rows = scanner.nextInt(); // receive user input
        System.out.println("Number of cols: ");
        int columns = scanner.nextInt(); // receive column value
        
        // Check if the provided size has at least 4 rows and 4 columns
        if (rows < 4 || columns < 4) {
            throw new InvalidBoardSizeException("Board size must be at least 4x4!");
        }
        
        this.board = new String[rows][columns]; // initialize a row by column array;
        
        // initialize empty board with dashes (-)
        for (String[] row : board) {
            // fill up each row of the board with dashes
            for(int column = 0; column < row.length; column++){
                row[column] = "-";
            }
        }
    }

    public void printBoard() {
        for (String[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
    public boolean columnFull(int col) {
    	return !(board[0][col]=="-"); // check if the column is full by just checking the 0'th row's value
    }

    public boolean boardFull() {
        // True understanding this code.
    	// Check if column 0 to board column length is full
        for (int i = 0; i < this.board[0].length; i++) {
            if (!columnFull(i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean addToken(int colToAddToken, String token) throws InvalidMoveException, ColumnFullException {
    	// Check if the column to add the token is not out of range (greater than board column length or less than zero)
    	if(colToAddToken < 0 || colToAddToken > board[0].length-1) {
    		throw new InvalidMoveException("Invalid column: " + colToAddToken);
    	}
    	// Check if the column to add the token is already full or not
    	if(columnFull(colToAddToken)) {
    		throw new ColumnFullException("Column " + colToAddToken + " is full!");
    	}
    	
        int rowToAddToken = board.length - 1;
        
        // As long as rowToAddToken >= 0, check if there is any space and add the token if the empty place is available
        while (rowToAddToken >= 0)// what condition should be here to allow you to keep searching for the right row level of the board to place the token?  
        {
            if (board[rowToAddToken][colToAddToken].equals("-")) {
               // You now know the right row and column to place the token. Place it and then return true.
               board[rowToAddToken][colToAddToken] = token;
                return true;
            } else {
                rowToAddToken -= 1;
            }
        }

        return false;
    }
    
    // To undo the last move
    public boolean undoMove(int colToAddToken, String token) throws InvalidMoveException, ColumnFullException {
        	
        int rowToAddToken = board.length - 1;
        
        // As long as rowToAddToken >= 0, check if there is any element at the top and add the token if the empty delete the token if it's is available
        while (rowToAddToken >= 0)// what condition should be here to allow you to keep searching for the right row level of the board to place the token?  
        {
            if (!board[rowToAddToken][colToAddToken].equals("-")) {
               // You now know the right row and column to delete the token and then return true.
               board[rowToAddToken][colToAddToken] = "-";
                return true;
            } else {
                rowToAddToken -= 1;
            }
        }

        return false;
    }

    public boolean checkIfPlayerIsTheWinner(String playerNumber, int inaRow) {
    	// If any of the check is true then it will return true and the winner will be known.
    	return checkHorizontal(playerNumber, inaRow) || checkLeftDiagonal(playerNumber, inaRow) || checkVertical(playerNumber, inaRow) || checkRightDiagonal(playerNumber, inaRow);
    }

    public boolean checkVertical(String playerNumber, int inaRow) {
    	switch(inaRow) {
    	case 4:
    		for (int col = 0; col < board[0].length; col++) {
                // length - 3 here because we are comparing 4 in a row items
                for (int row = 0; row < board.length - 3; row++) {
                	if (board[row][col].equals(playerNumber)) {
                        if (board[row][col] == board[row + 1][col]
                                && board[row][col] == board[row + 2][col]
                                && board[row][col] == board[row + 3][col]) {
                            return true;
                        }
                    }                    
                }
            }
    		break;
    		
    	case 5:
    		for (int col = 0; col < board[0].length; col++) {
                // length - 3 here because we are comparing 4 in a row items
                for (int row = 0; row < board.length - 4; row++) {
                	if (board[row][col].equals(playerNumber)) {
                        if (board[row][col] == board[row + 1][col]
                                && board[row][col] == board[row + 2][col]
                                && board[row][col] == board[row + 3][col]
                        		&& board[row][col] == board[row + 4][col]) {
                            return true;
                        }
                    }                    
                }
            }
    		break;
    		
    	case 6:
    		for (int col = 0; col < board[0].length; col++) {
                // length - 3 here because we are comparing 4 in a row items
                for (int row = 0; row < board.length - 5; row++) {
                	if (board[row][col].equals(playerNumber)) {
                        if (board[row][col] == board[row + 1][col]
                                && board[row][col] == board[row + 2][col]
                                && board[row][col] == board[row + 3][col]
                        		&& board[row][col] == board[row + 4][col]
                				&& board[row][col] == board[row + 5][col]) {
                            return true;
                        }
                    }                    
                }
            }
    		break;
    	}
        
        return false;

    }

    public boolean checkHorizontal(String playerNumber, int inaRow) {
    	switch(inaRow) {
    	case 4:
		   for (int row=0; row < board.length; row++) {
	           // try implementing this by being inspired by the checkVertical logic. Note avoid off by 1 errors. Also remember that you are now checking across columns within each row this time. 
	           for(int col = 0; col < board[0].length - 3; col++){
	                if(board[row][col].equals(playerNumber)){
	                    if(board[row][col] == board[row][col + 1]
	                    && board[row][col] == board[row][col + 2]
	                    && board[row][col] == board[row][col + 3]) {
	                    	return true;
	                    }
	                }
	           }
	        }
		   break;
    		   
    	case 5:
		   for (int row=0; row < board.length; row++) {
	           // try implementing this by being inspired by the checkVertical logic. Note avoid off by 1 errors. Also remember that you are now checking across columns within each row this time. 
	           for(int col = 0; col < board[0].length - 4; col++){
	                if(board[row][col].equals(playerNumber)){
	                    if(board[row][col] == board[row][col + 1]
	                    && board[row][col] == board[row][col + 2]
	                    && board[row][col] == board[row][col + 3]
                		&& board[row][col] == board[row][col + 4]) {
	                    	return true;
	                    }
	                }
	           }
	        }
		   break;
		   
    	case 6: 
    		for (int row=0; row < board.length; row++) {
 	           // try implementing this by being inspired by the checkVertical logic. Note avoid off by 1 errors. Also remember that you are now checking across columns within each row this time. 
 	           for(int col = 0; col < board[0].length - 4; col++){
 	                if(board[row][col].equals(playerNumber)){
 	                    if(board[row][col] == board[row][col + 1]
 	                    && board[row][col] == board[row][col + 2]
 	                    && board[row][col] == board[row][col + 3]
                 		&& board[row][col] == board[row][col + 4]) {
 	                    	return true;
 	                    }
 	                }
 	           }
 	        }
    		break;
    	}
    	
        return false;
        
    }

     public boolean checkLeftDiagonal(String playerNumber, int inaRow) {
	    switch(inaRow) {
	    case 4:
	        for (int row = 0; row < board.length - 3; row++) {
	            for (int col = 0; col < board[0].length - 3; col++) {
	                if (board[row][col].equals(playerNumber)) {
	                    if (board[row][col] == board[row + 1][col + 1]
	                            && board[row][col] == board[row + 2][col + 2]
	                            && board[row][col] == board[row + 3][col + 3]) {
	                        return true;
	                    }
	                }
	            }
	        }
	        break;
	    case 5: 
	        for (int row = 0; row < board.length - 4; row++) {
	            for (int col = 0; col < board[0].length - 4; col++) {
	                if (board[row][col].equals(playerNumber)) {
	                    if (board[row][col] == board[row + 1][col + 1]
	                        && board[row][col] == board[row + 2][col + 2]
	                        && board[row][col] == board[row + 3][col + 3]
	                		&& board[row][col] == board[row + 4][col + 4]) {
	                    		return true;
	                    }
	                }
	            }
	        }
	        break;
	        
	    case 6:
	    	  for (int row = 0; row < board.length - 5; row++) {
	              for (int col = 0; col < board[0].length - 5; col++) {
	                  if (board[row][col].equals(playerNumber)) {
	                      if (board[row][col] == board[row + 1][col + 1]
	                          && board[row][col] == board[row + 2][col + 2]
	                          && board[row][col] == board[row + 3][col + 3]
	                  		  && board[row][col] == board[row + 4][col + 4]
	          				  && board[row][col] == board[row + 4][col + 5]) {
	                      		return true;
	                      }
	                  }
	              }
	          }
	    	  break;
	    }
        return false;
    }

    public boolean checkRightDiagonal(String playerNumber, int inaRow) {
       // implment method and return an appropriate return type.
       switch(inaRow) {
       case 4:
    	   for (int row = 0; row < board.length - 3; row++){
               for (int col = board[0].length-1; col >= 3; col--){
                   if(board[row][col].equals(playerNumber)){
                       if(board[row][col] == board[row + 1][col - 1]
                       && board[row][col] == board[row + 2][col - 2]
                       && board[row][col] == board[row + 3][col - 3]){
                           return true;
                       }
                   }
               }
          }
    	   break;
       case 5:
    	   for (int row = 0; row < board.length - 4; row++){
               for (int col = board[0].length-1; col >= 4; col--){
                   if(board[row][col].equals(playerNumber)){
                       if(board[row][col] == board[row + 1][col - 1]
                       && board[row][col] == board[row + 2][col - 2]
                       && board[row][col] == board[row + 3][col - 3]
            		   && board[row][col] == board[row + 4][col - 4]){
                           return true;
                       }
                   }
               }
          }
    	   break;
    	   
       case 6:
    	   for (int row = 0; row < board.length - 5; row++){
               for (int col = board[0].length-1; col >= 5; col--){
                   if(board[row][col].equals(playerNumber)){
                       if(board[row][col] == board[row + 1][col - 1]
                       && board[row][col] == board[row + 2][col - 2]
                       && board[row][col] == board[row + 3][col - 3]
            		   && board[row][col] == board[row + 4][col - 4]
    				   && board[row][col] == board[row + 5][col - 5]){
                           return true;
                       }
                   }
               }
          }
    	   break;
       }
       return false;
    }
}
