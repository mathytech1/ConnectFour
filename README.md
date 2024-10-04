# **Connect Four Game**

## **Overview**
This is a customizable implementation of the classic Connect Four game. Players can enter their names, choose the size of the board, and take turns placing their pieces. The goal is to be the first player to connect four of their pieces either vertically, horizontally, or diagonally.

## **How the Game Works**
- **Player Names**: At the beginning of the game, each player will be asked to enter their name. Player 1 and Player 2 are then represented by their names throughout the game.
- **Dynamic Board Size**: Players can choose the size of the game board, ranging from 4x4 to 7x7. The program will prompt users to input the desired number of rows and columns, ensuring the size is within the allowed range.
- Players alternate turns, starting with Player 1.
- On each turn, the player is prompted to enter the column number where they want to place their piece.
- If the chosen column has available space, the player's number (1 or 2) is placed in the lowest available row in that column.
- The game checks after each turn to see if there is a winner by finding four consecutive pieces of the same player, either vertically, horizontally, or diagonally.
- The game continues until a player wins or the board is completely filled, resulting in a draw.

## **Features**
- **Player Name Input**: Players are asked to input their names at the start of the game, and those names are used throughout the gameplay.
- **Customizable Board Size**: Players can specify the number of rows and columns for the board, within the limits of 4x4 to 7x7.
- **Two-Player Mode**: Player 1 and Player 2 take turns.
- **Dynamic Column Selection**: Players select which column to drop their piece into.
- **Win Detection**: The game checks for four consecutive numbers of the same player in all possible directions (horizontal, vertical, and diagonal).
- **Draw Condition**: If the board is full and no player has connected four, the game ends in a draw.

## **How to Play**
1. **Enter Player Names**: The program will prompt each player to enter their name.
2. **Set Board Size**: The game will ask for the number of rows and columns, ensuring the size is between 4x4 and 7x7.
3. The program will prompt the current player to enter a column number (based on the chosen board size) to drop their piece.
4. The game board will update after each move, showing where the pieces have landed.
5. The game checks for a winner after each turn. If a player has connected four of their pieces, the game announces the winner.
6. If the board is full and no player has connected four, the game will declare a draw.

## **Example**


