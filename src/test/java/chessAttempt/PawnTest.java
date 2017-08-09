package chessAttempt;

import static chessAttempt.Team.*;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class PawnTest extends GamePieceTest{

	private GameBoard gameBoard;

	@Before
	public void before() {
		 gameBoard = generateEmptyBoard();
	}
	


	@Test
	public void forwardTwoSpaces() {
		// 1st test case has enemy piece directly in front
		gameBoard.board[0][1] = new Pawn(WHITE, new Coordinates(0,1));
		gameBoard.board[0][2] = new Pawn(BLACK, new Coordinates(0,2));
		// 2nd test case has friendly piece directly in front
		gameBoard.board[2][1] = new Pawn(WHITE, new Coordinates(2,1)); 
		gameBoard.board[2][2] = new Pawn(WHITE, new Coordinates(2,2));
		// 3rd test case has enemy piece 2 places in front
		gameBoard.board[3][1] = new Pawn(WHITE, new Coordinates(3,1));
		gameBoard.board[3][3] = new Pawn(BLACK, new Coordinates(3,3));
		// 4th test case has friendly piece 2 places in front
		gameBoard.board[4][1] = new Pawn(WHITE, new Coordinates(4,1));
		gameBoard.board[4][3] = new Pawn(WHITE, new Coordinates(4,3));
		// 5th test case has no piece in the way
		gameBoard.board[5][1] = new Pawn(WHITE, new Coordinates(5,1));

		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>();
		Set<Coordinates> expectedPermissibleMoves2 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves3 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves4 = new HashSet<Coordinates>();
		Set<Coordinates> expectedPermissibleMoves5 = new HashSet<Coordinates>();
		expectedPermissibleMoves3.add(new Coordinates(3,2));
		expectedPermissibleMoves4.add(new Coordinates(4,2));
		expectedPermissibleMoves5.add(new Coordinates(5,2));
		expectedPermissibleMoves5.add(new Coordinates(5,3));
		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[0][1].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves2 = gameBoard.board[2][1].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves3 = gameBoard.board[3][1].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves4 = gameBoard.board[4][1].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves5 = gameBoard.board[5][1].permissibleMoves(gameBoard.board);
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		assertEquals(expectedPermissibleMoves2, actualPermissibleMoves2);
		assertEquals(expectedPermissibleMoves3, actualPermissibleMoves3);
		assertEquals(expectedPermissibleMoves4, actualPermissibleMoves4);
		assertEquals(expectedPermissibleMoves5, actualPermissibleMoves5);	
	}

	@Test
	public void forwardOneSpace() {
		// 1st test case has enemy piece directly in front
		gameBoard.board[0][2] = new Pawn(WHITE, new Coordinates(0,2));
		gameBoard.board[0][3] = new Pawn(BLACK, new Coordinates(0,3));
		gameBoard.board[0][2].hasMoved = true;
		gameBoard.board[0][3].hasMoved = true;
		// 2nd test case has friendly piece directly in front
		gameBoard.board[2][2] = new Pawn(WHITE, new Coordinates(2,2));
		gameBoard.board[2][3] = new Pawn(WHITE, new Coordinates(2,3));
		gameBoard.board[2][2].hasMoved = true;
		gameBoard.board[2][3].hasMoved = true;
		// 3rd test case has no piece in the way
		gameBoard.board[3][2] = new Pawn(WHITE, new Coordinates(3,2));
		gameBoard.board[3][2].hasMoved = true;
		// 4th test case is at 7th row along
		gameBoard.board[0][6] = new Pawn(WHITE, new Coordinates(0,6));
		gameBoard.board[0][6].hasMoved = true;		
		// 5th test case is at 8th row along
		gameBoard.board[1][7] = new Pawn(WHITE, new Coordinates(1,7));
		gameBoard.board[1][7].hasMoved = true;
		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves2 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves3 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves4 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves5 = new HashSet<Coordinates>(); 

		expectedPermissibleMoves3.add(new Coordinates(3,3));
		expectedPermissibleMoves4.add(new Coordinates(0,7));
		
		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[0][2].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves2 = gameBoard.board[2][2].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves3 = gameBoard.board[3][2].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves4 = gameBoard.board[0][6].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves5 = gameBoard.board[1][7].permissibleMoves(gameBoard.board);

		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		assertEquals(expectedPermissibleMoves2, actualPermissibleMoves2);
		assertEquals(expectedPermissibleMoves3, actualPermissibleMoves3);
		assertEquals(expectedPermissibleMoves4, actualPermissibleMoves4);
		assertEquals(expectedPermissibleMoves5, actualPermissibleMoves5);
		
	}

	@ Test
	public void forwardDiagnoal() {
		 // 1st test case has enemy pieces in forward diagonal places
		gameBoard.board[1][2] = new Pawn(WHITE, new Coordinates(1,2));
		gameBoard.board[0][3] = new Pawn(BLACK, new Coordinates(0,3));
		gameBoard.board[2][3] = new Pawn(BLACK, new Coordinates(2,3));
		gameBoard.board[1][2].hasMoved = true;
		gameBoard.board[0][3].hasMoved = true;
		gameBoard.board[3][3].hasMoved = true;
		// 2nd test case has friendly pieces in forward diagonal places
		gameBoard.board[6][2] = new Pawn(WHITE, new Coordinates(6,2));
		gameBoard.board[5][3] = new Pawn(WHITE, new Coordinates(5,3));
		gameBoard.board[7][3] = new Pawn(WHITE, new Coordinates(7,3));
		gameBoard.board[6][2].hasMoved = true;
		gameBoard.board[5][3].hasMoved = true;
		gameBoard.board[7][3].hasMoved = true;
		// 3rd test case has no pieces in forward diagonal places
		gameBoard.board[4][3] = new Pawn(WHITE, new Coordinates(4,3)); 
		gameBoard.board[4][3].hasMoved = true;
		// 4th test case has piece in 0th column
		gameBoard.board[0][6] = new Pawn(WHITE, new Coordinates(0,6));
		gameBoard.board[0][6].hasMoved = true;
		 // 5th test case has piece in 7th column
		gameBoard.board[7][6] = new Pawn(WHITE, new Coordinates(7,6));
		gameBoard.board[7][6].hasMoved = true;
		// 6th test case has piece in 7th row
		gameBoard.board[4][7] = new Pawn(WHITE, new Coordinates(4,7));
		gameBoard.board[4][7].hasMoved = true;

		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>();
		Set<Coordinates> expectedPermissibleMoves2 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves3 = new HashSet<Coordinates>();
		Set<Coordinates> expectedPermissibleMoves4 = new HashSet<Coordinates>();
		Set<Coordinates> expectedPermissibleMoves5 = new HashSet<Coordinates>();
		Set<Coordinates> expectedPermissibleMoves6 = new HashSet<Coordinates>(); 

		expectedPermissibleMoves1.add(new Coordinates(1,3));
		expectedPermissibleMoves1.add(new Coordinates(0,3));
		expectedPermissibleMoves1.add(new Coordinates(2,3));
		expectedPermissibleMoves2.add(new Coordinates(6,3));
		expectedPermissibleMoves3.add(new Coordinates(4,4));
		expectedPermissibleMoves4.add(new Coordinates(0,7));
		expectedPermissibleMoves5.add(new Coordinates(7,7));
		expectedPermissibleMoves3.add(new Coordinates(4,4));
		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[1][2].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves2 = gameBoard.board[6][2].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves3 = gameBoard.board[4][3].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves4 = gameBoard.board[0][6].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves5 = gameBoard.board[7][6].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves6 = gameBoard.board[4][7].permissibleMoves(gameBoard.board);
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		assertEquals(expectedPermissibleMoves2, actualPermissibleMoves2);
		assertEquals(expectedPermissibleMoves3, actualPermissibleMoves3);
		assertEquals(expectedPermissibleMoves4, actualPermissibleMoves4);
		assertEquals(expectedPermissibleMoves5, actualPermissibleMoves5);
		assertEquals(expectedPermissibleMoves6, actualPermissibleMoves6);
		
	}



	 
}
