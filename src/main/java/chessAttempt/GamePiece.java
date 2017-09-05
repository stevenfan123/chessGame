package chessAttempt;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import static chessAttempt.Team.*;

abstract class GamePiece implements Serializable {
	public final Team team;
	protected Coordinates coordinates;
	public GamePiece(Team team, Coordinates coordinates){
	
		this.team = team;
		this.coordinates = coordinates;
		this.hasMoved = false;
	}
	
	public GamePiece(GamePiece copyPiece){
		this.team = copyPiece.team;
		this.coordinates = new Coordinates(copyPiece.coordinates);
		this.hasMoved = copyPiece.hasMoved;
	}
	protected boolean hasMoved;

	public  char display(){
		if(this.team.equals(WHITE)){
			return this.getClass().getSimpleName().charAt(0);
		} else{
			return this.getClass().getSimpleName().toLowerCase().charAt(0);
		}
	}
	
	public abstract Set<Coordinates> permissibleMoves(GameBoard gameBoard, boolean considerCheck);
	
	protected static Set<Coordinates> refinePermissibleMoves(GameBoard gameBoard, Coordinates selectedPieceCoords, Set<Coordinates> permissibleMoves){
		Set<Coordinates> refinedPermissibleMoves = new HashSet<>();
		GameBoard gameBoardTemp = null;
		Team currentTeam = gameBoard.board[selectedPieceCoords.x][selectedPieceCoords.y].team;
		for(Coordinates permissibleMove:permissibleMoves){
			try {
				gameBoardTemp = (GameBoard) ObjectCloner.deepCopy(gameBoard);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gameBoardTemp = ChessGame.makeMove(gameBoardTemp, selectedPieceCoords,permissibleMove);
			if(currentTeam.equals(WHITE)){
				if(!gameBoardTemp.whiteKing.inCheck(gameBoardTemp)){
					refinedPermissibleMoves.add(permissibleMove);
				}
			} else{
				if(!gameBoardTemp.blackKing.inCheck(gameBoardTemp)){
					refinedPermissibleMoves.add(permissibleMove);
				}
			}		
		}
		return refinedPermissibleMoves;
	}
	protected static String emptyOrEnemyOrFriendly(GamePiece gamePiece, Team team) {
		Team enemyTeam = WHITE;
		if (team.equals(WHITE)) {
			enemyTeam = BLACK;
		}
		if (gamePiece.getClass().getSimpleName().equals("EmptyPiece")) {
			return "empty";
		} else if (gamePiece.team.equals(enemyTeam)) {
			return "enemy";
		}
		return "friendly";
	}
	protected Set<Coordinates> permissibleMovesInDirection(GamePiece[][] board, Coordinates pieceCoords, int xIncrement, int yIncrement) {
		boolean endOfLine = false;
		Set<Coordinates> permissibleMoves = new HashSet<>();
		int i = 1;
		while (endOfLine == false) {
			Coordinates checkCoords = this.coordinates.add(i * xIncrement,i * yIncrement);
			if (pieceCoords.x + i * xIncrement < 0 | pieceCoords.x + i * xIncrement > 7 | pieceCoords.y + i * yIncrement < 0 | pieceCoords.y + i * yIncrement > 7) {
				endOfLine = true;
			} else if (emptyOrEnemyOrFriendly(board[checkCoords.x][checkCoords.y], this.team).equals("enemy")) {
				permissibleMoves.add(checkCoords);
				endOfLine = true;
			} else if (emptyOrEnemyOrFriendly(board[checkCoords.x][checkCoords.y], this.team).equals("friendly")) {
				endOfLine = true;
			} else {
				permissibleMoves.add(checkCoords);
			}
			i++;
		}
		return permissibleMoves;
	}
	protected static boolean checkIfWouldBeCheck(GamePiece[][] board, Team team){
		return false;
		
	}
}

class Pawn extends GamePiece {
	public Pawn(Team team, Coordinates coordinates) {
		super(team, coordinates);
	}
	public Pawn(Pawn copyPawn){
		super(copyPawn);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GameBoard gameBoard, boolean considerCheck) {
		GamePiece[][] board = gameBoard.board;
		Set<Coordinates> permissibleMoves = new HashSet<>();
		int yDirection = -1;
		if (this.team.equals(WHITE)) {
			yDirection = 1;
		}
		if (this.hasMoved== false && emptyOrEnemyOrFriendly(board[this.coordinates.x][this.coordinates.y + yDirection], this.team).equals("empty") & emptyOrEnemyOrFriendly(board[this.coordinates.x][this.coordinates.y + 2*yDirection], this.team).equals("empty")) {
			permissibleMoves.add(coordinates.add(0,2*yDirection)); // 2 forward // 
		}
		if (this.coordinates.y+yDirection > -1 & this.coordinates.y+yDirection < 8) {
			if (emptyOrEnemyOrFriendly(board[this.coordinates.x][this.coordinates.y + yDirection],this.team).equals("empty")) {
				permissibleMoves.add(coordinates.add(0,yDirection)); // 1 forward // 
			}
			if (this.coordinates.x > 0) {
				if (emptyOrEnemyOrFriendly(board[this.coordinates.x-1][this.coordinates.y+yDirection], this.team).equals("enemy")) {
					permissibleMoves.add(coordinates.add(-1,yDirection)); // 1 left 1 forward //
				}
			}
			if (this.coordinates.x < 7) {
				if (emptyOrEnemyOrFriendly(board[this.coordinates.x+1][this.coordinates.y+yDirection], this.team).equals("enemy")) {
					permissibleMoves.add(coordinates.add(1,yDirection)); // 1 right 1 forward //
				}
			}
		}
		if(considerCheck){
			return refinePermissibleMoves(gameBoard,this.coordinates,permissibleMoves);
		} else{
			return permissibleMoves;
		}
	}	

}

class King extends GamePiece {
	public King(Team team, Coordinates coordinates) {
		super(team, coordinates);
	}
	public King(King copyKing) {
		super(copyKing);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GameBoard gameBoard, boolean considerCheck){
		GamePiece[][] board = gameBoard.board;
		Set<Coordinates> possibleMoves = new HashSet<>();
		if (this.coordinates.x > 0) {
			possibleMoves.add(coordinates.add(-1, 0)); // 1 left //
		}
		if (this.coordinates.x > 0 & this.coordinates.y > 0) {
			possibleMoves.add(coordinates.add(-1, -1)); // 1 left 1 down//
		}
		if (this.coordinates.y > 0) {
			possibleMoves.add(coordinates.add(0, -1)); // 1 down //
		}
		if (this.coordinates.x < 7 & this.coordinates.y > 0) {
			possibleMoves.add(coordinates.add(1, -1)); // 1 right 1 down // 
		}
		if (this.coordinates.x < 7) {
			possibleMoves.add(coordinates.add(1, 0)); // 1 right //
		}
		if (this.coordinates.x < 7 & this.coordinates.y < 7) {
			possibleMoves.add(coordinates.add(1, 1)); // 1 right 1 up //
		}
		if (this.coordinates.y < 7) {
			possibleMoves.add(coordinates.add(0, 1));// 1 up //
		}
		if (this.coordinates.x > 0 & this.coordinates.y < 7) {
			possibleMoves.add(coordinates.add(-1, 1)); // 1 left 1 up //
		}
		Set<Coordinates> permissibleMoves = new HashSet<>();
		for (Coordinates move:possibleMoves) {
			if (!emptyOrEnemyOrFriendly(board[move.x][move.y],this.team).equals("friendly")) {
				permissibleMoves.add(move);
			}
		}
		if(castlePermissible(board)) {
			permissibleMoves.add(coordinates.add(3, 0));
		}
		if(considerCheck){
			return refinePermissibleMoves(gameBoard,this.coordinates,permissibleMoves);
		} else{
			return permissibleMoves;
		}
	}
	
