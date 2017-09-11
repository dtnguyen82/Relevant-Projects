#ifndef TICTACTOE_H
#define TICTACTOE_H
#include <iostream>
#include <string>
using namespace std;

enum status { WIN , DRAW , CONTINUE };

class ticTacToe
{
public:
	void play();
	//Function to start the play……x /O  keep playing …. getXOMove

	void displayBoard() const;
	//Function to print the board.

	bool isValidMove(int x, int y) const;
	//Function to determine if a move is valid.

	bool getXOMove(char moveSymbol);
	//Function to get a move for a player…read row col..check it displayboard…check game status..count nbof moves

	status gameStatus();
	//Function to determine the current status of the game.

	void reStart();
	//Function to restart the game.

	ticTacToe();
	//Default constructor.
	//Postcondition: Initializes the board to an empty state.

private:
	char board[3][3];
	int noOfMoves;
};
#endif