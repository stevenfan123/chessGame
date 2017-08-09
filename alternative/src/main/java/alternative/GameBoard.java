package alternative;

class GameBoard {

	public GamePiece[][] gamePieces = new GamePiece[8][8];

	public void display() {
		char[] line = new char[17];
		for (int j = 7; j > -1; j--) {
			System.out.println("-----------------");
			for (int i = 0; i < 8 ; i++) {
				line[2*i+1] = this.gamePieces[i][j].display();
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
					this.gamePieces[i][j] = new Pawn(Team.WHITE);
				} else if (j == 6) {
					this.gamePieces[i][j] = new Pawn(Team.BLACK);
				} 
//				else {
//					this.board[i][j] = new EmptyPiece("none");
//				}

			}
		}
//		this.board[0][0] = new Castle("white");
//		this.board[7][0] = new Castle("white");
//		this.board[1][0] = new Horse("white");
//		this.board[6][0] = new Horse("white");
//		this.board[2][0] = new Bishop("white");
//		this.board[5][0] = new Bishop("white");
//		this.board[3][0] = new Queen("white");
//		this.board[4][0] = new King("white");
//		this.board[0][7] = new Castle("black");
//		this.board[7][7] = new Castle("black");
//		this.board[1][7] = new Horse("black");
//		this.board[6][7] = new Horse("black");
//		this.board[2][7] = new Bishop("black");
//		this.board[5][7] = new Bishop("black");
//		this.board[3][7] = new Queen("black");
//		this.board[4][7] = new King("black");
//		this.display();
	}

	public String won() {
		// TODO Auto-generated method stub
		return "No";
	}

}
