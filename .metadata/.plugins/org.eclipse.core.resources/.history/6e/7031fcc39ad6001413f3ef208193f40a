package connectFour;

import java.awt.Component;

import javax.swing.JOptionPane;

public class checkWin {

	//displays a game drawn message when the game is in draw state
	private static void displayDraw(Board b){
		b.changeTitle("Connect Four - Game Drawn");
		JOptionPane.showMessageDialog(b, "Game Draw", "Game Drawn", JOptionPane.PLAIN_MESSAGE);
	}

	//displays a message with the winner
	private static void displayWinner(Piece player, Board b){
		if (player == Piece.RED){
			b.changeTitle("Connect Four - Red Wins");
			JOptionPane.showMessageDialog(b, "Red wins", "Winner!", JOptionPane.PLAIN_MESSAGE);
			
		}else{
			b.changeTitle("Connect Four - Blue Wins");
			JOptionPane.showMessageDialog(b, "Blue wins", "Winner!", JOptionPane.PLAIN_MESSAGE);
		}

		Board.endGame();
	}

	//displays error resulted from imbalance of player pieces
	private static void displayError(Piece player, Board b){
		if (player == Piece.RED){
			JOptionPane.showMessageDialog(b, "Uneven amount of game pieces, too many red pieces", "Error.", JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(b, "Uneven amount of game pieces, too many blue pieces", "Error.", JOptionPane.ERROR_MESSAGE);
		}
	}
	

	//checks piece imbalance when the end game is pressed
	public static void checkState(BoardArray brd, Board b){
			if (Game.firstPlayer == Piece.BLUE && brd.countBlue() > brd.countRed() + 1){
				displayError(Piece.BLUE, b);
			}else if (Game.firstPlayer == Piece.RED && brd.countBlue() + 1 < brd.countRed()){
				displayError(Piece.RED, b);
			}
	}
	
	//checks whether there is 4 in a row anywhere
	public static void checkMove(BoardArray brd, Board b){
		//checks the row 
		for (int row=0; row<brd.getRow(); row++) {
			for (int col=0; col<brd.getCol()-3; col++) {
				Piece current = brd.getPiece(row, col);
				if (current!=Piece.EMPTY 
						&& current == brd.getPiece(row,col+1) 
						&& current == brd.getPiece(row, col+2) 
						&& current == brd.getPiece(row, col+3)) {
					displayWinner(current, b);
				}
			}
		}

		//check if there are 4 same consecutive pieces in a column			
		for (int col=0; col<brd.getCol(); col++){
			for (int row=0; row<brd.getRow()-3; row++){
				Piece current = brd.getPiece(row, col);
				if (current != Piece.EMPTY
						&& current == brd.getPiece(row+1,col) 
						&& current == brd.getPiece(row+2,col) 
						&& current == brd.getPiece(row+3,col)){
					displayWinner(current, b);
				}
			}
		}

		//check if there are 4 same consecutive pieces going diagonal
		//checks from bottom left to top right
		for (int row=0; row<brd.getRow()-3; row++){
			for (int col=0; col<brd.getCol()-3; col++){
				Piece current = brd.getPiece(row, col);
				if (current != Piece.EMPTY 
						&& current == brd.getPiece(row+1,col+1) 
						&& current == brd.getPiece(row+2,col+2)  
						&& current == brd.getPiece(row+3,col+3) ){
					displayWinner(current, b);
				}
			}
		}

		//checks from top left to bottom right
		for (int row=brd.getRow()-1; row>=3; row--){
			for (int col=0; col<brd.getCol()-3; col++){
				Piece current = brd.getPiece(row, col);
				if (current != Piece.EMPTY
						&& current == brd.getPiece(row-1,col+1) 
						&& current == brd.getPiece(row-2,col+2)  
						&& current == brd.getPiece(row-3,col+3)){
					displayWinner(current, b);
				}
			}
		}
		
		if (Game.moveCount == 42){
			displayDraw(b);
		}
	}
}
