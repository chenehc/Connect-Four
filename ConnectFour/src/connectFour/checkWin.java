package connectFour;

import java.awt.Component;

public class checkWin {
	//checks piece imbalance when the end game is pressed
	public static void checkState(BoardArray brd, Board b){
			if (Game.firstPlayer == Piece.BLUE && brd.countBlue() > brd.countRed() + 1){
				displayMsg.displayError(Piece.BLUE, b);
			}else if (Game.firstPlayer == Piece.RED && brd.countBlue() + 1 < brd.countRed()){
				displayMsg.displayError(Piece.RED, b);
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
					displayMsg.displayWinner(current, b);
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
					displayMsg.displayWinner(current, b);
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
					displayMsg.displayWinner(current, b);
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
					displayMsg.displayWinner(current, b);
				}
			}
		}
		
		if (Game.moveCount == 42){
			displayMsg.displayDraw(b);
		}
	}
}
