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
		
		

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[0][0].permissibleMoves(gameBoard,true);
		Set<Coordinates> actualPermissibleMoves2 = gameBoard.board[0][7].permissibleMoves(gameBoard,true);
		Set<Coordinates> actualPermissibleMoves3 = gameBoard.board[7][7].permissibleMoves(gameBoard,true);
		Set<Coordinates> actualPermissibleMoves4 = gameBoard.board[7][0].permissibleMoves(gameBoard,true);


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
		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard,true);
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

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard,false);
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		//java.lang.AssertionError: expected:<[Coordinates [x=2, y=1], Coordinates [x=3, y=2], Coordinates [x=1, y=1], Coordinates [x=3, y=3], Coordinates [x=1, y=2], Coordinates [x=2, y=3], Coordinates [x=1, y=3], Coordinates [x=3, y=1]]> but was:<[Coordinates [x=3, y=1]]>



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

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard,true);
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


		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[4][0].permissibleMoves(gameBoard,true);
		Set<Coordinates> actualPermissibleMoves2 = gameBoard.board[4][7].permissibleMoves(gameBoard,true);
		Set<Coordinates> actualPermissibleMoves3 = gameBoard.board[4][3].permissibleMoves(gameBoard,true);
		
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		assertEquals(expectedPermissibleMoves2, actualPermissibleMoves2);
		assertEquals(expectedPermissibleMoves3, actualPermissibleMoves3);
		
	}
	
	@Test
	public void inCheck() {
		// 1st test case has white king in check by an enemy pawn
		gameBoard.board[0][0] = new King(WHITE, new Coordinates(0,0));
		gameBoard.board[0][0].hasMoved = true;
		gameBoard.board[1][1] = new Pawn(BLACK, new Coordinates(1,1));
		gameBoard.board[1][1].hasMoved = true;
		// 2nd test case has black king in check by an enemy pawn
		gameBoard.board[1][7] = new King(BLACK, new Coordinates(1,7));
		gameBoard.board[1][7].hasMoved = true;
		gameBoard.board[0][6] = new Pawn(WHITE, new Coordinates(0,6));
		gameBoard.board[0][6].hasMoved = true;
		// 3rd test case has king in check by enemy horse
		gameBoard.board[7][0] = new King(WHITE, new Coordinates(7,0));
		gameBoard.board[7][0].hasMoved = true;
		gameBoard.board[5][1] = new Horse(BLACK, new Coordinates(5,1));
		gameBoard.board[5][1].hasMoved = true;
		// 4th test case has king in "check" by friendly horse
		gameBoard.board[7][2] = new King(BLACK, new Coordinates(7,2));
		gameBoard.board[7][2].hasMoved = true;
		// 5th test case has king in protected by friendly pawn
		gameBoard.board[5][5] = new King(WHITE, new Coordinates(5,5));
		gameBoard.board[5][5].hasMoved = true;
		gameBoard.board[6][6] = new Pawn(WHITE, new Coordinates(6,6));
		gameBoard.board[6][6].hasMoved = true;
		gameBoard.board[7][7] = new Bishop(BLACK, new Coordinates(5,5));
		gameBoard.board[7][7].hasMoved = true;
		// 6th test has horse able to take the pawn placing the white king in check
		gameBoard.board[0][3] = new Horse(WHITE, new Coordinates(0,3));
		gameBoard.board[0][3].hasMoved = true;
		// 7th test has horse able to move in the way of line of taking
		gameBoard.board[0][5] = new King(WHITE, new Coordinates(0,5));
		gameBoard.board[0][5].hasMoved = true;
		gameBoard.board[2][5] = new Castle(BLACK, new Coordinates(2,5));
		gameBoard.board[2][5].hasMoved = true;
		gameBoard.board[3][5] = new Pawn(WHITE, new Coordinates(3,5));
		gameBoard.board[3][5].hasMoved = true;
		
		boolean expectedCheckStatus1 = true; 
		boolean expectedCheckStatus2 = true;
		boolean expectedCheckStatus3 = true;
		boolean expectedCheckStatus4 = false;
		Set<Coordinates> expectedPermissibleMoves5 = new HashSet<Coordinates>(); 		
		Set<Coordinates> expectedPermissibleMoves6 = new HashSet<Coordinates>();
		Set<Coordinates> expectedPermissibleMoves7 = new HashSet<Coordinates>();
		
		expectedPermissibleMoves5.add(new Coordinates(7,7));
		expectedPermissibleMoves6.add(new Coordinates(1,1));
		expectedPermissibleMoves7.add(new Coordinates(1,5));
		
		this.gameBoard.whiteKing = (King) gameBoard.board[0][0];
		boolean actualCheckStatus1 = ((King) gameBoard.board[0][0]).inCheck(gameBoard);
		this.gameBoard.whiteKing = (King) gameBoard.board[1][7];
		boolean actualCheckStatus2 = ((King) gameBoard.board[1][7]).inCheck(gameBoard);
		this.gameBoard.whiteKing = (King) gameBoard.board[7][0];
		boolean actualCheckStatus3 = ((King) gameBoard.board[7][0]).inCheck(gameBoard);
		this.gameBoard.blackKing = (King) gameBoard.board[7][2];
		boolean actualCheckStatus4 = ((King) gameBoard.board[7][2]).inCheck(gameBoard);
		this.gameBoard.whiteKing = (King) gameBoard.board[5][5];
		Set<Coordinates> actualPermissibleMoves5 = gameBoard.board[6][6].permissibleMoves(gameBoard,true);
		this.gameBoard.whiteKing = (King) gameBoard.board[0][0];
		Set<Coordinates> actualPermissibleMoves6 = gameBoard.board[0][3].permissibleMoves(gameBoard,true);
		this.gameBoard.whiteKing = (King) gameBoard.board[0][5];
		Set<Coordinates> actualPermissibleMoves7 = gameBoard.board[0][3].permissibleMoves(gameBoard,true);
		
		assertEquals(expectedCheckStatus1, actualCheckStatus1);
		assertEquals(expectedCheckStatus2, actualCheckStatus2);
		assertEquals(expectedCheckStatus3, actualCheckStatus3);
		assertEquals(expectedCheckStatus4, actualCheckStatus4);
		assertEquals(expectedPermissibleMoves5, actualPermissibleMoves5);
		assertEquals(expectedPermissibleMoves6, actualPermissibleMoves6);
		assertEquals(expectedPermissibleMoves7, actualPermissibleMoves7);


	}
	 
}