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
		while (gameBoard.won() == "No") {
			System.out.println("It's " + playerTurn + "'s turn ");
			boolean validSelection = false;
			Coordinates inputSelectCoords = new Coordinates(0, 0);
			while (validSelection == false) {
				inputSelectCoords = getPlayerInput(playerTurn);
				validSelection = checkValidCoord(gameBoard.board, inputSelectCoords, playerTurn);
			}
			GamePiece selectedPiece = gameBoard.board[inputSelectCoords.x][inputSelectCoords.y];
			Set<Coordinates> permissibleMoves = selectedPiece.permissibleMoves(gameBoard.board);
			System.out.println("The possible moves for the selected piece are: ");
			for (Coordinates move:permissibleMoves) {
				move.display();
			}
			validSelection = false;
			Coordinates inputMoveCoords = new Coordinates(0, 0);
			while (validSelection == false) {
				inputMoveCoords = getPlayerInput(playerTurn);
				if(permissibleMoves.contains(inputMoveCoords)) {
					validSelection = true;
				} else {
					System.out.println("That was not one of the listed possible moves");
				}
			}
			
			gameBoard = makeMove(gameBoard, inputSelectCoords, inputMoveCoords);
			gameBoard.display();
			playerTurn = changeTurn(playerTurn);
		}

	}

	private static Team changeTurn(Team playerTurn) {
		if (playerTurn.equals(WHITE)) {
			playerTurn = BLACK;
		} else {
			playerTurn = WHITE;
		}
		return playerTurn;
	}

	private static GameBoard makeMove(GameBoard gameBoard, Coordinates inputSelectCoords, Coordinates inputMoveCoords) {
		if ((gameBoard.board[inputSelectCoords.x][inputSelectCoords.y].getClass().getSimpleName() == "King"
				&& inputMoveCoords.x == inputSelectCoords.x + 3)
				|(gameBoard.board[inputMoveCoords.x][inputMoveCoords.y].getClass().getSimpleName() == "King"
				&& inputMoveCoords.x == inputSelectCoords.x + 3)) {
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

	private static boolean checkValidCoord(GamePiece[][] board, Coordinates inputCoords, Team playerTurn) {
		GamePiece selectedPiece = board[inputCoords.x][inputCoords.y];
		if (selectedPiece.team.equals(playerTurn)) {
			if (selectedPiece.permissibleMoves(board).isEmpty()) {
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
