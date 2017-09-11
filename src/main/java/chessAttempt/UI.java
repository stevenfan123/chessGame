package chessAttempt;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import graphicsTesting.PieceButton;

public class UI {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(540,540);
		window.setTitle("Graphics Testing");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int selectedPiece;
		Container contentPane = window.getContentPane();
		String path = "E:/work/chessGame/src/resources/images/chessPieceImages/";
		PieceButton[] whitePawnButtons = new PieceButton[8];
		for(PieceButton pawnButton:whitePawnButtons){
			pawnButton = new PieceButton("white", "Pawn");
			pawnButton.coordinates = 10;
			contentPane.add(pawnButton);
		}
		PieceButton[] EmptyPieceButtons = new PieceButton[40];
		for(PieceButton pawnButton:EmptyPieceButtons){
			pawnButton = new PieceButton("", "EmptyPiece");
			contentPane.add(pawnButton);
		}
		PieceButton[] blackPawnButtons = new PieceButton[8];
		for(PieceButton pawnButton:blackPawnButtons){
			pawnButton = new PieceButton("black", "Pawn");
			contentPane.add(pawnButton);
			pawnButton.coordinates = 20;
		}
		

		FlowLayout layout = new FlowLayout();
		contentPane.setLayout(layout);
		window.setResizable(false);
		window.setVisible(true);
	}

}
