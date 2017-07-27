package chessAttempt;

class GameBoard {

	public GamePiece[][] Board = new GamePiece[8][8];

	public void display() {
		char[] line = new char[17];
		for (int j = 7; j > -1; j--) {
			System.out.println("-----------------");
			for (int i = 0; i < 8 ; i++) {
				line[2*i+1] = this.Board[i][j].display();
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
					this.Board[i][j] = new Pawn("white");
				} else if (j == 6) {
					this.Board[i][j] = new Pawn("black");
				} else {
					this.Board[i][j] = new EmptyPiece("none");
				}

			}
		}
		this.Board[0][0] = new Castle("white");
		this.Board[7][0] = new Castle("white");
		this.Board[1][0] = new Horse("white");
		this.Board[6][0] = new Horse("white");
		this.Board[2][0] = new Bishop("white");
		this.Board[5][0] = new Bishop("white");
		this.Board[3][0] = new Queen("white");
		this.Board[4][0] = new King("white");
		this.Board[0][7] = new Castle("black");
		this.Board[7][7] = new Castle("black");
		this.Board[1][7] = new Horse("black");
		this.Board[6][7] = new Horse("black");
		this.Board[2][7] = new Bishop("black");
		this.Board[5][7] = new Bishop("black");
		this.Board[3][7] = new Queen("black");
		this.Board[4][7] = new King("black");
		this.display();
	}

	public String won() {
		// TODO Auto-generated method stub
		return "No";
	}

}
