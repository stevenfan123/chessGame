package chessAttempt;

abstract class GamePiece {
	public String team;
	public GamePiece(String colour){
		this.team = colour;
	}
	public  char display(){
		if(this.team.equals("white")){
			return this.getClass().getSimpleName().charAt(0);
		} else{
			return this.getClass().getSimpleName().toLowerCase().charAt(0);
		}
	}
	public abstract Coordinates[] possibleMoves(GamePiece[][] Board, Coordinates userPicked);
	protected static Coordinates[] appendTwoArrays(Coordinates[] firstArray, Coordinates[] secondArray) {
		Coordinates[] joinedArray = new Coordinates[firstArray.length + secondArray.length];
		for (int i = 0; i < firstArray.length; i++) {
			joinedArray[i] = firstArray[i];
		}
		for (int j = 0; j < secondArray.length; j++) {
			joinedArray[j + firstArray.length] = secondArray[j];
		}
		return joinedArray;
	}
	protected static Coordinates[] combineArrays(Coordinates[][] arrayOfArrays) {

		if (arrayOfArrays.length == 0 | arrayOfArrays.length == 1) {
			return arrayOfArrays[0];
		} else {
			int lastIndex = arrayOfArrays.length - 1;
			Coordinates[][] compactArrayArray = new Coordinates[lastIndex][];
			compactArrayArray[0] = appendTwoArrays(arrayOfArrays[0], arrayOfArrays[lastIndex]);
			for (int i = 1; i < lastIndex; i++) {
				compactArrayArray[i] = arrayOfArrays[i];
			}
			return combineArrays(compactArrayArray);
		}
	}
	protected static String emptyOrEnemyOrFriendly(GamePiece[][] Board, Coordinates selectedCoordinates, String colour) {
		String enemyColour = "white";
		if (colour == "white") {
			enemyColour = "black";
		}
		if (Board[selectedCoordinates.x][selectedCoordinates.y].getClass().getSimpleName().equals("EmptyPiece")) {
			return "empty";
		} else if (Board[selectedCoordinates.x][selectedCoordinates.y].team.equals(enemyColour)) {
			return "enemy";
		}
		return "friendly";
	}
	protected static Coordinates[] possibleMovesInDirection(GamePiece[][] Board, Coordinates pieceCoords, int xIncrement, int yIncrement) {
		boolean endOfLine = false;
		GamePiece Piece = Board[pieceCoords.x][pieceCoords.y];
		Coordinates[] possibleMoves = new Coordinates[7];
		int i = 1;
		while (endOfLine == false) {
			Coordinates checkCoords = new Coordinates(pieceCoords.x + i * xIncrement, pieceCoords.y + i * yIncrement);
			if (pieceCoords.x + i * xIncrement < 0 | pieceCoords.x + i * xIncrement > 7 | pieceCoords.y + i * yIncrement < 0 | pieceCoords.y + i * yIncrement > 7) {
				endOfLine = true;
			} else if (emptyOrEnemyOrFriendly(Board, checkCoords, Piece.team) == "enemy") {
				possibleMoves[i-1] = checkCoords;
				endOfLine = true;
			} else if (emptyOrEnemyOrFriendly(Board, checkCoords, Piece.team) == "friendly") {
				endOfLine = true;
			} else {
				possibleMoves[i-1] = checkCoords;
			}
			i++;
		}
		return possibleMoves;
	}
}

