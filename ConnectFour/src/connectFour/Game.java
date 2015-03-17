package connectFour;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game {
	public static Piece currentPlayer;
	public static Piece firstPlayer;
	public static boolean illegalMove = false;
	public static int moveCount = 0;
	
	//add player piece to the board
	public static void addDisk(int col, Board c, BoardArray brd){
		if (Board.endGame) return;
		if (currentPlayer != Piece.BLUE && currentPlayer != Piece.RED) displayStartError(c);
        brd.addPiece(col-1);    
        checkWin.checkMove(brd, c);
        if (illegalMove) brd.removePiece(col-1);
        if (currentPlayer == Piece.BLUE){
        	currentPlayer = Piece.RED;
        	c.changeTitle("Connect Four - Red's Turn - Game in Progress");
        }else{
        	currentPlayer = Piece.BLUE;
        	c.changeTitle("Connect Four - Blue's Turn - Game in Progress");
        }
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

	//displays the first player
	private static void displayFirst(Board b){
		if (Game.currentPlayer == Piece.RED){
			JOptionPane.showMessageDialog(b, "Red goes first", "First Player", JOptionPane.PLAIN_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(b, "Blue goes first", "First Player", JOptionPane.PLAIN_MESSAGE);
		}
	}

	//displays an error when inserting into a game which has not started yet
	private static void displayStartError(Board b){
		JOptionPane.showMessageDialog(b, "Press 'New Game' to start the game");
	}
	
	//starts the game
	public static void start(Board c){
		Board.endGame = false;
		findFirst((JFrame) c);
		displayFirst(c);
	}

	public static void pause(BoardArray brd) throws FileNotFoundException{
		PrintWriter out = new PrintWriter(new File("save.txt"));
		for (int row = 0; row<brd.getRow(); row++){
			for (int col = 0; col<brd.getCol(); col++){
				if (row == brd.getRow()-1){
					out.print("\n");
				}
					out.print(brd.getPiece(row, col) + ",");
			}
		}
		
		out.close();
	}

	public static void resume(BoardArray brd) throws FileNotFoundException{
		Scanner in = new Scanner(new File("save.txt"));
		in.close();
	}
}
