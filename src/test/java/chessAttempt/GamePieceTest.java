package chessAttempt;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;



import static chessAttempt.Team.*;

public abstract class GamePieceTest {

	
	
	protected GameBoard generateEmptyBoard() {
		GameBoard gameBoard = new GameBoard();
		for(int i = 0; i<8;i++) {
			for(int j = 0; j <8; j++) {
				gameBoard.board[i][j] = new EmptyPiece(NONE,new Coordinates(i,j));
			}
		}
		return gameBoard;
	}

}

