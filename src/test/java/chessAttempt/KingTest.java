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
		gameBoard.board[1][1] = new Pawn(WHITE, new Coordinates(1,1));
		gameBoard.board[1][2] = new Pawn(WHITE, new Coordinates(1,2));
		gameBoard.board[1][3] = new Pawn(WHITE, new Coordinates(1,3));
		gameBoard.board[2][1] = new Pawn(WHITE, new Coordinates(2,1));
		gameBoard.board[2][3] = new Pawn(WHITE, new Coordinates(2,3));
		gameBoard.board[3][1] = new Pawn(WHITE, new Coordinates(3,1));
		gameBoard.board[3][2] = new Pawn(WHITE, new Coordinates(3,2));
		gameBoard.board[3][3] = new Pawn(WHITE, new Coordinates(3,3));
		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard.board);
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		
	}
	
	@Test
	public void enemiesInWay() {
		// test case has enemies at every possible move of the king
		gameBoard.board[2][2] = new King(WHITE, new Coordinates(2,2));
		gameBoard.board[1][1] = new Pawn(BLACK, new Coordinates(1,1));
		gameBoard.board[1][2] = new Pawn(BLACK, new Coordinates(1,2));
		gameBoard.board[1][3] = new Pawn(BLACK, new Coordinates(1,3));
		gameBoard.board[2][1] = new Pawn(BLACK, new Coordinates(2,1));
		gameBoard.board[2][3] = new Pawn(BLACK, new Coordinates(2,3));
		gameBoard.board[3][1] = new Pawn(BLACK, new Coordinates(3,1));
		gameBoard.board[3][2] = new Pawn(BLACK, new Coordinates(3,2));
		gameBoard.board[3][3] = new Pawn(BLACK, new Coordinates(3,3));
		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		expectedPermissibleMoves1.add(new Coordinates(1,1));
		expectedPermissibleMoves1.add(new Coordinates(1,2));
		expectedPermissibleMoves1.add(new Coordinates(1,3));
		expectedPermissibleMoves1.add(new Coordinates(2,1));
		expectedPermissibleMoves1.add(new Coordinates(2,3));
		expectedPermissibleMoves1.add(new Coordinates(3,1));
		expectedPermissibleMoves1.add(new Coordinates(3,2));
		expectedPermissibleMoves1.add(new Coordinates(3,3));

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard.board);
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		
	}

	@Test
	public void nothingInWay() {
		// test case has nothing in way of king
		gameBoard.board[2][2] = new King(WHITE, new Coordinates(2,2));

		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		expectedPermissibleMoves1.add(new Coordinates(1,1));
		expectedPermissibleMoves1.add(new Coordinates(1,2));
		expectedPermissibleMoves1.add(new Coordinates(1,3));
		expectedPermissibleMoves1.add(new Coordinates(2,1));
		expectedPermissibleMoves1.add(new Coordinates(2,3));
		expectedPermissibleMoves1.add(new Coordinates(3,1));
		expectedPermissibleMoves1.add(new Coordinates(3,2));
		expectedPermissibleMoves1.add(new Coordinates(3,3));

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard.board);
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		
	}

	@Test
	public void castling() {
		// 1st test case has nothing in way of king and castle in correct positions
		gameBoard.board[4][0] = new King(WHITE, new Coordinates(4,0));
		gameBoard.board[7][0] = new Castle(WHITE, new Coordinates(7,0));
		// 2nd test case has nothing in way of king and castle in correct positions
		gameBoard.board[4][7] = new King(BLACK, new Coordinates(4,7));
		gameBoard.board[7][7] = new Castle(BLACK, new Coordinates(7,7));
		// 3rd test case has king and castle in the wrong positions
		gameBoard.board[4][3] = new King(WHITE, new Coordinates(4,3));
		gameBoard.board[4][3].hasMoved = true;
		gameBoard.board[7][3] = new Castle(WHITE, new Coordinates(7,3));
		gameBoard.board[7][3].hasMoved = true;
		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>();
		Set<Coordinates> expectedPermissibleMoves2 = new HashSet<Coordinates>();
		Set<Coordinates> expectedPermissibleMoves3 = new HashSet<Coordinates>();
		
		expectedPermissibleMoves1.add(new Coordinates(3,0));
		expectedPermissibleMoves1.add(new Coordinates(3,1));
		expectedPermissibleMoves1.add(new Coordinates(4,1));
		expectedPermissibleMoves1.add(new Coordinates(5,1));
		expectedPermissibleMoves1.add(new Coordinates(5,0));
		expectedPermissibleMoves1.add(new Coordinates(7,0));

		expectedPermissibleMoves2.add(new Coordinates(3,7));
		expectedPermissibleMoves2.add(new Coordinates(3,6));
		expectedPermissibleMoves2.add(new Coordinates(4,6));
		expectedPermissibleMoves2.add(new Coordinates(5,6));
		expectedPermissibleMoves2.add(new Coordinates(5,7));
		expectedPermissibleMoves2.add(new Coordinates(7,7));
		
		expectedPermissibleMoves3.add(new Coordinates(3,2));
		expectedPermissibleMoves3.add(new Coordinates(3,3));
		expectedPermissibleMoves3.add(new Coordinates(3,4));
		expectedPermissibleMoves3.add(new Coordinates(4,4));
		expectedPermissibleMoves3.add(new Coordinates(4,2));
		expectedPermissibleMoves3.add(new Coordinates(5,4));
		expectedPermissibleMoves3.add(new Coordinates(5,3));
		expectedPermissibleMoves3.add(new Coordinates(5,2));


		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[4][0].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves2 = gameBoard.board[4][7].permissibleMoves(gameBoard.board);
		Set<Coordinates> actualPermissibleMoves3 = gameBoard.board[4][3].permissibleMoves(gameBoard.board);
		
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		assertEquals(expectedPermissibleMoves2, actualPermissibleMoves2);
		assertEquals(expectedPermissibleMoves3, actualPermissibleMoves3);
		
	}
	 
}