package chessAttempt;

import static chessAttempt.Team.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class QueenTest extends GamePieceTest {
	
	private GameBoard gameBoard;

	@Before
	public void before() {
		gameBoard = generateEmptyBoard();
	}

	@Test
	public void generalMovement() {
		// test case has friendly and enemy pieces obstructing some of its movement
		gameBoard.board[3][2] = new Queen(WHITE, new Coordinates(3,2));
		gameBoard.board[1][0] = new Pawn(WHITE, new Coordinates(1,0));
		gameBoard.board[5][4] = new Pawn(BLACK, new Coordinates(5,4));
		gameBoard.board[2][3] = new Pawn(WHITE, new Coordinates(2,3));
		gameBoard.board[5][0] = new Pawn(WHITE, new Coordinates(5,0));
		gameBoard.board[1][2] = new Pawn(BLACK, new Coordinates(1,2));
		gameBoard.board[4][2] = new Pawn(BLACK, new Coordinates(4,2));
		gameBoard.board[3][0] = new Pawn(WHITE, new Coordinates(3,0));
		gameBoard.board[3][7] = new Pawn(BLACK, new Coordinates(3,7));


		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 


		expectedPermissibleMoves1.add(new Coordinates(2,1));
		expectedPermissibleMoves1.add(new Coordinates(4,3));
		expectedPermissibleMoves1.add(new Coordinates(5,4));
		expectedPermissibleMoves1.add(new Coordinates(4,1));
		expectedPermissibleMoves1.add(new Coordinates(2,2));
		expectedPermissibleMoves1.add(new Coordinates(1,2));
		expectedPermissibleMoves1.add(new Coordinates(4,2));
		expectedPermissibleMoves1.add(new Coordinates(3,1));
		expectedPermissibleMoves1.add(new Coordinates(3,3));
		expectedPermissibleMoves1.add(new Coordinates(3,4));
		expectedPermissibleMoves1.add(new Coordinates(3,5));
		expectedPermissibleMoves1.add(new Coordinates(3,6));
		expectedPermissibleMoves1.add(new Coordinates(3,7));
		


		

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[3][2].permissibleMoves(gameBoard,false);



		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);


	}

}

