package connectFour;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class displayMsg extends JPanel{
	
	//shows the winner and ends the game
	public static void displayWinner(Piece player, Board b){
		if (player == Piece.RED){
			JOptionPane.showMessageDialog(b, "Red wins", "Winner!", JOptionPane.PLAIN_MESSAGE);
			b.changeTitle("Connect Four - Red Wins");
		}else{
			JOptionPane.showMessageDialog(b, "Blue wins", "Winner!", JOptionPane.PLAIN_MESSAGE);
			b.changeTitle("Connect Four - Blue Wins");
		}
		Board.endGame();
	}
	
	//displays a game drawn message when the game is in draw state
	public static void displayDraw(Board b){
		JOptionPane.showMessageDialog(b, "Game Draw", "Game Drawn", JOptionPane.PLAIN_MESSAGE);
		b.changeTitle("Connect Four - Game Drawn");
	}
	//displays the first player
	public static void displayFirst(Board b){
		if (Game.currentPlayer == Piece.RED){
			JOptionPane.showMessageDialog(b, "Red goes first", "First Player", JOptionPane.PLAIN_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(b, "Blue goes first", "First Player", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	//displays error resulted from imbalance of player pieces
	public static void displayError(Piece player, Board b){
		if (player == Piece.RED){
			JOptionPane.showMessageDialog(b, "Uneven amount of game pieces, too many red pieces", "Error.", JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(b, "Uneven amount of game pieces, too many blue pieces", "Error.", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//displays an error when inserting into a game which has not started yet
	public static void displayStartError(Board b){
		JOptionPane.showMessageDialog(b, "Press 'New Game' to start the game");
	}
	
	//this message plays when the user presses the 'End State' button
	public static void displayEndState(Board b){
		JOptionPane.showMessageDialog(b, "Game Over, press 'New Game' to play again");
	}
	
	//displays an error when making illegal moves
	public static void displayIllegalMove(Board b){
		JOptionPane.showMessageDialog(b, "Illegal Move, try again");
		Game.illegalMove=true;
	}
}
