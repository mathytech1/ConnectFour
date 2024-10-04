package mavenproject.connect_four.exceptions;

import mavenproject.connect_four.Game;

public class ColumnFullException extends Exception{
	public ColumnFullException(String message) {
		super(message);
	}
}
