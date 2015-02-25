package connectFour;

import java.awt.Component;

public class checkWin {
	public static void check(BoardArray brd, Component c){
			if (brd.countBlue() > brd.countRed()){
				displayMsg.displayError(Piece.BLUE, c);
			}else if (brd.countBlue() < brd.countRed()){
				displayMsg.displayError(Piece.RED, c);
			}else{
				//checks vertical, horizontal, diagonal win here.
			}
		}
}
