package chessAttempt;

import static chessAttempt.Team.*;
class GameBoard {

	public GamePiece[][] board = new GamePiece[8][8];

	public void display() {
		char[] line = new char[17];
		for (int j = 7; j > -1; j--) {
			System.out.println("-----------------");
			for (int i = 0; i < 8 ; i++) {
				line[2*i+1] = this.board[i][j].display();
				line[2*i] = '|';
			}
			line[16] = '|';
			String strLine = new String(line);
			System.out.println(strLine);
		}

	}

	public GameBoard() {
		// Constructor method sets standard board with white at bottom and black at top //
		for (int i = 0; i < 8; i++) {
			for (int j = 1; j < 7; j++) {
				if (j == 1) {
					this.board[i][j] = new Pawn(WHITE, new Coordinates(i,j));
				} else if (j == 6) {
					this.board[i][j] = new Pawn(BLACK, new Coordinates(i,j));
				} else {
					this.board[i][j] = new EmptyPiece(NONE, new Coordinates(i,j));
				}

			}
		}
		this.board[0][0] = new Castle(WHITE, new Coordinates(0,0));
		this.board[7][0] = new Castle(WHITE, new Coordinates(7,0));
		this.board[1][0] = new Horse(WHITE, new Coordinates(1,0));
		this.board[6][0] = new Horse(WHITE, new Coordinates(6,0));
		this.board[2][0] = new Bishop(WHITE, new Coordinates(2,0));
		this.board[5][0] = new Bishop(WHITE, new Coordinates(5,0));
		this.board[3][0] = new Queen(WHITE, new Coordinates(3,0));
		this.board[4][0] = new King(WHITE, new Coordinates(4,0));
		this.board[0][7] = new Castle(BLACK, new Coordinates(0,7));
		this.board[7][7] = new Castle(BLACK, new Coordinates(7,7));
		this.board[1][7] = new Horse(BLACK, new Coordinates(1,7));
		this.board[6][7] = new Horse(BLACK, new Coordinates(6,7));
		this.board[2][7] = new Bishop(BLACK, new Coordinates(2,7));
		this.board[5][7] = new Bishop(BLACK, new Coordinates(5,7));
		this.board[3][7] = new Queen(BLACK, new Coordinates(3,7));
		this.board[4][7] = new King(BLACK, new Coordinates(4,7));
	}

	public String won() {
		// TODO Auto-generated method stub
		return "No";
	}

}
