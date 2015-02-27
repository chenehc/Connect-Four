package connectFour;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class displayMsg extends JPanel{
	
	//shows the winner and ends the game
	public static void displayWinner(Piece player, Component c){
		if (player == Piece.RED){
			JOptionPane.showMessageDialog(c, "Red wins", "Winner!", JOptionPane.PLAIN_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(c, "Blue wins", "Winner!", JOptionPane.PLAIN_MESSAGE);
		}
		Board.endGame();
	}
	
	//displays the first player
	public static void displayFirst(Component c){
		if (Game.currentPlayer == Piece.RED){
			JOptionPane.showMessageDialog(c, "Red goes first", "First Player", JOptionPane.PLAIN_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(c, "Blue goes first", "First Player", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	//displays error resulted from imbalance of player pieces
	public static void displayError(Piece player, Component c){
		if (player == Piece.RED){
			JOptionPane.showMessageDialog(c, "Uneven amount of game pieces, too many red pieces", "Error.", JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(c, "Uneven amount of game pieces, too many blue pieces", "Error.", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//displays an error when inserting into a game which has not started yet
	public static void displayStartError(Component c){
		JOptionPane.showMessageDialog(c, "Press 'New Game' to start the game");
	}
	
	//this message plays when the user presses the 'End State' button
	public static void displayEndState(Component c){
		JOptionPane.showMessageDialog(c, "Game Over, press 'New Game' to play again");
	}
	
	//displays an error when making illegal moves
	public static void displayIllegalMove(Component c){
		JOptionPane.showMessageDialog(c, "Illegal Move, try again");
		Game.illegalMove=true;
	}
}
