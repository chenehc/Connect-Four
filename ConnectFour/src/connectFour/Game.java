package connectFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Game {
	public static Piece currentPlayer;
	public static Piece firstPlayer;
	public static boolean illegalMove = false;
	public static boolean isTie = false;
	private static int best_Column;
	
//	/**
//	 * This method adds and AI piece to the board, used in AI mode
//	 * @param c 	Board component for display
//	 * @param brd	BoardArray for piece manipulation
//	 */
//	public static void addDiskAI(int col, Board c, BoardArray brd){
//		//no move if game ended
//		if (Board.endGame) return;
//		
//		//essentially same as addDisk, except it adds the best_column
//		if (!legalMove(best_Column, brd)) displayIllegalMove(c);
//		else{
//			brd.addPiece(col, Piece.RED);
//		}
//		currentPlayer = Piece.BLUE;
//		//check if the game has ended, EMPTY signifies there are no winners yet, display the winning piece if the game has ended
//		if (checkWin.checkMove(brd) != Piece.EMPTY){
//			checkWin.displayWinner(checkWin.checkMove(brd), c);
//		}
//		c.repaint();
//	}
	
	/**
	 * This method adds a player piece to the board, used in two player mode
	 * @param col - The column number that is entered by the user when a column button is pressed
	 * @param c   - Board for display
	 * @param brd - BoardArray for board control
	 */
	public static void addDisk(int col, Board c, BoardArray brd){
		//no move if game ended
		if (Board.endGame) return;
		//if no player is chosen, display error
		if (currentPlayer != Piece.BLUE && currentPlayer != Piece.RED) displayStartError(c);

		//add the current player piece to the board if the move is legal
		if (!legalMove(col-1, brd)) displayIllegalMove(c);
		else { 
			brd.addPiece(col-1, currentPlayer);  
			if (c.getMode().equals("AI") && currentPlayer == Piece.BLUE){
        		brd.addPiece(makeAIMove(brd, c), Piece.RED);
        		currentPlayer = Piece.BLUE;
			}
		}
		
		//check state of board, whether there is a winner
		if (checkWin.checkMove(brd) != Piece.EMPTY){
			checkWin.displayWinner(checkWin.checkMove(brd), c);
		}
		
		if (c.getMode()!="AI"){
			//in turn player changing
			if (currentPlayer == Piece.BLUE){
				currentPlayer = Piece.RED;
				//TODO new update method for title change
				if (!Board.endGame) c.changeTitle("Connect Four - Red's Turn - Game in Progress");
			}else{
				currentPlayer = Piece.BLUE;
				if (!Board.endGame) c.changeTitle("Connect Four - Blue's Turn - Game in Progress");


			}
		}
        c.repaint();
	}
	
	/**
	 * This method checks if the move about to be made is valid on the board (the piece is not added past the top row)
	 * @param col 	- The column that the user has selected
	 * @param brd 	- BoardArray for checking the pieces on the board
	 * @return true if the move can be made, false if the move can't be made
	 */
	private static boolean legalMove(int col, BoardArray brd){
		if (brd.getPiece(0, col) != Piece.EMPTY) return false;
		return true;
	}
	
	/**
	 * This method finds the move that the AI should make and returns the best Column
	 * @param brd
	 * @param b
	 * @return
	 */
	//make move for AI
	//AI is always RED and the player is always BLUE
	private static int makeAIMove(BoardArray brd, Board b){
		Value(brd, 0, Piece.BLUE);
//		System.out.println(best_Column);
		return best_Column;
	}
	
	/**
	 * This method finds and compares the value of each possible move,
	 * when it finds the max value, it sets the best_Column to the column 
	 * with best value for the AI.
	 * @param brd
	 * @param depth
	 * @param player
	 * @return the value of each possible move and whether min or max is winning
	 */
	private static int Value(BoardArray brd, int depth, Piece player){
		//if the game has not ended, check which player is winning
		if (checkWin.checkMove(brd) != Piece.EMPTY ){
			if (checkWin.checkMove(brd) == Piece.BLUE)
				//max player is winning
				return 255 - depth;
			//min player is winning
			else return 0 + depth; 
		}
		//depth of search will be 4, so evaluate value of move when depth = 4
		if (depth == 4 || isTie){
			return brd.evaluate();
		}
		++depth;
		//find the best valued move and set the best_Column to the column with highest value
		if (player == Piece.BLUE){
			int max = Integer.MIN_VALUE;
			int column = 0;
			for (int i = 0; i < brd.getCol(); i++){ 
				if (legalMove(i, brd)) {
					brd.addPiece(i, Piece.BLUE);
					int value = Value(brd, depth, Piece.RED);
					if (max < value) {
						max = value;
						column = i;
					}
					brd.removePiece(i);
//					printBoard(brd);
				}
			}
//			System.out.println("best column" + best_Column);
			best_Column = column;
			return max;
		} else {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < brd.getCol(); i++) {
				if (legalMove(i, brd)) {
					brd.addPiece(i, Piece.RED);
					int value = Value(brd, depth, Piece.BLUE);
					if (min > value)
						min = value;
					brd.removePiece(i);
				}
			}
			return min;
		}
	}
	
	/**	
	 * This method determines which player goes first using RNG
	 * @param b - Board for display
	 */
	public static void findFirst(Board b){
		Random rng = new Random();
		int randomInt = rng.nextInt(100);
		//if the number generated is 0<=x<50, blue is first, else red is first
		if (randomInt < 50){
			firstPlayer = Piece.BLUE;
			currentPlayer = Piece.BLUE;
			b.setTitle("Connect Four - Blue's Turn - Game in Progress");
		}else{
			firstPlayer = Piece.RED;
			currentPlayer = Piece.RED;
			b.setTitle("Connect Four - Red's Turn - Game in Progress");
		}
	}
	
	
	/**
	 * This method displays the first player
	 * @param b - Board for display 
	 */
	private static void displayFirst(Board b){
		if (b.getMode().equals("AI")){
			if (Game.firstPlayer == Piece.RED){
				JOptionPane.showMessageDialog(b, "AI goes first", "First Player", JOptionPane.PLAIN_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(b, "You go first", "First Player", JOptionPane.PLAIN_MESSAGE);
			}
		}else{
			if (Game.currentPlayer == Piece.RED){
				JOptionPane.showMessageDialog(b, "Red goes first", "First Player", JOptionPane.PLAIN_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(b, "Blue goes first", "First Player", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	/**
	 * This method displays an error when inserting into a game which has not started yet
	 * @param b - Board for display
	 */
	private static void displayStartError(Board b){
		JOptionPane.showMessageDialog(b, "Press 'New Game' to start the game");
	}
	
	/**
	 * This method starts the game 
	 * @param b - Board for display
	 */
	public static void start(Board b){
		Board.endGame = false;
		findFirst(b);
		if (b.getMode().equals("AI") && firstPlayer == Piece.RED){
//			best_Column = 3;
			b.getBoardArray().addPiece(3, Piece.RED);
			currentPlayer = Piece.BLUE;
		}
		displayFirst(b);
	}
	
	//saves the game to a file, only one save state is allowed
	//save is permanent until overwritten with another "save"
	public static void save(BoardArray brd, Board b) throws FileNotFoundException{
		PrintWriter out = new PrintWriter(new File("save/save.txt"));
		String temp = "";
		//first line stores current player
		if (currentPlayer == Piece.RED) temp = "RED";
		else if (currentPlayer == Piece.BLUE) temp = "BLUE"; 
		out.println(temp);
		
		//second line stores mode
		out.println(b.getMode());
		
		//second line stores the game pieces
		for (int row = 0; row<brd.getRow(); row++){
			for (int col = 0; col<brd.getCol(); col++){
				
					switch (brd.getPiece(row, col)){
						case RED: out.print("RED,"); break;
						case BLUE: out.print("BLUE,"); break;
						case EMPTY: out.print("EMPTY,"); break;
					}
			}
		}
		JOptionPane.showMessageDialog(b, "Game saved");
		out.close();
	}
	
	/**
	 * This method resumes the game from the permanent save
	 * @param brd - BoardArray for write
	 * @param b   - Board for display
	 * @throws FileNotFoundException
	 */
	public static void resume(BoardArray brd, Board b) throws FileNotFoundException{
		Scanner in = new Scanner(new File("save/save.txt"));
		in.useDelimiter(",");
		
		//reads the first player
		String pl = in.nextLine();
		
		//reads the mode
		String mode = in.nextLine();
		b.setMode(mode);
		
		int row = 0;
		int col = 0;
		
		//reads the second line for how the board pieces are setup
		while(in.hasNext()){
			String t = in.next();
//			System.out.println(t);
			if (t.equals("RED")) brd.setPiece(row, col, Piece.RED); 
			else if (t.equals("BLUE")) brd.setPiece(row, col, Piece.BLUE);
			else if (t.equals("EMPTY")) brd.setPiece(row, col, Piece.EMPTY);

			if (col == 6){
				row ++;
				col = 0;
				continue;
			}
			col++;
		}
		
		//sets current player
		if (pl.equals("RED")) currentPlayer = Piece.RED;
		else if (pl.equals("BLUE")) currentPlayer = Piece.BLUE; 
		
		JOptionPane.showMessageDialog(b, "Game resumed.");
		in.close();
	}
	
	/**
	 * This method is for displaying an error when making illegal moves
	 * @param b - Board for display
	 */
	private static void displayIllegalMove(Board b){
		JOptionPane.showMessageDialog(b, "Illegal Move, try again");
	}
	
	//debug method, prints the BoardArray in console
	private static void printBoard(BoardArray brd){
		for (int row = 0; row<brd.getRow(); row++){
			for (int col = 0; col<brd.getCol(); col++){
				System.out.print(brd.getPiece(row,col) + "\t");
			}
			System.out.println();
		}
	}
	
	//unit testing
	public static void main(String args[]){
		BoardArray a= new BoardArray(6,7);
		Value(a, 0, Piece.BLUE);
	}
}
