//package chessAttempt;
//
//public class PossibleMoves {
//	public PossibleMoves() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public static Coordinates[] get(GamePiece[][] Board, Coordinates userPicked) {
//		if (Board[userPicked.x][userPicked.y].type.equals("Pawn")) {
//			return possibleMovesPawn(Board, userPicked);
//		} else if (Board[userPicked.x][userPicked.y].type.equals("Castle")) {
//			return possibleMovesCastle(Board, userPicked);
//		} else if (Board[userPicked.x][userPicked.y].type.equals("Horse")) {
//			return possibleMovesHorse(Board, userPicked);
//		} else if (Board[userPicked.x][userPicked.y].type.equals("Bishop")) {
//			return possibleMovesBishop(Board,userPicked);
//		} else if (Board[userPicked.x][userPicked.y].type.equals("Queen")) {
//			return possibleMovesQueen(Board,userPicked);
//		} else if (Board[userPicked.x][userPicked.y].type.equals("King")) {
//			return possibleMovesKing(Board, userPicked);
//		}
//		return null;
//	}
//
//	private static Coordinates[] possibleMovesQueen(GamePiece[][] board, Coordinates userPicked) {
//		Coordinates[] possibleMoves = appendTwoArrays(possibleMovesCastle(board, userPicked),possibleMovesBishop(board,userPicked));
//		return possibleMoves;
//	}
//
//	private static Coordinates[] possibleMovesHorse(GamePiece[][] Board, Coordinates userPicked) {
//
//		GamePiece Horse = Board[userPicked.x][userPicked.y];
//		Coordinates[] possibleMoves = new Coordinates[8];
//		if (userPicked.x > 1 & userPicked.y > 0) {
//			possibleMoves[0] = new Coordinates(userPicked.x - 2, userPicked.y - 1); // 2 left 1 down //
//		}
//		if (userPicked.x > 0 & userPicked.y > 1) {
//			possibleMoves[1] = new Coordinates(userPicked.x - 1, userPicked.y - 2); // 1 left 2 down //
//		}
//		if (userPicked.x < 7 & userPicked.y > 1) {
//			possibleMoves[2] = new Coordinates(userPicked.x + 1, userPicked.y - 2); // 1 right 2 down //
//		}
//		if (userPicked.x < 6 & userPicked.y > 0) {
//			possibleMoves[3] = new Coordinates(userPicked.x + 2, userPicked.y - 1); // 2 right 1 down //
//		}
//		if (userPicked.x < 6 & userPicked.y < 7) {
//			possibleMoves[4] = new Coordinates(userPicked.x + 2, userPicked.y + 1); // 2 right 1 up //
//		}
//		if (userPicked.x < 7 & userPicked.y < 6) {
//			possibleMoves[5] = new Coordinates(userPicked.x + 1, userPicked.y + 2); // 1 right 2 up //
//		}
//		if (userPicked.x > 0 & userPicked.y < 6) {
//			possibleMoves[6] = new Coordinates(userPicked.x - 1, userPicked.y + 2); // 1 left 2 up //
//		}
//		if (userPicked.x > 1 & userPicked.y < 7) {
//			possibleMoves[7] = new Coordinates(userPicked.x - 2, userPicked.y + 1); // 2 left 1 up //
//		}
//		for (int i = 0; i < 8; i++) {
//			if (possibleMoves[i] != null) {
//				if (emptyOrEnemyOrFriendly(Board, possibleMoves[i], Horse.team) == "friendly") {
//					possibleMoves[i] = null;
//				}
//			}
//		}
//		return (possibleMoves);
//	}
//
//	private static Coordinates[] possibleMovesCastle(GamePiece[][] Board, Coordinates userPicked) {
//		Coordinates[] possibleMovesLeft = new Coordinates[userPicked.x];
//		Coordinates[] possibleMovesRight = new Coordinates[7 - userPicked.x];
//		Coordinates[] possibleMovesDown = new Coordinates[userPicked.y];
//		Coordinates[] possibleMovesUp = new Coordinates[7 - userPicked.y];
//		if (possibleMovesLeft.length > 0) {
//			possibleMovesLeft = possibleMovesInDirection(Board, userPicked, -1, 0);
//		}
//		if (possibleMovesRight.length > 0) {
//			possibleMovesRight = possibleMovesInDirection(Board, userPicked, 1, 0);
//		}
//		if (possibleMovesDown.length > 0) {
//			possibleMovesDown = possibleMovesInDirection(Board, userPicked, 0, -1);
//		}
//		if (possibleMovesUp.length > 0) {
//			possibleMovesUp = possibleMovesInDirection(Board, userPicked, 0, 1);
//		}
//
//		Coordinates[][] possibleMovesArray = new Coordinates[4][];
//		possibleMovesArray[0] = possibleMovesLeft;
//		possibleMovesArray[1] = possibleMovesRight;
//		possibleMovesArray[2] = possibleMovesDown;
//		possibleMovesArray[3] = possibleMovesUp;
//		Coordinates[] possibleMoves = (Coordinates[]) combineArrays(possibleMovesArray);
//		return possibleMoves;
//	}
//
//	private static Coordinates[] possibleMovesPawn(GamePiece[][] Board, Coordinates userPicked) {
//		GamePiece Pawn = Board[userPicked.x][userPicked.y];
//		Coordinates[] possibleMoves = new Coordinates[4];
//		if (Pawn.team.equals("white")) {
//			if (userPicked.y == 1 & Board[userPicked.x][userPicked.y + 1].type.equals("EmptySpace") & Board[userPicked.x][userPicked.y + 2].type.equals("EmptySpace")) {
//				possibleMoves[3] = new Coordinates(userPicked.x, userPicked.y + 2); // 2 forward //
//			}
//			if (userPicked.y != 7) {
//				if (Board[userPicked.x][userPicked.y + 1].type.equals("EmptySpace")) {
//					possibleMoves[0] = new Coordinates(userPicked.x, userPicked.y + 1); // 1 forward // 
//				}
//				if (userPicked.x > 0) {
//					if (Board[userPicked.x - 1][userPicked.y + 1].team.equals("black")) {
//						possibleMoves[1] = new Coordinates(userPicked.x - 1, userPicked.y + 1); // 1 left 1 forward //
//					}
//				}
//				if (userPicked.x < 7) {
//					if (Board[userPicked.x + 1][userPicked.y + 1].team.equals("black")) {
//						possibleMoves[2] = new Coordinates(userPicked.x + 1, userPicked.y + 1); // 1 right 1 forward //
//					}
//				}
//			}
//		} else {
//			if (userPicked.y == 6 & Board[userPicked.x][userPicked.y - 1].type.equals("EmptySpace") & Board[userPicked.x][userPicked.y - 2].type.equals("EmptySpace")) {
//				possibleMoves[3] = new Coordinates(userPicked.x, userPicked.y - 2); // 2 back //
//			}
//			if (userPicked.y != 0) {
//				if (Board[userPicked.x][userPicked.y - 1].type.equals("EmptySpace")) {
//					possibleMoves[0] = new Coordinates(userPicked.x, userPicked.y - 1); // 1 back //
//				}
//				if (userPicked.x > 0) {
//					if (Board[userPicked.x - 1][userPicked.y - 1].team.equals("white")) {
//						possibleMoves[1] = new Coordinates(userPicked.x - 1, userPicked.y - 1); // 1 left 1 back // 
//					}
//				}
//				if (userPicked.x < 7) {
//					if (Board[userPicked.x + 1][userPicked.y - 1].team.equals("white")) {
//						possibleMoves[2] = new Coordinates(userPicked.x + 1, userPicked.y - 1); // 1 right 1 back //
//					}
//				}
//			}
//		}
//		return (possibleMoves);
//	}
//
//	private static Coordinates[] possibleMovesKing(GamePiece[][] Board, Coordinates userPicked) {
//		GamePiece King = Board[userPicked.x][userPicked.y];
//		Coordinates[] possibleMoves = new Coordinates[8];
//		if (userPicked.x > 0) {
//			possibleMoves[0] = new Coordinates(userPicked.x - 1, userPicked.y); // 1 left //
//		}
//		if (userPicked.x > 0 & userPicked.y > 0) {
//			possibleMoves[1] = new Coordinates(userPicked.x - 1, userPicked.y - 1); // 1 left 1 down//
//		}
//		if (userPicked.y > 0) {
//			possibleMoves[2] = new Coordinates(userPicked.x, userPicked.y - 1); // 1 down //
//		}
//		if (userPicked.x < 7 & userPicked.y > 0) {
//			possibleMoves[3] = new Coordinates(userPicked.x + 1, userPicked.y - 1); // 1 right 1 down // 
//		}
//		if (userPicked.x < 7) {
//			possibleMoves[4] = new Coordinates(userPicked.x + 1, userPicked.y); // 1 right //
//		}
//		if (userPicked.x < 7 & userPicked.y < 7) {
//			possibleMoves[5] = new Coordinates(userPicked.x + 1, userPicked.y + 1); // 1 right 1 up //
//		}
//		if (userPicked.y < 7) {
//			possibleMoves[6] = new Coordinates(userPicked.x, userPicked.y + 1); // 1 up //
//		}
//		if (userPicked.x > 0 & userPicked.y < 7) {
//			possibleMoves[7] = new Coordinates(userPicked.x - 1, userPicked.y + 1); // 1 left 1 up //
//		}
//		for (int i = 0; i < 8; i++) {
//			if (possibleMoves[i] != null) {
//				if (emptyOrEnemyOrFriendly(Board, possibleMoves[i], King.team) == "friendly") {
//					possibleMoves[i] = null;
//				}
//			}
//		}
//		return possibleMoves;
//	}
//
//	private static Coordinates[] possibleMovesBishop(GamePiece[][] Board, Coordinates userPicked) {
//		
//		Coordinates[] possibleMovesUpLeft = new Coordinates[7];
//		Coordinates[] possibleMovesUpRight = new Coordinates[7];
//		Coordinates[] possibleMovesDownLeft = new Coordinates[7];
//		Coordinates[] possibleMovesDownRight = new Coordinates[7];
//		if (possibleMovesUpLeft.length > 0) {
//			possibleMovesUpLeft = possibleMovesInDirection(Board, userPicked, -1, 1);
//		}
//		if (possibleMovesUpRight.length > 0) {
//			possibleMovesUpRight = possibleMovesInDirection(Board, userPicked, 1, 1);
//		}
//		if (possibleMovesDownLeft.length > 0) {
//			possibleMovesDownLeft = possibleMovesInDirection(Board, userPicked, -1, -1);
//		}
//		if (possibleMovesDownRight.length > 0) {
//			possibleMovesDownRight = possibleMovesInDirection(Board, userPicked, 1, -1);
//		}
//
//		Coordinates[][] possibleMovesArray = new Coordinates[4][];
//		possibleMovesArray[0] = possibleMovesUpLeft;
//		possibleMovesArray[1] = possibleMovesUpRight;
//		possibleMovesArray[2] = possibleMovesDownLeft;
//		possibleMovesArray[3] = possibleMovesDownRight;
//		Coordinates[] possibleMoves = (Coordinates[]) combineArrays(possibleMovesArray);
//		return possibleMoves;
//	}
//	
//	private static String emptyOrEnemyOrFriendly(GamePiece[][] Board, Coordinates selectedCoordinates, String colour) {
//		String enemyColour = "white";
//		if (colour == "white") {
//			enemyColour = "black";
//		}
//		if (Board[selectedCoordinates.x][selectedCoordinates.y].type.equals("EmptySpace")) {
//			return "empty";
//		} else if (Board[selectedCoordinates.x][selectedCoordinates.y].team.equals(enemyColour)) {
//			return "enemy";
//		}
//		return "friendly";
//	}
//
//	private static Coordinates[] possibleMovesInDirection(GamePiece[][] Board, Coordinates pieceCoords, int xIncrement, int yIncrement) {
//		boolean endOfLine = false;
//		GamePiece Piece = Board[pieceCoords.x][pieceCoords.y];
//		Coordinates[] possibleMoves = new Coordinates[7];
//		int i = 1;
//		while (endOfLine == false) {
//			Coordinates checkCoords = new Coordinates(pieceCoords.x + i * xIncrement, pieceCoords.y + i * yIncrement);
//			if (pieceCoords.x + i * xIncrement < 0 | pieceCoords.x + i * xIncrement > 7 | pieceCoords.y + i * yIncrement < 0 | pieceCoords.y + i * yIncrement > 7) {
//				endOfLine = true;
//			} else if (emptyOrEnemyOrFriendly(Board, checkCoords, Piece.team) == "enemy") {
//				possibleMoves[i-1] = checkCoords;
//				endOfLine = true;
//			} else if (emptyOrEnemyOrFriendly(Board, checkCoords, Piece.team) == "friendly") {
//				endOfLine = true;
//			} else {
//				possibleMoves[i-1] = checkCoords;
//			}
//			i++;
//		}
//		return possibleMoves;
//	}
//
//	private static Coordinates[] combineArrays(Coordinates[][] arrayOfArrays) {
//		if (arrayOfArrays.length == 0 | arrayOfArrays.length == 1) {
//			return arrayOfArrays[0];
//		} else {
//			int lastIndex = arrayOfArrays.length - 1;
//			Coordinates[][] compactArrayArray = new Coordinates[lastIndex][];
//			compactArrayArray[0] = appendTwoArrays(arrayOfArrays[0], arrayOfArrays[lastIndex]);
//			for (int i = 1; i < lastIndex; i++) {
//				compactArrayArray[i] = arrayOfArrays[i];
//			}
//			return combineArrays(compactArrayArray);
//		}
//	}
//
//	private static Coordinates[] appendTwoArrays(Coordinates[] firstArray, Coordinates[] secondArray) {
//		Coordinates[] joinedArray = new Coordinates[firstArray.length + secondArray.length];
//		for (int i = 0; i < firstArray.length; i++) {
//			joinedArray[i] = firstArray[i];
//		}
//		for (int j = 0; j < secondArray.length; j++) {
//			joinedArray[j + firstArray.length] = secondArray[j];
//		}
//		return joinedArray;
//	}
//}
