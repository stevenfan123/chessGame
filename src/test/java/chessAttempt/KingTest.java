package chessAttempt;

import static chessAttempt.Team.*;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class KingTest extends GamePieceTest{

	private GameBoard gameBoard;

	@Before
	public void before() {
		 gameBoard = generateEmptyBoard();
	}
	


	@Test
	public void atEdgesOfBoard() {
		// 1st test case has piece in the bottom left corner
		gameBoard.board[0][0] = new King(WHITE, new Coordinates(0,0));
		gameBoard.board[0][0].hasMoved = true;
		// 2nd test case has piece in the top left corner
		gameBoard.board[0][7] = new King(WHITE, new Coordinates(0,7));
		gameBoard.board[0][7].hasMoved = true;
		// 3rd test case has piece in the top right corner
		gameBoard.board[7][7] = new King(WHITE, new Coordinates(7,7));
		gameBoard.board[7][7].hasMoved = true;
		// 4th test case has piece in the bottom right corner
		gameBoard.board[7][0] = new King(WHITE, new Coordinates(7,0));
		gameBoard.board[7][0].hasMoved = true;

	
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves2 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves3 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves4 = new HashSet<Coordinates>(); 		

		expectedPermissibleMoves1.add(new Coordinates(0,1));
		expectedPermissibleMoves1.add(new Coordinates(1,1));
		expectedPermissibleMoves1.add(new Coordinates(1,0));
		
		expectedPermissibleMoves2.add(new Coordinates(0,6));
		expectedPermissibleMoves2.add(new Coordinates(1,6));
		expectedPermissibleMoves2.add(new Coordinates(1,7));
		
		expectedPermissibleMoves3.add(new Coordinates(7,6));
		expectedPermissibleMoves3.add(new Coordinates(6,6));
		expectedPermissibleMoves3.add(new Coordinates(6,7));
		
		expectedPermissibleMoves4.add(new Coordinates(7,1));
		expectedPermissibleMoves4.add(new Coordinates(6,1));
		expectedPermissibleMoves4.add(new Coordinates(6,0));
		
		

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[0][0].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves2 = gameBoard.board[0][7].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves3 = gameBoard.board[7][7].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves4 = gameBoard.board[7][0].permissibleMoves(gameBoard.board);


		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		assertEquals(expectedPermissibleMoves2, actualPermissibleMoves2);
		assertEquals(expectedPermissibleMoves3, actualPermissibleMoves3);
		assertEquals(expectedPermissibleMoves4, actualPermissibleMoves4);


	}

	@Test
	public void friendliesInWay() {
		// test case has friendlies at every possible move of the horse
		gameBoard.board[2][2] = new King(WHITE, new Coordinates(2,2));
		gameBoard.board[3][4] = new Pawn(WHITE, new Coordinates(3,4));
		gameBoard.board[4][3] = new Pawn(WHITE, new Coordinates(4,3));
		gameBoard.board[4][1] = new Pawn(WHITE, new Coordinates(4,1));
		gameBoard.board[3][0] = new Pawn(WHITE, new Coordinates(3,0));
		gameBoard.board[1][0] = new Pawn(WHITE, new Coordinates(1,0));
		gameBoard.board[0][1] = new Pawn(WHITE, new Coordinates(0,1));
		gameBoard.board[0][3] = new Pawn(WHITE, new Coordinates(0,3));
		gameBoard.board[1][4] = new Pawn(WHITE, new Coordinates(1,4));
		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard.board);
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		
	}
	
	@Test
	public void enemiesInWay() {
		// test case has friendlies at every possible move of the horse
		gameBoard.board[2][2] = new King(WHITE, new Coordinates(2,2));
		gameBoard.board[3][4] = new Pawn(BLACK, new Coordinates(3,4));
		gameBoard.board[4][3] = new Pawn(BLACK, new Coordinates(4,3));
		gameBoard.board[4][1] = new Pawn(BLACK, new Coordinates(4,1));
		gameBoard.board[3][0] = new Pawn(BLACK, new Coordinates(3,0));
		gameBoard.board[1][0] = new Pawn(BLACK, new Coordinates(1,0));
		gameBoard.board[0][1] = new Pawn(BLACK, new Coordinates(0,1));
		gameBoard.board[0][3] = new Pawn(BLACK, new Coordinates(0,3));
		gameBoard.board[1][4] = new Pawn(BLACK, new Coordinates(1,4));
		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		expectedPermissibleMoves1.add(new Coordinates(3,4));
		expectedPermissibleMoves1.add(new Coordinates(4,3));
		expectedPermissibleMoves1.add(new Coordinates(4,1));
		expectedPermissibleMoves1.add(new Coordinates(3,0));
		expectedPermissibleMoves1.add(new Coordinates(1,0));
		expectedPermissibleMoves1.add(new Coordinates(0,1));
		expectedPermissibleMoves1.add(new Coordinates(0,3));
		expectedPermissibleMoves1.add(new Coordinates(1,4));

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard.board);
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		
	}

	@Test
	public void nothingInWay() {
		// test case has friendlies at every possible move of the horse
		gameBoard.board[2][2] = new King(WHITE, new Coordinates(2,2));

		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		expectedPermissibleMoves1.add(new Coordinates(3,4));
		expectedPermissibleMoves1.add(new Coordinates(4,3));
		expectedPermissibleMoves1.add(new Coordinates(4,1));
		expectedPermissibleMoves1.add(new Coordinates(3,0));
		expectedPermissibleMoves1.add(new Coordinates(1,0));
		expectedPermissibleMoves1.add(new Coordinates(0,1));
		expectedPermissibleMoves1.add(new Coordinates(0,3));
		expectedPermissibleMoves1.add(new Coordinates(1,4));

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard.board);
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		
	}


	 
}