package chessAttempt;

import java.util.Scanner;

public class chessGame {

	public static void main(String[] args) {
		GameBoard gameBoard1 = new GameBoard();
		playGame(gameBoard1);

	}

	private static void playGame(GameBoard gameBoard) {
		String playerTurn = "white";
		while (gameBoard.won() == "No") {
			System.out.println("It's " + playerTurn + "'s turn ");
			boolean validSelection = false;
			Coordinates inputSelectCoords = new Coordinates(0, 0);
			while (validSelection == false) {
				inputSelectCoords = getPlayerInput(playerTurn);
				validSelection = checkValidCoord(gameBoard.Board, inputSelectCoords, playerTurn);
			}
			GamePiece selectedPiece = gameBoard.Board[inputSelectCoords.x][inputSelectCoords.y];
			Coordinates[] possibleMoves = selectedPiece.possibleMoves(gameBoard.Board, inputSelectCoords);
			System.out.println("The possible moves for the selected piece are: ");
			for (int i = 0; i < possibleMoves.length; i++) {
				if (possibleMoves[i] != null) {
					possibleMoves[i].display();
				}
			}
			validSelection = false;
			Coordinates inputMoveCoords = new Coordinates(0, 0);
			while (validSelection == false) {
				inputMoveCoords = getPlayerInput(playerTurn);
				for (int i = 0; i < possibleMoves.length; i++) {
					if (inputMoveCoords.equals(possibleMoves[i])) {
						validSelection = true;
					}
				}
			}
			gameBoard.Board[inputMoveCoords.x][inputMoveCoords.y] = gameBoard.Board[inputSelectCoords.x][inputSelectCoords.y];
			gameBoard.Board[inputSelectCoords.x][inputSelectCoords.y] = new EmptyPiece("none");
			gameBoard.display();
			if (playerTurn.equals("white")) {
				playerTurn = "black";
			} else {
				playerTurn = "white";
			}
		}

	}

	private static boolean checkValidCoord(GamePiece[][] board, Coordinates inputCoords, String playerTurn) {
		GamePiece selectedPiece = board[inputCoords.x][inputCoords.y];
		if (selectedPiece.team == playerTurn) {
			if(getLength(selectedPiece.possibleMoves(board, inputCoords)) != 0){
				return true;
			} else{
				System.out.println("The selected piece has no possible moves, select another piece");
				return false;
			}
		} else {
			System.out.println("That was not a valid selection, select another piece");
			return false;
		}
	}

	private static Coordinates getPlayerInput(String playerTurn) {
		Scanner scanner = new Scanner(System.in);
		String playerinput = scanner.next();
		Coordinates inputCoords = new Coordinates(Integer.parseInt(playerinput.substring(0, 1)), Integer.parseInt(playerinput.substring(1, 2)));
		return inputCoords;
	}
	
	public static int getLength(Coordinates[] arr){
	    int count = 0;
	    for(int i = 0; i< arr.length;i++){
	        if (arr[i] != null){
	            ++count;
	        }
	    }
	    return count;
	}
}
