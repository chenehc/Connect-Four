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
	private static int best_Column;
	
	/**
	 * This method displays an error if the user tries to setup a winning board
	 */
	private static void displayMoveError(){
		JOptionPane.showMessageDialog(new JOptionPane(), "Invalid move, try again");
	}
	
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
			if (c.getMode().equals("player")){
				//in turn player changing
				if (currentPlayer == Piece.BLUE){
					currentPlayer = Piece.RED;
					if (!Board.endGame) c.changeTitle("Connect Four - Red's Turn - Game in Progress");
				}else{
					currentPlayer = Piece.BLUE;
					if (!Board.endGame) c.changeTitle("Connect Four - Blue's Turn - Game in Progress");


				}
			}
		}
		
		//check state of board, whether there is a winner
		if (checkMove(brd) != Piece.EMPTY){
			if (!c.getMode().equals("setup"))	displayWinner(checkMove(brd), c);
			else{
				displayMoveError();
				brd.removePiece(col-1);
			}
		}
		
		//if game is drawn, display draw message
		if (brd.isTie()){
			displayDraw(c);
			c.endGame();
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
		if (checkMove(brd) != Piece.EMPTY ){
			if (checkMove(brd) == Piece.BLUE)
				//max player is winning
				return 255 - depth;
			//min player is winning
			else return 0 + depth; 
		}
		//depth of search will be 4, so evaluate value of move when depth = 4
		if (depth == 4 || brd.isTie()){
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
	 * Checks whether there is an imbalance on the board after End State button is pressed
	 * @param brd   - BoardArray for Piece checking
	 * @param b     - Board for display
	 */
	public static void checkState(BoardArray brd, Board b){
			if (Game.firstPlayer == Piece.BLUE && brd.countBlue() > brd.countRed()){
				displayCountError(Piece.BLUE, new JOptionPane());
				return;
			}else if (Game.firstPlayer == Piece.BLUE && brd.countBlue() < brd.countRed()){
				displayCountError(Piece.RED, new JOptionPane());
				return;
			}else if (Game.firstPlayer == Piece.RED && brd.countBlue() > brd.countRed()){
				displayCountError(Piece.BLUE, new JOptionPane());
				return;
			}else if (Game.firstPlayer == Piece.RED && brd.countBlue() < brd.countRed()){
				displayCountError(Piece.RED, new JOptionPane());
				return;
			}
			//show message and continue the game from setup phase
			JOptionPane.showMessageDialog(new JOptionPane(), "Setup complete, start game.");
			//sets the current player to the first player so that piece balance is maintained
			currentPlayer = firstPlayer;
			//set mode to player for the game to continue
			b.setMode("player");
			b.updatePanelAfterSetup();
	}
	

	/**
	 * Displays an error if there is an imbalance in the game
	 * @param player - The player with more pieces
	 * @param b	     - Board for display
	 */
	private static void displayCountError(Piece player, JOptionPane b){
		if (player == Piece.RED){
			JOptionPane.showMessageDialog(b, "Uneven amount of game pieces, too many red pieces", "Error.", JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(b, "Uneven amount of game pieces, too many blue pieces", "Error.", JOptionPane.ERROR_MESSAGE);
		}
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
	
	/**
	 * This method saves the game to a file, only one save state is allowed.
	 * Save is permanent until overwritten with another "save"
	 * @param brd - BoardArray for reading
	 * @param b   - Board for display
	 * @throws FileNotFoundException
	 */
	
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
		
		BoardArray loadedBoard = new BoardArray(6,7);
		
		//reads the first player
		String pl = in.nextLine();
		
		//reads the mode and set mode
		String mode = in.nextLine();
		b.setMode(mode);
		
		int row = 0;
		int col = 0;
		
		//reads the second line for how the board pieces are setup
		while(in.hasNext()){
			String t = in.next();
//			System.out.println(t);
			if (t.equals("RED")) loadedBoard.setPiece(row, col, Piece.RED); 
			else if (t.equals("BLUE")) loadedBoard.setPiece(row, col, Piece.BLUE);
			else if (t.equals("EMPTY")) loadedBoard.setPiece(row, col, Piece.EMPTY);

			if (col == 6){
				row ++;
				col = 0;
				continue;
			}
			col++;
		}
		
		

		//checks if the game is already won, if it is, display error and reset the board, if not, display game resumed
		if (checkMove(loadedBoard) != Piece.EMPTY) {
			JOptionPane.showMessageDialog(b, "Game loaded is already won, cannot load", "Load Error", JOptionPane.ERROR_MESSAGE);
//			brd.newGame();
		}else if (brd.isTie()){
			JOptionPane.showMessageDialog(b, "Game loaded is drawn, cannot load", "Load error", JOptionPane.ERROR_MESSAGE);
		}else{
			brd.clone(loadedBoard);
			
			//sets current player
			if (pl.equals("RED")) currentPlayer = Piece.RED;
			else if (pl.equals("BLUE")) currentPlayer = Piece.BLUE; 
			
			//update messages and update title
			JOptionPane.showMessageDialog(b, "Game resumed.");
			b.changeTitle("Connect Four - " + currentPlayer + "'s Turn - Game in Progress");
			
			if (Board.endGame) Board.endGame = false;
		}
		
		in.close();
		b.repaint();
	}
	
	/**
	 * This method is for displaying an error when making illegal moves
	 * @param b - Board for display
	 */
	private static void displayIllegalMove(Board b){
		JOptionPane.showMessageDialog(b, "Illegal Move, try again");
	}
	
	/**
	 * This method displays a game drawn message when the game is in draw state
	 * @param p - A component that the pop up message needs
	 */
		private static void displayDraw(Board b){
			b.changeTitle("Connect Four - Game Drawn");
			JOptionPane.showMessageDialog(b, "Game Draw", "Game Drawn", JOptionPane.PLAIN_MESSAGE);
		}
		
	/**
	 * This method displays the winner of the game
	 * @param player - The winning player
	 * @param b      - Board for display
	 */
	public static void displayWinner(Piece player, Board b){
		if (b.getMode().equals("AI")){
			if (player == Piece.RED){
				b.changeTitle("Connect Four - AI Wins");
				JOptionPane.showMessageDialog(b, "You lose ):", "You Lose", JOptionPane.PLAIN_MESSAGE);
			}else{
				b.changeTitle("ConnectFour - You Win!");
				JOptionPane.showMessageDialog(b, "You Win!", "Winner!", JOptionPane.PLAIN_MESSAGE);
			}
		}else{
			if (player == Piece.RED){
				b.changeTitle("Connect Four - Red Wins");
				JOptionPane.showMessageDialog(b, "Red wins", "Winner!", JOptionPane.PLAIN_MESSAGE);

			}else{
				b.changeTitle("Connect Four - Blue Wins");
				JOptionPane.showMessageDialog(b, "Blue wins", "Winner!", JOptionPane.PLAIN_MESSAGE);
			}
		}
		b.endGame();
	}

	/**
	 * This method checks whether there is a winner on the board, if there is, return that player
	 * @param brd - BoardArray for Piece checking
	 * @return the Piece associated with the winning player, or EMPTY if there's no winner
	 */
	public static Piece checkMove(BoardArray brd){
		//checks the row 
		for (int row=0; row<brd.getRow(); row++) {
			for (int col=0; col<brd.getCol()-3; col++) {
				Piece current = brd.getPiece(row, col);
				if (current!=Piece.EMPTY 
						&& current == brd.getPiece(row,col+1) 
						&& current == brd.getPiece(row, col+2) 
						&& current == brd.getPiece(row, col+3)) {
					return current;
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
					return current;
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
					return current;
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
					return current;
				}
			}
		}
		
		return Piece.EMPTY;
		
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
	public static void main(String args[]) throws FileNotFoundException{
		BoardArray a= new BoardArray(6,7);
		a.setPiece(1, 2, Piece.BLUE);
		resume(a, new Board("player"));
	}
}