class Pawn extends GamePiece {
	public Pawn(String colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Coordinates[] possibleMoves(GamePiece[][] Board, Coordinates userPicked) {
		GamePiece Pawn = Board[userPicked.x][userPicked.y];
		Coordinates[] possibleMoves = new Coordinates[4];
		int yDirection = -1;
		if (Pawn.team.equals("white")) {
			yDirection = 1;
		}
		if (userPicked.y == 1 & Board[userPicked.x][userPicked.y + 1].getClass().getSimpleName().equals("EmptyPiece") & Board[userPicked.x][userPicked.y + 2].getClass().getSimpleName().equals("EmptyPiece")) {
			possibleMoves[3] = new Coordinates(userPicked.x, userPicked.y + 2); // 2 forward //
		}
		if (userPicked.y+yDirection > -1 & userPicked.y+yDirection < 8) {
			if (Board[userPicked.x][userPicked.y + yDirection].getClass().getSimpleName().equals("EmptyPiece")) {
				possibleMoves[0] = new Coordinates(userPicked.x, userPicked.y + yDirection); // 1 forward // 
			}
			if (userPicked.x > 0) {
				if (Board[userPicked.x - 1][userPicked.y + yDirection].team.equals("black")) {
					possibleMoves[1] = new Coordinates(userPicked.x - 1, userPicked.y + yDirection); // 1 left 1 forward //
				}
			}
			if (userPicked.x < 7) {
				if (Board[userPicked.x + 1][userPicked.y + yDirection].team.equals("black")) {
					possibleMoves[2] = new Coordinates(userPicked.x + 1, userPicked.y + yDirection); // 1 right 1 forward //
				}
			}
		}

		return (possibleMoves);
	}	

}

class King extends GamePiece {
	public King(String colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Coordinates[] possibleMoves(GamePiece[][] Board, Coordinates userPicked) {
		GamePiece King = Board[userPicked.x][userPicked.y];
		Coordinates[] possibleMoves = new Coordinates[8];
		if (userPicked.x > 0) {
			possibleMoves[0] = new Coordinates(userPicked.x - 1, userPicked.y); // 1 left //
		}
		if (userPicked.x > 0 & userPicked.y > 0) {
			possibleMoves[1] = new Coordinates(userPicked.x - 1, userPicked.y - 1); // 1 left 1 down//
		}
		if (userPicked.y > 0) {
			possibleMoves[2] = new Coordinates(userPicked.x, userPicked.y - 1); // 1 down //
		}
		if (userPicked.x < 7 & userPicked.y > 0) {
			possibleMoves[3] = new Coordinates(userPicked.x + 1, userPicked.y - 1); // 1 right 1 down // 
		}
		if (userPicked.x < 7) {
			possibleMoves[4] = new Coordinates(userPicked.x + 1, userPicked.y); // 1 right //
		}
		if (userPicked.x < 7 & userPicked.y < 7) {
			possibleMoves[5] = new Coordinates(userPicked.x + 1, userPicked.y + 1); // 1 right 1 up //
		}
		if (userPicked.y < 7) {
			possibleMoves[6] = new Coordinates(userPicked.x, userPicked.y + 1); // 1 up //
		}
		if (userPicked.x > 0 & userPicked.y < 7) {
			possibleMoves[7] = new Coordinates(userPicked.x - 1, userPicked.y + 1); // 1 left 1 up //
		}
		for (int i = 0; i < 8; i++) {
			if (possibleMoves[i] != null) {
				if (emptyOrEnemyOrFriendly(Board, possibleMoves[i], King.team) == "friendly") {
					possibleMoves[i] = null;
				}
			}
		}
		return possibleMoves;
	}

}

class Horse extends GamePiece {
	public Horse(String colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Coordinates[] possibleMoves(GamePiece[][] Board, Coordinates userPicked) {
		GamePiece Horse = Board[userPicked.x][userPicked.y];
		Coordinates[] possibleMoves = new Coordinates[8];
		if (userPicked.x > 1 & userPicked.y > 0) {
			possibleMoves[0] = new Coordinates(userPicked.x - 2, userPicked.y - 1); // 2 left 1 down //
		}
		if (userPicked.x > 0 & userPicked.y > 1) {
			possibleMoves[1] = new Coordinates(userPicked.x - 1, userPicked.y - 2); // 1 left 2 down //
		}
		if (userPicked.x < 7 & userPicked.y > 1) {
			possibleMoves[2] = new Coordinates(userPicked.x + 1, userPicked.y - 2); // 1 right 2 down //
		}
		if (userPicked.x < 6 & userPicked.y > 0) {
			possibleMoves[3] = new Coordinates(userPicked.x + 2, userPicked.y - 1); // 2 right 1 down //
		}
		if (userPicked.x < 6 & userPicked.y < 7) {
			possibleMoves[4] = new Coordinates(userPicked.x + 2, userPicked.y + 1); // 2 right 1 up //
		}
		if (userPicked.x < 7 & userPicked.y < 6) {
			possibleMoves[5] = new Coordinates(userPicked.x + 1, userPicked.y + 2); // 1 right 2 up //
		}
		if (userPicked.x > 0 & userPicked.y < 6) {
			possibleMoves[6] = new Coordinates(userPicked.x - 1, userPicked.y + 2); // 1 left 2 up //
		}
		if (userPicked.x > 1 & userPicked.y < 7) {
			possibleMoves[7] = new Coordinates(userPicked.x - 2, userPicked.y + 1); // 2 left 1 up //
		}
		for (int i = 0; i < 8; i++) {
			if (possibleMoves[i] != null) {
				if (emptyOrEnemyOrFriendly(Board, possibleMoves[i], Horse.team) == "friendly") {
					possibleMoves[i] = null;
				}
			}
		}
		return (possibleMoves);
	}
	
}

class Castle extends GamePiece {
	public Castle(String colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Coordinates[] possibleMoves(GamePiece[][] Board, Coordinates userPicked) {
		Coordinates[] possibleMovesLeft = new Coordinates[userPicked.x];
		Coordinates[] possibleMovesRight = new Coordinates[7 - userPicked.x];
		Coordinates[] possibleMovesDown = new Coordinates[userPicked.y];
		Coordinates[] possibleMovesUp = new Coordinates[7 - userPicked.y];
		if (possibleMovesLeft.length > 0) {
			possibleMovesLeft = possibleMovesInDirection(Board, userPicked, -1, 0);
		}
		if (possibleMovesRight.length > 0) {
			possibleMovesRight = possibleMovesInDirection(Board, userPicked, 1, 0);
		}
		if (possibleMovesDown.length > 0) {
			possibleMovesDown = possibleMovesInDirection(Board, userPicked, 0, -1);
		}
		if (possibleMovesUp.length > 0) {
			possibleMovesUp = possibleMovesInDirection(Board, userPicked, 0, 1);
		}

		Coordinates[][] possibleMovesArray = new Coordinates[4][];
		possibleMovesArray[0] = possibleMovesLeft;
		possibleMovesArray[1] = possibleMovesRight;
		possibleMovesArray[2] = possibleMovesDown;
		possibleMovesArray[3] = possibleMovesUp;
		Coordinates[] possibleMoves = (Coordinates[]) combineArrays(possibleMovesArray);
		return possibleMoves;
	}
	
}

class Bishop extends GamePiece {
	public Bishop(String colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Coordinates[] possibleMoves(GamePiece[][] Board, Coordinates userPicked) {
		Coordinates[] possibleMovesUpLeft = new Coordinates[7];
		Coordinates[] possibleMovesUpRight = new Coordinates[7];
		Coordinates[] possibleMovesDownLeft = new Coordinates[7];
		Coordinates[] possibleMovesDownRight = new Coordinates[7];
		if (possibleMovesUpLeft.length > 0) {
			possibleMovesUpLeft = possibleMovesInDirection(Board, userPicked, -1, 1);
		}
		if (possibleMovesUpRight.length > 0) {
			possibleMovesUpRight = possibleMovesInDirection(Board, userPicked, 1, 1);
		}
		if (possibleMovesDownLeft.length > 0) {
			possibleMovesDownLeft = possibleMovesInDirection(Board, userPicked, -1, -1);
		}
		if (possibleMovesDownRight.length > 0) {
			possibleMovesDownRight = possibleMovesInDirection(Board, userPicked, 1, -1);
		}

		Coordinates[][] possibleMovesArray = new Coordinates[4][];
		possibleMovesArray[0] = possibleMovesUpLeft;
		possibleMovesArray[1] = possibleMovesUpRight;
		possibleMovesArray[2] = possibleMovesDownLeft;
		possibleMovesArray[3] = possibleMovesDownRight;
		Coordinates[] possibleMoves = (Coordinates[]) combineArrays(possibleMovesArray);
		return possibleMoves;
	}
	
}

class Queen extends GamePiece {
	public Queen(String colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Coordinates[] possibleMoves(GamePiece[][] Board, Coordinates userPicked) {
		return null;

	}
	
}

class EmptyPiece extends GamePiece {

	public EmptyPiece(String colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Coordinates[] possibleMoves(GamePiece[][] Board, Coordinates userPicked) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public char display(){
		return ' ';
	}
	
	
}

