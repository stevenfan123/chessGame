package ui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import chessAttempt.Coordinates;
import chessAttempt.GameBoard;
import chessAttempt.Pawn;
import chessAttempt.Team;
public class UITest {

	public static void main(String[] args) {
		
		GameBoard gameBoard = new GameBoard();
		JFrame window = new JFrame();
		window.setSize(540,540);
		window.setTitle("Graphics Testing");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int selectedPiece;
		Container contentPane = window.getContentPane();
		Pawn[] whitePawnButtons = new Pawn[8];
		int x=0;
		for(Pawn pawnButton:whitePawnButtons){
			pawnButton = new Pawn(Team.WHITE, new Coordinates(x++,0), gameBoard); 
					contentPane.add(pawnButton);
		}
		Pawn[] EmptyPawns = new Pawn[40];
		for(Pawn pawnButton:EmptyPawns){
			pawnButton = new Pawn(Team.NONE, new Coordinates(0,0), gameBoard); 
			contentPane.add(pawnButton);
		}
		Pawn[] blackPawnButtons = new Pawn[8];
		x=7;
		for(Pawn pawnButton:blackPawnButtons){
			pawnButton = new Pawn(Team.BLACK, new Coordinates(x++,0), gameBoard); 
			contentPane.add(pawnButton);
		}
		

		FlowLayout layout = new FlowLayout();
		contentPane.setLayout(layout);
		window.setVisible(true);
		window.setResizable(false);
	}

}

