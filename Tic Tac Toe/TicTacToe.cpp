#include "ticTacToe.h"
ticTacToe::ticTacToe()
{
	for (int i = 0; i < 3; i++)
		for (int x = 0; x < 3; x++)
			board[i][x] = '-';
	noOfMoves = 0;
}
void ticTacToe::play() {
	int slot =0;
	char moveSymbol;
	cout << "TIC-TAC-TOE" << endl;
	displayBoard();
	while (slot < 9 ) {
		if (slot % 2 == 0)
			moveSymbol = 'X';
		else
			moveSymbol = 'O';
		if (getXOMove(moveSymbol) == true)
			slot++;
		else if(gameStatus() == 0)
			break;
	}
	char input;
	cout << "Do you want to play again? (Y/N)" << endl;
	cin >> input;
	if (input == 'Y' || input == 'y')
		reStart();
	else
		cout << "Game over!" << endl;
}
void ticTacToe::displayBoard() const
{
	for (int i = 0; i < 3; i++) {
		cout << endl;
		cout << "   ";
		for (int x = 0; x < 3; x++) {
			cout << board[i][x];
			if (x < 2)
				cout << "|";
		}
	}	
	cout << endl << endl;
}

bool ticTacToe::isValidMove(int x, int y)const {
	if (board[x][y] == '-' && x >= 0 && x < 3 && y >= 0 && y<3)
		return true;
	cout << endl << "Invalid Input" << endl;
	return false;
}

bool ticTacToe::getXOMove(char moveSymbol) {
	int x, y;
	cout << "Enter location for x(0,1,2): ";
	cin >> x;
	cout << "Enter location for y(0,1,2): ";
	cin >> y;
	if (isValidMove(x, y) == true) {
		board[x][y] = moveSymbol;
		displayBoard();
		char user;
		int c = gameStatus();
		switch (c) {
		case 0: cout << "You won!" << endl;
			return false;
				break;
			case 1: cout <<  "DRAW!" << endl << endl;
				break;
			case 2: cout << "Next" << endl << endl; break;
			default: cout << "Goodbye!" << endl;	
		}
		noOfMoves++;
		cout << "Number Of Moves: " << noOfMoves << endl;
		return true;
	}
	else {
		displayBoard();
		return false;
	}

}
void ticTacToe::reStart() {
	for (int i = 0; i < 3; i++)
		for (int x = 0; x < 3; x++)
			board[i][x] = '-';
	noOfMoves = 0;
	play();
}
status ticTacToe::gameStatus() {
	if (board[0][0] == 'X' && board[0][1] == 'X' &&board[0][2] == 'X' || board[0][0] == 'O' && board[0][1] == 'O' &&board[0][2] == 'O')
		return WIN;
	else if (board[1][0] == 'X' && board[1][1] == 'X' &&board[1][2] == 'X' || board[1][0] == 'O' && board[1][1] == 'O' &&board[1][2] == 'O')
		return WIN;
	else if (board[2][0] == 'X' && board[2][1] == 'X' &&board[2][2] == 'X' || board[2][0] == 'O' && board[2][1] == 'O' &&board[2][2] == 'O')
		return WIN;
	else if (board[0][0] == 'X' && board[1][0] == 'X' &&board[2][0] == 'X' || board[0][0] == 'O' && board[1][0] == 'O' &&board[2][0] == 'O')
		return WIN;
	else if (board[0][1] == 'X' && board[1][1] == 'X' &&board[2][1] == 'X' || board[0][1] == 'O' && board[1][1] == 'O' &&board[2][1] == 'O')
		return WIN;
	else if (board[0][2] == 'X' && board[1][2] == 'X' &&board[2][2] == 'X' || board[0][2] == 'O' && board[1][2] == 'O' &&board[2][2] == 'O')
		return WIN;
	else if (board[0][0] == 'X' && board[1][1] == 'X' &&board[2][2] == 'X' || board[0][0] == 'O' && board[1][1] == 'O' &&board[2][2] == 'O')
		return WIN;
	else if (board[2][0] == 'X' && board[1][1] == 'X' &&board[0][2] == 'X' || board[2][0] == 'O' && board[1][1] == 'O' &&board[0][2] == 'O')
		return WIN;
	else if (noOfMoves == 8)
		return DRAW;
	else
		return CONTINUE;
}
