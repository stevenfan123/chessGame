package chessAttempt;

import java.util.HashSet;
import java.util.Set;
import static chessAttempt.Team.*;

abstract class GamePiece {
	public final Team team;
	protected Coordinates coordinates;
	public GamePiece(Team team, Coordinates coordinates){
		this.team = team;
		this.coordinates = coordinates;
		this.hasMoved = false;
	}
	protected boolean hasMoved;

	public  char display(){
		if(this.team.equals(WHITE)){
			return this.getClass().getSimpleName().charAt(0);
		} else{
			return this.getClass().getSimpleName().toLowerCase().charAt(0);
		}
	}
	
	public abstract Set<Coordinates> permissibleMoves(GamePiece[][] board);
	
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
}

class Pawn extends GamePiece {
	public Pawn(Team team, Coordinates coordinates) {
		super(team, coordinates);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GamePiece[][] board) {
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

		return (permissibleMoves);
	}	

}

class King extends GamePiece {
	public King(Team team, Coordinates coordinates) {
		super(team, coordinates);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GamePiece[][] board) {
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
		if(castlePermissible(board,this.coordinates)) {
			permissibleMoves.add(coordinates.add(3, 0));
		}
		return permissibleMoves;
	}
	
	private boolean castlePermissible(GamePiece[][] board, Coordinates userPicked) {
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

}

class Horse extends GamePiece {
	public Horse(Team team, Coordinates coordinates) {
		super(team, coordinates);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GamePiece[][] board) {
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
		return (permissibleMoves);
	}
	
}

class Castle extends GamePiece {
	public Castle(Team team, Coordinates coordinates) {
		super(team, coordinates);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GamePiece[][] board) {
		Set<Coordinates> permissibleMoves = new HashSet<>();
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, -1, 0));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 1, 0));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 0, -1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 0, 1));
		return permissibleMoves;
	}
	
}

class Bishop extends GamePiece {
	public Bishop(Team team, Coordinates coordinates) {
		super(team, coordinates);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GamePiece[][] board) {
		Set<Coordinates> permissibleMoves = new HashSet<>();
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, -1, 1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, -1, -1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 1, -1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 1, 1));
		return permissibleMoves;
	}
	
}

class Queen extends GamePiece {
	public Queen(Team team, Coordinates coordinates) {
		super(team, coordinates);
	}
	@Override
	public Set<Coordinates> permissibleMoves(GamePiece[][] board) {
		Set<Coordinates> permissibleMoves = new HashSet<>();
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, -1, 0));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 1, 0));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 0, -1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 0, 1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, -1, 1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, -1, -1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 1, -1));
		permissibleMoves.addAll(permissibleMovesInDirection(board, this.coordinates, 1, 1));
		return permissibleMoves;
	}
	
}

class EmptyPiece extends GamePiece {

	public EmptyPiece(Team team, Coordinates coordinates) {
		super(team, coordinates);
		this.hasMoved = true;
	}
	@Override
	public Set<Coordinates> permissibleMoves(GamePiece[][] board) {
		// TODO Auto-generated method stub
		Set<Coordinates> permissibleMoves = new HashSet<>();
		return permissibleMoves;
	}
	@Override
	public char display(){
		return ' ';
	}
	
	
}