	public boolean castlePermissible(GamePiece[][] board) {
		Coordinates castlePosition = new Coordinates(7,7);
		if(this.team.equals(WHITE)) {
			castlePosition = new Coordinates(7,0);
		}
		if(this.hasMoved == false) {
			if(board[castlePosition.x][castlePosition.y].hasMoved == false) {
				if(emptyOrEnemyOrFriendly(board[this.coordinates.x+1][this.coordinates.y], this.team).equals("empty")){
					if(emptyOrEnemyOrFriendly(board[this.coordinates.x+2][this.coordinates.y], this.team).equals("empty")){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean inCheck(GameBoard gameBoard){
		GamePiece[][] board = gameBoard.board;
		GamePiece[] arrayOfPieces = new GamePiece[]{new King(this.team,this.coordinates), new Castle(this.team,this.coordinates),new Bishop(this.team,this.coordinates),new Horse(this.team,this.coordinates),new Queen(this.team,this.coordinates),new Pawn(this.team,this.coordinates)} ;
		for(GamePiece piece:arrayOfPieces){
			piece.hasMoved = this.hasMoved;
			board[this.coordinates.x][this.coordinates.y] = piece;
			for(Coordinates permissibleMove:piece.permissibleMoves(gameBoard, false)){		
				if(board[permissibleMove.x][permissibleMove.y].getClass().equals(piece.getClass()) & !board[permissibleMove.x][permissibleMove.y].team.equals(this.team)){
					board[this.coordinates.x][this.coordinates.y] = this;
					return true;
				}
			}
		}
//		try{
//			if(this.team.equals(WHITE)){
//				if((board[this.coordinates.x-1][this.coordinates.y+1].getClass().getSimpleName().equals("Pawn") & !board[this.coordinates.x-1][this.coordinates.y+1].team.equals(this.team )) |
//						(board[this.coordinates.x+1][this.coordinates.y+1].getClass().getSimpleName().equals("Pawn") & !board[this.coordinates.x+1][this.coordinates.y+1].team.equals(this.team ))){
//					return true;
//				}
//			} else{
//				if((board[this.coordinates.x-1][this.coordinates.y-1].getClass().getSimpleName().equals("Pawn") & !board[this.coordinates.x-1][this.coordinates.y+1].team.equals(this.team )) |
//						(board[this.coordinates.x+1][this.coordinates.y-1].getClass().getSimpleName().equals("Pawn") & !board[this.coordinates.x+1][this.coordinates.y+1].team.equals(this.team ))){
//					return true;
//				}
//			}
//		} catch(ArrayIndexOutOfBoundsException){
//			
//		}
		board[this.coordinates.x][this.coordinates.y] = this;
		return false;
	}

}

class Horse extends GamePiece {
	public Horse(Team team, Coordinates coordinates) {
		super(team, coordinates);
	}
	public Horse(Horse copyHorse) {
		super(copyHorse);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GameBoard gameBoard, boolean considerCheck){
		GamePiece[][] board = gameBoard.board;
		Set<Coordinates> possibleMoves = new HashSet<>();
		if (this.coordinates.x > 1 & this.coordinates.y > 0) {
			possibleMoves.add(coordinates.add(-2, -1)); // 2 left 1 down //
		}
		if (this.coordinates.x > 0 & this.coordinates.y > 1) {
			possibleMoves.add(coordinates.add(-1, -2)); // 1 left 2 down //
		}
		if (this.coordinates.x < 7 & this.coordinates.y > 1) {
			possibleMoves.add(coordinates.add(1, -2)); // 1 right 2 down //
		}
		if (this.coordinates.x < 6 & this.coordinates.y > 0) {
			possibleMoves.add(coordinates.add(2, -1)); // 2 right 1 down //
		}
		if (this.coordinates.x < 6 & this.coordinates.y < 7) {
			possibleMoves.add(coordinates.add(2, 1)); // 2 right 1 up //
		}
		if (this.coordinates.x < 7 & this.coordinates.y < 6) {
			possibleMoves.add(coordinates.add(1, 2)); // 1 right 2 up //
		}
		if (this.coordinates.x > 0 & this.coordinates.y < 6) {
			possibleMoves.add(coordinates.add(-1, 2)); // 1 left 2 up //
		}
		if (this.coordinates.x > 1 & this.coordinates.y < 7) {
			possibleMoves.add(coordinates.add(-2, 1)); // 2 left 1 up //
		}
		Set<Coordinates> permissibleMoves = new HashSet<>();
		for (Coordinates move:possibleMoves) {
				if (!emptyOrEnemyOrFriendly(board[move.x][move.y],this.team).equals("friendly")) {
					permissibleMoves.add(move);
				}
		}
		if(considerCheck){
			return refinePermissibleMoves(gameBoard,this.coordinates,permissibleMoves);
		} else{
			return permissibleMoves;
		}
	}
	
}

class Castle extends GamePiece {
	public Castle(Team team, Coordinates coordinates) {
		super(team, coordinates);
	}
	public Castle(Castle copyCastle) {
		super(copyCastle);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GameBoard gameBoard, boolean considerCheck) {
		GamePiece[][] board = gameBoard.board;
		Set<Coordinates> permissibleMoves = new HashSet<>();
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, -1, 0));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 1, 0));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 0, -1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 0, 1));
		if(castlePermissible(board,this.coordinates)){
			permissibleMoves.add(this.coordinates.add(-3, 0));
		}
		if(considerCheck){
			return refinePermissibleMoves(gameBoard,this.coordinates,permissibleMoves);
		} else{
			return permissibleMoves;
		}
	}
	
