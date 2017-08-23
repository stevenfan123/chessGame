package chessAttempt;

import static chessAttempt.Team.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CastleTest extends GamePieceTest {
	
	private GameBoard gameBoard;

	@Before
	public void before() {
		gameBoard = generateEmptyBoard();
	}

	@Test
	public void generalMovement() {
		// 1st test case has no pieces obstructing it
		gameBoard.board[2][2] = new Castle(WHITE, new Coordinates(2,2));
		gameBoard.board[2][2].hasMoved = true;
		// 2nd test case has friendly pieces obstructing some of its movement
		gameBoard.board[3][3] = new Castle(WHITE, new Coordinates(3,3));
		gameBoard.board[3][3].hasMoved = true;
		gameBoard.board[0][3] = new Pawn(WHITE, new Coordinates(0,3));
		gameBoard.board[3][1] = new Pawn(WHITE, new Coordinates(3,1));
		gameBoard.board[6][3] = new Pawn(WHITE, new Coordinates(6,3));
		gameBoard.board[3][5] = new Pawn(WHITE, new Coordinates(3,5));
		// 3rd test case has enemy pieces obstructing some of its movement
		gameBoard.board[4][4].hasMoved = true;
		gameBoard.board[4][4] = new Castle(WHITE, new Coordinates(4,4));
		gameBoard.board[4][5] = new Pawn(BLACK, new Coordinates(4,5));
		gameBoard.board[4][1] = new Pawn(BLACK, new Coordinates(4,1));
		gameBoard.board[6][4] = new Pawn(BLACK, new Coordinates(6,4));
		gameBoard.board[0][4] = new Pawn(BLACK, new Coordinates(0,4));
		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves2 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves3 = new HashSet<Coordinates>(); 

		expectedPermissibleMoves1.add(new Coordinates(0,2));
		expectedPermissibleMoves1.add(new Coordinates(1,2));
		expectedPermissibleMoves1.add(new Coordinates(3,2));
		expectedPermissibleMoves1.add(new Coordinates(4,2));
		expectedPermissibleMoves1.add(new Coordinates(5,2));
		expectedPermissibleMoves1.add(new Coordinates(6,2));
		expectedPermissibleMoves1.add(new Coordinates(7,2));
		expectedPermissibleMoves1.add(new Coordinates(2,0));
		expectedPermissibleMoves1.add(new Coordinates(2,1));
		expectedPermissibleMoves1.add(new Coordinates(2,3));
		expectedPermissibleMoves1.add(new Coordinates(2,4));
		expectedPermissibleMoves1.add(new Coordinates(2,5));
		expectedPermissibleMoves1.add(new Coordinates(2,6));
		expectedPermissibleMoves1.add(new Coordinates(2,7));
		
		expectedPermissibleMoves2.add(new Coordinates(1,3));
		expectedPermissibleMoves2.add(new Coordinates(2,3));
		expectedPermissibleMoves2.add(new Coordinates(5,3));
		expectedPermissibleMoves2.add(new Coordinates(4,3));
		expectedPermissibleMoves2.add(new Coordinates(3,2));
		expectedPermissibleMoves2.add(new Coordinates(3,4));
		
		expectedPermissibleMoves3.add(new Coordinates(4,5));
		expectedPermissibleMoves3.add(new Coordinates(4,3));
		expectedPermissibleMoves3.add(new Coordinates(4,2));
		expectedPermissibleMoves3.add(new Coordinates(4,1));
		expectedPermissibleMoves3.add(new Coordinates(0,4));
		expectedPermissibleMoves3.add(new Coordinates(1,4));
		expectedPermissibleMoves3.add(new Coordinates(2,4));
		expectedPermissibleMoves3.add(new Coordinates(3,4));
		expectedPermissibleMoves3.add(new Coordinates(5,4));
		expectedPermissibleMoves3.add(new Coordinates(6,4));

		

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves2 = gameBoard.board[3][3].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves3 = gameBoard.board[4][4].permissibleMoves(gameBoard.board);


		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		assertEquals(expectedPermissibleMoves2, actualPermissibleMoves2);
		assertEquals(expectedPermissibleMoves3, actualPermissibleMoves3);


	}
	@Test
	public void castling() {
		// 1st test case has nothing in way of king and castle in correct positions (but vertical movement of castle blocked)
		gameBoard.board[4][0] = new King(WHITE, new Coordinates(4,0));
		gameBoard.board[7][0] = new Castle(WHITE, new Coordinates(7,0));
		gameBoard.board[7][1] = new Pawn(WHITE, new Coordinates(7,1));
		// 2nd test case has nothing in way of king and castle in correct positions (but vertical movement of castle blocked)
		gameBoard.board[4][7] = new King(BLACK, new Coordinates(4,7));
		gameBoard.board[7][7] = new Castle(BLACK, new Coordinates(7,7));
		gameBoard.board[7][6] = new Pawn(BLACK, new Coordinates(7,6));
		// 3rd test case has king and castle in the wrong positions (but vertical movement of castle blocked)
		gameBoard.board[4][3] = new King(WHITE, new Coordinates(4,3)); 
		gameBoard.board[4][3].hasMoved = true;
		gameBoard.board[7][3] = new Castle(WHITE, new Coordinates(7,3));
		gameBoard.board[7][3].hasMoved = true;
		gameBoard.board[7][2] = new Pawn(WHITE, new Coordinates(7,2));
		gameBoard.board[7][4] = new Pawn(WHITE, new Coordinates(7,4));
		gameBoard.board[7][2].hasMoved = true;
		gameBoard.board[7][4].hasMoved = true;
		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>();
		Set<Coordinates> expectedPermissibleMoves2 = new HashSet<Coordinates>();
		Set<Coordinates> expectedPermissibleMoves3 = new HashSet<Coordinates>();
		
		expectedPermissibleMoves1.add(new Coordinates(4,0));
		expectedPermissibleMoves1.add(new Coordinates(5,0));
		expectedPermissibleMoves1.add(new Coordinates(6,0));

		expectedPermissibleMoves2.add(new Coordinates(4,7));
		expectedPermissibleMoves2.add(new Coordinates(5,7));
		expectedPermissibleMoves2.add(new Coordinates(6,7));
		
		expectedPermissibleMoves3.add(new Coordinates(5,3));
		expectedPermissibleMoves3.add(new Coordinates(6,3));


		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[7][0].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves2 = gameBoard.board[7][7].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves3 = gameBoard.board[7][3].permissibleMoves(gameBoard.board);
		
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		assertEquals(expectedPermissibleMoves2, actualPermissibleMoves2);
		assertEquals(expectedPermissibleMoves3, actualPermissibleMoves3);
		
	}

}
