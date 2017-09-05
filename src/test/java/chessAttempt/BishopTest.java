package chessAttempt;

import static chessAttempt.Team.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class BishopTest extends GamePieceTest {
	
	private GameBoard gameBoard;

	@Before
	public void before() {
		gameBoard = generateEmptyBoard();
	}

	@Test
	public void generalMovement() {
		// 1st test case has no pieces obstructing it
		gameBoard.board[2][2] = new Bishop(WHITE, new Coordinates(2,2));
		// 2nd test case has friendly pieces obstructing some of its movement
		gameBoard.board[3][2] = new Bishop(WHITE, new Coordinates(3,2));
		gameBoard.board[1][0] = new Pawn(WHITE, new Coordinates(1,0));
		gameBoard.board[5][4] = new Pawn(WHITE, new Coordinates(5,4));
		gameBoard.board[2][3] = new Pawn(WHITE, new Coordinates(2,3));
		gameBoard.board[5][0] = new Pawn(WHITE, new Coordinates(5,0));
		// 3rd test case has enemy pieces obstructing some of its movement
		gameBoard.board[4][2] = new Bishop(WHITE, new Coordinates(4,2));
		gameBoard.board[6][4] = new Pawn(BLACK, new Coordinates(6,4));
		gameBoard.board[2][0] = new Pawn(BLACK, new Coordinates(2,0));
		gameBoard.board[2][4] = new Pawn(BLACK, new Coordinates(2,4));
		gameBoard.board[6][0] = new Pawn(BLACK, new Coordinates(6,0));
		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves2 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves3 = new HashSet<Coordinates>(); 

		expectedPermissibleMoves1.add(new Coordinates(0,0));
		expectedPermissibleMoves1.add(new Coordinates(1,1));
		expectedPermissibleMoves1.add(new Coordinates(3,3));
		expectedPermissibleMoves1.add(new Coordinates(4,4));
		expectedPermissibleMoves1.add(new Coordinates(5,5));
		expectedPermissibleMoves1.add(new Coordinates(6,6));
		expectedPermissibleMoves1.add(new Coordinates(7,7));
		expectedPermissibleMoves1.add(new Coordinates(1,3));
		expectedPermissibleMoves1.add(new Coordinates(0,4));
		expectedPermissibleMoves1.add(new Coordinates(3,1));
		expectedPermissibleMoves1.add(new Coordinates(4,0));
		
		expectedPermissibleMoves2.add(new Coordinates(2,1));
		expectedPermissibleMoves2.add(new Coordinates(4,3));
		expectedPermissibleMoves2.add(new Coordinates(4,1));

		
		expectedPermissibleMoves3.add(new Coordinates(5,3));
		expectedPermissibleMoves3.add(new Coordinates(6,4));
		expectedPermissibleMoves3.add(new Coordinates(3,1));
		expectedPermissibleMoves3.add(new Coordinates(2,0));
		expectedPermissibleMoves3.add(new Coordinates(3,3));
		expectedPermissibleMoves3.add(new Coordinates(2,4));
		expectedPermissibleMoves3.add(new Coordinates(5,1));
		expectedPermissibleMoves3.add(new Coordinates(6,0));
		

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard);
		Set<Coordinates> actualPermissibleMoves2 = gameBoard.board[3][2].permissibleMoves(gameBoard);
		Set<Coordinates> actualPermissibleMoves3 = gameBoard.board[4][2].permissibleMoves(gameBoard);


		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		assertEquals(expectedPermissibleMoves2, actualPermissibleMoves2);
		assertEquals(expectedPermissibleMoves3, actualPermissibleMoves3);


	}

}