	private boolean castlePermissible(GamePiece[][] board, Coordinates userPicked){
		if(board[userPicked.x][userPicked.y].hasMoved == false & this.coordinates.x == 7){
			GamePiece gamePiece = board[userPicked.x-3][userPicked.y];
			if(gamePiece.getClass().getSimpleName().equals("King")){
				return ((King) board[userPicked.x-3][userPicked.y]).castlePermissible(board);
			}
		}
		return false;
	}
	
}

class Bishop extends GamePiece {
	public Bishop(Team team, Coordinates coordinates) {
		super(team, coordinates);
	}
	public Bishop(Bishop copyBishop) {
		super(copyBishop);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GameBoard gameBoard, boolean considerCheck) {
		GamePiece[][] board = gameBoard.board;
		Set<Coordinates> permissibleMoves = new HashSet<>();
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, -1, 1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, -1, -1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 1, -1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 1, 1));
		if(considerCheck){
			return refinePermissibleMoves(gameBoard,this.coordinates,permissibleMoves);
		} else{
			return permissibleMoves;
		}
	}
	
}

class Queen extends GamePiece {
	public Queen(Team team, Coordinates coordinates) {
		super(team, coordinates);
	}
	public Queen(Queen copyQueen) {
		super(copyQueen);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GameBoard gameBoard, boolean considerCheck) {
		GamePiece[][] board = gameBoard.board;
		Set<Coordinates> permissibleMoves = new HashSet<>();
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, -1, 0));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 1, 0));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 0, -1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 0, 1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, -1, 1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, -1, -1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 1, -1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 1, 1));
		if(considerCheck){
			return refinePermissibleMoves(gameBoard,this.coordinates,permissibleMoves);
		} else{
			return permissibleMoves;
		}
	}
	
}

class EmptyPiece extends GamePiece {

	public EmptyPiece(Team team, Coordinates coordinates) {
		super(team, coordinates);
		this.hasMoved = true;
	}
	public EmptyPiece(EmptyPiece copyEmptyPiece) {
		super(copyEmptyPiece);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GameBoard gameBoard, boolean considerCheck) {
		// TODO Auto-generated method stub
		Set<Coordinates> permissibleMoves = new HashSet<>();
		return permissibleMoves;
	}
	@Override
	public char display(){
		return ' ';
	}
}


