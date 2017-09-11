package chessAttempt;

import static chessAttempt.Team.*;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class HorseTest extends GamePieceTest{

	private GameBoard gameBoard;

	@Before
	public void before() {
		 gameBoard = generateEmptyBoard();
	}
	


	@Test
	public void atEdgesOfBoard() {
		// 1st test case has piece in the bottom left corner
		gameBoard.board[0][0] = new Horse(WHITE, new Coordinates(0,0));
		// 2nd test case has piece in the top left corner
		gameBoard.board[0][7] = new Horse(WHITE, new Coordinates(0,7));
		// 3rd test case has piece in the top right corner
		gameBoard.board[7][7] = new Horse(WHITE, new Coordinates(7,7));
		// 4th test case has piece in the bottom right corner
		gameBoard.board[7][0] = new Horse(WHITE, new Coordinates(7,0));
		// 5th test case has piece 1 off the bottom left corner
		gameBoard.board[1][1] = new Horse(WHITE, new Coordinates(1,1));
		// 6th test case has piece 1 off the top left corner
		gameBoard.board[1][6] = new Horse(WHITE, new Coordinates(1,6));
		// 7th test case has piece 1 off the top right corner
		gameBoard.board[6][6] = new Horse(WHITE, new Coordinates(6,6));
		// 8th test case has piece 1 off the bottom right corner
		gameBoard.board[6][1] = new Horse(WHITE, new Coordinates(6,1));
	
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves2 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves3 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves4 = new HashSet<Coordinates>(); 
		Set<Coordinates> expectedPermissibleMoves5 = new HashSet<Coordinates>(); 		
		Set<Coordinates> expectedPermissibleMoves6 = new HashSet<Coordinates>(); 		
		Set<Coordinates> expectedPermissibleMoves7 = new HashSet<Coordinates>(); 		
		Set<Coordinates> expectedPermissibleMoves8 = new HashSet<Coordinates>(); 		

		expectedPermissibleMoves1.add(new Coordinates(1,2));
		expectedPermissibleMoves1.add(new Coordinates(2,1));
		
		expectedPermissibleMoves2.add(new Coordinates(2,6));
		expectedPermissibleMoves2.add(new Coordinates(1,5));
		
		expectedPermissibleMoves3.add(new Coordinates(6,5));
		expectedPermissibleMoves3.add(new Coordinates(5,6));
		
		expectedPermissibleMoves4.add(new Coordinates(5,1));
		expectedPermissibleMoves4.add(new Coordinates(6,2));
		
		expectedPermissibleMoves5.add(new Coordinates(0,3));
		expectedPermissibleMoves5.add(new Coordinates(2,3));
		expectedPermissibleMoves5.add(new Coordinates(3,2));
		expectedPermissibleMoves5.add(new Coordinates(3,0));

		expectedPermissibleMoves6.add(new Coordinates(3,7));
		expectedPermissibleMoves6.add(new Coordinates(3,5));
		expectedPermissibleMoves6.add(new Coordinates(2,4));
		expectedPermissibleMoves6.add(new Coordinates(0,4));
		
		expectedPermissibleMoves7.add(new Coordinates(7,4));
		expectedPermissibleMoves7.add(new Coordinates(5,4));
		expectedPermissibleMoves7.add(new Coordinates(4,5));
		expectedPermissibleMoves7.add(new Coordinates(4,7));

		expectedPermissibleMoves8.add(new Coordinates(4,0));
		expectedPermissibleMoves8.add(new Coordinates(4,2));
		expectedPermissibleMoves8.add(new Coordinates(5,3));
		expectedPermissibleMoves8.add(new Coordinates(7,3));

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[0][0].permissibleMoves(gameBoard,true);
		Set<Coordinates> actualPermissibleMoves2 = gameBoard.board[0][7].permissibleMoves(gameBoard,true);
		Set<Coordinates> actualPermissibleMoves3 = gameBoard.board[7][7].permissibleMoves(gameBoard,true);
		Set<Coordinates> actualPermissibleMoves4 = gameBoard.board[7][0].permissibleMoves(gameBoard,true);
		Set<Coordinates> actualPermissibleMoves5 = gameBoard.board[1][1].permissibleMoves(gameBoard,true);
		Set<Coordinates> actualPermissibleMoves6 = gameBoard.board[1][6].permissibleMoves(gameBoard,true);
		Set<Coordinates> actualPermissibleMoves7 = gameBoard.board[6][6].permissibleMoves(gameBoard,true);
		Set<Coordinates> actualPermissibleMoves8 = gameBoard.board[6][1].permissibleMoves(gameBoard,true);

		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		assertEquals(expectedPermissibleMoves2, actualPermissibleMoves2);
		assertEquals(expectedPermissibleMoves3, actualPermissibleMoves3);
		assertEquals(expectedPermissibleMoves4, actualPermissibleMoves4);
		assertEquals(expectedPermissibleMoves5, actualPermissibleMoves5);
		assertEquals(expectedPermissibleMoves6, actualPermissibleMoves6);
		assertEquals(expectedPermissibleMoves7, actualPermissibleMoves7);
		assertEquals(expectedPermissibleMoves8, actualPermissibleMoves8);

	}

	@Test
	public void friendliesInWay() {
		// test case has friendlies at every possible move of the horse
		gameBoard.board[2][2] = new Horse(WHITE, new Coordinates(2,2));
		gameBoard.board[3][4] = new Pawn(WHITE, new Coordinates(3,4));
		gameBoard.board[4][3] = new Pawn(WHITE, new Coordinates(4,3));
		gameBoard.board[4][1] = new Pawn(WHITE, new Coordinates(4,1));
		gameBoard.board[3][0] = new Pawn(WHITE, new Coordinates(3,0));
		gameBoard.board[1][0] = new Pawn(WHITE, new Coordinates(1,0));
		gameBoard.board[0][1] = new Pawn(WHITE, new Coordinates(0,1));
		gameBoard.board[0][3] = new Pawn(WHITE, new Coordinates(0,3));
		gameBoard.board[1][4] = new Pawn(WHITE, new Coordinates(1,4));
		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard,true);
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		
	}
	
	@Test
	public void enemiesInWay() {
		// test case has friendlies at every possible move of the horse
		gameBoard.board[2][2] = new Horse(WHITE, new Coordinates(2,2));
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

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard,true);
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		
	}

	@Test
	public void nothingInWay() {
		// test case has friendlies at every possible move of the horse
		gameBoard.board[2][2] = new Horse(WHITE, new Coordinates(2,2));

		
		Set<Coordinates> expectedPermissibleMoves1 = new HashSet<Coordinates>(); 
		expectedPermissibleMoves1.add(new Coordinates(3,4));
		expectedPermissibleMoves1.add(new Coordinates(4,3));
		expectedPermissibleMoves1.add(new Coordinates(4,1));
		expectedPermissibleMoves1.add(new Coordinates(3,0));
		expectedPermissibleMoves1.add(new Coordinates(1,0));
		expectedPermissibleMoves1.add(new Coordinates(0,1));
		expectedPermissibleMoves1.add(new Coordinates(0,3));
		expectedPermissibleMoves1.add(new Coordinates(1,4));

		Set<Coordinates> actualPermissibleMoves1 = gameBoard.board[2][2].permissibleMoves(gameBoard,true);
		assertEquals(expectedPermissibleMoves1, actualPermissibleMoves1);
		
	}


	 
}