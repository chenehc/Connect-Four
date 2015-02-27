package connectFour;

import java.awt.Component;
import java.util.Random;

import javax.swing.JFrame;

public class Game {
	public static Piece currentPlayer;
	public static Piece firstPlayer;
	public static boolean illegalMove = false;
	
	//add player piece to the board
	public static void addDisk(int col, Board c, BoardArray brd){
		if (Board.endGame) return;
		if (currentPlayer != Piece.BLUE && currentPlayer != Piece.RED) displayMsg.displayStartError(c);
        brd.addPiece(col-1);    
        checkWin.checkMove(brd, c);
        if (illegalMove) brd.removePiece(col-1);
        c.repaint();
	}
	
	//determines who goes first
	public static void findFirst(JFrame f){
		Random rng = new Random();
		int randomInt = rng.nextInt(100);
		//if the number generated is 0<=x<50, blue is first, else red is first
		if (randomInt < 50){
			firstPlayer = Piece.BLUE;
			currentPlayer = Piece.BLUE;
			f.setTitle("Connect Four - Blue's Turn - Game in Progress");
		}else{
			firstPlayer = Piece.RED;
			currentPlayer = Piece.RED;
			f.setTitle("Connect Four - Red's Turn - Game in Progress");
		}
	}
	
	//starts the game
	public static void start(Component c){
		Board.endGame = false;
		findFirst((JFrame) c);
		displayMsg.displayFirst(c);
	}

}
