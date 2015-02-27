package connectFour;

import java.awt.Component;

public class checkWin {
	public static void check(BoardArray brd, Component c){
			if (Game.firstPlayer == Piece.BLUE && brd.countBlue() > brd.countRed() + 1){
				displayMsg.displayError(Piece.BLUE, c);
			}else if (Game.firstPlayer == Piece.RED && brd.countBlue() + 1 < brd.countRed()){
				displayMsg.displayError(Piece.RED, c);
			}else{
				//checks vertical, horizontal, diagonal win here.
			}
		}
}
