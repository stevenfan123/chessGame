package chessAttempt;

import java.util.Scanner;
import java.util.Set;
import static chessAttempt.Team.*;

public class ChessGame {

	public static void main(String[] args) {
		GameBoard gameBoard1 = new GameBoard();
		gameBoard1.display();
		playGame(gameBoard1);

	}

	private static void playGame(GameBoard gameBoard) {
		Team playerTurn = WHITE;
		gameBoard.won = false;
		while (gameBoard.won == false) {
			System.out.println("It's " + playerTurn + "'s turn ");
			Coordinates inputSelectCoords = getValidPlayerInput(gameBoard, playerTurn);
			GamePiece selectedPiece = gameBoard.board[inputSelectCoords.x][inputSelectCoords.y];
			Set<Coordinates> permissibleMoves = selectedPiece.permissibleMoves(gameBoard,true);
			System.out.println("The possible moves for the selected piece are: ");
			for (Coordinates move:permissibleMoves) {
				move.display();
			}
			Coordinates inputMoveCoords = getValidPlayerMove(playerTurn, permissibleMoves);	
			gameBoard = makeMove(gameBoard, inputSelectCoords, inputMoveCoords);
			gameBoard.display();
			playerTurn = changeTurn(playerTurn);
			if(playerTurn.equals(BLACK)){
				if(gameBoard.blackKing.inCheckMate(gameBoard)){
					System.out.println("Checkmate! White team wins!");
					gameBoard.won = true;
				} else if(gameBoard.blackKing.inCheck(gameBoard)){
					System.out.println("The black king is now in check!");
				}
			} else{
				if(gameBoard.whiteKing.inCheckMate(gameBoard)){
					System.out.println("Checkmate! Black team wins!");
					gameBoard.won = true;
				} else if(gameBoard.whiteKing.inCheck(gameBoard)){	
					System.out.println("The white king is now in check!");
				}
			}

		}

	}

	private static Coordinates getValidPlayerMove(Team playerTurn, Set<Coordinates> permissibleMoves) {
		boolean validSelection = false;
		Coordinates inputMoveCoords = new Coordinates(0, 0);
		while (validSelection == false) {
			inputMoveCoords = getPlayerInput(playerTurn);
			if(permissibleMoves.contains(inputMoveCoords)) {
				validSelection = true;
			} else {
				System.out.println("That was not one of the listed possible moves");
			}
		}
		return inputMoveCoords;
	}

	private static Coordinates getValidPlayerInput(GameBoard gameBoard, Team playerTurn) {
		boolean validSelection = false;
		Coordinates inputSelectCoords = new Coordinates(0, 0);
		while (validSelection == false) {
			inputSelectCoords = getPlayerInput(playerTurn);
			validSelection = checkValidCoord(gameBoard, inputSelectCoords, playerTurn);
		}
		return inputSelectCoords;
	}

	private static Team changeTurn(Team playerTurn) {
		if (playerTurn.equals(WHITE)) {
			playerTurn = BLACK;
		} else {
			playerTurn = WHITE;
		}
		return playerTurn;
	}

	static GameBoard makeMove(GameBoard gameBoard, Coordinates inputSelectCoords, Coordinates inputMoveCoords) {
		if ((gameBoard.board[inputSelectCoords.x][inputSelectCoords.y].getClass().getSimpleName().equals("King")
				&& inputMoveCoords.x == inputSelectCoords.x + 3)
				|(gameBoard.board[inputMoveCoords.x][inputMoveCoords.y].getClass().getSimpleName().equals("King")
				&& inputMoveCoords.x == inputSelectCoords.x - 3)) {
			GamePiece storedPiece = gameBoard.board[inputMoveCoords.x][inputMoveCoords.y];
			gameBoard.board[inputMoveCoords.x][inputMoveCoords.y] = gameBoard.board[inputSelectCoords.x][inputSelectCoords.y];
			gameBoard.board[inputMoveCoords.x][inputMoveCoords.y].coordinates = inputMoveCoords;
			gameBoard.board[inputMoveCoords.x][inputMoveCoords.y].hasMoved = true;
			gameBoard.board[inputSelectCoords.x][inputSelectCoords.y] = storedPiece;
			gameBoard.board[inputSelectCoords.x][inputSelectCoords.y].coordinates = inputSelectCoords;
			gameBoard.board[inputSelectCoords.x][inputSelectCoords.y].hasMoved = true;
		} else {
			gameBoard.board[inputMoveCoords.x][inputMoveCoords.y] = gameBoard.board[inputSelectCoords.x][inputSelectCoords.y];
			gameBoard.board[inputMoveCoords.x][inputMoveCoords.y].coordinates = inputMoveCoords;
			gameBoard.board[inputMoveCoords.x][inputMoveCoords.y].hasMoved = true;
			gameBoard.board[inputSelectCoords.x][inputSelectCoords.y] = new EmptyPiece(NONE,inputSelectCoords);
			
		}
		return gameBoard;
	}

	private static boolean checkValidCoord(GameBoard gameBoard, Coordinates inputCoords, Team playerTurn){
		GamePiece selectedPiece = gameBoard.board[inputCoords.x][inputCoords.y];
		if (selectedPiece.team.equals(playerTurn)) {
			if (selectedPiece.permissibleMoves(gameBoard, true).isEmpty()) {
				System.out.println("The selected piece has no possible moves, select another piece");
				return false;
			} else {

				return true;
			}
		} else {
			System.out.println("That was not a valid selection, select another piece");
			return false;
		}
	}

	private static Coordinates getPlayerInput(Team playerTurn) {
		Scanner scanner = new Scanner(System.in);
		String playerinput = scanner.next();
		Coordinates inputCoords = new Coordinates(Integer.parseInt(playerinput.substring(0, 1)),
				Integer.parseInt(playerinput.substring(1, 2)));
		return inputCoords;
	}


}
